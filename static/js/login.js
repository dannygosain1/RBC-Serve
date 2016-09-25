$(document).ready(function() {
	var user = {
				email = $("#INPUT_14").val(),
				password = $("#INPUT_20").val(),				
			};
	
	$("#button_2").click(function() {
		$.post(, user, function(data,status) {
			if(status == "success")
				window.location.replace("../../templates/jobs.html");
			else
				alert("Log in failed.");
		});
	});
});