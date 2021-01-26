
var dept_show_flag ; //增员津贴展示标识;0区间数值 ；1 固定数值
//赋值下拉框
var deptOptionsminSign =  "<option value='0'> < </option><option value='1'> <= </option>"; //小于标识
var deptOptionsmaxSign =  "<option value='0'> < </option><option value='1'> <= </option>"; //大于标识

/*展示直辖部管理津贴配置*/
function showcalDept(ruleType,show_salesOrgId,show_isDefaultCal,show_calOrgId) { //规则数值 ;自己组织机构主键;是否默认基本法（0：默认；1:自定义）;使用默认基本法总/分公司机构id
   // alert(ruleType+"_"+show_salesOrgId+"_"+show_isDefaultCal+"_"+show_calOrgId);
    if(show_isDefaultCal == ''){
        $.alert({
            title: '提示信息！',
            content: '请先选择机构！',
            type: 'red'
        });
        return false;
    }
    var calDepts ; // 部门津贴信息
    var orgId ; // 获取增员津贴配置表对应的 Id
    if(show_isDefaultCal==0){ //0：默认是分公司的组织机构Id ；1:自定义是各自团队的组织机构Id
        orgId=show_calOrgId;
    }else{
        orgId=show_salesOrgId;
    }
    // 获取增员津贴的配置参数
    $.ajax({
        async: false,
        url:'calDept/getDeptList',
        type:'post',
        dataType:'json',
        data:{"orgId":orgId,"ruleType":ruleType},
        success:function(data){
            if(data.messageCode == "200"){
                calDepts=data.rows;
            }else{
                $.alert({
                    title: '提示信息！',
                    content: '获取直辖部管理津贴信息失败！',
                    type: 'red'
                });
            }
        },
        error:function(){
            $.alert({
                title: '提示信息！',
                content: '获取直辖部管理津贴信息失败！',
                type: 'red'
            });
        }
    });
    if(ruleType==50){ //区间设定值
        dept_show_flag =0 ;
        $("#calDept_d").show();
        $("#calDept_d  tr:not(:first)").empty("");
        $("#calDept_g").hide();
        $("#deptButton").hide();
        /*拼接参数值*/
        $.each(calDepts, function(i, n) {
            var tbBody = "";
            var deptId =n.id;
            var ruleType = n.ruleType;  //规则（10：区间设定值；11：固定数值；）
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
            tbBody+="<tr><td>"+"<input value='总价值佣金FYC(元)'  disabled='disabled' style=\"width:200px;\"/>"+" </td>"
            tbBody+="<td>"+  "<input value='区间设定值' disabled='disabled' style=\"width:220px;\"/>"+
                "</td><td><input   name='deptMinValue'  type='text' style=\"width:140px;\" value='"+minValue+"' disabled='disabled'/>" +
                "<select  name='deptSelectMinSign' style='width:60px;' disabled='disabled'><option value='"  + n.minSign + "' selected  ='selected'>"+minSign+"</option></select>FYC" +
                "<select  name='deptSelectMaxSign' style='width:60px;' disabled='disabled'><option value='"  + n.maxSign + "' selected  ='selected'>"+maxSign+"</option></select>" +
                "<input   name='deptMaxValue' type='text' style=\"width:140px;\" value='"+maxValue+"' disabled='disabled' /> " +
                "</td><td><input   name='deptAllowance' type='text' style=\"width:150px;\" value='"+allowance+"' disabled='disabled'/></td>"+
                "<td style='display:none' ><input   name='deptId'  value="+deptId+" ></td> " +
                "<td style='display:none' ><input   name='deptSalesOrgId'  value="+$('#show_salesOrgId').val()+"></td> " +
                "<td style='display:none' ><input   name='deptCalOrgId'  value="+$('#show_calOrgId').val()+"></td> " +
                "<td style='display:none' ><input   name='deptIsDefaultCal'  value="+$('#show_isDefaultCal').val()+"></td> " +
                "<td><a href='#'  rel='external nofollow' class='removeclass'><input  type='button' value='删除' onclick='delDeptRow(\"" +deptId+ "\",\""+$('#show_salesOrgId').val()+"\",\""+$('#show_calOrgId').val()+"\",\""+$('#show_isDefaultCal').val()+"\");' disabled='disabled' ></a></td>";
            tbBody+="</tr>"
            $("#calDept_d").append(tbBody);

        });
    }
    if(ruleType==51){//固定数值
        dept_show_flag =1 ;
        $("#calDept_g").show();
        $("#calDept_g  tr:not(:first)").empty("");
        $("#calDept_d").hide();
        $("#deptButton").hide();
        /*拼接参数值*/
        $.each(calDepts, function(i, n) {
            var tbBody1 = ""; //直辖处
            var tbBody0 = ""; //非直辖处
            var deptId =n.id;
            var allowance = accMul(n.allowanceRatio, 100); //津贴比例
            var extraRatio = accMul(n.extraRatio, 100); //津贴比例
            if("1"==deptIncludeDirectlyGroupFlag){
                tbBody1+="<tr><td>"+"<input value='总价值佣金FYC(元)' style='text-align:center;width:200px' disabled='disabled' />"+" </td>"
                tbBody1+="<td><input  value='固定数值' disabled='disabled' style='text-align:center;width:220px'/>" +
                    "<td><input         value='直辖处' disabled='disabled' style='text-align:center;width:300px'/></td>"+
                    "<td><input type='text'  name='deptExtraRatio' value='"+extraRatio+"' disabled='disabled' style='text-align:center;width:300px'/></td>"+
                    "<td style='display:none' ><input   name='deptIncludeFlag'  value="+deptIncludeDirectlyGroupFlag+"></td> " ;
                tbBody1+="</tr>"
            }

            tbBody0+="<tr><td>"+"<input value='总价值佣金FYC(元)' style='text-align:center;width:200px' disabled='disabled' />"+" </td>"
            tbBody0+="<td><input  value='固定数值' disabled='disabled' style='text-align:center;width:220px'/>" +
                "<td><input       value='非直辖处' disabled='disabled' style='text-align:center;width:300px'/></td>"+
                "<td><input type='text'  name='deptAllowance' value='"+allowance+"' disabled='disabled' style='text-align:center;width:300px'/></td>"+
                "<td style='display:none' ><input   name='deptId'  value="+deptId+" ></td> " +
                "<td style='display:none' ><input   name='deptSalesOrgId'  value="+$('#show_salesOrgId').val()+"></td> " +
                "<td style='display:none' ><input   name='deptCalOrgId'  value="+$('#show_calOrgId').val()+"></td> " +
                "<td style='display:none' ><input   name='deptIsDefaultCal'  value="+$('#show_isDefaultCal').val()+"></td> " ;
            tbBody0+="</tr>"
            $("#calDept_g").append(tbBody1+tbBody0);
        });
    }

}
//删除行操作
function delDeptRow(deptId ,show_salesOrgId ,show_calOrgId,show_isDefaultCal) {
    //组织机构主键:show_salesOrgId ;      show_calOrgId  默认基本法总/分公司机构id

    var mymessage = confirm("确认删除直辖部参数吗？");
    if (mymessage == true) {
        /* 查询  */
        $.ajax({
            url : "calDept/updateDeptState",
            type : "post",
            dataType : "json",
            data : {
                id : deptId ,
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
                    showcalDept(cal_dept_staff_ruleType,$('#show_salesOrgId').val(),$('#show_isDefaultCal').val(),$('#show_calOrgId').val());
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
function deptUpdate(){

    if(dept_show_flag==0){
        $("#calDept_d input[type='text']").attr("disabled",false);
        $("#calDept_d input[type='button']").attr("disabled",false);
        $("#calDept_d select[name ='deptSelectMinSign']").attr("disabled",false);
        $("#calDept_d select[name ='deptSelectMaxSign']").attr("disabled",false);

        // 下拉框赋值
       // $("#calDept_d select[name='deptSelectMinSign']").append(deptOptionsminSign);
       // $("#calDept_d select[name='deptSelectMaxSign']").append(deptOptionsmaxSign);

        $("#calDept_d select[name='deptSelectMinSign']").each(function () {
            var minV = $(this).val();
            $(this).html(deptOptionsminSign);
            $(this).find("option[value='"+minV+"']").attr("selected",true);
        })
        $("#calDept_d select[name='deptSelectMaxSign']").each(function () {
            var maxV = $(this).val();
            $(this).html(deptOptionsmaxSign);
            $(this).find("option[value='"+maxV+"']").attr("selected",true);
        })

    }
    if(dept_show_flag==1){
        $("#calDept_g input[type='text']").attr("disabled",false);
    }

    //修改按钮展示
    $("#deptButton").show();



}

// 添加按钮
function deptAdd() {
    if(dept_show_flag==0){
        var tbBody = "";
    tbBody+="<tr><td><input value='总价值佣金FYC(元)'  disabled='disabled' style=\"width:200px;\"/>"+" </td>";
    tbBody+="<td><input value='区间设定值' disabled='disabled' style=\"width:220px;\"/>"+
            "</td><td><input    name='deptMinValue'     type='text' style=\"width:140px;\" value='' />" +
            "<select  name='deptSelectMinSign' style='width:60px;' >"+deptOptionsminSign+"</select>FYC" +
            "<select  name='deptSelectMaxSign' style='width:60px;' >"+deptOptionsmaxSign+"</select>" +
            "<input   name='deptMaxValue'      type='text' style=\"width:140px;\" value=''  /> " +
            "</td><td><input    name='deptAllowance'     type='text' style=\"width:150px;\" value='' /></td>"+
            "<td style='display:none' ><input  name='deptIsDefaultCal'  value="+$('#show_isDefaultCal').val()+"></td> " +
            "<td style='display:none' ><input   name='deptCalOrgId'  value="+$('#show_calOrgId').val()+"></td> " +
            "<td style='display:none' ><input  name='deptSalesOrgId'    value="+$('#show_salesOrgId').val()+" ></td> " ;
        /* "<td><a href='#'  rel='external nofollow' class='removeclass'><input  type='button' value='删除' onclick='delIncreaseRow("+increseid+");' disabled='disabled' ></a></td>";*/
        tbBody+="</tr>";
        $("#calDept_d").append(tbBody);
    }
    if(dept_show_flag==1){
        $.alert({
            title: '提示信息！',
            content: '固定值不能添加!',
            type: 'blue'
        });
    }
}

// 取消操作
function deptCancel(){
    //还原初始操作
    $("#deptButton").hide();
    showcalDept(cal_dept_staff_ruleType,$('#show_salesOrgId').val(),$('#show_isDefaultCal').val(),$('#show_calOrgId').val());

}

//保存按钮
function deptCommit() {
    //校验参数
    var regFlag =deptReg();
    if(regFlag==false){
        return false;
    }

    if(dept_show_flag==0){ // 区间值

        $.ajax({
            url:'calDept/qjCommit',
            type:'post',
            dataType:'json',
            data:$("#addFormDeptd").serialize(),
            success:function(data){
                if(data.messageCode == "200"){
                    $.alert({
                        title: '提示信息！',
                        content: '添加修改成功!',
                        type: 'blue'
                    });
                    $("#show_isDefaultCal").val("1");
                    teokAll($("#show_salesOrgId").val(),$("#name_salesOrgId").val(),"1",$("#show_calOrgId").val(),"1");
                    showcalDept(cal_dept_staff_ruleType,$('#show_salesOrgId').val(),$('#show_isDefaultCal').val(),$('#show_calOrgId').val());
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
    if(dept_show_flag==1){

        $.ajax({
            url:'calDept/deptGDCommit',
            type:'post',
            dataType:'json',
            data:$("#addFormDeptg").serialize(),
            success:function(data){
                if(data.messageCode == "200"){
                    $.alert({
                        title: '提示信息！',
                        content: '添加修改成功!',
                        type: 'blue'
                    });
                    $("#show_isDefaultCal").val("1");
                    teokAll($("#show_salesOrgId").val(),$("#name_salesOrgId").val(),"1",$("#show_calOrgId").val(),"1");
                    showcalDept(cal_dept_staff_ruleType,$('#show_salesOrgId').val(),$('#show_isDefaultCal').val(),$('#show_calOrgId').val());
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
function deptReg(){
    var regFlag =true ;
    var ysRex = /^(0|[1-9][0-9]*)+(\.\d{0,2})?$/; //保留两位小数
    var zsReg = /(^[1-9]([0-9]*)$|^[0-9]$)/;//正整数
    if(dept_show_flag==0) { // 区间值
        $("#calDept_d input[name='deptMinValue']").each(function () {
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
        $("#calDept_d input[name='deptMaxValue']").each(function () {
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
        $("#calDept_d input[name='deptAllowance']").each(function () {
            var allowanceValue = $(this).val();
            if(!ysRex.test(allowanceValue)){
                regFlag=false;
                $.alert({
                    title: '提示信息！',
                    content: '直辖部管理津贴比例请输入正数，可以保留两位小数！',
                    type: 'red'
                });
                return false ;
            }
        })
    }

    // 固定值
    if(dept_show_flag==1){

        $("#calDept_g input[name='deptAllowance']").each(function () {
            var allowanceValue_g = $(this).val();
            if(!ysRex.test(allowanceValue_g)){
                regFlag=false;
                $.alert({
                    title: '提示信息！',
                    content: '直辖部(非直辖)津贴比例请输入正数，可以保留两位小数！',
                    type: 'red'
                });
                return false ;
            }
        });
        //当有额外津贴时
        if("1"==deptIncludeDirectlyGroupFlag){
            $("#calDept_g input[name='deptExtraRatio']").each(function () {
                var deptExtraRatio = $(this).val();
                if(!ysRex.test(deptExtraRatio)){
                    regFlag=false;
                    $.alert({
                        title: '提示信息！',
                        content: '直辖部(直辖)津贴比例请输入正数，可以保留两位小数！',
                        type: 'red'
                    });
                    return false ;
                }
            });

        }


    }

    return regFlag;

}



