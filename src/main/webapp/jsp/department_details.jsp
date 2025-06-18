<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <title>Detalles del Departamento</title>
</head>
<body>
<h2>Detalles del Departamento</h2>

<div>
  <c:if test="${currentPage > 1}">
    <a href="Department_details?page=${currentPage - 1}&dept_no=${dept_no}"> <- Anterior </a>
  </c:if>
  Página ${currentPage}
  <c:if test="${hasNext}">
    <a href="Department_details?page=${currentPage + 1}&dept_no=${dept_no}"> Siguiente -></a>
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
    <th>salary</th>
    <th>dept_no</th>
  </tr>
  <c:forEach var="e" items="${employees}">
    <tr>
      <td><a href="Employee_details?emp_no=${e.emp_no}">${e.emp_no}</a></td>
      <td>${e.birth_date}</td>
      <td>${e.first_name}</td>
      <td>${e.last_name}</td>
      <td>${e.gender}</td>
      <td>${e.hire_date}</td>
      <td>${e.salary}</td>
      <td>${e.dept_no}</td>
    </tr>
  </c:forEach>
</table>

</body>
</html>
