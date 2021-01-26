var SalesCommission = {
    seItem: null		//选中的条目
};

$(function () {	
	SalesCommission.init();
	getRankSequenceList("SC_rankSequenceId");			
    $("#SC_rankSequenceId").on(  
        "change",function(){
        	getSalesGrade("SC_salesGradeId",$(this).find("option:selected").val());
        }
    )
    commSalesOrgs("SC_salesOrgId");
    $("#SC_salesOrgId").on(  
        "change",function(){
        	commSalesTeams("SC_salesTeamId",$(this).find("option:selected").val());
        }
	)		    
    
});

 function openDetailTabDetail(salerId){
				 createAddProcessTab('SalesCommission:detail',salerId);
		}


function openDetailTab(commissionId){
    alert(commissionId)
	$.ajax({
        url:'salesDaysCommission/findDetail',
        dataType:'json',
        type:'post',
        data:{commissionId:commissionId},
        success:function(data){
        	$("#insurance_saler").text(data.insurance_saler);
        	$("#insurance_saler_no").text(data.insurance_saler_no);
        	$("#initial_annual_commission").text(data.INITIAL_ANNUAL_COMMISSION);
        	$("#exhibition_allowance").text(data.EXHIBITION_ALLOWANCE);
        	$("#increase_allowance").text(data.INCREASE_ALLOWANCE);
        	$("#continuous_allowance").text(data.CONTINUOUS_ALLOWANCE);
        	$("#continuation_rate_bonus").text(data.CONTINUATION_RATE_BONUS);
        	$("#annual_bonus").text(data.ANNUAL_BONUS);
        	$("#service_award").text(data.SERVICE_AWARD);
        	$("#group_run_allowance").text(data.GROUP_RUN_ALLOWANCE);
        	$("#director_culture_award").text(data.DIRECTOR_CULTURE_AWARD);
        	$("#dep_run_allowance").text(data.DEP_RUN_ALLOWANCE);
        	$("#minister_culture_award").text(data.MINISTER_CULTURE_AWARD);
        	$("#chief_inspector_allowance").text(data.CHIEF_INSPECTOR_ALLOWANCE);
        	$("#total_commission").text(data.TOTAL_COMMISSION);
        	$("#salesCommissionDlg").modal('show');
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

var SalesCommission = function (){
	return{
		init:function (){
            $('#SalesCommission-table').bootstrapTable({
            	url: "salesDaysCommission/getCommissionPage",
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
                toolbar:"#SalesCommission_toolbar",
                showColumns : false, //显示隐藏列
                uniqueId: "INSURANCE_SALES_ID", //每一行的唯一标识，一般为主键列
                queryParamsType:'',
                queryParams: SalesCommission.queryParams,//传递参数（*）
                columns : [{
                    checkbox: true
            	},{
                    field : "INSURANCE_SALES_ID",
                    title : "编号",
                    align : "center",
                    valign : "middle",
                    sortable : "true",
		            formatter:function(value,row,index){
		                var pageSize=$('#SalesCommission-table').bootstrapTable('getOptions').pageSize;//通过表的#id 可以得到每页多少条
		                var pageNumber=$('#SalesCommission-table').bootstrapTable('getOptions').pageNumber;
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
                    field : "settledAmount",
                    title : "应结佣金(元)",
                    align : "center",
                    valign : "middle",
                    sortable : "true"
                },{
                    field : "updatedTime",
                    title : "计算时间",
                    align : "center",
                    valign : "middle",
                    sortable : "true",
                    formatter:function(value,row,index){
                        if (undefined == row.updatedTime){
                            return "--"
                        }
                    	var time = new Date(row.updatedTime);
                        var year = time.getFullYear();
                        var month = (time.getMonth()+1<10)?'0'+(time.getMonth()+1):(time.getMonth()+1);
                        var date = (time.getDate()+1<11)?'0'+time.getDate():time.getDate();
                        var hours = (time.getHours()+1<11)?'0'+time.getHours():time.getHours();
                        var minutes = (time.getMinutes()+1<11)?'0'+time.getMinutes():time.getMinutes();
                        var seconds = (time.getSeconds()+1<11)?'0'+time.getSeconds():time.getSeconds();
                        return year+'-'+month+'-'+date+' '+hours+':'+minutes+':'+seconds;
                    }
                },{
                    field : "操作",
                    title : "操作",
                    align : "center",
                    valign : "middle",
                    sortable : "true",
                    formatter:function(value,row,index){
						return '<a href="javascript:void(0)" onclick="openDetailTabDetail(\''+row.INSURANCE_SALES_ID+'\')" style="color:blue">查看</a>';

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
				salesOrgId: $("#SC_salesOrgId").val(),
				salesTeamId: $("#SC_salesTeamId").val(),
				insuranceSalerNo: $("#SC_insuranceSalerNo").val(),
				insuranceSaler: $("#SC_insuranceSaler").val(),
				rankSequenceId: $("#SC_rankSequenceId").val(),
				salesGradeId: $("#SC_salesGradeId").val(),
				startTime:$("#SC_startTime").val(),
				endTime:$("#SC_endTime").val()
			};
			return temp;
		},
		
		search:function(){
			$("#SalesCommission-table").bootstrapTable('destroy');
			SalesCommission.init();
		},
		
		empty:function(){
			$("#SC_salesOrgId").val("");
			$("#SC_salesTeamId").val("");
			$("#SC_insuranceSalerNo").val("");
			$("#SC_insuranceSaler").val("");
			$("#SC_rankSequenceId").val("");
			$("#SC_salesGradeId").val("");	
			$("#SC_startTime").val("");
			$("#SC_endTime").val("");
			$("#SalesCommission-table").bootstrapTable('refresh');
		},
		
        checkSingleData:function () {
            var selected = $('#SalesCommission-table').bootstrapTable('getSelections');
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
            	SalesCommission.seItem = selected[0];
                return true;
            }
        },
        
        //关闭模态框
        closeDlg:function () {
            $("#salerQuitAddDlg").modal('hide');
		    document.getElementById("saveButton").removeAttribute("disabled");
		    SalesCommission.formValidator();           
        },


	}
}();