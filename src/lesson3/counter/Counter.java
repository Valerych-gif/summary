package lesson3.counter;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Counter {
    private int counter;
    private Lock lock = new ReentrantLock();

    public void increaseCounter() {
        lock.lock();
        counter++;
        lock.unlock();
    }

    public int getCounter() {
        return counter;
    }
}
