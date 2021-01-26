
//基本法--参数骨子额配置的单例对象
var CalParmRule = {
    seItem: null		//选中的条目
};

$(function(){
    //findDictByDictTypeRetEntity('SEX', RULETYPE);
    CalParmRule.formValidator();
    CalParmRule.init();
    $('.close').click(function(){
        CalParmRule.closeDlg();
    });
});
/*获取规则类型文本*/
function getRuleTypText(ruleVal) {

    var result = "";
    var ruleArray = ruleVal.split(",");
    for(var i = 0;i < ruleArray.length; i++){
        result += "  " + RULETYPE[ruleArray[i]].dictName + "  " +'|';
    }
    if(result.lastIndexOf("|" != -1)) {
        result = result.substr(0, (result.length - 1));
    }
    return result;
}

/*获取参数类型文本*/
function getParamTypText(paramTypeVal){
    return PARAMTYPE[paramTypeVal].dictName;
}

var CalParmRule = function () {
    return{
        init:function(){
            $('#calParamRule-table').bootstrapTable({
                url: "/calParamRuleConfig/getListByPage",
                method:"get",
                dataType: "json",
                contentType: "application/x-www-form-urlencoded",
                striped:true,//隔行变色
                cache:false,  //是否使用缓存
                showColumns:false,// 列
                toobar:'#toolbar',
                pagination: true, //分页
                sortable: false, //是否启用排序
                singleSelect: false,
                search:false, //显示搜索框
                buttonsAlign: "right", //按钮对齐方式
                showRefresh:false,//是否显示刷新按钮
                sidePagination: "server", //服务端处理分页
                pageSize : 10, //默认每页条数
                pageNumber : 1, //默认分页
                pageList : [5, 10, 20, 50 ],//分页数
                toolbar:"#toolbar",
                showColumns : false, //显示隐藏列
                uniqueId: "id", //每一行的唯一标识，一般为主键列
                queryParamsType:'',
                queryParams: CalParmRule.queryParams,//传递参数（*）
                columns : [{
                    checkbox: true
                },{
                    field: 'SerialNumber',
                    title: '序号',
                    width : '70',
                    formatter: function (value, row, index) {
                        return index + 1;
                    }
                },{
                    field : "orgName",
                    title : "机构名称",
                    align : "center",
                    valign : "middle",
                    sortable : "true"
                }, {
                    field : "param_type",
                    title : "参数名称",
                    align : "center",
                    valign : "middle",
                    sortable : "true",
                    formatter: function (value, row, index) {
                        return getParamTypText(value);
                    }
                }, {
                    field : "rule_type",
                    title : "规则",
                    align : "center",
                    valign : "middle",
                    sortable : "true",
                    formatter: function (value, row, index) {
                        debugger;
                        return getRuleTypText(value);
                    }
                }],
                formatLoadingMessage : function() {
                    return "请稍等，正在加载中...";
                },
                formatNoMatches : function() {
                    return '无符合条件的记录';
                }
            });
        },
        cla:function() {
            $("#s_orgId").val('');
            $("#s_paramType").val('');
            CalParmRule.search()
        },
        search:function(){
            $("#calParamRule-table").bootstrapTable('destroy');
            CalParmRule.init();
        },
        //得到查询的参数
        queryParams:function (params) {
            var temp = {
                pageSize: params.pageSize,  //页面大小
                pageNumber: params.pageNumber, //页码
                orgId: $("#s_orgId").val(), //机构id
                paramType: $("#s_paramType").val(), //参数类型
            };
            return temp;
        },
        /**
         * 检查是否选中单条记录
         */
        checkSingleData:function () {
            var selected = $('#calParamRule-table').bootstrapTable('getSelections');
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
                CalParmRule.seItem = selected[0];
                return true;
            }
        },
        //打开添加模态框
        openAddModal:function () {
            $("#id").val('');
            $("#calParamRuleAddDlg").modal('show');
        },
        //添加
        saveCalParamRuleConfig:function () {
            //document.getElementById("saveButton").setAttribute("disabled", true);
            flag = false;
            /**校验录入项 */
            var valiMsg = '';
            var paramType = $("#add_paramType").val();
            var obj = $("#ruleType" + paramType);
            var name = 'ruleType' + paramType;
            debugger;
            if($("#add_orgId").val() == null || $("#add_orgId").val() == '') {
                valiMsg = '请选择机构';
            }else if($("#add_paramType").val() == null || $("#add_paramType").val() == '') {
                valiMsg = '请选择参数类型';
            }else if($("input[name="+ name+"]:checked").length == 0) {
                valiMsg = '请勾选规则';
            }else if(paramType == '4'){
                if($("input[name ='activeSalerFlag']:checked").length == 0) {
                    valiMsg = '请选择是否包含组活力人数';
                }else if( $("input[name ='excludeDirectlyGroupFlag']:checked").length == 0) {
                    valiMsg = '请选择直辖组参数是否排除部长直辖组';
                }
            }else if(paramType == '5' && $("input[name ='includeDirectlyGroupFlag']:checked").length == 0) {
                valiMsg = '请选择直辖部参数是否包含部长直辖组';
            }
            if(valiMsg != '') {
                $.alert({
                    title: '提示信息！',
                    content: valiMsg + '!',
                    type: 'red'
                });
                return;
            }
            $.ajax({
                url:'calParamRuleConfig/saveCalParamRuleConfig',
                dataType:'json',
                data:$("#addCalParamRuleForm").serialize(),
                type:'post',
                async:false, //同步 验证后再执行
                success:function(data){
                    if(data && data.messageCode == '200') {
                        $.alert({
                            title: '提示信息！',
                            content: '保存成功!',
                            type: 'blue'
                        });

                    }else{
                        $.alert({
                            title: '提示信息！',
                            content: data.errorMsg,
                            type: 'red'
                        });
                    }
                    CalParmRule.closeDlg();
                    $("#employee-table").bootstrapTable('refresh');
                },
                error:function(){
                    $.alert({
                        title: '提示信息！',
                        content: '请求失败！',
                        type: 'red'
                    });
                }
            });

        },
        //打开修改模态框
        openUpdateModal:function () {
            if(this.checkSingleData()) {
                var id = CalParmRule.seItem.id;
                $.ajax({
                    url:'/calParamRuleConfig/getCalParamRuleById',
                    dataType:'json',
                    type:'get',
                    data:{id:id},
                    success:function(data){
                        if(data.code == '200') {
                            showUpdateModalData(data.data);
                        }else {
                            $.alert({
                                title: '提示信息！',
                                content: data.errorMsg,
                                type: 'red'
                            });
                            return;
                        }
                    },
                    error:function(){
                        $.alert({
                            title: '提示信息！',
                            content: '请求失败！',
                            type: 'red'
                        });
                    }
                });
                $("#calParamRuleAddDlg").modal('show');
            }
        },
        //修改规则权限
        updateCalParamRule:function () {

        },
        //删除规则权限
        deleteCalParamRule:function () {
            // if(this.checkSingleData()) {
            //     var id = CalParmRule.seItem.id;
            //     $.confirm({
            //         title: '提示信息!',
            //         content: '您确定要删除这个参数规则配置吗？',
            //         type: 'blue',
            //         typeAnimated: true,
            //         buttons: {
            //             确定: {
            //                 action: function(){
            //                     $.ajax({
            //                         url:'role/deleteRole',
            //                         dataType:'json',
            //                         type:'post',
            //                         data:{
            //                             id:id
            //                         },
            //                         success:function(data){
            //                             if(data){
            //                                 $.alert({
            //                                     title: '提示信息！',
            //                                     content: '删除成功!',
            //                                     type: 'blue'
            //                                 });
            //                             }else{
            //                                 $.alert({
            //                                     title: '提示信息！',
            //                                     content: '删除失败!',
            //                                     type: 'red'
            //                                 });
            //                             }
            //                             $("#role-table").bootstrapTable('refresh');
            //                         },
            //                         error:function(){
            //                             $.alert({
            //                                 title: '提示信息！',
            //                                 content: '请求失败！',
            //                                 type: 'red'
            //                             });
            //                         }
            //                     });
            //                 }
            //             },
            //             取消: function () {
            //             }
            //         }
            //     });
            // }

        },

        //关闭模态框
        closeDlg:function () {
            $('#addCalParamRuleForm')[0].reset()
            $("div[name = 'ruleTypeName']").hide();
            $("div[name = 'flagDivName']").hide();
            $("#calParamRuleAddDlg").modal('hide');
            $('#addCalParamRuleForm').data('bootstrapValidator', null);
            document.getElementById("saveButton").removeAttribute("disabled");
            // $("#roleAddDlg").modal('hide');
            // $("#roleMydlg").modal('hide');
            // $("input[type=reset]").trigger("click");
            // $('#updateRoleForm').data('bootstrapValidator', null);
            // $('#addRoleForm').data('bootstrapValidator', null);
            // document.getElementById("saveButton").removeAttribute("disabled");
            // Role.formValidator();

        },


        //表单验证
        formValidator:function () {
            $("#addCalParamRuleForm").bootstrapValidator({
                fields:{
                    orgId:{
                        validators:{
                            notEmpty:{
                                message:"请选择总/省分机构"
                            }
                        }
                    },
                    paramType:{
                        validators:{
                            notEmpty:{
                                message:'请选择参数类型'
                            }
                        }
                    }
                }
            });


            $("#updateRoleForm").bootstrapValidator({
                fields:{
                    roleName:{
                        validators:{
                            notEmpty:{
                                message:"角色名称不能为空"
                            },
                            stringLength:{
                                max:20,
                                message:'不能超过20个字符长度'
                            },
                        }
                    },
                    roleCode:{
                        validators:{
                            notEmpty:{
                                message:'角色编码不能为空',
                            },
                            stringLength:{
                                max:200,
                                message:'字符长度不能超过20'
                            }
                        }
                    },
                    remark:{
                        validators:{
                            notEmpty:{
                                message:'角色描述不能为空',
                            },
                            stringLength:{
                                max:200,
                                message:'字符长度不能超过200'
                            }
                        }
                    }
                }
            });
        }

    }


}();
//修改参数类型触发change事件
$("#add_paramType").change(function () {
    var paramType = $("#add_paramType").val();
    /*显示参数对应的规则*/
    var obj = $("#ruleType" + paramType);
    obj.show();
    $(obj).siblings("div").hide();
    if('2' == paramType || '3' == paramType) {
        $("div[name = 'ruleTypeName']").hide();
    }

    /*选择参数 = '直辖组'展示 excludeDirectlyGroupFlagDivId*/
    if('4' == paramType){
        $("div[name = 'flagDivName']").hide();
        $("#activeSalerFlagDivId").show();
        $("#excludeDirectlyGroupFlagDivId").show();
    }
    /*选择参数 = '直辖部'展示 includeDirectlyGroupFlagDivId*/
    if('5' == paramType){
        $("div[name = 'flagDivName']").hide();
        $("#includeDirectlyGroupFlagDivId").show();
    }
});


//点击修改展示页面数据
function showUpdateModalData(data){
    $("#id").val(data.id);
    var orgId = data.orgId;
    var paramType = data.paramType;
    var ruleTypeStr = data.ruleType;
    var activeSalerFlag = data.activeSalerFlag;
    var excludeDirectlyGroupFlag = data.excludeDirectlyGroupFlag;
    var includeDirectlyGroupFlag = data.includeDirectlyGroupFlag;
    $("#add_orgId").find("option[value='"+orgId+"']").attr("selected",true);

    $("#add_paramType").find("option[value='"+paramType+"']").attr("selected",true);

    /**反显规则 */
    var obj = $("#ruleType" + paramType);
    obj.show();
    $(obj).siblings("div").hide();
    var ruletypeArr = ruleTypeStr.split(",");
    for(var i=0; i< ruletypeArr.length; i++) {
        $("input[name='ruleType"+ paramType +"'][value='"+ruletypeArr[i]+"']").attr("checked", true);
    }

    debugger;
    /*选择参数 = '直辖组'展示 excludeDirectlyGroupFlagDivId*/
    if('4' == paramType){
        $("div[name = 'flagDivName']").hide();
        $("#activeSalerFlagDivId").show();
        $("#excludeDirectlyGroupFlagDivId").show();
        if(activeSalerFlag == '0' || activeSalerFlag == null) {
            activeSalerFlag = '0';
        }
        $("input[name='activeSalerFlag'][value='"+ activeSalerFlag +"']").attr("checked", true);

        if(excludeDirectlyGroupFlag == '0' || excludeDirectlyGroupFlag == null) {
            excludeDirectlyGroupFlag = '0';
        }
        $("input[name='excludeDirectlyGroupFlag'][value='"+ excludeDirectlyGroupFlag +"']").attr("checked", true);
    }
    /*选择参数 = '直辖部'展示 includeDirectlyGroupFlagDivId*/
    if('5' == paramType){
        $("div[name = 'flagDivName']").hide();
        $("#includeDirectlyGroupFlagDivId").show();
        if(includeDirectlyGroupFlag == '0' || includeDirectlyGroupFlag == null) {
            includeDirectlyGroupFlag = '0';
        }
        $("input[name='includeDirectlyGroupFlag'][value='"+ includeDirectlyGroupFlag +"']").attr("checked", true);
    }

}


