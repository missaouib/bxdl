//系统管理--部门管理的单例对象
var Department = {
    seItem: null		//选中的条目
};

$(function(){
	Department.formValidator();
	Department.init();
	$("#dept_departmentname").click(function() {
  		$("#parentIdtreeview").show();
  		 $.ajax({
  		        url:'dept/getDeptTree',
  		        dataType:'json',
  		        type:'post',
  		        data:{rid:1},
  		        success:function(data){
  		        	$('#parentIdtreeview').treeview({
  	                     color: "#428bca",
  	                     expandIcon: 'glyphicon glyphicon-chevron-right',
  	                     collapseIcon: 'glyphicon glyphicon-chevron-down',
  	                     nodeIcon: 'glyphicon glyphicon-bookmark',
  	                     data: data,
  	                     onNodeSelected: function(event, node) {
  	                    		$("#dept_departmentname").val(node.text);
  	                    		$("#dept_parentId").val(node.id);
  	        					$("#parentIdtreeview").hide();
  	                       },
  	                   });
  		        }
  		     });
  		});
	 $("#dept_update_departmentname").click(function() {
	  		$("#update_parentIdtreeview").show();
	  		 $.ajax({
	  		        url:'dept/getDeptTree',
	  		        dataType:'json',
	  		        type:'post',
	  		        data:{rid:1},
	  		        success:function(data){
	  		        	$('#update_parentIdtreeview').treeview({
	  	                     color: "#428bca",
	  	                     expandIcon: 'glyphicon glyphicon-chevron-right',
	  	                     collapseIcon: 'glyphicon glyphicon-chevron-down',
	  	                     nodeIcon: 'glyphicon glyphicon-bookmark',
	  	                     data: data,
	  	                     onNodeSelected: function(event, node) {
	  	                    		$("#dept_update_departmentname").val(node.text);
	  	                    		$("#dept_update_parentId").val(node.id);
	  	        					$("#update_parentIdtreeview").hide();
	  	                       },
	  	                   });
	  		        }
	  		     });
	  		});
	 $('.close').click(function(){
		 Department.closeDlg();
	 });
});
var Department = function(){
    return{
        init:function(){
            $('#dept-table').bootstrapTable({
            	url: "dept/getDeptList",
            	dataType: "json",
            	idField: 'dept_id',
            	toolbar:"#toolbar",
            	sortable: false, //是否启用排序
                uniqueId: "dept_id", //每一行的唯一标识，一般为主键列
                queryParamsType:'',
               // queryParams: Department.deptQueryParams,//传递参数（*）
                columns : [{
                    checkbox: true
            	}, {
                    field : "dept_name",
                    title : "部门名称",
                    align : "center",
                    valign : "middle",
                    sortable : "true"
                }, {
                    field : "levels",
                    title : "部门层级",
                    align : "center",
                    valign : "middle",
                    sortable : "true"
                },{
                    field : "remark",
                    title : "备注",
                    align : "center",
                    valign : "middle",
                    sortable : "true"
                }],
                //在哪一列展开树形
        		treeShowField : 'dept_name',
        		//指定父id列
        		parentIdField : 'parent_id',
        		onResetView : function(data) {
        			 $('#dept-table').treegrid({
        				initialState : 'collapsed',// 所有节点都折叠
        				// initialState: 'expanded',// 所有节点都展开，默认展开
        				treeColumn : 1,
        				onChange : function() {
        					$('#dept-table').bootstrapTable('resetWidth');
        				}
        			});
        			//只展开树形的第一级节点
        			 $('#dept-table').treegrid('getRootNodes').treegrid('expand');
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
        deptQueryParams:function(params){
            var temp = {
                deptName: $("#dept_name").val(),
            };
	            return temp;
	        },
        
        /**
         * 检查是否选中单条记录
         */
        checkSingleData:function () {
            var selected = $('#dept-table').bootstrapTable('getSelections');
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
            	Department.seItem = selected[0];
                return true;
            }
        },
        //删除
        delDepartment:function () {
        	if(this.checkSingleData()) {
        		var DepartmentId = Department.seItem.dept_id;
        		 $.confirm({
                     title: '提示信息!',
                     content: '您确定要删除这个菜单吗？',
                     type: 'red',
                     typeAnimated: true,
                     buttons: {
                         确定: {
                             action: function(){
                                 $.ajax({
                                     url:'Department/deleteDepartment',
                                     dataType:'json',
                                     type:'post',
                                     data:{
                                         DepartmentId:DepartmentId
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
                                         $("#dept-table").bootstrapTable('refresh');
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
        saveDepartment:function(){
        	document.getElementById("saveButton").setAttribute("disabled", true);
            if($("#addDeptForm").data('bootstrapValidator').validate().isValid()){
        				 $.ajax({
        					url:'dept/saveDepartment',
        					dataType:'json',
        					type:'post',
        					data:$("#addDeptForm").serialize(),
        					success:function(data){
        						if(data){
        						    $.alert({
        						    	 title: '提示信息！',
        							     content: '添加成功!',
        							     type: 'blue'
        						    });
        						    document.getElementById("saveButton").removeAttribute("disabled");
        						    $("#dept-table").bootstrapTable('refresh');
        						    Department.closeDlg();
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
        //打开  添加页面
        addDepartment:function () {
            $("#dept_addDlg").modal('show');
        },
        //修改 回显
        getValue:function () {
        	if(this.checkSingleData()) {
        		var DepartmentId = Department.seItem.dept_id;
        		$.ajax({
                    url:'dept/getDepartmentById',
                    dataType:'json',
                    type:'post',
                    data:{
                        did:DepartmentId
                    },
                    success:function(data){
                        $("#update_deptName").val(data.department.deptName);
                        $("#dept_update_departmentname").val(data.department.departmentname);
                        $("#update_dept_id").val(data.department.deptId);
                        if(data.department.levels ==1){
                            $("#dept_update_parentId").val(0);
                            $("#yes").prop('checked',true);
                       		document.getElementById("update_isShow").style.display = "none";
                        }
                        if(data.department.levels !=1){
                        	 $("#no").prop('checked',true);
                        	$("#dept_update_parentId").val(data.department.parent_id);
                       		document.getElementById("update_isShow").style.display = "block";
                        }
                        $("#update_sort").val(data.department.sort);
                        $("#update_remark").val(data.department.remark);
                        $("#dept_updateDlg").modal('show');
                    },
                    error:function(){
                        alert("请求失败！");
                    }
                });
        	}
        },
        //修改
        updateDepartment:function () {
            if($("#updateDeptForm").data('bootstrapValidator').validate().isValid()){
	            	$.ajax({
	                    url:'dept/updateDepartment',
	                    dataType:'json',
	                    type:'post',
	                    data:$("#updateDeptForm").serialize(),
	                    success:function(data){
	                        if(data == '1'){
	                            $.alert({
	                                title: '提示信息！',
	                                content: '修改成功!',
	                                type: 'blue'
	                            });
	                            $("#dept-table").bootstrapTable('refresh');
	                            Department.closeDlg();
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
            $("#dept_addDlg").modal('hide');
            $("#dept_updateDlg").modal('hide');
            $("input[type=reset]").trigger("click");
            $('#updateDeptForm').data('bootstrapValidator', null);
            $('#addDeptForm').data('bootstrapValidator', null);
		    document.getElementById("saveButton").removeAttribute("disabled");
            Department.formValidator();
        },
        
        formValidator:function () {
            $("#addDeptForm").bootstrapValidator({
                fields:{
                    deptName:{
                        validators:{
                            notEmpty:{
                                message:"部门名称不能为空"
                            }
                        }
                    },oneLevel:{
                        validators:{
                            notEmpty:{
                                message:'请选择是否是一级部门'
                            }
                        }
                    },
                }
            });


            $("#updateDeptForm").bootstrapValidator({
                fields:{
                    deptName:{
                        validators:{
                            notEmpty:{
                                message:"部门名称不能为空"
                            }
                        }
                    }
                }
            });
        },
        //搜索
        searchDepartment:function () {
        		$("#dept-table").bootstrapTable('refresh');
        },
        //清空
        empty:function () {
            $("#dept_name").val('');
            $("#dept-table").bootstrapTable('refresh');
        },
        //add-选择父级菜单展示
        addChooseNo:function(){
       	document.getElementById("isShow").style.display = "block";
       },
        //add-选择父级菜单隐藏
       addChooseYes:function(){
    	   $("#dept_parentId").val(0);
       		document.getElementById("isShow").style.display = "none";
      },
       //updade-选择父级菜单展示
      updateChooseNo:function(){
   	   document.getElementById("update_isShow").style.display = "block";
      },
       //updade-选择父级菜单隐藏
      updateChooseYes:function(){
      		document.getElementById("update_isShow").style.display = "none";
     }
        
    }
}();


