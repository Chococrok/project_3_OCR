<script>
	const contextPath = '${pageContext.request.contextPath}';
	function navigate(adress) {
		location.href = contextPath + adress;
	}
</script>