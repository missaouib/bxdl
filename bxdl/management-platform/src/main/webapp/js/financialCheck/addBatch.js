$("#addCheckPolicyBatchForm").bootstrapValidator({
     excluded:[":disabled"],
    fields: {
        checkMonth: {
            trigger: 'focus',
            validators: {
                notEmpty: {
                    message: "请选择对账时间"
                }
            }
        },
       /* salesOrgId: {
              trigger:'change',
            validators: {
                notEmpty: {
                    message: "请选择正确的组织机构"
                }
            }
        },*/
         salesOrgName: {
             //trigger: 'focus',
            validators: {
                notEmpty: {
                    message: "请选择组织机构"
                }
            }
        },
        companyOrgName: {
            //  trigger: 'focus',
            validators: {
                notEmpty: {
                    message: "请选择保险公司"
                }
            }
        },
        batchName: {
            validators: {
                notEmpty: {
                    message: "对账名称不能为空"
                }
            }
        },
    }
})

/*$("#checkMonth").blur(function () {

 $('#addCheckPolicyBatchForm').data('bootstrapValidator')
 .updateStatus('checkMonth', 'NOT_VALIDATED',null)
 .validateField('checkMonth');
 })*/

$("#companyOrgName").typeahead({
    items: 8,
    source: function (query, process) {
        var parameter = {insuranceCompanyName: query};
        $.post('insurance_dept/findInsurCompanys', parameter, function (data) {
            process(data.rows);
        });
    },
    displayText: function (item) {
      // this.$menu[0].setAttribute("style","margin-top: 20px");
        return item.insuranceCompanyName;
    },
    updater: function (item) {
        $("#companyOrgId").val(item.insuranceCompanyId);
        return item;

    },
    afterSelect: function (item) {
        //选择项之后的事件 ，item是当前选中的。
        var id = this.$element[0].id
        $("#" + id).val(item.insuranceCompanyName);
    },
})


$("#salesOrgName").typeahead({
    items: 8,
    source: function (query, process) {
        var parameter = {salesOrgName: query,dataAuthFlag: '1'};
        $.post('salesOrgInfo/findSalesOrgs', parameter, function (data) {
            process(data.rows);
        });
    },
    displayText: function (item) {
      // this.$menu[0].setAttribute("style","margin-top: 35px");
        return item.salesOrgName;
    },
    updater: function (item) {
        $("#salesOrgId").val(item.salesOrgId);
        return item;

    },
    afterSelect: function (item) {
        //选择项之后的事件 ，item是当前选中的。
        var id = this.$element[0].id
        $("#" + id).val(item.salesOrgName);
    },
})

/*保险公司清空*/
$('#companyOrgName').bind('input propertychange', function () {
    $("#companyOrgId").val("");
});
/*组织机构清空*/
$('#salesOrgName').bind('input propertychange', function () {
    $("#salesOrgId").val("");
});

//返回列表
function returnCheckPolicyBatchList() {
    commCloseTab('checkPolicyBatch:add');
    createAddProcessTab('checkPolicyBatch:list', '');
}
/*保存批次*/
function addCheckBatch() {
    var salesOrgId = $("#salesOrgId").val();
    var companyOrgId = $("#companyOrgId").val();
    if (salesOrgId == ""){
       alert("请选择正确的组织机构名称");
       return
    }
    if (companyOrgId == ""){
       alert("请选择正确的保险公司名称");
       return
    }
    //保险公司 导入数据页面
    if ($("#addCheckPolicyBatchForm").data('bootstrapValidator').validate().isValid()) {
        $.ajax({
            url: 'check_policy_batch/addCheckPolicyBatch',
            type: 'post',
            dataType: 'json',
            data: $("#addCheckPolicyBatchForm").serialize(),
            beforeSend: function () {
                $("#saveCheckPolicyBatch_id").attr('disabled', 'disabled');
            },
            complete: function () {
                $("#saveCheckPolicyBatch_id").removeAttr('disabled');
            },
            success: function (data) {
                if (data.messageCode == '300') {
                    $.alert({
                        title: '提示信息！',
                        content: data.data,
                        type: 'red'
                    });
                } else if (data.messageCode == '200') {
                    /*  $.alert({
                     title: '提示信息！',
                     content: '添加成功!',
                     type: 'blue'
                     });*/
                    //保险公司 导入数据页面
                    $("#checkImport_button").removeAttr("hidden");
                     //改变值 说明已经生成过批次
                    $("#saveCheckPolicyBatch_id").val("1");
                    //添加批次隐藏
                    $("#batch_div").hide();
                    $("#checkPolicyDataComp_div").removeAttr("hidden");
                    //赋值批次号
                    $("#batchNum_file").val($("#batchNum").val());

                } else {
                    $.alert({
                        title: '提示信息！',
                        content: '添加失败！',
                        type: 'red'
                    });
                }
            },
            error: function () {
                $.alert({
                    title: '提示信息！',
                    content: '请求失败！',
                    type: 'red'
                });
            }
        });
    }
}
/*生成批次按钮*/
function createBatchButton() {
     $("#showOne button").css("background-color","");
     $("#addBatch_button").css({'background-color' : 'rgb(184, 202, 235)'});
      $("#showSecond").hide();

    $("#batch_div").show();
    //值为1  说明已经生成过批次  隐藏生成批次按钮
    if ($("#saveCheckPolicyBatch_id").val() == '1'){
          $("#saveCheckPolicyBatch_id").hide();
         $("#returnCheckPolicyBatchList_id").css("margin-left","400px");
    }

    $("#checkPolicyDataComp_div").hide();
    $("#extractPolicyDataHK_div").hide();
    $("#checkResult_div").hide();
}

function checkImportButton() {
     $("#showOne button").css("background-color","");
     $("#checkImport_button").css({'background-color' : 'rgb(184, 202, 235)'});
      $("#showSecond").hide();
     $("#batch_div").hide();
    $("#checkPolicyDataComp_div").show();
    $("#toolbarPolList").hide();
    $("#extractPolicyDataHK_div").hide();
    $("#checkResult_div").hide();
}

function checkResultButton() {
     $("#batch_div").hide();
    $("#checkPolicyDataComp_div").hide();
    $("#extractPolicyDataHK_div").hide();
    $("#checkResult_div").show();
}