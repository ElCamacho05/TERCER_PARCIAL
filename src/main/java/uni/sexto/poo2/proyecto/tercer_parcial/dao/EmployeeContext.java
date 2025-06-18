package uni.sexto.poo2.proyecto.tercer_parcial.DB_Classes.dao;

import uni.sexto.poo2.proyecto.tercer_parcial.DB_Classes.*;
import java.sql.*;

public class EmployeeContext {
    private final Connection connection;

    public EmployeeContext(String user, String password, String url)
            throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        this.connection = DriverManager.getConnection(url, user, password);
    }

    public boolean addEmployee(Employee emp) {
        String sql = getInsertEmployeeStatement(emp);
        try (PreparedStatement prepared = connection.prepareStatement(sql)) {
            prepared.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean editEmployee(int emp_no, Employee emp) {
        String sql = getUpdateEmployeeStatement(emp_no, emp);
        try (PreparedStatement prepared = connection.prepareStatement(sql)) {
            prepared.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ResultSet getAllEmployees() {
        String sql = getAllEmployeesStatement();
        try {
            PreparedStatement prepared = connection.prepareStatement(sql);
            return prepared.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet getEmployeeById(int emp_no) {
        String sql = getEmployeeByIdStatement(emp_no);
        try {
            PreparedStatement prepared = connection.prepareStatement(sql);
            return prepared.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    // MySQL string formated methods

    private String getInsertEmployeeStatement(Employee emp) {
        String template = "INSERT INTO employees (emp_no, birth_date, first_name, last_name, gender, hire_date) " +
                "VALUES (%d, '%s', '%s', '%s', '%s', '%s')";
        return String.format(template,
                emp.getEmp_no(),
                emp.getBirth_date(),
                emp.getFirst_name(),
                emp.getLast_name(),
                emp.getGender(),
                emp.getHire_date()
        );
    }

    private String getUpdateEmployeeStatement(int emp_no, Employee emp) {
        String template = "UPDATE employees SET birth_date='%s', first_name='%s', last_name='%s', " +
                "gender='%s', hire_date='%s' WHERE emp_no = %d";
        return String.format(template,
                emp.getBirth_date(),
                emp.getFirst_name(),
                emp.getLast_name(),
                emp.getGender(),
                emp.getHire_date(),
                emp_no
        );
    }

    private String getAllEmployeesStatement() {
        return "SELECT emp_no, birth_date, first_name, last_name, gender, hire_date FROM employees";
    }

    private String getEmployeeByIdStatement(int emp_no) {
        return "SELECT emp_no, birth_date, first_name, last_name, gender, hire_date FROM employees WHERE emp_no = " + emp_no;
    }

    // close connection
    public void close() throws SQLException{
        connection.close();
    }

}