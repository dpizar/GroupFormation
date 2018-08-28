<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<title>User Login</title>
<script>
	function goBack() {
		window.history.back();
	}
</script>
</head>
<body>
	<c:if test="${empty sessionScope.userName}">
		<c:if test="${!empty error}">
			<b>${error}</b>&nbsp;&nbsp;
			<a href="javascript:goBack()">Go Back!</a>
		</c:if>
		<c:if test="${empty error}">
			<form method="POST" action="assignments/login">
				<p>
					<b>UserName:</b> <input type="text" name="userName" size="10">
				</p>
				<p>
					<b>Password:</b> &nbsp;&nbsp;<input type="Password" name="password"
						size="10">
				</p>
				<p>
					<input type="submit" value="Submit" name="submit"> <input
						type="reset" value="Reset" name="reset">
				</p>
			</form>
		</c:if>
	</c:if>

	<c:if test="${! empty sessionScope.userName}">
		<p>
			<b>You have already logged in!!</b>
		</p>
		<a href="assignments/logout">Logout</a>
		<br />
		<a href="javascript:goBack()">Go Back!</a>
	</c:if>

</body>
</html>