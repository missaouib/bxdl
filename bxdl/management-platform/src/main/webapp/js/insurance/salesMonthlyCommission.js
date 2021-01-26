var SalesMonthlyCommission = {
    seItem: null		//选中的条目
};

$(function () {	
	SalesMonthlyCommission.init();
	getRankSequenceList("SMC_rankSequenceId");			
    $("#SMC_rankSequenceId").on(  
        "change",function(){
        	getSalesGrade("SMC_salesGradeId",$(this).find("option:selected").val());
        }
    )
    commSalesOrgs("SMC_salesOrgId");
    $("#SMC_salesOrgId").on(  
        "change",function(){
        	commSalesTeams("SMC_salesTeamId",$(this).find("option:selected").val());
        }
	)		    
    
});	

function checkStatus(){
	var item = $('input[name=commission_status]:checked').val();
	var str = '执行成功！';
	if(item == '-1'){str='该记录审核不通过，后期可以修改后重新审核！'};
	$.ajax({
        url:'salesDaysCommission/checkStatus',
        type:'post',
        dataType:'json',
        data:$("#commissionStatus_Form").serialize(),
        success:function(data){
        	$.alert({
                 title: '提示信息！',
                 content: str,
                 type: 'blue'
            });
        	$("#SalesMonthlyCommission-table").bootstrapTable('refresh');
        	SalesMonthlyCommission.closeDlg();
        }
	})
}


$("#cut_commission").click(function() {
    var s = document.getElementById("cut_commission_status");
    if(s.selectedIndex==0)
    {
      alert("请选择佣金加扣");
    }

});
$('input[type=radio][name=commission_status]').change(function () {
    if(this.value == -1){
       $("#cut_commission").attr("disabled","disabled");
       $("#cut_commission_status").attr("disabled","disabled");
       $("#cut_reason").attr("disabled","disabled");
       $("#freezing_taxes").attr("disabled","disabled");

    }else if(this.value == 1){
        $("#cut_commission").removeAttr("disabled");
        $("#cut_commission_status").removeAttr("disabled");
        $("#cut_reason").removeAttr("disabled");
        $("#freezing_taxes").removeAttr("disabled");
    }
})

function checkStatuss(commissionId,commissionStatus){
	$.confirm({
        title: '提示信息!',
        content: '您确定发放佣金吗？',
        type: 'blue',
        typeAnimated: true,
        buttons: {
        	确定: {
	        	action: function(){
					$.ajax({
				        url:'salesDaysCommission/batchRelease',
				        type:'post',
				        dataType:'json',
				        data:{commissionId:commissionId,commission_status:commissionStatus},
                        success:function(data){
                             if(data.result){
                                 $.alert({
                                     title: '提示信息！',
                                     content: '发放成功!',
                                     type: 'blue'
                                 });
                             }else{
                                 $.alert({
                                     title: '提示信息！',
                                     content: '发放失败!--'+data.msg,
                                     type: 'red'
                                 });
                             }
                             $("#SalesMonthlyCommission-table").bootstrapTable('refresh');
                             SalesMonthlyCommission.closeDlg();
                         },
				         error:function(){
                         $.alert({
                             title: '提示信息！',
                             content: '请求失败！',
                             type: 'red'
                         });
                     }
					})
	        	}
        	},
	      	  取消: function () {
	        	return true; 
	        }
        }
	});
}

function openDetailTab(commissionId){
	$.ajax({
        url:'salesDaysCommission/findMDetail',
        dataType:'json',
        type:'post',
        data:{commissionId:commissionId},
        success:function(data){
        	$("#M_insurance_saler").text(data.insurance_saler);
        	$("#M_insurance_saler_no").text(data.insurance_saler_no);
        	$("#M_initial_annual_commission").text(data.INITIAL_ANNUAL_COMMISSION);
        	$("#M_exhibition_allowance").text(data.EXHIBITION_ALLOWANCE);
        	$("#M_increase_allowance").text(data.INCREASE_ALLOWANCE);
        	$("#M_continuous_allowance").text(data.CONTINUOUS_ALLOWANCE);
        	$("#M_continuation_rate_bonus").text(data.CONTINUATION_RATE_BONUS);
        	$("#M_annual_bonus").text(data.ANNUAL_BONUS);
        	$("#M_service_award").text(data.SERVICE_AWARD);
        	$("#M_group_run_allowance").text(data.GROUP_RUN_ALLOWANCE);
        	$("#M_director_culture_award").text(data.DIRECTOR_CULTURE_AWARD);
        	$("#M_dep_run_allowance").text(data.DEP_RUN_ALLOWANCE);
        	$("#M_minister_culture_award").text(data.MINISTER_CULTURE_AWARD);
        	$("#M_chief_inspector_allowance").text(data.CHIEF_INSPECTOR_ALLOWANCE);
        	$("#M_cut_commission").text(data.CUT_COMMISSION);
        	$("#M_freezing_taxes").text(data.FREEZING_TAXES);
        	$("#M_after_tax_commission").text(data.AFTER_TAX_COMMISSION);
        	$("#M_cut_reason").text(data.CUT_REASON);
        	$("#M_remark").text(data.remark);
        	$("#salesMCommissionDlg").modal('show');
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

function openCheckTab(commissionId,afterTaxCommission){
	$("#checkCommissionId").val(commissionId);
	$("#afterTaxCommission").val(afterTaxCommission);
	$("#commissionStatusDlg").modal('show');
}

function exportSelect(){
	var idsS = $('#SalesMonthlyCommission-table').bootstrapTable('getSelections');
	var ids = "";
	for(var i=0;i<idsS.length;i++){
		ids = ids + idsS[i].COMMISSION_ID + ",";
	}
	ids = ids.substring(0,ids.length-1);
	commissionExport(ids);
}

function exportAll(){
	commissionExport("all");
}

var SalesMonthlyCommission = function (){
	return{
		init:function (){
            $('#SalesMonthlyCommission-table').bootstrapTable({
            	url: "salesDaysCommission/getMonthlyCommissionPage",
            	method:"post",
                dataType: "json",
                contentType: "application/x-www-form-urlencoded",
                striped:true,//隔行变色
                cache:false,  //是否使用缓存
                pagination: true, //分页
                sortable: false, //是否启用排序
                singleSelect: false,
                search:false, //显示搜索框
                buttonsAlign: "right", //按钮对齐方式
                showRefresh:false,//是否显示刷新按钮
                sidePagination: "server", //服务端处理分页
                pageSize : 10, //默认每页条数
                pageNumber : 1, //默认分页
                pageList : [5, 10, 20, 50 ],//分页数
                toolbar:"#salerCommission_toolbar",
                showColumns : false, //显示隐藏列
                uniqueId: "COMMISSION_ID", //每一行的唯一标识，一般为主键列
                queryParamsType:'',
                queryParams: SalesMonthlyCommission.queryParams,//传递参数（*）
                columns : [{
                    checkbox: true
            	},{
                    field : "COMMISSION_ID",
                    title : "编号",
                    align : "center",
                    valign : "middle",
                    sortable : "true",
		            formatter:function(value,row,index){
		                var pageSize=$('#SalesMonthlyCommission-table').bootstrapTable('getOptions').pageSize;//通过表的#id 可以得到每页多少条
		                var pageNumber=$('#SalesMonthlyCommission-table').bootstrapTable('getOptions').pageNumber;
		                return pageSize * (pageNumber - 1) + index + 1;    //返回每条的序号： 每页条数 * （当前页 - 1 ）+ 序号
		            }
                },{
                    field : "sales_org_name",
                    title : "组织机构",
                    align : "center",
                    valign : "middle",
                    sortable : "true"
                },{
                    field : "sales_team_name",
                    title : "销售团队",
                    align : "center",
                    valign : "middle",
                    sortable : "true"
                },{
                    field : "insurance_saler",
                    title : "员工姓名",
                    align : "center",
                    valign : "middle",
                    sortable : "true"
                },{
                    field : "insurance_saler_no",
                    title : "员工工号",
                    align : "center",
                    valign : "middle",
                    sortable : "true"
                },{
                    field : "AFTER_TAX_COMMISSION",
                    title : "应结佣金(元)",
                    align : "center",
                    valign : "middle",
                    sortable : "true"
                },{
                    field : "COMMISSION_STATUS",
                    title : "状态",
                    align : "center",
                    valign : "middle",
                    sortable : "true",
                    formatter:function(value,row,index){
	                    if(row.COMMISSION_STATUS=='0'){
							return '待审核';
						}else if(row.COMMISSION_STATUS=='1'){
							return '待发放';
						}else if(row.COMMISSION_STATUS=='-1'){
							return '不通过';
						}else if(row.COMMISSION_STATUS=='2'){
							return '已发放';
						}
                    }
                },{
                    field : "COMMISSION_MONTH",
                    title : "佣金月",
                    align : "center",
                    valign : "middle",
                    sortable : "true"
                },{
                    field : "操作",
                    title : "操作",
                    align : "center",
                    valign : "middle",
                    sortable : "true",
                    formatter:function(value,row,index){ 
						var shtml = '<a href="javascript:void(0)" onclick="openDetailTab(\''+row.COMMISSION_ID+'\')" style="color:blue">查看</a>';
						if(row.COMMISSION_STATUS=='0' || row.COMMISSION_STATUS=='-1'){
							return shtml+' | <a href="javascript:void(0)" onclick="openCheckTab(\''+row.COMMISSION_ID+'\',\''+row.AFTER_TAX_COMMISSION+'\')" style="color:blue">审核</a>';
						}else if(row.COMMISSION_STATUS=='1'){
							return shtml+' | <a href="javascript:void(0)" onclick="checkStatuss(\''+row.COMMISSION_ID+'\',\'2\')" style="color:blue">发放</a>';
						}else if(row.COMMISSION_STATUS=='2'){
							return shtml;
						}
                    }
                }
                ],
        	// bootstrap-table-treetreegrid.js 插件配置 -- end
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
				pageNo: params.pageNumber, //页码
				salesOrgId: $("#SMC_salesOrgId").val(),
				salesTeamId: $("#SMC_salesTeamId").val(),
				insuranceSalerNo: $("#SMC_insuranceSalerNo").val(),
				insuranceSaler: $("#SMC_insuranceSaler").val(),
				rankSequenceId: $("#SMC_rankSequenceId").val(),
				salesGradeId: $("#SMC_salesGradeId").val(),
				commissionStatus:$("#SMC_commissionStatus").val(),
				commissionMonth_s:$("#SMC_YJY_startTime").val(),
				commissionMonth_e:$("#SMC_YJY_endTime").val(),
				pushTime_s:$("#SMC_FF_startTime").val(),
				pushTime_e:$("#SMC_FF_endTime").val()
			};
			return temp;
		},
		
		search:function(){
			$("#SalesMonthlyCommission-table").bootstrapTable('destroy');
			SalesMonthlyCommission.init();
		},
		
		empty:function(){
			$("#SMC_salesOrgId").val("");
			$("#SMC_salesTeamId").val("");
			$("#SMC_insuranceSalerNo").val("");
			$("#SMC_insuranceSaler").val("");
			$("#SMC_rankSequenceId").val("");
			$("#SMC_salesGradeId").val("");
			$("#SMC_commissionStatus").val("");
			$("#SMC_YJY_startTime").val("");
			$("#SMC_YJY_endTime").val("");
			$("#SMC_FF_startTime").val("");
			$("#SMC_FF_endTime").val("");
			$("#SalesMonthlyCommission-table").bootstrapTable('refresh');
		},
		
        checkSingleData:function () {
            var selected = $('#SalesMonthlyCommission-table').bootstrapTable('getSelections');
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
            	SalesMonthlyCommission.seItem = selected[0];
                return true;
            }
        },


         checkBatchData:function () {
           var selected = $('#SalesMonthlyCommission-table').bootstrapTable('getSelections');
            if (selected.length == 0) {
            	 $.alert({
                     title: '提示信息！',
                     content: '至少选择一条记录！',
                     type: 'red'
                 });
                return false;
            }else {
            	SalesMonthlyCommission.seItem = selected;
                return true;
            }
        },
        /*批量发放*/
        BatchRelease:function () {
        	if(this.checkBatchData()) {
        	     var commissonIds = "";
                for (var i = 0; i < SalesMonthlyCommission.seItem.length; i++) {
                    var a=SalesMonthlyCommission.seItem[i].COMMISSION_STATUS;
                    if ( a != '1'){
                        alert("存在不可发放状态的数据,请核实");
                        return;
                    }
                    commissonIds += SalesMonthlyCommission.seItem[i].COMMISSION_ID + ",";
                }
        		 $.confirm({
                     title: '提示信息!',
                     content: '您确定要批量发放这些佣金吗？',
                     type: 'red',
                     typeAnimated: true,
                     buttons: {
                         确定: {
                             action: function(){
                                 $.ajax({
                                     url:'salesDaysCommission/batchRelease',
                                     dataType:'json',
                                     type:'post',
                                     data:{
                                         commissionId:commissonIds
                                     },
                                     success:function(data){
                                         if(data.result){
                                             $.alert({
                                                 title: '提示信息！',
                                                 content: '发放成功!',
                                                 type: 'blue'
                                             });
                                         }else{
                                             $.alert({
                                                 title: '提示信息！',
                                                 content: '发放失败!--'+data.msg,
                                                 type: 'red'
                                             });
                                         }
                                         $("#SalesMonthlyCommission-table").bootstrapTable('refresh');
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

        
        impFile:function(imageForm){
            var multipart = $("#fileName").val();
            if (multipart == "" || multipart == null) {
	        	$.alert({
                    title: '提示信息！',
                    content: '请先选择文件!',
                    type: 'red'
                });
                return false;
            }
            SalesMonthlyCommission.closeDlg();
            $("#loadingModal").modal('show');
            //$('#loadingModal').modal('hide');
            $("#"+imageForm).ajaxSubmit({
                type: 'POST',
                url: 'salesDaysCommission/importExcel',
                dataType: 'json',
                success: function (data) {
                	$("#fileName").val("");
                    $('#loadingModal').modal('hide');
                    if (data.code == "200") {
			        	$.alert({
	                        title: '提示信息！',
	                        content: '文件导入成功!',
	                        type: 'blue'
	                    });
                    } else if(data.code == "400"){
			        	$.alert({
	                        title: '提示信息！',
	                        content: '文件导入失败请重新上传!',
	                        type: 'red'
	                    });
                    }else if (data.code == "0000"){
			        	$.alert({
	                        title: '提示信息！',
	                        content: '文件不能为空!',
	                        type: 'red'
	                    });
                    }else if (data.code == "0001"){
			        	$.alert({
	                        title: '提示信息！',
	                        content: '导入失败：原因-'+data.msg,
	                        type: 'red'
	                    });
                    }else if(data.code == "500"){
                       $.alert({
                           title: '导入失败！',
                           content:data.error,
                           type: 'red'

                       })
                    }
                },
                error: function (data) {
                    $('#loadingModal').modal('hide');
                    alert("系统异常")
                }
            })
        },
        
        //关闭模态框
        closeDlg:function () {
            $("#salesMCommissionDlg").modal('hide');
            $("#commissionStatusDlg").modal('hide');
            
            $("#checkCommissionId").val("");;
            $("#check_mark").val("");
            $("#commission_status1").prop('checked',true);
            $("#commission_status2").prop('checked',false);
            $("#openImageDlg").modal('hide');
		    document.getElementById("saveButton").removeAttribute("disabled");
		      $('#commissionStatus_Form')[0].reset();
        },
	}
}();