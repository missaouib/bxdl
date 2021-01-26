<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/core.jsp" %>
<!DOCTYPE  html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <title>保险产品添加</title>
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
			InsurancePro.formValidator();
		});
	
		var InsurancePro = function () {
			return{				
				 //添加
		        add:function () {
				 document.getElementById("saveButton").setAttribute("disabled", false);
		         if($("#addFormPro").data('bootstrapValidator').validate().isValid()){ 
			            	flag = true; 
			            	//jquery获取复选框值    
			            	var chk_value =[];//定义一个数组    
	         	            $('input[name="payMode"]:checked').each(function(){//遍历每一个名字为interest的复选框，其中选中的执行函数    
	         					chk_value.push($(this).val());//将选中的值添加到数组chk_value中    
	         	            });
	         	        
							if (chk_value.length<1) {
									confirm("缴费方式不能为空！");
										flag = false;
										document.getElementById("saveButton").removeAttribute("disabled");
										return false;
							}
							$('#payModeArr').val(chk_value);
	         	    
							/* 遍历table中所有name是否为空 */
					 		$('input[name=productsName]').each(function(){
								 if ($(this).val()=='') {
									confirm("产品名称不能为空！");
										flag = false;
										document.getElementById("saveButton").removeAttribute("disabled");
										return false;
									 }
							});
					 		$('input[name=productsCode]').each(function(){
								 if ($(this).val()=='') {
									confirm("产品编码不能为空！");
										flag = false;
										document.getElementById("saveButton").removeAttribute("disabled");
										return false;
									 }
							});
							$('input[name=insurancePeriodMin]').each(function(){
								 if ($(this).val()=='') {
									confirm("保险起期不能为空！");
										flag = false;
										document.getElementById("saveButton").removeAttribute("disabled");
										return false;
									 }
							});
	         	    	 
	         	    	    /* 遍历table中所有name是否为空 */
	         	            $('input[name=insurancePeriodMax]').each(function(){ 
								 if ($(this).val()=='') {
										confirm("保险止期不能为空！");
										flag = false;
										document.getElementById("saveButton").removeAttribute("disabled");
										return false;
								 }
							});
	         	    	 
	         	    	    /* 遍历table中所有name是否为空 */
	         	            $('input[name=renewPeriodMin]').each(function(){ 
	         	    	    if ($(this).val()=='') {
								confirm("缴费起期不能为空！");
									flag = false;
									document.getElementById("saveButton").removeAttribute("disabled");
									return false;
								 }
	         	    	    });
	         	    	 
	         	    	 /* 遍历table中所有name是否为空 */
	         	            $('input[name=renewPeriodMax]').each(function(){ 
								 if ($(this).val()=='') {
									confirm("缴费止期不能为空！");
										flag = false;
										document.getElementById("saveButton").removeAttribute("disabled");
										return false;
								 }
							 });
	         	    	 /* 遍历table中所有name是否为空 */
	         	     $('input[name=valueCommissionCoefficient]').each(function(){ 
	         	    	 if ($(this).val()=='') {
	         	    		confirm("价值佣金系数不能为空！");
	         	    			flag = false;
	         	    			document.getElementById("saveButton").removeAttribute("disabled");
	         	    			return false;
	         	    		 }
	         	    	 });
	         	    	 
	         	    	/* 遍历table中所有name是否为空 */
	         	     $('input[name=inStandardCommissionCoefficient]').each(function(){ 
	         	    	 if ($(this).val()=='') {
	         	    		confirm("内部标保佣金系数不能为空！");
	         	    			flag = false;	
	         	    			document.getElementById("saveButton").removeAttribute("disabled");
	         	    			return false;
	         	    		 }
	         	    	 });
	         	    	 
	         	    	/* 遍历table中所有name是否为空 */
	         	     $('input[name=outStandardCommissionCoefficient]').each(function(){ 
	         	    	 if ($(this).val()=='') {
	         	    		confirm("外部标保佣金系数不能为空！");
	         	    			flag = false;
	         	    			document.getElementById("saveButton").removeAttribute("disabled");
	         	    			return false;
	         	    		 }
	         	    	 });
	         	    	 
	         	    	/* 遍历table中所有name是否为空 */
	         	    	$('input[name=indexingCoefficient]').each(function(){ 
	         	    	 if ($(this).val()=='') {
	         	    		confirm("标保折标系数不能为空！");
	         	    			flag = false;
	         	    			document.getElementById("saveButton").removeAttribute("disabled");
	         	    			return false;
	         	    		 }
	         	    	 });
		     
			    	  if(flag){
							$.ajax({
								url:'product_basic_information/add_insurance_product',
								type:'post',
							  //  contentType: "application/json",//如果想以json格式把数据提交到后台的话，这个必须有，否则只会当做表单提交
							  //  data: JSON.stringify(insuranceProductInfo),//JSON.stringify()必须有,否则只会当做表单的格式提交
								dataType:'json',
								data:$("#addFormPro").serialize(),
								success:function(data){
									if(data){
										if(data.messageCode != "200"){
											$.alert({
												title: '提示信息！',
												content: data.data,
												type: 'red'
											});
											document.getElementById("saveButton").removeAttribute("disabled");
											return;
										}
										 $.alert({
											 title: '提示信息！',
											 content: '添加成功!',
											 type: 'blue'
										 });
										 document.getElementById("saveButton").removeAttribute("disabled");
										 windowCloseTab();
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
			          }else{
			    		  document.getElementById("saveButton").removeAttribute("disabled"); 
			          }
		         }else{
		    		    document.getElementById("saveButton").removeAttribute("disabled");
		         }
		        },
			
		        //表单验证
		        formValidator:function () {
		            $("#addFormPro").bootstrapValidator({
		                fields:{
		                	insurance:{
		                        validators:{
		                            notEmpty:{
		                                message:"保险公司不能为空"
		                            },
		                        }
		                    },
		                    productCode:{
		                        validators:{
		                            notEmpty:{
		                                message:'产品代码不能为空',
		                            },
		                            stringLength:{
		                                max:20,
		                                message:'字符长度不能超过20'
		                            }
		                        }
		                    },
		                    productName:{
		                        validators:{
		                            notEmpty:{
		                                message:"产品名称不能为空"
		                            },
		                            stringLength:{
		                                max:32,
		                                message:'不能超过32个字符长度'
		                            },
		                        }
		                    },
		                    companyInsuranceType:{
		                        validators:{
		                            notEmpty:{
		                                message:"保险公司险种类别不能为空"
		                            }
		                        }
		                    },
							insuranceType:{
		                        validators:{
		                            notEmpty:{
		                                message:"汇康险种类别不能为空"
		                            }
		                        }
		                    },
		                    companyInsuranceCode:{
		                        validators:{
		                            notEmpty:{
		                                message:'保险公司险种代码不能为空',
		                            },
		                            stringLength:{
		                                max:32,
		                                message:'字符长度不能超过32'
		                            }
		                        }
		                    } ,
							circInsuranceType:{
		                		validators:{
		                			 notEmpty:{
		                                message:'保监委险种类别不能为空',
		                            },
								}
							},
		                    insurancePeriodType:{
		                        validators:{
		                            notEmpty:{
		                                message:"保险期间方式不能为空"
		                            }
		                        }
		                    },
		                    renewalPeriodType:{
		                        validators:{
		                            notEmpty:{
		                                message:"缴费期间方式不能为空"
		                            }
		                        }
		                    },
		                    cardProType:{
		                        validators:{
		                            notEmpty:{
		                                message:"卡单产品标记不能为空"
		                            }
		                        }
		                    },
		                    autoPaySign:{
		                        validators:{
		                            notEmpty:{
		                                message:"自动续费标记不能为空"
		                            }
		                        }
		                    },
		                    mainOrAdditional:{
		                        validators:{
		                            notEmpty:{
		                                message:"主附险标记不能为空"
		                            }
		                        }
		                    },
		                    longShortRiskType:{
		                        validators:{
		                            notEmpty:{
		                                message:"长短险标记不能为空"
		                            }
		                        }
		                    },
		                    groupOrPersonal:{
		                        validators:{
		                            notEmpty:{
		                                message:"团个险标记不能为空"
		                            }
		                        }
		                    },
		                    needReceipt:{
		                        validators:{
		                            notEmpty:{
		                                message:"是否需要回执不能为空"
		                            }
		                        }
		                    },
		                    saleType:{
		                        validators:{
		                            notEmpty:{
		                                message:"发布类型不能为空"
		                            }
		                        }
		                    },
							insuranceCompanyNameSearch:{
		                		validators:{
		                			notEmpty:{
										message:"请输入公司名称查询"
									}
								}
							},
							insuranceCompanyName:{
		                		validators:{
		                			notEmpty:{
		                				message:"请选择组织架构 "
									}
								}
							},
							takeEffectDate:{
		                		validators:{
		                			notEmpty:{
		                				message:"生效日期不能为空"
									}
								},
								trigger: 'focus'
							},
							invalidDate:{
		                		validators:{
		                			notEmpty:{
		                				message:"失效日期不能为空"
									}
								},
								trigger: 'focus'
							}
		                }
		            });
		         }
				}
			}();		
			
			/* remove and add */
			 $(document).ready(function() {
			        var MaxInputs    = 8; //maximum input boxes allowed
			        var InputsWrapper  = $("#InputsWrapper"); //Input boxes wrapper ID
			        var AddButton    = $("#AddMoreFileBox"); //Add button ID
			        var x = InputsWrapper.length; //initlal text box count
			        var FieldCount=0; //to keep track of text box added
					var FieldCounts=1;
					var rowId=1;
			        $(AddButton).click(function (e) //on add input button click
			          {
			            if(x <= MaxInputs) //max input box allowed
			            {
			                FieldCount++; //text box added increment
							FieldCounts++;
							rowId++;
			                //add input box
			               // $(InputsWrapper).append('<tr id=tr'+ FieldCount +'><td> <input style=width:100px; type=\"text\" name=\"productsName\" id=\"productsName'+ FieldCount +'\" /></td><td> <input style=width:100px; type=\"text\" name=\"productsCode\" id=\"productsCode'+ FieldCount +'\" /></td><td> <input style=width:50px; type=\"text\" name=\"insurancePeriodMin\" id=\"insurancePeriodMin_'+ FieldCount +'\" /> - <input style=width:50px; type=\"text\" name=\"insurancePeriodMax\" id=\"insurancePeriodMax_'+ FieldCount +'\"/>岁</td> <td> <input style=width:50px; type=\"text\" name=\"renewPeriodMin\" id=\"renewPeriodMin_'+ FieldCount +'\" /> - <input style=width:50px; type=\"text\" name=\"renewPeriodMax\" id=\"renewPeriodMax_'+ FieldCount +'\" />年</td><td> <input style=width:100px; type=\"text\" name=\"valueCommissionCoefficient\" id=\"valueCommissionCoefficient_'+ FieldCount +'\" />%</td><td> <input style=width:100px; type=\"text\" name=\"inStandardCommissionCoefficient\" id=\"inStandardCommissionCoefficient_'+ FieldCount +'\" />%</td><td> <input style=width:100px; type=\"text\" name=\"outStandardCommissionCoefficient\" id=\"outStandardCommissionCoefficient_'+ FieldCount +'\" />%</td><td> <input style=width:100px; type=\"text\" name=\"indexingCoefficient\" id=\"indexingCoefficient_'+ FieldCount +'\" />%</td><td><a href=\"#\" rel=\"external nofollow\" rel=\"external nofollow\" rel=\"external nofollow\" class=\"removeclass\"><input  type=\"button\" value=\"删除\"></a></td><tr>');
			                $(InputsWrapper).append('<tr id=tr'+ FieldCount +'><td> <input style=width:150px; type=\"text\" name=\"productsName\" id=\"productsName'+ FieldCount +'\" /></td><td> <input style=width:150px; type=\"text\" name=\"productsCode\" id=\"productsCode'+ FieldCount +'\" /></td><td> <input style=width:60px; type=\"text\" name=\"insurancePeriodMin\" id=\"insurancePeriodMin_'+ FieldCount +'\" /> - <input style=width:60px; type=\"text\" name=\"insurancePeriodMax\" id=\"insurancePeriodMax_'+ FieldCount +'\"/>岁</td> <td> <input style=width:60px; type=\"text\" name=\"renewPeriodMin\" id=\"renewPeriodMin_'+ FieldCount +'\" /> - <input style=width:60px; type=\"text\" name=\"renewPeriodMax\" id=\"renewPeriodMax_'+ FieldCount +'\" />年</td><td><a href=\"#\" rel=\"external nofollow\" rel=\"external nofollow\" rel=\"external nofollow\" class=\"removeclass\" style="color: red"><input  type=\"button\" value=\"删除\"></a></td><tr>');
			                 x++; //text box increment
			            }
			            return false;
			        });
			        $("body").on("click",".removeclass", function(e){ //user click on remove text
			            if( x > 1 ) {
			                $(this).parent().parent().remove(); //remove text box   '<tr \"'+ FieldCount +'\">'
			                x--; //decrement textbox
			            }
			            return false;
			        })
			    });		
			    
			    $("#insuranceCompanyNameSearch").bind("input propertychange",function(event){
			    	/* 如果不为空就把值传给下拉列表 */
			    	var companyName=$("#insuranceCompanyNameSearch").val();
			    	if(companyName.replace(/(^s*)|(s*$)/g, "").length >0){
			    	    getInsuranceCompany("insurance",companyName);
						getInsuranceType("insuranceSelect", null);
			    	}
                 });
			    
			    function getInsuranceCompany(selectId,companyName){
					 $.ajax({
				         type: "POST",
				         data:{insuranceCompanyName:companyName},
				         url: "insurance_dept/findInsurCompanys",
				         dataType: "json",
				         success: function(data){
				        	var dataObject = data.rows;
							var h = "<option value=''>请选择保险公司</option>";
				            $.each(dataObject, function(key, value) {
				                var orgLevelMass = value.orgLevel;
				                if(value.orgLevel == "01"){
				                    orgLevelMass = "总公司";
								}else if (value.orgLevel == "02"){
				                    orgLevelMass = "省份公司";
								}else if (value.orgLevel == "03"){
				                    orgLevelMass = "地市公司";
								}
				                h += "<option value='" + value.insuranceCompanyId + "' orgLevelValue='"+ orgLevelMass+ "' insuranceCompanyCodeValue='"+ value.insuranceCompanyCode+ "' insuranceCompanyNamesValue='"+ value.insuranceCompanyName +"'>"  + value.insuranceCompanyName //下拉框序言的循环数据
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
					    	            	$('#insuranceCompanyCodes').val(insuranceCompanyCodeValues); 
					    	            	$('#orgLevel').val(orgLevels);
					    	            	$('#insuranceCompanyNames').val(insuranceCompanyNamesValues); 
					    	                $('#insuranceCompanyId').val($('#insurance option:selected').val());
					    	            	//根据所选择的内容给其它input框赋值
					    	                getCompanyType("insType",$('#insurance option:selected').val());
					    	            }
				    	        )
				            } 
				         }
				     });
		   }	    
		
	    
	      
	    /*通过保险公司险种类别找到对应汇康类型映射  */
		function getCompanyType(selectId,companyId){
			 $.ajax({
		         type: "POST",
		         data:{insuranceCompanyId:companyId},
		         url: "Insurance_type_manager/find_types",
		         dataType: "json",
		         success: function(data){
		        	var dataObject = data.rows;
					var h = "<option value=''>请选择保险公司险种类</option>";
		            $.each(dataObject, function(key, value) {
		                h += "<option value='" + value.insuranceTypeCode + "' parentInsuranceTypeId='"+ value.parentInsuranceTypeId +"'>"  + value.insuranceTypeName //下拉框序言的循环数据
		                + "</option>"; 
		            });
		            $("#"+selectId).empty();
		            $("#"+selectId).append(h);
		             if(selectId="insType"){
			            $("#"+selectId).on(  
			    	            "change",function(){
			    	            	var pId = $("#insType").find("option:selected").attr("parentInsuranceTypeId");
			    	            /* 	$('#orgLevel').val(orgLevels); 
			    	            	$('#insuranceCompanyNames').val(insuranceCompanyNamesValues); 
			    	                $('#insuranceCompanyId').val($('#insurance option:selected').val()); */
			    	            	//根据所选择的内容给其它input框赋值
		    	      		    	    getInsuranceType("insuranceSelect",pId);
			    	            }
		    	        )
		            } 
		         }
		     });
		 }
	    
		function getInsuranceType(selectId,pId){
			  $.ajax({
		         type: "POST",
		         data:{parentInsuranceTypeId:pId},
		         url: "Insurance_type_manager/find_types",
		         dataType: "json",
		         success: function(data){
		        	var dataObject = data.rows;
					var h = "<option value=''>请选择汇康险种类别</option>";
		            $.each(dataObject, function(key, value) {
		            	  h += "<option value='" + value.insuranceTypeCode + "' parentInsuranceTypeId='"+ value.parentInsuranceTypeId +"'>"  + value.insuranceTypeName //下拉框序言的循环数据
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
		<form class="form-horizontal" id="addFormPro"  method="post"  >
		<input type="hidden" class="form-control form-control-static" id="payModeArr"  name="payModeArr" />
		<input type="hidden" class="form-control form-control-static" id="insuranceCompanyId"  name="insuranceCompanyId" />
		<div class="form-group" style="margin-top:20px;"></div>	
		<label class="col-md-2 control-label">保险公司基本信息：</label>	</br></br>
		
		<div class="form-group">
			<label class="col-md-2 control-label">组织架构名称 *：</label>
			<div class="col-md-3">
				<input type="text" class="form-control form-control-static" id="insuranceCompanyNameSearch"  name="insuranceCompanyNameSearch"  placeholder="请输入公司名称查询"/>
			</div>
			<label class="col-md-2 control-label">请选择组织架构 *：</label>
			<div class="col-md-3 ">
			<select class="form-control " id="insurance"  name="insuranceCompanyName" ></select>
			</div>
			
		</div>		
			<div class="form-group">
               <label class="col-md-2 control-label">组织架构代码 *：</label>
				<div class="col-md-3 ">
				<input type="text" class="form-control form-control-static" id="insuranceCompanyCodes"  name="insuranceCompanyCode"  readonly="readonly" />
				</div>
				 <label class="col-md-2 control-label">组织架构级别 *：</label>
				<div class="col-md-3 ">
				<input type="text" class="form-control form-control-static" id="orgLevel"  name="orgLevel"  readonly="readonly"/>
				</div>
			</div>		
			
			<div class="form-group">
				 <label class="col-md-2 control-label">保险公司名称 *：</label>
				<div class="col-md-3 ">
				<input type="text"  class="form-control form-control-static" id="insuranceCompanyNames"  name="insuranceCompanyNames" readonly="readonly"></select>
				</div>
			</div>		
					
			<label class="col-md-2 control-label">产品基本信息：</label></br></br>
			<div class="form-group">
				<label class="col-md-2 control-label">产品名称 *：</label>
				<div class="col-md-3 ">
					<input type="text"  id="productName"  name="productName" class="form-control form-control-static" placeholder="请输入产品名称">
				</div>

				<label class="col-md-2 control-label">产品代码 *：</label>
				<div class="col-md-3 ">
					<input type="text" id="productCode"  name="productCode" class="form-control form-control-static" placeholder="请输入产品代码">
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-md-2 control-label">产品简称：</label>
				<div class="col-md-3 ">
					<input type="text" id="productNameLess"  name="productNameLess" class="form-control form-control-static" placeholder="请输入产品简称">
				</div>
				
				<label class="col-md-2 control-label">产品英文名：</label>
				 <div class="col-md-3 ">
				 <input type="text" id="productEnName"  name="productEnName" class="form-control form-control-static" placeholder="请输入产品英文名">
             	</div>
			</div>
			
			<div class="form-group">
		    	<label class="col-md-2 control-label">保险公司险种类别 *：</label>
				<div class="col-md-3 ">
					<select class="form-control form-control-static" id="insType" name="companyInsuranceType" >
					</select>			
				<!-- 	<input type="text" id="companyInsuranceType"  name="companyInsuranceType" class="form-control form-control-static" > -->
				</div>	
				
			<label class="col-md-2 control-label">汇康险种类别 *：</label>
              <div class="col-md-3 ">
					<select class="form-control form-control-static" id="insuranceSelect" name="insuranceType">
                      <!--   <option value="">请选择</option>
                        <option value="0">健康险</option>
                        <option value="1">年金险</option>
                        <option value="2">车险</option>
                        <option value="3">非车险</option> -->
             		</select>				
             	</div>
			</div> 
			
			<div class="form-group">
				<label class="col-md-2 control-label">保险公司险种代码 *：</label>
				<div class="col-md-3 ">
					<input type="text" id="companyInsuranceCode"  name="companyInsuranceCode" class="form-control form-control-static" placeholder="请输入保险公司险种代码">
				</div>
			<label class="col-md-2 control-label">保监委险种类别 *：</label>
				 <div class="col-md-3 ">
				 <input type="text" id="circInsuranceType"  name="circInsuranceType" class="form-control form-control-static" placeholder="请输入保监委险种类别">
             	</div>
			</div> 
			
			
			<div class="form-group">
			<label class="col-md-2 control-label">保险期间方式 *：</label>
				 <div class="col-md-3 ">
					<select class="form-control form-control-static" id="insurancePeriodType" name="insurancePeriodType">
                        <option value="">请选择</option>
                        <option value="0">终身</option>
                        <option value="1">N年</option>
                        <option value="2">1年</option>
             		</select>				
             	</div>
             	
			<label class="col-md-2 control-label">缴费期间方式 *：</label>
              <div class="col-md-3 ">
					<select class="form-control form-control-static" id="renewalPeriodType" name="renewalPeriodType">
                        <option value="">请选择</option>
                        <option value="0">N年</option>
             		</select>				
             	</div>
			</div>	
			
			<div class="form-group">
			<label class="col-md-2 control-label">卡单产品标记 *：</label>
				 <div class="col-md-3 ">
					<select class="form-control form-control-static" id="cardProType" name="cardProType">
                        <option value="">请选择</option>
                        <option value="0">否</option>
                        <option value="1">是</option>
             		</select>				
             	</div>
             	
             	<label class="col-md-2 control-label">自动续保标记 *：</label>
              <div class="col-md-3 ">
					<select class="form-control form-control-static" id="autoPaySign" name="autoPaySign">
                        <option value="">请选择</option>
                        <option value="0">否</option>
                        <option value="1">是</option>
             		</select>				
             	</div>
				
			</div>	
			
		<div class="form-group">
			<label class="col-md-2 control-label">主附险标记 *：</label>
              <div class="col-md-3 ">
					<select class="form-control form-control-static" id="mainOrAdditional" name="mainOrAdditional">
                        <option value="">请选择</option>
                        <option value="0">主险</option>
                        <option value="1">附险</option>
             		</select>				
             	</div> 
			
		   <label class="col-md-2 control-label">长短险标记 *：</label>
              <div class="col-md-3 ">
					<select class="form-control form-control-static" id="longShortRiskType" name="longShortRiskType">
                        <option value="">请选择</option>
                        <option value="0">短期险</option>
                        <option value="1">长期险</option>
             		</select>				
             	</div> 	
		</div>		
		
		<div class="form-group">
			<label class="col-md-2 control-label">最大续保年龄：</label>
				<div class="col-md-3 ">
					<input type="text" id="maxRenewalYears"  name="maxRenewalYears" class="form-control form-control-static" placeholder="请输入最大续保年龄">
				</div>
		
			<!-- <label class="col-md-2 control-label">特殊授权标记*：</label>
				 <div class="col-md-3 ">
					<select class="form-control form-control-static" id="specialAuthorization" name="specialAuthorization">
                        <option value="">请选择</option>
                        <option value="0">销售需要对销售人员特殊授权</option>
                        <option value="1">财险</option>
             		</select>				
             	</div> -->
             <label class="col-md-2 control-label">保监会备案代码：</label>
				<div class="col-md-3 ">
					<input type="text" id="circRecordCode"  name="circRecordCode" class="form-control form-control-static" placeholder="请输入保监会备案代码">
				</div>	
			</div>		
			
			<div class="form-group">
				<label class="col-md-2 control-label">生效日期 *：</label>
             	<div class="col-md-3 ">		
             		<input type="text" id="takeEffectDate" value="2019-01-01"  name="takeEffectDate" class="form-control form-control-static"  placeholder="请选择生效时间" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})">
				</div>
				
				<label class="col-md-2 control-label">失效日期 *：</label>
				<div class="col-md-3 ">
					<input type="text" id="invalidDate" value="2019-12-31"  name="invalidDate" class="form-control form-control-static"  placeholder="请选择失效时间" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})">
				</div>
            </div>
             
            <div class="form-group"> 
            	<label class="col-md-2 control-label">团个险标记 *：</label>
             	<div class="col-md-3 ">		
             		<select class="form-control form-control-static" id="groupOrPersonal" name="groupOrPersonal">
                        <option value="">请选择</option>
                        <option value="0">团体购买险种</option>
                        <option value="1">个人购买险种</option>
             		</select>			
				</div>
				
			<label class="col-md-2 control-label">是否需要回执 *：</label>
              <div class="col-md-3 ">
					<select class="form-control form-control-static" id="needReceipt" name="needReceipt">
                        <option value="">请选择</option>
                        <option value="0">否</option>
                        <option value="1">是</option>
             		</select>				
             	</div> 
			</div>
				
			<div class="form-group">
				<label class="col-md-2 control-label">缴费方式 *：</label>
				<input type="checkbox" name="payMode" value="0"> 年交  
				<input type="checkbox" name="payMode" value="1"> 半年交
				<input type="checkbox" name="payMode" value="2"> 季交 
				<input type="checkbox" name="payMode" value="3"> 月交
				<input type="checkbox" name="payMode" value="4"> 趸交
				<input type="checkbox" name="payMode" value="5"> 三年交  
			</div>
			
	<label class="col-md-1 control-label">保险期间 *：</label><br/>

<div  >
	<table id="InputsWrapper" border="1" >
		   <tr  style="background: silver;">
			<td width="14%" ><label>产品名称</label></td>
		 	<td width="14%" >产品编码</td>
		    <td width="13%" >保险期间</td>
		    <td width="13%" >缴费期间</td>
		    <%--<td width="14%" >价值佣金系数(%)</td>
		    <td width="14%" >内部标保佣金系数(%)</td>
		    <td width="14%" >外部标保佣金系数(%)</td>
		    <td width="11%" >标保折标系数(%)</td>--%>
		    <td width="11%">操作</td>
		  </tr>
		  <tr >
		    <td  ><input style=width:150px;  type="text" id="productsName"  name="productsName" placeholder="请输入产品名称" ></td>
		    <td  ><input style=width:150px;  type="text" id="productsCode"  name="productsCode" placeholder="请输入产品编码" ></td>
		    <td  ><input style=width:60px;  type="text" id="insurancePeriodMin"  name="insurancePeriodMin">
		    	 - <input style=width:60px;  type="text" id="insurancePeriodMax"  name="insurancePeriodMax">岁
		    </td>
		    <td><input style=width:60px; type="text" id="renewPeriodMin"  name="renewPeriodMin"> -
		    	<input style=width:60px; type="text" id="renewPeriodMax" name="renewPeriodMax">年</td>
		<%--    <td ><input style=width:100px; type="text" id="valueCommissionCoefficient"  name="valueCommissionCoefficient"placeholder="请输入价值佣金系数" >%</td>
		    <td ><input style=width:100px; type="text" id="inStandardCommissionCoefficient"  name="inStandardCommissionCoefficient"  placeholder="请输入内部标保佣金系数" >%</td>
		    <td ><input style=width:100px; type="text" id="outStandardCommissionCoefficient"  name="outStandardCommissionCoefficient"  placeholder="请输入外部标保佣金系数" >%</td>
		    <td ><input style=width:100px; type="text" id="indexingCoefficient"  name="indexingCoefficient"  placeholder="请输入标保折标系数" >%</td>	--%>
		    <td><a href="#" rel="external nofollow" rel="external nofollow" rel="external nofollow" id="AddMoreFileBox" class="btn btn-info">添加</a></td>
		  </tr>
	</table>
</div>
						
		<label class="col-md-1 control-label">说明：</label><br />		
		   <div class="form-group">
				<label class="col-md-2 control-label">保障范围说明：</label>
				<div class="col-md-3 ">
				 
					<input  type="textarea" style="width:700px; height:60px"  id="insuranCoverExplain"  name="insuranCoverExplain" class="form-control form-control-static" placeholder="请输入保障范围说明">  
				</div>
			</div>			
									
			<label class="col-md-1 control-label"></label>	
			 <div class="form-group">
				<label class="col-md-2 control-label">基本责任说明：</label>
				<div class="col-md-3 ">
					<input  type="textarea" style="width:700px; height:60px"  id="basicResponsibilitiesExplain"  name="basicResponsibilitiesExplain" class="form-control form-control-static" placeholder="请输入基本责任说明">  
				</div>
			</div>	
			
			<label class="col-md-1 control-label"></label>	
			 <div class="form-group">
				<label class="col-md-2 control-label">投保人限制说明：</label>
				<div class="col-md-3 ">
					<input  type="textarea" style="width:700px; height:60px"  id="polocyHolderRestrictions"  name="polocyHolderRestrictions" class="form-control form-control-static" placeholder="请输入投保人限制说明">  
				</div>
			</div>	
			
			<label class="col-md-1 control-label"></label>	
			 <div class="form-group">
				<label class="col-md-2 control-label">保险责任免责说明：</label>
				<div class="col-md-3 ">
					<input  type="textarea" style="width:700px; height:60px"  id="exemptionInstruction"  name="exemptionInstruction" class="form-control form-control-static" placeholder="请输入保险责任免责说明">  
				</div>
			</div>	
					
			<label class="col-md-1 control-label"></label>	
			 <div class="form-group">
				<label class="col-md-2 control-label">备注说明：</label>
				<div class="col-md-3 ">
					<input  type="textarea" style="width:700px; height:60px"  id="remark"  name="remark" class="form-control form-control-static" placeholder="请输入备注说明">  
				</div>
			</div>	
			<label class="col-md-1 control-label"></label>	
			 <div class="form-group">
				<label class="col-md-2 control-label">发布类型 *：</label>
             	<div class="col-md-3 ">		
             		<select class="form-control form-control-static" id="saleType" name="saleType">
                        <option value="">请选择</option>
                        <option value="0">线下</option>
                        <option value="1">线上</option>
                        <option value="2">二者皆可</option>
             		</select>			
				</div>
			</div>	
																																										
            <div class="modal-footer col-md-6">
            <!--用来清空表单数据-->
            <input type="reset" name="reset" style="display: none;" />
                <button type="button" class="btn btn-default" onclick="windowCloseTab()">关闭</button>
               <button id="saveButton" type="button" onclick="InsurancePro.add()" class="btn btn-primary">保存</button>
            </div>
            </form>
            </div>
</body>
</html>