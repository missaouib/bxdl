<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/core.jsp" %>
<!DOCTYPE  html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <title>新增组织机构</title>
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Cache" content="no-cache">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   	<link rel="stylesheet" href="${path}/css/system/role/role.css">
    <script src="${path}/static/js/bootstrap.js"></script>
	<script type="text/javascript">	
		$(function () {	
			getAreaByPid("0","sheng");
			getInsuranceCompany("insuranceCode","01","");
			InsuranceOrg.formValidator();
			
			var areaCode = $("#areaCode").val();
			var insuranceCode_hide = $("#insuranceCode_hide").val();
			var parentCompanyCode_hide = $("#parentCompanyCode_hide").val();
			setTimeout(function(){ 
				$("#sheng option").each(function(){
					//alert($(this).val());
			        if($(this).val()==areaCode.substring(0,6)){
			            $(this).attr("selected", true);
			        }
				});
				
				$("#insuranceCode option").each(function(){
					//alert($(this).val());
			        if($(this).val()==insuranceCode_hide){
			            $(this).attr("selected", true);
			        }
				});
				
				var getlevelindex = $("#orgLevel").val();
				if(getlevelindex=="02"){getlevelindex="01";}else if(getlevelindex=="03"){getlevelindex="02";}else if(getlevelindex=="04"){getlevelindex="03";}
				getInsuranceCompany("parentCompanyCode",getlevelindex,insuranceCode_hide);
				setTimeout(function(){ 
					$("#parentCompanyCode option").each(function(){
						//alert($(this).val());
				        if($(this).val()==parentCompanyCode_hide){
				            $(this).attr("selected", true);
				        }
					});
				},100);	
				
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
			
			$("#orgLevel").on(  
    	            "change",function(){
						var getInsuranceCode = $("#insuranceCode").val();
						if(getInsuranceCode==""){
							alert("请先选择保险公司");
							$(this).val("");
						}else{
							var getlevel = $(this).val();
							if(getlevel=="02"){getlevel="01";}else if(getlevel=="03"){getlevel="02";}else if(getlevel=="04"){getlevel="03";}
							getInsuranceCompany("parentCompanyCode",getlevel,getInsuranceCode);
						}
    	            }
	        )
		});
	
		function getInsuranceCompany(selectId,orgLevel,indexCode){
			 $.ajax({
		         type: "POST",
		         url: "insurance_dept/findInsurCompanys",
		         data:{orgLevel:orgLevel,indexCode:indexCode},
		         dataType: "json",
		         success: function(data){
		        	//alert(data.rows);
		        	var insuranceCompanys = data.rows;
					var h = "<option value=''>请选择保险公司</option>";
					if(selectId=="parentCompanyCode"){h="<option value=''>请选择上级组织机构</option>"}
					if(selectId=="parentCompanyCode" && insuranceCompanys.length==0){
						alert("所选择的公司级别无上级机构，请先创建上级机构！");
						$("#orgLevel").val("");
						return false;
					}
		            $.each(insuranceCompanys, function(key, value) {
		            	//alert(value.insuranceCompanyCode);
		                h += "<option value='" + value.insuranceCompanyCode + "'>" + value.insuranceCompanyName //下拉框序言的循环数据
		                + "</option>";  
		            });
		            $("#"+selectId).empty();
		            $("#"+selectId).append(h);
		            if(selectId="insuranceCode"){
			            $("#"+selectId).on(  
			    	            "change",function(){
									var getlevel = $("#orgLevel").val();
									if(getlevel=="02"){getlevel="01";}else if(getlevel=="03"){getlevel="02";}else if(getlevel=="04"){getlevel="03";}
									if(getlevel!=""){
										getInsuranceCompany("parentCompanyCode",getlevel,$(this).val());
									}
			    	            }
		    	        )
		            }
		         }
		     });
		}
		function number(){
			var parentCode = $("#parentCompanyCode").val();
			var codess = $("#insuranceCompanyCode").val();
			var showCheap=document.getElementById("showCheap");
			var el =new RegExp("^"+"("+parentCode+")");
			Judge(codess,el,showCheap)
		}
		function Judge(k,m,n){
			//k 是被判断的元素
			//m是正则表达式，用来判断
			//n是警告的显示元素
			var patrn=m;
			if (patrn.exec(k)) {
				$("#saveButton").attr("disabled", false);
				n.setAttribute("style","display:none;");

			} else{
				if(k==""){
					$("#saveButton").attr("disabled", false);
					n.setAttribute("style","display:none;");

				}else{
					$("#saveButton").attr("disabled", true);
					$("#showCheap").html("请以"+$("#parentCompanyCode").val()+"开头");
					n.setAttribute("style","display:inline;");
					n.setAttribute("style","color:red;font-size:10px;");
				}
			}
		}
		var InsuranceOrg = function () {
			return{				
				 //添加
		        add:function () {
		        	document.getElementById("saveButton").setAttribute("disabled", true);
		            if($("#insuranceOrg_addForm").data('bootstrapValidator').validate().isValid()){
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
			                    data:$("#insuranceOrg_addForm").serialize(),
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
		            $("#insuranceOrg_addForm").bootstrapValidator({
		                fields:{
		                	insuranceCode:{
		                        validators:{
		                            notEmpty:{
		                                message:"保险公司名称不能为空"
		                            }
		                        }
		                    },
		                    insuranceCompanyName:{
		                        validators:{
		                            notEmpty:{
		                                message:"组织机构名称不能为空"
		                            },
		                            stringLength:{
		                                max:128,
		                                message:'不能超过128个字符长度'
		                            },
		                        }
		                    },	
		                    orgLevel:{
		                        validators:{
		                            notEmpty:{
		                                message:"组织机构级别不能为空"
		                            }
		                        }
		                    },	
		                    parentCompanyCode:{
		                        validators:{
		                            notEmpty:{
		                                message:"组织机构级别不能为空"
		                            }
		                        }
		                    },		                    
		                    insuranceCompanyCode:{
		                        validators:{
		                            notEmpty:{
		                                message:'组织机构代码不能为空',
		                            },
		                            stringLength:{
		                                max:32,
		                                message:'字符长度不能超过32'
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
			<form class="form-horizontal" id="insuranceOrg_addForm"  method="post">
			<div class="form-group" style="margin-top:20px;">
			<input type="hidden" id="insuranceCompanyId" name="insuranceCompanyId" value="${insurancedept.insuranceCompanyId}" />
			<input type="hidden" id="insuranceCode_hide" name="insuranceCode_hide" value="${insurancedept.insuranceCode}" />
			<input type="hidden" id="parentCompanyCode_hide" name="parentCompanyCode_hide" value="${insurancedept.parentCompanyCode}" />
			</div>			
			<div class="form-group">
				<label class="col-md-2 control-label">保险公司名称：</label>
				<div class="col-md-3 ">
					<select class="form-control form-control-static" disabled="disabled" id="insuranceCode" name="insuranceCode">
                        <option value="">请选择保险公司</option>
             		</select>
				</div>

				<label class="col-md-2 control-label">组织机构名称：</label>
				<div class="col-md-3 ">
					<input type="text" disabled="disabled" id="insuranceCompanyName"  name="insuranceCompanyName" value="${insurancedept.insuranceCompanyName}" class="form-control form-control-static" placeholder="请输入组织机构名称">
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-2 control-label">组织机构级别：</label>
				<div class="col-md-3 ">
					<select class="form-control form-control-static" disabled="disabled" id="orgLevel" name="orgLevel">
                        <option value="">请选择组织机构级别</option>
                        <!-- <option value="01">总公司</option>  -->
                        <option value="01" <c:if test="${insurancedept.orgLevel=='01'}"> selected="selected"</c:if> >总公司</option>
                        <option value="02" <c:if test="${insurancedept.orgLevel=='02'}"> selected="selected"</c:if> >省份公司</option>
                        <option value="03" <c:if test="${insurancedept.orgLevel=='03'}"> selected="selected"</c:if> >地市公司</option>
             		</select>
				</div>

				<label class="col-md-2 control-label">上级组织机构：</label>
				<div class="col-md-3 ">
					<select class="form-control form-control-static" disabled="disabled" id="parentCompanyCode" name="parentCompanyCode">
                        <option value="">请选择上级组织机构</option>
             		</select>				
             	</div>
			</div>
			<div class="form-group">
			
				<label class="col-md-2 control-label">组织机构代码：</label>
				<div class="col-md-3 ">
					<input type="text" id="insuranceCompanyCode" disabled="disabled" name="insuranceCompanyCode"  value="${insurancedept.insuranceCompanyCode}" class="form-control form-control-static" placeholder="请输入组织机构代码"onkeyup="number()" />
					<span style="display:none;" id="showCheap">格式错误</span>
				</div>
			
				<label class="col-md-2 control-label">组织机构简称：</label>
				<div class="col-md-3 ">
					<input type="text" id="insuranceCompanyNameLess" disabled="disabled" name="insuranceCompanyNameLess" value="${insurancedept.insuranceCompanyNameLess}" class="form-control form-control-static" placeholder="请输入组织机构简称">
             	</div>
			</div>
			<div class="form-group">
				<label class="col-md-2 control-label">组织机构英文名：</label>
               <div class="col-md-3">
                   <input class="form-control form-control-static" disabled="disabled" id="insuranceCompanyEnName" value="${insurancedept.insuranceCompanyEnName}" name="insuranceCompanyEnName" type="text" placeholder="请选择英文名" />
               </div>

				<label class="col-md-2 control-label">组织机构英文简称：</label>
				<div class="col-md-3 ">
					<input type="text" id="insuranceCompanyEnNameLess" disabled="disabled" name="insuranceCompanyEnNameLess" value="${insurancedept.insuranceCompanyEnNameLess}" class="form-control form-control-static" placeholder="请输入英文简称">
				</div>
			</div>	
			<div class="form-group">
				<input type="hidden"  id="areaCode"  name="areaCode" value="${insurancedept.areaCode}"  class="form-control form-control-static" placeholder="请输入区域编码">
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
				<label class="col-md-2 control-label">公司传真：</label>
				<div class="col-md-3 ">
					<input type="text" disabled="disabled" id="fax"  name="fax" value="${insurancedept.fax}" class="form-control form-control-static" placeholder="请输入公司传真">
				</div>

				<label class="col-md-2 control-label">公司电话：</label>
				<div class="col-md-3 ">
					<input type="text" disabled="disabled" id="tel"  name="tel" value="${insurancedept.tel}" class="form-control form-control-static" placeholder="请输入公司电话">
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