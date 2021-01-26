<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/core.jsp" %>
<!DOCTYPE  html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <title>保合伙组织机构佣金率设置</title>
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
		var partnership = {
		    seItem: null		//选中的条目
		};
	
		$(function () {	
			partnership.init();
		});
		
		function openDetailTab(permission){
			if(partnership.checkSingleData()){
				var id = partnership.seItem.PARTNERSHIP_ID;
				 createAddProcessTab('partnership:update',id);
			}
		}	
		function openDetailTabDetail(permission){
			if(partnership.checkSingleData()){
				var id = partnership.seItem.PARTNERSHIP_ID;
				 createAddProcessTab('partnership:detail',id);
			}
		}	
		
		var partnership = function (){
			return{
				init:function (){
		            $('#partnership-table').bootstrapTable({
		            	url: "/partnership_commission_manager/partnership_commission_list",
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
		                pageSize : 5, //默认每页条数
		                pageNumber : 1, //默认分页
		                pageList : [5, 10, 20, 50 ],//分页数
		                toolbar:"#partnership_toolbar",
		                showColumns : false, //显示隐藏列
		                uniqueId: "PARTNERSHIP_ID", //每一行的唯一标识，一般为主键列
		                queryParamsType:'',
		                queryParams: partnership.queryParams,//传递参数（*）
		                columns : [{
		                    checkbox: true
		            	},{
	                		//field: 'Number',//可不加
	 						title: '序号',//标题  可不加
	 						switchable:false,
	  			            formatter:function(value,row,index){
	  			                //return index+1; //序号正序排序从1开始
	  			                var pageSize=$('#partnership-table').bootstrapTable('getOptions').pageSize;//通过表的#id 可以得到每页多少条
	  			                var pageNumber=$('#partnership-table').bootstrapTable('getOptions').pageNumber;//通过表的#id 可以得到当前第几页
	  			                return pageSize * (pageNumber - 1) + index + 1;    //返回每条的序号： 每页条数 * （当前页 - 1 ）+ 序号
	  			            }
	 	                } ,{
		                    field : "PARTNERSHIP_ID",
		                    title : "编号",
		                    align : "center",
		                    valign : "middle",
		                    sortable : "true",
		                    visible:false
		                } ,{
		                    field : "ENTERPRIS_EORGANIZATION_NAME",
		                    title : "企业内部组织机构名称",
		                    align : "center",
		                    valign : "middle",
		                    sortable : "true"
		                },{
		                    field : "INSURANCE_COMPANY_NAME",
		                    title : "公司名称",
		                    align : "center",
		                    valign : "middle",
		                    sortable : "true"
		                },{
		                    field : "COMPUTATIONAL_TERM",
		                    title : "计算项",
		                    align : "center",
		                    valign : "middle",
		                    sortable : "true"
		                },{
		                    field : "PRODUCT_NAME",
		                    title : "父产品名称",
		                    align : "center",
		                    valign : "middle",
		                    sortable : "true"
		                },{
		                    field : "PRODUCT_CODE",
		                    title : "父产品代码",
		                    align : "center",
		                    valign : "middle",
		                    sortable : "true"
		                }/* ,{
		                    field : "COUNTING_RULES",
		                    title : "计算规则",
		                    align : "center",
		                    valign : "middle",
		                    sortable : "true"
		                } */,{
		                    field : "CREATED_TIME",
		                    title : "创建日期",
		                    align : "center",
		                    valign : "middle",
		                    sortable : "true",
							formatter:function(value,row,index){
								var time = new Date(row.CREATED_TIME);
								var year = time.getFullYear();
								var month = (time.getMonth()+1<10)?'0'+(time.getMonth()+1):(time.getMonth()+1);
								var date = (time.getDate()+1<11)?'0'+time.getDate():time.getDate();
								var hours = (time.getHours()+1<11)?'0'+time.getHours():time.getHours();
								var minutes = (time.getMinutes()+1<11)?'0'+time.getMinutes():time.getMinutes();
								var seconds = (time.getSeconds()+1<11)?'0'+time.getSeconds():time.getSeconds();
								return year+'-'+month+'-'+date+' '+hours+':'+minutes+':'+seconds;
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
						enterprisEorganizationName: $("#enterprisEorganizationName").val(),
						insuranceCompanyName:$("#insuranceCompanyName").val(),
		                productNmae:$("#productNmae").val(),
		                productCode:$("#productCode").val(), 
		                computationalTerm:$("#computationalTerm").val(), 
		                
		                minCreateTime:$("#minCreateTime").val(), 
		                maxCreateTime:$("#maxCreateTime").val(),  
					};
					return temp;
				},
				
		        checkSingleData:function () {
		            var selected = $('#partnership-table').bootstrapTable('getSelections');
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
		            	partnership.seItem = selected[0];
		                return true;
		            }
		        },
		        
		        //搜索
		        searchProduct:function () {
		        	 $("#partnership-table").bootstrapTable('destroy');
		        	 partnership.init();
		        },
		        //清空
		        empty:function () {
		            $("#enterprisEorganizationName").val('');
		            $("#insuranceCompanyName").val('');
		            $("#productNmae").val(''); 
		            $("#productCode").val('');
		            $("#computationalTerm").val(''); 
		            $("#minCreateTime").val('');
		            $("#maxCreateTime").val('');
		            $("#partnership-table").bootstrapTable('refresh');
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
				<label for="enterprisEorganizationName"  class="control-label">销售机构</label>
	   		 	<input type="text" class="form-control" id="enterprisEorganizationName" name="enterprisEorganizationName" placeholder="请输入企业内部组织机构名称">
		    </div>

		    <div class="form-group">
				<label for="insuranceCompanyName"  class="control-label">保险公司</label>
		   		<input type="text" class="form-control" id="insuranceCompanyName" name="insuranceCompanyName" placeholder="请输入保险公司名称">
		    </div>

		    <div class="form-group">
				<label for="productNmae"  class="control-label">产品名称</label>
			   	<input type="text" class="form-control" id="productNmae" name="productNmae" placeholder="请输入产品名称">
		    </div>

		    <div class="form-group">
				<label for="productCode"  class="control-label">产品代码</label>
	   		 	<input type="text" class="form-control" id="productCode" name="productCode" placeholder="请输入产品代码">
		  	</div>

		    <div class="form-group">
				<label for="computationalTerm"  class="control-label">计算项</label>
	            <select class="form-control form-control-static" id="computationalTerm" name="computationalTerm">
	                <option value="">请选择</option>
	                <option value="0">C保护</option>
	                <option value="1">P手续费</option>
					<option value="2">基础佣金率</option>
	     		</select>
		    </div>

		    <div class="form-group">
				<label class="control-label" for="minCreateTime">创建时间</label>
	          	<input class="form-control input-sm Wdate" id="minCreateTime" name="minCreateTime" type="text" placeholder="请选择开始时间" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'minCreateTime\');}',dateFmt:'yyyy-MM-dd',readOnly:true})"/>
				<label class="control-label control-label-time">—</label>
	          	<input class="form-control input-sm Wdate" id="maxCreateTime" type="text" name="maxCreateTime" placeholder="请选择结束时间" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'maxCreateTime\');}',dateFmt:'yyyy-MM-dd',readOnly:true})"/>
			</div>

			<div class="form-group">
		  		<button type="button" onclick="partnership.searchProduct()" class="btn btn-info ">
		   			<span class="glyphicon glyphicon-search" aria-hidden="true" >搜索</span>
		   		</button>
		   		<button type="button" onclick="partnership.empty()" class="btn btn-danger ">
		   			<span class="glyphicon glyphicon-remove" aria-hidden="true" >清空</span>
		   		</button>
		   	</div>
		</form>
	</div>
</div>

<!--列表 -->
<div style="width: 99%;overflow: auto;">
	<table id="partnership-table" class="table table-hover table-striped table-condensed table-bordered"></table>
</div>
<!--toolbar  -->
<div id="partnership_toolbar" class="btn-toolbar">
	<%-- <shiro:hasPermission name="partnership:add"> --%>
    	<button class="btn btn-info" type="button" onclick="createAddProcessTab('partnership:add','')">
	    		<span class="glyphicon glyphicon-plus" >添加</span>
    	</button>
   <%--  </shiro:hasPermission>
    <shiro:hasPermission name="partnership:update"> --%>
    	<button type="button" class=" btn btn-info" onclick="openDetailTab('partnership:update')">
    			<span class="glyphicon glyphicon-pencil" >修改</span>
    	</button>

<%--   <shiro:hasPermission name="partnership:delete">   --%>
    	<button class=" btn btn-info" type="button" onclick="openDetailTabDetail('partnership:detail')"  >
    			<span class="glyphicon glyphicon-detail" >查看</span>
    	</button>
  <%--   </shiro:hasPermission> --%>
  <%--   <shiro:hasPermission name="partnership:export"> --%>
	    <!-- <button onclick="" type="button" class="btn btn-success">
	      		<span class="glyphicon glyphicon-paste" aria-hidden="true" >导出</span>
	    </button> -->
 	<%-- </shiro:hasPermission>    --%> 
</div> 
</body>
</html>