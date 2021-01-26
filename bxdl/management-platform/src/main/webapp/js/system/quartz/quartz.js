//系统管理--菜单管理的单例对象
var Quartz = {
    seItem: null		//选中的条目
};

$(function(){
    Quartz.formValidator();

	 $('.close').click(function(){
		 Quartz.closeDlg();
	 });

});
var Quartz = function(){
    return{

        /**
         * 检查是否选中单条记录
         */
        checkSingleDatadel:function () {
            var selected = $('#job-table').bootstrapTable('getSelections');
            if (selected.length == 0) {
            	 $.alert({
                     title: '提示信息！',
                     content: '至少选择一条记录！',
                     type: 'red'
                 });
                return false;
            }else {
            	Quartz.seItem = selected;
                return true;
            }
        },
         checkSingleData:function () {
            var selected = $('#job-table').bootstrapTable('getSelections');
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
						Quartz.seItem = selected[0];
		                return true;
            }
        },
        //删除
        delJob:function () {
        	if(this.checkSingleDatadel()) {
        	     var jobId = "";
                for (var i = 0; i < Quartz.seItem.length; i++) {
                    jobId += Quartz.seItem[i].job_id + ",";
                }
        		//var jobId = Quartz.seItem.job_id;
        		 $.confirm({
                     title: '提示信息!',
                     content: '您确定要删除这些任务吗？',
                     type: 'red',
                     typeAnimated: true,
                     buttons: {
                         确定: {
                             action: function(){
                                 $.ajax({
                                     url:'quartz/deleteJob',
                                     dataType:'json',
                                     type:'post',
                                     data:{
                                         jobId:jobId
                                     },
                                     success:function(data){
                                         if(data.result){
                                             $.alert({
                                                 title: '提示信息！',
                                                 content: '删除成功!',
                                                 type: 'blue'
                                             });
                                         }else{
                                             $.alert({
                                                 title: '提示信息！',
                                                 content: '删除失败!--'+data.msg,
                                                 type: 'red'
                                             });
                                         }
                                         $("#job-table").bootstrapTable('refresh');
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
        saveJob:function(){
        	document.getElementById("saveButton").setAttribute("disabled", true);
            if($("#addJobForm").data('bootstrapValidator').validate().isValid()){
        				 $.ajax({
        					url:'quartz/addJob',
        					dataType:'json',
        					type:'post',
        					data:$("#addJobForm").serialize(),
        					success:function(data){
        						if(data.result){
        						    $.alert({
        						    	 title: '提示信息！',
        							     content: '添加成功!',
        							     type: 'blue'
        						    });
        						    document.getElementById("saveButton").removeAttribute("disabled");
        						    $("#job-table").bootstrapTable('refresh');
        						    Quartz.closeDlg();
        						}else{
        							$.alert({
        						        title: '提示信息！',
        						        content: '添加失败！'+data.msg,
        						        type: 'red'
        						    });
        						    document.getElementById("saveButton").removeAttribute("disabled");
        						}
                                $("#job-table").bootstrapTable('refresh');
                                Quartz.closeDlg();
        					},
        					error:function(){
        						$.alert({
        					        title: '提示信息！',
        					        content: '请求失败！',
        					        type: 'red'
        					    });
        						 $("#job-table").bootstrapTable('refresh');
        						 Quartz.closeDlg();
        					}
        				}); 
            }else{
    		    document.getElementById("saveButton").removeAttribute("disabled");
            }
        },
        //打开  添加 加载pid为0的菜单
        addJob:function () {

            $("#jobAddDlg").modal('show');
        },
        //修改 回显
        getValue:function () {
        	if(this.checkSingleData()) {
        		var jobId = Quartz.seItem.job_id;
        		$.ajax({
                    url:'quartz/getJobById',
                    dataType:'json',
                    type:'post',
                    data:{
                        jobId:jobId
                    },
                    success:function(data){
                        $("#update_job_id").val(jobId);
                        $("#update_job_name").val(data.model.jobName);
                        $("#update_job_class_name").val(data.model.jobClassName);
                        $("#update_cron").val(data.model.cron);
                        $("#update_job_describe").val(data.model.jobDescribe);

                        if(data.model.status=='1'){
                            $("#close").prop('checked',true);
                        }else{
                            $("#open").prop('checked',true);
                        }
                        $("#jobUpdateDlg").modal('show');
                    },
                    error:function(){
                        alert("请求失败！");
                    }
                });
        	}
        },
        //修改
        updateJob:function () {
            if($("#updateJobForm").data('bootstrapValidator').validate().isValid()){
            	$.ajax({
                    url:'quartz/updateJob',
                    dataType:'json',
                    type:'post',
                    data:$("#updateJobForm").serialize(),
                    success:function(data){
                        if(data.result){
                            $.alert({
                                title: '提示信息！',
                                content: '修改成功!',
                                type: 'blue'
                            });
                            $("#job-table").bootstrapTable('refresh');
                            Quartz.closeDlg();
                        }else{
                            $.alert({
                                title: '提示信息！',
                                content: '修改失败！--'+data.msg,
                                type: 'red'
                            });
                            $("#job-table").bootstrapTable('refresh');
                            Quartz.closeDlg();
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
            $("#jobAddDlg").modal('hide');
            $("#jobUpdateDlg").modal('hide');
            $("input[type=reset]").trigger("click");
            $('#updateJobForm').data('bootstrapValidator', null);
            $('#addJobForm').data('bootstrapValidator', null);
		    document.getElementById("saveButton").removeAttribute("disabled");
		    document.getElementById("addJobForm").reset()
            Quartz.formValidator();
        },
        
        formValidator:function () {
            $("#addJobForm").bootstrapValidator({
                fields:{
                    jobName:{
                        validators:{
                            notEmpty:{
                                message:"任务名称不能为空"
                            }
                        }
                    },
                    jobClassName:{
                        validators:{
                            notEmpty:{
                                message:'类名称不能为空'
                            }
                        }
                    },
                    cron:{
                        validators:{
                            notEmpty:{
                                message:'core表达式不能为空'
                            }
                        }
                    },

                }
            });


            $("#updateJobForm").bootstrapValidator({
                fields:{
                   jobName:{
                        validators:{
                            notEmpty:{
                                message:"任务名称不能为空"
                            }
                        }
                    },
                    jobClassName:{
                        validators:{
                            notEmpty:{
                                message:'类名称不能为空'
                            }
                        }
                    },
                    cron:{
                        validators:{
                            notEmpty:{
                                message:'core表达式不能为空'
                            }
                        }
                    }
                }
            });
        },
    }
}();


