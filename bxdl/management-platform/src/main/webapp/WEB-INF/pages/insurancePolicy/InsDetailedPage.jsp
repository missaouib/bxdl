<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/include/core.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>保单详情</title>
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
            getSysDictVal("note_problem_type","ERROR_TYPE")
            getSysDictVal("note_sell_back","YESORNO")
            var policy_holder_marital_status = '${iph.maritalStatus}'
            var insured_marital_status = '${pip.maritalStatus}'
            getSysDictValHX("policy_holder_marital_status","MARITAL_STATUS（zh）",policy_holder_marital_status)
            getSysDictValHX("insured_marital_status","MARITAL_STATUS（zh）",insured_marital_status)
            $("#state_cn").text(getStateTest('${net.INS_STATE}'))
            var ipaJsonList3 = JSON.parse('${ipaType3}')
            var im3="";
            for(var i=0;i<ipaJsonList3.length;i++){
                var id = ipaJsonList3[i].attId
                var attName = ipaJsonList3[i].attName
                var attAdd = ipaJsonList3[i].attAdd
                im3+="<tr>" +//att_add
                    "<td>" + (i + 1) + "<input value='"+id+"' name='ipaId' type='hidden'> </td>" +
                    "<td>" + attName + "<input value='" +attName + "' name='fileName' type='hidden'><input value='" +attAdd + "' name='fastPath' type='hidden'></td>" +
                    "<td onclick='del_tr(this)'>" + "删除" + "</td></tr>";
            }
            $("#note_images").append(im3)
            var ipaJsonList4 = JSON.parse('${ipaType4}')
            var im="";
            for(var i=0;i<ipaJsonList4.length;i++){
                var id = ipaJsonList4[i].attId
                var attName = ipaJsonList4[i].attName
                var attAdd = ipaJsonList4[i].attAdd
                im+="<tr>" +//att_add
                    "<td>" + (i + 1) + "<input value='"+id+"' name='ipaId' type='hidden'> </td>" +
                    "<td>" + attName + "<input value='" +attName + "' name='fileName' type='hidden'><input value='" +attAdd + "' name='fastPath' type='hidden'></td>" +
                    "<td onclick='del_tr(this)'>" + "删除" + "</td></tr>";
            }
            $("#recr_images").append(im)


            $("input").attr("disabled",true);
            $("#retVis").hide()
            $("#insNote").hide()
            $("#receDeta").hide()
            $("#netReceipts").hide()
            $("#insDeta").show()
            $("#stateDiv").hide();
            var string="  <tr>\n" +
                "            <td>操作作业节点</td>\n" +
                "            <td>开始时间</td>\n" +
                "            <td>操作用户</td>\n" +
                "        </tr>";
            var jsonString ='${sta}'
            var obj = JSON.parse(jsonString)
            for(var i=0;i<'${staSize}';i++){
                var state =obj[i].state
                var time = obj[i].create_time
                var emp = obj[i].name
                var ststeText = getStateTest(state);
                var formatDate =  formatDatew(time);
                string+="<tr>" +
                    "<td>" + ststeText + "</td>" +
                    "<td>" + formatDate + "</td>" +
                    "<td>" + emp + "</td>" +
                    "</tr>";
            }
            $("#state-table").append(string)





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
                $("#bxcp").append("<tr>" +
                    "<td>" + inforList[i].PRODUCT_CODE + "<input value='" + inforList[i].PRODUCT_CODE + "' name='a1' type='hidden'></td>" +
                    "<td>" + inforList[i].PRODUCT_NAME + "<input value='" + inforList[i].PRODUCT_NAME + "' name='a2' type='hidden'>" + "</td>" +
                    "<td>" + mainText + "<input value='" + inforList[i].principal_deputy_sign + "' name='a11' type='hidden'></td>"+
                    "<td>" + inforList[i].insured_amount + "<input value='" + inforList[i].insured_amount + "' name='a3' type='hidden'></td>" +
                    "<td>" + inforList[i].premium + "<input value='" + inforList[i].premium + "' name='a4' type='hidden'><input value='" + inforList[i].size + "' name='a5' type='hidden'></td>" +
                    "<td>"+periText+"<input value='" + inforList[i].PERIOD_OF_INSURANCE_SIGN + "' name='a9' type='hidden'></td>"+
                    "<td>" +payModeText+ "<input value='" + inforList[i].payment_method + "' name='a6' type='hidden'></td>" +
                    "<td>" + reneText + "<input value='" + inforList[i].payment_period_sign + "' name='a7' type='hidden'><input value='" + inforList[i].payment + "' name='a8' type='hidden'><input value='" + inforList[i].period_of_insurance + "' name='a10' type='hidden'></td>" + "" +

                    "<td>删除</td></tr>")
            }
            var ipaJsonList = JSON.parse('${ipaType1}')

            var im="";
            for(var i=0;i<ipaJsonList.length;i++){
                var id = ipaJsonList[i].attId
                var attName = ipaJsonList[i].attName
                var attAdd = ipaJsonList[i].attAdd
                im+="<tr>" +
                    "<td>" + (i + 1) + "<input value='"+id+"' name='ipaId' type='hidden'></td>" +
                    "<td>" + attName + "<input value='" +attName + "' name='fileName' type='hidden'><input value='" +attAdd + "' name='fastPath' type='hidden'></td>" +
                    "<td onclick='del_tr(this)'>" + "删除" + "</td></tr>";
            }
            $("#images").append(im)


            $("#adddiv").modal('hide');
            //加载页面异步填充下拉框等
            var car=''
            var sex=''
            $.ajax({
                url: "systemDict/getParamsData",
                type: "post",
                dataType: "json",
                async: false, //更改为同步

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
//                        alert(JSON.stringify(value));
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
                    getSysDictValHX('insured_sex1','SEX',chesex)
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
                success: function (obj) {
                    var insuranceCompanys = obj.data.org
                    var h = "<option value=''>请选择保险公司</option>";
                    $.each(insuranceCompanys, function (key, value) {
//                        alert(JSON.stringify(value));
                        h += "<option value='" + value.insuranceCompanyId + "'>" + value.insuranceCompanyName //下拉框序言的循环数据
                            + "</option>";
                    });
                    var k="";
                    var jsonList = JSON.parse('${pppJson}')
                    var cardt = $("#cardTest").val()
                    var sext = $("#sexTest").val()
                    for(var i=0;i<'${pppSize}';i++){
                        var pppName = jsonList[i].name
                        var pppCardNo = jsonList[i].cardNo
                        var pppBirthday = jsonList[i].birthday
                        var pppLevel = jsonList[i].level
                        var pppBenefitRatio = jsonList[i].benefitRatio
                        var pppRelationship = jsonList[i].relationship
                        var profitsPersonId = jsonList[i].profitsPersonId
                        k+="<tr>" +
                            "<td><input disabled='disabled' type='test' name='b1' value='"+pppName+"'><input disabled='disabled' type='hidden' value='"+profitsPersonId+"' name='profitsPersonId'> </td>" +
                            "<td><select disabled=\"disabled\" name='b2' style='width: 200px' disabled=\"disabled\"  class=\"form-control form-control-static\">" + cardt + "</select></td>" +
                            "<td><input disabled='disabled' value='"+pppCardNo+"' name='b3' type='test'></td>" +
                            "<td><select disabled='disabled' name='b4' type='test' style='width: 200px'  class=\"form-control form-control-static\">" + sext + "</select>" + "</td>" +
                            "<td><input disabled='disabled' value='"+pppBirthday+"' name='b5' type='date'></td>" +
                            "<td><input disabled='disabled' value='"+pppLevel+"' name='b6' type='test'></td>" +
                            "<td><input disabled='disabled' value='"+pppBenefitRatio+"' name='b7' type='test'></td>" +
                            "<td><input disabled='disabled' value='"+pppRelationship+"' name='b8' type='test'></td>" +
                            "<td><a href='#'>" + "操作" + "</a>" +
                            "</td></tr>"
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


        });
        function bxqd() {
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
        }

        var trSize = 0;

        function addBeneficiary() {
            var k = "<tr><td><input type='test' name='b1'></td><td><select name='b2' disabled=\"disabled\"  class=\"form-control form-control-static\"<option value=\"0\">居民身份证</option><option value=\"1\">出生证明</option><option value=\"2\">驾驶证</option><option value=\"3\">军官证</option>" +
                "<option value=\"4\">士兵证</option><option value=\"8\">旅行证</option><option value=\"9\">回乡证</option>" +
                "<option value=\"10\">警官证</option><option value=\"12\">户口本</option>" +
                "<option value=\"14\">台胞证</option><option value=\"15\">学生证</option>" +
                "<option value=\"16\">工作证</option><option value=\"17\">无证件</option>" +

                "</select></td><td><input name='b3' type='test'></td><td><select disabled=\"disabled\" name='b4' type='test'><option>男</option><option>女</option></select>" +
                "</td><td><input name='b5' type='test'></td><td><input name='b6' type='test'></td><td><input name='b7' type='test'></td><td><input name='b8' type='test'></td><td><a href='#'>" + "操作" + "</a>" +
                "</td></tr>>"
            $("#beneficiary").append(k)
        }

        function addInsurance() {
            $("#adddiv").modal('show')
        }

        function alertFile() {
            $("#alertFile").modal('show')
        }

        function changeVal(data) {
            var file = $("#fileName").val();
            var fileName = getFileName(file);

            function getFileName(o) {
                var pos = o.lastIndexOf("\\");
                return o.substring(pos + 1);
            }

            trSize = trSize + 1;
            data.newFileName
            var ts = $("#images tr").length
            $("#images").append("<tr><td>" + ts + "</td><td>" + fileName + "<input value='" + fileName + "' name='fileName' type='hidden'></td><td onclick='del_tr(this)'>" + "删除" + "</td></tr>")
        }
function insDet() {
    $("#retVis").hide()
    $("#insNote").hide()
    $("#receDeta").hide()
    $("#netReceipts").hide()
    $("#insDeta").show()
    $("#stateDiv").hide();

}
function receDet() {
    $("#retVis").hide()
    $("#insNote").hide()
    $("#insDeta").hide()
    $("#netReceipts").hide()
    $("#receDeta").show()
    $("#stateDiv").hide();
}
function retVis() {
    $("#retVis").show()
    $("#receDeta").hide()
    $("#insDeta").hide()
    $("#insNote").hide()
    $("#netReceipts").hide()
    $("#stateDiv").hide();
}
function insNote() {
    $("#insNote").show()
    $("#retVis").hide()
    $("#receDeta").hide()
    $("#insDeta").hide()
    $("#netReceipts").hide()
    $("#stateDiv").hide();
}
function netReceipts() {
    $("#netReceipts").show()
    $("#insNote").hide()
    $("#retVis").hide()
    $("#receDeta").hide()
    $("#insDeta").hide()
    $("#stateDiv").hide();
}
function stateDiv(){
         $("#stateDiv").show();
    $("#netReceipts").hide()
    $("#insNote").hide()
    $("#retVis").hide()
    $("#receDeta").hide()
    $("#insDeta").hide()

}
        function clo(sta) {
            if (sta==1){
                commCloseTab('insDetaPage:list')
                createAddProcessTab('operation:list','')
            } else{
                if ('${net.INS_STATE}'>1005){
                    alert("当前状态不可以转到回执")
                    return;
                }
                commCloseTab('insDetaPage:list')
                createAddProcessTab("updateReceiptOne:list",'')
            }

        }
    </script>
</head>
<body>
<div class="container" style="width: 2000px">
    <input type="hidden" id="sexTest">
    <input type="hidden" id="cardTest">
        <div class="form-group" style="background: #708090;">
            <div class="col-md-1 ">
                <button class="btn btn-primary" onclick="insDet()">1保单基本信息</button>
            </div>
             <div class="col-md-1 ">
                <button class="btn btn-primary" onclick="receDet()">2回执记录</button>
            </div>
              <div class="col-md-1 ">
                <button class="btn btn-primary" onclick="retVis()">3回访记录</button>
            </div>
              <div class="col-md-1 ">
                <button class="btn btn-primary" onclick="insNote()">4照会信息</button>
            </div>
            <div class="col-md-1 ">
                <button class="btn btn-primary" onclick="netReceipts()">5实收记录</button>
            </div>
            <div class="col-md-1 ">
                <button class="btn btn-primary" onclick="stateDiv()">6操作记录</button>
            </div>
            <div class="col-md-1 ">
                <button class="btn btn-default" onclick="clo(1)">返回到撤保</button>
            </div>
            <div class="col-md-1 ">
                <button class="btn btn-default" onclick="clo(2)">返回到回执</button>
            </div>

         <%--   <div class="col-md-1 ">
                <button class="btn btn-default" onclick="clo(1)">返回到撤保</button>
            </div>
            <div class="col-md-1 ">
                <button class="btn btn-default" onclick="clo(2)">返回到回执</button>
            </div>--%>
        </div>
</div>
<div class="container" style="width: 2000px" id="insDeta">
    <form class="form-horizontal" id="addForm">
        <div id="basetoolbar" class="btn-toolbar" style="overflow:scroll;background: slategray;width: 1800px;">
            <span style="color: cornsilk">投保单基本信息</span>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">保单号*</label>
            <div class="col-md-3 ">

                <input value="${pip.policyId}"  type="text" class="form-control form-control-static" id="exampleInsName1"
                       name="exampleInsName1" placeholder="保单号" >
            </div>

        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">保单承保日期*</label>
            <div class="col-md-3 ">

                <input value="${pip.underwritingData}" type="date" class="form-control form-control-static" id="exampleInsName2"
                       name="exampleInsName2">
            </div>
            <label class="col-md-2 control-label">保单生效日期*</label>
            <div class="col-md-3 ">
                <input  value="${pip.takeEffectData}"  class="form-control form-control-static" id="exampleInsName3" type="date" name="exampleInsName3"/>

            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">投保单号</label>
            <div class="col-md-3 ">

                <input type="text" class="form-control form-control-static" id="exampleInputName1"
                       name="exampleInputName1" placeholder="投保单号" value="${pip.corresponding}">
            </div>
            <label class="col-md-2 control-label">投保公司*</label>
            <div class="col-md-3 ">
                <select class="form-control form-control-static" id="insuranceCompany" name="insuranceCompany" disabled="disabled">
                    <option value="">-请选择-</option>
                </select>
            </div>
        </div>
        <div class="form-group" style="margin-top:20px;">
            <label class="col-md-2 control-label">总保费（元）*</label>
            <div class="col-md-3 ">

                <input type="text" class="form-control form-control-static" name="exampleInputName3"
                       id="exampleInputName3" placeholder="投保单号" value="${ipp.total_premium}">
            </div>
            <label class="col-md-2 control-label">投保日期*</label>
            <div class="col-md-3 ">

                <input value="${ipp.propose_date}" type="date" class="form-control form-control-static"
                       name="exampleInputName4"
                       id="exampleInputName4" placeholder="投保单号">
            </div>
        </div>
        <div id="tbrtoolbar" class="btn-toolbar" style="overflow:scroll;background: slategray;width: 1800px;">
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
                <select id="card_type" name="card_type" class="form-control form-control-static" disabled="disabled">

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
                        id="policy_holder_sex" disabled="disabled"></select>
            </div>
            <label class="col-md-2 control-label">投保人出生日期*</label>
            <div class="col-md-3 ">
                <input value="${iph.birthday}" type="date" class="form-control form-control-static"
                       name="policy_holder_birthday"
                       id="policy_holder_birthday" placeholder="投保人出生日期">
            </div>
        </div>
        <div class="form-group" >
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
                <select disabled="disabled" name="pay_type" id="pay_type" class="form-control form-control-static">
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
                <select disabled="disabled"  class="form-control form-control-static"
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
        <div id="gxtoolbar" class="btn-toolbar" style="overflow:scroll;background: slategray;width: 1800px;">
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
                <select disabled="disabled" name="insured_card_type" id="insured_card_type" class="form-control form-control-static">
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
                <select class="form-control form-control-static" name="insured_sex1"
                        id="insured_sex1" disabled="disabled"></select>
                <%--<select class="form-control form-control-static" name="insured_sex1" id="insured_sex1"
                        placeholder="性别"></select>--%>
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
                <select disabled="disabled" name="multiple_insurance_coverage" id="multiple_insurance_coverage"
                        class="form-control form-control-static">

                </select>
            </div>
            <label class="col-md-2 control-label">是否有健康告知*</label>
            <div class="col-md-3 ">
                <select disabled="disabled" name="health_report" id="health_report" class="form-control form-control-static">

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
                <select disabled="disabled" class="form-control form-control-static" name="insured_marital_status"
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
        <div id="dlrtoolbar" class="btn-toolbar" style="overflow:scroll;background: slategray;width: 1800px;">
            <span style="color: cornsilk">代理人信息</span>
        </div>
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
                <select disabled="disabled" type="text" class="form-control form-control-static" name="order_taking_mechanism"
                         id="order_taking_mechanism" placeholder="接单机构">
                </select>
            </div>
            <label class="col-md-2 control-label">销售团队*</label>
            <div class="col-md-3 ">
                <select disabled="disabled" type="text" class="form-control form-control-static" name="sales_team" id="sales_team"
                         placeholder="销售团队">
                </select>
            </div>
        </div>

        <div id="imagetoolbar" class="btn-toolbar" style="overflow:scroll;background: slategray;width: 1800px;">
            <span style="color: cornsilk">投保单影像信息</span>
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
        <div id="xzxxtoolbar" class="btn-toolbar" style="overflow:scroll;background: slategray;width: 1800px;">
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
                    <td><a style="color: black" href="#" onclick="addInsurance()">添加</a></td>
                </tr>
            </table>
        </div>
        <div id="syrtoolbar" class="btn-toolbar" style="overflow:scroll;background: slategray;width: 1800px;">
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
                    <td><a style="color: black" href="#" onclick="addBeneficiary()">新增受益人</a></td>
                </tr>
            </table>
        </div>
    </form>
    <div/>

</div>
</div>
</div>
<form class="form-horizontal" >
    <div class="container" style="width: 2000px;display: none" id="receDeta" >

        <div style="overflow:scroll;">
            <div class="form-group">
                <label class="col-md-2 control-label">投保单号*</label>
                <div class="col-md-3 ">
                    <input value="${obj.CORRESPONDING}" type="text" class="form-control form-control-static" id="RECE_CORRESPONDING"
                           name="RECE_CORRESPONDING" readonly="readonly">
                </div>

                <label class="col-md-2 control-label">保单号*</label>
                <div class="col-md-3 ">
                    <input value="${obj.POLICY_ID}" type="text" class="form-control form-control-static" id="RECE_POLICY_ID"
                           name="RECE_POLICY_ID" readonly="readonly">
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 control-label">保险公司*</label>
                <div class="col-md-3 ">
                    <input value="${obj.INSURANCE_COMPANY_NAME}" type="text" class="form-control form-control-static" id="RECE_INSURANCE_COMPANY_NAME"
                           name="RECE_INSURANCE_COMPANY_NAME" readonly="readonly">
                </div>


                <label class="col-md-2 control-label">组织机构*</label>
                <div class="col-md-3 ">
                    <input value="${obj.SALES_ORG_NAME}" type="text" class="form-control form-control-static" id="RECE_SALES_ORG_NAME"
                           name="RECE_SALES_ORG_NAME" readonly="readonly">
                </div>


            </div>
            <div class="form-group">
                <label class="col-md-2 control-label">营销团队*</label>
                <div class="col-md-3 ">
                    <input value="${obj.SALES_TEAM_NAME}" type="text"  class="form-control form-control-static" id="RECE_SALES_TEAM_NAME"
                           name="RECE_SALES_TEAM_NAME" readonly="readonly"/>
                </div>
                <label class="col-md-2 control-label">员工工号*</label>
                <div class="col-md-3 ">
                    <input value="${obj.EMPLOYEE_NO}" type="text" class="form-control form-control-static" id="RECE_EMPLOYEE_NO"
                           name="RECE_EMPLOYEE_NO" readonly="readonly">
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-2 control-label">员工姓名</label>
                <div class="col-md-3 ">
                    <input value="${obj.NAME3}" type="text"  class="form-control form-control-static" id="RECE_NAME3"
                           name="RECE_NAME3" readonly="readonly"/>
                </div>
                <label class="col-md-2 control-label">投保人姓名</label>
                <div class="col-md-3 ">
                    <input value="${obj.NAME1}" type="text" class="form-control form-control-static" id="RECE_NAME1"
                           name="RECE_NAME1" readonly="readonly">
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-2 control-label">被保人姓名</label>
                <div class="col-md-3 ">
                    <input value="${obj.NAME2}" type="text"  class="form-control form-control-static" id="RECE_NAME2"
                           name="RECE_NAME2" readonly="readonly"/>
                </div>
                <label class="col-md-2 control-label">客户签收回执日期*</label>
                <div class="col-md-3 ">
                    <input readonly="readonly" value="${obj.RECE_CUSTOMER_SIGNATURE}"  type="date" class="form-control form-control-static" id="RECE_Customer_Signature"
                            name="RECE_Customer_Signature">
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-2 control-label">组织机构回执日期录入</label>
                <div class="col-md-3 ">
                    <input readonly="readonly" value="${obj.RECE_ORG_DATE}"  type="date"  class="form-control form-control-static" id="RECE_ORG_DATE"
                            name="RECE_ORG_DATE"/>
                </div>
                <label class="col-md-2 control-label">回执情况说明</label>
                <div class="col-md-3 ">
                    <input readonly="readonly" value="${obj.RECE_EXPLAIN}"  type="text" class="form-control form-control-static" id="RECE_Explain"
                            name="RECE_Explain">
                </div>
            </div>
        </div>
        <!--toolbar  -->
        <div id="yxtoolbar" class="btn-toolbar" style="overflow:scroll;background: slategray;width: 1800px;">
            <span style="color: cornsilk">投保单影像信息</span>
        </div>

        <div style="overflow:auto;width:1050px;">
            <table class="table table-hover table-striped table-condensed table-bordered"
                   style="width:1200px;font-size:13px;" id="recr_images">
                <tr>
                    <td>影像件序列</td>
                    <td>影像件名称</td>
                    <td>操作</td>
                </tr>
            </table>
        </div>
    </div>


</form>
<form class="form-horizontal">
    <div class="container" style="width: 2000px" id="retVis">
        <div style="overflow:scroll;">
            <div class="form-group">
                <label class="col-md-2 control-label">投保单号*</label>
                <div class="col-md-3 ">
                    <input value="${obj.CORRESPONDING}" type="text" class="form-control form-control-static" id="RETURN_CORRESPONDING"
                           name="RETURN_CORRESPONDING" readonly="readonly">
                </div>

                <label class="col-md-2 control-label">保单号*</label>
                <div class="col-md-3 ">
                    <input value="${obj.POLICY_ID}" type="text" class="form-control form-control-static" id="RETURN_POLICY_ID"
                           name="RETURN_POLICY_ID" readonly="readonly">
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 control-label">保险公司*</label>
                <div class="col-md-3 ">
                    <input value="${obj.INSURANCE_COMPANY_NAME}" type="text" class="form-control form-control-static" id="RETURN_INSURANCE_COMPANY_NAME"
                           name="RETURN_INSURANCE_COMPANY_NAME" readonly="readonly">
                </div>


                <label class="col-md-2 control-label">组织机构*</label>
                <div class="col-md-3 ">
                    <input value="${obj.SALES_ORG_NAME}" type="text" class="form-control form-control-static" id="RETURN_SALES_ORG_NAME"
                           name="RETURN_SALES_ORG_NAME" readonly="readonly">
                </div>


            </div>
            <div class="form-group">
                <label class="col-md-2 control-label">营销团队*</label>
                <div class="col-md-3 ">
                    <input value="${obj.SALES_TEAM_NAME}" type="text"  class="form-control form-control-static" id="RETURN_SALES_TEAM_NAME"
                           name="RETURN_SALES_TEAM_NAME" readonly="readonly"/>
                </div>
                <label class="col-md-2 control-label">员工工号*</label>
                <div class="col-md-3 ">
                    <input value="${obj.EMPLOYEE_NO}" type="text" class="form-control form-control-static" id="RETURN_EMPLOYEE_NO"
                           name="RETURN_EMPLOYEE_NO" readonly="readonly">
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-2 control-label">员工姓名</label>
                <div class="col-md-3 ">
                    <input value="${obj.NAME3}" type="text"  class="form-control form-control-static" id="RETURN_NAME3"
                           name="RETURN_NAME3" readonly="readonly"/>
                </div>
                <label class="col-md-2 control-label">投保人姓名</label>
                <div class="col-md-3 ">
                    <input value="${obj.NAME1}" type="text" class="form-control form-control-static" id="RETURN_NAME1"
                           name="RETURN_NAME1" readonly="readonly">
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-2 control-label">被保人姓名</label>
                <div class="col-md-3 ">
                    <input value="${obj.NAME2}" type="text"  class="form-control form-control-static" id="RETURN_NAME2"
                           name="RETURN_NAME2" readonly="readonly"/>
                </div>
                <label class="col-md-2 control-label">回访方式*</label>
                <div class="col-md-3 ">
                    <input readonly="readonly" value="${obj.RETURN_TYPE}" type="text" class="form-control form-control-static" id="RETURN_type"
                           name="RETURN_type">
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-2 control-label">回访成功日期*</label>
                <div class="col-md-3 ">
                    <input readonly="readonly" value="${obj.RETURN_ORG_DATE}" type="date"  class="form-control form-control-static" id="RETURN_ORG_DATE"
                            name="RETURN_ORG_DATE"/>
                </div>
                <label class="col-md-2 control-label">回访情况说明</label>
                <div class="col-md-3 ">
                    <input readonly="readonly" value="${obj.RETURN_EXPLAIN}" type="text" class="form-control form-control-static" id="RETURN_Explain"
                           name="RETURN_Explain">
                </div>
            </div>
        </div>

    </div>
</form>
<form class="form-horizontal">
    <div class="container" style="width: 2000px;display: none" id="insNote">

        <div style="overflow:scroll;">
            <div class="form-group">
                <label class="col-md-2 control-label">保险公司机构*</label>
                <div class="col-md-3 ">
                    <input value="${obj.INSURANCE_COMPANY_NAME}" type="text" class="form-control form-control-static" id="note_org"
                           name="note_org" readonly="readonly">
                </div>

                <label class="col-md-2 control-label">投保单号*</label>
                <div class="col-md-3 ">
                    <input value="${obj.POLICY_ID}" type="text" class="form-control form-control-static" id="note_policy"
                           name="note_policy" readonly="readonly">
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 control-label">投保人*</label>
                <div class="col-md-3 ">
                    <input value="${obj.NAME1}" type="text" class="form-control form-control-static" id="note_ph"
                           name="note_ph" readonly="readonly">
                </div>


                <label class="col-md-2 control-label">问题件类型*</label>
                <div class="col-md-3 ">
                    <select disabled="disabled"   class="form-control form-control-static" id="note_problem_type"
                            name="note_problem_type"></select>
                </div>


            </div>
            <div class="form-group">
                <label class="col-md-2 control-label">是否回销*</label>
                <div class="col-md-3 ">
                    <select disabled="disabled" readonly="readonly"  class="form-control form-control-static" id="note_sell_back"
                            name="note_sell_back"/></div>

                </div>

                <label class="col-md-2 control-label">问题件说明*</label>
                <div class="col-md-3 ">
                    <input readonly="readonly" value="${obj.NOTE_EXPLAIN}" type="text" class="form-control form-control-static" id="note_problem_explain"
                           name="note_problem_explain">
                </div>
            </div>
        <div id="toolbar_next_image" class="btn-toolbar" style="overflow:scroll;background: slategray;width: 1800px;">
            <span style="color: cornsilk">投保单影像信息</span>
        </div>

        <div style="overflow:auto;width:1050px;">
            <table class="table table-hover table-striped table-condensed table-bordered"
                   style="width:1200px;font-size:13px;" id="note_images">
                <tr>
                    <td>影像件序列</td>
                    <td>影像件名称</td>
                    <td>操作</td>
                </tr>
            </table>
        </div>
        </div>
        <!--toolbar  -->



</form>
<div style="overflow:scroll;display: none;" id="netReceipts">
    <table id="insComanpy-table" class="table table-hover table-striped table-condensed table-bordered">
        <tr>
            <td>产品代码</td>
            <td>${net.PRODUCT_CODE}</td>
        </tr>
        <tr>
            <td>产品名称</td>
            <td>${net.PRODUCT_NAME}</td>
        </tr>
        <tr>
            <td>应收日期</td>
            <td>${net.INSURE}</td>
        </tr>
        <tr>
            <td>应收保费</td>
            <td>${net.PREMIUM}</td>
        </tr>
        <tr>
            <td>实收日期</td>
            <td>${net.UNDERWRITING_DATA}</td>
        </tr>
        <tr>
            <td>实收保费</td>
            <td>${net.bdPREMIUM}</td>
        </tr>
        <tr>
            <td>代理人编码</td>
            <td>${net.INSURANCE_SALER_NO}</td>
        </tr>
        <tr>
            <td>代理人姓名</td>
            <td>${net.INSURANCE_SALER}</td>
        </tr>
        <tr>
            <td>状态</td>
            <td><span id="state_cn"></span></td>
        </tr>
    </table>
</div>
<div style="overflow:scroll;display: none;" id="stateDiv">
    <table id="state-table" class="table table-hover table-striped table-condensed table-bordered">

    </table>
</div>
</body>

</html>