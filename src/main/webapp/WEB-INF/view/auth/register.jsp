<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ru">
<head>
	<meta charset="UTF-8">
	<title>Document</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/auth/login/login.css">
	<style>.err {color : red; font-size: 10px}</style>
</head>
<body>
	
	<form id="msform" action="register.do" method="POST">
	  <fieldset>
	    <h2 class="fs-title">Register to your account</h2>
	    <br><br>
		<p class="err">${alreadyExistingUserError}</p>
		<p class="err">${emailError}</p>
	    <input type="text" name="email" placeholder="Email" value="${incorrectUserData.email}"/>
		<p class="err">${loginError}</p>
	    <input type="text" name="login" placeholder="Login" value="${incorrectUserData.login}"/>
	    <p class="err">${passwordError}</p>
	    <input type="password" name="password" placeholder="Password" />
	    <p class="err">${cpasswordError}</p>
	    <input type="password" name="cpassword" placeholder="Repeat password" />
	    <input type="submit" name="next" class="next action-button" value="Register" />
	  </fieldset>
	</form>

</body>
</html>
