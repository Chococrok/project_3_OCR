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
			<p class="description">L'espace "propriétaire" vous permet de
				mettre vos topos à la disposition d'autre usagers.</p>
		</div>

		<div class="card-content">
			<form id="signIn" action="signIn" method="post">
				<span> <input type="email" id="email" name="email"
						placeholder="Email" /> <input type="password" id="password"
						name="password" placeholder="Mot de passe" />
				</span>
				<button type="submit" form="signIn" name="action" value="signIn">s'authentifier</button>
				<c:if test="${ !empty error }">
					<p class="false">${ error }</p>
				</c:if>
				<br /> <a onclick="navigate('/signUp')">Pas encore de compte ?
					C'est par ici que ça se passe !</a>
			</form>

		</div>

	</div>
</body>
</html>