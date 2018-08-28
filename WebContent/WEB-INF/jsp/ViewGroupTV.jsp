<%@page import="org.soenea.assignments.domain.model.person.IPerson"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>View Person</title>
</head>
<body>

	<c:if test="${! empty sessionScope.userName}">
		<h2>View Person</h2>
		<a href="logout">LogOut</a>
		<h3>
			List of Users in group, ${Grp}:
		</h3>
		
		<c:forEach items="${GrpUser}" var="person">
			<h4>${person.getFirstName()} ${person.getLastName()}</h4><br/>
		</c:forEach>
		<a href="BrowseGroup">Return</a><br/>
	</c:if>

	<c:if test="${empty sessionScope.userName}">
		<h2>You must log in!!</h2>
		<a href="http://snape.encs.concordia.ca/soen3872013a/servlet/">Login</a>
	</c:if>
</body>
</html>