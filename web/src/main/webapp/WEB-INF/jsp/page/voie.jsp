<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/jsp/style/global-style.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${ requestScope.voie.name }</title>
</head>
<body>
	<div class="card">
		<div class="card-header">
			<div class="editable">
				<h2>${ requestScope.voie.name }</h2>
				<input value="editer" class="editButton"
					type="button" onclick="return navigate('/voie/edit?id=${requestScope.voie.id}')">
			</div>
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
