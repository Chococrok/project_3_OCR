<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script>
	function remove() {
		var ok = confirm('Supprimer ce secteur ? Les voies associées seront également supprimées.');
		if (!ok) {
			return false;
		}
	}
</script>