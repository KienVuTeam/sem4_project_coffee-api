// ====================
// VOUCHER

let V_editDateBegin = document.querySelector('.editStartDate');
let V_editDateEnd = document.querySelector('.editEndDate');
let V_dateBegin = document.querySelector('.startDate');
let V_dateEnd = document.querySelector('.endDate');


// Edit Voucher
V_editDateBegin.valueAsDate = currentDate;
V_editDateBegin.min = V_editDateBegin.value;

let maxEditValueBegin = new Date(V_editDateBegin.value);
maxEditValueBegin.setDate(maxEditValueBegin.getDate() - 1);
maxEditValueBegin.setMonth(maxEditValueBegin.getMonth() + 1);
maxEditValueBegin.setFullYear(maxYear);
V_editDateBegin.max = "" + maxEditValueBegin.getFullYear() + "-" + (maxEditValueBegin.getMonth() < 10 ? ("0" + maxEditValueBegin.getMonth()) : maxEditValueBegin.getMonth()) + "-" + maxEditValueBegin.getDate() + "";

V_editDateEnd.valueAsDate = tomorrowDate;
V_editDateEnd.min = V_editDateEnd.value;
let maxEditValueEnd = new Date(V_editDateEnd.value);
maxEditValueEnd.setDate(maxEditValueEnd.getDate() - 1);
maxEditValueEnd.setFullYear(maxYear);
V_editDateEnd.max = "" + maxEditValueEnd.getFullYear() + "-" + (maxEditValueEnd.getMonth() < 10 ? ("0" + maxEditValueEnd.getMonth()) : maxEditValueEnd.getMonth()) + "-" + maxEditValueEnd.getDate() + "";

V_editDateBegin.addEventListener("change", function () {
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
    if (thisDate >= new Date(V_editDateEnd.value)) {
        let date = thisDate;
        date.setDate(date.getDate() + 1);
        V_editDateEnd.valueAsDate = date;
    }
});

V_editDateEnd.addEventListener("change", function () {
    let thisDate = new Date(this.value);

    if (thisDate.getFullYear() > maxYear) {
        let date = new Date();
        date.setFullYear(maxYear);
        date.setMonth(12);
        date.setDate(31);
        this.valueAsDate = date;
    }
    if (thisDate <= new Date(V_editDateBegin.value)) {
        let date = thisDate;
        date.setDate(date.getDate() - 1);
        V_editDateBegin.valueAsDate = date;
    }
    if (thisDate < tomorrowDate) {
        V_editDateBegin.valueAsDate = currentDate
        this.valueAsDate = tomorrowDate;
    }
});

// Add Voucher
V_dateBegin.valueAsDate = currentDate;
V_dateBegin.min = V_dateBegin.value;

let maxValueBegin = new Date(V_dateBegin.value);
maxValueBegin.setDate(maxValueBegin.getDate() - 1);
maxValueBegin.setFullYear(maxYear);
V_dateBegin.max = "" + maxValueBegin.getFullYear() + "-" + (maxValueBegin.getMonth() < 10 ? ("0" + maxValueBegin.getMonth()) : maxValueBegin.getMonth()) + "-" + maxValueBegin.getDate() + "";

V_dateEnd.valueAsDate = tomorrowDate;
V_dateEnd.min = V_dateEnd.value;
let maxValueEnd = new Date(V_dateEnd.value);
maxValueEnd.setDate(maxValueEnd.getDate() - 1);
maxValueEnd.setFullYear(maxYear);
V_dateEnd.max = "" + maxValueEnd.getFullYear() + "-" + (maxValueEnd.getMonth() < 10 ? ("0" + maxValueEnd.getMonth()) : maxValueEnd.getMonth()) + "-" + maxValueEnd.getDate() + "";

V_dateBegin.addEventListener("change", function () {
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
    if (thisDate >= new Date(V_dateEnd.value)) {
        let date = thisDate;
        date.setDate(date.getDate() + 1);
        V_dateEnd.valueAsDate = date;
    }
});

V_dateEnd.addEventListener("change", function () {
    let thisDate = new Date(this.value);

    if (thisDate.getFullYear() > maxYear) {
        let date = new Date();
        date.setFullYear(maxYear);
        date.setMonth(12);
        date.setDate(31);
        this.valueAsDate = date;
    }
    if (thisDate <= new Date(V_dateBegin.value)) {
        let date = thisDate;
        date.setDate(date.getDate() - 1);
        V_dateBegin.valueAsDate = date;
    }
    if (thisDate < tomorrowDate) {
        V_dateBegin.valueAsDate = currentDate
        this.valueAsDate = tomorrowDate;
    }
});