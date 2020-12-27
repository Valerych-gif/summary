package lesson4;

import java.util.List;

public class CinemaApp {
    private static CinemaClass cc;
    public static void main(String[] args) {
        cc = new CinemaClass();
        printCollisions();
    }

    private static void printCollisions() {
        List<Session> sessions = cc.findAllSession();
        for (Session session : sessions){
            List <Session> collisions = cc.findSessionCollisions(session);
            if (collisions.size()!=0) {
                System.out.println("\n" + session.getTitle() + " " + session.getTime() + " " + session.getDuration());
                System.out.println("пересекается в расписании с фильмами:");
                for (Session collision : collisions) {
                    System.out.println(collision.getTitle() + " " + collision.getTime() + " " + collision.getDuration());
                }
            }
        }
    }
}
