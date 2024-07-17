/**
 * 
 */
$.validator.addMethod("threeSpaces", function(value, element) {
            return !/\s{3}/.test(value);
        }, "Please enter at least 3 consecutive spaces.");
		//
		$.validator.addMethod("noSpecialChars", function(value, element) {
			//let regex = /^[!@#$%^&*(),.?":{}|<>]+{2}$/;
		    //return /^[a-zA-Z0-9]+$/.test(value);
		    // /^[!@#$%^&*(),.?":{}|<>]+$/
		    return /^[a-zA-Z0-9 .,]+$/.test(value);
		  }, "Special characters are not allowed!");
		//
		$("#formAdd").validate({
			rules: {
				blogTitle: {
					required: true,
					minlength: 4,
					threeSpaces: true  ,
					maxlength: 50,
					noSpecialChars: true
				},
				//blogImage: "required",
				//hdImage: "required",
				//ckeditor1: "required",
				rePlaceDescription: {
					required: true,
					minlength: 50,
					maxlength: 2000,
					threeSpaces: true,
					noSpecialChars: true
					
				}

			},
			messages: {
				blogTitle: {
					required: "Required to enter!",
					minlength: "You must enter more than 4 characters!",
					maxlength: "Enter up to 50 characters!",
					noSpecialChars : "Special characters are not allowed!"
					
				},
				rePlaceDescription: {
					required: "Required to enter!",
					minlength: "You must enter more than 50 characters!",
					maxlength: "Enter up to 2000 characters!",
					noSpecialChars : "Special characters are not allowed!"
				}
			}
		});
		//
		
		$("#formEdit").validate({
			rules:{
				edit_blogTitle:{
					required: true,
					minlength: 4,
					maxlength: 50,
					threeSpaces: true ,
					noSpecialChars: true
				},
				edit_rePlaceDescription:{
					required: true,
					minlength: 50,
					maxlength: 2000,
					threeSpaces: true ,
					noSpecialChars: true
				}
				
			},
			messages:{
				edit_blogTitle:{
					required: "Required to enter!",
					minlength: "You must enter more than 15 characters!",
					maxlength: "Enter up to 50 characters!",
					noSpecialChars : "Special characters are not allowed!"
				},
				edit_rePlaceDescription:{
					required: "Required to enter!",
					minlength: "You must enter more than 50 characters!",
					maxlength: "Enter up to 2000 characters!",
					noSpecialChars : "Special characters are not allowed!"
				}
			}
		});