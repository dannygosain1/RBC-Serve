$(document).ready(function() {
	$.get('/api/get_posts?', function(data, status) {
		var jsonObj = $.parseJSON('['+data+']');
		if (status == "success") {
			document.getElementById("job-list").innerHTML = "";
			jsonObj.forEach(function(job) {
				document.getElementById("job-list").innerHTML += '<li>'+
					'<div class="industry">'+jsonObj[0].service+'</div>'+
					'<div class="dscription">'+jsonObj[0].description+'</div'+
					'<div class="budget">'+jsonObj[0].budget+'</div>'+
					'</li>';
			});
		} 
	});

	var submitsearch = $("#submitsearch");
	submitsearch.click(function() {
		let service = $("#servicesearch").val(),
				location = $("#locationsearch").val(),
				reqstring = '/api/search_posts?location="'+location+'"&service="'+service+'"';
		$.get(reqstring, function(data, status){
			var jsonObj = $.parseJSON('['+data+']')
			if (status == "success") {
				$(".submit-status").innerHTML = "Submitted successfully.";
				document.getElementById("job-list").innerHTML = "";
				jsonObj.forEach(function(job) {
					document.getElementById("job-list").innerHTML += '<li>'+
						'<div class="industry">'+jsonObj[0].service+'</div>'+
						'<div class="dscription">'+jsonObj[0].description+'</div'+
						'<button type="button" class="message">Message</div>'+
						'</li>';
				});
			} else {
				$(".submit-status").innerHTML = "Submission failed. Please try again";			
			}
		});		
	});	
});
