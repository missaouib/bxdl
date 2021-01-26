<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/core.jsp" %>
<!DOCTYPE  html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <title>新增销售组织机构</title>
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Cache" content="no-cache">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   	<link rel="stylesheet" href="${path}/css/system/role/role.css">
    <script src="${path}/static/js/bootstrap.js"></script>
	<script src="${path}/js/insurance/salesOrgUpd.js"></script>
	<script src="${path}/js/jquery-form.js" charset="UTF-8" type="text/javascript"></script>
</head>
<body>
            <div class="container">
				<input type="hidden" id="fastInnerUrl" name="fastInnerUrl" value="${fastInnerUrl}" />
				<input type="hidden" id="belongAccountingOrgCodeHid" name="" value="${salesOrgInfo.belongAccountingOrgCode}" />
			<form class="form-horizontal" id="updateSalesOrgInfoForm"  method="post">
				<input type="hidden" id="isNoticy"  name="isNoticy" value="0" />
			<input type="hidden" id="salesOrgId"  name="salesOrgId" value="${salesOrgInfo.salesOrgId}" />
			<input type="hidden" id="treeCode"  name="treeCode" value="${salesOrgInfo.treeCode}" />
			<input type="hidden" id="preSalesOrgLevel" name="preSalesOrgLevel" value="${salesOrgInfo.salesOrgLevel}"/>
			<input type="hidden" id = "preParentSalesOrgCode" name="preParentSalesOrgCode" value="${salesOrgInfo.parentSalesOrgCode}"/>
			<div class="form-group" style="margin-top:20px;">
			</div>			
			<div class="form-group">
				<label class="col-md-2 control-label">组织机构名称*：</label>
				<div class="col-md-3 ">
					<input type="text" id="salesOrgName"  name="salesOrgName" value="${salesOrgInfo.salesOrgName}" oldvalue="${salesOrgInfo.salesOrgName}" class="form-control form-control-static" placeholder="请输入组织机构名称">
				</div>

				<label class="col-md-2 control-label">组织机构简称*：</label>
				<div class="col-md-3 ">
					<input type="text" id="salesOrgNameLess"  name="salesOrgNameLess" value="${salesOrgInfo.salesOrgNameLess}" class="form-control form-control-static" placeholder="请输入组织机构简称">
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-2 control-label">组织机构英文名：</label>
				<div class="col-md-3 ">
					<input type="text" id="salesOrgEnName"  name="salesOrgEnName" value="${salesOrgInfo.salesOrgEnName}" class="form-control form-control-static" placeholder="请输入组织机构英文名">
				</div>

				<label class="col-md-2 control-label">组织机构英文简称：</label>
				<div class="col-md-3 ">
					<input type="text" id="salesOrgEname"  name="salesOrgEname" value="${salesOrgInfo.salesOrgEname}" class="form-control form-control-static" placeholder="请输入组织机构英文简称">
				</div>
			</div>
			<div class="form-group">			
				<label class="col-md-2 control-label">顶级机构：</label>
				<div class="col-md-3 ">
					<select class="form-control form-control-static" id="topParentSalesOrgCode" name="topParentSalesOrgCode" hideValue="${salesOrgInfo.topParentSalesOrgCode}">
                        <option value="">请选择顶级机构</option>
             		</select>				
             	</div>	
			
				<label class="col-md-2 control-label">上级组织机构：</label>
				<div class="col-md-3 ">
					<select class="form-control form-control-static" id="parentSalesOrgCode" name="parentSalesOrgCode" hideValue="${salesOrgInfo.parentSalesOrgCode}" oldValue="${salesOrgInfo.parentSalesOrgCode}">
						<option value="">请选择上级组织机构</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-2 control-label">组织机构级别*：</label>
				<div class="col-md-3 ">
					<select class="form-control form-control-static" id="salesOrgLevel" name="salesOrgLevel" oldValue = ${salesOrgInfo.salesOrgLevel}>
						<option value="">请选择组织机构级别</option>
						<option value="01" <c:if test="${salesOrgInfo.salesOrgLevel=='01'}"> selected="selected"</c:if> >总公司</option>
						<option value="02" <c:if test="${salesOrgInfo.salesOrgLevel=='02'}"> selected="selected"</c:if> >省级分公司</option>
						<option value="03" <c:if test="${salesOrgInfo.salesOrgLevel=='03'}"> selected="selected"</c:if> >地市分公司</option>
						<option value="04" <c:if test="${salesOrgInfo.salesOrgLevel=='04'}"> selected="selected"</c:if> >区县分公司</option>
						<option value="05" <c:if test="${salesOrgInfo.salesOrgLevel=='05'}"> selected="selected"</c:if> >团队级别</option>
					</select>
				</div>

				<label class="col-md-2 control-label">组织机构代码*：</label>
				<div class="col-md-3 ">
					<input type="text" id="salesOrgCode"  name="salesOrgCode" readonly="readonly" value="${salesOrgInfo.salesOrgCode}" class="form-control form-control-static" placeholder="请输入组织机构代码">
				</div>
			</div>	
			<div class="form-group">
				<label class="col-md-2 control-label">是否核算机构：</label>
				<div class="col-md-3 ">
					<select class="form-control form-control-static" id="accountingOrg" name="accountingOrg">
                        <option value="0" <c:if test="${salesOrgInfo.accountingOrg=='0'}"> selected="selected"</c:if> >否</option>
                        <option value="1" <c:if test="${salesOrgInfo.accountingOrg=='1'}"> selected="selected"</c:if> >是</option>
             		</select>				
             	</div>			
				<label class="col-md-2 control-label">所属核算机构：</label>
				<div class="col-md-3 ">
					<select class="form-control form-control-static" id="belongAccountingOrgCode" name="belongAccountingOrgCode">
                        <option value="">请选择所属核算机构</option>
             		</select>				
             	</div>
			</div>				
			<div class="form-group">
				<label class="col-md-2 control-label">公司传真：</label>
				<div class="col-md-3 ">
					<input type="text" id="fax"  name="fax" value="${salesOrgInfo.fax}" class="form-control form-control-static" placeholder="请输入公司传真">
				</div>

				<label class="col-md-2 control-label">公司电话：</label>
				<div class="col-md-3 ">
					<input type="text" id="phone"  name="phone" value="${salesOrgInfo.phone}"  class="form-control form-control-static" placeholder="请输入公司电话">
				</div>
			</div>	
			<div class="form-group">
				<input type="hidden" id="areaCode"  name="areaCode" value="${salesOrgInfo.areaCode}" class="form-control form-control-static" placeholder="请输入区域编码">
				<label class="col-md-2 control-label">所在省份：</label>
				<div class="col-md-3 ">
					<select class="form-control form-control-static" id="sheng" name="sheng">
                        <option value="">请选择省份</option>
             		</select>
             	</div>
             	
             	<label class="col-md-2 control-label">所在城市：</label>
             	<div class="col-md-3 ">	
             		<select class="form-control form-control-static" id="shi" name="shi">
                        <option value="">请选择城市</option>
             		</select>
             	</div>
             	
             </div>
             
            <div class="form-group"> 
            
            	<label class="col-md-2 control-label">所在区县：</label>
             	<div class="col-md-3 ">		
             		<select class="form-control form-control-static" id="qu" name="qu">
                        <option value="">请选择区县</option>
             		</select>
				</div>
				
				<label class="col-md-2 control-label">邮政编码：</label>
				<div class="col-md-3 ">
					<input type="text" id="postCode"  name="postCode" value="${salesOrgInfo.postCode}"  class="form-control form-control-static" placeholder="请输入邮政编码" />
				</div>
			</div>	
			<div class="form-group">
				<label class="col-md-2 control-label">详细地址：</label>
				<div class="col-md-3 ">
					<input style="width:655px;" type="text" id="address"  name="address" value="${salesOrgInfo.address}"  class="form-control form-control-static" placeholder="请输入详细地址" />
				</div>	
			</div>
			<div class="form-group">
				<label class="col-md-2 control-label">注册地址：</label>
				<div class="col-md-3 ">
					<input style="width:655px;" type="text" id="regAddr"  name="regAddr" value="${salesOrgInfo.regAddr}" class="form-control form-control-static" placeholder="请输入注册地址" />
				</div>	
			</div>	
			
			<div class="form-group">
				<label class="col-md-2 control-label">成立日期：</label>
                <div class="col-md-3">
                   <input class="form-control form-control-static" id="dateOfEstablishment"  value="${salesOrgInfo.dateOfEstablishment}" name="dateOfEstablishment" type="text" placeholder="请选择成立时间" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
                </div>			
			
				<label class="col-md-2 control-label">开票机构：</label>
             	<div class="col-md-3 ">	
             		<select class="form-control form-control-static" id="invoiceOrgCode" name="invoiceOrgCode">
                        <option value="">请选择</option>
             		</select>
             	</div>
			</div>
			
			<div class="form-group">
				<label class="col-md-2 control-label">机构状态：</label>
             	<div class="col-md-3 ">	
             		<select class="form-control form-control-static" id="orgStatus" name="orgStatus">
                        <option value="">请选择</option>
                        <option value="0" <c:if test="${salesOrgInfo.orgStatus=='0'}"> selected="selected"</c:if>>筹建</option>
                        <option value="1" <c:if test="${salesOrgInfo.orgStatus=='1'}"> selected="selected"</c:if>>正常</option>
                        <option value="2" <c:if test="${salesOrgInfo.orgStatus=='2'}"> selected="selected"</c:if>>整改</option>
                        <option value="3" <c:if test="${salesOrgInfo.orgStatus=='3'}"> selected="selected"</c:if>>撤销</option>
             		</select>
             	</div>
			</div>
			
			<input type="hidden" id="zjjtList" name="zjjtList" value='${zjjtList}' /><!-- 总监津贴 -->
			<div class="form-group" id="zjjt_div" style="display:none">
				<label class="col-md-2 control-label">总监津贴：
				（<button id="zhengshu_add" type="button" onclick="SalesOrgInfo.addZjjt()" class="btn btn-primary">新增</button>）</label>
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
				<label class="col-md-2 control-label">是否挂牌：</label>
             	<div class="col-md-3 ">	
             		<select class="form-control form-control-static" id="haveListed" name="haveListed">
                        <option value="">请选择</option>
                        <option value="0"  <c:if test="${salesOrgInfo.haveListed=='0'}"> selected="selected"</c:if> >否</option>
                        <option value="1"  <c:if test="${salesOrgInfo.haveListed=='1'}"> selected="selected"</c:if> >是</option>
             		</select>
             	</div>			
			
				<label class="col-md-2 control-label">营业执照编号：</label>
                <div class="col-md-3">
					<input type="text" id="businessLicense"  name="businessLicense" value="${salesOrgInfo.businessLicense}" class="form-control form-control-static" placeholder="请输入营业执照编号" />
                </div>			
			</div>			
						
			<div class="form-group">
				<label class="col-md-2 control-label">营业税纳税类型：</label>
             	<div class="col-md-3 ">	
             		<select class="form-control form-control-static" id="businessPayTaxesType" name="businessPayTaxesType">
                        <option value="">请选择</option>
                        <option value="1" <c:if test="${salesOrgInfo.businessPayTaxesType=='1'}"> selected="selected"</c:if>>普通</option>
                        <option value="2" <c:if test="${salesOrgInfo.businessPayTaxesType=='2'}"> selected="selected"</c:if>>小规模</option>
             		</select>
             	</div>			
			
				<label class="col-md-2 control-label">营业税纳税率(%)：</label>
                <div class="col-md-3">
					<input type="text" id="businessTaxRate"  name="businessTaxRate" value="${salesOrgInfo.businessTaxRate}" class="form-control form-control-static" placeholder="请输入营业税纳税率(%)" />
                </div>			
			</div>	
						
			<div class="form-group">
				<label class="col-md-2 control-label">所得税纳税类型：</label>
             	<div class="col-md-3 ">	
             		<select class="form-control form-control-static" id="incomePayTaxesType" name="incomePayTaxesType">
                        <option value="">请选择</option>
                        <option value="1" <c:if test="${salesOrgInfo.incomePayTaxesType=='1'}"> selected="selected"</c:if>>普通</option>
                        <option value="2" <c:if test="${salesOrgInfo.incomePayTaxesType=='2'}"> selected="selected"</c:if>>小规模</option>
             		</select>
             	</div>			
			
				<label class="col-md-2 control-label">所得税纳税率(%)：</label>
                <div class="col-md-3">
					<input type="text" id="incomeTaxRate"  name="incomeTaxRate" value="${salesOrgInfo.incomeTaxRate}" class="form-control form-control-static" placeholder="请输入所得税纳税率(%)" />
                </div>			
			</div>	
			
			<div class="form-group">
				<label class="col-md-2 control-label">许可证地址：</label>
				<div class="col-md-3 ">
					<input type="text" id="licenseAddr"  name="licenseAddr" value="${salesOrgInfo.licenseAddr}" class="form-control form-control-static" placeholder="请输入许可证地址" />
				</div>

				<label class="col-md-2 control-label">发证机关：</label>
				<div class="col-md-3 ">
					<input type="text" id="licensingAuthority"  name="licensingAuthority" value="${salesOrgInfo.licensingAuthority}" class="form-control form-control-static" placeholder="请输入发证机关" />
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-md-2 control-label">联系人手机号：</label>
				<div class="col-md-3 ">
					<input type="text" id="contactsTel"  name="contactsTel" value="${salesOrgInfo.contactsTel}" class="form-control form-control-static" placeholder="请输入联系人手机号" />
				</div>

				<label class="col-md-2 control-label">负责人姓名：</label>
				<div class="col-md-3 ">
					<input type="text" id="liableName"  name="liableName" value="${salesOrgInfo.liableName}" class="form-control form-control-static" placeholder="请输入负责人姓名" />
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-md-2 control-label">员工数量：</label>
				<div class="col-md-3 ">
					<input type="text" id="employeesNum"  name="employeesNum" value="${salesOrgInfo.employeesNum}" class="form-control form-control-static" placeholder="请输入员工数量" />
				</div>

				<label class="col-md-2 control-label">社保人数：</label>
				<div class="col-md-3 ">
					<input type="text" id="socialSecurityNum"  name="socialSecurityNum" value="${salesOrgInfo.socialSecurityNum}" class="form-control form-control-static" placeholder="请输入社保人数" />
				</div>
			</div>									

			<div class="form-group">
				<label class="col-md-2 control-label">银行开户许可证：</label>
				<div class="col-md-3 ">
					<button type="button" class="btn btn-primary" onclick="SalesOrgInfo.showImg('bankAccountLicense')">查看资料</button>
               		<button type="button" class="btn btn-primary" onclick="SalesOrgInfo.openImageDlg('bankAccountLicense')">上传资料</button>
               		<input type="hidden" id="bankAccountLicense" name="bankAccountLicense" value="${salesOrgInfo.bankAccountLicense}" />
				</div>

				<label class="col-md-2 control-label">信用代码证：</label>
				<div class="col-md-3 ">
					<button type="button" class="btn btn-primary" onclick="SalesOrgInfo.showImg('creditCodePic')">查看资料</button>
               		<button type="button" class="btn btn-primary" onclick="SalesOrgInfo.openImageDlg('creditCodePic')">上传资料</button>
               		<input type="hidden" id="creditCodePic" name="creditCodePic" value="${salesOrgInfo.creditCodePic}" />
               	</div>
			</div>	
			
			<div class="form-group">
				<label class="col-md-2 control-label">营业执照：</label>
				<div class="col-md-3 ">
					<button type="button" class="btn btn-primary" onclick="SalesOrgInfo.showImg('businessLicensePic')">查看资料</button>
               		<button type="button" class="btn btn-primary" onclick="SalesOrgInfo.openImageDlg('businessLicensePic')">上传资料</button>
               		<input type="hidden" id="businessLicensePic" name="businessLicensePic" value="${salesOrgInfo.businessLicensePic}" />
				</div>

				<label class="col-md-2 control-label">业务许可证：</label>
				<div class="col-md-3 ">
					<button type="button" class="btn btn-primary" onclick="SalesOrgInfo.showImg('carryOutBusinessPic')">查看资料</button>
               		<button type="button" class="btn btn-primary" onclick="SalesOrgInfo.openImageDlg('carryOutBusinessPic')">上传资料</button>
               		<input type="hidden" id="carryOutBusinessPic" name="carryOutBusinessPic" value="${salesOrgInfo.carryOutBusinessPic}" />
               	</div>
			</div>						
						
			<div class="form-group">
				<label class="col-md-2 control-label">公司发展历程：</label>
				<div class="col-md-3 ">
					<textarea  style="width:655px;" rows="4" id="developmentHistory" name="developmentHistory" class="form-control form-control-static" placeholder="请输入公司发展历程">${salesOrgInfo.salesOrgDetail.developmentHistory}</textarea>
				</div>
			</div>	
			
			<div class="form-group">
				<label class="col-md-2 control-label">公司重要资质：</label>
				<div class="col-md-3 ">
					<textarea  style="width:655px;" rows="4" id="qualifications" name="qualifications" class="form-control form-control-static" placeholder="请输入公司重要资质">${salesOrgInfo.salesOrgDetail.qualifications}</textarea>
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-md-2 control-label">公司近年经营指标：</label>
				<div class="col-md-3 ">
					<textarea  style="width:655px;" rows="4" id="operatingIndicators" name="operatingIndicators" class="form-control form-control-static" placeholder="请输入公司近年经营指标">${salesOrgInfo.salesOrgDetail.operatingIndicators}</textarea>
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-md-2 control-label">人员优势：</label>
				<div class="col-md-3 ">
					<textarea  style="width:655px;" rows="4" id="personnelAdvantage" name="personnelAdvantage" class="form-control form-control-static" placeholder="请输入人员优势">${salesOrgInfo.salesOrgDetail.personnelAdvantage}</textarea>
				</div>
			</div>									
			
			<div class="form-group">
				<label class="col-md-2 control-label">项目优势：</label>
				<div class="col-md-3 ">
					<textarea  style="width:655px;" rows="4" id="projectAdvantage" name="projectAdvantage" class="form-control form-control-static" placeholder="请输入项目优势">${salesOrgInfo.salesOrgDetail.projectAdvantage}</textarea>
				</div>
			</div>			
																																												
            <div class="modal-footer col-md-6">
            <!--用来清空表单数据-->
            <input type="reset" name="reset" style="display: none;" />
                <button type="button" class="btn btn-default" onclick="windowCloseTab()">关闭</button>
               <button id="saveButton" type="button" onclick="SalesOrgInfo.add()" class="btn btn-primary">保存</button>
            </div>
            </form>
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
								<input style="margin-left: 260px;" type="button" value="上传" onclick="SalesOrgInfo.uploadImage('imageForm')" class="btn btn-info" />  
							</form>
			            </div>
			        </div><!-- /.modal-content -->
			    </div><!-- /.modal -->
			</div>
</body>
</html>