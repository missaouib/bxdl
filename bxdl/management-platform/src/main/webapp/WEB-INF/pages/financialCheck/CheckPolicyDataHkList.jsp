<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/core.jsp" %>
<!DOCTYPE  html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>对账数据上传</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="Cache" content="no-cache">
	<meta http-equiv="Pragma" content="no-cache">
	<meta http-equiv="Cache-Control" content="no-cache">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${path}/css/system/role/role.css">
	<script src="${path}/static/js/jquery-form.js"></script>
	<script src="${path}/js/system/comm.js" charset="UTF-8" type="text/javascript"></script>
	<script src="${path}/static/js/bootstrap.js"></script>
    <script src="${path}/static/js/tableExport.js"></script>
	<script src="${path}/js/jquery-form.js"></script>
	<script src="${path}/js/bootstrap3-typeahead.js" type="text/javascript"></script>
	<script src="${path}/js/financialCheck/checkPolicyDataHK.js"></script>
	<script type="text/javascript">

        function closeDlg() {
            $("#alertFile").modal('hide');
        }
        function alertFile(type) {
            $("#alertFileType").val(type);
            $("#alertFile").modal('show');
        }
        function downloadTemplate() {
            window.open("${path}/upload/files/我司对账数据模板.xls");
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
                url: 'check_policy_data_hk/importPolicyDataHk',
                dataType: 'json',
                success: function (data) {
                    $('#loadingModal').modal('hide');
                    if (data.code == "200") {
                        $("#checkPolicyDataHK-table").bootstrapTable('refresh');
                        // createAddProcessTab('to_insurance_policy_list','')
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
<!--列表 -->
<div class="panel panel-default">
    <div class="panel-body">
		<form class="form-inline hz-form-inline" id="searchForm">
			<input type="hidden" value="1" id="type" name="type">
			<div class="form-group has-feedback">
				<label class="control-label" for="company_org_name">保险公司名称</label>

				<input type="text" class="form-control" id="company_org_name" name="company_org_name" placeholder="保险公司名称" data-provide="typeahead" autocomplete="off" value="" onmouseover="this.title=this.value"></input>
			 <span class="glyphicon glyphicon-search form-control-feedback"></span>
			     <input  type="hidden" class="form-control" id="company_org_id" name="company_org_id" placeholder="保险公司"></input>
			</div>

			<%--<div class="form-group">
				<label class="control-label" for="company_org_id">保险公司</label>
				<select type="text" class="form-control" id="company_org_id" name="company_org_id" placeholder="保险公司"></select>
			</div>--%>
			<div class="form-group has-feedback">
				<label class="control-label" for="product_name">产品名称</label>
				<input type="text" class="form-control" id="product_name" name="product_name" placeholder="产品名称" data-provide="typeahead" autocomplete="off" onmouseover="this.title=this.value"></input>
				<span class="glyphicon glyphicon-search form-control-feedback"></span>
			   <input type="hidden" class="form-control" id="product_id" name="product_id" ></input>
			</div>
			<div class="form-group has-feedback">
				<label class="control-label" for="sales_org_name">组织机构名称</label>
				<input type="text" class="form-control" id="sales_org_name" name="sales_org_name" placeholder="组织机构名称" data-provide="typeahead" autocomplete="off" value="" onmouseover="this.title=this.value"></input>
				<span class="glyphicon glyphicon-search form-control-feedback"></span>
			   <input type="hidden" class="form-control" id="sales_org_id" name="sales_org_id" ></input>
			</div>

			<%--<div class="form-group">
				<label class="control-label" for="sales_org_id">组织机构</label>
				<select type="text" class="form-control" id="sales_org_id" name="sales_org_id" placeholder="组织机构"></select>
			</div>--%>


			<div class="form-group">
				<label class="col-md-2 control-label">对账月份</label>
					<input name="check_month" id="check_month" type='text' class="form-control input-sm Wdate"
						   onfocus="WdatePicker({dateFmt:'yyyy-MM',readOnly:true})"/>

			</div>
			<div class="form-group">
				<label class="col-md-2 control-label">核对状态</label>
					<select id="check_status" name="check_status" class="form-control">
						<option value=""></option>
						<option value="0">未核对</option>
						<option value="1">核对无误</option>
						<option value="2">延期核对</option>
						<option value="3">我司存在</option>
						<option value="4">核对不一致</option>
					</select>

			</div>
			<div class="form-group">
				<label class="col-md-2 control-label">结算状态</label>
					<select id="settle_status" name="settle_status" class="form-control">
						<option value=""></option>
						<option value="0">未结算</option>
						<option value="1">已结算</option>
					</select>

			</div>
			<div class="form-group">
				<button type="button" class="btn btn-info" onclick="checkPolicyDataHK.search()">
					<span class="glyphicon glyphicon-search" aria-hidden="true" >搜索</span>
				</button>
				<button type="button" class="btn btn btn-danger" onclick="cla()">
					<span class="glyphicon glyphicon-remove" aria-hidden="true" >清空</span>
				</button>
			</div>
		</form>
	</div>

</div>

<div style="width: 99%;overflow: auto;">
	<table id="checkPolicyDataHK-table" class="table table-hover table-striped table-condensed table-bordered"></table>
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
						<input type="hidden" id="alertFileType" name="alertFileType" value="">
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

<!--toolbar  -->
<div id="toolbarPolList" class="btn-toolbar" style="overflow:scroll;">
	<%--<shiro:hasPermission name="inspolicyDataHk:add">--%>
		<button type="button" class=" btn btn-primary" onclick="downloadTemplate()">
			<span class="glyphicon glyphicon-book">下载模板</span>
		</button>
		<button class="btn btn-success" type="button" onclick="alertFile(0)">
			<span class="glyphicon glyphicon-import" >数据上传</span>
		</button>
		<button class="btn btn-info" type="button" onclick="alertFile(1)">
			<span class="glyphicon glyphicon-edit" >修改数据上传</span>
		</button>
	<%--</shiro:hasPermission>--%>
</div>
</body>
</html>