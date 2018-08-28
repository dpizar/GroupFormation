<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Users</title>
</head>
<body>

	<c:if test="${empty sessionScope.userName}">
		<h2>You must log in!!</h2>
		<a href="http://snape.encs.concordia.ca/soen3872013a/servlet/">Login</a>
	</c:if>

	<c:if test="${! empty sessionScope.userName}">
		<p>
			<b>Upload a CSV file to add users</b>
		</p>
		<form id="form1" enctype="multipart/form-data" action="CSVProcessor"
			method="post">
			<table>
				<tr>
					<td>Browse File</td>
					<td><input type="file" name="csvfile" />
					<td><input type="submit" value="Upload File" />
				</tr>
			</table>
		</form>
		<a href="logout">LogOut</a>
	</c:if>

</body>
</html>