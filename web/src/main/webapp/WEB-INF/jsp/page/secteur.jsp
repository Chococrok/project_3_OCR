<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/jsp/style/global-style.jsp" %>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/layout/header.jsp" %>
	<div class="card">
		<div class="card-header">
			<h2>${ secteur.name }</h2>
			<p class="comment">"${ secteur.description }"</p>
		</div>
		<div class="card-content">
			<h3>Les voies de ce secteur:</h3>
			<c:forEach items="${ secteur.voies }" var="voie" >	
				<h4>${ voie.name }</h4>		
				<table>
					<tr>
						<td>Longueur de la voie:</td>
						<td>${ voie.length }</td>
					</tr>
					<tr>
						<td>Nombre de points:</td>
						<td>${ voie.pointNumber }</td>
					</tr>
					<tr>
						<td>Cotation:</td>
						<td>${ voie.cotation }</td>
					</tr>
				</table>	
			</c:forEach>

			
			<h3>Commentaires:</h3>
			<div class="list">
			 	<c:forEach items="${ secteur.comments }" var="comment" >
			 	 	<div class="comment-item">
			    			<p>${ comment.content }</p>
			    			<p class="date">${ comment.timestamp }</p>
		   			</div>
				</c:forEach>
			</div>
			
			<form method="post" action="secteur#comment">
		        <div class ="input">
		            <label for="comment">Commentaire: </label>
		            <textarea type="text" name="content" id="comment" placeholder="entrer un nouveau commentaire"></textarea>
		            <input type="hidden" name="id" value="${ secteur.id }"/>
        		        <input type="submit" />
		        </div>
		    </form>
		</div>
	</div>
	<%@ include file="/WEB-INF/jsp/layout/footer.jsp" %>
</body>
</html>
