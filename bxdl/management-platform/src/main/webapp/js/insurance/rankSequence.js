//
var RankSequence = {
    seItem: null		//选中的条目
};
$(function(){
	RankSequence.formValidator();
	RankSequence.init();
    $('.close').click(function(){
    	RankSequence.closeDlg();
	 });
});

var RankSequence = function () {
    return{
        init:function(){
            $('#rankSequence-table').bootstrapTable({
                url: "rankSequence/getRankSequencePage",
                method:"post",
                dataType: "json",
                contentType: "application/x-www-form-urlencoded",
                striped:true,//隔行变色
                cache:false,  //是否使用缓存
                showColumns:false,// 列
                toobar:'#RankSequenceToolbar',
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
                toolbar:"#RankSequenceToolbar",
                showColumns : false, //显示隐藏列
                uniqueId: "SEQUENCE_ID", //每一行的唯一标识，一般为主键列
                queryParamsType:'',
                queryParams: RankSequence.queryParams,//传递参数（*）
                columns : [{
                    checkbox: true
            	},{
                    field : "SEQUENCE_ID",
                    title : "职级序列编号",
                    class : 'col-md-1',
                    align : "center",
                    valign : "middle",
                    sortable : "true"
                },{
                    field : "SEQUENCE_NAME",
                    title : "职级序列名称",
                    align : "center",
                    valign : "middle",
                    sortable : "true"
                }, {
                    field : "SEQUENCE_CODE",
                    title : "职级序列编码",
                    align : "center",
                    valign : "middle",
                    sortable : "true"
                },{
                    field : "REMARK",
                    title : "职级序列描述",
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
            pageNumber: params.pageNumber, //页码
        };
        return temp;
        },
        /**
         * 检查是否选中单条记录
         */
        checkSingleData:function () {
            var selected = $('#rankSequence-table').bootstrapTable('getSelections');
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
            	RankSequence.seItem = selected[0];
                return true;
            }
        },
        //打开添加模态框
        openAddModal:function () {
            $("#rankSequenceAddDlg").modal('show');
        },
        //添加
        addRankSequence:function () {
        	document.getElementById("saveButton").setAttribute("disabled", true);
            if($("#addForm").data('bootstrapValidator').validate().isValid()){
            	flag = false;
    			//校验编码是否存在
    			var sequenceCode = $("#sequenceCode").val();
    			$.ajax({
    				url:'rankSequence/getRankSequenceList',
    				dataType:'json',
    				data:{sequenceCode:sequenceCode},
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
	                    url:'rankSequence/addRankSequence',
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
	                            $("#rankSequence-table").bootstrapTable("refresh");
	                            RankSequence.closeDlg();
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
        		var id = RankSequence.seItem.SEQUENCE_ID;
        		$.ajax({
                    url:'rankSequence/selectById',
                    dataType:'json',
                    type:'post',
                    data:{id:id},
                    success:function(data){
                        $("#update_sequenceId").val(id);
                        $("#update_sequenceName").val(data.rankSequence.sequenceName);
                        $("#update_sequenceCode").val(data.rankSequence.sequenceCode);
                        $("#update_remark").val(data.rankSequence.remark);
                    },
                    error:function(){
                        $.alert({
                            title: '提示信息！',
                            content: '请求失败！',
                            type: 'red'
                        });
                    }
                });
                $("#rankSequenceMydlg").modal('show');
        	}
        },
        //修改角色
        updateRankSequence:function () {
            if($("#updateForm").data('bootstrapValidator').validate().isValid()){
            	flag = false;
    			//校验角色编码是否存在
    			var sequenceCode = $("#update_sequenceCode").val();
    			var checkSequenceId = $("#update_sequenceId").val();
    			$.ajax({
    				url:'rankSequence/getRankSequenceList',
    				dataType:'json',
    				data:{sequenceCode:sequenceCode,checkSequenceId:checkSequenceId},
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
	                    url:'rankSequence/updateRankSequence',
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
	                            $("#rankSequence-table").bootstrapTable("refresh");
	                            RankSequence.closeDlg();
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
            $("#rankSequenceAddDlg").modal('hide');
            $("#rankSequenceMydlg").modal('hide');
            $("input[type=reset]").trigger("click");
            $('#updateForm').data('bootstrapValidator', null);
            $('#addForm').data('bootstrapValidator', null);
		    document.getElementById("saveButton").removeAttribute("disabled");
            RankSequence.formValidator();           
        },
        //表单验证
        formValidator:function () {
            $("#addForm").bootstrapValidator({
                fields:{
                    sequenceName:{
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
                    sequenceCode:{
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
                	sequenceName:{
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
                    sequenceCode:{
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

