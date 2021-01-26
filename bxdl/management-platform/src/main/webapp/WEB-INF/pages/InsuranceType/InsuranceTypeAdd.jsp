<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/core.jsp" %>
<!DOCTYPE  html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <title>险种类别添加</title>
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Cache" content="no-cache">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   	<link rel="stylesheet" href="${path}/css/system/role/role.css">
   	<link rel="stylesheet" href="${path}/css/product/product.css">
    <script src="${path}/static/js/bootstrap.js"></script>
	<script type="text/javascript">	
		$(function () {	
			insTypeAdd.formValidator();
		});
	
		var insTypeAdd = function () {
			return{				
				 //添加
		        add:function () {
		        	document.getElementById("saveButton").setAttribute("disabled", false);
		             if($("#addFormType").data('bootstrapValidator').validate().isValid()){ 
		            	flag = true;
		            	var isSys = $("#sysParameter").val();
		            	if(isSys=="1"){
		            		var insurance = $("#insurance").val();
		            		if(isEmpty(insurance)){
	                        	 $.alert({
	                                 title: '提示信息！',
	                                 content: '险种所属保险公司必填!',
	                                 type: 'blue'
	                             });
		            			 flag = false;
		            			 document.getElementById("saveButton").removeAttribute("disabled");
		            			 return;
		            		}
		            	}
		            	if(isSys=="1"){
		            		var asSysType = $("#localType").val();
		            		if(isEmpty(asSysType)){
	                        	 $.alert({
	                                 title: '提示信息！',
	                                 content: '险种对应本系统险种必填!',
	                                 type: 'blue'
	                             });
		            			 flag = false;
		            			 document.getElementById("saveButton").removeAttribute("disabled");
		            			 return;
		            		}
		            	}
		    	        if(flag){
			            	$.ajax({
			                    url:'Insurance_type_manager/add_Insurance_type',
			                    type:'post',
			                    dataType:'json',
			                    data:$("#addFormType").serialize(),
			                    success:function(data){
			                        if("200" == data.messageCode){
			                        	 $.alert({
			                                 title: '提示信息！',
			                                 content: '添加成功!',
			                                 type: 'blue'
			                             });
			                 		    document.getElementById("saveButton").removeAttribute("disabled");
			                 		    windowCloseTab();
			                        }else if("500" == data.messageCode){
			                        	 $.alert({
			                                 title: '提示信息！',
			                                 content: '存在（系统、公司）代码相同的数据!',
			                                 type: 'red'
			                             });
			                        	 document.getElementById("saveButton").removeAttribute("disabled");
			    					}else{
			                        	 $.alert({
			                                 title: '提示信息！',
			                                 content: '系统异常!',
			                                 type: 'blue'
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
			            }else{
			    		     document.getElementById("saveButton").removeAttribute("disabled"); 
			            } 
		             }else{
		    		     document.getElementById("saveButton").removeAttribute("disabled"); 
		            } 
		        },
		        //表单验证
		        formValidator:function () {
		            $("#addFormType").bootstrapValidator({
		                fields:{
		                	insuranceTypeName:{
		                        validators:{
		                            notEmpty:{
		                                message:"名称不能为空"
		                            },
		                            stringLength:{
		                                max:20,
		                                message:'不能超过20个字符长度'
		                            },
		                        }
		                    },
		                    insuranceTypeCode:{
		                        validators:{
		                            notEmpty:{
		                                message:'代码不能为空',
		                            },
		                            stringLength:{
		                                max:20,
		                                message:'字符长度不能超过20'
		                            }
		                        }
		                    },
		                }
		            });
		         }
				}
			
			}();		
			
			var sp=$('#sysParameter option:selected').val();
			if(sp==''||sp=='0'){
				  document.getElementById("isshow").style.display = "none";
				  document.getElementById("isShowTwo").style.display = "none";
			}
			 function funcChengeType(value){  
			     if(value=='1'){
			    	  getInsuranceCompany("insurance");
			    	  getLocalType("localType","0");
			    	  document.getElementById("isshow").style.display = "";
			     	  document.getElementById("isShowTwo").style.display = "";
			     }else if(value=='0'){
			    	 $('#insuranceCompanyId').val(""); 
			    	  document.getElementById("isshow").style.display = "none";
			    	  document.getElementById("isShowTwo").style.display = "none";
			    	  $("#insurance").val("");
			    	  $("#localType").val("");
			     }else{
			    	  document.getElementById("isshow").style.display = "none";
			          document.getElementById("isShowTwo").style.display = "none";
			    	  $("#insurance").val("");
			    	  $("#localType").val("");
			     }
			    }    	
			 
			 function getInsuranceCompany(selectId){
				 $.ajax({
			         type: "POST",
			         url: "insurance_dept/findInsurCompanys",
			         dataType: "json",
			         success: function(data){
			        	var dataObject = data.rows;
						var h = "<option value=''>请选择保险公司</option>";
			            $.each(dataObject, function(key, value) {
			                h += "<option value='" + value.insuranceCompanyId + "' orgLevelValue='"+ value.orgLevel+ "' insuranceCompanyCodeValue='"+ value.insuranceCompanyCode+ "' insuranceCompanyNamesValue='"+ value.insuranceCompanyName +"'>"  + value.insuranceCompanyName //下拉框序言的循环数据
			                + "</option>"; 
			            });
			            $("#"+selectId).empty();
			            $("#"+selectId).append(h);
			         if(selectId="insurance"){
				            $("#"+selectId).on(  
				    	            "change",function(){
				    	            	var orgLevels = $("#insurance").find("option:selected").attr("orgLevelValue");
				    	            	var insuranceCompanyCodeValues = $("#insurance").find("option:selected").attr("insuranceCompanyCodeValue");
				    	            	var insuranceCompanyNamesValues = $("#insurance").find("option:selected").attr("insuranceCompanyNamesValue");
				    	            	/* $('#insuranceCompanyCodes').val(insuranceCompanyCodeValues); 
				    	            	$('#orgLevel').val(orgLevels); 
				    	            	$('#insuranceCompanyNames').val(insuranceCompanyNamesValues); */
				    	                $('#insuranceCompanyId').val($('#insurance option:selected').val()); 
				    	            }
			    	        )
			            } 
			         }
			     });
			}
			 
	function getLocalType(selectId,sysParameter){
		  $.ajax({
	         type: "POST",
	         data:{sysParameter:sysParameter},
	         url: "Insurance_type_manager/find_types",
	         dataType: "json",
	         success: function(data){
	        	var dataObject = data.rows;
				var h = "<option value=''>请选择汇康险种类别</option>";
	            $.each(dataObject, function(key, value) {
	            	  h += "<option value='" + value.insuranceTypeId + "' parentInsuranceTypeId='"+ value.parentInsuranceTypeId +"'>"  + value.insuranceTypeName //下拉框序言的循环数据
		                + "</option>"; 
	            });
	            $("#"+selectId).empty();
	            $("#"+selectId).append(h);
	            
	         }
	     }); 
	}		 
			 
	</script> 
</head>
<body>
        <div class="container">
		<form class="form-horizontal" id="addFormType"  method="post"  >
			<input type="hidden" id="insuranceCompanyId"  name="insuranceCompanyId" class="form-control form-control-static" >
		<div class="form-group" style="margin-top:20px;"></div>	
			<div class="form-group">
				<label class="col-md-2 control-label">险种类别名称*：</label>
				<div class="col-md-3 ">
					<input type="text" id="insuranceTypeName"  name="insuranceTypeName" class="form-control form-control-static" placeholder="请输入险种类别名称">
				</div>

				<label class="col-md-2 control-label">险种类别代码*：</label>
				<div class="col-md-3 ">
					<input type="text" id="insuranceTypeCode"  name="insuranceTypeCode" class="form-control form-control-static" placeholder="请输入险种类别代码">
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-md-2 control-label">是否本系统参数*：</label>
				 <div class="col-md-3 ">
					<select class="form-control form-control-static" id="sysParameter" name="sysParameter" onchange="funcChengeType(this.options[this.options.selectedIndex].value)">
                        <option value="">请选择</option>
                         <option value="0">是</option>
                         <option value="1">否</option>
             		</select>				
             	</div>
             	
             <div id="isshow">
                <label class="col-md-2 control-label">保险公司名称*：</label>
				<div class="col-md-3 ">
				<select class="form-control " id="insurance"  name="insuranceCompanyName" ></select>
		        </div>
		    </div>	
		</div>
		
		<div class="form-group">
		  <div id="isShowTwo">
		        <label class="col-md-2 control-label">本系统险种类别*：</label>
				<div class="col-md-3 ">
				<select class="form-control " id="localType"  name="parentInsuranceTypeId" ></select>
		        </div>
		   </div>
		</div>
            <div class="modal-footer col-md-6">
            <!--用来清空表单数据-->
            <input type="reset" name="reset" style="display: none;" />
                <button type="button" class="btn btn-default" onclick="windowCloseTab()">关闭</button>
               <button id="saveButton" type="button" onclick="insTypeAdd.add()" class="btn btn-primary">保存</button>
            </div>
            </form>
          </div>
</body>
</html>