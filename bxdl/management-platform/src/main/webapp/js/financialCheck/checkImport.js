/*初始化对账导入数据*/
$(function () {
    checkPolicyDataComp.init();
})
var checkPolicyDataComp = function () {
    return {
        init: function () {
            $('#checkPolicyDataComp-table').bootstrapTable({
                url: "check_policy_data_comp/getCheckPolicyDataCompPage",
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
                toolbar: "#toolbarPolList",
                showColumns: false, //显示隐藏列
                uniqueId: "id", //每一行的唯一标识，一般为主键列
                queryParamsType: '',
                queryParams: checkPolicyDataComp.queryParams,//传递参数（*）
                columns: [{
                    checkbox: true
                }, {
                    field: 'SerialNumber',
                    title: '序号',
                    formatter: function (value, row, index) {
                        return index + 1;
                    }
                }, {
                    field: "id",
                    visible: false
                }, {
                    field: "check_month",
                    title: "结算月",
                    align: "center",
                    valign: "middle",
                    sortable: "true"
                }, {
                    field: "sales_org_name",
                    title: "机构名称",
                    align: "center",
                    valign: "middle",
                    sortable: "true"
                }, {
                    field: "company_org_name",
                    title: "保险公司名称",
                    align: "center",
                    valign: "middle",
                    sortable: "true"
                }, {
                    field: "policy_id",
                    title: "保单号",
                    align: "center",
                    valign: "middle",
                    sortable: "true"
                }, {
                    field: "product_name",
                    title: "投保险种",
                    align: "center",
                    valign: "middle",
                    sortable: "true"
                }, {
                    field: "insurance_type",
                    title: "险种类别",
                    align: "center",
                    valign: "middle",
                    sortable: "true"
                }, {
                    field: "propost_date",
                    title: "投保日期",
                    align: "center",
                    valign: "middle",
                    sortable: "true"
                }, {
                    field: "underwriting_date",
                    title: "生效日期",
                    align: "center",
                    valign: "middle",
                    sortable: "true"
                }, {
                    field: "premium",
                    title: "规模保费",
                    align: "center",
                    valign: "middle",
                    sortable: "true"
                }, {
                    field: "payment_method",
                    title: "缴费方式",
                    align: "center",
                    valign: "middle",
                    sortable: "true"
                }, {
                    field: "payment_period",
                    title: "缴费期间",
                    align: "center",
                    valign: "middle",
                    sortable: "true"
                }, {
                    field: "payment_num",
                    title: "首期/续期",
                    align: "center",
                    valign: "middle",
                    sortable: "true"
                }, {
                    field: "process_cost",
                    title: "手续费",
                    align: "center",
                    valign: "middle",
                    sortable: "true"
                }, {
                    field: "pust_cost",
                    title: "推动费",
                    align: "center",
                    valign: "middle",
                    sortable: "true"

                }, {
                    field: "total_cost",
                    title: "合计",
                    align: "center",
                    valign: "middle",
                    sortable: "true"

                }, {
                    field: "remark",
                    title: "备注",
                    align: "center",
                    valign: "middle",
                    sortable: "true"
                }, {
                    field: "batch_num",
                    title: "批次",
                    align: "center",
                    valign: "middle",
                    sortable: "true"
                }, {
                    field: "create_time",
                    title: "创建时间",
                    align: "center",
                    valign: "middle",
                    sortable: "true",
                    formatter: function (value, row, index) {
                        var time = new Date(row.create_time);
                        var year = time.getFullYear();
                        var month = (time.getMonth() + 1 < 10) ? '0' + (time.getMonth() + 1) : (time.getMonth() + 1);
                        var date = (time.getDate() + 1 < 11) ? '0' + time.getDate() : time.getDate();
                        var hours = (time.getHours() + 1 < 11) ? '0' + time.getHours() : time.getHours();
                        var minutes = (time.getMinutes() + 1 < 11) ? '0' + time.getMinutes() : time.getMinutes();
                        var seconds = (time.getSeconds() + 1 < 11) ? '0' + time.getSeconds() : time.getSeconds();
                        return year + '-' + month + '-' + date + ' ' + hours + ':' + minutes + ':' + seconds;
                    }
                }],

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
                batchNum: $("#batchNum").val()
            };
            return temp;
        },
        search: function () {
            $("#checkPolicyDataComp-table").bootstrapTable('destroy');
            checkPolicyDataComp.init();
        },
    }
}();
