package uni.sexto.poo2.proyecto.tercer_parcial.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import uni.sexto.poo2.proyecto.tercer_parcial.dao.DepartmentContext;
import uni.sexto.poo2.proyecto.tercer_parcial.db_classes.Department;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


@WebServlet(name = "Departments_servlet", value = "/Departments_servlet")
public class DepartmentServlet extends HttpServlet {
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

            DepartmentContext context = new DepartmentContext(username, password, url);

            var resultSet = context.getAllDepartments();
            List<Department> all = new java.util.ArrayList<>();

            while (resultSet.next()) {
                Department emp = new Department(
                        resultSet.getString("dept_no"),
                        resultSet.getString("dept_name")
                );
                all.add(emp);
            }

            context.close();

            List<Department> pageItems = all.stream()
                    .skip((page - 1) * size)
                    .limit(size)
                    .collect(Collectors.toList());

            req.setAttribute("departments", pageItems);
            req.setAttribute("currentPage", page);
            req.setAttribute("hasNext", (page * size) < all.size());

            RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/departments.jsp");
            dispatcher.forward(req, resp);

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
