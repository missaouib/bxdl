<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/core.jsp" %>
<!DOCTYPE  html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <title>销售人员管理</title>
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Cache" content="no-cache">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   	<link rel="stylesheet" href="${path}/css/system/role/role.css">
	<link rel="stylesheet" href="${path}/static/js/plugins/treeview/bootstrap-treeview.css">
   	<script src="${path}/js/system/comm.js" charset="UTF-8" type="text/javascript"></script>
    <script src="${path}/static/js/bootstrap.js"></script>
	<script src="${path}/static/js/jquery-form.js"></script>
	<script src="${path}/static/js/plugins/treeview/bootstrap-treeview.js"></script>
	<script type="text/javascript">
		var insuranceSalesInfo = {
		    seItem: null		//选中的条目
		};
	
		$(function () {	
			insuranceSalesInfo.init();
			
			getRankSequenceList("ins_rankSequenceId");			
		    $("#ins_rankSequenceId").on(  
	            "change",function(){
	            	getSalesGrade("ins_salesGradeId",$(this).find("option:selected").val());
	            }
		    );
		    commSalesOrgs("ins_salesOrgId");
		    $("#ins_salesOrgId").on(  
	            "change",function(){
	            	commSalesTeams("ins_salesTeamId",$(this).find("option:selected").val());
	            }
			)	
		});

		function openDetailTabForRelation(permission){
			if(insuranceSalesInfo.checkSingleData()){
			    debugger;
				var insuranceSalesId = insuranceSalesInfo.seItem.INSURANCE_SALES_ID;
				var salesGradeId = insuranceSalesInfo.seItem.SALES_GRADE_ID;
				createAddProcessTabForRelation(permission,insuranceSalesId,salesGradeId);
			}
		}
		/*员工关系*/
		function createAddProcessTabForRelation(permission,rowId){
			 $.ajax({
				 type: "POST",
				 url: "menu/getMenuByPermission",
				 data: {permission:permission},
				 dataType: "json",
				 success: function(data){
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


		function openDetailTab(permission){
			if(insuranceSalesInfo.checkSingleData()){
				var insuranceSalesId = insuranceSalesInfo.seItem.INSURANCE_SALES_ID;
				createAddProcessTab(permission,insuranceSalesId);
			}
		}
		function openDetailView(INSURANCE_SALES_ID,permission){
				var insuranceSalesId = INSURANCE_SALES_ID;
				createAddProcessTab(permission,insuranceSalesId);
		}
		function openWorkGroupTab(permission) {
			if(insuranceSalesInfo.checkSingleData()){
				var insuranceSalesId = insuranceSalesInfo.seItem.INSURANCE_SALES_ID;

				createAddProcessTab(permission,insuranceSalesId);
			}
		}
		function exportSelect(cols){
			var salerIdsS = $('#insuranceSalesInfo-table').bootstrapTable('getSelections');
            if (salerIdsS.length == 0) {
           	 $.alert({
                    title: '提示信息！',
                    content: '至少选择一条记录！',
                    type: 'red'
                });
               return false;
           	}else{
				var salerIds = "";
				for(var i=0;i<salerIdsS.length;i++){
					salerIds = salerIds + salerIdsS[i].INSURANCE_SALES_ID + ",";
				}
				salerIds = salerIds.substring(0,salerIds.length-1);
				salerExport(salerIds,cols);
           	}
		}
		
		function exportAll(cols){
			salerExport("all",cols);
		}
		
		function salerExport(ids,cols){
		    var params = "";
		    if(ids == "all"){
		        var salesOrgId=$("#ins_salesOrgId").val();
				var salesTeamId= $("#ins_salesTeamId").val();
				var insuranceSalerNo=$("#ins_insuranceSalerNo").val();
				var insuranceSaler=$("#ins_insuranceSaler").val();
				var rankSequenceId= $("#ins_rankSequenceId").val();
				var salesGradeId=$("#ins_salesGradeId").val();
				params += "&salesOrgId=" + salesOrgId +"&salesTeamId=" + salesTeamId + "&insuranceSalerNo=" + insuranceSalerNo + "&insuranceSaler=" + insuranceSaler + "&rankSequenceId=" + rankSequenceId + "&salesGradeId=" + salesGradeId;

			}
			location.href="insuranceSalesInfo/salerExport?ids="+ids+"&cols="+cols+params;
			/* $.ajax({
                url:'insuranceSalesInfo/salerExport',
                type:'post',
                dataType:'json',
                data:{ids:ids,cols:cols},
                success:function(data){
					if("200" == data.messageCode){
						$('<form method="post" action="${path}/fileUpload/downloadExcel"><input name="fileName" value="'+data.data+'"/></form>').appendTo('body').submit().remove();
						$.alert({
							title: '提示！',
							content: '导出成功',
							type: 'blue'
						});
					}else if(300 == data.messageCode){
						$.messager.alert('提示','依据搜索条件没有获取到数据！');
					}else if(500 == data.messageCode){
						$.messager.alert('提示','系统异常，请刷新后重试！');
					}
                	$("#insuranceSalesInfo-table").bootstrapTable('refresh');
                }
			}) */
		}

		function addRelation(insuranceSalerId){

		}

		function checkPass(insuranceSalerId){
			var flag = true;
			if(flag){
				$.confirm({
			        title: '提示信息!',
			        content: '您确定让该员工转正吗？',
			        type: 'blue',
			        typeAnimated: true,
			        buttons: {
			        	确定: {
				        	action: function(){
								$.ajax({
							        type: "POST",
							        url: "insuranceSalesInfo/checkPass",
							        data:{insuranceSalerId:insuranceSalerId},
							        dataType: "json",
							        success: function(data){
							        	if("200" == data.messageCode){
									       	 $.alert({
									             title: '提示信息！',
									             content: '转正成功！',
									             type: 'blue'
									         });
									       	 $("#insuranceSalesInfo-table").bootstrapTable('refresh');
							        	}else if("400" == data.messageCode){
									       	 $.alert({
									             title: '提示信息！',
									             content: data.requestState,
									             type: 'blue'
									         });						        		
							        	}
								        return true;        	
							        }
								});
				            }
			        	},
				      	  取消: function () {
				        	return true; 
				        }
			        }
				});	
			}
		}
		
		function setValue(selectId,value){
			$("#"+selectId+" option").each(function(){
				if($(this).val()==value){
					value = $(this).text();
				}
			});
			return value;
		}
		
		var insuranceSalesInfo = function (){
			return{
				init:function (){
		            $('#insuranceSalesInfo-table').bootstrapTable({
		            	url: "insuranceSalesInfo/insuranceSalesPage",
		            	method:"post",
		                dataType: "json",
		                contentType: "application/x-www-form-urlencoded",
		                striped:true,//隔行变色
		                cache:false,  //是否使用缓存
		                pagination: true, //分页
		                sortable: false, //是否启用排序
		                singleSelect: false,
		                search:false, //显示搜索框
		                buttonsAlign: "right", //按钮对齐方式
		                showRefresh:false,//是否显示刷新按钮
		                sidePagination: "server", //服务端处理分页
		                pageSize : 10, //默认每页条数
		                pageNumber : 1, //默认分页
		                pageList : [5, 10, 20, 50 ],//分页数
		                toolbar:"#insuranceSalesInfo_toolbar",
		                showColumns : false, //显示隐藏列
		                uniqueId: "INSURANCE_SALES_ID", //每一行的唯一标识，一般为主键列
		                queryParamsType:'',
		                queryParams: insuranceSalesInfo.queryParams,//传递参数（*）
		                columns : [{
		                    checkbox: true
		            	},{
		                    field : "INSURANCE_SALES_ID",
		                    title : "编号",
		                    align : "center",
		                    valign : "middle",
		                    sortable : "true",
		                    formatter:function(value,row,index){
	  			                var pageSize=$('#insuranceSalesInfo-table').bootstrapTable('getOptions').pageSize;//通过表的#id 可以得到每页多少条
	  			                var pageNumber=$('#insuranceSalesInfo-table').bootstrapTable('getOptions').pageNumber;
	  			                return pageSize * (pageNumber - 1) + index + 1;    //返回每条的序号： 每页条数 * （当前页 - 1 ）+ 序号
	  			            }
		                },{
		                    field : "INSURANCE_SALER",
		                    title : "员工姓名",
		                    align : "center",
		                    valign : "middle",
		                    sortable : "true"
		                },{
		                    field : "INSURANCE_SALER_NO",
		                    title : "员工工号",
		                    align : "center",
		                    valign : "middle",
		                    sortable : "true"
		                },{
		                    field : "SALES_ORG_NAME",
		                    title : "组织机构",
		                    align : "center",
		                    valign : "middle",
		                    sortable : "true"
		                },{
		                    field : "SALES_TEAM_NAME",
		                    title : "销售团队",
		                    align : "center",
		                    valign : "middle",
		                    sortable : "true"
		                },{
							field : "RANK_SEQUENCE_NAME",
							title : "职级序列",
							align : "center",
							valign : "middle",
							sortable : "true"
						},{
							field : "SALES_GRADE_NAME",
							title : "职级",
							align : "center",
							valign : "middle",
							sortable : "true"
						},{
							field : "JOIN_DATE",
							title : "入司时间",
							align : "center",
							valign : "middle",
							sortable : "true"
						},{
							field : "CREATED_TIME",
							title : "创建时间",
							align : "center",
							valign : "middle",
							sortable : "true",
							formatter:function(value,row,index){
								var time = new Date(row.CREATED_TIME);
								var year = time.getFullYear();
								var month = (time.getMonth()+1<10)?'0'+(time.getMonth()+1):(time.getMonth()+1);
								var date = (time.getDate()+1<11)?'0'+time.getDate():time.getDate();
								var hours = (time.getHours()+1<11)?'0'+time.getHours():time.getHours();
								var minutes = (time.getMinutes()+1<11)?'0'+time.getMinutes():time.getMinutes();
								var seconds = (time.getSeconds()+1<11)?'0'+time.getSeconds():time.getSeconds();
								return year+'-'+month+'-'+date+' '+hours+':'+minutes+':'+seconds;
							}
						},{
		                    field : "SALES_STATUS",
		                    title : "状态",
		                    align : "center",
		                    valign : "middle",
		                    sortable : "true",
		                    formatter:function(value,row,index){ 
				            	var value="";
				            	if(row.SALES_STATUS=="0"){
				            		value = "试用";
				            	}else if(row.SALES_STATUS=="1"){
				            		value = "正式";
				            	}else if(row.SALES_STATUS=="2"){
				            		value = "离职";
				            	}else if(row.SALES_STATUS=="3"){
				            		value = "黑名单";
				            	}else{
				            		value = row.SALES_STATUS;
				            	}
				            	return value;
		                    }
		                  },{
			                    field : "操作",
			                    title : "操作",
			                    align : "center",
			                    valign : "middle",
			                    sortable : "true",
			                    formatter:function(value,row,index){
			                    	var h = '<a href="javascript:void(0)" onclick="openDetailView(\''+row.INSURANCE_SALES_ID+'\',\'insuranceSalesInfo:view\')" style="color:blue">查看</a>';
			                    	if(row.SALES_STATUS=="0"){
			                    		h += '&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" onclick="checkPass(\''+row.INSURANCE_SALES_ID+'\')" style="color:blue">转正</a>';
			                    	}
			                    	if(row.direct_dept_b != null || row.direct_group_c != null || row.TJ_SALES_ID != null){
										  <shiro:hasPermission name="salesAssess:relationView">
										h += '&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" onclick="openDetailView(\''+row.INSURANCE_SALES_ID+'\',\'salesAssess:relationView\')" style="color:blue">员工关系</a>';
										</shiro:hasPermission>
									}else{
			                    	     <shiro:hasPermission name="salesAssess:relation">
										h += '&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" onclick="openDetailView(\''+row.INSURANCE_SALES_ID+'\',\'salesAssess:relation\')" style="color:blue">设置员工关系</a>';
									</shiro:hasPermission>

									}


									return h;
			                    }
			                }
		                ],
		        	// bootstrap-table-treetreegrid.js 插件配置 -- end
		                formatLoadingMessage : function() {
		                    return "请稍等，正在加载中...";
		                },
		                formatNoMatches : function() {
		                    return '无符合条件的记录';
		                }
		            });
				},
				queryParams:function(params){
					var temp = {
						pageSize: params.pageSize,  //页面大小
						pageNo: params.pageNumber, //页码
						salesOrgId: $("#ins_salesOrgId").val(),
						salesTeamId: $("#ins_salesTeamId").val(),
						insuranceSalerNo: $("#ins_insuranceSalerNo").val(),
						insuranceSaler: $("#ins_insuranceSaler").val(),
						rankSequenceId: $("#ins_rankSequenceId").val(),
						salesGradeId: $("#ins_salesGradeId").val(),
						salesStatus: $("#ins_salesStatus").val(),
						mobile: $("#ins_mobile").val(),
						cardNo: $("#ins_cardNo").val(),
						minJoinDate: $("#minJoinDate").val(),
						maxJoinDate: $("#maxJoinDate").val(),
						minQuitDate: $("#minQuitDate").val(),
						maxQuitDate: $("#maxQuitDate").val()


					};
					return temp;
				},
				
				updateStatusCheck:function(status){
					var nowStatus = $('#insuranceSalesInfo-table').bootstrapTable('getSelections')[0].SALES_STATUS;
					if(status == nowStatus){
						if(nowStatus=='2'){
			            	 $.alert({
			                     title: '提示信息！',
			                     content: '该员工已离职！',
			                     type: 'red'
			                 });							
						}else if(nowStatus=='3'){
			            	 $.alert({
			                     title: '提示信息！',
			                     content: '该员工已拉黑！',
			                     type: 'red'
			                 });							
						}
						return false;
					}else{
						if(status=='2'){
							openDetailTab('insuranceSalesInfo:salerQuit');
						}else if(status=='3'){
							openDetailTab('insuranceSalesInfo:black');
						}
					}
				},
				
		        checkSingleData:function () {
		            var selected = $('#insuranceSalesInfo-table').bootstrapTable('getSelections');
		            if (selected.length == 0) {
		            	 $.alert({
		                     title: '提示信息！',
		                     content: '至少选择一条记录！',
		                     type: 'red'
		                 });
		                return false;
		            }else if (selected.length > 1){
		            	 $.alert({
		                     title: '提示信息！',
		                     content: '该操作只能选择一条记录!',
		                     type: 'blue'
		                 });
		            }else {
		            	insuranceSalesInfo.seItem = selected[0];
		                return true;
		            }
		        },

				search:function(){
					$("#insuranceSalesInfo-table").bootstrapTable('destroy');
					insuranceSalesInfo.init();
				},	
				
				empty:function(){
					$("#ins_salesOrgId").val("");
					$("#ins_salesTeamId").val("");
					$("#ins_insuranceSalerNo").val("");
					$("#ins_insuranceSaler").val("");
					$("#ins_rankSequenceId").val("");
					$("#ins_salesGradeId").val("");
					$("#ins_salesStatus").val("");
					$("#ins_mobile").val("");
					$("#ins_cardNo").val("");
					$("#minJoinDate").val("");
					$("#maxJoinDate").val("");
					$("#minQuitDate").val("");
					$("#maxQuitDate").val("");
					$("#insuranceSalesInfo-table").bootstrapTable('refresh');
				},
		        
		        //关闭模态框
		        closeDlg:function () {
		            $("#salerQuitAddDlg").modal('hide');
				    document.getElementById("saveButton").removeAttribute("disabled");
				    insuranceSalesInfo.formValidator();           
		        },
			}
		}();

			 function downloadTemplate() {
            window.open("${path}/upload/files/销售人员模板.xls");
        }
        
        function downloadUpdateBatch() {
			window.open("${path}/upload/files/销售人员批量修改模板.xls");
		}
		 function downloadRelation() {
            window.open("${path}/upload/files/员工关系导入.xls");
        }

         function downloadQuit() {
            window.open("${path}/upload/files/离职人员处理.xls");
        }

         function closeDlg() {
            $("#alertFile").modal('hide');
        }
		function alertFile() {
            $("#alertFile").modal('show');
        }

        function alertUpdateFile () {
			$("#alertUpdateFile").modal('show');
		}

		 function closeUpdateDlg() {
            $("#alertUpdateFile").modal('hide');
        }
         function alertRelationFile () {
			$("#alertRelationFile").modal('show');
		}
		 function closeRelationFileDlg() {
            $("#alertRelationFile").modal('hide');
        }

         function alertQuitFile () {
			$("#alertQuitFile").modal('show');
		}
		 function closeQuitFileDlg() {
            $("#alertQuitFile").modal('hide');
        }

         function upload() {
            var multipart = $("#fileName").val();
            if (multipart == "" || multipart == null) {
                alert("请先选择文件!");
                return;
            }
            closeDlg();
            $("#loadingModal").modal('show');
            $("#fileForm").ajaxSubmit({
                type: 'POST',
                url: 'insuranceSalesInfo/importSalesInfo',
                dataType: 'json',
                success: function (data) {
                    $('#loadingModal').modal('hide');
                    if (data.code == "200") {
                        $("#insuranceSalesInfo-table").bootstrapTable('refresh');
                        alert("文件导入成功")
                    } else if(data.code == "400"){
                        alert("文件导入失败请重新上传")
                    }else if (data.code == "0000"){
                        alert("文件不能为空")
                    }else if (data.code == "0001"){
                        alert("导入失败：原因-"+data.msg)
                    }else if(data.code == "500"){
                       $.alert({
                           title: '导入失败！',
                           content:data.error,
                           type: 'red'

                       })
                    }
                },
                error: function (data) {
                    $('#loadingModal').modal('hide');
                    alert("系统异常")
                }
            })
        }

        function upUpdateload() {
			var multipart = $("#fileupdateName").val();
            if (multipart == "" || multipart == null) {
                alert("请先选择文件!");
                return;
            }
            closeUpdateDlg();
            $("#loadingModal").modal('show');
            $("#fileupdateForm").ajaxSubmit({
                type: 'POST',
                url: 'insuranceSalesInfo/importUpdateSalesInfo',
                dataType: 'json',
                success: function (data) {
                    $('#loadingModal').modal('hide');
                    if (data.code == "200") {
                        $("#insuranceSalesInfo-table").bootstrapTable('refresh');
                        alert("文件导入成功")
                    } else if(data.code == "400"){
                        alert("文件导入失败请重新上传")
                    }else if (data.code == "0000"){
                        alert("文件不能为空")
                    }else if (data.code == "0001"){
                        alert("导入失败：原因-"+data.msg)
                    }else if(data.code == "500"){
                       $.alert({
                           title: '导入失败！',
                           content:data.error,
                           type: 'red'

                       })
                    }
                },
                error: function (data) {
                    $('#loadingModal').modal('hide');
                    alert("系统异常")
                }
            })

		}


		 function quitload() {
			var multipart = $("#fileQuitName").val();
            if (multipart == "" || multipart == null) {
                alert("请先选择文件!");
                return;
            }
            closeQuitFileDlg();
            $("#loadingModal").modal('show');
            $("#fileQuitForm").ajaxSubmit({
                type: 'POST',
                url: 'insuranceSalesInfo/importQuit',
                dataType: 'json',
                success: function (data) {
                    $('#loadingModal').modal('hide');
                    if (data.code == "200") {
                        $("#insuranceSalesInfo-table").bootstrapTable('refresh');
                        alert("批量处理离职成功")
                    } else if(data.code == "400"){
                        alert("批量处理离职失败请重新上传")
                    }else if (data.code == "0000"){
                        alert("文件不能为空")
                    }else if (data.code == "0001"){
                        alert("批量处理离职失败：原因-"+data.msg)
                    }else if(data.code == "500"){
                       $.alert({
                           title: '处理失败！',
                           content:data.error,
                           type: 'red'

                       })
                    }
                },
                error: function (data) {
                    $('#loadingModal').modal('hide');
                    alert("系统异常")
                }
            })

		}


		 function relationload() {
			var multipart = $("#fileRelationName").val();
            if (multipart == "" || multipart == null) {
                alert("请先选择文件!");
                return;
            }
            closeRelationFileDlg();
            $("#loadingModal").modal('show');
            $("#fileRelationForm").ajaxSubmit({
                type: 'POST',
                url: 'insuranceSalesInfo/importRelation',
                dataType: 'json',
                success: function (data) {
                    $('#loadingModal').modal('hide');
                    if (data.code == "200") {
                        $("#insuranceSalesInfo-table").bootstrapTable('refresh');
                        alert("文件导入成功")
                    } else if(data.code == "400"){
                        alert("文件导入失败请重新上传")
                    }else if (data.code == "0000"){
                        alert("文件不能为空")
                    }else if (data.code == "0001"){
                        alert("导入失败：原因-"+data.msg)
                    }else if(data.code == "500"){
                       $.alert({
                           title: '导入失败！',
                           content:data.error,
                           type: 'red'

                       })
                    }
                },
                error: function (data) {
                    $('#loadingModal').modal('hide');
                    alert("系统异常")
                }
            })

		}

	</script>
 
</head>
<body>

<div class="panel panel-default">
	<div class="panel-body">
		<form id="SearchConForm" class=" form-inline hz-form-inline">
		  	<div class="form-group">
		  		<label class="control-label">组织机构</label>
				<select class="form-control" id="ins_salesOrgId" name="salesOrgId">
		              <option value="">销售机构</option>
		        </select>
		  	</div>
		  	<div class="form-group">
		  		<label class="control-label">销售团队</label>
				<select class="form-control" id="ins_salesTeamId" name="salesTeamId">
		             <option value="">销售团队</option>
		        </select>
	      	</div>
		  	<div class="form-group">
		  		<label class="control-label">员工工号</label>
		   		<input type="text" class="form-control" id="ins_insuranceSalerNo" name="insuranceSalerNo" placeholder="请输入员工工号">
		  	</div>
		  	<div class="form-group">
		  		<label class="control-label">员工姓名</label>
		   		<input type="text" class="form-control" id="ins_insuranceSaler" name="insuranceSaler" placeholder="请输入员工姓名">
		  	</div>

		  	<div class="form-group">
		  		<label class="control-label">职级序列</label>
				<select class="form-control" id="ins_rankSequenceId" name="rankSequenceId">
		             <option value="">职级序列</option>
		        </select>
		  	</div>
		  	<div class="form-group">
		  		<label class="control-label">员工职级</label>
				<select class="form-control" id="ins_salesGradeId" name="salesGradeId">
		             <option value="">员工职级</option>
		        </select>
	      	</div>

			<div class="form-group">
				<label class="control-label">状态</label>
				<select class="form-control" id="ins_salesStatus" name="salesStatus">
					<option value="">状态</option>
					<option value="0">试用</option>
					<option value="1">正式</option>
					<option value="2">离职</option>
					<option value="3">黑名单</option>

				</select>
			</div>
			<div class="form-group">
				<label class="control-label">手机号码</label>
				<input class="form-control" type="text" id="ins_mobile" name="mobile" oninput = "value=value.replace(/[^\d]/g,'')" maxlength="11" placeholder="请输入手机号码">
			</div>
			<div class="form-group">
				<label class="control-label">证件号码</label>
				<input class="form-control" type="text" id="ins_cardNo" name="cardNo" <%--oninput="value=value.replace(/[^\w\.\/]/ig,'')"--%> placeholder="请输入证件号码">
			</div>
			<div class="form-group">
				<label class="control-label">入职日期</label>
				<input class="form-control input-sm Wdate" id="minJoinDate" name="minJoinDate" type="text" placeholder="请选择入职时间" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'maxJoinDate\');}',dateFmt:'yyyy-MM-dd',readOnly:true})">
				<label class="control-label control-label-time">至</label>
				<input class="form-control input-sm Wdate" id="maxJoinDate" name="maxJoinDate" type="text" placeholder="请选择入职时间" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})">
			</div>
			<div class="form-group">
				<label class="control-label">离职日期</label>
				<input class="form-control input-sm Wdate" id="minQuitDate" name="minQuitDate" type="text" placeholder="请选择离职时间" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'maxQuitDate\');}',dateFmt:'yyyy-MM-dd',readOnly:true})">
				<label class="control-label control-label-time">至</label>
				<input class="form-control input-sm Wdate" id="maxQuitDate" name="maxQuitDate" type="text" placeholder="请选择离职时间" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})">
			</div>
	       	<div class="form-group pull-right">
		  		<button type="button" onclick="insuranceSalesInfo.search()" class="btn btn-info ">
		   			<span class="glyphicon glyphicon-search" aria-hidden="true" >搜索</span>
		   		</button>
		   		<button type="button" onclick="insuranceSalesInfo.empty()" class="btn btn-danger ">
		   			<span class="glyphicon glyphicon-remove" aria-hidden="true" >清空</span>
		   		</button>
	   	  	</div>
		</form>
	</div>
</div>

<!--列表 -->
<div style="width: 99%;overflow: auto;">
	<table id="insuranceSalesInfo-table" class="table table-hover table-striped table-condensed table-bordered"></table>
</div>

<!-- 导入文件div -->
<div id="alertFile" class="modal fade" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false"
     aria-labelledby="zsadd_myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="zsadd_myModalLabel">上传文件</h4>
            </div>
            <div class="container">
                <form class="form-horizontal" id="fileForm" enctype="multipart/form-data">
                    <div class="form-group">
                        <label class="col-md-2 control-label">选择文件</label>
                        <div class="col-md-3 ">
                            <input type="file" name="file" id="fileName"/>
                        </div>
                    </div>
                    <div class="modal-footer col-md-6">
                        <!--用来清空表单数据-->
                        <input type="reset" name="reset" style="display: none;"/>
                        <button type="button" class="btn btn-default" onclick="closeDlg()">关闭</button>
                        <button id="zs_saveButton" type="button" onclick="upload()" class="btn btn-primary">保存</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<div id="alertUpdateFile"class="modal fade" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false"
     aria-labelledby="zsupdate_myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="zsupdate_myModalLabel">上传文件</h4>
            </div>
            <div class="container">
                <form class="form-horizontal" id="fileupdateForm" enctype="multipart/form-data">
                    <div class="form-group">
                        <label class="col-md-2 control-label">选择文件</label>
                        <div class="col-md-3 ">
                            <input type="file" name="file" id="fileupdateName"/>
                        </div>
                    </div>
                    <div class="modal-footer col-md-6">
                        <!--用来清空表单数据-->
                        <input type="reset" name="reset" style="display: none;"/>
                        <button type="button" class="btn btn-default" onclick="closeUpdateDlg()">关闭</button>
                        <button id="zs_updatesaveButton" type="button" onclick="upUpdateload()" class="btn btn-primary">保存</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<div id="alertRelationFile"class="modal fade" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false"
     aria-labelledby="relation_myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="relation_myModalLabel">上传文件</h4>
            </div>
            <div class="container">
                <form class="form-horizontal" id="fileRelationForm" enctype="multipart/form-data">
                    <div class="form-group">
                        <label class="col-md-2 control-label">选择文件</label>
                        <div class="col-md-3 ">
                            <input type="file" name="file" id="fileRelationName"/>
                        </div>
                    </div>
                    <div class="modal-footer col-md-6">
                        <!--用来清空表单数据-->
                        <input type="reset" name="reset" style="display: none;"/>
                        <button type="button" class="btn btn-default" onclick="closeRelationFileDlg()">关闭</button>
                        <button id="zs_RelationsaveButton" type="button" onclick="relationload()" class="btn btn-primary">保存</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<div id="alertQuitFile"class="modal fade" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false"
     aria-labelledby="quit_myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="quit_myModalLabel">上传文件</h4>
            </div>
            <div class="container">
                <form class="form-horizontal" id="fileQuitForm" enctype="multipart/form-data">
                    <div class="form-group">
                        <label class="col-md-2 control-label">选择文件</label>
                        <div class="col-md-3 ">
                            <input type="file" name="file" id="fileQuitName"/>
                        </div>
                    </div>
                    <div class="modal-footer col-md-6">
                        <!--用来清空表单数据-->
                        <input type="reset" name="reset" style="display: none;"/>
                        <button type="button" class="btn btn-default" onclick="closeQuitFileDlg()">关闭</button>
                        <button id="zs_QuitsaveButton" type="button" onclick="quitload()" class="btn btn-primary">保存</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>


<%--缓冲div--%>
<div class="modal fade" id="loadingModal">
    <div style="width: 200px;height:20px; z-index: 20000; position: absolute; text-align: center; left: 50%; top: 50%;margin-left:-100px;margin-top:-10px">
        <div class="progress progress-striped active" style="margin-bottom: 0;">
            <div class="progress-bar" style="width: 100%;"></div>
        </div>
        <h5 style="color:black"><strong>正在缓冲文件...预计1-5秒，请稍等！</strong></h5>
    </div>
</div>
<!--toolbar  -->
<div id="insuranceSalesInfo_toolbar" class="btn-toolbar">
	<shiro:hasPermission name="insuranceSalesInfo:add">
    	<button class="btn btn-info" type="button" onclick="createAddProcessTab('insuranceSalesInfo:add','')">
	    		<span class="glyphicon glyphicon-plus" >添加</span>
    	</button>
		<button type="button" class=" btn btn-primary" onclick="downloadTemplate()">
			<span class="glyphicon glyphicon-book">下载新增导入模板</span>
		</button>
		<button class="btn btn-warning " type="button" onclick="alertFile()">
			<span class="glyphicon glyphicon-import" >新增导入销售人员</span>
		</button>
    </shiro:hasPermission>
    <shiro:hasPermission name="insuranceSalesInfo:update">
    	<button type="button" class=" btn btn-info" onclick="openDetailTab('insuranceSalesInfo:update')">
    			<span class="glyphicon glyphicon-pencil" >修改</span>
    	</button>
		<button type="button" class=" btn btn-primary" onclick="downloadUpdateBatch()">
			<span class="glyphicon glyphicon-book">下载修改导入模板</span>
		</button>
		<button class="btn btn-warning " type="button" onclick="alertUpdateFile()">
			<span class="glyphicon glyphicon-import" >修改导入销售人员</span>
		</button>
    </shiro:hasPermission>
    <shiro:hasPermission name="insuranceSalesInfo:export">
	    <button onclick="exportAll('full')" type="button" class=" btn btn-default ">
	      		<span class="glyphicon glyphicon-paste" aria-hidden="true" >导出全部</span>
	    </button>
 	</shiro:hasPermission>
    <shiro:hasPermission name="insuranceSalesInfo:export">
	    <button onclick="exportSelect('full')" type="button" class=" btn btn-default ">
	      		<span class="glyphicon glyphicon-paste" aria-hidden="true" >导出选中</span>
	    </button>
 	</shiro:hasPermission>
    <shiro:hasPermission name="insuranceSalesInfo:export">
	   <br/><br/> <button onclick="exportAll('min')" type="button" class=" btn btn-default ">
	      		<span class="glyphicon glyphicon-paste" aria-hidden="true" >导出全部执业证及销售关系</span>
	    </button>
 	</shiro:hasPermission>
    <shiro:hasPermission name="insuranceSalesInfo:export">
	    <button onclick="exportSelect('min')" type="button" class=" btn btn-default ">
	      		<span class="glyphicon glyphicon-paste" aria-hidden="true" >导出选中执业证及销售关系</span>
	    </button>
 	</shiro:hasPermission>
    <shiro:hasPermission name="insuranceSalesInfo:salerQuit">
    	<button type="button" class=" btn btn-info" onclick="insuranceSalesInfo.updateStatusCheck('2')">
    			<span class="glyphicon glyphicon-pencil" >人员离职</span>
    	</button>
		<button type="button" class=" btn btn-primary  " onclick="downloadQuit()">
			<span class="glyphicon glyphicon-book">下载批处理人员离职模板</span>
		</button>
		<button class="btn btn-warning " type="button" onclick="alertQuitFile()">
			<span class="glyphicon glyphicon-import" >批处理人员离职</span>
		</button>
    </shiro:hasPermission>
    <shiro:hasPermission name="insuranceSalesInfo:move">
    	<button type="button" class=" btn btn-info" onclick="openDetailTab('insuranceSalesInfo:move')">
    			<span class="glyphicon glyphicon-pencil" >员工异动</span>
    	</button>
    </shiro:hasPermission> 
    <shiro:hasPermission name="insuranceSalesInfo:black">
    	<button type="button" class=" btn btn-info" onclick="insuranceSalesInfo.updateStatusCheck('3')">
    			<span class="glyphicon glyphicon-pencil" >加入黑名单</span>
    	</button>
    </shiro:hasPermission>
	<shiro:hasPermission name="salesAssess:list">
	<br/><br/>	<button type="button" class=" btn btn-info" onclick="openDetailTab('salesAssess:list')">
			<span class="glyphicon glyphicon-pencil" >考核设置</span>
		</button>
	</shiro:hasPermission>
	<shiro:hasPermission name="salesAssess:relation">
		<button type="button" class=" btn btn-primary " onclick="downloadRelation()">
			<span class="glyphicon glyphicon-book">下载员工关系模板</span>
		</button>
		<button class="btn btn-warning " type="button" onclick="alertRelationFile()">
			<span class="glyphicon glyphicon-import" >批量导入员工关系</span>
		</button>
	</shiro:hasPermission>
	<shiro:hasPermission name="insuranceSalesInfo:delete">
    	<button class=" btn btn-danger" type="button" onclick="">
    			<span class="glyphicon glyphicon-remove" >删除</span>
    	</button>
    </shiro:hasPermission>

	<%--<shiro:hasPermission name="workGroup:update">--%>
		<%--<button type="button" class=" btn btn-info" onclick="openWorkGroupTab('workGroup:update')">--%>
			<%--<span class="glyphicon glyphicon-pencil" >设置工作组</span>--%>
		<%--</button>--%>
	<%--</shiro:hasPermission>--%>
</div> 
</body>
</html>