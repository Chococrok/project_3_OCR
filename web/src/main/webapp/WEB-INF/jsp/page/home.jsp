<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/jsp/style/global-style.jsp" %>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/layout/header.jsp" %>
	<form class="site-list-container" action="site" method="get">
	 	<c:forEach items="${ sites }" var="site" >
	 	 	<div class="site-container">
	    			<p>${ site.name }</p>
	    			<button type="submit" name="id" value="${ site.id }">
					<i class="material-icons">&#xE88F;</i>
					<span>informations</span>
				</button>
   			</div>
		</c:forEach>
	</form>
	<%@ include file="/WEB-INF/jsp/layout/footer.jsp" %>
</body>
</html>
