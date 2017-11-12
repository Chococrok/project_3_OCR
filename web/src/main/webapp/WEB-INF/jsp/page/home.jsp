<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/jsp/style/global-style.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Escalade.com</title>
</head>
<body>
	<div class="card">
		<div class="card-header">
			<h2>Listes des sites:</h2>
		</div>
		<div class="card-content">
			<div class="list">
				<c:forEach items="${ sites }" var="site">
					<div class="clickable" onClick="navigate('/site?id=${ site.id }')">
						<p>${ site.name }</p>
						<i class="material-icons">&#xE315;</i>
					</div>
				</c:forEach>
			</div>

			<form style="display: none;" id="addSiteForm" action="home"
				method="post">
				<input name="name" placeholder="nom du site" required />
				<input name="description" placeholder="description du site" required />
				<input name="howToFind" required
					placeholder="comment trouver ce site" />
				<input name="latitude" required
					placeholder="latitude" />
				<input name="longitude" required
					placeholder="longitude" />
			</form>
			<button form="addSiteForm" type="submit" name="action"
				value="addSite"
				onclick="return displayForm(addSiteForm, this)">Nouveau
				site</button>
		</div>
	</div>
</body>
</html>
