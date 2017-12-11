/**
 * Created by Administrator on 2017/3/6.
 */
$(function () {
    editGoods();
})
function editGoods(){
    var getImg='';
    var selectBtn;
    var imgCount=1;
    var isMain=false;
    var id=1;
    $("#selectPrice").change(function () {
        if($(this).val()=="面议"){
            $("#showPrice").css({
                "display":"none"
            })
        }else{
            $("#showPrice").css({
                "display":"block"
            })
        }
    })
    $("#enterpriseForm").bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            ei_name: {
                validators: {
                    notEmpty: {
                        message: '请输入企业名称'
                    }
                }
            },
            contract_name: {
                validators: {
                    notEmpty: {
                        message: '请输入联系人'
                    },
                }
            },
            contract_phone:{
                validators:{
                    notEmpty: {
                        message: '请输入联系电话'
                    },
                    regexp: {
                        regexp: /^[0-9]*$/,
                        message: '请输入0-9数字'
                    }
                }
            },
        }
    });
    $('#detailGoods').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            ei_name: {
                validators: {
                    notEmpty: {
                        message: '请输入企业名称'
                    }
                }
            },
            contract_name: {
                validators: {
                    notEmpty: {
                        message: '请输入联系人'
                    },
                }
            },
            contract_phone:{
                validators:{
                    notEmpty: {
                        message: '请输入联系电话'
                    },
                    regexp: {
                        regexp: /^[0-9]*$/,
                        message: '请输入0-9数字'
                    }
                }
            },
            storeQty:{
                validators:{
                    notEmpty: {
                        message: '请输入库存量'
                    },
                    regexp: {
                        regexp: /^[0-9]*$/,
                        message: '请输入0-9数字'
                    },
                }
            },
            currentPrice:{
                validators:{
                    regexp: {
                        regexp: /^[0-9]*$/,
                        message: '请输入0-9数字'
                    },
                    callback: {
                        message: '请输入当前价格',
                        callback: function(value, validator) {
                            var specialPrice=$('#selectPrice option:selected').text()
                            var currentPrice=$("#currentPrice").val();
                            var publicPrice=$("#publicPrice").val();
                            if(specialPrice=="面议"){
                                return true;
                            }else{
                                if(!currentPrice){
                                    if(publicPrice){
                                        return true;
                                    }else{
                                        return false;
                                    }
                                }else{
                                    return true;
                                }
                            }
                        }
                    }
                }
            },
            surplus:{
                validators:{
                    regexp: {
                        regexp: /^[0-9]*$/,
                        message: '请输入0-9数字'
                    },
                }
            },
            currentPriceEnd:{
                validators:{
                    regexp: {
                        regexp: /^[0-9]*$/,
                        message: '请输入0-9数字'
                    },
                }
            },
            discount:{
                validators:{
                    regexp: {
                        regexp: /^[0-9]*$/,
                        message: '请输入0-9数字'
                    },
                }
            },
            currentAllPrice:{
                validators:{
                    regexp: {
                        regexp: /^[0-9]*$/,
                        message: '请输入0-9数字'
                    },
                }
            },
            publicPrice:{
                validators:{
                    regexp: {
                        regexp: /^[0-9]*$/,
                        message: '请输入0-9数字'
                    },
                }
            },
            publicPriceEnd:{
                validators:{
                    regexp: {
                        regexp: /^[0-9]*$/,
                        message: '请输入0-9数字'
                    },
                }
            },
            publicDiscount:{
                validators:{
                    regexp: {
                        regexp: /^[0-9]*$/,
                        message: '请输入0-9数字'
                    },
                }
            },
            publicAllPrice:{
                validators:{
                    regexp: {
                        regexp: /^[0-9]*$/,
                        message: '请输入0-9数字'
                    },
                }
            },

        }
    });
    $("#btnSubmit").click(function () {
        $("#enterpriseForm").bootstrapValidator('validate');
        $("#detailGoods").bootstrapValidator('validate');
        if($("#enterpriseForm").data("bootstrapValidator").isValid() && $("#detailGoods").data("bootstrapValidator").isValid()){

        }
    })
    $("#selectImg").click(function () {
        isMain=false;
        selectImg(this);
    })
    $("#upload").change(function () {
        var filenow=$(this)[0];
        var windowURL = window.URL || window.webkitURL;
        var dataURL;
        var imgs=getImg;
        if(isMain){
            imgs=$(".mainImg");
        }
        if(filenow && filenow.files && filenow.files[0]) {
            for(var i=0;i<filenow.files.length;i++){
                var currentfile = filenow.files[i];
                dataURL = windowURL.createObjectURL(currentfile);
                if(!isMain) {
                    var divid = document.getElementById("addImages");
                    var firstdiv = document.createElement("div");
                    firstdiv.className = "col-sm-2";
                    var seconddiv = document.createElement("div");
                    seconddiv.className = "imgdiv";
                    var thirddiv = document.createElement("div");
                    var img = document.createElement("img");
                    img.src = dataURL;
                    img.className = "myimage";
                    thirddiv.appendChild(img);
                    var fourdiv = document.createElement("div");
                    fourdiv.className = "imgBtns";
                    id += 1;
                    var btn1 = document.createElement("input");
                    btn1.id="selectImg"+id;
                    btn1.type = "button";
                    btn1.value = "修改";
                    var btn2 = document.createElement("input");
                    btn2.type = "button";
                    btn2.value = "删除";
                    btn2.className="clearImg"

                    btn2.id = "clearImg" + id;
                    fourdiv.appendChild(btn1);
                    fourdiv.appendChild(btn2);
                    seconddiv.appendChild(thirddiv);
                    seconddiv.appendChild(fourdiv);
                    firstdiv.appendChild(seconddiv);
                    divid.appendChild(firstdiv);
                    $("#selectImg"+id).click(function () {
                        isMain=false;
                        getImg=$(this).parent().parent().find("img");
                        $("#uploadone").unbind("click");
                        $("#uploadone").click();
                    })
                    $("#clearImg" + id).click(function () {
                        deleteDiv(this);
                    })
                }else{
                    dataURL = windowURL.createObjectURL(currentfile);
                    imgs.prop('src',dataURL);
                }
            }
        }
    })
    $("#uploadone").change(function () {
        var filenow=$(this)[0];
        var windowURL = window.URL || window.webkitURL;
        var dataURL;
        var imgs=getImg;
        if(isMain){
            imgs=$(".mainImg");
        }
        var currentfile = filenow.files[0];
        dataURL = windowURL.createObjectURL(currentfile);
        imgs.prop('src',dataURL);
        if(isMain){
            var currentval=$(selectBtn).val();
            if(currentval=="选择"){
                $(selectBtn).val("修改");
                $(selectBtn).next().css({
                    "display": "inline-block"
                })
            }
        }
    })
    $(".selectMainImg").click(function () {
        isMain=true;
        selectBtn=this;
        getImg=$(this).parent().parent().find("img");
        $("#uploadone").unbind("click");
        $("#uploadone").click();;
    })
    $(".clearMainImg").click(function () {
        var img=$(this).parent().parent().find("img");
        img.prop("src","img/add.png");
        $(".selectMainImg").text("选择");
    })
    $("#cleagImgDefault").click(function () {
        deleteDiv(this);
    })
    function selectImg(btn){
        selectBtn=$(btn);
        getImg=$(btn).parent().parent().find("img");
        $("#upload").unbind("click");
        $("#upload").click();
    }
    function  addImg(){
        var divid=document.getElementById("moreImg");
        setDiv(divid);
        /*if(imgCount%6!=0){
         setDiv(divid);
         }else{
         imgCount+=1;
         var firstdiv=document.createElement("div");
         firstdiv.className="col-sm-2";
         divid.appendChild(firstdiv);
         setDiv(divid);
         }*/


    }
    function setDiv(divid){
        var firstdiv=document.createElement("div");
        firstdiv.className="col-sm-2";
        var seconddiv=document.createElement("div");
        seconddiv.className="imgdiv";
        var thirddiv=document.createElement("div");
        var img=document.createElement("img");
        img.src="img/add.png";
        img.className="myimage";
        thirddiv.appendChild(img);
        var fourdiv=document.createElement("div");
        fourdiv.className="imgBtns";
        var btn1=document.createElement("input");
        btn1.type="button";
        btn1.value="选择";
        btn1.className="selectImg";
        var btn2=document.createElement("input");
        btn2.type="button";
        btn2.value="删除";
        id+=1;
        btn2.className="clearImg";
        btn2.id="clearImg"+id;
        fourdiv.appendChild(btn1);
        fourdiv.appendChild(btn2);
        seconddiv.appendChild(thirddiv);
        seconddiv.appendChild(fourdiv);
        firstdiv.appendChild(seconddiv);
        divid.appendChild(firstdiv);
        $(".selectImg").click(function () {
            selectImg(this);
        })
        $("#clearImg"+id).click(function () {
            deleteDiv(this);
        })
    }
    function deleteDiv(btn){
        imgCount-=1;
        var ss=$(btn).parent().parent().parent().css({
            "display":"none"
        })
    }
}
