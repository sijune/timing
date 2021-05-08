$(document).ready(function(data) {
    var analysisChart20_1 = document.getElementById('analysisChart20-1');
    var analysisChart20_2 = document.getElementById('analysisChart20-2');
    var analysisChart20_3 = document.getElementById('analysisChart20-3');

    var stock_date = []
    var close = []
    var ma20 = []
    var upper = []
    var lower = []
    var pb = []
    var mfi10 = []

    var table = document.getElementById('analysis20-table');
    for (var r = 0, n = table.rows.length; r < n; r++) {
        stock_date.push(table.rows[r].cells[2].innerHTML)
        close.push(table.rows[r].cells[3].innerHTML)
        ma20.push(table.rows[r].cells[4].innerHTML)
        upper.push(table.rows[r].cells[5].innerHTML)
        lower.push(table.rows[r].cells[6].innerHTML)
        pb.push(table.rows[r].cells[7].innerHTML)
        mfi10.push(table.rows[r].cells[8].innerHTML * 0.01)
    }

    var mixedChart1 = new Chart(analysisChart20_1, {
        data: {
            datasets: [{
                type: 'line',
                label: 'close',
                data: close,
                borderColor: '#F08282',
                pointBorderWidth: -1,
            }, ],
            labels: stock_date,
            options: {
                responsive: true,
                plugins: {
                    legend: {
                        position: 'top',
                    },
                    title: {
                        display: true,
                        text: 'EMA130'
                    }
                }
            },
        },
    });

    var mixedChart2 = new Chart(analysisChart20_2, {
        data: {
            datasets: [{
                type: 'line',
                label: 'upper',
                data: upper,
                fill: true,
                borderColor: 'red',
                pointBorderWidth: -1,
            }, {
                type: 'line',
                label: 'lower',
                data: lower,
                fill: true,
                borderColor: 'blue',
                pointBorderWidth: -1,
            },{
                type: 'line',
                label: 'ma20',
                data: ma20,
                fill: true,
                borderColor: 'grey',
                pointBorderWidth: -1,
            },{
                type: 'line',
                label: 'close',
                data: close,
                fill: true,
                borderColor: '#A1B3A4',
                pointBorderWidth: -1,
            }],
            labels: stock_date,
            options: {
                responsive: true,
                plugins: {
                    legend: {
                        position: 'top',
                    },
                    title: {
                        display: true,
                        text: 'Bollinger'
                    }
                }
            },
        },
    });

    var mixedChart3 = new Chart(analysisChart20_3, {
        data: {
            datasets: [{
                type: 'line',
                label: 'B%',
                data: pb,
                borderColor: '#A1B3A4',
                pointBorderWidth: -1,
            }, {
                type: 'line',
                label: 'MFI10',
                data: mfi10,
                borderColor: '#1E652A',
                pointBorderWidth: -1,
            }],
            labels: stock_date,
            options: {
                responsive: true,
                plugins: {
                    legend: {
                        position: 'top',
                    },
                    title: {
                        display: true,
                        text: 'B% & MFI10'
                    }
                }
            },
        },
    });
})