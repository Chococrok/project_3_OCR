<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Les topos</title>
<%@ include file="/WEB-INF/jsp/style/global-style.jsp"%>
</head>
<body>
	<div class="card-container">
		<c:forEach items="${ requestScope.topos }" var="topo">
			<div class="card topo clickable" onclick="location.href='topo?id=${ topo.id }'">
				<div class="card-header">
					<h2><c:out value="${ topo.name }"></c:out></h2>
				</div>
				<div class="card-content">
					<h3>site correspondant:</h3>
					<p>${ topo.site.name }</p>
				</div>
			</div>
		</c:forEach>
	</div>

</body>
</html>