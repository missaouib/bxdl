<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/core.jsp" %>
<!DOCTYPE  html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <title>新增销售人员	</title>
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Cache" content="no-cache">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   	<link rel="stylesheet" href="${path}/css/system/role/role.css">
    <script src="${path}/static/js/bootstrap.js"></script>
    <script src="${path}/js/insurance/insuranceSalesView.js"></script>
</head>
<body>
            <div class="container">
			<form class="form-horizontal" id="addForm"  method="post">
			<div class="form-group" style="margin-top:20px;">			
				<input type="hidden" id="insuranceSalesId"  name="insuranceSalesId" value="${insuranceSalesInfo.insuranceSalesId}" />			
			</div>	
			
			<%--<div class="form-group">
				<label class="col-md-2 control-label">所属机构性质：</label>
				<div class="col-md-3" class="form-control form-control-static">
					<input type="radio" readonly="readonly" name="cooperationType" value="0" <c:if test="${insuranceSalesInfo.cooperationType=='0'}"> checked="checked"</c:if>>直营&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="radio" readonly="readonly" name="cooperationType" value="1" <c:if test="${insuranceSalesInfo.cooperationType=='1'}"> checked="checked"</c:if>>加盟
				</div>
			</div>	--%>
							
			<div class="form-group">
				<label class="col-md-2 control-label">组织机构名称：</label>
				<div class="col-md-3 ">
					<input type="text" readonly="readonly"  id="salesOrgName"  name="salesOrgName" value="${insuranceSalesInfo.salesOrgName}" class="form-control form-control-static" placeholder="请输入组织机构名称">
				</div>

				<label class="col-md-2 control-label">组织机构：</label>
				<div class="col-md-3 ">
					<select class="form-control form-control-static" disabled="disabled" id="salesOrgId" name="salesOrgId" myvalue="${insuranceSalesInfo.salesOrgId}">
                        <option value="">请选择机构</option>
             		</select>				
             	</div>	
			</div>

			<div class="form-group">
				<label class="col-md-2 control-label">销售团队名称：</label>
				<div class="col-md-3 ">
					<input type="text" readonly="readonly"  id="parentSalesTeamName"  name="parentSalesTeamName" value="${insuranceSalesInfo.salesTeamName}" class="form-control form-control-static" placeholder="请输入销售团队名称">
				</div>

				<label class="col-md-2 control-label">销售团队：</label>
				<input type="hidden" id="PTcode" name="PTcode" value="" />
				<div class="col-md-3 ">
					<select class="form-control form-control-static" disabled="disabled" id="salesTeamId" name="salesTeamId" myvalue="${insuranceSalesInfo.salesTeamId}">
                        <option value="">请选择销售团队</option>
             		</select>				
             	</div>	
			</div>
			
			<div class="form-group">
				<label class="col-md-2 control-label">员工工号：</label>
				<div class="col-md-3 ">
					<input type="text" readonly="readonly"  id="insuranceSalerNo"  name="insuranceSalerNo" value="${insuranceSalesInfo.insuranceSalerNo}" class="form-control form-control-static" placeholder="请输入员工工号">
				</div>
             	
				<label class="col-md-2 control-label">员工姓名：</label>
				<div class="col-md-3 ">
					<input type="text" readonly="readonly"  id="insuranceSaler"  name="insuranceSaler" value="${insuranceSalesInfo.insuranceSaler}" class="form-control form-control-static" placeholder="请输入员工姓名">
				</div>             	
			</div>	
			
			<div class="form-group">
				<label class="col-md-2 control-label">入职职级序列：</label>
				<div class="col-md-3 ">
					<select class="form-control form-control-static" disabled="disabled" id="rankSequenceId" name="rankSequenceId" myvalue="${insuranceSalesInfo.rankSequenceId}">
                        <option value="">请选择职级序列</option>
             		</select>				
             	</div>
				
				<label class="col-md-2 control-label">入职职级：</label>
				<div class="col-md-3 ">
					<select class="form-control form-control-static" disabled="disabled" id="salesGradeId" name="salesGradeId" myvalue="${insuranceSalesInfo.salesGradeId}">
                        <option value="">请选择职级</option>
             		</select>				
             	</div>	            	
			</div>
			
			<div class="form-group" id="zjjt_div" style="display:none">
				<label class="col-md-2 control-label">总监津贴：
				（<button id="zhengshu_add" type="button" onclick="InsuranceSalesInfo.addZjjt()" class="btn btn-primary">新增</button>）</label>
				<div class="col-md-3 " style="width:880px;">
					<div style="overflow:auto;width:850px;">
					<table id="zjjt_list" class="table table-hover table-striped table-condensed table-bordered" style="width:850px;font-size:13px;">
						<tr>
							<td style="width:120px;text-align:center;">操作</td>
							<td style="width:140px;text-align:center;">设置项</td>
							<td style="width:470px;text-align:center;">规则</td>
							<td style="width:120px;text-align:center;">管理津贴比例(%)</td>
						</tr>					
					</table>
					</div>
				</div>			
			</div>			
						
			<div class="form-group">
				<label class="col-md-2 control-label">证件类型：</label>
				<div class="col-md-3 ">
					<select class="form-control form-control-static" disabled="disabled" id="cardType" name="cardType" myvalue="${insuranceSalesInfo.cardType}">
						<option value="">请选择证件类型</option>
             		</select>				
             	</div>			
			
				<label class="col-md-2 control-label">证件号码：</label>
				<div class="col-md-3 ">
					<input type="text" readonly="readonly"  id="cardNo"  name="cardNo" value="${insuranceSalesInfo.cardNo}" class="form-control form-control-static" placeholder="请输入证件号码">			
             	</div>
			</div>
							
			<div class="form-group">
				<label class="col-md-2 control-label">手机号码：</label>
				<div class="col-md-3 ">
					<input type="text" readonly="readonly"  id="mobile"  name="mobile" value="${insuranceSalesInfo.mobile}" class="form-control form-control-static" placeholder="请输入手机号码">
				</div>

				<label class="col-md-2 control-label">入司时间：</label>
				<div class="col-md-3 ">
                   <input readonly="readonly" class="form-control form-control-static" id="joinDate" name="joinDate" value="${insuranceSalesInfo.joinDate}" type="text" placeholder="请选择入司时间"/>
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-md-2 control-label">性别：</label>
				<div class="col-md-3 ">
					<select class="form-control form-control-static" disabled="disabled" id="sex" name="sex">
						<option value="">请选择</option>
						<option value="0" <c:if test="${insuranceSalesInfo.sex=='0'}"> selected="selected"</c:if>>女</option>
						<option value="1" <c:if test="${insuranceSalesInfo.sex=='1'}"> selected="selected"</c:if>>男</option>
             		</select>				
             	</div>			
			
				<label class="col-md-2 control-label">出生日期：</label>
				<div class="col-md-3 ">
                   <input readonly="readonly" class="form-control form-control-static" id="birthday" name="birthday" value="${insuranceSalesInfo.birthday}" type="text" placeholder="请选择出生时间"/>
             	</div>
			</div>
			
			<div class="form-group">
				<label class="col-md-2 control-label">银行名称：</label>
				<div class="col-md-3 ">
					<select class="form-control form-control-static" disabled="disabled" id="bankChannel" name="bankChannel" myvalue="${insuranceSalesInfo.bankChannel}">
						<option value="">请选择银行名称</option>
             		</select>				
             	</div>
			</div>
			<div class="form-group">
				<label class="col-md-2 control-label">开户行名称：</label>
				<div class="col-md-3 ">
					<input type="text" readonly="readonly" id="openBank"  name="openBank" value="${insuranceSalesInfo.openBank}" class="form-control form-control-static" placeholder="开户行名称">
             	</div>

				<label class="col-md-2 control-label">银行卡号：</label>
				<div class="col-md-3 ">
					<input type="text" readonly="readonly"  id="bankCardNo"  name="bankCardNo" value="${insuranceSalesInfo.bankCardNo}" class="form-control form-control-static" placeholder="请输入证件号码">
             	</div>
			</div>
			
			<div class="form-group">
				<label class="col-md-2 control-label">国籍：</label>
				<div class="col-md-3 ">
					<select class="form-control form-control-static" disabled="disabled" id="country" name="country"  myvalue="${insuranceSalesInfo.country}">
                        <option value="">请选择国籍</option>
             		</select>
             	</div>
             	
             	<label class="col-md-2 control-label">民族：</label>
             	<div class="col-md-3 ">	
             		<select class="form-control form-control-static" disabled="disabled" id="nation" name="nation"  myvalue="${insuranceSalesInfo.nation}">
                        <option value="">请选择民族</option>
             		</select>
             	</div>             	
             </div>
             
			<div class="form-group">
				<label class="col-md-2 control-label">政治面貌：</label>
				<div class="col-md-3 ">
					<select class="form-control form-control-static" disabled="disabled" id="politicalAppearance" name="politicalAppearance"  myvalue="${insuranceSalesInfo.politicalAppearance}">
                        <option value="">请选择政治面貌</option>
             		</select>
             	</div>
             	
             	<label class="col-md-2 control-label">党派：</label>
             	<div class="col-md-3 ">	
             		<select class="form-control form-control-static" disabled="disabled" id="party" name="party"  myvalue="${insuranceSalesInfo.party}">
                        <option value="">请选择党派</option>
             		</select>
             	</div>             	
             </div>             			
			
			<div class="form-group">
				<input type="hidden" id="areaCode"  name="areaCode" value="${insuranceSalesInfo.areaCode}" class="form-control form-control-static" placeholder="请输入区域编码">
				<label class="col-md-2 control-label">所在省份：</label>
				<div class="col-md-3 ">
					<select class="form-control form-control-static" disabled="disabled" id="sheng" name="sheng">
                        <option value="">请选择省份</option>
             		</select>
             	</div>
             	
             	<label class="col-md-2 control-label">所在城市：</label>
             	<div class="col-md-3 ">	
             		<select class="form-control form-control-static" disabled="disabled" id="shi" name="shi">
                        <option value="">请选择城市</option>
             		</select>
             	</div>             	
             </div>
             
            <div class="form-group">            
            	<label class="col-md-2 control-label">所在区县：</label>
             	<div class="col-md-3 ">		
             		<select class="form-control form-control-static" disabled="disabled" id="qu" name="qu">
                        <option value="">请选择区县</option>
             		</select>
				</div>
				
				<label class="col-md-2 control-label">家庭住址：</label>
				<div class="col-md-3 ">
					<input type="text" readonly="readonly"  id="address"  name="address"  value="${insuranceSalesInfo.address}" class="form-control form-control-static" placeholder="请输入家庭住址">
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-md-2 control-label">邮编：</label>
				<div class="col-md-3 ">
					<input type="text" readonly="readonly"  id="postCode"  name="postCode"  value="${insuranceSalesInfo.postCode}" class="form-control form-control-static" placeholder="请输入邮编">
				</div> 			
			
				<label class="col-md-2 control-label">固定电话：</label>
				<div class="col-md-3 ">
					<input type="text" readonly="readonly"  id="cellPhone"  name="cellPhone" value="${insuranceSalesInfo.cellPhone}" class="form-control form-control-static" placeholder="请输入固定电话">
				</div>            	
			</div>	
			
			<div class="form-group">			
				<label class="col-md-2 control-label">户口所在地：</label>
				<div class="col-md-3 ">
					<input type="text" readonly="readonly"  id="householdRegPlace"  name="householdRegPlace" value="${insuranceSalesInfo.householdRegPlace}" class="form-control form-control-static" placeholder="请输入户口所在地">
				</div> 			
			
				<label class="col-md-2 control-label">档案所在地：</label>
				<div class="col-md-3 ">
					<input type="text" readonly="readonly"  id="archivesPreservationPlace"  name="archivesPreservationPlace" value="${insuranceSalesInfo.archivesPreservationPlace}" class="form-control form-control-static" placeholder="请输入档案所在地">
				</div>            	
			</div>																				

			<div class="form-group">
				<label class="col-md-2 control-label">籍贯：</label>
				<div class="col-md-3 ">
					<input type="text" readonly="readonly"  id="nativePlace"  name="nativePlace" value="${insuranceSalesInfo.nativePlace}" class="form-control form-control-static" placeholder="请输入籍贯">
				</div>  			
			
				<%--<label class="col-md-2 control-label">兼职/全职：</label>
				<div class="col-md-3 ">
					<select class="form-control form-control-static" disabled="disabled" id="dutyType" name="dutyType">
						<option value="">请选择</option>
						<option value="0" <c:if test="${insuranceSalesInfo.dutyType=='0'}"> selected="selected"</c:if>>兼职</option>
						<option value="1" <c:if test="${insuranceSalesInfo.dutyType=='1'}"> selected="selected"</c:if>>全职</option>
             		</select>				
             	</div>--%>
			</div>
			
			<div class="form-group">	
				<label class="col-md-2 control-label">邮箱：</label>
				<div class="col-md-3 ">
                   <input readonly="readonly" class="form-control form-control-static" id="email" name="email" value="${insuranceSalesInfo.email}" type="text" placeholder="请输入邮箱" />
             	</div>
             						
				<label class="col-md-2 control-label">QQ号码：</label>
				<div class="col-md-3 ">
					<input type="text" readonly="readonly"  id="qqNumber"  name="qqNumber" value="${insuranceSalesInfo.qqNumber}" class="form-control form-control-static" placeholder="请输入QQ号">
				</div> 			            	
			</div>	
			
			<div class="form-group">
				<label class="col-md-2 control-label">微信号码：</label>
				<div class="col-md-3 ">
					<input type="text" readonly="readonly"  id="wechatNumber"  name="wechatNumber" value="${insuranceSalesInfo.wechatNumber}" class="form-control form-control-static" placeholder="请输入微信号码">
				</div>			
			
				<label class="col-md-2 control-label">最高学历：</label>
				<div class="col-md-3 ">
					<select class="form-control form-control-static" disabled="disabled" id="educationalBg" name="educationalBg"  myvalue="${insuranceSalesInfo.educationalBg}">
                        <option value="">请选择学历</option>
             		</select>
             	</div>             	
             </div>
             
             <div class="form-group">             	
             	<label class="col-md-2 control-label">最高学位：</label>
             	<div class="col-md-3 ">	
             		<select class="form-control form-control-static" disabled="disabled" id="academicDegree" name="academicDegree" myvalue="${insuranceSalesInfo.academicDegree}">
                        <option value="">请选择学位</option>
             		</select>
             	</div>	
             	
 				<label class="col-md-2 control-label">外语水平：</label>
				<div class="col-md-3 ">
					<input type="text" readonly="readonly"  id="foreignLanguageAbility"  name="foreignLanguageAbility" value="${insuranceSalesInfo.foreignLanguageAbility}" class="form-control form-control-static" placeholder="请输入外语水平">
				</div>	            	
             </div>						

			<div class="form-group">
				<label class="col-md-2 control-label">婚姻状况：</label>
				<div class="col-md-3 ">
					<select class="form-control form-control-static" disabled="disabled" id="maritalStatus" name="maritalStatus">
						<option value="">请选择</option>
						<option value="0" <c:if test="${insuranceSalesInfo.maritalStatus=='0'}"> selected="selected"</c:if>>未婚</option>
						<option value="1" <c:if test="${insuranceSalesInfo.maritalStatus=='1'}"> selected="selected"</c:if>>已婚</option>
             		</select>				
             	</div>			
			
				<label class="col-md-2 control-label">结婚日期：</label>
				<div class="col-md-3 ">
                   <input  readonly="readonly" class="form-control form-control-static" id="marriageDate" name="marriageDate"  value="${insuranceSalesInfo.marriageDate}" type="text" placeholder="请选择结婚时间"/>
             	</div>
			</div>
			
          	<div class="form-group">    	
 				<label class="col-md-2 control-label">上家公司名称：</label>
				<div class="col-md-3 ">
					<input type="text" readonly="readonly"  id="preCompany"  name="preCompany" value="${insuranceSalesInfo.preCompany}" class="form-control form-control-static" placeholder="请输入外语水平">
				</div>	
				
 				<label class="col-md-2 control-label">上家公司职业：</label>
				<div class="col-md-3 ">
					<input type="text" readonly="readonly"  id="preOccupation"  name="preOccupation" value="${insuranceSalesInfo.preOccupation}" class="form-control form-control-static" placeholder="请输入外语水平">
				</div>					            	
             </div>				

             <div class="form-group">             	
             	<label class="col-md-2 control-label">是否有待业证：</label>
             	<div class="col-md-3 ">	
             		<select class="form-control form-control-static" disabled="disabled" id="leavingCertificate" name="leavingCertificate">
						<option value="0" <c:if test="${insuranceSalesInfo.leavingCertificate=='0'}"> selected="selected"</c:if>>否</option>
						<option value="1" <c:if test="${insuranceSalesInfo.leavingCertificate=='1'}"> selected="selected"</c:if>>是</option>
             		</select>
             	</div>	
             	
             	<label class="col-md-2 control-label">是否有工作室：</label>
             	<div class="col-md-3 ">	
             		<select class="form-control form-control-static" disabled="disabled" id="havingWorkGroup" name="havingWorkGroup">
						<option value="0" <c:if test="${insuranceSalesInfo.havingWorkGroup=='0'}"> selected="selected"</c:if>>否</option>
						<option value="1" <c:if test="${insuranceSalesInfo.havingWorkGroup=='1'}"> selected="selected"</c:if>>是</option>
             		</select>
             	</div> 
             </div>

			<div class="form-group">
				<label class="col-md-2 control-label">工作室联系电话：</label>
				<div class="col-md-3 ">
                   <input  readonly="readonly" class="form-control form-control-static" id="workGroupPhone" name="workGroupPhone" value="${insuranceSalesInfo.workGroupPhone}" type="text" placeholder="请输入工作室联系电话" />
             	</div>			
			
				<label class="col-md-2 control-label">是否内部推荐：</label>
				<div class="col-md-3 ">
					<select class="form-control form-control-static" disabled="disabled" id="internalReferral" name="internalReferral">
						<option value="">请选择</option>
						<option value="0" <c:if test="${insuranceSalesInfo.internalReferral=='0'}"> selected="selected"</c:if>>是</option>
						<option value="1" <c:if test="${insuranceSalesInfo.internalReferral=='1'}"> selected="selected"</c:if>>否</option>
             		</select>				
             	</div>			
			</div>
			
			<div class="form-group">
				<label class="col-md-2 control-label">执业证号：</label>
				<div class="col-md-3 ">
                   <input readonly="readonly" class="form-control form-control-static" id="certificateNumber" name="certificateNumber" value="${insuranceSalesInfo.certificateNumber}" type="text" placeholder="请输入执业证号" />
             	</div>			
			
				<label class="col-md-2 control-label">执业证状态：</label>
				<div class="col-md-3 ">
					<select class="form-control form-control-static" disabled="disabled" id="certificateStatus" name="certificateStatus">
						<option value="">请选择</option>
						<option value="0" <c:if test="${insuranceSalesInfo.certificateStatus=='0'}"> selected="selected"</c:if>>有效</option>
						<option value="1" <c:if test="${insuranceSalesInfo.certificateStatus=='1'}"> selected="selected"</c:if>>失效</option>
             		</select>				
             	</div>			
			</div>	
			
			<div class="form-group">
				<label class="col-md-2 control-label">执业证有效期起：</label>
				<div class="col-md-3 ">
                   <input  readonly="readonly" class="form-control form-control-static" id="certificateStartDate" name="certificateStartDate" value="${insuranceSalesInfo.certificateStartDate}" type="text" placeholder="请选择执业证有效期起"/>
             	</div>			
			
				<label class="col-md-2 control-label">执业证有效期止：</label>
				<div class="col-md-3 ">
                   <input readonly="readonly" class="form-control form-control-static" id="certificateEndDate" name="certificateEndDate" value="${insuranceSalesInfo.certificateEndDate}" type="text" placeholder="请选择执业证有效期止"/>
             	</div>
			</div>	
			
			<div class="form-group">
				<label class="col-md-2 control-label">兴趣爱好：</label>
				<div class="col-md-3 ">
					<textarea  readonly="readonly" style="width:655px;" rows="4" id="interest" name="interest" class="form-control form-control-static" placeholder="请输入兴趣爱好">${insuranceSalesInfo.interest}</textarea>
				</div>
			</div>	
			
			<div class="form-group">
				<label class="col-md-2 control-label">备注：</label>
				<div class="col-md-3 ">
					<textarea  readonly="readonly" style="width:655px;" rows="4" id="remark" name="remark" class="form-control form-control-static" placeholder="请输入备注">${insuranceSalesInfo.remark}</textarea>
				</div>
			</div>	



			<table id="protocol-insurance-table" class="table table-hover table-striped table-condensed table-bordered"></table>
			<div id="checkInsuranceDlg"  class="modal fade" aria-hidden="true" style="height: 370px;background: #fff; ">
				<div>
					<table id="check-insurance-table"  style="overflow: hidden;padding: 5px 15px 15px;" class="table table-hover table-striped table-condensed table-bordered"></table>
				</div>
				<div id="checkkInsuranceButton" class="btn-group">
					<button type="button" onclick="InsuranceSalesView.cancelCheckProduct()" class="btn btn-default" style="margin-left: 20px;">关闭</button>
				</div>
			</div>
			
			<input type="hidden" id="titleList" name="titleList" value='${salesTitles}' /><!-- 职称、证书 -->
			<input type="hidden" id="eduJobsList" name="eduJobsList" value='${salesEduJobs}' /><!-- 教育、工作、培训 -->
			<input type="hidden" id="relativeList" name="relativeList" value='${salesRelatives}' /><!-- 家庭成员、紧急联系人、司外担保人 -->
			<input type="hidden" id="contractList" name="contractList" value='${salesContracts}' /><!-- 合同 -->	
			<input type="hidden" id="zjjtList" name="zjjtList" value='${zjjtList}' /><!-- 合同 -->	
					
			<div class="form-group">
				<label class="col-md-2 control-label">职称：
				<!-- （<button id="zc_add" type="button" onclick="InsuranceSalesInfo.openAddModal('zcAddDlg')" class="btn btn-primary">新增</button>） --></label>
				<div class="col-md-3 " style="width:880px;">
					<div style="overflow:auto;width:850px;">
					<table id="zc_list" class="table table-hover table-striped table-condensed table-bordered" style="width:1200px;font-size:13px;">
						<tr>
							<td style="width:220px;text-align:center;">职称名称</td>
							<td style="width:220px;text-align:center;">发证机关</td>
							<td style="width:120px;text-align:center;">获取日期</td>
							<td style="width:120px;text-align:center;">生效日期</td>
							<td style="width:120px;text-align:center;">结束日期</td>
							<td style="width:280px;text-align:center;">备注</td>
						</tr>
					</table>
					</div>
				</div>			
			</div>	
			
			<div class="form-group">
				<label class="col-md-2 control-label">证书：
				<!-- （<button id="zhengshu_add" type="button" onclick="InsuranceSalesInfo.openAddModal('zhengshuAddDlg')" class="btn btn-primary">新增</button>） --></label>
				<div class="col-md-3 " style="width:880px;">
					<div style="overflow:auto;width:850px;">
					<table id="zs_list" class="table table-hover table-striped table-condensed table-bordered" style="width:1200px;font-size:13px;">
						<tr>
							<td style="width:220px;text-align:center;">证书名称</td>
							<td style="width:120px;text-align:center;">证书编号</td>
							<td style="width:220px;text-align:center;">发证机构</td>
							<td style="width:120px;text-align:center;">发证日期</td>
							<td style="width:120px;text-align:center;">失效日期</td>
							<td style="width:280px;text-align:center;">有效状态</td>
						</tr>
					</table>
					</div>
				</div>			
			</div>
			
			<div class="form-group">
				<label class="col-md-2 control-label">教育经历：
				<!-- （<button id="jyjl_add" type="button" onclick="InsuranceSalesInfo.openAddModal('jyjlAddDlg')" class="btn btn-primary">新增</button>） --></label>
				<div class="col-md-3 " style="width:880px;">
					<div style="overflow:auto;width:850px;">
					<table id="jyjl_list" class="table table-hover table-striped table-condensed table-bordered" style="width:1100px;font-size:13px;">
						<tr>
							<td style="width:220px;text-align:center;">学校名称</td>
							<td style="width:120px;text-align:center;">开始日期</td>
							<td style="width:120px;text-align:center;">结束日期</td>
							<td style="width:120px;text-align:center;">学历</td>
							<td style="width:120px;text-align:center;">学位</td>
							<td style="width:280px;text-align:center;">备注</td>
						</tr>
					</table>
					</div>
				</div>			
			</div>		
			
			<div class="form-group">
				<label class="col-md-2 control-label">工作经历：
				<!-- （<button id="gzjl_add" type="button" onclick="InsuranceSalesInfo.openAddModal('gzjlAddDlg')" class="btn btn-primary">新增</button>） --></label>
				<div class="col-md-3 " style="width:880px;">
					<div style="overflow:auto;width:850px;">
					<table id="gzjl_list" class="table table-hover table-striped table-condensed table-bordered" style="width:980px;font-size:13px;">
						<tr>
							<td style="width:220px;text-align:center;">单位名称</td>
							<td style="width:120px;text-align:center;">开始日期</td>
							<td style="width:120px;text-align:center;">结束日期</td>
							<td style="width:120px;text-align:center;">职位</td>
							<td style="width:280px;text-align:center;">备注</td>
						</tr>
					</table>
					</div>
				</div>			
			</div>	
			
			<div class="form-group">
				<label class="col-md-2 control-label">培训经历：
				<!-- （<button id="pxjl_add" type="button" onclick="InsuranceSalesInfo.openAddModal('pxjlAddDlg')" class="btn btn-primary">新增</button>） --></label>
				<div class="col-md-3 " style="width:880px;">
					<div style="overflow:auto;width:850px;">
					<table id="pxjl_list" class="table table-hover table-striped table-condensed table-bordered" style="width:980px;font-size:13px;">
						<tr>
							<td style="width:220px;text-align:center;">课程名称</td>
							<td style="width:120px;text-align:center;">开始日期</td>
							<td style="width:120px;text-align:center;">结束日期</td>
							<td style="width:120px;text-align:center;">成绩</td>
							<td style="width:280px;text-align:center;">备注</td>
						</tr>
					</table>
					</div>
				</div>			
			</div>	
			
			<div class="form-group">
				<label class="col-md-2 control-label">家庭成员：
				<!-- （<button id="jtcy_add" type="button" onclick="InsuranceSalesInfo.openAddModal('jtcyAddDlg')" class="btn btn-primary">新增</button>） --></label>
				<div class="col-md-3 " style="width:880px;">
					<div style="overflow:auto;width:850px;">
					<table id="jtcy_list" class="table table-hover table-striped table-condensed table-bordered" style="width:980px;font-size:13px;">
						<tr>
							<td style="width:220px;text-align:center;">成员姓名</td>
							<td style="width:120px;text-align:center;">联系电话</td>
							<td style="width:120px;text-align:center;">关系</td>
							<td style="width:120px;text-align:center;">职业</td>
							<td style="width:280px;text-align:center;">备注</td>
						</tr>
					</table>
					</div>
				</div>			
			</div>	
			
			<div class="form-group">
				<label class="col-md-2 control-label">紧急联系人：
				<!-- （<button id="jjlxr_add" type="button" onclick="InsuranceSalesInfo.openAddModal('jjlxrAddDlg')" class="btn btn-primary">新增</button>） --></label>
				<div class="col-md-3 " style="width:880px;">
					<div style="overflow:auto;width:850px;">
					<table id="jjlxr_list" class="table table-hover table-striped table-condensed table-bordered" style="width:1200px;font-size:13px;">
						<tr>
							<td style="width:220px;text-align:center;">联系人姓名</td>
							<td style="width:120px;text-align:center;">联系人电话</td>
							<td style="width:120px;text-align:center;">关系</td>
							<td style="width:220px;text-align:center;">联系人地址</td>
							<td style="width:120px;text-align:center;">联系人邮编</td>
							<td style="width:280px;text-align:center;">备注</td>
						</tr>
					</table>
					</div>
				</div>			
			</div>	
			
			<div class="form-group">
				<label class="col-md-2 control-label">司外担保人：
				<!-- （<button id="swdbr_add" type="button" onclick="InsuranceSalesInfo.openAddModal('swdbrAddDlg')" class="btn btn-primary">新增</button>） --></label>
				<div class="col-md-3 " style="width:880px;">
					<div style="overflow:auto;width:850px;">
					<table id="swdbr_list" class="table table-hover table-striped table-condensed table-bordered" style="width:920px;font-size:13px;">
						<tr>
							<td style="width:220px;text-align:center;">担保人姓名</td>
							<td style="width:120px;text-align:center;">担保人电话</td>
							<td style="width:120px;text-align:center;">关系</td>
							<td style="width:220px;text-align:center;">担保人身份证号</td>
							<td style="width:120px;text-align:center;">担保日期</td>
						</tr>
					</table>
					</div>
				</div>			
			</div>	
			
			<div class="form-group">
				<label class="col-md-2 control-label">合同信息：
				<!-- （<button id="ht_add" type="button" onclick="InsuranceSalesInfo.openAddModal('htAddDlg')" class="btn btn-primary">新增</button>） --></label>
				<div class="col-md-3 " style="width:880px;">
					<div style="overflow:auto;width:850px;">
					<table id="ht_list" class="table table-hover table-striped table-condensed table-bordered" style="width:1180px;font-size:13px;">
						<tr>
							<td style="width:220px;text-align:center;">合同编号</td>
							<td style="width:120px;text-align:center;">合同类型</td>
							<td style="width:120px;text-align:center;">签订竞业协议</td>
							<td style="width:120px;text-align:center;">签订保密协议</td>
							<td style="width:120px;text-align:center;">签订日期</td>
							<td style="width:120px;text-align:center;">试用结束日期</td>
							<td style="width:120px;text-align:center;">合同生效日期</td>
							<td style="width:120px;text-align:center;">合同终止日期</td>
						</tr>
					</table>
					</div>
				</div>			
			</div>																																	
																																												
            <div class="modal-footer col-md-6">
            <!--用来清空表单数据-->
            <input type="reset" name="reset" style="display: none;" />
                <button type="button" class="btn btn-default" onclick="windowCloseTab()">关闭</button>
            </div>
            </form>
            </div>
            
</body>
</html>