package uni.sexto.poo2.proyecto.tercer_parcial;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;


import java.io.IOException;

import java.util.List;
import java.util.stream.Collectors;


import uni.sexto.poo2.proyecto.tercer_parcial.db_classes.*;
import uni.sexto.poo2.proyecto.tercer_parcial.dao.EmployeeContext;


@WebServlet(name = "Employees_servlet", value = "/Employees_servlet")
public class EmployeeServlet extends HttpServlet {
    private String message;

    public void init() {
        this.message = "Hello World";
    }


    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int page = 1;
        int size = 50;

        if (req.getParameter("page") != null) {
            page = Integer.parseInt(req.getParameter("page"));
        }

        try {
            String username = getServletContext().getInitParameter("username");
            String password = getServletContext().getInitParameter("password");
            String url = getServletContext().getInitParameter("connectionURL");

            EmployeeContext context = new EmployeeContext(username, password, url);

            var resultSet = context.getAllEmployees();
            List<Employee> all = new java.util.ArrayList<>();

            while (resultSet.next()) {
                Employee emp = new Employee(
                        resultSet.getInt("emp_no"),
                        resultSet.getString("birth_date"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("gender"),
                        resultSet.getString("hire_date")
                );
                all.add(emp);
            }

            context.close();

            List<Employee> pageItems = all.stream()
                    .skip((page - 1) * size)
                    .limit(size)
                    .collect(Collectors.toList());

            req.setAttribute("employees", pageItems);
            req.setAttribute("currentPage", page);
            req.setAttribute("hasNext", (page * size) < all.size());

            RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/employees.jsp");
            dispatcher.forward(req, resp);

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
