
/**
 *添加协议内部折标率
 */
var insideStandardProtocolId = $("#inside-standard-protocol-id").val();
var isLook = $("#isLook").val();
var AddLifeProtocolInsideStandard = {
    seItem: null		//选中的条目
};

$(function (){
	AddLifeProtocolInsideStandard.init();
	$('.close').click(function(){
		AddLifeProtocolInsideStandard.closeDlg();
	 });
});
var AddLifeProtocolInsideStandard = function (){
    return{
    	  init:function (){
              $('#inside-standard-protocol-table').bootstrapTable({
                  url: "lifeProtocol/getProtocolInsideStandardList",
                  method:"post",
                  dataType: "json",
                  contentType: "application/x-www-form-urlencoded",
                  striped:true,//隔行变色
                  cache:false,  //是否使用缓存
                  showColumns:false,// 列
                  toobar:'#toolbar',
                  pagination: true, //分页
                  sortable: false, //是否启用排序
                  singleSelect: false,
                  search:false, //显示搜索框
                  buttonsAlign: "right", //按钮对齐方式
                  showRefresh:false,//是否显示刷新按钮
                  sidePagination: "server", //服务端处理分页
                  pageSize : 5, //默认每页条数
                  pageNumber : 1, //默认分页
                  pageList : [ 5, 10, 20, 50],//分页数
                  toolbar:"#toolbar",
                  showColumns : false, //显示隐藏列
                  uniqueId: "PROTOCAL_PRODUCT_ID", //每一行的唯一标识，一般为主键列
                  queryParamsType:'',
                  queryParams: AddLifeProtocolInsideStandard.queryInsideStandardParams,//传递参数（*）
                  columns : [{
                      field : "PRODUCTS_NAME",
                      title : "子产品名称",
                      align : "center",
                      valign : "middle",
                      sortable : "true"
                  }, {
                      field : "COMPANY_INSURANCE_CODE",
                      title : "保司险种代码",
                      align : "center",
                      valign : "middle",
                      sortable : "true"
                  }, {
                      field : "PRODUCTS_CODE",
                      title : "子产品代码",
                      align : "center",
                      valign : "middle",
                      sortable : "true"
                  },  {
                      field : "MAIN_OR_ADDITIONAL",
                      title : "主附险标记",
                      align : "center",
                      valign : "middle",
                      sortable : "true"
                  }, {
                      field : "OUT_STANDARD_COMMISSION_COEFFICIENT",
                      title : "外部标保佣金系数",
                      align : "center",
                      valign : "middle",
                      sortable : "true"
                  }, {
                      field : "INSURANCE_PERIOD_MIN",
                      title : "保险起期（年）",
                      align : "center",
                      valign : "middle",
                      sortable : "true"
                  }, {
                      field : "INSURANCE_PERIOD_MAX",
                      title : "保险止期（年）",
                      align : "center",
                      valign : "middle",
                      sortable : "true"
                  }, {
                      field : "RENEW_PERIOD_MIN",
                      title : "缴费起期（年）",
                      align : "center",
                      valign : "middle",
                      sortable : "true"
                  }, {
                      field : "RENEW_PERIOD_MAX",
                      title : "缴费止期（年）",
                      align : "center",
                      valign : "middle",
                      sortable : "true"
                  }, {
                	  field: 'FIRST_STANDARD_RATE',
                	  title: '首期折标率（%）', 
                	  formatter: function (value, row , index) {
                		  return '<input   id="'+index+'FIRST_STANDARD_RATE_inside_standard" style="width:60px;"  type="number" step="0.01" min="0" value="'+row.FIRST_STANDARD_RATE+'" name="FIRST_STANDARD_RATE" />';
                	  }
                  },{
                	  field: 'NEXT_STANDARD_RATE',
                	  title: '续期折标率（%）',
                	  formatter: function (value, row , index) {
                		  return '<input id="'+index+'NEXT_STANDARD_RATE_inside_standard" style="width:60px;"  type="number" step="0.01" min="0" value="'+row.NEXT_STANDARD_RATE+'" name="NEXT_STANDARD_RATE"/>';
                	  }
                  }],
                  onLoadSuccess:function(data){
                      if(isLook =='yes'){
                    	  disProtocol();
                  	}
                  },
              });
          },
          queryInsideStandardParams:function(params){
              var temp = {
                  pageSize: params.pageSize,  //页面大小
                  pageNumber: params.pageNumber, //页码
                  protocolId: insideStandardProtocolId,
              };
              return temp;
          },
    }
}();
function addProtocolInsideStandard(){
	var flag = true; 
	var rows  = $('#inside-standard-protocol-table').bootstrapTable('getData');
	for (var i = 0; i < rows.length; i++) {
		 var first = $("#"+i+"FIRST_STANDARD_RATE_inside_standard").val();
		 var second = $("#"+i+"NEXT_STANDARD_RATE_inside_standard").val();
		 if(isEmpty(first) || isEmpty(second)){
			 $.alert({
		            title: '提示信息！',
		            content: '内部折标率不能为空！',
		            type: 'red'
		        });
			 flag = false;
			  	return;
			 }
		}
	if(flag){
		var rows = updateProtocolInsideStandardRows();
		$.ajax({
		    type: "POST",
		    url: "lifeProtocol/addProtocolInsideStandard",
		    dataType: "json",
		    traditional: true,
		    data:{
		        rows: JSON.stringify(rows),
		        protocolId:$("#inside-standard-protocol-id").val()
		    },
		    success: function (data) {
		        if(data){
	    		 	commCloseTab('lifeProtocolInsideStandard:add');
		 			createAddProcessTab('lifeProtocolBasic:add',$('#in_standard_look_protocolId').val());
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
}
function updateProtocolInsideStandardRows(){
	 var rows  = $('#inside-standard-protocol-table').bootstrapTable('getData');
	 for(var index=0;index <rows.length;index++){
		 var row = rows[index];
		 var first = $("#"+index+"FIRST_STANDARD_RATE_inside_standard").val();
		 var second = $("#"+index+"NEXT_STANDARD_RATE_inside_standard").val();
		 row["FIRST_STANDARD_RATE"]= first;
		 row["NEXT_STANDARD_RATE"]= second;
   }
	 //更新数据源
	$('#inside-standard-protocol-table').bootstrapTable('updateRow', {rows: rows})
	return rows;
};
//返回
function cancelInProtocolInStandard (){
 	commCloseTab('lifeProtocolInsideStandard:add');
	createAddProcessTab('lifeProtocolBasic:add',$('#in_standard_look_protocolId').val());
}
