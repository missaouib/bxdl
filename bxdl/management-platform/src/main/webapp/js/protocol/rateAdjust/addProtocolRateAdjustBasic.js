/**
 * 新增费率调节基本信息
 */

var adjustProtocolId = $("#rate-adjust-protocol-id").val();//协议id
var isLook = $("#adjust_basic_isLook").val();
var AddLifeProtocolAdjust = {
    seItem: null		//选中的条目
};
$(function (){
	AddLifeProtocolAdjust.init();
	$('.close').click(function(){
		AddLifeProtocolAdjust.closeDlg();
	 });
	//回显附加费率基于
	var adjust =$("#hidden_adjust").html();
	var obj = eval('(' + adjust + ')');
	var nValue = document.getElementsByName("subjoinType");
	var sValue = obj.SUBJOIN_TYPE.split(",");
	for(var i=0;i<nValue.length;i++){
		for(var j=0;j<sValue.length;j++){
			if(nValue[i].value == sValue[j]){
				nValue[i].checked = true;
				break;
			}
		}
	}
	//根据选择项控制展示与隐藏
	var rateAdjustType = obj.RATE_ADJUST_TYPE;
	var productType = obj.PRODUCT_TYPE;
	// 单产品/全产品/混合产品 - 固定费率
	if (rateAdjustType == '0') {
		showTable("showAdjustChangeDetail", false);
		showTable("showAdjustFixedDetail", true);
	};
	//单产品/全产品 - 变动费率
	if (productType != 2 && rateAdjustType == '1') {
		showTable("showAdjustChangeDetail", true);
		showTable("showAdjustFixedDetail", false);
	};
	//混合产品 - 变动费率
	if (productType == 2 && rateAdjustType == '1') {
		showTable("showAdjustChangeDetail", false);
		showTable("showAdjustFixedDetail", false);
		showTable("showBlendAdjustChangeDetail", true);
	}
});
var AddLifeProtocolAdjust = function (){
    return{
    	  init:function (){
		  //固定费率产品管理列表
          $('#adjust-fixed-rate-table').bootstrapTable({
              url: "lifeProtocol/getEpAdjustList",
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
              uniqueId: "ADJUST_ID", //每一行的唯一标识，一般为主键列
              queryParamsType:'',
              queryParams: AddLifeProtocolAdjust.queryProtocolAdjustParams,//传递参数（*）
              columns : [{
					title: '序列',
					width : '50',
					align : "center",
					valign : "middle",
		            switchable:false,
		            formatter:function(value,row,index){
		                var pageSize=$('#adjust-fixed-rate-table').bootstrapTable('getOptions').pageSize;//通过表的#id 可以得到每页多少条
		                var pageNumber=$('#adjust-fixed-rate-table').bootstrapTable('getOptions').pageNumber;//通过表的#id 可以得到当前第几页
		                return pageSize * (pageNumber - 1) + index + 1;    //返回每条的序号： 每页条数 * （当前页 - 1 ）+ 序号
		            }
	             },{
                  field : "PRODUCT_NAME",
                  title : "父产品名称",
                  align : "center",
                  valign : "middle",
                  sortable : "true"
              }, {
                  field : "PRODUCT_CODE",
                  title : "父产品编码",
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
                  title : "子产品代码",
                  align : "center",
                  valign : "middle",
                  sortable : "true"
              },{
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
            	  field: 'CHANGE_RATE',
            	  title: '调节费率（%）', 
            	  formatter: function (value, row , index) {
            		  return '<input id="'+index+'CHANGE_RATE" style="width:60px;" value="'+row.CHANGE_RATE+'"  type="number" step="0.01" min="0"  name="CHANGE_RATE" />';
            	  }
              },],
              onLoadSuccess:function(data){
                  if(isLook =='yes'){
                	  disProtocol();
              	}
              }
          });
          
              
        //单产品 /全产品  - 变动费率产品管理列表
        $('#adjust-change-rate-table').bootstrapTable({
            url: "lifeProtocol/getEpAdjustList",
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
            uniqueId: "ADJUST_ID", //每一行的唯一标识，一般为主键列
            queryParamsType:'',
            queryParams: AddLifeProtocolAdjust.queryProtocolAdjustParams,//传递参数（*）
            columns : [{
					title: '序列',
					width : '50',
					align : "center",
					valign : "middle",
		            switchable:false,
		            formatter:function(value,row,index){
		                var pageSize=$('#adjust-change-rate-table').bootstrapTable('getOptions').pageSize;//通过表的#id 可以得到每页多少条
		                var pageNumber=$('#adjust-change-rate-table').bootstrapTable('getOptions').pageNumber;//通过表的#id 可以得到当前第几页
		                return pageSize * (pageNumber - 1) + index + 1;    //返回每条的序号： 每页条数 * （当前页 - 1 ）+ 序号
		            }
	             },{
                field : "PRODUCT_NAME",
                title : "父产品名称",
                align : "center",
                valign : "middle",
                sortable : "true"
            }, {
                field : "PRODUCT_CODE",
                title : "父产品编码",
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
                title : "子产品代码",
                align : "center",
                valign : "middle",
                sortable : "true"
            },{
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
                formatter: operateChangeRateFormatter
            },],
            onLoadSuccess:function(data){
                if(isLook =='yes'){
              	  disProtocol();
            	}
            }
        });
        
        //混合产品  - 变动费率产品管理列表
        $('#blend-adjust-change-rate-table').bootstrapTable({
            url: "lifeProtocol/getEpAdjustList",
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
            uniqueId: "ADJUST_ID", //每一行的唯一标识，一般为主键列
            queryParamsType:'',
            queryParams: AddLifeProtocolAdjust.queryProtocolAdjustParams,//传递参数（*）
            columns : [{
					title: '序列',
					width : '50',
					align : "center",
					valign : "middle",
		            switchable:false,
		            formatter:function(value,row,index){
		                var pageSize=$('#adjust-change-rate-table').bootstrapTable('getOptions').pageSize;//通过表的#id 可以得到每页多少条
		                var pageNumber=$('#adjust-change-rate-table').bootstrapTable('getOptions').pageNumber;//通过表的#id 可以得到当前第几页
		                return pageSize * (pageNumber - 1) + index + 1;    //返回每条的序号： 每页条数 * （当前页 - 1 ）+ 序号
		            }
	             },{
                field : "PRODUCT_NAME",
                title : "父产品名称",
                align : "center",
                valign : "middle",
                sortable : "true"
            }, {
                field : "PRODUCT_CODE",
                title : "父产品编码",
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
                title : "子产品代码",
                align : "center",
                valign : "middle",
                sortable : "true"
            },{
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
                formatter: operateBlendChangeRate
            }],
            onLoadSuccess:function(data){
                if(isLook =='yes'){
              	  disProtocol();
            	}
            }
        	});
    	  },
        queryProtocolAdjustParams:function(params){
            var temp = {
                pageSize: params.pageSize,  //页面大小
                pageNumber: params.pageNumber, //页码
                protocolId: $("#rate-adjust-protocol-id").val(),
                adjustId: $("#add-adjust-id").val(),
            };
            return temp;
        },
    }
}();

//单产品/全产品  变动费率按钮
function operateChangeRateFormatter(value, row, index) {
	var adjustParamId = row.ADJUST_PARAM_ID;
	return [
/*            '<button onclick="lookChangeRate('+adjustParamId+')"  style="background:#c2c2c2;  height: 30px;width: 80px;color: white; line-height:20px"  type="button">查看</button>',
*/	    	'<input onclick="editChangeRate('+adjustParamId+')" style="background:#c2c2c2;  height: 30px;width: 80px;color: white; line-height:20px" type="button"  value="编辑" />',
	    	'<input onclick="deleteChangeRate('+adjustParamId+')" style="background:#ed5565;  height: 30px;width: 80px;color: white; line-height:20px" type="button"  value="删除" />'
        ].join('');
};
//混合产品  变动费率按钮
function operateBlendChangeRate(value, row, index) {
	var adjustParamId = row.ADJUST_PARAM_ID;
	return [
/*            '<button onclick="lookBlendChangeRate('+adjustParamId+')" style="background:#c2c2c2;  height: 30px;width: 80px;color: white; line-height:20px"  type="button"  >查看</button>',
*/	    	'<input onclick="editBlendChangeRate('+adjustParamId+')" style="background:#c2c2c2;  height: 30px;width: 80px;color: white; line-height:20px" type="button"  value="编辑" />',
	    	'<input onclick="deleteChangeRate('+adjustParamId+')" style="background:#ed5565;  height: 30px;width: 80px;color: white; line-height:20px" type="button"  value="删除" />'
        ].join('');
};

//删除费率调节产品
function deleteChangeRate(adjustParamId){
	$.confirm({
        title: '提示信息!',
        content: '您确定要删除吗？',
        type: 'blue',
        typeAnimated: true,
        buttons: {
            确定: {
                action: function(){
                	$.ajax({
                		type : "post",
                		url : "lifeProtocol/deleteChangeRate",
                		dataType : "json",
                		data : {
                			"adjustParamId" : adjustParamId,
                		},
                		success : function(data) {
                			if (data) {
                				$("#adjust-fixed-rate-table").bootstrapTable('refresh');
                				$("#adjust-change-rate-table").bootstrapTable('refresh');
                				$("#blend-adjust-change-rate-table").bootstrapTable('refresh');
                			} else {
                				$.alert({
                					title : '提示信息！',
                					content : '保存失败！',
                					type : 'red'
                				});
                			}
                		}
                	})
                }
            },
            取消: function () {
            }
        }
    });
};
//编辑 单产品/全产品  变动费率
function editChangeRate(adjustParamId){
	createAddProcessTab('lifeProtocolSingleRateAdjust:add',adjustParamId);
};
//查看单产品/全产品  变动费率
function lookChangeRate(adjustParamId){
	createAddProcessTab('lifeProtocolSingleRateAdjust:add',"look"+":"+adjustParamId);
};
function editBlendChangeRate(adjustParamId){
	createAddProcessTab('lifeProtocolBlendRateAdjust:add',adjustParamId);
}
function lookBlendChangeRate(adjustParamId){
	createAddProcessTab('lifeProtocolBlendRateAdjust:add',"look"+":"+adjustParamId);
};
function returnAdjustRateBasic(){
	
}
/**
 * 保存费率调节基本信息
 */
function addProtocolRateAdjustBasic(){
	
	var addAdjustName =  $("#add-adjust-name").val();
	if (isEmpty(addAdjustName)) {
		$.alert({
			title : '提示信息！',
			content : '调节名称不能为空！',
			type : 'red'
		});
		return;
	}
	var rateType = $('input[name="rateType"]:checked').val();
	if (isEmpty(rateType)) {
		$.alert({
			title : '提示信息！',
			content : '请选择费率！',
			type : 'red'
		});
		clearAdjustRadio();
		return;
	}
	var subjoinType = $('input[name="subjoinType"]:checked').val();
	if (isEmpty(subjoinType)) {
		$.alert({
			title : '提示信息！',
			content : '请选择附加费率！',
			type : 'red'
		});
		clearAdjustRadio();
		return;
	}
	var settlementInterval = $('input[name="settlementInterval"]:checked').val();
	if (isEmpty(settlementInterval)) {
		$.alert({
			title : '提示信息！',
			content : '请选择结算周期！',
			type : 'red'
		});
		clearAdjustRadio();
		return;
	}
	var productType = $('input[name="productType"]:checked').val();
	if (isEmpty(productType)) {
		$.alert({
			title : '提示信息！',
			content : '请选择产品类型！',
			type : 'red'
		});
		clearAdjustRadio();
		return;
	}
	var rateAdjustType = $('input[name="rateAdjustType"]:checked').val();
	if (isEmpty(rateAdjustType)) {
		$.alert({
			title : '提示信息！',
			content : '请选择费率调节类型！',
			type : 'red'
		});
		return;
	}
		$.ajax({
	        url:'lifeProtocol/addProtocolRateAdjustBasic',
	        type:'post',
	        dataType:'json',
	        data:$("#addProtocolRateAdjust").serialize(),
	        success:function(data){
	       	 var result = data.result;
	       	 if(result){
	       		 var adjustId = data.adjustId;
	       		 $("#add-adjust-id").val(adjustId);
	       	 }else{
	                $.alert({
	                    title: '提示信息！',
	                    content: '保存失败！',
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
	    // 单产品/全产品/混合产品 - 固定费率
		if (rateAdjustType == '0') {
			showTable("showAdjustChangeDetail", false);
			showTable("showAdjustFixedDetail", true);
		};
		// 单产品/全产品 - 变动费率
		if (productType != 2 && rateAdjustType == '1') {
			showTable("showAdjustChangeDetail", true);
			showTable("showAdjustFixedDetail", false);
		};
		// 混合产品 - 变动费率
		if (productType == 2 && rateAdjustType == '1') {
			showTable("showAdjustChangeDetail", false);
			showTable("showAdjustFixedDetail", false);
			showTable("showBlendAdjustChangeDetail", true);
		}
	
};
//返回到费率调节列表
function returnAdjustRateBasic(){
	commCloseTab('lifeProtocolAdjustBasic:add');
	createAddProcessTab('lifeProtocolRateAdjust:list',$('#rate-adjust-protocol-id').val());
}
//选择例外产品
function checkAdjustProduct(){
	//判断基本信息是否添加
	var adjustId = $("#add-adjust-id").val();
	if(!isEmpty(adjustId)){
	 	$('#adjust-check-product-table').bootstrapTable({
	        url: "lifeProtocol/getProductManageList",
	        method:"post",
	        dataType: "json",
	        contentType: "application/x-www-form-urlencoded",
	        striped:true,//隔行变色
	        cache:false,  //是否使用缓存
	        showColumns:false,// 列
	        pagination: true, //分页
	        sortable: false, //是否启用排序
	        singleSelect: false,
	        search:false, //显示搜索框
	        buttonsAlign: "right", //按钮对齐方式
	        showRefresh:false,//是否显示刷新按钮
	        sidePagination: "server", //服务端处理分页
	        pageSize : 5, //默认每页条数
	        pageNumber : 1, //默认分页
	        pageList : [5, 10, 20, 50 ],//分页数
	        toolbar:"#checkAdjustProducrButton",
	        showColumns : false, //显示隐藏列
	        uniqueId: "PRODUCTS_RATIO_ID", //每一行的唯一标识，一般为主键列
	        queryParamsType:'',
	        queryParams: AddLifeProtocolAdjust.queryProtocolAdjustParams,//传递参数（*）
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
	                var pageSize=$('#adjust-check-product-table').bootstrapTable('getOptions').pageSize;//通过表的#id 可以得到每页多少条
	                var pageNumber=$('#adjust-check-product-table').bootstrapTable('getOptions').pageNumber;//通过表的#id 可以得到当前第几页
	                return pageSize * (pageNumber - 1) + index + 1;    //返回每条的序号： 每页条数 * （当前页 - 1 ）+ 序号
	            }
	         },{
	            field : "P_PRODUCT_NAME",
	            title : "父产品名称",
	            align : "center",
	            valign : "middle"
	        },{
	            field : "S_PRODUCTS_NAME",
	            title : "子产品名称",
	            align : "center",
	            valign : "middle"
	        },{
	            field : "S_PRODUCTS_CODE",
	            title : "子产品代码",
	            align : "center",
	            valign : "middle",
	            sortable : "true"
	        },{
	            field : "COMPANY_INSURANCE_CODE",
	            title : "保司险种代码",
	            align : "center",
	            valign : "middle",
	            sortable : "true"
	        },{
	            field : "MAIN_OR_ADDITIONAL",
	            title : "主附险标记",
	            align : "center",
	            valign : "middle",
	            sortable : "true"
	        }]
	    });
		$("#checkAdjustProductDlg").modal('show');
	}else{
		 $.alert({
            title: '提示信息！',
            content: '请先保存基本信息！',
            type: 'red'
        });
	}
}

//取消添加例外产品
function cancelAdjustEpProduct(){
	$("#checkAdjustProductDlg").modal('hide');
	$("#adjust-fixed-rate-table").bootstrapTable('refresh');
	$("#adjust-change-rate-table").bootstrapTable('refresh');
	$("#blend-adjust-change-rate-table").bootstrapTable('refresh');
}
//确定添加例外产品
function addAdjustEpProduct() {
	//判断是否有重复
    var fixedFlag = true;
    var changeFlag = true;
    var blendFlag = true;
    var productName;
    var rows = $("#adjust-check-product-table").bootstrapTable('getSelections');
	var adjustFixedRate = $('#adjust-fixed-rate-table').bootstrapTable('getData');//三款固定费率
	var adjustChangeRate = $('#adjust-change-rate-table').bootstrapTable('getData');//单产品/全产品 变动费率 
	var adjustBlendRate = $('#blend-adjust-change-rate-table').bootstrapTable('getData');//混合产品 变动费率 

	for (var i = 0; i < adjustFixedRate.length; i++) {
		if (fixedFlag) {
			for (var j = 0; j < rows.length; j++) {
				if (adjustFixedRate[i].S_PRODUCT_ID == rows[j].PRODUCTS_RATIO_ID) {
					productName = rows[j].S_PRODUCTS_NAME;
					fixedFlag = false;
					break;
				}
			}
		}
	}

	for (var i = 0; i < changeFlag.length; i++) {
		if (changeFlag) {
			for (var j = 0; j < rows.length; j++) {
				if (changeFlag[i].S_PRODUCT_ID == rows[j].PRODUCTS_RATIO_ID) {
					productName = rows[j].S_PRODUCTS_NAME;
					changeFlag = false;
					break;
				}
			}
		}
	}

	for (var i = 0; i < blendFlag.length; i++) {
		if (blendFlag) {
			for (var j = 0; j < rows.length; j++) {
				if (BlendFlag[i].S_PRODUCT_ID == rows[j].PRODUCTS_RATIO_ID) {
					productName =rows[j].S_PRODUCTS_NAME;
					blendFlag = false;
					break;
				}
			}
		}
	};
	if(fixedFlag && changeFlag && blendFlag){
		var protocolId = $("#rate-adjust-protocol-id").val();
		var adjustId = $("#add-adjust-id").val();
		var list = [];
		for (var i = 0; i < rows.length; i++) {
			list[i] = rows[i].P_PRODUCT_ID + ":" + rows[i].PRODUCTS_RATIO_ID;
		}
		$.ajax({
			url : 'lifeProtocol/addAdjustEpProduct',
			type : 'post',
			dataType : "json",
			data : {
				"list" : list,
				"adjustId":adjustId,
				"protocolId":protocolId
			},
			success : function(data) {
				var result = data.result;
				var productName = data.productName;
				if (result) {
					$("#checkAdjustProductDlg").modal('hide');
					$("#adjust-fixed-rate-table").bootstrapTable('refresh');
					$("#adjust-change-rate-table").bootstrapTable('refresh');
					$("#blend-adjust-change-rate-table").bootstrapTable('refresh');
				} else {
					$.alert({
						title : '提示信息！',
						content : productName+'不能重复！',
						type : 'red'
					});
				}
			},
			error : function() {
				$.alert({
					title : '提示信息！',
					content : '请求失败！',
					type : 'red'
				});
			}
		});
	}else{
 			$.alert({
				title : '提示信息！',
				content : productName+'不能重复！',
				type : 'red'
			});
 		 }
	
};
//更新table调节费率
function updateAdjustFixedRate() {
	var rows = updateAdjustRows();
	$.ajax({
		type : "POST",
		url : "lifeProtocol/updateAdjustFixedRate",
		dataType : "json",
		traditional : true,
		data : {
			rows : JSON.stringify(rows),
			protocolId : $("#rate-adjust-protocol-id").val()
		},
		success : function(data) {
			if (data) {
				commCloseTab('lifeProtocolAdjustBasic:add');
				createAddProcessTab('lifeProtocolRateAdjust:list',$('#look_rate_adjust_protocolId').val());
			} else {
				$.alert({
					title : '提示信息！',
					content : '保存失败！',
					type : 'red'
				});
			}
		},
		error : function() {
			$.alert({
				title : '提示信息！',
				content : '请求失败！',
				type : 'red'
			});
		}
	});
};
//单产品-全产品-混合产品 固定费率 返回按钮
function returnAdjustFixedRate(){
	commCloseTab('lifeProtocolAdjustBasic:add');
	createAddProcessTab('lifeProtocolRateAdjust:list',$('#look_rate_adjust_protocolId').val());
}
/**
 * 公共方法
 * 
 */
function updateAdjustRows() {
	var rows = $('#adjust-fixed-rate-table').bootstrapTable('getData');
	for (var index = 0; index < rows.length; index++) {
		var row = rows[index];
		var CHANGE_RATE = $("#" + index + "CHANGE_RATE").val();
		row["CHANGE_RATE"] = CHANGE_RATE;
	}
	// 更新数据源
	$('#adjust-fixed-rate-table').bootstrapTable('updateRow', {
		rows : rows
	})
	return rows;
}

// 清除费率调节选中状态
function clearAdjustRadio() {
	var x = document.getElementsByName("rateAdjustType");
	for (var i = 0; i < x.length; i++) {
		if (x[i].checked == true) {
			x[i].checked = false;
		}
	}
}
// 展示指定的table
function showTable(id, flag) {
	if (flag) {
		document.getElementById(id).style.display = "block";
	} else {
		document.getElementById(id).style.display = "none";
	}
};

function disBasic() {
	var nodeList = document.getElementsByTagName("input");
	for (var i = 0; i < nodeList.length; i++) {
		nodeList[i].disabled = true;
	}
}
