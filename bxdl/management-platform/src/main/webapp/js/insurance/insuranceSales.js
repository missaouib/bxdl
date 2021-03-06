$(function () {
	InsuranceSalesInfo.formValidator();
	getAreaByPid("0","sheng");
	getRankSequenceList("rankSequenceId");
	getSalesOrgs("salesOrgId");
	getParents();//初始化获取销售团队
	getSysDict("cardType","CARD");
	getSysDict("bankChannel","BANK");
	
	getSysDict("country","COUNTRY");
	getSysDict("nation","NATION");
	getSysDict("politicalAppearance","FACE");
	getSysDict("party","PARTY");
	getSysDict("educationalBg","EDU");
	getSysDict("academicDegree","DEGREE");

	getSales("","all");
	
	$('#salesOrgName').bind('input propertychange', function() {
		getSalesOrgs("salesOrgId");
	});
	
	$('#dbSalesNo').bind('input propertychange', function() {
		getSales($(this).val(),'dbSalesId');
	});
	$('#tjSalesNo').bind('input propertychange', function() {
		getSales($(this).val(),'tjSalesId');
	});
	$('#ycCFirstGenerNo').bind('input propertychange', function() {
		getSales($(this).val(),'ycCFirstGener');
	});
	$('#ycCSecondGenerNo').bind('input propertychange', function() {
		getSales($(this).val(),'ycCSecondGener');
	});
	$('#ycBFirstGenerNo').bind('input propertychange', function() {
		getSales($(this).val(),'ycBFirstGener');
	});
	$('#ycBSecondGenerNo').bind('input propertychange', function() {
		getSales($(this).val(),'ycBSecondGener');
	});
	$('#ycSalesNo').bind('input propertychange', function() {
		getSales($(this).val(),'ycSalesId');
	});
	$('#fdSalesNo').bind('input propertychange', function() {
		getSales($(this).val(),'fdSalesId');
	});
	$('#jcSalesNo').bind('input propertychange', function() {
		getSales($(this).val(),'jcSalesId');
	});
	$('#sjSalesNo').bind('input propertychange', function() {
		getSales($(this).val(),'sjSalesId');
	});
	$('#directGroupCNo').bind('input propertychange', function() {
		getSales($(this).val(),'directGroupC');
	});
	$('#directDeptBNo').bind('input propertychange', function() {
		getSales($(this).val(),'directDeptB');
	});

	/*setTimeout(function(){
	        //组织机构代码
			$("#salesOrgId option").each(function(){
				//alert($(this).val());
		        if($(this).val()==$("#salesOrgId").attr("myvalue")){
		            $(this).attr("selected", true);
		            $("#salesOrgName").val($(this).text());
		        }
			});
			//销售团队代码
			$("#salesTeamId option").each(function(){
				//alert($(this).val());
		        if($(this).val()==$("#salesTeamId").attr("myvalue")){
		            $(this).attr("selected", true);
		             $("#parentSalesTeamName").val($(this).text());
		        }
			});
		},200);*/

	//时间格式化
	Date.prototype.Format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "H+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
  }



    
    $("#salesOrgId").on(  
        "change",function(){
        	$('#salesOrgName').val($(this).find("option:selected").text());
        	if ($('#salesOrgName').val() != ""){
        		$("#addForm").data('bootstrapValidator').enableFieldValidators('salesOrgName',true,'notEmpty');
			}
        	$("#parentSalesTeamName").val("");
        	var h = "<option value='' myvalue=''>请选择销售团队</option>";
            $("#salesTeamId").empty();
            $("#salesTeamId").append(h);
            getParents();   	            	
        }
    )
    
    $('input[type=radio][name=cooperationType]').on(  
	    "change",function(){
        	$("#salesOrgName").val("");
        	var text = "<option value='' myvalue=''>请选择组织机构</option>";
            $("#salesOrgId").empty();
            $("#salesOrgId").append(text);	    	
            getSalesOrgs("salesOrgId");
            
        	$("#parentSalesTeamName").val("");
        	var h = "<option value='' myvalue=''>请选择销售团队</option>";
            $("#salesTeamId").empty();
            $("#salesTeamId").append(h);
            
            var salesGrade = $("#salesGradeId").val();
            var cooperationType = $("input[name='cooperationType']:checked").val();
            if(salesGrade == "1" && cooperationType == "1"){
            	$("#zjjt_div tr:gt(0)").remove();
            	$("#zjjt_div").show();
            }else{
            	$("#zjjt_div").hide();
            	$("#zjjt_div tr:gt(0)").remove();
            }
	    }
    )
    
	$("#parentSalesTeamName").bind('input propertychange', function() {
		getParents();
	}); 
    
    $("#salesTeamId").on(  
        "change",function(){
        	$('#parentSalesTeamName').val($(this).find("option:selected").text());
        	if ($('#parentSalesTeamName').val() != ""){
        		$("#addForm").data('bootstrapValidator').enableFieldValidators('parentSalesTeamName',true,'notEmpty');
			}
        	$('#PTcode').val($(this).find("option:selected").attr("myvalue"));
        }
	) 
	
    $("#rankSequenceId").on(  
        "change",function(){
			getSalesGrade("salesGradeId");  	            	
        }
	)
	
	$("#salesGradeId").on(  
        "change",function(){
            var salesGrade = $("#salesGradeId").val();
            var cooperationType = $("input[name='cooperationType']:checked").val();
            if(salesGrade == "1" && cooperationType == "1"){
            	$("#zjjt_div").show();
            }else{
            	$("#zjjt_div").hide();
            	$("#zjjt_div tr:gt(0)").remove();
            }
        }
    )


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
					 if(value.salesTeamId==$("#salesTeamId").attr("myvalue")){
					 	  h += "<option value='" + value.salesTeamId + "' selected='selected'>" + value.salesTeamName //下拉框序言的循环数据
	                       + "</option>";
					 	   $("#parentSalesTeamName").val(value.salesTeamName);
					 }else {
					 	  h += "<option value='" + value.salesTeamId + "'>" + value.salesTeamName //下拉框序言的循环数据
	                       + "</option>";
					 }

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
         data:{salesOrgName:salesOrgName,cooperationType:cooperationType,dataAuthFlag:'1'},
         dataType: "json",
         success: function(data){
        	//alert(data.rows);
        	var salesOrgs = data.rows;
			var h = "<option value='' myvalue=''>请选择组织机构</option>";
            $.each(salesOrgs, function(key, value) {
            	//alert(value.insuranceCompanyCode);
				if (value.salesOrgId == $("#salesOrgId").attr("myvalue")){
                   h += "<option value='" + value.salesOrgId + "' selected='selected'>" + value.salesOrgName //下拉框序言的循环数据
                    + "</option>";
                    $("#salesOrgName").val(value.salesOrgName);
				}else {
					 h += "<option value='" + value.salesOrgId + "'>" + value.salesOrgName //下拉框序言的循环数据
                    + "</option>";
				}

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
        data:{insuranceSalesNo:insuranceSalesNo,dataAuthFlag:"1"},
        dataType: "json",
        success: function(data){
           //alert(data.rows);
           var sales = data.rows;
		   var h = "<option value='' myvalue=''>请选择</option>";
           $.each(sales, function(key, value) {
           	   //alert(value.insuranceCompanyCode);
               h += "<option value='" + value.insuranceSalesId + "' myvalue='" + value.insuranceSalerNo + "" 
               +"' salesOrgName ='"+value.salesOrgName+"' salesTeamName ='"+value.salesTeamName+"' salesGradeId ='"+value.salesGradeId+"'>" + value.insuranceSaler //下拉框序言的循环数据
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

function creatArryList(){
	
	var zjjtList = [];
	var zjjtListLength = $("#zjjt_list").find("tr").length;
	for(var i=1 ; i< zjjtListLength ; i++){
		var zjjtObj = {};
		zjjtObj.allowanceRatio = $("#zjjt_list tr:eq("+i+")").find("#jtRate").val();
		var maxFYC = $("#zjjt_list tr:eq("+i+")").find("input#maxFYC").val();
		var maxFYC_t = $("#zjjt_list tr:eq("+i+")").find("#maxFYC_t").find("option:selected").text();
		var minFYC = $("#zjjt_list tr:eq("+i+")").find("#minFYC").val();
		var minFYC_t = $("#zjjt_list tr:eq("+i+")").find("#minFYC_t").find("option:selected").text();
		zjjtObj.allowanceFormula = (maxFYC+maxFYC_t+"FYC"+minFYC_t+minFYC).replace(/>/g,"&gt;");
		zjjtList.push(zjjtObj);
	}
	console.log(zjjtList);//---------合同信息
	$("#zjjtList").val(JSON.stringify(zjjtList));		
	
	var titleList = [];
	var zcListLength = $("#zc_list").find("tr").length;
	for(var i=1 ; i< zcListLength ; i++){
		var zcobj = {};
		zcobj.title = $("#zc_list tr:eq("+i+")").children("td[name=list_zc_title]").text();
		zcobj.awardOrg = $("#zc_list tr:eq("+i+")").children("td[name=list_zc_awardOrg]").text();
		zcobj.gotDate = $("#zc_list tr:eq("+i+")").children("td[name=list_zc_gotDate]").text();
		zcobj.effectiveDate = $("#zc_list tr:eq("+i+")").children("td[name=list_zc_effectiveDate]").text();
		zcobj.invalidDate = $("#zc_list tr:eq("+i+")").children("td[name=list_zc_invalidDate]").text(); 
		zcobj.remark = $("#zc_list tr:eq("+i+")").children("td[name=list_zc_remark]").text();
		zcobj.certificateNo = "";
		zcobj.titleStatus = "0";
		zcobj.titleType = "0";
		titleList.push(zcobj);
	}
	var zsListLength = $("#zs_list").find("tr").length;
	for(var i=1 ; i< zsListLength ; i++){
		var zsobj = {};
		zsobj.title = $("#zs_list tr:eq("+i+")").children("td[name=list_zs_title]").text();
		zsobj.awardOrg = $("#zs_list tr:eq("+i+")").children("td[name=list_zs_awardOrg]").text();
		zsobj.gotDate = $("#zs_list tr:eq("+i+")").children("td[name=list_zs_gotDate]").text();
		zsobj.certificateNo = $("#zs_list tr:eq("+i+")").children("td[name=list_zs_certificateNo]").text();
		zsobj.invalidDate = $("#zs_list tr:eq("+i+")").children("td[name=list_zs_invalidDate]").text(); 
		zsobj.titleStatus = $("#zs_list tr:eq("+i+")").children("td[name=list_zs_status]").text();
		if(zsobj.titleStatus == '有效'){
		   zsobj.titleStatus = '0';
		}else if(zsobj.titleStatus == '失效'){
			zsobj.titleStatus = '1';
		}
		zsobj.effectiveDate = "";
		zsobj.remark = "";
		zsobj.titleType = "1";
		titleList.push(zsobj);
	}
	console.log(titleList);//--------职称&证书
	$("#titleList").val(JSON.stringify(titleList));
	
	var eduJobsList = [];
	var jyjlListLength = $("#jyjl_list").find("tr").length;
	for(var i=1 ; i< jyjlListLength ; i++){
		var jyjlobj = {};
		jyjlobj.eduName = $("#jyjl_list tr:eq("+i+")").children("td[name=list_jyjl_eduName]").text();
		jyjlobj.startDate = $("#jyjl_list tr:eq("+i+")").children("td[name=list_jyjl_startDate]").text();
		jyjlobj.endDate = $("#jyjl_list tr:eq("+i+")").children("td[name=list_jyjl_endDate]").text();
		jyjlobj.education = $("#jyjl_list tr:eq("+i+")").children("td[name=list_jyjl_education]").text();
		jyjlobj.academicDegree = $("#jyjl_list tr:eq("+i+")").children("td[name=list_jyjl_academicDegree]").text(); 
		jyjlobj.remark = $("#jyjl_list tr:eq("+i+")").children("td[name=list_jyjl_remark]").text();	
		jyjlobj.achievement = "";
		jyjlobj.position = "";
		jyjlobj.tableType = "1";
		eduJobsList.push(jyjlobj);
	}	
	var gzjlListLength = $("#gzjl_list").find("tr").length;
	for(var i=1 ; i< gzjlListLength ; i++){
		var gzjlobj = {};
		gzjlobj.eduName = $("#gzjl_list tr:eq("+i+")").children("td[name=list_gzjl_eduName]").text();
		gzjlobj.startDate = $("#gzjl_list tr:eq("+i+")").children("td[name=list_gzjl_startDate]").text();
		gzjlobj.endDate = $("#gzjl_list tr:eq("+i+")").children("td[name=list_gzjl_endDate]").text();
		gzjlobj.position = $("#gzjl_list tr:eq("+i+")").children("td[name=list_gzjl_position]").text();
		gzjlobj.remark = $("#gzjl_list tr:eq("+i+")").children("td[name=list_gzjl_remark]").text();
		gzjlobj.education = "";
		gzjlobj.academicDegree = "";
		gzjlobj.achievement = "";
		gzjlobj.tableType = "2";
		eduJobsList.push(gzjlobj);
	}
	var pxjlListLength = $("#pxjl_list").find("tr").length;
	for(var i=1 ; i< pxjlListLength ; i++){
		var pxjlobj = {};
		pxjlobj.eduName = $("#pxjl_list tr:eq("+i+")").children("td[name=list_pxjl_eduName]").text();
		pxjlobj.startDate = $("#pxjl_list tr:eq("+i+")").children("td[name=list_pxjl_startDate]").text();
		pxjlobj.endDate = $("#pxjl_list tr:eq("+i+")").children("td[name=list_pxjl_endDate]").text();
		pxjlobj.achievement = $("#pxjl_list tr:eq("+i+")").children("td[name=list_pxjl_achievement]").text();
		pxjlobj.remark = $("#pxjl_list tr:eq("+i+")").children("td[name=list_pxjl_remark]").text();	
		pxjlobj.education = "";
		pxjlobj.academicDegree = "";
		pxjlobj.position = "";
		pxjlobj.tableType = "3";		
		eduJobsList.push(pxjlobj);
	}
	console.log(eduJobsList);//---------教育经历、工作经历、培训经历
	$("#eduJobsList").val(JSON.stringify(eduJobsList));
	
	var relativeList = [];
	var jtcyListLength = $("#jtcy_list").find("tr").length;
	for(var i=1 ; i< jtcyListLength ; i++){
		var jtcyObj = {};
		jtcyObj.personName = $("#jtcy_list tr:eq("+i+")").children("td[name=list_jtcy_personName]").text();
		jtcyObj.shipCellPhone = $("#jtcy_list tr:eq("+i+")").children("td[name=list_jtcy_shipCellPhone]").text();
		jtcyObj.relationShip = $("#jtcy_list tr:eq("+i+")").children("td[name=list_jtcy_relationShip]").text();
		jtcyObj.shipOccupation = $("#jtcy_list tr:eq("+i+")").children("td[name=list_jtcy_shipOccupation]").text();
		jtcyObj.remark = $("#jtcy_list tr:eq("+i+")").children("td[name=list_jtcy_remark]").text();
		jtcyObj.shipEmail = "";
		jtcyObj.shipAddr = "";
		jtcyObj.idCard = "";
		jtcyObj.guaranteeDate="";
		jtcyObj.shipType="1";
		relativeList.push(jtcyObj);
	}
	var jjlxrListLength = $("#jjlxr_list").find("tr").length;
	for(var i=1 ; i< jjlxrListLength ; i++){
		var jjlxrObj = {};
		jjlxrObj.personName = $("#jjlxr_list tr:eq("+i+")").children("td[name=list_jjlxr_personName]").text();
		jjlxrObj.shipCellPhone = $("#jjlxr_list tr:eq("+i+")").children("td[name=list_jjlxr_shipCellPhone]").text();
		jjlxrObj.relationShip = $("#jjlxr_list tr:eq("+i+")").children("td[name=list_jjlxr_relationShip]").text();
		jjlxrObj.shipAddr = $("#jjlxr_list tr:eq("+i+")").children("td[name=list_jjlxr_shipAddr]").text();
		jjlxrObj.shipEmail = $("#jjlxr_list tr:eq("+i+")").children("td[name=list_jjlxr_shipEmail]").text();
		jjlxrObj.remark = $("#jjlxr_list tr:eq("+i+")").children("td[name=list_jjlxr_remark]").text();
		jjlxrObj.shipOccupation="";
		jjlxrObj.idCard="";
		jjlxrObj.guaranteeDate="";
		jjlxrObj.shipType="2";
		relativeList.push(jjlxrObj);
	}
	var swdbrListLength = $("#swdbr_list").find("tr").length;
	for(var i=1 ; i< swdbrListLength ; i++){
		var swdbrObj = {};
		swdbrObj.personName = $("#swdbr_list tr:eq("+i+")").children("td[name=list_swdbr_personName]").text();
		swdbrObj.shipCellPhone = $("#swdbr_list tr:eq("+i+")").children("td[name=list_swdbr_shipCellPhone]").text();
		swdbrObj.relationShip = $("#swdbr_list tr:eq("+i+")").children("td[name=list_swdbr_relationShip]").text();
		swdbrObj.idCard = $("#swdbr_list tr:eq("+i+")").children("td[name=list_swdbr_idCard]").text();
		swdbrObj.guaranteeDate = $("#swdbr_list tr:eq("+i+")").children("td[name=list_swdbr_guaranteeDate]").text();
		swdbrObj.remark = "";
		swdbrObj.shipAddr = "";
		swdbrObj.shipEmail = "";
		swdbrObj.shipOccupation="";
		swdbrObj.shipType="3";
		relativeList.push(swdbrObj);
	}
	console.log(relativeList);//---------家庭成员、紧急联系人、司外担保人
	$("#relativeList").val(JSON.stringify(relativeList));
	
	var contractList = [];
	var htListLength = $("#ht_list").find("tr").length;
	for(var i=1 ; i< htListLength ; i++){
		var htObj = {};
		htObj.contractNo = $("#ht_list tr:eq("+i+")").children("td[name=list_ht_contractNo]").text();
		htObj.contractType = $("#ht_list tr:eq("+i+")").children("td[name=list_ht_contractType]").text();
		htObj.businessAgreementFlag = $("#ht_list tr:eq("+i+")").children("td[name=list_ht_businessAgreementFlag]").text();
		if (htObj.businessAgreementFlag == '是'){
			htObj.businessAgreementFlag = '0'
		}else  if(htObj.businessAgreementFlag == '否'){
			htObj.businessAgreementFlag = '1'
		}
		htObj.secretAgreementFlag = $("#ht_list tr:eq("+i+")").children("td[name=list_ht_secretAgreementFlag]").text();
		if (htObj.secretAgreementFlag == '是'){
			htObj.secretAgreementFlag = '0'
		}else  if(htObj.secretAgreementFlag == '否'){
			htObj.secretAgreementFlag = '1'
		}
		htObj.writeDate = $("#ht_list tr:eq("+i+")").children("td[name=list_ht_writeDate]").text();
		htObj.probationEnd = $("#ht_list tr:eq("+i+")").children("td[name=list_ht_probationEnd]").text();
		htObj.contractEffectDate = $("#ht_list tr:eq("+i+")").children("td[name=list_ht_contractEffectDate]").text();
		htObj.contractStopDate = $("#ht_list tr:eq("+i+")").children("td[name=list_ht_contractStopDate]").text();		
		contractList.push(htObj);
	}
	console.log(contractList);//---------合同信息
	$("#contractList").val(JSON.stringify(contractList));
	
}
		
var InsuranceSalesInfo = function () {
	return{				
		 //添加
    add:function () {
    	//目的为了后台获取值
    	$("#directDeptB").removeAttr('disabled','disabled');
        $("#directGroupC").removeAttr('disabled','disabled');
        $("#directDeptB").parent().parent("td").next().next().next().next().find("input").removeAttr('disabled','disabled')
		$("#directGroupC").parent().parent("td").next().next().next().next().find("input").removeAttr('disabled','disabled')
    	//document.getElementById("saveButton").setAttribute("disabled", true);
        if($("#addForm").data('bootstrapValidator').validate().isValid()){
        	flag = true;
        	var sheng = $("#sheng").val();
        	var shi = $("#shi").val();
        	var qu = $("#qu").val();
        	if(qu != ""){ 
        		$("#areaCode").val(qu);
        	}else{
        		if(shi!=""){$("#areaCode").val(shi);}else{$("#areaCode").val(sheng);}
        	}
        	if($("#joinDate").val()==""){
        		$.alert({
                    title: '提示信息！',
                    content: '入司时间不能为空!',
                    type: 'red'
                });
        		flag = false;
    		    document.getElementById("saveButton").removeAttribute("disabled");
    		    return false;
        	}
        	if($("#salesOrgId").val()==""){
        		$.alert({
                    title: '提示信息！',
                    content: '请选择组织机构!',
                    type: 'red'
                });
        		flag = false;
    		    document.getElementById("saveButton").removeAttribute("disabled");
    		    return false;
			}
			if($("#salesTeamId").val()==""){
        		$.alert({
                    title: '提示信息！',
                    content: '请选择销售团队!',
                    type: 'red'
                });
        		flag = false;
    		    document.getElementById("saveButton").removeAttribute("disabled");
    		    return false;
			}
			if(flag){
				creatArryList();				
            	$.ajax({
                    url:'insuranceSalesInfo/addInsuranceSales',
                    dataType:'json',
                    type:'post',
                    data:$("#addForm").serialize(),
					beforeSend: function () {
							$("#saveButton").attr('disabled', 'disabled');
					},
				   complete: function () {
					   $("#saveButton").removeAttr('disabled');
				   },
					success:function(data){
                    	if("200001"==data.messageCode){
                    		$.alert({
								title: '提示信息！',
								content: data.data,
								type: 'red'
                    		});
                    		document.getElementById("saveButton").removeAttribute("disabled");
                    		return;
						}
                        if("200" == data.messageCode){
                  		    windowCloseTab();
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
        $("#addForm").bootstrapValidator({
            fields:{
            	salesOrgId:{
                    validators:{
                        notEmpty:{
                            message:"组织机构名称不能为空"
                        }
                    }
                },
                salesTeamId:{
                    validators:{
                        notEmpty:{
                            message:'销售团队代码不能为空',
                        },
                        stringLength:{
                            max:32,
                            message:'字符长度不能超过32'
                        }
                    }
                },
				rankSequenceId:{
					validators:{
						notEmpty:{
							message:"职级序列不能为空"
						}
					}
				},
				salesGradeId:{
					validators:{
						notEmpty:{
							message:"职级不能为空"
						}
					}
				},
                insuranceSaler:{
                    validators:{
                        notEmpty:{
                            message:'员工姓名不能为空',
                        }
                    }
                },
                mobile:{
                    validators:{
                        notEmpty:{
                            message:'手机号码不能为空',
                        },
                    	regexp: {
                            regexp:/^1(3|4|5|7|8)\d{9}$/,
                            message: '手机号码不符合规则!'
                        },
                        stringLength:{
                        	max:11,
							message:'字符长度不能超过11'
						}
                    }
                },
                sex:{
                    validators:{
                        notEmpty:{
                            message:'性别不能为空',
                        }
                    }
                },
                tjSalesId:{
                    validators:{
                        notEmpty:{
                            message:'推荐人不能为空',
                        }
                    }
                },
				directGroupC:{
            		validators:{
                        notEmpty:{
                            message:'直属组处长不能为空',
                        }
                    }
				},
				directDeptB:{
            		validators:{
                        notEmpty:{
                            message:'直属部部长不能为空',
                        }
                    }
				},
				salesOrgName:{
            		validators:{
            			notEmpty:{
            				message:'组织机构名称不能为空',
						}
					}
				},
				parentSalesTeamName:{
            		validators:{
            			notEmpty:{
            				message:'销售团队名称不能为空',
						}
					}
				},
				joinDate:{
            		validators:{
            			notEmpty:{
            				message:"入司时间不能为空",
						}
					},
					trigger: 'focus'
				},
				birthday:{
            		validators:{
            			notEmpty:{
            				message:"出生日期不能为空",
						}
					},
					trigger: 'focus'
				},
				bankCardNo:{
            		validators:{
            			notEmpty:{
            				message:"银行卡号不能为空",
						},
            			regexp: {
							regexp: /^\d+(\.\d+)?$/,
							message: '请输入数字'
						},
						stringLength:{
            				max:20,
							message:'字符长度不能超过20'
						}
					}
				},
				qqNumber:{
            		validators:{
            			regexp: {
							regexp: /^\d+(\.\d+)?$/,
							message: '请输入数字'
						},
						stringLength:{
            				max:20,
							message:'字符长度不能超过20'
						}
					}
				},
				bankChannel:{
            		validators:{
            			notEmpty:{
            				message:"银行名称不能为空",
						}
					}
				},
				openBank:{
            		validators:{
            			notEmpty:{
            				message:"开户行名称不能为空",
						}
					}
				},
				politicalAppearance:{
					validators:{
            			notEmpty:{
            				message:"政治面貌不能为空",
						}
					}
				},
				sheng:{
            		validators:{
            			notEmpty:{
            				message:"所在省份不能为空",
						}
					}
				},
				shi:{
            		validators:{
            			notEmpty:{
            				message:"所在城市不能为空",
						}
					}
				},
				qu:{
            		validators:{
            			notEmpty:{
            				message:"所在区县不能为空",
						}
					}
				},
				educationalBg:{
            		validators:{
            			notEmpty:{
            				message:"最高学历不能为空",
						}
					}
				},
				postCode:{
            		validators:{
            			regexp: {
							regexp: /^\d+(\.\d+)?$/,
							message: '请输入数字'
						}
					}
				},
				cardType:{
                    validators:{
                        notEmpty:{
                            message:'证件类型不能为空',
                        }
                    }
                },
                cardNo:{
                    validators:{
                        notEmpty:{
                            message:'证件号码不能为空',
                        },
                        stringLength:{
                        	max:30,
							message:'字符长度不能超过30'
						},
						callback: {
                         message: '证件号格式不正确',
                         callback: function(value, validator) {
                         	// debugger;
                         	var sel = $("#cardType option:selected");
                         	var cardType = sel.attr("myvalue");
                         	 //  var sel=document.getElementById("cardType");
                         	 //  var index = sel.selectedIndex; // 选中索引
							  // var cardType= sel.options[index].value;//要的值
                         	// console.log(cardType);
                         	var cardNo = value;
                         	var regIdCard = /^(^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$)|(^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[Xx])$)$/;
                         	var regHkCard = /^([A-Z]\d{6,10}(\(\w{1}\))?)$/;
                         	var regPassPortCard = /^([a-zA-z]|[0-9]){5,17}$/;
                         	var regOfficerCard =  /^[\u4E00-\u9FA5](字第)([0-9a-zA-Z]{4,8})(号?)$/;
                         	if(cardType=='1' || cardType=='5'){
								if (regIdCard.test(cardNo)) {//身份证
									// alert ("身份证验证通过");
									return true;
								}else{
									// alert ("身份证验证失败");
									return false;
								}
							}else if(cardType=='2'){
                         		if (regHkCard.test(cardNo)) {//港澳通行证
									return true;
								}else{
									// alert ("港澳通行证验证失败");
									return false;
								}
							}else if(cardType=='3'){
								if (regPassPortCard.test(cardNo)) {//护照
									return true;
								}else{
									// alert ("护照验证失败");
									return false;
								}
							}else if(cardType=='4'){
                         		if (regOfficerCard.test(cardNo)) {//军官证
									return true;
								}else{
									// alert ("护照验证失败");
									return false;
								}
							}else {
								return true
							}
                         }
                     	}
                    }
                }

            }
        });
     },
    
    
    //打开添加模态框
    openAddModal:function (dlgId) {
        $("#"+dlgId).modal('show');
    },
    
    //打开添加模态框
    removeList:function (trid) {
        $("#"+trid).remove();
    },    
    
    //----------------------------------------------------------------------------------------职称
    addZc:function(listId){
    	var zc_title = $("#zc_title").val();
    	var zc_awardOrg = $("#zc_awardOrg").val();
    	var zc_gotDate = $("#zc_gotDate").val();
    	var zc_effectiveDate = $("#zc_effectiveDate").val();
    	var zc_invalidDate = $("#zc_invalidDate").val();
    	var zc_remark = $("#zc_remark").val();
    	var zc_tr_index = Date.parse(new Date());
		if (zc_title == '' && zc_awardOrg == '' && zc_gotDate == '' && zc_effectiveDate=='' && zc_invalidDate == ''){
				$.alert({
				title: '提示信息！',
				content: '未填写任何职称信息，请先填写再保存',
				type: 'red'
			});
			 return false;
		}
    	var h = '<tr id="zc_tr_index_'+zc_tr_index+'">'
		+'<td style="width:120px;text-align:center;"><a href="javascript:void(0)" onclick="InsuranceSalesInfo.updateZc(\'zc_tr_index_'+zc_tr_index+'\''
		+')" style="color:blue">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" onclick="InsuranceSalesInfo.removeList(\'zc_tr_index_'+zc_tr_index+'\')" style="color:red">删除</a></td>'
		+'<td style="width:220px;text-align:center;" name="list_zc_title">'+zc_title+'</td>'
		+'<td style="width:220px;text-align:center;" name="list_zc_awardOrg">'+zc_awardOrg+'</td>'
		+'<td style="width:120px;text-align:center;" name="list_zc_gotDate">'+zc_gotDate+'</td>'
		+'<td style="width:120px;text-align:center;" name="list_zc_effectiveDate">'+zc_effectiveDate+'</td>'
		+'<td style="width:120px;text-align:center;" name="list_zc_invalidDate">'+zc_invalidDate+'</td>'
		+'<td style="width:280px;text-align:center;" name="list_zc_remark">'+zc_remark+'</td>'
		+'</tr>';
    	$("#"+listId).append(h);
    	$("#zcAddDlg").modal('hide');
    	$("#zc_addForm").find("input[type=text]").val("");
    },
    
    updateZc:function(trid){
    	$("#zcMydlg").modal('show');
    	var zc_title = $("#"+trid).children("td[name=list_zc_title]").text();
    	var zc_awardOrg = $("#"+trid).children("td[name=list_zc_awardOrg]").text();
    	var zc_gotDate = $("#"+trid).children("td[name=list_zc_gotDate]").text();
    	var zc_effectiveDate = $("#"+trid).children("td[name=list_zc_effectiveDate]").text();
    	var zc_invalidDate = $("#"+trid).children("td[name=list_zc_invalidDate]").text(); 
    	var zc_remark = $("#"+trid).children("td[name=list_zc_remark]").text();
    	
    	$("#zc_update_title").val(zc_title);
    	$("#zc_update_awardOrg").val(zc_awardOrg);
    	$("#zc_update_gotDate").val(zc_gotDate);
    	$("#zc_update_effectiveDate").val(zc_effectiveDate);
    	$("#zc_update_invalidDate").val(zc_invalidDate);
    	$("#zc_update_remark").val(zc_remark);
    	$("#zc_index_update").val(trid);
    	//alert($("#"+trid).html());
    }, 
    
    updateZcSave:function(){
    	var zc_title = $("#zc_update_title").val();
    	var zc_awardOrg = $("#zc_update_awardOrg").val();
    	var zc_gotDate = $("#zc_update_gotDate").val();
    	var zc_effectiveDate = $("#zc_update_effectiveDate").val();
    	var zc_invalidDate = $("#zc_update_invalidDate").val();
    	var zc_remark = $("#zc_update_remark").val();    	
    	var trid = $("#zc_index_update").val();
    	if (zc_title == '' && zc_awardOrg == '' && zc_gotDate == '' && zc_effectiveDate=='' && zc_invalidDate == ''){
				$.alert({
				title: '提示信息！',
				content: '未填写任何职称信息，请先填写再保存',
				type: 'red'
			});
			 return false;
		}
    	
    	$("#"+trid).children("td[name=list_zc_title]").text(zc_title);
    	$("#"+trid).children("td[name=list_zc_awardOrg]").text(zc_awardOrg);
    	$("#"+trid).children("td[name=list_zc_gotDate]").text(zc_gotDate);
    	$("#"+trid).children("td[name=list_zc_effectiveDate]").text(zc_effectiveDate);
    	$("#"+trid).children("td[name=list_zc_invalidDate]").text(zc_invalidDate);
    	$("#"+trid).children("td[name=list_zc_remark]").text(zc_remark);
    	
    	$("#zcMydlg").modal('hide');
    	$("#zc_updateForm").find("input[type=text]").val("");
    },
    
    //----------------------------------------------------------------------------------------证书
    addZs:function(listId){
    	var zs_title = $("#zs_title").val();
    	var zs_certificateNo = $("#zs_certificateNo").val();
    	var zs_awardOrg = $("#zs_awardOrg").val();
    	var zs_gotDate = $("#zs_gotDate").val();
    	var zs_invalidDate = $("#zs_invalidDate").val();
    	var zs_status = $("input[name='zs_status']:checked").val();
    	var zs_tr_index = Date.parse(new Date());
    	if (zs_title == '' && zs_certificateNo == '' && zs_awardOrg == '' && zs_gotDate=='' && zs_invalidDate == ''){
				$.alert({
				title: '提示信息！',
				content: '未填写任何证书信息，请先填写再保存',
				type: 'red'
			});
			 return false;
		}
		if (zs_status =='0'){
    		zs_status = '有效';
		}else if  (zs_status =='1'){
			zs_status = '失效';
		}

    	var h = '<tr id="zs_tr_index_'+zs_tr_index+'">'
		+'<td style="width:120px;text-align:center;"><a href="javascript:void(0)" onclick="InsuranceSalesInfo.updateZs(\'zs_tr_index_'+zs_tr_index+'\''
		+')" style="color:blue">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" onclick="InsuranceSalesInfo.removeList(\'zs_tr_index_'+zs_tr_index+'\')" style="color:red">删除</a></td>'
		+'<td style="width:220px;text-align:center;" name="list_zs_title">'+zs_title+'</td>'
		+'<td style="width:120px;text-align:center;" name="list_zs_certificateNo">'+zs_certificateNo+'</td>'
		+'<td style="width:220px;text-align:center;" name="list_zs_awardOrg">'+zs_awardOrg+'</td>'
		+'<td style="width:120px;text-align:center;" name="list_zs_gotDate">'+zs_gotDate+'</td>'
		+'<td style="width:120px;text-align:center;" name="list_zs_invalidDate">'+zs_invalidDate+'</td>'
		+'<td style="width:280px;text-align:center;" name="list_zs_status">'+zs_status+'</td>'
		+'</tr>';
    	$("#"+listId).append(h);
    	$("#zhengshuAddDlg").modal('hide');
    	$("#zs_addForm").find("input[type=text]").val("");
    },
    
    updateZs:function(trid){
    	$("#zhengshuMydlg").modal('show');
    	var zs_title = $("#"+trid).children("td[name=list_zs_title]").text();
    	var zs_awardOrg = $("#"+trid).children("td[name=list_zs_awardOrg]").text();
    	var zs_gotDate = $("#"+trid).children("td[name=list_zs_gotDate]").text();
    	var zs_certificateNo = $("#"+trid).children("td[name=list_zs_certificateNo]").text();
    	var zs_invalidDate = $("#"+trid).children("td[name=list_zs_invalidDate]").text(); 
    	var zs_status = $("#"+trid).children("td[name=list_zs_status]").text();
    	if(zs_status == '0'){
    		zs_status = '有效';
		}else if (zs_status == '1'){
    		zs_status = '失效';
		}
    	
    	$("#zs_update_title").val(zs_title);
    	$("#zs_update_awardOrg").val(zs_awardOrg);
    	$("#zs_update_gotDate").val(zs_gotDate);
    	$("#zs_update_certificateNo").val(zs_certificateNo);
    	$("#zs_update_invalidDate").val(zs_invalidDate);
    	$("input[name='zs_update_status'][value='"+zs_status+"']").prop("checked", "checked");
    	$("#zs_index_update").val(trid);
    	//alert($("#"+trid).html());
    },
    
    updateZsSave:function(){
    	var zs_title = $("#zs_update_title").val();
    	var zs_awardOrg = $("#zs_update_awardOrg").val();
    	var zs_gotDate = $("#zs_update_gotDate").val();
    	var zs_certificateNo = $("#zs_update_certificateNo").val();
    	var zs_invalidDate = $("#zs_update_invalidDate").val();
    	var zs_status = $("input[name='zs_update_status']:checked").val();
    	var trid = $("#zs_index_update").val();
    	if (zs_title == '' && zs_certificateNo == '' && zs_awardOrg == '' && zs_gotDate=='' && zs_invalidDate == ''){
				$.alert({
				title: '提示信息！',
				content: '未填写任何证书信息，请先填写再保存',
				type: 'red'
			});
			 return false;
		}
		if (zs_status =='0'){
    		zs_status = '有效';
		}else if  (zs_status =='1'){
			zs_status = '失效';
		}
    	
    	$("#"+trid).children("td[name=list_zs_title]").text(zs_title);
    	$("#"+trid).children("td[name=list_zs_awardOrg]").text(zs_awardOrg);
    	$("#"+trid).children("td[name=list_zs_gotDate]").text(zs_gotDate);
    	$("#"+trid).children("td[name=list_zs_certificateNo]").text(zs_certificateNo);
    	$("#"+trid).children("td[name=list_zs_invalidDate]").text(zs_invalidDate);
    	$("#"+trid).children("td[name=list_zs_status]").text(zs_status);
    	
    	$("#zhengshuMydlg").modal('hide');
    	$("#zs_updateForm").find("input[type=text]").val("");
    },
    
    //----------------------------------------------------------------------------------------教育经历
    addJyjl:function(listId){
    	var jyjl_eduName = $("#jyjl_eduName").val();
    	var jyjl_startDate = $("#jyjl_startDate").val();
    	var jyjl_endDate = $("#jyjl_endDate").val();
    	var jyjl_education = $("#jyjl_education").val();
    	var jyjl_academicDegree = $("#jyjl_academicDegree").val();
    	var jyjl_remark = $("#jyjl_remark").val();
    	var jyjl_tr_index = Date.parse(new Date());
    	if (jyjl_eduName == '' && jyjl_startDate == '' && jyjl_endDate == '' && jyjl_education=='' && jyjl_academicDegree == ''){
				$.alert({
				title: '提示信息！',
				content: '未填写任何教育经历信息，请先填写再保存',
				type: 'red'
			});
			 return false;
		}
    	var h = '<tr id="jyjl_tr_index_'+jyjl_tr_index+'">'
		+'<td style="width:120px;text-align:center;"><a href="javascript:void(0)" onclick="InsuranceSalesInfo.updateJyjl(\'jyjl_tr_index_'+jyjl_tr_index+'\''
		+')" style="color:blue">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" onclick="InsuranceSalesInfo.removeList(\'jyjl_tr_index_'+jyjl_tr_index+'\')" style="color:red">删除</a></td>'
		+'<td style="width:220px;text-align:center;" name="list_jyjl_eduName">'+jyjl_eduName+'</td>'
		+'<td style="width:120px;text-align:center;" name="list_jyjl_startDate">'+jyjl_startDate+'</td>'
		+'<td style="width:120px;text-align:center;" name="list_jyjl_endDate">'+jyjl_endDate+'</td>'
		+'<td style="width:120px;text-align:center;" name="list_jyjl_education">'+jyjl_education+'</td>'
		+'<td style="width:120px;text-align:center;" name="list_jyjl_academicDegree">'+jyjl_academicDegree+'</td>'
		+'<td style="width:280px;text-align:center;" name="list_jyjl_remark">'+jyjl_remark+'</td>'
		+'</tr>';
    	$("#"+listId).append(h);
    	$("#jyjlAddDlg").modal('hide');
    	$("#jyjl_addForm").find("input[type=text]").val("");
    },
    
    updateJyjl:function(trid){
    	$("#jyjlMydlg").modal('show');
    	var jyjl_eduName = $("#"+trid).children("td[name=list_jyjl_eduName]").text();
    	var jyjl_startDate = $("#"+trid).children("td[name=list_jyjl_startDate]").text();
    	var jyjl_endDate = $("#"+trid).children("td[name=list_jyjl_endDate]").text();
    	var jyjl_education = $("#"+trid).children("td[name=list_jyjl_education]").text();
    	var jyjl_academicDegree = $("#"+trid).children("td[name=list_jyjl_academicDegree]").text(); 
    	var jyjl_remark = $("#"+trid).children("td[name=list_jyjl_remark]").text();
    	
    	$("#jyjl_update_eduName").val(jyjl_eduName);
    	$("#jyjl_update_startDate").val(jyjl_startDate);
    	$("#jyjl_update_endDate").val(jyjl_endDate);
    	$("#jyjl_update_education").val(jyjl_education);
    	$("#jyjl_update_academicDegree").val(jyjl_academicDegree);
    	$("#jyjl_update_remark").val(jyjl_remark);
    	$("#jyjl_index_update").val(trid);
    	//alert($("#"+trid).html());
    },
    
    updateJyjlSave:function(){
    	var jyjl_eduName = $("#jyjl_update_eduName").val();
    	var jyjl_startDate = $("#jyjl_update_startDate").val();
    	var jyjl_endDate = $("#jyjl_update_endDate").val();
    	var jyjl_education = $("#jyjl_update_education").val();
    	var jyjl_academicDegree = $("#jyjl_update_academicDegree").val();
    	var jyjl_remark = $("#jyjl_update_remark").val();

    	var trid = $("#jyjl_index_update").val();
    	if (jyjl_eduName == '' && jyjl_startDate == '' && jyjl_endDate == '' && jyjl_education=='' && jyjl_academicDegree == ''){
				$.alert({
				title: '提示信息！',
				content: '未填写任何教育经历信息，请先填写再保存',
				type: 'red'
			});
			 return false;
		}
    	
    	$("#"+trid).children("td[name=list_jyjl_eduName]").text(jyjl_eduName);
    	$("#"+trid).children("td[name=list_jyjl_startDate]").text(jyjl_startDate);
    	$("#"+trid).children("td[name=list_jyjl_endDate]").text(jyjl_endDate);
    	$("#"+trid).children("td[name=list_jyjl_education]").text(jyjl_education);
    	$("#"+trid).children("td[name=list_jyjl_academicDegree]").text(jyjl_academicDegree);
    	$("#"+trid).children("td[name=list_jyjl_remark]").text(jyjl_remark);
    	
    	$("#jyjlMydlg").modal('hide');
    	$("#jyjl_updateForm").find("input[type=text]").val("");
    },
    
    //----------------------------------------------------------------------------------------工作经历
    addGzjl:function(listId){
    	var gzjl_eduName = $("#gzjl_eduName").val();
    	var gzjl_startDate = $("#gzjl_startDate").val();
    	var gzjl_endDate = $("#gzjl_endDate").val();
    	var gzjl_position = $("#gzjl_position").val();
    	var gzjl_remark = $("#gzjl_remark").val();
    	var gzjl_tr_index = Date.parse(new Date());
    	if (gzjl_eduName == '' && gzjl_startDate == '' && gzjl_endDate == '' && gzjl_position=='' ){
				$.alert({
				title: '提示信息！',
				content: '未填写任何工作经历信息，请先填写再保存',
				type: 'red'
			});
			 return false;
		}
    	var h = '<tr id="gzjl_tr_index_'+gzjl_tr_index+'">'
		+'<td style="width:120px;text-align:center;"><a href="javascript:void(0)" onclick="InsuranceSalesInfo.updateGzjl(\'gzjl_tr_index_'+gzjl_tr_index+'\''
		+')" style="color:blue">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" onclick="InsuranceSalesInfo.removeList(\'gzjl_tr_index_'+gzjl_tr_index+'\')" style="color:red">删除</a></td>'
		+'<td style="width:220px;text-align:center;" name="list_gzjl_eduName">'+gzjl_eduName+'</td>'
		+'<td style="width:120px;text-align:center;" name="list_gzjl_startDate">'+gzjl_startDate+'</td>'
		+'<td style="width:120px;text-align:center;" name="list_gzjl_endDate">'+gzjl_endDate+'</td>'
		+'<td style="width:120px;text-align:center;" name="list_gzjl_position">'+gzjl_position+'</td>'
		+'<td style="width:280px;text-align:center;" name="list_gzjl_remark">'+gzjl_remark+'</td>'
		+'</tr>';
    	$("#"+listId).append(h);
    	$("#gzjlAddDlg").modal('hide');
    	$("#gzjl_addForm").find("input[type=text]").val("");
    },
    
    updateGzjl:function(trid){
    	$("#gzjlMydlg").modal('show');
    	var gzjl_eduName = $("#"+trid).children("td[name=list_gzjl_eduName]").text();
    	var gzjl_startDate = $("#"+trid).children("td[name=list_gzjl_startDate]").text();
    	var gzjl_endDate = $("#"+trid).children("td[name=list_gzjl_endDate]").text();
    	var gzjl_position = $("#"+trid).children("td[name=list_gzjl_position]").text();
    	var gzjl_remark = $("#"+trid).children("td[name=list_gzjl_remark]").text();
    	
    	$("#gzjl_update_eduName").val(gzjl_eduName);
    	$("#gzjl_update_startDate").val(gzjl_startDate);
    	$("#gzjl_update_endDate").val(gzjl_endDate);
    	$("#gzjl_update_position").val(gzjl_position);
    	$("#gzjl_update_remark").val(gzjl_remark);
    	$("#gzjl_index_update").val(trid);
    	//alert($("#"+trid).html());
    },
    
    updateGzjlSave:function(){
    	var gzjl_eduName = $("#gzjl_update_eduName").val();
    	var gzjl_startDate = $("#gzjl_update_startDate").val();
    	var gzjl_endDate = $("#gzjl_update_endDate").val();
    	var gzjl_position = $("#gzjl_update_position").val();
    	var gzjl_remark = $("#gzjl_update_remark").val();

    	var trid = $("#gzjl_index_update").val();
    	if (gzjl_eduName == '' && gzjl_startDate == '' && gzjl_endDate == '' && gzjl_position=='' ){
				$.alert({
				title: '提示信息！',
				content: '未填写任何工作经历信息，请先填写再保存',
				type: 'red'
			});
			 return false;
		}
    	
    	$("#"+trid).children("td[name=list_gzjl_eduName]").text(gzjl_eduName);
    	$("#"+trid).children("td[name=list_gzjl_startDate]").text(gzjl_startDate);
    	$("#"+trid).children("td[name=list_gzjl_endDate]").text(gzjl_endDate);
    	$("#"+trid).children("td[name=list_gzjl_position]").text(gzjl_position);
    	$("#"+trid).children("td[name=list_gzjl_remark]").text(gzjl_remark);
    	
    	$("#gzjlMydlg").modal('hide');
    	$("#gzjl_updateForm").find("input[type=text]").val("");
    },
    
  //----------------------------------------------------------------------------------------培训经历
    addPxjl:function(listId){
    	var pxjl_eduName = $("#pxjl_eduName").val();
    	var pxjl_startDate = $("#pxjl_startDate").val();
    	var pxjl_endDate = $("#pxjl_endDate").val();
    	var pxjl_achievement = $("#pxjl_achievement").val();
    	var pxjl_remark = $("#pxjl_remark").val();
    	var pxjl_tr_index = Date.parse(new Date());
    	if (pxjl_eduName == '' && pxjl_startDate == '' && pxjl_endDate == '' && pxjl_achievement=='' ){
				$.alert({
				title: '提示信息！',
				content: '未填写任何培训经历信息，请先填写再保存',
				type: 'red'
			});
			 return false;
		}
    	var h = '<tr id="pxjl_tr_index_'+pxjl_tr_index+'">'
		+'<td style="width:120px;text-align:center;"><a href="javascript:void(0)" onclick="InsuranceSalesInfo.updatePxjl(\'pxjl_tr_index_'+pxjl_tr_index+'\''
		+')" style="color:blue">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" onclick="InsuranceSalesInfo.removeList(\'pxjl_tr_index_'+pxjl_tr_index+'\')" style="color:red">删除</a></td>'
		+'<td style="width:220px;text-align:center;" name="list_pxjl_eduName">'+pxjl_eduName+'</td>'
		+'<td style="width:120px;text-align:center;" name="list_pxjl_startDate">'+pxjl_startDate+'</td>'
		+'<td style="width:120px;text-align:center;" name="list_pxjl_endDate">'+pxjl_endDate+'</td>'
		+'<td style="width:120px;text-align:center;" name="list_pxjl_achievement">'+pxjl_achievement+'</td>'
		+'<td style="width:280px;text-align:center;" name="list_pxjl_remark">'+pxjl_remark+'</td>'
		+'</tr>';
    	$("#"+listId).append(h);
    	$("#pxjlAddDlg").modal('hide');
    	$("#pxjl_addForm").find("input[type=text]").val("");
    },
    
    updatePxjl:function(trid){
    	$("#pxjlMydlg").modal('show');
    	var pxjl_eduName = $("#"+trid).children("td[name=list_pxjl_eduName]").text();
    	var pxjl_startDate = $("#"+trid).children("td[name=list_pxjl_startDate]").text();
    	var pxjl_endDate = $("#"+trid).children("td[name=list_pxjl_endDate]").text();
    	var pxjl_achievement = $("#"+trid).children("td[name=list_pxjl_achievement]").text();
    	var pxjl_remark = $("#"+trid).children("td[name=list_pxjl_remark]").text();
    	
    	$("#pxjl_update_eduName").val(pxjl_eduName);
    	$("#pxjl_update_startDate").val(pxjl_startDate);
    	$("#pxjl_update_endDate").val(pxjl_endDate);
    	$("#pxjl_update_achievement").val(pxjl_achievement);
    	$("#pxjl_update_remark").val(pxjl_remark);
    	$("#pxjl_index_update").val(trid);
    	//alert($("#"+trid).html());
    },
    
    updatePxjlSave:function(){
    	var pxjl_eduName = $("#pxjl_update_eduName").val();
    	var pxjl_startDate = $("#pxjl_update_startDate").val();
    	var pxjl_endDate = $("#pxjl_update_endDate").val();
    	var pxjl_achievement = $("#pxjl_update_achievement").val();
    	var pxjl_remark = $("#pxjl_update_remark").val();

    	var trid = $("#pxjl_index_update").val();
    	if (pxjl_eduName == '' && pxjl_startDate == '' && pxjl_endDate == '' && pxjl_achievement=='' ){
				$.alert({
				title: '提示信息！',
				content: '未填写任何培训经历信息，请先填写再保存',
				type: 'red'
			});
			 return false;
		}
    	
    	$("#"+trid).children("td[name=list_pxjl_eduName]").text(pxjl_eduName);
    	$("#"+trid).children("td[name=list_pxjl_startDate]").text(pxjl_startDate);
    	$("#"+trid).children("td[name=list_pxjl_endDate]").text(pxjl_endDate);
    	$("#"+trid).children("td[name=list_pxjl_achievement]").text(pxjl_achievement);
    	$("#"+trid).children("td[name=list_pxjl_remark]").text(pxjl_remark);
    	
    	$("#pxjlMydlg").modal('hide');
    	$("#pxjl_updateForm").find("input[type=text]").val("");
    },   
    
  //----------------------------------------------------------------------------------------家庭成员
    addJtcy:function(listId){
    	var jtcy_personName = $("#jtcy_personName").val();
    	var jtcy_shipCellPhone = $("#jtcy_shipCellPhone").val();
    	var jtcy_relationShip = $("#jtcy_relationShip").val();
    	var jtcy_shipOccupation = $("#jtcy_shipOccupation").val();
    	var jtcy_remark = $("#jtcy_remark").val();
    	var jtcy_tr_index = Date.parse(new Date());
    	if (jtcy_personName == '' && jtcy_shipCellPhone == '' && jtcy_relationShip == '' && jtcy_shipOccupation=='' ){
				$.alert({
				title: '提示信息！',
				content: '未填写任何家庭成员信息，请先填写再保存',
				type: 'red'
			});
			 return false;
		}
    	var h = '<tr id="jtcy_tr_index_'+jtcy_tr_index+'">'
		+'<td style="width:120px;text-align:center;"><a href="javascript:void(0)" onclick="InsuranceSalesInfo.updateJtcy(\'jtcy_tr_index_'+jtcy_tr_index+'\''
		+')" style="color:blue">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" onclick="InsuranceSalesInfo.removeList(\'jtcy_tr_index_'+jtcy_tr_index+'\')" style="color:red">删除</a></td>'
		+'<td style="width:220px;text-align:center;" name="list_jtcy_personName">'+jtcy_personName+'</td>'
		+'<td style="width:120px;text-align:center;" name="list_jtcy_shipCellPhone">'+jtcy_shipCellPhone+'</td>'
		+'<td style="width:120px;text-align:center;" name="list_jtcy_relationShip">'+jtcy_relationShip+'</td>'
		+'<td style="width:120px;text-align:center;" name="list_jtcy_shipOccupation">'+jtcy_shipOccupation+'</td>'
		+'<td style="width:280px;text-align:center;" name="list_jtcy_remark">'+jtcy_remark+'</td>'
		+'</tr>';
    	$("#"+listId).append(h);
    	$("#jtcyAddDlg").modal('hide');
    	$("#jtcy_addForm").find("input[type=text]").val("");
    },
    
    updateJtcy:function(trid){
    	$("#jtcyMydlg").modal('show');
    	var jtcy_personName = $("#"+trid).children("td[name=list_jtcy_personName]").text();
    	var jtcy_shipCellPhone = $("#"+trid).children("td[name=list_jtcy_shipCellPhone]").text();
    	var jtcy_relationShip = $("#"+trid).children("td[name=list_jtcy_relationShip]").text();
    	var jtcy_shipOccupation = $("#"+trid).children("td[name=list_jtcy_shipOccupation]").text();
    	var jtcy_remark = $("#"+trid).children("td[name=list_jtcy_remark]").text();
    	$("#jtcy_update_personName").val(jtcy_personName);
    	$("#jtcy_update_shipCellPhone").val(jtcy_shipCellPhone);
    	$("#jtcy_update_relationShip").val(jtcy_relationShip);
    	$("#jtcy_update_shipOccupation").val(jtcy_shipOccupation);
    	$("#jtcy_update_remark").val(jtcy_remark);
    	$("#jtcy_index_update").val(trid);
    	//alert($("#"+trid).html());
    },
    
    updateJtcySave:function(){
    	var jtcy_personName = $("#jtcy_update_personName").val();
    	var jtcy_shipCellPhone = $("#jtcy_update_shipCellPhone").val();
    	var jtcy_relationShip = $("#jtcy_update_relationShip").val();
    	var jtcy_shipOccupation = $("#jtcy_update_shipOccupation").val();
    	var jtcy_remark = $("#jtcy_update_remark").val();

    	var trid = $("#jtcy_index_update").val();
    	if (jtcy_personName == '' && jtcy_shipCellPhone == '' && jtcy_relationShip == '' && jtcy_shipOccupation=='' ){
				$.alert({
				title: '提示信息！',
				content: '未填写任何家庭成员信息，请先填写再保存',
				type: 'red'
			});
			 return false;
		}
    	
    	$("#"+trid).children("td[name=list_jtcy_personName]").text(jtcy_personName);
    	$("#"+trid).children("td[name=list_jtcy_shipCellPhone]").text(jtcy_shipCellPhone);
    	$("#"+trid).children("td[name=list_jtcy_relationShip]").text(jtcy_relationShip);
    	$("#"+trid).children("td[name=list_jtcy_shipOccupation]").text(jtcy_shipOccupation);
    	$("#"+trid).children("td[name=list_jtcy_remark]").text(jtcy_remark);
    	
    	$("#jtcyMydlg").modal('hide');
    	$("#jtcy_updateForm").find("input[type=text]").val("");
    },  
       
    //----------------------------------------------------------------------------------------紧急联系人
    addJjlxr:function(listId){
    	var jjlxr_personName = $("#jjlxr_personName").val();
    	var jjlxr_shipCellPhone = $("#jjlxr_shipCellPhone").val();
    	var jjlxr_relationShip = $("#jjlxr_relationShip").val();
    	var jjlxr_shipAddr = $("#jjlxr_shipAddr").val();
    	var jjlxr_shipEmail = $("#jjlxr_shipEmail").val();
    	var jjlxr_remark = $("#jjlxr_remark").val();
    	var jjlxr_tr_index = Date.parse(new Date());
    	if (jjlxr_personName == '' && jjlxr_shipCellPhone == '' && jjlxr_relationShip == '' && jjlxr_shipAddr=='' && jjlxr_shipEmail=='' ){
				$.alert({
				title: '提示信息！',
				content: '未填写任何紧急联系人信息，请先填写再保存',
				type: 'red'
			});
			 return false;
		}
    	var h = '<tr id="jjlxr_tr_index_'+jjlxr_tr_index+'">'
		+'<td style="width:120px;text-align:center;"><a href="javascript:void(0)" onclick="InsuranceSalesInfo.updateJjlxr(\'jjlxr_tr_index_'+jjlxr_tr_index+'\''
		+')" style="color:blue">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" onclick="InsuranceSalesInfo.removeList(\'jjlxr_tr_index_'+jjlxr_tr_index+'\')" style="color:red">删除</a></td>'
		+'<td style="width:220px;text-align:center;" name="list_jjlxr_personName">'+jjlxr_personName+'</td>'
		+'<td style="width:120px;text-align:center;" name="list_jjlxr_shipCellPhone">'+jjlxr_shipCellPhone+'</td>'
		+'<td style="width:120px;text-align:center;" name="list_jjlxr_relationShip">'+jjlxr_relationShip+'</td>'
		+'<td style="width:220px;text-align:center;" name="list_jjlxr_shipAddr">'+jjlxr_shipAddr+'</td>'
		+'<td style="width:120px;text-align:center;" name="list_jjlxr_shipEmail">'+jjlxr_shipEmail+'</td>'
		+'<td style="width:280px;text-align:center;" name="list_jjlxr_remark">'+jjlxr_remark+'</td>'
		+'</tr>';
    	$("#"+listId).append(h);
    	$("#jjlxrAddDlg").modal('hide');
    	$("#jjlxr_addForm").find("input[type=text]").val("");
    },
    
    updateJjlxr:function(trid){
    	$("#jjlxrMydlg").modal('show');
    	var jjlxr_personName = $("#"+trid).children("td[name=list_jjlxr_personName]").text();
    	var jjlxr_shipCellPhone = $("#"+trid).children("td[name=list_jjlxr_shipCellPhone]").text();
    	var jjlxr_relationShip = $("#"+trid).children("td[name=list_jjlxr_relationShip]").text();
    	var jjlxr_shipAddr = $("#"+trid).children("td[name=list_jjlxr_shipAddr]").text();
    	var jjlxr_shipEmail = $("#"+trid).children("td[name=list_jjlxr_shipEmail]").text();
    	var jjlxr_remark = $("#"+trid).children("td[name=list_jjlxr_remark]").text();
    	
    	$("#jjlxr_update_personName").val(jjlxr_personName);
    	$("#jjlxr_update_shipCellPhone").val(jjlxr_shipCellPhone);
    	$("#jjlxr_update_relationShip").val(jjlxr_relationShip);
    	$("#jjlxr_update_shipAddr").val(jjlxr_shipAddr);
    	$("#jjlxr_update_shipEmail").val(jjlxr_shipEmail);
    	$("#jjlxr_update_remark").val(jjlxr_remark);
    	$("#jjlxr_index_update").val(trid);
    	//alert($("#"+trid).html());
    },
    
    updateJjlxrSave:function(){
    	var jjlxr_personName = $("#jjlxr_update_personName").val();
    	var jjlxr_shipCellPhone = $("#jjlxr_update_shipCellPhone").val();
    	var jjlxr_relationShip = $("#jjlxr_update_relationShip").val();
    	var jjlxr_shipAddr = $("#jjlxr_update_shipAddr").val();
    	var jjlxr_shipEmail = $("#jjlxr_update_shipEmail").val();
    	var jjlxr_remark = $("#jjlxr_update_remark").val();

    	var trid = $("#jjlxr_index_update").val();
    	if (jjlxr_personName == '' && jjlxr_shipCellPhone == '' && jjlxr_relationShip == '' && jjlxr_shipAddr=='' && jjlxr_shipEmail=='' ){
				$.alert({
				title: '提示信息！',
				content: '未填写任何紧急联系人信息，请先填写再保存',
				type: 'red'
			});
			 return false;
		}
    	
    	$("#"+trid).children("td[name=list_jjlxr_personName]").text(jjlxr_personName);
    	$("#"+trid).children("td[name=list_jjlxr_shipCellPhone]").text(jjlxr_shipCellPhone);
    	$("#"+trid).children("td[name=list_jjlxr_relationShip]").text(jjlxr_relationShip);
    	$("#"+trid).children("td[name=list_jjlxr_shipAddr]").text(jjlxr_shipAddr);
    	$("#"+trid).children("td[name=list_jjlxr_shipEmail]").text(jjlxr_shipEmail);
    	$("#"+trid).children("td[name=list_jjlxr_remark]").text(jjlxr_remark);
    	
    	$("#jjlxrMydlg").modal('hide');
    	$("#jjlxr_updateForm").find("input[type=text]").val("");
    },
    
    //----------------------------------------------------------------------------------------司外担保人
    addSwdbr:function(listId){
    	var swdbr_personName = $("#swdbr_personName").val();
    	var swdbr_shipCellPhone = $("#swdbr_shipCellPhone").val();
    	var swdbr_relationShip = $("#swdbr_relationShip").val();
    	var swdbr_idCard = $("#swdbr_idCard").val();
    	var swdbr_guaranteeDate = $("#swdbr_guaranteeDate").val();
    	var swdbr_tr_index = Date.parse(new Date());
    	if (swdbr_personName == '' && swdbr_shipCellPhone == '' && swdbr_relationShip == '' && swdbr_idCard=='' && swdbr_guaranteeDate=='' ){
				$.alert({
				title: '提示信息！',
				content: '未填写任何司外担保人信息，请先填写再保存',
				type: 'red'
			});
			 return false;
		}
    	var h = '<tr id="swdbr_tr_index_'+swdbr_tr_index+'">'
		+'<td style="width:120px;text-align:center;"><a href="javascript:void(0)" onclick="InsuranceSalesInfo.updateSwdbr(\'swdbr_tr_index_'+swdbr_tr_index+'\''
		+')" style="color:blue">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" onclick="InsuranceSalesInfo.removeList(\'swdbr_tr_index_'+swdbr_tr_index+'\')" style="color:red">删除</a></td>'
		+'<td style="width:220px;text-align:center;" name="list_swdbr_personName">'+swdbr_personName+'</td>'
		+'<td style="width:120px;text-align:center;" name="list_swdbr_shipCellPhone">'+swdbr_shipCellPhone+'</td>'
		+'<td style="width:120px;text-align:center;" name="list_swdbr_relationShip">'+swdbr_relationShip+'</td>'
		+'<td style="width:220px;text-align:center;" name="list_swdbr_idCard">'+swdbr_idCard+'</td>'
		+'<td style="width:120px;text-align:center;" name="list_swdbr_guaranteeDate">'+swdbr_guaranteeDate+'</td>'
		+'</tr>';
    	$("#"+listId).append(h);
    	$("#swdbrAddDlg").modal('hide');
    	$("#swdbr_addForm").find("input[type=text]").val("");
    },
    
    updateSwdbr:function(trid){
    	$("#swdbrMydlg").modal('show');
    	var swdbr_personName = $("#"+trid).children("td[name=list_swdbr_personName]").text();
    	var swdbr_shipCellPhone = $("#"+trid).children("td[name=list_swdbr_shipCellPhone]").text();
    	var swdbr_relationShip = $("#"+trid).children("td[name=list_swdbr_relationShip]").text();
    	var swdbr_idCard = $("#"+trid).children("td[name=list_swdbr_idCard]").text();
    	var swdbr_guaranteeDate = $("#"+trid).children("td[name=list_swdbr_guaranteeDate]").text();
    	
    	$("#swdbr_update_personName").val(swdbr_personName);
    	$("#swdbr_update_shipCellPhone").val(swdbr_shipCellPhone);
    	$("#swdbr_update_relationShip").val(swdbr_relationShip);
    	$("#swdbr_update_idCard").val(swdbr_idCard);
    	$("#swdbr_update_guaranteeDate").val(swdbr_guaranteeDate);
    	$("#swdbr_index_update").val(trid);
    	//alert($("#"+trid).html());
    },
    
    updateSwdbrSave:function(){
    	var swdbr_personName = $("#swdbr_update_personName").val();
    	var swdbr_shipCellPhone = $("#swdbr_update_shipCellPhone").val();
    	var swdbr_relationShip = $("#swdbr_update_relationShip").val();
    	var swdbr_idCard = $("#swdbr_update_idCard").val();
    	var swdbr_guaranteeDate = $("#swdbr_update_guaranteeDate").val();

    	var trid = $("#swdbr_index_update").val();
    	if (swdbr_personName == '' && swdbr_shipCellPhone == '' && swdbr_relationShip == '' && swdbr_idCard=='' && swdbr_guaranteeDate=='' ){
				$.alert({
				title: '提示信息！',
				content: '未填写任何司外担保人信息，请先填写再保存',
				type: 'red'
			});
			 return false;
		}
    	
    	$("#"+trid).children("td[name=list_swdbr_personName]").text(swdbr_personName);
    	$("#"+trid).children("td[name=list_swdbr_shipCellPhone]").text(swdbr_shipCellPhone);
    	$("#"+trid).children("td[name=list_swdbr_relationShip]").text(swdbr_relationShip);
    	$("#"+trid).children("td[name=list_swdbr_idCard]").text(swdbr_idCard);
    	$("#"+trid).children("td[name=list_swdbr_guaranteeDate]").text(swdbr_guaranteeDate);
    	
    	$("#swdbrMydlg").modal('hide');
    	$("#swdbr_updateForm").find("input[type=text]").val("");
    },  
    
    //----------------------------------------------------------------------------------------合同信息
    addHt:function(listId){
    	var ht_contractNo = $("#ht_contractNo").val();
    	var ht_contractType = $("#ht_contractType").val();
    	var ht_businessAgreementFlag = $("input[name='ht_businessAgreementFlag']:checked").val();
    	var ht_secretAgreementFlag = $("input[name='ht_secretAgreementFlag']:checked").val();
    	var ht_writeDate = $("#ht_writeDate").val();
    	var ht_probationEnd = $("#ht_probationEnd").val();
    	var ht_contractEffectDate = $("#ht_contractEffectDate").val();
    	var ht_contractStopDate = $("#ht_contractStopDate").val();    	
    	var ht_tr_index = Date.parse(new Date());
    	if (ht_contractNo == '' && ht_contractType == ''  && ht_writeDate=='' && ht_probationEnd =='' && ht_contractEffectDate =='' && ht_contractStopDate== '' ){
				$.alert({
				title: '提示信息！',
				content: '未填写任何合同信息，请先填写再保存',
				type: 'red'
			});
			 return false;
		}
    	if (ht_businessAgreementFlag == '0'){
    		ht_businessAgreementFlag = '是';
		}else if (ht_businessAgreementFlag == '1'){
    		ht_businessAgreementFlag = '否';
		}
		if (ht_secretAgreementFlag == '0'){
    		ht_secretAgreementFlag = '是';
		}else if (ht_secretAgreementFlag == '1'){
    		ht_secretAgreementFlag = '否';
		}
    	var h = '<tr id="ht_tr_index_'+ht_tr_index+'">'
		+'<td style="width:120px;text-align:center;"><a href="javascript:void(0)" onclick="InsuranceSalesInfo.updateHt(\'ht_tr_index_'+ht_tr_index+'\''
		+')" style="color:blue">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" onclick="InsuranceSalesInfo.removeList(\'ht_tr_index_'+ht_tr_index+'\')" style="color:red">删除</a></td>'
		+'<td style="width:220px;text-align:center;" name="list_ht_contractNo">'+ht_contractNo+'</td>'
		+'<td style="width:120px;text-align:center;" name="list_ht_contractType">'+ht_contractType+'</td>'
		+'<td style="width:120px;text-align:center;" name="list_ht_businessAgreementFlag">'+ht_businessAgreementFlag+'</td>'
		+'<td style="width:120px;text-align:center;" name="list_ht_secretAgreementFlag">'+ht_secretAgreementFlag+'</td>'
		+'<td style="width:120px;text-align:center;" name="list_ht_writeDate">'+ht_writeDate+'</td>'
		+'<td style="width:120px;text-align:center;" name="list_ht_probationEnd">'+ht_probationEnd+'</td>'
		+'<td style="width:120px;text-align:center;" name="list_ht_contractEffectDate">'+ht_contractEffectDate+'</td>'
		+'<td style="width:120px;text-align:center;" name="list_ht_contractStopDate">'+ht_contractStopDate+'</td>'
		+'</tr>';
    	$("#"+listId).append(h);
    	$("#htAddDlg").modal('hide');
    	$("#ht_addForm").find("input[type=text]").val("");
    },
    
    updateHt:function(trid){
    	$("#htMydlg").modal('show');
    	var ht_contractNo = $("#"+trid).children("td[name=list_ht_contractNo]").text();
    	var ht_contractType = $("#"+trid).children("td[name=list_ht_contractType]").text();
    	var ht_businessAgreementFlag = $("#"+trid).children("td[name=list_ht_businessAgreementFlag]").text();
    	var ht_secretAgreementFlag = $("#"+trid).children("td[name=list_ht_secretAgreementFlag]").text();
    	var ht_writeDate = $("#"+trid).children("td[name=list_ht_writeDate]").text();
    	var ht_probationEnd = $("#"+trid).children("td[name=list_ht_probationEnd]").text();
    	var ht_contractEffectDate = $("#"+trid).children("td[name=list_ht_contractEffectDate]").text();
    	var ht_contractStopDate = $("#"+trid).children("td[name=list_ht_contractStopDate]").text();
    	if (ht_businessAgreementFlag == '是'){
    		ht_businessAgreementFlag = '0';
		}else if (ht_businessAgreementFlag == '否'){
    		ht_businessAgreementFlag = '1';
		}
		if (ht_secretAgreementFlag == '是'){
    		ht_secretAgreementFlag = '0';
		}else if (ht_secretAgreementFlag == '否'){
    		ht_secretAgreementFlag = '1';
		}
    	$("#ht_update_contractNo").val(ht_contractNo);
    	$("#ht_update_contractType").val(ht_contractType);
    	$("input[name='ht_update_businessAgreementFlag'][value='"+ht_businessAgreementFlag+"']").prop("checked", "checked");
    	$("input[name='ht_update_secretAgreementFlag'][value='"+ht_secretAgreementFlag+"']").prop("checked", "checked");    	
    	$("#ht_update_writeDate").val(ht_writeDate);
    	$("#ht_update_probationEnd").val(ht_probationEnd);
    	$("#ht_update_contractEffectDate").val(ht_contractEffectDate);
    	$("#ht_update_contractStopDate").val(ht_contractStopDate);    	
    	$("#ht_index_update").val(trid);
    	//alert($("#"+trid).html());
    },
    
    updateHtSave:function(){
    	var ht_contractNo = $("#ht_update_contractNo").val();
    	var ht_contractType = $("#ht_update_contractType").val();
    	var ht_businessAgreementFlag = $("input[name='ht_update_businessAgreementFlag']:checked").val();    	
    	var ht_secretAgreementFlag = $("input[name='ht_update_secretAgreementFlag']:checked").val();    	
    	var ht_writeDate = $("#ht_update_writeDate").val();
    	var ht_probationEnd = $("#ht_update_probationEnd").val();
    	var ht_contractEffectDate = $("#ht_update_contractEffectDate").val();
    	var ht_contractStopDate = $("#ht_update_contractStopDate").val();    	

    	var trid = $("#ht_index_update").val();
    	if (ht_contractNo == '' && ht_contractType == ''  && ht_writeDate=='' && ht_probationEnd =='' && ht_contractEffectDate =='' && ht_contractStopDate== '' ){
				$.alert({
				title: '提示信息！',
				content: '未填写任何合同信息，请先填写再保存',
				type: 'red'
			});
			 return false;
		}
    	if (ht_businessAgreementFlag == '0'){
    		ht_businessAgreementFlag = '是';
		}else if (ht_businessAgreementFlag == '1'){
    		ht_businessAgreementFlag = '否';
		}
		if (ht_secretAgreementFlag == '0'){
    		ht_secretAgreementFlag = '是';
		}else if (ht_secretAgreementFlag == '1'){
    		ht_secretAgreementFlag = '否';
		}
    	
    	$("#"+trid).children("td[name=list_ht_contractNo]").text(ht_contractNo);
    	$("#"+trid).children("td[name=list_ht_contractType]").text(ht_contractType);
    	$("#"+trid).children("td[name=list_ht_businessAgreementFlag]").text(ht_businessAgreementFlag);   	
    	$("#"+trid).children("td[name=list_ht_secretAgreementFlag]").text(ht_secretAgreementFlag);    	
    	$("#"+trid).children("td[name=list_ht_writeDate]").text(ht_writeDate);
    	$("#"+trid).children("td[name=list_ht_probationEnd]").text(ht_probationEnd);
    	$("#"+trid).children("td[name=list_ht_contractEffectDate]").text(ht_contractEffectDate);
    	$("#"+trid).children("td[name=list_ht_contractStopDate]").text(ht_contractStopDate);
    	
    	$("#htMydlg").modal('hide');
    	$("#ht_updateForm").find("input[type=text]").val("");
    },      
    
    //----------------------------------------------------------------------------------------总监津贴
    addZjjt:function(){
    	var zjjt_tr_index = Date.parse(new Date());
    	var h = '<tr id="zjjt_tr_index_'+zjjt_tr_index+'">'
		+'<td style="width:120px;text-align:center;"><a href="javascript:void(0)" onclick="InsuranceSalesInfo.removeList(\'zjjt_tr_index_'+zjjt_tr_index+'\')" style="color:red">删除</a></td>'
		+'<td style="width:140px;text-align:center;">总价值佣金FYC(元)</td>'
		+'<td style="width:470px;text-align:center;">'
		+'<input id="maxFYC" style="width:70px;" type="number" value="">&nbsp;元&nbsp;'
		+'<select id="maxFYC_t" style="width:50px"><option>></option><option>=</option><option>>=</option></select>&nbsp;FYC&nbsp;'
		+'<select id="minFYC_t" style="width:50px"><option>></option><option>=</option><option>>=</option></select>&nbsp;' 
		+'<input id="minFYC" style="width:70px;" type="number" value="">&nbsp;元</td>'
		+'<td style="width:120px;text-align:center;"><input id="jtRate" style="width:80px;" type="number" value="">%</td>';
    	$("#zjjt_list").append(h);
    },
    
    
    //关闭模态框
    closeDlg:function () {
        $("#zcAddDlg").modal('hide');
        $("#zcMydlg").modal('hide');
        $("#zhengshuAddDlg").modal('hide');
        $("#zhengshuMydlg").modal('hide');
        $("#jyjlAddDlg").modal('hide');
        $("#jyjlMydlg").modal('hide'); 
        $("#gzjlAddDlg").modal('hide');
        $("#gzjlMydlg").modal('hide'); 
        $("#pxjlAddDlg").modal('hide');
        $("#pxjlMydlg").modal('hide');   
        $("#jtcyAddDlg").modal('hide');
        $("#jtcyMydlg").modal('hide'); 
        $("#jjlxrAddDlg").modal('hide');
        $("#jjlxrMydlg").modal('hide'); 
        $("#swdbrAddDlg").modal('hide');
        $("#swdbrMydlg").modal('hide');         
        $("#htAddDlg").modal('hide');
        $("#htMydlg").modal('hide'); 
        document.getElementById("saveButton").removeAttribute("disabled");         
    },
    
	}
}();

// //证件类型change事件
// $("#cardType").on("change", function(){
// 	debugger;
// 	var cardType = $("#cardType option:selected").attr("myvalue");
// 	// if(cardType=='1' || cardType=='5'){
// 	// 	$("#sex").attr("disabled",true);
// 	// 	$("#birthday").attr("disabled",true);
// 	// }else {
// 	// 	$("#sex").attr("disabled", false);
// 	// 	$("#birthday").attr("disabled", false);
// 	// }
// });
//证件号blur事件
$("#cardNo").blur(function () {
	var cardNo = $(this).val();
	var cardType = $("#cardType option:selected").attr("myvalue");
	if(cardType=='1' || cardType=='5'){

		var res = transformIdentityCard(cardNo, birthday, sex);
		var birthday = res.birthday;
		var sex = res.sex;
		if(sex == '0' || sex == '1' ){
			$("#sex option[value='"+ sex +"']").attr("selected", false);
			$("#sex option[value='"+ sex +"']").attr("selected", true);
		}
		$("#birthday").val(birthday);
	}
});

function transformIdentityCard (value) {
	var res = {};
	var birthday = "";
	var sex = "";

	// sex 0未知  1男  2女
	if (value.length === 15) {
		birthday = `19${value.substr(6, 2)}-${value.substr(8, 2)}-${value.substr(10, 2)}`
		sex = (value.substr(14, 1) % 2)
	}
	if (value.length === 18) {
		debugger;
		birthday = `${value.substr(6, 4)}-${value.substr(10, 2)}-${value.substr(12, 2)}`
		sex = (value.substr(16, 1) % 2)
	}
	res.birthday = birthday;
	res.sex = sex;
	return res;
}
