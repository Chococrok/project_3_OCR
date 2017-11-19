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
					<div class="clickable" onClick="navigate('/site/${ site.id }')">
						<p>${ site.name }</p>
						<i class="material-icons">&#xE315;</i>
					</div>
				</c:forEach>
			</div>

			<form style="display: none;" id="addSiteForm" action="site/add"
				method="post">
				<input name="site.name" placeholder="nom du site" required />
				<input name="site.description" placeholder="description du site"
					required />
				<input name="site.howToFind" required
					placeholder="comment trouver ce site" />
				<input name="site.latitude" required placeholder="latitude" pattern="^-?[0-9]{1,3},[0-9]+$" />
				<input name="site.longitude" required placeholder="longitude" pattern="^-?[0-9]{1,3},[0-9]+$" />
			</form>
			<button form="addSiteForm" type="submit"
				onclick="return displayForm(addSiteForm, this)">Nouveau
				site</button>
		</div>
	</div>
</body>
</html>
