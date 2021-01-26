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
    <script src="${path}/js/insurance/insuranceSales.js"></script>
</head>
<body>
            <div class="container">
			<form class="form-horizontal" id="addForm"  method="post">
			<div class="form-group" style="margin-top:20px;">
			</div>	
			
		    <%--	<div class="form-group">
				<label class="col-md-2 control-label">所属机构性质 *：</label>
				<div class="col-md-3" class="form-control form-control-static">
					<input type="radio" name="cooperationType" value="0" checked="checked">直营&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="radio" name="cooperationType" value="1">加盟
				</div>
			</div>--%>
							
			<div class="form-group">
				<label class="col-md-2 control-label">组织机构名称 *：</label>
				<div class="col-md-3 ">
					<input type="text" id="salesOrgName"  name="salesOrgName" class="form-control form-control-static" placeholder="请输入组织机构名称">
				</div>

				<label class="col-md-2 control-label">组织机构 *：</label>
				<div class="col-md-3 ">
					<select class="form-control form-control-static" id="salesOrgId" name="salesOrgId" myvalue="${salesOrgId}" >
                        <option value="">请选择机构</option>
             		</select>				
             	</div>	
			</div>

			<div class="form-group">
				<label class="col-md-2 control-label">销售团队名称 *：</label>
				<div class="col-md-3 ">
					<input type="text" id="parentSalesTeamName"  name="parentSalesTeamName" class="form-control form-control-static" placeholder="销售团队名称">
				</div>

				<label class="col-md-2 control-label">销售团队 *：</label>
				<input type="hidden" id="PTcode" name="PTcode" value="" />
				<div class="col-md-3 ">
					<select class="form-control form-control-static" id="salesTeamId" name="salesTeamId" myvalue="${salesTeamId}" >
                        <option value="">请选择销售团队</option>
             		</select>				
             	</div>	
			</div>
			
			<div class="form-group">
				<label class="col-md-2 control-label">员工工号 *：</label>
				<div class="col-md-3 ">
					<input type="text" id="insuranceSalerNo"  name="insuranceSalerNo" readonly="readonly" class="form-control form-control-static" placeholder="自动生成">
				</div>
             	
				<label class="col-md-2 control-label">员工姓名 *：</label>
				<div class="col-md-3 ">
					<input type="text" id="insuranceSaler"  name="insuranceSaler" class="form-control form-control-static" placeholder="请输入员工姓名">
				</div>             	
			</div>	
			
			<div class="form-group">
				<label class="col-md-2 control-label">入职职级序列 *：</label>
				<div class="col-md-3 ">
					<select class="form-control form-control-static" id="rankSequenceId" name="rankSequenceId">
                        <option value="">请选择职级序列</option>
             		</select>				
             	</div>
				
				<label class="col-md-2 control-label">入职职级 *：</label>
				<div class="col-md-3 ">
					<select class="form-control form-control-static" id="salesGradeId" name="salesGradeId">
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
				<label class="col-md-2 control-label">证件类型 *：</label>
				<div class="col-md-3 ">
					<select class="form-control form-control-static" id="cardType" name="cardType">
						<option value="">请选择证件类型</option>
             		</select>				
             	</div>			
			
				<label class="col-md-2 control-label">证件号码 *：</label>
				<div class="col-md-3 ">
					<input type="text" id="cardNo"  name="cardNo" class="form-control form-control-static" placeholder="请输入证件号码">			
             	</div>
			</div>
							
			<div class="form-group">
				<label class="col-md-2 control-label">手机号码 *：</label>
				<div class="col-md-3 ">
					<input type="text" id="mobile"  name="mobile" class="form-control form-control-static" placeholder="请输入手机号码">
				</div>

				<label class="col-md-2 control-label">入司时间 *：</label>
				<div class="col-md-3 ">
                   <input class="form-control form-control-static" id="joinDate" name="joinDate" value="" type="text" placeholder="请选择入司时间" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-md-2 control-label">性别 *：</label>
				<div class="col-md-3 ">
					<select class="form-control form-control-static" id="sex" name="sex">
						<option value="">请选择</option>
						<option value="0">女</option>
						<option value="1">男</option>
             		</select>				
             	</div>			
			
				<label class="col-md-2 control-label">出生日期 *：</label>
				<div class="col-md-3 ">
                   <input class="form-control form-control-static" id="birthday" name="birthday" value="" type="text" placeholder="请选择出生时间" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
             	</div>
			</div>
			
			<div class="form-group">
				<label class="col-md-2 control-label">银行名称 *：</label>
				<div class="col-md-3 ">
					<select class="form-control form-control-static" id="bankChannel" name="bankChannel">
						<option value="">请选择银行名称</option>
             		</select>				
             	</div>
			</div>

			<div class="form-group">
				<label class="col-md-2 control-label">开户行名称 *：</label>
				<div class="col-md-3 ">
					<input type="text" id="openBank"  name="openBank" class="form-control form-control-static" placeholder="开户行名称">
             	</div>

				<label class="col-md-2 control-label">银行卡号 *：</label>
				<div class="col-md-3 ">
					<input type="text" id="bankCardNo"  name="bankCardNo" class="form-control form-control-static" placeholder="请输入银行卡号">
             	</div>

			</div>
			
			<div class="form-group">
				<label class="col-md-2 control-label">国籍：</label>
				<div class="col-md-3 ">
					<select class="form-control form-control-static" id="country" name="country">
                        <option value="">请选择国籍</option>
             		</select>
             	</div>
             	
             	<label class="col-md-2 control-label">民族：</label>
             	<div class="col-md-3 ">	
             		<select class="form-control form-control-static" id="nation" name="nation">
                        <option value="">请选择民族</option>
             		</select>
             	</div>             	
             </div>
             
			<div class="form-group">
				<label class="col-md-2 control-label">政治面貌 *：</label>
				<div class="col-md-3 ">
					<select class="form-control form-control-static" id="politicalAppearance" name="politicalAppearance">
                        <option value="">请选择政治面貌</option>
             		</select>
             	</div>
             	
             	<label class="col-md-2 control-label">党派：</label>
             	<div class="col-md-3 ">	
             		<select class="form-control form-control-static" id="party" name="party">
                        <option value="">请选择党派</option>
             		</select>
             	</div>             	
             </div>             			
			
			<div class="form-group">
				<input type="hidden" id="areaCode"  name="areaCode" class="form-control form-control-static" placeholder="请输入区域编码">
				<label class="col-md-2 control-label">所在省份 *：</label>
				<div class="col-md-3 ">
					<select class="form-control form-control-static" id="sheng" name="sheng">
                        <option value="">请选择省份</option>
             		</select>
             	</div>
             	
             	<label class="col-md-2 control-label">所在城市 *：</label>
             	<div class="col-md-3 ">	
             		<select class="form-control form-control-static" id="shi" name="shi">
                        <option value="">请选择城市</option>
             		</select>
             	</div>             	
             </div>
             
            <div class="form-group">            
            	<label class="col-md-2 control-label">所在区县 *：</label>
             	<div class="col-md-3 ">		
             		<select class="form-control form-control-static" id="qu" name="qu">
                        <option value="">请选择区县</option>
             		</select>
				</div>
				
				<label class="col-md-2 control-label">家庭住址：</label>
				<div class="col-md-3 ">
					<input type="text" id="address"  name="address" class="form-control form-control-static" placeholder="请输入家庭住址">
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-md-2 control-label">邮编：</label>
				<div class="col-md-3 ">
					<input type="text" id="postCode"  name="postCode" class="form-control form-control-static" placeholder="请输入邮编">
				</div> 			
			
				<label class="col-md-2 control-label">固定电话：</label>
				<div class="col-md-3 ">
					<input type="text" id="cellPhone"  name="cellPhone" class="form-control form-control-static" placeholder="请输入固定电话">
				</div>            	
			</div>	
			
			<div class="form-group">			
				<label class="col-md-2 control-label">户口所在地：</label>
				<div class="col-md-3 ">
					<input type="text" id="householdRegPlace"  name="householdRegPlace" class="form-control form-control-static" placeholder="请输入户口所在地">
				</div> 			
			
				<label class="col-md-2 control-label">档案所在地：</label>
				<div class="col-md-3 ">
					<input type="text" id="archivesPreservationPlace"  name="archivesPreservationPlace" class="form-control form-control-static" placeholder="请输入档案所在地">
				</div>            	
			</div>																				

			<div class="form-group">
				<label class="col-md-2 control-label">籍贯：</label>
				<div class="col-md-3 ">
					<input type="text" id="nativePlace"  name="nativePlace" class="form-control form-control-static" placeholder="请输入籍贯">
				</div>  			
			
				<%--<label class="col-md-2 control-label">兼职/全职：</label>
				<div class="col-md-3 ">
					<select class="form-control form-control-static" id="dutyType" name="dutyType">
						<option value="">请选择</option>
						<option value="0">兼职</option>
						<option value="1">全职</option>
             		</select>				
             	</div>--%>
			</div>
			
			<div class="form-group">	
				<label class="col-md-2 control-label">邮箱：</label>
				<div class="col-md-3 ">
                   <input class="form-control form-control-static" id="email" name="email" value="" type="text" placeholder="请输入邮箱" />
             	</div>
             						
				<label class="col-md-2 control-label">QQ号码：</label>
				<div class="col-md-3 ">
					<input type="text" id="qqNumber"  name="qqNumber" class="form-control form-control-static" placeholder="请输入QQ号">
				</div> 			            	
			</div>	
			
			<div class="form-group">
				<label class="col-md-2 control-label">微信号码：</label>
				<div class="col-md-3 ">
					<input type="text" id="wechatNumber"  name="wechatNumber" class="form-control form-control-static" placeholder="请输入微信号码">
				</div>			
			
				<label class="col-md-2 control-label">最高学历 *：</label>
				<div class="col-md-3 ">
					<select class="form-control form-control-static" id="educationalBg" name="educationalBg">
                        <option value="">请选择学历</option>
             		</select>
             	</div>             	
             </div>
             
             <div class="form-group">             	
             	<label class="col-md-2 control-label">最高学位：</label>
             	<div class="col-md-3 ">	
             		<select class="form-control form-control-static" id="academicDegree" name="academicDegree">
                        <option value="">请选择学位</option>
             		</select>
             	</div>	
             	
 				<label class="col-md-2 control-label">外语水平：</label>
				<div class="col-md-3 ">
					<input type="text" id="foreignLanguageAbility"  name="foreignLanguageAbility" class="form-control form-control-static" placeholder="请输入外语水平">
				</div>	            	
             </div>						

			<div class="form-group">
				<label class="col-md-2 control-label">婚姻状况：</label>
				<div class="col-md-3 ">
					<select class="form-control form-control-static" id="maritalStatus" name="maritalStatus">
						<option value="">请选择</option>
						<option value="0">未婚</option>
						<option value="1">已婚</option>
             		</select>				
             	</div>			
			
				<label class="col-md-2 control-label">结婚日期：</label>
				<div class="col-md-3 ">
                   <input class="form-control form-control-static" id="marriageDate" name="marriageDate" value="" type="text" placeholder="请选择结婚时间" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
             	</div>
			</div>
			
          	<div class="form-group">    	
 				<label class="col-md-2 control-label">上家公司名称：</label>
				<div class="col-md-3 ">
					<input type="text" id="preCompany"  name="preCompany" class="form-control form-control-static" placeholder="请输入上家公司名称">
				</div>	
				
 				<label class="col-md-2 control-label">上家公司职业：</label>
				<div class="col-md-3 ">
					<input type="text" id="preOccupation"  name="preOccupation" class="form-control form-control-static" placeholder="请输入上家公司职业">
				</div>					            	
             </div>				

             <div class="form-group">             	
             	<label class="col-md-2 control-label">是否有待业证：</label>
             	<div class="col-md-3 ">	
             		<select class="form-control form-control-static" id="leavingCertificate" name="leavingCertificate">
						<option value="0">否</option>
						<option value="1">是</option>
             		</select>
             	</div>	
             	
             	<label class="col-md-2 control-label">是否有工作室：</label>
             	<div class="col-md-3 ">	
             		<select class="form-control form-control-static" id="havingWorkGroup" name="havingWorkGroup">
						<option value="0">否</option>
						<option value="1">是</option>
             		</select>
             	</div> 
             </div>

			<div class="form-group">
				<label class="col-md-2 control-label">工作室联系电话：</label>
				<div class="col-md-3 ">
                   <input class="form-control form-control-static" id="workGroupPhone" name="workGroupPhone" value="" type="text" placeholder="请输入工作室联系电话" />
             	</div>			
			
				<label class="col-md-2 control-label">是否内部推荐：</label>
				<div class="col-md-3 ">
					<select class="form-control form-control-static" id="internalReferral" name="internalReferral">
						<option value="">请选择</option>
						<option value="0">是</option>
						<option value="1">否</option>
             		</select>				
             	</div>			
			</div>
			
			<div class="form-group">
				<label class="col-md-2 control-label">执业证号：</label>
				<div class="col-md-3 ">
                   <input class="form-control form-control-static" id="certificateNumber" name="certificateNumber" value="" type="text" placeholder="请输入执业证号" />
             	</div>			
			
				<label class="col-md-2 control-label">执业证状态：</label>
				<div class="col-md-3 ">
					<select class="form-control form-control-static" id="certificateStatus" name="certificateStatus">
						<option value="">请选择</option>
						<option value="0">有效</option>
						<option value="1">失效</option>
             		</select>				
             	</div>			
			</div>	
			
			<div class="form-group">
				<label class="col-md-2 control-label">执业证有效期起：</label>
				<div class="col-md-3 ">
                   <input class="form-control form-control-static" id="certificateStartDate" name="certificateStartDate" value="" type="text" placeholder="请选择执业证有效期起" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
             	</div>			
			
				<label class="col-md-2 control-label">执业证有效期止：</label>
				<div class="col-md-3 ">
                   <input class="form-control form-control-static" id="certificateEndDate" name="certificateEndDate" value="" type="text" placeholder="请选择执业证有效期止" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
             	</div>
			</div>	
			
			<div class="form-group">
				<label class="col-md-2 control-label">兴趣爱好：</label>
				<div class="col-md-3 ">
					<textarea  style="width:655px;" rows="4" id="interest" name="interest" class="form-control form-control-static" placeholder="请输入兴趣爱好"></textarea>
				</div>
			</div>	
			
			<div class="form-group">
				<label class="col-md-2 control-label">备注：</label>
				<div class="col-md-3 ">
					<textarea  style="width:655px;" rows="4" id="remark" name="remark" class="form-control form-control-static" placeholder="请输入备注"></textarea>
				</div>
			</div>	

			
			<input type="hidden" id="titleList" name="titleList" value="" /><!-- 职称、证书 -->
			<input type="hidden" id="eduJobsList" name="eduJobsList" value="" /><!-- 教育、工作、培训 -->
			<input type="hidden" id="relativeList" name="relativeList" value="" /><!-- 家庭成员、紧急联系人、司外担保人 -->
			<input type="hidden" id="contractList" name="contractList" value="" /><!-- 合同 -->
			<input type="hidden" id="zjjtList" name="zjjtList" value="" /><!-- 总监津贴 -->	
					
			<div class="form-group">
				<label class="col-md-2 control-label">职称：
				（<button id="zc_add" type="button" onclick="InsuranceSalesInfo.openAddModal('zcAddDlg')" class="btn btn-primary">新增</button>）</label>
				<div class="col-md-3 " style="width:880px;">
					<div style="overflow:auto;width:850px;">
					<table id="zc_list" class="table table-hover table-striped table-condensed table-bordered" style="width:1200px;font-size:13px;">
						<tr>
							<td style="width:120px;text-align:center;">操作</td>
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
				（<button id="zhengshu_add" type="button" onclick="InsuranceSalesInfo.openAddModal('zhengshuAddDlg')" class="btn btn-primary">新增</button>）</label>
				<div class="col-md-3 " style="width:880px;">
					<div style="overflow:auto;width:850px;">
					<table id="zs_list" class="table table-hover table-striped table-condensed table-bordered" style="width:1200px;font-size:13px;">
						<tr>
							<td style="width:120px;text-align:center;">操作</td>
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
				（<button id="jyjl_add" type="button" onclick="InsuranceSalesInfo.openAddModal('jyjlAddDlg')" class="btn btn-primary">新增</button>）</label>
				<div class="col-md-3 " style="width:880px;">
					<div style="overflow:auto;width:850px;">
					<table id="jyjl_list" class="table table-hover table-striped table-condensed table-bordered" style="width:1100px;font-size:13px;">
						<tr>
							<td style="width:120px;text-align:center;">操作</td>
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
				（<button id="gzjl_add" type="button" onclick="InsuranceSalesInfo.openAddModal('gzjlAddDlg')" class="btn btn-primary">新增</button>）</label>
				<div class="col-md-3 " style="width:880px;">
					<div style="overflow:auto;width:850px;">
					<table id="gzjl_list" class="table table-hover table-striped table-condensed table-bordered" style="width:980px;font-size:13px;">
						<tr>
							<td style="width:120px;text-align:center;">操作</td>
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
				（<button id="pxjl_add" type="button" onclick="InsuranceSalesInfo.openAddModal('pxjlAddDlg')" class="btn btn-primary">新增</button>）</label>
				<div class="col-md-3 " style="width:880px;">
					<div style="overflow:auto;width:850px;">
					<table id="pxjl_list" class="table table-hover table-striped table-condensed table-bordered" style="width:980px;font-size:13px;">
						<tr>
							<td style="width:120px;text-align:center;">操作</td>
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
				（<button id="jtcy_add" type="button" onclick="InsuranceSalesInfo.openAddModal('jtcyAddDlg')" class="btn btn-primary">新增</button>）</label>
				<div class="col-md-3 " style="width:880px;">
					<div style="overflow:auto;width:850px;">
					<table id="jtcy_list" class="table table-hover table-striped table-condensed table-bordered" style="width:980px;font-size:13px;">
						<tr>
							<td style="width:120px;text-align:center;">操作</td>
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
				（<button id="jjlxr_add" type="button" onclick="InsuranceSalesInfo.openAddModal('jjlxrAddDlg')" class="btn btn-primary">新增</button>）</label>
				<div class="col-md-3 " style="width:880px;">
					<div style="overflow:auto;width:850px;">
					<table id="jjlxr_list" class="table table-hover table-striped table-condensed table-bordered" style="width:1200px;font-size:13px;">
						<tr>
							<td style="width:120px;text-align:center;">操作</td>
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
				（<button id="swdbr_add" type="button" onclick="InsuranceSalesInfo.openAddModal('swdbrAddDlg')" class="btn btn-primary">新增</button>）</label>
				<div class="col-md-3 " style="width:880px;">
					<div style="overflow:auto;width:850px;">
					<table id="swdbr_list" class="table table-hover table-striped table-condensed table-bordered" style="width:920px;font-size:13px;">
						<tr>
							<td style="width:120px;text-align:center;">操作</td>
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
				（<button id="ht_add" type="button" onclick="InsuranceSalesInfo.openAddModal('htAddDlg')" class="btn btn-primary">新增</button>）</label>
				<div class="col-md-3 " style="width:880px;">
					<div style="overflow:auto;width:850px;">
					<table id="ht_list" class="table table-hover table-striped table-condensed table-bordered" style="width:1180px;font-size:13px;">
						<tr>
							<td style="width:120px;text-align:center;">操作</td>
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
               <button id="saveButton" type="button" onclick="InsuranceSalesInfo.add()" class="btn btn-primary">保存</button>
            </div>
            </form>
            </div>
            
            
<!--职称------------------------------------------------------------------------------------------------------------------------------------------------------------ -->

<!-- 模态框（Modal） -->
<!-- 添加 -->
<div id="zcAddDlg" class="modal fade" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false" aria-labelledby="zcadd_myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="zcadd_myModalLabel">新增职称</h4>
            </div>
            <div class="container">
			<form class="form-horizontal" id="zc_addForm"  method="post">
						
			<div class="form-group">
			<label class="col-md-2 control-label">职称名称：</label>
			<div class="col-md-3">
			<input type="text" id="zc_title" name="zc_title" class="form-control form-control-static" placeholder="请输入职称名称">							
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">发证机关：</label>
			<div class="col-md-3 ">
			<input type="text" id="zc_awardOrg" name="zc_awardOrg" class="form-control form-control-static" placeholder="请输入发证机关">
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">获取日期：</label>
			<div class="col-md-3">
            <input class="form-control form-control-static" id="zc_gotDate" name="zc_gotDate" value="" type="text" placeholder="请选择获得时间" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">生效日期：</label>
			<div class="col-md-3 ">
            <input class="form-control form-control-static" id="zc_effectiveDate" name="zc_effectiveDate" value="" type="text" placeholder="请选择生效时间" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
			</div>
			</div>	
			
			<div class="form-group">
			<label class="col-md-2 control-label">失效日期：</label>
			<div class="col-md-3 ">
            <input class="form-control form-control-static" id="zc_invalidDate" name="zc_invalidDate" value="" type="text" placeholder="请选择失效时间" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
			</div>
			</div>						
			
			<div class="form-group">
			<label class="col-md-2 control-label">备注：</label>
			<div class="col-md-3 ">
			<input type="text" id="zc_remark" name="zc_remark" class="form-control form-control-static" placeholder="请输入备注">
			</div>
			</div>
            <div class="modal-footer col-md-6">
            <!--用来清空表单数据-->
           	   <input type="reset" name="reset" style="display: none;" />
               <button type="button" class="btn btn-default" onclick="InsuranceSalesInfo.closeDlg()">关闭</button>
               <button id="zc_saveButton" type="button" onclick="InsuranceSalesInfo.addZc('zc_list')" class="btn btn-primary">保存</button>
            </div>
            </form>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>


<!-- 模态框（Modal） -->
<!-- 修改 -->
<div id="zcMydlg" class="modal fade"  tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false" aria-labelledby="zcup_myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="zcup_myModalLabel">修改职称</h4>
            </div>
            <div class="container">
			<form class="form-horizontal" id="zc_updateForm"  method="post">
			<input type="hidden" id="zc_index_update" name="zc_index_update" value="" class="form-control form-control-static">
			<div class="form-group">
			<label class="col-md-2 control-label">职称名称：</label>
			<div class="col-md-3">
			<input type="text" id="zc_update_title" name="zc_update_title" class="form-control form-control-static" placeholder="请输入职称名称">							
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">发证机关：</label>
			<div class="col-md-3 ">
			<input type="text" id="zc_update_awardOrg" name="zc_update_awardOrg" class="form-control form-control-static" placeholder="请输入发证机关">
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">获取日期：</label>
			<div class="col-md-3">
            <input class="form-control form-control-static" id="zc_update_gotDate" name="zc_update_gotDate" value="" type="text" placeholder="请选择获得时间" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">生效日期：</label>
			<div class="col-md-3 ">
            <input class="form-control form-control-static" id="zc_update_effectiveDate" name="zc_effectiveDate" value="" type="text" placeholder="请选择生效时间" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
			</div>
			</div>	
			
			<div class="form-group">
			<label class="col-md-2 control-label">失效日期：</label>
			<div class="col-md-3 ">
            <input class="form-control form-control-static" id="zc_update_invalidDate" name="zc_update_invalidDate" value="" type="text" placeholder="请选择失效时间" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
			</div>
			</div>						
			
			<div class="form-group">
			<label class="col-md-2 control-label">备注：</label>
			<div class="col-md-3 ">
			<input type="text" id="zc_update_remark" name="zc_update_remark" class="form-control form-control-static" placeholder="请输入职级名称">
			</div>
			</div>

            <div class="modal-footer col-md-6">
            <!--用来清空表单数据-->
            <input type="reset" name="reset" style="display: none;" />
               <button type="button" class="btn btn-default" onclick="InsuranceSalesInfo.closeDlg()">关闭</button>
               <button type="button" id="zc_updateButton" onclick="InsuranceSalesInfo.updateZcSave()" class="btn btn-primary">保存</button>
            </div>
            </form>
            </div>
        </div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>

<!--证书------------------------------------------------------------------------------------------------------------------------------------------------------------ -->

<!-- 模态框（Modal） -->
<!-- 添加 -->
<div id="zhengshuAddDlg" class="modal fade" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false" aria-labelledby="zsadd_myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="zsadd_myModalLabel">新增证书</h4>
            </div>
            <div class="container">
			<form class="form-horizontal" id="zs_addForm"  method="post">
						
			<div class="form-group">
			<label class="col-md-2 control-label">证书名称：</label>
			<div class="col-md-3">
			<input type="text" id="zs_title" name="zs_title" class="form-control form-control-static" placeholder="请输入证书名称">							
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">证书编号：</label>
			<div class="col-md-3">
			<input type="text" id="zs_certificateNo" name="zs_certificateNo" class="form-control form-control-static" placeholder="请输入证书编号">							
			</div>
			</div>			
			
			<div class="form-group">
			<label class="col-md-2 control-label">发证机构：</label>
			<div class="col-md-3 ">
			<input type="text" id="zs_awardOrg" name="zs_awardOrg" class="form-control form-control-static" placeholder="请输入发证机构">
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">发证日期：</label>
			<div class="col-md-3">
            <input class="form-control form-control-static" id="zs_gotDate" name="zs_gotDate" value="" type="text" placeholder="请选择发证时间" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">失效日期：</label>
			<div class="col-md-3 ">
            <input class="form-control form-control-static" id="zs_invalidDate" name="zs_invalidDate" value="" type="text" placeholder="请选择失效时间" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
			</div>
			</div>						
			
			<div class="form-group">
			<label class="col-md-2 control-label">有效状态：</label>
			<div class="col-md-3 ">
					<input type="radio" id="zs_status_yes" name="zs_status" value="0" checked="checked">有效&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="radio" id="zs_status_no" name="zs_status" value="1">失效
			</div>
			</div>
            <div class="modal-footer col-md-6">
            <!--用来清空表单数据-->
           	   <input type="reset" name="reset" style="display: none;" />
               <button type="button" class="btn btn-default" onclick="InsuranceSalesInfo.closeDlg()">关闭</button>
               <button id="zs_saveButton" type="button" onclick="InsuranceSalesInfo.addZs('zs_list')" class="btn btn-primary">保存</button>
            </div>
            </form>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>


<!-- 模态框（Modal） -->
<!-- 修改 -->
<div id="zhengshuMydlg" class="modal fade"  tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false" aria-labelledby="zsup_myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="zsup_myModalLabel">修改证书</h4>
            </div>
            <div class="container">
			<form class="form-horizontal" id="zc_updateForm"  method="post">
			<input type="hidden" id="zs_index_update" name="zs_index_update" value="" class="form-control form-control-static">
			<div class="form-group">
			<label class="col-md-2 control-label">职称名称：</label>
			<div class="col-md-3">
			<input type="text" id="zs_update_title" name="zs_update_title" class="form-control form-control-static" placeholder="请输入职称名称">							
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">证书编号：</label>
			<div class="col-md-3 ">
			<input type="text" id="zs_update_certificateNo" name="zs_update_certificateNo" class="form-control form-control-static" placeholder="请输入证书编号">
			</div>
			</div>			
			
			<div class="form-group">
			<label class="col-md-2 control-label">发证机关：</label>
			<div class="col-md-3 ">
			<input type="text" id="zs_update_awardOrg" name="zs_update_awardOrg" class="form-control form-control-static" placeholder="请输入发证机关">
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">发证日期：</label>
			<div class="col-md-3">
            <input class="form-control form-control-static" id="zs_update_gotDate" name="zs_update_gotDate" value="" type="text" placeholder="请选择获得时间" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">失效日期：</label>
			<div class="col-md-3 ">
            <input class="form-control form-control-static" id="zs_update_invalidDate" name="zs_update_invalidDate" value="" type="text" placeholder="请选择失效时间" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
			</div>
			</div>						

			<div class="form-group">
			<label class="col-md-2 control-label">有效状态：</label>
			<div class="col-md-3 ">
					<input type="radio" id="zs_update_status_yes" name="zs_update_status" value="0" checked="checked">有效&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="radio" id="zs_update_status_no" name="zs_update_status" value="1">失效
			</div>
			</div>
            <div class="modal-footer col-md-6">
            <!--用来清空表单数据-->
            <input type="reset" name="reset" style="display: none;" />
               <button type="button" class="btn btn-default" onclick="InsuranceSalesInfo.closeDlg()">关闭</button>
               <button type="button" id="zc_updateButton" onclick="InsuranceSalesInfo.updateZsSave()" class="btn btn-primary">保存</button>
            </div>
            </form>
            </div>
        </div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>

<!--教育经历------------------------------------------------------------------------------------------------------------------------------------------------------------ -->

<!-- 模态框（Modal） -->
<!-- 添加 -->
<div id="jyjlAddDlg" class="modal fade" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">新增教育经历</h4>
            </div>
            <div class="container">
			<form class="form-horizontal" id="jyjl_addForm"  method="post">
			<div class="form-group">
			<label class="col-md-2 control-label">学校名称：</label>
			<div class="col-md-3 ">
			<input type="text" id="jyjl_eduName"  name="jyjl_eduName" class="form-control form-control-static" placeholder="请输入学校名称">
			</div>
			</div>		

			<div class="form-group">
			<label class="col-md-2 control-label">开始日期：</label>
			<div class="col-md-3">
            <input class="form-control form-control-static" id="jyjl_startDate" name="jyjl_startDate" value="" type="text" placeholder="请选择开始日期" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">结束日期：</label>
			<div class="col-md-3 ">
            <input class="form-control form-control-static" id="jyjl_endDate" name="jyjl_endDate" value="" type="text" placeholder="请选择结束时间" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">学历：</label>
			<div class="col-md-3 ">
			<input type="text" id="jyjl_education"  name="jyjl_education" class="form-control form-control-static" placeholder="请输入学历">
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">学位：</label>
			<div class="col-md-3 ">
			<input type="text" id="jyjl_academicDegree"  name="jyjl_academicDegree" class="form-control form-control-static" placeholder="请输入学位">
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">备注：</label>
			<div class="col-md-3 ">
			<input type="text" id="jyjl_remark"  name="jyjl_remark" class="form-control form-control-static" placeholder="请输入备注">
			</div>
			</div>			
            <div class="modal-footer col-md-6">
            <!--用来清空表单数据-->
            <input type="reset" name="reset" style="display: none;" />
                <button type="button" class="btn btn-default" onclick="InsuranceSalesInfo.closeDlg()">关闭</button>
               <button id="saveButton" type="button" onclick="InsuranceSalesInfo.addJyjl('jyjl_list')" class="btn btn-primary">保存</button>
            </div>
            </form>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>


<!-- 模态框（Modal） -->
<!-- 修改 -->
<div id="jyjlMydlg" class="modal fade"  tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">修改教育经历</h4>
            </div>
            <div class="container">
			<form class="form-horizontal" id="jyjl_updateForm"  method="post">
			<input type="hidden" id="jyjl_index_update" name="jyjl_index_update" value="" class="form-control form-control-static">
			<div class="form-group">
			<label class="col-md-2 control-label">学校名称：</label>
			<div class="col-md-3 ">
			<input type="text" id="jyjl_update_eduName"  name="jyjl_update_eduName" class="form-control form-control-static" placeholder="请输入学校名称">
			</div>
			</div>		

			<div class="form-group">
			<label class="col-md-2 control-label">开始日期：</label>
			<div class="col-md-3">
            <input class="form-control form-control-static" id="jyjl_update_startDate" name="jyjl_update_startDate" value="" type="text" placeholder="请选择开始日期" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">结束日期：</label>
			<div class="col-md-3 ">
            <input class="form-control form-control-static" id="jyjl_update_endDate" name="jyjl_update_endDate" value="" type="text" placeholder="请选择结束时间" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">学历：</label>
			<div class="col-md-3 ">
			<input type="text" id="jyjl_update_education"  name="jyjl_update_education" class="form-control form-control-static" placeholder="请输入学历">
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">学位：</label>
			<div class="col-md-3 ">
			<input type="text" id="jyjl_update_academicDegree"  name="jyjl_update_academicDegree" class="form-control form-control-static" placeholder="请输入学位">
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">备注：</label>
			<div class="col-md-3 ">
			<input type="text" id="jyjl_update_remark"  name="jyjl_update_remark" class="form-control form-control-static" placeholder="请输入备注">
			</div>
			</div>
            <div class="modal-footer col-md-6">
            <!--用来清空表单数据-->
            <input type="reset" name="reset" style="display: none;" />
                <button type="button" class="btn btn-default" onclick="InsuranceSalesInfo.closeDlg()">关闭</button>
               <button type="button" id="updateButton" onclick="InsuranceSalesInfo.updateJyjlSave()" class="btn btn-primary">保存</button>
            </div>
            </form>
            </div>
        </div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>

<!--工作经历------------------------------------------------------------------------------------------------------------------------------------------------------------ -->

<!-- 模态框（Modal） -->
<!-- 添加 -->
<div id="gzjlAddDlg" class="modal fade" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">新增工作经历</h4>
            </div>
            <div class="container">
			<form class="form-horizontal" id="gzjl_addForm"  method="post">
			<div class="form-group">
			<label class="col-md-2 control-label">单位名称：</label>
			<div class="col-md-3 ">
			<input type="text" id="gzjl_eduName"  name="gzjl_eduName" class="form-control form-control-static" placeholder="请输入单位名称">
			</div>
			</div>		

			<div class="form-group">
			<label class="col-md-2 control-label">开始日期：</label>
			<div class="col-md-3">
            <input class="form-control form-control-static" id="gzjl_startDate" name="gzjl_startDate" value="" type="text" placeholder="请选择开始日期" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">结束日期：</label>
			<div class="col-md-3 ">
            <input class="form-control form-control-static" id="gzjl_endDate" name="gzjl_endDate" value="" type="text" placeholder="请选择结束时间" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">职位：</label>
			<div class="col-md-3 ">
			<input type="text" id="gzjl_position"  name="gzjl_position" class="form-control form-control-static" placeholder="请输入职位">
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">备注：</label>
			<div class="col-md-3 ">
			<input type="text" id="gzjl_remark"  name="gzjl_remark" class="form-control form-control-static" placeholder="请输入备注">
			</div>
			</div>			
            <div class="modal-footer col-md-6">
            <!--用来清空表单数据-->
            <input type="reset" name="reset" style="display: none;" />
                <button type="button" class="btn btn-default" onclick="InsuranceSalesInfo.closeDlg()">关闭</button>
               <button id="saveButton" type="button" onclick="InsuranceSalesInfo.addGzjl('gzjl_list')" class="btn btn-primary">保存</button>
            </div>
            </form>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>


<!-- 模态框（Modal） -->
<!-- 修改 -->
<div id="gzjlMydlg" class="modal fade"  tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">修改工作经历</h4>
            </div>
            <div class="container">
			<form class="form-horizontal" id="gzjl_updateForm"  method="post">
			<input type="hidden" id="gzjl_index_update" name="gzjl_index_update" value="" class="form-control form-control-static">
			<div class="form-group">
			<label class="col-md-2 control-label">单位名称：</label>
			<div class="col-md-3 ">
			<input type="text" id="gzjl_update_eduName"  name="gzjl_update_eduName" class="form-control form-control-static" placeholder="请输入单位名称">
			</div>
			</div>		

			<div class="form-group">
			<label class="col-md-2 control-label">开始日期：</label>
			<div class="col-md-3">
            <input class="form-control form-control-static" id="gzjl_update_startDate" name="gzjl_update_startDate" value="" type="text" placeholder="请选择开始日期" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">结束日期：</label>
			<div class="col-md-3 ">
            <input class="form-control form-control-static" id="gzjl_update_endDate" name="gzjl_update_endDate" value="" type="text" placeholder="请选择结束时间" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">职位：</label>
			<div class="col-md-3 ">
			<input type="text" id="gzjl_update_position"  name="gzjl_update_position" class="form-control form-control-static" placeholder="请输入职位">
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">备注：</label>
			<div class="col-md-3 ">
			<input type="text" id="gzjl_update_remark"  name="gzjl_update_remark" class="form-control form-control-static" placeholder="请输入备注">
			</div>
			</div>
            <div class="modal-footer col-md-6">
            <!--用来清空表单数据-->
            <input type="reset" name="reset" style="display: none;" />
                <button type="button" class="btn btn-default" onclick="InsuranceSalesInfo.closeDlg()">关闭</button>
               <button type="button" id="updateButton" onclick="InsuranceSalesInfo.updateGzjlSave()" class="btn btn-primary">保存</button>
            </div>
            </form>
            </div>
        </div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>

<!--培训经历------------------------------------------------------------------------------------------------------------------------------------------------------------ -->

<!-- 模态框（Modal） -->
<!-- 添加 -->
<div id="pxjlAddDlg" class="modal fade" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">新增培训经历</h4>
            </div>
            <div class="container">
			<form class="form-horizontal" id="pxjl_addForm"  method="post">
			<div class="form-group">
			<label class="col-md-2 control-label">课程名称：</label>
			<div class="col-md-3 ">
			<input type="text" id="pxjl_eduName"  name="pxjl_eduName" class="form-control form-control-static" placeholder="请输入课程名称">
			</div>
			</div>		

			<div class="form-group">
			<label class="col-md-2 control-label">开始日期：</label>
			<div class="col-md-3">
            <input class="form-control form-control-static" id="pxjl_startDate" name="pxjl_startDate" value="" type="text" placeholder="请选择开始日期" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">结束日期：</label>
			<div class="col-md-3 ">
            <input class="form-control form-control-static" id="pxjl_endDate" name="pxjl_endDate" value="" type="text" placeholder="请选择结束时间" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">成绩：</label>
			<div class="col-md-3 ">
			<input type="text" id="pxjl_achievement"  name="pxjl_achievement" class="form-control form-control-static" placeholder="请输入成绩">
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">备注：</label>
			<div class="col-md-3 ">
			<input type="text" id="pxjl_remark"  name="pxjl_remark" class="form-control form-control-static" placeholder="请输入备注">
			</div>
			</div>			
            <div class="modal-footer col-md-6">
            <!--用来清空表单数据-->
            <input type="reset" name="reset" style="display: none;" />
                <button type="button" class="btn btn-default" onclick="InsuranceSalesInfo.closeDlg()">关闭</button>
               <button id="saveButton" type="button" onclick="InsuranceSalesInfo.addPxjl('pxjl_list')" class="btn btn-primary">保存</button>
            </div>
            </form>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>


<!-- 模态框（Modal） -->
<!-- 修改 -->
<div id="pxjlMydlg" class="modal fade"  tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">修改培训经历</h4>
            </div>
            <div class="container">
			<form class="form-horizontal" id="pxjl_updateForm"  method="post">
			<input type="hidden" id="pxjl_index_update" name="pxjl_index_update" value="" class="form-control form-control-static">
			<div class="form-group">
			<label class="col-md-2 control-label">课程名称：</label>
			<div class="col-md-3 ">
			<input type="text" id="pxjl_update_eduName"  name="pxjl_update_eduName" class="form-control form-control-static" placeholder="请输入课程名称">
			</div>
			</div>		

			<div class="form-group">
			<label class="col-md-2 control-label">开始日期：</label>
			<div class="col-md-3">
            <input class="form-control form-control-static" id="pxjl_update_startDate" name="pxjl_update_startDate" value="" type="text" placeholder="请选择开始日期" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">结束日期：</label>
			<div class="col-md-3 ">
            <input class="form-control form-control-static" id="pxjl_update_endDate" name="pxjl_update_endDate" value="" type="text" placeholder="请选择结束时间" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">成绩：</label>
			<div class="col-md-3 ">
			<input type="text" id="pxjl_update_achievement"  name="pxjl_update_achievement" class="form-control form-control-static" placeholder="请输入成绩">
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">备注：</label>
			<div class="col-md-3 ">
			<input type="text" id="pxjl_update_remark"  name="pxjl_update_remark" class="form-control form-control-static" placeholder="请输入备注">
			</div>
			</div>
            <div class="modal-footer col-md-6">
            <!--用来清空表单数据-->
            <input type="reset" name="reset" style="display: none;" />
                <button type="button" class="btn btn-default" onclick="InsuranceSalesInfo.closeDlg()">关闭</button>
               <button type="button" id="updateButton" onclick="InsuranceSalesInfo.updatePxjlSave()" class="btn btn-primary">保存</button>
            </div>
            </form>
            </div>
        </div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>

<!--家庭成员------------------------------------------------------------------------------------------------------------------------------------------------------------ -->

<!-- 模态框（Modal） -->
<!-- 添加 -->
<div id="jtcyAddDlg" class="modal fade" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">添加家庭成员</h4>
            </div>
            <div class="container">
			<form class="form-horizontal" id="jtcy_addForm"  method="post">
						
			<div class="form-group">
			<label class="col-md-2 control-label">成员姓名：</label>
			<div class="col-md-3">
			<input type="text" id="jtcy_personName"  name="jtcy_personName" class="form-control form-control-static" placeholder="请输入成员姓名">
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">联系电话：</label>
			<div class="col-md-3 ">
			<input type="text" id="jtcy_shipCellPhone"  name="jtcy_shipCellPhone" class="form-control form-control-static" placeholder="请输入联系电话">
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">关系：</label>
			<div class="col-md-3 ">
			<input type="text" id="jtcy_relationShip"  name="jtcy_relationShip" class="form-control form-control-static" placeholder="请输入关系">
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">职业：</label>
			<div class="col-md-3 ">
			<input type="text" id="jtcy_shipOccupation"  name="jtcy_shipOccupation" class="form-control form-control-static" placeholder="请输入职业">
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">备注：</label>
			<div class="col-md-3 ">
			<input type="text" id="jtcy_remark"  name="jtcy_remark" class="form-control form-control-static" placeholder="请输入备注">
			</div>
			</div>						
            <div class="modal-footer col-md-6">
            <!--用来清空表单数据-->
            <input type="reset" name="reset" style="display: none;" />
                <button type="button" class="btn btn-default" onclick="InsuranceSalesInfo.closeDlg()">关闭</button>
               <button id="saveButton" type="button" onclick="InsuranceSalesInfo.addJtcy('jtcy_list')" class="btn btn-primary">保存</button>
            </div>
            </form>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>


<!-- 模态框（Modal） -->
<!-- 修改 -->
<div id="jtcyMydlg" class="modal fade"  tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">修改家庭成员</h4>
            </div>
            <div class="container">
			<form class="form-horizontal" id="jtcy_updateForm"  method="post">
			<input type="hidden" id="jtcy_index_update" name="jtcy_index_update" value="" class="form-control form-control-static">			
			<div class="form-group">
			<label class="col-md-2 control-label">成员姓名：</label>
			<div class="col-md-3">
			<input type="text" id="jtcy_update_personName"  name="jtcy_update_personName" class="form-control form-control-static" placeholder="请输入成员姓名">
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">联系电话：</label>
			<div class="col-md-3 ">
			<input type="text" id="jtcy_update_shipCellPhone"  name="jtcy_update_shipCellPhone" class="form-control form-control-static" placeholder="请输入联系电话">
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">关系：</label>
			<div class="col-md-3 ">
			<input type="text" id="jtcy_update_relationShip"  name="jtcy_update_relationShip" class="form-control form-control-static" placeholder="请输入关系">
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">职业：</label>
			<div class="col-md-3 ">
			<input type="text" id="jtcy_update_shipOccupation"  name="jtcy_update_shipOccupation" class="form-control form-control-static" placeholder="请输入执业">
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">备注：</label>
			<div class="col-md-3 ">
			<input type="text" id="jtcy_update_remark"  name="jtcy_update_remark" class="form-control form-control-static" placeholder="请输入备注">
			</div>
			</div>				
            <div class="modal-footer col-md-6">
            <!--用来清空表单数据-->
            <input type="reset" name="reset" style="display: none;" />
                <button type="button" class="btn btn-default" onclick="InsuranceSalesInfo.closeDlg()">关闭</button>
               <button type="button" id="updateButton" onclick="InsuranceSalesInfo.updateJtcySave()" class="btn btn-primary">保存</button>
            </div>
            </form>
            </div>
        </div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>

<!--紧急联系人------------------------------------------------------------------------------------------------------------------------------------------------------------ -->

<!-- 模态框（Modal） -->
<!-- 添加 -->
<div id="jjlxrAddDlg" class="modal fade" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">添加紧急联系人</h4>
            </div>
            <div class="container">
			<form class="form-horizontal" id="jjlxr_addForm"  method="post">
						
			<div class="form-group">
			<label class="col-md-2 control-label">联系人姓名：</label>
			<div class="col-md-3">
			<input type="text" id="jjlxr_personName"  name="jjlxr_personName" class="form-control form-control-static" placeholder="请输入联系人姓名">
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">联系电话：</label>
			<div class="col-md-3 ">
			<input type="text" id="jjlxr_shipCellPhone"  name="jjlxr_shipCellPhone" class="form-control form-control-static" placeholder="请输入联系电话">
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">关系：</label>
			<div class="col-md-3 ">
			<input type="text" id="jjlxr_relationShip"  name="jjlxr_relationShip" class="form-control form-control-static" placeholder="请输入关系">
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">联系人地址：</label>
			<div class="col-md-3 ">
			<input type="text" id="jjlxr_shipAddr"  name="jjlxr_shipAddr" class="form-control form-control-static" placeholder="请输入地址">
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">联系人邮箱：</label>
			<div class="col-md-3 ">
			<input type="text" id="jjlxr_shipEmail"  name="jjlxr_shipEmail" class="form-control form-control-static" placeholder="请输入联系人邮箱">
			</div>
			</div>
						
			<div class="form-group">
			<label class="col-md-2 control-label">备注：</label>
			<div class="col-md-3 ">
			<input type="text" id="jjlxr_remark"  name="jjlxr_remark" class="form-control form-control-static" placeholder="请输入备注">
			</div>
			</div>						
            <div class="modal-footer col-md-6">
            <!--用来清空表单数据-->
            <input type="reset" name="reset" style="display: none;" />
                <button type="button" class="btn btn-default" onclick="InsuranceSalesInfo.closeDlg()">关闭</button>
               <button id="saveButton" type="button" onclick="InsuranceSalesInfo.addJjlxr('jjlxr_list')" class="btn btn-primary">保存</button>
            </div>
            </form>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>


<!-- 模态框（Modal） -->
<!-- 修改 -->
<div id="jjlxrMydlg" class="modal fade"  tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">修改紧急联系人</h4>
            </div>
            <div class="container">
			<form class="form-horizontal" id="jjlxr_updateForm"  method="post">
			<input type="hidden" id="jjlxr_index_update" name="jjlxr_index_update" value="" class="form-control form-control-static">			
			<div class="form-group">
			<label class="col-md-2 control-label">联系人姓名：</label>
			<div class="col-md-3">
			<input type="text" id="jjlxr_update_personName"  name="jjlxr_update_personName" class="form-control form-control-static" placeholder="请输入联系人姓名">
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">联系电话：</label>
			<div class="col-md-3 ">
			<input type="text" id="jjlxr_update_shipCellPhone"  name="jjlxr_update_shipCellPhone" class="form-control form-control-static" placeholder="请输入联系电话">
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">关系：</label>
			<div class="col-md-3 ">
			<input type="text" id="jjlxr_update_relationShip"  name="jjlxr_update_relationShip" class="form-control form-control-static" placeholder="请输入关系">
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">联系人地址：</label>
			<div class="col-md-3 ">
			<input type="text" id="jjlxr_update_shipAddr"  name="jjlxr_update_shipAddr" class="form-control form-control-static" placeholder="请输入地址">
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">联系人邮箱：</label>
			<div class="col-md-3 ">
			<input type="text" id="jjlxr_update_shipEmail"  name="jjlxr_update_shipEmail" class="form-control form-control-static" placeholder="请输入联系人邮箱">
			</div>
			</div>
						
			<div class="form-group">
			<label class="col-md-2 control-label">备注：</label>
			<div class="col-md-3 ">
			<input type="text" id="jjlxr_update_remark"  name="jjlxr_update_remark" class="form-control form-control-static" placeholder="请输入备注">
			</div>
			</div>
						
            <div class="modal-footer col-md-6">
            <!--用来清空表单数据-->
            <input type="reset" name="reset" style="display: none;" />
                <button type="button" class="btn btn-default" onclick="InsuranceSalesInfo.closeDlg()">关闭</button>
               <button type="button" id="updateButton" onclick="InsuranceSalesInfo.updateJjlxrSave()" class="btn btn-primary">保存</button>
            </div>
            </form>
            </div>
        </div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>

<!--司外担保人------------------------------------------------------------------------------------------------------------------------------------------------------------ -->

<!-- 模态框（Modal） -->
<!-- 添加 -->
<div id="swdbrAddDlg" class="modal fade" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">添加司外担保人</h4>
            </div>
            <div class="container">
			<form class="form-horizontal" id="swdbr_addForm"  method="post">
						
			<div class="form-group">
			<label class="col-md-2 control-label">担保人姓名：</label>
			<div class="col-md-3">
			<input type="text" id="swdbr_personName"  name="swdbr_personName" class="form-control form-control-static" placeholder="请输入联系人姓名">
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">联系电话：</label>
			<div class="col-md-3 ">
			<input type="text" id="swdbr_shipCellPhone"  name="swdbr_shipCellPhone" class="form-control form-control-static" placeholder="请输入联系电话">
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">关系：</label>
			<div class="col-md-3 ">
			<input type="text" id="swdbr_relationShip"  name="swdbr_relationShip" class="form-control form-control-static" placeholder="请输入关系">
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">身份证号：</label>
			<div class="col-md-3 ">
			<input type="text" id="swdbr_idCard"  name="swdbr_idCard" class="form-control form-control-static" placeholder="请输入身份证号">
			</div>
			</div>
						
			<div class="form-group">
			<label class="col-md-2 control-label">担保日期：</label>
			<div class="col-md-3 ">
			<input type="text" id="swdbr_guaranteeDate"  name="swdbr_guaranteeDate" value="" class="form-control form-control-static" placeholder="请输入担保日期" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})" >
			</div>
			</div>						
            <div class="modal-footer col-md-6">
            <!--用来清空表单数据-->
            <input type="reset" name="reset" style="display: none;" />
                <button type="button" class="btn btn-default" onclick="InsuranceSalesInfo.closeDlg()">关闭</button>
               <button id="saveButton" type="button" onclick="InsuranceSalesInfo.addSwdbr('swdbr_list')" class="btn btn-primary">保存</button>
            </div>
            </form>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>


<!-- 模态框（Modal） -->
<!-- 修改 -->
<div id="swdbrMydlg" class="modal fade"  tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">修改司外担保人</h4>
            </div>
            <div class="container">
			<form class="form-horizontal" id="swdbr_updateForm"  method="post">
			<input type="hidden" id="swdbr_index_update" name="swdbr_index_update" value="" class="form-control form-control-static">			
			<div class="form-group">
			<label class="col-md-2 control-label">担保人姓名：</label>
			<div class="col-md-3">
			<input type="text" id="swdbr_update_personName"  name="swdbr_update_personName" class="form-control form-control-static" placeholder="请输入担保人姓名">
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">联系电话：</label>
			<div class="col-md-3 ">
			<input type="text" id="swdbr_update_shipCellPhone"  name="swdbr_update_shipCellPhone" class="form-control form-control-static" placeholder="请输入联系电话">
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">关系：</label>
			<div class="col-md-3 ">
			<input type="text" id="swdbr_update_relationShip"  name="swdbr_update_relationShip" class="form-control form-control-static" placeholder="请输入关系">
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">身份证号：</label>
			<div class="col-md-3 ">
			<input type="text" id="swdbr_update_idCard"  name="swdbr_update_idCard" class="form-control form-control-static" placeholder="请输入身份证号">
			</div>
			</div>
						
			<div class="form-group">
			<label class="col-md-2 control-label">担保日期：</label>
			<div class="col-md-3 ">
			<input type="text" id="swdbr_update_guaranteeDate"  name="swdbr_update_guaranteeDate" class="form-control form-control-static" placeholder="请输入担保日期" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})" />
			</div>
			</div>
						
            <div class="modal-footer col-md-6">
            <!--用来清空表单数据-->
            <input type="reset" name="reset" style="display: none;" />
                <button type="button" class="btn btn-default" onclick="InsuranceSalesInfo.closeDlg()">关闭</button>
               <button type="button" id="updateButton" onclick="InsuranceSalesInfo.updateSwdbrSave()" class="btn btn-primary">保存</button>
            </div>
            </form>
            </div>
        </div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>

<!--合同信息------------------------------------------------------------------------------------------------------------------------------------------------------------ -->

<!-- 模态框（Modal） -->
<!-- 添加 -->
<div id="htAddDlg" class="modal fade" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">新增合同信息</h4>
            </div>
            <div class="container">
			<form class="form-horizontal" id="ht_addForm"  method="post">
						
			<div class="form-group">
			<label class="col-md-2 control-label">合同编号：</label>
			<div class="col-md-3">
			<input type="text" id="ht_contractNo"  name="ht_contractNo" class="form-control form-control-static" placeholder="请输入合同编号">						
			</div>
			</div>
						
			<div class="form-group">
			<label class="col-md-2 control-label">合同类型：</label>
			<div class="col-md-3 ">
			<input type="text" id="ht_contractType"  name="ht_contractType" class="form-control form-control-static" placeholder="请输入合同类型">
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">签订商业协议：</label>
			<div class="col-md-3">			
				<input type="radio" id="ht_businessAgreementFlag_yes" name="ht_businessAgreementFlag" value="0" checked="checked">是&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" id="ht_businessAgreementFlag_no" name="ht_businessAgreementFlag" value="1">否
			</div>
			</div>			
			
			<div class="form-group">
			<label class="col-md-2 control-label">签订保密协议：</label>
			<div class="col-md-3">			
				<input type="radio" id="ht_secretAgreementFlag_yes" name="ht_secretAgreementFlag" value="0" checked="checked">是&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" id="ht_secretAgreementFlag_no" name="ht_secretAgreementFlag" value="1">否
			</div>
			</div>				

			<div class="form-group">
			<label class="col-md-2 control-label">签订日期：</label>
			<div class="col-md-3 ">
			<input type="text" id="ht_writeDate"  name="ht_writeDate"  value="" class="form-control form-control-static" placeholder="请输入签订日期" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})" />
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">试用结束日期：</label>
			<div class="col-md-3 ">
			<input type="text" id="ht_probationEnd"  name="ht_probationEnd" value="" class="form-control form-control-static" placeholder="请输入试用结束日期" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})" />
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">合同生效日期：</label>
			<div class="col-md-3 ">
			<input type="text" id="ht_contractEffectDate"  name="ht_contractEffectDate" value="" class="form-control form-control-static" placeholder="请输入合同生效日期" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})" />
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">合同失效日期：</label>
			<div class="col-md-3 ">
			<input type="text" id="ht_contractStopDate"  name="ht_contractStopDate" value="" class="form-control form-control-static" placeholder="请输入合同失效日期" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})" />
			</div>
			</div>														
            <div class="modal-footer col-md-6">
            <!--用来清空表单数据-->
            <input type="reset" name="reset" style="display: none;" />
                <button type="button" class="btn btn-default" onclick="InsuranceSalesInfo.closeDlg()">关闭</button>
               <button id="saveButton" type="button" onclick="InsuranceSalesInfo.addHt('ht_list')" class="btn btn-primary">保存</button>
            </div>
            </form>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>


<!-- 模态框（Modal） -->
<!-- 修改 -->
<div id="htMydlg" class="modal fade"  tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">修改合同信息</h4>
            </div>
            <div class="container">
			<form class="form-horizontal" id="ht_updateForm"  method="post">
			<input type="hidden" id="ht_index_update" name="ht_index_update" value="" class="form-control form-control-static">			
			<div class="form-group">
			<label class="col-md-2 control-label">合同编号：</label>
			<div class="col-md-3">
			<input type="text" id="ht_update_contractNo"  name="ht_update_contractNo" class="form-control form-control-static" placeholder="请输入合同编号">						
			</div>
			</div>
						
			<div class="form-group">
			<label class="col-md-2 control-label">合同类型：</label>
			<div class="col-md-3 ">
			<input type="text" id="ht_update_contractType"  name="ht_update_contractType" class="form-control form-control-static" placeholder="请输入合同类型">
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">签订商业协议：</label>
			<div class="col-md-3">			
				<input type="radio" id="ht_update_businessAgreementFlag_yes" name="ht_update_businessAgreementFlag" value="0" checked="checked">是&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" id="ht_update_businessAgreementFlag_no" name="ht_update_businessAgreementFlag" value="1">否
			</div>
			</div>			
			
			<div class="form-group">
			<label class="col-md-2 control-label">签订保密协议：</label>
			<div class="col-md-3">			
				<input type="radio" id="ht_update_secretAgreementFlag_yes" name="ht_update_secretAgreementFlag" value="0" checked="checked">是&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" id="ht_update_secretAgreementFlag_no" name="ht_update_secretAgreementFlag" value="1">否
			</div>
			</div>	
			
			<div class="form-group">
			<label class="col-md-2 control-label">签订日期：</label>
			<div class="col-md-3 ">
			<input type="text" id="ht_update_writeDate"  name="ht_update_writeDate" class="form-control form-control-static" placeholder="请输入签订日期" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})" />
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">试用结束日期：</label>
			<div class="col-md-3 ">
			<input type="text" id="ht_update_probationEnd"  name="sht_update_probationEnd" class="form-control form-control-static" placeholder="请输入试用结束日期" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})" />
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">合同生效日期：</label>
			<div class="col-md-3 ">
			<input type="text" id="ht_update_contractEffectDate"  name="ht_update_contractEffectDate" class="form-control form-control-static" placeholder="请输入合同生效日期" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})" />
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">合同失效日期：</label>
			<div class="col-md-3 ">
			<input type="text" id="ht_update_contractStopDate"  name="ht_update_contractStopDate" class="form-control form-control-static" placeholder="请输入合同失效日期" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})" />
			</div>
			</div>	
			
			<div class="modal-footer col-md-6">
            <!--用来清空表单数据-->
            <input type="reset" name="reset" style="display: none;" />
                <button type="button" class="btn btn-default" onclick="InsuranceSalesInfo.closeDlg()">关闭</button>
               <button type="button" id="updateButton" onclick="InsuranceSalesInfo.updateHtSave()" class="btn btn-primary">保存</button>
            </div>
            </form>
            </div>
        </div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>
            
</body>
</html>