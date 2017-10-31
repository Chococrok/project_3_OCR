<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<nav>
	<div class="sub-nav">
		<a href="home">Accueil</a>
		<p>/</p>
		<a href="search">Recherche</a>
		<p>/</p>
		<a href="topo">Prêt de topos</a>
	</div>

	<c:choose>
		<c:when test="${ empty sessionScope.owner }">
			<a href="owner">Connexion</a>
		</c:when>

		<c:otherwise>
			<a href="owner"><c:out value=" Mon espace (${ sessionScope.owner.fullName })" /></a>
		</c:otherwise>
	</c:choose>

</nav>