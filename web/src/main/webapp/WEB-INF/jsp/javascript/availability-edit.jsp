<script>
	function edit(value) {
		var input = document.getElementById('input' + value);
		var button = document.getElementById('button' + value);
		var available = document.getElementById('available' + value);

		if (input.disabled) {
			input.disabled = false;
			toggleText(button);
			return false;
		}

		input.disabled = true;
		available.value = input.checked;
		toggleText(button);
	}

	function toggleText(button) {
		var text = button.firstChild;
		text.data = text.data == 'editer' ? 'confirmer' : 'editer';
	}
</script>