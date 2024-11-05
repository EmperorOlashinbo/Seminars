package Philosophers;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Philosopher implements Runnable {
    private int myId;
    private Table myTable;
    private static final int MAX_WAIT_TIME = 1000;

    public Philosopher(int id, Table table) {
        myId = id;
        myTable = table;
    }
    public void run() {
        for (int i = 0; i < 100; i++) {
            try {
                System.out.println("Philosopher " + myId + " thinks. Iteration " + i);
                Thread.sleep((int) (Math.random() * 100));

                long startTime = System.currentTimeMillis();
                myTable.getLeftChopstick(myId);
                long elapseTime = System.currentTimeMillis() - startTime;
                if (elapseTime > MAX_WAIT_TIME) {
                    System.out.println("Philosopher " + myId + " waited too long for left chopstick! ");
                }
                System.out.println("Philosopher " + myId + " pick up left chopstick ");
                Thread.sleep((int)(Math.random() * 10));

                startTime = System.currentTimeMillis();
                myTable.getRightChopstick(myId);
                elapseTime = System.currentTimeMillis() - startTime;
                if (elapseTime > MAX_WAIT_TIME) {
                    System.out.println("Philosopher " + myId + " waited too long for right chopstick!");
                }
                System.out.println("Philosopher " + myId + " pick up right chopstick ");
                System.out.println("Philosopher " + myId + " eats. Iteration " + i);
                Thread.sleep((int)(Math.random() * 100));

                myTable.releaseLeftChopstick(myId);
                System.out.println("Philosopher " + myId + " drop left chopstick ");
                Thread.sleep((int)(Math.random() * 10));

                myTable.releaseRightChopstick(myId);
                System.out.println("Philosopher " + myId + " drop right chopstick ");
                Thread.sleep((int)(Math.random() * 10));
            } catch (InterruptedException ex) {
                Logger.getLogger(Philosopher.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

