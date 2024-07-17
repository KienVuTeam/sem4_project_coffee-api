// ================================
// DISCOUNT

let dateBegin = document.querySelector('#AddDiscount .dateBegin');
let dateEnd = document.querySelector('#AddDiscount .dateEnd');
let editDateBegin = document.querySelector(".editDateBegin");
let editDateEnd = document.querySelector(".editDateEnd");


// Edit Discount
editDateBegin.valueAsDate = currentDate;
editDateBegin.min = editDateBegin.value;

let maxEditDiscountValueBegin = new Date(editDateBegin.value);
maxEditDiscountValueBegin.setDate(maxEditDiscountValueBegin.getDate() - 1);
maxEditDiscountValueBegin.setMonth(maxEditDiscountValueBegin.getMonth() + 1);
maxEditDiscountValueBegin.setFullYear(maxYear);
editDateBegin.max = "" + maxEditDiscountValueBegin.getFullYear() + "-" + (maxEditDiscountValueBegin.getMonth() < 10 ? ("0" + maxEditDiscountValueBegin.getMonth()) : maxEditDiscountValueBegin.getMonth()) + "-" + maxEditDiscountValueBegin.getDate() + "";

editDateEnd.valueAsDate = tomorrowDate;
editDateEnd.min = editDateEnd.value;
let maxEditDiscountValueEnd = new Date(editDateEnd.value);
maxEditDiscountValueEnd.setDate(maxEditDiscountValueEnd.getDate() - 1);
maxEditDiscountValueEnd.setFullYear(maxYear);
editDateEnd.max = "" + maxEditDiscountValueEnd.getFullYear() + "-" + (maxEditDiscountValueEnd.getMonth() < 10 ? ("0" + maxEditDiscountValueEnd.getMonth()) : maxEditDiscountValueEnd.getMonth()) + "-" + maxEditDiscountValueEnd.getDate() + "";

editDateBegin.addEventListener("change", function() {
	let thisDate = new Date(this.value);
	if (thisDate.getFullYear() > maxYear) {
		let date = new Date();
		date.setFullYear(maxYear);
		date.setMonth(12);
		date.setDate(30);
		this.valueAsDate = date;
	}
	if (thisDate < currentDate) {
		this.valueAsDate = currentDate;
		thisDate = currentDate;
	}
	if (thisDate >= new Date(editDateEnd.value)) {
		let date = thisDate;
		date.setDate(date.getDate() + 1);
		editDateEnd.valueAsDate = date;
	}
});

editDateEnd.addEventListener("change", function() {
	let thisDate = new Date(this.value);

	if (thisDate.getFullYear() > maxYear) {
		let date = new Date();
		date.setFullYear(maxYear);
		date.setMonth(12);
		date.setDate(31);
		this.valueAsDate = date;
	}
	if (thisDate <= new Date(editDateBegin.value)) {
		let date = thisDate;
		date.setDate(date.getDate() - 1);
		editDateBegin.valueAsDate = date;
	}
	if (thisDate < tomorrowDate) {
		editDateBegin.valueAsDate = currentDate
		this.valueAsDate = tomorrowDate;
	}
});

// Add Discount
dateBegin.valueAsDate = currentDate;
dateBegin.min = dateBegin.value;

let maxDiscountValueBegin = new Date(dateBegin.value);
maxDiscountValueBegin.setDate(maxDiscountValueBegin.getDate() - 1);
maxDiscountValueBegin.setFullYear(maxYear);
dateBegin.max = "" + maxDiscountValueBegin.getFullYear() + "-" + (maxDiscountValueBegin.getMonth() < 10 ? ("0" + maxDiscountValueBegin.getMonth()) : maxDiscountValueBegin.getMonth()) + "-" + maxDiscountValueBegin.getDate() + "";

dateEnd.valueAsDate = tomorrowDate;
dateEnd.min = dateEnd.value;
let maxDiscountValueEnd = new Date(dateEnd.value);
maxDiscountValueEnd.setDate(maxDiscountValueEnd.getDate() - 1);
maxDiscountValueEnd.setFullYear(maxYear);
dateEnd.max = "" + maxDiscountValueEnd.getFullYear() + "-" + (maxDiscountValueEnd.getMonth() < 10 ? ("0" + maxDiscountValueEnd.getMonth()) : maxDiscountValueEnd.getMonth()) + "-" + maxDiscountValueEnd.getDate() + "";

dateBegin.addEventListener("change", function() {
	let thisDate = new Date(this.value);
	if (thisDate.getFullYear() > maxYear) {
		let date = new Date();
		date.setFullYear(maxYear);
		date.setMonth(12);
		date.setDate(30);
		this.valueAsDate = date;
	}
	if (thisDate < currentDate) {
		this.valueAsDate = currentDate;
	}
	if (thisDate >= new Date(dateEnd.value)) {
		let date = thisDate;
		date.setDate(date.getDate() + 1);
		dateEnd.valueAsDate = date;
	}
});

dateEnd.addEventListener("change", function() {
	let thisDate = new Date(this.value);

	if (thisDate.getFullYear() > maxYear) {
		let date = new Date();
		date.setFullYear(maxYear);
		date.setMonth(12);
		date.setDate(31);
		this.valueAsDate = date;
	}
	if (thisDate <= new Date(dateBegin.value)) {
		let date = thisDate;
		date.setDate(date.getDate() - 1);
		dateBegin.valueAsDate = date;
	}
	if (thisDate < tomorrowDate) {
		dateBegin.valueAsDate = currentDate
		this.valueAsDate = tomorrowDate;
	}
});