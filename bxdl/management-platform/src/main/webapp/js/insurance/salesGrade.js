var SalesGrade = {
    seItem: null		//选中的条目
};
$(function(){
	SalesGrade.formValidator();
	SalesGrade.init();
    $('.close').click(function(){
    	SalesGrade.closeDlg();
	 });
    
	getRankSequenceList("rankSequenceId");
	
	$("#rankSequenceId").on(  
	    "change",function(){
	    	$('#rankSequenceName').val($(this).find("option:selected").text());
	    }
 	) 
 	 
	$("#update_rankSequenceId").on(  
	    "change",function(){
	    	$('#update_rankSequenceName').val($(this).find("option:selected").text());
	    }
 	) 	 
});

function getRankSequenceList(selectId){
	$.ajax({
        url:'rankSequence/getRankSequenceList',
        dataType:'json',
        type:'post',
        data:{},
        success:function(data){
        	var h = "<option value=''>请选择职级序列</option>";
        	if(data){
        		var rankSequences = data.rows;
	            $.each(rankSequences, function(key, value){	            	
	                h += "<option value='" + value.sequenceId +"'>" + value.sequenceName //下拉框序言的循环数据
	                + "</option>";  
	            });
	            $("#"+selectId).empty();
	            $("#"+selectId).append(h);	            
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

var SalesGrade = function () {
    return{
        init:function(){
            $('#salesGrade-table').bootstrapTable({
                url: "salesGrade/getSalesGradePage",
                method:"post",
                dataType: "json",
                contentType: "application/x-www-form-urlencoded",
                striped:true,//隔行变色
                cache:false,  //是否使用缓存
                showColumns:false,// 列
                toobar:'#SalesGradeToolbar',
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
                toolbar:"#SalesGradeToolbar",
                showColumns : false, //显示隐藏列
                uniqueId: "SALES_GRADE_ID", //每一行的唯一标识，一般为主键列
                queryParamsType:'',
                queryParams: SalesGrade.queryParams,//传递参数（*）
                columns : [{
                    checkbox: true
            	},{
                    field : "SALES_GRADE_ID",
                    title : "职级编号",
                    class : 'col-md-1',
                    align : "center",
                    valign : "middle",
                    sortable : "true",
		            formatter:function(value,row,index){
		                var pageSize=$('#salesGrade-table').bootstrapTable('getOptions').pageSize;//通过表的#id 可以得到每页多少条
		                var pageNumber=$('#salesGrade-table').bootstrapTable('getOptions').pageNumber;
		                return pageSize * (pageNumber - 1) + index + 1;    //返回每条的序号： 每页条数 * （当前页 - 1 ）+ 序号
		            }
                },{
                    field : "RANK_SEQUENCE_NAME",
                    title : "职级序列",
                    align : "center",
                    valign : "middle",
                    sortable : "true"
                },{
                    field : "SALES_GRADE_NAME",
                    title : "职级名称",
                    align : "center",
                    valign : "middle",
                    sortable : "true"
                }, {
                    field : "SALES_GRADE_CODE",
                    title : "职级编码",
                    align : "center",
                    valign : "middle",
                    sortable : "true"
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
        var temp = {
            pageSize: params.pageSize,  //页面大小
            pageNo: params.pageNumber, //页码
        };
        return temp;
        },
        /**
         * 检查是否选中单条记录
         */
        checkSingleData:function () {
            var selected = $('#salesGrade-table').bootstrapTable('getSelections');
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
            	SalesGrade.seItem = selected[0];
                return true;
            }
        },
        //打开添加模态框
        openAddModal:function () {
            $("#salesGradeAddDlg").modal('show');
        },
        //添加
        addSalesGrade:function () {
        	document.getElementById("saveButton").setAttribute("disabled", true);
            if($("#addForm").data('bootstrapValidator').validate().isValid()){
            	flag = false;
    			//校验角色编码是否存在
    			var salesGradeCode = $("#salesGradeCode").val();
    			$.ajax({
    				url:'salesGrade/getSalesGradeList',
    				dataType:'json',
    				data:{salesGradeCode:salesGradeCode},
    				type:'post',
    				async:false, //同步 验证后再执行
    				success:function(data){
    					if(data.rows.length>0){
    						flag = false;
    						$.alert({
    					        title: '提示信息！',
    					        content: '编码已存在！',
    					        type: 'red'
    					    });
    						document.getElementById("saveButton").removeAttribute("disabled");
    					}else{
    						flag  = true;
    					}
    				}
    			})
    			if(flag){
	            	$.ajax({
	                    url:'salesGrade/addSalesGrade',
	                    dataType:'json',
	                    type:'post',
	                    data:$("#addForm").serialize(),
	                    success:function(data){
	                        if(data){
	                        	 $.alert({
	                                 title: '提示信息！',
	                                 content: '添加成功!',
	                                 type: 'blue'
	                             });
	                 		    document.getElementById("saveButton").removeAttribute("disabled");
	                            $("#salesGrade-table").bootstrapTable("refresh");
	                            SalesGrade.closeDlg();
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
        //打开修改模态框
        openUpdateModal:function () {
        	if(this.checkSingleData()) {
        		getRankSequenceList("update_rankSequenceId");
        		var id = SalesGrade.seItem.SALES_GRADE_ID;
        		$.ajax({
                    url:'salesGrade/selectById',
                    dataType:'json',
                    type:'post',
                    data:{id:id},
                    success:function(data){
                        $("#update_salesGradeId").val(id);
                        $("#update_salesGradeName").val(data.salesGrade.salesGradeName);
                        $("#update_salesGradeCode").val(data.salesGrade.salesGradeCode);
                        $("#update_rankSequenceName").val(data.salesGrade.rankSequenceName);
                        setTimeout(function(){ 
        					$("#update_rankSequenceId option").each(function(){
        						//alert($(this).val());
        				        if($(this).val() == data.salesGrade.rankSequenceId){
        				            $(this).attr("selected", true);
        				        }
        					});
        				},100);	
                    },
                    error:function(){
                        $.alert({
                            title: '提示信息！',
                            content: '请求失败！',
                            type: 'red'
                        });
                    }
                });
                $("#salesGradeMydlg").modal('show');
        	}
        },
        //修改角色
        updateSalesGrade:function () {
            if($("#updateForm").data('bootstrapValidator').validate().isValid()){
            	flag = false;
    			//校验角色编码是否存在
    			var salesGradeCode = $("#update_salesGradeCode").val();
    			var salesGradeId = $("#update_salesGradeId").val();
    			$.ajax({
    				url:'salesGrade/getSalesGradeList',
    				dataType:'json',
    				data:{salesGradeCode:salesGradeCode,salesGradeId:salesGradeId},
    				type:'post',
    				async:false, //同步 验证后再执行
    				success:function(data){
    					if(data.rows.length>0){
    						flag = false;
    						$.alert({
    					        title: '提示信息！',
    					        content: '编码已存在！',
    					        type: 'red'
    					    });
    						document.getElementById("updateButton").removeAttribute("disabled");
    					}else{
    						flag = true;
    					}
    				}
    			})
    			if(flag){
	            	$.ajax({
	                    url:'salesGrade/updateSalesGrade',
	                    dataType:'json',
	                    type:'post',
	                    data:$("#updateForm").serialize(),
	                    success:function(data){
	                        if(data){
	                            $.alert({
	                                title: '提示信息！',
	                                content: '修改成功!',
	                                type: 'blue'
	                            });
	                            $("#salesGrade-table").bootstrapTable("refresh");
	                            SalesGrade.closeDlg();
	                        }else{
	                            $.alert({
	                                title: '提示信息！',
	                                content: '修改失败！',
	                                type: 'red'
	                            });
	                            document.getElementById("updateButton").removeAttribute("disabled");
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
      
        //关闭模态框
        closeDlg:function () {
            $("#salesGradeAddDlg").modal('hide');
            $("#salesGradeMydlg").modal('hide');
            $("input[type=reset]").trigger("click");
            $('#updateForm').data('bootstrapValidator', null);
            $('#addForm').data('bootstrapValidator', null);
		    document.getElementById("saveButton").removeAttribute("disabled");
		    SalesGrade.formValidator();           
        },
        //表单验证
        formValidator:function () {
            $("#addForm").bootstrapValidator({
                fields:{
                	salesGradeName:{
                        validators:{
                            notEmpty:{
                                message:"名称不能为空"
                            },
                            stringLength:{
                                max:20,
                                message:'不能超过20个字符长度'
                            },
                        }
                    },
                    salesGradeCode:{
                        validators:{
                            notEmpty:{
                                message:'编码不能为空',
                            },
                            stringLength:{
                                max:200,
                                message:'字符长度不能超过20'
                            }
                        }
                    }
                }
            });


            $("#updateForm").bootstrapValidator({
                fields:{
                	salesGradeName:{
                        validators:{
                            notEmpty:{
                                message:"名称不能为空"
                            },
                            stringLength:{
                                max:20,
                                message:'不能超过20个字符长度'
                            },
                        }
                    },
                    salesGradeCode:{
                        validators:{
                            notEmpty:{
                                message:'编码不能为空',
                            },
                            stringLength:{
                                max:200,
                                message:'字符长度不能超过20'
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

