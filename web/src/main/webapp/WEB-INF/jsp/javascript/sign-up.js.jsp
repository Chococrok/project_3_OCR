<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script>
	var error = document.getElementById('error');
	
	function validate(form) {
		if (!form.firstName.value || !form.lastName.value || !form.email.value) {
			error.style.display = 'unset';
			error.innerHTML = 'Vous devez renseigner le nom le prénom et l\'email.';
			return false;
		}
		
		if (!form.password.value || !form.confirmPassword.value) {
			error.style.display = 'unset';
			error.innerHTML = 'Renseignez le mot de passe et confirmez le.';
			return false;
		}
		
		if (form.password.value !== form.confirmPassword.value) {
			error.style.display = 'unset';
			error.innerHTML = 'Les mot de passe ne correspondent pas.';
			return false;
		}
	}
</script>