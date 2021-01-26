var SalesTeamLeaders = {
    seItem: null		//选中的条目
};
$(function(){
	getSysDict("cardType_dis","CARD");
	
	SalesTeamLeaders.formValidator();
	SalesTeamLeaders.init();
	
    $('.close').click(function(){
    	SalesTeamLeaders.closeDlg();
	});
    
	$('#salerNo').bind('input propertychange', function() {
		getSalers("salerName");
	});
	
    $("#salerName").on(  
        "change",function(){
        	$('#cardNo').val($(this).find("option:selected").attr("mycardNo"));
        	$('#salerNo').val($(this).find("option:selected").attr("mySalerNo"));
        	var cardType = $(this).find("option:selected").attr("mycardType");
        	$("#cardType").val(cardType);
        	setTimeout(function(){
    		$("#cardType_dis option").each(function(){
    			//alert($(this).val());
    	        if($(this).val()==cardType){
    	            $(this).attr("selected", true);
    	        }
    		});
        	},300);
        }
	) 	
});

function getSalers(selectId){
	 var salerNo = $("#salerNo").val();
	 $.ajax({
        type: "POST",
        url: "insuranceSalesInfo/insuranceSalesList",
        data:{likeSalerNo:salerNo},
        dataType: "json",
        success: function(data){
       	//alert(data.rows);
       	var salers = data.rows;
			var h = "<option value='' myvalue=''>请选择员工</option>";
           $.each(salers, function(key, value) {
               h += "<option value='" + value.insuranceSaler + "' mySalerNo='" + value.insuranceSalerNo + "' mycardType='"+ value.cardType +"' mycardNo='"+ value.cardNo +"'>" + value.insuranceSaler
               + "</option>";  
           });
           $("#"+selectId).empty();
           $("#"+selectId).append(h);
        }
    });
}

function disableLeaders(){
	var flag = SalesTeamLeaders.checkSingleData();
	if(flag){
		if($('#salesTeamLeaders-table').bootstrapTable('getSelections')[0].LEADER_STATUS != "2"){
			$.confirm({
		        title: '提示信息!',
		        content: '您确定要终止选中的对象吗？',
		        type: 'blue',
		        typeAnimated: true,
		        buttons: {
		        	确定: {
			        	action: function(){
							var teamLeaderIds = $('#salesTeamLeaders-table').bootstrapTable('getSelections');
							var leaderIds = "";
							for(var i=0;i<teamLeaderIds.length;i++){
								leaderIds = leaderIds + teamLeaderIds[i].TEAM_LEADER_ID + ",";
							}
							var salesTeamId = $("#salesTeamId").val();
							//alert(leaderIds);
							$.ajax({
						        type: "POST",
						        url: "salesTeamLeaders/disableLeaders",
						        data:{leaderIds:leaderIds,salesTeamId:salesTeamId},
						        dataType: "json",
						        success: function(data){
							       	 $.alert({
							             title: '提示信息！',
							             content: '终止成功！',
							             type: 'blue'
							         });
							         return true;        	
						        }
							});
			            }
		        	},
			        取消: function () {
			        	return true; 
			        }
		        }
			});	
		}else{
	       	 $.alert({
	             title: '提示信息！',
	             content: '该主管已是终止任命状态！',
	             type: 'blue'
	         });
	         return true;  			
		}
	}else{
         return true;		
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

var SalesTeamLeaders = function () {
    return{
        init:function(){
            $('#salesTeamLeaders-table').bootstrapTable({
                url: "salesTeamLeaders/getSalesTeamLeadersPage",
                method:"post",
                dataType: "json",
                contentType: "application/x-www-form-urlencoded",
                striped:true,//隔行变色
                cache:false,  //是否使用缓存
                showColumns:false,// 列
                toobar:'#salesTeamLeadersToolbar',
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
                toolbar:"#salesTeamLeadersToolbar",
                showColumns : false, //显示隐藏列
                uniqueId: "TEAM_LEADER_ID", //每一行的唯一标识，一般为主键列
                queryParamsType:'',
                queryParams: SalesTeamLeaders.queryParams,//传递参数（*）
                columns : [{
                    checkbox: true
            	},{
                    field : "TEAM_LEADER_ID",
                    title : "编号",
                    class : 'col-md-1',
                    align : "center",
                    valign : "middle",
                    sortable : "true",
		            formatter:function(value,row,index){
		                var pageSize=$('#salesTeamLeaders-table').bootstrapTable('getOptions').pageSize;//通过表的#id 可以得到每页多少条
		                var pageNumber=$('#salesTeamLeaders-table').bootstrapTable('getOptions').pageNumber;
		                return pageSize * (pageNumber - 1) + index + 1;    //返回每条的序号： 每页条数 * （当前页 - 1 ）+ 序号
		            }
                },{
                    field : "SALER_NO",
                    title : "主管工号",
                    align : "center",
                    valign : "middle",
                    sortable : "true"
                }, {
                    field : "SALER_NAME",
                    title : "主管姓名",
                    align : "center",
                    valign : "middle",
                    sortable : "true"
                },{
                    field : "LEADER_TYPE",
                    title : "领导类型",
                    align : "center",
                    valign : "middle",
                    sortable : "true",
                    formatter:function(value,row,index){ 
		            	var value="";
		            	if(row.LEADER_TYPE=="1"){
		            		value = "行政主管";
		            	}else if(row.LEADER_TYPE=="2"){
		            		value = "销售主管";
		            	}else{
		            		value = row.SALES_STATUS;
		            	}
		            	return value;
                    }
                },{
                    field : "CARD_TYPE",
                    title : "证件类型",
                    align : "center",
                    valign : "middle",
                    sortable : "true",
                    formatter:function(value,row,index){ 			            	
		            	return setValue("cardType_dis",row.CARD_TYPE);
                    }
                },{
                    field : "CARD_NO",
                    title : "证件编号",
                    align : "center",
                    valign : "middle",
                    sortable : "true"
                },{
                    field : "EFFECT_DATE",
                    title : "生效日期",
                    align : "center",
                    valign : "middle",
                    sortable : "true"
                },{
                    field : "STOP_DATE",
                    title : "失效日期",
                    align : "center",
                    valign : "middle",
                    sortable : "true"
                },{
                    field : "LEADER_STATUS",
                    title : "状态",
                    align : "center",
                    valign : "middle",
                    sortable : "true",
                    formatter:function(value,row,index){ 
		            	var value="";
		            	if(row.LEADER_STATUS=="0"){
		            		value = "正常";
		            	}else if(row.LEADER_STATUS=="1"){
		            		value = "失效";
		            	}else if(row.LEADER_STATUS=="2"){
		            		value = "终止";
		            	}else{
		            		value = row.SALES_STATUS;
		            	}
		            	return value;
                    }
                }],
                formatLoadingMessage : function() {
                    return "请稍等，正在加载中...";
                },
                formatNoMatches : function() {
                    return '无符合条件的记录';
                }
            });
        },
        //得到查询的参数
        queryParams:function (params) {
        var salesTeamId = $("#salesTeamId").val();
        //alert(salesTeamId);
        var temp = {
            pageSize: params.pageSize,  //页面大小
            pageNo: params.pageNumber, //页码
            salesTeamId: salesTeamId
        };
        return temp;
        },
        /**
         * 检查是否选中单条记录
         */
        checkSingleData:function () {
            var selected = $('#salesTeamLeaders-table').bootstrapTable('getSelections');
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
            	SalesTeamLeaders.seItem = selected[0];
                return true;
            }
        },       
        
        //打开添加模态框
        openAddModal:function () {
            $("#salesTeamLeadersAddDlg").modal('show');
        },
        //添加
        addSalesTeamLeaders:function () {
        	document.getElementById("saveButton").setAttribute("disabled", true);
            if($("#salesTeamLeaders_addForm").data('bootstrapValidator').validate().isValid()){
            	flag = true;
    			if(flag){
	            	$.ajax({
	                    url:'salesTeamLeaders/addSalesTeamLeaders',
	                    dataType:'json',
	                    type:'post',
	                    data:$("#salesTeamLeaders_addForm").serialize(),
	                    success:function(data){
	                        if(data.messageCode=="200"){
	                        	 $.alert({
	                                 title: '提示信息！',
	                                 content: '添加成功!',
	                                 type: 'blue'
	                             });
	                 		    document.getElementById("saveButton").removeAttribute("disabled");
	                            $("#salesTeamLeaders-table").bootstrapTable("refresh");
	                            SalesTeamLeaders.closeDlg();
	                        }else if(data.messageCode=="500"){
	                        	 $.alert({
	                                 title: '提示信息！',
	                                 content: data.requestState,
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
	            }
            }else{
    		    document.getElementById("saveButton").removeAttribute("disabled");
            }
        },       
      
        //关闭模态框
        closeDlg:function () {
            $("#salesTeamLeadersAddDlg").modal('hide');
            $("#salesTeamLeadersMydlg").modal('hide');
            $("input[type=reset]").trigger("click");
            $('#salesTeamLeaders_updateForm').data('bootstrapValidator', null);
            $('#salesTeamLeaders_addForm').data('bootstrapValidator', null);
		    document.getElementById("saveButton").removeAttribute("disabled");
		    SalesTeamLeaders.formValidator();           
        },
        //表单验证
        formValidator:function () {
            $("#salesTeamLeaders_addForm").bootstrapValidator({
                fields:{
                	salerNo:{
                        validators:{
                            notEmpty:{
                                message:"工号不能为空"
                            },
                            stringLength:{
                                max:32,
                                message:'不能超过32个字符长度'
                            },
                        }
                    },
                    leaderType:{
                        validators:{
                            notEmpty:{
                                message:"领导类型不能为空"
                            }
                        }
                    }
                }
            });
        }

    }
    
   function getChildNodeIdArr(node) {
	   var ts = [];
	   if (node.nodes) {
	    for (x in node.nodes) {
    	     ts.push(node.nodes[x].nodeId);
    	     if (node.nodes[x].nodes) {
    	      var getNodeDieDai = getChildNodeIdArr(node.nodes[x]);
    	      for (j in getNodeDieDai) {
    	       ts.push(getNodeDieDai[j]);
    	      }
	     }
	    }
	   }else {
		   ts.push(node.nodeId);
	   }
	   return ts;
   }
    	 
  function setParentNodeCheck(node) {
   var parentNode = $("#treeview-checkable").treeview("getNode", node.parentId);
   if (parentNode.nodes) {
    var checkedCount = 0;
	    for (x in parentNode.nodes) {
    	     if (parentNode.nodes[x].state.checked) {
    	      checkedCount ++;
    	     } else {
    	      break;
    	     }
	    }
    if (checkedCount === parentNode.nodes.length) {
	     $("#treeview-checkable").treeview("checkNode", parentNode.nodeId);
	     setParentNodeCheck(parentNode);
    	}
   	}
  }
}();

