<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script>
	function remove() {
		var ok = confirm('Supprimer cette voie ? Les longueures associées seront également supprimées.');
		if (!ok) {
			return false;
		}
	}
	
	function removeLongueur() {
		var ok = confirm('Supprimer cette longueur ?');
		if (!ok) {
			return false;
		}
	}
</script>