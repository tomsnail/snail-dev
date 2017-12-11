/**
 * Created by Administrator on 2017/3/6.
 */
$(function () {
   /* $(".datepicker" ).datepicker({});*/
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
    pieCharts();
    tendencyCharts();
})
function getDatas(){
    var selectedTime= $("#selectTime option:selected").val();
    if(selectedTime=="本月" || selectedTime=="上月"){
        return [{"dateType":"1","quotationTotal":"300","member":"236","invalidNo":"20","audit":"100"},
            {"dateType":"2","quotationTotal":"24","member":"236","invalidNo":"20","audit":"100"},
            {"dateType":"3","quotationTotal":"456","member":"236","invalidNo":"20","audit":"100"},
            {"dateType":"4","quotationTotal":"1234","member":"236","invalidNo":"20","audit":"100"},
            {"dateType":"5","quotationTotal":"300","member":"236","invalidNo":"20","audit":"100"},
            {"dateType":"6","quotationTotal":"446","member":"236","invalidNo":"20","audit":"100"},
            {"dateType":"7","quotationTotal":"67","member":"236","invalidNo":"20","audit":"100"},
            {"dateType":"8","quotationTotal":"345","member":"236","invalidNo":"20","audit":"100"},
            {"dateType":"9","quotationTotal":"879","member":"236","invalidNo":"20","audit":"100"},
            {"dateType":"10","quotationTotal":"232","member":"236","invalidNo":"20","audit":"100"},
            {"dateType":"11","quotationTotal":"554","member":"236","invalidNo":"20","audit":"100"},
            {"dateType":"12","quotationTotal":"89","member":"236","invalidNo":"20","audit":"100"},
            {"dateType":"13","quotationTotal":"554","member":"236","invalidNo":"20","audit":"100"},
            {"dateType":"14","quotationTotal":"89","member":"236","invalidNo":"20","audit":"100"},
            {"dateType":"15","quotationTotal":"554","member":"236","invalidNo":"20","audit":"100"},
            {"dateType":"16","quotationTotal":"89","member":"236","invalidNo":"20","audit":"100"},
            {"dateType":"17","quotationTotal":"554","member":"236","invalidNo":"20","audit":"100"},
            {"dateType":"18","quotationTotal":"89","member":"236","invalidNo":"20","audit":"100"},
            {"dateType":"19","quotationTotal":"554","member":"236","invalidNo":"20","audit":"100"},
            {"dateType":"20","quotationTotal":"89","member":"236","invalidNo":"20","audit":"100"},
            {"dateType":"21","quotationTotal":"554","member":"236","invalidNo":"20","audit":"100"},
            {"dateType":"22","quotationTotal":"89","member":"236","invalidNo":"20","audit":"100"},
            {"dateType":"23","quotationTotal":"554","member":"236","invalidNo":"20","audit":"100"},
            {"dateType":"24","quotationTotal":"89","member":"236","invalidNo":"20","audit":"100"},
            {"dateType":"25","quotationTotal":"554","member":"236","invalidNo":"20","audit":"100"},
            {"dateType":"26","quotationTotal":"89","member":"236","invalidNo":"20","audit":"100"},
            {"dateType":"27","quotationTotal":"554","member":"236","invalidNo":"20","audit":"100"},
            {"dateType":"28","quotationTotal":"89","member":"236","invalidNo":"20","audit":"100"},
            {"dateType":"29","quotationTotal":"554","member":"236","invalidNo":"20","audit":"100"},
            {"dateType":"30","quotationTotal":"89","member":"236","invalidNo":"20","audit":"100"},]
    }else if(selectedTime=="本周" || selectedTime=="上周"){
        return [{"dateType":"周一","quotationTotal":"323","member":"353","invalidNo":"23","audit":"22"},
            {"dateType":"周二","quotationTotal":"545","member":"236","invalidNo":"1","audit":"453"},
            {"dateType":"周三","quotationTotal":"456","member":"452","invalidNo":"3","audit":"243"},
            {"dateType":"周四","quotationTotal":"1234","member":"86","invalidNo":"34","audit":"462"},
            {"dateType":"周五","quotationTotal":"434","member":"234","invalidNo":"76","audit":"124"},
            {"dateType":"周六","quotationTotal":"234","member":"653","invalidNo":"6","audit":"475"},
            {"dateType":"周日","quotationTotal":"67","member":"124","invalidNo":"87","audit":"232"},]
    }
}
function pieChart(){
    var myChart=echarts.init(document.getElementById('pieChart'));
    var option = {
        title : {
            text: '报价单统计',
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
            data: ['报价单总数','报价会员数','无效数','待审核数']
        },
        series : [
            {
                name: '访问来源',
                type: 'pie',
                radius : '55%',
                center: ['50%', '60%'],
                data:[
                    {value:335, name:'报价单总数'},
                    {value:310, name:'报价会员数'},
                    {value:234, name:'无效数'},
                    {value:135, name:'待审核数'}
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
function tendencyChart(mydata){
    var dataList=new Array();
    var quotationTotal=new Array();
    var member=new Array();
    var invalidNo=new Array();
    var audit=new Array();
    for (var i=0;i<mydata.length;i++){
        dataList[i]=mydata[i].dateType;
        quotationTotal[i]=mydata[i].quotationTotal;
        member[i]=mydata[i].member;
        invalidNo[i]=mydata[i].invalidNo;
        audit[i]=mydata[i].audit;
    }
    var tendencyChart=echarts.init(document.getElementById("pieTendency"));
    var tendencyoption = {
        title: {
            text: '报价单趋势图'
        },
        tooltip : {
            trigger: 'axis'
        },
        legend: {
            data:['报价单总数','报价会员数','无效数','待审核数']
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
                data :dataList /*['周一','周二','周三','周四','周五','周六','周日']*/
            }
        ],
        yAxis : [
            {
                type : 'value'
            }
        ],
        series : [
            {
                name:'报价单总数',
                type:'line',
                stack: '总量',
                areaStyle: {normal: {}},
                data:quotationTotal
            },
            {
                name:'报价会员数',
                type:'line',
                stack: '总量',
                areaStyle: {normal: {}},
                data:member
            },
            {
                name:'无效数',
                type:'line',
                stack: '总量',
                areaStyle: {normal: {}},
                data:invalidNo
            },
            {
                name:'待审核数',
                type:'line',
                stack: '总量',
                label: {
                    normal: {
                        show: true,
                        position: 'top'
                    }
                },
                areaStyle: {normal: {}},
                data:audit
            }
        ]
    };
    tendencyChart.setOption(tendencyoption);
}
function pieCharts(){
    var myChart=echarts.init(document.getElementById('pieChart'));
    var data = [{
        value: 100,
        name: '待审核数'
    }, {
        value: 150,
        name: '已发布数'
    }, {
        value: 50,
        name: '无效数'
    }, {
        value: 200,
        name: '已作废数'
    }];
    var option = {
        backgroundColor: '#fff',
        title: {
            text: '报价单总数',
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
            data: ['待审核数', '已发布数', '无效数', '已作废数']
        },
        series: [{
            type: 'pie',
            selectedMode: 'single',
            radius: ['25%', '58%'],
            color: ['#9933CC', '#00CC33', '#FF0033', '#FF9900'],

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
    myChart.setOption(option);
}
function tendencyCharts(){
    var tendencyChart=echarts.init(document.getElementById("pieTendency"));

    var colors = ['#9933CC', '#00CC33', '#FF0033', '#FF9900','#0099FF'];

    var option = {
        color: colors,

        tooltip: {
            trigger: 'axis'
        },
        grid: {
            right: '20%'
        },
        toolbox: {
            feature: {
                dataView: {show: false, readOnly: false},
                restore: {show: false},
                saveAsImage: {show: false}
            }
        },
        legend: {
            data:['待审核','已发布','无效数','已作废','会员数']
        },
        xAxis: [
            {
                type: 'category',
                axisTick: {
                    alignWithLabel: true
                },
                data: ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月']
            }
        ],
        yAxis: [
            {
                type: 'value',
                name: '报价单数量',
                min: 0,
                max: 250,
                position: 'left',
                axisLine: {
                    lineStyle: {
                        color: '#000'
                    }
                },
                axisLabel: {
                    formatter: '{value} 个'
                }
            },
            {
                type: 'value',
                name: '会员数',
                min: 0,
                max: 25,
                position: 'right',
                axisLine: {
                    lineStyle: {
                        color: colors[4]
                    }
                },
                axisLabel: {
                    formatter: '{value}个 '
                }
            }
        ],
        series: [
            {
                name:'待审核',
                type:'bar',
                data:[2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3]
            },
            {
                name:'已发布',
                type:'bar',
                yAxisIndex: 0,
                data:[2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3]
            },
            {
                name:'无效数',
                type:'bar',
                yAxisIndex: 0,
                data:[2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3]
            },
            {
                name:'已作废',
                type:'bar',
                yAxisIndex: 0,
                data:[2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3]
            },
            {
                name:'会员数',
                type:'line',
                yAxisIndex: 1,
                data:[2.0, 2.2, 3.3, 4.5, 6.3, 10.2, 20.3, 23.4, 23.0, 16.5, 12.0, 6.2]
            }
        ]
    };
    tendencyChart.setOption(option);
}


