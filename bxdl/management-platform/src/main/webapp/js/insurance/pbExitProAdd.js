$(function(){
	getIppbsPro();
	
	pbExitPro.formValidator();
	
    setTimeout(function(){
    	 /*通算规则*/
	     var addSubsidyWay = $("#addSubsidyWay").val();
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
    },100)
    
    $("#exitProductId").on(  
        "change",function(){
        	$('#exitProCode').val($(this).val());
        }
    ) 
    
    $("#exitProCode").on(  
        "change",function(){
        	$('#exitProductId').val($(this).val());
        }
    ) 	
})

function getIppbsPro(){
	$.ajax({
        url:'lifeProtocol/insProtocolProducts',
        dataType:'json',
        type:'get',
        data:{protocolId:$("#protocolId").val()},
        success:function(data){
            $.each(data, function(key, value) {
            	var h = "<option value='' myvalue=''>请选择</option>";
                h += "<option value='" + value.productId + "' myvalue='"+ value.productCode +"'>" + value.productName //下拉框序言的循环数据
                + "</option>"; 
                $("#exitProductId").empty();
	            $("#exitProductId").append(h);
	            
            	var c = "<option value='' myvalue=''>请选择</option>";
                c += "<option value='" + value.productId + "' myvalue='"+ value.productCode +"'>" + value.productCode //下拉框序言的循环数据
                + "</option>"; 
                $("#exitProCode").empty();
	            $("#exitProCode").append(c);
            });
        }
	})
}

//返回继续率-奖金
function backToPb(){
	commCloseTab('persistencyBonus:exitPro');
	createAddProcessTab('persistencyBonus:edit',$('#protocolId').val());
}

//返回列表
function backToIPList(){
	commCloseTab('persistencyBonus:exitPro');
	createAddProcessTab('lifeProtocol:list','');
}

//1.打开协议信息
function openAddProtocolBasic(){
	commCloseTab('persistencyBonus:exitPro');
	createAddProcessTab('lifeProtocolBasic:add',$('#protocolId').val());
}

//2.1打开手续费费
function openAddProtocolService(){
	commCloseTab('persistencyBonus:exitPro');
	createAddProcessTab('lifeProtocolService:add',$('#protocolId').val());
}
//3.打开续年度服务津贴
function openAddProtocolSubsidy(){
	commCloseTab('persistencyBonus:exitPro');
	createAddProcessTab('lifeProtocolSubsidy:add',$('#protocolId').val());
}

//5.费率调节
function openPersistencyBonus(){
}

//6.打开业务推动
function openProtocolPromotion(){
	commCloseTab('persistencyBonus:exitPro');
	createAddProcessTab('protocolPromotion:list',$('#protocolId').val());
}

function setDatas(){	
	/*获取月继续率规则*/
	var monthyBasic = [];
	var monthyBasicLength = $("#monthyBasic_list").find("tr").length;
	for(var i=1 ; i< monthyBasicLength ; i++){
		var mbObj = {};
		mbObj.persistencyBonusId = $("#bonusId").val();
		mbObj.basicFlag = "1";
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
			mbObj.basicFlag = "1";
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
			mbObj.basicFlag = "1";
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
			mbObj.basicFlag = "1";
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

var pbExitPro = function () {
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
                
        add:function(){
        	document.getElementById("saveButton").setAttribute("disabled", true);
        	if($("#Pb_ExitPro_addForm").data('bootstrapValidator').validate().isValid()){
	        	flag = true;
	        	/*异步校验*/
				if(flag){
					setDatas();
	            	$.ajax({
	                    url:'persistencyBonus/addExitProPbr',
	                    dataType:'json',
	                    type:'post',
	                    data:$("#Pb_ExitPro_addForm").serialize(),
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
            }else{
    		    document.getElementById("saveButton").removeAttribute("disabled");
            }			
        },
        
      //表单验证
        formValidator:function () {
            $("#Pb_ExitPro_addForm").bootstrapValidator({
                fields:{
                	exitProductId:{
                        validators:{
                            notEmpty:{
                                message:"请选择例外产品"
                            }
                        }
                    }
                }
            });
         }
	}
}();