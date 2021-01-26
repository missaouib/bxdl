<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/include/core.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>考核设置</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Cache" content="no-cache">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="${path}/css/system/role/role.css">
    <script src="${path}/js/system/comm.js" charset="UTF-8" type="text/javascript"></script>
    <script src="${path}/static/js/bootstrap.js"></script>
    <script src="${path}/static/js/jquery-form.js"></script>
    <script type="text/javascript">

        var a = {
            seItem: null		//选中的条目
        };

        $(function () {
            a.init();
        });

        var a = function () {
            return {
                init: function () {
                    $('#sales-assess-table').bootstrapTable({
                        url: "insuranceSalesInfo/salesAssessPage/" + ${insuranceSalesId},
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
                        toolbar: "",
                        showColumns: false, //显示隐藏列
                        uniqueId: "id", //每一行的唯一标识，一般为主键列
                        queryParamsType: '',
                        queryParams: a.queryParams,//传递参数（*）
                        columns: [{
                            checkbox: true
                        }, {
                            field: 'SerialNumber',
                            title: '序号',
                            formatter: function (value, row, index) {
                                return index + 1;
                            }
                        }, {
                            field: "salesGradeName",
                            title: "职级",
                            align: "center",
                            valign: "middle",
                            sortable: "true"
                        }, {
                            field: "condition1",
                            title: "新人岗前培训是否通过",
                            align: "center",
                            valign: "middle",
                            sortable: "true",
                            formatter: function (data, type, row) {
                                if (1 == data) {
                                    return "是";
                                } else if (0 == data) {
                                    return "否";
                                }
                            }
                        }, {
                            field: "condition2",
                            title: "日常会议及培训出勤率是否不低于70%",
                            align: "center",
                            valign: "middle",
                            sortable: "true",
                            formatter: function (data, type, row) {
                                if (1 == data) {
                                    return "是";
                                } else if (0 == data) {
                                    return "否";
                                }
                            }
                        }, {
                            field: "condition3",
                            title: "晋级考核是否通过",
                            align: "center",
                            valign: "middle",
                            sortable: "true",
                            formatter: function (data, type, row) {
                                if (1 == data) {
                                    return "是";
                                } else if (0 == data) {
                                    return "否";
                                }
                            }
                        }, {
                            field: "condition4",
                            title: "处长考核是否通过",
                            align: "center",
                            valign: "middle",
                            sortable: "true",
                            formatter: function (data, type, row) {
                                if (1 == data) {
                                    return "是";
                                } else if (0 == data) {
                                    return "否";
                                }
                            }
                        }, {
                            field: "condition5",
                            title: "是否认同公司文化",
                            align: "center",
                            valign: "middle",
                            sortable: "true",
                            formatter: function (data, type, row) {
                                if (1 == data) {
                                    return "是";
                                } else if (0 == data) {
                                    return "否";
                                }
                            }
                        }, {
                            field: "condition6",
                            title: "总经理面谈是否通过",
                            align: "center",
                            valign: "middle",
                            sortable: "true",
                            formatter: function (data, type, row) {
                                if (1 == data) {
                                    return "是";
                                } else if (0 == data) {
                                    return "否";
                                }
                            }
                        }, {
                            field: "condition8",
                            title: "代理制保险营销员品质考核是否通过",
                            align: "center",
                            valign: "middle",
                            sortable: "true",
                            formatter: function (data, type, row) {
                                if (1 == data) {
                                    return "是";
                                } else if (0 == data) {
                                    return "否";
                                }
                            }
                        }, {
                            field: "condition7",
                            title: "部长考核是否通过",
                            align: "center",
                            valign: "middle",
                            sortable: "true",
                            formatter: function (data, type, row) {
                                if (1 == data) {
                                    return "是";
                                } else if (0 == data) {
                                    return "否";
                                }
                            }
                        }, {
                            field: "updatedTime",
                            title: "考核时间",
                            align: "center",
                            valign: "middle",
                            sortable: "true",
                            formatter:resultFormatter,
                            editrules:{required:true}
                        }],
                        // bootstrap-table-treetreegrid.js 插件配置 -- end
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
                        pageNo: params.pageNumber //页码
                    };
                    return temp;
                },
                search: function () {
                    $("#sales-assess-table").bootstrapTable('refresh');
                },

            }
        }();

        function getzf(num){
            if(parseInt(num) < 10){
                num = '0'+num;
            }
            return num;
        }

        function resultFormatter(cellvalue, timestamp, rowObject){
            var oDate = new Date(cellvalue),
                oYear = oDate.getFullYear(),
                oMonth = oDate.getMonth()+1,
                oDay = oDate.getDate(),
                oHour = oDate.getHours(),
                oMin = oDate.getMinutes(),
                oSen = oDate.getSeconds(),
                oTime = oYear +'-'+ getzf(oMonth) +'-'+ getzf(oDay) +' '+ getzf(oHour) +':'+ getzf(oMin) +':'+getzf(oSen);//最后拼接时间
            return oTime;
        }


        function openDetailTab(permission) {
            if (checkSingleData()) {
                var salesAssessId = a.seItem.id;
                console.info(salesAssessId);
                createAddProcessTab('salesAssess:update', $("#insuranceSalesId").val() + "&salesAssessId=" + salesAssessId);
            }
        }

        function checkSingleData() {
            var selected = $('#sales-assess-table').bootstrapTable('getSelections');
            if (selected.length == 0) {
                $.alert({
                    title: '提示信息！',
                    content: '至少选择一条记录！',
                    type: 'red'
                });
                return false;
            } else if (selected.length > 1) {
                $.alert({
                    title: '提示信息！',
                    content: '该操作只能选择一条记录!',
                    type: 'blue'
                });
            } else {
                a.seItem = selected[0];
                return true;
            }
        }

    </script>
</head>
<body>

<!--toolbar  -->
<div id="insuranceSalesInfo_toolbar" class="btn-toolbar">
    <shiro:hasPermission name="salesAssess:add">
        <button class="btn btn-info" type="button"
                onclick="createAddProcessTab('salesAssess:add', ${insuranceSalesId})">
            <span class="glyphicon glyphicon-plus">添加</span>
        </button>
    </shiro:hasPermission>
    <shiro:hasPermission name="salesAssess:update">
        <button type="button" class=" btn btn-info" onclick="openDetailTab('salesAssess:update')">
            <span class="glyphicon glyphicon-pencil">修改</span>
        </button>
    </shiro:hasPermission>
</div>

<!--列表 -->
<div style="width: 99%;overflow: auto;">
    <input type="hidden" value="${insuranceSalesId}" id="insuranceSalesId">
    <table id="sales-assess-table" class="table table-hover table-striped table-condensed table-bordered"></table>
</div>
</body>
</html>