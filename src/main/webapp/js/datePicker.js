$(document).ready(
$(function() {
    $("#day").datepicker({dateFormat:"mm/dd/yy" }); 
    
    $('#startTime').timepicker({'timeFormat': 'H:i'});
    $('#endTime').timepicker({'timeFormat': 'H:i'});
    $('#breakStart').timepicker({'timeFormat': 'H:i', 'showDuration' : 'true'});
    $('#breakEnd').timepicker({'timeFormat': 'H:i', 'showDuration' : 'true'});
var breaks;

$("#break").change(function(){
	$("#breakEntry").toggle();
})

}


))