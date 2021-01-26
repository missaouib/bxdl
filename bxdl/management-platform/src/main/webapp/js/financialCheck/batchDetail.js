
//返回列表
function returnCheckPolicyBatchList() {
    commCloseTab('checkPolicyBatch:add');
    createAddProcessTab('checkPolicyBatch:list', '');
}
/*生成批次按钮*/
function createBatchButton() {
     $("#showOne button").css("background-color","");
     $("#addBatch_button").css({'background-color' : 'rgb(184, 202, 235)'});
      $("#showSecond").hide();
    $("#batch_div").show();
    $("#checkPolicyDataComp_div").hide();
    $("#extractPolicyDataHK_div").hide();
    $("#checkResult_div").hide();
}

function checkImportButton() {
     $("#showOne button").css("background-color","");
     $("#checkImport_button").css({'background-color' : 'rgb(184, 202, 235)'});
      $("#showSecond").hide();
     $("#batch_div").hide();
     checkPolicyDataComp.init();
    $("#checkPolicyDataComp_div").show();
    $("#extractPolicyDataHK_div").hide();
    $("#checkResult_div").hide();
}
function extractDataButton() {
     $("#showOne button").css("background-color","");
     $("#extractData_button").css({'background-color' : 'rgb(184, 202, 235)'});
      $("#showSecond").hide();
     $("#batch_div").hide();
    $("#checkPolicyDataComp_div").hide();
    extractPolicyDataHK.init();
    $("#extractPolicyDataHK_div").show();
    $("#checkResult_div").hide();
}
function checkResultButton() {
     $("#showOne button").css("background-color","");
     $("#checkResult_button").css({'background-color' : 'rgb(184, 202, 235)'});
     $("#batch_div").hide();
    $("#checkPolicyDataComp_div").hide();
     $("#showSecond button").css("background-color","");
     $("#checkResultForStatus_1").css({'background-color' : 'rgb(184, 202, 235)'});
    $("#checkReustStatus_id").val("1");
    checkResult.search();
    $("#showSecond").show();
    $("#extractPolicyDataHK_div").hide();
    $("#checkResult_div").show();
}

/*按照批次查询保险公司数据*/
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


   /*提取我司数据*/
    var extractPolicyDataHK = function () {
    return {
        init: function () {
            $('#extractPolicyDataHK-table').bootstrapTable({
                url: "check_policy_data_hk/getDataHkBatchPage",
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
                showColumns: false, //显示隐藏列
                uniqueId: "id", //每一行的唯一标识，一般为主键列
                queryParamsType: '',
                queryParams: extractPolicyDataHK.queryParams,//传递参数（*）
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
                },{
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
                },/*, {
                    field: "操作",
                    title: "操作",
                    align: "center",
                    valign: "middle",
                    sortable: "true",
                    formatter: function (value, row, index) {
                        return '<a href="javascript:void(0)" onclick="delayCheck(\'' + row.id + '\')" style="color:blue">延迟核对</a>';
                    }
                }*/
                ],

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
                batchNum: $("#batchNum").val(),
            };
            return temp;
        },
        search: function () {
            $("#extractPolicyDataHK-table").bootstrapTable('destroy');
            extractPolicyDataHK.init();
        },
    }
}();

 function showCheckResultForStatus(id) {
    $("#showSecond button").css("background-color","");
     $("#checkResultForStatus_"+id).css({'background-color' : 'rgb(184, 202, 235)'});
    $("#checkReustStatus_id").val(id);
    checkResult.search();
}
    /*展示核对结果*/
    var checkResult = function () {
    return {
        init: function () {
            $('#checkResult-table').bootstrapTable({
                url: "check_policy_data_comp/getCheckResultPage",
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
                 toolbar: "#toolbarCheckResult",
                showColumns: false, //显示隐藏列
                uniqueId: "id", //每一行的唯一标识，一般为主键列
                queryParamsType: '',
                queryParams: checkResult.queryParams,//传递参数（*）
                onLoadSuccess: function (data) {
                        $("#checkResultForStatus_1").html("4.1核对不一致("+data.resultDiff+")");
                $("#checkResultForStatus_2").html("4.2.我司存在,保司不存在("+data.hkExist+")");
                $("#checkResultForStatus_3").html("4.3.保司存在,我司不存在("+data.compExist+")");
                $("#checkResultForStatus_4").html("4.4.核对无误("+data.resultNoDiff+")");
                   // $("#total_premium").html(data.data == null ? 0 : data.data)
                },
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
                }
                ],

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
                checkMonth: $("#checkMonth").val(),
                salesOrgId: $("#salesOrgId").val(),
                companyOrgId: $("#companyOrgId").val(),
                 batchNum: $("#batchNum").val(),
                checkReustStatus:$("#checkReustStatus_id").val()
            };
            return temp;
        },
        search: function () {
            $("#checkResult-table").bootstrapTable('destroy');
            checkResult.init();
        },
    }
}();

    /*导出excel*/
    function exportExtractData() {
        var checkMonth =  $("#checkMonth").val();
        var batchNum = $("#batchNum").val();
        var checkReustStatus = $("#checkReustStatus_id").val();
        var params =  "checkMonth=" + checkMonth +"&batchNum=" + batchNum + "&checkReustStatus=" + checkReustStatus;
        location.href="check_policy_data_comp/exportExtractData?"+params;
    }