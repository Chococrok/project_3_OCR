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
			<h2>${ voie.name }</h2>
			<p class="description">"${ voie.description }"</p>
		</div>
		<div class="card-content">
			<h3>Description:</h3>
			<table>
				<tr>
					<td>Longueur de la voie:</td>
					<td>${ voie.length } m</td>
				</tr>
				<tr>
					<td>Nombre de points:</td>
					<td>${ voie.pointNumber }</td>
				</tr>
				<tr>
					<td>Cotation:</td>
					<td>${ voie.cotation }</td>
				</tr>
			</table>

			<h3>Les longueurs de ${ voie.name }</h3>
			<div class="list">
				<c:forEach items="${ voie.longueurs }" var="longueur">
					<div class="list-item-column">
						<p>${ longueur.name }</p>
						<p class="date">${ longueur.length } m</p>
						<p class="date">${ longueur.cotation }</p>
					</div>
				</c:forEach>
			</div>

			<h3>Commentaires:</h3>
			<div class="list">
				<c:forEach items="${ voie.comments }" var="comment">
					<div class="list-item-column">
						<p>
							<c:out value="${ comment.content }"></c:out>
						</p>
						<p class="date">${ comment.timestamp }</p>
					</div>
				</c:forEach>
			</div>

			<form method="post" action="voie?id=${ requestScope.voie.id }#comment" onSubmit="return validateComment(this)">
				<div class="input">
					<label for="comment">Commentaire: </label>
					<textarea name="content" id="comment"
						placeholder="entrer un nouveau commentaire"></textarea>
					<input type="hidden" name="id" value="${ voie.id }" /> <input
						type="submit" />
				</div>
			</form>
		</div>
	</div>
</body>
</html>
