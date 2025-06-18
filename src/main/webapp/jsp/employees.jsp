<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Lista de Empleados</title>
</head>
<body>
<h2>Empleados</h2>

<form action="Employee_details" method="get">
    <label for="emp_no">Buscar empleado:</label>
    <input type="text" name="emp_no" id="emp_no">
    <button type="submit">Buscar</button>
</form>

<div>
    <c:if test="${currentPage > 1}">
        <a href="Employees_servlet?page=${currentPage - 1}"> <- Anterior </a>
    </c:if>
    Pagina ${currentPage}
    <c:if test="${hasNext}">
        <a href="Employees_servlet?page=${currentPage + 1}"> Siguiente -></a>
    </c:if>
</div>
<table border="1">
    <tr>
        <th>emp_no</th>
        <th>birth_date</th>
        <th>first_name</th>
        <th>last_name</th>
        <th>gender</th>
        <th>hire_date</th>
    </tr>
    <c:forEach var="e" items="${employees}">
        <tr>
            <td><a href="Employee_details?emp_no=${e.emp_no}">${e.emp_no}</a></td>
            <td>${e.birth_date}</td>
            <td>${e.first_name}</td>
            <td>${e.last_name}</td>
            <td>${e.gender}</td>
            <td>${e.hire_date}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
