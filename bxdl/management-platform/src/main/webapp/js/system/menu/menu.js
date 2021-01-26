//系统管理--菜单管理的单例对象
var Menu = {
    seItem: null		//选中的条目
};

$(function(){
    Menu.formValidator();
    Menu.init();
    $("#txt_parentMenuName").click(function() {
		$("#menu_parentIdtreeview").show();
		 $.ajax({
		        url:'menu/getManageMenuTree',
		        dataType:'json',
		        type:'post',
		        success:function(data){
		        	$('#menu_parentIdtreeview').treeview({
	                     color: "#428bca",
	                     expandIcon: 'glyphicon glyphicon-chevron-right',
	                     collapseIcon: 'glyphicon glyphicon-chevron-down',
	                     nodeIcon: 'glyphicon glyphicon-bookmark',
	                     data: data,
	                     onNodeSelected: function(event, node) {
	                    		$("#txt_parentMenuName").val(node.text);
	                    		$("#parentMenuId").val(node.id);
	        					$("#menu_parentIdtreeview").hide();
	                       },
	                   });
		        }
		      });
		});
	 $("#update_parentMenuName").click(function() {
			$("#update_parentMenuIdtreeview").show();
			 $.ajax({
			        url:'menu/getMenuTree',
			        dataType:'json',
			        type:'post',
			        data:{rid:1},
			        success:function(data){
			        	$('#update_parentMenuIdtreeview').treeview({
		                     color: "#428bca",
		                     expandIcon: 'glyphicon glyphicon-chevron-right',
		                     collapseIcon: 'glyphicon glyphicon-chevron-down',
		                     nodeIcon: 'glyphicon glyphicon-bookmark',
		                     data: data,
		                     onNodeSelected: function(event, node) {
		                    		$("#update_parentMenuName").val(node.text);
		                    		$("#update_parentMenuId").val(node.id);
		        					$("#update_parentMenuIdtreeview").hide();
		                       },
		                   });
			        }
			      });
			});

	 $('.close').click(function(){
		 Menu.closeDlg();
	 });

});
var Menu = function(){
    return{
        init:function(){
            $('#menu-table').bootstrapTable({
            	url: "menu/getMenuList",
            	dataType: "json",
            	idField: 'menu_id',
            	toolbar:"#toolbar",
            	sortable: false, //是否启用排序
                uniqueId: "menu_id", //每一行的唯一标识，一般为主键列
                queryParamsType:'',
                columns : [{
                    checkbox: true
            	}, {
                    field : "name_zh",
                    title : "菜单名称",
                    align : "center",
                    valign : "middle",
                    sortable : "true"
                }, {
                    field : "menu_url",
                    title : "请求地址",
                    align : "center",
                    valign : "middle",
                    sortable : "true"
                }, {
                    field : "menu_icon",
                    title : "图标样式",
                    align : "center",
                    valign : "middle",
                    sortable : "true"
                }, {
                    field : "menu_type",
                    title : "菜单类型",
                    align : "center",
                    valign : "middle",
                    sortable : "true"
                }, {
                    field : "permission",
                    title : "权限编码",
                    align : "center",
                    valign : "middle",
                    sortable : "true"
                }, {
                    field : "menu_status",
                    title : "状态",
                    align : "center",
                    valign : "middle",
                    sortable : "true"
                },{
                    field : "remark",
                    title : "菜单描述",
                    align : "center",
                    valign : "middle",
                    sortable : "true"
                }],
                //在哪一列展开树形
        		treeShowField : 'name_zh',
        		//指定父id列
        		parentIdField : 'parent_id',
        		onResetView : function(data) {
        			 $('#menu-table').treegrid({
        				initialState : 'collapsed',// 所有节点都折叠
        				// initialState: 'expanded',// 所有节点都展开，默认展开
        				treeColumn : 1,
        				onChange : function() {
        					$('#menu-table').bootstrapTable('resetWidth');
        				}
        			});

        			//只展开树形的第一级节点
        			// $('#menu-table').treegrid('getRootNodes').treegrid('expand');
        		},
        	// bootstrap-table-treetreegrid.js 插件配置 -- end
                formatLoadingMessage : function() {
                    return "请稍等，正在加载中...";
                },
                formatNoMatches : function() {
                    return '无符合条件的记录';
                }
            });
        },
        //得到查询的参数
       /* queryParams:function(params){
            var temp = {
                pageSize: params.pageSize,  //页面大小
                pageNumber: params.pageNumber, //页码
                nameZh: $("#menu_name").val(),
            };
            return temp;
        },*/
        
        /**
         * 检查是否选中单条记录
         */
        checkSingleData:function () {
            var selected = $('#menu-table').bootstrapTable('getSelections');
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
            	Menu.seItem = selected[0];
                return true;
            }
        },
        //删除
        delMenu:function () {
        	if(this.checkSingleData()) {
        		var menuId = Menu.seItem.menu_id;
        		 $.confirm({
                     title: '提示信息!',
                     content: '您确定要删除这个菜单吗？',
                     type: 'red',
                     typeAnimated: true,
                     buttons: {
                         确定: {
                             action: function(){
                                 $.ajax({
                                     url:'menu/deleteMenu',
                                     dataType:'json',
                                     type:'post',
                                     data:{
                                         menuId:menuId
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
                                         $("#menu-table").bootstrapTable('refresh');
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
        //添加
        saveMenu:function(){
        	document.getElementById("saveButton").setAttribute("disabled", true);
            if($("#addMenuForm").data('bootstrapValidator').validate().isValid()){
        				 $.ajax({
        					url:'menu/saveMenu',
        					dataType:'json',
        					type:'post',
        					data:$("#addMenuForm").serialize(),
        					success:function(data){
        						if(data){
        						    $.alert({
        						    	 title: '提示信息！',
        							     content: '添加成功!',
        							     type: 'blue'
        						    });
        						    document.getElementById("saveButton").removeAttribute("disabled");
        						    $("#menu-table").bootstrapTable('refresh');
        						    Menu.closeDlg();
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
            }else{
    		    document.getElementById("saveButton").removeAttribute("disabled");
            }
        },
        //打开  添加 加载pid为0的菜单
        addMenu:function () {
            $.ajax({
                url:'menu/getParentMenuList',
                dataType:'json',
                type:'post',
                success:function(data){
                    $("#parentMenuId").empty();
                    $("#parentMenuId").append("<option value='0'>请选择</option>");
                    $.each(data,function(index,items){
                        $("#parentMenuId").append("<option value="+items.menuId+">"+items.menuName+"</option>");
                    });
                },
                error:function(){
                    $.alert({
                        title: '提示信息！',
                        content: '请求失败！',
                        type: 'red'
                    });
                }
            });
            $("#menuAddDlg").modal('show');
        },
        //修改 回显
        getValue:function () {
        	if(this.checkSingleData()) {
        		var menuId = Menu.seItem.menu_id;
        		$.ajax({
                    url:'menu/getMenuById',
                    dataType:'json',
                    type:'post',
                    data:{
                        mid:menuId
                    },
                    success:function(data){
                        $("#update_menu_id").val(menuId);
                        $("#update_menu_name").val(data.menu.nameZh);
                        $("#update_menu_url").val(data.menu.menuUrl);
                        $("#update_menu_icon").val(data.menu.menuIcon);
                        $("#update_menu_type").val(data.menu.menuType);
                        $("#update_menu_permission").val(data.menu.permission);
                        $("#menu_update_remark").val(data.menu.remark);
                        $("#update_menu_sort").val(data.menu.menuSort);

                        if(data.menu.menuStatus=='2'){
                            $("#close").prop('checked',true);
                        }else{
                            $("#open").prop('checked',true);
                        }
                        //一级菜单
                        if(data.menu.parentId=='0'){
                            $("#yes").prop('checked',true);
                            document.getElementById("menuUpdateIsShow").style.display = "none";
                        }else{
                            $("#no").prop('checked',true);
                            document.getElementById("menuUpdateIsShow").style.display = "block";
                        }
                        $("#update_parentMenuName").val(data.menu.parentMenuName);
                        $("#update_parentMenuId").val(data.menu.parentId);
                        $("#menuUpdateDlg").modal('show');
                    },
                    error:function(){
                        alert("请求失败！");
                    }
                });
        	}
        },
        //修改
        updateMenu:function () {
            if($("#updateMenuForm").data('bootstrapValidator').validate().isValid()){
            	$.ajax({
                    url:'menu/updateMenu',
                    dataType:'json',
                    type:'post',
                    data:$("#updateMenuForm").serialize(),
                    success:function(data){
                        if(data == '1'){
                            $.alert({
                                title: '提示信息！',
                                content: '修改成功!',
                                type: 'blue'
                            });
                            $("#menu-table").bootstrapTable('refresh');
                            Menu.closeDlg();
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
                            content: '修改失败!',
                            type: 'red'
                        });
                    }
                });
            }
        },
        //关闭
        closeDlg:function () {
            $("#menuAddDlg").modal('hide');
            $("#menuUpdateDlg").modal('hide');
            $("input[type=reset]").trigger("click");
            $('#updateMenuForm').data('bootstrapValidator', null);
            $('#addMenuForm').data('bootstrapValidator', null);
		    document.getElementById("saveButton").removeAttribute("disabled");
            Menu.formValidator();
        },
        
        formValidator:function () {
            $("#addMenuForm").bootstrapValidator({
                fields:{
                    nameZh:{
                        validators:{
                            notEmpty:{
                                message:"菜单名称不能为空"
                            },
                            stringLength:{
                                max:20,
                                message:"字符长度不能超过20个字符"
                            }
                        }
                    },
                    menuType:{
                        validators:{
                            notEmpty:{
                                message:'菜单类型不能为空'
                            }
                        }
                    },
                    permission:{
                        validators:{
                            notEmpty:{
                                message:'权限标识不能为空'
                            }
                        }
                    },
                    oneLevel:{
                        validators:{
                            notEmpty:{
                                message:'一级菜单不能为空'
                            }
                        }
                    },
                }
            });


            $("#updateMenuForm").bootstrapValidator({
                fields:{
                    nameZh:{
                        validators:{
                            notEmpty:{
                                message:"菜单名称不能为空"
                            },
                            stringLength:{
                                max:20,
                                message:"字符长度不能超过20个字符"
                            }
                        }
                    },
                    menuType:{
                        validators:{
                            notEmpty:{
                                message:'菜单类型不能为空'
                            }
                        }
                    },
                    permission:{
                        validators:{
                            notEmpty:{
                                message:'权限标识不能为空'
                            }
                        }
                    }
                }
            });
        },
        //搜索
        searchMenu:function () {
        	$("#menu-table").bootstrapTable('refresh');
        },
        //清空
        empty:function () {
            $("#menu_name").val('');
            $("#menu-table").bootstrapTable('refresh');
        },
        //add-选择父级菜单展示
         addChooseNo:function(){
        	document.getElementById("menuIsShow").style.display = "block";
        },
        //add-选择父级菜单隐藏
        addChooseYes:function(){
        	$("#parentMenuId").val(0);
        	document.getElementById("menuIsShow").style.display = "none";
       },
       //updade-选择父级菜单展示
       updateChooseNo:function(){
    	   document.getElementById("menuUpdateIsShow").style.display = "block";
       },
       //updade-选择父级菜单隐藏
       updateChooseYes:function(){
       		document.getElementById("menuUpdateIsShow").style.display = "none";
      }
    }
}();


