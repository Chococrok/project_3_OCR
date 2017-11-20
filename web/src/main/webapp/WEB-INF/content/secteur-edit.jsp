<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/jsp/style/global-style.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edition d'un secteur</title>
</head>
<body>
	<div class="card">
		<div class="card-header">
			<h2>
				Edition de
				<c:out value="${ requestScope.secteur.name }" />
			</h2>
			<p class="description">"${ secteur.description }"</p>
		</div>
		<div class="card-content">
			<form action="edit/submit" method="post">
				<input name="secteur.name" required
					value="<c:out value="${ requestScope.secteur.name }"/>"
					placeholder="nom du secteur" />
				<input name="secteur.description" required
					value="<c:out value="${ requestScope.secteur.description }"/>"
					placeholder="description du secteur" />
				<input type="hidden" name="secteur.id"
					value="${ requestScope.secteur.id }" />
				<button type="submit" name="action" value="updateSecteur">
					Editer
					<c:out value="${ requestScope.secteur.name }" />
				</button>
			</form>

			<h3>Les voies de ce secteur:</h3>
			<div>
				<c:forEach items="${ secteur.voies }" var="voie">
					<form class="editable"
						action="${pageContext.request.contextPath}/voie/${ voie.id }/delete"
						method="post">
						<p>${ voie.name }(${ voie.cotation },${ voie.length }m)</p>
						<div class="editable">
							<button class="editButton" type="button"
								onclick="navigate('/voie/${ voie.id }/edit');">éditer</button>
							<button class="editButton" type="submit"
								onclick="return confirm('Supprimer cette voie ? Les longueurs associées seront également supprimées.');">
								supprimer</button>
						</div>
					</form>
				</c:forEach>
				<form style="display: none;" id="addVoieForm"
					action="${pageContext.request.contextPath}/voie/add" method="post">
					<input name="voie.name" placeholder="nom de la voie" required />
					<input name="voie.description" placeholder="description de la voie"
						required />
					<input name="voie.cotation" placeholder="cotation de la voie"
						required />
					<input name="voie.length" placeholder="longueur de la voie"
						type="number" required />
					<input name="voie.pointNumber"
						placeholder="nombre de point de la voie" type="number" required />
					<input type="hidden" name="voie.secteur.id"
						value="${ requestScope.secteur.id }" />
				</form>
				<button form="addVoieForm" type="submit"
					onclick="return displayForm(addVoieForm, this)">Nouvelle
					voie</button>
			</div>

			<h3>Supprimer ce secteur ?</h3>
			<form class="simpleForm" action="delete" method="post">
				<button type="submit" name="action" value="delete"
					onClick="return confirm('Supprimer ce secteur ? Les voies associées seront également supprimées.');">
					<i class="material-icons">delete</i>
				</button>
			</form>

		</div>
	</div>
</body>
</html>