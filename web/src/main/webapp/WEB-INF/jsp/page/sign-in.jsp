<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/jsp/style/global-style.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Authentification</title>
</head>
<body>
	<div class="card">

		<div class="card-header">
			<h2>Authentification</h2>
			<p class="description">L'espace "propriétaire" vous
				permet de mettre vos topos à la disposition d'autre usagers.</p>
		</div>

		<div class="card-content">
			<form id="sign-in" action="sign-in" method="post">
				<input type="email" id="email" name="email" placeholder="Email" />
				<input type="password" id="password" name="password"
					placeholder="Mot de passe" />
			</form>
			<button type="submit" form="sign-in">s'authentifier</button>
			<c:if test="${ !empty error }">
				<p class="false">${ error }</p>
			</c:if>
			<br /> <a href="sign-up">Pas encore de compte ? C'est par ici
				que ça se passe !</a>
		</div>

	</div>
</body>
</html>