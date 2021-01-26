<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/include/core.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>投保单审核</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Cache" content="no-cache">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="${path}/css/system/role/role.css">
    <script src="${path}/js/system/comm.js" charset="UTF-8" type="text/javascript"></script>
    <script src="${path}/static/js/bootstrap.js"></script>
    <script type="text/javascript">
        $(function () {
            gen.formValidator();

            gen.init()
            $.ajax({
                url: "/insurance_dept/select_all_org",
                type: "post",
                dataType: "json",
                success: function (obj) {
                    var string = "";
                    for (var s = 0; s < obj.data.length; s++) {
                        string += "<option value="+obj.data[s].INSURANCE_COMPANY_ID+">" + obj.data[s].INSURANCE_COMPANY_NAME + "</option>";
                    }
                    $("#gen_org").append(string)
                }
            })

            getSysDictVal("gen_type","DATA_TRANSFER_TYPE")
            var grant_forTdVal1s = '${grant_forTdVal1s}'
            grant_forTdVal1s = grant_forTdVal1s.substring(0, grant_forTdVal1s.length)
            grant_forTdVal1s = grant_forTdVal1s.substring(1, grant_forTdVal1s.length - 1)
            var grant_forTdVal1 = grant_forTdVal1s.split(",")

            var grant_forTdVal2s = '${grant_forTdVal2s}'
            grant_forTdVal2s = grant_forTdVal2s.substring(0, grant_forTdVal2s.length)
            grant_forTdVal2s = grant_forTdVal2s.substring(1, grant_forTdVal2s.length - 1)
            var grant_forTdVal2 = grant_forTdVal2s.split(",")

            var grant_forTdVal3s = '${grant_forTdVal3s}'
            grant_forTdVal3s = grant_forTdVal3s.substring(0, grant_forTdVal3s.length)
            grant_forTdVal3s = grant_forTdVal3s.substring(1, grant_forTdVal3s.length - 1)
            var grant_forTdVal3 = grant_forTdVal3s.split(",")

            var grant_forTdVal4s = '${grant_forTdVal4s}'
            grant_forTdVal4s = grant_forTdVal4s.substring(0, grant_forTdVal4s.length)
            grant_forTdVal4s = grant_forTdVal4s.substring(1, grant_forTdVal4s.length - 1)
            var grant_forTdVal4 = grant_forTdVal4s.split(",")

            var grant_forTdVal5s = '${grant_forTdVal5s}'
            grant_forTdVal5s = grant_forTdVal5s.substring(0, grant_forTdVal5s.length)
            grant_forTdVal5s = grant_forTdVal5s.substring(1, grant_forTdVal5s.length - 1)
            var grant_forTdVal5 = grant_forTdVal5s.split(",")

            var grant_forTdVal6s = '${grant_forTdVal6s}'
            grant_forTdVal6s = grant_forTdVal6s.substring(0, grant_forTdVal6s.length)
            grant_forTdVal6s = grant_forTdVal6s.substring(1, grant_forTdVal6s.length - 1)
            var grant_forTdVal6 = grant_forTdVal6s.split(",")


            var grant_forTdVal7s = '${grant_forTdVal7s}'
            grant_forTdVal7s = grant_forTdVal7s.substring(0, grant_forTdVal7s.length)
            grant_forTdVal7s = grant_forTdVal7s.substring(1, grant_forTdVal7s.length - 1)
            var grant_forTdVal7 = grant_forTdVal7s.split(",")

            var grant_forTdVal8s = '${grant_forTdVal8s}'
            grant_forTdVal8s = grant_forTdVal8s.substring(0, grant_forTdVal8s.length)
            grant_forTdVal8s = grant_forTdVal8s.substring(1, grant_forTdVal8s.length - 1)
            var grant_forTdVal8 = grant_forTdVal8s.split(",")

            var grant_forTdVal9s = '${grant_forTdVal9s}'
            grant_forTdVal9s = grant_forTdVal9s.substring(0, grant_forTdVal9s.length)
            grant_forTdVal9s = grant_forTdVal9s.substring(1, grant_forTdVal9s.length - 1)
            var grant_forTdVal9 = grant_forTdVal9s.split(",")

            var grant_forTdVal10s = '${grant_forTdVal10s}'
            grant_forTdVal10s = grant_forTdVal10s.substring(0, grant_forTdVal10s.length)
            grant_forTdVal10s = grant_forTdVal10s.substring(1, grant_forTdVal10s.length - 1)
            var grant_forTdVal10 = grant_forTdVal10s.split(",")

            var grant_forTdVal11s = '${grant_forTdVal11s}'
            grant_forTdVal11s = grant_forTdVal11s.substring(0, grant_forTdVal11s.length)
            grant_forTdVal11s = grant_forTdVal11s.substring(1, grant_forTdVal11s.length - 1)
            var grant_forTdVal11 = grant_forTdVal11s.split(",")

            var grant_forTdVal12s = '${grant_forTdVal12s}'
            grant_forTdVal12s = grant_forTdVal12s.substring(0, grant_forTdVal12s.length)
            grant_forTdVal12s = grant_forTdVal12s.substring(1, grant_forTdVal12s.length - 1)
            var grant_forTdVal12 = grant_forTdVal12s.split(",")
            var table = "";
            for (var i = 0; i < '${size}'; i++) {
                var a = i + 1;
                table += " <tr>\n" +

                    "<td><input name='alreadyCheck_son' type=\"checkbox\"></td>\n" +
                    "<td><input name=\"fla_son\" type=\"hidden\"  ><input value='" + a + "' name=\"grant_forTdVal0_son\" type=\"hidden\">" + a + "</td>\n" +
                    "<td><input name=\"fla_son\" type=\"hidden\" ><input value=" + grant_forTdVal1[i] + " name=\"grant_forTdVal1_son\" type=\"hidden\">" + grant_forTdVal1[i] + "</td>\n" +
                    "<td><input name=\"fla_son\" type=\"hidden\" ><input value='" + grant_forTdVal2[i] + "' name=\"grant_forTdVal2_son\" type=\"hidden\">" + grant_forTdVal2[i] + "</td>\n" +
                    "<td><input name=\"fla_son\" type=\"hidden\" ><input value='" + grant_forTdVal3[i] + "' name=\"grant_forTdVal3_son\" type=\"hidden\">" + grant_forTdVal3[i] + "</td>\n" +
                    "<td><input name=\"fla_son\" type=\"hidden\" ><input value='" + grant_forTdVal4[i] + "' name=\"grant_forTdVal4_son\" type=\"hidden\">" + grant_forTdVal4[i] + "</td>\n" +
                    "<td><input name=\"fla_son\" type=\"hidden\" ><input value='" + grant_forTdVal5[i] + "' name=\"grant_forTdVal5_son\" type=\"hidden\">" + grant_forTdVal5[i] + "</td>\n" +
                    "<td><input name=\"fla_son\" type=\"hidden\" ><input value='" + grant_forTdVal6[i] + "' name=\"grant_forTdVal6_son\" type=\"hidden\">" + grant_forTdVal6[i] + "</td>\n" +
                    "<td><input name=\"fla_son\" type=\"hidden\" ><input value='" + grant_forTdVal7[i] + "' name=\"grant_forTdVal7_son\" type=\"hidden\">" + grant_forTdVal7[i] + "</td>\n" +
                    "<td><input name=\"fla_son\" type=\"hidden\" ><input value='" + grant_forTdVal8[i] + "' name=\"grant_forTdVal8_son\" type=\"hidden\">" + grant_forTdVal8[i] + "</td>\n" +
                    "<td><input name=\"fla_son\" type=\"hidden\" ><input value='" + grant_forTdVal9[i] + "' name=\"grant_forTdVal9_son\" type=\"hidden\">" + grant_forTdVal9[i] + "</td>\n" +
                    "<td><input name=\"fla_son\" type=\"hidden\" ><input value='" + grant_forTdVal10[i] + "' name=\"grant_forTdVal10_son\" type=\"hidden\">" + grant_forTdVal10[i] + "</td>\n" +
                    "<td><input name=\"fla_son\" type=\"hidden\" ><input value='" + grant_forTdVal11[i] + "' name=\"grant_forTdVal11_son\" type=\"hidden\">" + grant_forTdVal11[i] + "</td>\n" +
                    "<td><input name=\"fla_son\" type=\"hidden\" ><input value='" + grant_forTdVal12[i] + "' name=\"grant_forTdVal12_son\" type=\"hidden\">" + grant_forTdVal12[i] + "</td>\n" +
                    "</tr>";
            }
            $("#grant_already_thead_son").append(table)
        });
        function genCommit() {
            $('#gen_addForm').data('bootstrapValidator').validate();//启用验证
            var flag = $('#gen_addForm').data('bootstrapValidator').isValid()//验证是否通过true/false
            if (!flag){
                return;
            }
            var data = $("#gen_addForm").serialize()
            $.ajax({
                url:"/insurance_policy/commit_batch",
                data:data,
                type:"post",
                dataType:"json",
                success: function (obj) {
                    if (obj.returnCode=="200"){
                        alert("生成批次成功")
                        commCloseTab('insurancePolicyGrant:commit')
                        createAddProcessTab("insurancePolicyGrant:list",'')
                    } else {
                        alert("生成批次失败")
                    }
                },
                error: function (obj) {
                    alert("系统异常")
                }
            })
        }
        var gen = function (){
            return{
                init:function (){
                    $('#grant_already_table_son').bootstrapTable({
                        url: "insurance_policy/getList",
                        method:"post",
                        dataType: "json",
                        contentType: "application/x-www-form-urlencoded",
                        striped:true,//隔行变色
                        cache:false,  //是否使用缓存

                        sortable: false, //是否启用排序
                        singleSelect: false,
                        search:false, //显示搜索框
                        buttonsAlign: "right", //按钮对齐方式
                        showRefresh:false,//是否显示刷新按钮
                        sidePagination: "server", //服务端处理分页

                        toolbar:"#grant_toolbar",
                        showColumns : false, //显示隐藏列
                        uniqueId: "INSURED_PERSON_ID", //每一行的唯一标识，一般为主键列
                        queryParamsType:'',
                        queryParams: gen.queryParams,//传递参数（*）
                        columns : [{
                            checkbox: true
                        },{
                            field: 'SerialNumber',
                            title: '序号',
                            formatter: function (value, row, index) {
                                return index+1;
                            }
                        },{
                            field : "INSURED_PERSON_ID",
                            visible:false
                        },{
                            field : "POLICY_ID",
                            title : "投保单号",
                            align : "center",
                            valign : "middle",
                            sortable : "true"
                        },{
                            field : "INSURANCE_COMPANY_NAME",
                            title : "保险公司",
                            align : "center",
                            valign : "middle",
                            sortable : "true"
                        },{
                            field : "PRODUCT_CODE",
                            title : "产品代码",
                            align : "center",
                            valign : "middle",
                            sortable : "true"
                        },{
                            field : "PRODUCT_NAME",
                            title : "产品名称",
                            align : "center",
                            valign : "middle",
                            sortable : "true"
                        },{
                            field : "PREMIUM",
                            title : "保费",
                            align : "center",
                            valign : "middle",
                            sortable : "true"
                        },{
                            field : "SALES_ORG_NAME",
                            title : "组织机构",
                            align : "center",
                            valign : "middle",
                            sortable : "true"
                        },{
                            field : "SALES_TEAM_NAME",
                            title : "营销团队",
                            align : "center",
                            valign : "middle",
                            sortable : "true"
                        },{
                            field : "NAME1",
                            title : "投保人",
                            align : "center",
                            valign : "middle",
                            sortable : "true"
                        },{
                            field : "NAME2",
                            title : "被保人",
                            align : "center",
                            valign : "middle",
                            sortable : "true"
                        },{
                            field : "EMPLOYEE_NO",
                            title : "员工工号",
                            align : "center",
                            valign : "middle",
                            sortable : "true"
                        },{
                            field : "NAME3",
                            title : "员工姓名",
                            align : "center",
                            valign : "middle",
                            sortable : "true"
                        },{
                            field : "INSURE",
                            title : "投保日期",
                            align : "center",
                            valign : "middle",
                            sortable : "true",
                          
                        }],
                        // bootstrap-table-treetreegrid.js 插件配置 -- end
                        formatLoadingMessage : function() {
                            return "请稍等，正在加载中...";
                        },
                        onLoadSuccess:function(data){
                            var ALLDATE = $('#grant_already_table_son').bootstrapTable('getData');
                            var selectListIds=''
                            for(var i =0;i<ALLDATE.length;i++){
                                var grant_forTdVal1 = ALLDATE[i].POLICY_ID//bao保单号
                                selectListIds+=grant_forTdVal1+","
                            }
                            $("#grant_forTdVal1_son").val(selectListIds)
                        },
                        formatNoMatches : function() {
                            return '无符合条件的记录';
                        }
                    });
                },
                search:function (){
                    $("#grant_already_table_son").bootstrapTable('destroy');
                    gen.init();
                },
                queryParams:function(params){
                    var temp = {
                        principalDeputySign:"0",
                        selectListIds:"${ids}",
                    };
                    return temp;
                },

                checkSingleData:function () {
                    var selected = $('#grant_already_table_son').bootstrapTable('getSelections');
                    if (selected.length == 0) {
                        $.alert({
                            title: '提示信息！',
                            content: '至少选择一条记录！',
                            type: 'red'
                        });
                        return false;
                    }else if (selected.length > 1){
                        $.alert({
                            title: '提示信息！',
                            content: '该操作只能选择一条记录!',
                            type: 'blue'
                        });
                    }else {
                        gen.seItem = selected[0];
                        return true;
                    }
                },
                formValidator:function () {
                    $("#gen_addForm").bootstrapValidator({
                        fields:{
                            gen_name:{
                                validators: {
                                    notEmpty: {
                                        message: "转移批次名称不能为空"
                                    }
                                }
                            },
                                gen_org  :{
                                    validators:{
                                        notEmpty: {
                                            message: "接收机构不能为空"
                                        }
                                    }
                                },
                                gen_type  :{
                                        validators:{
                                            notEmpty: {
                                                message: "转移资料类型不能为空"
                                            }
                                        }
                                },
                                gen_date:{
                                            validators:{
                                                notEmpty: {
                                                    message: "预计接收日期不能为空"
                                                }
                                            }
                            }
                        }

                    })
                },
            }
        }();
        function clo() {
            commCloseTab('insurancePolicyGrant:commit')
            createAddProcessTab("insurancePolicyGrant:list",'')
        }
    </script>

</head>
<body>
<!--列表 -->
<button class="btn btn-success" id="gen_commit" onclick="genCommit()">确认生成批次</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<button class="btn btn-info"  onclick="clo()">返回</button>
<form class="form-horizontal" id="gen_addForm" method="post" enctype="application/x-www-form-urlencoded">
    <div class="container" style="width: 2000px">

        <div style="overflow:scroll;">
            <div class="form-group">
                <label class="col-md-2 control-label">转移批次名称*</label>
                <div class="col-md-3 ">
                    <input type="text" class="form-control form-control-static" id="gen_name"
                           name="gen_name">
                </div>

                <label class="col-md-2 control-label">接收机构*</label>
                <div class="col-md-3 ">
                    <select class="form-control form-control-static" id="gen_org"
                            name="gen_org">


                    </select>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 control-label">转移资料类型*</label>
                <div class="col-md-3 ">
                    <select type="text" class="form-control form-control-static" id="gen_type"
                            name="gen_type"></select>
                </div>

                <label class="col-md-2 control-label">预计接收日期*</label>
                <div class="col-md-3 ">
                    <input type="date" class="form-control form-control-static" id="gen_date"
                           name="gen_date">
                    <input type="hidden" name="grant_forTdVal1_son" id="grant_forTdVal1_son">
                </div>
            </div>
        </div>

    </div>
    <!--toolbar  -->
    <div style="overflow:scroll;">
        <table id="grant_already_table_son" class="table table-hover table-striped table-condensed table-bordered">
            <thead>
            <tr>

            </tr>
            </thead>
            <tbody id="grant_already_thead_son">

            </tbody>
        </table>

    </div>
</form>

</body>
</html>