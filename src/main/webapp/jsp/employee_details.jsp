<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Detalles del empleado</title>
</head>
<body>
<h2>Detalles del empleado</h2>

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
        <tr>
            <td>${employee.emp_no}</td>
            <td>${employee.birth_date}</td>
            <td>${employee.first_name}</td>
            <td>${employee.last_name}</td>
            <td>${employee.gender}</td>
            <td>${employee.hire_date}</td>
            <td>${employee.salary}</td>
<%--            <td>${employee.dept_no}</td>--%>
            <td><a href="Department_details?dept_no=${employee.dept_no}">${employee.dept_no}</a></td>
        </tr>
</table>

</body>
</html>
