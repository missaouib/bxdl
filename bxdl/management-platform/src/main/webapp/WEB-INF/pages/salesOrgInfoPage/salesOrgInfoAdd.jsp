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
    <script src="${path}/js/jquery-form.js" charset="UTF-8" type="text/javascript"></script>
	<script type="text/javascript">	
		$(function () {	
			getAreaByPid("0","sheng");
			SalesOrgInfo.formValidator();
			
			getSalesOrgs("topParentSalesOrgCode","01","");
			//（topParentSalesOrgCode：顶级机构；parentSalesOrgCode：上级机构）
			$("#salesOrgLevel").on(  
    	            "change",function(){
						var topParentSalesOrgCode = $("#topParentSalesOrgCode").find("option:selected").attr("myvalue");
						//alert(topParentSalesOrgCode);
						if(topParentSalesOrgCode=="" && $(this).val()!="01"){
							alert("请先选择顶级机构");
							$(this).val("");
						}else if(topParentSalesOrgCode!="" && $(this).val()=="01"){
							alert("顶级机构下不允许创建总公司级别！");
							$(this).val("");
    	            		var h = "<option value='' myvalue=''>请选择上级机构</option>";
    	            		$("#parentSalesOrgCode").empty();
    	            		$("#parentSalesOrgCode").append(h);							
						}else{
							var getlevel = $(this).val();
							if(getlevel=="02"){
								getlevel="01";
							}else if(getlevel=="03"){
								getlevel="02";
							}else if(getlevel=="04"){
								getlevel="03";
							}else if(getlevel=="05"){
								getlevel = "05";
							}
							getSalesOrgs("parentSalesOrgCode",getlevel,topParentSalesOrgCode);
						}
    	            }
	        );
	        
	        $("#accountingOrg,#parentSalesOrgCode").on(  
   	            "change",function(){
   	            	var isflag = $("#accountingOrg").val();
   	            	if(isflag=="0"){
   	            		getParents();
   	            	}else if(isflag=="1"){
   	            		var h = "<option value='' myvalue=''>请选择</option>";
			            $("#belongAccountingOrgCode").html(h);
			            $("#invoiceOrgCode").empty();
			            $("#invoiceOrgCode").append(h);   	            		
   	            	}
   	            }
    	    );
    	    
			$("#cooperationType").on(  
		        "change",function(){
		            var cooperationType = $("#cooperationType").val();
		            if(cooperationType == "0"){
		            	$("#zjjt_div").show();
		            }else{
		            	$("#zjjt_div").hide();
		            	$("#zjjt_div tr:gt(0)").remove();
		            }        	
		        }
		    )    	    
		});
		
		function getParents(){
			var accountingOrg = "1";
			var pTreeCode = $("#parentSalesOrgCode").find("option:selected").attr("myvalue");
			if(topParentSalesOrgCode != ""){
				 $.ajax({
			         type: "POST",
			         url: "salesOrgInfo/getParents",
			         data:{accountingOrg:accountingOrg,pTreeCode:pTreeCode},
			         dataType: "json",
			         success: function(data){
			        	 	var salesOrgs = data.rows;
						 	var h = "<option value='' myvalue=''>请选择</option>";
				            $.each(salesOrgs, function(key, value) {
				            	//alert(value.insuranceCompanyCode);
				                h += "<option value='" + value.salesOrgCode + "' myvalue='"+ value.treeCode +"'>" + value.salesOrgName //下拉框序言的循环数据
				                + "</option>";  
				            });
				            $("#belongAccountingOrgCode").empty();
				            $("#belongAccountingOrgCode").append(h);
				            $("#invoiceOrgCode").empty();
				            $("#invoiceOrgCode").append(h);
			         }
			     })
			}
		}
		
		function getSalesOrgs(selectId,orgLevel,indexCode){
			 $.ajax({
		         type: "POST",
		         url: "salesOrgInfo/findSalesOrgs",
		         data:{salesOrgLevel:orgLevel,treeCode:indexCode},
		         dataType: "json",
		         success: function(data){
		        	//alert(data.rows);
		        	var salesOrgs = data.rows;
					var h = "<option value='' myvalue=''>请选择顶级机构</option>";
					if(selectId=="parentSalesOrgCode"){h="<option value=''>请选择上级组织机构</option>"}
					if(selectId=="parentSalesOrgCode" && salesOrgs.length==0 && orgLevel != "01"){
						alert("所选择的机构级别无上级机构，请先创建上级机构！");
						$("#salesOrgLevel").val("");
						return false;
					}
		            $.each(salesOrgs, function(key, value) {
		            	//alert(value.insuranceCompanyCode);
		                h += "<option value='" + value.salesOrgCode + "' myvalue='"+ value.treeCode +"'>" + value.salesOrgName //下拉框序言的循环数据
		                + "</option>";  
		            });
		            $("#"+selectId).empty();
		            $("#"+selectId).append(h);
		            if(selectId="topParentSalesOrgCode"){
			            $("#"+selectId).on(  
			    	            "change",function(){
			    	            	if($(this).val()==""){
			    	            		$("#salesOrgLevel").val("01");
			    	            		var h = "<option value='' myvalue=''>请选择上级机构</option>";
			    	            		$("#parentSalesOrgCode").empty();
			    	            		$("#parentSalesOrgCode").append(h);
			    	            	}else{			    	            	
										var getlevel = $("#salesOrgLevel").val();										
										if(getlevel!=""){
											if(getlevel=="01"){
												alert("顶级机构下不允许创建总公司级别！");
												$(this).val("");
					    	            		var h = "<option value='' myvalue=''>请选择上级机构</option>";
					    	            		$("#parentSalesOrgCode").empty();
					    	            		$("#parentSalesOrgCode").append(h);
											}else{
												if(getlevel=="02"){getlevel="01";}else if(getlevel=="03"){getlevel="02";}else if(getlevel=="04"){getlevel="03";}
												getSalesOrgs("parentSalesOrgCode",getlevel,$(this).find("option:selected").attr("myvalue"));
											}
										}
			    	            	}
			    	            }
		    	        )
		            }
		         }
		     });
		}
		
		function creatArryList(){			
			var zjjtList = [];
			var zjjtListLength = $("#zjjt_list").find("tr").length;
			for(var i=1 ; i< zjjtListLength ; i++){
				var zjjtObj = {};
				zjjtObj.allowanceRatio = $("#zjjt_list tr:eq("+i+")").find("#jtRate").val();
				var maxFYC = $("#zjjt_list tr:eq("+i+")").find("input#maxFYC").val();
				var maxFYC_t = $("#zjjt_list tr:eq("+i+")").find("#maxFYC_t").find("option:selected").text();
				var minFYC = $("#zjjt_list tr:eq("+i+")").find("#minFYC").val();
				var minFYC_t = $("#zjjt_list tr:eq("+i+")").find("#minFYC_t").find("option:selected").text();
				zjjtObj.allowanceFormula = (maxFYC+maxFYC_t+"FYC"+minFYC_t+minFYC).replace(/>/g,"&gt;");
				zjjtList.push(zjjtObj);
			}
			console.log(zjjtList);//---------合同信息
			$("#zjjtList").val(JSON.stringify(zjjtList));
		}
		
		var SalesOrgInfo = function () {
			return{				
				 //添加
		        add:function () {
		        	document.getElementById("saveButton").setAttribute("disabled", true);
		            if($("#addSalesOrgInfoForm").data('bootstrapValidator').validate().isValid()){
		            	flag = true;
		            	var sheng = $("#sheng").val();
		            	var shi = $("#shi").val();
		            	var qu = $("#qu").val();
		            	if(qu != ""){ 
		            		$("#areaCode").val(qu);
		            	}else{
		            		if(shi!=""){$("#areaCode").val(shi);}else{$("#areaCode").val(sheng);}
		            	}
		            	$("#PTcode").val($("#parentSalesOrgCode").find("option:selected").attr("myvalue"));
		            	if($("#salesOrgLevel").val()!="01" && $("#parentSalesOrgCode").val()==""){
		            		$.alert({
                                title: '提示信息！',
                                content: '请选择上级机构!',
                                type: 'blue'
                            });
		            		flag = false;
                		    document.getElementById("saveButton").removeAttribute("disabled");
                		    return false;
		            	}
		    			if(flag){
		    				creatArryList();
			            	$.ajax({
			                    url:'salesOrgInfo/addSalesOrgInfo',
			                    dataType:'json',
			                    type:'post',
			                    data:$("#addSalesOrgInfoForm").serialize(),
			                    success:function(data){
			                        if(data.messageCode=="200"){
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
			                                content:data.requestState,
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
		        
		        //----------------------------------------------------------------------------------------总监津贴
		        addZjjt:function(){
		        	var zjjt_tr_index = Date.parse(new Date());
		        	var h = '<tr id="zjjt_tr_index_'+zjjt_tr_index+'">'
		    		+'<td style="width:120px;text-align:center;"><a href="javascript:void(0)" onclick="SalesOrgInfo.removeList(\'zjjt_tr_index_'+zjjt_tr_index+'\')" style="color:red">删除</a></td>'
		    		+'<td style="width:140px;text-align:center;">总价值佣金FYC(元)</td>'
		    		+'<td style="width:470px;text-align:center;">'
		    		+'<input id="maxFYC" style="width:70px;" type="number" value="">&nbsp;元&nbsp;'
		    		+'<select id="maxFYC_t" style="width:50px"><option>></option><option>=</option><option>>=</option></select>&nbsp;FYC&nbsp;'
		    		+'<select id="minFYC_t" style="width:50px"><option>></option><option>=</option><option>>=</option></select>&nbsp;' 
		    		+'<input id="minFYC" style="width:70px;" type="number" value="">&nbsp;元</td>'
		    		+'<td style="width:120px;text-align:center;"><input id="jtRate" style="width:80px;" type="number" value="">%</td>';
		        	$("#zjjt_list").append(h);
		        },
		        
		        //打开添加模态框
		        removeList:function (trid) {
		            $("#"+trid).remove();
		        },
		        
		        openImageDlg:function(value){
		        	$("#backto").val(value);
		   	        $("#openImageDlg").modal('show');
		        },
		        
		        showImg:function(value){
		        	var url = $("#"+value).val();
		        	//var fullUrl = configSys.fdfs_location+url;
					var fullUrl = $("#fastInnerUrl").val()+url;
		        	if(isEmpty(url)){
	                   	 $.alert({
	                         title: '提示信息！',
	                         content: '请先上传资料!',
	                         type: 'red'
	                     });
	                   	 return false;
		        	}
		        	window.open(fullUrl,"_blank");
		        },
		        
		        delImg:function(value){
		        	if(isEmpty($("#"+value).val())){
		        		$.alert({
	                        title: '提示信息！',
	                        content: '无信息需要移除!',
	                        type: 'red'
	                    });
		        		return true;
		        	}else{
			        	$.confirm({
							title: '提示信息!',
							content:"确定移除上传信息吗？",
							type: 'blue',
							typeAnimated: true,
							buttons: {
								确定: {
									action: function(){
							        	$("#"+value).val("");
							        	$.alert({
					                        title: '提示信息！',
					                        content: '移除成功!',
					                        type: 'red'
					                    });
									}
								},
								取消: function () {
									return true;
								}
							}
						});
		        	}
		        },
		        
		        uploadImage:function(formId){
		        	comm_uploadImage(formId,$("#backto").val());
		        },
			
		        //表单验证
		        formValidator:function () {
		            $("#addSalesOrgInfoForm").bootstrapValidator({
		                fields:{
		                	salesOrgName:{
		                        validators:{
		                            notEmpty:{
		                                message:"组织机构名称不能为空"
		                            },
		                            stringLength:{
		                                max:100,
		                                message:'不能超过100个字符长度'
		                            },
		                        }
		                    },
							salesOrgNameLess:{
		                        validators:{
		                            notEmpty:{
		                                message:"组织机构简称不能为空"
		                            },
		                            stringLength:{
		                                max:100,
		                                message:'不能超过100个字符长度'
		                            },
		                        }
		                    },
		                    salesOrgCode:{
		                        validators:{
		                            notEmpty:{
		                                message:'组织机构不能为空',
		                            },
		                            stringLength:{
		                                max:32,
		                                message:'字符长度不能超过32'
		                            }
		                        }
		                    },
		                    salesOrgLevel:{
		                        validators:{
		                            notEmpty:{
		                                message:'组织机构级别不能为空',
		                            }
		                        }
		                    },
		                    sheng:{
		                        validators:{
		                            notEmpty:{
		                                message:'所在省份不能为空',
		                            }
		                        }
		                    },
                            shi:{
		                        validators:{
		                            notEmpty:{
		                                message:'所在城市不能为空',
		                            }
		                        }
		                    },
		                    businessTaxRate:{
		                        validators:{
		                        	regexp: {
		                                regexp:/^-?(100|(([1-9]\d|\d)(\.\d{1,2})?))%$/,
		                                message: '请输入小于100%的百分数,最多2位小数!'
		                            }
		                        }
		                    },
		                    incomeTaxRate:{
		                        validators:{
		                        	regexp: {
		                                regexp:/^-?(100|(([1-9]\d|\d)(\.\d{1,2})?))%$/,
		                                message: '请输入小于100%的百分数,最多2位小数!'
		                            }									
		                        }
		                    },
		                    contactsTel:{
		                		validators:{
									regexp:{
                                        regexp:/^1[3456789]\d{9}$/,
                                        message:"请输入正确手机号格式"
                                    }
								}
							},
							postCode:{
		                		validators:{
		                			regexp:{
		                				regexp: /^\d+(\.\d+)?$/,
										message: '请输入数字'
									}
								}
							},
							employeesNum:{
		                		validators:{
		                			regexp:{
		                				regexp: /^\d+(\.\d+)?$/,
										message: '请输入数字'
									}
								}
							},
							socialSecurityNum:{
		                		validators:{
		                			regexp:{
		                				regexp: /^\d+(\.\d+)?$/,
										message: '请输入数字'
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
			<input type="hidden" id="fastInnerUrl" name="fastInnerUrl" value="${fastInnerUrl}" />
			<form class="form-horizontal" id="addSalesOrgInfoForm"  method="post">
			<div class="form-group" style="margin-top:20px;">
			</div>			
			<div class="form-group">
				<label class="col-md-2 control-label">组织机构名称 *：</label>
				<div class="col-md-3 ">
					<input type="text" id="salesOrgName"  name="salesOrgName" class="form-control form-control-static" placeholder="请输入组织机构名称">
				</div>

				<label class="col-md-2 control-label">组织机构简称 *：</label>
				<div class="col-md-3 ">
					<input type="text" id="salesOrgNameLess"  name="salesOrgNameLess" class="form-control form-control-static" placeholder="请输入组织机构简称">
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-2 control-label">组织机构英文名：</label>
				<div class="col-md-3 ">
					<input type="text" id="salesOrgEnName"  name="salesOrgEnName" class="form-control form-control-static" placeholder="请输入组织机构英文名">
				</div>

				<label class="col-md-2 control-label">组织机构英文简称：</label>
				<div class="col-md-3 ">
					<input type="text" id="salesOrgEname"  name="salesOrgEname" class="form-control form-control-static" placeholder="请输入组织机构英文简称">
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-2 control-label">顶级机构：</label>
				<div class="col-md-3 ">
					<select class="form-control form-control-static" id="topParentSalesOrgCode" name="topParentSalesOrgCode">
                        <option value="">请选择顶级机构</option>
             		</select>				
             	</div>			
			
				<label class="col-md-2 control-label">上级组织机构：</label>
				<input type="hidden" id="PTcode" name="PTcode" value="" />
				<div class="col-md-3 ">
					<select class="form-control form-control-static" id="parentSalesOrgCode" name="parentSalesOrgCode">
						<option value="">请选择上级组织机构</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-2 control-label">当前组织机构级别 *：</label>
				<div class="col-md-3 ">
					<select class="form-control form-control-static" id="salesOrgLevel" name="salesOrgLevel">
						<option value="">请选择组织机构级别</option>
						<option value="01">总公司</option>
						<option value="02">省级分公司</option>
						<option value="03">地市分公司</option>
						<option value="04">区县分公司</option>
						<option value="05">团队级别</option>
					</select>
				</div>
             	
				<label class="col-md-2 control-label">当前组织机构代码 *：</label>
				<div class="col-md-3 ">
					<input type="text" id="salesOrgCode"  name="salesOrgCode" class="form-control form-control-static" placeholder="请输入组织机构代码">
				</div>             	
			</div>	
			<div class="form-group">
				<label class="col-md-2 control-label">是否核算机构：</label>
				<div class="col-md-3 ">
					<select class="form-control form-control-static" id="accountingOrg" name="accountingOrg">
                        <option value="0">否</option>
                        <option value="1">是</option>
             		</select>				
             	</div>			
			
				<label class="col-md-2 control-label">所属核算机构：</label>
				<div class="col-md-3 ">
					<select class="form-control form-control-static" id="belongAccountingOrgCode" name="belongAccountingOrgCode">
                        <option value="">请选择</option>
             		</select>				
             	</div>
			</div>				
			<div class="form-group">
				<label class="col-md-2 control-label">公司传真：</label>
				<div class="col-md-3 ">
					<input type="text" id="fax"  name="fax" class="form-control form-control-static" placeholder="请输入公司传真">
				</div>

				<label class="col-md-2 control-label">公司电话：</label>
				<div class="col-md-3 ">
					<input type="text" id="phone"  name="phone" class="form-control form-control-static" placeholder="请输入公司电话">
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
            
            	<label class="col-md-2 control-label">所在区县：</label>
             	<div class="col-md-3 ">		
             		<select class="form-control form-control-static" id="qu" name="qu">
                        <option value="">请选择区县</option>
             		</select>
				</div>
				
				<label class="col-md-2 control-label">邮政编码：</label>
				<div class="col-md-3 ">
					<input type="text" id="postCode"  name="postCode" class="form-control form-control-static" placeholder="请输入邮政编码">
				</div>
			</div>	
			<div class="form-group">
				<label class="col-md-2 control-label">详细地址：</label>
				<div class="col-md-3 ">
					<input style="width:655px;" type="text" id="address"  name="address" class="form-control form-control-static" placeholder="请输入详细地址">
				</div>	
			</div>
			<div class="form-group">
				<label class="col-md-2 control-label">注册地址：</label>
				<div class="col-md-3 ">
					<input style="width:655px;" type="text" id="regAddr"  name="regAddr" class="form-control form-control-static" placeholder="请输入注册地址">
				</div>	
			</div>	
			
			<div class="form-group">
				<label class="col-md-2 control-label">成立日期：</label>
                <div class="col-md-3">
                   <input class="form-control form-control-static" id="dateOfEstablishment" value="2019-01-01" name="dateOfEstablishment" type="text" placeholder="请选择成立时间" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
                </div>			
			
				<label class="col-md-2 control-label">开票机构：</label>
             	<div class="col-md-3 ">	
             		<select class="form-control form-control-static" id="invoiceOrgCode" name="invoiceOrgCode">
                        <option value="">请选择</option>
             		</select>
             	</div>
			</div>		
			
			<div class="form-group">
<%--				<label class="col-md-2 control-label">机构性质：</label>--%>
<%--                <div class="col-md-3">--%>
<%--             		<select class="form-control form-control-static" id="cooperationType" name="cooperationType">--%>
<%--                        <option value="0">直营</option>--%>
<%--                        <option value="1">加盟</option>--%>
<%--             		</select>                --%>
<%--             	</div>			--%>
			
				<label class="col-md-2 control-label">机构状态：</label>
             	<div class="col-md-3 ">	
             		<select class="form-control form-control-static" id="orgStatus" name="orgStatus">
                        <option value="">请选择</option>
                        <option value="0">筹建</option>
                        <option value="1">正常</option>
                        <option value="2">整改</option>
                        <option value="3">撤销</option>
             		</select>
             	</div>
			</div>	
			
			<input type="hidden" id="zjjtList" name="zjjtList" value="" /><!-- 总监津贴 -->
									
			<div class="form-group">
				<label class="col-md-2 control-label">是否挂牌：</label>
             	<div class="col-md-3 ">	
             		<select class="form-control form-control-static" id="haveListed" name="haveListed">
                        <option value="">请选择</option>
                        <option value="0">否</option>
                        <option value="1">是</option>
             		</select>
             	</div>			
			
				<label class="col-md-2 control-label">营业执照编号：</label>
                <div class="col-md-3">
					<input type="text" id="businessLicense"  name="businessLicense" class="form-control form-control-static" placeholder="请输入营业执照编号">
                </div>			
			</div>			
						
			<div class="form-group">
				<label class="col-md-2 control-label">营业税纳税类型：</label>
             	<div class="col-md-3 ">	
             		<select class="form-control form-control-static" id="businessPayTaxesType" name="businessPayTaxesType">
                        <option value="">请选择</option>
                        <option value="1">普通</option>
                        <option value="2">小规模</option>
             		</select>
             	</div>			
			
				<label class="col-md-2 control-label">营业税纳税率(%)：</label>
                <div class="col-md-3">
					<input type="text" id="businessTaxRate"  name="businessTaxRate" class="form-control form-control-static" placeholder="请输入营业税纳税率(%)">
                </div>			
			</div>	
						
			<div class="form-group">
				<label class="col-md-2 control-label">所得税纳税类型：</label>
             	<div class="col-md-3 ">	
             		<select class="form-control form-control-static" id="incomePayTaxesType" name="incomePayTaxesType">
                        <option value="">请选择</option>
                        <option value="1">普通</option>
                        <option value="2">小规模</option>
             		</select>
             	</div>			
			
				<label class="col-md-2 control-label">所得税纳税率(%)：</label>
                <div class="col-md-3">
					<input type="text" id="incomeTaxRate"  name="incomeTaxRate" class="form-control form-control-static" placeholder="请输入所得税纳税率(%)">
                </div>			
			</div>	
			
			<div class="form-group">
				<label class="col-md-2 control-label">许可证地址：</label>
				<div class="col-md-3 ">
					<input type="text" id="licenseAddr"  name="licenseAddr" class="form-control form-control-static" placeholder="请输入许可证地址">
				</div>

				<label class="col-md-2 control-label">发证机关：</label>
				<div class="col-md-3 ">
					<input type="text" id="licensingAuthority"  name="licensingAuthority" class="form-control form-control-static" placeholder="请输入发证机关">
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-md-2 control-label">联系人手机号：</label>
				<div class="col-md-3 ">
					<input type="text" id="contactsTel"  name="contactsTel" class="form-control form-control-static" placeholder="请输入联系人手机号">
				</div>

				<label class="col-md-2 control-label">负责人姓名：</label>
				<div class="col-md-3 ">
					<input type="text" id="liableName"  name="liableName" class="form-control form-control-static" placeholder="请输入负责人姓名">
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-md-2 control-label">员工数量：</label>
				<div class="col-md-3 ">
					<input type="text" id="employeesNum"  name="employeesNum" class="form-control form-control-static" placeholder="请输入员工数量">
				</div>

				<label class="col-md-2 control-label">社保人数：</label>
				<div class="col-md-3 ">
					<input type="text" id="socialSecurityNum"  name="socialSecurityNum" class="form-control form-control-static" placeholder="请输入社保人数">
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-md-2 control-label">银行开户许可证：</label>
				<div class="col-md-3 ">
					<button type="button" class="btn btn-primary" onclick="SalesOrgInfo.showImg('bankAccountLicense')">预览</button>
               		<button type="button" class="btn btn-primary" onclick="SalesOrgInfo.openImageDlg('bankAccountLicense')">上传资料</button>
               		<button type="button" class="btn btn-primary" onclick="SalesOrgInfo.delImg('bankAccountLicense')">移除</button>
               		<input type="hidden" id="bankAccountLicense" name="bankAccountLicense" value="" />
				</div>

				<label class="col-md-2 control-label">信用代码证：</label>
				<div class="col-md-3 ">
					<button type="button" class="btn btn-primary" onclick="SalesOrgInfo.showImg('creditCodePic')">预览</button>
               		<button type="button" class="btn btn-primary" onclick="SalesOrgInfo.openImageDlg('creditCodePic')">上传资料</button>
               		<button type="button" class="btn btn-primary" onclick="SalesOrgInfo.delImg('creditCodePic')">移除</button>
               		<input type="hidden" id="creditCodePic" name="creditCodePic" value="" />
               	</div>
			</div>	
			
			<div class="form-group">
				<label class="col-md-2 control-label">营业执照：</label>
				<div class="col-md-3 ">
					<button type="button" class="btn btn-primary" onclick="SalesOrgInfo.showImg('businessLicensePic')">预览</button>
               		<button type="button" class="btn btn-primary" onclick="SalesOrgInfo.openImageDlg('businessLicensePic')">上传资料</button>
               		<button type="button" class="btn btn-primary" onclick="SalesOrgInfo.delImg('businessLicensePic')">移除</button>
               		<input type="hidden" id="businessLicensePic" name="businessLicensePic" value="" />
				</div>

				<label class="col-md-2 control-label">业务许可证：</label>
				<div class="col-md-3 ">
					<button type="button" class="btn btn-primary" onclick="SalesOrgInfo.showImg('carryOutBusinessPic')">预览</button>
               		<button type="button" class="btn btn-primary" onclick="SalesOrgInfo.openImageDlg('carryOutBusinessPic')">上传资料</button>
               		<button type="button" class="btn btn-primary" onclick="SalesOrgInfo.delImg('carryOutBusinessPic')">移除</button>
               		<input type="hidden" id="carryOutBusinessPic" name="carryOutBusinessPic" value="" />
               	</div>
			</div>																	
						
			<div class="form-group">
				<label class="col-md-2 control-label">公司发展历程：</label>
				<div class="col-md-3 ">
					<textarea  style="width:655px;" rows="4" id="developmentHistory" name="developmentHistory" class="form-control form-control-static" placeholder="请输入公司发展历程"></textarea>
				</div>
			</div>	
			
			<div class="form-group">
				<label class="col-md-2 control-label">公司重要资质：</label>
				<div class="col-md-3 ">
					<textarea  style="width:655px;" rows="4" id="qualifications" name="qualifications" class="form-control form-control-static" placeholder="请输入公司重要资质"></textarea>
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-md-2 control-label">公司近年经营指标：</label>
				<div class="col-md-3 ">
					<textarea  style="width:655px;" rows="4" id="operatingIndicators" name="operatingIndicators" class="form-control form-control-static" placeholder="请输入公司近年经营指标"></textarea>
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-md-2 control-label">人员优势：</label>
				<div class="col-md-3 ">
					<textarea  style="width:655px;" rows="4" id="personnelAdvantage" name="personnelAdvantage" class="form-control form-control-static" placeholder="请输入人员优势"></textarea>
				</div>
			</div>									
			
			<div class="form-group">
				<label class="col-md-2 control-label">项目优势：</label>
				<div class="col-md-3 ">
					<textarea  style="width:655px;" rows="4" id="projectAdvantage" name="projectAdvantage" class="form-control form-control-static" placeholder="请输入项目优势"></textarea>
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