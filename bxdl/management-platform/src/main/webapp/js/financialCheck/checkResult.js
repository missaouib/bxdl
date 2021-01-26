function startCheck() {
    $("#checkResult_id").val("1");
    beginComparison();
}

function beginComparison() {
     var checkMonth= $("#checkMonth").val();
     var salesOrgId=$("#salesOrgId").val();
     var companyOrgId= $("#companyOrgId").val();
     var batchNum = $("#batchNum_file").val();
     $.ajax({
        type: "POST",
        url: "check_policy_data_comp/beginComparison",
        data: {checkMonth: checkMonth,salesOrgId:salesOrgId,companyOrgId:companyOrgId,batchNum:batchNum},
        dataType: "json",
        success: function (data) {
            if (data.code == '200') {
                $("#checkResultForStatus_1").css({'background-color' : 'rgb(184, 202, 235)'});
                $("#checkReustStatus_id").val("1");
                checkResult.init();
                $("#checkResult_button").show();
                $("#showSecond").show();
                $("#extractPolicyDataHK_div").hide();
                $("#checkResult_div").show();
            } else {
                $.alert({
                    title: '提示信息！',
                    content: '核对失败！',
                    type: 'red'
                });
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            $.alert({
                title: '提示信息！',
                content: '请求失败！',
                type: 'red'
            });
        }
    })
}

function showCheckResultForStatus(id) {
    $("#showSecond button").css("background-color","");
     $("#checkResultForStatus_"+id).css({'background-color' : 'rgb(184, 202, 235)'});
    $("#checkReustStatus_id").val(id);
    checkResult.search();
}


    /*导出excel*/
    function exportExtractData() {
        var checkMonth =  $("#checkMonth").val();
        var batchNum = $("#batchNum").val();
        var checkReustStatus = $("#checkReustStatus_id").val();
        var params =  "checkMonth=" + checkMonth +"&batchNum=" + batchNum + "&checkReustStatus=" + checkReustStatus;
        location.href="check_policy_data_comp/exportExtractData?"+params;
    }

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