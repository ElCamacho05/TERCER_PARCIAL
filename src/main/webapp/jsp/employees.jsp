<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Lista de Empleados</title>
</head>
<body>
<h2>Empleados</h2>
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
            <td>${e.emp_no}</td>
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
