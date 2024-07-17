

function configMonthChart(datas) {
  var barOptions = {
    series: [
      {
        name: "Revenue",
        data: datas,
      },
    ],
    chart: {
      type: "bar",
      height: 350,
    },
    plotOptions: {
      bar: {
        horizontal: false,
        columnWidth: "50%",
        endingShape: "rounded",
        distributed: true,
      },
    },

    dataLabels: {
      enabled: false,
    },
    stroke: {
      show: true,
      width: 2,
      colors: ["transparent"],
    },
    legend: {
      show: false
    },
    xaxis: {
      categories: ["1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16",
                  "17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"],
	  style:{
       	colors: returnRandom()
   	  }
    },
    yaxis: {
      title: {
        text: "$ (US Dollar )",
      },
      max: function(datas){
        let max = 800;
        if(Math.max(datas) > max) {
          return Math.max(datas) + 100
        }else{
          return max;
        }
      }
    },
    fill: {
      opacity: 1,
    },
    tooltip: {
      y: {
        formatter: function(val) {
          return "$" + val + " (US Dollar)";
        },
      },
    },
  };
  return barOptions;
}

// ========= Another Function ==========
function returnRandom(){
  let arr = ['#D9EDBF','#FFB996','#FFCF81','#FDFFAB','#80BCBD','#AAD9BB','#D5F0C1','#F9F7C9','#7BD3EA','#A1EEBD',
              '#F6F7C4','#F6D6D6','#7ED7C1','#F0DBAF','#DC8686','#B06161','#BEADFA','#D0BFFF','#DFCCFB','#FFF8C9',
              '#C8E4B2','#9ED2BE','#7EAA92','#FFD9B7','#A1CCD1','#F4F2DE','#E9B384','#7C9D96','#AAC8A7','#C3EDC0','#E9FFC2'];
  var result = new Array();
  var arrLength = arr.length
  var randomNumber;
  for(var i = 0; i < arrLength; i++) {
      randomNumber = Math.floor(Math.random()*arr.length);
      result.push(arr[randomNumber]);
      arr.splice(randomNumber,1);
  }
  return result;
}
