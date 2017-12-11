/**
 * Created by Administrator on 2017/3/8.
 */
var grouptype=new Array();
var groupPerson=new Array();
var dateDatas={};
var dateary=new Array();
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
function init(){
    $.getJSON("../json/order.json",function (datas) {
        $("#myTbody").empty();
        data=datas.body;
        var tableID=document.getElementById("detailTable") ;
        var totalQty=0;
        var totalMoony=0;
        var allDatas={};
        var results='';
        /*groupPerson.personList=JSON.parse(persons);*/
        var getst='';
        for(var i=0;i<data.length+1;i++){
            /*alert(JSON.stringify(groupPerson));*/
            var tr=document.createElement("tr");
            var td1=document.createElement("td");
            var td2=document.createElement("td");
            var td3=document.createElement("td");
            if(i==data.length){
                td1.innerText="合计:";
                td1.className="total";
                td2.innerText=totalQty;
                td3.innerText=totalMoony;
            }else{
                td1.innerText=data[i].orderType;
                td2.innerText=data[i].orderQty;
                td3.innerText=data[i].orderMoony;
                totalQty+=parseInt(data[i].orderQty)  ;
                totalMoony+=parseFloat(data[i].orderMoony);
                grouptype[i]=data[i].orderType;
                var dataTimes=data[i].dataList
                var dataCollection='';
                var st={"personList":getPerson(datas)} ;
                for(var ii=0;ii<dataTimes.length;ii++){
                    var getData={};
                    getData.dateTime=dataTimes[ii].dateTime;
                    dateary[ii]=dataTimes[ii].dateTime;
                    getData.persons=new Array();
                    getData.qty=new Array();
                    for(var iii=0;iii<dataTimes[ii].dataList.length;iii++){
                        getData.persons[iii]=dataTimes[ii].dataList[iii].backPerson;
                        getData.qty[iii]=dataTimes[ii].dataList[iii].qty;
                        var namear=new Array();
                        for(var s=0;s<st.personList.length;s++){
                            if(st.personList[s].name==dataTimes[ii].dataList[iii].backPerson){
                                if(!st.personList[s].qty){
                                    namear[0]=dataTimes[ii].dataList[iii].qty;
                                    st.personList[s].qty=namear;
                                }else{
                                    namear=st.personList[s].qty;
                                    namear[namear.length]=dataTimes[ii].dataList[iii].qty;
                                    st.personList[s].qty=namear;
                                }
                                break;
                            }
                        }
                    }
                    dataCollection+=JSON.stringify(getData)+",";
                }
                st.type=data[i].orderType;
                getst+=JSON.stringify(st)+",";
                dataCollection=dataCollection.substr(0,dataCollection.length-1);
                dataCollection="["+dataCollection+"]"
                allDatas.dataList=JSON.parse(dataCollection);
                allDatas.type=data[i].orderType;
                results+=JSON.stringify(allDatas)+',';
            }
            tr.appendChild(td1);
            tr.appendChild(td2);
            tr.appendChild(td3);
            tableID.tBodies[0].appendChild(tr);
        }
        results=results.substr(0,results.length-1);
        results="["+results+"]";
        getst=getst.substr(0,getst.length-1);
        getst="["+getst+"]";
        dateDatas.datas=JSON.parse(getst);
        tendencyChartsTwo();
    })
}
function getPerson(datas){
    var persons='';
    if(groupPerson.length>0){
        for(var i=0;i<datas.persons.length;i++){
            var st={};
            st.name=datas.persons[i].name;
            persons+=JSON.stringify(st)+',';
        }
    }else{
        for(var i=0;i<datas.persons.length;i++){
            var st={};
            st.name=datas.persons[i].name;
            persons+=JSON.stringify(st)+',';
            groupPerson[i]=datas.persons[i].name;
        }
    }
    persons=persons.substr(0,persons.length-1);
    persons="["+persons+"]";
    persons=JSON.parse(persons);
    return persons;
}
function tendencyCharts(){
    var tendencyChart=echarts.init(document.getElementById("tendencyChart"));
    var option = {
        tooltip : {
            trigger: 'axis',
            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        legend: {
            data:['团购订单','采购订单',"物流订单",'赵伟冲','陈卫星']
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
                name:'物流订单',
                type:'bar',
                data:[350, 464, 302, 208, 290, 660, 610]
            },
            {
                name:'赵伟冲',
                type:'bar',
                stack: '物流订单',
                data:[50, 232, 101, 154, 100, 330, 410]
            },
            {
                name:'陈卫星',
                type:'bar',
                stack: '物流订单',
                data:[300, 232, 201, 54, 190, 330, 200]
            },
            {
                name:'采购订单',
                type:'bar',
                data:[400, 334, 502, 508, 280, 660, 620]
            },
            {
                name:'赵伟冲',
                type:'bar',
                stack: '采购订单',
                data:[150, 232, 201, 154, 90, 430, 210]
            },
            {
                name:'陈卫星',
                type:'bar',
                stack: '采购订单',
                data:[250, 102, 301, 354, 190, 230, 410]
            },
            {
                name:'团购订单',
                type:'bar',
                data:[740, 864, 802, 868, 1380, 1360, 1340],
            },
            {
                name:'赵伟冲',
                type:'bar',
                barWidth : 10,
                stack: '团购订单',
                data:[620, 732, 701, 734, 1090, 1130, 1120]
            },
            {
                name:'陈卫星',
                type:'bar',
                stack: '团购订单',
                data:[120, 132, 101, 134, 290, 230, 220]
            }
        ]
    };
    tendencyChart.setOption(option);
}
function tendencyChartsTwo(){
    var st=new Array();
    var ins=0;
    for(var i=0;i<grouptype.length;i++){
        for(var ii=0;ii<groupPerson.length;ii++ ){
            st[ins]=grouptype[i];
            ins+=1;
        }
    }
    var tendencyChart=echarts.init(document.getElementById("tendencyChartTwo"));
    var bcBySeriesIndex = ['团购', '团购', '团购', '团购', '采购', '采购', '采购', '采购',"物流","物流","物流","物流"];
    var option = {
        tooltip : {
            trigger: 'axis',
            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            },
            formatter: function (params) {
                var html = [];

                var category = {};
                echarts.util.each(params, function (param) {
                    var catName = param.seriesName;
                    var bc = bcBySeriesIndex[param.seriesIndex];
                    if (!category[catName]) {
                        category[catName] = {};
                    }
                    category[catName][bc] = param.value;
                });
                console.log(category);
                echarts.util.each(category, function (cate, key) {
                    console.log(cate);
                    console.log(key);
                    html.push(
                        '<tr>',
                        '<td>', key, '</td>',
                        '<td>', cate.团购, '</td>',
                        '<td>', cate.采购, '</td>',
                        '<td>', cate.物流, '</td>',
                        '</tr>'
                    );
                })

                return '<table border=1 style="width:200px;"><tbody>'
                    + '<tr><td></td><td>团购</td><td>采购</td><td>物流</td></tr>'
                    + html.join('')
                    + '</tbody></table>';
            }
        },
        legend: {
            data: [
                '赵伟冲', '陈卫星', '毕红岩', '张猛'
            ]
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
                data : ['3/1','3/2','3/3','3/4','3/5','3/6','3/7','3/8','3/9','3/10','3/11','3/12','3/13','3/14']
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
                type:'bar',
                barWidth : 18,
                stack: '团购',
                data:[320, 332, 301, 334, 390, 330, 320,320, 332, 301, 334, 390, 330, 320]
            },
            {
                name:'陈卫星',
                type:'bar',
                stack: '团购',
                data:[120, 132, 101, 134, 90, 230, 210.320, 332, 301, 334, 390, 330, 320]
            },
            {
                name:'毕红岩',
                type:"bar",
                stack: '团购',
                data:[520, 730, 331, 1150, 920, 320, 130,320, 332, 301, 334, 390, 330, 320]
            },
            {
                name:'张猛',
                type:'bar',
                stack: '团购',
                data:[922, 32, 441, 114, 130, 1230, 230,320, 332, 301, 334, 390, 330, 320],
                label: {
                    normal: {
                        show: true,
                        position: 'top',
                        formatter: '团购',
                        textStyle: {color: '#333'}
                    }
                }
            },
            {
                name:'赵伟冲',
                type:'bar',
                barWidth : 18,
                stack: '采购',
                data:[620, 732, 701, 734, 1090, 1130, 1120,320, 332, 301, 334, 390, 330, 320]
            },
            {
                name:'陈卫星',
                type:'bar',
                stack: '采购',
                data:[120, 132, 101, 134, 290, 230, 220,320, 332, 301, 334, 390, 330, 320]
            },
            {
                name:'毕红岩',
                type:'bar',
                stack: '采购',
                data:[260, 732, 721, 714, 1910, 1330, 1130,3220, 3232, 3101, 334, 390, 330, 320]
            },
            {
                name:'张猛',
                type:'bar',
                stack: '采购',
                data:[62, 82, 91, 84, 109, 110, 120,320, 332, 301, 334, 390, 330, 320],
                label: {
                    normal: {
                        show: true,
                        position: 'top',
                        formatter: '采购',
                        textStyle: {color: '#333'}
                    }
                }
            },
            {
                name:'赵伟冲',
                type:'bar',
                barWidth : 18,
                stack: '物流',
                data:[620, 732, 701, 734, 1090, 1130, 1120,320, 332, 301, 334, 390, 330, 320]
            },
            {
                name:'陈卫星',
                type:'bar',
                stack: '物流',
                data:[120, 132, 101, 134, 290, 230, 220,320, 332, 301, 334, 390, 330, 320]
            },
            {
                name:'毕红岩',
                type:'bar',
                stack: '物流',
                data:[60, 72, 71, 74, 190, 130, 110,320, 332, 301, 334, 390, 330, 320]
            },
            {
                name:'张猛',
                type:'bar',
                stack: '物流',
                data:[62, 82, 91, 84, 109, 110, 120,320, 332, 301, 334, 390, 330, 320],
                label: {
                    normal: {
                        show: true,
                        position: 'top',
                        formatter: '物流',
                        textStyle: {color: '#333'}
                    }
                }
            }
        ]
    };
    tendencyChart.setOption(option);

}
