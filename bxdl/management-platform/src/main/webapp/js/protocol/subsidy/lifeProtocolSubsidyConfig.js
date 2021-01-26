
/**
 *续年度津贴--例外产品配置
 */
var isLook = $("#isLook").val();
$(function (){
	  if(isLook =='yes'){
    	  disProtocol();
  	}
});
function addSubsidyConfig() {
	var subsidyId = $("#subsidy-config-id").val();
	var tr = $("#subsidy-config-table tr");
	var result = [];
	for (var i = 0; i < tr.length; i++) {
		var tds = $(tr[i]).find("td");
		if (tds.length > 0) {
			result.push({
				"code" : $(tds[0]).find("input").val(),
				"2期" : $(tds[1]).find("input").val(),
				"3期" : $(tds[2]).find("input").val(),
				"4期" : $(tds[3]).find("input").val(),
				"5期" : $(tds[4]).find("input").val(),
				"6期" : $(tds[5]).find("input").val(),
				"7期" : $(tds[6]).find("input").val(),
				"8期" : $(tds[7]).find("input").val(),
				"9期" : $(tds[8]).find("input").val(),
				"10期" : $(tds[9]).find("input").val(),
				"11期" : $(tds[10]).find("input").val(),
				"12期" : $(tds[11]).find("input").val(),
				"13期" : $(tds[12]).find("input").val(),
				"14期" : $(tds[13]).find("input").val(),
				"15期" : $(tds[14]).find("input").val(),
				"16期" : $(tds[15]).find("input").val(),
				"17期" : $(tds[16]).find("input").val(),
				"18期" : $(tds[17]).find("input").val(),
				"19期" : $(tds[18]).find("input").val(),
				"20期" : $(tds[19]).find("input").val(),
				"21期" : $(tds[20]).find("input").val(),
				"22期" : $(tds[21]).find("input").val(),
				"23期" : $(tds[22]).find("input").val(),
				"24期" : $(tds[23]).find("input").val(),
				"25期" : $(tds[24]).find("input").val(),
				"26期" : $(tds[25]).find("input").val(),
				"27期" : $(tds[26]).find("input").val(),
				"28期" : $(tds[27]).find("input").val(),
				"29期" : $(tds[28]).find("input").val(),
				"30期及以上" : $(tds[29]).find("input").val()
			})
		}
	}
	$.ajax({
		type : "post",
		url : "lifeProtocol/updateSubsidyConfig",
		dataType : "json",
		data : {
			subsidyId : subsidyId,
			datas : JSON.stringify(result)
		},
		success : function(data) {
			if(data){
				commCloseTab("liftProtocolSubsidy:configSubsidy");
				createAddProcessTab('lifeProtocolSubsidy:add',$("#ep_subsidy_look_protocolId").val());
			}else{
				 $.alert({
                     title: '提示信息！',
                     content: '保存失败!',
                     type: 'red'
                 }); 
			}
		}
	})
}
//取消例外产品配置
function cancelSubsidyConfig(){
	commCloseTab("liftProtocolSubsidy:configSubsidy");
	createAddProcessTab('lifeProtocolSubsidy:add',$("#ep_subsidy_look_protocolId").val());
}
