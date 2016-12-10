$(document).ready(function () {
	
	//default text needs to be set on html element
	var defaultText = "Please tell us about yourself";
	
	function endEdit(e) {
	    var input = $(e.target),
	        label = input && input.prev();
	
	    label.text(input.val() === '' ? defaultText : input.val());
	    console.log(defaultText);
	    input.hide();
	    label.show();
	    $('#form').submit();
//	    sendUpdate(input);
	}
	
	function changeSubmitButton() {
		$('#form').attr('action', window.location.pathname);
		$(':submit').val("Submit Changes");
	}
	
	function aggregateData(allData) {
		$('.clickedit').each(function() {
			var input = $(this);
			allData[input.attr('id')] = input.val();
		});
	}
	
	function sendUpdate(input) {
		var allData = {'id' : $('#id').val()}; // model object id
		aggregateData(allData);
		var testData = {description : "d"};
		var pathname = window.location.pathname;
		console.log(pathname);
		$.ajax({
		    url: pathname,
		    type: 'POST',
		    data: testData,
		    //dataType: 'json',
//		    contentType : "application/json",
		    success: function(result) {
		    	if (result) {
		    		alert("Updated!");
		    	} else {
		    		alert("DB problem.")
		    	}
		    },
		    error: function(result) {
		    	//console.log(input.val());
		    }
		}).fail(function(xhr, status, errorMessage){
	        console.log(errorMessage);
	        console.log(status);
	        console.log(xhr);
		});
	}
	var label = $('.pull-left');
	var input = $('.clickedit');
	input.val(label.text());
	label.text(input.val() === '' ? defaultText : input.val());
	input.hide()
	.focusout(endEdit)
	.keyup(function (e) {
	    if ((e.which && e.which == 13) || (e.keyCode && e.keyCode == 13)) {
	        endEdit(e);
	        return false;
	    } else {
	        return true;
	    }
	})
	.prev().click(function () {
	    $(this).hide();
	    $(this).next().show().focus();
	    //changeSubmitButton();
	});
});