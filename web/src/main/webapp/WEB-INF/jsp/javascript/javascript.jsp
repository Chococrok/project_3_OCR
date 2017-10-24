<script>
	function setDisabledState() {
	   var checked = document.getElementById("cotation").checked;
	   document.getElementById("site").disabled = checked;
	   document.getElementById("secteur").disabled = checked;
	   if(checked) {
		   document.getElementById("voie").checked = checked; 
	   }
	}
</script>