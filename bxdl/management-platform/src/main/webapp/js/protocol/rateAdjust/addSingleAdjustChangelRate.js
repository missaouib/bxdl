/**
 *单/全产品 变动费率设置
 */
var isLook = $("#single_adjust_isLook").val();
$(function (){
	updateTheEcho();//修改回显
	  if(isLook =='yes'){
     	  disProtocol();
   	 }
});

/**
 * 1.费率基于模块
 */
//追加费率基于
function addChangeRate(){
    var random = Math.random() * 61;
    var zhValue = getSelectValue("changeRateType");
    var changeRateHtml = "<tr>\n" +
         "                        <td style=\"width:10%;\">\n"
		+ "                            <select id="+random+" class=\"form-control form-control-static\" name=\"rateType\" disabled=\"disabled\">\n"
		+ "                            		<option value=\"0\">规保</option>\n"
		+ '                           		<option value=\"1\">标保</option>\n'
		+ "                            </select>\n"
		+ "                        </td>\n"
		+ "                        <td style=\"width:77%;\">\n"
		+ "                            <input style=\"width:15%;margin-left:2.5%;height:32px;display:inline;\"  type=\"number\" step=\"0.01\" min=\"0\"  name=\"bigNum\"> 万元\n"
		
		+ "                            <select style=\"margin-left:0.5%;width:15%;display:inline;\" class=\"form-control form-control-static\" name=\"firstMark\">\n"
		+ "                                <option value=\">\">></option>\n"
		+ "                                <option value=\">=\">>=</option>\n"
		+ "                            </select>\n"
		+ "							  <span  name=\"zhRateType\">"+zhValue+"</span>"
		+ "                            <select style=\"margin-left:2.5%;width:15%;display:inline;\" class=\"form-control form-control-static\" name=\"secondMark\">\n"
		+ "                                <option value=\">\">></option>\n"
		+ "                                <option value=\">=\">>=</option>\n"
		+ "                            </select>\n"
		
		+ "                            <input style=\"margin-left:0.5%;width:15%;height:32px;display:inline;\" type=\"number\" step=\"0.01\" min=\"0\" name=\"smallNum\"> 万元\n"
		+ "                        </td>\n"
		+ "                        <td style=\"width:5%;\">\n"
		
		+ "                            <input style=\"width:auto;height:32px;\" type=\"number\" step=\"0.01\" min=\"0\" name=\"changeRate\">\n"
		+ "                        </td>\n"
		+ "                        <td style=\"width:10%;\">\n"
		+ "                            <a style=\"color: black\" href=\"javascript:void(0)\" onclick=\"addChangeRate()\">➕</a>\n"
		+ "                            <a style=\"color: black\" href=\"javascript:void(0)\" onclick=\"deleteChangeRate(this)\">➖</a>\n"
		+ "                        </td>\n" + " " +
		  " 				 </tr>";
    $("#addChangeRateTable").append(changeRateHtml);
    //动态设置计算项
    selectedOption(random,zhValue);
};

//获取选中的计算项
function changeRateType(){
	var value = getSelectValue("changeRateType")
	$('#addChangeRateType').html(value);
}

/**
 * 2.附加费率模块
 */
//获取选中的计算项
function checkSubjoinRateType(index){
	var value = getSelectValue("checkSubjoinRateType");
	if (value != '继续率') {
		$('#addSubjoinRateType').html(value);
		divShowOrHide(index,"showRule",true);//规则规则前半部分
		divShowOrHide(index,"syntagmatic",true);// 组合关系（并且/或者）
	}else{
		divShowOrHide(index,"showRule",false);
		divShowOrHide(index,"syntagmatic", false);
		divShowOrHide(index,"showPerRate", true);//继续率规则
	}
}	
//是否与继续率组合
function checkPerRate(index){
	var isGroupPerRate = getSelectValue(index+"isGroupPerRate");
	if (isGroupPerRate == '是') {
		divShowOrHide(index,"showPerRate", true);
	} else {
		divShowOrHide(index,"showPerRate", false);
	}
};
//追加附加费率
function addSubjoinRate(){
	var list = $("#subjoinList").text();
	var subjoinList = eval('(' + list + ')');
	var random = (new Date()).valueOf() + parseInt(Math.random() * 61);
	// 获取第一次选中计算项的值
	var zhValue = getSelectValue("checkSubjoinRateType");
	// 获取第一次选中是否组合的值
	var groupPerRateValue = getSelectValue("0isGroupPerRate");
	var showRule_id = random + "showRule"//规则前半部分
	var subjoinRateType_id = random + "subjoinRateType";//计算项
	var perRateNum_id = random + "perRateNum";//下拉框继续率数值
	var isGroupPerRate_id = random + "isGroupPerRate";//与继续率组合（是/否）
	var showPerRate_id = random + "showPerRate"; //是否展示继续率规则
	var syntagmatic_id = random + "syntagmatic"; //组合关系（并且/或者）

    var subjoinRateHtml = "<tr>\n" +
         "                 <td >\n"
		+ "                      <select id="+subjoinRateType_id+"  name=\"subjoinRateType\" disabled=\"disabled\" style=\"width:100px;height:32px;\">\n"
		+ "                            	<option value=\"0\">规保</option>\n"
		+ "                          	<option value=\"1\">标保</option>\n"
		+ "                          	<option value=\"2\">继续率</option>\n"
		+ "                      </select>\n"
		+ "                 </td>\n"
		+ "                 <td style=\"width: 900px;\">\n"
		+"						 <div id="+showRule_id+" style=\"display:inline\">"
									// 规则最大值 
		+ "                           <input style=\"margin-left:2px;width:100px;height:32px;\"  type=\"number\" step=\"0.01\" min=\"0\"  name=\"bigNum\" \> 万元"
		
		+ "                            <select  style=\"margin-left:2px;width:100px;height:32px;\" name=\"firstMark\">\n"
		+ "                                <option value=\">\">></option>\n"
		+ "                                <option value=\">=\">>=</option>\n"
		+ "                            </select>\n"
		
		+ "							  <span  name=\"zhSubjoinRateType\">"+zhValue+"</span>"
		+ "                            <select style=\"margin-left:2px;width:100px;height:32px;\"  name=\"secondMark\">\n"
		+ "                                <option value=\">\">></option>\n"
		+ "                                <option value=\">=\">>=</option>\n"
		+ "                            </select>\n"
										// 规则最小值 
		+ "                            <input style=\"margin-left:2px;width:100px;height:32px;\"  type=\"number\" step=\"0.01\" min=\"0\" name=\"smallNum\"> 万元\n"
		
		+ "								<span >是否与继续率组合:</span>"
		+ "								<select style=\"margin-left:2px;width:100px;height:32px;\" name=\"isGroupPerRate\"  disabled=\"disabled\"  id="+isGroupPerRate_id+" onchange=\"checkPerRate('"+random+"')\">\n"
		+ "    								<option value=\"是\">是</option>\n"
		+ "     							<option value=\"否\" >否</option>\n"
		+ "								</select>\n"
        + "						</div>"
		
        + " 					<div id ="+showPerRate_id+" style=\"display:inline\">"
       
        + "							<div id="+syntagmatic_id+" style=\"display:inline\">"
        + " 							<span>组合关系:</span>"	
        + "								<select style=\"margin-left:2px;width:100px;height:32px;\"  name=\"syntagmatic\">\n"
        + "   								 <option value=\"&&\">并且</option>\n"	
        + "  								 <option value=\"||\" >或者</option>\n"	
        + "								</select>\n"	
        + "							</div>"
        + "							<span>继续率:</span>"	
        + "							<select id="+perRateNum_id+" style=\"margin-left:2px;width:100px;height:32px;\"  name=\"perRateNum\">\n"	
        + " 								<option>请选择</option>\n"
        + "							</select>\n"
        					
        							//规则继续率最大值
        + " 						<input style=\"margin-left:2px;width:100px;height:32px;\"  type=\"number\" step=\"0.01\" min=\"0\"  name=\"bigParRateNum\">%\n"
        
		+ "                         <select style=\"margin-left:2px;width:100px;height:32px;\"  name=\"thirdMark\">\n"
		+ "                                <option value=\">\">></option>\n"
		+ "                                <option value=\">=\">>=</option>\n"
		+ "                         </select>\n"
		+ " 						<span>继续率</span>\n" 
		+ "                         <select style=\"margin-left:2px;width:100px;height:32px;\"  name=\"否认Mark\">\n"
		+ "                                <option value=\">\">></option>\n"
		+ "                                <option value=\">=\">>=</option>\n"
		+ "                         </select>\n"
        + "						</div>"
        							//规则继续率最小值
        + " 						<input style=\"margin-left:2px;width:100px;height:32px;\" type=\"number\" step=\"0.01\" min=\"0\"  name=\"smallPerRateNum\">%\n"
		
		+ "                        </td>\n"
		
		+ "                        <td style=\"width:5%;\">\n"
									//调节费率
		+ "                         <input style=\"width:auto;height:32px\"  type=\"number\" step=\"0.01\" min=\"0\"  name=\"changeRate\">\n"
		+ "                        </td>\n"
		+ "                        <td style=\"width:10%;\">\n"
		+ "                            <a style=\"color: black\" href=\"javascript:void(0)\" onclick=\"addSubjoinRate()\">➕</a>\n"
		+ "                            <a style=\"color: black\" href=\"javascript:void(0)\" onclick=\"deleteChangeRate(this)\">➖</a>\n"
		+ "                        </td>\n" + " " +
		  " 				 </tr>";
 
    $("#addSubjoinRateTable").append(subjoinRateHtml);
    //回显继续率下拉框内容
    for ( var i in subjoinList){
    	$("#"+perRateNum_id).append("<option value="+subjoinList[i].subKey+">"+subjoinList[i].subValue+"</option>\n")
    }
	// 动态设置计算项
	selectedOption(subjoinRateType_id, zhValue);
	//根据首行计算项内容和是否组合 控制相关div展示或隐藏
	setDiv(random,zhValue,groupPerRateValue);
	// 动态设置是否组合
	selectedOption(isGroupPerRate_id, groupPerRateValue);
};

//保存 -调节费率-附加费率
function saveAdjustChangeRate(){
	//调节费率 table
	var tr = $("#addChangeRateTable tr");
	var changeRateArr = []; 
	for (var i = 1; i < tr.length; i++) {
		var tds = $(tr[i]).find("td");
		if (tds.length > 0) {
			var rateTypeText = $(tds[0]).find("option:selected").text();
			var rateTypeValue = $(tds[0]).find("option:selected").val();
			var  bigNum = $(tds[1]).find("input[name='bigNum']").val();
			var firstMark = $(tds[1]).find("select[name='firstMark']").val();
			var secondMark = $(tds[1]).find("select[name='secondMark']").val()
			var  smallNum = $(tds[1]).find("input[name='smallNum']").val();
			var changeRate = $(tds[2]).find("input[name='changeRate']").val();
			changeRateArr.push({
				"rateTypeText":rateTypeText,
				"rateTypeValue":rateTypeValue,
				"bigNum":bigNum,
				"firstMark":firstMark,
				"secondMark":secondMark, 
				"smallNum":smallNum ,
				"changeRate":changeRate ,
				"summaryRule":bigNum+firstMark+rateTypeText+"&&"+rateTypeText+secondMark+smallNum,
			})
		}
	};
	
	//附加费率table
	var tr = $("#addSubjoinRateTable tr");
	var subjoinRateArr = []; 
	for (var i = 1; i < tr.length; i++) {
		var tds = $(tr[i]).find("td");
		if (tds.length > 0) {
			var rateTypeText = $(tds[0]).find("option:selected").text();
			var rateTypeValue = $(tds[0]).find("option:selected").val();
			var  bigNum = $(tds[1]).find("input[name='bigNum']").val();
			var firstMark = $(tds[1]).find("select[name='firstMark']").val();
			var secondMark = $(tds[1]).find("select[name='secondMark']").val();
			var  smallNum = $(tds[1]).find("input[name='smallNum']").val();
			var isGroupPerRate = $(tds[1]).find("select[name='isGroupPerRate']").val();//是否与继续率组合
			var syntagmatic = $(tds[1]).find("select[name='syntagmatic']").val();//组合关系
			var perRateNum = $(tds[1]).find("select[name='perRateNum']").val();//继续率数值
			var bigParRateNum = $(tds[1]).find("input[name='bigParRateNum']").val();//继续率规则最大值
			var thirdMark = $(tds[1]).find("select[name='thirdMark']").val();
			var fourMark = $(tds[1]).find("select[name='fourMark']").val();
			var smallPerRateNum = $(tds[1]).find("input[name='smallPerRateNum']").val();//继续率规则最小值
			var changeRate = $(tds[2]).find("input[name='changeRate']").val();//调节费率
			// 是否 选择继续率
			if (rateTypeText == '继续率') {
				subjoinRateArr.push({
					"rateTypeText" : rateTypeText,
					"rateTypeValue" : rateTypeValue,
					"perRateNum" : perRateNum,
					"bigParRateNum" : bigParRateNum,
					"thirdMark" : thirdMark,
					"fourMark" : fourMark,
					"smallPerRateNum" : smallPerRateNum,
					"changeRate" : changeRate,
					"summaryRule" : bigParRateNum + thirdMark + rateTypeText
							+ "&&" + rateTypeText + fourMark + smallPerRateNum,
				})
			} else {
				// 是否组合
				if (isGroupPerRate == '是') {
					subjoinRateArr.push({
						"rateTypeText" : rateTypeText,
						"rateTypeValue" : rateTypeValue,
						"bigNum" : bigNum,
						"firstMark" : firstMark,
						"secondMark" : secondMark,
						"smallNum" : smallNum,
						"isGroupPerRate" : isGroupPerRate,
						"syntagmatic" : syntagmatic,
						"perRateNum" : perRateNum,

						"bigParRateNum" : bigParRateNum,
						"thirdMark" : thirdMark,
						"fourMark" : fourMark,
						"smallPerRateNum" : smallPerRateNum,
						"changeRate" : changeRate,
						"summaryRule" : "(" + bigNum + firstMark + rateTypeText
								+ "&&" + rateTypeText + secondMark + smallNum
								+ ")" + syntagmatic + "(" + bigParRateNum
								+ thirdMark + "继续率" + "&&" + "继续率" + fourMark
								+ smallPerRateNum + ")",
					})
				} else {
					subjoinRateArr.push({
						"rateTypeText" : rateTypeText,
						"rateTypeValue" : rateTypeValue,
						"bigNum" : bigNum,
						"firstMark" : firstMark,
						"secondMark" : secondMark,
						"smallNum" : smallNum,
						"changeRate" : changeRate,
						"isGroupPerRate" : isGroupPerRate,
						"summaryRule" : bigNum + firstMark + rateTypeText
								+ "&&" + rateTypeText + secondMark + smallNum,
					})
				}
			}
		}
	}
	$.ajax({
		type : "post",
		url : "lifeProtocol/updateAdjustChangeRate",
		dataType : "json",
		data : {
			"adjustParamId" : $("#adjust-param-id").val(),
			"changeRate" : JSON.stringify(changeRateArr),
			"changeSubjoinRate" : JSON.stringify(subjoinRateArr)
		},
		success : function(data) {
			if (data) {
				commCloseTab('lifeProtocolSingleRateAdjust:add');
			} else {
				$.alert({
					title : '提示信息！',
					content : '保存失败！',
					type : 'red'
				});
			}
		}
	})
};
function returnAdjustChangeRate(){
	commCloseTab('lifeProtocolSingleRateAdjust:add');

}
// 修改回显
function updateTheEcho(){
	var subjoinRatelist = $("#subjoinRatelist").text();
	var list = eval('(' + subjoinRatelist + ')');
	for (var i=0;i<list.length;i++){
		 if(list[i].rateTypeText == '继续率'){
			 divShowOrHide(i,"showRule",false);//规则前半部分
			 divShowOrHide(i,"syntagmatic",false);//组合关系（并且/或者）
		 }else{
			 if(list[i].isGroupPerRate == '是' ||  typeof(list[i].isGroupPerRate) == "undefined"){
				 divShowOrHide(i,"showPerRate",true);//继续率规则
			 }else{
				 divShowOrHide(i,"showPerRate",false);
			 }
		 }
	}
};
//根据首行计算项内容和是否组合 控制相关div展示或隐藏
function setDiv(random,zhValue,groupPerRateValue){
	if (zhValue == '继续率') {
		divShowOrHide(random,"showRule", false);
		divShowOrHide(random,"syntagmatic", false);
	} else {
		if (groupPerRateValue == '是') {
			divShowOrHide(random,"showRule", true);
			divShowOrHide(random,"syntagmatic", true);
		} else {
			divShowOrHide(random,"showPerRate", false);
		}
	}
};

/**
 * 3.公共方法
 * 
 */
//div展示或隐藏
function divShowOrHide(index,idName, flag) {
	var id = index+idName;
	if (flag) {
		$("#" + id).attr("style", "display:inline;");
	}else {
		$("#" + id).attr("style", "display:none;");
	}
};

//获取指定下拉框的值
function getSelectValue(id) {
	var value = $('#'+id+' option:selected').text();
	return value;
};
//下拉框设置选中状态
function selectedOption(random,zhValue){
	var select = document.getElementById(random);
	for (var i = 0; i < select.options.length; i++) {
		if (select.options[i].innerHTML == zhValue) {
			select.options[i].selected = true;
			break;
		}
	}
};
//删除费率调节
function deleteChangeRate(a) {
   var table = a.parentNode.parentNode.parentNode;
   var tr = a.parentNode.parentNode;
   table.removeChild(tr);
};

