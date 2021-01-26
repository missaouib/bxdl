var isLook = $("#isLook").val();
$(function(){
	
	if(isLook =='yes'){
		disBasic();
	}
	
    $('#addSubsidyWay').on(  
	    "change",function(){
	    	 var addSubsidyWay = $(this).val();
	    	 if(addSubsidyWay=="2"){
	    		 $("#thrMonthyBasic_div").show();
	    		 $("#sixMonthyBasic_div").hide();
	    		 $("#twnMonthyBasic_div").hide();
	    	 }else if(addSubsidyWay=="3"){
	    		 $("#thrMonthyBasic_div").hide();
	    		 $("#sixMonthyBasic_div").show();
	    		 $("#twnMonthyBasic_div").hide();
	    	 }else if(addSubsidyWay=="4"){
	    		 $("#thrMonthyBasic_div").hide();
	    		 $("#sixMonthyBasic_div").hide();
	    		 $("#twnMonthyBasic_div").show();
	    	 }else{
	    		 $("#thrMonthyBasic_div").hide();
	    		 $("#sixMonthyBasic_div").hide();
	    		 $("#twnMonthyBasic_div").hide();	    		 
	    	 }
	    }
    )
    
    $('input[type=radio][name=periodRatioShow]').on(  
	    "change",function(){
	    	 var periodRatioShow = $("input[name='periodRatioShow']:checked").val();
	    	 if(periodRatioShow=="0"){
	    		 $("#nq-div").show();
	    	 }else{
	    		 $("#nq-div").hide();
	    	 }
	    }
    )
    
    setTimeout(function(){
    	 /*年期换算系数*/
    	 nq_back();
    	 /*月结算规则*/
    	 backshowData("monthyBasic","monthyBasic_list"); 
    	 /*通算规则*/
	     var addSubsidyWay = $("#addSubsidyWay").val();
	   	 if(addSubsidyWay=="2"){
	   		 $("#thrMonthyBasic_div").show();
	   		 $("#sixMonthyBasic_div").hide();
	   		 $("#twnMonthyBasic_div").hide();
	   		 backshowData("thrMonthyBasic","thrMonthyBasic_list");
	   	 }else if(addSubsidyWay=="3"){
	   		 $("#thrMonthyBasic_div").hide();
	   		 $("#sixMonthyBasic_div").show();
	   		 $("#twnMonthyBasic_div").hide();
	   		 backshowData("sixMonthyBasic","sixMonthyBasic_list");
	   	 }else if(addSubsidyWay=="4"){
	   		 $("#thrMonthyBasic_div").hide();
	   		 $("#sixMonthyBasic_div").hide();
	   		 $("#twnMonthyBasic_div").show();
	   		 backshowData("twnMonthyBasic","twnMonthyBasic_list");
	   	 }else{
	   		 $("#thrMonthyBasic_div").hide();
	   		 $("#sixMonthyBasic_div").hide();
	   		 $("#twnMonthyBasic_div").hide();	    		 
	   	 } 
	   	 /*例外产品*/
	   	 backOutPro();	   	 
    },300)
	
})

//返回列表
function backToIPList(){
	commCloseTab('persistencyBonus:edit');
//	createAddProcessTab('lifeProtocol:list','');
	createAddProcessTab('lifeProtocolBasic:add',$('#persistencyBounds_look_protocolId').val());
}

function toUpdExitPro(exitProductId){
	var protocolId = $('#protocolId').val();
	var id = protocolId+"_"+exitProductId;
	if(isLook =='yes'){
		id += "_look";
	}
	
	commCloseTab('persistencyBonus:edit');
	createAddProcessTab('persistencyBonus:ExitProUpd',id);
}

//新增例外产品
function exitProAdd(){
	commCloseTab('persistencyBonus:edit');
	createAddProcessTab('persistencyBonus:exitPro',$('#protocolId').val());
}

function disBasic() {
	var nodeList = document.getElementsByTagName("input");
	for (var i = 0; i < nodeList.length; i++) {
		nodeList[i].disabled = true;
	}
	nodeList = document.getElementsByTagName("select");
	for (var i = 0; i < nodeList.length; i++) {
		nodeList[i].disabled = true;
	}
}

//1.打开协议信息
function openAddProtocolBasic(){
	commCloseTab('persistencyBonus:edit');
	createAddProcessTab('lifeProtocolBasic:add',$('#protocolId').val());
}

//2.1打开手续费费
function openAddProtocolService(){
	commCloseTab('persistencyBonus:edit');
	createAddProcessTab('lifeProtocolService:add',$('#protocolId').val());
}
//3.打开续年度服务津贴
function openAddProtocolSubsidy(){
	commCloseTab('persistencyBonus:edit');
	createAddProcessTab('lifeProtocolSubsidy:add',$('#protocolId').val());
}

//5.费率调节
function openPersistencyBonus(){
}

//6.打开业务推动
function openProtocolPromotion(){
	commCloseTab('persistencyBonus:edit');
	createAddProcessTab('protocolPromotion:list',$('#protocolId').val());
}

function nq_back(){
	var backValues = $("#periodRatio").val();
	var backVsAs = JSON.parse(backValues);
	var ptrs = $("#nq-table").find("tr").length;
	//alert(ptrs);
	for(var i=1 ; i< ptrs ; i++){
		var ptds = $("#nq-table tr:eq("+i+")").find("td").length;
		for(var j=1;j<ptds;j++){
			$("#nq-table tr:eq("+i+") td:eq("+j+")").find("input").val(backVsAs[i-1][j-1]);
		}
	}	
}

function backOutPro(){
	var backPros = JSON.parse($("#outProducts").val());
	var valueId = "outProducts";
	for(var i=0;i<backPros.length;i++){
		var time_index = (Date.parse(new Date()))+''+backPros[i].PRODUCTS_RATIO_ID;
    	var h = '<tr id="'+valueId+'_tr_index_'+time_index+'">';
    	if(isLook =='yes'){
    		h = h +'<td style="width:120px;text-align:center;"><a href="javascript:return false;" style="color:red">删除</a>'
    		+'&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" onclick="toUpdExitPro(\''+backPros[i].PRODUCTS_RATIO_ID+'\')" style="color:blue">配置</a></td>';
    	} else {
    		+'<td style="width:120px;text-align:center;"><a href="javascript:void(0)" onclick="deletePbrByPro(\''+valueId+'_tr_index_'+time_index+'\',\''+backPros[i].PRODUCTS_RATIO_ID+'\')" style="color:red">删除</a>'
    		+'&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" onclick="toUpdExitPro(\''+backPros[i].PRODUCTS_RATIO_ID+'\')" style="color:blue">配置</a></td>';
    	}
		h = h +'<td style="width:140px;text-align:center;"><input type="hidden" id="exitProductId" name="exitProductId" value="'+backPros[i].PRODUCTS_RATIO_ID+'" />'+backPros[i].PRODUCTS_NAME+'</td>'
		+'<td style="width:140px;text-align:center;">'+backPros[i].PRODUCTS_CODE+'</td>'
		+'<td style="width:140px;text-align:center;">'+backPros[i].OUT_STANDARD_COMMISSION_COEFFICIENT+'</td>';
    	$("#outPro_list").append(h);		
	}
	if(isLook =='yes'){
		disBasic();
	}
}

function deletePbrByPro(trId,exitProductId){
	$.confirm({
        title: '提示信息!',
        content: '您确定要移除该例外产品的配置信息吗？',
        type: 'blue',
        typeAnimated: true,
        buttons: {
        	确定: {
	        	action: function(){	
					var persistencyBonusId = $("#bonusId").val();
					$.ajax({
				        url:'persistencyBonus/deletePbrByProId',
				        dataType:'json',
				        type:'post',
				        data:{exitProductId:exitProductId,persistencyBonusId:persistencyBonusId},
				        success:function(data){
				        	 persistencyBonus.removeList(trId);
					       	 $.alert({
					             title: '提示信息！',
					             content: '移除成功!',
					             type: 'blue'
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

function backshowData(valueId,tableId){
	var backValues = $("#"+valueId).val();
	var backVsAs = JSON.parse(backValues);
	for(var i=0;i<backVsAs.length;i++){
		var computeFormula = backVsAs[i].computeFormula;
		var max_min = (computeFormula.replace('>','').replace('=','')).replace('>','').replace('=','').split('继续率');
		var max_min_length = computeFormula.split(">=").length-1;
		var max_min_all = computeFormula.split('继续率');
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
		var maxPbr_thtml = "<option>></option><option>=</option><option>>=</option>"; 
		if(max_t==">"){maxPbr_thtml = "<option selected='selected'>></option><option>=</option><option>>=</option>"}
		if(max_t==">="){maxPbr_thtml = "<option>></option><option>=</option><option selected='selected'>>=</option>"}
		if(max_t=="="){maxPbr_thtml = "<option>></option><option selected='selected'>=</option><option>>=</option>"}		        		
		var minPbr_thtml = "<option>></option><option>=</option><option>>=</option>"; 
		if(min_t==">"){minPbr_thtml = "<option selected='selected'>></option><option>=</option><option>>=</option>"}
		if(min_t==">="){minPbr_thtml = "<option>></option><option>=</option><option selected='selected'>>=</option>"}
		if(min_t=="="){minPbr_thtml = "<option>></option><option selected='selected'>=</option><option>>=</option>"}
		var monthly_thtml = '<option value="13">13</option><option value="25">25</option><option value="37">37</option><option value="49">49</option><option value="61">61</option></select></td>';
		if(backVsAs[i].monthly=="13")
			monthly_thtml = '<option value="13" selected="selected">13</option><option value="25">25</option><option value="37">37</option><option value="49">49</option><option value="61">61</option></select></td>';
		if(backVsAs[i].monthly=="25")
			monthly_thtml = '<option value="13">13</option><option value="25" selected="selected">25</option><option value="37">37</option><option value="49">49</option><option value="61">61</option></select></td>';
		if(backVsAs[i].monthly=="37")
			monthly_thtml = '<option value="13">13</option><option value="25">25</option><option value="37" selected="selected">37</option><option value="49">49</option><option value="61">61</option></select></td>';
		if(backVsAs[i].monthly=="49")
			monthly_thtml = '<option value="13">13</option><option value="25">25</option><option value="37">37</option><option value="49" selected="selected">49</option><option value="61">61</option></select></td>';
		if(backVsAs[i].monthly=="61")	
			monthly_thtml = '<option value="13">13</option><option value="25">25</option><option value="37">37</option><option value="49">49</option><option value="61" selected="selected">61</option></select></td>';		
		var max = max_min[0];
		var min = max_min[1];
    	var time_index = (Date.parse(new Date()))+''+backVsAs[i].ruleId;
    	var h = '<tr id="'+valueId+'_tr_index_'+time_index+'">';
    	if(isLook =='yes'){
    		h = h +'<td style="width:120px;text-align:center;"><a href="javascript:return false;" style="color:red">删除</a></td>';
    	} else {
    		h = h +'<td style="width:120px;text-align:center;"><a href="javascript:void(0)" onclick="persistencyBonus.removeList(\''+valueId+'_tr_index_'+time_index+'\')" style="color:red">删除</a></td>';
    	}
    	h = h +'<td style="width:140px;text-align:center;"><select id="monthly"><option value="">请选择</option>'+monthly_thtml+'</td>'
		+'<td style="width:470px;text-align:center;">'
		+'<input type="hidden" id="ruleId" style="width:70px;" value="'+backVsAs[i].ruleId+'">'
		+'<input id="maxRatio" style="width:70px;" type="text" value="'+max+'">&nbsp;%&nbsp;'
		+'<select id="maxRatio_t" style="width:50px">'+maxPbr_thtml
		+'</select>&nbsp;继续率&nbsp;'
		+'<select id="minRatio_t" style="width:50px">'+minPbr_thtml+'</select>&nbsp;' 
		+'<input id="minRatio" style="width:70px;" type="text" value="'+min+'">&nbsp;%</td>'
		+'<td style="width:120px;text-align:center;"><input id="bonusRatio" style="width:80px;" type="text" value="'+backVsAs[i].bonusRatio+'"></td>';
    	$("#"+tableId).append(h);        	
	}
	if(isLook =='yes'){
		disBasic();
	}
}

function setDatas(){	
	/*获取年期系数*/
	var periodRatio = [];
	var ptrs = $("#nq-table").find("tr").length;
	//alert(ptrs);
	for(var i=1 ; i< ptrs ; i++){
		periodRatio[i-1] = [];
		var ptds = $("#nq-table tr:eq("+i+")").find("td").length;
		for(var j=1;j<ptds;j++){
			periodRatio[i-1][j-1] = $("#nq-table tr:eq("+i+") td:eq("+j+")").find("input").val();
		}
	}
	//alert(periodRatio.length);
	$("#periodRatio").val(JSON.stringify(periodRatio));	
	/*获取月继续率规则*/
	var monthyBasic = [];
	var monthyBasicLength = $("#monthyBasic_list").find("tr").length;
	for(var i=1 ; i< monthyBasicLength ; i++){
		var mbObj = {};
		mbObj.persistencyBonusId = $("#bonusId").val();
		mbObj.basicFlag = "0";
		mbObj.computeType = "1";
		mbObj.ruleId = $("#monthyBasic_list tr:eq("+i+")").find("#ruleId").val();
		mbObj.monthly = $("#monthyBasic_list tr:eq("+i+")").find("#monthly").val();
		var maxRatio = $("#monthyBasic_list tr:eq("+i+")").find("#maxRatio").val();
		var maxRatio_t = $("#monthyBasic_list tr:eq("+i+")").find("#maxRatio_t").val();
		var minRatio = $("#monthyBasic_list tr:eq("+i+")").find("#minRatio").val();
		var minRatio_t = $("#monthyBasic_list tr:eq("+i+")").find("#minRatio_t").val();
		mbObj.computeFormula = (maxRatio + maxRatio_t +"继续率"+ minRatio_t + minRatio).replace(/>/g,"&gt;");
		mbObj.bonusRatio = $("#monthyBasic_list tr:eq("+i+")").find("#bonusRatio").val();
		monthyBasic.push(mbObj);
	}
	console.log(monthyBasic);//---------
	$("#monthyBasic").val(JSON.stringify(monthyBasic));		
	/*获取通算规则*/	
	if($("#addSubsidyWay").val()=="2"){
		var thrMonthyBasic = [];
		var thrMonthyBasicLength = $("#thrMonthyBasic_list").find("tr").length;
		for(var i=1 ; i< thrMonthyBasicLength ; i++){
			var mbObj = {};
			mbObj.persistencyBonusId = $("#bonusId").val();
			mbObj.basicFlag = "0";
			mbObj.computeType = "2";
			mbObj.ruleId = $("#thrMonthyBasic_list tr:eq("+i+")").find("#ruleId").val();
			mbObj.monthly = $("#thrMonthyBasic_list tr:eq("+i+")").find("#monthly").val();
			var maxRatio = $("#thrMonthyBasic_list tr:eq("+i+")").find("#maxRatio").val();
			var maxRatio_t = $("#thrMonthyBasic_list tr:eq("+i+")").find("#maxRatio_t").val();
			var minRatio = $("#thrMonthyBasic_list tr:eq("+i+")").find("#minRatio").val();
			var minRatio_t = $("#thrMonthyBasic_list tr:eq("+i+")").find("#minRatio_t").val();
			mbObj.computeFormula = (maxRatio + maxRatio_t +"继续率"+ minRatio_t + minRatio).replace(/>/g,"&gt;");
			mbObj.bonusRatio = $("#thrMonthyBasic_list tr:eq("+i+")").find("#bonusRatio").val();
			thrMonthyBasic.push(mbObj);
		}
		console.log(thrMonthyBasic);//---------
		$("#thrMonthyBasic").val(JSON.stringify(thrMonthyBasic));
		$("#sixMonthyBasic").val("");
		$("#twnMonthyBasic").val("");
	}else if($("#addSubsidyWay").val()=="3"){
		var sixMonthyBasic = [];
		var sixMonthyBasicLength = $("#sixMonthyBasic_list").find("tr").length;
		for(var i=1 ; i< sixMonthyBasicLength ; i++){
			var mbObj = {};
			mbObj.persistencyBonusId = $("#bonusId").val();
			mbObj.basicFlag = "0";
			mbObj.computeType = "3";
			mbObj.ruleId = $("#sixMonthyBasicLength_list tr:eq("+i+")").find("#ruleId").val();
			mbObj.monthly = $("#sixMonthyBasicLength_list tr:eq("+i+")").find("#monthly").val();
			var maxRatio = $("#sixMonthyBasicLength_list tr:eq("+i+")").find("#maxRatio").val();
			var maxRatio_t = $("#sixMonthyBasicLength_list tr:eq("+i+")").find("#maxRatio_t").val();
			var minRatio = $("#sixMonthyBasicLength_list tr:eq("+i+")").find("#minRatio").val();
			var minRatio_t = $("#sixMonthyBasicLength_list tr:eq("+i+")").find("#minRatio_t").val();
			mbObj.computeFormula = (maxRatio + maxRatio_t +"继续率"+ minRatio_t + minRatio).replace(/>/g,"&gt;");
			mbObj.bonusRatio = $("#sixMonthyBasicLength_list tr:eq("+i+")").find("#bonusRatio").val();
			sixMonthyBasic.push(mbObj);
		}
		console.log(sixMonthyBasic);//---------
		$("#sixMonthyBasic").val(JSON.stringify(sixMonthyBasic));
		$("#thrMonthyBasic").val("");
		$("#twnMonthyBasic").val("");
	}else if($("#addSubsidyWay").val()=="4"){
		var twnMonthyBasic = [];
		var twnMonthyBasicLength = $("#twnMonthyBasic_list").find("tr").length;
		for(var i=1 ; i< twnMonthyBasicLength ; i++){
			var mbObj = {};
			mbObj.persistencyBonusId = $("#bonusId").val();
			mbObj.basicFlag = "0";
			mbObj.computeType = "4";
			mbObj.ruleId = $("#twnMonthyBasic_list tr:eq("+i+")").find("#ruleId").val();
			mbObj.monthly = $("#twnMonthyBasic_list tr:eq("+i+")").find("#monthly").val();
			var maxRatio = $("#twnMonthyBasic_list tr:eq("+i+")").find("#maxRatio").val();
			var maxRatio_t = $("#twnMonthyBasic_list tr:eq("+i+")").find("#maxRatio_t").val();
			var minRatio = $("#twnMonthyBasic_list tr:eq("+i+")").find("#minRatio").val();
			var minRatio_t = $("#twnMonthyBasic_list tr:eq("+i+")").find("#minRatio_t").val();
			mbObj.computeFormula = (maxRatio + maxRatio_t +"继续率"+ minRatio_t + minRatio).replace(/>/g,"&gt;");
			mbObj.bonusRatio = $("#twnMonthyBasic_list tr:eq("+i+")").find("#bonusRatio").val();
			twnMonthyBasic.push(mbObj);
		}
		console.log(twnMonthyBasic);//---------
		$("#twnMonthyBasic").val(JSON.stringify(twnMonthyBasic));	
		$("#thrMonthyBasic").val("");
		$("#sixMonthyBasic").val("");		
	}
}

var persistencyBonus = function () {
	return{
		add_monthyBasic:function(){
        	var monthyBasic_tr_index = Date.parse(new Date());
        	var h = '<tr id="monthyBasic_tr_index_'+monthyBasic_tr_index+'">'
    		+'<td style="width:120px;text-align:center;"><a href="javascript:void(0)" onclick="persistencyBonus.removeList(\'monthyBasic_tr_index_'+monthyBasic_tr_index+'\')" style="color:red">删除</a></td>'
    		+'<td style="width:140px;text-align:center;"><select id="monthly"><option value="">请选择</option>'
    		+'<option value="13">13</option><option value="25">25</option><option value="37">37</option>'
    		+'<option value="49">49</option><option value="61">61</option></select></td><td style="width:470px;text-align:center;">'
    		+'<input id="maxRatio" style="width:70px;" type="text" value="">&nbsp;%&nbsp;'
    		+'<select id="maxRatio_t" style="width:50px"><option>></option><option>=</option><option>>=</option></select>&nbsp;继续率&nbsp;'
    		+'<select id="minRatio_t" style="width:50px"><option>></option><option>=</option><option>>=</option></select>&nbsp;' 
    		+'<input id="minRatio" style="width:70px;" type="text" value="">&nbsp;%</td>'
    		+'<td style="width:120px;text-align:center;"><input id="bonusRatio" style="width:80px;" type="text" value=""></td>';
        	$("#monthyBasic_list").append(h);
        },
        
		add_thrMonthyBasic:function(){
        	var thrMonthyBasic_tr_index = Date.parse(new Date());
        	var h = '<tr id="thrMonthyBasic_tr_index_'+thrMonthyBasic_tr_index+'">'
    		+'<td style="width:120px;text-align:center;"><a href="javascript:void(0)" onclick="persistencyBonus.removeList(\'thrMonthyBasic_tr_index_'+thrMonthyBasic_tr_index+'\')" style="color:red">删除</a></td>'
    		+'<td style="width:140px;text-align:center;"><select id="monthly"><option value="">请选择</option>'
    		+'<option value="13">13</option><option value="25">25</option><option value="37">37</option>'
    		+'<option value="49">49</option><option value="61">61</option></select></td><td style="width:470px;text-align:center;">'
    		+'<input id="maxRatio" style="width:70px;" type="text" value="">&nbsp;%&nbsp;'
    		+'<select id="maxRatio_t" style="width:50px"><option>></option><option>=</option><option>>=</option></select>&nbsp;继续率&nbsp;'
    		+'<select id="minRatio_t" style="width:50px"><option>></option><option>=</option><option>>=</option></select>&nbsp;' 
    		+'<input id="minRatio" style="width:70px;" type="text" value="">&nbsp;%</td>'
    		+'<td style="width:120px;text-align:center;"><input id="bonusRatio" style="width:80px;" type="text" value=""></td>';
        	$("#thrMonthyBasic_list").append(h);
        },
        
		add_sixMonthyBasic:function(){
        	var sixMonthyBasic_tr_index = Date.parse(new Date());
        	var h = '<tr id="sixMonthyBasic_tr_index_'+sixMonthyBasic_tr_index+'">'
    		+'<td style="width:120px;text-align:center;"><a href="javascript:void(0)" onclick="persistencyBonus.removeList(\'sixMonthyBasic_tr_index_'+sixMonthyBasic_tr_index+'\')" style="color:red">删除</a></td>'
    		+'<td style="width:140px;text-align:center;"><select id="monthly"><option value="">请选择</option>'
    		+'<option value="13">13</option><option value="25">25</option><option value="37">37</option>'
    		+'<option value="49">49</option><option value="61">61</option></select></td><td style="width:470px;text-align:center;">'
    		+'<input id="maxRatio" style="width:70px;" type="text" value="">&nbsp;%&nbsp;'
    		+'<select id="maxRatio_t" style="width:50px"><option>></option><option>=</option><option>>=</option></select>&nbsp;继续率&nbsp;'
    		+'<select id="minRatio_t" style="width:50px"><option>></option><option>=</option><option>>=</option></select>&nbsp;' 
    		+'<input id="minRatio" style="width:70px;" type="text" value="">&nbsp;%</td>'
    		+'<td style="width:120px;text-align:center;"><input id="bonusRatio" style="width:80px;" type="text" value=""></td>';
        	$("#sixMonthyBasic_list").append(h);
        },
        
		add_twnMonthyBasic:function(){
        	var twnMonthyBasic_tr_index = Date.parse(new Date());
        	var h = '<tr id="twnMonthyBasic_tr_index_'+twnMonthyBasic_tr_index+'">'
    		+'<td style="width:120px;text-align:center;"><a href="javascript:void(0)" onclick="persistencyBonus.removeList(\'twnMonthyBasic_tr_index_'+twnMonthyBasic_tr_index+'\')" style="color:red">删除</a></td>'
    		+'<td style="width:140px;text-align:center;"><select id="monthly"><option value="">请选择</option>'
    		+'<option value="13">13</option><option value="25">25</option><option value="37">37</option>'
    		+'<option value="49">49</option><option value="61">61</option></select></td><td style="width:470px;text-align:center;">'
    		+'<input id="maxRatio" style="width:70px;" type="text" value="">&nbsp;%&nbsp;'
    		+'<select id="maxRatio_t" style="width:50px"><option>></option><option>=</option><option>>=</option></select>&nbsp;继续率&nbsp;'
    		+'<select id="minRatio_t" style="width:50px"><option>></option><option>=</option><option>>=</option></select>&nbsp;' 
    		+'<input id="minRatio" style="width:70px;" type="text" value="">&nbsp;%</td>'
    		+'<td style="width:120px;text-align:center;"><input id="bonusRatio" style="width:80px;" type="text" value=""></td>';
        	$("#twnMonthyBasic_list").append(h);
        },
        
        //打开添加模态框
        removeList:function (trid) {
            $("#"+trid).remove();
        }, 
                
        update:function(){
        	document.getElementById("saveButton").setAttribute("disabled", true);
        	flag = true;
        	/*异步校验*/
			if(flag){
				setDatas();
            	$.ajax({
                    url:'persistencyBonus/updatePb',
                    dataType:'json',
                    type:'post',
                    data:$("#persistencyBonus_updateForm").serialize(),
                    success:function(data){
                        if(data){
                        	 $.alert({
                                 title: '提示信息！',
                                 content: '修改成功!',
                                 type: 'blue'
                             });
                 		    document.getElementById("saveButton").removeAttribute("disabled");
                        }else{
                            $.alert({
                                title: '提示信息！',
                                content: '修改失败！',
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
        }
	}
}();