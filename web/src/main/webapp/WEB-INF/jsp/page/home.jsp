<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/jsp/style/global-style.jsp"%>
</head>
<body>
	<div class="card">
		<div class="card-header">
			<h2>Listes des sites:</h2>
		</div>
		<div class="card-content">
			<div class="list">
				<c:forEach items="${ sites }" var="site">
					<div class="clickable" onClick="navigate('site?id=${ site.id }')">
						<p>${ site.name }</p>
						<i class="material-icons">&#xE315;</i>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
</body>
</html>
