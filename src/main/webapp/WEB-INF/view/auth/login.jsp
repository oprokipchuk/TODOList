<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Document</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/auth/login/login.css">
	<style>.err {color : red; font-size: 10px}</style>
</head>
<body>
	
	<form id="msform" action="login" method="POST">
	  <fieldset>
	    <h2 class="fs-title">Login to your account</h2>
	    <br><br>
	    <p class="err">${incorrectLoginOrPasswordError}</p>
	    <input type="text" name="login" placeholder="Login" value="${incorrectUserData.login}"/>
	    <input type="password" name="password" placeholder="Password" />
	    <input type="submit" name="next" class="next action-button" value="Login" />
	  </fieldset>
	</form>

</body>
</html>