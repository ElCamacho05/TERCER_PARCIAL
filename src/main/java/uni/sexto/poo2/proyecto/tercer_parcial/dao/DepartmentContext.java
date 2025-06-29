package uni.sexto.poo2.proyecto.tercer_parcial.dao;

import uni.sexto.poo2.proyecto.tercer_parcial.db_classes.*;
import java.sql.*;

public class DepartmentContext {
    private final Connection connection;

    public DepartmentContext(String user, String password, String url)
            throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        this.connection = DriverManager.getConnection(url, user, password);
    }

    public boolean addDepartment(Department dept) {
        String sql = getInsertDepartmentStatement(dept);
        try (PreparedStatement prepared = connection.prepareStatement(sql)) {
            prepared.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean editDepartment(String dept_no, Department dept) {
        String sql = getUpdateDepartmentStatement(dept_no, dept);
        try (PreparedStatement prepared = connection.prepareStatement(sql)) {
            prepared.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ResultSet getAllDepartments() {
        String sql = getAllDepartmentsStatement();
        try {
            PreparedStatement prepared = connection.prepareStatement(sql);
            return prepared.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet getDepartmentById(String dept_no) {
        String sql = getDepartmentByIdStatement(dept_no);
        try {
            PreparedStatement prepared = connection.prepareStatement(sql);
            return prepared.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet getAllEmployeesFromDepartment(String dept_no) {
        String sql = getAllEmployeesFromDepartmentStatement(dept_no);
        try {
            PreparedStatement prepared = connection.prepareStatement(sql);
            return prepared.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    // MySQL string formated methods

    private String getInsertDepartmentStatement(Department dept) {
        String template = "INSERT INTO Departments (dept_no, dept_name) " +
                "VALUES ('%s', '%s')";
        return String.format(template,
                dept.getDept_no(),
                dept.getDept_name()
        );
    }

    private String getUpdateDepartmentStatement(String dept_no, Department dept) {
        String template = "UPDATE Departments SET dept_name='%s' WHERE dept_no = '%s'";
        return String.format(template,
                dept.getDept_no(),
                dept.getDept_name()
        );
    }

    private String getAllDepartmentsStatement() {
        return "SELECT dept_no, dept_name FROM Departments";
    }

    private String getDepartmentByIdStatement(String dept_no) {
        String template = "SELECT dept_no, dept_name FROM Department WHERE dept_no = '%s'";
        return String.format(template,
                dept_no
        );
    }

    private String getAllEmployeesFromDepartmentStatement(String dept_no) {
        String template = "SELECT employees.emp_no, employees.birth_date, employees.first_name, employees.last_name, employees.gender, employees.hire_date, salaries.salary, departments.dept_no " +
                "from departments " +
                "join dept_emp on departments.dept_no = dept_emp.dept_no " +
                "join employees on employees.emp_no = dept_emp.emp_no " +
                "join salaries on salaries.emp_no = employees.emp_no " +
                "where departments.dept_no like '%s';";
        return String.format(template,
                dept_no
        );
    }

    // close connection
    public void close() throws SQLException{
        connection.close();
    }

}

