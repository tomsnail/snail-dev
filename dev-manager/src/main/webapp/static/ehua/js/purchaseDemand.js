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
    pieChart();
    tendencyChart();
})

function tendencyChart(){
    var tendencyChart=echarts.init(document.getElementById("pieTendency"));
    var option = {
        title: {
            text: '交易员采购成交量统计'
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
    var giftImageUrl='';
    var myChart=echarts.init(document.getElementById('pieChart'));
    var option = {
        graphic: {
            elements: [{
                type: 'image',
                style: {
                    image: giftImageUrl,
                    width: 30,
                    height: 30
                },
                left: 'center',
                top: 'center'
            }]
        },
        title: {
            text: '采购需求',
            subtext: '',
            x: '45%', //center
            y: '35%', //center
            textStyle: {
                fontWeight: 'normal',
                fontSize: 16
            }
        },
        backgroundColor: '#dde',/*
        backgroundColor: '#fff',*/
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b}: {c} ({d}%)"
        },
        legend: {
            orient: 'vertical', //horizontal
            //bottom: '0%',
            x: 'left',
            data: ['已成交', '已作废', '已取消','发布中']
        },
        series: [
            //内圈
            {
                name: '交易数量',
                type: 'pie',
                roseType: 'area', //area比例缩放 ，radius
                radius: ['30%', '60%'],
                center: ['50%', '40%'], //位置
                color: ['#00CC33', '#CC6600','#bbbbbb','#9966CC'],
                avoidLabelOverlap: false,
                label: {
                    normal: {
                        show: true,
                        position: 'inner', //center
                        formatter: '{d}%', //{d}
                        textStyle: {
                            color: '#fff',
                            fontWeight: 'bold',
                            fontSize: 14
                        }
                    },
                    emphasis: {
                        show: true,
                        textStyle: {
                            fontSize: '30',
                            fontWeight: 'bold'
                        }
                    }
                },
                labelLine: {
                    normal: {
                        show: false
                    }
                },

                data: [{
                    value: 100,
                    name: '已成交'
                }, {
                    value: 300,
                    name: '已作废'
                }, {
                    value: 250,
                    name: '已取消'
                }, {
                    value: 350,
                    name: '发布中'
                }]
            }, //外圈
            {
                name: '交易量',
                type: 'pie',
                radius: ['70%', '85%'],
                avoidLabelOverlap: false,
                color: ['#bbbbbb'],
                label: {
                    normal: {
                        show: true,
                        position: 'inner', //center
                        formatter: function(param) {
                            return param.name + ':\n' + Math.round(param.percent) + '%';
                        },
                        textStyle: {
                            color: '#fff',
                            fontWeight: 'bold',
                            fontSize: 14
                        }
                    },

                    emphasis: {
                        show: false,
                    }
                },
                labelLine: {
                    normal: {
                        smooth: true,
                        lineStyle: {
                            width: 2
                        }
                    }
                },
                itemStyle: {
                    normal: {
                        shadowBlur: 30,
                        shadowColor: 'rgba(0, 0, 0, 0.4)'
                    }
                },

                data: [{
                    value: 184,
                    name: '已成交'
                }, {
                    value: 294,
                    name: '已作废'
                }, {
                    value: 234,
                    name: '已取消'
                }, {
                    value: 580,
                    name: '发布中'
                }]
            }, {
                name: '交易量',
                type: 'pie',
                radius: ['90%', '95%'],
                /*color: ['#ffff00'],*/
                color: ['#83D560', '#AF89D6', '#a9ADF3','#d58512'],
                avoidLabelOverlap: false,
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
                        formatter: '{c}',
                        textStyle: {
                            color: '#777777',
                            fontWeight: 'bold',
                            fontSize: 10
                        }
                    }
                },

                data: [{
                    value: 184,
                    name: '已成交'
                }, {
                    value: 294,
                    name: '已作废'
                }, {
                    value: 234,
                    name: '已取消'
                }, {
                    value: 580,
                    name: '发布中'
                }]
            }
        ]
    };
    myChart.setOption(option)
}