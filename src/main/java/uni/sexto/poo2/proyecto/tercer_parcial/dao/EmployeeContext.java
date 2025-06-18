package uni.sexto.poo2.proyecto.tercer_parcial.dao;

import uni.sexto.poo2.proyecto.tercer_parcial.db_classes.*;
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

    public ResultSet getEmployeeDetails(int emp_no) {
        String sql = getEmployeeDetailsStatement(emp_no);
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

    private String getEmployeeDetailsStatement(int emp_no) {
        return "SELECT employees.emp_no, employees.birth_date, employees.first_name, employees.last_name, employees.gender, employees.hire_date, salaries.salary, departments.dept_no " +
                "FROM employees " +
                "JOIN salaries on employees.emp_no = salaries.emp_no " +
                "JOIN dept_emp on dept_emp.emp_no = employees.emp_no " +
                "JOIN departments on dept_emp.dept_no = departments.dept_no " +
                "WHERE employees.emp_no = " + emp_no+
                " order by salaries.to_date desc";
    }

    // close connection
    public void close() throws SQLException{
        connection.close();
    }

}