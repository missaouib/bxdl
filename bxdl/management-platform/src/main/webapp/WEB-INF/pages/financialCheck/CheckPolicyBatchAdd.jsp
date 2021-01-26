<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/include/core.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>新增批次</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Cache" content="no-cache">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script src="${path}/static/js/bootstrap.js"></script>
    <script src="${path}/js/system/comm.js" charset="UTF-8" type="text/javascript"></script>
    <script src="${path}/js/jquery-form.js" charset="UTF-8" type="text/javascript"></script>
    <script src="${path}/js/bootstrap3-typeahead.js" type="text/javascript"></script>
    <script src="${path}/js/financialCheck/addBatch.js"></script>
    <script src="${path}/js/financialCheck/checkImport.js"></script>
    <script src="${path}/js/financialCheck/extractData.js"></script>
    <script src="${path}/js/financialCheck/checkResult.js"></script>
    <script type="text/javascript">
        function closeDlg() {
            $("#alertFile").modal('hide');
        }
        function alertFile() {
            $("#alertFile").modal('show');
        }
        function downloadTemplate() {
            window.open("${path}/upload/files/保司对账数据模板.xls");
        }

        function upload() {
            var multipart = $("#fileName").val();
            if (multipart == "" || multipart == null) {
                alert("请先选择文件!");
                return;
            }
            closeDlg();
            $("#loadingModal").modal('show');
            $("#fileForm").ajaxSubmit({
                type: 'POST',
                url: 'check_policy_data_comp/importPolicyDataComp',
                dataType: 'json',
                success: function (data) {
                    $('#loadingModal').modal('hide');
                    if (data.code == "200") {
                        $("#checkPolicyDataComp-table").bootstrapTable('refresh');
                        alert("文件导入成功")
                    } else if (data.code == "400") {
                        alert("文件导入失败请重新上传")
                    } else if (data.code == "0000") {
                        alert("文件不能为空")
                    } else if (data.code == "0001") {
                        alert("导入失败：原因-" + data.msg)
                    } else if (data.code == "500") {
                        $.alert({
                            title: '导入失败！',
                            content: data.error,
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
<!-- 导航栏 -->
<div id="showOne" style="height: 60px;margin-top: 5px;background-color: #bcdedf;margin-left: 15px;">
    <button id="addBatch_button" style="margin-left: 20px;height: 40px;width: 150px;margin-top: 10px"
            onclick="createBatchButton()">1.生成批次
    </button>
    <button id="checkImport_button"  hidden="hidden" style="margin-left: 25px;height: 40px;width: 150px;"
            onclick="checkImportButton()">2.对账导入
    </button>
    <button id="extractData_button" hidden="hidden"  style="margin-left: 25px;height: 40px;width: 150px;"
            onclick="extractDataButton()">3.本司提取清单
    </button>
    <button id="checkResult_button" hidden="hidden"  style="margin-left: 25px;height: 40px;width: 150px;"
            onclick="checkResultButton()">4.核对结果
    </button>
</div>
<div id="showSecond" hidden="hidden" style="height: 40px;background-color: #d1e2e3;margin-left: 15px;">
		<button id = "checkResultForStatus_1"   style="margin-left: 380px;height: 35px;width: 140px;" onclick="showCheckResultForStatus(1)">4.1.保单结算不一致</button>
		<button id = "checkResultForStatus_2"   style="margin-left: 20px;height: 35px;width: 180px;" onclick="showCheckResultForStatus(2)" >4.2.我司存在,保司不存在</button>
		<button id = "checkResultForStatus_3"  style="margin-left: 20px;height: 35px;width: 180px;" onclick="showCheckResultForStatus(3)" >4.3.保司存在,我司不存在</button>
    	<button id = "checkResultForStatus_4"   style="margin-left: 20px;height: 35px;width: 140px;" onclick="showCheckResultForStatus(4)" >4.4.核对无误</button>
</div>
<div class="container">
    <!-- 新增批次-->
    <div id="batch_div" class="panel panel-default" style="margin-top: 5px">
        <div class="panel-body" style="margin-left: 150px">
            <form class="form-horizontal" id="addCheckPolicyBatchForm" method="post"
                  enctype="application/x-www-form-urlencoded">

                <div class="form-group has-feedback">
                    <label class="col-md-1 control-label" for="companyOrgName">保险公司名称</label>
                    <div class="col-md-4 ">
                        <input type="text" class="form-control" id="companyOrgName"
                               name="companyOrgName" placeholder="保险公司名称" data-provide="typeahead" autocomplete="off"
                               value=""></input>
                        <span class="glyphicon glyphicon-search form-control-feedback"></span>
                        <input type="hidden" id="companyOrgId" name="companyOrgId"></input>
                    </div>
                    <label class="col-md-1 control-label" for="salesOrgName">组织机构名称</label>
                    <div class="col-md-4 ">
                        <input type="text" class="form-control" id="salesOrgName" name="salesOrgName"
                               placeholder="组织机构名称" data-provide="typeahead" autocomplete="off" value=""></input>
                        <span class="glyphicon glyphicon-search form-control-feedback"></span>
                        <input type="hidden" class="form-control" id="salesOrgId" name="salesOrgId"></input>
                    </div>
                </div>
                <div class="form-group" style="margin-top: 25px">
                    <label class="col-md-1 control-label">对账月份</label>
                    <div class="col-md-4 ">
                        <input name="checkMonth" id="checkMonth" type='text'
                               class="form-control input-sm Wdate" style="height: 33px"
                               onfocus="WdatePicker({dateFmt:'yyyy-MM',readOnly:true})"/>
                    </div>
                    <label class="col-md-1 control-label" for="batchName">批次名称</label>
                    <div class="col-md-4 ">
                        <input style="width: 300px" type="text" class="form-control" id="batchName" name="batchName"
                               placeholder="批次名称"></input>
                    </div>
                </div>
                <div class="form-group" style="margin-top: 25px;">
                    <label class="col-md-1 control-label" for="batchNum">批次号</label>
                    <div class="col-md-4 ">
                        <input type="text" class="form-control" id="batchNum" name="batchNum" value="${checkNum}"
                               readonly="readonly"></input>
                    </div>
                </div>
                <input id="checkReustStatus_id" hidden="hidden" value="">
                <input id="cancelDelayCheck_id" hidden="hidden" value="1">
            </form>
        </div>
        <button id="saveCheckPolicyBatch_id" onclick="addCheckBatch()"
                style="background:#22adb3;  height: 30px;margin-left: 400px;width: 80px;color: white; line-height:20px"
                type="button" value="0">生成批次
        </button>
        <button id="returnCheckPolicyBatchList_id" type="button"
                style="background:#22adb3;  height:30px;width: 80px; color: white; line-height:20px"
                onclick="returnCheckPolicyBatchList()">返回列表
        </button>
    </div>

    <%--保险公司上传数据--%>
    <div id="checkPolicyDataComp_div" hidden="hidden">
        <div style="width: 99%;overflow: auto;">
            <table id="checkPolicyDataComp-table"
                   class="table table-hover table-striped table-condensed table-bordered"></table>
        </div>
        <div id="toolbarPolList" class="btn-toolbar" style="overflow:scroll;">
            <%--<shiro:hasPermission name="inspolicyDataHk:add">--%>
            <button type="button" class=" btn btn-primary" onclick="downloadTemplate()">
                <span class="glyphicon glyphicon-book">下载模板</span>
            </button>
            <button class="btn btn-success" type="button" onclick="alertFile()">
                <span class="glyphicon glyphicon-plus">导入</span>
            </button>
            <button class="btn btn-info" type="button" onclick="extractData()">
                <span class="glyphicon glyphicon-zoom-out">提取数据</span>
            </button>
            <%--</shiro:hasPermission>--%>

            <%--<span style="color: black">&nbsp;&nbsp;"总保费:</span><span id="total_premium" STYLE="color: crimson"></span><span style="color: black">万元</span>--%>

        </div>
    </div>


    <%--提取数据--%>
    <div id="extractPolicyDataHK_div" hidden="hidden">
        <div style="width: 99%;overflow: auto;">
            <table id="extractPolicyDataHK-table"
                   class="table table-hover table-striped table-condensed table-bordered"></table>
        </div>
        <button id="checkResult_id" onclick="startCheck()" style="background:#22adb3;  height: 30px;margin-left: 400px;width: 80px;color: white; line-height:20px" value="0">批次确认</button>
    </div>

    <%--核对结果--%>
    <div id="checkResult_div" hidden="hidden">
        <div style="width: 99%;overflow: auto;">
            <table id="checkResult-table"
                   class="table table-hover table-striped table-condensed table-bordered"></table>
        </div>
         <div id="toolbarCheckResult" class="btn-toolbar" style="overflow:scroll;">
            <button class="btn btn-info"  type="button" onclick="exportExtractData()">
                <span class="glyphicon glyphicon-import	">导出excel</span>
            </button>
        </div>
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
                        <input hidden="hidden" id="batchNum_file" name="batchNum_file" value="">
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
                            <button id="zs_saveButton" type="button" onclick="upload()" class="btn btn-primary">保存
                            </button>
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