
var incresestaff_show_flag ; //增员津贴展示标识;0区间数值 ；1 固定数值
//赋值下拉框
var increaseOptionsminSign =  "<option value='0'> < </option><option value='1'> <= </option>"; //增员小于标识
var increaseOptionsmaxSign =  "<option value='0'> < </option><option value='1'> <= </option>"; //增员大于标识

/*展示增也津贴配置*/
function showcalIncreaseStaff(ruleType,show_salesOrgId,show_isDefaultCal,show_calOrgId) { //规则数值 ;自己组织机构主键;是否默认基本法（0：默认；1:自定义）;使用默认基本法总/分公司机构id
   // alert(ruleType+"_机构Id："+show_salesOrgId+"_"+show_isDefaultCal+"_默认："+show_calOrgId);
    if(show_isDefaultCal == ''){
        $.alert({
            title: '提示信息！',
            content: '请先选择机构！',
            type: 'red'
        });
        return false;
    }

    var calIncreaseStaffs ; // 增员津贴信息
    var orgId ; // 获取增员津贴配置表对应的 Id
    if(show_isDefaultCal==0){ //0：默认是分公司的组织机构Id ；1:自定义是各自团队的组织机构Id
        orgId=show_calOrgId;
    }else{
        orgId=show_salesOrgId;
    }
    // 获取增员津贴的配置参数
    $.ajax({
        async: false,
        url:'calIncreaseStaff/getCalIncreaseStaffList',
        type:'post',
        dataType:'json',
        data:{"orgId":orgId,"ruleType":ruleType},
        success:function(data){
            if(data.messageCode == "200"){
                calIncreaseStaffs=data.rows;

            }else{
                $.alert({
                    title: '提示信息！',
                    content: '获取增加津贴信息失败！',
                    type: 'red'
                });
            }
        },
        error:function(){
            $.alert({
                title: '提示信息！',
                content: '获取增加津贴信息失败！',
                type: 'red'
            });
        }
    });
    if(ruleType==10){ //区间设定值
        incresestaff_show_flag =0 ;
        $("#calIncreaseStaff_d").show();
        $("#calIncreaseStaff_d  tr:not(:first)").empty("");
        $("#calIncreaseStaff_g").hide();
        $("#updateButton").hide();
        /*拼接参数值*/
        $.each(calIncreaseStaffs, function(i, n) {
            var tbBody = "";
            var increseid =n.id;
            var ruleType = n.ruleType;  //规则（10：区间设定值；11：固定数值；）
            var minValue = n.minValue;  //下限值
            var maxValue = n.maxValue;  //上限值
            var minSign = n.minSign;    //下限标记符号（0：大于，1：大于等于）
            var maxSign = n.maxSign;    //上限标记符号（0：小于，1：小于等于）
            var allowance = accMul(n.allowanceRatio,100); //津贴比例
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
            tbBody+="<tr><td>"+"<input value='总价值佣金FYC(元)'  disabled='disabled' style=\"width:200px;\"/>"+" </td>"
                ruleType ='区间设定值';
                tbBody+="<td>"+  "<input value='"+ruleType+"' disabled='disabled' style=\"width:220px;\"/>"+
                        "</td><td><input   name='increaseMinValue'  type='text' style=\"width:140px;\" value='"+minValue+"' disabled='disabled'/>" +
                                 "<select  name='increaseSelectMinSign' style='width:60px;' disabled='disabled'><option value='"  + n.minSign + "' selected  ='selected'>"+minSign+"</option></select>FYC" +
                                 "<select  name='increaseSelectMaxSign' style='width:60px;' disabled='disabled'><option value='"  + n.maxSign + "' selected  ='selected'>"+maxSign+"</option></select>" +
                                 "<input   name='increaseMaxValue' type='text' style=\"width:140px;\" value='"+maxValue+"' disabled='disabled' /> " +
                        "</td><td><input   name='increaseAllowance' type='text' style=\"width:150px;\" value='"+allowance+"' disabled='disabled'/></td>"+
       "<td style='display:none' ><input   name='increseId'  value="+increseid+" id='increseId'></td> " +
       "<td style='display:none' ><input   name='salesOrgId'  value="+$('#show_salesOrgId').val()+"></td> " +
       "<td style='display:none' ><input   name='increseCalOrgId'  value="+$('#show_calOrgId').val()+"></td> " +
       "<td style='display:none' ><input   name='isDefaultCal'  value="+$('#show_isDefaultCal').val()+"></td> " +
       "<td><a href='#'  rel='external nofollow' class='removeclass'><input  type='button' value='删除' onclick='delIncreaseRow(\"" +increseid+ "\",\""+$('#show_salesOrgId').val()+"\",\""+$('#show_calOrgId').val()+"\",\""+$('#show_isDefaultCal').val()+"\");' disabled='disabled' ></a></td>";
            tbBody+="</tr>"
            $("#calIncreaseStaff_d").append(tbBody);

        });
    }
    if(ruleType==11){//固定数值
        incresestaff_show_flag =1 ;
        $("#calIncreaseStaff_g").show();
        $("#calIncreaseStaff_g  tr:not(:first)").empty("");
        $("#calIncreaseStaff_d").hide();
        $("#updateButton").hide();
        /*拼接参数值*/
        $.each(calIncreaseStaffs, function(i, n) {
            var tbBody = "";
            var increseid =n.id;
            var ruleType = n.ruleType;  //规则（10：区间设定值；11：固定数值；）
            var minValue = n.minValue;  //下限值
            var maxValue = n.maxValue;  //上限值
            var minSign = n.minSign;    //下限标记符号（0：大于，1：大于等于）
            var maxSign = n.maxSign;    //上限标记符号（0：小于，1：小于等于）
            var allowance = accMul(n.allowanceRatio,100); //津贴比例
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
            ruleType ='固定数值';
            tbBody+="<td><input  value='"+ruleType+"' disabled='disabled' style='text-align:center;width:220px'/>" +
                    "<td><input type='text'  name='increaseAllowance' value='"+allowance+"' disabled='disabled' style='text-align:center;width:300px'/></td>"+
            "<td style='display:none' ><input   name='increseId'  value="+increseid+" id='increseId'></td> " +
            "<td style='display:none' ><input   name='salesOrgId'  value="+$('#show_salesOrgId').val()+"></td> " +
            "<td style='display:none' ><input   name='increseCalOrgId'  value="+$('#show_calOrgId').val()+"></td> " +
            "<td style='display:none' ><input   name='isDefaultCal'  value="+$('#show_isDefaultCal').val()+"></td> " ;
            tbBody+="</tr>"
            $("#calIncreaseStaff_g").append(tbBody);
        });
    }

}

//删除行操作
function delIncreaseRow(increseid ,show_salesOrgId ,show_calOrgId,show_isDefaultCal) {
    //组织机构主键:show_salesOrgId ;      show_calOrgId  默认基本法总/分公司机构id
  //  alert("增员删除：增员配置id="+increseid+",组织机构主键="+show_salesOrgId+",分公司机构id="+show_calOrgId+",是否默认="+show_isDefaultCal)
    var mymessage = confirm("确认删除增员津贴配置吗？");
    if (mymessage == true) {
        /* 查询  */
        $.ajax({
            url : "calIncreaseStaff/updateIncreaseState",
            type : "post",
            dataType : "json",
            data : {
                id : increseid ,
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
                    $("#show_isDefaultCal").val("1");
                    teokAll($("#show_salesOrgId").val(),$("#name_salesOrgId").val(),"1",$("#show_calOrgId").val(),"1");
                    showcalIncreaseStaff(cal_increase_staff_ruleType,$('#show_salesOrgId').val(),$('#show_isDefaultCal').val(),$('#show_calOrgId').val());
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

// 修改增员津贴
function increaseStaffUpdate(){

    if(incresestaff_show_flag==0){
        $("#calIncreaseStaff_d input[type='text']").attr("disabled",false);
        $("#calIncreaseStaff_d input[type='button']").attr("disabled",false);
        $("#calIncreaseStaff_d select[name ='increaseSelectMinSign']").attr("disabled",false);
        $("#calIncreaseStaff_d select[name ='increaseSelectMaxSign']").attr("disabled",false);

        // 下拉框赋值
       // $("#calIncreaseStaff_d select[name='increaseSelectMinSign']").append(increaseOptionsminSign);
       // $("#calIncreaseStaff_d select[name='increaseSelectMaxSign']").append(increaseOptionsmaxSign);

        $("#calIncreaseStaff_d select[name='increaseSelectMinSign']").each(function () {
            var minV = $(this).val();
            $(this).html(increaseOptionsminSign);
            $(this).find("option[value='"+minV+"']").attr("selected",true);
        })
        $("#calIncreaseStaff_d select[name='increaseSelectMaxSign']").each(function () {
            var maxV = $(this).val();
            $(this).html(increaseOptionsmaxSign);
            $(this).find("option[value='"+maxV+"']").attr("selected",true);
        })


    }
    if(incresestaff_show_flag==1){
         $("#calIncreaseStaff_g input[type='text']").attr("disabled",false);
    }

   //修改按钮展示
    $("#updateButton").show();



}

// 增加按钮
function increseStaffAdd() {
    if(incresestaff_show_flag==0){
        var insertTbBody = "";
        var insertRuleType ='区间设定值';
        insertTbBody+="<tr><td>"+"<input value='总价值佣金FYC(元)'  disabled='disabled' style=\"width:200px;\"/>"+" </td>";
        insertTbBody+="<td><input value='"+insertRuleType+"' disabled='disabled' style=\"width:220px;\"/>"+
               "</td><td><input    name='increaseMinValue'     type='text' style=\"width:140px;\" value='' />" +
                         "<select  name='increaseSelectMinSign' style='width:60px;' >"+increaseOptionsminSign+"</select>FYC" +
                         "<select  name='increaseSelectMaxSign' style='width:60px;' >"+increaseOptionsmaxSign+"</select>" +
                         "<input   name='increaseMaxValue'      type='text' style=\"width:140px;\" value=''  /> " +
               "</td><td><input    name='increaseAllowance'     type='text' style=\"width:150px;\" value='' /></td>"+
            "<td style='display:none' ><input  name='isDefaultCal'  value="+$('#show_isDefaultCal').val()+"></td> " +
            "<td style='display:none' ><input   name='increseCalOrgId'  value="+$('#show_calOrgId').val()+"></td> " +
            "<td style='display:none' ><input  name='salesOrgId'    value="+$('#show_salesOrgId').val()+" ></td> " ;
              /* "<td><a href='#'  rel='external nofollow' class='removeclass'><input  type='button' value='删除' onclick='delIncreaseRow("+increseid+");' disabled='disabled' ></a></td>";*/
        insertTbBody+="</tr>";
        $("#calIncreaseStaff_d").append(insertTbBody);
    }
    if(incresestaff_show_flag==1){
        $.alert({
            title: '提示信息！',
            content: '固定值不能添加!',
            type: 'blue'
        });
    }
}

// 取消操作
function increseStaffCancel(){
    //还原初始操作
    $("#updateButton").hide();
    showcalIncreaseStaff(cal_increase_staff_ruleType,$('#show_salesOrgId').val(),$('#show_isDefaultCal').val(),$('#show_calOrgId').val());

}

//保存按钮
function increseStaffCommit() {
    //校验参数
    var regFlag =increaseReg();
    if(regFlag==false){
        return false;
    }

    if(incresestaff_show_flag==0){ // 区间值

        $.ajax({
            url:'calIncreaseStaff/qjCommit',
            type:'post',
            dataType:'json',
            data:$("#addFormIncreaseStaffd").serialize(),
            success:function(data){
                if(data.messageCode == "200"){
                    $.alert({
                        title: '提示信息！',
                        content: '添加修改成功!',
                        type: 'blue'
                    });
                    $("#show_isDefaultCal").val("1");
                    teokAll($("#show_salesOrgId").val(),$("#name_salesOrgId").val(),"1",$("#show_calOrgId").val(),"1");
                    showcalIncreaseStaff(cal_increase_staff_ruleType,$('#show_salesOrgId').val(),"1",$('#show_calOrgId').val());
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

    // 固定值
    if(incresestaff_show_flag==1){

        $.ajax({
            url:'calIncreaseStaff/gdCommit',
            type:'post',
            dataType:'json',
            data:$("#addFormIncreaseStaffg").serialize(),
            success:function(data){
                if(data.messageCode == "200"){
                    $.alert({
                        title: '提示信息！',
                        content: '添加修改成功!',
                        type: 'blue'
                    });
                    $("#show_isDefaultCal").val("1");
                    teokAll($("#show_salesOrgId").val(),$("#name_salesOrgId").val(),"1",$("#show_calOrgId").val(),"1");
                    showcalIncreaseStaff(cal_increase_staff_ruleType,$('#show_salesOrgId').val(),"1",$('#show_calOrgId').val());
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
/*参数校验*/
function increaseReg(){
    var regFlag =true ;
    var ysRex = /^(0|[1-9][0-9]*)+(\.\d{0,2})?$/; //保留两位小数
    var zsReg = /(^[1-9]([0-9]*)$|^[0-9]$)/;//正整数
    if(incresestaff_show_flag==0) { // 区间值
        $("#calIncreaseStaff_d input[name='increaseMinValue']").each(function () {
            var minV = $(this).val();
                if(!zsReg.test(minV)){
                    regFlag=false;
                    $.alert({
                        title: '提示信息！',
                        content: '区间小值请输入正整数！',
                        type: 'red'
                    });
                    return false ;
                }
        });
        $("#calIncreaseStaff_d input[name='increaseMaxValue']").each(function () {
            var MaxV = $(this).val();
            if(!zsReg.test(MaxV)){
                regFlag=false;
                $.alert({
                    title: '提示信息！',
                    content: '区间大值请输入正整数！',
                    type: 'red'
                });
                return false ;
            }
        });
        $("#calIncreaseStaff_d input[name='increaseAllowance']").each(function () {
            var allowanceValue = $(this).val();
            if(!ysRex.test(allowanceValue)){
                regFlag=false;
                $.alert({
                    title: '提示信息！',
                    content: '增员津贴比例请输入正数，可以保留两位小数！',
                    type: 'red'
                });
                return false ;
            }
        })
    }

    // 固定值
    if(incresestaff_show_flag==1){
        $("#calIncreaseStaff_g input[name='increaseAllowance']").each(function () {
            var allowanceValue_g = $(this).val();
            if(!ysRex.test(allowanceValue_g)){
                regFlag=false;
                $.alert({
                    title: '提示信息！',
                    content: '增员津贴比例请输入正数，可以保留两位小数！',
                    type: 'red'
                });
                return false ;
            }
        })
    }

    return regFlag;

}



