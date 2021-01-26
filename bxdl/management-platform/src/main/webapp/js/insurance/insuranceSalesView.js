$(function () {	
	getAreaByPid("0","sheng");
	getRankSequenceList("rankSequenceId");
	
	getSalesOrgs("salesOrgId");//初始化获取组织机构
	getParents();//初始化获取销售团队
	
	getSysDict("cardType","CARD");
	getSysDict("bankChannel","BANK");
	
	getSysDict("country","COUNTRY");
	getSysDict("nation","NATION");
	getSysDict("politicalAppearance","FACE");
	getSysDict("party","PARTY");
	getSysDict("educationalBg","EDU");
	getSysDict("academicDegree","DEGREE");
	//$("#insuranceSalesId").val()
	//getSales("","all");
	var insuranceSalesId = $("#insuranceSalesId").val();
	var dbSalesId = $("#dbSalesId").attr("myvalue"); //担保人
	var tjSalesId = $("#tjSalesId").attr("myvalue"); //推荐人
	var ycCFirstGener = $("#ycCFirstGener").attr("myvalue");//一代处育成人
	var ycCSecondGener = $("#ycCSecondGener").attr("myvalue");//二代处育成人
	var ycBFirstGener = $("#ycBFirstGener").attr("myvalue");//一代部育成人
	var ycBSecondGener = $("#ycBSecondGener").attr("myvalue");//二代部育成人
	var ycSalesId = $("#ycSalesId").attr("myvalue"); //育成人
	var sjSalesId = $("#sjSalesId").attr("myvalue"); //上级管理人
	var jcSalesId = $("#jcSalesId").attr("myvalue"); //继承人
	var fdSalesId = $("#fdSalesId").attr("myvalue"); //辅导人
	var directGroupC = $("#directGroupC").attr("myvalue"); //直属组处长
	var directDeptB = $("#directDeptB").attr("myvalue"); //直属部部长


	getSales(insuranceSalesId,dbSalesId,tjSalesId,ycCFirstGener,ycCSecondGener,ycBFirstGener,ycBSecondGener,ycSalesId,sjSalesId,jcSalesId,fdSalesId,directGroupC,directDeptB,"all");
	
	var areaCode = $("#areaCode").val();
	setTimeout(function(){
		setTimeout(function(){
	        //组织机构代码
			$("#salesOrgId option").each(function(){
				//alert($(this).val());
		        if($(this).val()==$("#salesOrgId").attr("myvalue")){
		            $(this).attr("selected", true);
		        }
			});
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
			
			//民族
			$("#nation option").each(function(){
				//alert($(this).val());
		        if($(this).val()==$("#nation").attr("myvalue")){
		            $(this).attr("selected", true);
		        }
			});	
		},500);
		
		//证件类型
		$("#cardType option").each(function(){
			//alert($(this).val());
	        if($(this).val()==$("#cardType").attr("myvalue")){
	            $(this).attr("selected", true);
	        }
		});	
		//银行
		$("#bankChannel option").each(function(){
			//alert($(this).val());
	        if($(this).val()==$("#bankChannel").attr("myvalue")){
	            $(this).attr("selected", true);
	        }
		});	
		
		//国籍
		$("#country option").each(function(){
			//alert($(this).val());
	        if($(this).val()==$("#country").attr("myvalue")){
	            $(this).attr("selected", true);
	        }
		});	
		
		//政治面貌
		$("#politicalAppearance option").each(function(){
			//alert($(this).val());
	        if($(this).val()==$("#politicalAppearance").attr("myvalue")){
	            $(this).attr("selected", true);
	        }
		});	
		
		//党派
		$("#party option").each(function(){
			//alert($(this).val());
	        if($(this).val()==$("#party").attr("myvalue")){
	            $(this).attr("selected", true);
	        }
		});	
		
		//学历
		$("#educationalBg option").each(function(){
			//alert($(this).val());
	        if($(this).val()==$("#educationalBg").attr("myvalue")){
	            $(this).attr("selected", true);
	        }
		});
		
		//学历
		$("#academicDegree option").each(function(){
			//alert($(this).val());
	        if($(this).val()==$("#academicDegree").attr("myvalue")){
	            $(this).attr("selected", true);
	        }
		});
		
		
		$("#sheng option").each(function(){
			//alert($(this).val());
	        if($(this).val()==areaCode.substring(0,6)){
	            $(this).attr("selected", true);
	        }
		});
		getAreaByPid($("#sheng").find("option:selected").attr("myvalue"),"shi");
        if(areaCode.length>8){
			setTimeout(function(){ 
				$("#shi option").each(function(){
					//alert($(this).val());
			        if($(this).val()==areaCode.substring(0,9)){
			            $(this).attr("selected", true);					            
			        }
				});
				getAreaByPid($("#shi").find("option:selected").attr("myvalue"),"qu");
			},100);	
        }
        if(areaCode.length>11){		        	
			setTimeout(function(){ 
				$("#qu option").each(function(){
					//alert($(this).val());
			        if($(this).val()==areaCode.substring(0,12)){
			            $(this).attr("selected", true);
			        }
				});
			},300);	
        }
        
        //职称、证书表格初始化
        var titleListArry = JSON.parse($("#titleList").val());
        for(var i=0;i<titleListArry.length;i++){
        	if(titleListArry[i].titleType=="0"){
        		var zc_tr_index = Date.parse(new Date());
            	var h = '<tr id="zc_tr_index_'+zc_tr_index+'">'
        		+'<td style="display:none;text-align:center;" name="list_zc_titleId">'+titleListArry[i].titleId+'</td>'
        		+'<td style="width:220px;text-align:center;" name="list_zc_title">'+titleListArry[i].title+'</td>'
        		+'<td style="width:220px;text-align:center;" name="list_zc_awardOrg">'+titleListArry[i].awardOrg+'</td>'
        		+'<td style="width:120px;text-align:center;" name="list_zc_gotDate">'+titleListArry[i].gotDate+'</td>'
        		+'<td style="width:120px;text-align:center;" name="list_zc_effectiveDate">'+titleListArry[i].effectiveDate+'</td>'
        		+'<td style="width:120px;text-align:center;" name="list_zc_invalidDate">'+titleListArry[i].invalidDate+'</td>'
        		+'<td style="width:280px;text-align:center;" name="list_zc_remark">'+titleListArry[i].remark+'</td>'
        		+'</tr>';
            	$("#zc_list").append(h);       		
        	}else{
            	var zs_tr_index = Date.parse(new Date());
            	titleListArry[i].titleStatus = titleListArry[i].titleStatus == "0"?"有效":"失效";
            	var h = '<tr id="zs_tr_index_'+zs_tr_index+'">'
        		+'<td style="display:none;text-align:center;" name="list_zs_titleId">'+titleListArry[i].titleId+'</td>'
        		+'<td style="width:220px;text-align:center;" name="list_zs_title">'+titleListArry[i].title+'</td>'
        		+'<td style="width:120px;text-align:center;" name="list_zs_certificateNo">'+titleListArry[i].certificateNo+'</td>'
        		+'<td style="width:220px;text-align:center;" name="list_zs_awardOrg">'+titleListArry[i].awardOrg+'</td>'
        		+'<td style="width:120px;text-align:center;" name="list_zs_gotDate">'+titleListArry[i].gotDate+'</td>'
        		+'<td style="width:120px;text-align:center;" name="list_zs_invalidDate">'+titleListArry[i].invalidDate+'</td>'
        		+'<td style="width:280px;text-align:center;" name="list_zs_status">'+titleListArry[i].titleStatus+'</td>'
        		+'</tr>';
            	$("#zs_list").append(h);        		
        	}
        }
        
        //教育、工作、培训表格初始化
        var eduJobsListArry = JSON.parse($("#eduJobsList").val());
        for(var i=0;i<eduJobsListArry.length;i++){
        	if(eduJobsListArry[i].tableType=="1"){
        		var jyjl_tr_index = Date.parse(new Date());
            	var h = '<tr id="jyjl_tr_index_'+jyjl_tr_index+'">'
        		+'<td style="display:none;text-align:center;" name="list_jyjl_eduId">'+eduJobsListArry[i].eduId+'</td>'
        		+'<td style="width:220px;text-align:center;" name="list_jyjl_eduName">'+eduJobsListArry[i].eduName+'</td>'
        		+'<td style="width:120px;text-align:center;" name="list_jyjl_startDate">'+eduJobsListArry[i].startDate+'</td>'
        		+'<td style="width:120px;text-align:center;" name="list_jyjl_endDate">'+eduJobsListArry[i].endDate+'</td>'
        		+'<td style="width:120px;text-align:center;" name="list_jyjl_education">'+eduJobsListArry[i].education+'</td>'
        		+'<td style="width:120px;text-align:center;" name="list_jyjl_academicDegree">'+eduJobsListArry[i].academicDegree+'</td>'
        		+'<td style="width:280px;text-align:center;" name="list_jyjl_remark">'+eduJobsListArry[i].remark+'</td>'
        		+'</tr>';
            	$("#jyjl_list").append(h);        		
        	}else if(eduJobsListArry[i].tableType=="2"){
            	var gzjl_tr_index = Date.parse(new Date());
            	var h = '<tr id="gzjl_tr_index_'+gzjl_tr_index+'">'
        		+'<td style="display:none;text-align:center;" name="list_gzjl_eduId">'+eduJobsListArry[i].eduId+'</td>'
        		+'<td style="width:220px;text-align:center;" name="list_gzjl_eduName">'+eduJobsListArry[i].eduName+'</td>'
        		+'<td style="width:120px;text-align:center;" name="list_gzjl_startDate">'+eduJobsListArry[i].startDate+'</td>'
        		+'<td style="width:120px;text-align:center;" name="list_gzjl_endDate">'+eduJobsListArry[i].endDate+'</td>'
        		+'<td style="width:120px;text-align:center;" name="list_gzjl_position">'+eduJobsListArry[i].position+'</td>'
        		+'<td style="width:280px;text-align:center;" name="list_gzjl_remark">'+eduJobsListArry[i].remark+'</td>'
        		+'</tr>';
            	$("#gzjl_list").append(h);        		
        	}else{
            	var pxjl_tr_index = Date.parse(new Date());
            	var h = '<tr id="pxjl_tr_index_'+pxjl_tr_index+'">'
        		+'<td style="display:none;text-align:center;" name="list_pxjl_eduId">'+eduJobsListArry[i].eduId+'</td>'
        		+'<td style="width:220px;text-align:center;" name="list_pxjl_eduName">'+eduJobsListArry[i].eduName+'</td>'
        		+'<td style="width:120px;text-align:center;" name="list_pxjl_startDate">'+eduJobsListArry[i].startDate+'</td>'
        		+'<td style="width:120px;text-align:center;" name="list_pxjl_endDate">'+eduJobsListArry[i].endDate+'</td>'
        		+'<td style="width:120px;text-align:center;" name="list_pxjl_achievement">'+eduJobsListArry[i].achievement+'</td>'
        		+'<td style="width:280px;text-align:center;" name="list_pxjl_remark">'+eduJobsListArry[i].remark+'</td>'
        		+'</tr>';
            	$("#pxjl_list").append(h);        		
        	}
        }
        
        //家庭成员、紧急联系人、司外担保人
        var relativeListArry = JSON.parse($("#relativeList").val());
        for(var i=0;i<relativeListArry.length;i++){
        	if(relativeListArry[i].shipType=="1"){
            	var jtcy_tr_index = Date.parse(new Date());
            	var h = '<tr id="jtcy_tr_index_'+jtcy_tr_index+'">'
        		+'<td style="display:none;text-align:center;" name="list_jtcy_relativeId">'+relativeListArry[i].relativeId+'</td>'
        		+'<td style="width:220px;text-align:center;" name="list_jtcy_personName">'+relativeListArry[i].personName+'</td>'
        		+'<td style="width:120px;text-align:center;" name="list_jtcy_shipCellPhone">'+relativeListArry[i].shipCellPhone+'</td>'
        		+'<td style="width:120px;text-align:center;" name="list_jtcy_relationShip">'+relativeListArry[i].relationShip+'</td>'
        		+'<td style="width:120px;text-align:center;" name="list_jtcy_shipOccupation">'+relativeListArry[i].shipOccupation+'</td>'
        		+'<td style="width:280px;text-align:center;" name="list_jtcy_remark">'+relativeListArry[i].remark+'</td>'
        		+'</tr>';
            	$("#jtcy_list").append(h);        		
        	}else if(relativeListArry[i].shipType=="2"){
            	var jjlxr_tr_index = Date.parse(new Date());
            	var h = '<tr id="jjlxr_tr_index_'+jjlxr_tr_index+'">'
        		+'<td style="display:none;text-align:center;" name="list_jjlxr_relativeId">'+relativeListArry[i].relativeId+'</td>'
        		+'<td style="width:220px;text-align:center;" name="list_jjlxr_personName">'+relativeListArry[i].personName+'</td>'
        		+'<td style="width:120px;text-align:center;" name="list_jjlxr_shipCellPhone">'+relativeListArry[i].shipCellPhone+'</td>'
        		+'<td style="width:120px;text-align:center;" name="list_jjlxr_relationShip">'+relativeListArry[i].relationShip+'</td>'
        		+'<td style="width:220px;text-align:center;" name="list_jjlxr_shipAddr">'+relativeListArry[i].shipAddr+'</td>'
        		+'<td style="width:120px;text-align:center;" name="list_jjlxr_shipEmail">'+relativeListArry[i].shipEmail+'</td>'
        		+'<td style="width:280px;text-align:center;" name="list_jjlxr_remark">'+relativeListArry[i].remark+'</td>'
        		+'</tr>';
            	$("#jjlxr_list").append(h);        		
        	}else{
            	var swdbr_tr_index = Date.parse(new Date());
            	var h = '<tr id="swdbr_tr_index_'+swdbr_tr_index+'">'
        		+'<td style="display:none;text-align:center;" name="list_swdbr_relativeId">'+relativeListArry[i].relativeId+'</td>'
        		+'<td style="width:220px;text-align:center;" name="list_swdbr_personName">'+relativeListArry[i].personName+'</td>'
        		+'<td style="width:120px;text-align:center;" name="list_swdbr_shipCellPhone">'+relativeListArry[i].shipCellPhone+'</td>'
        		+'<td style="width:120px;text-align:center;" name="list_swdbr_relationShip">'+relativeListArry[i].relationShip+'</td>'
        		+'<td style="width:220px;text-align:center;" name="list_swdbr_idCard">'+relativeListArry[i].idCard+'</td>'
        		+'<td style="width:120px;text-align:center;" name="list_swdbr_guaranteeDate">'+relativeListArry[i].guaranteeDate+'</td>'
        		+'</tr>';
            	$("#swdbr_list").append(h);        		
        	}
        }
        
        //合同信息
        var contractListArry = JSON.parse($("#contractList").val());
        for(var i=0;i<contractListArry.length;i++){
	        	var ht_tr_index = Date.parse(new Date());
	        	contractListArry[i].businessAgreementFlag = contractListArry[i].businessAgreementFlag=='0'?'是':'否'
			    contractListArry[i].secretAgreementFlag = contractListArry[i].secretAgreementFlag=='0'?'是':'否'
	        	var h = '<tr id="ht_tr_index_'+ht_tr_index+'">'
	    		+'<td style="display:none;text-align:center;" name="list_ht_contractId">'+contractListArry[i].contractId+'</td>'
	    		+'<td style="width:220px;text-align:center;" name="list_ht_contractNo">'+contractListArry[i].contractNo+'</td>'
	    		+'<td style="width:120px;text-align:center;" name="list_ht_contractType">'+contractListArry[i].contractType+'</td>'
	    		+'<td style="width:120px;text-align:center;" name="list_ht_businessAgreementFlag">'+contractListArry[i].businessAgreementFlag+'</td>'
	    		+'<td style="width:120px;text-align:center;" name="list_ht_secretAgreementFlag">'+contractListArry[i].secretAgreementFlag+'</td>'
	    		+'<td style="width:120px;text-align:center;" name="list_ht_writeDate">'+contractListArry[i].writeDate+'</td>'
	    		+'<td style="width:120px;text-align:center;" name="list_ht_probationEnd">'+contractListArry[i].probationEnd+'</td>'
	    		+'<td style="width:120px;text-align:center;" name="list_ht_contractEffectDate">'+contractListArry[i].contractEffectDate+'</td>'
	    		+'<td style="width:120px;text-align:center;" name="list_ht_contractStopDate">'+contractListArry[i].contractStopDate+'</td>'
	    		+'</tr>';
	        	$("#ht_list").append(h);        	
        }
        
        setTimeout(function(){
	        var salesGrade = $("#salesGradeId").attr("myvalue");
	        var cooperationType = $("input[name='cooperationType']:checked").val();
	        //alert(salesGrade+"+"+cooperationType)
	        if(salesGrade=="1" && cooperationType=="1"){
	        	$("#zjjt_div").show();
		        //总监津贴
		        var zjjtListArry = JSON.parse($("#zjjtList").val());
		        for(var i=0;i<zjjtListArry.length;i++){
		        		var allowanceFormula = zjjtListArry[i].allowanceFormula;
		        		var max_min = (allowanceFormula.replace(/>/g,'').replace(/=/g,'')).split('FYC');
		        		var max_min_length = allowanceFormula.split(">=").length-1;
		        		var max_min_all = allowanceFormula.split('FYC');
		        		var max_t = "";
		        		var min_t = "";
		        		if(max_min_length==2){
		        			max_t = min_t = ">=";
		        		}else if(max_min_length==1){
		        			if(max_min_all[0].indexOf(">=")>max_min_all[1].indexOf(">=")){
		        				max_t = ">=";
		        				if(max_min_all[1].indexOf(">")>max_min_all[1].indexOf("=")){min_t = ">"}else{min_t="=";}		        					
		        			}else{
		        				min_t = ">=";
		        				if(max_min_all[0].indexOf(">")>max_min_all[0].indexOf("=")){max_t = ">"}else{max_t="=";}
		        			}
		        		}else{
		        			if(max_min_all[1].indexOf(">")>max_min_all[1].indexOf("=")){min_t = ">"}else{min_t="=";}
		        			if(max_min_all[0].indexOf(">")>max_min_all[0].indexOf("=")){max_t = ">"}else{max_t="=";}
		        		}
		        		var maxFYC_thtml = "<option>></option><option>=</option><option>>=</option>"; 
		        		if(max_t==">"){maxFYC_thtml = "<option selected='selected'>></option><option>=</option><option>>=</option>"}
		        		if(max_t==">="){maxFYC_thtml = "<option>></option><option>=</option><option selected='selected'>>=</option>"}
		        		if(max_t=="="){maxFYC_thtml = "<option>></option><option selected='selected'>=</option><option>>=</option>"}		        		
		        		var minFYC_thtml = "<option>></option><option>=</option><option>>=</option>"; 
		        		if(min_t==">"){minFYC_thtml = "<option selected='selected'>></option><option>=</option><option>>=</option>"}
		        		if(min_t==">="){minFYC_thtml = "<option>></option><option>=</option><option selected='selected'>>=</option>"}
		        		if(min_t=="="){minFYC_thtml = "<option>></option><option selected='selected'>=</option><option>>=</option>"}
		        		var max = max_min[0];
		        		var min = max_min[1];
			        	var zjjt_tr_index = Date.parse(new Date());
			        	var h = '<tr id="zjjt_tr_index_'+zjjt_tr_index+'">'
			    		+'<td style="width:120px;text-align:center;"><a href="javascript:void(0)" onclick="InsuranceSalesInfo.removeList(\'zjjt_tr_index_'+zjjt_tr_index+'\')" style="color:red">删除</a></td>'
			    		+'<td style="width:140px;text-align:center;">总价值佣金FYC(元)</td>'
			    		+'<td style="width:470px;text-align:center;">'
			    		+'<input type="hidden" id="allowanceId" style="width:70px;" value="'+zjjtListArry[i].allowanceId+'">'
			    		+'<input id="maxFYC" style="width:70px;" type="text" value="'+max+'">&nbsp;元&nbsp;'
			    		+'<select id="maxFYC_t" style="width:50px">'+maxFYC_thtml
			    		+'</select>&nbsp;FYC&nbsp;'
			    		+'<select id="minFYC_t" style="width:50px">'+minFYC_thtml+'</select>&nbsp;' 
			    		+'<input id="minFYC" style="width:70px;" type="text" value="'+min+'">&nbsp;元</td>'
			    		+'<td style="width:120px;text-align:center;"><input id="jtRate" style="width:80px;" type="text" value="'+zjjtListArry[i].allowanceRatio+'">%</td>';
			        	$("#zjjt_list").append(h);        	
		        }
	        }
        },100);
        
		//担保人
		$("#dbSalesId option").each(function(){
			//alert($(this).val());
	        if($(this).val()==$("#dbSalesId").attr("myvalue")){
	            $(this).attr("selected", true);
	            setTimeout(function(){
		        	var salesOrgName = $("#dbSalesId").find("option:selected").attr("salesOrgName");
		        	var salesTeamName = $("#dbSalesId").find("option:selected").attr("salesTeamName");
		        	var salesNo = $("#dbSalesId").find("option:selected").attr("myvalue");
		        	$("#dbSalesId").parent().parent("td").next().find("input").val(salesNo);
		        	$("#dbSalesId").parent().parent("td").next().next().html(salesOrgName);
		        	$("#dbSalesId").parent().parent("td").next().next().next().html(salesTeamName);
	            },100);
	        }
		});	
		
		//推荐人
		$("#tjSalesId option").each(function(){
			//alert($(this).val());
	        if($(this).val()==$("#tjSalesId").attr("myvalue")){
	            $(this).attr("selected", true);
	            setTimeout(function(){
		        	var salesOrgName = $("#tjSalesId").find("option:selected").attr("salesOrgName");
		        	var salesTeamName = $("#tjSalesId").find("option:selected").attr("salesTeamName");
		        	var salesNo = $("#tjSalesId").find("option:selected").attr("myvalue");
		        	$("#tjSalesId").parent().parent("td").next().find("input").val(salesNo);
		        	$("#tjSalesId").parent().parent("td").next().next().html(salesOrgName);
		        	$("#tjSalesId").parent().parent("td").next().next().next().html(salesTeamName);
	            },100);		            
	        }
		});
		//一代处育成人
		$("#ycCFirstGener option").each(function(){
			//alert($(this).val());
	        if($(this).val()==$("#ycCFirstGener").attr("myvalue")){
	            $(this).attr("selected", true);
	            setTimeout(function(){
		        	var salesOrgName = $("#ycCFirstGener").find("option:selected").attr("salesOrgName");
		        	var salesTeamName = $("#ycCFirstGener").find("option:selected").attr("salesTeamName");
		        	var salesNo = $("#ycCFirstGener").find("option:selected").attr("myvalue");
		        	$("#ycCFirstGener").parent().parent("td").next().find("input").val(salesNo);
		        	$("#ycCFirstGener").parent().parent("td").next().next().html(salesOrgName);
		        	$("#ycCFirstGener").parent().parent("td").next().next().next().html(salesTeamName);
	            },100);
	        }
		});
		//二代处育成人
		$("#ycCSecondGener option").each(function(){
			//alert($(this).val());
	        if($(this).val()==$("#ycCSecondGener").attr("myvalue")){
	            $(this).attr("selected", true);
	            setTimeout(function(){
		        	var salesOrgName = $("#ycCSecondGener").find("option:selected").attr("salesOrgName");
		        	var salesTeamName = $("#ycCSecondGener").find("option:selected").attr("salesTeamName");
		        	var salesNo = $("#ycCSecondGener").find("option:selected").attr("myvalue");
		        	$("#ycCSecondGener").parent().parent("td").next().find("input").val(salesNo);
		        	$("#ycCSecondGener").parent().parent("td").next().next().html(salesOrgName);
		        	$("#ycCSecondGener").parent().parent("td").next().next().next().html(salesTeamName);
	            },100);
	        }
		});
		//一代部育成人
		$("#ycBFirstGener option").each(function(){
			//alert($(this).val());
	        if($(this).val()==$("#ycBFirstGener").attr("myvalue")){
	            $(this).attr("selected", true);
	            setTimeout(function(){
		        	var salesOrgName = $("#ycBFirstGener").find("option:selected").attr("salesOrgName");
		        	var salesTeamName = $("#ycBFirstGener").find("option:selected").attr("salesTeamName");
		        	var salesNo = $("#ycBFirstGener").find("option:selected").attr("myvalue");
		        	$("#ycBFirstGener").parent().parent("td").next().find("input").val(salesNo);
		        	$("#ycBFirstGener").parent().parent("td").next().next().html(salesOrgName);
		        	$("#ycBFirstGener").parent().parent("td").next().next().next().html(salesTeamName);
	            },100);
	        }
		});
		//二代部育成人
		$("#ycBSecondGener option").each(function(){
			//alert($(this).val());
	        if($(this).val()==$("#ycBSecondGener").attr("myvalue")){
	            $(this).attr("selected", true);
	            setTimeout(function(){
		        	var salesOrgName = $("#ycBSecondGener").find("option:selected").attr("salesOrgName");
		        	var salesTeamName = $("#ycBSecondGener").find("option:selected").attr("salesTeamName");
		        	var salesNo = $("#ycBSecondGener").find("option:selected").attr("myvalue");
		        	$("#ycBSecondGener").parent().parent("td").next().find("input").val(salesNo);
		        	$("#ycBSecondGener").parent().parent("td").next().next().html(salesOrgName);
		        	$("#ycBSecondGener").parent().parent("td").next().next().next().html(salesTeamName);
	            },100);
	        }
		});

		
		//育成人
		$("#ycSalesId option").each(function(){
			//alert($(this).val());
	        if($(this).val()==$("#ycSalesId").attr("myvalue")){
	            $(this).attr("selected", true);
	            setTimeout(function(){
		        	var salesOrgName = $("#ycSalesId").find("option:selected").attr("salesOrgName");
		        	var salesTeamName = $("#ycSalesId").find("option:selected").attr("salesTeamName");
		        	var salesNo = $("#ycSalesId").find("option:selected").attr("myvalue");
		        	$("#ycSalesId").parent().parent("td").next().find("input").val(salesNo);
		        	$("#ycSalesId").parent().parent("td").next().next().html(salesOrgName);
		        	$("#ycSalesId").parent().parent("td").next().next().next().html(salesTeamName);
	            },100);	            
	        }
		});	
		
		//上级人
		$("#sjSalesId option").each(function(){
			//alert($(this).val());
	        if($(this).val()==$("#sjSalesId").attr("myvalue")){
	            $(this).attr("selected", true);
	            setTimeout(function(){
		        	var salesOrgName = $("#sjSalesId").find("option:selected").attr("salesOrgName");
		        	var salesTeamName = $("#sjSalesId").find("option:selected").attr("salesTeamName");
		        	var salesNo = $("#sjSalesId").find("option:selected").attr("myvalue");
		        	$("#sjSalesId").parent().parent("td").next().find("input").val(salesNo);
		        	$("#sjSalesId").parent().parent("td").next().next().html(salesOrgName);
		        	$("#sjSalesId").parent().parent("td").next().next().next().html(salesTeamName);
	            },100);	            
	        }
		});	
		
		//继承人
		$("#jcSalesId option").each(function(){
			//alert($(this).val());
	        if($(this).val()==$("#jcSalesId").attr("myvalue")){
	            $(this).attr("selected", true);
	            setTimeout(function(){
		        	var salesOrgName = $("#jcSalesId").find("option:selected").attr("salesOrgName");
		        	var salesTeamName = $("#jcSalesId").find("option:selected").attr("salesTeamName");
		        	var salesNo = $("#jcSalesId").find("option:selected").attr("myvalue");
		        	$("#jcSalesId").parent().parent("td").next().find("input").val(salesNo);
		        	$("#jcSalesId").parent().parent("td").next().next().html(salesOrgName);
		        	$("#jcSalesId").parent().parent("td").next().next().next().html(salesTeamName);
	            },100);	            
	        }
		});	
		
		//辅导人
		$("#fdSalesId option").each(function(){
			//alert($(this).val());
	        if($(this).val()==$("#fdSalesId").attr("myvalue")){
	            $(this).attr("selected", true);
	            setTimeout(function(){
		        	var salesOrgName = $("#fdSalesId").find("option:selected").attr("salesOrgName");
		        	var salesTeamName = $("#fdSalesId").find("option:selected").attr("salesTeamName");
		        	var salesNo = $("#fdSalesId").find("option:selected").attr("myvalue");
		        	$("#fdSalesId").parent().parent("td").next().find("input").val(salesNo);
		        	$("#fdSalesId").parent().parent("td").next().next().html(salesOrgName);
		        	$("#fdSalesId").parent().parent("td").next().next().next().html(salesTeamName);
	            },100);	            
	        }
		});

		$("#directGroupC option").each(function(){
			//alert($(this).val());
	        if($(this).val()==$("#directGroupC").attr("myvalue")){
	            $(this).attr("selected", true);
	            setTimeout(function(){
		        	var salesOrgName = $("#directGroupC").find("option:selected").attr("salesOrgName");
		        	var salesTeamName = $("#directGroupC").find("option:selected").attr("salesTeamName");
		        	var salesNo = $("#directGroupC").find("option:selected").attr("myvalue");
		        	$("#directGroupC").parent().parent("td").next().find("input").val(salesNo);
		        	$("#directGroupC").parent().parent("td").next().next().html(salesOrgName);
		        	$("#directGroupC").parent().parent("td").next().next().next().html(salesTeamName);
	            },100);
	        }
		});

		$("#directDeptB option").each(function(){
			//alert($(this).val());
	        if($(this).val()==$("#directDeptB").attr("myvalue")){
	            $(this).attr("selected", true);
	            setTimeout(function(){
		        	var salesOrgName = $("#directDeptB").find("option:selected").attr("salesOrgName");
		        	var salesTeamName = $("#directDeptB").find("option:selected").attr("salesTeamName");
		        	var salesNo = $("#directDeptB").find("option:selected").attr("myvalue");
		        	$("#directDeptB").parent().parent("td").next().find("input").val(salesNo);
		        	$("#directDeptB").parent().parent("td").next().next().html(salesOrgName);
		        	$("#directDeptB").parent().parent("td").next().next().next().html(salesTeamName);
	            },100);
	        }
		});
        
	},1500);
});

/*获取销售团队*/
function getParents(){
	 var salesTeamName = $("#parentSalesTeamName").val();
	 var salesOrgId = $("#salesOrgId").val();
	 $.ajax({
         type: "POST",
         url: "salesTeamInfo/getParents",
         data:{salesTeamName:salesTeamName,salesOrgId:salesOrgId},
         dataType: "json",
         success: function(data){
        	 	var salesOrgs = data.rows;
			 	var h = "<option value='' myvalue=''>请选择销售团队</option>";
	            $.each(salesOrgs, function(key, value) {
	            	//alert(value.insuranceCompanyCode);
	                h += "<option value='" + value.salesTeamId + "'>" + value.salesTeamName //下拉框序言的循环数据
	                + "</option>";  
	            });
	            $("#salesTeamId").empty();
	            $("#salesTeamId").append(h);
         }
     })
}

/*获取职级序列*/
function getRankSequenceList(selectId){
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
/*
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
               +"' salesOrgName ='"+value.salesOrgName+"' salesTeamName ='"+value.salesTeamName+"'>" + value.insuranceSaler //下拉框序言的循环数据
               + "</option>";  
           });
           if(selectId=="all"){
        	   $("#dbSalesId").empty();
        	   $("#dbSalesId").append(h);
        	   $("#tjSalesId").empty();
        	   $("#tjSalesId").append(h);
        	   $("#ycSalesId").empty();
        	   $("#ycSalesId").append(h);  
        	   $("#sjSalesId").empty();
        	   $("#sjSalesId").append(h);  
        	   $("#jcSalesId").empty();
        	   $("#jcSalesId").append(h);         	   
        	   $("#fdSalesId").empty();
        	   $("#fdSalesId").append(h);         	   
           }else{
	           $("#"+selectId).empty();
	           $("#"+selectId).append(h);
           }
        }
    });	
}*/



function getSales(insuranceSalesId,dbSalesId,tjSalesId,ycCFirstGener,ycCSecondGener,ycBFirstGener,ycBSecondGener,ycSalesId,sjSalesId,jcSalesId,fdSalesId,directGroupC,directDeptB,selectId){
	$.ajax({
        type: "POST",
        url: "insuranceSalesInfo/insuranceSalesListForSalesNo",
        data:{insuranceSalesId:insuranceSalesId,dbSalesId:dbSalesId,tjSalesId:tjSalesId,ycCFirstGener:ycCFirstGener,ycCSecondGener:ycCSecondGener,ycBFirstGener:ycBFirstGener,ycBSecondGener:ycBSecondGener,ycSalesId:ycSalesId,sjSalesId:sjSalesId,jcSalesId:jcSalesId,fdSalesId:fdSalesId,directGroupC:directGroupC,directDeptB:directDeptB},
        dataType: "json",
        success: function(data){
          // alert(data.rows);
           var sales = data.rows;
		   var h = "<option value='' myvalue=''>请选择</option>";
           $.each(sales, function(key, value) {
           	//alert(value.insuranceCompanyCode);
               h += "<option value='" + value.insuranceSalesId + "' myvalue='" + value.insuranceSalerNo + ""
               +"' salesOrgName ='"+value.salesOrgName+"' salesTeamName ='"+value.salesTeamName+"'>" + value.insuranceSaler //下拉框序言的循环数据
               + "</option>";
           });
           if(selectId=="all"){
        	   $("#dbSalesId").empty();
        	   $("#dbSalesId").append(h);
        	   $("#tjSalesId").empty();
        	   $("#tjSalesId").append(h);
        	   $("#ycCFirstGener").empty();
        	   $("#ycCFirstGener").append(h);
        	    $("#ycCSecondGener").empty();
        	   $("#ycCSecondGener").append(h);
        	    $("#ycBFirstGener").empty();
        	   $("#ycBFirstGener").append(h);
        	    $("#ycBSecondGener").empty();
        	   $("#ycBSecondGener").append(h);
        	   $("#ycSalesId").empty();
        	   $("#ycSalesId").append(h);
        	   $("#sjSalesId").empty();
        	   $("#sjSalesId").append(h);
        	   $("#jcSalesId").empty();
        	   $("#jcSalesId").append(h);
        	   $("#fdSalesId").empty();
        	   $("#fdSalesId").append(h);
        	   $("#directGroupC").empty();
        	   $("#directGroupC").append(h);
        	   $("#directDeptB").empty();
        	   $("#directDeptB").append(h);
           }else{
	           $("#"+selectId).empty();
	           $("#"+selectId).append(h);
           }
        }
    });
}
var InsuranceSalesView = function (){
	return{
		newStaffRelationship:function(){
   		 	var insuranceSalesId = $("#insuranceSalesId").val();
		   		 	$('#check-insurance-table').bootstrapTable({
		                url: "insuranceSalesInfo/getInsuranceSalesShip",
		                method:"post",
		                dataType: "json",
		                contentType: "application/x-www-form-urlencoded",
		                striped:true,//隔行变色
		                cache:false,  //是否使用缓存
		                showColumns:false,// 列
		                pagination: false, //分页
		                sortable: false, //是否启用排序
		                singleSelect: false,
		                search:false, //显示搜索框
		                buttonsAlign: "right", //按钮对齐方式
		                showRefresh:false,//是否显示刷新按钮
		                sidePagination: "server", //服务端处理分页
		                pageSize : 5, //默认每页条数
		                pageNumber : 1, //默认分页
		                pageList : [5, 10, 20, 50 ],//分页数
		                toolbar:"#checkkInsuranceButton",
		                showColumns : false, //显示隐藏列
		                uniqueId: "PRODUCTS_RATIO_ID", //每一行的唯一标识，一般为主键列
		                queryParamsType:'',
		                queryParams: InsuranceSalesView.queryProtocolBasicParams,//传递参数（*）
		                columns : [
		                   // {
		                	//    checkbox: true
		                	// },
		                 //    {
							// title: '序列',
							// width : '50',
							// align : "center",
							// valign : "middle",
				         //    switchable:false,
				         //    formatter:function(value,row,index){
				         //        var pageSize=$('#check-insurance-table').bootstrapTable('getOptions').pageSize;//通过表的#id 可以得到每页多少条
				         //        var pageNumber=$('#check-insurance-table').bootstrapTable('getOptions').pageNumber;//通过表的#id 可以得到当前第几页
				         //        return pageSize * (pageNumber - 1) + index + 1;    //返回每条的序号： 每页条数 * （当前页 - 1 ）+ 序号
				         //    }
			             // },
						{
		                    field : "relationship",
		                    title : "关系",
		                    align : "center",
							height:"15px",
		                    valign : "middle"
		                },{
		                    field : "insurance_saler",
		                    title : "销售人员",
		                    align : "center",
							height:"15px",
		                    valign : "middle"
		                },{
		                    field : "insurance_salerno",
		                    title : "销售人员代码",
		                    align : "center",
		                    valign : "middle",
							height:"15px",
		                    sortable : "true"
		                },{
		                    field : "sales_org",
		                    title : "组织机构",
		                    align : "center",
		                    valign : "middle",
							height:"15px",
		                    sortable : "true"
		                },{
		                    field : "sales_team",
		                    title : "营销团队",
		                    align : "center",
		                    valign : "middle",
							height:"15px",
		                    sortable : "true"
		                },{
							field : "determine_date",
							title : "确立日期",
							align : "center",
							valign : "middle",
							height:"15px",
							sortable : "true"
                            }]
		            });
		        	$("#checkInsuranceDlg").modal('show');


        },
		queryProtocolBasicParams:function(params){
            var temp = {
                pageSize: params.pageSize,  //页面大小
                pageNumber: params.pageNumber, //页码
				insuranceSalesId: $("#insuranceSalesId").val(),
            };
            return temp;
        },
		cancelCheckProduct:function(){
        	$("#checkInsuranceDlg").modal('hide');
            $("#check-insurance-table").bootstrapTable('refresh');
        },
	}
}();

		
var InsuranceSalesInfo = function(){}();