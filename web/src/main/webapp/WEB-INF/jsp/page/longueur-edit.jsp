<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/jsp/style/global-style.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edition d'une longueur</title>
</head>
<body>
	<div class="card">
		<div class="card-header">
			<h2>
				Edition de
				<c:out value="${ requestScope.longueur.name }" />
			</h2>
		</div>
		<div class="card-content">
			<form action="edit?id=${ requestScope.longueur.id }" method="post">
				<input name="name" required
					value="<c:out value="${ requestScope.longueur.name }"/>"
					placeholder="nom de la voie" />
				<input name="cotation" required
					value="<c:out value="${ requestScope.longueur.cotation }"/>"
					placeholder="cotation de la voie" />
				<input name="length" required type="number"
					value="<c:out value="${ requestScope.longueur.length }"/>"
					placeholder="longueur de la voie" />
				<input type="hidden" name="longueurId" value="${ requestScope.longueur.id }" />
				<button type="submit" name="action" value="updateLongueur">
					Editer
					<c:out value="${ requestScope.longueur.name }" />
				</button>
			</form>

			<h3>Supprimer cette longueur ?</h3>
			<form class="simpleForm" action="edit?id=${ requestScope.longueur.id }"
				method="post">
				<button type="submit" name="action" value="delete"
					onClick="return confirm('Supprimer cette longueur ?');">
					<i class="material-icons">delete</i>
				</button>
			</form>

		</div>
	</div>
</body>
</html>