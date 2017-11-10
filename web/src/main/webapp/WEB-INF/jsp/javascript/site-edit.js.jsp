<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script>
	<%--function editTopo(button, hidden, checkbox) {

		if (checkbox.disabled) {
			checkbox.disabled = false;
			toggleTextEdit(button);
			return false;
		}

		checkbox.disabled = true;
		hidden.value = checkbox.checked;
		toggleTextEdit(button);
	}

	function toggleTextEdit(button) {
		var text = button.firstChild;
		text.data = text.data == 'editer' ? 'confirmer' : 'editer';
	}--%>
	
	function remove() {
		var ok = confirm('Supprimer ce site ? Les secteurs et voies associés seront également supprimés.');
		if (!ok) {
			return false;
		}
	}
</script>