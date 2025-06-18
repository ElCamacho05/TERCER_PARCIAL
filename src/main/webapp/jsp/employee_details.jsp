<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Detalles del empleado</title>
</head>
<body>
<h2>Detalles del empleado</h2>
<%--<div>--%>
<%--    <c:if test="${currentPage > 1}">--%>
<%--        <a href="Employees_servlet?page=${currentPage - 1}"> <- Anterior </a>--%>
<%--    </c:if>--%>
<%--    Pagina ${currentPage}--%>
<%--    <c:if test="${hasNext}">--%>
<%--        <a href="Employees_servlet?page=${currentPage + 1}"> Siguiente -></a>--%>
<%--    </c:if>--%>
<%--</div>--%>
<table border="1">
    <tr>
        <th>emp_no</th>
        <th>birth_date</th>
        <th>first_name</th>
        <th>last_name</th>
        <th>gender</th>
        <th>hire_date</th>
        <th>salary</th>
        <th>dept_no</th>
    </tr>
<%--    <c:forEach var="e" items="${employee}">--%>
        <tr>
            <td>${employee.emp_no}</td>
            <td>${employee.birth_date}</td>
            <td>${employee.first_name}</td>
            <td>${employee.last_name}</td>
            <td>${employee.gender}</td>
            <td>${employee.hire_date}</td>
            <td>${employee.salary}</td>
            <td>${employee.dept_no}</td>
        </tr>
<%--    </c:forEach>--%>
</table>

</body>
</html>
