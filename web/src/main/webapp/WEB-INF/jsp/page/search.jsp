<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/jsp/style/global-style.jsp" %>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/layout/header.jsp" %>
	<div class="card">
		<div class="card-header">
			<h2>Listes des sites:</h2>
		</div>
		<div class="card-content">
			<form action="search" method="get" class="list">
			 	<input type="search" name="name"/>
			 	<input type="submit"/>
			</form>
			<p>${ error }</p>
		</div>
	</div>
	<%@ include file="/WEB-INF/jsp/layout/footer.jsp" %>
</body>
</html>
