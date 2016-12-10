$(document).ready(function () {
	
	var applicationURL = "http://localhost:8080/capstone/";
	
	$('#newsLetterButton')[0].click(){
	
	$.ajax({
    	url : applicationURL+"/newsletter", 
        type: "GET",
        dataType: "json"
    }).success(function () {  
        sendNewsletter.send();
    }).fail(function(xhr, status, errorMessage){
        console.log(errorMessage);
        console.log(status);
        console.log(xhr);
    });
	
	$("#refreshLink").click(refreshSurveyResults);
	}
})