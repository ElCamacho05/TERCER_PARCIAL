package uni.sexto.poo2.proyecto.tercer_parcial.dao;

import uni.sexto.poo2.proyecto.tercer_parcial.db_classes.Dept_emp;

import java.sql.*;

public class Dept_empContext {
    private final Connection connection;

    public Dept_empContext(String user, String password, String url)
            throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        this.connection = DriverManager.getConnection(url, user, password);
    }

    public boolean addDept_emp(Dept_emp dept) {
        String sql = getInsertDept_empStatement(dept);
        try (PreparedStatement prepared = connection.prepareStatement(sql)) {
            prepared.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean editDept_emp(int emp_no, Dept_emp dept) {
        String sql = getUpdateDept_empStatement(emp_no, dept);
        try (PreparedStatement prepared = connection.prepareStatement(sql)) {
            prepared.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ResultSet getAllDept_emps() {
        String sql = getAllDept_empsStatement();
        try {
            PreparedStatement prepared = connection.prepareStatement(sql);
            return prepared.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet getDept_empById(int emp_no, int dept_no) {
        String sql = getDept_empByIdStatement(emp_no, dept_no);
        try {
            PreparedStatement prepared = connection.prepareStatement(sql);
            return prepared.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    // MySQL string formated methods

    private String getInsertDept_empStatement(Dept_emp dept) {
        String template = "INSERT INTO dept_emp (emp_no, dept_no, from_date, to_date) " +
                "VALUES (%d, '%s', %s, '%s')";
        return String.format(template,
                dept.getEmp_no(),
                dept.getDept_no(),
                dept.getFrom_date(),
                dept.getTo_date()
        );
    }

    private String getUpdateDept_empStatement(int emp_no, Dept_emp dept) {
        String template = "UPDATE dept_emp SET dept_no = '%s', from_date = '%s', to_date = '%s' WHERE emp_no = %d";
        return String.format(template,
                dept.getDept_no(),
                dept.getFrom_date(),
                dept.getTo_date(),
                emp_no
        );
    }

    private String getAllDept_empsStatement() {
        return "SELECT emp_no, dept_no, from_date, to_date FROM dept_emp";
    }

    private String getDept_empByIdStatement(int emp_no, int dept_no) {
        return "SELECT emp_no, dept_no, from_date, to_date WHERE emp_no = " + emp_no + " AND dept_no = " + dept_no;
    }

    // close connection
    public void close() throws SQLException{
        connection.close();
    }

}

