<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/core.jsp" %>
<!DOCTYPE  html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <title>销售组织机构树</title>
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Cache" content="no-cache">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   	<link rel="stylesheet" href="${path}/css/system/role/role.css">
    <script src="${path}/static/js/bootstrap.js"></script>
    <link rel="stylesheet" href="${path}/js/insurance/dtree.css">
    <script src="${path}/js/insurance/dtree.js"></script>
    <script src="${path}/js/jquery-form.js"></script>
    <style>
        html,body,div,span,applet,object,iframe,h1,h2,h3,h4,h5,h6,p,blockquote,pre,a,abbr,acronym,address,big,cite,code,del,dfn,em,img,ins,kbd,q,s,samp,small,strike,strong,sub,sup,tt,var,b,u,i,center,dl,dt,dd,ol,ul,li,fieldset,form,label,legend,table,caption,tbody,tfoot,thead,tr,th,td,article,aside,canvas,details,figcaption,figure,footer,header,hgroup,menu,nav,section,summary,time,mark,audio,video { margin: 0;padding: 0;border: 0;outline: 0;}
        /*当样式表里font-size<12px时，中文版chrome浏览器里字体显示仍为12px，修复bug*/
        html,body,form,fieldset,p,div,h1,h2,h3,h4,h5,h6 { -webkit-text-size-adjust: none;}
        /* 重设 HTML5 标签*/
        article,aside,details,figcaption,figure,footer,header,hgroup,menu,nav,section {display: block;clear: both;}
        body {font-family: "Helvetica Neue", Helvetica, STHeiTi, sans-serif; -webkit-text-size-adjust: none; color: #111; background: #eff1f3; -webkit-text-size-adjust: none; min-width: 320px;  }
        html,body{height:100%;}
        h1,h2,h3,h4,h5,h6 {font-size: 100%; font-weight: normal;}
        form {display: inline;}
        textarea {resize: none;}
        table {border-collapse: collapse; border-spacing: 0;}
        ul,ol {list-style: none;}
        a {text-decoration: none; color: #111;}
        a:hover {color: #111;text-decoration: none;}
        a:visited {text-decoration: none;}
        .wrap{width: 100%;height: 100%;overflow: hidden;margin-top:20px;}
        .wrap .left{float: left;width: 30%;height:100%;border:1px solid #999999}
        .wrap .right{float: right;width:70%;height:100%;border:1px solid #ff1f3;padding-left:10px;overflow:auto}
        .wrap .data-item .cont{padding: 40px;}
    </style> 
    
    <script type="text/javascript">
	$(function () {	
		salesTeamInfo.init();
		salesInfo.init();
	});
	
	function getTeamSales1(salesTeamId){
	     $("#salesId").show()
		// $("#sales-table").find("tr").remove();
		// $("#sales-table").append("<tr><td>序号</td><td>姓名</td><td>工号</td><td>序号</td><td>姓名</td><td>工号</td></tr>");
		 $.ajax({
	         type: "POST",
	         url: "insuranceSalesInfo/insuranceSalesList",
	         data:{salesTeamId:salesTeamId},
	         dataType: "json",
	         success: function(data){
	        	 	var sales = data.rows;
					for(var i=0;i<data.rows.length;){
						var j = i+1;
						var k = i+2;
						if(i+1<data.rows.length){
							var h = "<tr><td>"+j+"</td><td>"+data.rows[i].insuranceSaler+"</td><td>"+data.rows[i].insuranceSalerNo+"</td>"
									+"<td>"+k+"</td><td>"+data.rows[j].insuranceSaler+"</td><td>"+data.rows[j].insuranceSalerNo+"</td></tr>";
							$("#sales-table").append(h);
						}else{
							var h = "<tr><td>"+j+"</td><td>"+data.rows[i].insuranceSaler+"</td><td>"+data.rows[i].insuranceSalerNo+"</td>"
							+"<td></td><td></td><td></td></tr>";
							$("#sales-table").append(h);							
						}
						i = k;
						if(i>data.rows.length)
							break;
					}
	         }
	     })		
	}

	var salesInfo = function (){
        	return {
                init: function () {
                    $('#sales-table').bootstrapTable({
                        url: "insuranceSalesInfo/insuranceSalesPage",
                        method: "post",
                        dataType: "json",
                        contentType: "application/x-www-form-urlencoded",
                        striped: true,//隔行变色
                        cache: false,  //是否使用缓存
                        pagination: true, //分页
                        sortable: false, //是否启用排序
                        singleSelect: false,
                        search: false, //显示搜索框
                        buttonsAlign: "right", //按钮对齐方式
                        showRefresh: false,//是否显示刷新按钮
                        sidePagination: "server", //服务端处理分页
                        pageSize: 5, //默认每页条数
                        pageNumber: 1, //默认分页
                        pageList: [5, 10, 20, 50],//分页数
                        toolbar: "",
                        showColumns: false, //显示隐藏列
                        uniqueId: "INSURANCE_SALES_ID", //每一行的唯一标识，一般为主键列
                        queryParamsType: '',
                        queryParams: salesInfo.queryParams,//传递参数（*）
                        columns: [{
                            checkbox: true
                        }, {
                            field: "INSURANCE_SALES_ID",
                            title: "编号",
                            align: "center",
                            valign: "middle",
                            sortable: "true",
                            formatter: function (value, row, index) {
                                var pageSize = $('#sales-table').bootstrapTable('getOptions').pageSize;//通过表的#id 可以得到每页多少条
                                var pageNumber = $('#sales-table').bootstrapTable('getOptions').pageNumber;
                                return pageSize * (pageNumber - 1) + index + 1;    //返回每条的序号： 每页条数 * （当前页 - 1 ）+ 序号
                            }
                        }, {
                            field: "INSURANCE_SALER",
                            title: "员工姓名",
                            align: "center",
                            valign: "middle",
                            sortable: "true"
                        }, {
                            field: "SALES_GRADE_NAME",
                            title: "销售职级",
                            align: "center",
                            valign: "middle",
                            sortable: "true"
                        }, {
                            field: "INSURANCE_SALER_NO",
                            title: "员工工号",
                            align: "center",
                            valign: "middle",
                            sortable: "true"
                        }],
                        // bootstrap-table-treetreegrid.js 插件配置 -- end
                        formatLoadingMessage: function () {
                            return "请稍等，正在加载中...";
                        },
                        formatNoMatches: function () {
                            return '无符合条件的记录';
                        }
                    });
                },

                queryParams: function (params) {
                    var temp = {
                        pageSize: params.pageSize,  //页面大小
                        pageNo: params.pageNumber, //页码
                        salesTeamId: $("#searchSalesTeamId").val(),
                    };
                    return temp;
                },

                search: function () {
                    $("#sales-table").bootstrapTable('refresh');
                }
    	}
		}();

	var salesTeamInfo = function (){
		return{
			init:function(){
	            $('#salesTeamInfot-table').bootstrapTable({
	            	url: "salesTeamInfo/salesTeamInfoList",
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
	                pageSize : 5, //默认每页条数
	                pageNumber : 1, //默认分页
	                pageList : [5, 10, 20, 50 ],//分页数
	                toolbar:"",
	                showColumns : false, //显示隐藏列
	                uniqueId: "SALES_TEAM_ID", //每一行的唯一标识，一般为主键列
	                queryParamsType:'',
	                queryParams:salesTeamInfo.queryParams,//传递参数（*）
	                columns : [{
	                    checkbox: true
	            	},{
	                    field : "SALES_TEAM_ID",
	                    title : "编号",
	                    align : "center",
	                    valign : "middle",
	                    sortable : "true",
  			            formatter:function(value,row,index){
  			                var pageSize=$('#salesTeamInfot-table').bootstrapTable('getOptions').pageSize;//通过表的#id 可以得到每页多少条
  			                var pageNumber=$('#salesTeamInfot-table').bootstrapTable('getOptions').pageNumber;
  			                return pageSize * (pageNumber - 1) + index + 1;    //返回每条的序号： 每页条数 * （当前页 - 1 ）+ 序号
  			            }
	                },{
	                    field : "SALES_TEAM_NAME",
	                    title : "团队名称",
	                    align : "center",
	                    valign : "middle",
	                    sortable : "true",
	                    formatter:function(value,row,index){ 			            								
	            	        return "<a href='javascript:void(0)' style='color:blue' onclick='getTeamSales("+row.SALES_TEAM_ID+","+'"'+row.SALES_TEAM_NAME+'"'+")'>"+row.SALES_TEAM_NAME+"</a>";
	                    }
	                },{
	                    field : "SALES_TEAM_CODE",
	                    title : "团队编码",
	                    align : "center",
	                    valign : "middle",
	                    sortable : "true"
	                }],
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
					salesOrgId:$("#searchSalesOrgId").val(),
				};
				return temp;
			},			
			
	        search:function () {
	        	 $("#salesTeamInfot-table").bootstrapTable('refresh');
	        },
             checkSingleData:function () {
		            var selected = $('#salesTeamInfot-table').bootstrapTable('getSelections');
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
		            	salesTeamInfo.seItem = selected[0];
		                return true;
		            }
		        },
		}
	}();

		 function downloadTemplate() {
            window.open("${path}/upload/files/销售团队模板.xls");
        }

         function closeDlg() {
            $("#alertFile").modal('hide');
        }
		function alertFile() {
            $("#alertFile").modal('show');
        }

        function downloadSalesTemplate() {
            window.open("${path}/upload/files/销售人员模板.xls");
        }

         function closeSalesDlg() {
            $("#alertSalesFile").modal('hide');
        }
		function alertSalesFile() {
            $("#alertSalesFile").modal('show');
        }
/*销售团队导入*/
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
                url: 'salesTeamInfo/importSalesTeam',
                dataType: 'json',
                success: function (data) {
                    $('#loadingModal').modal('hide');
                    if (data.code == "200") {
                        clickThisTab();
                       // d.aNodes[d.selectedNode].click();
                       // $("#salesTeamInfot-table").bootstrapTable('refresh');
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

        /*销售人员导入*/
         function uploadSales() {
            var multipart = $("#fileSalesName").val();
            if (multipart == "" || multipart == null) {
                alert("请先选择文件!");
                return;
            }
            closeSalesDlg();
            $("#loadingModal").modal('show');
            $("#fileSalesForm").ajaxSubmit({
                type: 'POST',
                url: 'insuranceSalesInfo/importSalesInfo',
                dataType: 'json',
                success: function (data) {
                    $('#loadingModal').modal('hide');
                    if (data.code == "200") {
                        $("#sales-table").bootstrapTable('refresh');
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

        	function openDetailTab(permission){
			if(salesTeamInfo.checkSingleData()){
				var salesTeamId = salesTeamInfo.seItem.SALES_TEAM_ID;
				//alert(insComanpy.seItem.INSURANCE_COMPANY_ID);
				createAddProcessTab('salesTeamInfo:update',salesTeamId);
			}
		}
		
		function createAddProcessTabTeamTree(permission,rowId) {
            var  searchSalesOrgId =   $("#searchSalesOrgId").val();
             createAddProcessTab(permission,searchSalesOrgId);
        }

        function createAddProcessTabSalesTree(permission,rowId) {
             var  searchSalesTeamId =   $("#searchSalesTeamId").val();
             createAddProcessTab(permission,searchSalesTeamId);
        }
    
    </script>
       
</head>
<body>
<input type="hidden" id="treeJson" name="treeJson" value='${salesOrgList}'>
<div class="wrap">
        <div class="left dtree" id="orgTreeDiv" style="overflow-y:auto">
			<script type="text/javascript">	
				d = new dTree('d');
				var salesOrgList = $("#treeJson").val();
				d.add(0,-1,'销售组织','javascript:teok()');
				$.each(JSON.parse(salesOrgList),function(key, value) {					
					d.add(value.salesOrgId,0,value.salesOrgName,'javascript:teok('+value.salesOrgId+')');
					if(value.orgChildrens){
						setChild(d,value.salesOrgId,value.orgChildrens);
					}
					if(value.orgTeamInfos){
                       setTeam(d,value.salesOrgId,value.orgTeamInfos);
                    }
				});					
				function setChild(d,i,data){
					$.each(data,function(key, value) {
						d.add(value.salesOrgId,i,value.salesOrgName,'javascript:teok('+value.salesOrgId+')');
						if(value.orgChildrens){
							setChild(d,value.salesOrgId,value.orgChildrens);
						}
						if(value.orgTeamInfos){
                           setTeam(d,value.salesOrgId,value.orgTeamInfos);
                        }
					});
				}
				function setTeam(d,i,data){
				    $.each(data,function (key,value) {
                       d.add(i+'_'+value.salesTeamId,i,value.salesTeamName,'javascript:teok1('+value.salesTeamId+","+"'"+value.salesTeamName+"'"+')');
                       if(value.childTeamInfoList){
                           setTeam(d,i+'_'+value.salesTeamId,value.childTeamInfoList);
                       }

                    });

                }
				function teok(salesOrgId){
				    debugger;
				     $("#teamId").show();
				     $("#salesId").hide();
					$("#searchSalesOrgId").val(salesOrgId);
					salesTeamInfo.search();

				//	var h = "";
				//	$("#sales-table").find("tr").remove();
				//	$("#sales-table").append(h);
				}

				function getTeamSales(salesTeamId,salesTeamName) {
				    $("#salesId").show();
                     $("#searchSalesTeamId").val(salesTeamId);
                       $("#salesTeam_span").html(salesTeamName+"：");
                     salesInfo.search();
                }

				function teok1(salesTeamId,salesTeamName){
				    $("#salesId").show();
				   $("#teamId").hide();
				    $("#searchSalesTeamId").val(salesTeamId);
				    $("#salesTeam_span").html(salesTeamName+"：");
				    salesInfo.search();
				}
				
				document.getElementById("orgTreeDiv").innerHTML=d;		
			</script> 
        </div>
        <div class="right">
            <div class="data-item">
				<div style="overflow:scroll;" id="teamId">
					<input type="hidden" id="searchSalesOrgId" value="" />
					<%--<button type="button" onclick="" class="btn btn-info ">--%>
			   			<%--<span class="glyphicon glyphicon-sort-by-attributes-alt" aria-hidden="true" >销售团队</span>--%>
			   		<%--</button>--%>
                    <span class="glyphicon glyphicon-flag" aria-hidden="true" style="text-shadow: #f8ac59 7px 5px 15px; color: rgb(237, 160, 76); font-size: 14px">销售团队：</span>
                        <shiro:hasPermission name="salesTeamInfo:add">
                        <button class="btn btn-info" type="button" onclick="createAddProcessTabTeamTree('salesTeamInfo:add','')">
                                <span class="glyphicon glyphicon-plus" >添加</span>
                        </button>
                        <button type="button" class=" btn btn-primary" onclick="downloadTemplate()">
                            <span class="glyphicon glyphicon-book">导入模板</span>
                        </button>
                        <button class="btn btn-success" type="button" onclick="alertFile()">
                            <span class="glyphicon glyphicon-import" >批量导入</span>
                        </button>
                    </shiro:hasPermission>
                     <shiro:hasPermission name="salesTeamInfo:update">
                        <button type="button" class=" btn btn-info" onclick="openDetailTab('salesTeamInfo:update')">
                                <span class="glyphicon glyphicon-pencil" >修改</span>
                        </button>
                    </shiro:hasPermission>
                   <%-- <shiro:hasPermission name="salesTeamInfo:update">
                        <button type="button" class=" btn btn-info" onclick="openDetailTab('salesTeamInfo:update')">
                                <span class="glyphicon glyphicon-pencil" >修改</span>
                        </button>
                    </shiro:hasPermission>--%>
					<table id="salesTeamInfot-table" style="border:1px solid #999999" class="table table-hover table-striped table-condensed">
					</table>
				</div>
				<div style="overflow:scroll;" id="salesId">
                    <input type="hidden" id="searchSalesTeamId" value="-1" />
			   			<span id="salesTeam_span" class="glyphicon glyphicon-user" aria-hidden="true" style="text-shadow: #f8ac59 7px 5px 15px; color: rgb(237, 160, 76); font-size: 14px" >团队人员：</span>
                    <shiro:hasPermission name="insuranceSalesInfo:add">
                    <button class="btn btn-info" type="button" onclick="createAddProcessTabSalesTree('insuranceSalesInfo:add','')">
                            <span class="glyphicon glyphicon-plus" >添加</span>
                    </button>
                    <button type="button" class=" btn btn-primary" onclick="downloadSalesTemplate()">
                        <span class="glyphicon glyphicon-book">导入模板</span>
                    </button>
                    <button class="btn btn-success" type="button" onclick="alertSalesFile()">
                        <span class="glyphicon glyphicon-import" >批量导入</span>
                    </button>
                </shiro:hasPermission>
                    <table id="sales-table" style="border:1px solid #999999" class="table table-hover table-striped table-condensed" >
					</table>
					<%--<table id="sales-table" style="border:1px solid #999999" class="table table-hover table-striped table-condensed">
					&lt;%&ndash;<tr><td>序号</td><td>姓名</td><td>工号</td><td>序号</td><td>姓名</td><td>工号</td></tr>&ndash;%&gt;
					</table>--%>
				</div>				
            </div>
        </div>
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

<!-- 导入文件销售人员div -->
<div id="alertSalesFile" class="modal fade" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false"
     aria-labelledby="sales_myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="sales_myModalLabel">上传文件</h4>
            </div>
            <div class="container">
                <form class="form-horizontal" id="fileSalesForm" enctype="multipart/form-data">
                    <div class="form-group">
                        <label class="col-md-2 control-label">选择文件</label>
                        <div class="col-md-3 ">
                            <input type="file" name="file" id="fileSalesName"/>
                        </div>
                    </div>
                    <div class="modal-footer col-md-6">
                        <!--用来清空表单数据-->
                        <input type="reset" name="reset" style="display: none;"/>
                        <button type="button" class="btn btn-default" onclick="closeSalesDlg()">关闭</button>
                        <button id="sales_saveButton" type="button" onclick="uploadSales()" class="btn btn-primary">保存</button>
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

    <script>
        $(function(){
            $('#menu li').each(function(){
                var _html=$(this).html();
                $(this).click(function(){
                    $(this).siblings('li').attr('data-disabled','');
                    $('.data-item li').attr('data-disabled','');
                    if($(this).attr('data-disabled')==""){
                        $(this).attr('data-disabled','disabled');
                        $('.data-item li').removeClass('bur');
                        $('.data-item li:first').addClass('bur');
                        $('.data-item .cont').html('这是'+$('.data-item li:first').html());
                    }
                })
            })
            $('.data-item li').each(function(){
                var liHtml=$(this).html();
                $(this).click(function(){
                    $('.data-item li').removeClass('bur');
                    $(this).siblings('li').attr('data-disabled','')
                    $(this).addClass('bur');
                    if($(this).attr('data-disabled')==""){
                        $('.data-item .cont').html('这是'+liHtml);
                        $(this).attr('data-disabled','disabled')
                    }
                })
            })
        })
    </script>
</body>
</html>