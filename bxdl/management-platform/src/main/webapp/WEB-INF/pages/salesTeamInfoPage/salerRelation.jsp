<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/core.jsp" %>
<!DOCTYPE  html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <title>设置员工关系</title>
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Cache" content="no-cache">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   	<link rel="stylesheet" href="${path}/css/system/role/role.css">


   	<script src="${path}/js/system/comm.js" charset="UTF-8" type="text/javascript"></script>
    <script src="${path}/static/js/bootstrap.js"></script>
	<script src="${path}/js/bootstrap3-typeahead.js" type="text/javascript"></script>
	<script type="text/javascript">
		$(function () {
			getSalesGrade("salesGrade");
			if($("#salesGradeId").val() == '2' || $("#salesGradeId").val() == '3'){
			   $("#directGroupCDate").val(new Date().Format("yyyy-MM-dd"));
			}
			if($("#salesGradeId").val() == '2'){
			   $("#directDeptBDate").val(new Date().Format("yyyy-MM-dd"));
			}



});




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
	});
     /*删除员工编号 重置*/
      $("#dbSalesNo,#tjSalesNo,#ycCFirstGenerNo,#ycCSecondGenerNo,#ycBFirstGenerNo,#ycBSeconGenerNo,#sjSalesNo,#jcSalesNo,#fdSalesNo,#directGroupCNo,#directDeptBNo").bind('input propertychange', function() {
		         $(this).parent("td").prev("input").prev().find("input").val("");
				    $(this).parent("td").prev("input").val("");
			       $(this).parent("td").next().html("");
				   $(this).parent("td").next().next().html("");
				   $(this).parent("td").next().next().next().html("");
				   $(this).parent("td").next().next().next().next().find("input").val("");
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
			       debugger;
			        var cal_org_id = $("#insuranceSalesId").attr("cal_org_id");
			       var id =this.$element[0].id
				   if(id == "ycCFirstGener" || id == "ycCSecondGener"){
                       if (cal_org_id == "5"){
                            if (item.SALES_GRADE_ID != "2" && item.SALES_GRADE_ID != "3"){
                                 $.alert({
								title: '提示信息！',
								content: '处育成人选择错误，只能选择处长或部长职级的人员!',
								type: 'red'
							   });
                           return;
							}

						}else if(item.SALES_GRADE_ID != "3"){
                           $.alert({
                            title: '提示信息！',
                            content: '处育成人选择错误，只能选择处长职级的人员!',
                            type: 'red'
                        });
                           return;
                      }
				   }
				    if(id == "ycBFirstGener" || id == "ycBSeconGener"){
                       if (item.SALES_GRADE_ID != "2"){
                           $.alert({
                            title: '提示信息！',
                            content: '部育成人选择错误，只能选择部职级的人员!',
                            type: 'red'
                        });
                           return;
                      }
				   }
				       if(id == "directGroupC" ){
                       if (item.SALES_GRADE_ID != "2" && item.SALES_GRADE_ID != "3"){
                           $.alert({
                            title: '提示信息！',
                            content: '直辖组选择错误，只能选择处长或者部长职级的人员!',
                            type: 'red'
                        });
                           return;
                      }
				   }
				    if(id == "directDeptB"){
                       if (item.SALES_GRADE_ID != "2"){
                           $.alert({
                            title: '提示信息！',
                            content: '直辖部选择错误，只能选择部职级的人员!',
                            type: 'red'
                        });
                           return;
                      }
				   }
				    if(id == "sjSales"){
				        debugger;
				        var sjFlag = true;
				         var SalesLevel =$("#salesGradeId").val();
				        var sjLevel = item.SALES_GRADE_ID;
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
				   $("#"+id).parent("td").next().next().next().next().next().next().find("input").val(new Date().Format("yyyy-MM-dd"));
                return item;

               }
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
			         var cal_org_id = $("#insuranceSalesId").attr("cal_org_id");
			       var id =this.$element[0].id
				   if(id == "ycCFirstGenerNo" || id == "ycCSecondGenerNo"){
                      if (cal_org_id == "5"){
                            if (item.SALES_GRADE_ID != "2" && item.SALES_GRADE_ID != "3"){
                                 $.alert({
								title: '提示信息！',
								content: '处育成人选择错误，只能选择处长或部长职级的人员!',
								type: 'red'
							   });
                           return;
							}

						}else if(item.SALES_GRADE_ID != "3"){
                           $.alert({
                            title: '提示信息！',
                            content: '处育成人选择错误，只能选择处长职级的人员!',
                            type: 'red'
                        });
                           return;
                      }
				   }
				    if(id == "ycBFirstGenerNo" || id == "ycBSeconGenerNo"){
                       if (item.SALES_GRADE_ID != "2"){
                           $.alert({
                            title: '提示信息！',
                            content: '部育成人选择错误，只能选择部长职级的人员!',
                            type: 'red'
                        });
                           return;
                      }
				   }
				    if(id == "directGroupCNo" ){
                       if (item.SALES_GRADE_ID != "2" && item.SALES_GRADE_ID != "3"){
                           $.alert({
                            title: '提示信息！',
                            content: '直辖组选择错误，只能选择处长或者部长职级的人员!',
                            type: 'red'
                        });
                           return;
                      }
				   }
				    if(id == "directDeptBNo"){
                       if (item.SALES_GRADE_ID != "2"){
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
				         var SalesLevel =$("#salesGradeId").val();
				        var sjLevel = item.SALES_GRADE_ID;
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
				   $("#"+id).parent("td").next().next().next().next().find("input").val(new Date().Format("yyyy-MM-dd"));
                return item;

               }
            });


	 //表单验证
      /*  $("#addForm").bootstrapValidator({
            fields: {
                tjSales: {
                    validators: {
                        notEmpty: {
                            message: '推荐人不能为空',
                        }
                    }
                },
                directGroupC: {
                    validators: {
                        notEmpty: {
                            message: '直属组处长不能为空',
                        }
                    }
                },
                directDeptB: {
                    validators: {
                        notEmpty: {
                            message: '直属部部长不能为空',
                        }
                    }
                },
            }
        });*/


        function add() {
				flag = true;
				/*if($("#tjSales").val()==""){
					$.alert({
						title: '提示信息！',
						content: '推荐人不能为空!',
						type: 'red'
					});
					flag = false;
					document.getElementById("saveButton").removeAttribute("disabled");
					return false;
				}*/
				/*直辖部必填*/
					if($("#directDeptB").val()==""){
					$.alert({
						title: '提示信息！',
						content: '直辖部部长不能为空!',
						type: 'red'
					});
					flag = false;
					document.getElementById("saveButton").removeAttribute("disabled");
					return false;
				}
				/*直辖组必填*/
					if($("#directGroupC").val()==""){
					$.alert({
						title: '提示信息！',
						content: '直辖处处长不能为空!',
						type: 'red'
					});
					flag = false;
					document.getElementById("saveButton").removeAttribute("disabled");
					return false;
				}
				if(flag){
					$.ajax({
						url:'insuranceSalesInfo/addSalesRelation',
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
									content: '添加失败！'+data.data,
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

		
	</script>
 
</head>
<body>


<!--列表 -->
<label style="color: #eea236">${insuranceSalesInfo.INSURANCE_SALER}:&lt;${insuranceSalesInfo.SALES_TEAM_NAME},${insuranceSalesInfo.SALES_GRADE_NAME}&gt;</label>
<div style="width: 99%;overflow: auto;">
		<form class="form-horizontal" id="addForm"  method="post">
            <div class="form-group">
			<input type="hidden" id="insuranceSalesId" name="insuranceSalesId" value="${insuranceSalesInfo.INSURANCE_SALES_ID}" cal_org_id="${insuranceSalesInfo.cal_org_id}">
			<input type="hidden" id="salesGradeId" name="salesGradeId" value="${insuranceSalesInfo.SALES_GRADE_ID}">
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
			<input  id="dbSalesId" name="dbSalesId" value="" type="hidden" />
			<td style="width:150px;text-align:center;">
				<input class="form-control form-control-static" id="dbSalesNo" name="dbSalesNo" value="" type="text"  data-provide="typeahead" autocomplete="off" placeholder="员工编号" />
			</td>
			<td style="width:200px;text-align:center;" id="dbSalesOrg"></td>
			<td style="width:150px;text-align:center;" id="dbSalesTeam"></td>
			<td style="width:130px;text-align:center;" id="dbSalesGrade"></td>
			<td style="width:130px;text-align:center;">
				<input class="form-control form-control-static"  readonly = "readonly" id="dbSalesDate" name="dbSalesDate" value="" type="text" placeholder=""/>
			</td>
		</tr>

		<tr>
			<td style="width:100px;text-align:center;">推荐人</td>
			<td style="width:120px;text-align:center;" >
				<input class="form-control form-control-static" autocomplete="off" id="tjSales" name="tjSales" data-provide="typeahead" value="" type="text" placeholder="员工姓名" />
            </td>
			<input  id="tjSalesId" name="tjSalesId" value="" type="hidden" />
			<td style="width:150px;text-align:center;" >
				<input class="form-control form-control-static" id="tjSalesNo" name="tjSalesNo" value="" type="text" data-provide="typeahead" autocomplete="off" placeholder="员工编号" />
            </td>
			<td style="width:200px;text-align:center;" id="tjSalesOrg"></td>
			<td style="width:150px;text-align:center;" id="tjSalesTeam"></td>
			<td style="width:130px;text-align:center;" id="tjSalesGrade"></td>
			<td style="width:130px;text-align:center;" >
				<input class="form-control form-control-static"  readonly = "readonly" id="tjSalesDate" name="tjSalesDate" value="" type="text" placeholder=""/>
			</td>
		</tr>
		<tr>
			<td style="width:100px;text-align:center;">一级处育成人</td>
			<td style="width:120px;text-align:center;" >
					<input <c:if test="${insuranceSalesInfo.SALES_GRADE_ID != '3'}"> disabled="disabled" </c:if> class="form-control form-control-static" autocomplete="off" id="ycCFirstGener" name="$ycCFirstGener" data-provide="typeahead" value="" type="text" placeholder="员工姓名" />
			</td>
			<input  id="ycCFirstGenerId" name="ycCFirstGener" value="" type="hidden" />
			<td style="width:150px;text-align:center;" >
				<input <c:if test="${insuranceSalesInfo.SALES_GRADE_ID != '3'}"> disabled="disabled" </c:if> class="form-control form-control-static" id="ycCFirstGenerNo" name="ycCFirstGenerNo" value="" type="text" data-provide="typeahead" autocomplete="off" placeholder="员工编号" />
			</td>
			<td style="width:200px;text-align:center;" id="ycCFirstGenerOrg"></td>
			<td style="width:150px;text-align:center;" id="ycCFirstGenerTeam"></td>
			<td style="width:130px;text-align:center;" id="ycCFirstGenerGrade"></td>
			<td style="width:130px;text-align:center;" >
				<input class="form-control form-control-static"  readonly = "readonly" id="ycCFirstGenerDate" name="establishTime" value="" type="text" placeholder=""/>
			</td>
		</tr>
		<tr>
			<td style="width:100px;text-align:center;">二级处育成人</td>
			<td style="width:120px;text-align:center;" >
				<input <c:if test="${insuranceSalesInfo.SALES_GRADE_ID != '3'}"> disabled="disabled" </c:if> class="form-control form-control-static" autocomplete="off" id="ycCSecondGener" name="$ycCSecondGener" data-provide="typeahead" value="" type="text" placeholder="员工姓名" />
			</td>
			<input  id="ycCSecondGenerId" name="ycCSecondGener" value="" type="hidden" />
			<td style="width:150px;text-align:center;" >
				<input <c:if test="${insuranceSalesInfo.SALES_GRADE_ID != '3'}"> disabled="disabled" </c:if> class="form-control form-control-static" id="ycCSecondGenerNo" name="ycCSecondGenerNo" value="" type="text"  data-provide="typeahead" autocomplete="off" placeholder="员工编号" />
			</td>
			<td style="width:200px;text-align:center;" id="ycCSecondGenerOrg"></td>
			<td style="width:150px;text-align:center;" id="ycCSecondGenerTeam"></td>
			<td style="width:130px;text-align:center;" id="ycCSecondGenerGrade"></td>
			<td style="width:130px;text-align:center;" >
				<input class="form-control form-control-static"  readonly = "readonly" id="ycCSecondGenerDate" name="establishTime1" value="" type="text" placeholder=""/>
			</td>
		</tr>
		<tr>
			<td style="width:100px;text-align:center;">一级部育成人</td>
			<td style="width:120px;text-align:center;" >
				<input <c:if test="${insuranceSalesInfo.SALES_GRADE_ID != '2'}"> disabled="disabled" </c:if> class="form-control form-control-static" autocomplete="off" id="ycBFirstGener" name="$ycBFirstGener" data-provide="typeahead" value="" type="text" placeholder="员工姓名" />
			</td>
			<input  id="ycBFirstGenerId" name="ycBFirstGener" value="" type="hidden" />
			<td style="width:150px;text-align:center;" >
				<input <c:if test="${insuranceSalesInfo.SALES_GRADE_ID != '2'}"> disabled="disabled" </c:if> class="form-control form-control-static" id="ycBFirstGenerNo" name="ycBFirstGenerNo" value="" type="text" data-provide="typeahead" autocomplete="off" placeholder="员工编号" />
			</td>
			<td style="width:200px;text-align:center;" id="ycBFirstGenerOrg"></td>
			<td style="width:150px;text-align:center;" id="ycBFirstGenerTeam"></td>
			<td style="width:130px;text-align:center;" id="ycBFirstGenerGrade"></td>
			<td style="width:130px;text-align:center;">
				<input class="form-control form-control-static"  readonly = "readonly" id="ycBFirstGenerDate" name="establishTime2" value="" type="text" placeholder=""/>
			</td>
		</tr>
		<tr>
			<td style="width:100px;text-align:center;">二级部育成人</td>
			<td style="width:120px;text-align:center;">
				<input <c:if test="${insuranceSalesInfo.SALES_GRADE_ID != '2'}"> disabled="disabled" </c:if> class="form-control form-control-static" autocomplete="off" id="ycBSeconGener" name="$ycBSeconGener" data-provide="typeahead" value="" type="text" placeholder="员工姓名" />
			</td>
			<input  id="ycBSeconGenerId" name="ycBSecondGener" value="" type="hidden" />
			<td style="width:150px;text-align:center;" >
				<input <c:if test="${insuranceSalesInfo.SALES_GRADE_ID != '2'}"> disabled="disabled" </c:if> class="form-control form-control-static" id="ycBSeconGenerNo" name="ycBSeconGenerNo" value="" type="text"  data-provide="typeahead" autocomplete="off" placeholder="员工编号" />
			</td>
			<td style="width:200px;text-align:center;" id="ycBSeconGenerOrg"></td>
			<td style="width:150px;text-align:center;" id="ycBSeconGenerTeam"></td>
			<td style="width:130px;text-align:center;" id="ycBSeconGenerGrade"></td>
			<td style="width:130px;text-align:center;" >
				<input class="form-control form-control-static"  readonly = "readonly" id="ycBSeconGenerDate" name="establishTime3" value="" type="text" placeholder=""/>
			</td>
		</tr>
		<tr>
			<td style="width:100px;text-align:center;">上级管理人</td>
			<td style="width:120px;text-align:center;" >
				<input class="form-control form-control-static" autocomplete="off" id="sjSales" name="sjSales" data-provide="typeahead" value="" type="text" placeholder="员工姓名" />
			</td>
			<input  id="sjSalesId" name="sjSalesId" value="" type="hidden" />
			<td style="width:150px;text-align:center;" >
				<input class="form-control form-control-static" id="sjSalesNo" name="sjSalesNo" value="" type="text" data-provide="typeahead" autocomplete="off" placeholder="员工编号" />
			</td>
			<td style="width:200px;text-align:center;" id="sjSalesOrg"></td>
			<td style="width:150px;text-align:center;" id="sjSalesTeam"></td>
			<td style="width:130px;text-align:center;" id="sjSalesGrade"></td>
			<td style="width:130px;text-align:center;" >
				<input class="form-control form-control-static"  readonly = "readonly" id="sjSalesDate" name="sjSalesDate" value="" type="text" placeholder=""/>
			</td>
		</tr>
		<tr>
			<td style="width:100px;text-align:center;">继承人</td>
			<td style="width:120px;text-align:center;" >
				<input class="form-control form-control-static" autocomplete="off" id="jcSales" name="jcSales" data-provide="typeahead" value="" type="text" placeholder="员工姓名" />
			</td>
			<input  id="jcSalesId" name="jcSalesId" value="" type="hidden" />
			<td style="width:150px;text-align:center;" >
				<input class="form-control form-control-static" id="jcSalesNo" name="jcSalesNo" value="" type="text"  data-provide="typeahead" autocomplete="off" placeholder="员工编号" />
			</td>
			<td style="width:200px;text-align:center;" id="jcSalesOrg"></td>
			<td style="width:150px;text-align:center;" id="jcSalesTeam"></td>
			<td style="width:130px;text-align:center;" id="jcSalesGrade"></td>
			<td style="width:130px;text-align:center;" >
				<input class="form-control form-control-static"  readonly = "readonly" id="jcSalesDate" name="jcSalesDate" value="" type="text" placeholder=""/>
			</td>
		</tr>
		<tr>
			<td style="width:100px;text-align:center;">辅导人</td>
			<td style="width:120px;text-align:center;" >
				<input class="form-control form-control-static" autocomplete="off" id="fdSales" name="fdSales" data-provide="typeahead" value="" type="text" placeholder="员工姓名" />
			</td>
			<input  id="fdSalesId" name="fdSalesId" value="" type="hidden" />
			<td style="width:150px;text-align:center;" >
				<input class="form-control form-control-static" id="fdSalesNo" name="fdSalesNo" value="" type="text" data-provide="typeahead" autocomplete="off" placeholder="员工编号" />
			</td>
			<td style="width:200px;text-align:center;" id="fdSalesOrg"></td>
			<td style="width:150px;text-align:center;" id="fdSalesTeam"></td>
			<td style="width:130px;text-align:center;" id="fdSalesGrade"></td>
			<td style="width:130px;text-align:center;" >
				<input class="form-control form-control-static"  readonly = "readonly" id="fdSalesDate" name="fdSalesDate" value="" type="text" placeholder=""/>
			</td>
		</tr>
		<tr>
			<td style="width:100px;text-align:center;">直辖组处长/业务经</td>
			<td style="width:120px;text-align:center;" >
				<input <c:if test="${insuranceSalesInfo.SALES_GRADE_ID == '3' || insuranceSalesInfo.SALES_GRADE_ID == '2'}"> disabled="disabled" </c:if> class="form-control form-control-static" autocomplete="off" id="directGroupC" name="$directGroupC" data-provide="typeahead" value="<c:if test="${insuranceSalesInfo.SALES_GRADE_ID == '3' || insuranceSalesInfo.SALES_GRADE_ID == '2'}"> ${insuranceSalesInfo.name_team} </c:if>" type="text" placeholder="员工姓名" />
			</td>
			<input  id="directGroupCId" name="directGroupC" value="<c:if test="${insuranceSalesInfo.SALES_GRADE_ID == '3' || insuranceSalesInfo.SALES_GRADE_ID == '2'}"> ${insuranceSalesInfo.INSURANCE_SALES_ID} </c:if>" type="hidden" />
			<td style="width:150px;text-align:center;" >
				<input <c:if test="${insuranceSalesInfo.SALES_GRADE_ID == '3' || insuranceSalesInfo.SALES_GRADE_ID == '2'}"> disabled="disabled" </c:if>  class="form-control form-control-static" id="directGroupCNo" name="directGroupCNo" value="<c:if test="${insuranceSalesInfo.SALES_GRADE_ID == '3' || insuranceSalesInfo.SALES_GRADE_ID == '2'}"> ${insuranceSalesInfo.INSURANCE_SALER_NO} </c:if>" type="text" data-provide="typeahead" autocomplete="off" placeholder="员工编号" />
			</td>
			<td style="width:200px;text-align:center;" id="directGroupCOrg"><c:if test="${insuranceSalesInfo.SALES_GRADE_ID == '3' || insuranceSalesInfo.SALES_GRADE_ID == '2'}"> ${insuranceSalesInfo.SALES_ORG_NAME} </c:if></td>
			<td style="width:150px;text-align:center;" id="directGroupCTeam"><c:if test="${insuranceSalesInfo.SALES_GRADE_ID == '3' || insuranceSalesInfo.SALES_GRADE_ID == '2'}"> ${insuranceSalesInfo.SALES_TEAM_NAME} </c:if></td>
			<td style="width:130px;text-align:center;" id="directGroupCGrade"><c:if test="${insuranceSalesInfo.SALES_GRADE_ID == '3' || insuranceSalesInfo.SALES_GRADE_ID == '2'}"> ${insuranceSalesInfo.SALES_GRADE_NAME} </c:if></td>
			<td style="width:130px;text-align:center;" >
				<input class="form-control form-control-static"  readonly = "readonly" id="directGroupCDate" name="establishTime4" value="" type="text" placeholder=""/>
			</td>
		</tr>
		<tr>
			<td style="width:100px;text-align:center;">直辖部部长/业务总监</td>
			<td style="width:120px;text-align:center;" >
				<input <c:if test="${insuranceSalesInfo.SALES_GRADE_ID == '2'}"> disabled="disabled" </c:if>  class="form-control form-control-static" autocomplete="off" id="directDeptB" name="$directDeptB" data-provide="typeahead" value="<c:if test="${insuranceSalesInfo.SALES_GRADE_ID == '2'}">${insuranceSalesInfo.name_team} </c:if>" type="text" placeholder="员工姓名" />
			</td>
			<input  id="directDeptBId" name="directDeptB" value="<c:if test="${insuranceSalesInfo.SALES_GRADE_ID == '2'}"> ${insuranceSalesInfo.INSURANCE_SALES_ID}</c:if>" type="hidden" />
			<td style="width:150px;text-align:center;" >
				<input <c:if test="${insuranceSalesInfo.SALES_GRADE_ID == '2'}"> disabled="disabled" </c:if>  class="form-control form-control-static" id="directDeptBNo" name="directDeptBNo" value="<c:if test="${insuranceSalesInfo.SALES_GRADE_ID == '2'}">${insuranceSalesInfo.INSURANCE_SALER_NO} </c:if>" type="text" data-provide="typeahead" autocomplete="off" placeholder="员工编号" />
			</td>
			<td style="width:200px;text-align:center;" id="directDeptBOrg"><c:if test="${insuranceSalesInfo.SALES_GRADE_ID == '2'}"> ${insuranceSalesInfo.SALES_ORG_NAME} </c:if></td>
			<td style="width:150px;text-align:center;" id="directDeptBTeam"><c:if test="${insuranceSalesInfo.SALES_GRADE_ID == '2'}"> ${insuranceSalesInfo.SALES_TEAM_NAME} </c:if></td>
			<td style="width:130px;text-align:center;" id="directDeptBGrade"><c:if test="${insuranceSalesInfo.SALES_GRADE_ID == '2'}"> ${insuranceSalesInfo.SALES_GRADE_NAME} </c:if></td>
			<td style="width:130px;text-align:center;" >
				<input class="form-control form-control-static"  readonly = "readonly" id="directDeptBDate" name="establishTime5" value="" type="text" placeholder=""/>
			</td>
		</tr>
	</table>
    </div>
	 <div class="modal-footer col-md-6">
            <!--用来清空表单数据-->
            <input type="reset" name="reset" style="display: none;" />
                <button type="button" class="btn btn-default" onclick="windowCloseTab()">关闭</button>
               <button id="saveButton" type="button" onclick="add()" class="btn btn-primary">保存</button>
            </div>
	  </form>
</div>
<!--toolbar  -->

</body>
</html>