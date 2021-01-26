$(function(){
	SalerQuit.formValidator();
	
	getRankSequenceList();
	getSalesOrgs("salesOrgId");//初始化获取组织机构
	getSales("", "","jobHandoverId");
	// getSales("","salerTreeHandoverId");
	
    $("#jobHandoverId").on(  
        "change",function(){
        	if($(this).find("option:selected").val() == $("#salerId").val()){
                $.alert({
                    title: '提示信息！',
                    content: '保单交接人不能为离职人本人！',
                    type: 'red'
                });
        		$(this).val("");
        		return false;
        	}
        	
        	$('#jobHandoverNo').val($(this).find("option:selected").attr("myvalue"));
        	$('#jobHandoverOrg').val($(this).find("option:selected").attr("salesOrgName"));
        	$('#jobHandoverTeam').val($(this).find("option:selected").attr("salesTeamName"));
        }
    )
    
    // $("#salerTreeHandoverId").on(
    //     "change",function(){
    //     	if($(this).find("option:selected").val() == $("#salerId").val()){
    //             $.alert({
    //                 title: '提示信息！',
    //                 content: '关系网交接人不能为离职人本人！',
    //                 type: 'red'
    //             });
    //     		$(this).val("");
    //     		return false;
    //     	}
    //
    //     	$('#salerTreeHandoverNo').val($(this).find("option:selected").attr("myvalue"));
    //     	$('#salerTreeHandoverOrg').val($(this).find("option:selected").attr("salesOrgName"));
    //     	$('#salerTreeHandoverTeam').val($(this).find("option:selected").attr("salesTeamName"));
    //     	var rankId = $(this).find("option:selected").attr("rankSequenceId");
    //     	var salesGradeId = $(this).find("option:selected").attr("salesGradeId");
    //
    // 		$("#tree_to_rankSequenceId option").each(function(){
    // 	        if($(this).val() == rankId){
    // 	            $(this).attr("selected", true);
    //
    // 	            getSalesGrade("tree_to_salesGradeId");
    // 	            setTimeout(function(){
	// 	        		$("#tree_to_salesGradeId option").each(function(){
	// 	        			//alert($(this).val());
	// 	        	        if($(this).val() == salesGradeId){
	// 	        	            $(this).attr("selected", true);
	// 	        	        }
	// 	        		});
    // 	            },100);
    // 	        }
    // 		});
    //
    //
    //     }
    // )

	setTimeout(function(){
        //组织机构代码
		$("#salesOrgId option").each(function(){
			//alert($(this).val());
	        if($(this).val()==$("#salesOrgId").attr("myvalue")){
	            $(this).attr("selected", true);
	            
	        	getSalesTeam();//初始化获取销售团队
	        }
		});
	},100);
    
	setTimeout(function(){
		//销售团队代码
		$("#salesTeamId option").each(function(){
			//alert($(this).val());
	        if($(this).val()==$("#salesTeamId").attr("myvalue")){
	            $(this).attr("selected", true);
	        }
		});
	},200);	
	
	//入职职级序列
	setTimeout(function(){
		$("#rankSequenceId option").each(function(){
			//alert($(this).val());
	        if($(this).val()==$("#rankSequenceId").attr("myvalue")){
	            $(this).attr("selected", true);
	            getSalesGrade("salesGradeId");
	            setTimeout(function(){
	        		$("#salesGradeId option").each(function(){
	        			//alert($(this).val());
	        	        if($(this).val()==$("#salesGradeId").attr("myvalue")){
	        	            $(this).attr("selected", true);
	        	        }
	        		});	            	
	            },100);	            
	        }
		});
	},200);	
});

/*获取销售团队*/
function getSalesTeam(){
	 var salesOrgId = $("#salesOrgId").val();
	 $.ajax({
         type: "POST",
         url: "salesTeamInfo/getParents",
         data:{salesOrgId:salesOrgId},
         dataType: "json",
         success: function(data){
        	 	var salesTeams = data.rows;
			 	var h = "<option value='' myvalue=''>请选择销售团队</option>";
	            $.each(salesTeams, function(key, value) {
	                h += "<option value='" + value.salesTeamId + "'>" + value.salesTeamName //下拉框序言的循环数据
	                + "</option>";  
	            });
	            $("#salesTeamId").empty();
	            $("#salesTeamId").append(h);
         }
     })
}

/*获取职级序列*/
function getRankSequenceList(){
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
	            $("#rankSequenceId").empty();
	            $("#rankSequenceId").append(h);	 
	            //$("#tree_to_rankSequenceId").empty();
	            //$("#tree_to_rankSequenceId").append(h);
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

/*获取职级*/
function getSalesGrade(selectId){
	var rankSequenceId = $("#rankSequenceId").val();
	$.ajax({
        url:'salesGrade/getSalesGradeList',
        dataType:'json',
        type:'post',
        data:{rankSequenceId:rankSequenceId},
        success:function(data){
        	var h = "<option value=''>请选择职级序列</option>";
        	if(data){
        		var salesGrades = data.rows;
	            $.each(salesGrades, function(key, value){	            	
	                h += "<option value='" + value.salesGradeId +"'>" + value.salesGradeName //下拉框序言的循环数据
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

/*获取组织机构*/
function getSalesOrgs(selectId){
	 var salesOrgName = $("#salesOrgName").val();
	 var cooperationType = $("input[name='cooperationType']:checked").val();
	 $.ajax({
         type: "POST",
         url: "salesOrgInfo/findSalesOrgs",
         data:{salesOrgName:salesOrgName,cooperationType:cooperationType},
         dataType: "json",
         success: function(data){
        	//alert(data.rows);
        	var salesOrgs = data.rows;
			var h = "<option value='' myvalue=''>请选择组织机构</option>";
            $.each(salesOrgs, function(key, value) {
            	//alert(value.insuranceCompanyCode);
                h += "<option value='" + value.salesOrgId + "'>" + value.salesOrgName //下拉框序言的循环数据
                + "</option>";  
            });
            $("#"+selectId).empty();
            $("#"+selectId).append(h);
         }
     });
}	

function getSales(insuranceSalesNo, likeSalerNo, selectId){
	var saleOrgId = $("#salesOrgId").attr("myvalue");
	$.ajax({
        type: "POST",
        url: "insuranceSalesInfo/insuranceSalesList",
        data:{
        	insuranceSalesNo : insuranceSalesNo,
			likeSalerNo : likeSalerNo,
			salesStatusList : '0,1',
			salesAllOrgs : saleOrgId
		},
        dataType: "json",
        success: function(data){
           //alert(data.rows);
           var sales = data.rows;
		   var h = "<option value='' myvalue=''>请选择</option>";
           $.each(sales, function(key, value) {
           	//alert(value.insuranceCompanyCode);
               h += "<option value='" + value.insuranceSalesId + "' myvalue='" + value.insuranceSalerNo + "" 
               +"' salesOrgName ='"+value.salesOrgName+"' salesTeamName ='"+value.salesTeamName+"' rankSequenceId ='"+value.rankSequenceId+"' salesGradeId ='"+value.salesGradeId+"'>" + value.insuranceSaler //下拉框序言的循环数据
               + "</option>";  
           });

           $("#"+selectId).empty();
           $("#"+selectId).append(h);
        }
    });	
}

var SalerQuit = function () {
	return{
	   add:function(){
	    	debugger;
	        if($("#salerQuit_addForm").data('bootstrapValidator').validate().isValid()){
	        	if( $("#jobHandoverId").val() == null || $("#jobHandoverId").val() == '') {
					$.alert({
						title: '提示信息！',
						content: '请选择保单交接人!',
						type: 'red'
					});
					document.getElementById("saveButton").removeAttribute("disabled");
					return false;
				}
				if($("#quitDate").val() == null || $("#quitDate").val() ==''){
	        		$.alert({
						title: '提示信息！',
						content: '请选择离职日期!',
						type: 'red'
					});
					document.getElementById("saveButton").removeAttribute("disabled");
					return false;
				}
				document.getElementById("saveButton").setAttribute("disabled", true);
	        	flag = true;
				if(flag){
	            	$.ajax({
	                    url:'insuranceSalesInfo/salerQuit',
	                    dataType:'json',
	                    type:'post',
	                    data:$("#salerQuit_addForm").serialize(),
	                    success:function(data){
	                        if(data.messageCode == "200"){
	                        	 $.alert({
	                                 title: '提示信息！',
	                                 content: '离职信息提交成功，请及时调整员工关系!',
	                                 type: 'blue'
	                             });
	                 		    document.getElementById("saveButton").removeAttribute("disabled");
	                 		    windowCloseTab();
	                        }else{
	                            $.alert({
	                                title: '提示信息！',
	                                content: '离职处理失败！',
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
	   
	    //表单验证
	    formValidator:function () {
	        $("#salerQuit_addForm").bootstrapValidator({
	            fields:{
					jobHandoverId:{
						validators:{
							callback:{
								message: '请选择保单交接人',
								callback: function (value, validator) {
									debugger;
									if (value =="") { //""是–请选择–选项
										return false;
									} else {
										return true;
									}
								}
							}
						}
					}
	                // salerTreeHandoverNo:{
	                //     validators:{
	                //         notEmpty:{
	                //             message:'关系网转移人不能为空',
	                //         }
	                //     }
	                // }
	            }
	        });
	    },	   
	}   
}();

//保单交接人员工编号变化
$('#jobHandoverNo').bind('input propertychange', function() {
	$("#jobHandoverId").empty();
	var likeSalerNo = $("#jobHandoverNo").val();
	getSales("", likeSalerNo, "jobHandoverId");
});

