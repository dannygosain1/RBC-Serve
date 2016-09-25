$(document).ready(function() {
	submitpost.click(function() {
		let	formdata = {
					service : $("#servicelist").val(),
					budget : $("#budget").val(),
					description : $("#description").val(),					
					city : $("#cityaddress").val()
				}
		$.post("/api/create_post", formdata, function(data, status){
			if (status == "success") {
				$(".submit-status").innerHTML = "Submitted successfully.";
			} else {
				$(".submit-status").innerHTML = "Submission failed. Please try again";			
			}
		});
	});
});


