/*提取数据*/
function extractData() {
    extractPolicyDataHK.init();
    $("#extractData_button").show();
    $("#checkPolicyDataComp_div").hide();
    $("#extractPolicyDataHK_div").show();
}

/*延迟核对*/
function delayCheck(id) {
    $.ajax({
        type: "POST",
        url: "check_policy_data_hk/delayCheck",
        data: {id: id},
        dataType: "json",
        success: function (data) {
            if (data.messageCode == '200') {
                $.alert({
                    title: '提示信息！',
                    content: '操作成功！',
                    type: 'blue'
                });
                $("#cancelDelayCheck_id").val("");
                extractPolicyDataHK.search();
            } else {
                $.alert({
                    title: '提示信息！',
                    content: '操作失败！',
                    type: 'red'
                });
            }
        },
        error: function (XMLHttpRequest) {
            alert(XMLHttpRequest.status);
            $.alert({
                title: '提示信息！',
                content: '请求失败！',
                type: 'red'
            });
        }
    })
}

var extractPolicyDataHK = function () {
    return {
        init: function () {
            $('#extractPolicyDataHK-table').bootstrapTable({
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
                    field: "操作",
                    title: "操作",
                    align: "center",
                    valign: "middle",
                    sortable: "true",
                    formatter: function (value, row, index) {
                        debugger
                       var checkResultFlag = $("#checkResult_id").val();
                        if(row.check_status != '2' && checkResultFlag != '1'){
                          return '<a id = "aaa" href="javascript:void(0)" onclick="delayCheck(\'' + row.id + '\')" style="color:blue">延迟核对</a>';
                        }

                    }
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
                batchNum:$("#batchNum").val(),
                settleStatus:"0",
                cancelDelayCheck:$("#cancelDelayCheck_id").val(),

            };
            return temp;
        },
        search: function () {
            $("#extractPolicyDataHK-table").bootstrapTable('destroy');
            extractPolicyDataHK.init();
        },
    }
}();

    /*点击提取数据按钮*/
function extractDataButton() {
      $("#showOne button").css("background-color","");
     $("#extractData_button").css({'background-color' : 'rgb(184, 202, 235)'});
     $("#batch_div").hide();
    $("#checkPolicyDataComp_div").hide();
$("#extractPolicyDataHK_div").show();
    //值为1  说明已经做过一次点击 匹配过一次 就不再对比
    if ($("#checkResult_id").val() == '1'){
         $("#extractPolicyDataHK-table").bootstrapTable('updateCell', {
						index: 2,       //行索引
						field: "操作",       //列名
						value: "_"          //cell值
					});
         $("#checkResult_id").hide();
    }

    $("#checkResult_div").hide();
}