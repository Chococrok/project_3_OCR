<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/jsp/style/global-style.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Inscription</title>
</head>
<body>
	<div class="card">

		<div class="card-header">
			<h2>Inscription</h2>
			<p class="description">L'espace "propriétaire" vous permet de
				mettre vos topos à la disposition d'autre usagers.</p>
		</div>

		<div class="card-content">
			<form id="signUp" action="signUp#signUp" method="post"
				onSubmit="return validate(this)">
				<input type="text" id="firstName" name="firstName"
					placeholder="Prénom" />
				<input type="text" id="lastName" name="lastName"
					placeholder="Nom" />
				<input type="email" id="email" name="email" placeholder="Email" />
				<input type="password" id="password" name="password"
					placeholder="Mot de passe" />
				<input type="password" id="confirmPassword"
					name="confirmPassword" placeholder="Confirmez le mot de passe" />
			</form>

			<p id="error" class="false" style="display: none;"></p>
			<c:if test="${ !empty error }">
				<p class="false">${ error }</p>
			</c:if>

			<button type="submit" form="signUp" name="action" value="signUp">s'authentifier</button>
			
		</div>

	</div>

	<%@ include file="/WEB-INF/jsp/javascript/sign-up-js.jsp"%>

</body>
</html>