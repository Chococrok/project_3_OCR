<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
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

				<h3>Secteurs de ce site:</h3>
				<form action="secteur" method="get" class="list">
					<c:forEach items="${ site.secteurs }" var="secteur">
						<div class="clickable"
							onClick="navigate('secteur?id=${ secteur.id }')">
							<p>${ secteur.name }</p>
							<i class="material-icons">&#xE315;</i>
						</div>
					</c:forEach>
				</form>

			</c:if>

			<h3>Topos associés à ce site:</h3>
			<form action="secteur" method="get" class="list">
				<c:forEach items="${ requestScope.topos }" var="topo">
					<div class="clickable" onClick="navigate('topo?id=${ topo.id }')">
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
			<form method="post" action="site#comment">
				<div class="input">
					<label for="comment">Commentaire: </label>
					<textarea name="content" id="comment"
						placeholder="entrer un nouveau commentaire"></textarea>
					<input type="hidden" name="id" value="${ site.id }" /> <input
						type="submit" />
				</div>
			</form>
		</div>
	</div>
</body>
</html>
