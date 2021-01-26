<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/core.jsp" %>
<!DOCTYPE  html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <title>编辑佣金率</title>
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
			 getSalOrlInforEdit("salOrlInfor")
			 getInsuranceCompanyEdit("insurance");
			 $('#paramet').val();
			 insProductSubEdit.searchProduct();
		});
		
		function updateProductsListRows(){
			 var rows  = $('#productsList-table_edit').bootstrapTable('getData');
			 for(var index=0;index <rows.length;index++){
				 var row = rows[index];
				 var first = $("#"+index+"FIRST_ANNUAL_RATE_inside_standard").val();
        		 var second = $("#"+index+"SECOND_ANNUAL_RATE_inside_standard").val();
        		 var third = $("#"+index+"THIRD_ANNUAL_RATE_inside_standard").val();
        		 var fourth = $("#"+index+"FOURTH_ANNUAL_RATE_inside_standard").val();
        		 var fifth = $("#"+index+"FIFTH_ANNUAL_RATE_inside_standard").val();
        		 var sixth = $("#"+index+"SIXTH_ANNUAL_RATE_inside_standard").val();
				 row["FIRST_ANNUAL_RATE"]= first;
				 row["SECOND_ANNUAL_RATE"]= second;
				 row["THIRD_ANNUAL_RATE"]= third;
				 row["FOURTH_ANNUAL_RATE"]= fourth;
				 row["FIFTH_ANNUAL_RATE"]= fifth;
				 row["SIXTH_ANNUAL_RATE"]= sixth;
		   }
			 //更新数据源
			$('#productsList-table_edit').bootstrapTable('updateRow', {rows: rows})
			return rows;
		};
		
		var partnershipAdd = function () {
			return{				
				 //添加
		        add:function () {
		        	document.getElementById("saveButton").setAttribute("disabled", false);
	            	flag = true; 
	            	var rows  = $('#productsList-table_edit').bootstrapTable('getData');
	            	for (var i = 0; i < rows.length; i++) {
	            		 var first = $("#"+i+"FIRST_ANNUAL_RATE_inside_standard").val();
	            		 var second = $("#"+i+"SECOND_ANNUAL_RATE_inside_standard").val();
	            		 var third = $("#"+i+"THIRD_ANNUAL_RATE_inside_standard").val();
	            		 var fourth = $("#"+i+"FOURTH_ANNUAL_RATE_inside_standard").val();
	            		 var fifth = $("#"+i+"FIFTH_ANNUAL_RATE_inside_standard").val();
	            		 var sixth = $("#"+i+"SIXTH_ANNUAL_RATE_inside_standard").val();
	            		/* if(isEmpty(first) || isEmpty(second)||isEmpty(third) || isEmpty(fourth)||isEmpty(fifth) || isEmpty(sixth)){
	            			 $.alert({
	            		            title: '提示信息！',
	            		            content: '年度费率能为空！',
	            		            type: 'red'
	            		     });
	            			 flag = false;
	            			 break;
	            		}*/
	            		if(isEmpty(first)){
	            			 $.alert({
	            		            title: '提示信息！',
	            		            content: '基础佣金率不能为空！',
	            		            type: 'red'
	            		     });
	            			 flag = false;
	            			 break;
	            		}
	                }
    		    	if(flag){
		    		  var rows = updateProductsListRows();   	
		    		  $("#stringJson").val(JSON.stringify(rows));
			            	$.ajax({
			                    url:'partnership_commission_manager/update_partnership_commission',
			                    type:'post',
			                    dataType:'json',
			                     data:$("#partnershipAddForm").serialize(),
			                    success:function(data){
			                        if(data){
			                        	windowCloseTab();
			                        	 $.alert({
			                                 title: '提示信息！',
			                                 content: '修改成功!',
			                                 type: 'blue'
			                             });
			                 		    document.getElementById("saveButton").removeAttribute("disabled");
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
		        },
				}
			}();		
			function getSalOrlInforEdit(selectId){
				 $.ajax({
			         type: "POST",
			         url: "salesOrgInfo/findSalesOrgs",
			         dataType: "json",
			         success: function(data){
			        	var dataObject = data.rows;
						var h = "<option value=''>请选择内部组织机构</option>";
			            $.each(dataObject, function(key, value) {
			            	if(value.salesOrgId == $("#salesOrgId_hide").val()){
				                h += "<option value='" + value.salesOrgId + "' salesOrgLevelValue='"+ value.salesOrgLevel+ "' salesOrgCodeValue='"+ value.salesOrgCode+ "' salesOrgCodeName='"+ value.salesOrgName+ "' selected='selected'>"  + value.salesOrgName //下拉框序言的循环数据
				                + "</option>"; 
			                }else{
			                	h += "<option value='" + value.salesOrgId + "' salesOrgLevelValue='"+ value.salesOrgLevel+ "' salesOrgCodeValue='"+ value.salesOrgCode+ "' salesOrgCodeName='"+ value.salesOrgName+ "'>"  + value.salesOrgName //下拉框序言的循环数据
				                + "</option>"; 			                	
			                }
			            });
			            $("#"+selectId).empty();
			            $("#"+selectId).append(h);
			            $("#"+selectId).attr("disabled","disabled");
			         }
			     });
			}	 

			
			    function getInsuranceCompanyEdit(selectId){
					 $.ajax({
				         type: "POST",
				     //    data:{insuranceCompanyName:companyName},
				         url: "insurance_dept/findInsurCompanys",
				         dataType: "json",
				         success: function(data){
				        	var dataObject = data.rows;
							var h = "<option value=''>请选择保险公司</option>";
				            $.each(dataObject, function(key, value){
				            	if(value.insuranceCompanyId == $("#insCompanyId_hide").val()){
					                h += "<option value='" + value.insuranceCompanyId + "' orgLevelValue='"+ value.orgLevel+ "' insuranceCompanyCodeValue='"+ value.insuranceCompanyCode+ "' insuranceCompanyNameValues='"+ value.insuranceCompanyName +"' selected='selected'>"  + value.insuranceCompanyName //下拉框序言的循环数据
					                + "</option>"; 
					                getProductNameEdit("productName",value.insuranceCompanyId);
				            	}else{
					                h += "<option value='" + value.insuranceCompanyId + "' orgLevelValue='"+ value.orgLevel+ "' insuranceCompanyCodeValue='"+ value.insuranceCompanyCode+ "' insuranceCompanyNameValues='"+ value.insuranceCompanyName +"'>"  + value.insuranceCompanyName //下拉框序言的循环数据
					                + "</option>"; 				            		
				            	}
				            });
				            $("#"+selectId).empty();
				            $("#"+selectId).append(h);
				            $("#"+selectId).attr("disabled","disabled");
				         }
				     });
				}	  
			    
			    function getProductNameEdit(selectId,companyId){
					 $.ajax({
						 data:{companyId:companyId},
				         type: "POST",
				         url: "product_basic_information/findInsurProducts",
				         dataType: "json",
				         success: function(data){
				        	var dataObject = data.rows;
				        	if(dataObject.length==0){
				        	 	$('#proList').empty("");
				        	}
							var h = "<option value=''>请选择产品</option>";
				            $.each(dataObject, function(key, value) {
				            	if(value.productId == $("#insProductId_hide").val()){
					                h += "<option value='" + value.productId + "'productCodeValue='"+ value.productCode+ "' insuranceCompanyId='"+ value.insuranceCompanyId +"' productNamevalues='"+ value.productName +"' selected='selected'>"  + value.productName //下拉框序言的循环数据
					                + "</option>"; 
				            	}else{
					                h += "<option value='" + value.productId + "'productCodeValue='"+ value.productCode+ "' insuranceCompanyId='"+ value.insuranceCompanyId +"' productNamevalues='"+ value.productName +"'>"  + value.productName //下拉框序言的循环数据
					                + "</option>"; 				            		
				            	}
				            });
				            $("#"+selectId).empty();
				            $("#"+selectId).append(h);
				            $("#"+selectId).attr("disabled","disabled");
				         }
				     });
				}

			    var insProductSubEdit = {
					    seItem: null		//选中的条目
					};
				
					$(function () {	
						insProductSubEdit.init();
					});
					
					var insProductSubEdit = function (){
						return{
							init:function (){
					            $('#productsList-table_edit').bootstrapTable({
					            	url: "/partnership_commission_manager/partnership_commission_list_sub_edit",
					            	method:"post",
					                dataType: "json",
					                contentType: "application/x-www-form-urlencoded",
					                striped:true,//隔行变色
					                cache:false,  //是否使用缓存
					                pagination: true, //分页
					                sortable: true, //是否启用排序
					                sortOrder: false, 
					                singleSelect: false,
					                search:false, //显示搜索框
					                buttonsAlign: "right", //按钮对齐方式
					                showRefresh:false,//是否显示刷新按钮
					                sidePagination: "server", //服务端处理分页
					                pageSize : 50, //默认每页条数
					                pageNumber : 1, //默认分页
					                pageList : '',//分页数
					                toolbar:"",
					                showColumns : false, //显示隐藏列
					                uniqueId: "PRODUCTS_RATIO_ID", //每一行的唯一标识，一般为主键列
					                queryParamsType:'',
					                queryParams: insProductSubEdit.queryParams,//传递参数（*）
					                columns : [ {
					                    field : "PRODUCTS_RATIO_ID",
					                    title : "编号",
					                    align : "center",
					                    valign : "middle",
					                    sortable : "true"
					                } ,{
					                    field : "PRODUCTS_NAME",
					                    title : "产品名称",
					                    align : "center",
					                    valign : "middle",
					                    sortable : "true"
					                },{
					                    field : "PRODUCTS_CODE",
					                    title : "产品编码",
					                    align : "center",
					                    valign : "middle",
					                    sortable : "true"
					                },{
					                    field : "INSURANCE_PERIOD_MIN",
					                    title : "保险期间下限",
					                    align : "center",
					                    valign : "middle",
					                    sortable : "true"
					                },{
					                    field : "INSURANCE_PERIOD_MAX",
					                    title : "保险期间上限",
					                    align : "center",
					                    valign : "middle",
					                    sortable : "true"
					                },{
					                    field : "RENEW_PERIOD_MIN",
					                    title : "续费期间下限",
					                    align : "center",
					                    valign : "middle",
					                    sortable : "true"
					                },{
					                    field : "RENEW_PERIOD_MAX",
					                    title : "续费期间上限",
					                    align : "center",
					                    valign : "middle",
					                    sortable : "true"
					                },{
					                    field : "FIRST_ANNUAL_RATE",
					                    title : "第1年度费率",
					                    formatter: function (value, row , index){
					                		  return '<input   id="'+index+'FIRST_ANNUAL_RATE_inside_standard" style="width:60px;"  type="number" step="0.01" min="0" value="'+row.FIRST_ANNUAL_RATE+'" name="FIRST_ANNUAL_RATE" />%';
					                	}
					                },{
					                    field : "SECOND_ANNUAL_RATE",
					                    title : "第2年度费率",
					                    formatter: function (value, row , index){
					                		  return '<input   id="'+index+'SECOND_ANNUAL_RATE_inside_standard" style="width:60px;"  type="number" step="0.01" min="0" value="'+row.SECOND_ANNUAL_RATE+'" name="SECOND_ANNUAL_RATE" />%';
					                	}
					                }
					                ,{
					                    field : "THIRD_ANNUAL_RATE",
					                    title : "第3年度费率",
					                    formatter: function (value, row , index) {
					                		  return '<input   id="'+index+'THIRD_ANNUAL_RATE_inside_standard" style="width:60px;"  type="number" step="0.01" min="0" value="'+row.THIRD_ANNUAL_RATE+'" name="THIRD_ANNUAL_RATE" />%';
					                	  }
					                },{
					                    field : "FOURTH_ANNUAL_RATE",
					                    title : "第4年度费率",
					                    formatter: function (value, row , index) {
					                		  return '<input   id="'+index+'FOURTH_ANNUAL_RATE_inside_standard" style="width:60px;"  type="number" step="0.01" min="0" value="'+row.FOURTH_ANNUAL_RATE+'" name="FOURTH_ANNUAL_RATE" />%';
					                	  }
					                },{
					                    field : "FIFTH_ANNUAL_RATE",
					                    title : "第5年度费率",
					                    formatter: function (value, row , index) {
					                		  return '<input   id="'+index+'FIFTH_ANNUAL_RATE_inside_standard" style="width:60px;"  type="number" step="0.01" min="0" value="'+row.FIFTH_ANNUAL_RATE+'" name="FIFTH_ANNUAL_RATE" />%';
					                	  }
					                },{
					                    field : "SIXTH_ANNUAL_RATE",
					                    title : "第6年度费率",
					                    formatter: function (value, row , index) {
					                		  return '<input   id="'+index+'SIXTH_ANNUAL_RATE_inside_standard" style="width:60px;"  type="number" step="0.01" min="0" value="'+row.SIXTH_ANNUAL_RATE+'" name="SIXTH_ANNUAL_RATE" />%';
					                	  }
					                }],
					        	// bootstrap-table-treetreegrid.js 插件配置 -- end
					                formatLoadingMessage : function() {
					                    return "请稍等，正在加载中...";
					                },
					                formatNoMatches : function() {
					                    return '无符合条件的记录';
					                }
					            });
							},
							queryParams:function(params){
								var temp = {
									pageSize: params.pageSize,  //页面大小
									pageNo: params.pageNumber, //页码
									productId: $("#insProductId_hide").val(), 
									paramet:$("#paramet").val(),
									partnershipId:$("#partnershipId").val(),
								};
								console.log(temp);
								return temp;
							},
					        //搜索
					        searchProduct:function () {
					        	 $("#productsList-table_edit").bootstrapTable('refresh');
					        },
						}
					}();	
					
				
	</script> 
</head>
<body>
        <div class="container">
		<form class="form-horizontal" id="partnershipAddForm"  method="post"  >
		<input type="hidden" id="paramet"  value="1" name="paramet" />
		<input type="hidden" id="stringJson"  name="stringJson" />
		<input type="hidden" value='${partnershipDetail.partnershipId}' id="partnershipId"  name="partnershipId" />
		<input type="hidden" value='${partnershipDetail.insProductId}' id="insProductId_hide" />
		<input type="hidden" value='${partnershipDetail.insuranceCompanyId}' id="insCompanyId_hide" />
		<input type="hidden" value='${partnershipDetail.salesOrgId}' id="salesOrgId_hide" />
		<div class="form-group" style="margin-top:20px;"></div>	
		    <div class="form-group">
				<label for="disabledSelect"  class="col-sm-2 control-label">计算项：</label>
			    <div class="col-md-3 ">
			            <select class="form-control form-control-static" id="computationalTerm" name="computationalTerm" disabled="disabled">
	                         <option value="" <c:if test="${partnershipDetail.computationalTerm eq '' }">selected="selected"</c:if>>请选择</option>       
							 <option value="0"<c:if test="${partnershipDetail.computationalTerm eq '0' }">selected="selected"</c:if>>C保护</option>     
							 <option value="1"<c:if test="${partnershipDetail.computationalTerm eq '1' }">selected="selected"</c:if>>P手续费</option>
							<option value="2"<c:if test="${partnershipDetail.computationalTerm eq '2' }">selected="selected"</c:if>>基础佣金率</option>
	             		</select>			
			    </div>
			<label class="col-md-2 control-label">销售机构名称*：</label>
			<div class="col-md-3 ">
			<select class="form-control " id="salOrlInfor"  name="salesOrgId" >
			</select>
			</div>
		</div>
			<div class="form-group">
               <label class="col-md-2 control-label">保险公司名称*：</label>
				<div class="col-md-3 ">
				<select class="form-control " id="insurance"  name="insuranceCompanyId" ></select>
				</div>
				 <label class="col-md-2 control-label">父产品名称*：</label>
				<div class="col-md-3 ">
				<select class="form-control " id="productName"  name="insProductId" ></select>
				</div>
			</div>		
	<div>
	    <!--子产品列表  -->
		<!-- <table id="proList" border="1">
		
		</table> -->
		
	<div >
<table  id="productsList-table_edit"  class="table text-nowrap" ></table>
	</div>	
	
		<div id="test"></div>
	</div>
           <div class="modal-footer col-md-6">
	             <!--用来清空表单数据-->
	             <input type="reset" name="reset" style="display: none;" />
	              <button type="button" class="btn btn-default" onclick="windowCloseTab()">关闭</button>
	             <button id="saveButton" type="button" onclick="partnershipAdd.add()" class="btn btn-primary">保存</button>
           </div>
     </form>
 </div>
</body>
</html>