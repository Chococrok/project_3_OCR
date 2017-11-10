<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/jsp/style/global-style.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edition d'un secteur</title>
</head>
<body>
	<div class="card">
		<div class="card-header">
			<h2>${ requestScope.secteur.name }</h2>
			<p class="description">"${ secteur.description }"</p>
		</div>
		<div class="card-content">
			<h3>Supprimer ce secteur ?</h3>
			<form class="simpleForm"
				action="edit?id=${ requestScope.secteur.id }" method="post">
				<button type="submit" name="action" value="delete"
					onClick="return remove();">
					<i class="material-icons">delete</i>
				</button>
			</form>

		</div>
	</div>
	<%@ include file="/WEB-INF/jsp/javascript/secteur-edit.js.jsp"%>
</body>
</html>