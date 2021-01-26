<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/core.jsp" %>
<!DOCTYPE  html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <title>员工佣金审核</title>
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Cache" content="no-cache">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   	<link rel="stylesheet" href="${path}/css/system/role/role.css">
   	<script src="${path}/js/system/comm.js" charset="UTF-8" type="text/javascript"></script>
    <script src="${path}/static/js/bootstrap.js"></script>
    <script src="${path}/js/insurance/salesMonthlyCommission.js"></script>
    <script src="${path}/static/js/jquery-form.js"></script>
    <script type="text/javascript">
	    function commissionExport(ids){
	    	location.href="salesDaysCommission/commissionExport?ids="+ids;
	    	/* $.ajax({
	            url:'salesDaysCommission/commissionExport',
	            type:'post',
	            dataType:'json',
	            data:{ids:ids},
	            success:function(data){
	    			if("200" == data.messageCode){
	    				$('<form method="post" action="${path}/fileUpload/downloadExcel"><input name="fileName" value="'+data.data+'"/></form>').appendTo('body').submit().remove();
	    				$.alert({
	    					title: '提示！',
	    					content: '导出成功',
	    					type: 'blue'
	    				});
	    			}else if(300 == data.messageCode){
	    				$.messager.alert('提示','依据搜索条件没有获取到数据！');
	    			}else if(500 == data.messageCode){
	    				$.messager.alert('提示','系统异常，请刷新后重试！');
	    			}
	            	$("#SalesMonthlyCommission-table").bootstrapTable('refresh');
	            }
	    	}) */			
	    }
	    
	    function importCut(){
   	        $("#openImageDlg").modal('show');
        }
    </script>
</head>
<body>

<div class="panel panel-default">
	<div class="panel-body">
		<form id="SearchConForm" class=" form-inline hz-form-inline">
		  <div class="form-group">
		  		<label class="control-label">组织机构</label>
				<select class="form-control" id="SMC_salesOrgId" name="salesOrgId">
		              <option value="">组织机构</option>
		        </select>
		  </div>

		  <div class="form-group">
		  		<label class="control-label">销售团队</label>
				<select class="form-control" id="SMC_salesTeamId" name="salesTeamId">
		             <option value="">销售团队</option>
		        </select>
	      </div>
		  <div class="form-group">
		  		<label class="control-label">员工工号</label>
		   		 <input type="text" class="form-control" id="SMC_insuranceSalerNo" name="insuranceSalerNo" placeholder="请输入员工工号">
		  </div>
		  <div class="form-group">
		  		<label class="control-label">员工姓名</label>
		   		 <input type="text" class="form-control" id="SMC_insuranceSaler" name="insuranceSaler" placeholder="请输入员工姓名">
		  </div>
		  <div class="form-group">
		  		<label class="control-label">职级序列</label>
				<select class="form-control" id="SMC_rankSequenceId" name="rankSequenceId">
		             <option value="">职级序列</option>
		        </select>
		  </div>
		  <div class="form-group">
		 		 <label class="control-label">员工职级</label>
				<select class="form-control" id="SMC_salesGradeId" name="salesGradeId">
		             <option value="">员工职级</option>
		        </select>
	      </div>
		  <div class="form-group">
		  		<label class="control-label">佣金状态</label>
				<select class="form-control" id="SMC_commissionStatus" name="commissionStatus">
		             <option value="">状态</option>
		             <option value="0">待审核</option>
		             <option value="-1">审核不通过</option>
		             <option value="1">待发放</option>
		             <option value="2">已发放</option>
		        </select>
	      </div>
	      	  <div class="form-group">
	      	  		<label class="control-label">佣金月</label>
			   		 <input type="text" class="form-control" id="SMC_YJY_startTime" name="YJY_startTime" placeholder="开始时间" onFocus="WdatePicker({dateFmt:'yyyy-MM',readOnly:true})">
			   		 <label class="control-label control-label-time">—</label>
			   		 <input type="text" class="form-control" id="SMC_YJY_endTime" name="YJY_endTime" placeholder="结束时间" onFocus="WdatePicker({dateFmt:'yyyy-MM',readOnly:true})">
			  </div>

	      	  <div class="form-group">
	      	  		<label class="control-label">发放时间</label>
			   		 <input type="text" class="form-control" id="SMC_FF_startTime" name="FF_startTime" placeholder="开始时间" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})">
			   		 <label class="control-label control-label-time">—</label>
			   		 <input type="text" class="form-control" id="SMC_FF_endTime" name="FF_endTime" placeholder="结束时间" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})">
			  </div>

			<div class="form-group">
		  		<button type="button" onclick="SalesMonthlyCommission.search()" class="btn btn-info ">
		   			<span class="glyphicon glyphicon-search" aria-hidden="true" >搜索</span>
		   		</button>
		   		<button type="button" onclick="SalesMonthlyCommission.empty()" class="btn btn-danger ">
		   			<span class="glyphicon glyphicon-remove" aria-hidden="true" >清空</span>
		   		</button>
		   	</div>
		</form>
	</div>
</div>

<!--toolbar  -->
<div id="salerCommission_toolbar" class="btn-toolbar">
    <shiro:hasPermission name="salesOrgInfo:exportAll">
	    <button onclick="exportAll()" type="button" class=" btn btn-info">
	      		<span class="glyphicon glyphicon-paste" aria-hidden="true" >导出全部(导入模板)</span>
	    </button>
 	</shiro:hasPermission>
    <shiro:hasPermission name="salesOrgInfo:exportSelect">
	    <button onclick="exportSelect()" type="button" class=" btn btn-info">
	      		<span class="glyphicon glyphicon-paste" aria-hidden="true" >导出选中(导入模板)</span>
	    </button>
 	</shiro:hasPermission>
    <%-- <shiro:hasPermission name="salesOrgInfo:exportSelect"> --%>
	    <button onclick="importCut()" type="button" class=" btn btn-info">
	      		<span class="glyphicon glyphicon-paste" aria-hidden="true" >导入</span>
	    </button>
 	<%-- </shiro:hasPermission>  --%>
	    <button onclick="SalesMonthlyCommission.BatchRelease()" type="button" class=" btn btn-primary">
	      		<span class="glyphicon glyphicon-book" aria-hidden="true" >批量发放</span>
	    </button>
</div>

<!--列表 -->
<div style="width: 99%;overflow: auto;">
	<table id="SalesMonthlyCommission-table" class="table table-hover table-striped table-condensed table-bordered"></table>
</div>
<!--toolbar  -->

<!-- 模态框（Modal） -->
<!-- 添加 -->
<div id="salesMCommissionDlg" class="modal fade" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">佣金明细</h4>
            </div>
            <div class="container">
			<form class="form-horizontal"  method="post">
				<table class="table-bordered" style="text-align:center;">
				<tr style="height:35px;">
					<td colspan="5"><span id="M_insurance_saler"></span>&nbsp;&nbsp;&nbsp;<span id="M_insurance_saler_no"></span></td>
				</tr>
				<tr style="height:35px;">
					<td style="width:113px;">初年度佣金(元)</td>
					<td style="width:113px;">展业津贴(元)</td>
					<td style="width:113px;">增员津贴(元)</td>
					<td style="width:113px;">续年度佣金(元)</td>
					<td style="width:113px;">继续率奖金(元)</td>
				</tr>
				<tr style="height:35px;">
					<td><span id="M_initial_annual_commission"></span></td>
					<td><span id="M_exhibition_allowance"></span></td>
					<td><span id="M_increase_allowance"></span></td>
					<td><span id="M_continuous_allowance"></span></td>
					<td><span id="M_continuation_rate_bonus"></span></td>
				</tr>
				<tr style="height:35px;">
				<td style="width:113px;">年终奖(元)</td>
				<td>长期客服奖(元)</td><td>直辖组管理津贴(元)</td><td>处长育成奖(元)</td><td>直辖部管理津贴(元)</td></tr>
				<tr style="height:35px;">
					<td><span id="M_annual_bonus"></span></td>
					<td><span id="M_service_award"></span></td>
					<td><span id="M_group_run_allowance"></span></td>
					<td><span id="M_director_culture_award"></span></td>
					<td><span id="M_dep_run_allowance"></span></td>					
				</tr>
				<tr style="height:35px;"><td>部育成奖(元)</td><td>总监津贴(元)</td><td>佣金加扣(元)</td><td>冻结税额(元)</td><td>合计(元)</td></tr>
				<tr style="height:35px;">
					<td><span id="M_minister_culture_award"></span></td>
					<td><span id="M_chief_inspector_allowance"></span></td>
					<td><span id="M_cut_commission"></span></td>
					<td><span id="M_freezing_taxes"></span></td>
					<td><span id="M_after_tax_commission"></span></td>
				</tr>
				<tr style="height:35px;text-align:left;color:red;"><td colspan="5" style="padding-left:5px;">佣金加扣原因：<textarea readonly="readonly" id="M_cut_reason"  name="M_cut_reason" class="form-control form-control-static" cols="2" rows="1"></textarea></td></tr>
					<tr style="height:35px;text-align:left;color:red;"><td colspan="5" style="padding-left:5px;">备注：<textarea readonly="readonly" id="M_remark"  name="M_remark" class="form-control form-control-static" cols="2" rows="2"></textarea></td></tr>
				</table>		
            </form>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<!-- 模态框（Modal） -->
<!-- 审核 -->
<div id="commissionStatusDlg" class="modal fade" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">佣金审核</h4>
            </div>
            <div class="container">
			<form class="form-horizontal" id="commissionStatus_Form"  method="post">
			<input type="hidden" id="checkCommissionId" name="checkCommissionId" value="" />
				<input type="hidden" id="afterTaxCommission" name="afterTaxCommission" value="" />
			<div class="form-group">
			<label class="col-md-2 control-label">审核结果：</label>
			<div class="col-md-3 ">
				<input type="radio" id="commission_status1" name="commission_status" value="1" checked="checked">&nbsp;同意&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" id="commission_status2" name="commission_status" value="-1">&nbsp;不同意
			</div>
			</div>

				<div class="form-group">
					<label class="col-md-2 control-label">佣金加扣：</label>
					<div class="col-md-1 ">
						<select class="form-control " style = "width:110px;" id="cut_commission_status" name="cut_commission_status">
							<option value="">请选择</option>
							<option value="0">佣金增加</option>
							<option value="1">佣金扣除</option>
						</select>
					</div>
					<label class="col-md-2 control-label" style="width: 13.66666667%;"> 金额(元)：</label>
					<div class="col-md-1 ">
						<input type="number" style = "width:100px;" step = "0.01" id="cut_commission" name="cut_commission"
							   class="form-control form-control-static ">
					</div>
				</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">佣金加扣原因备注：</label>
			<div class="col-md-3 ">
			<%--<input type="text" id="check_mark"  name="check_mark" class="form-control form-control-static" placeholder="请输入备注">--%>
			<textarea  id="cut_reason"  name="cut_reason" class="form-control form-control-static" cols="2"   rows="1" ></textarea>
			</div>
			</div>

				<div class="form-group">
			<label class="col-md-2 control-label">冻结税额（元）：</label>
			<div class="col-md-3 ">
			<input type="number" step = "0.01" id="freezing_taxes"  name="freezing_taxes" class="form-control form-control-static" >
			</div>
			</div>

			<div class="form-group">
			<label class="col-md-2 control-label">备注：</label>
			<div class="col-md-3 ">
			<%--<input type="text" id="remark"  name="remark" class="form-control form-control-static">--%>
				<textarea  id="remark"  name="remark" class="form-control form-control-static" cols="2" rows="3"></textarea>
			</div>
			</div>

            <div class="modal-footer col-md-6">
            <!--用来清空表单数据-->
            <input type="reset" name="resetButton" style="display: none;" />
                <button type="button" class="btn btn-default" onclick="SalesMonthlyCommission.closeDlg()">关闭</button>
                <button id="saveButton" type="button" onclick="checkStatus()" class="btn btn-primary">保存</button>
            </div>
            </form>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<div id="openImageDlg" class="modal fade"  tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">上传文件</h4>
            </div>
            <div class="container">
	    		 <form id="imageForm"  enctype="multipart/form-data" style="margin-top: 10px;">
	       			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		    		<input type="file" name="file" id="fileName" style="height: 30px;" >
		    		<input type="hidden" id="backto" name="backto" value="" />
					<input style="margin-left: 260px;" type="button" value="上传" onclick="SalesMonthlyCommission.impFile('imageForm')" class="btn btn-info" />  
				</form>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
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