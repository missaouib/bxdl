<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/core.jsp" %>
<!DOCTYPE  html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <title>佣金率详情</title>
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Cache" content="no-cache">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   	<link rel="stylesheet" href="${path}/css/system/role/role.css">
   	<link rel="stylesheet" href="${path}/css/product/product.css">
    <script src="${path}/static/js/bootstrap.js"></script>
	<script type="text/javascript">	
		var partnershipAdd = function () {
			return{				
				 //添加
		        add:function () {
		        	document.getElementById("saveButton").setAttribute("disabled", false);
		            	flag = true; 
		    	  if(flag){
			            	$.ajax({
			                    url:'product_basic_information/add_insurance_product',
			                    type:'post',
			                  //  contentType: "application/json",//如果想以json格式把数据提交到后台的话，这个必须有，否则只会当做表单提交
			                  //  data: JSON.stringify(insuranceProductInfo),//JSON.stringify()必须有,否则只会当做表单的格式提交
			                    dataType:'json',
			                    data:$("#partnershipAddForm").serialize(),
			                    success:function(data){
			                        if(data){
			                        	 $.alert({
			                                 title: '提示信息！',
			                                 content: '添加成功!',
			                                 type: 'blue'
			                             });
			                 		    document.getElementById("saveButton").removeAttribute("disabled");
			                        }else{
			                            $.alert({
			                                title: '提示信息！',
			                                content: '添加失败！',
			                                type: 'red'
			                            });
			                		    document.getElementById("saveButton").removeAttribute("disabled");
			                        }
			                    },
			                    error:function(){
			                        $.alert({
			                            title: '提示信息！',
			                            content: '请求失败！',
			                            type: 'red'
			                        });
			                    }
			                });
			            }
		        },
				}
			}();		
			
	</script> 
</head>
<body>
        <div class="container">
		<form class="form-horizontal" id="partnershipAddForm"  method="post"  >
		<input type="hidden" id="salesOrgId"  name="salesOrgId" />
		<input type="hidden" id="salesOrgName"  name="salesOrgName" />
		<div class="form-group" style="margin-top:20px;"></div>	
		    <div class="form-group">
				<label for="disabledSelect"  class="col-sm-2 control-label"> 计算项：</label>
			    <div class="col-md-4 ">
			            <select class="form-control form-control-static" id="computationalTerm" name="computationalTerm"  disabled="disabled">
	                           <option value="" <c:if test="${partnershipDetail.computationalTerm eq '' }">selected</c:if>>请选择</option>       
							   <option value="0"<c:if test="${partnershipDetail.computationalTerm eq '0' }">selected</c:if>>C保护</option>     
							   <option value="1"<c:if test="${partnershipDetail.computationalTerm eq '1' }">selected</c:if>>P手续费</option>
							<option value="2"<c:if test="${partnershipDetail.computationalTerm eq '2' }">selected</c:if>>基础佣金率</option>
	             		</select>			
			    </div>
			<label class="col-md-2 control-label">企业内部组织机构名称*：</label>
			<div class="col-md-3 ">
			<input type="text" value="${partnershipDetail.enterprisEorganizationName}"  id="salOrlInfor"  name="enterprisEorganizationName"  disabled="disabled"/>
			</div>
		</div>
			<div class="form-group">
               <label class="col-md-2 control-label">保险公司名称*：</label>
				<div class="col-md-3 ">
			<input type="text"  value="${partnershipDetail.insuranceCompanyName}"  id="insurance"  name="insuranceCompanyName" disabled="disabled"/>
				</div>
				 <label class="col-md-2 control-label">父产品名称*：</label>
				<div class="col-md-3 ">
				<input type="text"  value="${partnershipDetail.productName}" id="productName"  name="productName"  disabled="disabled"/>
				</div>
			</div>		
	<div>
	
		<table id="proListEditTwo" border="1" >
		   <tr  style="background: silver;">
			<th width="11%" >产品名称</th>
		 	<th width="11%" >产品编码</th>
		    <th width="11%" >保险起期（年）</th>
		    <th width="11%" >保险止期（年）</th>
		    <th width="11%" >缴费起期（年）</th>
		    <th width="11%" >缴费止期（年）</th>
		    <th width="14%" >第1年度费率（%）</th>
		    <th width="14%" >第2年度费率（%）</th>
		    <th width="14%" >第3年度费率（%）</th>
		    <th width="14%" >第4年度费率（%）</th>
		    <th width="14%" >第5年度费率（%）</th>
		    <th width="14%" >第6年度费率（%）</th>
		  </tr>              
		   <c:forEach items="${partnershipSub}" var="base"> 
		  	 <tr>
			    <td  ><input style=width:100px; value='${base.productsName}'      type="text" id="productsName"  name="productsName" readonly="readonly" >  </td>
			    <td  ><input style=width:100px; value='${base.productsCode}'      type="text" id="productsCode"  name="productsCode" readonly="readonly">  </td>
			    <td  ><input style=width:100px; value='${base.insurancePeriodMin}'type="text" id="insurancePeriodMin"  name="insurancePeriodMin" readonly="readonly"></td>
			    <td ><input style=width:100px;  value='${base.insurancePeriodMax}' type="text" id="insurancePeriodMax"  name="insurancePeriodMax" readonly="readonly"></td>
			    <td  ><input style=width:100px; value='${base.renewPeriodMin}'     type="text" id="renewPeriodMin"  name="renewPeriodMin" readonly="readonly"></td>
			    <td  ><input style=width:100px; value='${base.renewPeriodMax}'     type="text" id="renewPeriodMax" name="renewPeriodMax" readonly="readonly" > </td>
			    <td ><input style=width:100px;  value='${base.firstAnnualRate}'      type="text" id="firstAnnualRate"  name="firstAnnualRate" readonly="readonly"></td>
			    <td ><input style=width:100px;  value='${base.secondAnnualRate}'     type="text" id="secondAnnualRate"  name="secondAnnualRate" readonly="readonly"></td>
			    <td ><input style=width:100px;  value='${base.thirdAnnualRate}'      type="text" id="thirdAnnualRate"  name="thirdAnnualRate" readonly="readonly"></td>
			    <td ><input style=width:100px;  value='${base.fourthAnnualRate}'     type="text" id="fourthAnnualRate"  name="fourthAnnualRate"readonly="readonly" ></td>
			    <td ><input style=width:100px;  value='${base.fifthAnnualRate}'      type="text" id="fifthAnnualRate"  name="fifthAnnualRate"  readonly="readonly"></td>
			    <td ><input style=width:100px;  value='${base.sixthAnnualRate}'      type="text" id="sixthAnnualRate"  name="sixthAnnualRate" readonly="readonly"></td>
			  </tr>
  </c:forEach>
	</table>	
		
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