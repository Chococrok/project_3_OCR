<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script>
	var error = document.getElementById('error');

	function editAvailability(value) {
		var input = document.getElementById('input' + value);
		var button = document.getElementById('button' + value);
		var available = document.getElementById('available' + value);

		if (input.disabled) {
			input.disabled = false;
			toggleTextEdit(button);
			return false;
		}

		input.disabled = true;
		available.value = input.checked;
		toggleTextEdit(button);
	}

	function enableForm(button, inputList) {
		if (inputList[1].disabled) {
			inputList.forEach((input) => {
				input.disabled = false;
			});
			
			toggleTextEdit(button);
			return false;
		}
	}

	function uncheckRadio(name) {
		var radios = document.getElementsByName(name);
		for (var i = 0; i < radios.length; i++)
			radios[i].checked = false;
	}

	function add(value) {
		var button = document.getElementById('button' + value);
		var form = document.getElementById('form' + value);
		console.log(form.style.display);

		if (form.style.display == 'none') {
			toggleTextAdd(button);
			toggleDisplay(form);
			return false;
		}

		if (!form.name.value) {
			return false;
		}

		if (!form.siteId.value && !form.siteName.value) {
			return false;
		}

		toggleTextAdd(button);
		toggleDisplay(form);
	}

	function toggleTextEdit(button) {
		var text = button.firstChild;
		text.data = text.data == 'éditer' ? 'confirmer' : 'éditer';
	}

	function toggleTextAdd(button) {
		var text = button.firstChild;
		text.data = text.data == 'ajouter' ? 'confirmer' : 'ajouter';
	}

	function toggleDisplay(element) {
		element.style.display = element.style.display == 'none' ? 'unset'
				: 'none';
	}

	function setDisplay(toDisplay, toHide) {
		toDisplay.style.display = 'flex';
		toHide.style.display = 'none';
	}

	function validateNew(form) {
		if (!form.topoName.value || !form.siteId.value && !form.siteName.value) {
			error.style.display = 'unset';
			error.innerHTML = 'Entrez au moins le nom du nouveau topo et choissez un site ou ajoutez en un nouveau';
			return false;
		}
	}
	
	function validateExisting(form) {
		if (!form.topoId.value) {
			error.style.display = 'unset';
			error.innerHTML = 'Choisissez au moins un topo à ajouter ou ajoutez en un nouveau';
			return false;
		}
	}
</script>