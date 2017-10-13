<html>
<body>
	<h2>Hello Arnaud</h2>

 	<p>${ sites[0].name } <c:out value="coucou" /></p>
 	
 	<c:forEach items="${ sites }" var="site" >
    		<p> <c:out value="${ site.name }" /> !</p>
	</c:forEach>

</body>
</html>
