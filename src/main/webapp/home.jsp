<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cassandra and JBoss Example</title>
</head>
<body>
<div style="width: 100%">
<c:if test="${not empty user}">
	<div style="width:40%;border:1px solid #ccc">
		You have selected<br />
		Name: ${user.name}<br />
		Last Name: ${user.lastname}<br />
		Email: ${user.email}
	</div>
</c:if>
<div style="width:30%;">
<table id="table">
	<thead >
		<tr>
			<th>Name</th>
			<th>Last Name</th>
			<th>Email</th>
			<th>&nbsp;</th>
		</tr>
	</thead>
	<tbody>
	<c:forEach var="u" items="${users}">
			<tr>
				<td>${u.name}</td>
				<td>${u.lastname}</td>
				<td>${u.email}</td>
				<td><a href="?email=${u.email}">select</a></td>
			</tr>
	</c:forEach>

	</tbody>
</table>
</div>




</div>
</body>
</html>