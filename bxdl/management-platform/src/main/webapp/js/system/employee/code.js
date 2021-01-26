/**
 * 数据字典管理
 */

//系统管理--数据字典管理的单例对象
var Code = {
    seItem: null		//选中的条目
};

$(function () {
    Code.init();
});
//表格数据展示
var Code = function () {
    return {
        init: function () {
            $('#code-table').bootstrapTable({
                url: "employeeCodeRule/page",
                method: "get",
                dataType: "json",
                contentType: "application/x-www-form-urlencoded",
                striped: true,//隔行变色
                cache: false,  //是否使用缓存
                pagination: false, //分页
                sortable: false, //是否启用排序
                singleSelect: false,
                search: false, //显示搜索框
                buttonsAlign: "right", //按钮对齐方式
                showRefresh: false,//是否显示刷新按钮
                sidePagination: "server", //服务端处理分页
                pageSize: 100, //默认每页条数
                pageNumber: 1, //默认分页
                pageList: [5, 10, 20, 50],//分页数
                toolbar: "#toolbar",
                showColumns: false, //显示隐藏列
                uniqueId: "id", //每一行的唯一标识，一般为主键列
                queryParamsType: '',
                queryParams: Code.queryParams,//传递参数（*）
                columns: [{
                    checkbox: true
                }, {
                    field: "mapName",
                    title: "名称",
                    align: "center",
                    valign: "middle",
                    sortable: "true"
                },{
                    field: "mapId",
                    title: "关联id",
                    align: "center",
                    valign: "middle",
                    sortable: "true",
                    visible: false
                },{
                    field: "code",
                    title: "序号",
                    align: "center",
                    valign: "middle",
                    sortable: "true",
                    formatter: function(data, type, row) {
                        return "<div style=\"text-align:center; vertical-align:middel;\" ><input id='"+type.mapId+"' value='"+data+"'></div>"
                    }
                },{
                    field: 'type',
                    visible: false   //这一列隐藏
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
                pageNumber: params.pageNumber, //页码
                type: 1,
            };
            return temp;
        },

        /**
         * 检查是否选中单条记录
         */
        checkSingleData:function () {
            var selected = $('#code-table').bootstrapTable('getSelections');
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
                Code.seItem = selected[0];
                return true;
            }
        },

        //按省份修改序号
        updateDic:function() {
            if (this.checkSingleData()) {
                var id = Code.seItem.id;
                var mapId = Code.seItem.mapId;
                var code = $("#"+ mapId).val();
                $.ajax({
                    url: 'employeeCodeRule',
                    type: 'put',
                    dataType: 'json',
                    data: {
                        id: id,
                        type: 1,
                        mapId: mapId,
                        code: code,
                    },
                    success: function (data) {
                        if ("200" == data.messageCode) {
                            $.alert({
                                title: '提示信息！',
                                content: '修改成功!',
                                type: 'blue'
                            });

                        } else if ("200001" == data.messageCode) {
                            $.alert({
                                title: '提示信息！',
                                content: '该序号或省份已存在',
                                type: 'red'
                            });
                            return;
                        } else {
                            $.alert({
                                title: '提示信息！',
                                content: '修改失败！',
                                type: 'red'
                            });
                        }

                        $("#code-table").bootstrapTable('refresh');
                    },
                    error: function () {
                        $.alert({
                            title: '提示信息！',
                            content: '修改失败！',
                            type: 'red'
                        });
                    }
                });
            }
        }
    }
}();




