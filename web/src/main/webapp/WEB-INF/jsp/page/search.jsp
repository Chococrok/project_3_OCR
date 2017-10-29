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
			<form action="search#submit" method="get">
				<div>
					<p>Je souhaite effectuer une recherche par:</p>
					<input type="radio" name="method" id="name" value="name" checked />
					<label for="name">nom</label> <input type="radio" name="method"
						id="cotation" value="cotation" /> <label for="cotation">cotation</label>
				</div>

				<div>
					<p>Je recherche:</p>
					<input type="radio" name="type" id="site" value="site" checked />
					<label for="site">site</label> <input type="radio" name="type"
						id="secteur" value="secteur" /> <label for="secteur">secteur</label>
					<input type="radio" name="type" id="voie" value="voie" /> <label
						for="voie">voie</label>
				</div>

				<input type="search" name="content" /> <input type="submit"
					id="submit" />
			</form>
			<p class="${ empty error }">${ error }</p>

			<c:if test="${!entities.isEmpty()}">
				<h3>
					<c:out value="${ param.type }${ empty param.type ? '' : 's :' }"></c:out>
				</h3>
				<div class="list">
					<c:forEach items="${ entities }" var="entity">
						<div class="clickable" onClick="navigate('${ param.type }?id=${ entity.id }')">
							<p>${ entity.name }</p>
							<i class="material-icons">&#xE315;</i>
						</div>
					</c:forEach>
				</div>
			</c:if>

		</div>
	</div>
</body>
</html>
