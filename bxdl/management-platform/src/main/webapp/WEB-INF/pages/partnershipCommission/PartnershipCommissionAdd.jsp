<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/core.jsp" %>
<!DOCTYPE  html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <title>新增佣金率</title>
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
	
			 getSalOrlInfor("salOrlInfor")
			  getInsuranceCompany("insurance");
			 /*   $('#proList').append('<tr  style=\"background: silver;\">'
						+'<th width=\"8%\" >子产品名称</th>'
						+'<th width=\"8%\" >子产品编码</th>'
						+'<th width=\"8%\" >保险起期（年）</th>'
						+'<th width=\"8%\" >保险止期（年）</th>'
						+'<th width=\"8%\" >缴费起期（年）</th>'
					    +'<th width=\"8%\" >缴费止期（年）</th>'
					    +'<th width=\"8%\" >第1年度费率（%）</th>'
					    +'<th width=\"8%\" >第2年度费率（%）</th>'
					    +'<th width=\"8%\" >第3年度费率（%）</th>'
					    +'<th width=\"8%\">第4年度费率（%）</th>'
					    +'<th width=\"8%\">第5年度费率（%）</th>'
					    +'<th width=\"8%\">第6年度费率（%）</th>'
					    +'</tr>'
		    	   ); */
		});
		
		function updateProductsListRows(){
			var rows  = $('#productsList-table').bootstrapTable('getData');
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
			$('#productsList-table').bootstrapTable('updateRow', {rows: rows})
			return rows;
		};
		
		var partnershipAdd = function () {
			return{				
				 //添加
		        add:function () {
		        	document.getElementById("saveButton").setAttribute("disabled", false);
	            	flag = true; 
	            	var rows  = $('#productsList-table').bootstrapTable('getData');
	            	for (var i = 0; i < rows.length; i++) {
	            		 var first = $("#"+i+"FIRST_ANNUAL_RATE_inside_standard").val();
	            		 var second = $("#"+i+"SECOND_ANNUAL_RATE_inside_standard").val();
	            		 var third = $("#"+i+"THIRD_ANNUAL_RATE_inside_standard").val();
	            		 var fourth = $("#"+i+"FOURTH_ANNUAL_RATE_inside_standard").val();
	            		 var fifth = $("#"+i+"FIFTH_ANNUAL_RATE_inside_standard").val();
	            		 var sixth = $("#"+i+"SIXTH_ANNUAL_RATE_inside_standard").val();
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
	            	if(isEmpty($("#computationalTerm_add").val())){
	           			 $.alert({
	       		            title: '提示信息！',
	       		            content: '计算项为空！',
	       		            type: 'red'
	           		     });
	           			 flag = false;
	           			 document.getElementById("saveButton").removeAttribute("disabled");
	           			 return;
           	     	}
	            	if(isEmpty($("#salOrlInfor").val())){
	           			 $.alert({
	       		            title: '提示信息！',
	       		            content: '销售机构为空！',
	       		            type: 'red'
	           		     });
	           			 flag = false;
	           			 document.getElementById("saveButton").removeAttribute("disabled");
	           			 return;
          	     	}
	            	if(isEmpty($("#insurance").val())){
	           			 $.alert({
	       		            title: '提示信息！',
	       		            content: '保险公司为空！',
	       		            type: 'red'
	           		     });
	           			 flag = false;
	           			 document.getElementById("saveButton").removeAttribute("disabled");
	           			 return;
          	     	}
	            	if(isEmpty($("#productName").val())){
	           			 $.alert({
	       		            title: '提示信息！',
	       		            content: '产品为空！',
	       		            type: 'red'
	           		     });
	           			 flag = false;
	           			 document.getElementById("saveButton").removeAttribute("disabled");
	           			 return;
          	     	}
		    	    if(flag){
		    		  var rows = updateProductsListRows();   	
		    		  $("#stringJson").val(JSON.stringify(rows));
			            	$.ajax({
			                    url:'partnership_commission_manager/add_partnership_commission',
			                    type:'post',
			                    dataType:'json',
			                 /*    json: JSON.stringify(rows), */
			                    /* productNameValue:$("#productNameValue").val(),
			                    salesOrgName:$("#salesOrgName").val(),
			                    insuranceCompanyNameValue:$("#insuranceCompanyNameValue").val(),
			                    productNameValue:$("#productNameValue").val() */
			                  /*  $("#partnershipAddForm").serialize() */
			                
			                     data:$("#partnershipAddForm").serialize(),
			                    success:function(data){
			                        if(data){
			                        	windowCloseTab();
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
			            }else{
			            	document.getElementById("saveButton").removeAttribute("disabled");
			            }
		        },
				}
			}();		
			function getSalOrlInfor(selectId){
				 $.ajax({
			         type: "POST",
			         url: "salesOrgInfo/findSalesOrgs",
                     data:{dataAuthFlag:'1'},
			         dataType: "json",
			         success: function(data){
			        	var dataObject = data.rows;
						var h = "<option value=''>请选择内部组织机构</option>";
			            $.each(dataObject, function(key, value) {
			                h += "<option value='" + value.salesOrgId + "' salesOrgLevelValue='"+ value.salesOrgLevel+ "' salesOrgCodeValue='"+ value.salesOrgCode+ "' salesOrgCodeName='"+ value.salesOrgName+ "'>"  + value.salesOrgName //下拉框序言的循环数据
			                + "</option>"; 
			            });
			            $("#"+selectId).empty();
			            $("#"+selectId).append(h);
			         if(selectId="salOrlInfor"){
				            $("#"+selectId).on(  
				    	            "change",function(){
				    	            	var salesOrgIdValues = $("#salOrlInfor").find("option:selected").attr("value");
				    	            	var salesOrgCodeNameValues = $("#salOrlInfor").find("option:selected").attr("salesOrgCodeName");
				    	            	$('#salesOrgName').val(salesOrgCodeNameValues);
				    	            	//根据所选择的内容给其它input框赋值
				    	            	var companyId = $("#insurance").val();
				    	            	if(!isEmpty(companyId) && !isEmpty($('#salOrlInfor').val())){
				    	            		getProductName("productName",companyId,$('#salOrlInfor').val());
				    	            	}
				    	            }
			    	        )
			            } 
			         }
			     });
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
				                h += "<option value='" + value.insuranceCompanyId + "' orgLevelValue='"+ value.orgLevel+ "' insuranceCompanyCodeValue='"+ value.insuranceCompanyCode+ "' insuranceCompanyNameValues='"+ value.insuranceCompanyName +"'>"  + value.insuranceCompanyName //下拉框序言的循环数据
				                + "</option>"; 
				            });
				            $("#"+selectId).empty();
				            $("#"+selectId).append(h);
				         if(selectId="insurance"){
					            $("#"+selectId).on(  
					    	            "change",function(){
					    	            	var orgLevels = $("#insurance").find("option:selected").attr("orgLevelValue");
					    	            	var companyId = $("#insurance").find("option:selected").attr("value");
					    	            	var insuranceCompanyCodeValues = $("#insurance").find("option:selected").attr("insuranceCompanyCodeValue");
					    	            	$('#insuranceCompanyNameValue').val($("#insurance").find("option:selected").attr("insuranceCompanyNameValues"));
					    	            	//根据所选择的内容给其它input框赋值
					    	            	var salesOrgId = $('#salOrlInfor').val();
					    	            	if(!isEmpty(companyId) && !isEmpty(salesOrgId)){
					    	            		getProductName("productName",companyId,salesOrgId);
					    	            	}
					    	            }
				    	        )
				            } 
				         }
				     });
				}	  
			    
			    function getProductName(selectId,companyId,salesOrgId){
			       var trem = $("#computationalTerm_add").val();
					 $.ajax({
						 data:{companyId:companyId,salesOrgId:salesOrgId,computationalTerm:trem},
				         type: "POST",
				         url: "/partnership_commission_manager/findInsurProducts",
				         dataType: "json",
				         success: function(data){
				        	var dataObject = data.rows;
				        	if(dataObject.length==0){
				        	 	$('#proList').empty("");
				        	}
							var h = "<option value=''>请选择产品</option>";
				            $.each(dataObject, function(key, value) {
				                h += "<option value='" + value.productId + "'productCodeValue='"+ value.productCode+ "' insuranceCompanyId='"+ value.insuranceCompanyId +"' productNamevalues='"+ value.productName +"'>"  + value.productName //下拉框序言的循环数据
				                + "</option>"; 
				            });
				            $("#"+selectId).empty();
				            $("#"+selectId).append(h);
				            if(selectId="productName"){
					            $("#"+selectId).on(
					    	            "change",function(){
					    	            	var orgLevels = $("#productName").find("option:selected").attr("orgLevelValue");
					    	            	var productCode = $("#productName").find("option:selected").attr("productCodeValue");
					    	            	$('#productNameValue').val($("#productName").find("option:selected").attr("productNamevalues"));
					    	                var proId=$('#productName option:selected').val();
					    	                $('#proList').empty("");
					    	                //getProductNameSub(proId);
					    	                $('#productId').val(proId);
					    	                $('#add_productCode').val(productCode);
					    	                insProductSub.searchProduct();
					    	            	//根据所选择的内容给其它input框赋值
					    	            }
				    	        )
				            } 
				         }
				     });
				}
			    
			    var insProductSub = {
					    seItem: null		//选中的条目
					};
				
					$(function () {	
						insProductSub.init();
					});
					
					var insProductSub = function (){
						return{
							init:function (){
					            $('#productsList-table').bootstrapTable({
					            	url: "/partnership_commission_manager/partnership_commission_list_sub",
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
					                pageSize : 100, //默认每页条数
					                pageNumber : 1, //默认分页
					                pageList : '',//分页数
					                toolbar:"",
					                showColumns : false, //显示隐藏列
					                uniqueId: "PRODUCTS_RATIO_ID", //每一行的唯一标识，一般为主键列
					                queryParamsType:'',
					                queryParams: insProductSub.queryParams,//传递参数（*）
					                columns : [{
					                    field : "PRODUCTS_RATIO_ID",
					                    title : "编号",
					                    align : "center",
					                    valign : "middle",
					                    sortable : "true",
				  			            formatter:function(value,row,index){
				  			                var pageSize=$('#productsList-table').bootstrapTable('getOptions').pageSize;//通过表的#id 可以得到每页多少条
				  			                var pageNumber=$('#productsList-table').bootstrapTable('getOptions').pageNumber;
				  			                return pageSize * (pageNumber - 1) + index + 1;    //返回每条的序号： 每页条数 * （当前页 - 1 ）+ 序号
				  			            }
					                },{
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
					                   /* formatter: function (value, row , index) {
					                    	  var FIRST_ANNUAL_RATE = (row.VALUE_COMMISSION_COEFFICIENT + row.IN_STANDARD_COMMISSION_COEFFICIENT)*row.INDEXING_COEFFICIENT/100;
					                		  return '<input readonly="readonly" id="'+index+'FIRST_ANNUAL_RATE_inside_standard" style="width:60px;"  type="number" step="0.01" min="0" value="'+FIRST_ANNUAL_RATE+'" name="FIRST_ANNUAL_RATE" />%';
					                	  }*/
					                   formatter: function (value, row , index) {
					                		  return '<input  id="'+index+'FIRST_ANNUAL_RATE_inside_standard" style="width:60px;"  type="number" step="0.01" min="0" value="'+row.FIFTH_ANNUAL_RATE+'" name="FIRST_ANNUAL_RATE" />%';
					                	  }
					                },{
					                    field : "SECOND_ANNUAL_RATE",
					                    title : "第2年度费率",
					                    formatter: function (value, row , index) {
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
									productId: $("#productId").val(), 
									salesOrgId: $("#salOrlInfor").val(),
									insuranceCompanyId: $("#insurance").val()
								};
								return temp;
							},
					        //搜索
					        searchProduct:function () {
					        	 $("#productsList-table").bootstrapTable('refresh');
					        },
						}
					}();		 	    
	</script> 
</head>
<body>
        <div class="container">
		<form class="form-horizontal" id="partnershipAddForm"  method="post"  >
		<input type="hidden" id="stringJson"  name="stringJson" />
		<input type="hidden" id="productId"  name="productId" />
        <input type="hidden" id="productNameValue"  name="productNameValue" />
		<input type="hidden" id="salesOrgName"  name="salesOrgName" />
		<input type="hidden" id="insuranceCompanyNameValue"  name="insuranceCompanyNameValue" />
		<input type="hidden" id="add_productCode"  name="productCode" />
		<div class="form-group" style="margin-top:20px;"></div>
		<div class="form-group">
               <label class="control-label" style="padding-left:50px;color:red">说明：请先选择销售机构和保险公司然后才能选择父产品信息！</label>
        </div>       	
		    <div class="form-group">
				<label for="disabledSelect"  class="col-sm-2 control-label"> 计算项：</label>
			    <div class="col-md-3">
			            <select class="form-control form-control-static" id="computationalTerm_add" name="computationalTerm">
	                        <option value="">请选择</option>
	                        <option value="0">C保护</option>
	                        <option value="1">P手续费</option>
							<option value="2">基础佣金率</option>
	             		</select>			
			    </div>
			<label class="col-md-2 control-label">销售机构机构名称*：</label>
			<div class="col-md-3 ">
			<select class="form-control " id="salOrlInfor"  name="salesOrgId" ></select>
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
<table  id="productsList-table"  class="table text-nowrap" ></table>
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