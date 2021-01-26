<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/core.jsp" %>
<!DOCTYPE  html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <title>保险产品管理</title>
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Cache" content="no-cache">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   	<link rel="stylesheet" href="${path}/css/system/role/role.css">
   <style>
      a.click{
	    background:#ff0000;
	    color:#ffffff;
	    }
	    a{
	    color:green
	    }
 
    </style>
    
   	<script src="${path}/js/system/comm.js" charset="UTF-8" type="text/javascript"></script>
    <script src="${path}/static/js/bootstrap.js"></script>
	<script type="text/javascript">
		var insProduct = {
		    seItem: null		//选中的条目
		};
	
		$(function () {	
		 	$.ajax({
		         type: "POST",
		         data:{sysParameter:"0"},
		         url: "Insurance_type_manager/find_types",
		         dataType: "json",
		         success: function(data){
		        	var dataObject = data.rows;
					var h = "<option value=''>请选择本系统险种！</option>";
		            $.each(dataObject, function(key, value) {
		            	  h += "<option value='" + value.insuranceTypeId + "'>"  + value.insuranceTypeName //下拉框序言的循环数据
			                + "</option>"; 
		            });
		            $("#P_insuranceType_S").empty();
		            $("#P_insuranceType_S").append(h);
		         }
		    }); 
			
			insProduct.init();
		});
		
		function openDetailTab(permission){
			if(insProduct.checkSingleData()){
				var id = insProduct.seItem.PRODUCT_ID;
				 createAddProcessTab('insProduct:update',id);
			}
		}	
		
		function setValue(selectId,value){
			$("#"+selectId+" option").each(function(){
		        if($(this).val()==value){
		        	value = $(this).text();
		        }
			});	
	        return value;			
		}
		
		function openDetailTabDetail(permission){
			if(insProduct.checkSingleData()){
				var id = insProduct.seItem.PRODUCT_ID;
				 createAddProcessTab('insProduct:detail',id);
			}
		}	
		/* 启用 */
		function enable(permission){
			if(insProduct.checkSingleData()){
				var productId = insProduct.seItem.PRODUCT_ID;
				if("启用中" == insProduct.seItem.PRODUCT_STATUS){
					$.alert({
						title: '提示信息！',
						content: '该产品已经是启用状态',
						type: 'red'
					});
					return;
				}
				var productStatus=0;
				$.confirm({
			        title: '提示信息!',
			        content: '您确定要启用选中的对象吗？',
			        type: 'blue',
			        typeAnimated: true,
			        buttons: {
			        	确定: {
				        	action: function(){
								 $.ajax({
							         type: "POST",
							         data:{productId:productId,productStatus:productStatus},
							         url: "product_basic_information/update_insurance_product_status",
							         dataType: "json",
							         success: function(messageCode){
							        	 var jsonobj= eval(messageCode);
							        	 if(jsonobj.messageCode!="200"){
							        		 $("#insProduct-table").bootstrapTable('refresh');
												$.alert({
													title: '提示信息！',
													content: '执行失败！',
													type: 'red'
												});
							        	 }else{
							        		 $("#insProduct-table").bootstrapTable('refresh');
												$.alert({
													title: '提示信息！',
													content: '执行成功',
													type: 'blue'
												});
							        	 }
							         },
							        error : function(e){
							        	console.log(e.status);
							        	console.log(e.responseText);
							       	}
							   
								})
				            }
			        	},
				                           取消: function () {
				        	return true; 
				        }
			        }
				});	
		 	}	
	 	}
		/* 废除 */
		function abolish(permission){
			if(insProduct.checkSingleData()){
				var productId = insProduct.seItem.PRODUCT_ID;
				if("已废除" == insProduct.seItem.PRODUCT_STATUS){
					$.alert({
						title: '提示信息！',
						content: '该产品已经是废除状态',
						type: 'red'
					});
					return;
				}
				var productStatus=2;
				$.confirm({
			        title: '提示信息!',
			        content: '您确定要废除选中的对象吗？',
			        type: 'blue',
			        typeAnimated: true,
			        buttons: {
			        	确定: {
				        	action: function(){
								 $.ajax({
							         type: "POST",
							         data:{productId:productId,productStatus:productStatus},
							         url: "product_basic_information/update_insurance_product_status",
							         dataType: "json",
							         success: function(messageCode){
							        	 var jsonobj= eval(messageCode);
							        	 if(jsonobj.messageCode!="200"){
							        		 $("#insProduct-table").bootstrapTable('refresh');
												$.alert({
													title: '提示信息！',
													content: '执行失败！',
													type: 'red'
												});
							        	 }else{
							        		 $("#insProduct-table").bootstrapTable('refresh');
												$.alert({
													title: '提示信息！',
													content: '执行成功！',
													type: 'blue'
												});
							        	 }
							         },
							        error : function(e){
							        	console.log(e.status);
							        	console.log(e.responseText);
							       	}
							   
								})
				            }
			        	},
				              取消: function () {
				        	return true; 
				        }
			        }
				});	
		 	}	
	 	}
		
		/*详情 */
		function createDetailProcessTab(permission){
			if(insProduct.checkSingleData()){
				var productId = insProduct.seItem.PRODUCT_ID;
				 $.ajax({
			         type: "POST",
			         data:{id:productId},
			         url: "product_basic_information/toProductDetailPage",
			         dataType: "json",
			         success: function(messageCode){
			        	 
			         },
			        error : function(e){
			        	console.log(e.status);
			        	console.log(e.responseText);
			       	}
			   
			})
		 }	
	 }
		
		var insProduct = function (){
			return{
				init:function (){
		            $('#insProduct-table').bootstrapTable({
		            	url: "/product_basic_information/select_product_list",
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
		                pageSize : 10, //默认每页条数
		                pageNumber : 1, //默认分页
		                pageList : [5, 10, 20, 50 ],//分页数
		                toolbar:"#insProduct_toolbar",
		                showColumns : false, //显示隐藏列
		                uniqueId: "PRODUCT_ID", //每一行的唯一标识，一般为主键列
		                queryParamsType:'',
		                queryParams: insProduct.queryParams,//传递参数（*）
		                columns : [{
		                    checkbox: true
		            	},{
	                		//field: 'Number',//可不加
	 						title: '序号',//标题  可不加
	 						switchable:false,
	  			            formatter:function(value,row,index){
	  			                //return index+1; //序号正序排序从1开始
	  			                var pageSize=$('#insProduct-table').bootstrapTable('getOptions').pageSize;//通过表的#id 可以得到每页多少条
	  			                var pageNumber=$('#insProduct-table').bootstrapTable('getOptions').pageNumber;//通过表的#id 可以得到当前第几页
	  			                return pageSize * (pageNumber - 1) + index + 1;    //返回每条的序号： 每页条数 * （当前页 - 1 ）+ 序号
	  			            }
	 	                } ,{
		                    field : "PRODUCT_ID",
		                    title : "编号",
		                    align : "center",
		                    valign : "middle",
		                    sortable : "true",
		                    visible:false
		                } ,{
		                    field : "INSURANCE_COMPANY_NAME",
		                    title : "组织机构名称",
		                    align : "center",
		                    valign : "middle",
		                    sortable : "true"
		                },{
		                    field : "COMPANY_INSURANCE_CODE",
		                    title : "保险公司险种代码",
		                    align : "center",
		                    valign : "middle",
		                    sortable : "true"
		                },{
		                    field : "PRODUCT_CODE",
		                    title : "产品代码",
		                    align : "center",
		                    valign : "middle",
		                    sortable : "true"
		                },{
		                    field : "PRODUCT_NAME",
		                    title : "产品名称",
		                    align : "center",
		                    valign : "middle",
		                    sortable : "true"
		                },{
		                    field : "TAKE_EFFECT_DATE",
		                    title : "生效日期",
		                    align : "center",
		                    valign : "middle",
		                   /*  sortable : "true",
		                    sortOrder:"desc" */
		                },{
		                    field : "INVALID_DATE",
		                    title : "失效日期",
		                    align : "center",
		                    valign : "middle",
		                  
		                }  /* ,{
		                    field : "PRODUCT_ID",
		                    title : "保险期间和缴费期间",
		                    align : "center",
		                    valign : "middle",
		                    sortable : "true",
		                     formatter: function (value, row, index) {
		                    	 
		                        		return "<a  href=\"javascript:void(0)\"  onclick = \"document.getElementById('light').style.display='block';document.getElementById('fade').style.display='block'\" >查看</a>";
		                        	
		    						} 
		                }  */,{
		                    field : "INSURANCE_TYPE",
		                    title : "系统险种",
		                    align : "center",
		                    valign : "middle",
		                    sortable : "true",
		                    formatter:function(value,row,index){ 			            	
				            	return setValue("P_insuranceType_S",row.INSURANCE_TYPE);
		                    }
		                },{
		                    field : "CREATED_TIME",
		                    title : "创建日期",
		                    align : "center",
		                    valign : "middle",
		                    sortable : "true",
		                    sortOrder:"desc" 
		                },{
		                    field : "SALE_TYPE",
		                    title : "发布状态",
		                    align : "center",
		                    valign : "middle",
		                    sortable : "true"
		                },{
		                    field : "PRODUCT_STATUS",
		                    title : "产品状态",
		                    align : "center",
		                    valign : "middle",
		                    sortable : "true"
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
						topCompanyName: $("#insuranceCompanyName").val(),
						topCompanyCode:$("#insuranceCompanyCode").val(),
						insuranceCompanyName: $("#customerName").val(),
						insuranceCompanyCode:$("#cardNo").val(),
		                productNmae:$("#productNmae").val(),
		                insuranceType:$("#P_insuranceType_S").val(), 
		                productStatus:$("#productStatus").val(), 
		                minCreateTime:$("#minCreateTime").val(), 
		                maxCreateTime:$("#maxCreateTime").val(),
		                saleType:$("#saleType").val(), 
					};
					return temp;
				},
				
		        checkSingleData:function () {
		            var selected = $('#insProduct-table').bootstrapTable('getSelections');
		            if (selected.length == 0) {
		            	 $.alert({
		                     title: '提示信息！',
		                     content: '至少选择一条记录！',
		                     type: 'red'
		                 });
		                return false;
		            }else if (selected.length > 1){
		            	 $.alert({
		                     title: '提示信息！',
		                     content: '该操作只能选择一条记录!',
		                     type: 'blue'
		                 });
		            }else {
		            	insProduct.seItem = selected[0];
		                return true;
		            }
		        },
		        
		        //搜索
		        searchProduct:function () {
		        	 $("#insProduct-table").bootstrapTable('destroy');
		        	 insProduct.init();
		        },
		        //清空
		        empty:function () {
		            $("#insuranceCompanyName").val('');
		            $("#insuranceCompanyCode").val('');
		            $("#customerName").val('');
		            $("#cardNo").val('');
		            $("#P_insuranceType_S").val('');
		            $("#productStatus").val(''); 
		            $("#minCreateTime").val('');
		            $("#maxCreateTime").val('');
		            $("#productNmae").val(''); 
		            $("#saleType").val('');
		            $("#insProduct-table").bootstrapTable('refresh');
		        },
			}
		}();
		
	</script>
</head>
<body>
 <div class="panel panel-default">
	<div class="panel-body">
		<form id="conFormCashAccount" class=" form-inline hz-form-inline">
			<div class="form-group">
				<label for="insuranceCompanyCode"  class="control-label"> 保险公司代码</label>
		   		<input type="text" class="form-control" id="insuranceCompanyCode" name="insuranceCompanyCode" placeholder="请输入保险公司代码">
		  	</div>

		    <div class="form-group">
				<label for="insuranceCompanyName"  class="control-label"> 保险公司名称</label>
		   		 <input type="text" class="form-control" id="insuranceCompanyName" name="insuranceCompanyName" placeholder="请输入保险公司名称">
		    </div>

		   <div class="form-group">
				<label for="cardNo"  class="control-label">组织机构代码</label>
		   		<input type="text" class="form-control" id="cardNo" name="cardNo" placeholder="请输入组织机构代码">
		  	</div>

		    <div class="form-group">
				<label for="customerName"  class="control-label">组织机构名称</label>
		   		 <input type="text" class="form-control" id="customerName" name="customerName" placeholder="请输入组织机构名称">
		    </div>

		    <div class="form-group">
				<label for="P_insuranceType_S"  class="control-label">险种类别</label>
	            <select class="form-control form-control-static" id="P_insuranceType_S" name="insuranceType">
         		</select>
		    </div>

		     <div class="form-group">
				<label for="productNmae"  class="control-label">产品名称</label>
		   		<input type="text" class="form-control" id="productNmae" name="productNmae" placeholder="请输入产品名称">
		    </div>

		    <div class="form-group">
				<label for="productStatus" class="control-label">产品状态</label>
	            <select class="form-control form-control-static" id="productStatus" name="productStatus">
                    <option value="">请选择</option>
                    <option value="0">启用中</option>
                    <option value="1">待启用</option>
                    <option value="2">已废除</option>
                    <option value="3">已失效</option>
         		</select>
		    </div>

		  	<div class="form-group">
		   		<label for="saleType"   class="control-label">发布类型</label>
		    	<select id="saleType" name="saleType" class="form-control form-control-static">
                    <option value="">请选择</option>
                    <option value="0">线下</option>
                    <option value="1">线上</option>
                    <option value="2">二者皆可</option>
              </select>
		  	</div>

		    <div class="form-group">
				<label class="control-label" for="minCreateTime">创建时间</label>
               <input class="form-control input-sm Wdate" id="minCreateTime" name="minCreateTime" type="text" placeholder="请选择开始时间" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'minCreateTime\');}',dateFmt:'yyyy-MM-dd',readOnly:true})"/>
               <label class="control-label control-label-time">至</label>
               <input class="form-control input-sm Wdate" id="maxCreateTime" type="text" name="maxCreateTime" placeholder="请选择结束时间" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'maxCreateTime\');}',dateFmt:'yyyy-MM-dd',readOnly:true})"/>
		  	</div>

			<div class="form-group">
		  		<button type="button" onclick="insProduct.searchProduct()" class="btn btn-info ">
		   			<span class="glyphicon glyphicon-search" aria-hidden="true" >搜索</span>
		   		</button>
		   		<button type="button" onclick="insProduct.empty()" class="btn btn-danger ">
		   			<span class="glyphicon glyphicon-remove" aria-hidden="true" >清空</span>
		   		</button>
		   	</div>

		</form>
	</div>
</div>

<!--列表 -->
<div style="width: 99%;overflow: auto;">
	<table id="insProduct-table" class="table table-hover table-striped table-condensed table-bordered"></table>
</div>
<!--toolbar  -->
<div id="insProduct_toolbar" class="btn-toolbar">
	<%-- <shiro:hasPermission name="insProduct:add"> --%>
    	<button class="btn btn-info" type="button" onclick="createAddProcessTab('insProduct:add','')">
	    		<span class="glyphicon glyphicon-plus" >添加</span>
    	</button>
   <%--  </shiro:hasPermission>
    <shiro:hasPermission name="insProduct:update"> --%>
    	<button type="button" class=" btn btn-info" onclick="openDetailTab('insProduct:update')">
    			<span class="glyphicon glyphicon-pencil" >修改</span>
    	</button>
  <%--   </shiro:hasPermission>
    <shiro:hasPermission name="insProduct:delete"> --%>
    	<button class=" btn btn-info" type="button" onclick="enable()">
    			<span class="glyphicon glyphicon-enable" >启用</span>
    	</button>
  <%--   </shiro:hasPermission> --%>
  
 <%--  <shiro:hasPermission name="insProduct:delete">  --%>
    	<button class=" btn btn-danger" type="button" onclick="abolish()">
    			<span class="glyphicon glyphicon-abolish" >废除</span>
    	</button>
  <%--  </shiro:hasPermission> 
  <shiro:hasPermission name="insProduct:delete">  --%>
    	<button class=" btn btn-info" type="button" onclick="openDetailTabDetail('insProduct:detail')"  >
    			<span class="glyphicon glyphicon-detail" >查看</span>
    	</button>
  <%--   </shiro:hasPermission> --%>
  <%--   <shiro:hasPermission name="insProduct:export"> --%>
	    <!-- <button onclick="" type="button" class="btn btn-success">
	      		<span class="glyphicon glyphicon-paste" aria-hidden="true" >导出</span>
	    </button> -->
 	<%-- </shiro:hasPermission>    --%> 
</div> 
</body>
</html>