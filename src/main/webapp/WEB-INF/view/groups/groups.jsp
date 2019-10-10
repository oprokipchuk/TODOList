<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ru">
<head>
	<meta charset="UTF-8">
	<title>Document</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/groups/style.css">
</head>
<body>
	<header>
		<p>Hi, ${User.login}</p>
		<p><a href="${pageContext.request.contextPath}/logout">logout</a></p>
	</header>
	
	<main>

		<section>
			<div class="groupsHeader">
				<p>Your groups</p>
			</div>
			<ul id="GroupList">
			</ul>
		</section>

		<aside>
			<div id="GroupHeader" class="groupHeader">
				<p>Group 1</p>
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

	<script>

		base = "${pageContext.request.contextPath}";
		userLogin = "${User.login}";

	</script>

	<script src="${pageContext.request.contextPath}/resources/groups/js.js"></script>
	

</body>
</html>