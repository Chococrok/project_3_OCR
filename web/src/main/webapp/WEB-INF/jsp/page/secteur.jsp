<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/jsp/style/global-style.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${ requestScope.secteur.name }</title>
</head>
<body>
	<div class="card">
		<div class="card-header">
			<div class="editable">
				<h2>${ requestScope.secteur.name }</h2>
				<input value="editer" class="editButton" type="button"
					onclick="return navigate('/secteur/edit?id=${requestScope.secteur.id}')">
			</div>
			<p class="description">"${ secteur.description }"</p>
		</div>
		<div class="card-content">
			<h3>Les voies de ce secteur:</h3>
			<div class="list">
				<c:forEach items="${ secteur.voies }" var="voie">
					<div class="clickable" onClick="navigate('/voie?id=${ voie.id }')">
						<p>${ voie.name }(${ voie.cotation },${ voie.length }m)</p>
						<i class="material-icons">&#xE315;</i>
					</div>
				</c:forEach>
			</div>

			<c:if test="${ empty requestScope.secteur.voies }">
				<p class="false">Aucune voie n'est connue pour ce secteur. En
					connaissez vous une ?</p>
			</c:if>


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

			<form method="post"
				action="secteur?id=${ requestScope.secteur.id }#comment"
				onSubmit="return validateComment(this)">
				<div class="input">
					<label for="comment">Commentaire: </label>
					<textarea type="text" name="content" id="comment"
						placeholder="entrer un nouveau commentaire"></textarea>
					<input type="hidden" name="id" value="${ secteur.id }" />
					<input type="submit" />
				</div>
			</form>
		</div>
	</div>
	<%@ include file="/WEB-INF/jsp/javascript/comment.js.jsp"%>
</body>
</html>
