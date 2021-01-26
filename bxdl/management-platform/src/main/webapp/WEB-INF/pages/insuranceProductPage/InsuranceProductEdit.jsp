<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/core.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE>
<html>
<head>
 <title>保险产品修改</title>
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Cache" content="no-cache">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   	<link rel="stylesheet" href="${path}/css/system/role/role.css">
   	<link rel="stylesheet" href="${path}/css/product/product.css">
    <script src="${path}/static/js/bootstrap.js"></script>
    <script src="${path}/js/product/format.js"></script>
   
	<script type="text/javascript">	
		$(function () {	
			InsProEdit.formValidator();
			
			setTimeout(function(){
		    	var companyName = $("#insuranceCompanyNames").val();
		    	$("#insuranceCompanyNameSearch").val(companyName);
		    	if(companyName.replace(/(^s*)|(s*$)/g, "").length >0){
		    	    getInsuranceCompany("insuranceProEdit",companyName);
		    	}		    	
		    	
		    	setTimeout(function(){
		    		getCompanyType("companyInsuranceType",$("#insuranceProEdit").val());
		    		var comInsType = $("#companyInsuranceType_hide").val();
		    		//alert(comInsType);
		    		setTimeout(function(){
		    			$("#companyInsuranceType").val(comInsType);
		    			setTimeout(function(){
		    				var pId = $("#companyInsuranceType").find("option:selected").attr("parentInsuranceTypeId");
    	      		        getInsuranceType("insuranceType",pId);
		    			},100);
		    		},100);
		    	},300);
            });
		
			//回显示被选中项
	        var boxObj = document.getElementsByName("payMode");  //获取所有的复选框
		 	  var payment = '${insuranceProductInfo.payMode}'; //用el表达式获取在控制层存放的复选框的值为字符串类型
		 	  var pay = payment.split(',');    //去掉它们之间的分割符“，”  
		    	  var index = 0;
				  for(i=0;i<boxObj.length;i++){
					for(j=0;j<pay.length;j++){            
				  		if(boxObj[i].value == pay[j])  //如果值与修改前的值相等
				  		{
			      			boxObj[i].checked= true;
			     			 break;
			  			}
					}
				}
				  
		});
	
		var InsProEdit = function () {
			return{				
				 //修改
		        add:function () {
		        	document.getElementById("saveButton").setAttribute("disabled", false);
		             if($("#editFormProduct").data('bootstrapValidator').validate().isValid()){ 
		            	flag = true; 
		            	//jquery获取复选框值    
		            	var chk_value =[];//定义一个数组    
         	            $('input[name="payMode"]:checked').each(function(){//遍历每一个名字为interest的复选框，其中选中的执行函数    
         	            chk_value.push($(this).val());//将选中的值修改到数组chk_value中    
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
			                    url:'product_basic_information/update_insurance_product',
			                    type:'post',
			                    dataType:'json',
			                    data:$("#editFormProduct").serialize(),
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
			                                 content: '修改成功!',
			                                 type: 'blue'
			                             });
			                 		     document.getElementById("saveButton").removeAttribute("disabled");
			                 		     windowCloseTab();
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
			        	}else{
			    		     document.getElementById("saveButton").removeAttribute("disabled"); 
			            } 
		             }else{
		    		     document.getElementById("saveButton").removeAttribute("disabled"); 
		            } 
		        },
			
		        //表单验证
		        formValidator:function () {
		             $("#editFormProduct").bootstrapValidator({
		                fields:{ 
		                	insuranceProEdit:{
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
		                    }, insuranceCompanyNameSearch:{
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
							circInsuranceType:{
		                		validators:{
		                			 notEmpty:{
		                                message:'保监委险种类别不能为空',
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
			        var InputsEdit  = $("#InputsEdit"); //Input boxes wrapper ID
			        var AddButton    = $("#AddMoreEditBox"); //Add button ID
			        var x = InputsEdit.length; //initlal text box count
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
							 //$(InputsEdit).append('<tr id=tr'+ FieldCount +'><td style="display:none"><input type="hidden" value="0" name="serialNumber"></td><td><input style=width:100px; type=\"text\" name=\"productsName\" id=\"productsName'+ FieldCount +'\" /></td><td> <input style=width:100px; type=\"text\" name=\"productsCode\" id=\"productsCode'+ FieldCount +'\" /></td><td> <input style=width:50px; type=\"text\" name=\"insurancePeriodMin\" id=\"insurancePeriodMin_'+ FieldCount +'\" /> - <input style=width:50px; type=\"text\" name=\"insurancePeriodMax\" id=\"insurancePeriodMax_'+ FieldCount +'\"/>岁</td> <td> <input style=width:50px; type=\"text\" name=\"renewPeriodMin\" id=\"renewPeriodMin_'+ FieldCount +'\" /> - <input style=width:50px; type=\"text\" name=\"renewPeriodMax\" id=\"renewPeriodMax_'+ FieldCount +'\" />年</td><td> <input style=width:100px; type=\"text\" name=\"valueCommissionCoefficient\" id=\"valueCommissionCoefficient_'+ FieldCount +'\" /></td><td> <input style=width:100px; type=\"text\" name=\"inStandardCommissionCoefficient\" id=\"inStandardCommissionCoefficient_'+ FieldCount +'\" /></td><td> <input style=width:100px; type=\"text\" name=\"outStandardCommissionCoefficient\" id=\"outStandardCommissionCoefficient_'+ FieldCount +'\" /></td><td> <input style=width:100px; type=\"text\" name=\"indexingCoefficient\" id=\"indexingCoefficient_'+ FieldCount +'\" /></td><td><a href=\"#\" rel=\"external nofollow\" rel=\"external nofollow\" rel=\"external nofollow\" class=\"removeclass\"><input  type=\"button\" value=\"删除\"></a></td><tr>');
			                $(InputsEdit).append('<tr id=tr'+ FieldCount +'><td style="display:none"><input type="hidden" value="0" name="serialNumber"></td><td><input style=width:150px; type=\"text\" name=\"productsName\" id=\"productsName'+ FieldCount +'\" /></td><td> <input style=width:150px; type=\"text\" name=\"productsCode\" id=\"productsCode'+ FieldCount +'\" /></td><td> <input style=width:60px; type=\"text\" name=\"insurancePeriodMin\" id=\"insurancePeriodMin_'+ FieldCount +'\" /> - <input style=width:60px; type=\"text\" name=\"insurancePeriodMax\" id=\"insurancePeriodMax_'+ FieldCount +'\"/>岁</td> <td> <input style=width:60px; type=\"text\" name=\"renewPeriodMin\" id=\"renewPeriodMin_'+ FieldCount +'\" /> - <input style=width:60px; type=\"text\" name=\"renewPeriodMax\" id=\"renewPeriodMax_'+ FieldCount +'\" />年</td><td><a href=\"#\" rel=\"external nofollow\" rel=\"external nofollow\" rel=\"external nofollow\" class=\"removeclass\"><input  type=\"button\" value=\"删除\"></a></td><tr>');
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
				            	var insuranceCompanyId = $("#insuranceCompanyId").val();
				            	if(value.insuranceCompanyId == insuranceCompanyId){
					                h += "<option value='" + value.insuranceCompanyId + "' orgLevelValue='"+ value.orgLevel+ "' insuranceCompanyCodeValue='"+ value.insuranceCompanyCode+ "' insuranceCompanyNamesValue='"+ value.insuranceCompanyName +"' selected='selected'>"  + value.insuranceCompanyName //下拉框序言的循环数据
					                + "</option>"; 
				            	}else{
					                h += "<option value='" + value.insuranceCompanyId + "' orgLevelValue='"+ value.orgLevel+ "' insuranceCompanyCodeValue='"+ value.insuranceCompanyCode+ "' insuranceCompanyNamesValue='"+ value.insuranceCompanyName +"'>"  + value.insuranceCompanyName //下拉框序言的循环数据
					                + "</option>"; 				            		
				            	}
				            });
				            $("#"+selectId).empty();
				            $("#"+selectId).append(h);
				            $("#"+selectId).attr("disabled","disabled");
				         }
				     });
				}
			    
			    /*通过保险公司险种类别找到对应汇康类型映射  */
				function getCompanyType(selectId,companyId){
			    	 //alert(companyId);
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
				            if(selectId="companyInsuranceType"){
					            $("#"+selectId).on(  
					    	            "change",function(){
					    	            	var pId = $("#companyInsuranceType").find("option:selected").attr("parentInsuranceTypeId");
				    	      		        getInsuranceType("insuranceType",pId);
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
							var h = "";
				            $.each(dataObject, function(key, value) {
				            	  h += "<option value='" + value.insuranceTypeCode + "' parentInsuranceTypeId='"+ value.parentInsuranceTypeId +"'>"  + value.insuranceTypeName //下拉框序言的循环数据
					                + "</option>"; 
				            });
				            $("#"+selectId).empty();
				            $("#"+selectId).append(h);
				         }
				     }); 
				}
			    
			 /*删除子产品  */    
			 function delRowProduct(productsRatioId,productId){
				 var mymessage = confirm("确认删除吗？");
					if (mymessage == true) {
						/* 查询  */
						$.ajax({
							url : "product_basic_information/check_renew_period_size",
							type : "post",
							dataType : "json",
							data : {
								productId : productId
							},
							success : function(size) {
								/* alert(size); */
								 if (size >1) {
									 /* 删除 */
										$.ajax({
											url : "product_basic_information/update_renew_period_status",
											type : "post",
											dataType : "json",
											data : {
												productsRatioId : productsRatioId
											},
											success : function(msg) {
												 if (msg.messageCode == "200") {
													 $.alert({
								                            title: '提示信息！',
								                            content: '删除成功！',
								                            type: 'red'
								                        });
													/*  parent.createTab('${app}/goldProduct/toUpdateGoldProduct/' + productId, '修改信息'); */
												 } else {
													 $.alert({
								                            title: '提示信息！',
								                            content: '请求失败！',
								                            type: 'red'
								                        });
													
											} 
										},
									});  
									
								} else {
									$.alert({
			                            title: '提示信息！',
			                            content: '至少保留一条数据！',
			                            type: 'red'
			                        });
							}
						},
						error : function() {
							$.messager.show({
								title : '提示',
								msg : '系统错误，请联系管理员------'
							});
						}
					});	 
					}	
				}
	</script> 
</head>
<body>
        <div class="container">
		<form class="form-horizontal" id="editFormProduct"  method="post"  >
		<input type="hidden" class="form-control form-control-static" id="payModeArr"  name="payModeArr" />
		<input type="hidden" class="form-control form-control-static" value='${insuranceProductDetail.infoId}' id="infoId"  name="infoId" />
		<input type="hidden" class="form-control form-control-static" value='${insuranceProductInfo.productId}' id="productId"  name="productId" />
		<input type="hidden" class="form-control form-control-static" value='${insuranceProductInfo.insuranceCompanyId}' id="insuranceCompanyId"  name="insuranceCompanyId" />
		<div class="form-group" style="margin-top:20px;"></div>	
		<label class="col-md-2 control-label">保险公司基本信息：</label>	</br></br>
		
		<div class="form-group">
			<label class="col-md-2 control-label">组织架构名称*：</label>
			<div class="col-md-3">
				<input type="text" class="form-control form-control-static" readonly="readonly" id="insuranceCompanyNameSearch"  name="insuranceCompanyNameSearch" placeholder="请输入组织架构名称查询"/>
			</div>
		
			<label class="col-md-2 control-label">请选择组织架构*：</label>
			<div class="col-md-3 ">
			<select class="form-control " id="insuranceProEdit"  name="insuranceCompanyName" readonly="readonly"></select>
			</div>
			
		</div>		
			<div class="form-group">
               <label class="col-md-2 control-label">组织架构代码*：</label>
				<div class="col-md-3 ">
				<input type="text" value='${insuranceDept.insuranceCompanyCode}' class="form-control form-control-static" id="insuranceCompanyCodes"  name="insuranceCompanyCode"  readonly="readonly" />
				</div>
				 <label class="col-md-2 control-label">组织架构级别*：</label>
				<div class="col-md-3 ">
				<input type="text" value='${insuranceDept.orgLevel}' class="form-control form-control-static" id="orgLevel"  name="orgLevel"  readonly="readonly"/>
				</div>
			</div>		
			
			<div class="form-group">
				 <label class="col-md-2 control-label">保险公司名称*：</label>
				<div class="col-md-3 ">
				<input type="text"  value='${insuranceDept.insuranceCompanyName}' class="form-control form-control-static" id="insuranceCompanyNames"  name="insuranceCompanyNames" readonly="readonly"></select>
				</div>
			</div>		
					
			<label class="col-md-2 control-label">产品基本信息：</label></br></br>
			<div class="form-group">
				<label class="col-md-2 control-label">产品名称*：</label>
				<div class="col-md-3 ">
					<input type="text" value='${insuranceProductInfo.productName}' id="productName"  name="productName" class="form-control form-control-static" placeholder="请输入产品名称">
				</div>

				<label class="col-md-2 control-label">产品代码*：</label>
				<div class="col-md-3 ">
					<input type="text"  value='${insuranceProductInfo.productCode}' id="productCode"  name="productCode" class="form-control form-control-static" placeholder="请输入产品代码">
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-md-2 control-label">产品简称：</label>
				<div class="col-md-3 ">
					<input type="text" value='${insuranceProductInfo.productNameLess}' id="productNameLess"  name="productNameLess" class="form-control form-control-static" placeholder="请输入产品简称">
				</div>
				<label class="col-md-2 control-label">产品英文名：</label>
				 <div class="col-md-3 ">
				 <input type="text" value='${insuranceProductInfo.productEnName}' id="productEnName"  name="productEnName" class="form-control form-control-static" placeholder="请输入产品英文名">
             	</div>
			</div>
			
			<div class="form-group">
             	<label class="col-md-2 control-label">保险公司险种类别*：</label>
				<div class="col-md-3 ">
					<input type="hidden" value='${insuranceProductInfo.companyInsuranceType}'  id="companyInsuranceType_hide">
					<select class="form-control form-control-static"  id="companyInsuranceType" name="companyInsuranceType">
					</select>
				</div>
				
				<label class="col-md-2 control-label">汇康险种类别*：</label>
                <div class="col-md-3 ">
					<select class="form-control form-control-static"  id="insuranceType" name="insuranceType">
             		</select>				
             	</div>
			</div> 
			
			<div class="form-group">
				<label class="col-md-2 control-label">保险公司险种代码*：</label>
				<div class="col-md-3 ">
					<input type="text" value='${insuranceProductInfo.companyInsuranceCode}' id="companyInsuranceCode"  name="companyInsuranceCode" class="form-control form-control-static" placeholder="请输入保险公司险种代码">
				</div>
				<label class="col-md-2 control-label">保监委险种类别*：</label>
				 <div class="col-md-3 ">
				 <input type="text" value='${insuranceProductInfo.circInsuranceType}' id="circInsuranceType"  name="circInsuranceType" class="form-control form-control-static" placeholder="请输入保监委险种类别">
             	</div>
			</div> 
			
			<div class="form-group">
			<label class="col-md-2 control-label">保险期间方式*：</label>
				 <div class="col-md-3 ">
					<select class="form-control form-control-static" id="insurancePeriodType" name="insurancePeriodType">
                        <option value="">请选择</option>
                        <option value="0"<c:if test="${insuranceProductInfo.insurancePeriodType eq '0' }">selected</c:if> >终身</option>
                        <option value="1"<c:if test="${insuranceProductInfo.insurancePeriodType eq '1'}">selected</c:if> >N年</option>
                        <option value="2"<c:if test="${insuranceProductInfo.insurancePeriodType eq '2'}">selected</c:if> >1年</option>
             		</select>				
             	</div>
             	
			<label class="col-md-2 control-label">缴费期间方式*：</label>
              <div class="col-md-3 ">
					<select class="form-control form-control-static"  value='${insuranceProductInfo.renewalPeriodType}'  id="renewalPeriodType" name="renewalPeriodType">
                        <option value="">请选择</option>
                        <option value="0"<c:if test="${insuranceProductInfo.renewalPeriodType eq '0'}">selected</c:if> >N年</option>
             		</select>				
             	</div>
			</div>	
			
			<div class="form-group">
			<label class="col-md-2 control-label">卡单产品标记*：</label>
				 <div class="col-md-3 ">
					<select class="form-control form-control-static" id="cardProType" name="cardProType">
                        <option value="" <c:if test="${insuranceProductInfo.cardProType eq '' }">selected</c:if> >请选择</option>
                        <option value="0" <c:if test="${insuranceProductInfo.cardProType eq '0' }">selected</c:if> >否</option>
                        <option value="1" <c:if test="${insuranceProductInfo.cardProType eq '1'}">selected</c:if> >是</option>
             		</select>				
             	</div>
             	
             	<label class="col-md-2 control-label">自动续保标记*：</label>
              <div class="col-md-3 ">
					<select class="form-control form-control-static" id="autoPaySign" name="autoPaySign">
                        <option value="" <c:if test="${insuranceProductInfo.autoPaySign eq '' }">selected</c:if> >请选择</option>
                        <option value="0"<c:if test="${insuranceProductInfo.autoPaySign eq '0' }">selected</c:if>>否</option>
                        <option value="1"<c:if test="${insuranceProductInfo.autoPaySign eq '1'}">selected</c:if> >是</option>
             		</select>				
             	</div>
			</div>	
			
		<div class="form-group">
			<label class="col-md-2 control-label">主附险标记*：</label>
              <div class="col-md-3 ">
					<select class="form-control form-control-static" id="mainOrAdditional" name="mainOrAdditional">
                        <option value=""  <c:if test="${insuranceProductInfo.mainOrAdditional eq '' }">selected</c:if>>请选择</option>
                        <option value="0" <c:if test="${insuranceProductInfo.mainOrAdditional eq '0' }">selected</c:if>>主险</option>
                        <option value="1" <c:if test="${insuranceProductInfo.mainOrAdditional eq '1'}">selected</c:if> >附险</option>
             		</select>				                                                                     
             	</div> 
			
		   <label class="col-md-2 control-label">长短险标记*：</label>
              <div class="col-md-3 ">
					<select class="form-control form-control-static" id="longShortRiskType" name="longShortRiskType">
                        <option value=""  <c:if test="${insuranceProductInfo.longShortRiskType eq '' }">selected</c:if>>请选择</option>
                        <option value="0" <c:if test="${insuranceProductInfo.longShortRiskType eq '0' }">selected</c:if>>短期险</option>
                        <option value="1" <c:if test="${insuranceProductInfo.longShortRiskType eq '1'}">selected</c:if> >长期险</option>
             		</select>				                                                                           
             	</div> 	
		</div>		
		
		<div class="form-group">
			<label class="col-md-2 control-label">最大续保年龄：</label>
				<div class="col-md-3 ">
					<input type="text" value='${insuranceProductInfo.maxRenewalYears}' id="maxRenewalYears"  name="maxRenewalYears" class="form-control form-control-static" placeholder="请输入最大续保年龄">
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
					<input type="text" value='${insuranceProductInfo.circRecordCode}' id="circRecordCode"  name="circRecordCode" class="form-control form-control-static" placeholder="请输入保监会备案代码">
				</div>
			</div>		
			
			
			<div class="form-group">
				<label class="col-md-2 control-label">生效日期*：</label>
             	<div class="col-md-3 ">		
             		<input type="text"  value='${paraData.takeEffectDates}'    id="takeEffectDate"  name="takeEffectDates" class="form-control form-control-static"  placeholder="请选择生效时间" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true,isShowClear:false})">
				</div>
				<label class="col-md-2 control-label">失效日期*：</label>
				<div class="col-md-3 ">
					<input type="text"   value='${paraData.invalidDates}'   id="invalidDate"  name="invalidDates" class="form-control form-control-static"  placeholder="请选择失效时间" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true,isShowClear:false})">
				</div>
            </div>
		<%-- 	<div class="form-group">
				<label class="col-md-2 control-label">生效日期*：</label>
             	<div class="col-md-3 ">		
             		<input type="text" value=' <fmt:formatDate value='${insuranceProductInfo.takeEffectDate}' pattern='yyyy-MM-dd' />'  id="takeEffectDate"  name="takeEffectDate" class="form-control form-control-static"  placeholder="请选择生效时间" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})">
				</div>
				<label class="col-md-2 control-label">失效日期*：</label>
				<div class="col-md-3 ">
					<input type="text"   value=' <fmt:formatDate value='${insuranceProductInfo.invalidDate}' pattern='yyyy-MM-dd' />'  id="invalidDate"  name="invalidDate" class="form-control form-control-static"  placeholder="请选择失效时间" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})">
				</div>
            </div> --%>
             
            <div class="form-group"> 
            	<label class="col-md-2 control-label">团个险标记*：</label>
             	<div class="col-md-3 ">		
             		<select class="form-control form-control-static" id="groupOrPersonal" name="groupOrPersonal">
                        <option value=""  <c:if test="${insuranceProductInfo.groupOrPersonal eq '' }">selected</c:if> >请选择</option>
                        <option value="0" <c:if test="${insuranceProductInfo.groupOrPersonal eq '0' }">selected</c:if> >团体购买险种</option>
                        <option value="1" <c:if test="${insuranceProductInfo.groupOrPersonal eq '1'}">selected</c:if>  >个人购买险种</option>
             		</select>			                                                                             
				</div>
				
			<label class="col-md-2 control-label">是否需要回执*：</label>
              <div class="col-md-3 ">
					<select class="form-control form-control-static" id="needReceipt" name="needReceipt">
                        <option value="" <c:if test="${insuranceProductInfo.needReceipt eq '' }">selected</c:if> >请选择</option>
                        <option value="0"<c:if test="${insuranceProductInfo.needReceipt eq '0' }">selected</c:if>>否</option>
                        <option value="1"<c:if test="${insuranceProductInfo.needReceipt eq '1'}">selected</c:if> >是</option>
             		</select>				                                                                            
             	</div> 
			</div>
			<div class="form-group">
				<label class="col-md-2 control-label">缴费方式*：</label>
				<input type="checkbox" name="payMode" value="0"> 年交  
				<input type="checkbox" name="payMode" value="1"> 半年交
				<input type="checkbox" name="payMode" value="2"> 季交 
				<input type="checkbox" name="payMode" value="3"> 月交
				<input type="checkbox" name="payMode" value="4"> 趸交
				<input type="checkbox" name="payMode" value="5"> 三年交  
			</div>
	<label class="col-md-1 control-label">保险期间 *：</label><br/>
<div >
<a href="#" rel="external nofollow" rel="external nofollow" rel="external nofollow" id="AddMoreEditBox" class="btn btn-info">添加</a>
	<table id="InputsEdit" border="1" >
		  <tr  style="background: silver;">
			<td width="14%" >产品名称</td>
		 	<td width="14%" >产品编码</td>
		    <td width="13%" >保险期间</td>
		    <td width="13%" >缴费期间</td>
		   <%-- <td width="14%" >价值佣金系数(%)</td>
		    <td width="14%" >内部标保佣金系数(%)</td>
		    <td width="14%" >外部标保佣金系数(%)</td>
		    <td width="11%" >标保折标系数(%)</td>--%>
		    <td width="11%">操作</td>
		  </tr>
		   <c:forEach items="${productsCommissionRatioList}" var="base"> 
		  <tr >
		     <td  style="display:none"><input   type="hidden"  value='0'   name="serialNumber" ></td>
		     <td style="display:none" ><input   type="hidden"  value='${base.productsRatioId}' id="productsRatioId"  name="productsRatioId" ></td>
		  	 <td><input style=width:150px;  type="text"  value='${base.productsName}' id="productsName"  name="productsName"placeholder="请输入保险起期" >  </td>
		     <td><input style=width:150px;  type="text"  value='${base.productsCode}' id="productsCode"  name="productsCode"placeholder="请输入保险起期" >  </td>
		    <td><input style=width:60px;  type="text"  value='${base.insurancePeriodMin}' id="insurancePeriodMin"  name="insurancePeriodMin"> - <input style=width:60px;  type="text" value='${base.insurancePeriodMax}' id="insurancePeriodMax"  name="insurancePeriodMax">岁</td>
		    <td><input style=width:60px; type="text" value='${base.renewPeriodMin}' id="renewPeriodMin"  name="renewPeriodMin"placeholder="请输入缴费起期" > - <input style=width:60px; type="text" value='${base.renewPeriodMax}' id="renewPeriodMax" name="renewPeriodMax">年</td>
		    <%--<td ><input style=width:100px; type="text" value='${base.valueCommissionCoefficient}'  id="valueCommissionCoefficient"  name="valueCommissionCoefficient"placeholder="请输入价值佣金系数" >%</td>
		    <td ><input style=width:100px; type="text" value='${base.inStandardCommissionCoefficient}'  id="inStandardCommissionCoefficient"  name="inStandardCommissionCoefficient"  placeholder="请输入内部标保佣金系数" >%</td>
		    <td ><input style=width:100px; type="text" value='${base.outStandardCommissionCoefficient}'  id="outStandardCommissionCoefficient"  name="outStandardCommissionCoefficient"  placeholder="请输入外部标保佣金系数" >%</td>
		    <td ><input style=width:100px; type="text" value='${base.indexingCoefficient}'  id="indexingCoefficient"  name="indexingCoefficient">%</td>--%>
			<td><a href="#" rel="external nofollow" rel="external nofollow" class="removeclass" style="color: red"><input  type="button" value="删除" onclick="delRowProduct('${base.productsRatioId}','${insuranceProductInfo.productId}' );" ></a></td>
		  </tr>
  </c:forEach>
	</table>
</div>
						
		<label class="col-md-1 control-label">说明：</label><br />		
		   <div class="form-group">
				<label class="col-md-2 control-label">保障范围说明：</label>
				<div class="col-md-3 ">
				 
					<input  type="textarea" style="width:700px; height:60px"  value='${insuranceProductDetail.insuranCoverExplain}'  id="insuranCoverExplain"  name="insuranCoverExplain" class="form-control form-control-static" placeholder="请输入保障范围说明">  
				</div>
			</div>			
									
			<label class="col-md-1 control-label"></label>	
			 <div class="form-group">
				<label class="col-md-2 control-label">基本责任说明：</label>
				<div class="col-md-3 ">
					<input  type="textarea" style="width:700px; height:60px" value='${insuranceProductDetail.basicResponsibilitiesExplain}' id="basicResponsibilitiesExplain"  name="basicResponsibilitiesExplain" class="form-control form-control-static" placeholder="请输入基本责任说明">  
				</div>
			</div>	
			
			<label class="col-md-1 control-label"></label>	
			 <div class="form-group">
				<label class="col-md-2 control-label">投保人限制说明：</label>
				<div class="col-md-3 ">
					<input  type="textarea" style="width:700px; height:60px"  value='${insuranceProductDetail.polocyHolderRestrictions}' id="polocyHolderRestrictions"  name="polocyHolderRestrictions" class="form-control form-control-static" placeholder="请输入投保人限制说明">  
				</div>
			</div>	
			
			<label class="col-md-1 control-label"></label>	
			 <div class="form-group">
				<label class="col-md-2 control-label">保险责任免责说明：</label>
				<div class="col-md-3 ">
					<input  type="textarea" style="width:700px; height:60px" value='${insuranceProductDetail.exemptionInstruction}' id="exemptionInstruction"  name="exemptionInstruction" class="form-control form-control-static" placeholder="请输入保险责任免责说明">  
				</div>
			</div>	
					
			<label class="col-md-1 control-label"></label>	
			 <div class="form-group">
				<label class="col-md-2 control-label">备注说明：</label>
				<div class="col-md-3 ">
					<input  type="textarea" style="width:700px; height:60px" value='${insuranceProductDetail.remark}'  id="remark"  name="remark" class="form-control form-control-static" placeholder="请输入备注说明">  
				</div>
			</div>	
			<label class="col-md-1 control-label"></label>	
			 <div class="form-group">
				<label class="col-md-2 control-label">发布类型*：</label>
             	<div class="col-md-3 ">		
             		<select class="form-control form-control-static" id="saleType" name="saleType">
                        <option value="" <c:if test="${insuranceProductInfo.saleType eq '' }">selected</c:if>>请选择</option>
                        <option value="0"<c:if test="${insuranceProductInfo.saleType eq '0' }">selected</c:if> >线下</option>
                        <option value="1"<c:if test="${insuranceProductInfo.saleType eq '1' }">selected</c:if>>线上</option>
                        <option value="2"<c:if test="${insuranceProductInfo.saleType eq '2'}">selected</c:if> >二者皆可</option>
             		</select>			                                                                             
				</div>
			</div>	
						
            <div class="modal-footer col-md-6">
            <!--用来清空表单数据-->
            <input type="reset" name="reset" style="display: none;" />
                <button type="button" class="btn btn-default" onclick="windowCloseTab()">关闭</button>
               <button id="saveButton" type="button" onclick="InsProEdit.add()" class="btn btn-primary">保存</button>
            </div>
            </form>
            </div>
</body>
</html>