package lesson4;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CinemaClass {

    private final long MILLISECONDS_IN_MINUTE = 60_000;

    private final String HOST = "localhost";
    private final String DB_NAME = "cinema";
    private final String DB_USER = "cinemaapp";
    private final String DB_PASSWORD = "1234";

    private final String SESSION_TBL = "session_tbl";
    private final String MOVIE_TBL = "movie_tbl";

    private final String ID_FLD = "id";
    private final String MOVIE_ID_FLD = "movie_id";
    private final String TITLE_FLD = "title_fld";
    private final String DATE_FLD = "date_fld";
    private final String TIME_FLD = "time_fld";
    private final String DURATION_FLD = "duration_fld";
    private final String PRICE_FLD = "price_fld";

    private final String SELECT_ALL_MOVIES_SQL =
            "SELECT s." + ID_FLD + ", " + TITLE_FLD + ", " + DATE_FLD + ", " + TIME_FLD + ", " + DURATION_FLD + ", " + PRICE_FLD + " \n" +
                    "FROM " + SESSION_TBL + " AS s JOIN " + MOVIE_TBL + " AS m \n" +
                    "WHERE s." + MOVIE_ID_FLD + " = m.id \n";

    private Connection conn;
    private Statement statement;

    public CinemaClass() {
        try {
            conn = DriverManager.getConnection(String.format("jdbc:mysql://%s/%s?user=%s&password=%s&serverTimezone=UTC&useSSL=false",
                    HOST, DB_NAME, DB_USER, DB_PASSWORD));
            statement = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Session> findAllSession() {
        List<Session> sessions = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_MOVIES_SQL);
            while (resultSet.next()) {
                sessions.add(
                        new Session(
                                resultSet.getLong(ID_FLD),
                                resultSet.getString(TITLE_FLD),
                                resultSet.getDate(DATE_FLD),
                                resultSet.getTime(TIME_FLD),
                                resultSet.getInt(DURATION_FLD),
                                resultSet.getInt(PRICE_FLD)
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sessions;
    }

    public List<Session> findSessionCollisions(Session session){
        Time sessionStart = session.getTime();
        Time sessionEnd = new Time(sessionStart.getTime() + (long) session.getDuration() * MILLISECONDS_IN_MINUTE);
        List<Session> collisions = new ArrayList<>();
        for (Session s : findAllSession()) {
            Time start = s.getTime();
            if ((start.after(sessionStart)&&start.before(sessionEnd))){
                collisions.add(s);
            }
        }
        return collisions;
    }
}
