/**
 * Created by Administrator on 2017/3/10.
 */
$(function () {
    $("#beginDate").datetimepicker({
        format: 'yyyy-mm-dd',
        minView: 'month',
        language: 'zh-CN',
        autoclose: true,
        endDate: new Date()
    })

    $("#endDate").datetimepicker({
        format: 'yyyy-mm-dd',
        minView: 'month',
        language: 'zh-CN',
        autoclose: true,
        endDate: new Date()
    })
    $("#beginDate").change(function () {
        var begindata = $("#beginDate").val();
        var enddata = $("#endDate").val();
        if (begindata && enddata) {
            if (begindata > enddata) {
                alert("不能大于结束日期" + enddata);
                $("#beginDate").val("");
            }
        }
    })
    $("#endDate").change(function () {
        var begindata = $("#beginDate").val();
        var enddata = $("#endDate").val();
        if (begindata && enddata) {
            if (begindata > enddata) {
                alert("不能小于起始日期" + begindata);
                $("#endDate").val("");
            }
        }
    })
    $("#selectTime").change(function () {
        if ($(this).val() == "自定义") {
            $(".selectDate").css({
                "display": "inline-block"
            })
        } else {
            $(".selectDate").css({
                "display": "none"
            })
        }
        var mydata = getDatas();
        tendencyChart(mydata);
    })
    newCharts();
    pieChart();
})
function pieChart(){
    var myChart=echarts.init(document.getElementById('s2'));
    var data = [{
        value: 100,
        name: '赵县'
    }, {
        value: 150,
        name: '正定县'
    }, {
        value: 50,
        name: '元氏县'
    }, {
        value: 200,
        name: '高邑县'
    }];
    var option = {
        backgroundColor: '#fff',
        title: {
            text: '石家庄',
            subtext: '500条',
            x: 'center',
            y: 'center',
            textStyle: {
                fontWeight: 'normal',
                fontSize: 16
            },
            subtextStyle:{
                fontWeight: 'normal',
                fontSize: 16,
                color:'#000',
            }
        },
        tooltip: {
            show: true,
            trigger: 'item',
            formatter: "{b}: {c} ({d}%)"
        },
        legend: {
            orient: 'horizontal',
            bottom: '0%',
            data: ['赵县', '正定县', '元氏县', '高邑县']
        },
        series: [{
            type: 'pie',
            selectedMode: 'single',
            radius: ['25%', '58%'],
            color: [getRandomColor, getRandomColor, getRandomColor, getRandomColor],

            label: {
                normal: {
                    position: 'inner',
                    formatter: '{d}%',

                    textStyle: {
                        color: '#fff',
                        fontWeight: 'bold',
                        fontSize: 14
                    }
                }
            },
            labelLine: {
                normal: {
                    show: false
                }
            },
            data: data
        }, {
            type: 'pie',
            radius: ['58%', '83%'],
            itemStyle: {
                normal: {
                    color: '#F2F2F2'
                },
                emphasis: {
                    color: '#ADADAD'
                }
            },
            label: {
                normal: {
                    position: 'inner',
                    formatter: '{c}条',
                    textStyle: {
                        color: '#777777',
                        fontWeight: 'bold',
                        fontSize: 14
                    }
                }
            },
            data: data
        }]
    };
    myChart.setOption(option);
}
function newCharts(){
    var colors = ['#0066FF', '#675bba'];
    var tendencyChart=echarts.init(document.getElementById("s1"));
    var option = {
        color: colors,
        title:{
            text:'河北省化学品录入统计分析',
            subtext:'总共;1000条',
            itemGap:5,
            left:'40',
            textStyle:{
                fontSize:18,
            }
        },
        tooltip: {
            trigger: 'axis'
        },
        grid: {
            right: '5%'
        },
        toolbox: {
            feature: {
                dataView: {show: false, readOnly: false},
                restore: {show: false},
                saveAsImage: {show: false}
            }
        },
        legend: {
            data:['录入条数'],
        },
        xAxis: [
            {
                type: 'category',
                axisTick: {
                    alignWithLabel: true
                },
                data: ['北京', '长沙', '天津', '太原', '石家庄', '广州', '重庆']
            }
        ],
        yAxis: [
            {
                type: 'value',
                name: '',
                nameLocation:'middle',
                min: 0,
                max: 1800,
                position: 'left',
                axisLine: {
                    lineStyle: {
                        //        color: colors[2]
                    }
                },
                nameTextStyle:{
                    fontSize:'',
                    fontWeight:''
                },
                axisLabel: {
                    formatter: '{value} 条'
                }
            }
        ],
        series: [
            {
                name:'录入条数',
                type:'bar',
                yAxisIndex: 0,barWidth:45,
                data:[160, 170, 240, 264, 300, 520, 610]
            }
        ]
    };
    tendencyChart.setOption(option);
    tendencyChart.on("click", function (params) {
        pieChart();
    })
}
function getRandomColor(){
    return "#"+("00000"+((Math.random()*16777215+0.5)>>0).toString(16)).slice(-6);
}
