<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Mes topos</title>
<%@ include file="/WEB-INF/jsp/style/global-style.jsp"%>
</head>
<body>
	<div class="card">
		<div class="card-header">
			<div class="editable">
				<form id="ownerForm" action="${ pageContext.request.contextPath }/owner/edit" method="post">
					<input type="hidden" name="owner.id"
						value="${ requestScope.owner.id }" />
					<input class="title" id="firstName" name="owner.firstName" disabled
						placeholder="Prénom"
						value="<c:out value="${ requestScope.owner.firstName }"/>" />
					<input class="title" id="lastName" name="owner.lastName" disabled
						placeholder="Nom de famille" style="margin: 0;"
						value="<c:out value="${ requestScope.owner.lastName }"/>" />
				</form>

				<div class="columnCenter">
					<button form="ownerForm"
						onclick="return enableForm(this, [firstName, lastName, email, phone]);">éditer</button>
					<form id="deleteForm" class="simpleForm" action="${ pageContext.request.contextPath }/owner/delete"
						method="post"></form>
					<button form="deleteForm"
						onclick="return confirm('Êtes vous certain de vouloir supprimer votre compte ? Cela est irréversible')">supprimer</button>
				</div>
			</div>
		</div>
		<div class="card-content">
			<table>
				<tr>
					<td>Email:</td>
					<td><input type="email" id="email" name="owner.email"
							placeholder="Email" form="ownerForm"
							value="<c:out value="${ sessionScope.owner.email }"/>" disabled />
				</tr>
				<tr>
					<td>Téléphone:</td>
					<td><input type="text" id="phone" name="owner.phoneNumber"
							placeholder="Numéro de téléphone" form="ownerForm"
							value="<c:out value="${ sessionScope.owner.phoneNumber }"/>"
							disabled /></td>
				</tr>
			</table>
		</div>
	</div>

	<div class="card">
		<div class="card-header">
			<h2>Mes topos:</h2>
		</div>
		<div class="card-content">
			<div class="list">
				<c:forEach items="${ requestScope.toposOwned }" var="topoOwned">
					<div class="list-item-column">
						<h4>
							<c:out value="${ topoOwned.name }"></c:out>
						</h4>
						<form class="editable" action="${ pageContext.request.contextPath }/owner/topo/available"
							method="post">
							<div>
								<label for="${ topoOwned.id }">Ce topo est disponible:</label>
								<input type="checkbox" id="input${ topoOwned.id }"
									${ topoOwned.available ? 'checked' : '' } disabled />
								<input type="hidden" id="available${ topoOwned.id }"
									name="topo.available" />
								<input type="hidden" id="id${ topoOwned.id }" name="topo.id"
									value="${ topoOwned.id }" />
							</div>
							<button type="submit" id="button${ topoOwned.id }"
								onClick="return editAvailability(${ topoOwned.id })">éditer</button>
						</form>
					</div>
				</c:forEach>
			</div>
			<h4>Ajouter:</h4>
			<div>
				<label for="existing">un topo existant</label>
				<input id="existing" type="radio" name="addChoice" value="existing"
					onchange='setDisplay(formExistingTopo, formNewTopo);' />
				<label for="new">un nouveau topo</label>
				<input id="new" type="radio" name="addChoice" value="new"
					onchange="setDisplay(formNewTopo, formExistingTopo);" />
			</div>

			<p id="error" class="false" style="display: none;"></p>

			<form id="formExistingTopo" style="display: none"
				action="${ pageContext.request.contextPath }/owner/topo/add"
				method="post">
				<c:if test="${ empty requestScope.toposNotOwned }">
					<p class="false">Vous possédez déjà tout les topos enregistrés
						sur ce site.</p>
				</c:if>
				<c:forEach items="${ requestScope.toposNotOwned }"
					var="topoNotOwned">
					<div>
						<label for="topo${ topoNotOwned.id }"><c:out
								value="${ topoNotOwned.name }" /></label>
						<input id="topo${ topoNotOwned.id }" type="radio" name="topo.id"
							value="${ topoNotOwned.id }" />
					</div>
				</c:forEach>
				<button type="submit" name="action" value="topo"
					onClick="return validateExisting(formExistingTopo)">ajouter</button>
			</form>

			<form id="formNewTopo" style="display: none"
				action="${ pageContext.request.contextPath }/owner/topo/add"
				method="post">
				<input type="text" id="topoName" name="topo.name"
					placeholder="nom du nouveau topo" />
				<h5>Site associé:</h5>
				<c:forEach items="${ requestScope.sites }" var="site">
					<div>
						<label for="radio${ site.id }"><c:out
								value="${ site.name }" /></label>
						<input id="radio${ site.id }" type="radio" name="topo.site.id"
							value="${ site.id }" />
					</div>
				</c:forEach>
				<input id="siteName" placeholder="nom du nouveau site" type="text"
					name="topo.site.name" onFocus="uncheckRadio('siteId')" />

				<button type="submit" onClick="return validateNew(formNewTopo)">ajouter</button>
			</form>


		</div>
	</div>
	<%@ include file="/WEB-INF/jsp/javascript/owner.js.jsp"%>
</body>
</html>