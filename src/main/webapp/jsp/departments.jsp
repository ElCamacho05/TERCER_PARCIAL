<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <title>Lista de Departamentos</title>
</head>
<body>
<h2>Departamentos</h2>
<div>
  <c:if test="${currentPage > 1}">
    <a href="Departments_servlet?page=${currentPage - 1}"> <- Anterior </a>
  </c:if>
  Pagina ${currentPage}
  <c:if test="${hasNext}">
    <a href="Departments_servlet?page=${currentPage + 1}"> Siguiente -></a>
  </c:if>
</div>
<table border="1">
  <tr>
    <th>dept_no</th>
    <th>dept_name</th>
  </tr>
  <c:forEach var="d" items="${departments}">
    <tr>
      <td>${d.dept_no}</td>
      <td>${d.dept_name}</td>
    </tr>
  </c:forEach>
</table>

</body>
</html>
