package uni.sexto.poo2.proyecto.tercer_parcial.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;


import java.io.IOException;

import java.sql.*;
import java.util.List;
import java.util.stream.Collectors;


import uni.sexto.poo2.proyecto.tercer_parcial.db_classes.*;
import uni.sexto.poo2.proyecto.tercer_parcial.dao.EmployeeContext;

@WebServlet(name = "Employee_details", value = "/Employee_details")

public class EmployeeDetailsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int empNo = Integer.parseInt(req.getParameter("emp_no"));

        String username = getServletContext().getInitParameter("username");
        String password = getServletContext().getInitParameter("password");
        String url = getServletContext().getInitParameter("connectionURL");
        try {
            EmployeeContext ctx = new EmployeeContext(username, password, url);
            ResultSet rs = ctx.getEmployeeDetails(empNo);

            if (rs.next()) {
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
                req.setAttribute("employee", emp);
            }


            RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/employee_details.jsp");
            dispatcher.forward(req, resp);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}