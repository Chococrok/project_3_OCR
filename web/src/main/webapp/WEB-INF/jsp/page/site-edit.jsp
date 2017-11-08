<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>edition d'un site</title>
<%@ include file="/WEB-INF/jsp/style/global-style.jsp"%>
</head>
<body>
	<div class="card">
		<div class="card-header">
			<h2>${ site.name }</h2>
			<c:if test="${ empty site.description }">
				<p class="false">Ce site n'est pas encore référencé sur
					Escalade.com</p>
			</c:if>

			<c:if test="${ !empty site.description }">
				<p class="description">"${ site.description }"</p>
			</c:if>
		</div>

		<div class="card-content">

			<c:if test="${ !empty site.description }">

				<table>
					<tr>
						<td>Coordonées géographiques:</td>
						<td>${ site.latitude },${ site.longitude }</td>
					</tr>
					<tr>
						<td>indication supplémentaire:</td>
						<td>${ site.howToFind }</td>
					</tr>
				</table>

				<h3>Supprimer ce site ?</h3>
				<form class="simpleForm" action="edit?id=${ requestScope.site.id }" method="post">
					<button type="submit" name="action" value="delete"
						onClick="return remove();">
						<i class="material-icons">delete</i>
					</button>
				</form>

			</c:if>
			<%-- the link between topos and sites should be updated in topo details
			
			<h3>Topos associés à ce site:</h3>
			<c:forEach items="${ requestScope.topos }" var="topo">
				<form action="edit?id=${ requestScope.site.id }" method="post">
					<span> <label for="topo${ topo.id }">${ topo.name }</label>
						<input type="checkbox" id="topo${ topo.id }" disabled="disabled"
							${ requestScope.toposSite.contains(topo) ? 'checked' : '' } /> <input
							id="hidden${ topo.id }" type="hidden" name="added" />
							 <input
							 type="hidden" name="topoId" value="${ topo.id }"/>
						<button type="submit" id="buttonTopo" name="action" value="topo"
							onclick="return editTopo(this, hidden${ topo.id }, topo${ topo.id })">editer</button>
					</span>
				</form>
			</c:forEach>**/
--%>
		</div>
	</div>
	<%@ include file="/WEB-INF/jsp/javascript/site-edit.js.jsp"%>

</body>
</html>