package lesson3.counter;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Counter {
    private int counter;
    private final Lock lock;

    public Counter () {
        this.lock = new ReentrantLock();
    }

    public void increaseCounter() {
        try {
            lock.lock();
            counter++;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public int getCounter() {
        return counter;
    }
}
