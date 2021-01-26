
/**
 *添加协议手续费
 */
var serviceProtocolId = $("#service-protocol-id").val();
var isLook = $("#isLook").val();
var AddLifeProtocolService = {
    seItem: null		//选中的条目
};

$(function (){
	AddLifeProtocolService.init();
	$('.close').click(function(){
		AddLifeProtocolService.closeDlg();
	 });
});
var AddLifeProtocolService = function (){
    return{
    	  init:function (){
    		  //手续费列表
              $('#service-protocol-table').bootstrapTable({
                  url: "lifeProtocol/getProtocolServiceList",
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
                  // pageSize : 5, //默认每页条数
                  pageSize : 100, //默认每页条数
                  pageNumber : 1, //默认分页
                  // pageList : [ 5, 10, 20, 50],//分页数
                  pageList : [100],//分页数
                  toolbar:"#toolbar",
                  showColumns : false, //显示隐藏列
                  uniqueId: "PROTOCAL_PRODUCT_ID", //每一行的唯一标识，一般为主键列
                  queryParamsType:'',
                  queryParams: AddLifeProtocolService.queryServiceParams,//传递参数（*）
                  columns : [{
                      field : "PRODUCT_NAME",
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
                  }, {
                      field : "MAIN_OR_ADDITIONAL",
                      title : "主附险标记",
                      align : "center",
                      valign : "middle",
                      sortable : "true"
                  }, {
                  //     field : "OUT_STANDARD_COMMISSION_COEFFICIENT",
                  //     title : "外部标保佣金系数",
                  //     align : "center",
                  //     valign : "middle",
                  //     sortable : "true"
                  // }, {
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
                	  field: 'FIRST_YEAR_COST',
                	  title: '第一年手续费（%）', 
                	  formatter: function (value, row , index) {
                		  return '<input id="'+index+'FIRST_YEAR_COST" style="width:60px;" value="'+row.FIRST_YEAR_COST+'"  type="number" step="0.01" min="0"  name="FIRST_YEAR_COST" onkeydown="return checkChargeRateKeydownVal(event)" onkeyup="correctChargeRateVal(this)"/>';
                	  }
                  },{
                	  field: 'SECOND_YEAR_COST',
                	  title: '第二年手续费（%）',
                	  formatter: function (value, row , index) {

                		  return '<input id="'+index+'SECOND_YEAR_COST" value="'+row.SECOND_YEAR_COST+'" style="width:60px;"  type="number" step="0.01" min="0" name="SECOND_YEAR_COST" onkeydown="return checkChargeRateKeydownVal(event)" onkeyup="correctChargeRateVal(this)"/>';
                	  }
                  }, {
                	  field: 'THIRD_YEAR_COST',
                	  title: '第三年手续费（%）',   
                	  formatter: function (value, row , index) {
                		  return '<input id="'+index+'THIRD_YEAR_COST"  value="'+row.THIRD_YEAR_COST+'"  style="width:60px;" value="" type="number" step="0.01" min="0" name="THIRD_YEAR_COST" onkeydown="return checkChargeRateKeydownVal(event)" onkeyup="correctChargeRateVal(this)"/>';
                	  }
                  }, {
                	  field: 'FOUR_YEAR_COST',
                	  title: '第四年手续费（%）',
                	  formatter: function (value, row , index) {
                		  return '<input id="'+index+'FOUR_YEAR_COST"  value="'+row.FOUR_YEAR_COST+'" style="width:60px;" value="" type="number" step="0.01" min="0" name="FOUR_YEAR_COST" onkeydown="return checkChargeRateKeydownVal(event)" onkeyup="correctChargeRateVal(this)"/>';
                	  }
                  }, {
                	  field: 'FIVE_YEAR_COST', 
                	  title: '第五年手续费（%）',
                	  formatter: function (value, row , index) {
                		  return '<input id="'+index+'FIVE_YEAR_COST"  value="'+row.FIVE_YEAR_COST+'" style="width:60px;" value="" type="number" step="0.01" min="0" name="FIVE_YEAR_COST" onkeydown="return checkChargeRateKeydownVal(event)" onkeyup="correctChargeRateVal(this)"/>';
                	  }
                  },{
                	  field: 'SIX_YEAR_COST',
                	  title: '第六年及以上（%）',   
                	  formatter: function (value, row , index) {
                		  return '<input id="'+index+'SIX_YEAR_COST" value="'+row.SIX_YEAR_COST+'" style="width:60px;" value="" type="number" step="0.01" min="0" name="SIX_YEAR_COST" onkeydown="return checkChargeRateKeydownVal(event)" onkeyup="correctChargeRateVal(this)"/>';
                	  }
                  }],
                  onLoadSuccess:function(data){
                      debugger
                	  var rows = data.rows;
                	  var rateType;
                	  var settlementInterval;

                	  if(rows != null && rows[0] != null) {
                          rateType = rows[0].RATE_TYPE;
                          settlementInterval = rows[0].SETTLEMENT_INTERVAL;
                      }

                      // $('input[name=rateType]').each(function () {
                      //     if ($(this).val() == rateType) {
                      //         $(this).attr('checked','true');
                      //     }
                      // })
                      // $('input[name=settlementInterval]').each(function () {
                      //     if ($(this).val() == settlementInterval) {
                      //         $(this).attr('checked','true');
                      //     }
                      // })

                      // 默认值处理逻辑
                      if(undefined != rateType) {
                          $('input[name=rateType]').each(function () {
                              if ($(this).val() == rateType) {
                                  $(this).attr('checked','true');
                              }
                          })
                      } else {
                          $('input[name=rateType]').eq(0).attr('checked','true');
                      }
                      if(undefined != settlementInterval) {
                          $('input[name=settlementInterval]').each(function () {
                              if ($(this).val() == settlementInterval) {
                                  $(this).attr('checked','true');
                              }
                          })
                      } else {
                          $('input[name=settlementInterval]').eq(3).attr('checked','true');
                      }

                      // “标保”选项置灰，待整体业务规则确认再开通
                      $('input[name=rateType]').eq(1).attr('disabled','disabled');

                      if(isLook =='yes'){
                    	  disProtocol();
                  	}
                  },
              });
          },
          queryServiceParams:function(params){
              var temp = {
                  pageSize: params.pageSize,  //页面大小
                  pageNumber: params.pageNumber, //页码
                  protocolId: serviceProtocolId,
              };
              return temp;
          },
          formValidator:function () {
              $("#addProtocolBasicInfoForm").bootstrapValidator({
                  fields:{
                  	protocolName:{
                          validators:{
                              notEmpty:{
                                  message:"协议名称不能为空"
                              }
                          }
                      },
                      protocolCode:{
                          validators:{
                              notEmpty:{
                                  message:"协议代码不能为空"
                              }
                          }
                      },
                  	c_salesOrgId:{
                          validators:{
                              notEmpty:{
                                  message:"中介机构不能为空"
                              }
                          }
                      },
                      insuranceCompanyId:{
                          validators:{
                              notEmpty:{
                                  message:"签约保险公司不能为空"
                              }
                          }
                      },
                      c_insuranceOrgId:{
                          validators:{
                              notEmpty:{
                                  message:"保险公司机构不能为空"
                              }
                          }
                      },
                     cSigningDate:{
                  	   trigger: 'focus',
                          validators:{
                              notEmpty:{
                                  message:"签订日期不能为空"
                              }
                          }
                      },
                      cEffectiveDate:{
                      	 trigger: 'focus',
                          validators:{
                              notEmpty:{
                                  message:"生效日期不能为空"
                              }
                          }
                      },
                      cTerminationDate:{
                      	trigger: 'focus',
                          validators:{
                              notEmpty:{
                                  message:"终止日期不能为空"
                              }
                          }
                      },
                  }
              });
          },
    }
}();

//保存手续费 回到主页面
function addProtocolService(){
    if(0 == $('input[type=number]').length) {
        $.alert({
            title: '提示信息！',
            content: '没有可以设置手续费率的产品！',
            type: 'red'
        });

        return;
    }

	var rateType = $('input[name="rateType"]:checked').val(); 
	 if(isEmpty(rateType)){
	  $.alert({
            title: '提示信息！',
            content: '请选择费率！',
            type: 'red'
        });
	  	return;
	 }
	 var settlementInterval = $('input[name="settlementInterval"]:checked').val(); 
	 if(isEmpty(settlementInterval)){
	  $.alert({
	            title: '提示信息！',
	            content: '请选择结算周期！',
	            type: 'red'
	        });
	  	return;
	 }

	var flag= true;
	$('input[type=number]').each(function(){
		// if(flag){
		// 	var value = $(this).val()
		// 	if(isEmpty(value)){
		// 		 $.alert({
         //             title: '提示信息！',
         //             content: '手续费率不能为空！',
         //             type: 'red'
         //         });
		// 		flag = false;
		// 	}
		// }

        var value = $(this).val()
        flag = checkChargeRateInputVal(value);
        if(!flag) {
            $(this).focus();
            return false;
        }
	})
	if(flag){
		var rows = updateServiceRows();
		$.ajax({
		    type: "POST",
		    url: "lifeProtocol/addProtocolService",
		    dataType: "json",
		    traditional: true,
		    data:{
		        rows: JSON.stringify(rows),
		        rateType:rateType,
		        settlementInterval:settlementInterval,
		        protocolId:$("#service_look_protocolId").val()
		    },
		    success: function (data) {
		        if(data){
		        	commCloseTab('lifeProtocolService:add');
		 			createAddProcessTab('lifeProtocolBasic:add',$('#service_look_protocolId').val());
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
	}
}

function updateServiceRows(){
	 var rows  = $('#service-protocol-table').bootstrapTable('getData');
	 for(var index=0;index <rows.length;index++){
		 var row = rows[index];
		 var first = $("#"+index+"FIRST_YEAR_COST").val();
		 var second = $("#"+index+"SECOND_YEAR_COST").val();
		 var third = $("#"+index+"THIRD_YEAR_COST").val();
		 var four = $("#"+index+"FOUR_YEAR_COST").val();
		 var five = $("#"+index+"FIVE_YEAR_COST").val();
		 var six = $("#"+index+"SIX_YEAR_COST").val();
		 row["FIRST_YEAR_COST"]= first;
		 row["SECOND_YEAR_COST"]= second;
		 row["THIRD_YEAR_COST"]= third;
		 row["FOUR_YEAR_COST"]= four;
		 row["FIVE_YEAR_COST"]= five;
		 row["SIX_YEAR_COST"]= six;
    }
	 //更新数据源
	$('#service-protocol-table').bootstrapTable('updateRow', {rows: rows})
	return rows;
}
//返回
function cancelProtocolService(){
	commCloseTab('lifeProtocolService:add');
	createAddProcessTab('lifeProtocolBasic:add',$('#service_look_protocolId').val());
}

//检查手续费率输入框的键盘输入
function checkChargeRateKeydownVal(e) {
    var kc = e.keyCode;
    //console.log(e.keyCode);
    if(
        (kc >= 48 && kc <= 57) // [0-9]
        //|| kc == 189  // - 负号
        || kc == 190  // . 小数bai点
        || kc == 8 // backspace
        || kc == 37 // 左箭头
        || kc == 39 // 右键头
    ){
        return true;
    }
    return false;
}

// 用户在手续费率输入框输入后，纠正输入的值
function correctChargeRateVal(obj) {
    // obj.value = obj.value.replace(/[^\d.]/g,""); //清除"数字"和"."以外的字符
    // obj.value = obj.value.replace(/^\./g,""); //验证第一个字符是数字
    // obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个, 清除多余的
    // obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");

    //只能输入两个小数
    var explorer = window.navigator.userAgent;
    if(explorer.indexOf("Firefox")>=0){
        if(obj.value.indexOf(".") > 0) {
            var bits = obj.value.split(".")[1].length;
            if(bits > 2) {
                var pos = obj.value.indexOf(".");
                obj.value = obj.value.substring(0, pos + 3);
            }
        }
    } else {
        obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3');
    }
}

function checkChargeRateInputVal(val) {
    if(isEmpty(val)){
        $.alert({
            title: '提示信息！',
            content: '手续费率不能为空！',
            type: 'red'
        });

        return false;
    }

    var a = val.indexOf(".")+1;
    if(a==0){
        a=val.length;
    }
    var b = val.length;
    var c = b-a;

    if(isNaN(val) || c > 2 || val < 0) {
        $.alert({
            title: '提示信息！',
            content: '请设置正确的手续费率！',
            type: 'red'
        });

        return false;
    }

    return true;
}