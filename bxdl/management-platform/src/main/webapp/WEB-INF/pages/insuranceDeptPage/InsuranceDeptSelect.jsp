<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/core.jsp" %>
<!DOCTYPE  html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <title>保险机构管理</title>
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Cache" content="no-cache">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   	<link rel="stylesheet" href="${path}/css/system/role/role.css">
   	<link rel="stylesheet" href="${path}/css/system/role/role.css">
    <script src="${path}/static/js/bootstrap.js"></script>
	<script type="text/javascript">	
		$(function () {	
			getAreaByPid("0","sheng");
			var areaCode = $("#areaCode").val();
			//alert(areaCode);
			setTimeout(function(){ 
				$("#sheng option").each(function(){
					//alert($(this).val());
			        if($(this).val()==areaCode.substring(0,6)){
			            $(this).attr("selected", true);
			        }
				});
				getAreaByPid($("#sheng").find("option:selected").attr("myvalue"),"shi");
		        if(areaCode.length>8){
					setTimeout(function(){ 
						$("#shi option").each(function(){
							//alert($(this).val());
					        if($(this).val()==areaCode.substring(0,9)){
					            $(this).attr("selected", true);					            
					        }
						});
						getAreaByPid($("#shi").find("option:selected").attr("myvalue"),"qu");
					},100);	
		        }
		        if(areaCode.length>11){		        	
					setTimeout(function(){ 
						$("#qu option").each(function(){
							//alert($(this).val());
					        if($(this).val()==areaCode.substring(0,12)){
					            $(this).attr("selected", true);
					        }
						});
					},300);	
		        }		        
			},300);
			InsuranceDep.formValidator();
		});
		
		var InsuranceDep = function () {
			return{				
				 //添加
		        add:function () {
		        	document.getElementById("saveButton").setAttribute("disabled", true);
		            if($("#addInsuranceDeptSelectForm").data('bootstrapValidator').validate().isValid()){
		            	flag = true;
		            	var sheng = $("#sheng").val();
		            	var shi = $("#shi").val();
		            	var qu = $("#qu").val();
		            	if(qu != ""){
		            		$("#areaCode").val(qu);
		            	}else{
		            		if(shi!=""){$("#areaCode").val(shi);}else{$("#areaCode").val(sheng);}
		            	}
		    			if(flag){
			            	$.ajax({
			                    url:'insurance_dept/update_insurance_dept',
			                    dataType:'json',
			                    type:'post',
			                    data:$("#addInsuranceDeptSelectForm").serialize(),
			                    success:function(data){
			                        if(data){
			                        	 $.alert({
			                                 title: '提示信息！',
			                                 content: '修改成功!',
			                                 type: 'blue'
			                             });
			                 		    document.getElementById("saveButton").removeAttribute("disabled");
										windowCloseTab()
			                        }else{
			                            $.alert({
			                                title: '提示信息！',
			                                content: '修改失败！',
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
		            }else{
		    		    document.getElementById("saveButton").removeAttribute("disabled");
		            }
		        },
			
		        //表单验证
		        formValidator:function () {
		            $("#addInsuranceDeptSelectForm").bootstrapValidator({
		                fields:{
		                	insuranceCompanyName:{
		                        validators:{
		                            notEmpty:{
		                                message:"保险公司名称不能为空"
		                            },
		                            stringLength:{
		                                max:128,
		                                message:'不能超过20个字符长度'
		                            },
		                        }
		                    },
		                    insuranceCompanyCode:{
		                        validators:{
		                            notEmpty:{
		                                message:'保险公司代码不能为空',
		                            },
		                            stringLength:{
		                                max:32,
		                                message:'字符长度不能超过20'
		                            }
		                        }
		                    }
		                }
		            });
		         }
				}
			}();		
	</script> 
</head>
<body>
            <div class="container">
			<form class="form-horizontal" id="addInsuranceDeptSelectForm"  method="post">
			<div class="form-group" style="margin-top:20px;">
			<input type="hidden" id="insuranceCompanyId" name="insuranceCompanyId" value="${insurancedept.insuranceCompanyId}" />
			</div>			
			<div class="form-group">
				<label class="col-md-2 control-label">保险公司名称：</label>
				<div class="col-md-3 ">
					<input type="text" disabled="disabled" id="insuranceCompanyName"  name="insuranceCompanyName" value="${insurancedept.insuranceCompanyName}" class="form-control form-control-static" placeholder="请输入保险公司名称">
				</div>

				<label class="col-md-2 control-label">保险公司简称：</label>
				<div class="col-md-3 ">
					<input type="text" disabled="disabled" id="insuranceCompanyNameLess"  name="insuranceCompanyNameLess" value="${insurancedept.insuranceCompanyNameLess}" class="form-control form-control-static" placeholder="请输入保险公司简称">
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-2 control-label">保险公司英文名：</label>
				<div class="col-md-3 ">
					<input type="text" disabled="disabled" id="insuranceCompanyEnName"  name="insuranceCompanyEnName" value="${insurancedept.insuranceCompanyEnName}" class="form-control form-control-static" placeholder="请输入保险公司英文名">
				</div>

				<label class="col-md-2 control-label">保险公司英文简称：</label>
				<div class="col-md-3 ">
					<input type="text" disabled="disabled" id="insuranceCompanyEnNameLess"  name="insuranceCompanyEnNameLess" value="${insurancedept.insuranceCompanyEnNameLess}" class="form-control form-control-static" placeholder="请输入保险公司英文简称">
				</div>
			</div>
			<div class="form-group">
			
				<label class="col-md-2 control-label">保险公司代码：</label>
				<div class="col-md-3 ">
					<input type="text" disabled="disabled" id="insuranceCompanyCode"  name="insuranceCompanyCode" value="${insurancedept.insuranceCompanyCode}"  class="form-control form-control-static" placeholder="请输入保险公司代码">
				</div>
			
				<label class="col-md-2 control-label">保险公司财寿标志：</label>
				<div class="col-md-3 ">
					<select class="form-control form-control-static" disabled="disabled" id="businessType" name="businessType">
                        <option value="">请选择财寿标志</option>
                        <option value="0" <c:if test="${insurancedept.businessType==0}"> selected="selected"</c:if> >寿险</option>
                        <option value="1" <c:if test="${insurancedept.businessType==1}"> selected="selected"</c:if> >财险</option>
             		</select>				
             	</div>
			</div>
			<div class="form-group">
				<label class="col-md-2 control-label">保险公司成立日期：</label>
               <div class="col-md-3">
                   <input class="form-control form-control-static" disabled="disabled" id="dateOfEstablishment" name="dateOfEstablishment" value="${insurancedept.dateOfEstablishment}"  type="text" placeholder="请选择成立时间" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
               </div>

				<label class="col-md-2 control-label">注册资本（万元）：</label>
				<div class="col-md-3 ">
					<input type="text" disabled="disabled" id="registeredCapital"  name="registeredCapital" value="${insurancedept.registeredCapital}"  class="form-control form-control-static" placeholder="请输入注册资本">
				</div>
			</div>	
			<div class="form-group">
				<label class="col-md-2 control-label">公司传真：</label>
				<div class="col-md-3 ">
					<input type="text" disabled="disabled" id="fax"  name="fax" value="${insurancedept.fax}" class="form-control form-control-static" placeholder="请输入公司传真">
				</div>

				<label class="col-md-2 control-label">公司电话：</label>
				<div class="col-md-3 ">
					<input type="text" disabled="disabled" id="tel"  name="tel" value="${insurancedept.tel}" class="form-control form-control-static" placeholder="请输入公司电话">
				</div>
			</div>	
			<div class="form-group">
				<input type="hidden" disabled="disabled" id="areaCode"  name="areaCode" value="${insurancedept.areaCode}" class="form-control form-control-static" placeholder="请输入区域编码">
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
				
				<label class="col-md-2 control-label">邮政编码：</label>
				<div class="col-md-3 ">
					<input type="text" disabled="disabled" id="postCode"  name="postCode" value="${insurancedept.postCode}" class="form-control form-control-static" placeholder="请输入邮政编码">
				</div>
			</div>	
			<div class="form-group">
				<label class="col-md-2 control-label">详细地址：</label>
				<div class="col-md-3 ">
					<input style="width:655px;" disabled="disabled" type="text" id="address"  name="address" value="${insurancedept.address}" class="form-control form-control-static" placeholder="请输入详细地址">
				</div>	
			</div>	
			<div class="form-group">
				<label class="col-md-2 control-label">公司网址：</label>
				<div class="col-md-3 ">
					<input style="width:655px;" disabled="disabled" type="text" id="webSite"  name="webSite" value="${insurancedept.webSite}" class="form-control form-control-static" placeholder="请输入公司网址">
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-2 control-label">主营业务：</label>
				<div class="col-md-3 ">
					<!-- <input style="width:655px;" type="text" id="role_code"  name="roleCode" class="form-control form-control-static" placeholder="请输入主营业务">  -->
					<textarea  style="width:655px;" disabled="disabled" rows="3" id="mainBusiness" name="mainBusiness" class="form-control form-control-static" placeholder="请输入主营业务">${insurancedept.mainBusiness}</textarea>
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