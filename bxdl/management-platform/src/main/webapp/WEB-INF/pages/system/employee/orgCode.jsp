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
    <script src="${path}/js/insurance/org_code_tree.js"></script>
    <style>
        .wrap{width: 100%;height: 100%;overflow: hidden;}
        .wrap .left{float: left;width: 30%;height:100%;border:1px solid #999999}
        .wrap .right{float: right;width: 70%;height:100%;border:1px solid #ff1f3;padding-left:10px;}
    </style> 
    
    <script type="text/javascript">
	$(function () {
        salesOrgInfo.init();
	});

    var salesOrgInfo = function (){
		return{
			init:function(){
	            $('#orgCode-table').bootstrapTable({
	            	url: "employeeCodeRule/page",
	            	method:"get",
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
	                uniqueId: "id", //每一行的唯一标识，一般为主键列
	                queryParamsType:'',
	                queryParams:salesOrgInfo.queryParams,//传递参数（*）
	                columns : [ {
	                    checkbox: true
	            	}, {
	                    field : "id",
	                    title : "编号",
	                    align : "center",
	                    valign : "middle",
	                    sortable : "true"
	                },{
	                    field : "mapName",
	                    title : "机构",
	                    align : "center",
	                    valign : "middle",
	                    sortable : "true"
	                },{
	                    field : "code",
	                    title : "序号",
	                    align : "center",
	                    valign : "middle",
	                    sortable : "true",
                        formatter: function(data, type, row) {
                            return "<div style=\"text-align:center; vertical-align:middel;\" ><input id='"+type.mapId+"' value='"+data+"'></div>"
                        }
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
					pageNumber: params.pageNumber, //页码
					salesOrgId:$("#searchSalesOrgId").val(),
					type:"2",
				};
				return temp;
			},			
			
	        search:function () {
	        	 $("#orgCode-table").bootstrapTable('refresh');
	        },

            /**
             * 检查是否选中单条记录
             */
            checkSingleData:function () {
                var selected = $('#orgCode-table').bootstrapTable('getSelections');
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
                    return true;
                }
            },

            //按机构修改序号
            updateCode:function() {
                if (this.checkSingleData()) {
                    var selected = $('#orgCode-table').bootstrapTable('getSelections');
                    var id = selected[0].id;
                    var mapId = selected[0].mapId;
                    var code = $("#"+ mapId).val();
                    $.ajax({
                        url: 'employeeCodeRule',
                        type: 'put',
                        dataType: 'json',
                        data: {
                            id: id,
                            type: 2,
                            mapId: mapId,
                            code: code,
                        },
                        success: function (data) {
                            if ("200" == data.messageCode) {
                                $.alert({
                                    title: '提示信息！',
                                    content: '修改成功!',
                                    type: 'blue'
                                });

                            } else if ("200001" == data.messageCode) {
                                $.alert({
                                    title: '提示信息！',
                                    content: '该序号或省份已存在',
                                    type: 'red'
                                });
                                return;
                            } else {
                                $.alert({
                                    title: '提示信息！',
                                    content: '修改失败！',
                                    type: 'red'
                                });
                            }

                            $("#orgCode-table").bootstrapTable('refresh');
                        },
                        error: function () {
                            $.alert({
                                title: '提示信息！',
                                content: '修改失败！',
                                type: 'red'
                            });
                        }
                    });
                }
            }
		}
	}();
    
    </script>
       
</head>
<body>
<input type="hidden" id="orgTreeJson" name="treeJson" value='${salesOrgList}'>
<div class="wrap">
        <div class="left dtree" id="orgTreeCodeDiv">
			<script type="text/javascript">	
				d = new orgTree('d');
				var salesOrgList = $("#orgTreeJson").val();
                d.add(0,-1,'销售组织','javascript:teok()');
				$.each(JSON.parse(salesOrgList),function(key, value) {					
					d.add(value.salesOrgId,0,value.salesOrgName,'javascript:teok('+value.salesOrgId+')');
					if(value.orgChildrens){
						setChild(d,value.salesOrgId,value.orgChildrens);
					}
				});					
				function setChild(d,i,data){
					$.each(data,function(key, value) {
						d.add(value.salesOrgId,i,value.salesOrgName,'javascript:teok('+value.salesOrgId+')');
						if(value.orgChildrens){
							setChild(d,value.salesOrgId,value.orgChildrens);
						}
					});						
				}				
				function teok(salesOrgId){
					$("#searchSalesOrgId").val(salesOrgId);
					salesOrgInfo.search();
				}	
				
				document.getElementById("orgTreeCodeDiv").innerHTML=d;
			</script> 
        </div>
        <div class="right">
            <button type="button" onclick="salesOrgInfo.updateCode()" class="btn btn-info ">
                <span class="glyphicon glyphicon-pencil" aria-hidden="true">修改</span>
            </button>
            <div class="data-item">
				<div style="overflow:scroll;">
					<input type="hidden" id="searchSalesOrgId" value="" />
					<table id="orgCode-table" class="table table-hover table-striped table-condensed table-bordered">
					</table>
				</div>
            </div>
        </div>
</div>
</body>
</html>