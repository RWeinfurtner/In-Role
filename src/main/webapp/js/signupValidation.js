

$(document).ready(function () {

    // Validate takes an object, not a function
    // Objects in javascript use { .. } notation and are the same as key / value pairs
    $("#applicationForm").validate({

        debug: true,
        rules: {

            email: {
                email: true,
                required: true
                
            },
    		password: {
    			required: true,
    			minlength: 8,
    			strongpassword: true
    		},
    		verifyPassword: {
    			equalTo: "#password"
    		}
        },
        messages: {
        	email: {
        		email: "A valid email is required to sign up."
        	},
        	password: {
        		required: "Please enter a password.",
        		minlength: "Please enter a password that is longer than 7 characters."
        	},
        	verifyPassword: {
        		equalTo: "The password does not match the one entered previously."
        	}
        },
        errorClass: "error",
        validClass: "valid"

    });

});

$.validator.addMethod("strongpassword", function (value, index) {
    return value.match(/[A-Z]/) && value.match(/[a-z]/) && value.match(/\d/);  //check for one capital letter, one lower case letter, one num
}, "Please enter a strong password (one capital, one lower case, and one number");
