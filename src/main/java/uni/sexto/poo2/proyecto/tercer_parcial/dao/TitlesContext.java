package uni.sexto.poo2.proyecto.tercer_parcial.dao;

import uni.sexto.poo2.proyecto.tercer_parcial.db_classes.*;
import java.sql.*;

public class TitlesContext {
    private final Connection connection;

    public TitlesContext(String user, String password, String url)
            throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        this.connection = DriverManager.getConnection(url, user, password);
    }

    public boolean addTitle(Title title) {
        String sql = getInsertTitlesStatement(title);
        try (PreparedStatement prepared = connection.prepareStatement(sql)) {
            prepared.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean editTitle(String emp_no, String title, String from_date, Title T) {
        String sql = getUpdateTitlesStatement(emp_no, title, from_date, T);
        try (PreparedStatement prepared = connection.prepareStatement(sql)) {
            prepared.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ResultSet getAllTitles() {
        String sql = getAllTitlesStatement();
        try {
            PreparedStatement prepared = connection.prepareStatement(sql);
            return prepared.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet getTitlesById(String emp_no, String title, String from_date) {
        String sql = getTitlesByIdStatement(emp_no, title, from_date);
        try {
            PreparedStatement prepared = connection.prepareStatement(sql);
            return prepared.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    // MySQL string formated methods

    private String getInsertTitlesStatement(Title title) {
        String template = "INSERT INTO Titles (emp_no, title, from_date, to_date) " +
                "VALUES (%d, '%s', '%s', '%s')";
        return String.format(template,
                title.getEmp_no(),
                title.getTitle(),
                title.getFrom_date(),
                title.getTo_date()
        );
    }

    private String getUpdateTitlesStatement(String emp_no, String title, String from_date, Title T) {
        String template = "UPDATE Titles SET to_date = '%d' WHERE emp_no = %d AND title = '%s' AND from_date = '%s'";
        return String.format(template,
                T.getTo_date(),
                emp_no,
                title,
                from_date
        );
    }

    private String getAllTitlesStatement() {
        return "SELECT emp_no, title, from_date, to_date FROM Titles";
    }

    private String getTitlesByIdStatement(String emp_no, String title, String from_date) {
        String template = "SELECT emp_no, title, from_date, to_date FROM Titles WHERE emp_no = %d AND title = '%s' AND from_date = '%s' AND from_date = '%s'";
        return String.format(template,
                emp_no,
                title,
                from_date

        );
    }

    // close connection
    public void close() throws SQLException{
        connection.close();
    }

}

