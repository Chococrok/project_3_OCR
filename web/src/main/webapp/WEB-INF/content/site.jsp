<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/jsp/style/global-style.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${ requestScope.site.name }</title>
</head>
<body>
	<div class="card">
		<div class="card-header">
			<div class="editable">
				<h2>${ requestScope.site.name }</h2>
				<input value="editer" class="editButton"
					type="button" onclick="return navigate('/site/${requestScope.site.id}/edit')">
			</div>
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
						<td>${ site.latitude }, ${ site.longitude }</td>
					</tr>
					<tr>
						<td>indication supplémentaire:</td>
						<td>${ site.howToFind }</td>
					</tr>
				</table>

				<h3>Secteurs de ce site:</h3>
				<div class="list">
					<c:forEach items="${ site.secteurs }" var="secteur">
						<div class="clickable"
							onClick="navigate('/secteur?id=${ secteur.id }')">
							<p>${ secteur.name }</p>
							<i class="material-icons">&#xE315;</i>
						</div>
					</c:forEach>
				</div>
				<c:if test="${ empty requestScope.site.secteurs }">
					<p class="false">Aucun secteur n'est connu pour ce site. En connaissez vous un ?</p>
				</c:if>

			</c:if>

			<h3>Topos associés à ce site:</h3>
			<form action="secteur" method="get" class="list">
				<c:forEach items="${ requestScope.site.topos }" var="topo">
					<div class="clickable" onClick="navigate('/topo?id=${ topo.id }')">
						<p>${ topo.name }</p>
						<i class="material-icons">&#xE315;</i>
					</div>
				</c:forEach>
			</form>

			<h3>Commentaires:</h3>
			<div class="list">
				<c:forEach items="${ site.comments }" var="comment">
					<div class="list-item-column">
						<p>
							<c:out value="${ comment.content }"></c:out>
						</p>
						<p class="date">${ comment.timestamp }</p>
					</div>
				</c:forEach>
			</div>
			<form method="post"
				action="${ requestScope.site.id }/comment"
				onSubmit="return validateComment(this)">
				<div class="input">
					<label for="comment">Commentaire: </label>
					<input type="hidden" name="comment.entityId" value="${ requestScope.site.id }"/>
					<textarea name="comment.content" id="comment"
						placeholder="entrer un nouveau commentaire"></textarea>
					<input type="submit" />
				</div>
			</form>
		</div>
	</div>
	<%@ include file="/WEB-INF/jsp/javascript/comment.js.jsp"%>
</body>
</html>
