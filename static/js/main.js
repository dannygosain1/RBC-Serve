$(document).ready(function() {

	var clienttab = $("#client"),
		providertab = $("#provider"),
	    clientpage = $("#client-page"),
	    providerpage = $("#provider-page"),
	    diffaddress = $("#diffaddress"),
	    submitpost = $("#submitpost"),
	    submitsearch = $("#submitsearch");

	clienttab.click(function() {
		if(clientpage.hasClass("hidden-content")) {
			clienttab.addClass("active");
			providertab.removeClass("active");
			clientpage.removeClass("hidden-content");
			providerpage.addClass("hidden-content");			
		}
	}); 
	providertab.click(function() {
		if(providerpage.hasClass("hidden-content")) {
			providertab.addClass("active");
			clienttab.removeClass("active");
			providerpage.removeClass("hidden-content");
			clientpage.addClass("hidden-content");			
		}		
	}); 
	diffaddress.click(function() {
		let addressfields = $("#addressfields")
		if(addressfields.hasClass("hidden-content")) {
			addressfields.removeClass("hidden-content");
		}	else {
			addressfields.addClass("hidden-content");			
		}
	});

	submitpost.click(function() {
		let	formdata = {
					service : $("#servicelist").val(),
					budget : $("#budget").val(),
					description : $("#description").val()					
				},
				diffaddress = $("#diffaddress").checked;
		if(diffaddress) {
			formdata["diffaddress"] = true;
			formdata["streetaddress"] = $("#streetaddress").val();
			formdata["cityaddress"] = $("#cityaddress").val();
			formdata["provinceaddress"] = $("#provinceaddress").val();
			formdata["postaladdress"] = $("#postaladdress").val();
		} else {
			formdata["diffaddress"] = false;			
		}
		$.post("/api/create_post", formdata, function(data, status){
			if (status == "success") {
				$("#submit-status").innerHTML = "Submitted successfully.";
			} else {
				$("#submit-status").innerHTML = "Submission failed. Please try again";			
			}
		});
	});

	submitsearch.click(function() {
		let formdata = {
			service : $("#servicesearch").val(),
			city : $("#citysearch").val()
		};
		$.post("/api/create_post", formdata, function(data, status){
			if (status == "success") {
				$("#submit-status").innerHTML = "Submitted successfully.";
				data.forEach(function(job) {
					var tttt = $(".job-list ul");
				});
			} else {
				$("#submit-status").innerHTML = "Submission failed. Please try again";			
			}
		});		
	})
});


