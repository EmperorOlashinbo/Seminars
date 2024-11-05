package memory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;

public class MemoryManager {
    private int myNumberOfPages;
    private int myPageSize;
    private int myNumberOfFrames;
    private int[] myPageTable;
    private byte[] myRAM;
    private RandomAccessFile myPageFile;
    private int myNextFreeFramePosition = 0;
    private int myNumberOfPageFaults = 0;
    private int myPageReplacementAlgorithm = 0;

    private Queue<Integer> fifoQueue = new LinkedList<>();
    private Map<Integer, Long> accessTimeMap = new HashMap<>();
    private Map<Integer, Integer> pageToFrameMap = new HashMap<>();

    public MemoryManager(int numberOfPages, int pageSize, int numberOfFrames, String pageFile,
                         int pageReplacementAlgorithm) {
        myNumberOfPages = numberOfPages;
        myPageSize = pageSize;
        myNumberOfFrames = numberOfFrames;
        myPageReplacementAlgorithm = pageReplacementAlgorithm;

        initPageTable();
        myRAM = new byte[myNumberOfFrames * myPageSize];

        try {
            myPageFile = new RandomAccessFile(pageFile, "r");
        } catch (FileNotFoundException error) {
            System.out.println("Can't open page file: " + error.getMessage());
        }
    }
    private void initPageTable() {
        myPageTable = new int[myNumberOfPages];
        Arrays.fill(myPageTable, -1);
    }
    public byte readFromMemory(int logicalAddress) {
        int pageNumber = getPageNumber(logicalAddress);
        int offset = getPageOffset(logicalAddress);

        if (myPageTable[pageNumber] == -1) {
            pageFault(pageNumber);
        }
        int frame = myPageTable[pageNumber];

        if (frame == -1) {
            throw new IllegalStateException("Page table entry points to an invalid frame.");
        }
        int physicalAddress = frame * myPageSize + offset;

        if (physicalAddress < 0 || physicalAddress >= myRAM.length) {
            throw new ArrayIndexOutOfBoundsException("Physical address " + physicalAddress + " is out of bounds.");
        }
        byte data = myRAM[physicalAddress];

        if (myPageReplacementAlgorithm == Seminar3.LRU_PAGE_REPLACEMENT) {
            accessTimeMap.put(pageNumber, System.nanoTime());
        }
        System.out.println("Virtual address: " + logicalAddress +
                " Physical address: " + physicalAddress +
                "Value: " + data);
        return data;
    }

    public int getNumberOfPageFaults() {
        return myNumberOfPageFaults;
    }
    private int getPageNumber(int logicalAddress) {
        return logicalAddress / myPageSize;
    }
    private int getPageOffset(int logicalAddress) {
        return logicalAddress % myPageSize;
    }
    private void pageFault(int pageNumber) {
        if (myPageReplacementAlgorithm == Seminar3.NO_PAGE_REPLACEMENT) {
            handlePageFault(pageNumber);
        } else if (myPageReplacementAlgorithm == Seminar3.FIFO_PAGE_REPLACEMENT) {
            handlePageFaultFIFO(pageNumber);
        } else if (myPageReplacementAlgorithm == Seminar3.LRU_PAGE_REPLACEMENT) {
            handlePageFaultLRU(pageNumber);
        }
        readFromPageFileToMemory(pageNumber);
    }
    private void readFromPageFileToMemory(int pageNumber) {
        try {
            int frame = myPageTable[pageNumber];
            if (frame == -1) {
                throw new IllegalStateException("Page table entry points to an invalid frame.");
            }
            myPageFile.seek(pageNumber * myPageSize);
            for (int b = 0; b < myPageSize; b++) {
                myRAM[frame * myPageSize + b] = myPageFile.readByte();
            }
        } catch (IOException error) {
            System.out.println("Error reading from page file: " + error.getMessage());
        } catch (IllegalStateException error) {
            System.out.println("Error: " + error.getMessage());
        }
    }
    public int getMyNumberOfPageFaults() {
        return myNumberOfPageFaults;
    }
    private void handlePageFault(int pageNumber) {
        if (myNextFreeFramePosition < myNumberOfFrames) {
            myPageTable[pageNumber] = myNextFreeFramePosition;
            pageToFrameMap.put(pageNumber, myNextFreeFramePosition);
            myNextFreeFramePosition++;
            myNumberOfPageFaults++;
        }
    }
    private void handlePageFaultFIFO(int pageNumber) {
        if (myNextFreeFramePosition < myNumberOfFrames) {
            myPageTable[pageNumber] = myNextFreeFramePosition;
            fifoQueue.add(pageNumber);
            pageToFrameMap.put(pageNumber, myNextFreeFramePosition);
            myNextFreeFramePosition++;
        } else {
            int oldestPage = fifoQueue.poll();
            Integer frame = pageToFrameMap.remove(oldestPage);
            if (frame == null) {
                throw new IllegalStateException("Tried to remove a page that doesn't exist in memory.");
            }
            myPageTable[oldestPage] = -1;
            myPageTable[pageNumber] = frame;
            pageToFrameMap.put(pageNumber, frame);
            fifoQueue.add(pageNumber);
        }
        myNumberOfPageFaults++;
    }
    private void handlePageFaultLRU(int pageNumber) {
        if (myNextFreeFramePosition < myNumberOfFrames) {
            myPageTable[pageNumber] = myNextFreeFramePosition;
            pageToFrameMap.put(pageNumber, myNextFreeFramePosition);
            myNextFreeFramePosition++;
        } else {
            int lruPage = findLRUPage();
            if (!pageToFrameMap.containsKey(lruPage)) {
                throw new IllegalStateException("Tried to remove an LRU page that doesn't exist in memory.");
            }
            int frame = pageToFrameMap.get(lruPage);
            pageToFrameMap.remove(lruPage);
            accessTimeMap.remove(lruPage);
            myPageTable[lruPage] = -1;
            myPageTable[pageNumber] = frame;
            pageToFrameMap.put(pageNumber, frame);
        }
        accessTimeMap.put(pageNumber, System.nanoTime());
        myNumberOfPageFaults++;
    }
    private int findLRUPage() {
        if (accessTimeMap.isEmpty()) {
            throw new IllegalStateException("No page available to evict in LRU.");
        }
        return accessTimeMap.entrySet().stream()
                .min(Map.Entry.comparingByValue())
                .get().getKey();
    }
}