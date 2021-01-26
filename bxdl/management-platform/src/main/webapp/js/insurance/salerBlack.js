$(function(){
	SalerBlack.formValidator();
	
	getRankSequenceList();
	getSalesOrgs("preOrgId");//初始化获取组织机构
	
	getSysDict("blackReason","BLACK_REASON");    

	setTimeout(function(){
        //组织机构代码
		$("#preOrgId option").each(function(){
			//alert($(this).val());
	        if($(this).val()==$("#preOrgId").attr("myvalue")){
	            $(this).attr("selected", true);
	            
	        	getSalesTeam("preTeamId",$("#preOrgId").attr("myvalue"));//初始化获取销售团队
	        }
		});
	},100);
    
	setTimeout(function(){
		//销售团队代码
		$("#preTeamId option").each(function(){
			//alert($(this).val());
	        if($(this).val()==$("#preTeamId").attr("myvalue")){
	            $(this).attr("selected", true);
	        }
		});
	},200);	
	
	//入职职级序列
	setTimeout(function(){
		$("#preRankSeqId option").each(function(){
			//alert($(this).val());
	        if($(this).val()==$("#preRankSeqId").attr("myvalue")){
	            $(this).attr("selected", true);
	            getSalesGrade("preSalesGradeId",$("#preRankSeqId").attr("myvalue"));
	            setTimeout(function(){
	        		$("#preSalesGradeId option").each(function(){
	        			//alert($(this).val());
	        	        if($(this).val()==$("#preSalesGradeId").attr("myvalue")){
	        	            $(this).attr("selected", true);
	        	        }
	        		});	            	
	            },100);	            
	        }
		});
	},200);	
});

/*获取销售团队*/
function getSalesTeam(selectId,salesOrgId){
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
	            $("#"+selectId).empty();
	            $("#"+selectId).append(h);
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
	            $("#preRankSeqId").empty();
	            $("#preRankSeqId").append(h);	 	            
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
function getSalesGrade(selectId,rankSequenceId){
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
	 $.ajax({
         type: "POST",
         url: "salesOrgInfo/findSalesOrgs",
         data:{},
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

var SalerBlack = function () {
	return{
	   add:function(){
	    	document.getElementById("saveButton").setAttribute("disabled", true);
	        if($("#salerMove_addForm").data('bootstrapValidator').validate().isValid()){
	        	flag = true;
				if(flag){							
	            	$.ajax({
	                    url:'insuranceSalesInfo/salerBlack',
	                    dataType:'json',
	                    type:'post',
	                    data:$("#salerMove_addForm").serialize(),
	                    success:function(data){
	                        if(data){
	                        	 if(data.messageCode == "999"){
		                        	 $.alert({
		                                 title: '提示信息！',
		                                 content: '该员工已被加黑请不要反复添加！',
		                                 type: 'red'
		                             });	                        		 
	                        	 }else{
		                        	 $.alert({
		                                 title: '提示信息！',
		                                 content: '加入黑名单请求提交成功，请及时调整员工关系!',
		                                 type: 'blue'
		                             });
		                        	 windowCloseTab();
	                        	 }
	                 		     document.getElementById("saveButton").removeAttribute("disabled");	                        	 
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
	   
	    //表单验证
	    formValidator:function () {
	        $("#salerMove_addForm").bootstrapValidator({
	            fields:{
	            	nextOrgId:{
	                    validators:{
	                        notEmpty:{
	                            message:"目标组织不能为空"
	                        }
	                    }
	                },
	                nextTeamId:{
	                    validators:{
	                        notEmpty:{
	                            message:'目标团队不能为空',
	                        }
	                    }
	                },
	                nextRankSeqId:{
	                    validators:{
	                        notEmpty:{
	                            message:'布标职级序列不能为空',
	                        }
	                    }
	                },
	                nextSalesGradeId:{
	                    validators:{
	                        notEmpty:{
	                            message:'目标职级不能为空',
	                        }
	                    }
	                }
	            }
	        });
	    },	   
	}   
}();

