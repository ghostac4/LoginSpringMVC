<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User Login and Registration</title>
<link rel="stylesheet" href="<c:url value="/resources/css/style.css" />">
</head>
<body>
<h1>Welcome, ${sessionScope.user.name}</h1>
<a href='Logout'><button type="button"><p>Logout</p></button></a>
</body>
</html>