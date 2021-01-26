/**
 * 数据字典管理
 */

//系统管理--数据字典管理的单例对象
var Dict = {
    seItem: null		//选中的条目
};

$(function () {
    Dict.formValidator();
    Dict.init();
});
//表格数据展示
var Dict = function () {
    return {
        init: function () {
            $('#dict-table').bootstrapTable({
                url: "systemDict/page",
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
                toolbar: "#toolbar",
                showColumns: false, //显示隐藏列
                uniqueId: "dict_id", //每一行的唯一标识，一般为主键列
                queryParamsType: '',
                queryParams: Dict.queryDictParams,//传递参数（*）
                columns: [{
                    checkbox: true
                }, {
                    field: "dict_type",
                    title: "字典类别",
                    align: "center",
                    valign: "middle",
                    sortable: "true"
                }, {
                    field: "dict_type_name",
                    title: "字典类别名称",
                    align: "center",
                    valign: "middle",
                    sortable: "true"
                }, {
                    field: "dict_name",
                    title: "字典名称",
                    align: "center",
                    valign: "middle",
                    sortable: "true"
                }, {
                    field: "dict_code",
                    title: "字典代码",
                    align: "center",
                    valign: "middle",
                    sortable: "true"
                }, {
                    field: "remark",
                    title: "描述",
                    align: "center",
                    valign: "middle",
                    sortable: "true"
                }, {
                    field: "status",
                    title: "状态",
                    align: "center",
                    valign: "middle",
                    sortable: "true"
                }],
                formatLoadingMessage: function () {
                    return "请稍等，正在加载中...";
                },
                formatNoMatches: function () {
                    return '无符合条件的记录';
                }
            });
        },
        queryDictParams: function (params) {
            var temp = {
                pageSize: params.pageSize,  //页面大小
                pageNumber: params.pageNumber, //页码
                dictTypeName: $("#search_dict_type_name").val(),
                dictName: $("#search_dict_name").val(),
            };
            return temp;
        },

        //搜索
        searchDict: function () {
            $("#dict-table").bootstrapTable('destroy');
            Dict.init();
        },
        //清空
        empty: function () {
            $("#search_dict_type_name").val('');
            $("#search_dict_name").val('');
            $("#dict-table").bootstrapTable('refresh');
        },


        //添加，打开模态框
        openDlg: function () {
            $("#dictAddDlg").modal('show');
        },

        //添加字典
        saveDict:function(){
            document.getElementById("saveButton").setAttribute("disabled", true);
            if($("#addDictForm").data('bootstrapValidator').validate().isValid()){
                $.ajax({
                    url:'systemDict',
                    type:'post',
                    dataType:'json',
                    data:$("#addDictForm").serialize(),
                    success:function(data){
                        if("200" == data.messageCode){
                            $.alert({
                                title: '提示信息！',
                                content: '添加成功!',
                                type: 'blue'
                            });

                        }else{
                            $.alert({
                                title: '提示信息！',
                                content: '添加失败！',
                                type: 'red'
                            });
                        }

                        $("#dict-table").bootstrapTable('refresh');
                        Dict.closeDlg();
                    },
                    error:function(){
                        $.alert({
                            title: '提示信息！',
                            content: '请求失败！',
                            type: 'red'
                        });
                    }
                });
            }
            document.getElementById("saveButton").removeAttribute("disabled");
        },

        /**
         * 检查是否选中单条记录
         */
        checkSingleData:function () {
            var selected = $('#dict-table').bootstrapTable('getSelections');
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
                Dict.seItem = selected[0];
                return true;
            }
        },

        //修改前，打开模态框
        openUpdateModal:function(){
            if (this.checkSingleData()) {
                var dictId = Dict.seItem.dict_id;
                $.ajax({
                    url:'systemDict',
                    dataType:'json',
                    type:'get',
                    data:{
                        dictId:dictId
                    },
                    success:function(data){
                        $("#update_dict_id").val(data.systemDict.dictId);
                        $("#update_dict_type").val(data.systemDict.dictType);
                        $("#update_dict_type_name").val(data.systemDict.dictTypeName);
                        $("#update_dict_name").val(data.systemDict.dictName);
                        $("#update_dict_code").val(data.systemDict.dictCode);
                        $("#update_remark").val(data.systemDict.remark);
                        $("#update_order_id").val(data.systemDict.orderId);
                        $("#update_parent_id").val(data.systemDict.parentId);
                        $("#update_dict_tree_name").val(data.parent);
                        $("#dictUpdateDlg").modal('show');
                    },
                    error:function(){
                        $.alert({
                            title: '提示信息！',
                            content: '请求失败！',
                            type: 'red'
                        });
                    }
                });
            }

        },

        //修改字典
        updateDict:function(){
            if($("#updateDictForm").data("bootstrapValidator").validate().isValid()){
                $.ajax({
                    url:'systemDict',
                    dataType:'json',
                    type:'put',
                    data:$("#updateDictForm").serialize(),
                    success:function(data){
                        if("200" == data.messageCode){
                            $.alert({
                                title: '提示信息！',
                                content: '修改成功!',
                                type: 'blue'
                            });

                        }else{
                            $.alert({
                                title: '提示信息！',
                                content: '修改失败！',
                                type: 'red'
                            });
                        }

                        $("#dict-table").bootstrapTable('refresh');
                        Dict.closeDlg();
                    },
                    error:function(){
                        $.alert({
                            title: '提示信息！',
                            content: '修改失败！',
                            type: 'red'
                        });
                    }
                });
            }
        },

        //启用字典
        enable:function(){
            if(this.checkSingleData()){
                var dictId = Dict.seItem.dict_id;
                $.ajax({
                    url:'systemDict/enable',
                    dataType:'json',
                    type:'post',
                    data:{dictId:dictId},
                    success:function(data){
                        if("200" == data.messageCode){
                            $.alert({
                                title: '提示信息！',
                                content: '启用成功!',
                                type: 'blue'
                            });

                        }else{
                            $.alert({
                                title: '提示信息！',
                                content: '启用失败！',
                                type: 'red'
                            });
                        }

                        $("#dict-table").bootstrapTable('refresh');
                        Dict.closeDlg();
                    },
                    error:function(){
                        $.alert({
                            title: '提示信息！',
                            content: '启用失败！',
                            type: 'red'
                        });
                    }
                });
            }
        },

        //禁用字典
        disable:function(){
            if(this.checkSingleData()){
                var dictId = Dict.seItem.dict_id;
                $.ajax({
                    url:'systemDict/disable',
                    dataType:'json',
                    type:'post',
                    data:{dictId:dictId},
                    success:function(data){
                        if("200" == data.messageCode){
                            $.alert({
                                title: '提示信息！',
                                content: '禁用成功!',
                                type: 'blue'
                            });

                        }else{
                            $.alert({
                                title: '提示信息！',
                                content: '禁用失败！',
                                type: 'red'
                            });
                        }

                        $("#dict-table").bootstrapTable('refresh');
                        Dict.closeDlg();
                    },
                    error:function(){
                        $.alert({
                            title: '提示信息！',
                            content: '禁用失败！',
                            type: 'red'
                        });
                    }
                });
            }
        },

        formValidator:function () {
            $("#addDictForm").bootstrapValidator({
                fields:{
                    dictTypeName:{
                        validators:{
                            notEmpty:{
                                message:"字典类别名称不能为空"
                            },
                            stringLength:{
                                max:30,
                                message:"字符长度不能超过30个字符"
                            }
                        }
                    },
                    dictName:{
                        validators:{
                            notEmpty:{
                                message:"字典名称不能为空"
                            },
                            stringLength:{
                                max:20,
                                message:"字符长度不能超过30个字符"
                            }
                        }
                    }
                }
            });

            $("#updateDictForm").bootstrapValidator({
                fields:{
                    dictTypeName:{
                        validators:{
                            notEmpty:{
                                message:"字典类别名称不能为空"
                            },
                            stringLength:{
                                max:30,
                                message:"字符长度不能超过30个字符"
                            }
                        }
                    },
                    dictName:{
                        validators:{
                            notEmpty:{
                                message:"字典名称不能为空"
                            },
                            stringLength:{
                                max:20,
                                message:"字符长度不能超过30个字符"
                            }
                        }
                    }
                }
            });
        },

        //关闭模态框
        closeDlg: function () {
            $("#dictAddDlg").modal('hide');
            $("#dictUpdateDlg").modal('hide');
            $('#addDictForm').data('bootstrapValidator', null);
            $('#updateDictForm').data('bootstrapValidator', null);
            document.getElementById("saveButton").removeAttribute("disabled");
            Dict.formValidator();
        }
    }
}();

$(document).ready(function () {
    $("#dict_tree_name").click(function () {
        $("#dictTreeView").show();
        $.ajax({
            url: 'systemDict/tree',
            dataType: 'json',
            type: 'get',
            data: {rid: 1},
            success: function (data) {
                $('#dictTreeView').treeview({
                    color: "#428bca",
                    expandIcon: 'glyphicon glyphicon-chevron-right',
                    collapseIcon: 'glyphicon glyphicon-chevron-down',
                    nodeIcon: 'glyphicon glyphicon-bookmark',
                    data: data,
                    onNodeSelected: function (event, node) {
                        $("#dict_tree_name").val(node.text);
                        $("#parent_id").val(node.id);
                        $("#dictTreeView").hide();
                    },
                });
            }
        });
    });

    $("#update_dict_tree_name").click(function () {
        $("#dictUpdateTreeView").show();
        $.ajax({
            url: 'systemDict/tree',
            dataType: 'json',
            type: 'get',
            data: {rid: 1},
            success: function (data) {
                $('#dictUpdateTreeView').treeview({
                    color: "#428bca",
                    expandIcon: 'glyphicon glyphicon-chevron-right',
                    collapseIcon: 'glyphicon glyphicon-chevron-down',
                    nodeIcon: 'glyphicon glyphicon-bookmark',
                    data: data,
                    onNodeSelected: function (event, node) {
                        $("#update_dict_tree_name").val(node.text);
                        $("#update_parent_id").val(node.id);
                        $("#dictUpdateTreeView").hide();
                    },
                });
            }
        });
    });
});
