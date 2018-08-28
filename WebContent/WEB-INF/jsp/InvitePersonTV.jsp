<%@page import="org.soenea.assignments.domain.model.person.IPerson"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<c:if test="${! empty sessionScope.userName}">
		<h2>View Person</h2>
		<a href="logout">LogOut</a>
		<p>
			Your Invitation has been sent.		
		</p>
		<br />
		<a href="BrowsePeople">Browse More People</a>
		<br />
	</c:if>
	<c:if test="${empty sessionScope.userName}">
		<h2>You must log in!!</h2>
		<a href="http://snape.encs.concordia.ca/soen3872013a/servlet/">Login</a>
	</c:if>
</body>
</html>