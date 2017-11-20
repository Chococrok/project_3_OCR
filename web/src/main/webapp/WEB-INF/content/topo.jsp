<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${ requestScope.topo.name }</title>
<%@ include file="/WEB-INF/jsp/style/global-style.jsp"%>
</head>
<body>
	<div class="card">
		<div class="card-header">
			<div class="editable">
				<h2>
					<c:out value="${ requestScope.topo.name }"></c:out>
				</h2>
				<input value="editer" class="editButton" type="button"
					onclick="return navigate('/topo/${requestScope.topo.id}/edit')">
			</div>
		</div>
		<div class="card-content">
			<h3>site correspondant:</h3>
			<div class="clickable"
				onClick="navigate('/site/${ requestScope.topo.site.id }')">
				<p>${ requestScope.topo.site.name }</p>
				<i class="material-icons">&#xE315;</i>
			</div>
			<h3>Propriétaires:</h3>

			<div class="list">
				<c:forEach items="${ requestScope.topo.owners }" var="owner">
					<table>
						<tr>
							<td>Prénom:</td>
							<td><c:out value="${ owner.firstName }"></c:out></td>
							<td class="${ owner.topoAvailable }"><c:out
									value="${ owner.topoAvailable? 'Disponible':'Déjà emprunté' }"></c:out></td>
						</tr>
						<c:if test="${ owner.topoAvailable }">
							<tr>
								<td>Email:</td>
								<td><c:out value="${ owner.email }"></c:out></td>
							</tr>
							<tr>
								<td>Téléphone:</td>
								<td><c:out value="${ owner.phoneNumber }"></c:out></td>
							</tr>
						</c:if>
					</table>
				</c:forEach>
			</div>

			<h3>Commentaires:</h3>
			<div class="list">
				<c:forEach items="${ requestScope.topo.comments }" var="comment">
					<div class="list-item-column">
						<p>
							<c:out value="${ comment.content }"></c:out>
						</p>
						<p class="date">${ comment.timestamp }</p>
					</div>
				</c:forEach>
			</div>

			<form method="post"
				action="topo?id=${ requestScope.topo.id }#comment"
				onSubmit="return validateComment(this)">
				<div class="input">
					<label for="comment">Commentaire: </label>
					<textarea name="content" id="comment"
						placeholder="entrer un nouveau commentaire"></textarea>
					<input type="hidden" name="id" value="${ topo.id }" />
					<input type="submit" />
				</div>
			</form>
		</div>
	</div>
	<%@ include file="/WEB-INF/jsp/javascript/comment.js.jsp"%>
</body>
</html>