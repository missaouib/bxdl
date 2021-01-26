$(function(){
	var insuranceSalesNo = $("#insuranceSalesNo").val();
	getRankSequenceList();
	getSalesOrgs("salesOrgId");//初始化获取组织机构
	// getjobHandoverIdSales(jobHandoverId,"jobHandoverId");
	// getSales(salerTreeHandoverId,"salerTreeHandoverId");
	getSales(insuranceSalesNo,"salerName");

	setTimeout(function(){
        //组织机构代码
		$("#salerName option").each(function(){
	        if($(this).val()==$("#salerId").val()){
	            $(this).attr("selected", true);	            
	        	$("#salerNo").val($(this).attr("myvalue"));
	        	$("#rankSequenceId").attr("myvalue",$(this).attr("rankSequenceId"));
	        	$("#salesGradeId").attr("myvalue",$(this).attr("salesGradeId"));
	        	$("#salesOrgName").val($(this).attr("salesOrgName"));
	        	$("#salesTeamName").val($(this).attr("salesTeamName"));
	        }
		});
		
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
		
        //交接人
		$("#jobHandoverId option").each(function(){
	        if($(this).val()==$("#jobHandoverId").attr("myvalue")){
	            $(this).attr("selected", true);	            
	        	$("#jobHandoverNo").val($(this).attr("myvalue"));
	        	$("#jobHandoverOrg").val($(this).attr("salesOrgName"));
	        	$("#jobHandoverTeam").val($(this).attr("salesTeamName"));
	        }
		});		
		
        //关系网
		// $("#salerTreeHandoverId option").each(function(){
	    //     if($(this).val()==$("#salerTreeHandoverId").attr("myvalue")){
	    //         $(this).attr("selected", true);
	    //     	$("#salerTreeHandoverNo").val($(this).attr("myvalue"));
	    //     	$("#tree_to_rankSequenceId").attr("myvalue",$(this).attr("rankSequenceId"));
	    //     	$("#tree_to_salesGradeId").attr("myvalue",$(this).attr("salesGradeId"));
	    //     	$("#salerTreeHandoverOrg").val($(this).attr("salesOrgName"));
	    //     	$("#salerTreeHandoverTeam").val($(this).attr("salesTeamName"));
	    //     }
		// });
		
		// $("#tree_to_rankSequenceId option").each(function(){
		// 	//alert($(this).val());
	    //     if($(this).val()==$("#tree_to_rankSequenceId").attr("myvalue")){
	    //         $(this).attr("selected", true);
	    //         getSalesGrade("tree_to_salesGradeId");
	    //         setTimeout(function(){
	    //     		$("#tree_to_salesGradeId option").each(function(){
	    //     			//alert($(this).val());
	    //     	        if($(this).val()==$("#tree_to_salesGradeId").attr("myvalue")){
	    //     	            $(this).attr("selected", true);
	    //     	        }
	    //     		});
	    //         },100);
	    //     }
		// });
				
		/*$("#salesOrgId option").each(function(){
			//alert($(this).val());
	        if($(this).val()==$("#salesOrgId").attr("myvalue")){
	            $(this).attr("selected", true);
	            getSalesTeam($(this).val());
	        }	        
		});
		
		setTimeout(function(){
			//销售团队代码
			$("#salesTeamId option").each(function(){
				//alert($(this).val());
		        if($(this).val()==$("#salesTeamId").attr("myvalue")){
		            $(this).attr("selected", true);
		        }
			});
		},300);*/	
	},200);
});

/*获取销售团队*/
function getSalesTeam(salesOrgId){
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
	            $("#tree_to_rankSequenceId").empty();
	            $("#tree_to_rankSequenceId").append(h);		            
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

function getSales(insuranceSalesNo,selectId){
	$.ajax({
        type: "POST",
        url: "insuranceSalesInfo/insuranceSalesList",
        data:{insuranceSalesNo:insuranceSalesNo},
        dataType: "json",
        success: function(data){
           //alert(data.rows);
           var sales = data.rows;
		   var h = "<option value='' myvalue=''>请选择</option>";
           $.each(sales, function(key, value) {
           	//alert(value.insuranceCompanyCode);
               h += "<option value='" + value.insuranceSalesId + "' myvalue='" + value.insuranceSalerNo + ""
               +"' salesOrgName='"+value.salesOrgName+"' salesTeamName ='"+value.salesTeamName+""
               +"' salesOrgId ='"+value.salesOrgId+"' salesTeamId ='"+value.salesTeamId+"' rankSequenceId ='"+value.rankSequenceId+"' salesGradeId ='"+value.salesGradeId+"'>" + value.insuranceSaler //下拉框序言的循环数据
               + "</option>";  
           });

           $("#"+selectId).empty();
           $("#"+selectId).append(h);
        }
    });	
}

var SalerQuit = function () {
	return{	   
	}   
}();

