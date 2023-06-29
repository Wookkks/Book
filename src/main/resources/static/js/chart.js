/**
 * 
 */
google.charts.load('current', {'packages':['corechart']});
google.charts.setOnLoadCallback(drawChart);
google.charts.setOnLoadCallback(drawAnthonyChart);
	  
  function drawChart() {
  var data = google.visualization.arrayToDataTable([
     ['Element', '횟수', { role: 'style' }],
     ['히가시노 게이고', 21, 'color: #8F0A38'],
     ['김난도', 12, 'color: #DB1A5D'],            
     ['기욤 뮈소', 10, 'color: #DBC904'],
     ['유시민', 10, 'color: #1AB4DB'],
  ]);
  
  var view = new google.visualization.DataView(data);
      view.setColumns([0, 1,
                       { calc: "stringify",
                         sourceColumn: 1,
                         type: "string",
                         role: "annotation" },
                         2
                       ]);                   

  var options = {
    width: 800,
    height: 600,
    bar: {groupWidth: "40%"},
    legend: { position: "none" },
    vAxis: {format: '0'}
  };

  var chart = new google.visualization.ColumnChart(document.getElementById('chart_div_one'));
  chart.draw(view, options);
}

  function drawAnthonyChart() {
  var data = google.visualization.arrayToDataTable([
     ['Element', '횟수', { role: 'style' }],
     ['소설', 364, 'color: #B0E0E6'],
     ['시/에세이', 311, 'color: #8eaf9d'],            
     ['경제/경영', 235, 'color: #a6d8d4'],
     ['자기계발', 228, 'color: #b9cdda'],
     ['인문', 212, 'color: #d7dae5'],
  ]);
  
  var view = new google.visualization.DataView(data);
      view.setColumns([0, 1,
                       { calc: "stringify",
                         sourceColumn: 1,
                         type: "string",
                         role: "annotation" },
                         2
                       ]);                   

  var options = {
    width: 800,
    height: 600,
    bar: {groupWidth: "40%"},
    legend: { position: "none" },
    vAxis: {format: '0'}
  };

  var chart = new google.visualization.ColumnChart(document.getElementById('chart_div_two'));
  chart.draw(view, options);
}