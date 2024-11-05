package Philosophers;

public class Table {
    private int nbrOfChopsticks;
    private boolean chopstick[];

    public Table(int nbrOfSticks) {
        nbrOfChopsticks = nbrOfSticks;
        chopstick = new boolean[nbrOfChopsticks];
        for (int i = 0; i < nbrOfChopsticks; i++) {
            chopstick[i] = true;
        }
    }
    public synchronized void getLeftChopstick(int n) throws InterruptedException {
        while (!chopstick[n]) {
            wait();
        }
        chopstick[n] = false;
        System.out.println("Philosopher " + n + " picked up left chopstick " + n);
    }
    public synchronized void getRightChopstick(int n) throws InterruptedException {
        int pos = n + 1;
        if (pos == nbrOfChopsticks)
            pos = 0;
        while (!chopstick[pos]) {
            wait();
        }
        chopstick[pos] = false;
        System.out.println("Philosopher " + n + " picked up right chopstick " + pos);
    }
    public synchronized void releaseLeftChopstick(int n) {
        chopstick[n] = true;
        System.out.println("Philosopher " + n + " released left chopstick " + n);
        notifyAll();
    }
    public synchronized void releaseRightChopstick(int n) {
        int pos = n + 1;
        if (pos == nbrOfChopsticks)
            pos = 0;
        chopstick[pos] = true;
        System.out.println("Philosopher " + n + " released right chopstick " + pos);
        notifyAll();
    }
}