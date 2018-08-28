<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${! empty sessionScope.userName}">
	<p>
		<b>You have already logged in!!</b>
	</p>
	<a href="logout">Logout</a>
	<br />
	<a href="javascript:goBack()">Go Back!</a>
</c:if>

<c:if test="${empty sessionScope.userName}">
	<h3>You have logged out.</h3>
	<a href="http://snape.encs.concordia.ca/soen3872013a/servlet/">Login</a>
</c:if>