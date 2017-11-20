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
			<form action="edit/submit" method="post">
				<input name="voie.name" required
					value="<c:out value="${ requestScope.voie.name }"/>"
					placeholder="nom de la voie" />
				<input name="voie.description" required
					value="<c:out value="${ requestScope.voie.description }"/>"
					placeholder="description de la voie" />
				<input name="voie.cotation" required
					value="<c:out value="${ requestScope.voie.cotation }"/>"
					placeholder="cotation de la voie" />
				<input name="voie.length" required type="number"
					value="<c:out value="${ requestScope.voie.length }"/>"
					placeholder="longueur de la voie" />
				<input name="voie.pointNumber" required type="number"
					value="<c:out value="${ requestScope.voie.pointNumber }"/>"
					placeholder="nombre de point de la voie" />
				<input type="hidden" name="voie.id"
					value="${ requestScope.voie.id }" />
				<button type="submit">
					Editer
					<c:out value="${ requestScope.voie.name }" />
				</button>
			</form>

			<h3>Les longueurs de ${ requestScope.voie.name }</h3>
			<div>
				<c:forEach items="${ requestScope.voie.longueurs }" var="longueur">
					<form class="editable"
						action="${ pageContext.request.contextPath }/longueur/${ longueur.id }/delete"
						method="post">
						<input type="hidden" name="longueurId" value="${ longueur.id }" />
						<p>${ longueur.name }(${ longueur.cotation },${ longueur.length }m)</p>
						<div class="editable">
							<button class="editButton" type="button"
								onclick="navigate('/longueur/${ longueur.id }/edit');">éditer</button>
							<button class="editButton" type="submit"
								onclick="return confirm('Supprimer cette longueur?');">
								supprimer</button>
						</div>
					</form>
				</c:forEach>
				<form style="display: none;" id="addLongueurForm"
					action="${pageContext.request.contextPath}/longueur/add" method="post">
					<input name="longueur.name" placeholder="nom de la voie" required />
					<input name="longueur.cotation" placeholder="cotation de la voie" required />
					<input name="longueur.length" placeholder="longueur de la voie"
						type="number" required />
					<input type="hidden" name="longueur.voie.id"
						value="${ requestScope.voie.id }" />
				</form>
				<button form="addLongueurForm" type="submit" name="action"
					value="addLongueur"
					onclick="return displayForm(addLongueurForm, this)">Nouvelle
					longueur</button>
			</div>

			<h3>Supprimer cette voie ?</h3>
			<form class="simpleForm" action="delete" method="post">
				<button type="submit"
					onClick="return confirm('Supprimer cette voie? Les longueures associeés seront également supprimées');">
					<i class="material-icons">delete</i>
				</button>
			</form>

		</div>
	</div>
</body>
</html>