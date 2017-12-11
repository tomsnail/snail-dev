/**
 * Created by Administrator on 2017/3/6.
 */
$(function () {
    publicGoods();
    updateGoods();
})
function  publicGoods(){
    $("#groupGoods").bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            activity_name: {
                validators: {
                    notEmpty: {
                        message: '请输入活动名称'
                    },
                }
            },
            original_price:{
                validators:{
                    notEmpty: {
                        message: '请输入原价'
                    },
                    regexp: {
                        regexp: /^[0-9]*$/,
                        message: '请输入0-9数字'
                    }
                }
            },
            group_qty: {
                validators: {
                    notEmpty: {
                        message: '请输入成团数量'
                    },
                    regexp: {
                        regexp: /^[0-9]*$/,
                        message: '请输入0-9数字'
                    }
                }
            },
            min_qty: {
                validators: {
                    notEmpty: {
                        message: '请输入最小采购量'
                    },
                    regexp: {
                        regexp: /^[0-9]*$/,
                        message: '请输入0-9数字'
                    }
                }
            },
            introduced_group: {
                validators: {
                    notEmpty: {
                        message: '请输入本团介绍'
                    },
                }
            },
            group_detail: {
                validators: {
                    notEmpty: {
                        message: '请输入团购详情'
                    },
                }
            },
            begin_date: {
                validators: {
                    notEmpty: {
                        message: '请选择开始时间'
                    },
                }
            },
            end_date: {
                validators: {
                    notEmpty: {
                        message: '请选择结束时间'
                    },
                }
            },
        }
    });
    $("#publicGoods").click(function () {
        $("#groupGoods").bootstrapValidator('validate');
        if($("#groupGoods").data("bootstrapValidator").isValid() ){

        }
    })
    var json=[{name:"元"},{name:"美元"}];
    var getid=document.getElementById("original_unit");
    for(var i=0;i<json.length;i++){
        getid.options[i]=new Option(json[i].name,i+1);
    }
}
function updateGoods(){
    var isshow=true;
    $("#detailGroupGoods").bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            activity_name1: {
                validators: {
                    notEmpty: {
                        message: '请输入活动名称'
                    },
                }
            },
            original_price1:{
                validators:{
                    notEmpty: {
                        message: '请输入原价'
                    },
                    regexp: {
                        regexp: /^[0-9]*$/,
                        message: '请输入0-9数字'
                    }
                }
            },
            group_qty1: {
                validators: {
                    notEmpty: {
                        message: '请输入成团数量'
                    },
                    regexp: {
                        regexp: /^[0-9]*$/,
                        message: '请输入0-9数字'
                    }
                }
            },
            min_qty1: {
                validators: {
                    notEmpty: {
                        message: '请输入最小采购量'
                    },
                    regexp: {
                        regexp: /^[0-9]*$/,
                        message: '请输入0-9数字'
                    }
                }
            },
            introduced_group1: {
                validators: {
                    notEmpty: {
                        message: '请输入本团介绍'
                    },
                }
            },
            group_detail1: {
                validators: {
                    notEmpty: {
                        message: '请输入团购详情'
                    },
                }
            },
            begin_date1: {
                validators: {
                    notEmpty: {
                        message: '请选择开始时间'
                    },
                }
            },
            end_date1: {
                validators: {
                    notEmpty: {
                        message: '请选择结束时间'
                    },
                }
            },
        }
    });
    $("#updateGoods").click(function(){
        $("#detailGroupGoods").bootstrapValidator('validate');
        if($("#detailGroupGoods").data("bootstrapValidator").isValid() ){

        }
    })
    $("#showMoreInfo").click(function () {
        if(isshow){
            $("#showAllInfo").fadeIn();
            $("#setcion").attr("class","glyphicon glyphicon-arrow-up");
            isshow=false;
        }else{
            $("#showAllInfo").fadeOut(100);
            $("#setcion").attr("class","glyphicon glyphicon-arrow-down");
            isshow=true;
        }
    })
}