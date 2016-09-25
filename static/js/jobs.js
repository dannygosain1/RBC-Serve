$(document).ready(function() {

	var submitsearch = $("#submitsearch");
	submitsearch.click(function() {
		let service = $("#servicesearch").val(),
				location = $("#locationsearch").val(),
				reqstring = '/api/search_posts?location="'+location+'"&service="'+service+'"';
		$.post(reqstring, function(data, status){
			if (status == "success") {
				let joblist = $(".job-list ul");
				$(".submit-status").innerHTML = "Submitted successfully.";
					data.forEach(function(job) {
						let industry = document.createElement("DIV"),
								serviceText = job.service,
								description = document.createElement("DIV"),
								descriptionText = job.description,
								budget = document.createElement("DIV"),
								budgetText = job.budget,
								listitem = document.createElement("LI");
						industry.appendChild(serviceText);
						description.appendChild(descriptionText);
						budget.appendChild(budgetText);
						listitem.appendChild(industry).appendChild(description).appendChild(budget);
						joblist.appendChild(listitem);
				});
			} else {
				$(".submit-status").innerHTML = "Submission failed. Please try again";			
			}
		});		
	});	
});
