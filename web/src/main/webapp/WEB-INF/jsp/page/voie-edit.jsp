<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/jsp/style/global-style.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edition d'une voie</title>
</head>
<body>
	<div class="card">
		<div class="card-header">
			<h3>
				Edition de
				<c:out value="${ requestScope.voie.name }" />
			</h3>
		</div>
		<div class="card-content">
			<form action="edit?id=${ requestScope.voie.id }" method ="post">
				<input name="name"
					value="<c:out value="${ requestScope.voie.name }"/>"
					placeholder="nom de la voie" />
				<input name="description"
					value="<c:out value="${ requestScope.voie.description }"/>"
					placeholder="description de la voie" />
				<input name="cotation"
					value="<c:out value="${ requestScope.voie.cotation }"/>"
					placeholder="cotation de la voie" />
				<input name="length"
					value="<c:out value="${ requestScope.voie.length }"/>"
					placeholder="longueur de la voie" />
				<input name="pointNumber"
					value="<c:out value="${ requestScope.voie.pointNumber }"/>"
					placeholder="nombre de point de la voie" />
				<input type="hidden" name="id" value="${ requestScope.voie.id }" />
				<button type="submit" name="action" value="updateVoie">Confirmer</button>
			</form>

			<h3>Les longueurs de ${ voie.name }</h3>
			<div class="list">
				<c:forEach items="${ voie.longueurs }" var="longueur">
					<form class="editable" action="edit?id=${ requestScope.voie.id }"
						method="post">
						<p>${ longueur.name }</p>
						<input type="hidden" name="longueurId" value="${ longueur.id }" />
						<button class="editButton" type="submit" name="action"
							value="deleteLongueur" onClick="return removeLongueur();">
							<i class="material-icons">delete</i>
						</button>
					</form>
				</c:forEach>
			</div>

			<h3>Supprimer cette voie ?</h3>
			<form class="simpleForm" action="edit?id=${ requestScope.voie.id }"
				method="post">
				<button type="submit" name="action" value="delete"
					onClick="return remove();">
					<i class="material-icons">delete</i>
				</button>
			</form>

		</div>
	</div>
	<%@ include file="/WEB-INF/jsp/javascript/voie-edit.js.jsp"%>
</body>
</html>