<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ru">
<head>
	<meta charset="UTF-8">
	<title>Document</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/groups/tasks/tasks.css">
</head>
<body>
	<header>
		<p>Hi, ${User.login}</p>
		<p><a href="${pageContext.request.contextPath}/logout">logout</a></p>
	</header>

	<main>

		<section>
			<div class="tasksHeader">
				<span>${group.groupName} tasks | </span>
				<span class="form-row">
					<input type="text" id='filterInput' placeholder="Filter">
				</span>
				<span>
					<span class='filter-checkbox'>
					not started <input id='c1' checked type="checkbox">
					</span>
					<span class='filter-checkbox'>
					in process <input id='c2' checked type="checkbox">
					</span>
					<span class='filter-checkbox'>
					done <input id='c3' checked type="checkbox">
					</span>
				</span>
			</div>
			<ul id="TaskList">
			</ul>
		</section>

		<aside>
			<div id="TaskHeader" class="taskHeader">
				<span>Task 1</span>
			</div>
			<div id="Content" class="content">
				<p>Owners</p>
				<ul>
					<li>user1</li>
					<li>user2</li>
				</ul>
				<a href="#">open group</a>
				<a href="#">delete group</a>
			</div>
		</aside>

	</main>

	<script src="${pageContext.request.contextPath}/resources/groups/tasks/tasks.js"></script>

</body>
</html>