<%@page import="org.soenea.assignments.domain.model.group.IGroup"%>
<%@page import="org.soenea.assignments.domain.model.person.IPerson"%>
<%@page import="org.soenea.assignments.domain.model.invite.IInvite"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>BrowseInvites</title>
</head>
<body>
	<c:if test="${!empty warning3}">
		<b>${warning3}</b>
	</c:if>
	<c:if test="${!empty warning4}">
		<b>${warning4}</b>
	</c:if>
	<c:if test="${! empty sessionScope.userName}">
		<a href="BrowseGroup">Go Back</a>
		<h2>Hi, ${userName.getFirstName()} You are a user without a group</h2>
		<h2>Here is a list Of Your Invites Click on one to accept it.</h2>

		<c:forEach items="${Invites}" var="invite">
			
			<p>${invite.getUserId()}: From Group:${invite.getGroupId()}</p>		
			<br />
		</c:forEach>			
	</c:if>

	<c:if test="${empty sessionScope.userName}">
		<h2>You must log in!!</h2>
		<a href="http://snape.encs.concordia.ca/soen3872013a/servlet/">Login</a>
	</c:if>
</body>
</html>