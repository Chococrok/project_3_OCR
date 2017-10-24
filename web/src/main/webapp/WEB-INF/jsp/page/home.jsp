<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
			<form action="site" method="get" class="list">
			 	<c:forEach items="${ sites }" var="site" >
			 	 	<div>
			    			<p>${ site.name }</p>
			    			<button type="submit" name="id" value="${ site.id }">
							<i class="material-icons">&#xE88F;</i>
							<span>informations</span>
						</button>
		   			</div>
				</c:forEach>
			</form>
		</div>
	</div>
	<%@ include file="/WEB-INF/jsp/layout/footer.jsp" %>
</body>
</html>
