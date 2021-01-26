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

            getSysDictVal('policy_holder_marital_status','MARITAL_STATUS（zh）')
            getSysDictVal('insured_marital_status','MARITAL_STATUS（zh）')

            /* $("#order_taking_mechanism").mouseover(function(){
                alert("此处不可选择！");
                return false;
            });
            $("#sales_team").mouseover(function(){
                alert("此处不可选择！");
                return false;
            }); */

            getSysDict("a6","JFFS")
            getSysDict("a7","NQNL")
            getSysDict("a9","BXQJ")
            getSysDict("a11","ZF")
            $("#insuranceCompany").on("change",function () {
            var id = $(this).find("option:selected").val()
                commSelectProduct("a2", $(this).find("option:selected").val())
            })
            $("#a2").on("change",function () {
                commSelectProductCode("a1","a6","a7","a7t","a9","a9t","a11","a11t","a12", $(this).find("option:selected").val())

            })
            commSalesOrgs("order_taking_mechanism");
            commSalesAllTeams("sales_team");

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
//                        alert(JSON.stringify(value));
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

                    $("#cardTest").val(card)
                    $("#sexTest").val(sex)
                    $("#card_type").empty();
                    $("#card_type").append(card);

                    $("#insured_card_type").empty();
                    $("#insured_card_type").append(card);

                    $("#policy_holder_sex").empty();
                    $("#policy_holder_sex").append(sex);

                    $("#insured_sex").empty();
                    $("#insured_sex").append(sex);
                    var systemPayType = obj.data.payType;
                    var payType = "<option value=''>请选择支付方式</option>";
                    $.each(systemPayType, function (key, value) {
//                        alert(JSON.stringify(value));
                        payType += "<option value='" + value.dict_code + "'>" + value.dict_name //下拉框序言的循环数据
                            + "</option>";
                    });
                    var systemRelationship = obj.data.relationship;
                    var relationship = "";
                    $.each(systemRelationship, function (key, value) {
//                        alert(JSON.stringify(value));
                        relationship += "<input type=\"radio\" value='" + value.dict_code + "' name=\"insured_relationship\"><span style=\"color: firebrick\">" + value.dict_name + "</span>";
                    });
                    $("#pay_type").empty();
                    $("#pay_type").append(payType);

                    $("#relationshipRadios").empty();
                    $("#relationshipRadios").append(relationship);

                    var systemYesOrNo=obj.data.yesOrNo;
                    var yesOrNo="<option value=''>请选择</option>";
                    $.each(systemYesOrNo, function (key, value) {
                        //      alert(JSON.stringify(value));
                        yesOrNo += "<option value='"+value.dict_code+"'>" + value.dict_name //下拉框序言的循环数据
                            + "</option>";
                    });
                    $("#multiple_insurance_coverage").empty();
                    $("#multiple_insurance_coverage").append(yesOrNo);

                    $("#health_report").empty();
                    $("#health_report").append(yesOrNo);


                }
            })
            $.ajax({
                url: "insurance_policy/select_search_params",
                type: "post",
                dataType: "json",
                success: function (obj) {
                    var insuranceCompanys = obj.data.org
                 //   alert(JSON.stringify(insuranceCompanys));
                    var h = "<option value=''>请选择保险公司</option>";
                    $.each(insuranceCompanys, function (key, value) {
                        //alert(value.insuranceCompanyCode);
                        h += "<option value='" + value.insuranceCompanyId + "'>" + value.insuranceCompanyName //下拉框序言的循环数据
                            + "</option>";
                    });
                    $("#insuranceCompany").empty();
                    $("#insuranceCompany").append(h);
                },
                error: function (obj) {
                }
            })
            getAreaByPid("0", "sheng");
            InsuranceDepAI.formValidator();
            $('#addFormAIP').data('bootstrapValidator').isValid();
        });

        function selectdept() {
            var emp_no = $("#emp_no").val()
            if (emp_no.length==10){
                var team_id = ''
                $.ajax({
                    url:"/insuranceSalesInfo/selectInsuranceSalesOne",
                    data:{"insuranceSalerNo":emp_no},
                    type:"POST",
                    dataType:"JSON",
                    success:function(obj){
                        $("#order_taking_mechanism").val(obj.SALES_ORG_ID).trigger("change");
                        $("#sales_team").val(obj.SALES_TEAM_ID).trigger("change");
                        $("#emp_name").val(obj.INSURANCE_SALER)
                    },error: function (obj) {
                        alert("人员工号不存在！")
                    }



                })


            }
        }
        function sleep(n) {

            var start = new Date().getTime();

            while(true)  if(new Date().getTime()-start > n) break;

        }
        function teamanddepr() {
            alert(1)
        }

        function image() {
            $("#image").click();
        }

        function closeDlg() {
            $("#adddiv").modal('hide');
            $("#alertFile").modal('hide');
        }


        function upload() {
            var multipart = $("#fileName").val();
            if (multipart == "" || multipart == null) {
                alert("请先选择文件!");
                return;
            }
            $("#loadingModal").modal('show');
            //$('#loadingModal').modal('hide');
            $("#fileForm").ajaxSubmit({
                type: 'POST',
                url: 'insurance_policy/uploadFile',
                dataType: 'json',
                success: function (data) {
                    $('#loadingModal').modal('hide');
                    if (data.code = "200") {
                        changeVal(data)
                        closeDlg()
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
        function temporaryCommit() {

            $('#addFormAIP').data('bootstrapValidator').validate();//启用验证
            var flag = $('#addFormAIP').data('bootstrapValidator').isValid()//验证是否通过true/false
            if (!flag){
                return;
            }
            var insured_relationship =  $("input:radio[name='insured_relationship']:checked").val()
            if (insured_relationship == undefined ){
                alert("请选择与投保人关系")
                return;
            }
            var xzxxtablelen = $("#xzxxtable tr").length
            if (xzxxtablelen <= 1){
                $.alert({
                    title: '错误提示！',
                    content: '至少添加一条险种信息',
                    type: 'red'
                })
                return;
            }
            var beneficiarylen = $("#beneficiary tr").length
             for(var i =1;i<beneficiarylen;i++){
                var syr = ($("#beneficiary").find('tr').eq(i).children('td').eq(0).children('input').val());
                var syrzjlx = ($("#beneficiary").find('tr').eq(i).children('td').eq(1).children('select').val());
                var syrzjhm = ($("#beneficiary").find('tr').eq(i).children('td').eq(2).children('input').val());
                if(syr == '' || syrzjlx =='' || syrzjhm == ''){
                     $.alert({
                    title: '错误提示！',
                    content: '若录入受益人信息，受益人姓名，证件类型，证件号必填否则请删除',
                    type: 'red'
                })
                  return;
                }
            }
            // if (beneficiarylen <= 1){
            //     $.alert({
            //         title: '错误提示！',
            //         content: '至少添加一条险种受益人',
            //         type: 'red'
            //     })
            //     return;
            // }
            $("#commitType").val(2)
            $("#myModal").modal('show');
            var data = $("#addFormAIP").serialize();
            $.ajax({
                url: "insurance_policy/add_insurance_policy",
                data: data,
                type: "post",
                dataType: "json",
                success: function (obj) {
                    if (obj.code=="200"){
                        alert("暂存成功")
                        $("#myModal").modal('hide');
                        commCloseTab('insurancePolicy:add')
                        createAddProcessTab('insurancePolicy:list','')
                    }else if (obj.code=="4001") {
                        alert("投保单号重复")
                        $("#myModal").modal('hide');
                    }else if(obj.code=="0001"){
                        alert(obj.error)
                        $("#myModal").modal('hide');
                    }
                    else {
                        alert("暂存失败")
                        $("#myModal").modal('hide');
                    }
                },
                error: function (obj) {
                    alert("系统异常")
                    $("#myModal").modal('hide');
                }
            })
        }


        function bxqd() {
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
            if(a3 == '' || a3 == null){
                $.alert({
                    title: '错误提示！',
                    content: '请输入保额',
                    type: 'red'
                })
                return;
            }

            var isMoney = /^(([1-9][0-9]*)|(([0]\.\d{1,2}|[1-9][0-9]*\.\d{1,2})))$/;
            var regExp = new RegExp(isMoney);
            if(!regExp.test(a3)){
                $.alert({
                    title: '错误提示！',
                    content: '请输入正确保额',
                    type: 'red'
                })
                return;
            }

            var a4 = $("#a4").val();
            if(a4 == '' || a4 == null){
                $.alert({
                    title: '错误提示！',
                    content: '请输入保费',
                    type: 'red'
                })
                return;
            }
            if(!regExp.test(a4)){
                $.alert({
                    title: '错误提示！',
                    content: '请输入正确保费',
                    type: 'red'
                })
                return;
            }
           /* var a5 = $("#a5").val();
           if(a5 == ''){
                $.alert({
                    title: '错误提示！',
                    content: '请输入份数',
                    type: 'red'
                })
                return;
            }*/
            var a6 = $("#a6").val();
            if(a6 == ''){
                $.alert({
                    title: '错误提示！',
                    content: '请选择缴费方式',
                    type: 'red'
                })
                return;
            }
            var a6t =$("#a6 option:selected").text();
            var a7 = $("#a7").val();
            var a7t = $("#a7t").val();
            var a8 = $("#a8").val();
            if(a8 == ''){
                $.alert({
                    title: '错误提示！',
                    content: '请输入缴费 年期/年龄',
                    type: 'red'
                })
                return;
            }
            var a9 = $("#a9").val();

            var a9t = $("#a9t").val();
            var a10 = $("#a10").val();
            if(a10 == ''){
                $.alert({
                    title: '错误提示！',
                    content: '请输入保险期间',
                    type: 'red'
                })
                return;
            }
            var a11 = $("#a11").val();
            var a11t = $("#a11t").val();
            var a12 = $("#a12").val();
            var a13 = $("#a13").val();
            if (upOrIn == 'insert') {
            $("#bxcp").append("<tr>" +
                "<td>" + a1 + "<input value='" + a1 + "' name='a1' type='hidden'></td>" +
                "<td>" + a2t + "<input value='" + a2 + "' name='a2' type='hidden'>" + "</td>" +
                "<td>" + a11t + "<input value='" + a11 + "' name='a11' type='hidden'></td>" +
                "<td>" + a3 + "<input value='" + a3 + "' name='a3' type='hidden'></td>" +
                /*"<td>" + a4 + "<input value='" + a4 + "' name='a4' type='hidden'><input value='" + a5 + "' name='a5' type='hidden'></td>" +*/
                "<td>" + a4 + "<input value='" + a4 + "' name='a4' type='hidden'></td>" +
                "<td>"+ a9t + "<input value='" + a9 + "' name='a9' type='hidden'></td>"+
                "<td>" + a6t + "<input value='" + a6 + "' name='a6' type='hidden'></td>" +
                "<td>" + a7t +  "<input value='" + a7 + "' name='a7' type='hidden'><input value='" + a8 + "' name='a8' type='hidden'><input value='" + a10 +"' name='a10' type='hidden'><input value='" + a12 +"' name='a12' type='hidden'><input value='" + a13 +"' name='a13' type='hidden'></td>"+
                "<td><a href='#' id='delBx' style=\"color: red\" onclick='del_tr(this)' >删除</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href='#' id='UptBx' style=\"color: green\" onclick='Upt_tr(this)' >修改</a></td></tr>")
            $("#adddiv").modal('hide');
            }else{
                $("#bxcp").find("tr").eq(upOrIn).html("<td>" + a1 + "<input value='" + a1 + "' name='a1' type='hidden'></td>" +
                    "<td>" + a2t + "<input value='" + a2 + "' name='a2' type='hidden'>" + "</td>" +
                    "<td>" + a11t + "<input value='" + a11 + "' name='a11' type='hidden'></td>" +
                    "<td>" + a3 + "<input value='" + a3 + "' name='a3' type='hidden'></td>" +
                   /* "<td>" + a4 + "<input value='" + a4 + "' name='a4' type='hidden'><input value='" + a5 + "' name='a5' type='hidden'></td>" +*/
                    "<td>" + a4 + "<input value='" + a4 + "' name='a4' type='hidden'></td>" +
                    "<td>"+ a9t + "<input value='" + a9 + "' name='a9' type='hidden'></td>"+
                    "<td>" + a6t + "<input value='" + a6 + "' name='a6' type='hidden'></td>" +
                    "<td>" + a7t +  "<input value='" + a7 + "' name='a7' type='hidden'><input value='" + a8 + "' name='a8' type='hidden'><input value='" + a10 +"' name='a10' type='hidden'><input value='" + a12 +"' name='a12' type='hidden'><input value='" + a13 +"' name='a13' type='hidden'></td>"+
                    "<td><a href='#' id='delBx' style=\"color: red\" onclick='del_tr(this)' >删除</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href='#' id='UptBx' style=\"color: green\" onclick='Upt_tr(this)' >修改</a></td>")

                $("#adddiv").modal('hide');
            }
        }

        var trSize = 0;

        function addBeneficiary() {
            var cardt = $("#cardTest").val()
            var sext = $("#sexTest").val()
            var k = "<tr>" +
                "<td><input type='test' name='b1'></td>" +
                "<td><select id='b2' name='b2' style=\"width: 200px\"  class=\"form-control form-control-static\">" +
                cardt
                +"</select></td>" +
                "<td><input name='b3' type='test'></td>" +
                "<td><select id='b4' style=\"width: 200px\" class=\"form-control form-control-static\" name='b4' type='test'>" +
                sext
                +"</select></td>" +
                "<td><input name='b5' type='date'></td>" +
                "<td><input name='b6' type='test'></td>" +
                "<td><input name='b7' type='test'></td>" +
                "<td><input name='b8' type='test'></td>" +
                "<td width='40000px'>" +
                "<a href='#' style='color: red;width: 200px' onclick='del_tr(this)'>" + "删除受益人" + "</a>" +
                "<a href='#' style='color: green;width: 200px' onclick='copy(this)'>" + "同投保人" + "</a></td>" +
                "</tr>"
            $("#beneficiary").append(k)
           /* getSysDict("b2","CARD")
            getSysDict("b4","SEX")*/
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
            $("#upOrin").val('insert');
           $("#a1").val('');
            $("#a2").val('');
            $("#a2").val();
            $("#a3").val('');
            $("#a4").val('');
           /* $("#a5").val('');*/
            $("#a6").val('');
            $("#a6").val('');
            $("#a7").val('');
            $("#a7t").val('');
            $("#a8").val('');
            $("#a9").val('');
            $("#a9t").val('');
            $("#a10").val('');
            $("#a11").val('');
            $("#a11t").val('');
            $("#a12").val('');
            $("#a13").val('');
            $("#adddiv").modal('show')
        }

        function alertFile() {
            $("#alertFile").modal('show')
        }

        function changeVal(data) {
            var file = $("#fileName").val();
            var fileName = getFileName(file);
            var dateurl = '';
            if(null!=data){
                dateUrl = data.fastPath;
            }
            function getFileName(o) {
                var pos = o.lastIndexOf("\\");
                return o.substring(pos + 1);
            }
           var ts = $("#images tr").length

            data.newFileName
             var url = 'http://10.10.10.173/'+dateUrl;
            // $("#images").append("<tr><td>" + ts + "</td><td>" + "<a href="+url+"><font color='blue'>"+fileName+"</font></a><input value='" + fileName + "' name='fileName' type='hidden'><input value='" + data.fastPath + "' name='fastPath' type='hidden'></td><td onclick='del_tr(this)'>" + "删除" + "</td></tr>")
            // $("#images").append("<tr><td>" + ts + "</td><td>" + "<a href="+url+"><font color='blue'>"+fileName+"</font></a><input value='" + fileName + "' name='fileName' type='hidden'><input value='" + data.fastPath + "' name='fastPath' type='hidden'></td><td onclick='showImg(dateUrl)'>" + "预览" + "</td><td onclick='del_tr(this)'>" + "删除" + "</td></tr>")
            //$("#images").append("<tr><td><a href='javascript:void(0);' onclick='showImg(\'+dateUrl+\');' >"+fileName+"</a></td></tr>");
            $("#images").append("<tr name='imageTrName'><td>" + ts + "</td><td onclick='showImg(&quot;"+data.fastPath+"&quot;)' style='color: blue'>" + fileName + "<input value='" + fileName + "' name='fileName' type='hidden'><input value='" + data.fastPath + "' name='fastPath' type='hidden'></td><td onclick='del_tr(this)'>" + "删除" + "</td></tr>")
            // $("#images").append("<tr><td onclick='showImg(dateUrl)'>"+fileName+"</td></tr>");

        }

        function del_tr(obj) {
            var index = $(obj).parents("tr").index();
            $(obj).parents("tr").remove();
            resetImagesSortNo();
        }

        //删除影像件时候重新排序
        function resetImagesSortNo() {
            $("#images tr[name='imageTrName']").each(function(index, value){
                $(this).find("td").eq(0).html(index+1);
            });
        }
        function showImg(value) {
            //'http://10.10.10.173/'
            var url = value;
            var fullUrl = $("#fastInnerUrl").val()+url;
            if(isEmpty(url)){
                $.alert({
                    title: '提示信息！',
                    content: '请先上传资料!',
                    type: 'red'
                });
                return false;
            }
            window.open(fullUrl,"_blank");
            // window.open(url);

        }

        function Upt_tr(obj) {
            var index = $(obj).parents("tr").index()
            var a1 = $("#bxcp").find("tr").eq(index).find("input[name = 'a1']").val()
            var a2 = $("#bxcp").find("tr").eq(index).find("input[name = 'a2']").val()
            var a3 = $("#bxcp").find("tr").eq(index).find("input[name = 'a3']").val()
            var a4 = $("#bxcp").find("tr").eq(index).find("input[name = 'a4']").val()
           /* var a5 = $("#bxcp").find("tr").eq(index).find("input[name = 'a5']").val()*/
            var a6 = $("#bxcp").find("tr").eq(index).find("input[name = 'a6']").val()
            var a7 = $("#bxcp").find("tr").eq(index).find("input[name = 'a7']").val()
            var a8 = $("#bxcp").find("tr").eq(index).find("input[name = 'a8']").val()
            var a9 = $("#bxcp").find("tr").eq(index).find("input[name = 'a9']").val()
            var a10 = $("#bxcp").find("tr").eq(index).find("input[name = 'a10']").val()
            var a11 = $("#bxcp").find("tr").eq(index).find("input[name = 'a11']").val()
            var a12 = $("#bxcp").find("tr").eq(index).find("input[name = 'a12']").val()
            var a13 = $("#bxcp").find("tr").eq(index).find("input[name = 'a13']").val()
            $("#adddiv").modal('show')

            $("#a2").val(a2)
            commSelectProductCode("a1","a6","a7","a7t","a9","a9t","a11","a11t","a12", a2)
            $("#a3").val(a3)
            $("#a4").val(a4)
           /* $("#a5").val(a5)*/
            $("#a8").val(a8)
            $("#a10").val(a10)
            $("#a6").val(a6).trigger("change");
            $("#upOrIn").val(index);
        }

        //.eq(i).val(cardType).trigger("change");
        function copy(obj) {
            var index = $(obj).parents("tr").index();
            $(obj).closest("tr").find("td").eq(0).children().val($("#policy_holder_name").val())
            $(obj).closest("tr").find("td").eq(1).children().val($("#card_type").val()).trigger("change");
            $(obj).closest("tr").find("td").eq(2).children().val($("#card_no").val())
            $(obj).closest("tr").find("td").eq(3).children().val($("#policy_holder_sex").val()).trigger("change")
            $(obj).closest("tr").find("td").eq(4).children().val($("#policy_holder_birthday").val())

        }
        var InsuranceDepAI = function () {
            return {
                //添加
                add:  function () {

                    $('#addFormAIP').data('bootstrapValidator').validate();//启用验证
                    var flag = $('#addFormAIP').data('bootstrapValidator').isValid()//验证是否通过true/false
                    if (!flag){
                        return;
                    }
                    var insured_relationship =  $("input:radio[name='insured_relationship']:checked").val()
                    if (insured_relationship == undefined ){
                        alert("请选择与投保人关系")
                        return;
                    }

                    var xzxxtablelen = $("#xzxxtable tr").length
                                if (xzxxtablelen <= 1){
                                    $.alert({
                                        title: '错误提示！',
                                        content: '至少添加一条险种信息',
                                        type: 'red'
                                    })
                                    return;
                                }
                                var beneficiarylen = $("#beneficiary tr").length
                                 for(var i =1;i<beneficiarylen;i++){
                                    var syr = ($("#beneficiary").find('tr').eq(i).children('td').eq(0).children('input').val());
                                    var syrzjlx = ($("#beneficiary").find('tr').eq(i).children('td').eq(1).children('select').val());
                                    var syrzjhm = ($("#beneficiary").find('tr').eq(i).children('td').eq(2).children('input').val());
                                    if(syr == '' || syrzjlx =='' || syrzjhm == ''){
                                         $.alert({
                                        title: '错误提示！',
                                        content: '若录入受益人信息，受益人姓名，证件类型，证件号必填否则请删除',
                                        type: 'red'
                                    })
                                      return;
                                    }
                                }
                                // if (beneficiarylen <= 1){
                                //     $.alert({
                                //         title: '错误提示！',
                                //         content: '至少添加一条险种受益人',
                                //         type: 'red'
                                //     })
                                //     return;
                                // }
                    $("#commitType").val(1)
                    $("#myModal").modal('show');
                    $.ajax({
                        url: "insurance_policy/add_insurance_policy",
                        data: $("#addFormAIP").serialize(),
                        type: "post",
                        dataType: "json",
                        success: function (obj) {
                            if (obj.code=="200"){
                                 $.alert({
                                     title: '提示信息！',
                                     content: '添加成功!',
                                     type: 'blue'
                                 });
                                $("#myModal").modal('hide');
                                commCloseTab('insurancePolicy:add')
                                createAddProcessTab('insurancePolicy:list','')
                            }else if (obj.code=="4001") {
                                 $.alert({
                                 title: '提示信息！',
                                 content: '投保单号重复!',
                                 type: 'red'
                             });
                                $("#myModal").modal('hide');
                            }else if(obj.code=="0001"){
                                 $.alert({
                                 title: '提示信息！',
                                 content: '新增失败： '+obj.error,
                                 type: 'red'
                             });
                                $("#myModal").modal('hide');
                            } else {
                                 $.alert({
                                 title: '提示信息！',
                                 content: '新增失败！',
                                 type: 'red'
                             });
                                $("#myModal").modal('hide');
                            }
                        },
                        error: function (obj) {
                             $.alert({
                                 title: '提示信息！',
                                 content: '系统异常！',
                                 type: 'red'
                             });
                            $("#myModal").modal('hide');
                        }
                    })
                },

                //表单验证
                formValidator: function () {
                    $("#addFormAIP").bootstrapValidator({
                        fields: {
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
                                    },
                                    regexp: {
                                        regexp: /^\d+(\.\d+)?$/,
                                        message: '请输入数字'
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
                            // multiple_insurance_coverage:{
                            //     validators:{
                            //         notEmpty: {
                            //             message: "是否多次投保不能为空"
                            //         }
                            //     }
                            // },
                            // health_report:{
                            //     validators:{
                            //         notEmpty: {
                            //             message: "是否有健康须知不能为空"
                            //         }
                            //     }
                            // },
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
                                    },regexp: {
                                        regexp: /^\d+(\.\d+)?$/,
                                        message: '请输入数字'
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
                            }, policy_holder_name:{
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
                            insured_name:{
                                validators:{
                                    notEmpty: {
                                        message: "被保人姓名不能为空"
                                    },

                                }
                            },
                            insured_tel:{
                                validators:{
                                    notEmpty: {
                                        message: "被保人移动电话不能为空"
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
                            },
                            policy_holder_occupation:{
                                validators: {
                                    notEmpty: {
                                        message: "投保人职业不能为空"
                                    }
                                }
                            },
                            policy_holder_bank_account:{
                                validators:{
                                    regexp: {
                                        regexp: /^\d+(\.\d+)?$/,
                                        message: '请输入数字'
                                    },
		                            stringLength:{
		                                max:20,
		                                message:'字符长度不能超过20'
		                            }
                                }
                            },
                            policy_holder_zip_code:{
                                validators:{
                                   regexp: {
                                        regexp: /^\d+(\.\d+)?$/,
                                        message: '请输入数字'
                                    }
                                }
                            },
                            policy_holder_liabilities:{
                                validators:{
                                    regexp: {
                                        regexp: /^\d+(\.\d+)?$/,
                                        message: '请输入数字'
                                    }
                                }
                            },
                            insured_zip_code:{
                                validators:{
                                    regexp: {
                                        regexp: /^\d+(\.\d+)?$/,
                                        message: '请输入数字'
                                    }
                                }
                            },
                            insured_liabilities:{
                                validators:{
                                    regexp: {
                                        regexp: /^\d+(\.\d+)?$/,
                                        message: '请输入数字'
                                    }
                                }
                            },
                            card_type:{
                                validators:{
                                    notEmpty: {
                                        message: "投保人证件类型不能为空"
                                    },

                                }
                            },
                            insured_card_type:{
                                validators:{
                                    notEmpty: {
                                        message: "被投保人证件类型不能为空"
                                    },

                                }
                            },
                            card_no:{
                                validators:{
                                    notEmpty: {
                                        message: "投保人证件号码不能为空"
                                    },
                                    callback:{
                                        message: '投保人证件号格式不正确',
                                        callback: function(value, validator){
                                            var sel=document.getElementById("card_type");
                                            var index = sel.selectedIndex; // 选中索引
							                var cardType= sel.options[index].value;//要的值
                                            // if(null==cardType){
                                            //     message:'请先输入证件类型'
                                            // }
                         	               //  console.log(cardType);
                         	                var cardNo = value;
                         	                var regIdCard = /^(^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$)|(^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[Xx])$)$/;
                         	                var regHkCard = /^([A-Z]\d{6,10}(\(\w{1}\))?)$/;
                         	                var regPassPortCard = /^([a-zA-z]|[0-9]){5,17}$/;
                         	                var regOfficerCard =  /^[\u4E00-\u9FA5](字第)([0-9a-zA-Z]{4,8})(号?)$/;
                         	                if(cardType=='1' || cardType=='5'){
                                                if (regIdCard.test(cardNo)) {//身份证
                                                    // alert ("身份证验证通过");
                                                    return true;
                                                }else{
                                                    // alert ("身份证验证失败");
                                                    return false;
                                                }
							                }else if(cardType=='2'){
                         	                    if (regHkCard.test(cardNo)) {//港澳通行证
                                                    return true;
                                                }else{
                                                    return false;
                                                }
                                            }else if(cardType=='3'){
                         	                    if (regPassPortCard.test(cardNo)) {//护照
                                                    return true;
                                                }else{
                                                    return false;
                                                }
                                            }else if(cardType=='4'){
                         	                    if (regOfficerCard.test(cardNo)) {//军官证
                                                    return true;
                                                }else{
                                                    return false;
                                                }
                                            }
                                        }
                                    }
                                }
                            },
                            insured_card_no:{
                                validators:{
                                    notEmpty: {
                                        message: "被投保人证件号码不能为空"
                                    },
                                    callback:{
                                        message: '被投保人证件号格式不正确',
                                        callback: function(value, validator){
                                            var sel=document.getElementById("insured_card_type");
                                            var index = sel.selectedIndex; // 选中索引
							                var insuredCardType= sel.options[index].value;//要的值
                                            // if(null==cardType){
                                            //     message:'请先输入证件类型'
                                            // }
                         	               //  console.log(cardType);
                         	                var cardNo = value;
                         	                var regIdCard = /^(^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$)|(^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[Xx])$)$/;
                         	                var regHkCard = /^([A-Z]\d{6,10}(\(\w{1}\))?)$/;
                         	                var regPassPortCard = /^([a-zA-z]|[0-9]){5,17}$/;
                         	                var regOfficerCard =  /^[\u4E00-\u9FA5](字第)([0-9a-zA-Z]{4,8})(号?)$/;
                         	                if(insuredCardType=='1' || insuredCardType=='5'){
                                                if (regIdCard.test(cardNo)) {//身份证
                                                    // alert ("身份证验证通过");
                                                    return true;
                                                }else{
                                                    // alert ("身份证验证失败");
                                                    return false;
                                                }
							                }else if(insuredCardType=='2'){
                         	                    if (regHkCard.test(cardNo)) {//港澳通行证
                                                    return true;
                                                }else{
                                                    return false;
                                                }
                                            }else if(insuredCardType=='3'){
                         	                    if (regPassPortCard.test(cardNo)) {//护照
                                                    return true;
                                                }else{
                                                    return false;
                                                }
                                            }else if(insuredCardType=='4'){
                         	                    if (regOfficerCard.test(cardNo)) {//军官证
                                                    return true;
                                                }else{
                                                    return false;
                                                }
                                            }
                                        }
                                    }

                                }
                            }


                        }
                    });
                }
            }
        }();
        function clo() {
            commCloseTab('insurancePolicy:add')
            createAddProcessTab('insurancePolicy:list','')
        }
    </script>
</head>
<body>
<div class="container">
    <input type="hidden" id="fastInnerUrl" name="fastInnerUrl" value="${fastInnerUrl}" />
    <form class="form-horizontal" id="addFormAIP" method="post" enctype="application/x-www-form-urlencoded">
        <div  class="btn-toolbar" style="overflow:scroll;background: slategray;">
            <span class="title" style="color: cornsilk">投保单基本信息</span>
            <input type="hidden"  id="commitType" name="commitType" ></input><%--提交类型1:提交2：暂存--%>
            <input type="hidden" id="updateType" name="updateType" value="add"></input><%--控制执行修改或者添加--%>

        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">投保单号 *</label>
            <div class="col-md-3 ">

                <input type="text" class="form-control form-control-static" id="exampleInputName1"
                       name="exampleInputName1" placeholder="投保单号">
            </div>
            <label class="col-md-2 control-label">投保公司 *</label>
            <div class="col-md-3 ">

                <select class="form-control form-control-static" id="insuranceCompany" name="insuranceCompany">
                    <option value="">-请选择-</option>
                </select>
            </div>
        </div>
        <div class="form-group" style="margin-top:20px;">
            <label class="col-md-2 control-label">总保费（元）*</label>
            <div class="col-md-3 ">

                <input type="text" class="form-control form-control-static" name="exampleInputName3"
                       id="exampleInputName3" placeholder="总保费（元）">
            </div>
            <label class="col-md-2 control-label">投保日期 *</label>
            <div class="col-md-3 ">

                <input type="date" class="form-control form-control-static" name="exampleInputName4"
                       id="exampleInputName4" placeholder="投保单号">
            </div>
        </div>
        <div class="btn-toolbar" style="overflow:scroll;background: slategray;">
            <span class="title" style="color: cornsilk">投保人资料</span>
        </div>
        <div class="form-group" style="margin-top:20px;">
            <label class="col-md-2 control-label">证件类型 *</label>
            <div class="col-md-3 ">
                <select id="card_type" name="card_type" class="form-control form-control-static">

                </select>
            </div>
            <label class="col-md-2 control-label">证件号码 *</label>
            <div class="col-md-3 ">
                <input type="text" class="form-control form-control-static" name="card_no" id="card_no"
                       placeholder="证件号码">
            </div>
        </div>
        <div class="form-group" style="margin-top:20px;">
            <label class="col-md-2 control-label">投保人姓名 *</label>
            <div class="col-md-3 ">
                <input type="text" class="form-control form-control-static" name="policy_holder_name"
                       id="policy_holder_name" placeholder="投保人姓名">
            </div>
            <label class="col-md-2 control-label">移动电话 *</label>
            <div class="col-md-3 ">
                <input type="text" class="form-control form-control-static" name="policy_holder_tel"
                       id="policy_holder_tel" placeholder="移动电话">
            </div>
        </div>
        <div class="form-group" style="margin-top:20px;">
            <label class="col-md-2 control-label">性别 *</label>
            <div class="col-md-3 ">
                <select class="form-control form-control-static" name="policy_holder_sex"
                        id="policy_holder_sex"></select>
            </div>
            <label class="col-md-2 control-label">投保人出生日期 *</label>
            <div class="col-md-3 ">
                <input type="date" class="form-control form-control-static" name="policy_holder_birthday"
                       id="policy_holder_birthday" placeholder="投保人出生日期">
            </div>
        </div>
        <div class="form-group" style="margin-top:20px;">
            <label class="col-md-2 control-label">年收入 *</label>
            <div class="col-md-3 ">
                <input type="text" class="form-control form-control-static" name="policy_holder_annual_income"
                       id="policy_holder_annual_income" placeholder="年收入">
            </div>
            <label class="col-md-2 control-label">投保人常住住址 *</label>
            <div class="col-md-3 ">
                <input type="text" class="form-control form-control-static" name="policy_holder_permanent_address"
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
                <input type="text" class="form-control form-control-static" name="policy_holder_opener_name"
                       id="policy_holder_opener_name" placeholder="开户人姓名">
            </div>
        </div>
        <div class="form-group" style="margin-top:20px;">
            <label class="col-md-2 control-label">银行名称</label>
            <div class="col-md-3 ">
                <input type="text" class="form-control form-control-static" name="policy_holder_bank_name"
                       id="policy_holder_bank_name" placeholder="银行名称">
            </div>
            <label class="col-md-2 control-label">银行账号</label>
            <div class="col-md-3 ">
                <input type="text" class="form-control form-control-static" name="policy_holder_bank_account"
                       id="policy_holder_bank_account" placeholder="银行账号">
            </div>
        </div>
        <div class="form-group" style="margin-top:20px;">
            <label class="col-md-2 control-label">学历</label>
            <div class="col-md-3 ">
                <input type="text" class="form-control form-control-static" name="policy_holder_education"
                       id="policy_holder_education" placeholder="学历">
            </div>
            <label class="col-md-2 control-label">婚姻状态</label>
            <div class="col-md-3 ">
                <select type="text" class="form-control form-control-static" name="policy_holder_marital_status"
                        id="policy_holder_marital_status" placeholder="婚姻状态"></select>
            </div>
        </div>
        <div class="form-group" style="margin-top:20px;">
            <label class="col-md-2 control-label">投保人公司地址</label>
            <div class="col-md-3 ">
                <input type="text" class="form-control form-control-static" name="policy_holder_company_address"
                       id="policy_holder_company_address" placeholder="投保人公司地址">
            </div>
            <label class="col-md-2 control-label">办公/家庭电话</label>
            <div class="col-md-3 ">
                <input type="text" class="form-control form-control-static" name="policy_holder_home_phone"
                       id="policy_holder_home_phone" placeholder="办公/家庭电话">
            </div>
        </div>
        <div class="form-group" style="margin-top:20px;">
            <label class="col-md-2 control-label">电子邮箱</label>
            <div class="col-md-3 ">
                <input type="text" class="form-control form-control-static" name="policy_holder_email"
                       id="policy_holder_email" placeholder="电子邮箱">
            </div>
            <label class="col-md-2 control-label">家庭住址邮编</label>
            <div class="col-md-3 ">
                <input type="text" class="form-control form-control-static" name="policy_holder_zip_code"
                       id="policy_holder_zip_code" placeholder="家庭住址邮编">
            </div>
        </div>
        <div class="form-group" style="margin-top:20px;">
            <label class="col-md-2 control-label">户籍地址</label>
            <div class="col-md-3 ">
                <input type="text" class="form-control form-control-static" name="policy_holder_permanent_address_add"
                       id="policy_holder_permanent_address_add" placeholder="户籍地址">
            </div>
            <label class="col-md-2 control-label">负债和贷款（万元）</label>
            <div class="col-md-3 ">
                <input type="text" class="form-control form-control-static" name="policy_holder_liabilities"
                       id="policy_holder_liabilities" placeholder="负债和贷款（万元）">
            </div>
        </div>
        <div class="form-group" style="margin-top:20px;">
            <label class="col-md-2 control-label">投保人执业编号</label>
            <div class="col-md-3 ">
                <input type="text" class="form-control form-control-static" name="policy_holder_occupation_code"
                       id="policy_holder_occupation_code" placeholder="投保人执业编号">
            </div>
            <label class="col-md-2 control-label">投保人职业 *</label>
            <div class="col-md-3 ">
                <input type="text" class="form-control form-control-static" name="policy_holder_occupation"
                       id="policy_holder_occupation" placeholder="投保人职业">
            </div>
        </div>
        <div  class="btn-toolbar" style="overflow:scroll;background: slategray;">
            <span class="title" style="color: cornsilk">被投保人资料</span>
            <span style="color: cornsilk">与投保人关系</span>
            <span id="relationshipRadios"></span>
        </div>
        <div class="form-group" style="margin-top:20px;">
            <label class="col-md-2 control-label">证件类型 *</label>
            <div class="col-md-3 ">
                <select name="insured_card_type" id="insured_card_type" class="form-control form-control-static">

                </select>
            </div>
            <label class="col-md-2 control-label">证件号码 *</label>
            <div class="col-md-3 ">
                <input type="text" class="form-control form-control-static" name="insured_card_no" id="insured_card_no"
                       placeholder="证件号码">
            </div>
        </div>
        <div class="form-group" style="margin-top:20px;">
            <label class="col-md-2 control-label">被保人姓名 *</label>
            <div class="col-md-3 ">
                <input type="text" class="form-control form-control-static" name="insured_name" id="insured_name"
                       placeholder="被保人姓名">
            </div>
            <label class="col-md-2 control-label">移动电话 *</label>
            <div class="col-md-3 ">
                <input type="text" class="form-control form-control-static" name="insured_tel" id="insured_tel"
                       placeholder="移动电话">
            </div>
        </div>
        <div class="form-group" style="margin-top:20px;">
            <label class="col-md-2 control-label">性别 *</label>
            <div class="col-md-3 ">
                <select type="text" class="form-control form-control-static" name="insured_sex" id="insured_sex"
                        placeholder="性别"></select>
            </div>
            <label class="col-md-2 control-label">被保人出生日期 *</label>
            <div class="col-md-3 ">
                <input type="date" class="form-control form-control-static" name="insured_birthday"
                       id="insured_birthday" placeholder="被保人出生日期">
            </div>
        </div>
        <div class="form-group " style="margin-top:20px;">
            <label class="col-md-2 control-label">年收入 *</label>
            <div class="col-md-3">
                <input type="text" class="form-control form-control-static" name="insured_annual_income"
                       id="insured_annual_income" placeholder="年收入">
            </div>
            <label class="col-md-2 control-label">被保人常住住址 *</label>
            <div class="col-md-3 ">
                <input type="text" class="form-control form-control-static" name="insured_permanent_address"
                       id="insured_permanent_address" placeholder="被保人常住住址">
            </div>
        </div>

        <div class="form-group" style="margin-top:20px;">
            <label class="col-md-2 control-label">是否多次投保</label>
            <div class="col-md-3 ">
                <select name="multiple_insurance_coverage" id="multiple_insurance_coverage"
                        class="form-control form-control-static">

                </select>
            </div>
            <label class="col-md-2 control-label">是否有健康告知</label>
            <div class="col-md-3 ">
                <select name="health_report" id="health_report" class="form-control form-control-static">

                </select>
            </div>
        </div>
        <div class="form-group" style="margin-top:20px;">
            <label class="col-md-2 control-label">学历</label>
            <div class="col-md-3 ">
                <input type="text" class="form-control form-control-static" name="insured_education"
                       id="insured_education" placeholder="学历">
            </div>
            <label class="col-md-2 control-label">婚姻状态</label>
            <div class="col-md-3 ">
                <select type="text" class="form-control form-control-static" name="insured_marital_status"
                        id="insured_marital_status" placeholder="婚姻状态"></select>
            </div>
        </div>

        <div class="form-group" style="margin-top:20px;">
            <label class="col-md-2 control-label">被保人公司地址</label>
            <div class="col-md-3 ">
                <input type="text" class="form-control form-control-static" name="insured_company_address"
                       id="insured_company_address" placeholder="投保人公司地址">
            </div>
            <label class="col-md-2 control-label">办公/家庭电话</label>
            <div class="col-md-3 ">
                <input type="text" class="form-control form-control-static" name="insured_home_phone"
                       id="insured_home_phone" placeholder="办公/家庭电话">
            </div>
        </div>
        <div class="form-group" style="margin-top:20px;">
            <label class="col-md-2 control-label">电子邮箱</label>
            <div class="col-md-3 ">
                <input type="text" class="form-control form-control-static" name="insured_email" id="insured_email"
                       placeholder="电子邮箱">
            </div>
            <label class="col-md-2 control-label">家庭住址邮编</label>
            <div class="col-md-3 ">
                <input type="text" class="form-control form-control-static" name="insured_zip_code"
                       id="insured_zip_code" placeholder="家庭住址邮编">
            </div>
        </div>
        <div class="form-group" style="margin-top:20px;">
            <label class="col-md-2 control-label">户籍地址</label>
            <div class="col-md-3 ">
                <input type="text" class="form-control form-control-static" name="insured_permanent_address_add"
                       id="insured_permanent_address_add" placeholder="户籍地址">
            </div>
            <label class="col-md-2 control-label">负债和贷款（万元）</label>
            <div class="col-md-3 ">
                <input type="text" class="form-control form-control-static" name="insured_liabilities"
                       id="insured_liabilities" placeholder="负债和贷款（万元）">
            </div>
        </div>
        <div class="form-group" style="margin-top:20px;">
            <label class="col-md-2 control-label">被保人执业编号</label>
            <div class="col-md-3 ">
                <input type="text" class="form-control form-control-static" name="insured_occupation_code"
                       id="insured_occupation_code" placeholder="被保人执业编号">
            </div>
            <label class="col-md-2 control-label">被保人职业</label>
            <div class="col-md-3 ">
                <input type="text" class="form-control form-control-static" name="insured_occupation"
                       id="insured_occupation" placeholder="被保人职业">
            </div>
        </div>
        <div  class="btn-toolbar" style="overflow:scroll;background: slategray;">
            <span class="title" style="color: cornsilk">代理人信息</span>
        </div>
  <%--      <div class="form-group" style="margin-top:20px;">
            <label class="col-md-2 control-label">合作渠道代码</label>
            <div class="col-md-3 ">
                <input type="text" class="form-control form-control-static" name="channels_of_cooperation"
                       id="channels_of_cooperation" placeholder="户做渠道代码">
            </div>

        </div>--%>
        <div class="form-group" style="margin-top:20px;">
            <label class="col-md-2 control-label">人员工号 *</label>
            <div class="col-md-3 ">
                <input  type="text" class="form-control form-control-static" name="emp_no" id="emp_no"
                       placeholder="人员工号" onkeyup="selectdept()">
            </div>
            <label class="col-md-2 control-label">代理人姓名 *</label>
            <div class="col-md-3 ">
                <input type="text" class="form-control form-control-static" name="emp_name" id="emp_name"
                       placeholder="代理人姓名" readonly="readonly">
            </div>
        </div>
        <div class="form-group" style="margin-top:20px;">
            <label class="col-md-2 control-label">接单机构*</label>
            <div class="col-md-3 ">
                <select style="background-color: #EEEEEE;"  class="form-control form-control-static" name="order_taking_mechanism"
                         id="order_taking_mechanism" placeholder="接单机构"></select>
            </div>
            <label class="col-md-2 control-label">销售团队*</label>
            <div class="col-md-3 ">
                <select style="background-color: #EEEEEE;" class="form-control form-control-static" name="sales_team" id="sales_team"
                         placeholder="销售团队"></select>
            </div>
        </div>

        <div  class="btn-toolbar" style="overflow:scroll;background: slategray;">
            <span class="title" style="color: cornsilk">投保单影像信息</span>
            <a href="#" id="file" onclick="alertFile()" style="color: blue">添加影像件信息</a>
        </div>
        <div style="overflow-x:auto;">
            <table class="table table-hover table-striped table-condensed table-bordered"
                   style="width:1200px;font-size:13px;" id="images">
                <tr>
                    <td>影像件序列</td>
                    <td>影像件名称</td>
                    <td>操作</td>
                </tr>
            </table>
        </div>
        <div class="btn-toolbar" style="overflow:scroll;background: slategray;">
            <span class="title" style="color: cornsilk">险种信息</span>

        </div>
        <div style="overflow-x:auto;">
            <table class="table table-hover table-striped table-condensed table-bordered"
                   style="width:1200px;font-size:13px;"  id="xzxxtable">
                <tr>
                    <td>产品代码</td>
                    <td>产品名称</td>
                    <td>主副险标志</td>
                    <td>保额</td>
                    <td>保费</td>
                    <td>保险期间</td>
                    <td>缴费方式</td>
                    <td>缴费年期</td>
                    <td><a style="color: red" href="#" onclick="addInsurance()">添加</a></td>
                </tr>
            <tbody id="bxcp">


            </tbody>
            </table>
        </div>
        <div  class="btn-toolbar" style="overflow:scroll;background: slategray;">
            <span style="color: cornsilk">险种受益人</span>
        </div>
        <div style="overflow-x:auto;">
            <table class="table table-hover table-striped table-condensed table-bordered"
                   style="width:1200px;font-size:13px;" id="beneficiary">
                <tr>
                    <td>险种受益人</td>
                    <td>证件类型</td>
                    <td>证件号码</td>
                    <td>性别</td>
                    <td>出生日期</td>
                    <td>受益顺序</td>
                    <td>受益比例</td>
                    <td>与被保人关系</td>
                    <td width="200px"><a style="color: red;width: 200px" href="#" onclick="addBeneficiary()">新增受益人</a></td>
                </tr>
            </table>
        </div>
        <div class="form-group" style="margin-top:20px;">
                <input type="hidden" id="sexTest">
                <input type="hidden" id="cardTest">
                <button class="btn btn-primary" onclick="InsuranceDepAI.add()" type="button" >保存提交</button>
                <button class="btn btn-primary" onclick="temporaryCommit()" type="button" >暂存</button>
                <button class="btn btn-default" onclick="clo()">取消</button>
        </div>
    </form>
    <div/>

</div>
</div>
</div>
<!-- 添加 -->
<div id="adddiv" class="modal fade" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false"
     aria-labelledby="zsadd_myModalLabel" aria-hidden="true" style="top: 50%; height: 550px; margin-top: -275px;">
    <input id="upOrIn" type="hidden" value="insert">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="zsadd_myModalLabel">添加</h4>
            </div>
            <div class="container">
                <br/>
                <div class="form-group">
                    <label class="col-md-2 control-label">产品组合代码</label>
                    <div class="col-md-3 ">
                        <input readonly="readonly" type="text" id="a1" name="role_id">
                    </div>
                </div>
                <br/>
                <div class="form-group">
                    <label class="col-md-2 control-label">产品名称</label>
                    <div class="col-md-3 ">
                        <select type="text" id="a2" name="role_id"></select>
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
                <%--<div class="form-group">
                    <label class="col-md-2 control-label">份数</label>
                    <div class="col-md-3 ">
                        <input type="text" id="a5" name="role_id">
                    </div>
                </div>
                <br/>--%>
                <div class="form-group">
                    <label class="col-md-2 control-label">缴费方式</label>
                    <div class="col-md-3 ">
                        <select type="text" id="a6" name="role_id">


                        </select>
                    </div>
                </div>
                <br/>
                <div class="form-group">
                    <label class="col-md-2 control-label">缴费 年期/年龄标志</label>
                    <div class="col-md-3 ">
                        <input type="hidden" id="a7" name="role_id"></input>
                        <input type="text" id="a7t" readonly="readonly"></input>
                    </div>
                </div>
                <br/>
                <div class="form-group">
                    <label class="col-md-2 control-label">缴费 年期/年龄</label>
                    <div class="col-md-3 ">
                        <input type="text" id="a8" name="role_id"></div>
                </div>
                <br/>
                <div class="form-group">
                    <label class="col-md-2 control-label">保险期间标志</label>
                    <div class="col-md-3 ">
                        <input type="hidden" id="a9" name="role_id" ></input>
                        <input type="text" id="a9t" readonly="readonly"></input>
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
                        <input type="hidden" id="a11" name="role_id" ></input>
                        <input type="text" id="a11t" readonly="readonly"></input>
                    </div>
                </div>

                <br/>


                <br/>
                <div class="form-group">
                    <div class="col-md-3 ">
                        <input type="hidden" id="a12" name="role_id" ></input>
                    </div>
                </div>
                <br/>
                <div class="modal-footer col-md-6">
                    <!--用来清空表单数据-->
                    <input type="reset" name="reset" style="display: none;"/>
                    <button type="button" class="btn btn-default" onclick="closeDlg()">关闭</button>
                    <button id="zs_saveButton" type="button" onclick="bxqd()" class="btn btn-primary">保存</button>
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
<div id="alertFile" class="modal fade" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false"
     aria-labelledby="zsadd_myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="zsadd_myModalLabel">上传文件</h4>
            </div>
            <div class="container">
                <form class="form-horizontal" id="fileForm" enctype="multipart/form-data">
                    <div class="form-group">
                        <label class="col-md-2 control-label">选择文件</label>
                        <div class="col-md-3 ">
                            <input type="file" name="file" id="fileName"/>
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