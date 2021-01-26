<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/include/core.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>组织机构管理</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Cache" content="no-cache">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="${path}/css/system/role/role.css">
    <script src="${path}/js/system/comm.js" charset="UTF-8" type="text/javascript"></script>
    <script src="${path}/static/js/bootstrap.js"></script>
    <script type="text/javascript">

        var insComanpyOrg = {
            seItem: null		//选中的条目
        };

        function operateFormatter(value, row, index) {
            var id = row.INSURANCE_COMPANY_ID;
            return [
                '<shiro:hasPermission name="insCompanyOrg:Select">',
                '<button type="button" class="btn btn-info" onclick="openSelectDetailTabById(\'' + id + '\')">',
                '<span class="glyphicon glyphicon-pencil" >查看</span>',
                '</button>',
                '</shiro:hasPermission>',
                '<shiro:hasPermission name="insCompanyOrg:update">',
                '<button type="button" class="btn btn-info" onclick="openDetailTabById(\'' + id + '\')">',
                '<span class="glyphicon glyphicon-pencil" >编辑</span>',
                '</button>',
                '</shiro:hasPermission>',
            ].join('');
        }

        function delDept(row) {
            var cha = $('#insComanpyOrg-table').bootstrapTable('getSelections');
            if (cha.length < 1) {
                $.alert({
                    title: '提示信息！',
                    content: '至少选择一条记录！',
                    type: 'red'
                });
            } else {
                $.confirm({
                    title: '提示信息！',
                    content: '您确定要删除所选记录吗？',
                    type: 'red',
                    text: "确定",
                    buttons: {

                        confirm: {
                            text: "确定",
                            action: function () {
                                var ids = "";
                                for (var i = 0; i < cha.length; i++) {
                                    ids += cha[i].INSURANCE_COMPANY_CODE + ",";
                                }

                                var data = {"ids": ids};
                                $.ajax({
                                    url: "insurance_dept/delete_insurance_org",
                                    data: data,
                                    type: "POST",
                                    dataType: "json",
                                    success: function (obj) {

                                        if (obj.returnCode == 200) {
                                            $.alert({
                                                title: '提示信息！',
                                                content: '删除成功！',
                                                type: 'green'
                                            });
                                            $("#insComanpyOrg-table").bootstrapTable('refresh');
                                        } else if (obj.returnCode == 201) {
                                            //数据有子集
                                            $.alert({
                                                title: '提示信息！',
                                                content: '无法删除：部门' + obj.data + "为选中部门子部门,请先删除该部门以及子部门",
                                                type: 'red'
                                            });
                                        }else if (obj.returnCode == 202) {
                                            //数据有子集
                                            $.alert({
                                                title: '提示信息！',
                                                content: obj.data,
                                                type: 'red'
                                            });
                                        } else {
                                            $.alert({
                                                title: '提示信息！',
                                                content: '删除失败,系统异常',
                                                type: 'red'
                                            });
                                        }
                                    },
                                    error: function (obj) {
                                        $.alert({
                                            title: '提示信息！',
                                            content: '删除失败,系统异常',
                                            type: 'red'
                                        });
                                    }

                                })
                            }
                        },
                        cancel: {
                            text: "取消"
                        }
                    }


                })
            }
        }

        $(function () {
            insComanpyOrg.init();
            commSelectProductOrg("c_insuranceCompanyName");
            commSelectProductOrg("b_insuranceCompanyName");
        });

        function openDetailTabById(id) {

            commCloseTab('insCompanyOrg:list');
            createAddProcessTab('insCompanyOrg:update', id);
        }

        function openSelectDetailTabById(id) {
            createAddProcessTab('insCompanyOrg:Select', id);
        }

        function openDetailTab(permission) {
            if (insComanpyOrg.checkSingleData()) {
                var rowid = insComanpyOrg.seItem.INSURANCE_COMPANY_ID;
                //alert(insComanpyOrg.seItem.INSURANCE_COMPANY_ID);
                createAddProcessTab('insCompanyOrg:update', rowid);
            }
        }

        var insComanpyOrg = function () {
            return {
                init: function () {

                    $('#insComanpyOrg-table').bootstrapTable({
                        url: "insurance_dept/do_insurance_dept",
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
                        toolbar: "#insComanpyOrg_toolbar",
                        showColumns: false, //显示隐藏列
                        uniqueId: "INSURANCE_COMPANY_ID", //每一行的唯一标识，一般为主键列
                        queryParamsType: '',
                        queryParams: insComanpyOrg.queryOrgParams,//传递参数（*）
                        columns: [{
                            checkbox: true
                        }, {
                            field: "INSURANCE_CODE",
                            visible: false
                        }, {
                            field: "INSURANCE_COMPANY_ID",
                            visible: false
                        }, {
                            field: 'SerialNumber',
                            title: '序号',
                            formatter: function (value, row, index) {
                                return index + 1;
                            }
                        }, {
                            field: "insuranceCompany",
                            title: "保险公司名称",
                            align: "center",
                            valign: "middle",
                            sortable: "true"
                        }, {
                            field: "INSURANCE_COMPANY_NAME",
                            title: "组织机构名称",
                            align: "center",
                            valign: "middle",
                            sortable: "true"
                        }, {
                            field: "pinsuranceDept",
                            title: "上级组织机构名称",
                            align: "center",
                            valign: "middle",
                            sortable: "true"
                        }, {
                            field: "ORG_LEVEL",
                            title: "组织机构级别",
                            align: "center",
                            valign: "middle",
                            sortable: "true",
                            formatter:function (value) {
								if (value == 01){
									return "总公司";
								} else if (value == 02){
									return "省份公司";
								} else if (value == 03){
                                    return "地市公司";
                                } else{
									return"-";
								}
							}
                        }, {
                            field: "CREATED_TIME",
                            title: "创建时间",
                            align: "center",
                            valign: "middle",
                            sortable: "true",
                            formatter: function (value) {
                                var cellval = value + "";
                                if (cellval != null) {
                                    var date = new Date(parseInt(cellval.replace("/Date(", "").replace(")/", ""), 10));
                                    var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
                                    var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
                                    var hours = (date.getHours() + 1 < 11) ? '0' + date.getHours() : date.getHours();
                                    var minutes = (date.getMinutes() + 1 < 11) ? '0' + date.getMinutes() : date.getMinutes();
                                    var seconds = (date.getSeconds() + 1 < 11) ? '0' + date.getSeconds() : date.getSeconds();
                                    return date.getFullYear() + "-" + month + "-" + currentDate + ' ' + hours + ':' + minutes + ':' + seconds;
                                }
                            }
                        }, {
                            title: "操作",
                            align: "center",
                            valign: "middle",
                            sortable: "true",
                            formatter: operateFormatter
                        }],

                        formatLoadingMessage: function () {
                            return "请稍等，正在加载中...";
                        },
                        formatNoMatches: function () {
                            return '无符合条件的记录';
                        }
                    });
                },
                search: function () {
                    $("#insComanpyOrg-table").bootstrapTable('destroy');
                    insComanpyOrg.init();
                },
                cla: function () {
                    $("#c_insuranceCompanyName").val('');
                    $("#b_insuranceCompanyName").val('');
                    $("#insuranceCompanyName").val('');
                    $("#orgLevel").val('');
					$("#insComanpyOrg-table").bootstrapTable('refresh');
                },
                queryOrgParams: function (params) {
                    var temp = {
                        pageSize: params.pageSize,  //页面大小
                        pageNo: params.pageNumber, //页码
                        
                        
                        c_insuranceCompanyName: $(".tab-pane.active #c_insuranceCompanyName").val(),
                        b_insuranceCompanyName: $(".tab-pane.active #b_insuranceCompanyName").val(),
                        insuranceCompanyName: $(".tab-pane.active #insuranceCompanyName").val(),
                        orgLevel: $(".tab-pane.active #orgLevel").val(),
                        noOrgLevel: "01",
                    };
                    return temp;
                },
                add: function () {
                    createAddProcessTab('insCompanyOrg:add', '');
                    commCloseTab('insCompanyOrg:list')
                },
                checkSingleData: function () {
                    var selected = $('#insComanpyOrg-table').bootstrapTable('getSelections');
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
                        insComanpyOrg.seItem = selected[0];
                        return true;
                    }
                },
            }
        }();

    </script>

</head>
<body>
<div class="panel panel-default">
    <div class="panel-body">
        <form class="form-inline hz-form-inline" id="searchForm">
            <div class="form-group">
                <label class="control-label" for="c_insuranceCompanyName">保险公司名称</label>
                <select type="text" class="form-control" id="c_insuranceCompanyName" name="c_insuranceCompanyName"></select>
            </div>
            <div class="form-group">
                <label class="control-label" for="b_insuranceCompanyName">上级组织机构名称</label>
                <select type="text" class="form-control" id="b_insuranceCompanyName" name="b_insuranceCompanyName"></select>
            </div>
            <div class="form-group">
                <label class="control-label" for="insuranceCompanyName">组织机构名称</label>
                <input type="text" class="form-control" id="insuranceCompanyName" name="insuranceCompanyName"></input>
            </div>
            <div class="form-group">
                <label class="control-label" for="orgLevel">组织机构级别</label>
                <select class="form-control" id="orgLevel" name="orgLevel">
		             <option value="">组织机构机构级别</option>
		             <option value="02">省份公司</option>
		             <option value="03">地市公司</option>
		        </select>
            </div>

            <div class="form-group">
                <button type="button" class="btn btn-info" onclick="insComanpyOrg.search()">
                    <span class="glyphicon glyphicon-search" aria-hidden="true" >搜索</span>
                </button>
                <button  type="button" class="btn btn-danger" onclick="insComanpyOrg.cla()">
                    <span class="glyphicon glyphicon-remove" aria-hidden="true" >清空</span>
                </button>
            </div>
        </form>
    </div>
</div>
<!--列表 -->
<div style="width: 99%;overflow: auto;">
    <table id="insComanpyOrg-table" class="table table-hover table-striped table-condensed table-bordered"></table>
</div>
<!--toolbar  -->
<div id="insComanpyOrg_toolbar" class="btn-toolbar">
    <shiro:hasPermission name="insCompanyOrg:add">
        <button class="btn btn-info" type="button" onclick="insComanpyOrg.add()">
            <span class="glyphicon glyphicon-plus">新增保险公司组织机构</span>
        </button>
    </shiro:hasPermission>
    <%--<shiro:hasPermission name="insCompanyOrg:update">
    	<button type="button" class=" btn btn-info" onclick="openDetailTab('insCompanyOrg:update')">
    			<span class="glyphicon glyphicon-pencil" >修改</span>
    	</button>
    </shiro:hasPermission>--%>
    <shiro:hasPermission name="insCompanyOrg:delete">
        <button class=" btn btn-danger" type="button" onclick="delDept('insCompanyOrg:delete')">
            <span class="glyphicon glyphicon-remove">删除</span>
        </button>
    </shiro:hasPermission>
    <shiro:hasPermission name="insCompanyOrg:export">
        <button onclick="" type="button" class="btn btn-success">
            <span class="glyphicon glyphicon-paste" aria-hidden="true">导出</span>
        </button>
    </shiro:hasPermission>
    <shiro:hasPermission name="insCompanyOrg:tree">
        <button onclick="createAddProcessTab('insCompanyOrg:tree','')" type="button" class="btn btn-success">
            <span class="glyphicon glyphicon-paste" aria-hidden="true">组织机构树</span>
        </button>
    </shiro:hasPermission>
</div>
</body>
</html>