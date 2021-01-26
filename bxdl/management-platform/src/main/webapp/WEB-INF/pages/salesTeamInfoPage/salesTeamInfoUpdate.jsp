<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/core.jsp" %>
<!DOCTYPE  html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <title>修改销售团队	</title>
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Cache" content="no-cache">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   	<link rel="stylesheet" href="${path}/css/system/role/role.css">
    <script src="${path}/static/js/bootstrap.js"></script>
	<script type="text/javascript">	
		$(function () {	
			SalesTeamInfo.formValidator();
			getSalesOrgs("salesOrgId","","");
			getParents($("#salesOrgId").attr("hideValue"));
			
			setTimeout(function(){
				
				$("#salesOrgId option").each(function(){
					//alert($(this).val());
			        if($(this).val()==$("#salesOrgId").attr("hideValue")){
			            $(this).attr("selected", true);
			        }
				});
				
				$("#parentSalesTeamCode option").each(function(){
					//alert($(this).val());
			        if($(this).val()==$("#parentSalesTeamCode").attr("hideValue")){
			            $(this).attr("selected", true);
			        }
				});				
			
			},500);	
			
			$('#salesOrgName').bind('input propertychange', function() {
				getSalesOrgs("salesOrgId","","");
			});
	        
	        $("#salesOrgId").on(  
   	            "change",function(){
   	            	$('#salesOrgName').val($(this).find("option:selected").text()).change();
   	            	$('#salesOrgName').focus();
   	            	$("#parentSalesTeamName").val("");
   	            	var h = "<option value='' myvalue=''>请选择上级团队</option>";
		            $("#parentSalesTeamCode").empty();
		            $("#parentSalesTeamCode").append(h);
		            getParents($("#salesOrgId").val());
   	            }
    	    );
    	    
			$("#parentSalesTeamName").bind('input propertychange', function() {
				getParents($("#salesOrgId").val());
			}); 
	        
	        $("#parentSalesTeamCode").on(  
   	            "change",function(){
   	            	$('#parentSalesTeamName').val($(this).find("option:selected").text());
   	            	$('#PTcode').val($(this).find("option:selected").attr("myvalue"));
   	            }
	    	) 	        
		});
		
		function getParents(salesOrgId){
			 var salesTeamName = $("#parentSalesTeamName").val();
			 $.ajax({
		         type: "POST",
		         url: "salesTeamInfo/getParents",
		         data:{salesTeamName:salesTeamName,salesOrgId:salesOrgId,teamTreeFlag:1},
		         dataType: "json",
		         success: function(data){
		        	 	var salesOrgs = data.rows;
					 	var h = "<option value='' myvalue=''>请选择上级团队</option>";
			            $.each(salesOrgs, function(key, value) {
			            	//alert(value.insuranceCompanyCode);
			                h += "<option value='" + value.salesTeamCode + "' myvalue='"+ value.treeCode +"' myId='"+ value.salesTeamId +"'>" + value.salesTeamName //下拉框序言的循环数据
			                + "</option>";
			            });
			            $("#parentSalesTeamCode").empty();
			            $("#parentSalesTeamCode").append(h);
		         }
		     })
		}
		
		function getSalesOrgs(selectId,orgLevel,indexCode){
			 var salesOrgName = $("#salesOrgName").val();
			 $.ajax({
		         type: "POST",
		         url: "salesOrgInfo/findSalesOrgs",
		         data:{salesOrgLevel:orgLevel,treeCode:indexCode,salesOrgName:salesOrgName},
		         dataType: "json",
		         success: function(data){
		        	//alert(data.rows);
		        	var salesOrgs = data.rows;
					var h = "<option value='' myvalue=''>请选择组织机构</option>";
		            $.each(salesOrgs, function(key, value) {
		            	//alert(value.insuranceCompanyCode);
		                h += "<option value='" + value.salesOrgId + "' myvalue='"+ value.salesOrgCode +"'>" + value.salesOrgName //下拉框序言的循环数据
		                + "</option>";  
		            });
		            $("#"+selectId).empty();
		            $("#"+selectId).append(h);
		         }
		     });
		}	
		
		var SalesTeamInfo = function () {
			return{				
				 //添加
		        add:function () {
		        	document.getElementById("saveButton").setAttribute("disabled", true);
		            if($("#addForm").data('bootstrapValidator').validate().isValid()){
		            	flag = true;
		            	if($("#dateOfEstablishment").val()==""){
		            		$.alert({
		                        title: '提示信息！',
		                        content: '成立时间不能为空!',
		                        type: 'red'
		                    });
		            		flag = false;
		        		    document.getElementById("saveButton").removeAttribute("disabled");
		        		    return false;
		            	}
		            	if($("#parentSalesTeamCode").find("option:selected").attr("myid")==$("#salesTeamId").val()){
		            		$.alert({
		                        title: '提示信息！',
		                        content: '上级销售团队不能是自己!',
		                        type: 'red'
		                    });
		            		flag = false;
		        		    document.getElementById("saveButton").removeAttribute("disabled");
		        		    return false;
		            	}
		            	if($("#salesOrgId").val()==''){
		            		$.alert({
		                        title: '提示信息！',
		                        content: '请选择组织机构',
		                        type: 'red'
		                    });
		            		flag = false;
		        		    document.getElementById("saveButton").removeAttribute("disabled");
		        		    return false;
		            	}
		            	if($("#teamLevel").val()=='03' && $("#parentSalesTeamCode").val()==''){
		            	     $.alert({
		                        title: '提示信息！',
		                        content: '处长级别请选择上级团队',
		                        type: 'red'
		                    });
		            		flag = false;
		        		    document.getElementById("saveButton").removeAttribute("disabled");
		        		    return false;
						}
		    			if(flag){
			            	$.ajax({
			                    url:'salesTeamInfo/updateSalesTeamInfo',
			                    dataType:'json',
			                    type:'post',
			                    data:$("#addForm").serialize(),
			                    success:function(data){
			                        if("200" == data.messageCode){
			                        	 $.alert({
			                                 title: '提示信息！',
			                                 content: '添加成功!',
			                                 type: 'blue'
			                             });
			                 		    document.getElementById("saveButton").removeAttribute("disabled");
			                 		    windowCloseTab();
			                        }else if("999" == data.messageCode){
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
			
		        //表单验证
		        formValidator:function () {
		            $("#addForm").bootstrapValidator({
		                fields:{
		                	salesOrgName:{
		                	    trigger:"change",
		                		validators:{
		                            notEmpty:{
		                                message:"组织机构名称不能为空"
		                            }
		                        }
							},
							salesOrgId:{
								validators:{
									notEmpty:{
										message:"组织机构代码不能为空"
									}
								}
							},
							salesTeamCode:{
								validators:{
									notEmpty:{
										message:'销售团队代码不能为空',
									},
									stringLength:{
										max:32,
										message:'字符长度不能超过32'
									}
								}
							},
							teamLevel:{
								validators:{
									notEmpty:{
										message:'团队级别不能为空',
									}
								}
							},
							salesTeamNameLess:{
								validators:{
									notEmpty:{
										message:'团队简称不能为空',
									},
									stringLength:{
										max:64,
										message:'字符长度不能超过64'
									}
								}
							},
							teamStatus:{
								validators:{
									notEmpty:{
										message:'团队状态不能为空',
									}
								}
							},
							salesTeamName:{
								validators:{
									notEmpty:{
										message:'团队名称不能为空',
									},
									stringLength:{
										max:64,
										message:'字符长度不能超过64'
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
	<form class="form-horizontal" id="addForm"  method="post">
	<div class="form-group" style="margin-top:20px;">
	<input type="hidden" id="salesTeamId" name="salesTeamId" value="${salesTeamInfo.salesTeamId}" />
	</div>			
	<div class="form-group">
		<label class="col-md-2 control-label">组织机构名称*：</label>
		<div class="col-md-3 ">
			<input type="text" id="salesOrgName"  name="salesOrgName" value="${salesOrgInfo.salesOrgName}" class="form-control form-control-static" placeholder="请输入组织机构名称">
		</div>

		<label class="col-md-2 control-label">组织机构*：</label>
		<div class="col-md-3 ">
			<select class="form-control form-control-static" id="salesOrgId" name="salesOrgId" hideValue="${salesTeamInfo.salesOrgId}" >
                      <option value="">请选择机构</option>
           		</select>				
           	</div>	
	</div>

	<div class="form-group">
		<label class="col-md-2 control-label">上级销售团队：</label>
		<div class="col-md-3 ">
			<input type="text" id="parentSalesTeamName"  name="parentSalesTeamName" value="${psalesTeamInfo.salesTeamName}"  class="form-control form-control-static" placeholder="请输入上级团队名称">
		</div>

		<label class="col-md-2 control-label">上级销售团队名称：</label>
		<input type="hidden" id="PTcode" name="PTcode" value="" />
		<div class="col-md-3 ">
			<select class="form-control form-control-static" id="parentSalesTeamCode" name="parentSalesTeamCode" hideValue="${salesTeamInfo.parentSalesTeamCode}" >
                      <option value="">请选择上级团队</option>
           		</select>				
           	</div>	
	</div>
	
	<div class="form-group">
		<label class="col-md-2 control-label">销售团队名称*：</label>
		<div class="col-md-3 ">
			<input type="text" id="salesTeamName"  name="salesTeamName" value="${salesTeamInfo.salesTeamName}"  class="form-control form-control-static" placeholder="请输入销售团队名称">
		</div>
           	
		<label class="col-md-2 control-label">销售团队代码*：</label>
		<div class="col-md-3 ">
			<input type="text"  id="salesTeamCode"  name="salesTeamCode" value="${salesTeamInfo.salesTeamCode}" class="form-control form-control-static" placeholder="请输入销售团队代码">
		</div>             	
	</div>	
	
	<div class="form-group">
		<label class="col-md-2 control-label">销售团队简称*：</label>
		<div class="col-md-3 ">
			<input type="text" id="salesTeamNameLess"  name="salesTeamNameLess" value="${salesTeamInfo.salesTeamNameLess}" class="form-control form-control-static" placeholder="请输入销售团队简称">
		</div>
		
		<label class="col-md-2 control-label">销售团队状态*：</label>
		<div class="col-md-3 ">
			<select class="form-control form-control-static" id="teamStatus" name="teamStatus">
               <option value="0" <c:if test="${salesTeamInfo.teamStatus=='0'}"> selected="selected"</c:if> >有效</option>
               <option value="1" <c:if test="${salesTeamInfo.teamStatus=='1'}"> selected="selected"</c:if> >无效</option>
            </select>				
        </div>	            	
	</div>
				
	<div class="form-group">
		<label class="col-md-2 control-label">销售团队级别*：</label>
		<div class="col-md-3 ">
			<select class="form-control form-control-static" id="teamLevel" name="teamLevel">
				<option value="">请选择</option>
<%--				<c:if test="${userName != 'superAdmin'}">--%>
<%--                	<option value="01" <c:if test="${salesTeamInfo.teamLevel=='01'}"> selected="selected"</c:if> >总监级团队</option>--%>
<%--				</c:if>--%>
				<option value="02" <c:if test="${salesTeamInfo.teamLevel=='02'}"> selected="selected"</c:if> >部长级/业务总监级团队</option>
                <option value="03" <c:if test="${salesTeamInfo.teamLevel=='03'}"> selected="selected"</c:if> >处长级/业务经理级团队</option>
                <option value="04" <c:if test="${salesTeamInfo.teamLevel=='04'}"> selected="selected"</c:if> >预留级别</option>
           	</select>				
        </div>
	</div>				
	<div class="form-group">
		<label class="col-md-2 control-label">职场传真：</label>
		<div class="col-md-3 ">
			<input type="text" id="fax"  name="fax" value="${salesTeamInfo.fax}" class="form-control form-control-static" placeholder="请输入公司传真">
		</div>

		<label class="col-md-2 control-label">职场电话：</label>
		<div class="col-md-3 ">
			<input type="text" id="tel"  name="tel" value="${salesTeamInfo.tel}" class="form-control form-control-static" placeholder="请输入公司电话">
		</div>
	</div>	
           
          <div class="form-group"> 
		<label class="col-md-2 control-label">成立时间 *：</label>
		<div class="col-md-3 ">
                 <input class="form-control form-control-static" id="dateOfEstablishment" name="dateOfEstablishment" value="${salesTeamInfo.dateOfEstablishment}" type="text" placeholder="请选择成立时间" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
		</div>
		
		<label class="col-md-2 control-label">邮政编码：</label>
		<div class="col-md-3 ">
			<input type="text" id="postCode"  name="postCode" value="${salesTeamInfo.postCode}" class="form-control form-control-static" placeholder="请输入邮政编码">
		</div>
	</div>	
	<div class="form-group">
		<label class="col-md-2 control-label">详细地址：</label>
		<div class="col-md-3 ">
			<input style="width:655px;" type="text" id="address"  name="address" value="${salesTeamInfo.address}" class="form-control form-control-static" placeholder="请输入详细地址">
		</div>	
	</div>					
																																										
     <div class="modal-footer col-md-6">
     <!--用来清空表单数据-->
     <input type="reset" name="reset" style="display: none;" />
         <button type="button" class="btn btn-default" onclick="windowCloseTab()">关闭</button>
        <button id="saveButton" type="button" onclick="SalesTeamInfo.add()" class="btn btn-primary">保存</button>
     </div>
     </form>
     </div>
</body>
</html>