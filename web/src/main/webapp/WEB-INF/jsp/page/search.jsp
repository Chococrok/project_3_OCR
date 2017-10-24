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
			<form action="search#submit" method="get">
				<div>
					<p>Je souhaite effectuer une recherche par:</p>
					<input type="radio" name="method" id="name" value="name" onclick="setDisabledState()" checked/>
					<label for="name">nom</label>
					<input type="radio" name="method" id="cotation" value="cotation" onclick="setDisabledState()" />
					<label for="cotation">cotation</label>
				</div>
					
				<div>
					<p>Je recherche:</p>
					<input type="radio" name="type" id="site" value="site" checked/>
					<label for="site">site</label>
					<input type="radio" name="type" id="secteur" value="secteur" />
					<label for="secteur">secteur</label>
					<input type="radio" name="type" id="voie" value="voie" />
					<label for="voie">voie</label>
				</div>
				
			 	<input type="search" name="content"/>
			 	<input type="submit" id="submit"/>
			</form>
			<p>${ error }</p>
			
			<c:if test="${!entities.isEmpty()}">
				<h3><c:out value="${ param.type }${ empty param.type ? '' : 's :' }"></c:out></h3>
			</c:if>
			<div class="list">
				<c:forEach items="${ entities }" var="entity">
					<p>${ entity.name }</p>
				</c:forEach>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/jsp/layout/footer.jsp" %>
</body>
</html>
