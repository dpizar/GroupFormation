<%@page import="org.soenea.assignments.domain.model.person.IPerson"%>
<%@page import="org.soenea.assignments.domain.model.group.IGroup"%>
<%@page import="org.soenea.assignments.domain.model.invite.IInvite"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Browse People</title>
</head>
<body>
	<c:if test="${empty sessionScope.userName}">
		<h2>You must log in!!</h2>
		<a href="http://snape.encs.concordia.ca/soen3872013a/servlet/">Login</a>
	</c:if>


	<c:if test="${empty sessionScope.Group}">
		<a href="BrowseGroup"><b>Browse Group</b></a>
	</c:if>

	<c:if test="${! empty sessionScope.userName}">
		<c:if test="${!empty warning}">
			<b>${warning}</b>
		</c:if>
		<c:if test="${!empty warning2}">
			<b>${warning2}</b>
		</c:if>
		<c:if test="${!empty warning4}">
			<b>${warning4}</b>
		</c:if>
				<c:if test="${!empty warning5}">
			<b>${warning5}</b>
		</c:if>
		
		<a href="logout">LogOut</a>
		<h2>Welcome! ${userName.getFirstName()}</h2>
		<h2>You are in a group : ${Group.getgroupName()}</h2>

		<form method="POST" action="EditGroup">
			<p>Edit Group Name:</p>
			<p>
				<b>New Group Name:</b> <input type="text" name="NewGroupName" size="10">
			</p>
			<p>
				<input type="submit" value="Submit" name="submit"> <input type="reset" value="Reset" name="reset">
			</p>
		</form>
		<h4>Remove Group( The members are also removed in group):</h4>
		<a href="DeleteGroup">DELETE	GROUP</a>
		<br />
		<h4>Choose the User you want to Remove:</h4>

        <c:forEach items="${Members}" var="Members">
			<a href="RemoveMember?userName=${Members.getUserName()}">${Members.getUserName()}</a><br/>
			<br />
		</c:forEach>
		
		<h4>Choose the User you want to Invite:</h4>
		<c:forEach items="${people}" var="person">
			<a href="ViewPerson?userName=${person.getUserName()}">${person.getUserName()}</a><br/>
			<br />
		</c:forEach>
	</c:if>
	
</body>
</html>