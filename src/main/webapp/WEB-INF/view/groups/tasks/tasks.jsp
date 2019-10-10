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


	<!--<h2>Tasks of group ${group.groupName}!</h2>
	<p>Hi, ${User.login}</p>
	<c:forEach var="taskItem" items="${group.taskList}" varStatus="loop">
		<div data-tasknum="${loop.index + 1}">
			<hr>
			<a href="task">${taskItem.name}</a>
			<a href="edit?taskNum=${loop.index + 1}">edit task</a>
			<input type="submit" value="delete task" onclick="doDelete(this)">
			<hr>
		</div>
	</c:forEach>
	<a href="new">new task</a>

	<script>
		
		deleteUrl = 'delete?taskNum=';

		async function doDelete(event) {

			/*let formData = new FormData();
			let gNum = event.parentNode.getAttribute("data-groupnum");
			//console.log(gNum);
			formData.append("groupNum", gNum);
			formData.append("someMessage", "message");
			console.log(formData.get("groupNum"));*/
			let tNum = event.parentNode.getAttribute("data-tasknum");

			let response = await fetch(deleteUrl + tNum, {
				method : 'DELETE',
			});

			if (response.ok) {
				console.log("successful delete");
				window.location.reload(false);
				//elemToDelete = Document.body.querySelector('div[data-groupnum = ' + gNum + ']');

			}

		}

	</script>-->

	<script src="${pageContext.request.contextPath}/resources/groups/tasks/tasks.js"></script>

</body>
</html>