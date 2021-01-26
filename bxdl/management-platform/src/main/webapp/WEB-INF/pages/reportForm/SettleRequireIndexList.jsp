<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/include/core.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>结算需求指标</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Cache" content="no-cache">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="${path}/css/system/role/role.css">
    <script src="${path}/js/system/comm.js" charset="UTF-8" type="text/javascript"></script>
    <script src="${path}/js/bootstrap3-typeahead.js" type="text/javascript"></script>
    <script src="${path}/static/js/bootstrap.js"></script>
    <%--<script src="${path}/static/js/tableExport.js"></script>--%>
    <%--<script src="${path}/js/jquery-form.js"></script>--%>
    <script type="text/javascript">
        $("#company_org_name").typeahead({
            items: 8,
            source: function (query, process) {
                var parameter = {insuranceCompanyName: query};
                $.post('insurance_dept/findInsurCompanys', parameter, function (data) {
                    process(data.rows);
                });
            },
            displayText: function (item) {
                this.$menu[0].setAttribute("style","margin-top: 35px");
                return item.insuranceCompanyName;
            },
            updater: function (item) {
                $("#company_org_id").val(item.insuranceCompanyId);
                return item;

            },
            afterSelect: function (item) {
                //选择项之后的事件 ，item是当前选中的。
                var id = this.$element[0].id
                $("#" + id).val(item.insuranceCompanyName);
            },
        })


        $("#sales_org_name").typeahead({
            items: 8,
            source: function (query, process) {
                var parameter = {salesOrgName: query,dataAuthFlag: '1'};
                $.post('salesOrgInfo/findSalesOrgs', parameter, function (data) {
                    process(data.rows);
                });
            },
            displayText: function (item) {
                this.$menu[0].setAttribute("style","margin-top: 35px");
                return item.salesOrgName;
            },
            updater: function (item) {
                $("#sales_org_id").val(item.salesOrgId);
                return item;

            },
            afterSelect: function (item) {
                //选择项之后的事件 ，item是当前选中的。
                var id = this.$element[0].id
                $("#" + id).val(item.salesOrgName);
            },
        })

        /*保险公司清空*/
        $('#company_org_name').bind('input propertychange', function () {
            $("#company_org_id").val("");
        });
        /*组织机构清空*/
        $('#sales_org_name').bind('input propertychange', function () {
            $("#sales_org_id").val("");
        });

        $(function () {
            settleRequireIndex.init();
        });

        var settleRequireIndex = function () {
            return {
                init: function () {
                    $('#data-table').bootstrapTable({
                        url: "reportForm/getSettleRequireIndexData",
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
                        pageSize: 10, //默认每页条数
                        pageNumber: 1, //默认分页
                        pageList: [5, 10, 20, 50],//分页数
                        //toolbar:"#insdept",
                        showColumns: false, //显示隐藏列
                        //uniqueId: "INSURANCE_COMPANY_ID", //每一行的唯一标识，一般为主键列
                        queryParamsType: '',
                        queryParams: settleRequireIndex.queryParams,//传递参数（*）
                        columns: [
                            {
                                field: 'SerialNumber',
                                title: '序号',
                                formatter: function (value, row, index) {
                                    return index + 1;
                                }
                            },
                            {
                                field: "SETTLE_MONTH",
                                title: "结算月",
                                align: "center",
                                valign: "middle",
                                sortable: "true"
                            },
                            {
                                field: "SALES_ORG_CODE",
                                title: "机构代码",
                                align: "center",
                                valign: "middle",
                                sortable: "true"
                            },
                            {
                                field: "SALES_ORG_NAME",
                                title: "机构名称",
                                align: "center",
                                valign: "middle",
                                sortable: "true"
                            },
                            {
                                field: "COMPANY_ORG_CODE",
                                title: "保险公司代码",
                                align: "center",
                                valign: "middle",
                                sortable: "true",

                            },
                            {
                                field: "COMPANY_ORG_NAME",
                                title: "保险公司名称",
                                align: "center",
                                valign: "middle",
                                sortable: "true",
                            },
                            // {
                            //     field: "INS_ORG_NAME",
                            //     title: "保险机构名称",
                            //     align: "center",
                            //     valign: "middle",
                            //     sortable: "true"
                            // },
                            {
                                field: "SETTLE_FEE",
                                title: "应结手续费(元)",
                                align: "center",
                                valign: "middle",
                                sortable: "true"
                            },
                            {
                                field: "SETTLED_FEE",
                                title: "已结手续费（元）",
                                align: "center",
                                valign: "middle",
                                sortable: "true"
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
                search: function () {
                    $("#data-table").bootstrapTable('destroy');
                    settleRequireIndex.init();
                },
                queryParams: function (params) {
                    var temp = {
                        pageSize: params.pageSize,  //页面大小
                        pageNo: params.pageNumber, //页码
                        salesOrgId: $("#sales_org_id").val(),
                        companyOrgId: $("#company_org_id").val(),
                        settleMonth: $("#settleMonth").val()
                    };
                    return temp;
                },
            }
        }();

        function cla() {
            $("#company_org_name").val('');
            $("#company_org_id").val('');
            $("#sales_org_name").val('');
            $("#sales_org_id").val('');
            $("#settleMonth").val('');

            settleRequireIndex.search()
        }

    </script>

</head>
<body>
<div class="panel panel-default">
    <div class="panel-body">
        <form class="form-inline hz-form-inline" id="searchForm">
            <div class="form-group has-feedback">
                <label class="control-label" for="company_org_name">保险公司名称</label>
                <input type="text" class="form-control" id="company_org_name" name="company_org_name" placeholder="保险公司名称" data-provide="typeahead" autocomplete="off" onmouseover="this.title=this.value"></input>
                <span class="glyphicon glyphicon-search form-control-feedback"></span>
                <input type="hidden" class="form-control" id="company_org_id" name="company_org_id" ></input>
            </div>
            <div class="form-group has-feedback">
                <label class="control-label" for="sales_org_name">组织机构名称</label>
                <input type="text" class="form-control" id="sales_org_name" name="sales_org_name" placeholder="组织机构名称" data-provide="typeahead" autocomplete="off" onmouseover="this.title=this.value"></input>
                <span class="glyphicon glyphicon-search form-control-feedback"></span>
                <input type="hidden" class="form-control" id="sales_org_id" name="sales_org_id"></input>
            </div>
            <div class="form-group">
                <label class="control-label" for="settleMonth">结算月度</label>
                <input type="month" class="form-control" id="settleMonth" name="settleMonth" placeholder="结算月度">
            </div>
            <div class="form-group">
                <button type="button" class="btn btn-info" onclick="settleRequireIndex.search()">
                    <span class="glyphicon glyphicon-search" aria-hidden="true">搜索</span>
                </button>
                <button type="button" class="btn btn-danger" onclick="cla()">
                    <span class="glyphicon glyphicon-remove" aria-hidden="true">清空</span>
                </button>
            </div>
        </form>
    </div>
</div>
<!--列表 -->
<div style="width: 99%;overflow: auto;">
    <table id="data-table" class="table table-hover table-striped table-condensed table-bordered"></table>
</div>
</body>
</html>