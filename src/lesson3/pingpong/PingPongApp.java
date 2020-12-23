package lesson3.pingpong;

public class PingPongApp {

    public static void main(String[] args) {
        PingPongClass pp = new PingPongClass();
        Thread t1 = new Thread(()->{
        while (true) {
                pp.ping();
            }
        });
        Thread t2 = new Thread(()->{
            while (true) {
                pp.pong();
            }
        });
        t1.start();
        t2.start();
    }
}
