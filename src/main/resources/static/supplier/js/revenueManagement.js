

// ==== Set Default Date Revenue
let defaultMonth = document.querySelector(".selectMonth").children;
let defaultYear = document.querySelector(".selectYear").children;
//  define chart bar
var bar;
// Return today's date and time
var currentTime = new Date();
// returns the month (from 0 to 11)
var month = currentTime.getMonth(); // vd: 0-Thang 1; 1-Thang 2...
// returns the year (four digits)
var year = currentTime.getFullYear();

defaultMonth[month].selected = true;
for (var i = 0; i < defaultYear.length; i++) {
	if (defaultYear[i].value == year) {
		defaultYear[i].selected = true
	}
}

$(window).on("load", function() {
	let currentYear = new Date().getFullYear();
	let lastYear = new Date().getFullYear() - 1;
	let param = parseRevenue(RevenueDefault);

	bar = new ApexCharts(document.querySelector("#bar"), configMonthChart(param));
	bar.render();
	
	let totalYears = document.querySelector(".YearsRevenue .totalYears");
	let total = 0;
	TotalRevenueYear.forEach(function(data){
		if (Number(data.year) == currentYear) {
			total = data.price;
		}
	})
	if(totalYears != null) totalYears.innerHTML = "$" + total;
	
	// =========================
	getTotalOfYear(TotalRevenueYear);
	function getTotalOfYear(params){
		var totalYear = 0;
		var totalLastyear = 0;
		let flag = false;
		params.forEach(function(param){
			if(Number(param.year) == currentYear){
				flag = true;
				totalYear = param.price;
			}
			if(Number(param.year) == lastYear){
				flag = true;
				totalLastyear = param.price;
			}
		});
		
		let difference = document.querySelector(".YearsRevenue .difference");
		let temp = (totalYear - totalLastyear) * 100;
		if(difference != null){
			if(temp > 0){
				difference.classList.remove("text-red");
				difference.classList.add("text-green");
				difference.innerHTML = `<i data-feather="bar-chart" width="15"></i> +` + temp + "%";
			}else{
				difference.classList.remove("text-green");
				difference.classList.add("text-red");
				difference.innerHTML = `<i data-feather="bar-chart" width="15"></i> -` + temp + "%";
			}
		}
	}
	
});

function selectRevenue() {
	let selectMonth = document.querySelector(".selectMonth").value;
	let selectYear = document.querySelector(".selectYear").value;

	$.ajax({
		type: "GET",
		contentType: "application/json",
		url: "/Supplier/SelectRevenue",
		data: {
			idSupplier: Number(idSupplier),
			year: selectYear,
			month: selectMonth
		},
		dataType: 'json',
		success: function(data) {
			let param = parseRevenue(data);
			bar.opts.series[0].data = param
			bar.update();
		},
		error: function(e) {
			console.log("ERROR: ", e);
		}
	});
}

function parseRevenue(revenue) {
	let param = [];
	revenue.forEach(item => {
		item.createDate = stringToDate(item.createDate, "yyyy-MM-dd", "-");
	});

	for (let i = 0; i < 31; i++) {
		let flag = revenue.find(function(item) {
			if (item.createDate.getDate() == i + 1) {
				param.splice(i, 0, item.price);
				return true;
			}
		});
		if (flag == undefined) {
			param.splice(i, 0, 0);
		}
	}
	return param;
}

function stringToDate(_date, _format, _delimiter) {
	var formatLowerCase = _format.toLowerCase();
	var formatItems = formatLowerCase.split(_delimiter);
	var dateItems = _date.split(_delimiter);
	var monthIndex = formatItems.indexOf("mm");
	var dayIndex = formatItems.indexOf("dd");
	var yearIndex = formatItems.indexOf("yyyy");
	var month = parseInt(dateItems[monthIndex]);
	month -= 1;
	var formatedDate = new Date(dateItems[yearIndex], month, dateItems[dayIndex]);
	return formatedDate;
}