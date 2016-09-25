$(document).ready(function() {
	var email = $("#INPUT_14").val(),
		password = $("#INPUT_20").val(),				
		reqstring = '/api/search_posts?email="'+email+'"&password="'+password+'"';
	
	$("#button_2").click(function() {
		$.post(reqstring, function(data,status) {
			if(!status) {
				alert("Invalid username or password.")
			}
		});
	});
});