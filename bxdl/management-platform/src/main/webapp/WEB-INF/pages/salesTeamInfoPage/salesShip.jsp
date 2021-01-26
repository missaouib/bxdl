<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/core.jsp" %>
<!DOCTYPE  html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <title>员工关系列表</title>
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Cache" content="no-cache">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   	<link rel="stylesheet" href="${path}/css/system/role/role.css">
   	<script src="${path}/js/system/comm.js" charset="UTF-8" type="text/javascript"></script>
    <script src="${path}/static/js/bootstrap.js"></script>
	<script type="text/javascript">
		$(function () {	
			getSalesGrade("salesGrade");
			
			$('#insuranceSalerNo').bind('input propertychange', function() {
				getSales($("#insuranceSalerNo").val(),"insuranceSaler");
			});		
		});	
		
		function getSales(insuranceSalerNo,selectId){
			$.ajax({
		        type: "POST",
		        url: "insuranceSalesInfo/insuranceSalesList",
		        data:{likeSalerNo:insuranceSalerNo},
		        dataType: "json",
		        success: function(data){
		           //alert(data.rows);
		           var sales = data.rows;
				   var h = "<option value='' myvalue=''>请选择员工</option>";
		           $.each(sales, function(key, value) {
		           	   //alert(value.insuranceCompanyCode);d
		               h += "<option value='" + value.insuranceSalesId + "' myvalue='" + value.insuranceSalerNo + "" 
		               +"' dbSalesId ='"+value.dbSalesId+"' tjSalesId ='"+value.tjSalesId+"' ycCFirstGenerId ='"+value.ycCFirstGener+"' ycCSecondGenerId ='"+value.ycCSecondGener+"' ycBFirstGenerId ='"+value.ycBFirstGener+"' ycBSeconGenerId ='"+value.ycBSecondGener+"'"
		               +"' sjSalesId ='"+value.sjSalesId+"' jcSalesId ='"+value.jcSalesId+"' directDeptBId ='"+value.directDeptB+"' directGroupCId ='"+value.directGroupC+"' fdSalesId ='"+value.fdSalesId+"'"
		               +"' dbSalesDate ='"+value.dbSalesDate+"' tjSalesDate ='"+value.tjSalesDate+"' ycCFirstGenerDate ='"+value.establishTime+"' ycCSecondGenerDate ='"+value.establishTime1+"' ycBFirstGenerDate ='"+value.establishTime2+"' ycBSeconGenerDate ='"+value.establishTime3+"'"
		               +"' sjSalesDate ='"+value.sjSalesDate+"' jcSalesDate ='"+value.jcSalesDate+"' directGroupCDate ='"+value.establishTime5+"' directDeptBDate ='"+value.establishTime5+"' fdSalesDate ='"+value.fdSalesDate+"'"
		               +">"+value.insuranceSaler //下拉框序言的循环数据
		               + "</option>";  
		           });
		           $("#"+selectId).empty();
		           $("#"+selectId).append(h);
		           if(sales.length == 1){
		        	   $("#"+selectId).find('option:eq(1)').attr('selected','selected'); 
		           }
		        }
		    });	
		}

		/*获取职级*/
		function getSalesGrade(selectId){
			$.ajax({
		        url:'salesGrade/getSalesGradeList',
		        dataType:'json',
		        type:'post',
		        data:{},
		        success:function(data){
		        	var h = "<option value=''>请选择</option>";
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
		
		function getShip(dbSalesId,tjSalesId,ycCFirstGenerId,ycCSecondGenerId,ycBFirstGenerId,ycBSeconGenerId,sjSalesId,jcSalesId,fdSalesId,directGroupCId,directDeptBId){
			$.ajax({
		        type: "POST",
		        url: "insuranceSalesInfo/selectShipByNo",
		        data:{dbSalesId:dbSalesId,tjSalesId:tjSalesId,
		        	  ycCFirstGenerId:ycCFirstGenerId,ycCSecondGenerId:ycCSecondGenerId,
					  ycBFirstGenerId:ycBFirstGenerId,ycBSeconGenerId:ycBSeconGenerId,
					  sjSalesId:sjSalesId, jcSalesId:jcSalesId,fdSalesId:fdSalesId,
					  directGroupCId:directGroupCId,directDeptBId:directDeptBId
		        },
		        dataType: "json",
		        success: function(data){
		           var sales = data.rows;
		           if(sales==null){
                       $.alert({
                           title: '提示信息！',
                           content: '该员工的相关信息为空！',
                           type: 'red'
                       }); 
                       
                       var trList = $("#SalesShip-table").find("tr");
                       for(var i=1;i<trList.length;i++){
                    	   var tdArr = trList.eq(i).find("td");
                    	   for(var j=1;j<tdArr.length;j++){
                    	      tdArr.eq(j).html("-");
                    	   }
                       }                       
                       return false;
		           }else{
			           $.each(sales,function(key, value){ 
			        	   if(value.salesinfoDb){$("#dbSaler").html(value.salesinfoDb.insuranceSaler)}else{$("#dbSaler").html("-")};
			        	   if(value.salesinfoTj){$("#tjSaler").html(value.salesinfoTj.insuranceSaler)}else{$("#tjSaler").html("-")};
			        	   if(value.salesinfoYcCF){$("#ycCFirstGener").html(value.salesinfoYcCF.insuranceSaler)}else{$("#ycCFirstGener").html("-")};
						   if(value.salesinfoYcCS){$("#ycCSecondGener").html(value.salesinfoYcCS.insuranceSaler)}else{$("#ycCSecondGener").html("-")};
						   if(value.salesinfoYcBF){$("#ycBFirstGener").html(value.salesinfoYcBF.insuranceSaler)}else{$("#ycBFirstGener").html("-")};
						   if(value.salesinfoYcBS){$("#ycBSeconGener").html(value.salesinfoYcBS.insuranceSaler)}else{$("#ycBSeconGener").html("-")};
			        	   if(value.salesinfoSj){$("#sjSaler").html(value.salesinfoSj.insuranceSaler)}else{$("#sjSaler").html("-")};
			        	   if(value.salesinfoJc){$("#jcSaler").html(value.salesinfoJc.insuranceSaler)}else{$("#jcSaler").html("-")};
			        	   if(value.salesinfoFd){$("#fdSaler").html(value.salesinfoFd.insuranceSaler)}else{$("#fdSaler").html("-")};
			        	   if(value.salesinfoCz){$("#directGroupC").html(value.salesinfoCz.insuranceSaler)}else{$("#directGroupC").html("-")};
			        	   if(value.salesinfoBz){$("#directDeptB").html(value.salesinfoBz.insuranceSaler)}else{$("#directDeptB").html("-")};
			        	   
			        	   if(value.salesinfoDb){$("#dbSalerNo").html(value.salesinfoDb.insuranceSalerNo)}else{$("#dbSalerNo").html("-")};
			        	   if(value.salesinfoTj){$("#tjSalerNo").html(value.salesinfoTj.insuranceSalerNo)}else{$("#tjSalerNo").html("-")};
			        	   if(value.salesinfoYcCF){$("#ycCFirstGenerNo").html(value.salesinfoYcCF.insuranceSalerNo)}else{$("#ycCFirstGenerNo").html("-")};
			        	   if(value.salesinfoYcCS){$("#ycCSecondGenerNo").html(value.salesinfoYcCS.insuranceSalerNo)}else{$("#ycCSecondGenerNo").html("-")};
			        	   if(value.salesinfoYcBF){$("#ycBFirstGenerNo").html(value.salesinfoYcBF.insuranceSalerNo)}else{$("#ycBFirstGenerNo").html("-")};
			        	   if(value.salesinfoYcBS){$("#ycBSeconGenerNo").html(value.salesinfoYcBS.insuranceSalerNo)}else{$("#ycBSeconGenerNo").html("-")};
			        	   if(value.salesinfoSj){$("#sjSalerNo").html(value.salesinfoSj.insuranceSalerNo)}else{$("#sjSalerNo").html("-")};
			        	   if(value.salesinfoJc){$("#jcSalerNo").html(value.salesinfoJc.insuranceSalerNo)}else{$("#jcSalerNo").html("-")};
			        	   if(value.salesinfoFd){$("#fdSalerNo").html(value.salesinfoFd.insuranceSalerNo)}else{$("#fdSalerNo").html("-")};
			        	   if(value.salesinfoCz){$("#directGroupCNo").html(value.salesinfoCz.insuranceSalerNo)}else{$("#directGroupCNo").html("-")};
			        	   if(value.salesinfoBz){$("#directDeptBNo").html(value.salesinfoBz.insuranceSalerNo)}else{$("#directDeptBNo").html("-")};
			        	   
			        	   if(value.salesinfoDb){$("#dbSalerOrg").html(value.salesinfoDb.salesOrgName)}else{$("#dbSalerOrg").html("-")};
			        	   if(value.salesinfoTj){$("#tjSalerOrg").html(value.salesinfoTj.salesOrgName)}else{$("#tjSalerOrg").html("-")};
			        	   if(value.salesinfoYcCF){$("#ycCFirstGenerOrg").html(value.salesinfoYcCF.salesOrgName)}else{$("#ycCFirstGenerOrg").html("-")};
						   if(value.salesinfoYcCS){$("#ycCSecondGenerOrg").html(value.salesinfoYcCS.salesOrgName)}else{$("#ycCSecondGenerOrg").html("-")};
						   if(value.salesinfoYcBF){$("#ycBFirstGenerOrg").html(value.salesinfoYcBF.salesOrgName)}else{$("#ycBFirstGenerOrg").html("-")};
						   if(value.salesinfoYcBS){$("#ycBSeconGenerOrg").html(value.salesinfoYcBS.salesOrgName)}else{$("#ycBSeconGenerOrg").html("-")};
						   if(value.salesinfoYc){$("#ycSalerOrg").html(value.salesinfoYc.salesOrgName)}else{$("#ycSalerOrg").html("-")};
			        	   if(value.salesinfoSj){$("#sjSalerOrg").html(value.salesinfoSj.salesOrgName)}else{$("#sjSalerOrg").html("-")};
			        	   if(value.salesinfoJc){$("#jcSalerOrg").html(value.salesinfoJc.salesOrgName)}else{$("#jcSalerOrg").html("-")};
			        	   if(value.salesinfoFd){$("#fdSalerOrg").html(value.salesinfoFd.salesOrgName)}else{$("#fdSalerOrg").html("-")};
						   if(value.salesinfoCz){$("#directGroupCOrg").html(value.salesinfoCz.salesOrgName)}else{$("#directGroupCOrg").html("-")};
						   if(value.salesinfoBz){$("#directDeptBOrg").html(value.salesinfoBz.salesOrgName)}else{$("#directDeptBOrg").html("-")};
			        	   
			        	   if(value.salesinfoDb){$("#dbSalerTeam").html(value.salesinfoDb.salesTeamName)}else{$("#dbSalerTeam").html("-")};
			        	   if(value.salesinfoTj){$("#tjSalerTeam").html(value.salesinfoTj.salesTeamName)}else{$("#tjSalerTeam").html("-")};
			        	   if(value.salesinfoYcCF){$("#ycCFirstGenerTeam").html(value.salesinfoYcCF.salesTeamName)}else{$("#ycCFirstGenerTeam").html("-")};
			        	   if(value.salesinfoYcCS){$("#ycCSecondGenerTeam").html(value.salesinfoYcCS.salesTeamName)}else{$("#ycCSecondGenerTeam").html("-")};
			        	   if(value.salesinfoYcBF){$("#ycBFirstGenerTeam").html(value.salesinfoYcBF.salesTeamName)}else{$("#ycBFirstGenerTeam").html("-")};
			        	   if(value.salesinfoYcBS){$("#ycBSeconGenerTeam").html(value.salesinfoYcBS.salesTeamName)}else{$("#ycBSeconGenerTeam").html("-")};
			        	   if(value.salesinfoSj){$("#sjSalerTeam").html(value.salesinfoSj.salesTeamName)}else{$("#sjSalerTeam").html("-")};
			        	   if(value.salesinfoJc){$("#jcSalerTeam").html(value.salesinfoJc.salesTeamName)}else{$("#jcSalerTeam").html("-")};
			        	   if(value.salesinfoFd){$("#fdSalerTeam").html(value.salesinfoFd.salesTeamName)}else{$("#fdSalerTeam").html("-")};
			        	   if(value.salesinfoCz){$("#directGroupCTeam").html(value.salesinfoCz.salesTeamName)}else{$("#directGroupCTeam").html("-")};
			        	   if(value.salesinfoBz){$("#directDeptBTeam").html(value.salesinfoBz.salesTeamName)}else{$("#directDeptBTeam").html("-")};
			        	   
			        	   if(value.salesinfoDb){$("#dbSalerInDate").html(value.salesinfoDb.joinDate)}else{$("#dbSalerInDate").html("-")};
			        	   if(value.salesinfoTj){$("#tjSalerInDate").html(value.salesinfoTj.joinDate)}else{$("#tjSalerInDate").html("-")};
			        	   if(value.salesinfoYcCF){$("#ycCFirstGenerInDate").html(value.salesinfoYcCF.joinDate)}else{$("#ycCFirstGenerInDate").html("-")};
			        	   if(value.salesinfoYcCS){$("#ycCSecondGenerInDate").html(value.salesinfoYcCS.joinDate)}else{$("#ycCSecondGenerInDate").html("-")};
			        	   if(value.salesinfoYcBF){$("#ycBFirstGenerInDate").html(value.salesinfoYcBF.joinDate)}else{$("#ycBFirstGenerInDate").html("-")};
			        	   if(value.salesinfoYcBS){$("#ycBSeconGenerInDate").html(value.salesinfoYcBS.joinDate)}else{$("#ycBSeconGenerInDate").html("-")};
			        	   if(value.salesinfoSj){$("#sjSalerInDate").html(value.salesinfoSj.joinDate)}else{$("#sjSalerInDate").html("-")};
			        	   if(value.salesinfoJc){$("#jcSalerInDate").html(value.salesinfoJc.joinDate)}else{$("#jcSalerInDate").html("-")};
			        	   if(value.salesinfoFd){$("#fdSalerInDate").html(value.salesinfoFd.joinDate)}else{$("#fdSalerInDate").html("-")};
			        	   if(value.salesinfoCz){$("#directGroupCInDate").html(value.salesinfoCz.joinDate)}else{$("#directGroupCInDate").html("-")};
			        	   if(value.salesinfoBz){$("#directDeptBInDate").html(value.salesinfoBz.joinDate)}else{$("#directDeptBInDate").html("-")};
			        	   
			        	   if(value.salesinfoDb){$("#dbSalerDate").html($("#insuranceSaler").find("option:selected").attr("dbSalesDate"))}else{$("#dbSalerDate").html("-")};
			        	   if(value.salesinfoTj){$("#tjSalerDate").html($("#insuranceSaler").find("option:selected").attr("tjSalesDate"))}else{$("#tjSalerDate").html("-")};
			        	   if(value.salesinfoYcCF){$("#ycCFirstGenerDate").html($("#insuranceSaler").find("option:selected").attr("ycCFirstGenerDate"))}else{$("#ycCFirstGenerDate").html("-")};
			        	   if(value.salesinfoYcCS){$("#ycCSecondGenerDate").html($("#insuranceSaler").find("option:selected").attr("ycCSecondGenerDate"))}else{$("#ycCSecondGenerDate").html("-")};
			        	   if(value.salesinfoYcBF){$("#ycBFirstGenerDate").html($("#insuranceSaler").find("option:selected").attr("ycBFirstGenerDate"))}else{$("#ycBFirstGenerDate").html("-")};
			        	   if(value.salesinfoYcBS){$("#ycBSeconGenerDate").html($("#insuranceSaler").find("option:selected").attr("ycBSeconGenerDate"))}else{$("#ycBSeconGenerDate").html("-")};
			        	   if(value.salesinfoSj){$("#sjSalerDate").html($("#insuranceSaler").find("option:selected").attr("sjSalesDate"))}else{$("#sjSalerDate").html("-")};
			        	   if(value.salesinfoJc){$("#jcSalerDate").html($("#insuranceSaler").find("option:selected").attr("jcSalesDate"))}else{$("#jcSalerDate").html("-")};
			        	   if(value.salesinfoFd){$("#fdSalerDate").html($("#insuranceSaler").find("option:selected").attr("fdSalesDate"))}else{$("#fdSalerDate").html("-")};
			        	   if(value.salesinfoCz){$("#directGroupCDate").html($("#insuranceSaler").find("option:selected").attr("directGroupCDate"))}else{$("#directGroupCDate").html("-")};
			        	   if(value.salesinfoBz){$("#directDeptBDate").html($("#insuranceSaler").find("option:selected").attr("directDeptBDate"))}else{$("#directDeptBDate").html("-")};
			        	   
			        	   if(value.salesinfoDb){$("#dbSalerGrade").html(setGrade(value.salesinfoDb.salesGradeId))}else{$("#dbSalerGrade").html("-")};
			        	   if(value.salesinfoTj){$("#tjSalerGrade").html(setGrade(value.salesinfoTj.salesGradeId))}else{$("#tjSalerGrade").html("-")};
			        	   if(value.salesinfoYcCF){$("#ycCFirstGenerGrade").html(setGrade(value.salesinfoYcCF.salesGradeId))}else{$("#ycCFirstGenerGrade").html("-")};
			        	   if(value.salesinfoYcCS){$("#ycCSecondGenerGrade").html(setGrade(value.salesinfoYcCS.salesGradeId))}else{$("#ycCSecondGenerGrade").html("-")};
			        	   if(value.salesinfoYcBF){$("#ycBFirstGenerGrade").html(setGrade(value.salesinfoYcBF.salesGradeId))}else{$("#ycBFirstGenerGrade").html("-")};
			        	   if(value.salesinfoYcBS){$("#ycBSeconGenerGrade").html(setGrade(value.salesinfoYcBS.salesGradeId))}else{$("#ycBSeconGenerGrade").html("-")};
			        	   if(value.salesinfoSj){$("#sjSalerGrade").html(setGrade(value.salesinfoSj.salesGradeId))}else{$("#sjSalerGrade").html("-")};
			        	   if(value.salesinfoJc){$("#jcSalerGrade").html(setGrade(value.salesinfoJc.salesGradeId))}else{$("#jcSalerGrade").html("-")};
			        	   if(value.salesinfoFd){$("#fdSalerGrade").html(setGrade(value.salesinfoFd.salesGradeId))}else{$("#fdSalerGrade").html("-")};
			        	   if(value.salesinfoCz){$("#directGroupCGrade").html(setGrade(value.salesinfoCz.salesGradeId))}else{$("#directGroupCGrade").html("-")};
			        	   if(value.salesinfoBz){$("#directDeptBGrade").html(setGrade(value.salesinfoBz.salesGradeId))}else{$("#directDeptBGrade").html("-")};
			           });
		           }
		        }
		    });	
		}
		
		function setGrade(value){
			$("#salesGrade option").each(function(){
		        if($(this).val()==value){
		            value = $(this).text();
		        }
			});	
	        return value;
		}
		
		var SalerShip = function (){
			return{
				search:function(){
					if($("#insuranceSaler").val()==""){
                        $.alert({
                            title: '提示信息！',
                            content: '请先输入员工编号、选择员工！',
                            type: 'red'
                        }); 
					}else{
						var dbSalesId = $("#insuranceSaler").find("option:selected").attr("dbSalesId");
						var tjSalesId = $("#insuranceSaler").find("option:selected").attr("tjSalesId");
						var ycCFirstGenerId = $("#insuranceSaler").find("option:selected").attr("ycCFirstGenerId");
						var ycCSecondGenerId = $("#insuranceSaler").find("option:selected").attr("ycCSecondGenerId");
						var ycBFirstGenerId = $("#insuranceSaler").find("option:selected").attr("ycBFirstGenerId");
						var ycBSeconGenerId = $("#insuranceSaler").find("option:selected").attr("ycBSeconGenerId");
						var sjSalesId = $("#insuranceSaler").find("option:selected").attr("sjSalesId");
						var jcSalesId = $("#insuranceSaler").find("option:selected").attr("jcSalesId");
						var fdSalesId = $("#insuranceSaler").find("option:selected").attr("fdSalesId");
						var directGroupCId = $("#insuranceSaler").find("option:selected").attr("directGroupCId");
						var directDeptBId = $("#insuranceSaler").find("option:selected").attr("directDeptBId");
						getShip(dbSalesId,tjSalesId,ycCFirstGenerId,ycCSecondGenerId,ycBFirstGenerId,ycBSeconGenerId,sjSalesId,jcSalesId,fdSalesId,directGroupCId,directDeptBId);
					}
				},
				
				empty:function(){					
					clickThisTab();
				}
			}
		}();
	</script>
 
</head>
<body>

<div class="panel panel-default">
	<div class="panel-body">
		<form id="SearchConForm" class=" form-inline hz-form-inline">
		  	<div class="form-group">
		  		<label class="control-label">员工编号</label>
		   		<input type="text" class="form-control" id="insuranceSalerNo" name="insuranceSalerNo" placeholder="请输入员工编号">
		  	</div>
		  	<div class="form-group">
		  		<label class="control-label">选择员工</label>
		    	<select class="form-control form-control-static" id="insuranceSaler" name="insuranceSaler">
		    		<option value="">请选择员工</option>
		   		</select>
		  	</div>
			<div class="form-group" style="display:none">
		    	<select class="form-control form-control-static" id="salesGrade" name="salesGrade">
		    		<option value="">请选择</option>
		   		</select>
			</div>
	      	<div class="form-group">
		  		<button type="button" onclick="SalerShip.search()" class="btn btn-info ">
		   			<span class="glyphicon glyphicon-search" aria-hidden="true" >搜索</span>
		   		</button>
		   		<button type="button" onclick="SalerShip.empty()" class="btn btn-danger ">
		   			<span class="glyphicon glyphicon-remove" aria-hidden="true" >清空</span>
		   		</button>
	   	  	</div>
		</form>
	</div>
</div>

<!--列表 -->
<div style="width: 99%;overflow: auto;">
	<table id="SalesShip-table" class="table table-hover table-striped table-condensed table-bordered">
		<tr>
			<td style="width:100px;text-align:center;">关系</td>
			<td style="width:120px;text-align:center;">销售人员</td>
			<td style="width:150px;text-align:center;">销售人员工号</td>
			<td style="width:200px;text-align:center;">组织机构</td>
			<td style="width:150px;text-align:center;">营销团队</td>
			<td style="width:130px;text-align:center;">销售职级</td>
			<td style="width:130px;text-align:center;">入司日期</td>			
			<td style="width:130px;text-align:center;">关系确立日期</td>
		</tr>
		<tr>
			<td style="width:100px;text-align:center;">担保人</td>
			<td style="width:120px;text-align:center;" id="dbSaler"></td>
			<td style="width:150px;text-align:center;" id="dbSalerNo"></td>
			<td style="width:200px;text-align:center;" id="dbSalerOrg"></td>
			<td style="width:150px;text-align:center;" id="dbSalerTeam"></td>
			<td style="width:130px;text-align:center;" id="dbSalerGrade"></td>
			<td style="width:130px;text-align:center;" id="dbSalerInDate"></td>
			<td style="width:130px;text-align:center;" id="dbSalerDate"></td>
		</tr>
		<tr>
			<td style="width:100px;text-align:center;">推荐人</td>
			<td style="width:120px;text-align:center;" id="tjSaler"></td>
			<td style="width:150px;text-align:center;" id="tjSalerNo"></td>
			<td style="width:200px;text-align:center;" id="tjSalerOrg"></td>
			<td style="width:150px;text-align:center;" id="tjSalerTeam"></td>
			<td style="width:130px;text-align:center;" id="tjSalerGrade"></td>
			<td style="width:130px;text-align:center;" id="tjSalerInDate"></td>
			<td style="width:130px;text-align:center;" id="tjSalerDate"></td>		
		</tr>
		<tr>
			<td style="width:100px;text-align:center;">一级处育成人</td>
			<td style="width:120px;text-align:center;" id="ycCFirstGener"></td>
			<td style="width:150px;text-align:center;" id="ycCFirstGenerNo"></td>
			<td style="width:200px;text-align:center;" id="ycCFirstGenerOrg"></td>
			<td style="width:150px;text-align:center;" id="ycCFirstGenerTeam"></td>
			<td style="width:130px;text-align:center;" id="ycCFirstGenerGrade"></td>
			<td style="width:130px;text-align:center;" id="ycCFirstGenerInDate"></td>
			<td style="width:130px;text-align:center;" id="ycCFirstGenerDate"></td>
		</tr>
		<tr>
			<td style="width:100px;text-align:center;">二级处育成人</td>
			<td style="width:120px;text-align:center;" id="ycCSecondGener"></td>
			<td style="width:150px;text-align:center;" id="ycCSecondGenerNo"></td>
			<td style="width:200px;text-align:center;" id="ycCSecondGenerOrg"></td>
			<td style="width:150px;text-align:center;" id="ycCSecondGenerTeam"></td>
			<td style="width:130px;text-align:center;" id="ycCSecondGenerGrade"></td>
			<td style="width:130px;text-align:center;" id="ycCSecondGenerInDate"></td>
			<td style="width:130px;text-align:center;" id="ycCSecondGenerDate"></td>
		</tr>
		<tr>
			<td style="width:100px;text-align:center;">一级部育成人</td>
			<td style="width:120px;text-align:center;" id="ycBFirstGener"></td>
			<td style="width:150px;text-align:center;" id="ycBFirstGenerNo"></td>
			<td style="width:200px;text-align:center;" id="ycBFirstGenerOrg"></td>
			<td style="width:150px;text-align:center;" id="ycBFirstGenerTeam"></td>
			<td style="width:130px;text-align:center;" id="ycBFirstGenerGrade"></td>
			<td style="width:130px;text-align:center;" id="ycBFirstGenerInDate"></td>
			<td style="width:130px;text-align:center;" id="ycBFirstGenerDate"></td>
		</tr>
		<tr>
			<td style="width:100px;text-align:center;">二级部育成人</td>
			<td style="width:120px;text-align:center;" id="ycBSeconGener"></td>
			<td style="width:150px;text-align:center;" id="ycBSeconGenerNo"></td>
			<td style="width:200px;text-align:center;" id="ycBSeconGenerOrg"></td>
			<td style="width:150px;text-align:center;" id="ycBSeconGenerTeam"></td>
			<td style="width:130px;text-align:center;" id="ycBSeconGenerGrade"></td>
			<td style="width:130px;text-align:center;" id="ycBSeconGenerInDate"></td>
			<td style="width:130px;text-align:center;" id="ycBSeconGenerDate"></td>
		</tr>
		<tr>
			<td style="width:100px;text-align:center;">上级管理人</td>
			<td style="width:120px;text-align:center;" id="sjSaler"></td>
			<td style="width:150px;text-align:center;" id="sjSalerNo"></td>
			<td style="width:200px;text-align:center;" id="sjSalerOrg"></td>
			<td style="width:150px;text-align:center;" id="sjSalerTeam"></td>
			<td style="width:130px;text-align:center;" id="sjSalerGrade"></td>
			<td style="width:130px;text-align:center;" id="sjSalerInDate"></td>
			<td style="width:130px;text-align:center;" id="sjSalerDate"></td>		
		</tr>
		<tr>
			<td style="width:100px;text-align:center;">继承人</td>
			<td style="width:120px;text-align:center;" id="jcSaler"></td>
			<td style="width:150px;text-align:center;" id="jcSalerNo"></td>
			<td style="width:200px;text-align:center;" id="jcSalerOrg"></td>
			<td style="width:150px;text-align:center;" id="jcSalerTeam"></td>
			<td style="width:130px;text-align:center;" id="jcSalerGrade"></td>
			<td style="width:130px;text-align:center;" id="jcSalerInDate"></td>
			<td style="width:130px;text-align:center;" id="jcSalerDate"></td>			
		</tr>
		<tr>
			<td style="width:100px;text-align:center;">辅导人</td>
			<td style="width:120px;text-align:center;" id="fdSaler"></td>
			<td style="width:150px;text-align:center;" id="fdSalerNo"></td>
			<td style="width:200px;text-align:center;" id="fdSalerOrg"></td>
			<td style="width:150px;text-align:center;" id="fdSalerTeam"></td>
			<td style="width:130px;text-align:center;" id="fdSalerGrade"></td>
			<td style="width:130px;text-align:center;" id="fdSalerInDate"></td>
			<td style="width:130px;text-align:center;" id="fdSalerDate"></td>			
		</tr>
		<tr>
			<td style="width:100px;text-align:center;">直辖组处长/业务经理</td>
			<td style="width:120px;text-align:center;" id="directGroupC"></td>
			<td style="width:150px;text-align:center;" id="directGroupCNo"></td>
			<td style="width:200px;text-align:center;" id="directGroupCOrg"></td>
			<td style="width:150px;text-align:center;" id="directGroupCTeam"></td>
			<td style="width:130px;text-align:center;" id="directGroupCGrade"></td>
			<td style="width:130px;text-align:center;" id="directGroupCInDate"></td>
			<td style="width:130px;text-align:center;" id="directGroupCDate"></td>
		</tr>
		<tr>
			<td style="width:100px;text-align:center;">直辖部部长/业务总监</td>
			<td style="width:120px;text-align:center;" id="directDeptB"></td>
			<td style="width:150px;text-align:center;" id="directDeptBNo"></td>
			<td style="width:200px;text-align:center;" id="directDeptBOrg"></td>
			<td style="width:150px;text-align:center;" id="directDeptBTeam"></td>
			<td style="width:130px;text-align:center;" id="directDeptBGrade"></td>
			<td style="width:130px;text-align:center;" id="directDeptBInDate"></td>
			<td style="width:130px;text-align:center;" id="directDeptBDate"></td>
		</tr>
	</table>
</div>
<!--toolbar  -->

</body>
</html>