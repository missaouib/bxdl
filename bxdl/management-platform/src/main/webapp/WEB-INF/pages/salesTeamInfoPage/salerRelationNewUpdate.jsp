<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/core.jsp" %>
<!DOCTYPE  html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <title>修改次月员工关系</title>
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Cache" content="no-cache">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   	<link rel="stylesheet" href="${path}/css/system/role/role.css">


   	<script src="${path}/js/system/comm.js" charset="UTF-8" type="text/javascript"></script>
    <script src="${path}/static/js/bootstrap.js"></script>
	<script src="${path}/js/bootstrap3-typeahead.js" type="text/javascript"></script>
	<script src="${path}/js/jquery.autocompleter.js" type="text/javascript"></script>
	<script type="text/javascript">
		$(function () {
		     var shiro = $("#shiro").val();
		    if (shiro != '111'){
		       $("#tjSales").attr('disabled', 'disabled');
                $("#tjSalesNo").attr('disabled', 'disabled');
            }
			getSalesGrade("salesGrade");

				var insuranceSalesId = $("#insuranceSalesId").val();
			    var dbSalesId = $("#dbSalesId").val(); //担保人
				var tjSalesId = $("#tjSalesId").val(); //推荐人
				var ycCFirstGenerId = $("#ycCFirstGenerId").val();//一代处育成人
				var ycCSecondGenerId = $("#ycCSecondGenerId").val();//二代处育成人
				var ycBFirstGenerId = $("#ycBFirstGenerId").val();//一代部育成人
				var ycBSecondGenerId = $("#ycBSeconGenerId").val();//二代部育成人
				var sjSalesId = $("#sjSalesId").val(); //上级管理人
				var jcSalesId = $("#jcSalesId").val(); //继承人
				var fdSalesId = $("#fdSalesId").val(); //辅导人
				var directGroupCId = $("#directGroupCId").val(); //直属组处长
				var directDeptBId = $("#directDeptBId").val(); //直属部部长

				getSalesRelation(insuranceSalesId,dbSalesId,tjSalesId,ycCFirstGenerId,ycCSecondGenerId,ycBFirstGenerId,ycBSecondGenerId,sjSalesId,jcSalesId,fdSalesId,directGroupCId,directDeptBId);



});


		function getSalesRelation(insuranceSalesId,dbSalesId,tjSalesId,ycCFirstGenerId,ycCSecondGenerId,ycBFirstGenerId,ycBSecondGenerId,sjSalesId,jcSalesId,fdSalesId,directGroupCId,directDeptBId){
					$.ajax({
						type: "POST",
						url: "insuranceSalesInfo/insuranceSalesRelationList",
						data:{insuranceSalesId:insuranceSalesId,dbSalesId:dbSalesId,tjSalesId:tjSalesId,ycCFirstGener:ycCFirstGenerId,ycCSecondGener:ycCSecondGenerId,ycBFirstGener:ycBFirstGenerId,ycBSecondGener:ycBSecondGenerId,sjSalesId:sjSalesId,jcSalesId:jcSalesId,fdSalesId:fdSalesId,directGroupC:directGroupCId,directDeptB:directDeptBId},
						dataType: "json",
						success: function(data){
						  // alert(data.rows);
						   var sales = data;
							 $.each(sales, function(key, value) {
							     if(value.INSURANCE_SALES_ID == insuranceSalesId){
									 $("#insuranceSalesId").attr("gradeId",value.SALES_GRADE_ID);
									  $("#insuranceSalesId").attr("nextgradeId",value.NEXT_SALES_GRADE_ID);
								 }
								 if(value.INSURANCE_SALES_ID == dbSalesId){
									 $("#dbSales").val(value.INSURANCE_SALER);
									 $("#dbSalesNo").val(value.INSURANCE_SALER_NO);
									 $("#dbSalesOrg").html(value.SALES_ORG_NAME);
									 $("#dbSalesTeam").html(value.SALES_TEAM_NAME);
									 $("#dbSalesGrade").html(value.SALES_GRADE_NAME);
									 $("#dbSalesGradeId").attr("gradeId",value.SALES_GRADE_ID);
									  $("#dbSalesGradeId").attr("nextgradeId",value.NEXT_SALES_GRADE_ID);
								 }
								 if(value.INSURANCE_SALES_ID == tjSalesId){
									 $("#tjSales").val(value.INSURANCE_SALER);
									 $("#tjSalesNo").val(value.INSURANCE_SALER_NO);
									 $("#tjSalesOrg").html(value.SALES_ORG_NAME);
									 $("#tjSalesTeam").html(value.SALES_TEAM_NAME);
									 $("#tjSalesGrade").html(value.SALES_GRADE_NAME);
									  $("#tjSalesGradeId").attr("gradeId",value.SALES_GRADE_ID);
									  $("#tjSalesGradeId").attr("nextgradeId",value.NEXT_SALES_GRADE_ID);
								 }
								 if(value.INSURANCE_SALES_ID == ycCFirstGenerId){
									 $("#ycCFirstGener").val(value.INSURANCE_SALER);
									 $("#ycCFirstGenerNo").val(value.INSURANCE_SALER_NO);
									 $("#ycCFirstGenerOrg").html(value.SALES_ORG_NAME);
									 $("#ycCFirstGenerTeam").html(value.SALES_TEAM_NAME);
									 $("#ycCFirstGenerGrade").html(value.SALES_GRADE_NAME);
									  $("#ycCFirstGenerGradeId").attr("gradeId",value.SALES_GRADE_ID);
									  $("#ycCFirstGenerGradeId").attr("nextgradeId",value.NEXT_SALES_GRADE_ID);
								 }
								 if(value.INSURANCE_SALES_ID == ycCSecondGenerId){
									 $("#ycCSecondGener").val(value.INSURANCE_SALER);
									 $("#ycCSecondGenerNo").val(value.INSURANCE_SALER_NO);
									 $("#ycCSecondGenerOrg").html(value.SALES_ORG_NAME);
									 $("#ycCSecondGenerTeam").html(value.SALES_TEAM_NAME);
									 $("#ycCSecondGenerGrade").html(value.SALES_GRADE_NAME);
									  $("#ycCSecondGenerGradeId").attr("gradeId",value.SALES_GRADE_ID);
									  $("#ycCSecondGenerGradeId").attr("nextgradeId",value.NEXT_SALES_GRADE_ID);
								 }
								 if(value.INSURANCE_SALES_ID == ycBFirstGenerId){
									 $("#ycBFirstGener").val(value.INSURANCE_SALER);
									 $("#ycBFirstGenerNo").val(value.INSURANCE_SALER_NO);
									 $("#ycBFirstGenerOrg").html(value.SALES_ORG_NAME);
									 $("#ycBFirstGenerTeam").html(value.SALES_TEAM_NAME);
									 $("#ycBFirstGenerGrade").html(value.SALES_GRADE_NAME);
									  $("#ycBFirstGenerGradeId").attr("gradeId",value.SALES_GRADE_ID);
									  $("#ycBFirstGenerGradeId").attr("nextgradeId",value.NEXT_SALES_GRADE_ID);
								 }
								 if(value.INSURANCE_SALES_ID == ycBSecondGenerId){
									 $("#ycBSeconGener").val(value.INSURANCE_SALER);
									 $("#ycBSeconGenerNo").val(value.INSURANCE_SALER_NO);
									 $("#ycBSeconGenerOrg").html(value.SALES_ORG_NAME);
									 $("#ycBSeconGenerTeam").html(value.SALES_TEAM_NAME);
									 $("#ycBSeconGenerGrade").html(value.SALES_GRADE_NAME);
									  $("#ycBSeconGenerGradeId").attr("gradeId",value.SALES_GRADE_ID);
									  $("#ycBSeconGenerGradeId").attr("nextgradeId",value.NEXT_SALES_GRADE_ID);
								 }
								 if(value.INSURANCE_SALES_ID == sjSalesId){
									 $("#sjSales").val(value.INSURANCE_SALER);
									 $("#sjSalesNo").val(value.INSURANCE_SALER_NO);
									 $("#sjSalesOrg").html(value.SALES_ORG_NAME);
									 $("#sjSalesTeam").html(value.SALES_TEAM_NAME);
									 $("#sjSalesGrade").html(value.SALES_GRADE_NAME);
									  $("#sjSalesGradeId").attr("gradeId",value.SALES_GRADE_ID);
									  $("#sjSalesGradeId").attr("nextgradeId",value.NEXT_SALES_GRADE_ID);
								 }
								 if(value.INSURANCE_SALES_ID == jcSalesId){
									 $("#jcSales").val(value.INSURANCE_SALER);
									 $("#jcSalesNo").val(value.INSURANCE_SALER_NO);
									 $("#jcSalesOrg").html(value.SALES_ORG_NAME);
									 $("#jcSalesTeam").html(value.SALES_TEAM_NAME);
									 $("#jcSalesGrade").html(value.SALES_GRADE_NAME);
									  $("#jcSalesGradeId").attr("gradeId",value.SALES_GRADE_ID);
									  $("#jcSalesGradeId").attr("nextgradeId",value.NEXT_SALES_GRADE_ID);
								 }
								 if(value.INSURANCE_SALES_ID == fdSalesId){
									 $("#fdSales").val(value.INSURANCE_SALER);
									 $("#fdSalesNo").val(value.INSURANCE_SALER_NO);
									 $("#fdSalesOrg").html(value.SALES_ORG_NAME);
									 $("#fdSalesTeam").html(value.SALES_TEAM_NAME);
									 $("#fdSalesGrade").html(value.SALES_GRADE_NAME);
									  $("#fdSalesGradeId").attr("gradeId",value.SALES_GRADE_ID);
									  $("#fdSalesGradeId").attr("nextgradeId",value.NEXT_SALES_GRADE_ID);
								 }
								 if(value.INSURANCE_SALES_ID == directGroupCId){
									 $("#directGroupC").val(value.INSURANCE_SALER);
									 $("#directGroupCNo").val(value.INSURANCE_SALER_NO);
									 $("#directGroupCOrg").html(value.SALES_ORG_NAME);
									 $("#directGroupCTeam").html(value.SALES_TEAM_NAME);
									 $("#directGroupCGrade").html(value.SALES_GRADE_NAME);
									  $("#directGroupCGradeId").attr("gradeId",value.SALES_GRADE_ID);
									  $("#directGroupCGradeId").attr("nextgradeId",value.NEXT_SALES_GRADE_ID);
								 }
								 if(value.INSURANCE_SALES_ID == directDeptBId){
									 $("#directDeptB").val(value.INSURANCE_SALER);
									 $("#directDeptBNo").val(value.INSURANCE_SALER_NO);
									 $("#directDeptBOrg").html(value.SALES_ORG_NAME);
									 $("#directDeptBTeam").html(value.SALES_TEAM_NAME);
									 $("#directDeptBGrade").html(value.SALES_GRADE_NAME);
									  $("#directDeptBGradeId").attr("gradeId",value.SALES_GRADE_ID);
									  $("#directDeptBGradeId").attr("nextgradeId",value.NEXT_SALES_GRADE_ID);
								 }
							});

						}
					});
				}


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
	  /*删除员工姓名取消相关值*/
     $("#dbSales,#tjSales,#ycCFirstGener,#ycCSecondGener,#ycBFirstGener,#ycBSeconGener,#sjSales,#jcSales,#fdSales,#directGroupC,#directDeptB").bind('input propertychange', function() {
		          $(this).parent("td").next("input").val("");
				   $(this).parent("td").next().next().find("input").val("");
			       $(this).parent("td").next().next().next().html("");
				   $(this).parent("td").next().next().next().next().html("");
				   $(this).parent("td").next().next().next().next().next().html("");
				   $(this).parent("td").next().next().next().next().next().next().find("input").val("");
		           $(this).parent("td").next().next().next().next().next().next().find("input").parent("td").next("input").attr("gradeId","");
		           $(this).parent("td").next().next().next().next().next().next().find("input").parent("td").next("input").attr("nextgradeId","");
	});
     /*删除员工编号 重置*/
      $("#dbSalesNo,#tjSalesNo,#ycCFirstGenerNo,#ycCSecondGenerNo,#ycBFirstGenerNo,#ycBSeconGenerNo,#sjSalesNo,#jcSalesNo,#fdSalesNo,#directGroupCNo,#directDeptBNo").bind('input propertychange', function() {
		         $(this).parent("td").prev("input").prev().find("input").val("");
				    $(this).parent("td").prev("input").val("");
			       $(this).parent("td").next().html("");
				   $(this).parent("td").next().next().html("");
				   $(this).parent("td").next().next().next().html("");
				   $(this).parent("td").next().next().next().next().find("input").val("");
		           $(this).parent("td").next().next().next().next().next().next().find("input").parent("td").next("input").attr("gradeId","");
		           $(this).parent("td").next().next().next().next().next().next().find("input").parent("td").next("input").attr("nextgradeId","");
	});
	  /*输入员工姓名自动填充*/
		 $("#dbSales,#tjSales,#ycCFirstGener,#ycCSecondGener,#ycBFirstGener,#ycBSeconGener,#sjSales,#jcSales,#fdSales,#directGroupC,#directDeptB").typeahead({
			// minLength:3,
			 items:8,
                source: function (query, process) {
					var parameter = {query: query};
					$.post('insuranceSalesInfo/insuranceSalesRelationList', parameter, function (data) {
						process(data);
					});
				   },
			   displayText:function(item){
				return item.name_team;
			   },
			   updater: function (item) {
			       var mydate = new Date();
					var myMonth = mydate.getMonth()+2;
					if (myMonth < 10){
						myMonth = "0"+myMonth;
					}
					var formatMyDate  = mydate.getFullYear()+"-"+myMonth+"-01";

			       debugger
			       var id =this.$element[0].id;
			        var cal_org_id = $("#insuranceSalesId").attr("cal_org_id");
				    if(id == "ycCFirstGener" || id == "ycCSecondGener"){
				        if (cal_org_id == "5"){
				             if (item.NEXT_SALES_GRADE_ID != "3" && item.NEXT_SALES_GRADE_ID != "2"){
							   $.alert({
								title: '提示信息！',
								content: '处育成人选择错误，只能选择次月为处长或部长职级的人员!',
								type: 'red'
							   });
							   return;
							}
						}else if (item.NEXT_SALES_GRADE_ID != "3" ){
				             $.alert({
								title: '提示信息！',
								content: '处育成人选择错误，只能选择次月为处长职级的人员!',
								type: 'red'
							});
							   return;
						}
					}


				    if(id == "ycBFirstGener" || id == "ycBSeconGener"){
                       if (item.NEXT_SALES_GRADE_ID != "2"){
                           $.alert({
                            title: '提示信息！',
                            content: '部育成人选择错误，只能选择次月为部长职级的人员!',
                            type: 'red'
                        });
                           return;
                      }
				   }
				       if(id == "directGroupC" ){
                       if (item.NEXT_SALES_GRADE_ID != "2" && item.NEXT_SALES_GRADE_ID != "3"){
                           $.alert({
                            title: '提示信息！',
                            content: '直辖组选择错误，只能选择次月为处长或者部长职级的人员!',
                            type: 'red'
                        });
                           return;
                      }
				   }
				    if(id == "directDeptB"){
                       if (item.NEXT_SALES_GRADE_ID != "2"){
                           $.alert({
                            title: '提示信息！',
                            content: '直辖部选择错误，只能选择次月为部长职级的人员!',
                            type: 'red'
                        });
                           return;
                      }
				   }
				    if(id == "sjSales"){
				        debugger;
				        var sjFlag = true;
				        var SalesLevel  = $("#insuranceSalesId").attr("nextgradeId");
				        var sjLevel = item.NEXT_SALES_GRADE_ID;
                          if(SalesLevel=="1" || SalesLevel=="2" || SalesLevel=="7"){
                            if(sjLevel!="1"){sjFlag=false};
                        }else if(SalesLevel=="3" || SalesLevel=="6"){
                            if(sjLevel!="2" && sjLevel!="7"){sjFlag=false};
                        }else if(SalesLevel=="4" || SalesLevel=="5"){
                            if(sjLevel!="3" && sjLevel!="6"){sjFlag=false};
                        }
                        if(!sjFlag){
                            $.alert({
                                title: '提示信息！',
                                content: '上级选择错误，上级职级不能跨级或低于员工职级!',
                                type: 'red'
                            });
                            return ;
                        }
				   }
				    $("#"+id).parent("td").next("input").val(item.INSURANCE_SALES_ID);
				   $("#"+id).parent("td").next().next().find("input").val(item.INSURANCE_SALER_NO);
			       $("#"+id).parent("td").next().next().next().html(item.SALES_ORG_NAME);
				   $("#"+id).parent("td").next().next().next().next().html(item.SALES_TEAM_NAME);
				   $("#"+id).parent("td").next().next().next().next().next().html(item.SALES_GRADE_NAME);
				   $("#"+id).parent("td").next().next().next().next().next().next().find("input").val(formatMyDate);
				    $("#"+id).parent("td").next().next().next().next().next().next().find("input").parent("td").next("input").attr("gradeId",item.SALES_GRADE_ID);
				    $("#"+id).parent("td").next().next().next().next().next().next().find("input").parent("td").next("input").attr("nextgradeId",item.NEXT_SALES_GRADE_ID);
                return item;

               },
			 afterSelect: function (item) {
                    //选择项之后的事件 ，item是当前选中的。
				   var id =this.$element[0].id
				  $("#"+id).val(item.INSURANCE_SALER);
                },
            });
		 /*员工编号自动填充*/
		 $("#dbSalesNo,#tjSalesNo,#ycCFirstGenerNo,#ycCSecondGenerNo,#ycBFirstGenerNo,#ycBSeconGenerNo,#sjSalesNo,#jcSalesNo,#fdSalesNo,#directGroupCNo,#directDeptBNo").typeahead({
			// minLength:3,
			 items:8,
                source: function (query, process) {
					var parameter = {queryNo: query};
					$.post('insuranceSalesInfo/insuranceSalesRelationList', parameter, function (data) {
						process(data);
					});
				   },
			   displayText:function(item){
				return item.INSURANCE_SALER_NO;
			   },
			   updater: function (item) {
			         var mydate = new Date();
					var myMonth = mydate.getMonth()+2;
					if (myMonth < 10){
						myMonth = "0"+myMonth;
					}
					var formatMyDate  = mydate.getFullYear()+"-"+myMonth+"-01";
			       debugger
			       var id =this.$element[0].id;
			        var cal_org_id = $("#insuranceSalesId").attr("cal_org_id");
				    if(id == "ycCFirstGenerNo" || id == "ycCSecondGenerNo"){
                       if (cal_org_id == "5"){
				             if (item.NEXT_SALES_GRADE_ID != "3" && item.NEXT_SALES_GRADE_ID != "2"){
							   $.alert({
								title: '提示信息！',
								content: '处育成人选择错误，只能选择次月为处长或部长职级的人员!',
								type: 'red'
							   });
							   return;
							}
						}else if (item.NEXT_SALES_GRADE_ID != "3" ){
				             $.alert({
								title: '提示信息！',
								content: '处育成人选择错误，只能选择次月为处长职级的人员!',
								type: 'red'
							});
							   return;
						}
				   }
				    if(id == "ycBFirstGenerNo" || id == "ycBSeconGenerNo"){
                       if (item.NEXT_SALES_GRADE_ID != "2"){
                           $.alert({
                            title: '提示信息！',
                            content: '部育成人选择错误，只能选择部职级的人员!',
                            type: 'red'
                        });
                           return;
                      }
				   }
				       if(id == "directGroupCNo" ){
                       if (item.NEXT_SALES_GRADE_ID != "2" && item.NEXT_SALES_GRADE_ID != "3"){
                           $.alert({
                            title: '提示信息！',
                            content: '直辖组选择错误，只能选择处长或者部长职级的人员!',
                            type: 'red'
                        });
                           return;
                      }
				   }
				    if(id == "directDeptBNo"){
                       if (item.NEXT_SALES_GRADE_ID != "2"){
                           $.alert({
                            title: '提示信息！',
                            content: '直辖部选择错误，只能选择部职级的人员!',
                            type: 'red'
                        });
                           return;
                      }
				   }
				    if(id == "sjSalesNo"){
				        debugger;
				        var sjFlag = true;
				        var SalesLevel  = $("#insuranceSalesId").attr("nextgradeId");
				        var sjLevel = item.NEXT_SALES_GRADE_ID;
                          if(SalesLevel=="1" || SalesLevel=="2" || SalesLevel=="7"){
                            if(sjLevel!="1"){sjFlag=false};
                        }else if(SalesLevel=="3" || SalesLevel=="6"){
                            if(sjLevel!="2" && sjLevel!="7"){sjFlag=false};
                        }else if(SalesLevel=="4" || SalesLevel=="5"){
                            if(sjLevel!="3" && sjLevel!="6"){sjFlag=false};
                        }
                        if(!sjFlag){
                            $.alert({
                                title: '提示信息！',
                                content: '上级选择错误，上级职级不能跨级或低于员工职级!',
                                type: 'red'
                            });
                            return ;
                        }
				   }
				    $("#"+id).parent("td").prev("input").prev().find("input").val(item.name_team);
				    $("#"+id).parent("td").prev("input").val(item.INSURANCE_SALES_ID);
			       $("#"+id).parent("td").next().html(item.SALES_ORG_NAME);
				   $("#"+id).parent("td").next().next().html(item.SALES_TEAM_NAME);
				   $("#"+id).parent("td").next().next().next().html(item.SALES_GRADE_NAME);
				   $("#"+id).parent("td").next().next().next().next().find("input").val(formatMyDate);
				   $("#"+id).parent("td").next().next().next().next().find("input").parent("td").next("input").attr("gradeId",item.SALES_GRADE_ID);
				   $("#"+id).parent("td").next().next().next().next().find("input").parent("td").next("input").attr("nextgradeId",item.NEXT_SALES_GRADE_ID);
                return item;

               }

            });



        function add(type) {
            var flag = true;
			/*debugger;
			if ($("#tjSales").val() == "") {
                $.alert({
                    title: '提示信息！',
                    content: '推荐人不能为空!',
                    type: 'red'
                });
                flag = false;
                return false;
            }*/

				$("#type").val(type);

				if(flag){
					$.ajax({
						url:'insuranceSalesInfo/updateSalesRelation',
						dataType:'json',
						type:'post',
						data:$("#addForm").serialize(),
						beforeSend: function () {
						     $("#tjSalesId").removeAttr('disabled');
						      $("#tjSalesNo").removeAttr('disabled');

								$("#effective_now").attr('disabled', 'disabled');
								$("#effective_next").attr('disabled', 'disabled');
						},
					   complete: function () {
						     $("#tjSalesId").attr('disabled', 'disabled');
						      $("#tjSalesNo").attr('disabled', 'disabled');

						   $("#effective_now").removeAttr('disabled');
						   $("#effective_next").attr('disabled', 'disabled');
					   },
						success:function(data){
							if("200" == data.messageCode){
								windowCloseTab();
								$.alert({
									 title: '提示信息！',
									 content: '添加成功!',
									 type: 'blue'
								});

							}else{
								$.alert({
									title: '提示信息！',
									content: '添加失败！'+data.data,
									type: 'red'
								});
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

		
	</script>
 
</head>
<body>

<%--用于判读是否有修改推荐人的权限--%>
<shiro:hasPermission name="salerRelationUpdate:updateTiSaler">
    	<input type="hidden" name="shiro" id="shiro" value="111">
</shiro:hasPermission>
<!--列表 -->
<label style="color: #eea236">${list.INSURANCE_SALER}:&lt;${list.SALES_TEAM_NAME},${list.SALES_GRADE_NAME}&gt;</label>
<div style="width: 99%;overflow: auto;">

		<form class="form-horizontal" id="addForm"  method="post">
            <div class="form-group">
			<input type="hidden" id="insuranceSalesId" name="insuranceSalesId" value="${insuranceSalesInfo.insuranceSalesId}" gradeId = "" nextgradeId ="" cal_org_id="${list.cal_org_id}"/>
				<input type="hidden" id="type" name="type" value="" />
	      </div>
            <div class="form-group">
            <table id="SalesShip-table" class="table table-hover table-striped table-condensed table-bordered">
		<tr>
			<td style="width:100px;text-align:center;" bgcolor="#f5f5dc">关系</td>
			<td style="width:120px;text-align:center;" bgcolor="#f5f5dc">销售人员</td>
			<td style="width:150px;text-align:center;" bgcolor="#f5f5dc">销售人员工号</td>
			<td style="width:200px;text-align:center;" bgcolor="#f5f5dc">组织机构</td>
			<td style="width:150px;text-align:center;" bgcolor="#f5f5dc">营销团队</td>
			<td style="width:130px;text-align:center;" bgcolor="#f5f5dc">销售职级</td>
			<td style="width:130px;text-align:center;" bgcolor="#f5f5dc">关系确立日期</td>
		</tr>
		<tr>
			<td style="width:100px;text-align:center;">担保人</td>
			<td style="width:120px;text-align:center;" >
				<input class="form-control form-control-static" autocomplete="off" id="dbSales" name="dbSales" data-provide="typeahead" value="" type="text" placeholder="员工姓名" />
			</td>
			<input  id="dbSalesId" name="dbSalesId" value="${insuranceSalesInfo.dbSalesId}" type="hidden" />
			<td style="width:150px;text-align:center;">
				<input class="form-control form-control-static" id="dbSalesNo" name="dbSalesNo" value="" type="text"  data-provide="typeahead" autocomplete="off" placeholder="员工编号" />
			</td>
			<td style="width:200px;text-align:center;" id="dbSalesOrg"></td>
			<td style="width:150px;text-align:center;" id="dbSalesTeam"></td>
			<td style="width:130px;text-align:center;" id="dbSalesGrade"></td>
			<td style="width:130px;text-align:center;">
				<input class="form-control form-control-static"  readonly = "readonly" id="dbSalesDate" name="dbSalesDate" value="${insuranceSalesInfo.dbSalesDate}" type="text" placeholder=""/>
			</td>
			<input type="hidden" id="dbSalesGradeId" gradeId = "" nextgradeId = ""/>
		</tr>

		<tr>
			<td style="width:100px;text-align:center;">推荐人</td>
			<td style="width:120px;text-align:center;" >
				<input  class="form-control form-control-static" autocomplete="off" id="tjSales" name="tjSales" data-provide="typeahead" value="" type="text" placeholder="员工姓名" />
            </td>
			<input  id="tjSalesId" name="tjSalesId" value="${insuranceSalesInfo.tjSalesId}" type="hidden" />
			<td style="width:150px;text-align:center;" >
				<input  class="form-control form-control-static" id="tjSalesNo" name="tjSalesNo" value="" type="text" data-provide="typeahead" autocomplete="off" placeholder="员工编号" />
            </td>
			<td style="width:200px;text-align:center;" id="tjSalesOrg"></td>
			<td style="width:150px;text-align:center;" id="tjSalesTeam"></td>
			<td style="width:130px;text-align:center;" id="tjSalesGrade"></td>
			<td style="width:130px;text-align:center;" >
				<input class="form-control form-control-static"  readonly = "readonly" id="tjSalesDate" name="tjSalesDate" value="${insuranceSalesInfo.tjSalesDate}" type="text" placeholder=""/>
			</td>
			<input type="hidden" id="tjSalesGradeId" gradeId = "" nextgradeId = ""/>
		</tr>
		<tr>
			<td style="width:100px;text-align:center;">一级处育成人</td>
			<td style="width:120px;text-align:center;" >
					<input  class="form-control form-control-static" autocomplete="off" id="ycCFirstGener" name="$ycCFirstGener" data-provide="typeahead" value="" type="text" placeholder="员工姓名" />
			</td>
			<input  id="ycCFirstGenerId" name="ycCFirstGener" value="${insuranceSalesInfo.ycCFirstGener}" type="hidden" />
			<td style="width:150px;text-align:center;" >
				<input  class="form-control form-control-static" id="ycCFirstGenerNo" name="ycCFirstGenerNo" value="" type="text" data-provide="typeahead" autocomplete="off" placeholder="员工编号" />
			</td>
			<td style="width:200px;text-align:center;" id="ycCFirstGenerOrg"></td>
			<td style="width:150px;text-align:center;" id="ycCFirstGenerTeam"></td>
			<td style="width:130px;text-align:center;" id="ycCFirstGenerGrade"></td>
			<td style="width:130px;text-align:center;" >
				<input class="form-control form-control-static"  readonly = "readonly" id="ycCFirstGenerDate" name="establishTime" value="${insuranceSalesInfo.establishTime}" type="text" placeholder=""/>
			</td>
			<input type="hidden" id="ycCFirstGenerGradeId" gradeId = "" nextgradeId = ""/>
		</tr>
		<tr>
			<td style="width:100px;text-align:center;">二级处育成人</td>
			<td style="width:120px;text-align:center;" >
				<input  class="form-control form-control-static" autocomplete="off" id="ycCSecondGener" name="$ycCSecondGener" data-provide="typeahead" value="" type="text" placeholder="员工姓名" />
			</td>
			<input  id="ycCSecondGenerId" name="ycCSecondGener" value="${insuranceSalesInfo.ycCSecondGener}" type="hidden" />
			<td style="width:150px;text-align:center;" >
				<input  class="form-control form-control-static" id="ycCSecondGenerNo" name="ycCSecondGenerNo" value="" type="text"  data-provide="typeahead" autocomplete="off" placeholder="员工编号" />
			</td>
			<td style="width:200px;text-align:center;" id="ycCSecondGenerOrg"></td>
			<td style="width:150px;text-align:center;" id="ycCSecondGenerTeam"></td>
			<td style="width:130px;text-align:center;" id="ycCSecondGenerGrade"></td>
			<td style="width:130px;text-align:center;" >
				<input class="form-control form-control-static"  readonly = "readonly" id="ycCSecondGenerDate" name="establishTime1" value="${insuranceSalesInfo.establishTime1}" type="text" placeholder=""/>
			</td>
			<input type="hidden" id="ycCSecondGenerGradeId" gradeId = "" nextgradeId = ""/>
		</tr>
		<tr>
			<td style="width:100px;text-align:center;">一级部育成人</td>
			<td style="width:120px;text-align:center;" >
				<input  class="form-control form-control-static" autocomplete="off" id="ycBFirstGener" name="$ycBFirstGener" data-provide="typeahead" value="" type="text" placeholder="员工姓名" />
			</td>
			<input  id="ycBFirstGenerId" name="ycBFirstGener" value="${insuranceSalesInfo.ycBFirstGener}" type="hidden" />
			<td style="width:150px;text-align:center;" >
				<input  class="form-control form-control-static" id="ycBFirstGenerNo" name="ycBFirstGenerNo" value="" type="text" data-provide="typeahead" autocomplete="off" placeholder="员工编号" />
			</td>
			<td style="width:200px;text-align:center;" id="ycBFirstGenerOrg"></td>
			<td style="width:150px;text-align:center;" id="ycBFirstGenerTeam"></td>
			<td style="width:130px;text-align:center;" id="ycBFirstGenerGrade"></td>
			<td style="width:130px;text-align:center;">
				<input class="form-control form-control-static"  readonly = "readonly" id="ycBFirstGenerDate" name="establishTime2" value="${insuranceSalesInfo.establishTime2}" type="text" placeholder=""/>
			</td>
			<input type="hidden" id="ycBFirstGenerGradeId" gradeId = "" nextgradeId = ""/>
		</tr>
		<tr>
			<td style="width:100px;text-align:center;">二级部育成人</td>
			<td style="width:120px;text-align:center;">
				<input  class="form-control form-control-static" autocomplete="off" id="ycBSeconGener" name="$ycBSeconGener" data-provide="typeahead" value="" type="text" placeholder="员工姓名" />
			</td>
			<input  id="ycBSeconGenerId" name="ycBSecondGener" value="${insuranceSalesInfo.ycBSecondGener}" type="hidden" />
			<td style="width:150px;text-align:center;" >
				<input  class="form-control form-control-static" id="ycBSeconGenerNo" name="ycBSeconGenerNo" value="" type="text"  data-provide="typeahead" autocomplete="off" placeholder="员工编号" />
			</td>
			<td style="width:200px;text-align:center;" id="ycBSeconGenerOrg"></td>
			<td style="width:150px;text-align:center;" id="ycBSeconGenerTeam"></td>
			<td style="width:130px;text-align:center;" id="ycBSeconGenerGrade"></td>
			<td style="width:130px;text-align:center;" >
				<input class="form-control form-control-static"  readonly = "readonly" id="ycBSeconGenerDate" name="establishTime3" value="${insuranceSalesInfo.establishTime3}" type="text" placeholder=""/>
			</td>
			<input type="hidden" id="ycBSeconGenerGradeId" gradeId = "" nextgradeId = ""/>
		</tr>
		<tr>
			<td style="width:100px;text-align:center;">上级管理人</td>
			<td style="width:120px;text-align:center;" >
				<input class="form-control form-control-static" autocomplete="off" id="sjSales" name="sjSales" data-provide="typeahead" value="" type="text" placeholder="员工姓名" />
			</td>
			<input  id="sjSalesId" name="sjSalesId" value="${insuranceSalesInfo.sjSalesId}" type="hidden" />
			<td style="width:150px;text-align:center;" >
				<input class="form-control form-control-static" id="sjSalesNo" name="sjSalesNo" value="" type="text" data-provide="typeahead" autocomplete="off" placeholder="员工编号" />
			</td>
			<td style="width:200px;text-align:center;" id="sjSalesOrg"></td>
			<td style="width:150px;text-align:center;" id="sjSalesTeam"></td>
			<td style="width:130px;text-align:center;" id="sjSalesGrade"></td>
			<td style="width:130px;text-align:center;" >
				<input class="form-control form-control-static"  readonly = "readonly" id="sjSalesDate" name="sjSalesDate" value="${insuranceSalesInfo.sjSalesDate}" type="text" placeholder=""/>
			</td>
			<input type="hidden" id="sjSalesGradeId" gradeId = "" nextgradeId = ""/>
		</tr>
		<tr>
			<td style="width:100px;text-align:center;">继承人</td>
			<td style="width:120px;text-align:center;" >
				<input class="form-control form-control-static" autocomplete="off" id="jcSales" name="jcSales" data-provide="typeahead" value="" type="text" placeholder="员工姓名" />
			</td>
			<input  id="jcSalesId" name="jcSalesId" value="${insuranceSalesInfo.jcSalesId}" type="hidden" />
			<td style="width:150px;text-align:center;" >
				<input class="form-control form-control-static" id="jcSalesNo" name="jcSalesNo" value="" type="text"  data-provide="typeahead" autocomplete="off" placeholder="员工编号" />
			</td>
			<td style="width:200px;text-align:center;" id="jcSalesOrg"></td>
			<td style="width:150px;text-align:center;" id="jcSalesTeam"></td>
			<td style="width:130px;text-align:center;" id="jcSalesGrade"></td>
			<td style="width:130px;text-align:center;" >
				<input class="form-control form-control-static"  readonly = "readonly" id="jcSalesDate" name="jcSalesDate" value="${insuranceSalesInfo.jcSalesDate}" type="text" placeholder=""/>
			</td>
			<input type="hidden" id="jcSalesGradeId" gradeId = "" nextgradeId = ""/>
		</tr>
		<tr>
			<td style="width:100px;text-align:center;">辅导人</td>
			<td style="width:120px;text-align:center;" >
				<input class="form-control form-control-static" autocomplete="off" id="fdSales" name="fdSales" data-provide="typeahead" value="" type="text" placeholder="员工姓名" />
			</td>
			<input  id="fdSalesId" name="fdSalesId" value="${insuranceSalesInfo.fdSalesId}" type="hidden" />
			<td style="width:150px;text-align:center;" >
				<input class="form-control form-control-static" id="fdSalesNo" name="fdSalesNo" value="" type="text" data-provide="typeahead" autocomplete="off" placeholder="员工编号" />
			</td>
			<td style="width:200px;text-align:center;" id="fdSalesOrg"></td>
			<td style="width:150px;text-align:center;" id="fdSalesTeam"></td>
			<td style="width:130px;text-align:center;" id="fdSalesGrade"></td>
			<td style="width:130px;text-align:center;" >
				<input class="form-control form-control-static"  readonly = "readonly" id="fdSalesDate" name="fdSalesDate" value="${insuranceSalesInfo.fdSalesDate}" type="text" placeholder=""/>
			</td>
			<input type="hidden" id="fdSalesGradeId" gradeId = "" nextgradeId = ""/>
		</tr>
		<tr>
			<td style="width:100px;text-align:center;">直辖组处长/业务经理</td>
			<td style="width:120px;text-align:center;" >
				<input  class="form-control form-control-static" autocomplete="off" id="directGroupC" name="$directGroupC" data-provide="typeahead" value="" type="text" placeholder="员工姓名" />
			</td>
			<input  id="directGroupCId" name="directGroupC" value="${insuranceSalesInfo.directGroupC}" type="hidden" />
			<td style="width:150px;text-align:center;" >
				<input   class="form-control form-control-static" id="directGroupCNo" name="directGroupCNo" value="" type="text" data-provide="typeahead" autocomplete="off" placeholder="员工编号" />
			</td>
			<td style="width:200px;text-align:center;" id="directGroupCOrg"></td>
			<td style="width:150px;text-align:center;" id="directGroupCTeam"></td>
			<td style="width:130px;text-align:center;" id="directGroupCGrade"></td>
			<td style="width:130px;text-align:center;" >
				<input class="form-control form-control-static"  readonly = "readonly" id="directGroupCDate" name="establishTime4" value="${insuranceSalesInfo.establishTime4}" type="text" placeholder=""/>
			</td>
			<input type="hidden" id="directGroupCGradeId" gradeId = "" nextgradeId = ""/>
		</tr>
		<tr>
			<td style="width:100px;text-align:center;">直辖部部长/业务总监</td>
			<td style="width:120px;text-align:center;" >
				<input   class="form-control form-control-static" autocomplete="off" id="directDeptB" name="$directDeptB" data-provide="typeahead" value="" type="text" placeholder="员工姓名" />
			</td>
			<input  id="directDeptBId" name="directDeptB" value="${insuranceSalesInfo.directDeptB}" type="hidden" />
			<td style="width:150px;text-align:center;" >
				<input   class="form-control form-control-static " id="directDeptBNo" name="directDeptBNo" value="" type="text" data-provide="typeahead" autocomplete="off" placeholder="员工编号" />
			</td>
			<td style="width:200px;text-align:center;" id="directDeptBOrg"></td>
			<td style="width:150px;text-align:center;" id="directDeptBTeam"></td>
			<td style="width:130px;text-align:center;" id="directDeptBGrade"></td>
			<td style="width:130px;text-align:center;" >
				<input class="form-control form-control-static"  readonly = "readonly" id="directDeptBDate" name="establishTime5" value="${insuranceSalesInfo.establishTime5}" type="text" placeholder=""/>
			</td>
			<input type="hidden" id="directDeptBGradeId" gradeId = "" nextgradeId = ""/>
		</tr>
	</table>
    </div>
	 <div class="modal-footer col-md-6">
            <!--用来清空表单数据-->
               <button id="effective_next" type="button" onclick="add(2)" class="btn btn-primary">次月生效</button>
            </div>
	  </form>
</div>
<!--toolbar  -->

</body>
</html>