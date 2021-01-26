<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/core.jsp" %>
<!DOCTYPE  html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <title>查看员工关系</title>
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Cache" content="no-cache">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   	<link rel="stylesheet" href="${path}/css/system/role/role.css">


   	<script src="${path}/js/system/comm.js" charset="UTF-8" type="text/javascript"></script>
    <script src="${path}/static/js/bootstrap.js"></script>
	<script src="${path}/js/bootstrap3-typeahead.js" type="text/javascript"></script>
	<script type="text/javascript" src="${path}/static/js/jquery-ui.min.js"></script>
	<script type="text/javascript">
		$(function () {
			getSalesGrade("salesGrade");

				var dbSalesId = $("#dbSalesId").val(); //担保人
				var tjSalesId = $("#tjSalesId").val(); //推荐人
				var ycCFirstGenerId = $("#ycCFirstGenerId").val();//一代处育成人
				var ycCSecondGenerId = $("#ycCSecondGenerId").val();//二代处育成人
				var ycBFirstGenerId = $("#ycBFirstGenerId").val();//一代部育成人
				var ycBSecondGenerId = $("#ycBSeconGenerId").val();//二代部育成人
				var sjSalesId = $("#sjSalesId").val(); //上级管理人
				var jcSalesId = $("#jcSalesId").val(); //继承人
				var fdSalesId = $("#fdSalesId").val(); //辅导人
				var directGroupCId = $("#directGroupCId").val(); //直辖组处长/业务经理
				var directDeptBId = $("#directDeptBId").val(); //直辖部部长/业务总监

				getSalesRelation(dbSalesId,tjSalesId,ycCFirstGenerId,ycCSecondGenerId,ycBFirstGenerId,ycBSecondGenerId,sjSalesId,jcSalesId,fdSalesId,directGroupCId,directDeptBId);

$("#checkInsuranceDlg").draggable();

});


		function getSalesRelation(dbSalesId,tjSalesId,ycCFirstGenerId,ycCSecondGenerId,ycBFirstGenerId,ycBSecondGenerId,sjSalesId,jcSalesId,fdSalesId,directGroupCId,directDeptBId){
					$.ajax({
						type: "POST",
						url: "insuranceSalesInfo/insuranceSalesRelationList",
						data:{dbSalesId:dbSalesId,tjSalesId:tjSalesId,ycCFirstGener:ycCFirstGenerId,ycCSecondGener:ycCSecondGenerId,ycBFirstGener:ycBFirstGenerId,ycBSecondGener:ycBSecondGenerId,sjSalesId:sjSalesId,jcSalesId:jcSalesId,fdSalesId:fdSalesId,directGroupC:directGroupCId,directDeptB:directDeptBId},
						dataType: "json",
						success: function(data){
						  // alert(data.rows);
						   var sales = data;
							 $.each(sales, function(key, value) {
								 if(value.INSURANCE_SALES_ID == dbSalesId){
									 $("#dbSales").html(value.INSURANCE_SALER);
									 $("#dbSalesNo").html(value.INSURANCE_SALER_NO);
									 $("#dbSalesOrg").html(value.SALES_ORG_NAME);
									 $("#dbSalesTeam").html(value.SALES_TEAM_NAME);
									 $("#dbSalesGrade").html(value.SALES_GRADE_NAME);
								 }
								 if(value.INSURANCE_SALES_ID == tjSalesId){
									 $("#tjSales").html(value.INSURANCE_SALER);
									 $("#tjSalesNo").html(value.INSURANCE_SALER_NO);
									 $("#tjSalesOrg").html(value.SALES_ORG_NAME);
									 $("#tjSalesTeam").html(value.SALES_TEAM_NAME);
									 $("#tjSalesGrade").html(value.SALES_GRADE_NAME);
								 }
								 if(value.INSURANCE_SALES_ID == ycCFirstGenerId){
									 $("#ycCFirstGener").html(value.INSURANCE_SALER);
									 $("#ycCFirstGenerNo").html(value.INSURANCE_SALER_NO);
									 $("#ycCFirstGenerOrg").html(value.SALES_ORG_NAME);
									 $("#ycCFirstGenerTeam").html(value.SALES_TEAM_NAME);
									 $("#ycCFirstGenerGrade").html(value.SALES_GRADE_NAME);
								 }
								 if(value.INSURANCE_SALES_ID == ycCSecondGenerId){
									 $("#ycCSecondGener").html(value.INSURANCE_SALER);
									 $("#ycCSecondGenerNo").html(value.INSURANCE_SALER_NO);
									 $("#ycCSecondGenerOrg").html(value.SALES_ORG_NAME);
									 $("#ycCSecondGenerTeam").html(value.SALES_TEAM_NAME);
									 $("#ycCSecondGenerGrade").html(value.SALES_GRADE_NAME);
								 }
								 if(value.INSURANCE_SALES_ID == ycBFirstGenerId){
									 $("#ycBFirstGener").html(value.INSURANCE_SALER);
									 $("#ycBFirstGenerNo").html(value.INSURANCE_SALER_NO);
									 $("#ycBFirstGenerOrg").html(value.SALES_ORG_NAME);
									 $("#ycBFirstGenerTeam").html(value.SALES_TEAM_NAME);
									 $("#ycBFirstGenerGrade").html(value.SALES_GRADE_NAME);
								 }
								 if(value.INSURANCE_SALES_ID == ycBSecondGenerId){
									 $("#ycBSeconGener").html(value.INSURANCE_SALER);
									 $("#ycBSeconGenerNo").html(value.INSURANCE_SALER_NO);
									 $("#ycBSeconGenerOrg").html(value.SALES_ORG_NAME);
									 $("#ycBSeconGenerTeam").html(value.SALES_TEAM_NAME);
									 $("#ycBSeconGenerGrade").html(value.SALES_GRADE_NAME);
								 }
								 if(value.INSURANCE_SALES_ID == sjSalesId){
									 $("#sjSales").html(value.INSURANCE_SALER);
									 $("#sjSalesNo").html(value.INSURANCE_SALER_NO);
									 $("#sjSalesOrg").html(value.SALES_ORG_NAME);
									 $("#sjSalesTeam").html(value.SALES_TEAM_NAME);
									 $("#sjSalesGrade").html(value.SALES_GRADE_NAME);
								 }
								 if(value.INSURANCE_SALES_ID == jcSalesId){
									 $("#jcSales").html(value.INSURANCE_SALER);
									 $("#jcSalesNo").html(value.INSURANCE_SALER_NO);
									 $("#jcSalesOrg").html(value.SALES_ORG_NAME);
									 $("#jcSalesTeam").html(value.SALES_TEAM_NAME);
									 $("#jcSalesGrade").html(value.SALES_GRADE_NAME);
								 }
								 if(value.INSURANCE_SALES_ID == fdSalesId){
									 $("#fdSales").html(value.INSURANCE_SALER);
									 $("#fdSalesNo").html(value.INSURANCE_SALER_NO);
									 $("#fdSalesOrg").html(value.SALES_ORG_NAME);
									 $("#fdSalesTeam").html(value.SALES_TEAM_NAME);
									 $("#fdSalesGrade").html(value.SALES_GRADE_NAME);
								 }
								 if(value.INSURANCE_SALES_ID == directGroupCId){
									 $("#directGroupC").html(value.INSURANCE_SALER);
									 $("#directGroupCNo").html(value.INSURANCE_SALER_NO);
									 $("#directGroupCOrg").html(value.SALES_ORG_NAME);
									 $("#directGroupCTeam").html(value.SALES_TEAM_NAME);
									 $("#directGroupCGrade").html(value.SALES_GRADE_NAME);
								 }
								 if(value.INSURANCE_SALES_ID == directDeptBId){
									 $("#directDeptB").html(value.INSURANCE_SALER);
									 $("#directDeptBNo").html(value.INSURANCE_SALER_NO);
									 $("#directDeptBOrg").html(value.SALES_ORG_NAME);
									 $("#directDeptBTeam").html(value.SALES_TEAM_NAME);
									 $("#directDeptBGrade").html(value.SALES_GRADE_NAME);
								 }
							});

						}
					});
				}



          function openDetailTab(permission){
				var insuranceSalesId = $("#insuranceSalesId").val();
				createAddProcessTab(permission,insuranceSalesId);
		}
		function newRelationship() {
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
		                toolbar:"#checkkInsuranceButton",
		                showColumns : false, //显示隐藏列
		                uniqueId: "PRODUCTS_RATIO_ID", //每一行的唯一标识，一般为主键列
		                queryParamsType:'',
		                queryParams: {insuranceSalesId:insuranceSalesId},//传递参数（*）
		                columns : [
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
        }
        function cancelCheckProduct() {
			$("#checkInsuranceDlg").modal('hide');
        }



		
	</script>
 
</head>
<body>


<!--列表 -->
<div style="width: 99%;overflow: auto;">
		<label style="color: #eea236">${insuranceSalesInfo.insuranceSaler}:&lt;${list.SALES_TEAM_NAME},${list.SALES_GRADE_NAME}&gt;</label>
	<div class=" pull-right">
	<shiro:hasPermission name="salesAssess:relationUpdate">
				<button type="button" class="btn btn-danger " onclick="openDetailTab('salesAssess:relationUpdate')">
					<span class="glyphicon glyphicon-edit" >修改本月员工关系</span>
				</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="salesAssess:relationNewUpdate">
				<button type="button" class="btn btn-warning" onclick="openDetailTab('salesAssess:relationNewUpdate')">
						<span class="glyphicon glyphicon-user" >修改次月新员工关系</span>
			    </button>
			</shiro:hasPermission>
		</div>
	<div id="checkInsuranceDlg"  class="modal fade" aria-hidden="true" style="height: 370px;background: #fff; ">
				<div>
					<table id="check-insurance-table"  style="overflow: hidden;padding: 5px 15px 15px;" class="table table-hover table-striped table-condensed table-bordered"></table>
				</div>
				<div id="checkkInsuranceButton" class="btn-group">
					<button type="button" onclick="cancelCheckProduct()" class="btn btn-default" style="margin-left: 20px;">关闭</button>
				</div>
			</div>
	<form class="form-horizontal" id="addForm"  method="post">
            <div class="form-group">
			<input type="hidden" id="insuranceSalesId" name="insuranceSalesId" value="${insuranceSalesInfo.insuranceSalesId}">
			<input type="hidden" id="salesGradeId" name="salesGradeId" value="${insuranceSalesInfo.salesGradeId}">
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
			<td style="width:120px;text-align:center;" id="dbSales" ></td>
			<input  id="dbSalesId" name="dbSalesId" value="${insuranceSalesInfo.dbSalesId}" type="hidden" />
			<td style="width:150px;text-align:center;" id="dbSalesNo"></td>
			<td style="width:200px;text-align:center;" id="dbSalesOrg"></td>
			<td style="width:150px;text-align:center;" id="dbSalesTeam"></td>
			<td style="width:130px;text-align:center;" id="dbSalesGrade"></td>
			<td style="width:130px;text-align:center;" id="dbSalesDate">
				${insuranceSalesInfo.dbSalesDate}
			</td>
		</tr>

		<tr>
			<td style="width:100px;text-align:center;">推荐人</td>
			<td style="width:120px;text-align:center;" id="tjSales"></td>
			<input  readonly = "readonly" id="tjSalesId" name="tjSalesId" value="${insuranceSalesInfo.tjSalesId}" type="hidden" />
			<td style="width:150px;text-align:center;" id="tjSalesNo"></td>
			<td style="width:200px;text-align:center;" id="tjSalesOrg"></td>
			<td style="width:150px;text-align:center;" id="tjSalesTeam"></td>
			<td style="width:130px;text-align:center;" id="tjSalesGrade"></td>
			<td style="width:130px;text-align:center;" id="tjSalesDate">
				${insuranceSalesInfo.tjSalesDate}
			</td>
		</tr>
		<tr>
			<td style="width:100px;text-align:center;">一级处育成人</td>
			<td style="width:120px;text-align:center;" id="ycCFirstGener"></td>
			<input  id="ycCFirstGenerId" name="ycCFirstGener" value="${insuranceSalesInfo.ycCFirstGener}" type="hidden" />
			<td style="width:150px;text-align:center;" id="ycCFirstGenerNo"></td>
			<td style="width:200px;text-align:center;" id="ycCFirstGenerOrg"></td>
			<td style="width:150px;text-align:center;" id="ycCFirstGenerTeam"></td>
			<td style="width:130px;text-align:center;" id="ycCFirstGenerGrade"></td>
			<td style="width:130px;text-align:center;" id="ycCFirstGenerDate">
				${insuranceSalesInfo.establishTime}
			</td>
		</tr>
		<tr>
			<td style="width:100px;text-align:center;">二级处育成人</td>
			<td style="width:120px;text-align:center;" id="ycCSecondGener"></td>
			<input  id="ycCSecondGenerId" name="ycCSecondGener" value="${insuranceSalesInfo.ycCSecondGener}" type="hidden" />
			<td style="width:150px;text-align:center;" id="ycCSecondGenerNo"></td>
			<td style="width:200px;text-align:center;" id="ycCSecondGenerOrg"></td>
			<td style="width:150px;text-align:center;" id="ycCSecondGenerTeam"></td>
			<td style="width:130px;text-align:center;" id="ycCSecondGenerGrade"></td>
			<td style="width:130px;text-align:center;" id="ycCSecondGenerDate">
				${insuranceSalesInfo.establishTime1}
			</td>
		</tr>
		<tr>
			<td style="width:100px;text-align:center;">一级部育成人</td>
			<td style="width:120px;text-align:center;" id="ycBFirstGener"></td>
			<input  id="ycBFirstGenerId" name="ycBFirstGener" value="${insuranceSalesInfo.ycBFirstGener}" type="hidden" />
			<td style="width:150px;text-align:center;"  id="ycBFirstGenerNo"></td>
			<td style="width:200px;text-align:center;" id="ycBFirstGenerOrg"></td>
			<td style="width:150px;text-align:center;" id="ycBFirstGenerTeam"></td>
			<td style="width:130px;text-align:center;" id="ycBFirstGenerGrade"></td>
			<td style="width:130px;text-align:center;" id="ycBFirstGenerDate">
				${insuranceSalesInfo.establishTime2}
			</td>
		</tr>
		<tr>
			<td style="width:100px;text-align:center;">二级部育成人</td>
			<td style="width:120px;text-align:center;" id="ycBSeconGener"></td>
			<input  id="ycBSeconGenerId" name="ycBSecondGener" value="${insuranceSalesInfo.ycBSecondGener}" type="hidden" />
			<td style="width:150px;text-align:center;"  id="ycBSeconGenerNo"></td>
			<td style="width:200px;text-align:center;" id="ycBSeconGenerOrg"></td>
			<td style="width:150px;text-align:center;" id="ycBSeconGenerTeam"></td>
			<td style="width:130px;text-align:center;" id="ycBSeconGenerGrade"></td>
			<td style="width:130px;text-align:center;" id="ycBSeconGenerDate">
				${insuranceSalesInfo.establishTime3}
			</td>
		</tr>
		<tr>
			<td style="width:100px;text-align:center;">上级管理人</td>
			<td style="width:120px;text-align:center;" id="sjSales"></td>
			<input  id="sjSalesId" name="sjSalesId" value="${insuranceSalesInfo.sjSalesId}" type="hidden" />
			<td style="width:150px;text-align:center;" id="sjSalesNo"></td>
			<td style="width:200px;text-align:center;" id="sjSalesOrg"></td>
			<td style="width:150px;text-align:center;" id="sjSalesTeam"></td>
			<td style="width:130px;text-align:center;" id="sjSalesGrade"></td>
			<td style="width:130px;text-align:center;" id="sjSalesDate">
				${insuranceSalesInfo.sjSalesDate}
			</td>
		</tr>
		<tr>
			<td style="width:100px;text-align:center;">继承人</td>
			<td style="width:120px;text-align:center;" id="jcSales"></td>
			<input  id="jcSalesId" name="jcSalesId" value="${insuranceSalesInfo.jcSalesId}" type="hidden" />
			<td style="width:150px;text-align:center;" id="jcSalesNo"></td>
			<td style="width:200px;text-align:center;" id="jcSalesOrg"></td>
			<td style="width:150px;text-align:center;" id="jcSalesTeam"></td>
			<td style="width:130px;text-align:center;" id="jcSalesGrade"></td>
			<td style="width:130px;text-align:center;"  id="jcSalesDate">
				${insuranceSalesInfo.jcSalesDate}
			</td>
		</tr>
		<tr>
			<td style="width:100px;text-align:center;">辅导人</td>
			<td style="width:120px;text-align:center;" id="fdSales"></td>
			<input  id="fdSalesId" name="fdSalesId" value="${insuranceSalesInfo.fdSalesId}" type="hidden" />
			<td style="width:150px;text-align:center;" id="fdSalesNo"></td>
			<td style="width:200px;text-align:center;" id="fdSalesOrg"></td>
			<td style="width:150px;text-align:center;" id="fdSalesTeam"></td>
			<td style="width:130px;text-align:center;" id="fdSalesGrade"></td>
			<td style="width:130px;text-align:center;" id="fdSalesDate">
				${insuranceSalesInfo.fdSalesDate}
			</td>
		</tr>
		<tr>
			<td style="width:100px;text-align:center;">直辖组处长/业务经理</td>
			<td style="width:120px;text-align:center;" id="directGroupC"></td>
			<input  id="directGroupCId" name="directGroupC" value="${insuranceSalesInfo.directGroupC}" type="hidden" />
			<td style="width:150px;text-align:center;" id="directGroupCNo"></td>
			<td style="width:200px;text-align:center;" id="directGroupCOrg"></td>
			<td style="width:150px;text-align:center;" id="directGroupCTeam"></td>
			<td style="width:130px;text-align:center;" id="directGroupCGrade"></td>
			<td style="width:130px;text-align:center;" id="directGroupCDate">
				${insuranceSalesInfo.establishTime4}
			</td>
		</tr>
		<tr>
			<td style="width:100px;text-align:center;">直辖部部长/业务总监</td>
			<td style="width:120px;text-align:center;" id="directDeptB"></td>
			<input  id="directDeptBId" name="directDeptB" value="${insuranceSalesInfo.directDeptB}" type="hidden" />
			<td style="width:150px;text-align:center;" id="directDeptBNo"></td>
			<td style="width:200px;text-align:center;" id="directDeptBOrg"></td>
			<td style="width:150px;text-align:center;" id="directDeptBTeam"></td>
			<td style="width:130px;text-align:center;" id="directDeptBGrade"></td>
			<td style="width:130px;text-align:center;" id="directDeptBDate">
				${insuranceSalesInfo.establishTime5}
			</td>
		</tr>
	</table>
    </div>

	  </form>
</div>
<!--toolbar  -->

</body>
</html>