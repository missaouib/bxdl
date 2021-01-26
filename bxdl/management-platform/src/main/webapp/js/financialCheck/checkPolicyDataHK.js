function cla() {
    $("#company_org_name").val('');
    $("#company_org_id").val('');
    $("#check_month").val('');
    $("#sales_org_name").val('');
    $("#sales_org_id").val('');
    $("#product_name").val('');
     $("#product_id").val('');
      $("#check_status").val('');
       $("#settle_status").val('');
    checkPolicyDataHK.search()
}
var checkPolicyDataHK = {
    seItem: null		//选中的条目

};

$(function () {

    checkPolicyDataHK.init();
    //getInsurCompanys("company_org_id");
    //getSalesOrgs("sales_org_id");
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

$("#product_name").typeahead({
    items: 8,
    source: function (query, process) {
        var parameter = {productNameLike: query};
        $.post('product_basic_information/findInsurProducts', parameter, function (data) {
            process(data.rows);
        });
    },
    displayText: function (item) {
       this.$menu[0].setAttribute("style","margin-top: 35px");
        return item.productName;
    },
    updater: function (item) {
        $("#product_id").val(item.productId);
        return item;

    },
    afterSelect: function (item) {
        //选择项之后的事件 ，item是当前选中的。
        var id = this.$element[0].id
        $("#" + id).val(item.productName);
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

/*产品清空*/
$('#product_name').bind('input propertychange', function () {
    $("#product_id").val("");
});


var checkPolicyDataHK = function () {
    return {
        init: function () {
            $('#checkPolicyDataHK-table').bootstrapTable({
                url: "check_policy_data_hk/getCheckPolicyDataHkPage",
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
                uniqueId: "ins_policy_hk_id", //每一行的唯一标识，一般为主键列
                queryParamsType: '',
                queryParams: checkPolicyDataHK.queryParams,//传递参数（*）
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
                    field: "check_status",
                    title: "核对状态",
                    align: "center",
                    valign: "middle",
                    sortable: "true",
                    formatter:function (value) {
                       if (value == "0"){
                           return "未核对";
                       }else if (value == "1"){
                           return "核对无误";
                       }else if (value == "2"){
                           return "延迟核对";
                       }else if (value == "3"){
                           return "我司存在";
                       }else if (value == "4"){
                           return "核对不一致";
                       }
                    }
                }, {
                    field: "settle_status",
                    title: "结算状态",
                    align: "center",
                    valign: "middle",
                    sortable: "true",
                     formatter:function (value) {
                        if (value == "0"){
                           return "未结算";
                       }else if (value == "1"){
                           return "已结算";
                       }
                    }

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
                checkMonth: $("#check_month").val(),
                salesOrgId: $("#sales_org_id").val(),
                companyOrgId: $("#company_org_id").val(),
                salesOrgName: $("#sales_org_name").val(),
                companyOrgName: $("#company_org_name").val(),
                productId: $("#product_id").val(),
                productName: $("#product_name").val(),
                checkStatus: $("#check_status").val(),
                settleStatus: $("#settle_status").val(),

            };
            return temp;
        },
        search: function () {
            $("#checkPolicyDataHK-table").bootstrapTable('destroy');
            checkPolicyDataHK.init();
        },
    }
}();


