<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ru">
<head>
	<meta charset="UTF-8">
	<title>Document</title>
	<!--<base href="${pageContext.request.contextPath}">-->
</head>
<body>
	<h2>Вітаю, ${User.login}! Ви успішно зареєструвались)</h2>
	<a href="${pageContext.request.contextPath}/groups">До роботи!</a>
</body>
</html>