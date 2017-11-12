<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/jsp/style/global-style.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edition de ${ requestScope.site.name }</title>
</head>
<body>
	<div class="card">
		<div class="card-header">
			<h2>
				Edition de
				<c:out value="${ requestScope.site.name }" />
			</h2>
			<p class="description">"${ requestScope.site.description }"</p>
		</div>
		<div class="card-content">
			<form action="edit?id=${ requestScope.site.id }" method="post">
				<input type="hidden" name="siteId" value="${ requestScope.site.id }" />
				<input name="name" required
					value="<c:out value="${ requestScope.site.name }"/>"
					placeholder="nom du site" />
				<input name="description" required
					value="<c:out value="${ requestScope.site.description }"/>"
					placeholder="description du site" />
				<input name="howToFind" required
					value="<c:out value="${ requestScope.site.howToFind }"/>"
					placeholder="comment trouver ce site" />
				<input name="latitude" required
					value="<c:out value="${ requestScope.site.latitude }"/>"
					placeholder="latitude" />
				<input name="longitude" required
					value="<c:out value="${ requestScope.site.longitude }"/>"
					placeholder="longitude" />
				<button type="submit" name="action" value="updateSite">
					Editer
					<c:out value="${ requestScope.site.name }" />
				</button>
			</form>

			<h3>Les secteurs de ce site:</h3>
			<div>
				<c:forEach items="${ site.secteurs }" var="secteur">
					<form class="editable" action="edit?id=${ requestScope.site.id }"
						method="post">
						<input type="hidden" name="secteurId" value="${ secteur.id }" />
						<p>
							<c:out value="${ secteur.name }"></c:out>
						</p>
						<div class="editable">
							<button class="editButton" type="button"
								onclick="navigate('/secteur/edit?id=${ secteur.id }');">éditer</button>
							<button class="editButton" type="submit" name="action"
								value="deleteSecteur"
								onclick="return confirm('Supprimer ce secteur ? Les voies et longueurs associées seront également supprimées.');">
								supprimer</button>
						</div>
					</form>
				</c:forEach>
				<form style="display: none;" id="addSecteurForm"
					action="edit?id=${ requestScope.site.id }" method="post">
					<input name="name" placeholder="nom du secteur" required />
					<input name="description" placeholder="description du secteur"
						required />
					<input type="hidden" name="siteId"
						value="${ requestScope.site.id }" />
				</form>
				<button form="addSecteurForm" type="submit" name="action"
					value="addSecteur"
					onclick="return displayForm(addSecteurForm, this)">Nouveau
					secteur</button>
			</div>

			<h3>Les topos de ce site:</h3>
			<div>
				<c:forEach items="${ requestScope.site.topos }" var="topo">
					<form class="editable" action="edit?id=${ requestScope.site.id }"
						method="post">
						<input type="hidden" name="topoId" value="${ topo.id }" />
						<p>
							<c:out value="${ topo.name }"></c:out>
						</p>
						<div class="editable">
							<button class="editButton" type="button"
								onclick="navigate('/topo/edit?id=${ topo.id }');">éditer</button>
							<button class="editButton" type="submit" name="action"
								value="deleteTopo">supprimer</button>
						</div>
					</form>
				</c:forEach>
				<form style="display: none;" id="addNewTopoForm"
					action="edit?id=${ requestScope.site.id }" method="post">
					<input name="name" placeholder="nom du secteur" required />
					<input type="hidden" name="siteId"
						value="${ requestScope.site.id }" />
				</form>
				<form style="display: none;" id="addExistingTopoForm"
					action="edit?id=${ requestScope.site.id }" method="post">
					<input type="hidden" name="siteId"
						value="${ requestScope.site.id }" />
					<select name="topoId">
						<c:forEach items="${ requestScope.topos }" var="topo">
							<option value="${ topo.id }"><c:out
									value="${ topo.name }"></c:out></option>
						</c:forEach>
					</select>
				</form>
				<button id="newTopoButton" form="addNewTopoForm" type="submit"
					name="action" value="addNewTopoForm"
					onclick="return displayForm(addNewTopoForm, this, [existingTopoButton])">Nouveau
					topo</button>
				<button id="existingTopoButton" form="addExistingTopoForm"
					type="submit" name="action" value="addExistingTopoForm"
					onclick="return displayForm(addExistingTopoForm, this, [newTopoButton])">Ajouter
					un topo existant</button>
			</div>

			<h3>Supprimer ce site ?</h3>
			<form class="simpleForm" action="edit?id=${ requestScope.site.id }"
				method="post">
				<button type="submit" name="action" value="delete"
					onClick="return confirm('Supprimer ce site ? Les secteurs, les voies et les longueures associés seront également supprimés.');">
					<i class="material-icons">delete</i>
				</button>
			</form>

		</div>
	</div>
</body>
</html>