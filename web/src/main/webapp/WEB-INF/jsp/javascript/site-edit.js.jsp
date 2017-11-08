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
	
	function deleteSecteur(input, secteurId) {
		var ok = confirm("Supprimer ce site ?");
		if (!ok) {
			return false;
		}
	}
</script>