<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
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
  <form action='Register' method="post" modalAttribute="user">
    <table>
      <tbody>
        <tr>
          <td class='input-row'><label for='username'>Username</label></td>
          <td class='input-row'><input type='text' id='username' name='name' value='${username}'>
          <p>${errorUsername}</p>
          </td>
        </tr>
        <tr>
          <td class='input-row'><label for='email'>Email</label></td>
          <td class='input-row'><input type='email' id='email' name='email' value='${email}'>
          <p>${errorEmail}</p>
          </td>
        </tr>
        <tr>
          <td class='input-row'><label for='password'>Password</label></td>
          <td class='input-row'><input type='password' id='password' name='password'>
          <p>${errorPassword}</p>
          </td>
        </tr>
        <tr>
          <td class='input-row'><label for='dob'>Date of Birth</label></td>
          <td class='input-row'><input type='date' id='dob' name='dateOfBirth' value='${dateOfBirth}'></td>
        </tr>
        <tr>
          <td class='input-row'><label for='mobile'>Mobile No</label></td>
          <td class='input-row'><input type='tel' id='mobile' name='mobile' value='${mobile}'>
          <p>${errorMobile}</p>
          </td>
        </tr>
        <tr>
          <td colspan="2"><button type="submit"><p>Register</p></button></td>
        </tr>
      </tbody>
    </table>
    </form>
  </div>
</div>
</body>
</html>
