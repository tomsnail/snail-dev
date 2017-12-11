/**
 * Created by Administrator on 2017/3/8.
 */
$(function () {
    $("#beginDate").datetimepicker({
        format: 'yyyy-mm-dd',
        minView:'month',
        language: 'zh-CN',
        autoclose:true,
        endDate:new Date()
    })

    $("#endDate").datetimepicker({
        format: 'yyyy-mm-dd',
        minView:'month',
        language: 'zh-CN',
        autoclose:true,
        endDate:new Date()
    })
    $("#beginDate").change(function () {
        var begindata=$("#beginDate").val();
        var enddata=$("#endDate").val();
        if(begindata && enddata){
            if(begindata>enddata){
                alert("不能大于结束日期"+enddata);
                $("#beginDate").val("");
            }
        }
    })
    $("#endDate").change(function () {
        var begindata=$("#beginDate").val();
        var enddata=$("#endDate").val();
        if(begindata && enddata){
            if(begindata>enddata){
                alert("不能小于起始日期"+begindata);
                $("#endDate").val("");
            }
        }
    })
    $("#selectTime").change(function () {
        if($(this).val()=="自定义"){
            $(".selectDate").css({
                "display":"inline-block"
            })
        }else{
            $(".selectDate").css({
                "display":"none"
            })
        }
        var mydata= getDatas();
        tendencyChart(mydata);
    })
    tendencyChart();
    pieChart();
})
function tendencyChart(){
    var tendencyChart=echarts.init(document.getElementById("pieTendency"));
    var option = {
        title: {
            text: '交易员物流委托成交量统计'
        },
        tooltip : {
            trigger: 'axis'
        },
        legend: {
            data:['赵伟冲','陈卫星','张猛','毕红岩','任亚飞','杨松']
        },
        toolbox: {
            feature: {

            }
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis : [
            {
                type : 'category',
                boundaryGap : false,
                data : ['周一','周二','周三','周四','周五','周六','周日']
            }
        ],
        yAxis : [
            {
                type : 'value'
            }
        ],
        series : [
            {
                name:'赵伟冲',
                type:'line',
                stack: '总量',
                areaStyle: {normal: {}},
                data:[120, 132, 101, 134, 90, 230, 210]
            },
            {
                name:'陈卫星',
                type:'line',
                stack: '总量',
                areaStyle: {normal: {}},
                data:[220, 182, 191, 234, 290, 330, 310]
            },
            {
                name:'张猛',
                type:'line',
                stack: '总量',
                areaStyle: {normal: {}},
                data:[150, 232, 201, 154, 190, 330, 410]
            },
            {
                name:'毕红岩',
                type:'line',
                stack: '总量',
                areaStyle: {normal: {}},
                data:[320, 332, 301, 334, 390, 330, 320]
            },
            {
                name:'任亚飞',
                type:'line',
                stack: '总量',
                areaStyle: {normal: {}},
                data:[320, 332, 301, 334, 390, 330, 320]
            },
            {
                name:'杨松',
                type:'line',
                stack: '总量',/*
             label: {
             normal: {
             show: true,
             position: 'bottom'
             }
             },*/
                areaStyle: {normal: {}},
                data:[820, 932, 901, 934, 1290, 1330, 1320]
            }
        ]
    };

    tendencyChart.setOption(option);
}
function pieChart(){
    var myChart=echarts.init(document.getElementById('pieChart'));
    var data = [{
        value: 150,
        name: '已成交数'
    }, {
        value: 50,
        name: '委托取消数'
    }, {
        value: 200,
        name: '匹配失败数'
    },{
        value: 100,
        name: '匹配中'
    }];
    var option = {
        backgroundColor: '#fff',
        title: {
            text: '物流委托总数',
            subtext: '500',
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
            data: ['已成交数', '委托取消数', '匹配失败数', '匹配中']
        },
        series: [{
            type: 'pie',
            selectedMode: 'single',
            radius: ['25%', '58%'],
            color: ['#00CC33','#8c8c8c',  '#FF0033', '#FF9900'],

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
                    formatter: '{c}个',
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
    myChart.setOption(option)
}