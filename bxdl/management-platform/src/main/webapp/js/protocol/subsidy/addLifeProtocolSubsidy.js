
/**
 *添加协议续年度津贴
 */
var isLook = $("#isLook").val();
var AddLifeProtocolSubsidy = {
    seItem: null		//选中的条目
};

$(function (){
	AddLifeProtocolSubsidy.init();
	$('.close').click(function(){
		AddLifeProtocolSubsidy.closeDlg();
	 });
});


var AddLifeProtocolSubsidy = function (){
    return{
    	  init:function (){
    		  //例外产品管理列表
              $('#add-subsidy-exception-table').bootstrapTable({
                  url: "lifeProtocol/getAddSubsidyExceptionList",
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
                  uniqueId: "SUBSIDY_ID", //每一行的唯一标识，一般为主键列
                  queryParamsType:'',
                  queryParams: AddLifeProtocolSubsidy.queryProtocolSubsidyParams,//传递参数（*）
                  columns : [{
						title: '序列',
						width : '50',
						align : "center",
						valign : "middle",
			            switchable:false,
			            formatter:function(value,row,index){
			                var pageSize=$('#add-subsidy-exception-table').bootstrapTable('getOptions').pageSize;//通过表的#id 可以得到每页多少条
			                var pageNumber=$('#add-subsidy-exception-table').bootstrapTable('getOptions').pageNumber;//通过表的#id 可以得到当前第几页
			                return pageSize * (pageNumber - 1) + index + 1;    //返回每条的序号： 每页条数 * （当前页 - 1 ）+ 序号
			            }
		             },{
                      field : "P_PRODUCT_NAME",
                      title : "父产品名称",
                      align : "center",
                      valign : "middle",
                      sortable : "true"
                  }, {
                      field : "PRODUCTS_NAME",
                      title : "子产品名称",
                      align : "center",
                      valign : "middle",
                      sortable : "true"
                  }, {
                      field : "PRODUCTS_CODE",
                      title : "产品代码",
                      align : "center",
                      valign : "middle",
                      sortable : "true"
                  },  {
                      field : "COMPANY_INSURANCE_CODE",
                      title : "保司险种代码",
                      align : "center",
                      valign : "middle",
                      sortable : "true"
                  },  {
                      field : "MAIN_OR_ADDITIONAL",
                      title : "主附险标记",
                      align : "center",
                      valign : "middle",
                      sortable : "true"
                  },{
                      field : "OUT_STANDARD_COMMISSION_COEFFICIENT",
                      title : "外部标保佣金系数（%）",
                      align : "center",
                      valign : "middle",
                      sortable : "true"
                  },{
                      field: 'operate',
                      title: '操作',
                      width: '80px',
                      formatter: operateSubsidyFormatter
                  }],
                  onLoadSuccess:function(data){
                      if(isLook =='yes'){
                    	  disProtocol();
                  	}
                  },
              });updateSubsidyStatus
        },
        //点击添加例外产品按钮
        addProtocolSubsidyProduct:function(){
        	//校验参数
	    	var subsidyRateType = $('input[name="rateType"]:checked').val(); 
	    	 if(isEmpty(subsidyRateType)){
	    	  $.alert({
	               title: '提示信息！',
	               content: '请选择费率！',
	               type: 'red'
	           });
	    	  	return;
	    	 }
	    	 var subsidySettlementInterval = $('input[name="settlementInterval"]:checked').val(); 
	    	 if(isEmpty(subsidySettlementInterval)){
	    	  $.alert({
	    	            title: '提示信息！',
	    	            content: '请选择结算周期！',
	    	            type: 'red'
	    	        });
	    	  	return;
	    	 }
        	$('#check-subsidy-ep-table').bootstrapTable({
        		 url: "lifeProtocol/getProductManageList",
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
                 toolbar:"#checkSubsidyEpButton",
                 showColumns : false, //显示隐藏列
                 uniqueId: "PROTOCAL_PRODUCT_ID", //每一行的唯一标识，一般为主键列
                 queryParamsType:'',
                 queryParams: AddLifeProtocolSubsidy.queryProtocolSubsidyParams,//传递参数（*）
                 columns : [
                        {
	                	   checkbox: true
	                	},
		                {
						title: '序列',
						width : '50',
						align : "center",
						valign : "middle",
			            switchable:false,
			            formatter:function(value,row,index){
			                var pageSize=$('#check-subsidy-ep-table').bootstrapTable('getOptions').pageSize;//通过表的#id 可以得到每页多少条
			                var pageNumber=$('#check-subsidy-ep-table').bootstrapTable('getOptions').pageNumber;//通过表的#id 可以得到当前第几页
			                return pageSize * (pageNumber - 1) + index + 1;    //返回每条的序号： 每页条数 * （当前页 - 1 ）+ 序号
			            }
		             },{
                     field : "P_PRODUCT_NAME",
                     title : "父产品名称",
                     align : "center",
                     valign : "middle",
                     sortable : "true"
                 }, {
                     field : "S_PRODUCTS_NAME",
                     title : "子产品名称",
                     align : "center",
                     valign : "middle",
                     sortable : "true"
                 }, {
                     field : "S_PRODUCTS_CODE",
                     title : "子产品代码",
                     align : "center",
                     valign : "middle",
                     sortable : "true"
                 },  {
                     field : "COMPANY_INSURANCE_CODE",
                     title : "保司险种代码",
                     align : "center",
                     valign : "middle",
                     sortable : "true"
                 },  {
                     field : "MAIN_OR_ADDITIONAL",
                     title : "主附险标记",
                     align : "center",
                     valign : "middle",
                     sortable : "true"
                 }]
        	});
        	$("#checkSubsidyEpDlg").modal('show');
        },
        queryProtocolSubsidyParams:function(params){
            var temp = {
                pageSize: params.pageSize,  //页面大小
                pageNumber: params.pageNumber, //页码
                protocolId: $("#add-subsidy-protocol-id").val(),
            };
            return temp;
        },
        cancelCheckSubsidyEp:function(){
        	$("#checkSubsidyEpDlg").modal('hide');
            $("#check-subsidy-ep-table").bootstrapTable('refresh');
        },
        updateSubsidyStatus:function(){
        	//校验参数
	    	var subsidyRateType = $('input[name="rateType"]:checked').val(); 
	    	 var subsidySettlementInterval = $('input[name="settlementInterval"]:checked').val(); 
        	 var rows = $("#check-subsidy-ep-table").bootstrapTable('getSelections');
        	 var list = new Array();
        	 for(var i=0;i<rows.length;i++){
	           	 list[i] = rows[i].PROTOCOL_ID+":"+rows[i].PRODUCT_ID;
	         }
        	 
        	 $.ajax({
                 url:'lifeProtocol/updateSubsidyStatus',
                 type:'post',
                 dataType : "json", 
                 data:{
                	 "list": list,
                	 "rateType":subsidyRateType,
                	 "settlementInterval":subsidySettlementInterval},
                 success:function(data){
                	 if(data){
                     	 $("#checkSubsidyEpDlg").modal('hide');
                         $("#add-subsidy-exception-table").bootstrapTable('refresh');
                	 }else{
                         $.alert({
                             title: '提示信息！',
                             content: '例外产品新增失败！',
                             type: 'red'
                         });
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
}();
function addProtocolSubsidy(){
	//校验参数
	var subsidyRateType = $('input[name="rateType"]:checked').val(); 
	 if(isEmpty(subsidyRateType)){
	  $.alert({
           title: '提示信息！',
           content: '请选择费率！',
           type: 'red'
       });
	  	return;
	 }
	 var subsidySettlementInterval = $('input[name="settlementInterval"]:checked').val(); 
	 if(isEmpty(subsidySettlementInterval)){
	  $.alert({
	            title: '提示信息！',
	            content: '请选择结算周期！',
	            type: 'red'
	        });
	  	return;
	 }
	var tr = $("#protocol-subsidy-table tr"); 
	var result = []; 
	for (var i = 0; i < tr.length; i++) {
		var tds = $(tr[i]).find("td");
		if (tds.length > 0) {
			result.push({
				"code":$(tds[0]).find("input").val(),
				"2期" : $(tds[1]).find("input").val(),
				"3期" : $(tds[2]).find("input").val(),
				"4期" : $(tds[3]).find("input").val(),
				"5期" : $(tds[4]).find("input").val(),
				"6期" : $(tds[5]).find("input").val(),
				"7期" : $(tds[6]).find("input").val(),
				"8期" : $(tds[7]).find("input").val(),
				"9期" : $(tds[8]).find("input").val(),
				"10期" : $(tds[9]).find("input").val(),
				"11期" : $(tds[10]).find("input").val(),
				"12期" : $(tds[11]).find("input").val(),
				"13期" : $(tds[12]).find("input").val(),
				"14期" : $(tds[13]).find("input").val(),
				"15期" : $(tds[14]).find("input").val(),
				"16期" : $(tds[15]).find("input").val(),
				"17期" : $(tds[16]).find("input").val(),
				"18期" : $(tds[17]).find("input").val(),
				"19期" : $(tds[18]).find("input").val(),
				"20期" : $(tds[19]).find("input").val(),
				"21期" : $(tds[20]).find("input").val(),
				"22期" : $(tds[21]).find("input").val(),
				"23期" : $(tds[22]).find("input").val(),
				"24期" : $(tds[23]).find("input").val(),
				"25期" : $(tds[24]).find("input").val(),
				"26期" : $(tds[25]).find("input").val(),
				"27期" : $(tds[26]).find("input").val(),
				"28期" : $(tds[27]).find("input").val(),
				"29期" : $(tds[28]).find("input").val(),
				"30期及以上" : $(tds[29]).find("input").val()
			})
		}
	}
	
	$.ajax({
		type : "post",
	    url: "lifeProtocol/addProtocolSubsidy",
        dataType : "json", 
		data :{
				rateType:subsidyRateType,
			    settlementInterval:subsidySettlementInterval,
				datas: JSON.stringify(result),
				protocolId: $("#add-subsidy-protocol-id").val()
			},
			success : function(data) {
			if(data){
    		 	commCloseTab('lifeProtocolSubsidy:add');
	 			createAddProcessTab('lifeProtocolBasic:add',$("#subsidy_look_protocolId").val());
			}else{
				$.alert({
                    title: '提示信息！',
                    content: '保存失败！',
                    type: 'red'
                });
			}
		}
	})
}
function operateSubsidyFormatter(value, row, index) {
	var subsidyId = row.SUBSIDY_ID;
	var protocolId = row.PROTOCOL_ID;
	return [
            '<button style=" height: 30px;width: 80px;"class=" btn btn-primary" type="button" onclick="configSubsidyEp('+subsidyId+')" >配置</button>',
	    	'<input onclick="deleteSubsidyEp('+subsidyId+')" style="background:#ed5565;  height: 30px;width: 80px;color: white; line-height:20px" type="button"  value="删除" />'
        ].join('');
};

//删除例外产品
function deleteSubsidyEp(subsidyId){
	
	$.ajax({
		type : "post",
	    url: "lifeProtocol/deleteSubsidyEp",
        dataType : "json", 
		data :{
				subsidyId:subsidyId
			},
			success : function(data) {
			if(data){
                $("#add-subsidy-exception-table").bootstrapTable('refresh');
			}else{
				 $.alert({
	 	             title: '提示信息！',
	 	             content: '删除失败!',
	 	             type: 'red'
	 	         }); 
			}
		}
	})
};
//例外产品配置
function configSubsidyEp(subsidyId){
	var protocolId = $('#subsidy_look_protocolId').val();
	commCloseTab('lifeProtocolSubsidy:add');
	createAddProcessTab('liftProtocolSubsidy:configSubsidy',protocolId+":"+subsidyId);
}
//返回
function cancelProtocolSubsidy(){
	commCloseTab('lifeProtocolSubsidy:add');
	createAddProcessTab('lifeProtocolBasic:add',$('#subsidy_look_protocolId').val());
}