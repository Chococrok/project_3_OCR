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
			<h2>
				Edition de
				<c:out value="${ requestScope.voie.name }" />
			</h2>
			<p class="description">"${ requestScope.voie.description }"</p>
		</div>
		<div class="card-content">
			<form action="edit?id=${ requestScope.voie.id }" method="post">
				<input name="name" required
					value="<c:out value="${ requestScope.voie.name }"/>"
					placeholder="nom de la voie" />
				<input name="description" required
					value="<c:out value="${ requestScope.voie.description }"/>"
					placeholder="description de la voie" />
				<input name="cotation" required
					value="<c:out value="${ requestScope.voie.cotation }"/>"
					placeholder="cotation de la voie" />
				<input name="length" required type="number"
					value="<c:out value="${ requestScope.voie.length }"/>"
					placeholder="longueur de la voie" />
				<input name="pointNumber" required type="number"
					value="<c:out value="${ requestScope.voie.pointNumber }"/>"
					placeholder="nombre de point de la voie" />
				<input type="hidden" name="voieId" value="${ requestScope.voie.id }" />
				<button type="submit" name="action" value="updateVoie">
					Editer
					<c:out value="${ requestScope.voie.name }" />
				</button>
			</form>

			<h3>Les longueurs de ${ requestScope.voie.name }</h3>
			<div>
				<c:forEach items="${ requestScope.voie.longueurs }" var="longueur">
					<form class="editable" action="edit?id=${ requestScope.voie.id }"
						method="post">
						<input type="hidden" name="longueurId" value="${ longueur.id }" />
						<p>${ longueur.name }(${ longueur.cotation },${ longueur.length }m)</p>
						<div class="editable">
							<button class="editButton" type="button"
								onclick="navigate('/longueur/edit?id=${ longueur.id }');">éditer</button>
							<button class="editButton" type="submit" name="action"
								value="deleteLongueur" onclick="return confirm('Supprimer cette longueur?');">
								supprimer</button>
						</div>
					</form>
				</c:forEach>
				<form style="display: none;" id="addLongueurForm"
					action="edit?id=${ requestScope.voie.id }" method="post">
					<input name="name" placeholder="nom de la voie" required />
					<input name="cotation" placeholder="cotation de la voie" required />
					<input name="length" placeholder="longueur de la voie"
						type="number" required />
					<input type="hidden" name="voieId"
						value="${ requestScope.voie.id }" />
				</form>
				<button form="addLongueurForm" type="submit" name="action"
					value="addLongueur"
					onclick="return activateForm(addLongueurForm, this)">Nouvelle
					longueur</button>
			</div>

			<h3>Supprimer cette voie ?</h3>
			<form class="simpleForm" action="edit?id=${ requestScope.voie.id }"
				method="post">
				<button type="submit" name="action" value="delete"
					onClick="return confirm('Supprimer cette voie? Les longueures associeés seront également supprimées');">
					<i class="material-icons">delete</i>
				</button>
			</form>

		</div>
	</div>
</body>
</html>