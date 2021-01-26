
//系统管理--角色管理的单例对象
var Role = {
    seItem: null		//选中的条目
};
var userName = $('#userEmployeeNo').val();
$(function(){
    if('superAdmin'!=userName){ // 特殊用户
        var ui = document.getElementById("userCondition");
        ui.style.display = "";
    }
	Role.formValidator();
    Role.init();
    $('.close').click(function(){
    	Role.closeDlg();
	 });
});

var Role = function () {
    return{
        init:function(){
            $('#role-table').bootstrapTable({
                url: "role/getRoleList",
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
                pageList : [5, 10, 20, 50 ],//分页数
                toolbar:"#toolbar",
                showColumns : false, //显示隐藏列
                uniqueId: "id", //每一行的唯一标识，一般为主键列
                queryParamsType:'',
                queryParams: Role.queryParams,//传递参数（*）
                columns : [{
                    checkbox: true
            	},{
                    field : "id",
                    title : "角色编号",
                    class : 'col-md-1',
                    align : "center",
                    valign : "middle",
                    sortable : "true"
                },{
                    field : "role_name",
                    title : "角色名称",
                    align : "center",
                    valign : "middle",
                    sortable : "true"
                }, {
                    field : "role_code",
                    title : "角色编码",
                    align : "center",
                    valign : "middle",
                    sortable : "true"
                },{
                    field : "remark",
                    title : "角色描述",
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
            var selected = $('#role-table').bootstrapTable('getSelections');
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
            	Role.seItem = selected[0];
                return true;
            }
        },
        //打开添加模态框
        openAddModal:function () {
            $("#roleAddDlg").modal('show');
        },
        //添加
        addRole:function () {
        	document.getElementById("saveButton").setAttribute("disabled", true);
            if($("#addRoleForm").data('bootstrapValidator').validate().isValid()){
            	flag = false;
    			//校验角色编码是否存在
    			var roleCode = $("#role_code").val();
    			$.ajax({
    				url:'role/checkRoleCode',
    				dataType:'json',
    				data:{roleCode:roleCode},
    				type:'post',
    				async:false, //同步 验证后再执行
    				success:function(data){
    					if(!data){
    						flag = false;
    						$.alert({
    					        title: '提示信息！',
    					        content: '角色编码已存在！',
    					        type: 'red'
    					    });
    					}else{
    						flag  = true;
    					}
    				}
    			})
    			if(flag){
	            	$.ajax({
	                    url:'role/saveRole',
	                    dataType:'json',
	                    type:'post',
	                    data:$("#addRoleForm").serialize(),
	                    success:function(data){
	                        if(data){
	                        	 $.alert({
	                                 title: '提示信息！',
	                                 content: '添加成功!',
	                                 type: 'blue'
	                             });
	                 		    document.getElementById("saveButton").removeAttribute("disabled");
	                            $("#role-table").bootstrapTable("refresh");
	                            Role.closeDlg();
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
        		var id = Role.seItem.id;
        		$.ajax({
                    url:'role/getRoleById',
                    dataType:'json',
                    type:'post',
                    data:{rid:id},
                    success:function(data){
                        $("#role_update_id").val(id);
                        $("#role_update_roleName").val(data.role.roleName);
                        $("#role_update_roleCode").val(data.role.roleCode);
                        $("#role_update_remark").val(data.role.remark);
                    },
                    error:function(){
                        $.alert({
                            title: '提示信息！',
                            content: '请求失败！',
                            type: 'red'
                        });
                    }
                });
                $("#roleMydlg").modal('show');
        	}
        },
        //修改角色
        updateRole:function () {
            if($("#updateRoleForm").data('bootstrapValidator').validate().isValid()){
            	flag = false;
    			//校验角色编码是否存在
    			var roleCode = $("#role_update_roleCode").val();
    			var id = $("#role_update_id").val();
    			$.ajax({
    				url:'role/checkRoleCode',
    				dataType:'json',
    				data:{roleCode:roleCode,id:id},
    				type:'post',
    				async:false, //同步 验证后再执行
    				success:function(data){
    					if(!data){
    						flag = false;
    						$.alert({
    					        title: '提示信息！',
    					        content: '角色编码已存在！',
    					        type: 'red'
    					    });
    					}else{
    						flag = true;
    					}
    				}
    			})
    			if(flag){
	            	$.ajax({
	                    url:'role/updateRole',
	                    dataType:'json',
	                    type:'post',
	                    data:$("#updateRoleForm").serialize(),
	                    success:function(data){
	                        if(data){
	                            $.alert({
	                                title: '提示信息！',
	                                content: '修改成功!',
	                                type: 'blue'
	                            });
	                            $("#role-table").bootstrapTable("refresh");
	                            Role.closeDlg();
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
        //删除角色
        deleteRole:function () {
        	if(this.checkSingleData()) {
        		var id = Role.seItem.id;
        		$.confirm({
                    title: '提示信息!',
                    content: '您确定要删除这个角色吗？',
                    type: 'blue',
                    typeAnimated: true,
                    buttons: {
                        确定: {
                            action: function(){
                                $.ajax({
                                    url:'role/deleteRole',
                                    dataType:'json',
                                    type:'post',
                                    data:{
                                        id:id
                                    },
                                    success:function(data){
                                        if(data){
                                            $.alert({
                                                title: '提示信息！',
                                                content: '删除成功!',
                                                type: 'blue'
                                            });
                                        }else{
                                            $.alert({
                                                title: '提示信息！',
                                                content: '删除失败!',
                                                type: 'red'
                                            });
                                        }
                                        $("#role-table").bootstrapTable('refresh');
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
            
        },
        //加载权限
        getAuth:function () {
        	if(this.checkSingleData()) {
        		var id = Role.seItem.id;
        		$.ajax({
   	             url:'role/viewTree',
   	             dataType:'json',
   	             type:'post',
   	             data:{rid:id},
   	             success:function(data){
   	            	 	$("#rid").val(id);
   	            	 	var $checkableTree = $('#treeview-checkable').treeview({
   	            		color: "#428bca",
   	            	    data: data,
   	            	    showIcon: true,
   	            	    showCheckbox: true,
   	            	    onNodeChecked: function(event, node) { //选中节点

   	            		  if(node.notCheckAllChildren == 0) {
                              var selectNodes = getChildNodeIdArr(node); //获取所有子节点
                              if (selectNodes) { //子节点不为空，则选中所有子节点
                                  $('#treeview-checkable').treeview('checkNode', [selectNodes, { silent: true }]);
                              }
                          }

                          if(node.notCheckAllChildren == 1) {
                              node.notCheckAllChildren = 0;
                          }

	   	                  var parentNode = $("#treeview-checkable").treeview("getNode", node.parentId);
	   	                  setParentNodeCheck(node);
   	            	    },
   	                 	onNodeUnchecked: function(event, node) { //取消选中节点
		   	                  var selectNodes = getChildNodeIdArr(node); //获取所有子节点
		   	                  if (selectNodes) { //子节点不为空，则取消选中所有子节点
		   	                   $('#treeview-checkable').treeview('uncheckNode', [selectNodes, { silent: true }]);
		   	                  }

		   	                  node.notCheckAllChildren=0;

		   	                  //获取兄弟节点，判断兄弟节点状态，如果兄弟节点都取消勾选了，就取消勾选父节点
                              var siblingNodes = $("#treeview-checkable").treeview("getSiblings", node);
		   	                  if(siblingNodes) {
                                  var siblingChecked = false;
                                  for(var i in siblingNodes) {
                                      if(siblingNodes[i].state.checked == true) {
                                          siblingChecked = true;
                                          break;
                                      }
                                  }

                                  if(siblingChecked == false) {
                                      var parentNode = $("#treeview-checkable").treeview("getNode", node.parentId);
                                      $('#treeview-checkable').treeview('uncheckNode', [parentNode, { silent: true }]);
                                  }
                              }
	   	                 },
   	            	  });

   	            	 	var treeNodes = $('#treeview-checkable').treeview('getEnabled');
   	            	 	if(treeNodes) {
   	            	 	    for(var i in treeNodes) {
   	            	 	        treeNodes[i].notCheckAllChildren=0;
                            }
                        }
   	             }
   	           });
                 $("#roleAuthDlg").modal('show');
        	}
        },
        //保存权限
        saveAuth:function () {
        	var id=$("#rid").val();
        	var ids=[];
        	var obj=$('#treeview-checkable').treeview('getChecked');
            $.each(obj,function(index,items){
                ids.push(items.id);
            });
            $.ajax({
                url:'role/updateRoleAuth',
                dataType:'json',
                type:'post',
                traditional:true,
                data:{
                    rid:id,
                    menuIds:ids
                },
                success:function(data){
                    if(data){
                        $.alert({
                            title: '提示信息！',
                            content: '保存成功！',
                            type: 'blue'
                        });
                    }else{
                        $.alert({
                            title: '提示信息！',
                            content: '保存失败！',
                            type: 'red'
                        });
                    }
                    $("#role-table").bootstrapTable("refresh");
                    Role.closeDlg();
                },
                error:function(){
                    $.alert({
                        title: '提示信息！',
                        content: '请求失败！',
                        type: 'red'
                    });
                }
            });
        },
        //关闭模态框
        closeDlg:function () {
            $("#roleAuthDlg").modal('hide');
            $("#roleAddDlg").modal('hide');
            $("#roleMydlg").modal('hide');
            $("input[type=reset]").trigger("click");
            $('#updateRoleForm').data('bootstrapValidator', null);
            $('#addRoleForm').data('bootstrapValidator', null);
		    document.getElementById("saveButton").removeAttribute("disabled");
            Role.formValidator();
            
        },
        //表单验证
        formValidator:function () {
            $("#addRoleForm").bootstrapValidator({
                fields:{
                    roleName:{
                        validators:{
                            notEmpty:{
                                message:"角色名称不能为空"
                            },
                            stringLength:{
                                max:20,
                                message:'不能超过20个字符长度'
                            },
                        }
                    },
                    roleCode:{
                        validators:{
                            notEmpty:{
                                message:'角色编码不能为空',
                            },
                            stringLength:{
                                max:200,
                                message:'字符长度不能超过20'
                            }
                        }
                    },
                    remark:{
                        validators:{
                            notEmpty:{
                                message:'角色描述不能为空',
                            },
                            stringLength:{
                                max:200,
                                message:'字符长度不能超过200'
                            }
                        }
                    }
                }
            });


            $("#updateRoleForm").bootstrapValidator({
                fields:{
                    roleName:{
                        validators:{
                            notEmpty:{
                                message:"角色名称不能为空"
                            },
                            stringLength:{
                                max:20,
                                message:'不能超过20个字符长度'
                            },
                        }
                    },
                    roleCode:{
                        validators:{
                            notEmpty:{
                                message:'角色编码不能为空',
                            },
                            stringLength:{
                                max:200,
                                message:'字符长度不能超过20'
                            }
                        }
                    },
                    remark:{
                        validators:{
                            notEmpty:{
                                message:'角色描述不能为空',
                            },
                            stringLength:{
                                max:200,
                                message:'字符长度不能超过200'
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
                         checkedCount++;
                     }
		    	     // } else {
		    	     //  break;
		    	     // }
	    	    }
    	    // if (checkedCount === parentNode.nodes.length) {
	    	 //     $("#treeview-checkable").treeview("checkNode", parentNode.nodeId);
	    	 //     setParentNodeCheck(parentNode);
    	    // 	}
               if (checkedCount > 0) {
                   parentNode.notCheckAllChildren=1;
                   $("#treeview-checkable").treeview("checkNode", parentNode.nodeId);
                   parentNode.notCheckAllChildren=0;
                   setParentNodeCheck(parentNode);
               }
    	   	}
    	  }
}();

