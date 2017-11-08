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
			<h2>${ secteur.name }</h2>
			<p class="description">"${ secteur.description }"</p>
		</div>
		<div class="card-content">
			<h3>Les voies de ce secteur:</h3>
			<form action="voie" method="get" class="list">
				<c:forEach items="${ secteur.voies }" var="voie">
					<div class="clickable"
						onClick="navigate('/voie?id=${ voie.id }')">
						<p>${ voie.name }(${ voie.cotation }, ${ voie.length }m)</p>
						<i class="material-icons">&#xE315;</i>
					</div>
				</c:forEach>
			</form>


			<h3>Commentaires:</h3>
			<div class="list">
				<c:forEach items="${ secteur.comments }" var="comment">
					<div class="list-item-column">
						<p>
							<c:out value="${ comment.content }"></c:out>
						</p>
						<p class="date">${ comment.timestamp }</p>
					</div>
				</c:forEach>
			</div>

			<form method="post" action="secteur?id=${ requestScope.secteur.id }#comment" onSubmit="return validateComment(this)">
				<div class="input">
					<label for="comment">Commentaire: </label>
					<textarea type="text" name="content" id="comment"
						placeholder="entrer un nouveau commentaire"></textarea>
					<input type="hidden" name="id" value="${ secteur.id }" /> <input
						type="submit" />
				</div>
			</form>
		</div>
	</div>
	<%@ include file="/WEB-INF/jsp/javascript/comment-js.jsp" %>
</body>
</html>
