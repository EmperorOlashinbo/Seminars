package ReaderWriter;

public class RWLock {
    private int readers = 0;
    private int writers = 0;
    private int writeRequests = 0;

    public RWLock() {

    }
    public synchronized void acquireRead() {
        try {
            while (writers > 0 || writeRequests > 0) {
                wait();
            }
            readers++;
        } catch (InterruptedException error) {
            Thread.currentThread().interrupt();
        }
    }
    public synchronized void acquireWrite() {
        try {
            writeRequests++;
            while (readers > 0 || writers > 0) {
                wait();
            }
            writeRequests--;
            writers++;
        } catch (InterruptedException error) {
            Thread.currentThread().interrupt();
        }
    }
    public synchronized void releaseRead() {
        readers--;
        if (readers == 0) {
            notifyAll();
        }
    }
    public synchronized void releaseWrite() {
        writers--;
        notifyAll();
    }
}
