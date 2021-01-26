<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/core.jsp" %>
<!DOCTYPE  html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>回执审核</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Cache" content="no-cache">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="${path}/css/system/role/role.css">
    <script src="${path}/js/system/comm.js" charset="UTF-8" type="text/javascript"></script>
    <script src="${path}/static/js/bootstrap.js"></script>
    <script type="text/javascript">
        function mingxi(id) {
            commCloseTab('returnVisit:list')
            createAddProcessTab('retuenVisit:det',id)
        }

        function cla() {
            $("#be_retVis_exampleInputName1").val('')
            $("#be_retVis_exampleInputName2").val('')
            $("#be_retVis_exampleInputName3").val('')
            $("#be_retVis_exampleInputName4").val('')
            $("#be_retVis_exampleInputName5").val('')
            $("#be_retVis_exampleInputName6").val('')
            $("#be_retVis_exampleInputName7").val('')
            $("#be_retVis_exampleInputName8").val('')
            $("#be_retVis_exampleInputName9").val('')
            $("#be_retVis_exampleInputName20").val('')
            $("#be_retVis_exampleInputName21").val('')
            $("#be_retVis_exampleInputName22").val('')
            $("#be_retVis_exampleInputName23").val('')
            $("#be_retVis_exampleInputName24").val('')
            $("#be_retVis_exampleInputName25").val('')
            insComanpy_RV.search()
        }
        var insComanpy_RV = {
            seItem: null		//选中的条目
        };
        function allExamine(){//全部审批
            var allTableData = $('#be_retVis_exampleInputName').bootstrapTable('getData');
            var param=""
            $.each(allTableData,function(i,e){
                param=param+e.POLICY_ID
            })
            $("#Tab").val(param)
            $("#examine_alert_div").modal('show')
        }
        function closeDlg() {//关闭
            $("#examine_alert_div").modal('hide');
        }
        function determine(){//审批确定
            var examine_fileForm=$("#examine_fileForm").serialize()
            $.ajax({
                url:"insurance_policy/submit_receipt_examine",
                data:examine_fileForm,
                type:"post",
                dataType:"json",
                success:function(obj){

                },
                error:function (obj) {

                }
            })
        }
        $(function () {
            insComanpy_RV.init();
            getSysDictVal("examine_auditresults","AUDITRESULTS")

            commSelectProductOrg("be_retVis_exampleInputName2")
            commSalesOrgsHX("be_retVis_exampleInputName5")
            $("#be_retVis_exampleInputName5").on(
                "change",function(){
                    commSalesTeamsHX("be_retVis_exampleInputName6",$(this).find("option:selected").val());
                }
            )
        });
        function huifang(id) {
            commCloseTab('returnVisit:list')
            createAddProcessTab("retuenVisit:commit",id)
        }

        //修改——转换日期格式(时间戳转换为datetime格式)
        function changeDateFormat(cellval) {
            if (cellval != null) {
                var date = new Date(parseInt(cellval.replace("/Date(", "").replace(")/", ""), 10));
                var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
                var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
                return date.getFullYear() + "-" + month + "-" + currentDate;
            }
        }
        function operateFormatter(value, row) {
            if (row.INS_STATE > 1009){
            var id = row.POLICY_ID;
            return [
                '<shiro:hasPermission name="retuenVisit:det">',
                '<button type="button" class="btn btn-info" onclick="mingxi(\'' + id + '\')">',
                '<span class="glyphicon glyphicon-pencil" >明细</span>',
                '</button>',
                '</shiro:hasPermission>'
            ].join('');
            }else if (row.INS_STATE==1009){
                var id = row.POLICY_ID;
                return [
                    '<shiro:hasPermission name="retuenVisit:det">',
                    '<button type="button" class="btn btn-info" onclick="mingxi(\'' + id + '\')">',
                    '<span class="glyphicon glyphicon-pencil" >明细</span>',
                    '</button>',
                    '</shiro:hasPermission>',
                    '<shiro:hasPermission name="retuenVisit:commit">',
                    '<button type="button" class=" btn btn-info" onclick="huifang(\'' + id + '\')">',
                    '<span class="glyphicon glyphicon-pencil" >回访</span>',
                    '</button>',
                    '</shiro:hasPermission>'
                ].join('')
            }
            else{
                return "";
            }
        }
        var insComanpy_RV = function (){
            return{
                init:function (){
                    $('#be_retVis_exampleInputName').bootstrapTable({
                        url: "insurance_policy/do_return_visit",
                        method:"post",
                        dataType: "json",
                        contentType: "application/x-www-form-urlencoded",
                        striped:true,//隔行变色
                        cache:false,  //是否使用缓存
                        pagination: true, //分页
                        sortable: false, //是否启用排序
                        singleSelect: false,
                        search:false, //显示搜索框
                        buttonsAlign: "right", //按钮对齐方式
                        showRefresh:false,//是否显示刷新按钮
                        sidePagination: "server", //服务端处理分页
                        pageSize : 5, //默认每页条数
                        pageNumber : 1, //默认分页
                        pageList : [5, 10, 20, 50 ],//分页数
                        toolbar:"#returnvisit",
                        showColumns : false, //显示隐藏列
                        uniqueId: "INSURED_PERSON_ID", //每一行的唯一标识，一般为主键列
                        queryParamsType:'',
                        queryParams: insComanpy_RV.queryParams,//传递参数（*）
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
                            field : "CORRESPONDING",
                            title : "投保单号",
                            align : "center",
                            valign : "middle",
                            sortable : "true"
                        },{
                            field : "POLICY_ID",
                            title : "保单号",
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
                            formatter:function (value) {
                                var cellval =value+"";
                                if (cellval != null) {
                                    var date = new Date(parseInt(cellval.replace("/Date(", "").replace(")/", ""), 10));
                                    var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
                                    var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
                                    return date.getFullYear() + "-" + month + "-" + currentDate;
                                }
                            }
                        },{
                            field : "INS_STATE",
                            title : "作业节点",
                            align : "center",
                            valign : "middle",
                            sortable : "true",
                            formatter:function (value) {
                                return getStateTest(value);
                            }
                        },{
                            field : "TAKE_EFFECT_DATA",
                            title : "生效日期",
                            align : "center",
                            valign : "middle",
                            sortable : "true",
                            formatter: function (value, row, index) {
                                value=value+"";
                                return changeDateFormat(value)
                            }
                        },{
                            field : "UNDERWRITING_DATA",
                            title : "承保日期",
                            align : "center",
                            valign : "middle",
                            sortable : "true",
                            formatter: function (value, row, index) {
                                value=value+"";
                                return changeDateFormat(value)
                            }
                        }, {
                            title: "操作",
                            align: "center",
                            valign: "middle",
                            sortable: "true",
                            formatter: operateFormatter
                        }],
                        // bootstrap-table-treetreegrid.js 插件配置 -- end
                        formatLoadingMessage : function() {
                            return "请稍等，正在加载中...";
                        },
                        formatNoMatches : function() {
                            return '无符合条件的记录';
                        }
                    });
                },
                queryParams:function(params){
                    var temp = {
                        pageSize: params.pageSize,  //页面大小
                        pageNo: params.pageNumber, //页码
                        principalDeputySign:"0",
                        orgLevel: "01", //组织机构级别
                        type:2,
                        insState:"1009,1010",
                        exampleInputName1:$("#be_retVis_exampleInputName1").val(),
                        exampleInputName2:$("#be_retVis_exampleInputName2").val(),
                        exampleInputName3:$("#be_retVis_exampleInputName3").val(),
                        exampleInputName4:$("#be_retVis_exampleInputName4").val(),
                        exampleInputName5:$("#be_retVis_exampleInputName5").val(),
                        exampleInputName6:$("#be_retVis_exampleInputName6").val(),
                        exampleInputName7:$("#be_retVis_exampleInputName7").val(),
                        exampleInputName8:$("#be_retVis_exampleInputName8").val(),
                        exampleInputName9:$("#be_retVis_exampleInputName9").val(),
                        exampleInputName20:$("#be_retVis_exampleInputName20").val(),
                        exampleInputName21:$("#be_retVis_exampleInputName21").val(),
                        exampleInputName22:$("#be_retVis_exampleInputName22").val(),
                        exampleInputName23:$("#be_retVis_exampleInputName23").val(),
                        exampleInputName24:$("#be_retVis_exampleInputName24").val(),
                        exampleInputName25:$("#be_retVis_exampleInputName25").val(),
                    };
                    return temp;
                },
                search:function(){
                    $("#be_retVis_exampleInputName").bootstrapTable('destroy');
                    insComanpy_RV.init();
                },
                checkSingleData:function () {
                    var selected = $('#be_retVis_exampleInputName').bootstrapTable('getSelections');
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
                        insComanpy_RV.seItem = selected[0];
                        return true;
                    }
                },
            }
        }();
    </script>

</head>
<body>
<!--列表 -->

<div class="panel panel-default">
    <div class="panel-body">
        <form class="form-inline hz-form-inline" id="examine_search" enctype="application/x-www-form-urlencoded">
            <div class="form-group" >
                <label class="control-label" for="be_retVis_exampleInputName1">保单号</label>
                <input type="text" class="form-control" id="be_retVis_exampleInputName1" name="be_retVis_exampleInputName1" placeholder="保单号">
            </div>
            <div class="form-group" >
                <label class="control-label" for="be_retVis_exampleInputName2">保险公司</label>
                <select type="text" class="form-control" id="be_retVis_exampleInputName2" name="be_retVis_exampleInputName2" placeholder="保险公司"></select>
            </div>
            <div class="form-group" >
                <label class="control-label" for="be_retVis_exampleInputName3">产品名称</label>
                <input type="text" class="form-control" id="be_retVis_exampleInputName3" name="be_retVis_exampleInputName3" placeholder="产品名称"></input>
            </div>
            <div class="form-group" >
                <label class="control-label" for="be_retVis_exampleInputName4">产品代码</label>
                <input type="text" class="form-control" id="be_retVis_exampleInputName4" name="be_retVis_exampleInputName4" placeholder="产品代码">
            </div>
            <div class="form-group" >
                <label class="control-label" for="be_retVis_exampleInputName5">组织机构</label>
                <select type="text" class="form-control" id="be_retVis_exampleInputName5" name="be_retVis_exampleInputName5" placeholder="组织机构"></select>
            </div>
            <div class="form-group" >
                <label class="control-label" for="be_retVis_exampleInputName6">营销团队</label>
                <select type="text" class="form-control" id="be_retVis_exampleInputName6" name="be_retVis_exampleInputName6" placeholder="营销团队"></select>
            </div>
            <div class="form-group" >
                <label class="control-label" for="be_retVis_exampleInputName7">投保人</label>
                <input type="text" class="form-control" id="be_retVis_exampleInputName7" name="be_retVis_exampleInputName7" placeholder="投保人">
            </div>
            <div class="form-group" >
                <label class="control-label" for="be_retVis_exampleInputName8">被保人</label>
                <input type="text" class="form-control" id="be_retVis_exampleInputName8" name="be_retVis_exampleInputName8" placeholder="被保人">
            </div>
            <div class="form-group" >
                <label class="control-label" for="be_retVis_exampleInputName9">员工工号</label>
                <input type="text" class="form-control" id="be_retVis_exampleInputName9" name="be_retVis_exampleInputName9" placeholder="员工工号">
            </div>

            <div class="form-group" >
                <label class="control-label" for="be_retVis_exampleInputName20">投保日期</label>
                <input type="date" class="form-control" id="be_retVis_exampleInputName20" name="be_retVis_exampleInputName20" placeholder="投保日期">
                <label class="control-label control-label-time">至</label>
                <input type="date" class="form-control" id="be_retVis_exampleInputName21" name="be_retVis_exampleInputName21" placeholder="投保日期">
            </div>

            <div class="form-group" >
                <label class="control-label" for="be_retVis_exampleInputName22">生效日期</label>
                <input type="date" class="form-control" id="be_retVis_exampleInputName22" name="be_retVis_exampleInputName22" placeholder="生效日期">
                <label class="control-label control-label-time">至</label>
                <input type="date" class="form-control" id="be_retVis_exampleInputName23" name="be_retVis_exampleInputName23" placeholder="生效日期">
            </div>

            <div class="form-group" >
                <label class="control-label" for="be_retVis_exampleInputName24">承保日期</label>
                <input type="date" class="form-control" id="be_retVis_exampleInputName24" name="be_retVis_exampleInputName24" placeholder="承保日期">
                <label class="control-label control-label-time">至</label>
                <input type="date" class="form-control" id="be_retVis_exampleInputName25" name="be_retVis_exampleInputName25" placeholder="承保日期">
            </div>
             <div class="form-group" >
                <button type="button" class="btn btn-info" onclick="insComanpy_RV.search()"><span class="glyphicon glyphicon-search" aria-hidden="true" >搜索</span></button>
                <button type="button" class="btn btn-danger" onclick="cla()"><span class="glyphicon glyphicon-remove" aria-hidden="true" >清空</span></button>
            </div>
        </form>
    </div>
</div>

<div style="width: 99%;overflow: auto;">
    <table id="be_retVis_exampleInputName" class="table table-hover table-striped table-condensed table-bordered"></table>
</div>
<div id="examine_alert_div" class="modal fade" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false"
     aria-labelledby="examine_zsadd_myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="examine_zsadd_myModalLabel">审核</h4>
            </div>
            <div class="container">
                <form class="form-horizontal" id="examine_fileForm"  enctype="application/x-www-form-urlencoded">
                    <input type="hidden" name="Tab" id="Tab">
                    <div class="form-group">
                        <label class="col-md-2 control-label">审核结果</label>
                        <select class="form-control" style=" width: 150px" id="examine_auditresults" name="examine_auditresults">
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="col-md-2 control-label">审核意见</label>
                        <textarea class="form-control" style=" width: 400px; height: 200px" id="examine_auditopinions" name="examine_auditopinions" type="text"></textarea>
                    </div>
                    <div class="modal-footer col-md-6">
                        <!--用来清空表单数据-->
                        <input type="reset" name="reset" style="display: none;"/>
                        <button type="button" class="btn btn-default" onclick="closeDlg()">关闭</button>
                        <button id="examine_zs_saveButton" type="button" onclick="determine()" class="btn btn-primary">确定</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>