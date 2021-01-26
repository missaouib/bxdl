<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/core.jsp" %>
<!DOCTYPE  html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <title>佣金明细详情</title>
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Cache" content="no-cache">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   	<link rel="stylesheet" href="${path}/css/system/role/role.css">
   	<link rel="stylesheet" href="${path}/css/product/product.css">
    <script src="${path}/static/js/bootstrap.js"></script>
	<script type="text/javascript">	
    var SalesCommissionDetail = {
        seItem: null		//选中的条目
     };
   $(function () {
			SalesCommissionDetail.init();
		});


    var SalesCommissionDetail = function (){
	return{
		init:function (){
            $('#SalesCommissionDetail-table').bootstrapTable({
            	url: "salesDaysCommission/getSalesCommissionDetail",
            	method:"post",
                dataType: "json",
                contentType: "application/x-www-form-urlencoded",
                striped:true,//隔行变色
                cache:false,  //是否使用缓存
                pagination: true, //分页
                sortable: false, //是否启用排序
                singleSelect: false,
                search:false, //显示搜索框
                buttonsAlign: "right", //按钮对齐方式
                showRefresh:false,//是否显示刷新按钮
                sidePagination: "server", //服务端处理分页
                pageSize : 10, //默认每页条数
                pageNumber : 1, //默认分页
                pageList : [5, 10, 20, 50 ],//分页数
                toolbar:"#SalesCommissionDetail_toolbar",
                showColumns : false, //显示隐藏列
                uniqueId: "COMMISSION_ID", //每一行的唯一标识，一般为主键列
                queryParamsType:'',
                queryParams: SalesCommissionDetail.queryParams,//传递参数（*）
                columns : [{
                    field : "COMMISSION_ID",
                    title : "编号",
                    align : "center",
                    valign : "middle",
                    sortable : "true",
		            formatter:function(value,row,index){
		                var pageSize=$('#SalesCommissionDetail-table').bootstrapTable('getOptions').pageSize;//通过表的#id 可以得到每页多少条
		                var pageNumber=$('#SalesCommissionDetail-table').bootstrapTable('getOptions').pageNumber;
		                return pageSize * (pageNumber - 1) + index + 1;    //返回每条的序号： 每页条数 * （当前页 - 1 ）+ 序号
		            }
                },{
                    field : "INITIAL_ANNUAL_COMMISSION",
                    title : "初年度佣金(元)",
                    align : "center",
                    valign : "middle",
                    sortable : "true"
                },{
                    field : "EXHIBITION_ALLOWANCE",
                    title : "展业津贴(元)",
                    align : "center",
                    valign : "middle",
                    sortable : "true"
                },{
                    field : "INCREASE_ALLOWANCE",
                    title : "增员津贴(元)",
                    align : "center",
                    valign : "middle",
                    sortable : "true"
                },{
                    field : "GROUP_RUN_ALLOWANCE",
                    title : "直辖组管理津贴(元)",
                    align : "center",
                    valign : "middle",
                    sortable : "true"
                },{
                    field : "DIRECTOR_CULTURE_AWARD",
                    title : "处长育成奖(元)",
                    align : "center",
                    valign : "middle",
                    sortable : "true"
                },{
                    field : "DEP_RUN_ALLOWANCE",
                    title : "直辖部管理津贴(元)",
                    align : "center",
                    valign : "middle",
                    sortable : "true"
                },{
                    field : "MINISTER_CULTURE_AWARD",
                    title : "部育成奖(元)",
                    align : "center",
                    valign : "middle",
                    sortable : "true"
                },{
                    field : "TOTAL_COMMISSION",
                    title : "合计(元)",
                    align : "center",
                    valign : "middle",
                    sortable : "true"
                },{
                    field : "settlement_month",
                    title : "结算月",
                    align : "center",
                    valign : "middle",
                    sortable : "true",
					 formatter:function(value,row,index){
                    	var month = row.settlement_month
						 if (month != null){
                    	    var qq = month.replace('-','年')+'月';
						 }

                    	return qq;
                    }
                },{
                    field : "cut_commission",
                    title : "佣金加扣（元）",
                    align : "center",
                    valign : "middle",
                    sortable : "true"
                },{
                    field : "freezing_taxes",
                    title : "冻结税额(元)",
                    align : "center",
                    valign : "middle",
                    sortable : "true"
                },{
                    field : "issued_amount",
                    title : "发放金额（元）",
                    align : "center",
                    valign : "middle",
                    sortable : "true"
                },{
                    field : "settlement_status",
                    title : "是否发放",
                    align : "center",
                    valign : "middle",
                    sortable : "true",
					formatter: function (value, row, index) {
						if (value==0){
							value = "未发放"
						}  else if (value==1){
							value = "待发放"
						} else if (value==2){
							value = "已发放"
						}
						return value;
					}
                }
                ],
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
				salerId: $("#salerId").val()
			};
			return temp;
		},
	}
		}();
	</script>
</head>
<body>
       <!--列表 -->
<div style="width: 99%;overflow: auto;">
	<table id="SalesCommissionDetail-table" class="table table-hover table-striped table-condensed table-bordered"></table>
</div>
<div>
	<input type="hidden" class="form-control" id="salerId" name="salerId" value="${salerId}">

</div>
</body>
</html>