<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/jsp/style/global-style.jsp" %>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/layout/header.jsp" %>
	<div class="card">
		<div class="card-header">
			<h2>${ site.name }</h2>
			<p class="comment">"${ site.description }"</p>
		</div>
		<div class="card-content">
			<table>
				<tr>
					<td>Coordonées géographiques:</td>
					<td>${ site.latitude }, ${ site.longitude }</td>
				</tr>
				<tr>
					<td>indication supplémentaire:</td>
					<td>${ site.howToFind }</td>
				</tr>

			</table>	
			
			<h3>Secteurs de ce site:</h3>
			<form action="secteur" method="get">
			 	<c:forEach items="${ site.secteurs }" var="secteur" >
			 	 	<div class="list-item">
			    			<p>${ secteur.name }</p>
			    			<button type="submit" name="id" value="${ secteur.id }">
							<i class="material-icons">&#xE88F;</i>
							<span>informations</span>
						</button>
		   			</div>
				</c:forEach>
			</form>
			
			<h3>Commentaires:</h3>
		 	<c:forEach items="${ site.comments }" var="comment" >
		 	 	<div class="comment-item">
		    			<p>${ comment.content }</p>
		    			<p class="date">${ comment.timestamp }</p>
	   			</div>
			</c:forEach>
			
			<form method="post" action="site">
		        <div class ="input">
		            <label for="comment">Commentaire: </label>
		            <textarea type="text" name="content" id="comment" placeholder="entrer un nouveau commentaire"></textarea>
		            <input type="hidden" name="id" value="${ site.id }"/>
        		        <input type="submit" />
		        </div>
		    </form>
		</div>
	</div>
	<%@ include file="/WEB-INF/jsp/layout/footer.jsp" %>
</body>
</html>
