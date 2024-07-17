
var toast = document.querySelector(".toast");
var wrapCheck = document.querySelector(".toast .wrap-check");
var close = document.querySelector(".toast .toast-close");
var progress = document.querySelector(".toast .progress");
var text1 = document.querySelector(".toast .text-1");
var text2 = document.querySelector(".toast .text-2");

function showAlert(type, message) {
	
	const success = "#40f467";
	const danger = "#FF414D";
	const warning = "#FCDA05";
	
	const successCheck = '<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-check-circle toast-check"><path d="M22 11.08V12a10 10 0 1 1-5.93-9.14"></path><polyline points="22 4 12 14.01 9 11.01"></polyline></svg>';
	const warningCheck = '<svg xmlns="http://www.w3.org/2000/svg" width="36" height="36" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5" stroke-linecap="round" stroke-linejoin="round" class="feather feather-alert-triangle"><path d="M10.29 3.86L1.82 18a2 2 0 0 0 1.71 3h16.94a2 2 0 0 0 1.71-3L13.71 3.86a2 2 0 0 0-3.42 0z"></path><line x1="12" y1="9" x2="12" y2="13"></line><line x1="12" y1="17" x2="12.01" y2="17"></line></svg>';

	switch (type) {
		case "success": document.documentElement.style.setProperty("--selection-background", success);
			wrapCheck.innerHTML = successCheck;
			text1.innerHTML = "Success"; break;
		case "danger": document.documentElement.style.setProperty("--selection-background", danger);
			wrapCheck.innerHTML = warningCheck;
			text1.innerHTML = "Error"; break;
		case "warning": document.documentElement.style.setProperty("--selection-background", warning);
			wrapCheck.innerHTML = warningCheck;
			text1.innerHTML = "Warning"; break;
		default: break;
	}

	toast.classList.add("active");
	toast.classList.add("showing");
	progress.classList.add("active");
	text2.innerHTML = message;

	setTimeout(() => {
		toast.classList.remove("active");
		toast.classList.remove("showing");
		text1.innerHTML = "";
		text2.innerHTML = "";
	}, 5000)

	setTimeout(() => {
		progress.classList.remove("active");
	}, 5300)
}

close.addEventListener("click", () => {
	toast.classList.remove("active");
	toast.classList.remove("showing");
	text1.innerHTML = "";
	text2.innerHTML = "";

	setTimeout(() => {
		progress.classList.remove("active");
	}, 300)
})