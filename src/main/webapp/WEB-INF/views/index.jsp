<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Login and Registration</title>
<link rel="stylesheet"
	href='<c:url value="/resources/css/style.css" />'>
</head>
<body>
	<div class='main-container'>
		<div class='login-box'>
			<form action='Login' method="post" onsubmit="return validate()" modalAttribute="login">
				<table>
					<tbody>
						<tr>
							<td class='input-row'><label for='username'>Email</label></td>
							<td class='input-row'><input type='text' id='username'
								name='username'>
								<p id='usernameError'></p></td>
						</tr>
						<tr>
							<td class='input-row'><label for='password'>Password</label></td>
							<td class='input-row'><input type='password' id='password'
								name='password'>
								<p id='passwordError'></p></td>
						</tr>
						<tr>
							<td colspan="2"><button type="submit">
									<p>Login</p>
								</button></td>
						</tr>
						<tr>
							<td colspan="2"><a href='RegistrationForm'><button type="button">
										<p>Register</p>
									</button></a></td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>
</body>
<script type="text/javascript"
	src="<c:url value="/resources/js/loginValidation.js" />"></script>
</html>
