
/**
 *添加协议基本信息
 */
 var insuranceCompanyId = $("#insuranceCompanyId").val();
 var isLook = $("#isLook").val();
var AddLifeProtocolBasic = {
    seItem: null		//选中的条目
};

$(function (){
	AddLifeProtocolBasic.init();
	$('.close').click(function(){
    	AddLifeProtocolBasic.closeDlg();
	 });
    AddLifeProtocolBasic.formValidator();
    //获取一级保险公司
    getFirstInsuranceCompany();
    //加载一级保险公司下的机构
    getOrgCompany();
    //加载销售中介机构
    getSaleTree();
    
    //一级保险公司回显
    var select = document.getElementById("INSURANCE_COMPANY_ID"); 
    for (var i = 0; i < select.options.length; i++){  
        if (select.options[i].value == insuranceCompanyId){  
            select.options[i].selected = true;  
            break;  
        }  
    }  
	
});
var AddLifeProtocolBasic = function (){
    return{
    	  init:function (){
    		  //产品管理列表
              $('#product-manage-table').bootstrapTable({
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
                  toolbar:"#toolbar",
                  showColumns : false, //显示隐藏列
                  uniqueId: "PROTOCAL_PRODUCT_ID", //每一行的唯一标识，一般为主键列
                  queryParamsType:'',
                  queryParams: AddLifeProtocolBasic.queryProtocolBasicParams,//传递参数（*）
                  columns : [{
						title: '序列',
						width : '50',
						align : "center",
						valign : "middle",
			            switchable:false,
			            formatter:function(value,row,index){
			                var pageSize=$('#product-manage-table').bootstrapTable('getOptions').pageSize;//通过表的#id 可以得到每页多少条
			                var pageNumber=$('#product-manage-table').bootstrapTable('getOptions').pageNumber;//通过表的#id 可以得到当前第几页
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
                      sortable : "true",

                  },{
                      field : "hesitation_period",
                      title : "犹豫期",
                      align : "center",
                      valign : "middle",
                      sortable : "true"
                  },{
                      field: 'operate',
                      title: '操作',
                      width: '80px',
                      formatter: operateBasicFormatter
                  },]
              });
              
            //影像管理列表
              $('#protocol-image-table').bootstrapTable({
                  url: "lifeProtocol/getImageManageList",
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
                  uniqueId: "PROTOCOL_IMAGE_ID", //每一行的唯一标识，一般为主键列
                  queryParamsType:'',
                  queryParams: AddLifeProtocolBasic.imageQueryParams,//传递参数（*）
                  columns : [{
						title: '影像序列',
						width : '50',
						align : "center",
						valign : "middle",
			            switchable:false,
			            formatter:function(value,row,index){
			                var pageSize=$('#protocol-image-table').bootstrapTable('getOptions').pageSize;//通过表的#id 可以得到每页多少条
			                var pageNumber=$('#protocol-image-table').bootstrapTable('getOptions').pageNumber;//通过表的#id 可以得到当前第几页
			                return pageSize * (pageNumber - 1) + index + 1;    //返回每条的序号： 每页条数 * （当前页 - 1 ）+ 序号
			            }
		             },{
                      field : "IMAGE_NAME",
                      title : "影像名称",
                      align : "center",
                      valign : "middle",
                      sortable : "true",
                      formatter: function (value, row, index) {
                          var retStr = "";
                          retStr += '<a href="javascript:void(0);" onclick="downLoadImage(\''+row.FAST_URL+'\',\''+row.IMAGE_NAME+'\');" ><span style="color:blue;">'+row.IMAGE_NAME+'</span></a>';
                          return retStr;
                      }
                  },{
                      field: 'operate',
                      title: '操作',
                      width: '80px',
                      formatter: imageOperateFormatter
                  }],onLoadSuccess:function(data){
                      if(isLook =='yes'){
                    	  disBasic();
                  	}
                  },
              });
          },
    	//保存或者修改协议基本信息
        saveProtocolBasicInfo:function(){
        	if($("#addProtocolBasicInfoForm").data('bootstrapValidator').validate().isValid()){
        		var protocolId =  $("#new_protocol_id").val();
        		if(isEmpty(protocolId)){
	            	$.ajax({
	                    url:'lifeProtocol/addProtocolBasicInfo',
	                    type:'post',
	                    dataType:'json',
	                    data:$("#addProtocolBasicInfoForm").serialize(),
						beforeSend: function () {
							$("#saveProtocolBasicInfo_id").attr('disabled', 'disabled');
						},
					   complete: function () {
						   $("#saveProtocolBasicInfo_id").removeAttr('disabled');
					   },
	                    success:function(data){
	                   	 var result = data.result;
	                   	 if(result == '1'){
	                   		 $.alert({
	                                title: '提示信息！',
	                                content: '协议已存在!',
	                                type: 'red'
	                            }); 
	                   	 }else if(result == '2'){
	                   		 $.alert({
	                                title: '提示信息！',
	                                content: '添加成功!',
	                                type: 'blue'
	                            });
	                   		 //赋值最新id
	                   		 var protocolId = data.protocolId;
	                   		 $("#new_protocol_id").val(protocolId);
	                   		 $("#basic_look_protocol_Ids").val(protocolId);
	                   	 }else{
	                            $.alert({
	                                title: '提示信息！',
	                                content: '添加失败！',
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
            }else{
            	//修改
            	$.ajax({
                    url:'lifeProtocol/updateProtocolBasicInfo',
                    type:'post',
                    dataType:'json',
                    data:$("#addProtocolBasicInfoForm").serialize(),
                    success:function(data){
                   	 var result = data.result;
                   	 if(result == '1'){
                   		 $.alert({
                                title: '提示信息！',
                                content: '协议已存在!',
                                type: 'red'
                            }); 
                   	 }else if(result == '2'){
                   		 $.alert({
                                title: '提示信息！',
                                content: '修改成功!',
                                type: 'blue'
                            });
                   	 }else{
                            $.alert({
                                title: '提示信息！',
                                content: '修改失败！',
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
        },
        //点击新增产品按钮 弹出产品列表
        addProtocolAndProduct:function(){
        	//判断基本信息是否添加
			$("#check-product-table").bootstrapTable('destroy');
   		 	var protocolId = $("#new_protocol_id").val();
   		 	// alert($("#INSURANCE_ORG_ID").val());
   		 	if(!isEmpty(protocolId)){
   	        	if($("#addProtocolBasicInfoForm").data('bootstrapValidator').validate().isValid()){
		   		 	$('#check-product-table').bootstrapTable({
		                url: "lifeProtocol/getCheckProductList",
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
		                toolbar:"#checkProducrButton",
		                showColumns : false, //显示隐藏列
		                uniqueId: "PRODUCTS_RATIO_ID", //每一行的唯一标识，一般为主键列
		                queryParamsType:'',
		                queryParams: AddLifeProtocolBasic.queryProtocolBasicParams,//传递参数（*）
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
				                var pageSize=$('#check-product-table').bootstrapTable('getOptions').pageSize;//通过表的#id 可以得到每页多少条
				                var pageNumber=$('#check-product-table').bootstrapTable('getOptions').pageNumber;//通过表的#id 可以得到当前第几页
				                return pageSize * (pageNumber - 1) + index + 1;    //返回每条的序号： 每页条数 * （当前页 - 1 ）+ 序号
				            }
			             },{
		                    field : "P_PRODUCT_NAME",
		                    title : "父产品名称",
		                    align : "center",
		                    valign : "middle"
		                },{
		                    field : "S_PRODUCT_NAME",
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
		                },{
							field : "hesitation_period",
							title : "犹豫期",
							align : "center",
							valign : "middle",
							sortable : "true",
							formatter : changeToInputFormatter
                            }]
		            });
		        	$("#checkProductDlg").modal('show');
   	        	}
   		 	}else{
	   		 	$.alert({
	                title: '提示信息！',
	                content: '请先保存产品基本信息!',
	                type: 'red'
	            }); 
   		 	}
        },
        queryProtocolBasicParams:function(params){
            var temp = {
                pageSize: params.pageSize,  //页面大小
                pageNumber: params.pageNumber, //页码
                protocolId: $("#new_protocol_id").val(),
				insuranceOrgId:$("#INSURANCE_ORG_ID").val(),
				insuranceCompanyId:$("#INSURANCE_COMPANY_ID").val(),
            };
            return temp;
        },
        imageQueryParams:function(params){
            var temp = {
                    pageSize: params.pageSize,  //页面大小
                    pageNumber: params.pageNumber, //页码
                    protocolId: $("#new_protocol_id").val(),
                };
                return temp;
            },
        //取消选择产品
        cancelCheckProduct:function(){
        	$("#checkProductDlg").modal('hide');
            //$("#check-product-table").bootstrapTable('refresh');
        },
        //确定添加产品
        productBindingProtocol:function(){
         //判断是否有重复(0:通过校验；1:产品重复；2: 犹豫期输入错误)
        var flag = true;
        var productName;
        var rows = $("#check-product-table").bootstrapTable('getSelections');
 		 var productRows  = $('#product-manage-table').bootstrapTable('getData');
 		 for(var i=0;i<productRows.length;i++){
 			 if(flag){
 				 for(var j=0;j<rows.length;j++){
 					 if(productRows[i].PRODUCT_ID ==rows[j].PRODUCTS_RATIO_ID ){
 						productName  = productRows[i].S_PRODUCTS_NAME;
 	 					 flag = false;
 	 					 break;
 	 				 } 
 				 }

 	         }
         }
 		 if(flag){
 			var salesOrgId = $("#salesOrgId").val();
 			var protocolId = $("#new_protocol_id").val();
			var list = [];
			var regex = new RegExp('^[1-9]+[0-9]*$');
			for (var i = 0; i < rows.length; i++) {
				var productId = rows[i].PRODUCTS_RATIO_ID; //产品id

				var hesitation_period = $("#productId-"+productId).val();
				if(!regex.test(hesitation_period)) {
                    var errorProdName = rows[i].S_PRODUCT_NAME;
                        $.alert({
                        title : '提示信息！',
                        content : '子产品['+ errorProdName +']:请输入正整数类型的犹豫期！',
                        type : 'red'
                    });
					return ;
				}
				list[i] = rows[i].PRODUCTS_RATIO_ID + ":"
						+ rows[i].S_PRODUCTS_CODE + ":"
						+ hesitation_period;
			}
			$.ajax({
				url : 'lifeProtocol/productBindingProtocol',
				type : 'post',
				dataType : "json",
				data : {
					"list" : list,
					"protocolId" : protocolId,
					"salesOrgId":salesOrgId
				},
				success : function(data) {
					var result= data.result;
					var saleName = data.saleName;
					var productName = data.productName;
					if (result) {
						$("#checkProductDlg").modal('hide');
						$("#product-manage-table").bootstrapTable('refresh');
					} else {
						$.alert({
							title : '提示信息！',
							content :"["+ productName+"]"+' 在 '+"["+saleName+"]"+"中已存在！",
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
        },
        // 新增影像件 打开上传dialog
        openImageDlg:function(){
        	// 判断基本信息是否添加
   		 	var protocolId = $("#new_protocol_id").val();
   		 	if(!isEmpty(protocolId)){
   	        	if($("#addProtocolBasicInfoForm").data('bootstrapValidator').validate().isValid()){
   	        		$("#openImageDlg").modal('show');
   	        	}
   		 	}else{
	   		 	$.alert({
	                title: '提示信息！',
	                content: '请先添加产品基本信息!',
	                type: 'red'
	            }); 
   		 	}
        },
        //上传图片
        uploadImage:function(){
        	var multipart = $("#fileName").val();
   		 	var protocolId = $("#new_protocol_id").val();
  			if(multipart==""||multipart==null){
  				$.alert({
                    title: '提示信息！',
                    content: '请先选择文件！',
                    type: 'red'
                });
  				return ;
  			}
  			$("#imageForm").ajaxSubmit({
  	          type : 'POST',
  	          data:{"protocolId":protocolId},
  	          dataType:"json",
  	          url:'lifeProtocol/uploadImage',
  	          success : function(data) {
  	        	  if(data){
  	        		  $("#openImageDlg").modal('hide');
  	        		  $("#protocol-image-table").bootstrapTable('refresh');
  	        	  }else{
  	        		$.alert({
  	                    title: '提示信息！',
  	                    content: '上传失败！',
  	                    type: 'red'
  	                });
  	        	  }
  	          }
  	      });
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

function operateBasicFormatter(value, row, index) {
	var prorocolProductId = row.PROTOCOL_PRODUCT_ID;
	return [
	    	'<input onclick="deleteProduct('+prorocolProductId+')" style="background:#ed5565;  height: 30px;width: 80px;color: white; line-height:20px" type="button"  value="删除" />'
        ].join('');
}
function imageOperateFormatter(value, row, index) {
	var protocolImageId = row.PROTOCOL_IMAGE_ID;
	return [
	    	'<input onclick="deleteImage('+protocolImageId+')" style="background:#ed5565;  height: 30px;width: 80px;color: white; line-height:20px" type="button"  value="删除" />'
        ].join('');
}
function deleteProduct(prorocolProductId){
	$.confirm({
        title: '提示信息!',
        content: '您确定要删除吗？',
        type: 'blue',
        typeAnimated: true,
        buttons: {
            确定: {
                action: function(){
                	$.ajax({
                        url:'lifeProtocol/deleteProduct',
                        type:'post',
                        dataType:'json',
                        data:{"prorocolProductId":prorocolProductId},
                        success:function(data){
                       	 if(data){
                                $("#product-manage-table").bootstrapTable('refresh');
                       	 }else{
                                $.alert({
                                    title: '提示信息！',
                                    content: '删除失败！',
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
            },
            取消: function () {
            }
        }
    });
}
function deleteImage(imageId){
	$.confirm({
        title: '提示信息!',
        content: '您确定要删除吗？',
        type: 'blue',
        typeAnimated: true,
        buttons: {
            确定: {
                action: function(){
                	$.ajax({
                        url:'lifeProtocol/deleteImage',
                        type:'post',
                        dataType:'json',
                        data:{"imageId":imageId},
                        success:function(data){
                       	 if(data){
                                $("#protocol-image-table").bootstrapTable('refresh');
                       	 }else{
                                $.alert({
                                    title: '提示信息！',
                                    content: '删除失败！',
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
            },
            取消: function () {
            }
        }
    });
}

function downLoadImage(fastUrl,imageName){
	imageName = encodeURIComponent(imageName);
	location.href="lifeProtocol/downLoadImage?url="+fastUrl+"&imageName="+imageName;
}

function getFirstInsuranceCompany(){
	$.ajax({
    url:'lifeProtocol/findFirstInsuranceCompany',
    type:'post',
    async: false,
    success:function(data){
    	var h = "";  
        $.each(data, function(key, value) {
            h += "<option value='" + value.INSURANCE_COMPANY_ID + "'>" + value.INSURANCE_COMPANY_NAME //下拉框序言的循环数据
            + "</option>";  
        });  
        $("#INSURANCE_COMPANY_ID").append(h);//append 添加进去并展示  
        $("#INSURANCE_COMPANY_ID").on(  
            "change"
         )
    	},
	});
}

function changeCompany(){
	$("#protocol_org_name").val("");
	$("#INSURANCE_ORG_ID").val("");
	$('#addProtocolBasicInfoForm').data('bootstrapValidator').updateStatus('c_insuranceOrgId', 'VALIDATED').validateField('c_insuranceOrgId');
}

function getOrgCompany(){
	$("#protocol_org_name").click(function() {
    	var insuranceCompanyId = $("#INSURANCE_COMPANY_ID").val();
    	if(insuranceCompanyId != ''){
		$("#protocolParentCodetreeview").modal('show');
    		$.ajax({
  		        url:'lifeProtocol/getSonCompanyTree',
  		        dataType:'json',
  		        data:{"INSURANCE_COMPANY_ID":insuranceCompanyId},
  		        type:'post',
  		        success:function(data){
	        	$('#protocolParentCodetreeview').treeview({
                     color: "#428bca",
                     expandIcon: 'glyphicon glyphicon-chevron-right',
                     collapseIcon: 'glyphicon glyphicon-chevron-down',
                     nodeIcon: 'glyphicon glyphicon-bookmark',
                     data: data,
                     onNodeSelected: function(event, node) {
                		$("#protocol_org_name").val(node.text);
                		$("#INSURANCE_ORG_ID").val(node.id);
                		$('#protocolParentCodetreeview').modal('hide');
                		$('#addProtocolBasicInfoForm').data('bootstrapValidator').updateStatus('c_insuranceOrgId', 'NOT_VALIDATED').validateField('c_insuranceOrgId'); 
                     },
                   });
  		        }
  		      });
			}
  		});
}
function getSaleTree(){
    $("#c_salesOrgId").click(function() {
		$.ajax({
	        url:'lifeProtocol/getSaleTree',
	        dataType:'json',
	        type:'post',
	        data:{rid:1},
	        success:function(data){
	        	console.log(data.length)
	        	if (data.length <= 0) {
	        		alert("请先添加中介机构");
	        		return false;
	        	} else {
	        		$("#protocolParentIdtreeview").modal('show');
	        		$('#protocolParentIdtreeviewDiv').treeview({
	                     color: "#428bca",
	                     expandIcon: 'glyphicon glyphicon-chevron-right',
	                     collapseIcon: 'glyphicon glyphicon-chevron-down',
	                     nodeIcon: 'glyphicon glyphicon-bookmark',
	                     data: data,
	                     onNodeSelected: function(event, node) {
	                    		$("#c_salesOrgId").val(node.text);
	                    		$("#salesOrgId").val(node.id);
	                    		$('#protocolParentIdtreeview').modal('hide');
	                    		//手动验证隐藏的input组件
	                    		$('#addProtocolBasicInfoForm').data('bootstrapValidator').updateStatus('c_salesOrgId', 'NOT_VALIDATED').validateField('c_salesOrgId');  
	                     },
	        		});
	        	}
	        }
	 });
	});
}
/**
 *保存
 */
function addfirstProtocol() {
	 	if(checkNewProtocol()){
	 		 var productRows  = $('#product-manage-table').bootstrapTable('getData');
	 		 if(isEmpty(productRows)){
	 			 $.alert({
	 	             title: '提示信息！',
	 	             content: '请先新增产品!',
	 	             type: 'red'
	 	         }); 
	 			 return ;
	 		 }
	 		 var imageRows  = $('#protocol-image-table').bootstrapTable('getData');
	 		 if(isEmpty(imageRows)){
	 			 $.alert({
	 	             title: '提示信息！',
	 	             content: '请先新增影像!',
	 	             type: 'red'
	 	         }); 
	 			 return 
	 		 }
	 		if($("#addProtocolBasicInfoForm").data('bootstrapValidator').validate().isValid()){
	 			$.alert({
	                title: '提示信息！',
	                content: '保存成功!',
	                type: 'blue'
	            });
	 			commCloseTab('lifeProtocolBasic:add');
	            createAddProcessTab('lifeProtocol:list','');
	       	}
	 	}else{
   		 	$.alert({
                title: '提示信息！',
                content: '请先保存产品基本信息!',
                type: 'red'
            }); 
		}
}

//展开手续费二级菜单
function showSecondMenu(){
		if(checkNewProtocol()){
			if($('#showSecond').is(':hidden')){
				 document.getElementById("showSecond").style.display = "block";
			}else{
				 document.getElementById("showSecond").style.display = "none";
			}
	 	}else{
	 		$.alert({
                title: '提示信息！',
                content: '请新增协议基本信息!',
                type: 'red'
            }); 
	 	}
}

//1.打开协议信息
function openAddProtocolBasic(){
	//createAddProcessTab('lifeProtocolBasic:add',$('#basic_look_protocol_Ids').val());
}

//2.1打开手续费费
function openAddProtocolService(){
	if(checkNewProtocol()){
		commCloseTab('lifeProtocolBasic:add');
		createAddProcessTab('lifeProtocolService:add',$('#basic_look_protocol_Ids').val());
	}else{
   		 	$.alert({
                title: '提示信息！',
                content: '请先保存产品基本信息!',
                type: 'red'
            }); 
		}
}
//2.2 打开折标率配置
function openAddProtocolStandard(){
	if(checkNewProtocol()){
		commCloseTab('lifeProtocolBasic:add');
		createAddProcessTab('lifeProtocolStandard:add',$('#basic_look_protocol_Ids').val());
	}else{
		 	$.alert({
            title: '提示信息！',
            content: '请先保存产品基本信息!',
            type: 'red'
        }); 
	}
}
//2.3 打开内部折标率配置
function openAddProtocolInsideStandard(){
	if(checkNewProtocol()){
		commCloseTab('lifeProtocolBasic:add');
		createAddProcessTab('lifeProtocolInsideStandard:add',$('#basic_look_protocol_Ids').val());
	}else{
		 	$.alert({
            title: '提示信息！',
            content: '请先保存产品基本信息!',
            type: 'red'
        }); 
	}
}
//3.打开续年度服务津贴
function openAddProtocolSubsidy(){
	if(checkNewProtocol()){
		commCloseTab('lifeProtocolBasic:add');
		createAddProcessTab('lifeProtocolSubsidy:add',$('#basic_look_protocol_Ids').val());
	}else{
		 	$.alert({
            title: '提示信息！',
            content: '请先保存产品基本信息!',
            type: 'red'
        }); 
	}
	}


//5.打开费率调节
function openProtocolRateAdjust(){
	if(checkNewProtocol()){
		commCloseTab('lifeProtocolBasic:add');
		createAddProcessTab('lifeProtocolRateAdjust:list',$('#basic_look_protocol_Ids').val());
	}else{
		 	$.alert({
            title: '提示信息！',
            content: '请先保存产品基本信息!',
            type: 'red'
		 	}); 
	}
}

//4.打开继续率奖金
function openPersistencyBonus(){
	if(checkNewProtocol()){
		commCloseTab('lifeProtocolBasic:add');
		createAddProcessTab('persistencyBonus:edit',$('#basic_look_protocol_Ids').val());
	}else{
		 	$.alert({
            title: '提示信息！',
            content: '请先保存产品基本信息!',
            type: 'red'
        }); 
	}
}

//6.打开业务推动

function openProtocolPromotion(){
	if(checkNewProtocol()){
			commCloseTab('lifeProtocolBasic:add');
			createAddProcessTab('protocolPromotion:list',$('#basic_look_protocol_Ids').val());
		}else{
			 	$.alert({
	            title: '提示信息！',
	            content: '请先保存产品基本信息!',
	            type: 'red'
	        }); 
		}
	}
//获取最新添加的协议id
function checkNewProtocol(){
	var protocolId = $("#new_protocol_id").val();
 	if(isEmpty(protocolId)){
 		return false;
 	}
 	return true;
}
//返回列表
function returnProtocolList(){
 	commCloseTab('lifeProtocolBasic:add');
	createAddProcessTab('lifeProtocol:list','');
}

function disBasic() {
	var nodeList = document.getElementsByTagName("input");
	for (var i = 0; i < nodeList.length; i++) {
		nodeList[i].disabled = true;
	}
	nodeList = document.getElementsByTagName("select");
	for (var i = 0; i < nodeList.length; i++) {
		nodeList[i].disabled = true;
	}
}

function changeToInputFormatter(value, row, index) {
    var productId = row.PRODUCTS_RATIO_ID;
    return [
        '<input id="productId-'+ productId +'" class="hesitationPeriodCls" name ="hesitationPeriod" value="0"' +
		// 'data-prorocolProductId="'+ prorocolProductId +'" ' +
		// 'data-hesitationPeriod="'+ hesitationPeriod +'" value="'+ hesitationPeriod +'" ' +
		'style="height: 30px;width: 80px; line-height:20px" type="text"  />'
    ].join('');

}

// $("body").on("blur", "#product-manage-table .hesitationPeriodCls", function () {
//     var prorocolProductId = $(this).attr("data-prorocolProductId");
//
// 	var hesitationPeriod = $(this).val();
// 	var dataHesitationPeriod = $(this).attr("data-hesitationPeriod");
// 	if(hesitationPeriod == dataHesitationPeriod) {
// 		return;
// 	}
//     var reg = new RegExp('^[1-9][0-9]\d*$');
//     if(!reg.test(hesitationPeriod)) {
//         $(this).val(dataHesitationPeriod);
// 		alert("请输入正整数");
// 		return;
// 	}
//
//     $.ajax({
//         url:'lifeProtocol/updateProrocolProduct',
//         type:'post',
//         async : false,
//         dataType:'json',
//         data:{"prorocolProductId":prorocolProductId, "hesitationPeriod": hesitationPeriod},
//         success:function(data){
//             if(data && data.messageCode == '200'){
//                 $("#product-manage-table").bootstrapTable('refresh');
//             }else{
//                 $.alert({
//                     title: '提示信息！',
//                     content: '更新犹豫期失败！',
//                     type: 'red'
//                 });
//             }
//         },
//         error:function(){
//             $.alert({
//                 title: '提示信息！',
//                 content: '更新犹豫期失败！',
//                 type: 'red'
//             });
//         }
//     });
// });
