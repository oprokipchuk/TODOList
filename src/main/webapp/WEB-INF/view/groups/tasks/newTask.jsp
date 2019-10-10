<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ru">
<head>
	<meta charset="UTF-8">
	<title>Document</title>
</head>
<body>
	
<h2>New task!</h2>
<form action="" method="POST">
	Name: <input type="text" name="taskName"><br>
	Status: <select name="taskStatus">
				<option value="not started">Task not started</option>
				<option value="in process">Task in process</option>
				<option value="done">Task is done</option>
			</select>
	<br>
	Description:
	<br>
	<textarea name="taskDescription" cols="30" rows="10"></textarea>
	<br>
	<input type="submit" value="create task">
</form>

</body>
</html>

