$(document).ready(function () {

	var applicationURL = "";
	
	$("#inviteButton").click(function(e) {
		e.preventDefault();
		$.ajax({
			url : applicationURL+"/invite", 
	        type: "POST",
	        dataType: "json"
		}).success(function (result) {
		    
		    	
	    });
    });
});