<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edition de <c:out value="${ requestScope.topo.name }"></c:out></title>
<%@ include file="/WEB-INF/jsp/style/global-style.jsp"%>
</head>
<body>
	<div class="card">
		<div class="card-header">
			<div class="editable">
				<form id="topoForm" action="edit?id=${ requestScope.topo.id }"
					method="post">
					<input type="hidden" name="topoId" value="${ requestScope.topo.id }" />
					<input class="title" id="inputTitle" name="name" disabled
						placeholder="Titre"
						value="<c:out value="${ requestScope.topo.name }"/>" />
				</form>

				<div class="columnCenter">
					<button form="topoForm" name="action" value="editTopo"
						onclick="return enableForm(this, [inputTitle]);">éditer</button>
					<form id="deleteForm" class="simpleForm"
						action="edit?id=${ requestScope.topo.id }" method="post"></form>
					<button form="deleteForm" name="action" value="delete"
						onclick="return confirm('Êtes vous certain de vouloir supprimer ce topo ? Cela est irréversible')">supprimer</button>
				</div>
			</div>
		</div>
		<div class="card-content">

			<h3>Site associé à ce topo :</h3>
			<form action="edit?id=${ requestScope.topo.id }" method="post">
				<input type="hidden" name="topoId" value="${ requestScope.topo.id }" />
				<select name="siteId">
					<c:forEach items="${ requestScope.sites }" var="site">
						<option ${ site.id == requestScope.topo.site.id ? 'selected' : '' }
							value="${ site.id }"><c:out value="${ site.name }"></c:out></option>
					</c:forEach>
				</select>
				<button type="submit" name="action" value="changeSite">Confirmer</button>
			</form>

		</div>
	</div>
</body>
</html>