<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ru">
<head>
	<meta charset="UTF-8">
	<title>Document</title>
</head>
<body>
	
<h2>New group!</h2>
<form action="${pageContext.request.contextPath}/groups/new" method="POST">
	Name: <input type="text" name="groupName"><br>
	<input type="submit" value="create group">
</form>

</body>
</html>
