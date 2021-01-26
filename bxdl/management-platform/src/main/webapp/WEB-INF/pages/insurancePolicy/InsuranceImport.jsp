<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/include/core.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>保单导入</title>
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
        function downloadTemplate() {
            window.open("${path}/upload/files/PolicyExcelTemplate.xls");
        }

        function empty() {
            $("#exampleInputName1").val('');
            $("#exampleInputName2").val('');
            $("#exampleInputName3").val('');
            $("#exampleInputName5").val('');
            $("#exampleInputName6").val('');
            $("#exampleInputName7").val('');
            $("#exampleInputName8").val('');
            $("#exampleInputName9").val('');
            $("#exampleInputName10").val('');
            $("#exampleInputName20").val('');
            $("#exampleInputName21").val('');
            $("#exampleInputName22").val('');
            $("#exampleInputName23").val('');
            $("#exampleInputName24").val('');
            $("#exampleInputName25").val('');

            insImport.search();
        }

        function closeDlg() {
            $("#alertFile").modal('hide');
        }

        function alertFile() {
            $("#alertFile").modal('show');
        }

        function exportAll() {
            $("#search").attr("action", "insurance_policy/export?type=2");
            $("#search").attr("method", "POST");
            $("#search").submit();
        }
        function exportSelect() {
           var a = $('#input-insurance-table').bootstrapTable('getSelections');
            if (a.length < 1) {
                alert("至少选择一条");
                return;
            }
            var ids = [];
            for (var i = 0; i < a.length; i++) {
                ids.push(a[i].POLICY_ID);
            }
            location.href="insurance_policy/export?type=2&ids="+JSON.stringify(ids);
        }

        $(function () {
            insImport.init();
            getSysDictVal("exampleInputName10","STATE");
        });

        var insImport = function () {
            return {
                init: function () {
                    $('#input-insurance-table').bootstrapTable({
                        url: "insurance_policy/insurance_import_page",
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
                        toolbar: "#insurance_toolbar_button_imp",
                        showColumns: false, //显示隐藏列
                        uniqueId: "POLICY_ID", //每一行的唯一标识，一般为主键列
                        queryParamsType: '',
                        queryParams: insImport.queryParams,//传递参数（*）
                        columns: [{
                            checkbox: true
                        }, {
                            field: 'SerialNumber',
                            title: '序号',
                            formatter: function (value, row, index) {
                                return index + 1;
                            }
                        }, {
                            field: "CORRESPONDING",
                            title: "投保单号",
                            align: "center",
                            valign: "middle",
                            sortable: "true"
                        }, {
                            field: "POLICY_ID",
                            title: "保单号",
                            align: "center",
                            valign: "middle",
                            sortable: "true"
                        }, {
                            field: "INSURANCE_COMPANY_NAME",
                            title: "保险公司",
                            align: "center",
                            valign: "middle",
                            sortable: "true"
                        }, {
                            field: "PRODUCT_NAME",
                            title: "产品名称",
                            align: "center",
                            valign: "middle",
                            sortable: "true"
                        }, {
                            field: "PRINCIPAL_DEPUTY_SIGN",
                            title: "主险/附险",
                            align: "center",
                            valign: "middle",
                            sortable: "true",
                            formatter:function (value) {
                                if(value == '0'){
                                    value="主险"
                                }   if(value == 1){
                                    value="附险"
                                }
                            return value;
                            }
                        }, {
                            field: "PREMIUM",
                            title: "保费",
                            align: "center",
                            valign: "middle",
                            sortable: "true"
                        }, {
                            field: "SALES_ORG_NAME",
                            title: "组织机构",
                            align: "center",
                            valign: "middle",
                            sortable: "true"
                        }, {
                            field: "SALES_TEAM_NAME",
                            title: "营销团队",
                            align: "center",
                            valign: "middle",
                            sortable: "true"
                        }, {
                            field: "NAME1",
                            title: "投保人",
                            align: "center",
                            valign: "middle",
                            sortable: "true"
                        }, {
                            field: "NAME2",
                            title: "被保人",
                            align: "center",
                            valign: "middle",
                            sortable: "true"
                        }, {
                            field: "EMPLOYEE_NO",
                            title: "员工工号",
                            align: "center",
                            valign: "middle",
                            sortable: "true"
                        }, {
                            field: "NAME3",
                            title: "员工姓名",
                            align: "center",
                            valign: "middle",
                            sortable: "true"
                        }, {
                            field: "INSURE",
                            title: "投保日期",
                            align: "center",
                            valign: "middle",
                            sortable: "true",
                            formatter:function (value) {
                                var cellval =value+"";
                                if (cellval != null) {
                                    var date = new Date(parseInt(cellval.replace("/Date(", "").replace(")/", ""), 10));
                                    var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
                                    var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
                                    return date.getFullYear() + "-" + month + "-" + currentDate;
                                }
                            }
                        }, {
                            field: "TAKE_EFFECT_DATA",
                            title: "生效日期",
                            align: "center",
                            valign: "middle",
                            sortable: "true",
                            formatter:function (value) {
                                var cellval =value+"";
                                if (cellval != null) {
                                    var date = new Date(parseInt(cellval.replace("/Date(", "").replace(")/", ""), 10));
                                    var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
                                    var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
                                    return date.getFullYear() + "-" + month + "-" + currentDate;
                                }
                            }
                        }, {
                            field: "UNDERWRITING_DATA",
                            title: "承保日期",
                            align: "center",
                            valign: "middle",
                            sortable: "true",
                            formatter:function (value) {
                                var cellval =value+"";
                                if (cellval != null) {
                                    var date = new Date(parseInt(cellval.replace("/Date(", "").replace(")/", ""), 10));
                                    var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
                                    var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
                                    return date.getFullYear() + "-" + month + "-" + currentDate;
                                }
                            }
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
                        pageNo: params.pageNumber, //页码
                        type: "2", // 查询保单
                        source: "2", // 查询导入
                        exampleInputName1:$("#exampleInputName1").val(),
                        exampleInputName2:$("#exampleInputName2").val(),
                        exampleInputName3:$("#exampleInputName3").val(),
                        exampleInputName5:$("#exampleInputName5").val(),
                        exampleInputName6:$("#exampleInputName6").val(),
                        exampleInputName7:$("#exampleInputName7").val(),
                        exampleInputName8:$("#exampleInputName8").val(),
                        exampleInputName9:$("#exampleInputName9").val(),
                        exampleInputName10:$("#exampleInputName10").val(),
                        exampleInputName20:$("#exampleInputName20").val(),
                        exampleInputName21:$("#exampleInputName21").val(),
                        exampleInputName22:$("#exampleInputName22").val(),
                        exampleInputName23:$("#exampleInputName23").val(),
                        exampleInputName24:$("#exampleInputName24").val(),
                        exampleInputName25:$("#exampleInputName25").val(),
                    };
                    return temp;
                },
                search:function(){
                    $("#input-insurance-table").bootstrapTable('destroy');
                    insImport.init();
                },

            }
        }();

        function upload() {
            var multipart = $("#fileName").val();
            if (multipart == "" || multipart == null) {
                alert("请先选择文件!");
                return;
            }
            closeDlg();
            $("#loadingModal").modal('show');
            //$('#loadingModal').modal('hide');
            $("#fileForm").ajaxSubmit({
                type: 'POST',
                url: 'insurance_policy/importInsurance',
                dataType: 'json',
                success: function (data) {
                    $('#loadingModal').modal('hide');
                    if (data.code == "200") {
                        createAddProcessTab('insuranceImport:list','')
                        alert("文件导入成功")
                    } else if(data.code == "400"){
                        alert("文件导入失败请重新上传")
                    }else if (data.code == "0000"){
                        alert("文件不能为空")
                    }else if (data.code == "0001"){
                        alert("导入失败：原因-"+data.msg)
                    }else if(data.code == "500"){
                       $.alert({
                           title: '导入失败！',
                           content:data.error,
                           type: 'red'

                       })
                    }
                },
                error: function (data) {
                    $('#loadingModal').modal('hide');
                    alert("系统异常")
                }
            })
        }
    </script>
</head>
<body>
<!--列表 -->
<div class="panel panel-default">
    <div class="panel-body">
        <form class="form-inline hz-form-inline" id="search" enctype="application/x-www-form-urlencoded">
            <div class="form-group" >
                <label  class="control-label" for="exampleInputName1">保单号</label>
                <input type="text" class="form-control" name="exampleInputName1" id="exampleInputName1"
                       placeholder="保单号">
            </div>
            <div class="form-group" >
                <label  class="control-label" for="exampleInputName2">保险公司</label>
                <input type="text" class="form-control" name="exampleInputName2" id="exampleInputName2"
                       placeholder="保险公司">
            </div>
            <div class="form-group" >
                <label  class="control-label" for="exampleInputName3">产品名称</label>
                <input type="text" class="form-control" name="exampleInputName3" id="exampleInputName3"
                       placeholder="产品名称">
            </div>
            <div class="form-group" >
                <label  class="control-label" for="exampleInputName5">组织机构</label>
                <input type="text" class="form-control" name="exampleInputName5" id="exampleInputName5"
                       placeholder="组织机构">
            </div>
            <div class="form-group" >
                <label  class="control-label" for="exampleInputName6">营销团队</label>
                <input type="text" class="form-control" name="exampleInputName6" id="exampleInputName6"
                       placeholder="营销团队">
            </div>
            <div class="form-group" >
                <label  class="control-label" for="exampleInputName7">投保人</label>
                <input type="text" class="form-control" name="exampleInputName7" id="exampleInputName7"
                       placeholder="投保人">
            </div>
            <div class="form-group" >
                <label  class="control-label" for="exampleInputName8">被保人</label>
                <input type="text" class="form-control" name="exampleInputName8" id="exampleInputName8"
                       placeholder="被保人">
            </div>
            <div class="form-group" >
                <label  class="control-label" for="exampleInputName9">员工工号</label>
                <input type="text" class="form-control" name="exampleInputName9" id="exampleInputName9"
                       placeholder="员工工号">
            </div>
            <div class="form-group" >
                <label  class="control-label"  class="control-label" for="exampleInputName10">状态</label>
                <select type="text" class="form-control" name="exampleInputName10" id="exampleInputName10"
                        placeholder="状态">
                </select>
            </div>
            <div class="form-group" >
                <label  class="control-label" for="exampleInputName20">投保日期</label>
                <input type="date"class="form-control" name="exampleInputName20" id="exampleInputName20"
                       placeholder="投保日期">
                <label class="control-label control-label-time">至</label>
                <input type="date" class="form-control" name="exampleInputName21" id="exampleInputName21"
                       placeholder="投保日期">
            </div>
            <div class="form-group" >
                <label  class="control-label" for="exampleInputName22">生效日期</label>
                <input type="date" class="form-control" name="exampleInputName22" id="exampleInputName22"
                       placeholder="生效日期">
                <label class="control-label control-label-time">至</label>
                <input type="date" class="form-control" name="exampleInputName23" id="exampleInputName23"
                       placeholder="生效日期">
            </div>
            <div class="form-group" >
                <label  class="control-label" for="exampleInputName24">承保日期</label>
                <input type="date" class="form-control" name="exampleInputName24" id="exampleInputName24"
                       placeholder="承保日期">
                <label class="control-label control-label-time">至</label>
                <input type="date" class="form-control" name="exampleInputName25" id="exampleInputName25"
                       placeholder="承保日期">
            </div>
            <div class="form-group" >
                <button type="button" class="btn btn-info" onclick="insImport.search()">
                    <span class="glyphicon glyphicon-search" aria-hidden="true" >搜索</span>
                </button>
                <button type="button" class="btn btn-danger" onclick="empty()">
                    <span class="glyphicon glyphicon-remove" aria-hidden="true" >清空</span>
                </button>
            </div>
        </form>
    </div>
</div>
<div id="insurance_toolbar_button_imp" class="btn-toolbar">
    <shiro:hasPermission name="insuranceTemplateExcel:download">
        <button type="button" class=" btn btn-info" onclick="downloadTemplate()">
            <span class="glyphicon glyphicon-pencil">导入模板</span>
        </button>
    </shiro:hasPermission>

    <shiro:hasPermission name="insurance:input">
        <button type="button" class=" btn btn-info" onclick="alertFile()">
            <span class="glyphicon glyphicon-pencil">导入</span>
        </button>
    </shiro:hasPermission>
    <shiro:hasPermission name="insurance:export">
		<button type="button" class="export-excel btn btn-info" onclick="exportAll()">
			<span class="glyphicon glyphicon-export" >导出全部</span>
		</button>
		<button type="button" class=" btn btn-info" onclick="exportSelect()">
			<span class="glyphicon glyphicon-export" >导出选中</span>
		</button>
	</shiro:hasPermission>
</div>

<!--列表 -->
<div style="width: 99%;overflow: auto;">
    <table id="input-insurance-table" class="table table-hover table-striped table-condensed table-bordered">

    </table>
</div>

<!-- 导入文件div -->
<div id="alertFile" class="modal fade" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false"
     aria-labelledby="zsadd_myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="zsadd_myModalLabel">上传文件</h4>
            </div>
            <div class="container">
                <form class="form-horizontal" id="fileForm" enctype="multipart/form-data">
                    <div class="form-group">
                        <label class="col-md-2 control-label">选择文件</label>
                        <div class="col-md-3 ">
                            <input type="file" name="file" id="fileName"/>
                        </div>
                    </div>
                    <div class="modal-footer col-md-6">
                        <!--用来清空表单数据-->
                        <input type="reset" name="reset" style="display: none;"/>
                        <button type="button" class="btn btn-default" onclick="closeDlg()">关闭</button>
                        <button id="zs_saveButton" type="button" onclick="upload()" class="btn btn-primary">保存</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="loadingModal">
    <div style="width: 200px;height:20px; z-index: 20000; position: absolute; text-align: center; left: 50%; top: 50%;margin-left:-100px;margin-top:-10px">
        <div class="progress progress-striped active" style="margin-bottom: 0;">
            <div class="progress-bar" style="width: 100%;"></div>
        </div>
        <h5 style="color:black"><strong>正在缓冲文件...预计1-5秒，请稍等！</strong></h5>
    </div>
</div>
</body>
</html>