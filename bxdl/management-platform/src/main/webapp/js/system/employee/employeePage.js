
/**
 * 员工管理
 */

//系统管理--员工管理的单例对象
var Employee = {
    seItem: null		//选中的条目
};
//当前登录用户所有角色
var owneRoleList = null;
var allRole = null;
$(function (){
	//select2 多选
    // $("#employee_rid").select2({
     //    language: "zh-CN", //设置 提示语言
     //    maximumSelectionLength: 1000,  //设置最多可以选择多少项
     //    //width: "100%", //设置下拉框的宽度
     //    placeholder: "请选择",
     //    tags: false  //输入无效
    // });
    Employee.formValidator();
    Employee.init();
    $('.close').click(function(){
    	Employee.closeDlg();
	 });
    //全选角色/取消选中
    $("#allCheckedId").change(function () {
        var checkedFlag = $(this).is(':checked');
        if(checkedFlag) { //全选
            $("input[name='roleCheckName']").prop("checked", true);
        }else{
            $("input[name='roleCheckName']").prop("checked", false);
        }
    });

});
/*获取组织机构*/
function getSalesOrgs(){
    $.ajax({
        type: "POST",
        url: "salesOrgInfo/findSalesOrgs",
        data:{},
        dataType: "json",
        success: function(data){
            //alert(data.rows);
            var salesOrgs = data.rows;
            var h = "<option value='' myvalue=''>请选择所属机构</option>";
            $.each(salesOrgs, function(key, value) {
                //alert(value.insuranceCompanyCode);
                h += "<option value='" + value.salesOrgId + "|" + value.salesOrgCode + "'>" + value.salesOrgName //下拉框序言的循环数据
                    + "</option>";
            });
            $("#deptInfo").empty();
            $("#deptInfo").append(h);

            $("#update_deptInfo").empty();
            $("#update_deptInfo").append(h);

        }
    });
}
//获取当前登录用户的所有角色
function getOwneRole (employeeId) {

    $.ajax({
        url:'employee/getRole',
        dataType:'json',
        type:'post',
        async:false,//同步
        traditional:true,
        data:{
            employeeId:employeeId
        },
        success:function(data){
            allRole = data.role
            owneRoleList = data.userRole;
            // $("#employee_rid").empty();
            // $.each(data.role,function(index,items){
            //     $("#employee_rid").append("<option value='"+items.id+"'>"+items.roleName+"</option>");
            // });
            // if((data.userRole!=null)){
            //     $.each(data.userRole,function(index,items){
            //         $("#employee_rid").val(data.userRole).trigger("change");//select2 选中
            //     });
            // }else{
            //     $("#employee_rid").val(0).trigger("change");
            // }
            // $("#employeeRoleDlg").modal('show');
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
//重置密码
function resetPwd(employeeId) {
    $.confirm({
        title: '提示信息!',
        content: '您确定要执行此操作吗？',
        type: 'blue',
        typeAnimated: true,
        buttons: {
            确定: {
                action: function(){
                    $.ajax({
                        url:'employee/updatePwd',
                        dataType:'json',
                        type:'post',
                        async:false,//同步
                        traditional:true,
                        data:{
                            employeeId:employeeId,
                            newPwd:"123456"
                        },
                        success:function(data){
                            $.alert({
                                title: '提示信息！',
                                content: '修改成功！',
                                type: 'blue'
                            });
                            //$("#employee-table").bootstrapTable('refresh');
                        },
                        error:function(){
                            $.alert({
                                title: '提示信息！',
                                content: '修改失败！',
                                type: 'red'
                            });
                        }
                    });
                }
            },
            取消: function () {
                return true;
            }
        }
    });

}

function showCurrentEmpRoles(employeeId){
    getOwneRole(employeeId);
    var html = "";
    $.each(allRole,function(index,items){
        var chekcStatus = "";
        $.each(owneRoleList,function(index1,items1){
            if(items.id == items1){
                html += "<tr>";
                html += "<th>"+ items.id +"</th>";
                html += "<th>"+ items.roleName +"</th>";
                html += "<th>"+ items.remark +"</th>";
                html += "<tr>";
            }
        });

    });
    $("#ownEmpRolesTbody").html(html)
    $("#ownEmployeeRolesDlg").modal('show');
}


//表格超出宽度鼠标悬停显示td内容
function paramsMatter(value,row,index) {
    debugger;
    var span=document.createElement("span");
    span.setAttribute("title",value);
    span.innerHTML = value;
    return span.outerHTML;
}
//td宽度以及内容超过宽度隐藏
function formatTableUnit(value, row, index) {
    return {
        css: {
            "white-space": "nowrap",
            "text-overflow": "ellipsis",
            "overflow": "hidden",
            "max-width": "150px"
        }
    }
}
//表格数据展示
var Employee = function (){
    return{
        init:function (){
            $('#employee-table').bootstrapTable({
                url: "employee/getEmployeeList",
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
                uniqueId: "employee_id", //每一行的唯一标识，一般为主键列
                queryParamsType:'',
                queryParams: Employee.queryParams,//传递参数（*）
                columns : [{
                    checkbox: true
                	},{
                    field : "name",
                    title : "姓名",
                    align : "center",
                    valign : "middle",
                    sortable : "true"
                }, {
                    field : "allDeptName",
                    title : "所属机构",
                    align : "center",
                    valign : "middle",
                    sortable : "true",
                    cellStyle:formatTableUnit,
                    formatter :paramsMatter
                }, {
                    field : "employee_no",
                    title : "员工编号",
                    align : "center",
                    valign : "middle",
                    sortable : "true"
                }, {
                    field : "mobile",
                    title : "手机号码",
                    align : "center",
                    valign : "middle",
                    sortable : "true"
                }, {
                    field : "activated_state",
                    title : "状态",
                    align : "center",
                    valign : "middle",
                    sortable : "true"
                },
                {
                    field : "角色",
                    title : "角色",
                    align : "center",
                    valign : "middle",
                    sortable : "true",
                    formatter:function(value,row,index){

                        return '<a href="javascript:void(0)" onclick="showCurrentEmpRoles(\''+row.employee_id+'\')" style="color:blue">查看角色</a>';
                    }
                },
                {
                    field : "操作",
                    title : "操作",
                    align : "center",
                    valign : "middle",
                    sortable : "true",
                    formatter:function(value,row,index){

                        return '<a href="javascript:void(0)" onclick="resetPwd(\''+row.employee_id+'\')" style="color:blue">重置密码</a>';
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
        queryParams:function(params){
            var temp = {
                pageSize: params.pageSize,  //页面大小
                pageNumber: params.pageNumber, //页码
                employeeNo: $("#search_employee_no").val(),
                employeeName:$("#search_employee_name").val(),
            };
            return temp;
        },
        /**
         * 检查是否选中单条记录
         */
        checkSingleData:function () {
            var selected = $('#employee-table').bootstrapTable('getSelections');
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
            	Employee.seItem = selected[0];
                return true;
            }
        },
        //角色分配
        getRole:function(){
        	if(this.checkSingleData()) {
                var employeeId = Employee.seItem.employee_id;
        	    getOwneRole(employeeId);
        	    if(allRole == null || allRole.length <= 0){
                    $.alert({
                        title: '提示信息！',
                        content: '获取角色信息失败！',
                        type: 'red'
                    });
                    return;
                }
                var html = "";
                $.each(allRole,function(index,items){
                    var chekcStatus = "";
                    $.each(owneRoleList,function(index1,items1){
                        if(items.id == items1){
                            chekcStatus = "checked";
                        }
                    });
                    html += "<tr>";
                    html += "<th><input type='checkbox' "+ chekcStatus +" name=\"roleCheckName\"  value="+ items.id + " /></th>";
                    html += "<th>"+ items.id +"</th>";
                    html += "<th>"+ items.roleName +"</th>";
                    html += "<th>"+ items.remark +"</th>";
                    html += "<tr>";
                });
                $("#roleTbody").html(html)
                $("#employeeRoleDlg").modal('show');
                
        	}
        },
        //保存修改角色
        saveRole:function(){
        	var employeeId = Employee.seItem.employee_id;
        	var roleIds = "";

            $.each($("input[name='roleCheckName']"),function(){
                if($(this).prop("checked")){
                    roleIds +=  $(this).val()+ ",";
                }
            });

            if(roleIds == '' || roleIds == null){
                $.alert({
                    title: '提示信息！',
                    content: '请选择角色！',
                    type: 'red'
                });
                return;
            }
            if(roleIds.lastIndexOf(",")) {
                roleIds = roleIds.substring(0, roleIds.length -1);
            }
            $.ajax({
                url:'employee/updateEmployeeRole',
                dataType:'json',
                type:'post',
                traditional:true,
                data:{
                    employeeId:employeeId,
                    rid:roleIds
                },
                success:function(data){
                    if(data){
                        $.alert({
                            title: '提示信息！',
                            content: '保存成功！',
                            type: 'blue'
                        });
                        Employee.closeDlg();
                        $("#employee-table").bootstrapTable('refresh');
                    }else{
                        $.alert({
                            title: '提示信息！',
                            content: '保存失败！',
                            type: 'red'
                        });

                    }
                }
            })
        },
        //修改前，打开模态框
        openUpdateModal:function(){
        	if (this.checkSingleData()) {
        		var employeeId = Employee.seItem.employee_id;
        		 $.ajax({
                     url:'employee/getEmployeeById',
                     dataType:'json',
                     type:'post',
                     data:{
                     	employeeId:employeeId
                     },
                     success:function(data){
                         var deptInfo = data.employee.deptId + "|" + data.employee.deptNo;
                         $("#update_employee_id").val(data.employee.employeeId);
                         $("#update_deptId").val(data.employee.deptId);
                         $("#update_deptNo").val(data.employee.deptNo);


                         $("#update_employee_name").val(data.employee.name);
                         $("#update_employee_no").val(data.employee.employeeNo);
                         $("#update_mobile").val(data.employee.mobile);
                         $("#update_card_no").val(data.employee.cardNo);

                         $("#update_deptInfo option[value='"+deptInfo+"']").attr("selected","selected")

                         $("#employeeUpdateDlg").modal('show');


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
        //修改用户
        updateEmployee:function(){
            if($("#employeeUpdateForm").data("bootstrapValidator").validate().isValid()){
                var deptInfoArr = $("#update_deptInfo").val().split("|");
                //alert("deptInfo" + $("#deptInfo").val())
                $("#update_deptId").val(deptInfoArr[0]);
                $("#update_deptNo").val(deptInfoArr[1]);
                $.ajax({
                    url:'employee/updateEmployee',
                    dataType:'json',
                    type:'post',
                    data:$("#employeeUpdateForm").serialize(),
                    success:function(data){
                        if(data){
                            $.alert({
                                title: '提示信息！',
                                content: '修改成功！',
                                type: 'blue'
                            });
                            Employee.closeDlg();
                            $("#employee-table").bootstrapTable('refresh');
                        }else{
                            $.alert({
                                title: '提示信息！',
                                content: '修改失败！',
                                type: 'red'
                            });
                        }
                       
                    }
                });
            }
        },
        
        //删除
        deleteEmployee:function(activatedState){
        	
        	if (this.checkSingleData()) {
        		var employeeId = Employee.seItem.employee_id;
        		if(1 == activatedState && '启用' == Employee.seItem.activated_state){
                    $.alert({
                        title: '提示信息！',
                        content: '员工已是启用状态!',
                        type: 'red'
                    });
                    return;
                }else if(3 == activatedState && '禁用' == Employee.seItem.activated_state){
                    $.alert({
                        title: '提示信息！',
                        content: '员工已是禁用状态!',
                        type: 'red'
                    });
                    return;
                }
        		$.confirm({
                    title: '提示信息!',
                    content: '您确定要提交更改吗？',
                    type: 'blue',
                    typeAnimated: true,
                    buttons: {
                        确定: {
                            action: function(){
                            	
                               $.ajax({
                                    url:'employee/deleteEmployee',
                                    dataType:'json',
                                    type:'post',
                                    data:{
                                    	employeeId:employeeId,
                                    activatedState:activatedState
                                    },
                                    success:function(data){
                                        if(2 == activatedState){
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
                                        }else {
                                            if(data){
                                                $.alert({
                                                    title: '提示信息！',
                                                    content: '修改成功!',
                                                    type: 'blue'
                                                });
                                            }else{
                                                $.alert({
                                                    title: '提示信息！',
                                                    content: '修改失败!',
                                                    type: 'red'
                                                });
                                            }
                                        }
                                        $("#employee-table").bootstrapTable('refresh');
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
        //添加，打开模态框
        openDlg:function(){
            $("#employeeAddDlg").modal('show');
        },
        //添加用户
        saveEmployee:function(){
        	document.getElementById("saveButton").setAttribute("disabled", true);
           if($("#addEmpForm").data('bootstrapValidator').validate().isValid()){
               var deptInfoArr = $("#deptInfo").val().split("|");
               //alert("deptInfo" + $("#deptInfo").val())
               $("#deptId").val(deptInfoArr[0]);
               $("#deptNo").val(deptInfoArr[1]);
               //alert("deptId=" + deptId);
               //alert("deptNo=" + deptNo);
               $.ajax({
                    url:'employee/saveEmployee',
                    type:'post',
                    dataType:'json',
                    data:$("#addEmpForm").serialize(),
                    success:function(data){
                        if(data){
                            $.alert({
                                title: '提示信息！',
                                content: '添加成功!',
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
                        $("#employee-table").bootstrapTable('refresh');
                        Employee.closeDlg();
                    },
                    error:function(){
                        $.alert({
                            title: '提示信息！',
                            content: '请求失败！',
                            type: 'red'
                        });
                        document.getElementById("saveButton").removeAttribute("disabled");
                    }
                });
            }else{
            	document.getElementById("saveButton").removeAttribute("disabled");
            }
        },
        //关闭模态框
        closeDlg:function () {
            $("#employeeRoleDlg").modal('hide');
            $("#employeeUpdateDlg").modal('hide');
            $("#employeeAddDlg").modal('hide');
            $("#ownEmployeeRolesDlg").modal('hide');
            $("input[type=reset]").trigger("click");
            $('#addEmpForm').data('bootstrapValidator', null);
            $('#employeeUpdateForm').data('bootstrapValidator', null);
		    document.getElementById("saveButton").removeAttribute("disabled");
            Employee.formValidator();
            $('#addEmpForm')[0].reset();
            $('#employeeUpdateForm')[0].reset();
            $(".form-group").removeClass("has-error");
            $(".form-group").removeClass("has-success");
        },
        formValidator:function () {
            $("#addEmpForm").bootstrapValidator({
                fields:{
                    deptInfo:{
                        validators:{
                            notEmpty:{
                                message:"组织机构名称不能为空"
                            }
                        }
                    },
                	name:{
                        validators:{
                            notEmpty:{
                                message:"姓名不能为空"
                            },
                            stringLength:{
                                max:20,
                                message:"字符长度不能超过20个字符"
                            }
                        }
                    },
                    employeeNo:{
                        validators:{
                            notEmpty:{
                                message:"员工编号不能为空"
                            },
                            stringLength:{
                                max:20,
                                message:"字符长度不能超过50个字符"
                            }
                        }
                    },
                    mobile:{
                        validators:{
                            regexp: {
                                regexp:/^1(3|4|5|7|8)\d{9}$/,
                                message: '手机号码不符合规则!'
                            }
                        }
                    },
                    cardNo:{
                        validators:{
                            stringLength:{
                                min:18,
                                max:18,
                                message:"请输入18位身份证号"
                            }
                        }
                    }
                }
            });


            $("#employeeUpdateForm").bootstrapValidator({
                fields:{
                    deptInfo:{
                        validators:{
                            notEmpty:{
                                message:"组织机构名称不能为空"
                            }
                        }
                    },
                    name:{
                        validators:{
                            notEmpty:{
                                message:"姓名不能为空"
                            },
                            stringLength:{
                                max:20,
                                message:"字符长度不能超过20个字符"
                            }
                        }
                    },
                    employeeNo:{
                        validators:{
                            notEmpty:{
                                message:"员工编号不能为空"
                            },
                            stringLength:{
                                max:20,
                                message:"字符长度不能超过50个字符"
                            }
                        }
                    },
                    mobile:{
                        validators:{
                            regexp: {
                                regexp:/^1(3|4|5|7|8)\d{9}$/,
                                message: '手机号码不符合规则!'
                            }
                        }
                    },
                    cardNo:{
                        validators:{
                            stringLength:{
                                min:18,
                                max:18,
                                message:"请输入18位身份证号"
                            }
                        }
                    }
                }
            });
        },
        //搜索
        searchEmployee:function () {
            $("#employee-table").bootstrapTable('destroy');
            Employee.init();
        },
        //清空
        empty:function () {
            $("#search_employee_no").val('');
            $("#search_employee_name").val('');
            $("#employee-table").bootstrapTable('refresh');
        }
    }
}();
$(document).ready(function(){
  	 // $("#department_name").click(function() {
  		// $("#parentIdtreeview").show();
  		//  $.ajax({
  		//         url:'dept/getDeptTree',
  		//         dataType:'json',
  		//         type:'post',
  		//         data:{rid:1},
  		//         success:function(data){
  		//         	$('#parentIdtreeview').treeview({
  	 //                     color: "#428bca",
  	 //                     expandIcon: 'glyphicon glyphicon-chevron-right',
  	 //                     collapseIcon: 'glyphicon glyphicon-chevron-down',
  	 //                     nodeIcon: 'glyphicon glyphicon-bookmark',
  	 //                     data: data,
  	 //                     onNodeSelected: function(event, node) {
  	 //                    		$("#department_name").val(node.text);
  	 //                    		$("#employee_dept_id").val(node.id);
  	 //        					$("#parentIdtreeview").hide();
  	 //                       },
  	 //                   });
  		//         }
  		//       });
  		// });
  	 //
     	//  $("#update_departmentname").click(function() {
       	// 	$("#update_parentIdtreeview").show();
       	// 	 $.ajax({
       	// 	        url:'dept/getDeptTree',
       	// 	        dataType:'json',
       	// 	        type:'post',
       	// 	        data:{rid:1},
       	// 	        success:function(data){
       	// 	        	$('#update_parentIdtreeview').treeview({
       	//                      color: "#428bca",
       	//                      expandIcon: 'glyphicon glyphicon-chevron-right',
       	//                      collapseIcon: 'glyphicon glyphicon-chevron-down',
       	//                      nodeIcon: 'glyphicon glyphicon-bookmark',
       	//                      data: data,
       	//                      onNodeSelected: function(event, node) {
       	//                     		$("#update_departmentname").val(node.text);
       	//                     		$("#employee_update_dept_id").val(node.id);
       	//         					$("#update_parentIdtreeview").hide();
       	//                        },
       	//                    });
       	// 	        }
       	// 	      });
       	// 	});
    //初始化获取组织机构
    getSalesOrgs();




});
