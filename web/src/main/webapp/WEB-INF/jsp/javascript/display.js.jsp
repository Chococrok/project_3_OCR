<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script>	
	function activateForm(form, button) {
		if (form.style.display == 'none') {
			form.style.display = 'flex';
			button.firstChild.data = 'Confirmer';
			return false;
		}
	}
</script>