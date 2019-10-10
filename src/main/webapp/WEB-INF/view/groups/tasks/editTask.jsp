<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ru">
<head>
	<meta charset="UTF-8">
	<title>Document</title>
</head>
<body>
	
<h2>New task!</h2>
<form id="editForm" action="" method="POST">
	Name: <input type="text" name="taskName" value="${Task.name}"><br>
	Status: <select name="taskStatus" value="${Task.status}">
				<option value="not started">Task not started</option>
				<option value="in process">Task in process</option>
				<option value="done">Task is done</option>
			</select>
	<br>
	Description:
	<br>
	<textarea name="taskDescription" cols="30" rows="10">${Task.description}</textarea>
	<br>
	<a onclick="doEdit(this)">edit task</a>
</form>

<script>
		
		editUrl = '${pageContext.request.contextPath}/groups/group/${groupNum}/tasks/edit';

		async function doEdit(event) {

			let formData = new FormData(editForm);
			formData.append("taskNum", ${taskNum});

			let putBody = {}
			putBody.name = formData.get("taskName");
			putBody.status = formData.get("taskStatus");
			putBody.description = formData.get("taskDescription");
			putBody.taskNum = formData.get("taskNum");

			let response = await fetch(editUrl, {
				method : 'PUT',
				body: JSON.stringify(putBody)
			});

			if (response.ok) {
				console.log("successful delete");
				//window.location.reload(false);
				//history.go(-1);
				//elemToDelete = Document.body.querySelector('div[data-groupnum = ' + gNum + ']');

			}
			return false;

		}

	</script>

</body>
</html>

