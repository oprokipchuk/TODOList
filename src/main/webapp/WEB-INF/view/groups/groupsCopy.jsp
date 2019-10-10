<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ru">
<head>
	<meta charset="UTF-8">
	<title>Document</title>
</head>
<body>
	<h2>Your groups!</h2>
	<p>Hi, ${User.login}</p>
	<c:forEach var="groupItem" items="${groupList}" varStatus="loop">
		<div data-groupnum="${loop.index + 1}">
			<hr>
			<input hidden type="text" name="groupNum" value="${loop.index + 1}">
			<a href="${pageContext.request.contextPath}/groups/group/${loop.index + 1}/tasks/">${groupItem.groupName}</a>
			<input type="submit" value="delete group" onclick="doDelete(this)">
			<!--<input hidden type="text" name="groupNum" value="${loop.index + 1}">-->
			<hr>
		</div>
	</c:forEach>
	<a href="${pageContext.request.contextPath}/groups/new">new group</a>

	<script>
		
		deleteUrl = '${pageContext.request.contextPath}/groups/delete?groupNum=';

		async function doDelete(event) {

			/*let formData = new FormData();
			let gNum = event.parentNode.getAttribute("data-groupnum");
			//console.log(gNum);
			formData.append("groupNum", gNum);
			formData.append("someMessage", "message");
			console.log(formData.get("groupNum"));*/
			let gNum = event.parentNode.getAttribute("data-groupnum");

			let response = await fetch(deleteUrl + gNum, {
				method : 'DELETE',
			});

			if (response.ok) {
				console.log("successful delete");
				window.location.reload(false);
				//elemToDelete = Document.body.querySelector('div[data-groupnum = ' + gNum + ']');

			}

		}

	</script>


</body>
</html>