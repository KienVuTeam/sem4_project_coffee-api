
// ============== TITLE ===============
let validateSpecialChars
try {
    validateSpecialChars = document.querySelectorAll(".validateSpecialChar");
} catch (error) {}
if(validateSpecialChars !== undefined){
    validateSpecialChars.forEach(function (element) {
        element.addEventListener("keypress", function (event) {
            var regex = new RegExp("^[a-zA-Z0-9 ]+$");
            var key = String.fromCharCode(!event.charCode ? event.which : event.charCode);
            if (!regex.test(key)) {
                event.preventDefault();
                return false;
            }
			this.setCustomValidity("");
        });
    
        element.addEventListener("blur", function (e) {
            let subVal = this.value;
                         
            element.value = subVal.replace(/[^a-zA-Z0-9 ]/g, ''); 
            element.value = element.value.trim();
        });
    
        element.addEventListener("paste", function (e) {
            e.preventDefault();
        });
    
        element.addEventListener("drop", function (e) {
            e.preventDefault();
        });
    });
}

// ============== NUMBER ===============
    let validateNumberValues
try {
    validateNumberValues = document.querySelectorAll(".validateNumberValue");
} catch (error) {}
if(validateNumberValues != undefined) {
    validateNumberValues.forEach(function (element) {
        element.addEventListener("change", function () {
            let value = this.value;
            if (value != "") {
                this.value = 0 + "" + parseInt(value, 10);
            }

            if (value <= 0) {
                this.value = '';
            }
        });

        element.addEventListener("keypress", function (event) {
            if (this.value.length >= 10) {
                event.preventDefault();
                return false;
            };

            var regex = new RegExp("^[0-9]+$");
            var key = String.fromCharCode(!event.charCode ? event.which : event.charCode);
            if (!regex.test(key)) {
                event.preventDefault();
                return false;
            }
			this.setCustomValidity("");
        });

        element.addEventListener("blur", function (e) {
			this.value = parseInt(this.value);
        });

        element.addEventListener("paste", function (e) {
            e.preventDefault();
        });

        element.addEventListener("drop", function (e) {
            e.preventDefault();
        });
    })
}

// ============== PHONE NUMBER ===============
    let validatePhoneNumberValues
try {
    validatePhoneNumberValues = document.querySelectorAll(".validatePhoneNumberValue");
} catch (error) {}
if(validatePhoneNumberValues != undefined) {
    validatePhoneNumberValues.forEach(function (element) {
        element.addEventListener("change", function () {
            let value = this.value;
            if (value != "") {
                this.value = 0 + "" + parseInt(value, 10);
            }

            if (value <= 0) {
                this.value = '';
            }
        });

        element.addEventListener("keypress", function (event) {
            if (this.value.length >= 10) {
                event.preventDefault();
                return false;
            };

            var regex = new RegExp("^[0-9]+$");
            var key = String.fromCharCode(!event.charCode ? event.which : event.charCode);
            if (!regex.test(key)) {
                event.preventDefault();
                return false;
            }
			this.setCustomValidity("");
        });

        element.addEventListener("blur", function () {
            let value = this.value;
            if (value.length < 10) {
                this.setCustomValidity("Please enter 10 digit phone number");
                this.reportValidity();
            }
        });

        element.addEventListener("paste", function (e) {
            e.preventDefault();
        });

        element.addEventListener("drop", function (e) {
            e.preventDefault();
        });
    })
}

// ============== EMAIL ===============
    let validateEmails
try {
    validateEmails = document.querySelectorAll(".validateEmail");
} catch (error) {}
if(validateEmails != undefined){
    validateEmails.forEach(function (element) {
        element.addEventListener("keypress", function (event) {
            var regex = new RegExp("^[a-zA-Z0-9._@]$");
            var key = String.fromCharCode(!event.charCode ? event.which : event.charCode);
            if (!regex.test(key)) {
                event.preventDefault();
                return false;
            }
            if(this.value.length > 50){
                event.preventDefault();
                return false;
			}
			this.setCustomValidity("");
        });
    
        element.addEventListener("blur", function (e) {
            let regex = new RegExp("^[a-zA-Z0-9._]+@[a-z0-9.]+\\.[a-z]{2,4}$");
            if(!regex.test(this.value)) {
                this.setCustomValidity("Invalid email address");
                this.reportValidity();
            }
        });
    
        element.addEventListener("paste", function (e) {
            e.preventDefault();
        });
    
        element.addEventListener("drop", function (e) {
            e.preventDefault();
        });
    }); 
}

// ============== LOCATION ===============
    let validateLocations
try {
    validateLocations = document.querySelectorAll(".validateLocation");
} catch (error) {}
if(validateLocations != undefined){
    validateLocations.forEach(function (element) {
        element.addEventListener("keypress", function (event) {
            var regex = new RegExp("^[a-zA-Z0-9 /,)(._-]+$");
            var key = String.fromCharCode(!event.charCode ? event.which : event.charCode);
            if (!regex.test(key)) {
                event.preventDefault();
                return false;
            }
			this.setCustomValidity("");
        });
    
        element.addEventListener("blur", function (e) {
            element.value = element.value.trim();
        });
    
        element.addEventListener("paste", function (e) {
            e.preventDefault();
        });
    
        element.addEventListener("drop", function (e) {
            e.preventDefault();
        });
    }); 
}

// ============== Password ===============
    let validatePasswords
try {
    validatePasswords = document.querySelectorAll(".validatePassword");
} catch (error) {}
if(validatePasswords != undefined){
    validatePasswords.forEach(function (element) {
        element.addEventListener("keypress", function (event) {
            if(this.value.length > 50){
                event.preventDefault();
                return false;
			}
			this.setCustomValidity("");
        });

        element.addEventListener("blur", function (e) {
            element.value = element.value.trim();
            if(this.value.length < 8){
                this.setCustomValidity("Please enter at least 8 characters");
                this.reportValidity();
            }else{
                this.setCustomValidity("");
            }
        });
    
        element.addEventListener("paste", function (e) {
            e.preventDefault();
        });
    
        element.addEventListener("drop", function (e) {
            e.preventDefault();
        });
    }); 
}