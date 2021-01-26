<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/include/core.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>回执下发</title>
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

        function empty() {
            $("#name").val('');
            $("#cardNo").val('');
            $("#cardType").val('');
            $("#mobile").val('');
            $("#saleName").val('');
            $("#employeeNo").val('');
            $("#salesOrgId").val('');
            $("#salesTeamId").val('');
            a.search();
        }


        $(function () {
            a.init();
            commSalesOrgsHX("salesOrgId");
            $("#salesOrgId").on(
                "change",function(){
                    commSalesTeamsHX("salesTeamId",$(this).find("option:selected").val());
                }
            );
            getSysDictVal("cardType","CARD");
        });

        var a = function () {
            return {
                init: function () {
                    $('#customer-table').bootstrapTable({
                        url: "insurance_policy/customerPage",
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
                        toolbar: "#customer_toolbar_button",
                        showColumns: false, //显示隐藏列
                        uniqueId: "policyId", //每一行的唯一标识，一般为主键列
                        queryParamsType: '',
                        queryParams: a.queryParams,//传递参数（*）
                        columns: [{
                            checkbox: true
                        }, {
                            field: 'SerialNumber',
                            title: '序号',
                            width : '70',
                            formatter: function (value, row, index) {
                                return index + 1;
                            }
                        }, {
                            field: "name",
                            title: "姓名",
                            align: "center",
                            valign: "middle",
                            sortable: "true"
                        }, {
                            field: "cardType",
                            title: "证件类型",
                            align: "center",
                            valign: "middle",
                            sortable: "true"
                        }, {
                            field: "cardNo",
                            title: "证件号码",
                            align: "center",
                            valign: "middle",
                            sortable: "true"
                        }, {
                            field: "mobile",
                            title: "手机号",
                            align: "center",
                            valign: "middle",
                            sortable: "true"
                        }, {
                            field: "firstSaleName",
                            title: "首次员工姓名",
                            align: "center",
                            valign: "middle",
                            sortable: "true"
                        }, {
                            field: "firstEmployeeNo",
                            title: "首次员工编号",
                            align: "center",
                            valign: "middle",
                            sortable: "true"
                        }, {
                            field: "firstSalesOrgName",
                            title: "首次所属组织机构",
                            align: "center",
                            valign: "middle",
                            sortable: "true"
                        }, {
                            field: "firstSalesTeamName",
                            title: "首次所属营销团队",
                            align: "center",
                            valign: "middle",
                            sortable: "true"
                        },  {
                            field: "newSaleName",
                            title: "最新员工姓名",
                            align: "center",
                            valign: "middle",
                            sortable: "true"
                        }, {
                            field: "newEmployeeNo",
                            title: "最新员工编号",
                            align: "center",
                            valign: "middle",
                            sortable: "true"
                        }, {
                            field: "newSalesOrgName",
                            title: "最新所属组织机构",
                            align: "center",
                            valign: "middle",
                            sortable: "true"
                        }, {
                            field: "newSalesTeamName",
                            title: "最新所属营销团队",
                            align: "center",
                            valign: "middle",
                            sortable: "true"
                        }, {
                            field: "createdTime",
                            title: "创建时间",
                            align: "center",
                            valign: "middle",
                            sortable: "true"
                        }, {
                            field: "type",
                            title: "来源类型",
                            align: "center",
                            valign: "middle",
                            sortable: "true",
                            visible:false
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
                queryParams: function (params) {
                    var temp = {
                        pageSize: params.pageSize,  //页面大小
                        pageNo: params.pageNumber, //页码
                        name:$("#name").val(),
                        cardNo:$("#cardNo").val(),
                        cardType:$("#cardType").val(),
                        mobile:$("#mobile").val(),
                        saleName:$("#saleName").val(),
                        employeeNo:$("#employeeNo").val(),
                        salesOrgId:$("#salesOrgId").val(),
                        salesTeamId:$("#salesTeamId").val(),
                    };
                    return temp;
                },
                search:function(){
                    $("#customer-table").bootstrapTable('destroy');
                    a.init();
                },

            }
        }();

        function operateFormatter(value, row, index) {
            var id = row.policyId + "&type=" + row.type;
            return [
                '<shiro:hasPermission name="customer:details">',
                '<button type="button" class="btn btn-info" onclick="selectOne(\'' + id + '\')">',
                '<span class="glyphicon glyphicon-pencil" >查看</span>',
                '</button>',
                '</shiro:hasPermission>',

            ].join('');
        }

        function selectOne(id) {
            createAddProcessTab('customer:details',id);
        }

        function exportAll() {
        	$("#search").attr("action", "insurance_policy/customerExport");
			$("#search").attr("method", "POST");
			$("#search").submit();
        }

        function exportSelect() {
            var a = $('#customer-table').bootstrapTable('getSelections');
            if (a.length < 1) {
                alert("至少选择一条");
                return;
            }
            var cardNos = [];
            for (var i = 0; i < a.length; i++) {
                cardNos.push(a[i].cardNo);
            }
            var data = {"cardNos": JSON.stringify(cardNos)};
			location.href="insurance_policy/customerExport?cardNos="+JSON.stringify(cardNos);

        }

    </script>
</head>
<body>
<!--列表 -->
<div class="panel panel-default">
    <div class="panel-body">
        <form class="form-inline hz-form-inline" id="search" enctype="application/x-www-form-urlencoded">
            <div class="form-group">
                <label class="control-label">姓名</label>
                <input type="text" class="form-control" name="name" id="name"
                		placeholder="姓名">
            </div>
            <div class="form-group">
                <label class="control-label">证件类型</label>
                <select class="form-control" name="cardType" id="cardType"></select>
            </div>
            <div class="form-group">
                <label class="control-label">证件号码</label>
                <input type="text" class="form-control" name="cardNo" id="cardNo"
                		placeholder="证件号码">
            </div>
            <div class="form-group">
                <label class="control-label">手机号</label>
                <input type="text" class="form-control" name="mobile" id="mobile"
                       placeholder="手机号">
            </div>
            <div class="form-group">
                <label class="control-label">最新员工姓名</label>
                <input type="text" class="form-control" name="saleName" id="saleName"
                       placeholder="最新员工姓名">
            </div>
            <div class="form-group">
                <label class="control-label">最新员工工号</label>
                <input type="text" class="form-control" name="employeeNo" id="employeeNo"
                       placeholder="最新员工工号">
            </div>
            <div class="form-group">
                <label class="control-label">最新组织机构</label>
                <select type="text" class="form-control" id="salesOrgId" name="salesOrgId" placeholder="最新组织机构"></select>
            </div>
            <div class="form-group">
                <label class="control-label">最新营销团队</label>
                <select class="form-control" id="salesTeamId" name="salesTeamId" placeholder="salesTeamId"></select>
            </div>

        	<div class="form-group">
        		<button type="button" class="btn btn-info" onclick="a.search()">
                    <span class="glyphicon glyphicon-search" aria-hidden="true" >搜索</span>
                </button>
        		<button type="button" class="btn btn-danger" onclick="empty()">
                    <span class="glyphicon glyphicon-remove" aria-hidden="true" >清空</span>
                </button>
        	</div>
        </form>
    </div>
</div>

<div id="customer_toolbar_button" class="btn-toolbar">
    <shiro:hasPermission name="customerExport:all">
        <button type="button" class=" btn btn-info" onclick="exportAll()">
            <span class="glyphicon glyphicon-pencil">全部导出</span>
        </button>
    </shiro:hasPermission>

    <shiro:hasPermission name="customerExport:select">
        <button type="button" class=" btn btn-info" onclick="exportSelect()">
            <span class="glyphicon glyphicon-pencil">选中导出</span>
        </button>
    </shiro:hasPermission>
</div>

<!--列表 -->
<div style="width: 99%;overflow: auto;">
    <table id="customer-table" class="table table-hover table-striped table-condensed table-bordered"></table>
</div>
</body>
</html>