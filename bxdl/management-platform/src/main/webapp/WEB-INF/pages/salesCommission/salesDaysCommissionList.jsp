<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/core.jsp" %>
<!DOCTYPE  html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <title>员工佣金列表</title>
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Cache" content="no-cache">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   	<link rel="stylesheet" href="${path}/css/system/role/role.css">
   	<script src="${path}/js/system/comm.js" charset="UTF-8" type="text/javascript"></script>
    <script src="${path}/static/js/bootstrap.js"></script>
    <script src="${path}/js/insurance/salesCommission.js"></script>
</head>
<body>

<div class="panel panel-default">
	<div class="panel-body">
		<form id="SearchConForm" class=" form-inline hz-form-inline">
		  	<div class="form-group">
		  		<label class="control-label">组织机构</label>
				<select class="form-control" id="SC_salesOrgId" name="salesOrgId">
		              <option value="">组织机构</option>
		        </select>
		  	</div>

		  <div class="form-group">
		  		<label class="control-label">销售团队</label>
				<select class="form-control" id="SC_salesTeamId" name="salesTeamId">
		             <option value="">销售团队</option>
		        </select>
	      </div>

		  <div class="form-group">
		  		<label class="control-label">员工工号</label>
		   		 <input type="text" class="form-control" id="SC_insuranceSalerNo" name="insuranceSalerNo" placeholder="请输入员工工号">
		  </div>

		  <div class="form-group">
		  		<label class="control-label">员工姓名</label>
		   		 <input type="text" class="form-control" id="SC_insuranceSaler" name="insuranceSaler" placeholder="请输入员工姓名">
		  </div>

		  <div class="form-group">
		  		<label class="control-label">职级序列</label>
				<select class="form-control" id="SC_rankSequenceId" name="rankSequenceId">
		             <option value="">职级序列</option>
		        </select>
		  </div>

		  <div class="form-group">
		  		<label class="control-label">员工职级</label>
				<select class="form-control" id="SC_salesGradeId" name="salesGradeId">
		             <option value="">员工职级</option>
		        </select>
	      </div>

		<%--  <div class="form-group">
		  		<label class="control-label">计算时间</label>
		   		<input type="text" class="form-control" id="SC_startTime" name="startTime" placeholder="开始时间" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})">
				<label class="control-label control-label-time">—</label>
		   		<input type="text" class="form-control" id="SC_endTime" name="endTime" placeholder="结束时间" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})">
		  </div>--%>

		  	<div class="form-group">
		  		<button type="button" onclick="SalesCommission.search()" class="btn btn-info ">
		   			<span class="glyphicon glyphicon-search" aria-hidden="true" >搜索</span>
		   		</button>
		   		<button type="button" onclick="SalesCommission.empty()" class="btn btn-danger ">
		   			<span class="glyphicon glyphicon-remove" aria-hidden="true" >清空</span>
		   		</button>
	   	  </div>
		</form>
	</div>
</div>

<!--列表 -->
<div style="width: 99%;overflow: auto;">
	<table id="SalesCommission-table" class="table table-hover table-striped table-condensed table-bordered"></table>
</div>
<!--toolbar  -->

<!-- 模态框（Modal） -->
<!-- 添加 -->
<div id="salesCommissionDlg" class="modal fade" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">佣金明细</h4>
            </div>
            <div class="container">
			<form class="form-horizontal"   method="post">
				<table class="table-bordered" style="text-align:center;">
				<tr style="height:35px;">
					<td colspan="5"><span id="insurance_saler"></span>&nbsp;&nbsp;&nbsp;<span id="insurance_saler_no"></span></td>
				</tr>
				<tr style="height:35px;">
					<td style="width:113px;">初年度佣金(元)</td>
					<td style="width:113px;">展业津贴(元)</td>
					<td style="width:113px;">增员津贴(元)</td>
					<td style="width:113px;">续年度佣金(元)</td>
					<td style="width:113px;">继续率奖金(元)</td>
				</tr>
				<tr style="height:35px;">
					<td><span id="initial_annual_commission"></span></td>
					<td><span id="exhibition_allowance"></span></td>
					<td><span id="increase_allowance"></span></td>
					<td><span id="continuous_allowance"></span></td>
					<td><span id="continuation_rate_bonus"></span></td>
				</tr>
				<tr style="height:35px;">
				<td style="width:113px;">年终奖(元)</td>
				<td>长期客服奖(元)</td><td>直辖组管理津贴(元)</td><td>处长育成奖(元)</td><td>直辖部管理津贴(元)</td></tr>
				<tr style="height:35px;">
					<td><span id="annual_bonus"></span></td>
					<td><span id="service_award"></span></td>
					<td><span id="group_run_allowance"></span></td>
					<td><span id="director_culture_award"></span></td>
					<td><span id="dep_run_allowance"></span></td>					
				</tr>
				<tr style="height:35px;"><td>部育成奖(元)</td><td>总监津贴(元)</td><td colspan="3">合计(元)</td></tr>
				<tr style="height:35px;">
					<td><span id="minister_culture_award"></span></td>
					<td><span id="chief_inspector_allowance"></span></td>
					<td colspan="3"><span id="total_commission"></span></td>
				</tr>
				</table>		
            </form>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

</body>
</html>