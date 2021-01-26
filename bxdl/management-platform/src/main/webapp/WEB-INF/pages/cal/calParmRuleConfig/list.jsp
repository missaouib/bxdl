<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/include/core.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>基本法规则参数配置页面</title>
    <link rel="stylesheet" href="${path}/css/system/role/role.css">
	<script src="${path}/js/system/comm.js" charset="UTF-8" type="text/javascript"></script>
	<script src="${path}/js/cal/calParmRuleConfig.js" charset="UTF-8" type="text/javascript"></script>
    <script src="${path}/static/js/bootstrap.js"></script>
<style>
.btn-toolbar {margin-bottom: 5px;}
/* 弹出框样式 */
.modal { padding-left: 0!important;}
.modal-dialog {	background: #fff;}
.modal-dialog .row { margin-left: 0; margin-right: 0;}
.modal-dialog .form-horizontal { padding-top: 15px;}
.modal-dialog .form-horizontal .form-group { padding-left: 100px;}
.modal-content { box-shadow: none;}
</style>    
</head>
<script>
	var RULETYPE = ${RULETYPE};
	var PARAMTYPE = ${PARAMTYPE};
</script>
<body>
<div class="panel panel-default">
	<div class="panel-body">
		<form class="form-inline hz-form-inline" id="searchForm">

			<div class="form-group">
				<label  class="control-label" for="s_orgId">总/省分机构</label>
				<select type="text" class="form-control" id="s_orgId" name="orgId">
					<option value="">请选择总/省分机构</option>
					<c:forEach items="${saleOrgList}" var="saleOrg" >
						<option value="${saleOrg.orgId}">${saleOrg.orgName}</option>
					</c:forEach>
				</select>
			</div>
			<div class="form-group">
				<label  class="control-label" for="s_paramType">参数类型</label>
				<select type="text" class="form-control" id="s_paramType" name="paramType">
					<option value="">请选择参数类型</option>
					<c:forEach items="${calParamTypeDictlist}" var="paramType" >
						<option value="${paramType.dictCode}">${paramType.dictName}</option>
					</c:forEach>
				</select>
			</div>

			<div class="form-group">
				<button type="button" class="btn btn-info" onclick="CalParmRule.search()">
					<span class="glyphicon glyphicon-search" aria-hidden="true" >搜索</span>
				</button>
				<button type="button" class="btn btn-danger" onclick="CalParmRule.cla()">
					<span class="glyphicon glyphicon-remove" aria-hidden="true" >清空</span>
				</button>
			</div>
		</form>
	</div>
</div>
<!--获取用员工号标签  -->
<input type="hidden" id="userEmployeeNo"  value ='<shiro:principal property="employeeNo"/>' />
<!--toolbar  -->
<div id="calParamRuleToolbar" class="btn-toolbar">
	<shiro:hasPermission name="calParmRuleConfig:add">
	    	<button class="btn btn-info" type="button" onclick="CalParmRule.openAddModal()">
	    		<span class="glyphicon glyphicon-plus" >添加</span>
	    	</button>
    </shiro:hasPermission>
	<shiro:hasPermission name="calParmRuleConfig:update">
		<button class="btn btn-info" type="button" onclick="CalParmRule.openUpdateModal()">
			<span class="glyphicon glyphicon-plus" >修改</span>
		</button>
	</shiro:hasPermission>
    <shiro:hasPermission name="calParmRuleConfig:delete">
    	<button class=" btn btn-danger" type="button" onclick="CalParmRule.deleteCalParamRule()">
    			<span class="glyphicon glyphicon-remove" >删除</span>
    	</button>
    </shiro:hasPermission>
</div> 
<table id="calParamRule-table" class="table table-hover table-striped table-condensed table-bordered"></table>

<!-- 模态框（Modal） -->
<!-- 添加 -->
<div id="calParamRuleAddDlg" class="modal fade" tabindex="-1" role="dialog"
	 data-backdrop="static" data-keyboard="false" aria-labelledby="addModalLabel" aria-hidden="true">
    <div class="modal-dialog">
    	<div class="modal-header">
             <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
             <h4 class="modal-title" id="addModalLabel">配置参数规则</h4>
         </div>
       	<div class="modal-content"> 
            <div class="row">
				<form class="form-horizontal" id="addCalParamRuleForm"  method="post">
					<input type="hidden" name="id" id="id" />
					<div class="form-group">
						<label class="col-md-2 control-label">机构：</label>
						<div class="col-md-8 ">
							<select class="form-control form-control-static" id="add_orgId" name="orgId">
								<option value="">请选择总/省分机构</option>
								<c:forEach items="${saleOrgList}" var="saleOrg" >
									<option value="${saleOrg.orgId}">${saleOrg.orgName}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-2 control-label">参数：</label>
						<div class="col-md-8 ">
							<select class="form-control form-control-static" id="add_paramType" name="paramType">
								<option>请选择参数类型</option>
								<c:forEach items="${calParamTypeDictlist}" var="calParamTypeDict" >
									<option value="${calParamTypeDict.dictCode}">${calParamTypeDict.dictName}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-2 control-label">规则：</label>
						<div class="col-md-8 "><span class="form-control form-control-static">请勾选对应规则</span></div>
					</div>
					<div class="form-group">
						<div id="ruleType0" name="ruleTypeName" style="display: none"><!-- 展业 -->
							<input type="checkbox" name="ruleType0" value="00" /> 区间设定值
							<input type="checkbox" name="ruleType0" value="01"/> FYC百位取整
							<input type="checkbox" name="ruleType0" value="02"/> FYC乘以比例参数
							<input type="checkbox" name="ruleType0" value="03"/> 有额外的递增
						</div>

						<div id="ruleType1" name="ruleTypeName" style="display: none"><!-- 增员 -->
							<input type="checkbox" name="ruleType1" value="10" /> 区间设定值
							<input type="checkbox" name="ruleType1" value="11"/> 固定数值
						</div>
						<div id="ruleType4" name="ruleTypeName" style="display: none"><!-- 直辖组 -->
							<input type="checkbox" name="ruleType4" value="40" /> 区间设定值
							<input type="checkbox" name="ruleType4" value="41"/> 固定数值
							<input type="checkbox" name="ruleType4" value="42"/> 组活力人数 || 区间设定值
						</div>
						<div id="ruleType5" name="ruleTypeName" style="display: none"><!-- 直辖部 -->
							<input type="checkbox" name="ruleType5" value="50" /> 区间设定值
							<input type="checkbox" name="ruleType5" value="51"/> 固定数值
						</div>

					</div>

					<div class="form-group" id="activeSalerFlagDivId" name="flagDivName" style="display: none">
						<label class="col-md-6 control-label">是否包含组活力人数：</label>
						<div class="col-md-3 ">
							<input type="radio" name="activeSalerFlag"  value="0" /> 否
							<input type="radio" name="activeSalerFlag"  value="1" /> 是
						</div>
					</div>
					<div class="form-group" id="excludeDirectlyGroupFlagDivId" name="flagDivName" style="display: none">
						<label class="col-md-6 control-label">直辖组参数是否排除部长直辖组：</label>
						<div class="col-md-3 ">
							<input type="radio" name="excludeDirectlyGroupFlag"  value="0" /> 否
							<input type="radio" name="excludeDirectlyGroupFlag"  value="1" /> 是
						</div>
					</div>
					<div class="form-group" id="includeDirectlyGroupFlagDivId" name="flagDivName" style="display: none">
						<label class="col-md-6 control-label">直辖部参数是否包含部长直辖组：</label>
						<div class="col-md-3 ">
							<input type="radio" name="includeDirectlyGroupFlag"  value="0" /> 否
							<input type="radio" name="includeDirectlyGroupFlag"  value="1" /> 是
						</div>
					</div>

	            </form>
            </div>
        </div><!-- /.modal-content -->
        <div class="modal-footer">
        	<!--用来清空表单数据-->
            <button type="button" class="btn btn-default" onclick="CalParmRule.closeDlg()">关闭</button>
           <button id="saveButton" type="button" onclick="CalParmRule.saveCalParamRuleConfig()" class="btn btn-primary">保存</button>
        </div>        
    </div><!-- /.modal -->
</div>


<!-- 模态框（Modal） -->
<!-- 修改 -->
<div id="calParamRuleMydlg" class="modal fade"  tabindex="-1" role="dialog" data-backdrop="static"
	 data-keyboard="false" aria-labelledby="myModalLabel" aria-hidden="true">
<%--    <div class="modal-dialog">--%>
<%--        <div class="modal-content">--%>
<%--            <div class="modal-header">--%>
<%--                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>--%>
<%--                <h4 class="modal-title" id="updateModalLabel">修改角色</h4>--%>
<%--            </div>--%>
<%--            <div class="row">--%>
<%--			<form class="form-horizontal" id="updateCalParamRuleForm"  method="post">--%>
<%--			<!-- <div class="form-group">--%>
<%--				<div class="col-md-6 "> --%>
<%--			-->--%>
<%--					<input type="hidden" id="calParamRuleId" name="id"  class="form-control form-control-static" readonly="readonly" placeholder="必填">--%>
<%--			<!-- 	</div>--%>
<%--			</div>--%>
<%--			-->--%>
<%--			--%>
<%--			<div class="form-group">--%>
<%--				<label class="col-md-3 control-label">角色名称：</label>--%>
<%--				<div class="col-md-6 ">--%>
<%--					<input type="text" id="role_update_roleName"  name="roleName" class="form-control form-control-static" placeholder="必填">--%>
<%--				</div>--%>
<%--			</div>--%>
<%--			--%>
<%--			<div class="form-group">--%>
<%--				<label class="col-md-3 control-label">角色编码：</label>--%>
<%--				<div class="col-md-6">--%>
<%--					<input type="text" id="role_update_roleCode"  name="roleCode" class="form-control form-control-static" placeholder="必填">--%>
<%--				</div>--%>
<%--			</div>--%>
<%--			--%>
<%--			<div class="form-group">--%>
<%--			<label class="col-md-3 control-label">角色描述：</label>--%>
<%--				<div class="col-md-6">--%>
<%--					<textarea rows="3" id="role_update_remark" name="remark" cols="30" class="form-control form-control-static" placeholder="必填"></textarea>--%>
<%--				</div>--%>
<%--			</div>--%>
<%--            <div class="modal-footer col-md-12">--%>
<%--            <!--用来清空表单数据-->--%>
<%--            <input type="reset" name="reset" style="display: none;" />--%>
<%--                <button type="button" class="btn btn-default" onclick="Role.closeDlg()">关闭</button>--%>
<%--               <button type="button" onclick="Role.updateRole()" class="btn btn-primary">保存</button>--%>
<%--            </div>--%>
<%--            </form>--%>
<%--            </div>--%>
<%--        </div><!-- /.modal-content -->--%>
    </div><!-- /.modal -->
</div>
</body>
</html>