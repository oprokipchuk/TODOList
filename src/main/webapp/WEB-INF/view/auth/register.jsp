<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ru">
<head>
	<meta charset="UTF-8">
	<title>Document</title>
	<style>
		.err {
			color : red;
		}
	</style>
</head>
<body>
	
	<form action="register.do" method="POST">

		<p class="err">${loginError}</p>
		Login: <input type="text" name="login" value="${incorrectUserData.login}"><br>
		<p class="err">${passwordError}</p>
		Password: <input type="password" name="password">
		<input type="submit" value="register">

	</form>

</body>
</html>
