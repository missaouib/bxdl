<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/include/core.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>投保单照会回销</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Cache" content="no-cache">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="${path}/css/system/role/role.css">
    <script src="${path}/js/system/comm.js" charset="UTF-8" type="text/javascript"></script>
    <script src="${path}/static/js/jquery-form.js"></script>
    <script src="${path}/static/js/bootstrap.js"></script>
    <script type="text/javascript">
        function search() {
            alert("搜索")
        }

        function cla() {
            $("#noteCan_exampleInputName1").val('')
            $("#noteCan_exampleInputName2").val('')
            $("#noteCan_exampleInputName3").val('')
            $("#noteCan_exampleInputName4").val('')
            $("#noteCan_exampleInputName5").val('')
            $("#noteCan_exampleInputName6").val('')
            $("#noteCan_exampleInputName7").val('')
            $("#noteCan_exampleInputName8").val('')
            $("#noteCan_exampleInputName9").val('')
            $("#noteCan_exampleInputName10").val('')
            $("#noteCan_exampleInputName20").val('')
            $("#noteCan_exampleInputName21").val('')
            insComanpy_IPNC.search()
        }
        function upload() {

            var noteCan_org  =$("#noteCan_org").val()
            var noteCan_team =   $("#noteCan_team").val()
            var noteCan_empNo =$("#noteCan_empNo").val()
            var noteCan_name = $("#noteCan_name").val()
            var noteCan_policy_id =$("#noteCan_policy_id").val()
            if (!noteCan_org) {
                alert("组织结构不能为空")
                return;
            }
            if (!noteCan_team) {
                alert("营销团队不能为空")
                return;
            }
            if (!noteCan_empNo) {
                alert("员工工号不能为空")
                return;
            }
            if (!noteCan_name) {
                alert("员工姓名不能为空")
                return;
            }
            var data={"noteCan_org":noteCan_org,"noteCan_team":noteCan_team,"noteCan_empNo":noteCan_empNo,"noteCan_name":noteCan_name,"noteCan_policy_id":noteCan_policy_id}
            $.ajax({
                url:"/insurance_policy/note_cancellation_grant",
                data:data,
                type:"post",
                dataType:"json",
                success:function (obj) {
                    if (obj.code='200'){
                        alert("提交成功")
                        closeDlg()
                    } else{
                        alert("提交失败")
                        closeDlg()
                    }
                },
                error:function (obj) {
                    alert("系统异常")
                    closeDlg()
                }
            })
        
        }
        var insComanpy_IPNC = {
            seItem: null		//选中的条目
        };

        function fafang(obj) {
            $("#noteCan_policy_id").val(obj)
            $("#noteCanAlertFile").modal('show')
        }
        function huifu(obj) {
            $("#noteCan_policy_id").val(obj)
            $("#noteCanAlertReply").modal('show')
        
        }
        function uploadAbnormal() {
         var noteCan_policy_id =   $("#noteCan_policy_id").val()
           var noteCan_Abnormal = $("#noteCan_Abnormal").val()
            var data = {"noteCan_policy_id":noteCan_policy_id,"noteCan_Abnormal":noteCan_Abnormal}
            $.ajax({
                url:"/insurance_policy/note_cancellation_Abnormal",
                data:data,
                type:"post",
                dataType:"json",
                success:function (obj) {
                    if (obj.code='200'){
                        alert("提交成功")
                        $("#noteCanAbnormal").modal('hide');
                        closeDlg()
                    } else{
                        alert("提交失败")
                    }
                },
                error:function (obj) {
                    alert("系统异常")
            }
                
            })


        }
        function huixiao(obj) {
            if(confirm("确认回销？")){
                var  data = {"noteCan_policy_id":obj}
                $.ajax({
                    url:"/insurance_policy/note_cancellation_sell_back",
                    data:data, 
                    type:"POST",
                    dataType:"json",
                    success:function (obj) {
                        if (obj.code='200'){
                            alert("提交成功")
                            closeDlg();
                        } else{
                            alert("提交失败")
                        }
                    },
                    error:function (obj) {
                        alert("系统异常")
                    }
                    
                })
            }else{
                
            }
        }
        function yichang(obj) {
            $("#noteCan_policy_id").val(obj)
            $("#noteCanAbnormal").modal('show')
        }
        function uploadReply() {
                   var noteCan_Reply = $("#noteCan_Reply").val();

            var noteCan_policy_id =$("#noteCan_policy_id").val()
            var data={"noteCan_Reply":noteCan_Reply,"noteCan_policy_id":noteCan_policy_id}
            $.ajax({
                url:"/insurance_policy/note_cancellation_reply",
                data:data,
                type:"post",
                dataType:"json",
                success:function (obj) {
                    if (obj.code='200'){
                        alert("提交成功")
                        closeDlg()
                    } else{
                        alert("提交失败")
                        closeDlg()
                    }
                },
                error:function (obj) {
                    alert("系统异常")
                    closeDlg()
                }
            })
        }
        function closeDlg() {//关闭
            $("#noteCanAlertReply").modal('hide')
            $("#noteCanAlertFile").modal('hide');
            $("#noteCanAbnormal").modal('hide');
            createAddProcessTab('insurancePolicyNoteCancellation:list','')

        }

        $(function () {
            insComanpy_IPNC.init();
            insComanpy_IPNC.formValidator()

            getSysDictVal("noteCan_auditresults", "AUDITRESULTS")
            //回显员工 组织机构  所属团队

            commSalesOrgs("noteCan_org");
            $("#noteCan_org").on(
                "change",function(){
                    commSalesTeams("noteCan_team",$(this).find("option:selected").val());
                }
            )
            getSysDictVal("noteCan_exampleInputName10","STATE")
            commSelectProductOrg("noteCan_exampleInputName2")
            commSalesOrgsHX("noteCan_exampleInputName5")
            $("#noteCan_exampleInputName5").on(
                "change",function(){
                    commSalesTeamsHX("noteCan_exampleInputName6",$(this).find("option:selected").val());
                }
            )
        });

        function notel(id) {
            createAddProcessTab("insurancePolicyNote:list", id)
        }

        function operateFormatter(value, row, index) {
            var id = row.POLICY_ID;
            var state = row.STATE
            if (state == 6) {
                return [
                    '<shiro:hasPermission name="insurancePolicyNoteCancellation:fafang">',
                    '<button type="button" class="btn btn-info" onclick="fafang(\'' + id + '\')">',
                    '<span class="glyphicon glyphicon-pencil" >发放</span>',
                    '</button>',
                    '</shiro:hasPermission>',
                    '<shiro:hasPermission name="insurancePolicyNoteCancellation:yichang">',
                    '<button type="button" class=" btn btn-info" onclick="yichang(\'' + id + '\')">',
                    '<span class="glyphicon glyphicon-pencil" >异常</span>',
                    '</button>',
                    '</shiro:hasPermission>'

                ].join('');
            }
            if (state == 7) {
                return [
                    '<shiro:hasPermission name="insurancePolicyNoteCancellation:huifu">',
                    '<button type="button" class="btn btn-info" onclick="huifu(\'' + id + '\')">',
                    '<span class="glyphicon glyphicon-pencil" >回复</span>',
                    '</button>',
                    '</shiro:hasPermission>',
                    '<shiro:hasPermission name="insurancePolicyNoteCancellation:yichang">',
                    '<button type="button" class=" btn btn-info" onclick="yichang(\'' + id + '\')">',
                    '<span class="glyphicon glyphicon-pencil" >异常</span>',
                    '</button>',
                    '</shiro:hasPermission>'

                ].join('');
            }
            if (state == 8) {
                return [
                    '<shiro:hasPermission name="insurancePolicyNoteCancellation:huixiao">',
                    '<button type="button" class="btn btn-info" onclick="huixiao(\'' + id + '\')">',
                    '<span class="glyphicon glyphicon-pencil" >回销</span>',
                    '</button>',
                    '</shiro:hasPermission>',
                    '<shiro:hasPermission name="insurancePolicyNoteCancellation:yichang">',
                    '<button type="button" class=" btn btn-info" onclick="yichang(\'' + id + '\')">',
                    '<span class="glyphicon glyphicon-pencil" >异常</span>',
                    '</button>',
                    '</shiro:hasPermission>'

                ].join('');
            }
            if (state == 9) {
                return [
                    '<shiro:hasPermission name="insurancePolicyNoteCancellation:yichang">',
                    '<button type="button" class=" btn btn-info" onclick="yichang(\'' + id + '\')">',
                    '<span class="glyphicon glyphicon-pencil" >异常</span>',
                    '</button>',
                    '</shiro:hasPermission>'

                ].join('');
            }
            if (state == 10) {
                return [].join('');
            }

        }
        var insComanpy_IPNC = function () {
            return {
                init: function () {
                    $('#noteCan_insComanpy-table').bootstrapTable({
                        url: "insurance_policy/do_insurance_policy_note_cancellation",
                        method: "post",
                        dataType: "json",
                        contentType: "application/x-www-form-urlencoded",
                        striped: true,//隔行变色
                        cache: false,  //是否使用缓存
                        pagination: true, //分页
                        sortable: false, //是否启用排序
                        singleSelect: false,
                        search: false, //显示搜索框
                        buttonsAlign: "right", //按钮对齐方式
                        showRefresh: false,//是否显示刷新按钮
                        sidePagination: "server", //服务端处理分页
                        pageSize: 5, //默认每页条数
                        pageNumber: 1, //默认分页
                        pageList: [5, 10, 20, 50],//分页数
                        toolbar: "#noteCan_toolbar",
                        showColumns: false, //显示隐藏列
                        uniqueId: "INSURED_PERSON_ID", //每一行的唯一标识，一般为主键列
                        queryParamsType: '',
                        queryParams: insComanpy_IPNC.queryParams,//传递参数（*）
                        columns: [{
                            checkbox: true
                        }, {
                            field: 'SerialNumber',
                            title: '序号',
                            formatter: function (value, row, index) {
                                return index + 1;
                            }
                        }, {
                            field: "INSURED_PERSON_ID",
                            visible: false
                        }, {
                            field: "POLICY_ID",
                            title: "投保单号",
                            align: "center",
                            valign: "middle",
                            sortable: "true"
                        }, {
                            field: "INSURANCE_COMPANY_NAME",
                            title: "保险公司",
                            align: "center",
                            valign: "middle",
                            sortable: "true"
                        }, {
                            field: "PRODUCT_CODE",
                            title: "产品代码",
                            align: "center",
                            valign: "middle",
                            sortable: "true"
                        }, {
                            field: "PRODUCT_NAME",
                            title: "产品名称",
                            align: "center",
                            valign: "middle",
                            sortable: "true"
                        }, {
                            field: "PREMIUM",
                            title: "保费",
                            align: "center",
                            valign: "middle",
                            sortable: "true"
                        }, {
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
                            field: "NAME1",
                            title: "投保人",
                            align: "center",
                            valign: "middle",
                            sortable: "true"
                        }, {
                            field: "NAME2",
                            title: "被保人",
                            align: "center",
                            valign: "middle",
                            sortable: "true"
                        }, {
                            field: "EMPLOYEE_NO",
                            title: "员工工号",
                            align: "center",
                            valign: "middle",
                            sortable: "true"
                        }, {
                            field: "NAME3",
                            title: "员工姓名",
                            align: "center",
                            valign: "middle",
                            sortable: "true"
                        }, {
                            field: "INSURE",
                            title: "投保日期",
                            align: "center",
                            valign: "middle",
                            sortable: "true",
                            formatter:function (value) {
                                var cellval =value+"";
                                if (cellval != null) {
                                    var date = new Date(parseInt(cellval.replace("/Date(", "").replace(")/", ""), 10));
                                    var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
                                    var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
                                    return date.getFullYear() + "-" + month + "-" + currentDate;
                                }
                            }
                        }, {
                            field: "STATE",
                            title: "状态",
                            align: "center",
                            valign: "middle",
                            sortable: "true",
                            formatter: function (value) {
                                if (value == '6') {
                                    value = "待发放"
                                }
                                if (value == '7') {
                                    value = "已发放"
                                }
                                if (value == '8') {
                                    value = "已回复"
                                }
                                if (value == '9') {
                                    value = "已回销"
                                }
                                if (value == '10') {
                                    value = "异常"
                                }
                                return value;
                            }
                        }, {
                            title: "操作",
                            align: "center",
                            valign: "middle",
                            sortable: "true",
                            formatter: operateFormatter
                        }],
                        // bootstrap-table-treetreegrid.js 插件配置 -- end
                        formatLoadingMessage: function () {
                            return "请稍等，正在加载中...";
                        },
                        formatNoMatches: function () {
                            return '无符合条件的记录';
                        }
                    });
                },
                search:function(){
                    $("#noteCan_insComanpy-table").bootstrapTable('destroy');
                    insComanpy_IPNC.init();
                },
                queryParams: function (params) {
                    var temp = {
                        pageSize: params.pageSize,  //页面大小
                        pageNo: params.pageNumber, //页码
                        principalDeputySign:"0",
                        type:1,
                        state: "6,7,8,9,10",
                        exampleInputName1:$("#noteCan_exampleInputName1").val(),
                        exampleInputName2:$("#noteCan_exampleInputName2").val(),
                        exampleInputName3:$("#noteCan_exampleInputName3").val(),
                        exampleInputName4:$("#noteCan_exampleInputName4").val(),
                        exampleInputName5:$("#noteCan_exampleInputName5").val(),
                        exampleInputName6:$("#noteCan_exampleInputName6").val(),
                        exampleInputName7:$("#noteCan_exampleInputName7").val(),
                        exampleInputName8:$("#noteCan_exampleInputName8").val(),
                        exampleInputName9:$("#noteCan_exampleInputName9").val(),
                        exampleInputName10:$("#noteCan_exampleInputName10").val(),
                        exampleInputName20:$("#noteCan_exampleInputName20").val(),
                        exampleInputName21:$("#noteCan_exampleInputName21").val(),
                    };
                    return temp;
                },

                checkSingleData: function () {
                    var selected = $('#noteCan_insComanpy-table').bootstrapTable('getSelections');
                    if (selected.length == 0) {
                        $.alert({
                            title: '提示信息！',
                            content: '至少选择一条记录！',
                            type: 'red'
                        });
                        return false;
                    } else if (selected.length > 1) {
                        $.alert({
                            title: '提示信息！',
                            content: '该操作只能选择一条记录!',
                            type: 'blue'
                        });
                    } else {
                        insComanpy_IPNC.seItem = selected[0];
                        return true;
                    }
                },
                formValidator:function () {
                    $("#noteCanAlertFile").bootstrapValidator({
                        fields:{
                            noteCan_org:{
                                validators:{
                                        notEmpty:{
                                            message:"组织结构不能为空"
                                        }
                                }
                            },
                            noteCan_team:{
                                validators:{
                                    notEmpty:{
                                        message:"营销团队不能为空"
                                    }
                                }
                            },
                            noteCan_empNo:{
                                validators:{
                                    notEmpty:{
                                        message:"员工工号不能为空"
                                    }
                                }
                            },
                            noteCan_name:{
                                validators:{
                                    notEmpty:{
                                        message:"员工姓名不能为空"
                                    }
                                }
                            },
                        }

                    })
                }
            }
        }();
    </script>

</head>
<body>
<!--列表 -->
<div class="panel panel-default">
    <div class="panel-body">
        <form class="form-inline hz-form-inline"  id="noteCan_search" enctype="application/x-www-form-urlencoded">
            <div class="form-group">
                <label class="control-label" for="noteCan_exampleInputName2">投保单号</label>
                <input type="text" class="form-control" name="noteCan_exampleInputName1" id="noteCan_exampleInputName1"
                       placeholder="投保单号">
            </div>
            <div class="form-group">
                <label class="control-label" for="noteCan_exampleInputName2">保险公司</label>
                <select type="text" class="form-control" name="noteCan_exampleInputName2" id="noteCan_exampleInputName2"
                        placeholder="保险公司"></select>
            </div>
            <div class="form-group">
                <label class="control-label" for="noteCan_exampleInputName2">产品名称</label>
                <input type="text" class="form-control" name="noteCan_exampleInputName3" id="noteCan_exampleInputName3"
                       placeholder="产品名称">
            </div>
            <div class="form-group">
                <label class="control-label" for="noteCan_exampleInputName2">产品代码</label>
                <input type="text" class="form-control" name="noteCan_exampleInputName4" id="noteCan_exampleInputName4"
                       placeholder="产品代码">
            </div>
            <div class="form-group">
                <label class="control-label" for="noteCan_exampleInputName2">组织机构</label>
                <select type="text" class="form-control" name="noteCan_exampleInputName5" id="noteCan_exampleInputName5"
                       placeholder="组织机构"></select>
            </div>
            <div class="form-group">
                <label class="control-label" for="noteCan_exampleInputName2">营销团队</label>
                <select type="text" class="form-control" name="noteCan_exampleInputName6" id="noteCan_exampleInputName6"
                        placeholder="营销团队"></select>
            </div>
            <div class="form-group">
                <label class="control-label" for="noteCan_exampleInputName2">投保人</label>
                <input type="text" class="form-control" name="noteCan_exampleInputName7" id="noteCan_exampleInputName7"
                       placeholder="投保人">
            </div>
            <div class="form-group">
                <label class="control-label" for="noteCan_exampleInputName2">被保人</label>
                <input type="text" class="form-control" name="noteCan_exampleInputName8" id="noteCan_exampleInputName8"
                       placeholder="被保人">
            </div>
            <div class="form-group">
                <label class="control-label" for="noteCan_exampleInputName2">员工工号</label>
                <input type="text" class="form-control" name="noteCan_exampleInputName9" id="noteCan_exampleInputName9"
                       placeholder="员工工号">
            </div>
            <div class="form-group">
                <label class="control-label" for="noteCan_exampleInputName2">状态</label>
                <select type="text" class="form-control" name="noteCan_exampleInputName10" id="noteCan_exampleInputName10"
                        placeholder="状态"></select>
            </div>
            <div class="form-group">
                <label class="control-label" for="noteCan_exampleInputName2">投保日期</label>
                <input type="text" class="form-control" name="noteCan_exampleInputName20" id="noteCan_exampleInputName20" placeholder="投保日期">
                <label class="control-label control-label-time">至</label>
                <input type="text" class="form-control" name="noteCan_exampleInputName21" id="noteCan_exampleInputName21" placeholder="投保日期">
            </div>
            <div class="form-group">
                <button type="button" class="btn btn-info" onclick="insComanpy_IPNC.search()">
                    <span class="glyphicon glyphicon-search" aria-hidden="true" >搜索</span>
                </button>
                <button type="button" class="btn btn-danger" onclick="cla()">
                    <span class="glyphicon glyphicon-remove" aria-hidden="true" >清空</span>
                </button>
            </div>
        </form>
    </div>
</div>

<div style="width: 99%;overflow: auto;">
    <table id="noteCan_insComanpy-table" class="table table-hover table-striped table-condensed table-bordered"></table>
</div>
<!--toolbar  -->
<div id="noteCanAlertFile" class="modal fade" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false"
     aria-labelledby="zsadd_myModalLabel" aria-hidden="true" >
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="zsadd_myModalLabel">添加</h4>
            </div>
            <div class="container">
                <form class="form-horizontal" id="noteCan_Form"  method="post">
                <div class="form-group">
                    <label class="col-md-2 control-label">组织结构</label>
                    <div class="col-md-3 ">
                        <select  class="form-control" name="noteCan_org" id="noteCan_org"></select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-2 control-label">营销团队</label>
                    <div class="col-md-3 ">
                        <select class="form-control" name="noteCan_team" id="noteCan_team"></select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-2 control-label">员工工号</label>
                    <div class="col-md-3 ">
                        <input class="form-control" name="noteCan_empNo" id="noteCan_empNo">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-2 control-label">员工姓名</label>
                    <div class="col-md-3 ">
                        <input class="form-control" name="noteCan_name" id="noteCan_name">
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
<div id="noteCanAlertReply" class="modal fade" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false"
     aria-labelledby="zsadd_myModalLabel" aria-hidden="true" >
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>

                    <label class="col-md-2 control-label">回复原因</label>
                <textarea rows="15" cols="25" class="form-control" name="noteCan_Reply" id="noteCan_Reply">


                </textarea>
                    <!--用来清空表单数据-->
                    <input type="reset" name="reset" style="display: none;"/>
                    <button type="button" class="btn btn-default" onclick="closeDlg()">关闭</button>
                    <button id="zs_saveButton_Reply" type="button" onclick="uploadReply()" class="btn btn-primary">保存</button>
            </div>
        </div>
    </div>
</div>

<div id="noteCanAbnormal" class="modal fade" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false"
     aria-labelledby="zsadd_myModalLabel" aria-hidden="true" >
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <label class="col-md-2 control-label">异常原因</label>
                        <textarea rows="15" cols="25" class="form-control" name="noteCan_Abnormal" id="noteCan_Abnormal">


                        </textarea>
                    <!--用来清空表单数据-->
                    <input type="reset" name="reset" style="display: none;"/>
                    <button type="button" class="btn btn-default" onclick="closeDlg()">关闭</button>
                    <button id="zs_saveButton_Abnormal" type="button" onclick="uploadAbnormal()" class="btn btn-primary">保存</button>
            </div>
        </div>
</div>
<input name="noteCan_policy_id" id="noteCan_policy_id" type="hidden">
</body>
</html>