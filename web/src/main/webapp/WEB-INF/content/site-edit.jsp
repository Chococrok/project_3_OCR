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
			<form action="edit/submit" method="post">
				<input name="site.name" required
					value="<c:out value="${ requestScope.site.name }"/>"
					placeholder="nom du site" />
				<input name="site.description" required
					value="<c:out value="${ requestScope.site.description }"/>"
					placeholder="description du site" />
				<input name="site.howToFind" required
					value="<c:out value="${ requestScope.site.howToFind }"/>"
					placeholder="comment trouver ce site" />
				<input name="site.latitude" required pattern="^-?[0-9]{1,3},[0-9]+$"
					value="<c:out value="${ requestScope.site.latitude }"/>"
					placeholder="latitude" />
				<input name="site.longitude" required
					pattern="^-?[0-9]{1,3},[0-9]+$"
					value="<c:out value="${ requestScope.site.longitude }"/>"
					placeholder="longitude" />
				<input type="hidden" name="site.id"
					value="${ requestScope.site.id }" />
				<button type="submit">
					Editer
					<c:out value="${ requestScope.site.name }" />
				</button>
			</form>

			<h3>Les secteurs de ce site:</h3>
			<div>
				<c:forEach items="${ site.secteurs }" var="secteur">
					<form class="editable"
						action="${pageContext.request.contextPath}/secteur/${ secteur.id }/delete"
						method="post">
						<p>
							<c:out value="${ secteur.name }"></c:out>
						</p>
						<div class="editable">
							<button class="editButton" type="button"
								onclick="navigate('/secteur/${ secteur.id }/edit');">éditer</button>
							<button class="editButton" type="submit"
								onclick="return confirm('Supprimer ce secteur ? Les voies et longueurs associées seront également supprimées.');">
								supprimer</button>
						</div>
					</form>
				</c:forEach>
				<form style="display: none;" id="addSecteurForm"
					action="${pageContext.request.contextPath}/secteur/add"
					method="post">
					<input name="secteur.name" placeholder="nom du secteur" required />
					<input name="secteur.description"
						placeholder="description du secteur" required />
					<input type="hidden" name="secteur.site.id"
						value="${ requestScope.site.id }" />
				</form>
				<button form="addSecteurForm" type="submit"
					onclick="return displayForm(addSecteurForm, this)">Nouveau
					secteur</button>
			</div>

			<h3>Les topos de ce site:</h3>
			<div>
				<c:forEach items="${ requestScope.site.topos }" var="topo">
					<form class="editable"
						action="${pageContext.request.contextPath}/topo/${ topo.id }/delete"
						method="post">
						<p>
							<c:out value="${ topo.name }"></c:out>
						</p>
						<div class="editable">
							<button class="editButton" type="button"
								onclick="navigate('/topo/${ topo.id }/edit');">éditer</button>
							<button class="editButton" onclick="return confirm('êtes vous certain de vouloir supprimer ce topo ?')"
							type="submit">supprimer</button>
						</div>
					</form>
				</c:forEach>
				<form style="display: none;" id="addNewTopoForm"
					action="${ pageContext.request.contextPath }/topo/new"
					method="post">
					<input name="topo.name" placeholder="nom du topo" required />
					<input type="hidden" name="topo.site.id"
						value="${ requestScope.site.id }" />
				</form>
				<form style="display: none;" id="addExistingTopoForm"
					action="${ pageContext.request.contextPath }/topo/edit/site"
					method="post">
					<input type="hidden" name="topo.site.id"
						value="${ requestScope.site.id }" />
					<select name="topo.id">
						<c:forEach items="${ requestScope.topos }" var="topo">
							<option value="${ topo.id }"><c:out
									value="${ topo.name }"></c:out></option>
						</c:forEach>
					</select>
				</form>
				<button id="newTopoButton" form="addNewTopoForm" type="submit"
					onclick="return displayForm(addNewTopoForm, this, [existingTopoButton])">Nouveau
					topo</button>
				<button id="existingTopoButton" form="addExistingTopoForm"
					type="submit"
					onclick="return displayForm(addExistingTopoForm, this, [newTopoButton])">Ajouter
					un topo existant</button>
			</div>

			<h3>Supprimer ce site ?</h3>
			<form class="simpleForm" action="delete" method="post">
				<button type="submit"
					onClick="return confirm('Supprimer ce site ? Les secteurs, les voies et les longueures associés seront également supprimés.');">
					<i class="material-icons">delete</i>
				</button>
			</form>

		</div>
	</div>
</body>
</html>