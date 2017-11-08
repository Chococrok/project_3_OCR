<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<nav>
	<div class="sub-nav">
		<a onclick="navigate('/home')">Accueil</a>
		<p>/</p>
		<a onclick="navigate('/search')">Recherche</a>
		<p>/</p>
		<a onclick="navigate('/topo')">Prêt de topos</a>
	</div>

	<c:choose>
		<c:when test="${ empty sessionScope.owner }">
			<a onclick="navigate('/owner')">Connexion</a>
		</c:when>

		<c:otherwise>
			<form class="simpleForm" action="signIn" method="post">
				<a onclick="navigate('/owner')"><c:out
						value=" Mon espace (${ sessionScope.owner.fullName })" /></a>
				<button type="submit" name="action" value="signOut">Déconnexion</button>
			</form>
		</c:otherwise>
	</c:choose>

</nav>