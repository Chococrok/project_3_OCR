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
			<h2>${ sessionScope.owner.fullName }</h2>
		</div>
		<div class="card-content">
			<table>
				<tr>
					<td>Email:</td>
					<td>
						<form id="formEmail" action="owner" method="post">
							<input type="email" id="inputEmail"
								value="<c:out value="${ sessionScope.owner.email }"/>" disabled />
							<input type="hidden" id="hiddenEmail" name="email" />
						</form>
					</td>
					<td>
						<button form="formEmail" type="submit" id="buttonEmail"
							name="action" value="email"
							onClick="return editPersonalData('Email')">editer</button>
					</td>
				</tr>
				<tr>
					<td>Téléphone:</td>
					<td>
						<form id="formPhone" action="owner" method="post">
							<input type="text" id="inputPhone"
								value="<c:out value="${ sessionScope.owner.phoneNumber }"/>"
								disabled /> <input type="hidden" id="hiddenPhone" name="phone" />
						</form>
					</td>
					<td>
						<button form="formPhone" type="submit" id="buttonPhone"
							name="action" value="phone"
							onClick="return editPersonalData('Phone')">editer</button>
					</td>
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
						<form class="editable" action="owner#input${ topoOwned.id }"
							method="post">
							<div>
								<label for="${ topoOwned.id }">Ce topo est disponible:</label> <input
									type="checkbox" id="input${ topoOwned.id }"
									${ topoOwned.available ? 'checked' : '' } disabled /> <input
									type="hidden" id="available${ topoOwned.id }" name="available" />
								<input type="hidden" id="id${ topoOwned.id }" name="id"
									value="${ topoOwned.id }" />
							</div>
							<button type="submit" id="button${ topoOwned.id }" name="action"
								value="availability"
								onClick="return editAvailability(${ topoOwned.id })">editer</button>
						</form>
					</div>
				</c:forEach>
			</div>
			<h4>Ajouter:</h4>
			<div>
				<label for="existing">un topo existant</label> <input id="existing"
					type="radio" name="addChoice" value="existing"
					onchange='setDisplay(formExistingTopo, formNewTopo);' /> <label
					for="new">un nouveau topo</label> <input id="new" type="radio"
					name="addChoice" value="new"
					onchange="setDisplay(formNewTopo, formExistingTopo);" />
			</div>

			<p id="error" class="false" style="display: none;"></p>

			<form id="formExistingTopo" style="display: none"
				action="owner#existing" method="post">
				<c:if test="${ empty requestScope.toposNotOwned }">
					<p class="false">Vous possédez déjà tout les topos enregistrés
						sur ce site.</p>
				</c:if>
				<c:forEach items="${ requestScope.toposNotOwned }"
					var="topoNotOwned">
					<div>
						<label for="topo${ topoNotOwned.id }"><c:out
								value="${ topoNotOwned.name }" /></label> <input
							id="topo${ topoNotOwned.id }" type="radio" name="topoId"
							value="${ topoNotOwned.id }" />
					</div>
				</c:forEach>
				<button type="submit" name="action" value="topo"
					onClick="return validateExisting(formExistingTopo)">ajouter</button>
			</form>

			<form id="formNewTopo" style="display: none" action="owner#existing"
				method="post">
				<input type="text" id="topoName" name="topoName"
					placeholder="nom du nouveau topo" />
				<h5>Site associé:</h5>
				<c:forEach items="${ requestScope.sites }" var="site">
					<div>
						<label for="radio${ site.id }"><c:out
								value="${ site.name }" /></label> <input id="radio${ site.id }"
							type="radio" name="siteId" value="${ site.id }" />
					</div>
				</c:forEach>
				<input id="siteName" placeholder="nom du nouveau site" type="text"
					name="siteName" onFocus="uncheckRadio('siteId')" />

				<button type="submit" name="action" value="topo"
					onClick="return validateNew(formNewTopo)">ajouter</button>
			</form>


		</div>
	</div>
	<%@ include file="/WEB-INF/jsp/javascript/owner.js.jsp"%>
</body>
</html>