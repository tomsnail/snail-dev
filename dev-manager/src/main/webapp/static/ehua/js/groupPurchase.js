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
     init();
})
function piePerson(){
    var pieChart=echarts.init(document.getElementById('piePerson'));
    var data = [{
        value: 350,
        name: '成交人数'
    }, {
        value: 150,
        name: '未成交人数'
    }];
    var option = {
        backgroundColor: '#fff',
        title: {
            text: '团购报名人数',
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
            data: ['成交人数', '未成交人数']
        },
        series: [{
            type: 'pie',
            selectedMode: 'single',
            radius: ['25%', '58%'],
            color: ['#00CC33', '#FF0033', '#FF9900'],

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
                    formatter: '{c}人',
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
    pieChart.setOption(option)
}
function pieQty(){
    var pieChart=echarts.init(document.getElementById('pieQty'));
    var data = [{
        value: 2500,
        name: '成交数量'
    }, {
        value: 500,
        name: '未成交数量'
    }];
    var option = {
        backgroundColor: '#fff',
        title: {
            text: '团购报名数量',
            subtext: '300',
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
            data: ['成交数量', '未成交数量']
        },
        series: [{
            type: 'pie',
            selectedMode: 'single',
            radius: ['25%', '58%'],
            color: ['#00CC33', '#FF0033', '#FF9900'],

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
                    formatter: '{c}人',
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
    pieChart.setOption(option)
}
function init(){
    $.getJSON('../json/groupPurchase',function(data){
        var dataList=data.body.dataList;
        var mytable=document.getElementById("detailTable");
        $("#myTbody").empty();
        for(var i=0;i<dataList.length;i++){
            var tr=document.createElement("tr");
            var td1=document.createElement("td");
            td1.innerText=dataList[i].name;
            var td2=document.createElement("td");
            td2.innerText=dataList[i].applyNo;
            var td3=document.createElement("td");
            td3.innerText=dataList[i].applyQty;
            var td4=document.createElement("td");
            td4.innerText=dataList[i].dealNo;
            var td5=document.createElement("td");
            td5.innerText=dataList[i].dealQty;
            var td6=document.createElement("td");
            td6.innerText=dataList[i].dealMoony;
            var td7=document.createElement("td");
            td7.innerText=dataList[i].dealNoRatio;
            var td8=document.createElement("td");
            td8.innerText=dataList[i].dealQtyRatio;
            tr.appendChild(td1);
            tr.appendChild(td2);
            tr.appendChild(td3);
            tr.appendChild(td4);
            tr.appendChild(td5);
            tr.appendChild(td6);
            tr.appendChild(td7);
            tr.appendChild(td8);
            mytable.tBodies[0].appendChild(tr);
        }
    });

}