package lesson3.counter;

public class CounterApp {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        for (int i = 0; i < 200; i++) {
            new Thread(()->{
                for (int j = 0; j < 1000; j++) {
                    counter.increaseCounter();
                }
            }).start();
        }

        Thread.sleep(1000);
        System.out.println(counter.getCounter());
    }
}
