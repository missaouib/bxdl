var configSys = {
	//本地测试环境
	//fdfs_location:"http://10.10.10.173/",
	//生产环境
	fdfs_location:"http://image.e-huikang.com/",	
}

//var tabId = $("select[name=sheng]:visible").attr("id");

function createAddProcessTab(permission,rowId){
	 $.ajax({
         type: "POST",
         url: "menu/getMenuByPermission",
         data: {permission:permission},
         dataType: "json",
         success: function(data){
        	 //alert(data.menu.permission);
        	 //alert(data.menu.menuId);
        	 var menuId = data.menu.menuId;
        	 var url = data.menu.menuUrl;
        	 if(rowId!=''){
        		 url = url+"?id="+rowId;
        	 }
        	 var name = data.menu.nameZh;
        	 createAddProcessTab1(menuId,url,name);
         }
     });
}

function commCloseTab(permission){
	 $.ajax({
         type: "POST",
         url: "menu/getMenuByPermission",
         data: {permission:permission},
         dataType: "json",
         success: function(data){
	         var menuId = data.menu.menuId;
	    	//如果关闭的是当前激活的TAB，激活他的前一个TAB
	    	 if ($("li.active").attr('id') =="tab_"+ menuId) {
	    		 $("#tab_"+ menuId).prev().addClass('active');
	    		 $("#content"+ menuId).prev().addClass('active');
	    	 }
	    	//关闭TAB
	    	 $("#tab_"+ menuId).remove();
	    	 $("#content"+ menuId).remove();
	         }
     });
}

/*创建tab*/
function createAddProcessTab1(menuId,menuUrl,name){
	$(".active").removeClass("active");
	if ($(".tab-pane.active #content"+menuId+"").length){//如果当前tab已存在
		clickTab(menuId,menuUrl);
		$("#tab_"+menuId+"").addClass('active');
		$("#content"+menuId+"").addClass("active").addClass("in");//iframe内容显示隐藏
	}else{
		 $('#myTab').append("<li id='tab_"+menuId+"'><a onclick=clickTab("+menuId+",'"+menuUrl+"') href='#content"+menuId+"' data-toggle='pill'>"+name+"<i onclick='closeTab("+menuId+")' class='fa fa-remove tab-close' style='padding-left: 3px;'></i></a></li>");
		 $('#myTabContent').append("<div class='tab-pane fade' id='content"+menuId+"'></div>");
		 clickTab(menuId,menuUrl);
		 $('#myTab a:last').tab('show');
	}
}


/*关闭tab*/
function windowCloseTab(){
	var divid = $("#myTabContent .active").attr("id");
	closeTab(divid.substring(7,divid.length));

}

function clickThisTab(){
	var tabId = $("#myTab .active").attr("id");
	$("#"+tabId).find("a").click();
}

/*根据父ID获取地区*/
function getAreaByPid(parentId,selectId){
	 $.ajax({
        type: "POST",
        url: "systemDict/findDistrictByParentId",
        data: {parentId:parentId},
        dataType: "json",
        success: function(data){
        	var selecttext = "";
        	if(selectId=="sheng"){selecttext="省份"}else if(selectId=="shi"){selecttext="城市"}else{selecttext="区县"};
        	var h = "<option value=''>请选择"+selecttext+"</option>";  
            $.each(data, function(key, value) {
                h += "<option value='" + value.sortcode + "' myvalue='"+value.districtid+"'>" + value.district //下拉框序言的循环数据
                + "</option>";  
            });
            var selectTarget = $(".tab-pane.active #"+selectId);
            selectTarget.empty();
            selectTarget.append(h);//append 添加进去并展示  
            selectTarget.on(
	            "change",function(){
	            	var sortcode = $(this).val();
	            	var parentIdnext = $(this).find("option:selected").attr("myvalue");
	            	if(sortcode.length==6){
	            		selectId = "shi";
	            		getAreaByPid(parentIdnext,selectId);
	            		var h = "<option value=''>请选择区县</option>";
	            		 var selectTarget = $(".tab-pane.active #qu");
	            		
	            		 selectTarget.empty();
	            		 selectTarget.append(h);//append 添加进去并展示  
	            	}
	            	if(sortcode.length==9){
	            		selectId = "qu";
	            		getAreaByPid(parentIdnext,selectId);
	            	}
	            }
	        )
        }
    });
}

function getSysDict(selectId,dictType){
	 $.ajax({
	        type: "POST",
	        url: "systemDict/findDictNameByDictType",
	        data: {dictType:dictType},
	        dataType: "json",
	        success: function(data){
	        	var h = "<option value=''>请选择</option>";
	            $.each(data, function(key, value) {
	                // console.log("dict_id:"+value.dict_id);
	                // console.log("dict_code:"+value.dict_code);
	                // console.log("dict_name:"+value.dict_name);
	                h += "<option value='" + value.dict_id + "' myvalue='"+value.dict_code+"'>" + value.dict_name //下拉框序言的循环数据
	                + "</option>";  
	            });
	            $(".tab-pane.active #"+selectId).empty();
	            $(".tab-pane.active #"+selectId).append(h);//append 添加进去并展示  	            
        	}	
     });	
}

function getSysDictVal(selectId,dictType){
	$.ajax({
		type: "POST",
		url: "systemDict/findDictNameByDictType",
		data: {dictType:dictType},
		dataType: "json",
		success: function(data){
			var h = "<option value=''>请选择</option>";
			$.each(data, function(key, value) {
				h += "<option value='" + value.dict_code + "' myvalue='"+value.dict_code+"'>" + value.dict_name //下拉框序言的循环数据
					+ "</option>";
			});
			$(".tab-pane.active #"+selectId).empty();
			$(".tab-pane.active #"+selectId).append(h);//append 添加进去并展示
		}
	});

}

function getSysDictValHX(selectId,dictType,value){
    $.ajax({
        type: "POST",
        url: "systemDict/findDictNameByDictType",
        data: {dictType:dictType},
        dataType: "json",
        success: function(data){
            var h = "<option value=''>请选择</option>";
            $.each(data, function(key, value) {
                h += "<option value='" + value.dict_code + "' myvalue='"+value.dict_code+"'>" + value.dict_name //下拉框序言的循环数据
                    + "</option>";
            });
            $(".tab-pane.active #"+selectId).empty();
            $(".tab-pane.active #"+selectId).append(h);//append 添加进去并展示
            $(".tab-pane.active #"+selectId).val(value).trigger("change");
        }
    });

}
function getSysDictOption(dictType){
    $.ajax({
        type: "POST",
        url: "systemDict/findDictNameByDictType",
        data: {dictType:dictType},
        dataType: "json",
        success: function(data){
            var h = "<option value=''>请选择</option>";
            $.each(data, function(key, value) {
                h += "<option value='" + value.dict_code + "' myvalue='"+value.dict_code+"'>" + value.dict_name //下拉框序言的循环数据
                    + "</option>";
            });
         return h;
        }
    });

}

function getSysDictOption(dictType){
    $.ajax({
        type: "POST",
        url: "systemDict/findDictNameByDictType",
        data: {dictType:dictType},
        dataType: "json",
        success: function(data){
            var h = "<option value=''>请选择</option>";
            $.each(data, function(key, value) {
                h += "<option value='" + value.dict_code + "' myvalue='"+value.dict_code+"'>" + value.dict_name //下拉框序言的循环数据
                    + "</option>";
            });
            return h;
        }
    });

}
function commSalesAllTeams(selectId){
    $.ajax({
        type: "POST",
        url: "salesTeamInfo/getParents",
        data:{},
        dataType: "json",
        success: function(data){
            //alert(data.rows);
            var salesTeams = data.rows;
            var h = "<option value='' myvalue=''>销售团队</option>";
            $.each(salesTeams, function(key, value) {
                h += "<option value='" + value.salesTeamId + "'>" + value.salesTeamName //下拉框序言的循环数据
                    + "</option>";
            });
            $(".tab-pane.active #"+selectId).empty();
            $(".tab-pane.active #"+selectId).append(h);
        }
    });
}
/*获取组织机构*/
function commSalesOrgs(selectId){
	 $.ajax({
         type: "POST",
         url: "salesOrgInfo/findSalesOrgs",
         data:{dataAuthFlag:'1'},
         dataType: "json",
         success: function(data){
        	//alert(data.rows);
        	var salesOrgs = data.rows;
			var h = "<option value='' myvalue=''>组织机构</option>";
            $.each(salesOrgs, function(key, value) {
            	//alert(value.insuranceCompanyCode);
                h += "<option value='" + value.salesOrgId + "'>" + value.salesOrgName //下拉框序言的循环数据
                + "</option>";  
            });
            $(".tab-pane.active #"+selectId).empty();
            $(".tab-pane.active #"+selectId).append(h);
         }
     });
}

/*获取销售团队*/
function commSalesTeams(selectId,salesOrgId){
	 $.ajax({
         type: "POST",
         url: "salesTeamInfo/getParents",
         data:{salesOrgId:salesOrgId},
         dataType: "json",
         success: function(data){
        	 	var salesTeams = data.rows;
			 	var h = "<option value='' myvalue=''>销售团队</option>";
	            $.each(salesTeams, function(key, value) {
	                h += "<option value='" + value.salesTeamId + "'>" + value.salesTeamName //下拉框序言的循环数据
	                + "</option>";  
	            });
	            $(".tab-pane.active #"+selectId).empty();
	            $(".tab-pane.active #"+selectId).append(h);
         }
     })
}
/*获取产品*/
function commSelectProduct(selectId,Id){
    $.ajax({
        type: "POST",
        url: "product_basic_information/findAllInsurProductSub",
        data:{"insuranceCompanyId":Id},
        dataType: "json",
        success: function(data){
            var salesTeams = data.rows;
            var h = "<option value='' myvalue=''>保险产品</option>";
            $.each(salesTeams, function(key, value) {
                h += "<option value='" + value.productsRatioId + "'>" + value.productsName //下拉框序言的循环数据
                    + "</option>";
            });
            $(".tab-pane.active #"+selectId).empty();
            $(".tab-pane.active #"+selectId).append(h);
        }
    })
}
/*获取产品代码*/
function commSelectProductCode(selectId,a6,a7,a7t,a9,a9t,a11,a11t,a12,Id){

    $.ajax({
        type: "POST",
        async: false,
        url: "product_basic_information/select_insurance_basic_product_son",
        data:{"productId":Id},
        dataType: "json",
        success: function(data){
            var h = "<option value='' myvalue=''>缴费方式</option>";
            var salesTeams = data.list
            $.each(salesTeams, function(key, value) {
                if(value == 0){
                    h += "<option value='" + value + "'>" + "年交" //下拉框序言的循环数据
                        + "</option>";
                }else if(value == 1){
                    h += "<option value='" + value + "'>" + "半年交" //下拉框序言的循环数据
                        + "</option>";
                }else if(value == 2){
                    h += "<option value='" + value + "'>" + "季交" //下拉框序言的循环数据
                        + "</option>";
                }else if(value == 3){
                    h += "<option value='" + value + "'>" + "月交" //下拉框序言的循环数据
                        + "</option>";
                }else if(value == 4){
                    h += "<option value='" + value + "'>" + "趸交" //下拉框序言的循环数据
                        + "</option>";
                }else if(value == 5){
                    h += "<option value='" + value + "'>" + "三年交" //下拉框序言的循环数据
                        + "</option>";
                }


            });
//险种类别
            var insuranceType = data.insuranceType
            //缴费期间标志
            var rene = data.renewalPeriodType
            var reneText ="其他"
            if(rene.toString() == '0'){
                reneText="年缴"
            }   if(rene == 1){
                reneText="N年"
            }
            //保险期间标志  0-短期;1-终身;2-n年；3-1年',
            var peri = data.insurancePeriodType
            var periText ="其他"
            if(peri.toString() == '0'){
                periText="短期"
            }   if(peri == 1){
                periText="终身"
            } if(peri == 2){
                periText="n年"
            } if(peri == 3){
                periText="1年"
            }
            //主附险标识 mainOrAdditional 0-主险；1-附险',
            var main = data.mainOrAdditional
            var  mainText="其他"
            if(main.toString() == '0'){
                mainText="主险"
            }   if(main == 1){
                mainText="附险"
            }
            $(".tab-pane.active #"+a12).empty();
            $(".tab-pane.active #"+a12).val(insuranceType);

            $(".tab-pane.active #"+a11).empty();
            $(".tab-pane.active #"+a11).val(main);

            $(".tab-pane.active #"+a11t).empty();
            $(".tab-pane.active #"+a11t).val(mainText);

            $(".tab-pane.active #"+a9).empty();
            $(".tab-pane.active #"+a9).val(peri);

            $(".tab-pane.active #"+a9t).empty();
            $(".tab-pane.active #"+a9t).val(periText);


            $(".tab-pane.active #"+a7).empty();
            $(".tab-pane.active #"+a7).val(rene);

            $(".tab-pane.active #"+a7t).empty();
            $(".tab-pane.active #"+a7t).val(reneText);

            $(".tab-pane.active #"+a6).empty();
            $(".tab-pane.active #"+a6).append(h);

            $(".tab-pane.active #"+selectId).empty();
            $(".tab-pane.active #"+selectId).val(data.productCode);

        }
    })
}
/*获取保险公司*/
function commSelectProductOrg(selectId){
    $.ajax({
        type: "POST",
        url: "insurance_dept/findInsurCompanys",
        dataType: "json",
        success: function(data){
            var salesTeams = data.rows;
            var h = "<option value='' myvalue=''>保险公司</option>";
            $.each(salesTeams, function(key, value) {
                h += "<option value='" + value.insuranceCompanyId + "'>" + value.insuranceCompanyName //下拉框序言的循环数据
                    + "</option>";
            });
            $(".tab-pane.active #"+selectId).empty();
            $(".tab-pane.active #"+selectId).append(h);
        }
    })
}
/*获取组织机构  回显*/
function commSalesOrgsHX(selectId,value){
    $.ajax({
        type: "POST",
        url: "salesOrgInfo/findSalesOrgs",
        data:{dataAuthFlag:'1'},
        dataType: "json",
        success: function(data){
            //alert(data.rows);
            var salesOrgs = data.rows;
            var h = "<option value='' myvalue=''>组织机构</option>";
            $.each(salesOrgs, function(key, value) {
                //alert(value.insuranceCompanyCode);
                h += "<option value='" + value.salesOrgId + "'>" + value.salesOrgName //下拉框序言的循环数据
                    + "</option>";
            });
            $(".tab-pane.active #"+selectId).empty();
            $(".tab-pane.active #"+selectId).append(h);

            $(".tab-pane.active #"+selectId).val(value).trigger("change");
        }
    });
}

/*获取销售团队 回显*/
function commSalesTeamsHX(selectId,salesOrgId,value){
    $.ajax({
        type: "POST",
        url: "salesTeamInfo/getParents",
        data:{salesOrgId:salesOrgId},
        dataType: "json",
        success: function(data){
            var salesTeams = data.rows;
            var h = "<option value='' myvalue=''>营销团队</option>";
            $.each(salesTeams, function(key, value) {
                h += "<option value='" + value.salesTeamId + "'>" + value.salesTeamName //下拉框序言的循环数据
                    + "</option>";
            });
            $(".tab-pane.active #"+selectId).empty();
            $(".tab-pane.active #"+selectId).append(h);
            $(".tab-pane.active #"+selectId).val(value).trigger("change");
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
        	var h = "<option value=''>职级序列</option>";
        	if(data){
        		var rankSequences = data.rows;
	            $.each(rankSequences, function(key, value){	            	
	                h += "<option value='" + value.sequenceId +"'>" + value.sequenceName //下拉框序言的循环数据
	                + "</option>";  
	            });
	            $(".tab-pane.active #"+selectId).empty();
	            $(".tab-pane.active #"+selectId).append(h);	 	            
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

function commGetSales(insuranceSalesNo,selectId){
	$.ajax({
        type: "POST",
        url: "insuranceSalesInfo/insuranceSalesList",
        data:{likeSalerNo:insuranceSalesNo},
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

           $(".tab-pane.active #"+selectId).empty();
           $(".tab-pane.active #"+selectId).append(h);
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
        	var h = "<option value=''>销售职级</option>";
        	if(data){
        		var salesGrades = data.rows;
	            $.each(salesGrades, function(key, value){	            	
	                h += "<option value='" + value.salesGradeId +"'>" + value.salesGradeName //下拉框序言的循环数据
	                + "</option>";  
	            });
	            $(".tab-pane.active #"+selectId).empty();
	            $(".tab-pane.active #"+selectId).append(h);	            
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

//上传图片
function comm_uploadImage(formId,backto){
	var multipart = $(".tab-pane.active #fileName").val();
	if(multipart==""||multipart==null){
		$.alert({
        title: '提示信息！',
        content: '请先选择文件！',
        type: 'red'
    });
		return ;
	}
	$(".tab-pane.active #"+formId).ajaxSubmit({
    type : 'POST',
    data:{},
    dataType:"json",
    url:'fdfs/uploadImage',
    success : function(data) {
  	  if(data){
  		  var backinfo = data.rows;
          $.each(backinfo, function(key, value){	            	
        	  $(".tab-pane.active #"+backto).val(value.fileUrl);
          });
  		  $(".tab-pane.active #fileName").val("");
  		  $(".tab-pane.active #openImageDlg").modal('hide');
  	  }else{
  		$.alert({
              title: '提示信息！',
              content: '上传失败！',
              type: 'red'
          });
  	  }
    }
	});
}

function isEmpty(obj){
    if(typeof obj == "undefined" || obj == null || obj == ""){
        return true;
    }else{
        return false;
    }
};
function disProtocol() {
	var nodeList = document.getElementsByTagName("input");
	for (var i = 0; i < nodeList.length; i++) {
		nodeList[i].disabled = true;
	}
	nodeList = document.getElementsByTagName("select");
	for (var i = 0; i < nodeList.length; i++) {
		nodeList[i].disabled = true;
	}
}

function getStateTest(state) {
    if(state == '1001'){
        state='保单已录入'
    }else if(state == '1002') {
        state = '审核通过'
    }else if(state == '1003') {
        state = '审核不通过'
    }else if(state == '1004') {
        state = '已下发'
    }else if(state == '1005') {
        state = '已接收'
    }else if(state == '1006') {
        state = '回执已录入'
    }else if(state == '1007') {
        state = '回执审核通过'
    }else if(state == '1008') {
        state = '回执未通过'
    }else if(state == '1009') {
        state = '已下发'
    }else if(state == '1010') {
        state = '已回访'
    }else if(state == '1011') {
        state = '撤保'
    }else if(state == '1012') {
        state = '退保'
    }else if(state == '1013') {
        state = '终止'
    }else if(state == '1014') {
        state = '中止'
    }
    return state;
}
function formatDatew (value) {
    var cellval =value+"";
    if (cellval != null) {
        var date = new Date(parseInt(cellval.replace("/Date(", "").replace(")/", ""), 10));
        var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
        var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
        return date.getFullYear() + "-" + month + "-" + currentDate;
    }
}

/**
 * 通过dictType获取响应字典
 * @param selectId
 * @param dictType
 */
function findDictByDictTypeRetEntity(dictType, RULETYPE){
    $.ajax({
        type: "GET",
        url: "systemDict/findDictByDictTypeRetEntity",
        data: {dictType:dictType},
        async: false,
        dataType: "json",
        success: function(data){
            RULETYPE = data;
        }
    });

}