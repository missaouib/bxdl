<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/include/core.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>投保单录入</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Cache" content="no-cache">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="${path}/css/system/role/role.css">
    <script src="${path}/js/system/comm.js" charset="UTF-8" type="text/javascript"></script>
    <script src="${path}/static/js/bootstrap.js"></script>
    <script src="${path}/static/js/jquery-form.js"></script>
    <script type="text/javascript">
        $(function () {
            InsuranceDep.formValidator();
            var policy_holder_marital_status = '${iph.maritalStatus}'
            var insured_marital_status = '${pip.maritalStatus}'
            getSysDictValHX('policy_holder_marital_status','MARITAL_STATUS（zh）',policy_holder_marital_status)
            getSysDictValHX('insured_marital_status','MARITAL_STATUS（zh）',insured_marital_status)

         /*    $("#policy_holder_marital_status").val(policy_holder_marital_status).trigger("change");
             $("#insured_marital_status").val(insured_marital_status).trigger("change");*/
            var dv = '${ipp.SALES_ORG_ID}'
            var teame = '${ipp.SALES_TEAM_ID}'
            commSalesOrgsHX("order_taking_mechanism",dv);
            $("#order_taking_mechanism").on(
                "change",function(){
                    commSalesTeamsHX("sales_team",$(this).find("option:selected").val(),teame);

                }
            )

            var inforList = JSON.parse('${inforJson}')
            for (var i=0;i<inforList.length;i++){
                var main = inforList[i].principal_deputy_sign
                var  mainText="其他"
                if(main == 0){
                    mainText="主险"
                }   if(main == 1){
                    mainText="附险"
                }
                var peri = inforList[i].PERIOD_OF_INSURANCE_SIGN
                var periText ="其他"
                if(peri == 0){
                    periText="短期"
                }   if(peri == 1){
                    periText="终身"
                } if(peri == 2){
                    periText="n年"
                } if(peri == 3){
                    periText="1年"
                }
                var payMode = inforList[i].payment_method
                var payModeText ="其他"
                if(payMode == 0){
                    payModeText =  "年交" //下拉框序言的循环数据
                }else if(payMode == 1){
                    payModeText =  "半年交" //下拉框序言的循环数据
                }else if(payMode == 2){
                    payModeText = "季交" //下拉框序言的循环数据
                }else if(payMode == 3){
                    payModeText ="月交" //下拉框序言的循环数据
                }else if(payMode == 4){
                    payModeText =  "趸交" //下拉框序言的循环数据
                }else if(payMode == 5){
                    payModeText = "三年交" //下拉框序言的循环数据
                }
                var rene = inforList[i].payment_period_sign
                var reneText ="其他"
                if(rene == 0){
                    reneText="年缴"
                }   if(rene == 1){
                    reneText="N年"
                }
                var insurance_type = inforList[i].insurance_type//险种类别
                $("#bxcp").append("<tr>" +
                    "<td>" + inforList[i].PRODUCT_CODE + "<input value='" + inforList[i].PRODUCT_CODE + "' name='a1' type='hidden'></td>" +
                    "<td>" + inforList[i].PRODUCT_NAME + "<input value='" + inforList[i].PRODUCTS_RATIO_ID + "' name='a2' type='hidden'>" + "</td>" +
                    "<td>" + mainText + "<input value='" + inforList[i].principal_deputy_sign + "' name='a11' type='hidden'></td>"+
                    "<td>" + inforList[i].insured_amount + "<input value='" + inforList[i].insured_amount + "' name='a3' type='hidden'></td>" +
                    "<td>" + inforList[i].premium + "<input value='" + inforList[i].premium + "' name='a4' type='hidden'><input value='" + inforList[i].size + "' name='a5' type='hidden'></td>" +
                    "<td>"+periText+"<input value='" + inforList[i].PERIOD_OF_INSURANCE_SIGN + "' name='a9' type='hidden'></td>"+
                    "<td>" +payModeText+ "<input value='" + inforList[i].payment_method + "' name='a6' type='hidden'></td>" +
                    "<td>" + reneText + "<input value='" + inforList[i].payment_period_sign + "' name='a7' type='hidden'><input value='" + inforList[i].payment + "' name='a8' type='hidden'><input value='" + inforList[i].period_of_insurance + "' name='a10' type='hidden'><input value='" + insurance_type + "' name='a12' type='hidden'></td>" + "" +

                    "<td>删除</td></tr>")
            }
            var ipaJsonList = JSON.parse('${ipaJson}')
            var im="";
            for(var i=0;i<'${ipasize}';i++){
                var id = ipaJsonList[i].attId
                var attName = ipaJsonList[i].attName
                var attAdd = ipaJsonList[i].attAdd
                im+="<tr>" +//att_add
                    "<td>" + i + "<input value='"+id+"' name='ipaId' type='hidden'> </td>" +
                    "<td>" + attName + "<input value='" +attName + "' name='fileName' type='hidden'><input value='" +attAdd + "' name='fastPath' type='hidden'></td>" +
                    "<td onclick='del_tr(this)'>" + "删除" + "</td></tr>";
            }
            $("#images").append(im)


            $("#adddiv").modal('hide');
            //加载页面异步填充下拉框等
            var card=''
            var sex=''
            $.ajax({
                url: "systemDict/getParamsData",
                type: "post",
                dataType: "json",
                async: false,
                success: function (obj){
                    var systemCard = obj.data.cardType;
                     card = "<option value=''>请选择证件类型</option>";
                    $.each(systemCard, function (key, value) {
                        card += "<option value='" + value.dict_code + "'>" + value.dict_name //下拉框序言的循环数据
                            + "</option>";
                    });
                    var systemSex = obj.data.sex;
                     sex = "<option value=''>请选择性别</option>";
                    $.each(systemSex, function (key, value) {
                        sex += "<option value='" + value.dict_code + "'>" + value.dict_name //下拉框序言的循环数据
                            + "</option>";
                    });
                    var relationship = "";
                    var systemRelationship = obj.data.relationship;
                    $.each(systemRelationship, function (key, value) {
                        relationship += "<input type=\"radio\" value='" + value.dict_code + "' name=\"insured_relationship\"><span style=\"color: firebrick\">" + value.dict_name + "</span>";
                    });
                    $("#cardTest").val(card)
                    $("#sexTest").val(sex)
                    $(".tab-pane.active #relationshipRadios").empty();
                    $(".tab-pane.active #relationshipRadios").append(relationship);//单选框
                    var cheackCard = '${iph.cardType}'
                    getSysDictValHX('card_type','CARD',cheackCard)
                    var cheackInsuredCard = '${pip.cardType}'
                    getSysDictValHX('insured_card_type','CARD',cheackInsuredCard)
                    var cheackSex = '${iph.sex}'
                    getSysDictValHX('policy_holder_sex','SEX',cheackSex)
                    var chesex='${pip.sex}'
                    getSysDictValHX('insured_sex','SEX',chesex)
                    var cheackPayType = '${iph.payType}'
                    getSysDictValHX('pay_type','PAY_TYPE',cheackPayType)
                    var cheackIsMultipleInsure = '${pip.isMultipleInsure}';
                    getSysDictValHX('multiple_insurance_coverage','YESORNO',cheackIsMultipleInsure)
                    var cheackRelationshipRadios = '${pip.relationship}';
                    $("input:radio[value='" + cheackRelationshipRadios + "']").attr('checked', 'true');
                    var cheackIsInformHealth = '${pip.isInformHealth}';
                    getSysDictValHX('health_report','YESORNO',cheackIsInformHealth)
                    var iphMarital = '${iph.maritalStatus}'
                    var pipMarital = '${pip.maritalStatus}'
                    getSysDictValHX('policy_holder_marital_status','MARITAL_STATUS（zh）',iphMarital)
                    getSysDictValHX('insured_marital_status','MARITAL_STATUS（zh）',pipMarital)
                }
            })
            $.ajax({
                url: "insurance_policy/select_search_params",
                type: "post",
                dataType: "json",
                async: false,
                success: function (obj) {
                    var insuranceCompanys = obj.data.org
                    var h = "<option value=''>请选择保险公司</option>";
                    $.each(insuranceCompanys, function (key, value) {
                        h += "<option value='" + value.insuranceCompanyId + "'>" + value.insuranceCompanyName //下拉框序言的循环数据
                            + "</option>";
                    });
                    var jsonList = JSON.parse('${pppJson}')
                    var k="";
                    for(var i=0;i<'${pppSize}';i++){
                        var pppName = jsonList[i].name
                        var pppCardNo = jsonList[i].cardNo
                        var pppBirthday = jsonList[i].birthday
                        var pppLevel = jsonList[i].level
                        var pppBenefitRatio = jsonList[i].benefitRatio
                        var pppRelationship = jsonList[i].relationship
                        var cardt = $("#cardTest").val()
                        var sext = $("#sexTest").val()
                        k+="<tr>" +
                            "<td><input readonly=readonly type='test' name='b1' value='"+pppName+"'></td>" +
                            "<td><select readonly=readonly style='width: 200px;' name='b2'  class=\"form-control form-control-static\">" + cardt + "</select></td>" +
                            "<td><input readonly=readonly value='"+pppCardNo+"' name='b3' type='test'></td>" +
                            "<td><select readonly=readonly style='width: 200px' name='b4' type='test' class=\"form-control form-control-static\">" + sext + "</select>" + "</td>" +
                            "<td><input value='"+pppBirthday+"' name='b5' readonly=readonly type='date'></td>" +
                            "<td><input value='"+pppLevel+"' name='b6' type='test' readonly=readonly></td>" +
                            "<td><input value='"+pppBenefitRatio+"' name='b7' type='test' readonly=readonly></td>" +
                            "<td><input value='"+pppRelationship+"' name='b8' type='test' readonly=readonly></td>" +
                            "<td><a href='#'>" + "操作" + "</a>" +
                            "</td></tr>>"
                    }
                    $("#beneficiary").append(k)
                    for(var i=0;i<'${pppSize}';i++){
                        var cardType = jsonList[i].cardType
                        var  beneficiarysex =jsonList[i].sex
                        $("[name='b2' ]").eq(i).val(cardType).trigger("change");
                        $("[name='b4' ]").eq(i).val(beneficiarysex).trigger("change");
                    }
                    $("#insuranceCompany").empty();
                    $("#insuranceCompany").append(h);
                    var dv = '${dept.insuranceCompanyId}'
                    $("#insuranceCompany").val(dv).trigger("change");
                },
                error: function (obj) {
                }
            })

            getAreaByPid("0", "sheng");
            InsuranceDep.formValidator();

        });
        function addTables() {

        }
        function image() {
            $("#image").click();
        }

        function closeDlg() {
            $("#adddiv").modal('hide');
            $("#alertFileEntry").modal('hide');
        }

        function delBx() {
            alert(1)
        }

        function upload() {
            var multipart = $("#fileNameEntry").val();
            if (multipart == "" || multipart == null) {
                alert("请先选择文件!");
                return;
            }
            closeDlg()
            $("#loadingModal").modal('show');
            //$('#loadingModal').modal('hide');
            $("#fileFormEntry").ajaxSubmit({
                type: 'POST',
                url: 'insurance_policy/uploadFile',
                dataType: 'json',
                success: function (data) {
                    $('#loadingModal').modal('hide');
                    if (data.code = "200") {
                        changeVal(data)
                    } else {
                        alert("文件上传失败请重新上传")
                    }
                },
                error: function (data) {
                    $('#loadingModal').modal('hide');
                    alert("系统异常")
                }
            })
        }

        function commit() {

            $('#addForm').data('bootstrapValidator').validate();//启用验证
            var flag = $('#addForm').data('bootstrapValidator').isValid()//验证是否通过true/false
            if (!flag){
                return;
            }
            var insured_relationship =  $("input:radio[name='insured_relationship']:checked").val()
            if (insured_relationship == undefined ){
                alert("请选择与投保人关系")
                return;
            }

           /* var beneficiarylen = $("#beneficiary tr").length
            if (beneficiarylen <= 1){
                $.alert({
                    title: '错误提示！',
                    content: '至少添加一条险种受益人',
                    type: 'red'
                })
                return;
            }*/
            var xzxxtablelen = $("#bxcp tr").length
            if (xzxxtablelen <= 1){
                $.alert({
                    title: '错误提示！',
                    content: '至少添加一条险种信息',
                    type: 'red'
                })
                return;
            }

            var data = $("#addForm").serialize();
            $.ajax({
                url: "insurance_policy/add_insurance_policy",
                data: data,
                type: "post",
                dataType: "json",
                success: function (obj) {
                    if (obj.code=="200"){
                        alert("添加成功")
                        commCloseTab('insurancePolicyEntry:list')
                        createAddProcessTab("insurancePolicyNoteOrEntry:list","")
                    } else if (obj.code=="4001") {
                        alert("投保单号重复")
                        $("#myModal").modal('hide');
                    }else if (obj.code=="0001") {
                        alert(obj.error)
                    }
                    else {
                        alert("添加失败")
                    }
                },
                error: function (obj) {
                    alert("系统异常")
                }
            })
        }
        function bxqd() {


            var a1 = $("#a1").val();
            var a2 = $("#a2").val();
            var a2t = $("#a2  option:selected").text();
            var a3 = $("#a3").val();
            var a4 = $("#a4").val();
            var a5 = $("#a5").val();
            var a6 = $("#a6").val();
            var a6t =$("#a6 option:selected").text();;
            var a7 = $("#a7").val();
            var a7t = $("#a7t").val();
            var a8 = $("#a8").val();
            var a9 = $("#a9").val();

            var a9t = $("#a9t").val();
            var a10 = $("#a10").val();
            var a11 = $("#a11").val();
            var a11t = $("#a11t").val();
            var upOrIn = $("#upOrIn").val()
            var a1 = $("#a1").val();
            var a2 = $("#a2").val();
            if(a2 == ''){
                $.alert({
                    title: '错误提示！',
                    content: '请选择产品',
                    type: 'red'
                })
                return;
            }
            var a2t = $("#a2  option:selected").text();
            var a3 = $("#a3").val();
            if(a3 == ''){
                $.alert({
                    title: '错误提示！',
                    content: '请输入保额',
                    type: 'red'
                })
                return;
            }
            var a4 = $("#a4").val();
            if(a4 == ''){
                $.alert({
                    title: '错误提示！',
                    content: '请输入保费',
                    type: 'red'
                })
                return;
            }
            var a5 = $("#a5").val();
            if(a5 == ''){
                $.alert({
                    title: '错误提示！',
                    content: '请输入份数',
                    type: 'red'
                })
                return;
            }
            var a6 = $("#a6").val();
            if(a6 == ''){
                $.alert({
                    title: '错误提示！',
                    content: '请选择缴费方式',
                    type: 'red'
                })
                return;
            }
            if(a8 == ''){
                $.alert({
                    title: '错误提示！',
                    content: '请输入缴费 年期/年龄',
                    type: 'red'
                })
                return;
            }
            if(a10 == ''){
                $.alert({
                    title: '错误提示！',
                    content: '请输入保险期间',
                    type: 'red'
                })
                return;
            }
            $("#bxcp  tr").html('')
            $("#bxcp").append("<tr>" +
                "<td>" + a1 + "<input value='" + a1 + "' name='a1' type='hidden'></td>" +
                "<td>" + a2t + "<input value='" + a2 + "' name='a2' type='hidden'>" + "</td>" +
                "<td>" + a11t + "<input value='" + a11 + "' name='a11' type='hidden'></td>" +
                "<td>" + a3 + "<input value='" + a3 + "' name='a3' type='hidden'></td>" +
                "<td>" + a4 + "<input value='" + a4 + "' name='a4' type='hidden'><input value='" + a5 + "' name='a5' type='hidden'></td>" +
                "<td>"+ a9t + "<input value='" + a9 + "' name='a9' type='hidden'></td>"+
                "<td>" + a6t + "<input value='" + a6 + "' name='a6' type='hidden'></td>" +
                "<td>" + a7t +  "<input value='" + a7 + "' name='a7' type='hidden'><input value='" + a8 + "' name='a8' type='hidden'><input value='" + a10 +"' name='a10' type='hidden'></td>"+
                "<td><a href='#' id='delBx' style=\"color: black\" onclick='del_tr(this)' >删除</a></td></tr>")
            $("#adddiv").modal('hide');
        }
      /*  function bxqd() {
            var a1 = $("#a1").val();
            var a2 = $("#a2").val();
            var a3 = $("#a3").val();
            var a4 = $("#a4").val();
            var a5 = $("#a5").val();
            var a6 = $("#a6").val();
            var a7 = $("#a7").val();
            var a8 = $("#a8").val();
            var a9 = $("#a9").val();
            var a10 = $("#a10").val();
            var a11 = $("#a11").val();
            $("#bxcp").append("<tr>" +
                "<td>" + a1 + "<input value='" + a1 + "' name='a1' type='hidden'></td>" +
                "<td>" + a2 + "<input value='" + a2 + "' name='a2' type='hidden'>" + "</td>" +
                "<td>" + a3 + "<input value='" + a3 + "' name='a3' type='hidden'></td>" +
                "<td>" + a4 + "<input value='" + a4 + "' name='a4' type='hidden'><input value='" + a5 + "' name='a5' type='hidden'></td><td>" + a6 + "" +
                "<input value='" + a6 + "' name='a6' type='hidden'><input value='" + a7 + "' name='a7' type='hidden'></td>" +
                "<td>" + a8 + "<input value='" + a8 + "' name='a8' type='hidden'><input value='" + a9 + "' name='a9' type='hidden'></td><td>" + a10 + "" +
                "<input value='" + a10 + "' name='a10' type='hidden'></td><td>" + a11 + "<input value='" + a11 + "' name='a11' type='hidden'></td>" +
                "<td><a href='#' id='delBx' style=\"color: black\" onclick='del_tr(this)' >删除</a></td></tr>")
            $("#adddiv").modal('hide');
        }*/

        var trSize = 0;

        function addBeneficiary() {
            var k = "<tr><td><input type='test' name='b1'></td><td><select name='b2'  class=\"form-control form-control-static\"<option value=\"0\">居民身份证</option><option value=\"1\">出生证明</option><option value=\"2\">驾驶证</option><option value=\"3\">军官证</option>" +
                "<option value=\"4\">士兵证</option><option value=\"8\">旅行证</option><option value=\"9\">回乡证</option>" +
                "<option value=\"10\">警官证</option><option value=\"12\">户口本</option>" +
                "<option value=\"14\">台胞证</option><option value=\"15\">学生证</option>" +
                "<option value=\"16\">工作证</option><option value=\"17\">无证件</option>" +

                "</select></td><td><input name='b3' type='test'></td><td><select name='b4' type='test'><option>男</option><option>女</option></select>" +
                "</td><td><input name='b5' type='test'></td><td><input name='b6' type='test'></td><td><input name='b7' type='test'></td><td><input name='b8' type='test'></td><td><a href='#'>" + "操作" + "</a>" +
                "</td></tr>>"
            $("#beneficiary").append(k)
        }

        function addInsurance() {
            if ($("#insuranceCompany").val() == ''){
                $.alert({
                    title: '错误提示！',
                    content: '请先选择投保公司',
                    type: 'red'
                })
                return;
            }
            $("#adddiv").modal('show')
        }

        function alertFile() {
            $("#alertFileEntry").modal('show')
        }

        function changeVal(data) {
            var file = $("#fileNameEntry").val();
            var fileName = getFileName(file);

            function getFileName(o) {
                var pos = o.lastIndexOf("\\");
                return o.substring(pos + 1);
            }

            trSize = trSize + 1;
            var ts = $("#images tr").length
            $("#images").append("<tr><td>" + ts + "</td><td>" + fileName + "<input value='" + fileName + "' name='fileName' type='hidden'><input value='" + data.fastPath + "' name='fastPath' type='hidden'></td><td onclick='del_tr(this)'>" + "删除" + "</td></tr>")
        }

        function del_tr(obj) {
            var index = $(obj).parents("tr").index();
            $(obj).parents("tr").remove();

        }
        var InsuranceDep = function () {
            return {
                //添加
                add: function () {
                    document.getElementById("saveButton").setAttribute("disabled", true);
                    if ($("#addForm").data('bootstrapValidator').validate().isValid()) {
                        flag = true;
                        var sheng = $("#sheng").val();
                        var shi = $("#shi").val();
                        var qu = $("#qu").val();
                        if (qu != "") {
                            $("#areaCode").val(qu);
                        } else {
                            if (shi != "") {
                                $("#areaCode").val(shi);
                            } else {
                                $("#areaCode").val(sheng);
                            }
                        }
                        if (flag) {
                            $.ajax({
                                url: 'insurance_dept/add_insurance_dept',
                                dataType: 'json',
                                type: 'post',
                                data: $("#addForm").serialize(),
                                success: function (data) {
                                    if (data) {
                                        $.alert({
                                            title: '提示信息！',
                                            content: '添加成功!',
                                            type: 'blue'
                                        });
                                        document.getElementById("saveButton").removeAttribute("disabled");
                                    } else {
                                        $.alert({
                                            title: '提示信息！',
                                            content: '添加失败！',
                                            type: 'red'
                                        });
                                        document.getElementById("saveButton").removeAttribute("disabled");
                                    }
                                },
                                error: function () {
                                    $.alert({
                                        title: '提示信息！',
                                        content: '请求失败！',
                                        type: 'red'
                                    });
                                }
                            });
                        }
                    } else {
                        document.getElementById("saveButton").removeAttribute("disabled");
                    }
                },

                //表单验证
                formValidator: function () {
                    $("#addForm").bootstrapValidator({
                        fields: {
                            exampleInsName1:{
                                validators:{
                                    notEmpty: {
                                        message: "保单号不能为空"
                                    },
                                    regexp: {
                                        regexp:/^[^ ]+$/,
                                        message: '保单号不能包含空格'
                                    }
                                }
                            },
                            exampleInsName2 :{
                                validators:{
                                    notEmpty: {
                                        message: "保单承保日期不能为空"
                                    }
                                }
                            },
                            exampleInsName3:{
                                validators:{
                                    notEmpty: {
                                        message: "保单生效日期不能为空"
                                    }
                                }
                            },
                            insured_sex:{
                                validators:{
                                    notEmpty: {
                                        message: "性别不能为空"
                                    }
                                }
                            },
                            insured_birthday:{
                                validators:{
                                    notEmpty: {
                                        message: "被保人出生日期不能为空"
                                    }
                                }
                            },
                            insured_annual_income:{
                                validators:{
                                    notEmpty: {
                                        message: "年收入不能为空"
                                    }
                                }
                            },
                            insured_permanent_address:{
                                validators:{
                                    notEmpty: {
                                        message: "被保人家庭地址不能为空"
                                    }
                                }
                            },
                            multiple_insurance_coverage:{
                                validators:{
                                    notEmpty: {
                                        message: "是否多次投保不能为空"
                                    }
                                }
                            },
                            health_report:{
                                validators:{
                                    notEmpty: {
                                        message: "是否有健康须知不能为空"
                                    }
                                }
                            },
                            exampleInputName1:{
                                validators:{
                                    notEmpty: {
                                        message: "投保单号不能为空"
                                    },
                                    regexp: {
                                        regexp:/^[^ ]+$/,
                                        message: '投保单号不能包含空格'
                                    }
                                }
                            },
                            policy_holder_sex:{
                                validators:{
                                    notEmpty: {
                                        message: "性别不能为空"
                                    }
                                }
                            },
                            policy_holder_annual_income:{
                                validators:{
                                    notEmpty: {
                                        message: "年收入不能为空"
                                    }
                                }
                            },
                            policy_holder_permanent_address:{
                                validators:{
                                    notEmpty: {
                                        message: "投保人常住住址不能为空"
                                    }
                                }
                            },
                            policy_holder_birthday:{
                                validators:{
                                    notEmpty: {
                                        message: "投保人出生日期不能为空"
                                    }
                                }
                            },
                            exampleInputName3:{
                                validators:{
                                    notEmpty: {
                                        message: "总保费号不能为空"
                                    },
                                    regexp: {
                                        regexp: /^\d+(\.\d+)?$/,
                                        message: '请输入数字'
                                    }
                                }
                            },
                            exampleInputName4:{
                                validators:{
                                    notEmpty: {
                                        message: "投保日期不能为空"
                                    },

                                }
                            },
                            card_no:{
                                validators:{
                                    notEmpty: {
                                        message: "投保人证件号码不能为空"
                                    },






                                }
                            },
                            card_type:{
                                validators:{
                                    notEmpty: {
                                        message: "投保人证件类型不能为空"
                                    },

                                }
                            },
                            policy_holder_name:{
                                validators:{
                                    notEmpty: {
                                        message: "投保人姓名不能为空"
                                    },

                                }
                            },
                            policy_holder_tel:{
                                validators:{
                                    notEmpty: {
                                        message: "投保人移动电话不能为空"
                                    },
                                    regexp:{
                                        regexp:/^1[3456789]\d{9}$/,
                                        message:"请输入正确手机号格式"
                                    }
                                }
                            },
                            insured_card_no:{
                                validators:{
                                    notEmpty: {
                                        message: "投保人证件号码不能为空"
                                    },

                                }
                            },
                            insured_card_type:{
                                validators:{
                                    notEmpty: {
                                        message: "投保人证件类型不能为空"
                                    },

                                }
                            },
                            insured_name:{
                                validators:{
                                    notEmpty: {
                                        message: "投保人姓名不能为空"
                                    },

                                }
                            },
                            insured_tel:{
                                validators:{
                                    notEmpty: {
                                        message: "投保人移动电话不能为空"
                                    },
                                    regexp:{
                                        regexp:/^1[3456789]\d{9}$/,
                                        message:"请输入正确手机号格式"
                                    }
                                }
                            },
                            emp_no:{
                                validators:{
                                    notEmpty: {
                                        message: "人员编号不能为空"
                                    },
                                    regexp:{
                                        regexp:/^\d{10}$/,
                                        message:"员工编号为10位数"
                                    }
                                }
                            },

                            insuranceCompany: {
                                validators: {
                                    notEmpty: {
                                        message: "保险公司名称不能为空"
                                    },
                                    stringLength: {
                                        max: 128,
                                        message: '不能超过20个字符长度'
                                    },
                                }
                            }
                        }
                    });
                }

            }
        }();
        function clo() {
            commCloseTab('insurancePolicyEntry:list')
            createAddProcessTab("insurancePolicyNoteOrEntry:list","")
        }
    </script>
</head>
<body>
<div class="container" style="width: 2000px">
    <form class="form-horizontal" id="addForm" method="post" enctype="application/x-www-form-urlencoded">
        <input type="hidden" id="updateType" name="updateType" value="add"></input><%--控制执行修改或者添加--%>

        <input value="${pip.policyId}" name="CORRESPONDING" id="CORRESPONDING" type="hidden">
        <div id="toolbar_base" class="btn-toolbar" style="overflow:scroll;background: slategray;width: 1800px;">
            <span style="color: cornsilk">投保单基本信息</span>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">保单号*</label>
            <div class="col-md-3 ">

                <input  type="text" class="form-control form-control-static" id="exampleInsName1"
                       name="exampleInsName1" placeholder="保单号" >
            </div>

        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">保单承保日期*</label>
            <div class="col-md-3 ">

                <input type="date" class="form-control form-control-static" id="exampleInsName2"
                       name="exampleInsName2" placeholder="投保单号" >
            </div>
            <label class="col-md-2 control-label">保单生效日期*</label>
            <div class="col-md-3 ">
                <input class="form-control form-control-static" id="exampleInsName3" type="date" name="exampleInsName3"/>

            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">投保单号*</label>
            <div class="col-md-3 ">

                <input readonly="readonly" type="text" class="form-control form-control-static" id="exampleInputName1"
                       name="exampleInputName1" placeholder="投保单号" value="${pip.policyId}">
            </div>
            <label class="col-md-2 control-label">投保公司*</label>
            <div class="col-md-3 ">
                <select class="form-control form-control-static" id="insuranceCompany" name="insuranceCompany">
                    <option value="">-请选择-</option>
                </select>
            </div>
        </div>
        <div class="form-group" style="margin-top:20px;">
            <label class="col-md-2 control-label">总保费（元）*</label>
            <div class="col-md-3 ">

                <input type="text" value="${ipp.total_premium}" class="form-control form-control-static" name="exampleInputName3"
                       id="exampleInputName3" placeholder="总保费（元）" value="${ipp.totalPremium}">
            </div>
            <label class="col-md-2 control-label">投保日期*</label>
            <div class="col-md-3 ">

                <input value="${ipp.propose_date}" type="date" class="form-control form-control-static"
                       name="exampleInputName4"
                       id="exampleInputName4" placeholder="投保单号">
            </div>
        </div>
        <div id="toolbar_tbr" class="btn-toolbar" style="overflow:scroll;background: slategray;width: 1800px;">
            <span style="color: cornsilk">投保人资料</span>
        </div>
        <div class="form-group" style="margin-top:20px;">
            <label class="col-md-2 control-label">证件号码*</label>
            <div class="col-md-3 ">
                <input value="${iph.cardNo}" type="text" class="form-control form-control-static" name="card_no"
                       id="card_no"
                       placeholder="证件号码">
            </div>
            <label class="col-md-2 control-label">证件类型*</label>
            <div class="col-md-3 ">
                <select id="card_type" name="card_type" class="form-control form-control-static">

                </select>
            </div>
        </div>
        <div class="form-group" style="margin-top:20px;">
            <label class="col-md-2 control-label">投保人姓名*</label>
            <div class="col-md-3 ">
                <input value="${iph.name}" type="text" class="form-control form-control-static"
                       name="policy_holder_name"
                       id="policy_holder_name" placeholder="投保人姓名">
            </div>
            <label class="col-md-2 control-label">移动电话*</label>
            <div class="col-md-3 ">
                <input value="${iph.mobile}" type="text" class="form-control form-control-static"
                       name="policy_holder_tel"
                       id="policy_holder_tel" placeholder="移动电话">
            </div>
        </div>
        <div class="form-group" style="margin-top:20px;">
            <label class="col-md-2 control-label">性别*</label>
            <div class="col-md-3 ">
                <select class="form-control form-control-static" name="policy_holder_sex"
                        id="policy_holder_sex"></select>
            </div>
            <label class="col-md-2 control-label">投保人出生日期*</label>
            <div class="col-md-3 ">
                <input value="${iph.birthday}" type="date" class="form-control form-control-static"
                       name="policy_holder_birthday"
                       id="policy_holder_birthday" placeholder="投保人出生日期">
            </div>
        </div>
        <div class="form-group" style="margin-top:20px;">
            <label class="col-md-2 control-label">年收入*</label>
            <div class="col-md-3 ">
                <input value="${iph.annualIncome}" type="text" class="form-control form-control-static"
                       name="policy_holder_annual_income"
                       id="policy_holder_annual_income" placeholder="年收入">
            </div>
            <label class="col-md-2 control-label">投保人常住住址*</label>
            <div class="col-md-3 ">
                <input value="${iph.homeAddress}" type="text" class="form-control form-control-static"
                       name="policy_holder_permanent_address"
                       id="policy_holder_permanent_address" placeholder="投保人常住住址">
            </div>
        </div>
        <div class="form-group" style="margin-top:20px;">
            <label class="col-md-2 control-label">付款方式</label>
            <div class="col-md-3 ">
                <select name="pay_type" id="pay_type" class="form-control form-control-static">
                </select>
            </div>
            <label class="col-md-2 control-label">开户人姓名</label>
            <div class="col-md-3 ">
                <input value="${iph.accountHolder}" type="text" class="form-control form-control-static"
                       name="policy_holder_opener_name"
                       id="policy_holder_opener_name" placeholder="开户人姓名">
            </div>
        </div>
        <div class="form-group" style="margin-top:20px;">
            <label class="col-md-2 control-label">银行名称</label>
            <div class="col-md-3 ">
                <input value="${iph.bankName}" type="text" class="form-control form-control-static"
                       name="policy_holder_bank_name"
                       id="policy_holder_bank_name" placeholder="银行名称">
            </div>
            <label class="col-md-2 control-label">银行账号</label>
            <div class="col-md-3 ">
                <input value="${iph.bankAccount}" type="text" class="form-control form-control-static"
                       name="policy_holder_bank_account"
                       id="policy_holder_bank_account" placeholder="银行账号">
            </div>
        </div>
        <div class="form-group" style="margin-top:20px;">
            <label class="col-md-2 control-label">学历</label>
            <div class="col-md-3 ">
                <input value="${iph.eduBackground}" type="text" class="form-control form-control-static"
                       name="policy_holder_education"
                       id="policy_holder_education" placeholder="学历">
            </div>
            <label class="col-md-2 control-label">婚姻状态</label>
            <div class="col-md-3 ">
                <select   class="form-control form-control-static"
                       name="policy_holder_marital_status"
                         id="policy_holder_marital_status" placeholder="婚姻状态"></select>
            </div>
        </div>
        <div class="form-group" style="margin-top:20px;">
            <label class="col-md-2 control-label">投保人公司地址</label>
            <div class="col-md-3 ">
                <input value="${iph.companyAddress}" type="text" class="form-control form-control-static"
                       name="policy_holder_company_address"
                       id="policy_holder_company_address" placeholder="投保人公司地址">
            </div>
            <label class="col-md-2 control-label">办公/家庭电话</label>
            <div class="col-md-3 ">
                <input value="${iph.telephone}" type="text" class="form-control form-control-static"
                       name="policy_holder_home_phone"
                       id="policy_holder_home_phone" placeholder="办公/家庭电话">
            </div>
        </div>
        <div class="form-group" style="margin-top:20px;">
            <label class="col-md-2 control-label">电子邮箱</label>
            <div class="col-md-3 ">
                <input value="${iph.email}" type="text" class="form-control form-control-static"
                       name="policy_holder_email"
                       id="policy_holder_email" placeholder="电子邮箱">
            </div>
            <label class="col-md-2 control-label">家庭住址邮编</label>
            <div class="col-md-3 ">
                <input value="${iph.homePostalCode}" type="text" class="form-control form-control-static"
                       name="policy_holder_zip_code"
                       id="policy_holder_zip_code" placeholder="家庭住址邮编">
            </div>
        </div>
        <div class="form-group" style="margin-top:20px;">
            <label class="col-md-2 control-label">户籍地址*</label>
            <div class="col-md-3 ">
                <input value="${iph.domicileAddress}" type="text" class="form-control form-control-static"
                       name="policy_holder_permanent_address_add"
                       id="policy_holder_permanent_address_add" placeholder="户籍地址">
            </div>
            <label class="col-md-2 control-label">负债和贷款（万元）</label>
            <div class="col-md-3 ">
                <input value="${iph.debts}" type="text" class="form-control form-control-static"
                       name="policy_holder_liabilities"
                       id="policy_holder_liabilities" placeholder="负债和贷款（万元）">
            </div>
        </div>
        <div class="form-group" style="margin-top:20px;">
            <label class="col-md-2 control-label">投保人执业编号*</label>
            <div class="col-md-3 ">
                <input value="${iph.occupationCode}" type="text" class="form-control form-control-static"
                       name="policy_holder_occupation_code"
                       id="policy_holder_occupation_code" placeholder="投保人执业编号">
            </div>
            <label class="col-md-2 control-label">投保人职业*</label>
            <div class="col-md-3 ">
                <input value="${iph.occupation}" type="text" class="form-control form-control-static"
                       name="policy_holder_occupation"
                       id="policy_holder_occupation" placeholder="投保人职业">
            </div>
        </div>
        <div id="toolbar_gx" class="btn-toolbar" style="overflow:scroll;background: slategray;width: 1800px;">
            <span style="color: cornsilk">被投保人资料</span>
            <span style="color: cornsilk">与投保人关系</span>
            <span id="relationshipRadios"></span>
        </div>
        <div class="form-group" style="margin-top:20px;">
            <label class="col-md-2 control-label">证件号码*</label>
            <div class="col-md-3 ">
                <input value="${pip.cardNo}" type="text" class="form-control form-control-static" name="insured_card_no" id="insured_card_no"
                       placeholder="证件号码">
            </div>
            <label class="col-md-2 control-label">证件类型*</label>
            <div class="col-md-3 ">
                <select name="insured_card_type" id="insured_card_type" class="form-control form-control-static">
                </select>
            </div>
        </div>
        <div class="form-group" style="margin-top:20px;">
            <label class="col-md-2 control-label">被保人姓名*</label>
            <div class="col-md-3 ">
                <input value="${pip.name}" type="text" class="form-control form-control-static" name="insured_name" id="insured_name"
                       placeholder="被保人姓名">
            </div>
            <label class="col-md-2 control-label">移动电话*</label>
            <div class="col-md-3 ">
                <input value="${pip.mobile}" type="text" class="form-control form-control-static" name="insured_tel" id="insured_tel"
                       placeholder="移动电话">
            </div>
        </div>
        <div class="form-group" style="margin-top:20px;">
            <label class="col-md-2 control-label">性别*</label>
            <div class="col-md-3 ">
                <select type="text" class="form-control form-control-static" name="insured_sex" id="insured_sex"
                        placeholder="性别"></select>
            </div>
            <label class="col-md-2 control-label">投保人出生日期*</label>
            <div class="col-md-3 ">
                <input value="${pip.birthday}" type="date" class="form-control form-control-static" name="insured_birthday"
                       id="insured_birthday" placeholder="投保人出生日期">
            </div>
        </div>
        <div class="form-group " style="margin-top:20px;">
            <label class="col-md-2 control-label">年收入*</label>
            <div class="col-md-3" style="margin-top:20px;">
                <input value="${pip.annualIncome}" type="text" class="form-control form-control-static" name="insured_annual_income"
                       id="insured_annual_income" placeholder="年收入">
            </div>
            <label class="col-md-2 control-label">投保人常住住址*</label>
            <div class="col-md-3 ">
                <input value="${pip.homeAddress}" type="text" class="form-control form-control-static" name="insured_permanent_address"
                       id="insured_permanent_address" placeholder="投保人常住住址">
            </div>
        </div>

        <div class="form-group" style="margin-top:20px;">
            <label class="col-md-2 control-label">是否多次投保*</label>
            <div class="col-md-3 ">
                <select name="multiple_insurance_coverage" id="multiple_insurance_coverage"
                        class="form-control form-control-static">

                </select>
            </div>
            <label class="col-md-2 control-label">是否有健康告知*</label>
            <div class="col-md-3 ">
                <select name="health_report" id="health_report" class="form-control form-control-static">

                </select>
            </div>
        </div>
        <div class="form-group" style="margin-top:20px;">
            <label class="col-md-2 control-label">学历</label>
            <div class="col-md-3 ">
                <input value="${pip.eduBackground}" type="text" class="form-control form-control-static" name="insured_education"
                       id="insured_education" placeholder="学历">
            </div>
            <label class="col-md-2 control-label">婚姻状态</label>
            <div class="col-md-3 ">
                <select class="form-control form-control-static" name="insured_marital_status"
                        id="insured_marital_status" placeholder="婚姻状态"></select>
            </div>
        </div>

        <div class="form-group" style="margin-top:20px;">
            <label class="col-md-2 control-label">被保人公司地址</label>
            <div class="col-md-3 ">
                <input value="${pip.companyAddress}" type="text" class="form-control form-control-static" name="insured_company_address"
                       id="insured_company_address" placeholder="投保人公司地址">
            </div>
            <label class="col-md-2 control-label">办公/家庭电话</label>
            <div class="col-md-3 ">
                <input value="${pip.telephone}" type="text" class="form-control form-control-static" name="insured_home_phone"
                       id="insured_home_phone" placeholder="办公/家庭电话">
            </div>
        </div>
        <div class="form-group" style="margin-top:20px;">
            <label class="col-md-2 control-label">电子邮箱</label>
            <div class="col-md-3 ">
                <input value="${pip.email}" type="text" class="form-control form-control-static" name="insured_email" id="insured_email"
                       placeholder="电子邮箱">
            </div>
            <label class="col-md-2 control-label">家庭住址邮编</label>
            <div class="col-md-3 ">
                <input value="${pip.homePostalCode}" type="text" class="form-control form-control-static" name="insured_zip_code"
                       id="insured_zip_code" placeholder="家庭住址邮编">
            </div>
        </div>
        <div class="form-group" style="margin-top:20px;">
            <label class="col-md-2 control-label">户籍地址*</label>
            <div class="col-md-3 ">
                <input value="${pip.domicileAddress}" type="text" class="form-control form-control-static" name="insured_permanent_address_add"
                       id="insured_permanent_address_add" placeholder="户籍地址">
            </div>
            <label class="col-md-2 control-label">负债和贷款（万元）</label>
            <div class="col-md-3 ">
                <input value="${pip.debts}" type="text" class="form-control form-control-static" name="insured_liabilities"
                       id="insured_liabilities" placeholder="负债和贷款（万元）">
            </div>
        </div>
        <div class="form-group" style="margin-top:20px;">
            <label class="col-md-2 control-label">投保人执业编号*</label>
            <div class="col-md-3 ">
                <input value="${pip.occupationCode}" type="text" class="form-control form-control-static" name="insured_occupation_code"
                       id="insured_occupation_code" placeholder="投保人执业编号">
            </div>
            <label class="col-md-2 control-label">投保人职业*</label>
            <div class="col-md-3 ">
                <input value="${pip.occupation}" type="text" class="form-control form-control-static" name="insured_occupation"
                       id="insured_occupation" placeholder="投保人职业">
            </div>
        </div>
        <div id="toolbar_dlr" class="btn-toolbar" style="overflow:scroll;background: slategray;width: 1800px;">
            <span style="color: cornsilk">代理人信息</span>
        </div>
    <%--    <div class="form-group" style="margin-top:20px;">
            <label class="col-md-2 control-label">合作渠道代码</label>
            <div class="col-md-3 ">
                <input type="text" class="form-control form-control-static" name="channels_of_cooperation"
                       id="channels_of_cooperation" placeholder="户做渠道代码">
            </div>

        </div>--%>
        <div class="form-group" style="margin-top:20px;">
            <label class="col-md-2 control-label">人员工号*</label>
            <div class="col-md-3 ">
                <input value="${ipp.INSURANCE_SALER_NO}" type="text" class="form-control form-control-static" name="emp_no" id="emp_no"
                       placeholder="人员工号">
            </div>
            <label class="col-md-2 control-label">代理人姓名*</label>
            <div class="col-md-3 ">
                <input value="${ipp.INSURANCE_SALER}"  type="text" class="form-control form-control-static" name="emp_name" id="emp_name"
                       placeholder="代理人姓名">
            </div>
        </div>
        <div class="form-group" style="margin-top:20px;">
            <label class="col-md-2 control-label">接单机构*</label>
            <div class="col-md-3 ">
                <select  type="text" class="form-control form-control-static" name="order_taking_mechanism"
                       id="order_taking_mechanism" placeholder="接单机构">
                </select>
            </div>
            <label class="col-md-2 control-label">销售团队*</label>
            <div class="col-md-3 ">
                <select  type="text" class="form-control form-control-static" name="sales_team" id="sales_team"
                       placeholder="销售团队">
                </select>
            </div>
        </div>

        <div id="toolbar_image" class="btn-toolbar" style="overflow:scroll;background: slategray;width: 1800px;">
            <span style="color: cornsilk">投保单影像信息</span>
            <a href="#" id="file" onclick="alertFile()" style="color: blue">添加影像件信息</a>
        </div>

        <div style="overflow:auto;width:1050px;">
            <table class="table table-hover table-striped table-condensed table-bordered"
                   style="width:1200px;font-size:13px;" id="images">
                <tr>
                    <td>影像件序列</td>
                    <td>影像件名称</td>
                    <td>操作</td>
                </tr>
            </table>
        </div>
        <div id="toolbar_xzxx" class="btn-toolbar" style="overflow:scroll;background: slategray;width: 1800px;">
            <span style="color: cornsilk">险种信息</span>

        </div>
        <div style="overflow:auto;width:1050px;">
            <table class="table table-hover table-striped table-condensed table-bordered"
                   style="width:1200px;font-size:13px;" id="bxcp">
                <tr>
                    <td>产品代码</td>
                    <td>产品名称</td>
                    <td>主副险标志</td>
                    <td>保额</td>
                    <td>保费</td>
                    <td>保险期间</td>
                    <td>缴费方式</td>
                    <td>缴费年期</td>
                    <td><a disabled="disabled" style="color: black" href="#" ></a></td>
                </tr>
            </table>
        </div>
        <div id="toolbar_syr" class="btn-toolbar" style="overflow:scroll;background: slategray;width: 1800px;">
            <span style="color: cornsilk">险种受益人</span>

        </div>
        <div style="overflow:auto;width:1050px;">
            <table class="table table-hover table-striped table-condensed table-bordered"
                   style="width:1200px;font-size:13px;" id="beneficiary">
                <tr>
                    <td>险种受益人</td>
                    <td width="250px">证件类型</td>
                    <td>证件号码</td>
                    <td>性别</td>
                    <td>出生日期</td>
                    <td>受益顺序</td>
                    <td>受益比例</td>
                    <td>与被保人关系</td>
                    <td><a disabled="disabled" style="color: black" href="#"></a></td>
                </tr>
            </table>
        </div>
        <div class="form-group" style="margin-top:20px;">
            <div class="col-md-3 ">
                <input type="hidden" id="sexTest">
                <input type="hidden" id="cardTest">
                <button class="btn btn-primary" onclick="commit()">保存提交</button>

                <button class="btn btn-default" onclick="clo()">取消</button>
            </div>
        </div>
    </form>
    <div/>

</div>
</div>
</div>
<!-- 添加 -->
<div id="adddiv" class="modal fade" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false"
     aria-labelledby="zsadd_myModalLabelDIV" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="zsadd_myModalLabelH">添加</h4>
            </div>
            <div class="container">

                <br/>

                <div class="form-group">
                    <label class="col-md-2 control-label">产品组合代码</label>
                    <div class="col-md-3 ">
                        <input type="text" id="a1" name="role_id">
                    </div>
                </div>

                <br/>
                <div class="form-group">
                    <label class="col-md-2 control-label">产品名称</label>
                    <div class="col-md-3 ">
                        <input type="text" id="a2" name="role_id">
                    </div>
                </div>
                <br/>
                <div class="form-group">
                    <label class="col-md-2 control-label">保额（元）</label>
                    <div class="col-md-3 ">
                        <input type="text" id="a3" name="role_id">
                    </div>
                </div>
                <br/>
                <div class="form-group">
                    <label class="col-md-2 control-label">保费（元）</label>
                    <div class="col-md-3 ">
                        <input type="text" id="a4" name="role_id">
                    </div>
                </div>
                <br/>
                <div class="form-group">
                    <label class="col-md-2 control-label">份数</label>
                    <div class="col-md-3 ">
                        <input type="text" id="a5" name="role_id">
                    </div>
                </div>
                <br/>
                <div class="form-group">
                    <label class="col-md-2 control-label">缴费方式</label>
                    <div class="col-md-3 ">
                        <input type="text" id="a6" name="role_id"></div>
                </div>
                <br/>
                <div class="form-group">
                    <label class="col-md-2 control-label">缴费 年期/年龄标志</label>
                    <div class="col-md-3 ">
                        <input type="text" id="a7" name="role_id"></div>
                </div>
                <br/>
                <div class="form-group">
                    <label class="col-md-2 control-label">缴费 年期/年龄*</label>
                    <div class="col-md-3 ">
                        <input type="text" id="a8" name="role_id"></div>
                </div>
                <br/>
                <div class="form-group">
                    <label class="col-md-2 control-label">保险期间标志</label>
                    <div class="col-md-3 ">
                        <input type="text" id="a9" name="role_id">
                    </div>
                </div>
                <br/>
                <div class="form-group">
                    <label class="col-md-2 control-label">保险期间</label>
                    <div class="col-md-3 ">
                        <input type="text" id="a10" name="role_id">
                    </div>
                </div>
                <br/>
                <div class="form-group">
                    <label class="col-md-2 control-label">主附险标志</label>
                    <div class="col-md-3 ">
                        <input type="text" id="a11" name="role_id">
                    </div>
                </div>
                <br/>

                <div class="modal-footer col-md-6">

                    <!--用来清空表单数据-->
                    <input type="reset" name="reset" style="display: none;"/>
                    <button type="button" class="btn btn-default" onclick="closeDlg()">关闭</button>
                    <button id="zs_saveButtonONE" type="button" onclick="bxqd()" class="btn btn-primary">保存</button>
                </div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<%--<div style="background: white" id="adddiv" class="modal fade" tabindex="-1" role="dialog" data-backdrop="static"
     data-keyboard="false" aria-labelledby="myModalLabel" aria-hidden="true"
     padding-left="180px">

    <label class="col-md-2 control-label">产品组合代码：</label>
    <input type="text" id="a1" name="role_id"><br/>
    <label class="col-md-2 control-label">产品名称*：</label>
    <input type="text" id="a2" name="role_id"><br/>
    <label class="col-md-2 control-label">保额（元）：</label>
    <input type="text" id="a3" name="role_id"><br/>
    <label class="col-md-2 control-label">保费（元）*：</label>
    <input type="text" id="a4" name="role_id"><br/>
    <label class="col-md-2 control-label">份数：</label>
    <input type="text" id="a5" name="role_id"><br/>
    <label class="col-md-2 control-label">缴费方式*：</label>
    <input type="text" id="a6" name="role_id"><br/>
    <label>缴费 年期/年龄标志*：</label>
    <input type="text" id="a7" name="role_id"><br/>
    <label>缴费 年期/年龄*：</label>
    <input type="text" id="a8" name="role_id"><br/>
    <label class="col-md-2 control-label">保险期间标志*：</label>
    <input type="text" id="a9" name="role_id"><br/>
    <label class="col-md-2 control-label">保险期间*：</label>
    <input type="text" id="a10" name="role_id"><br/>
    <label class="col-md-2 control-label">主附险标志：</label>
    <input type="text" id="a11" name="role_id"><br/>
    <!--用来清空表单数据-->
    <input type="reset" name="reset" style="display: none;"/>
    <button type="button" class="btn btn-default" onclick="closeDlg()">关闭</button>
    <button type="button" onclick="bxqd()" class="btn btn-primary">保存</button>


</div>--%>
<!-- 导入文件div -->
<div id="alertFileEntry" class="modal fade" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false"
     aria-labelledby="zsadd_myModalLabe2DIV" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="zsadd_myModalLabe2H">上传文件</h4>
            </div>
            <div class="container">
                <form class="form-horizontal" id="fileFormEntry" enctype="multipart/form-data">
                    <div class="form-group">
                        <label class="col-md-2 control-label">选择文件</label>
                        <div class="col-md-3 ">
                            <input type="file" name="file" id="fileNameEntry"/>
                        </div>
                    </div>
                    <div class="modal-footer col-md-6">
                        <!--用来清空表单数据-->
                        <input type="reset" name="reset" style="display: none;"/>
                        <button type="button" class="btn btn-default" onclick="closeDlg()">关闭</button>
                        <button id="zs_saveButton" type="button" onclick="upload()" class="btn btn-primary">保存</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>


<input onchange="changeVal()" type="file" id="image" name="image" style="display:none;"/>
<%--屏幕挡板--%>
<div class="modal" id="myModal" style="display:none">
</div>
<div class="modal fade" id="loadingModal">
    <div style="width: 200px;height:20px; z-index: 20000; position: absolute; text-align: center; left: 50%; top: 50%;margin-left:-100px;margin-top:-10px">
        <div class="progress progress-striped active" style="margin-bottom: 0;">
            <div class="progress-bar" style="width: 100%;"></div>
        </div>
        <h5 style="color:black"><strong>正在缓冲文件...预计1-5秒，请稍等！</strong></h5>
    </div>
</div>


</body>

</html>