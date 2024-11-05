package ReaderWriter;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class RWLock {
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);
    private final ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
    private final ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();

    public RWLock() {

    }
    public void acquireRead() {
        readLock.lock();
    }
    public void acquireWrite() {
        writeLock.lock();
    }
    public void releaseRead() {
        readLock.unlock();
    }
    public void releaseWrite() {
        writeLock.unlock();
    }
}


