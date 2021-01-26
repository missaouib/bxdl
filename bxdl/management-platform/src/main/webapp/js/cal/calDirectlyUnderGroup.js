var directly_under_group_show_flag;
var directly_under_group_rule_Type;

var underGroupminSign =  "<option value='0'> < </option><option value='1'> <= </option>"; //小于标识
var underGroupmaxSign =  "<option value='0'> < </option><option value='1'> <= </option>"; //大于标识
var underActiveSalerSign = "<option value='0'> < </option><option value='1'> > </option><option value='2'> <= </option><option value='3'> >= </option>";
var underRelationType = "<option value='0'> 或 </option><option value='1'> 且 </option>"


function showcalIUnderGroup(ruleType,show_salesOrgId,show_isDefaultCal,show_calOrgId) {// //规则数值 ;自己组织机构主键;是否默认基本法（0：默认；1:自定义）;使用默认基本法总/分公司机构id
    if(show_isDefaultCal == ''){
        $.alert({
            title: '提示信息！',
            content: '请先选择机构！',
            type: 'red'
        });
        return false;
    }
    clickshowtr(ruleType);
    var calDirectlyUnderGroups ; // 增员津贴信息
    var orgId ; // 获取增员津贴配置表对应的 Id
    if(show_isDefaultCal==0){ //0：默认是分公司的组织机构Id ；1:自定义是各自团队的组织机构Id
        orgId=show_calOrgId;
    }else{
        orgId=show_salesOrgId;
    }
    // 获取增员津贴的配置参数
    $.ajax({
        async: false,
        url:'calDirectlyUnderGroup/getCalDirectlyUnderGroup',
        type:'post',
        dataType:'json',
        data:{"orgId":orgId,"ruleType":ruleType},
        success:function(data){
            if(data.rows){
                calDirectlyUnderGroups=data.rows;
                console.log(calDirectlyUnderGroups);
            }else{
                $.alert({
                    title: '提示信息！',
                    content: '获取直辖组管理津贴信息失败！',
                    type: 'red'
                });
            }
        },
        error:function(){
            $.alert({
                title: '提示信息！',
                content: '获取直辖组管理津贴信息失败！',
                type: 'red'
            });
        }
    });
    if(ruleType==40||ruleType==42){ //区间设定值 活动人力+区间
        $("#calFormUnderGroup_q tr[class='groupTr']").remove();
        directly_under_group_show_flag =0 ;
        $("#calFormUnderGroup_q").show();
        $("#calFormUnderGroup_q  tr:not(:first)").empty("");
        $("#calIUnderGroup_gd").hide();
        $("#updateUnderGroupButton").hide();
        /*拼接参数值*/
        $.each(calDirectlyUnderGroups, function(i, n) {
            var tbBody = "";
            var underGroupId =n.id;
            var zruleType = n.ruleType;  //规则（40：区间设定值；42：活动人力+区间；）
            var minValue = n.minValue;  //下限值
            var maxValue = n.maxValue;  //上限值
            var minSign = n.minSign;    //下限标记符号（0：大于，1：大于等于）
            var maxSign = n.maxSign;    //上限标记符号（0：小于，1：小于等于）
            var allowance = accMul(n.allowanceRatio, 100); //津贴比例
            var activeSalerSign = n.activeSalerSign; //组活力人数标记符号
            var activeSalerNum = n.activeSalerNum; //组活力人数
            var relationType = n.relationType; //组活力人数与FYC设置值关系(0:或；1：且)
            if("0"==n.minSign){
                minSign = "<" ;
            }else if("1"==n.minSign){
                minSign = "<=" ;
            }
            if("0"==n.maxSign){
                maxSign = "<" ;
            }else if("1"==n.maxSign){
                maxSign = "<=" ;
            }
            if(ruleType==42){
                if("0"==activeSalerSign){
                        activeSalerSign = "<";
                }else if("1"==activeSalerSign){
                        activeSalerSign = ">";
                }else if("2"==activeSalerSign){
                        activeSalerSign = "<=";
                }else if("3"==activeSalerSign){
                        activeSalerSign = ">=";
                }
                 if("0"==relationType){
                        relationType = "或";
                }else if("1"==relationType){
                        relationType = "且";
                }
            }
            tbBody+="<tr class='groupTr'><td>"+"<input value='总价值佣金FYC(元)'  disabled='disabled' style=\"width:200px;\"/>"+" </td>"
                if (ruleType==42){
                    zruleType = '活动人力+区间';
                }else {
                    zruleType ='区间设定值';
                }
                tbBody+="<td>"+  "<input value='"+zruleType+"' myvalue='"+n.ruleType+"' name='underGroupRuleType' disabled='disabled' style=\"width:220px;\"/></td>";
                     if(ruleType==42){
                         tbBody+="<td><select name='activeSalerSign' style='width:40px;' disabled='disabled'><option value='"+n.activeSalerSign+"'>"+activeSalerSign+"</option></select>" +
                             "<input style='width:40px;' name='activeSalerNum' value='"+activeSalerNum+"' disabled='disabled'></td>" +
                             "<td><select style='width:40px;' name='relationType' disabled='disabled'><option value='"+n.relationType+"'>"+relationType+"</option></select></td>";
                     }
                tbBody+="<td><input   name='underGroupMinValue'  type='text' style=\"width:150px;\" value='"+minValue+"' disabled='disabled'/>" +
                                 "<select  name='underGroupMinSign' style='width:45px;' disabled='disabled'><option value='"  + n.minSign + "' selected  ='selected'>"+minSign+"</option></select>FYC" +
                                 "<select  name='underGroupMaxSign' style='width:45px;' disabled='disabled'><option value='"  + n.maxSign + "' selected  ='selected'>"+maxSign+"</option></select>" +
                                 "<input   name='underGroupMaxValue' type='text' style=\"width:130px;\" value='"+maxValue+"' disabled='disabled' /> " +
                        "</td><td><input   name='underGroupAllowance' type='text' style=\"width:150px;\" value='"+allowance+"' disabled='disabled'/></td>"+
       "<td style='display:none' ><input   name='underGroupId'  value="+underGroupId+" id='underGroupId'></td> " +
       "<td style='display:none' ><input   name='salesOrgId'  value="+$('#show_salesOrgId').val()+"></td> " +
       "<td style='display:none' ><input   name='underGroupOrgId'  value="+$('#show_calOrgId').val()+"></td> " +
       "<td style='display:none' ><input   name='isDefaultCal'  value="+$('#show_isDefaultCal').val()+"></td> " +
            "<td><a href='#'  rel='external nofollow' class='removeclass'><input  type='button' value='删除' onclick='delUnderGroupIncreaseRow(\"" +underGroupId+ "\",\""+$('#show_salesOrgId').val()+"\",\""+$('#show_calOrgId').val()+"\",\""+$('#show_isDefaultCal').val()+"\");' disabled='disabled' ></a></td>";
            tbBody+="</tr>"
            $("#calFormUnderGroup_q").append(tbBody);

        });
    }
    if(ruleType==41){//固定数值
        directly_under_group_show_flag =1 ;
        $("#calIUnderGroup_gd").show();
        $("#calIUnderGroup_gd  tr:not(:first)").empty("");
        $("#calFormUnderGroup_q").hide();
        $("#updateUnderGroupButton").hide();
        /*拼接参数值*/
        $.each(calDirectlyUnderGroups, function(i, n) {
            var tbBody = "";
            var underGroupId =n.id;
            var zruleType = n.ruleType;  //规则（10：区间设定值；11：固定数值；）
            var minValue = n.minValue;  //下限值
            var maxValue = n.maxValue;  //上限值
            var minSign = n.minSign;    //下限标记符号（0：大于，1：大于等于）
            var maxSign = n.maxSign;    //上限标记符号（0：小于，1：小于等于）
            var allowance = accMul(n.allowanceRatio, 100); //津贴比例
            if("0"==n.minSign){
                minSign = "<" ;
            }else if("1"==n.minSign){
                minSign = "<=" ;
            }
            if("0"==n.maxSign){
                maxSign = "<" ;
            }else if("1"==n.maxSign){
                maxSign = "<=" ;
            }
            tbBody+="<tr><td>"+"<input value='总价值佣金FYC(元)' style='text-align:center;width:200px' disabled='disabled' />"+" </td>"
            zruleType ='固定数值';
            tbBody+="<td><input  value='"+zruleType+"' myvalue='"+n.ruleType+"' name='underGroupRuleType' disabled='disabled' style='text-align:center;width:220px'/>" +
                    "<td><input type='text'  name='underGroupAllowance' value='"+allowance+"' disabled='disabled' style='text-align:center;width:300px'/></td>"+
            "<td style='display:none' ><input   name='underGroupId'  value="+underGroupId+" id='underGroupId'></td> " +
            "<td style='display:none' ><input   name='salesOrgId'  value="+$('#show_salesOrgId').val()+"></td> " +
            "<td style='display:none' ><input   name='underGroupOrgId'  value="+$('#show_calOrgId').val()+"></td> " +
            "<td style='display:none' ><input   name='isDefaultCal'  value="+$('#show_isDefaultCal').val()+"></td> " ;
            tbBody+="</tr>"
            $("#calIUnderGroup_gd").append(tbBody);
        });
    }
}
/** 表单序列化成json方法  */
function form2GroupJsonJson() {
    var paramArray = $("#calFormUnderGroup_q tr[class='groupTr']");
     /*请求参数转json对象*/
    var paramJsonArr =[]
     $(paramArray).each(function(){
        /** 组装规则参数*/
        var ruleType = $(this).find("input[name='underGroupRuleType']").attr("myvalue");
        var minValue = $(this).find("input[name='underGroupMinValue']").val();
        var minSign = $(this).find("select[name='underGroupMinSign']").val();
        var maxSign = $(this).find("select[name='underGroupMaxSign']").val();
        var maxValue = $(this).find("input[name='underGroupMaxValue']").val();
        var allowanceRatio = $(this).find("input[name='underGroupAllowance']").val();
        var activeSalerSign = $(this).find("select[name='activeSalerSign']").val();
        var activeSalerNum = $(this).find("input[name='activeSalerNum']").val();
        var relationType = $(this).find("select[name='relationType']").val();
        var underGroupId = $(this).find("input[name='underGroupId']").val();

        var jsonObj={};
        jsonObj['orgId'] = $('#show_salesOrgId').val();

        jsonObj['ruleType'] = ruleType;
        jsonObj['minValue'] = minValue;
        jsonObj['minSign'] = minSign;
        jsonObj['maxSign'] = maxSign;
        jsonObj['maxValue'] = maxValue;
        jsonObj['allowanceRatio'] = allowanceRatio;
        jsonObj['activeSalerSign'] = activeSalerSign;
        jsonObj['activeSalerNum'] = activeSalerNum;
        jsonObj['relationType'] = relationType;
        jsonObj['id'] = underGroupId;

        paramJsonArr.push(jsonObj);
    });
    console.log(paramJsonArr);
    var formGroupJson = {};
    formGroupJson['list'] = paramJsonArr;
    // json对象再转换成json字符串
    return formGroupJson;
}

function form2GroupJsonYz(formFlag) {
    if(directly_under_group_show_flag==0){
        var paramArray = $("#calFormUnderGroup_q tr[class='groupTr']");
     /*请求参数转json对象*/
    var paramJsonArr =[]
        $(paramArray).each(function(){
            /** 组装规则参数*/
            var ruleType = $(this).find("input[name='underGroupRuleType']").attr("myvalue");
            var minValue = $(this).find("input[name='underGroupMinValue']").val();
            var minSign = $(this).find("select[name='underGroupMinSign']").val();
            var maxSign = $(this).find("select[name='underGroupMaxSign']").val();
            var maxValue = $(this).find("input[name='underGroupMaxValue']").val();
            var allowanceRatio = $(this).find("input[name='underGroupAllowance']").val();
            var activeSalerSign = $(this).find("select[name='activeSalerSign']").val();
            var activeSalerNum = $(this).find("input[name='activeSalerNum']").val();
            var relationType = $(this).find("select[name='relationType']").val();
            var underGroupId = $(this).find("input[name='underGroupId']").val();
            var reg = /(^[1-9]([0-9]*)$|^[0-9]$)/;
            var yxreg =/^(0|[1-9][0-9]*)+(\.\d{0,2})?$/;
            if(''==minValue){
                    $.alert({
                        title: '提示信息！',
                        content: '请输入区间最小值！',
                        type: 'red'
                    });
                    formFlag = false;
                    return false;
            }
            if(''==maxValue){
                    $.alert({
                        title: '提示信息！',
                        content: '请输入区间最大值！',
                        type: 'red'
                    });
                    formFlag = false;
                    return false;
            }
            if(''==allowanceRatio){
                    $.alert({
                        title: '提示信息！',
                        content: '请输入展业津贴！',
                        type: 'red'
                    });
                    formFlag = false;
                    return false;
            }
            if(!reg.test(minValue)||!reg.test(maxValue)||!yxreg.test(allowanceRatio)){
                 $.alert({
                        title: '提示信息！',
                        content: '请输入有效数字！',
                        type: 'red'
                    });
                    formFlag = false;
                    return false;
            }
            if('42'==ruleType){
                if (''==activeSalerNum){
                    $.alert({
                        title: '提示信息！',
                        content: '请输入组活动人力人数！',
                        type: 'red'
                    });
                    formFlag = false;
                    return false;
                }
                if (!reg.test(activeSalerNum)){
                    $.alert({
                        title: '提示信息！',
                        content: '请输入有效数字！',
                        type: 'red'
                    });
                    formFlag = false;
                    return false;
                }
            }
        });
    }
    if(directly_under_group_show_flag==1){
        var yxreg =/^(0|[1-9][0-9]*)+(\.\d{0,2})?$/;
        $("#calIUnderGroup_gd input[name='underGroupAllowance']").each(function () {
            var allowanceValue_g = $(this).val();
            if(!yxreg.test(allowanceValue_g)){
                formFlag=false;
                $.alert({
                    title: '提示信息！',
                    content: '请输入有效数字！',
                    type: 'red'
                });
                return false ;
            }
        })
    }

    console.log(formFlag);
    return formFlag;

}

function underGroupCommit() {
     var formFlag =true;
    var formFlag = form2GroupJsonYz(formFlag);
    if(directly_under_group_show_flag==0){ // 区间值
        var formGroupJson = form2GroupJsonJson();
        formGroupJson['salesOrgId'] = $('#show_salesOrgId').val();
        formGroupJson['isDefaultCal'] = $('#show_isDefaultCal').val();
        formGroupJson['showCalOrgId'] = $('#show_calOrgId').val();
        if (formFlag){
          $.ajax({
            url:'calDirectlyUnderGroup/underGroupqjCommit',
            type:'post',
            dataType:'json',
            contentType: "application/json",
            data:JSON.stringify(formGroupJson),
            success:function(data){
                if(data.messageCode == "200"){
                    $.alert({
                        title: '提示信息！',
                        content: '添加修改成功!',
                        type: 'blue'
                    });
                    $("#show_isDefaultCal").val("1");
                    teokAll($("#show_salesOrgId").val(),$("#name_salesOrgId").val(),"1",$("#show_calOrgId").val(),"1");
                    showcalIUnderGroup(cal_directly_under_group_ruleType,$('#show_salesOrgId').val(),$('#show_isDefaultCal').val(),$('#show_calOrgId').val());
                }else{
                    $.alert({
                        title: '提示信息！',
                        content: '添加修改异常！',
                        type: 'red'
                    });
                }
            },
            error:function(){
                $.alert({
                    title: '提示信息！',
                    content: '添加修改异常！',
                    type: 'red'
                });
            }
        });
        }

    }

    // 固定值
    if(directly_under_group_show_flag==1){
        if(formFlag) {
            $.ajax({
                url: 'calDirectlyUnderGroup/underGroupgdCommit',
                type: 'post',
                dataType: 'json',
                data: $("#addFormUnderGroupGd").serialize(),
                success: function (data) {
                    if (data.messageCode == "200") {
                        $.alert({
                            title: '提示信息！',
                            content: '添加修改成功!',
                            type: 'blue'
                        });
                        $("#show_isDefaultCal").val("1");
                        teokAll($("#show_salesOrgId").val(), $("#name_salesOrgId").val(), "1", $("#show_calOrgId").val(), "1");
                        showcalIUnderGroup(cal_directly_under_group_ruleType, $('#show_salesOrgId').val(), $('#show_isDefaultCal').val(), $('#show_calOrgId').val());
                    } else {
                        $.alert({
                            title: '提示信息！',
                            content: '添加修改异常！',
                            type: 'red'
                        });
                    }
                },
                error: function () {
                    $.alert({
                        title: '提示信息！',
                        content: '添加修改异常！',
                        type: 'red'
                    });
                }
            });
        }
    }


}

function underGroupAdd() {
    if(directly_under_group_show_flag==0){
        var underGroupTbBody = "";
        var underGroupRuleType ='区间设定值';
        if (cal_directly_under_group_ruleType==42){
            underGroupRuleType = '活动人力+区间';
        }else {
            underGroupRuleType ='区间设定值';
        }

        underGroupTbBody+="<tr class='groupTr'><td>"+"<input value='总价值佣金FYC(元)'  disabled='disabled' style=\"width:200px;\"/>"+" </td>";
        underGroupTbBody+="<td><input value='"+underGroupRuleType+"'  name='underGroupRuleType' myvalue='"+cal_directly_under_group_ruleType+"' disabled='disabled' style=\"width:220px;\"/></td>";
        if(cal_directly_under_group_ruleType==42){
            underGroupTbBody+="<td><select name='activeSalerSign' style='width:40px;'>"+underActiveSalerSign+"</select>" +
                            "<input style='width:40px;' name='activeSalerNum' value=''></td>"+
                            "<td><select style='width:40px;' name='relationType'>"+underRelationType+"</select></td>";
        }
        underGroupTbBody+="<td><input    name='underGroupMinValue'     type='text' style=\"width:150px;\" value='' />" +
                         "<select  name='underGroupMinSign' style='width:45px;' >"+underGroupminSign+"</select>FYC" +
                         "<select  name='underGroupMaxSign' style='width:45px;' >"+underGroupmaxSign+"</select>" +
                         "<input   name='underGroupMaxValue'      type='text' style=\"width:130px;\" value=''  /> " +
               "</td><td><input    name='underGroupAllowance'     type='text' style=\"width:150px;\" value='' /></td>"+
            "<td style='display:none' ><input  name='isDefaultCal'  value="+$('#show_isDefaultCal').val()+"></td> " +
            "<td style='display:none' ><input  name='salesOrgId'    value="+$('#show_salesOrgId').val()+" ></td> "
            "<td style='display:none' ><input   name='underGroupOrgId'  value="+$('#show_calOrgId').val()+"></td> ";
              /* "<td><a href='#'  rel='external nofollow' class='removeclass'><input  type='button' value='删除' onclick='delIncreaseRow("+increseid+");' disabled='disabled' ></a></td>";*/
        underGroupTbBody+="</tr>";
        $("#calFormUnderGroup_q").append(underGroupTbBody);
    }
    if(directly_under_group_show_flag==1){
        $.alert({
            title: '提示信息！',
            content: '固定值不能添加!',
            type: 'blue'
        });
    }
}

function clickshowtr(ruleType) {
    if(42==ruleType){
        $("#groupNum").show();
        $("#grouprelation").show();
    }else {
        $("#groupNum").hide();
        $("#grouprelation").hide();
    }
}

// 修改增员津贴
function underGroupUpdate(){

    if(directly_under_group_show_flag==0){
        $("#calFormUnderGroup_q input[type='text']").attr("disabled",false);
        $("#calFormUnderGroup_q input[type='button']").attr("disabled",false);
        $("#calFormUnderGroup_q select[name ='underGroupMinSign']").attr("disabled",false);
        $("#calFormUnderGroup_q select[name ='underGroupMaxSign']").attr("disabled",false);

        // 下拉框赋值
        $("#calFormUnderGroup_q select[name='underGroupMinSign']").each(function () {
               var minV = $(this).val();
               $(this).html(underGroupminSign);
               $(this).find("option[value='"+minV+"']").attr("selected",true);
        })
        $("#calFormUnderGroup_q select[name='underGroupMaxSign']").each(function () {
           var maxV = $(this).val();
           $(this).html(underGroupmaxSign);
           $(this).find("option[value='"+maxV+"']").attr("selected",true);
        })
        if(42==cal_directly_under_group_ruleType){
            $("#calFormUnderGroup_q input[name='activeSalerNum']").attr("disabled",false);
            $("#calFormUnderGroup_q select[name ='activeSalerSign']").attr("disabled",false);
            $("#calFormUnderGroup_q select[name ='relationType']").attr("disabled",false);
            // 下拉框赋值
            $("#calFormUnderGroup_q select[name='activeSalerSign']").each(function () {
                   var actV = $(this).val();
                   $(this).html(underActiveSalerSign);
                   $(this).find("option[value='"+actV+"']").attr("selected",true);
            })
            $("#calFormUnderGroup_q select[name='relationType']").each(function () {
                   var relV = $(this).val();
                   $(this).html(underRelationType);
                   $(this).find("option[value='"+relV+"']").attr("selected",true);
            })
        }

    }
    if(directly_under_group_show_flag==1){
         $("#calIUnderGroup_gd input[type='text']").attr("disabled",false);
    }

   //修改按钮展示
    $("#updateUnderGroupButton").show();
}

// 取消操作
function underGroupCancel(){
    //还原初始操作
    $("#updateUnderGroupButton").hide();
    showcalIUnderGroup(cal_directly_under_group_ruleType,$('#show_salesOrgId').val(),$('#show_isDefaultCal').val(),$('#show_calOrgId').val());

}





//删除行操作
function delUnderGroupIncreaseRow(underGroupId,show_salesOrgId ,show_calOrgId,show_isDefaultCal) {
    var mymessage = confirm("确认删除吗？");
    if (mymessage == true) {
        /* 查询  */
        $.ajax({
            url : "calDirectlyUnderGroup/delUnderGroupIncreaseRow",
            type : "post",
            dataType : "json",
            data : {
                id : underGroupId,
                show_salesOrgId:show_salesOrgId ,
                show_calOrgId:show_calOrgId,
                show_isDefaultCal:show_isDefaultCal
            },
            success : function(msg) {
                if (msg.messageCode == "200") {
                    $.alert({
                        title: '提示信息！',
                        content: '删除成功!',
                        type: 'blue'
                    });
                    showcalIUnderGroup(cal_directly_under_group_ruleType,$('#show_salesOrgId').val(),$('#show_isDefaultCal').val(),$('#show_calOrgId').val());
                } else {
                    $.alert({
                        title: '提示信息！',
                        content: '删除失败!',
                        type: 'red'
                    });
                }
            },
            error : function() {
                $.alert({
                    title: '提示信息！',
                    content: '请求失败！',
                    type: 'red'
                });
            }
        });

    }


}