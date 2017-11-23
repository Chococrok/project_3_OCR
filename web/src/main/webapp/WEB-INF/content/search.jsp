<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/jsp/style/global-style.jsp"%>
</head>
<body>
	<div class="card">
		<div class="card-header">
			<h2>Recherche:</h2>
		</div>
		<div class="card-content">
			<form action="${ pageContext.request.contextPath }/search/submit"
				method="get">
				<div>
					<p>Je souhaite effectuer une recherche par:</p>
					<input type="radio" name="searchForm.method" id="name" value="name"
						${ empty param.method || param.method == 'name' ? 'checked':'' } />
					<label for="name">nom</label>
					<input type="radio" name="searchForm.method" id="cotation" value="cotation"
						${ param.method == 'cotation' ? 'checked':'' } />
					<label for="cotation">cotation</label>
				</div>

				<div>
					<p>Je recherche:</p>
					<input type="radio" name="searchForm.type" id="site" value="site"
						${ empty param.type || param.type == 'site' ? 'checked':'' } />
					<label for="site">site</label>
					<input type="radio" name="searchForm.type" id="secteur" value="secteur"
						${ param.type == 'secteur' ? 'checked':'' } />
					<label for="secteur">secteur</label>
					<input type="radio" name="searchForm.type" id="voie" value="voie"
						${ param.type == 'voie' ? 'checked':'' } />
					<label for="voie">voie</label>
				</div>

				<input type="search" name="searchForm.content" />
				<button type="submit" id="submit">Rechercher</button>
			</form>
			<c:if test="${ !empty error }">
				<p class="false">${ error }</p>
			</c:if>

			<s:if test="hasActionErrors()">
				<div class="false">
					<s:actionerror />
				</div>
			</s:if>

			<c:if test="${!empty entities && !entities.isEmpty()}">
				<h3>
					<c:out value="${ param.type }${ empty param.type ? '' : 's :' }"></c:out>
				</h3>
				<div class="list">
					<c:forEach items="${ entities }" var="entity">
						<div class="clickable"
							onClick="navigate('/${ param.type }?id=${ entity.id }')">
							<p>${ entity.name }</p>
							<i class="material-icons">&#xE315;</i>
						</div>
					</c:forEach>
				</div>
			</c:if>

		</div>
	</div>
</body>
</html>
