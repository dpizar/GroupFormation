<%@page import="org.soenea.assignments.domain.model.group.IGroup"%>
<%@page import="org.soenea.assignments.domain.model.person.IPerson"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>hope it works</title>
</head>
<body>
	<c:if test="${!empty warning3}">
		<b>${warning3}</b>
	</c:if>
	<c:if test="${!empty warning4}">
		<b>${warning4}</b>
	</c:if>
	<c:if test="${! empty sessionScope.userName}">
		<a href="logout">LogOut</a>
		<h2>Hi, ${userName.getFirstName()} You are a user without a group</h2>
		<h2>Here is a list of groups</h2>

		<c:forEach items="${Groups}" var="group">
			<a href="ViewGroup?groupName=${group.getgroupName()}">
			${group.getgroupName()}</a>
			<br />
		</c:forEach>
						
		<form method="POST"	action="CreateGroup">
			<p>Create your own Group:</p>
			<p>
				<b>GroupName:</b> <input type="text" name="groupname" size="10">
			</p>
			<p>
				<input type="submit" value="Submit" name="submit"> <input type="reset" value="Reset" name="reset">
			</p>
		</form>
	</c:if>
	
	<a href="BrowseInvites">Click Here to Browse Invites</a>
	
	<c:if test="${empty sessionScope.userName}">
		<h2>You must log in!!</h2>
		<a href="http://snape.encs.concordia.ca/soen3872013a/servlet/">Login</a>
	</c:if>
</body>
</html>