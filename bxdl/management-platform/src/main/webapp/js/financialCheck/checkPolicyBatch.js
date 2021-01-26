function cla() {
    $("#company_org_name").val('');
     $("#company_org_id").val('');
    $("#check_month").val('');
    $("#sales_org_name").val('');
     $("#sales_org_id").val('');
    $("#minCreateTime").val('');
    $("#maxCreateTime").val('');
    $("#batch_type").val('');
    //$("#status").val('');
     $("#batch_num").val('');
      $("#batch_name").val('');

    checkPolicyBatch.search()
}
var checkPolicyBatch = {
    seItem: null		//选中的条目

};

$(function () {

    checkPolicyBatch.init();
});

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

function batchDetail(id) {
   //commCloseTab('operation:list')
   createAddProcessTab('checkPolicyBatchDetail:list',id)
}
var checkPolicyBatch = function () {
    return {
        init: function () {
            $('#checkPolicyBatch-table').bootstrapTable({
                url: "check_policy_batch/getCheckPolicyBatchPage",
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
                queryParams: checkPolicyBatch.queryParams,//传递参数（*）
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
                    field: "batch_num",
                    title: "批次号码",
                    align: "center",
                    valign: "middle",
                    sortable: "true"
                }, {
                    field: "batch_name",
                    title: "批次名称",
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
                    field: "sales_org_name",
                    title: "组织机构名称",
                    align: "center",
                    valign: "middle",
                    sortable: "true"
                }, {
                    field: "check_month",
                    title: "对账月份",
                    align: "center",
                    valign: "middle",
                    sortable: "true"
                }, {
                    field: "batch_type",
                    title: "对账类型",
                    align: "center",
                    valign: "middle",
                    sortable: "true",
                    formatter: function (value, row, index) {
                        if (value == 0) {
                            value = "手续费对账"
                        } else if (value == 1) {
                            value = "技术服务费对账"
                        } else if (value == 2) {
                            value = "广告服务费对账 "
                        }
                        return value;
                    }
                }/*, {
                    field: "status",
                    title: "状态",
                    align: "center",
                    valign: "middle",
                    sortable: "true",
                    formatter: function (value, row, index) {
                        if (value == 0) {
                            value = "新建"
                        } else if (value == 1) {
                            value = "进行中"
                        } else if (value == 2) {
                            value = "处理完成"
                        }
                        return value;
                    }
                }*/, {
                    field: "create_by_name",
                    title: "创建人",
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
                }, {
                    field: "操作",
                    title: "操作",
                    align: "center",
                    valign: "middle",
                    sortable: "true",
                    formatter: function (value, row, index) {
                        return '<a href="javascript:void(0)" onclick="batchDetail(\'' + row.id + '\')" style="color:blue">批次详情</a>';
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
                checkMonth: $("#check_month").val(),
                salesOrgId: $("#sales_org_id").val(),
                companyOrgId: $("#company_org_id").val(),
                minCreateTime: $("#minCreateTime").val(),
                maxCreateTime: $("#maxCreateTime").val(),
                batchType: $("#batch_type").val(),
               // status: $("#status").val(),
                batchNum: $("#batch_num").val(),
                batchName: $("#batch_name").val(),

            };
            return temp;
        },
        search: function () {
            $("#checkPolicyBatch-table").bootstrapTable('destroy');
            checkPolicyBatch.init();
        },
    }
}();
function addCheckPolicyBatch() {
    commCloseTab('checkPolicyBatch:list')
    createAddProcessTab('checkPolicyBatch:add', '')
}