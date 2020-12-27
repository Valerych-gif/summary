package lesson4;

import java.sql.Date;
import java.sql.Time;

public class Session {
    private final Long id;
    private final String title;
    private final Date date;
    private final Time time;
    private final int duration;
    private final int price;

    public Session(Long id, String title, Date date, Time time, int duration, int price) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.time = time;
        this.duration = duration;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Date getDate() {
        return date;
    }

    public Time getTime() {
        return time;
    }

    public int getDuration() {
        return duration;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", duration=" + duration +
                ", price=" + price +
                '}';
    }
}
