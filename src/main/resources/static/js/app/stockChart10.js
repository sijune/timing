$(document).ready(function(data) {
    var analysisChart10_1 = document.getElementById('analysisChart10-1');
    var analysisChart10_2 = document.getElementById('analysisChart10-2');
    var analysisChart10_3 = document.getElementById('analysisChart10-3');

    var stock_date = []
    var close = []
    var ema130 = []
    var macd = []
    var macd_signal = []
    var macd_hist = []
    var fast_k = []
    var slow_d = []

    var table = document.getElementById('analysis10-table');
    for (var r = 0, n = table.rows.length; r < n; r++) {
        stock_date.push(table.rows[r].cells[2].innerHTML)
        close.push(table.rows[r].cells[3].innerHTML)
        ema130.push(table.rows[r].cells[4].innerHTML)
        macd.push(table.rows[r].cells[6].innerHTML)
        macd_signal.push(table.rows[r].cells[7].innerHTML)
        macd_hist.push(table.rows[r].cells[8].innerHTML)
        fast_k.push(table.rows[r].cells[9].innerHTML)
        slow_d.push(table.rows[r].cells[10].innerHTML)
    }

    var mixedChart1 = new Chart(analysisChart10_1, {
        data: {
            datasets: [{
                type: 'line',
                label: 'close',
                data: close,
                borderColor: '#F08282',
                pointBorderWidth: -1,
            }, {
                type: 'line',
                label: 'ema130',
                data: ema130,
                borderColor: '#9B7E7E',
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
                        text: 'EMA130'
                    }
                }
            },
        },
    });

    var mixedChart2 = new Chart(analysisChart10_2, {
        data: {
            datasets: [{
                type: 'line',
                label: 'MACD',
                data: macd,
                fill: true,
                borderColor: '#DA5252',
                pointBorderWidth: -1,
            }, {
                type: 'line',
                label: 'MACD Signal',
                data: macd_signal,
                fill: true,
                borderColor: '#8777EF',
                pointBorderWidth: -1,
            }, {
                type: 'bar',
                label: 'MACD Hist',
                borderColor: '#878052',
                backgroundColor: '#878052',
                data: macd_hist
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
                        text: 'MACD'
                    }
                }
            },
        },
    });

    var mixedChart3 = new Chart(analysisChart10_3, {
        data: {
            datasets: [{
                type: 'line',
                label: 'Fast K',
                data: fast_k,
                borderColor: '#A1B3A4',
                pointBorderWidth: -1,
            }, {
                type: 'line',
                label: 'Slow D',
                data: slow_d,
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
                        text: 'Fast & Slow'
                    }
                }
            },
        },
    });
})