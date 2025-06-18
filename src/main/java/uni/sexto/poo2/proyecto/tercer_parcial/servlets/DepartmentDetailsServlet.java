package uni.sexto.poo2.proyecto.tercer_parcial.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uni.sexto.poo2.proyecto.tercer_parcial.dao.DepartmentContext;
import uni.sexto.poo2.proyecto.tercer_parcial.dao.EmployeeContext;
import uni.sexto.poo2.proyecto.tercer_parcial.db_classes.Department;
import uni.sexto.poo2.proyecto.tercer_parcial.db_classes.Employee;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "Department_details", value = "/Department_details")
public class DepartmentDetailsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int page = 1;
        int size = 50;

        if (req.getParameter("page") != null) {
            page = Integer.parseInt(req.getParameter("page"));
        }

        String deptNo = req.getParameter("dept_no");

        String username = getServletContext().getInitParameter("username");
        String password = getServletContext().getInitParameter("password");
        String url = getServletContext().getInitParameter("connectionURL");

        try{
            DepartmentContext deptCtx = new DepartmentContext(username, password, url);

            ResultSet rs = deptCtx.getAllEmployeesFromDepartment(deptNo);
            List<Employee> all = new java.util.ArrayList<>();

            while (rs.next()) {
                Employee emp = new Employee(
                        rs.getInt("emp_no"),
                        rs.getString("birth_date"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("gender"),
                        rs.getString("hire_date"),
                        rs.getInt("salary"),
                        rs.getString("dept_no")
                );
                all.add(emp);
            }

            deptCtx.close();

            List<Employee> pageItems = all.stream()
                    .skip((page - 1) * size)
                    .limit(size)
                    .collect(Collectors.toList());

            req.setAttribute("employees", pageItems);
            req.setAttribute("currentPage", page);
            req.setAttribute("hasNext", (page * size) < all.size());

            req.setAttribute("dept_no", deptNo);

            RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/department_details.jsp");
            dispatcher.forward(req, resp);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
