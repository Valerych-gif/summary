package lesson3.pingpong;

public class PingPongClass {
    enum State{
        PING,
        PONG
    }
    private State state;
    private int counter;

    synchronized void ping(){
        state = State.PING;
        notify();
        while (state == State.PING) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(++counter + " Ping");
    }

    synchronized void pong() {
        state = State.PONG;
        notify();
        while (state == State.PONG) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(++counter + " Pong");
    }
}
