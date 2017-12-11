/**
 * Created by Administrator on 2017/3/6.
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
    ss();
    tendencyChart();
    /*piePersonChart();*/

})

function tendencyChart(){
    var tendencyChart=echarts.init(document.getElementById("pieTendency"));
    var option = {
        title: {
            text: '交易员成交量统计'
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
function piePersonChart(){
    var myChart=echarts.init(document.getElementById('piePersonChart'));
    var option = {
        title : {
            text: '委托洽谈统计',
            subtext: '',
            x:'center'
        },
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',//horizontal
            left: 'left',
            data: ['赵伟冲','陈卫星','张猛','毕红岩','任亚飞','杨松']
        },
        series : [
            {
                name: '访问来源',
                type: 'pie',
                radius : '55%',
                center: ['50%', '60%'],
                data:[
                    {value:310, name:'赵伟冲'},
                    {value:234, name:'陈卫星'},
                    {value:432, name:'张猛'},
                    {value:342, name:'毕红岩'},
                    {value:555, name:'任亚飞'},
                    {value:231, name:'杨松'}
                ],
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    };
    myChart.setOption(option);
}

function ss(){
   /* var giftImageUrl = "data:image/png;base64,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";
    */var giftImageUrl='';
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
            text: '委托洽谈',
            subtext: '',
            x: '45%', //center
            y: '35%', //center
            textStyle: {
                fontWeight: 'normal',
                fontSize: 16
            }
        },
        /*backgroundColor: '#dde',*/
        backgroundColor: '#fff',
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b}: {c} ({d}%)"
        },
        legend: {
            orient: 'vertical', //horizontal
            //bottom: '0%',
            x: 'left',
            data: ['洽谈中', '已成交', '未成交','已取消']
        },
        series: [
            //内圈
            {
                name: '交易笔数',
                type: 'pie',
                roseType: 'area', //area比例缩放 ，radius
                radius: ['30%', '60%'],
                center: ['50%', '40%'], //位置
                color: ['#9966CC', '#00CC33', '#CC6600','#bbbbbb'],
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
                    name: '洽谈中'
                }, {
                    value: 300,
                    name: '已成交'
                }, {
                    value: 250,
                    name: '未成交'
                }, {
                    value: 350,
                    name: '已取消'
                }]
            }, //外圈
            {
                name: '交易数量比例',
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
                    name: '洽谈中'
                }, {
                    value: 294,
                    name: '已成交'
                }, {
                    value: 234,
                    name: '未成交'
                }, {
                    value: 580,
                    name: '已取消'
                }]
            }, {
                name: '交易数量值',
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
                    name: '洽谈中'
                }, {
                    value: 294,
                    name: '已成交'
                }, {
                    value: 234,
                    name: '未成交'
                }, {
                    value: 580,
                    name: '已取消'
                }]
            }
        ]
    };
    myChart.setOption(option)
}

function s1(){
    var myChart=echarts.init(document.getElementById('sone'));
    var data = [{
        value: 10,
        name: '洽谈中'
    }, {
        value: 30,
        name: '已成交'
    }, {
        value: 25,
        name: '未成交'
    }, {
        value: 35,
        name: '已取消'
    }];

    var legendData = (function(){
        var ret = [];
        for(var i=0;i<data.length;i++){
            ret.push(data[i].name);
        }
        return ret;
    })();

    var pose = ['50%','40%'];

    option = {
        backgroundColor: '#fff',
        title: {
            text: '委托洽谈交易笔数',
            //subtext: '2016年1月',
            //x: 'center',
            //y: '37%',
            textStyle: {
                fontWeight: 'normal',
                fontSize: 14
            }
        },
        tooltip: {
            show: true,
            trigger: 'item',
            formatter: "{b}: {c} ({d}%)"
        },
        toolbox: {
            feature: {
                dataView: {show: false, readOnly: false},
                restore: {show: false},
                saveAsImage: {show: false}
            }
        },
        legend: {
            orient: 'horizontal',
            bottom: '0%',
            data: legendData
        },
        series: [{
            type: 'pie',
            selectedMode: 'single',
            //radius: ['25%', '58%'],
            radius:'58%',
            //color: ['#86D560', '#AF89D6', '#59ADF3', '#FF999A', '#FFCC67'],
            center:pose,
            label: {
                normal: {
                    position: 'inner',
                    formatter: '{d}%',

                    textStyle: {
                        color: '#fff',
                        fontWeight: 'bold',
                        fontSize: 10
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
            center:pose,
            radius: ['58%', '78%'],
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
            data: data
        }]
    };
   myChart.setOption(option);
}
function s2(){
    var myChart=echarts.init(document.getElementById('stwo'));
    var data= [{
        value: 184,
        name: '洽谈中'
    }, {
        value: 294,
        name: '已成交'
    }, {
        value: 234,
        name: '未成交'
    }, {
        value: 580,
        name: '已取消'
    }]

    var legendData = (function(){
        var ret = [];
        for(var i=0;i<data.length;i++){
            ret.push(data[i].name);
        }
        return ret;
    })();

    var pose = ['50%','40%'];

    option = {
        backgroundColor: '#fff',
        title: {
            text: '委托洽谈交易量（吨）',
            //subtext: '2016年1月',
            //x: 'center',
            //y: '37%',
            textStyle: {
                fontWeight: 'normal',
                fontSize: 14
            }
        },
        tooltip: {
            show: true,
            trigger: 'item',
            formatter: "{b}: {c} ({d}%)"
        },
        toolbox: {
            feature: {
                dataView: {show: false, readOnly: false},
                restore: {show: false},
                saveAsImage: {show: false}
            }
        },
        legend: {
            orient: 'horizontal',
            bottom: '0%',
            data: legendData
        },
        series: [{
            type: 'pie',
            selectedMode: 'single',
            //radius: ['25%', '58%'],
            radius:'58%',
            //color: ['#86D560', '#AF89D6', '#59ADF3', '#FF999A', '#FFCC67'],
            center:pose,
            label: {
                normal: {
                    position: 'inner',
                    formatter: '{d}%',

                    textStyle: {
                        color: '#fff',
                        fontWeight: 'bold',
                        fontSize: 10
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
            center:pose,
            radius: ['58%', '78%'],
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
            data: data
        }]
    };
    myChart.setOption(option);
}
