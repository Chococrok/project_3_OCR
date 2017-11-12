<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script>	
	function displayForm(form, button, toHide) {
		if (form.style.display == 'none') {
			
			if (toHide) {
				toHide.forEach((element) => {
					element.style.display = 'none';
				});
			}
			
			form.style.display = 'flex';
			button.firstChild.data = 'Confirmer';
			return false;
		}
	}
	
	function enableForm(button, toEnable) {
		if (toEnable[0].disabled) {
			toEnable.forEach((element) => {
				element.disabled = false;
			});
			
			button.firstChild.data = 'Confirmer';
			return false;
		}	
	}
</script>