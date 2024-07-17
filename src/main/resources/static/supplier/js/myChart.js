
// ===== Month Chart =====
function chartMonth(data) {
  var xValues = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];
  var yValues = data;
  var barColors = [
    "#b91d47",
    "#00aba9",
    "#2b5797",
    "#e8c3b9",
    "#1e7145",
    "#7e7235",
    "#3e5145",
    "#01aba9",
    "#0eeba9",
    "#412ba9",
    "#60aba9",
    "#80aba9",
  ];

  new Chart("monthBarChart", {
    type: "bar",
    data: {
      labels: xValues,
      datasets: [{
        backgroundColor: returnRandom(barColors),
        data: yValues
      }]
    },
    options: {
      legend: { display: false },
      title: {
        display: true,
        text: "Revenue Of The Week"
      },
      scales: {
        yAxes: [{
          ticks: {
            beginAtZero: true,
            // suggestedMax: 40 + 20,
            padding: 10,
          },
          gridLines: {
            drawBorder: false,
          }
        }],
        xAxes: [{
          gridLines: {
            display: false,
            drawBorder: false
          }
        }]
      }
    }
  });
}
// ===== End Month Chart =====

