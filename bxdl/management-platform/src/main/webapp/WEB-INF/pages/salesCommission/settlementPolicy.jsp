<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/core.jsp" %>
<!DOCTYPE  html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <title>可结算保单列表</title>
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Cache" content="no-cache">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   	<link rel="stylesheet" href="${path}/css/system/role/role.css">
	<link rel="stylesheet" href="${path}/static/My97DatePicker/skin/default/datepicker.css"/>
	<script type="text/javascript" src="/static/My97DatePicker/WdatePicker.js"></script>
   	<script src="${path}/js/system/comm.js" charset="UTF-8" type="text/javascript"></script>
    <script src="${path}/static/js/bootstrap.js"></script>
	<script type="text/javascript">
		function search(){
			alert("搜索")
		}
		function cla() {
			$("#ex_exampleInputName1").val('');
			$("#ex_exampleInputName2").val('');
			$("#ex_exampleInputName3").val('');
			$("#ex_exampleInputName4").val('');
			$("#ex_exampleInputName5").val('');
			$("#ex_exampleInputName6").val('');
			$("#ex_exampleInputName7").val('');
			$("#ex_exampleInputName8").val('');
			$("#ex_exampleInputName9").val('');
			$("#ex_exampleInputName20").val('');
			$("#ex_exampleInputName21").val('');
			$("#opera_exampleInputName22").val('');
			$("#opera_exampleInputName23").val('');
			$("#opera_exampleInputName24").val('');
			$("#opera_exampleInputName25").val('');
			$("#ex_auditStatus").val('');
            settlymentPolicy.search()

		}
		var settlymentPolicy = {
		    seItem: null		//选中的条目
		};

		//初始化操作
		$(function () {
            //隐藏字段
			settlymentPolicy.init();
			commSelectProductOrg("ex_exampleInputName2")
			commSalesOrgsHX("ex_exampleInputName5")
			$("#ex_exampleInputName5").on(
					"change",function(){
						commSalesTeamsHX("ex_exampleInputName6",$(this).find("option:selected").val());
					}
			)
		});

		//弹出审核或复审浮层页面
		function openDetailTab(id, auditStatus){
		    if(id == undefined || id == null){
		        return ;
			}
			if(auditStatus == undefined || auditStatus == null) {
				return ;
			}
			if(auditStatus == '3') {
		        $("#spanId").hide();
			}else {
                $("#spanId").show();
			}
            $("#auditPolicyId").val(id);
            $("#auditStatus").val(auditStatus);
            $("input[name='examine_auditresults']:checked").prop("checked",false)
            $("#datetimepicker").val('');
			$("#remark").val('');
			$("#examine_alert_div_ipe").modal('show')

		}

        //审批/复审
        function determine(){
		    var auditResults = $("input[name='examine_auditresults']:checked");
			if( auditResults == undefined || auditResults.val() == null || auditResults.val() == '' ) {
			    alert("请选择审核结果")
				return ;
			}
            if($("input[name='examine_auditresults']:checked").val() =='1'){
				if($("#datetimepicker").val() == null || $("#datetimepicker").val() == ''){
                    alert("请选择结算时间")
                    return ;
				}
			}
            // var flag = $("#examine_fileForm").data("bootstrapValidator").validate().isValid();
            // if(!flag) {
             //   return ;
			// }
			// else{
             //    alert(1111)
             //    return;
			// }
            var examine_fileForm=$("#examine_fileForm").serialize()
            $.ajax({
                url:"settlementPolicy/submitAuditResult",
                data:examine_fileForm,
                type:"post",
                dataType:"json",
                success:function(obj){
                    if (obj.messageCode=="200"){
                        $("#examine_alert_div_ipe").modal('hide');
                        alert("审核提交成功")
                        settlymentPolicy.search()
                    } else {
                        alert("审核提交失败")
                    }
                },
                error: function (obj) {
                    alert("系统异常")
                }
            })
        }
        //关闭 审批/复审页面
        function closeDlg() {
            $("#examine_alert_div_ipe").modal('hide');
            $('#examine_fileForm').data('bootstrapValidator', null);
            $("#auditPolicyId").val('');
            $("#auditStatus").val('');
            $("input[name='examine_auditresults']:checked").prop("checked",false)
            $("#datetimepicker").val('');
            $("#remark").val('');
        }

		function operateFormatter(value, row, index) {
			var id = row.POLICY_ID; //保单号
			var auditStatus = row.auditStatus; //审核状态
			var settlementType = $("#settlementType").val(); //1:待结算保单列表；2:异常结算保单列表
			if(settlementType == '2') {
                return ;
			}
			if(auditStatus == '0') { //待审核
                return [
                    '<shiro:hasPermission name="settlementPolicy:audit">',
                    '<button type="button" class=" btn btn-info" onclick="openDetailTab(\''+id+ '\',\''+ auditStatus +'\')">',
                    '<span class="glyphicon glyphicon-pencil" >审核</span>',
                    '</button>',
                    '</shiro:hasPermission>',
                ].join('');
			}else if(auditStatus == '3') { //挂起

                return [
                    '<shiro:hasPermission name="settlementPolicy:repeatAudit">',
                    '<button type="button" class=" btn btn-info" onclick="openDetailTab(\''+id+ '\',\''+ auditStatus +'\')">',
                    '<span class="glyphicon glyphicon-pencil" >复审</span>',
                    '</button>',
                    '</shiro:hasPermission>',
                ].join('');
			}else {
			    return '';
			}

		}

		var settlymentPolicy = function (){
			return{
				init:function (){
		            $('#examine_insComanpy-table').bootstrapTable({
		            	url: "settlementPolicy/getSettlementPolicyList",
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
		                toolbar:"#examine_toolbar_exa",
		                showColumns : false, //显示隐藏列
		                uniqueId: "POLICY_ID", //每一行的唯一标识，一般为主键列
		                queryParamsType:'',
		                queryParams: settlymentPolicy.queryParams,//传递参数（*）
		                columns : [{
		                    checkbox: true
		            	},{
							field: 'SerialNumber',
							title: '序号',
							formatter: function (value, row, index) {
								return index+1;
							}
						},{
							field : "INSURED_PERSON_ID",
							visible:false
						},{
		                    field : "CORRESPONDING",
		                    title : "投保单号",
		                    align : "center",
		                    valign : "middle",
		                    sortable : "true"
		                },{
                            field : "POLICY_ID",
                            title : "保单号",
                            align : "center",
                            valign : "middle",
                            sortable : "true"
                        },{
		                    field : "INSURANCE_COMPANY_NAME",
		                    title : "保险公司",
		                    align : "center",
		                    valign : "middle",
		                    sortable : "true"
		                },{
		                    field : "PRODUCT_CODE",
		                    title : "产品代码",
		                    align : "center",
		                    valign : "middle",
		                    sortable : "true"
		                },{
							field : "PRODUCT_NAME",
							title : "产品名称",
							align : "center",
							valign : "middle",
							sortable : "true"
						},{
							field : "PREMIUM",
							title : "保费",
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
							title : "营销团队",
							align : "center",
							valign : "middle",
							sortable : "true"
						},{
							field : "NAME1",
							title : "投保人",
							align : "center",
							valign : "middle",
							sortable : "true"
						},{
							field : "NAME2",
							title : "被保人",
							align : "center",
							valign : "middle",
							sortable : "true"
						},{
							field : "EMPLOYEE_NO",
							title : "员工工号",
							align : "center",
							valign : "middle",
							sortable : "true"
						},{
							field : "NAME3",
							title : "员工姓名",
							align : "center",
							valign : "middle",
							sortable : "true"
						},{
							field : "settlement_month",
							title : "结算月",
							align : "center",
							valign : "middle",
							sortable : "true",
							formatter:function (value) {
							    if(value == null) {
							        return '-';
								}
							    var year = value.substring(0,4);
                                var month = value.substring(5, value.length);
							    if(value.substring(5,6) == 0){
                                    month = value.substring(6, value.length);
								}
							    return year + '年' + month + '月';
							}
						},{
                            field : "auditStatusPreFromatter",
                            title : "状态",
                            align : "center",
                            valign : "middle",
                            sortable : "true",
							formatter:function (value) {
                                if(value == '0') {
                                    return '待审核';
								} else if(value == '1') {
                                    return '审核通过';
                                }else if(value == '2') {
									return '未通过';
                                }else if(value == '3') {
									return '挂起';
                                }
                                return '-';
                            }
                        }, {
							title: "操作",
							align: "center",
							valign: "middle",
							sortable: "true",
							formatter: operateFormatter
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
                        settlementType:$("#settlementType").val(), //展示类型
						exampleInputName1:$("#ex_exampleInputName1").val(), //保单号
						exampleInputName2:$("#ex_exampleInputName2").val(), //保险公司id
						exampleInputName3:$("#ex_exampleInputName3").val(), //产品名称
						exampleInputName4:$("#ex_exampleInputName4").val(), //产品代码
						exampleInputName5:$("#ex_exampleInputName5").val(), //组织机构
						exampleInputName6:$("#ex_exampleInputName6").val(), //营销团队
						exampleInputName7:$("#ex_exampleInputName7").val(), //投保人
						exampleInputName8:$("#ex_exampleInputName8").val(), //被保人
						exampleInputName9:$("#ex_exampleInputName9").val(), //员工工号
						exampleInputName20:$("#ex_exampleInputName20").val(), //投保日期-开始
						exampleInputName21:$("#ex_exampleInputName21").val(), //投保日期-结束
						opera_exampleInputName22:$("#opera_exampleInputName22").val(), //生效日期-开始
						opera_exampleInputName23:$("#opera_exampleInputName23").val(), //生效日期-结束
						opera_exampleInputName24:$("#opera_exampleInputName24").val(), //承保日期-开始
						opera_exampleInputName25:$("#opera_exampleInputName25").val(), //承保日期-结束
						ex_auditStatus:$("#ex_auditStatus").val(), //承保日期-结束
					};
					return temp;
				},
				search:function(){
					$("#examine_insComanpy-table").bootstrapTable('destroy');
                    settlymentPolicy.init();
				},
		        checkSingleData:function () {
		            var selected = $('#examine_insComanpy-table').bootstrapTable('getSelections');
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
                        settlymentPolicy.seItem = selected[0];
		                return true;
		            }
		        },
			}
		}();


        $("#examine_fileForm").bootstrapValidator({
            fields:{
                examine_auditresults:{
                    validators:{
                        notEmpty:{
                            message:"请选择审核结果"
                        }
                    }
                },
                datetimepicker:{
                    validators:{
                        notEmpty:{
                            message:"请选择结算月"
                        }
                    },
                    trigger: 'focus'
                },
                remark:{
                    validators:{
                        stringLength:{
                            max:500,
                            message:"备注不能超过500字符"
                        }
                    }
                }
            }
        });
	</script>

</head>
<body>
<!--列表 -->
<div class="panel panel-default">
    <div class="panel-body">
		<form class="form-inline hz-form-inline" id="examine_search" enctype="application/x-www-form-urlencoded">
			<input type="hidden" name="settlementType" id="settlementType" value="${settlementType}" /><!-- 可结算状态（0：待结算保单；1：异常结算保单） -->

			<div class="form-group">
				<label class="control-label">保单号</label>
				<input type="text" class="form-control" id="ex_exampleInputName1" name="ex_exampleInputName1" placeholder="保单号">
			</div>

			<div class="form-group">
				<label class="control-label">保险公司</label>
				<select type="text" class="form-control" id="ex_exampleInputName2" name="ex_exampleInputName2" placeholder="保险公司"></select>
			</div>
			<div class="form-group">
				<label class="control-label" for="ex_exampleInputName3">产品名称</label>
				<input type="text" class="form-control" id="ex_exampleInputName3" name="ex_exampleInputName3" placeholder="产品名称">
			</div>
			<div class="form-group">
				<label class="control-label" for="ex_exampleInputName4">产品代码</label>
				<input type="text" class="form-control" id="ex_exampleInputName4" name="ex_exampleInputName4" placeholder="产品代码">
			</div>
			<c:if test="${settlementType eq '1' }">
				<div class="form-group">
					<label class="control-label" for="ex_auditStatus">状态</label>
					<select type="text" class="form-control" id="ex_auditStatus" name="ex_auditStatus" placeholder="状态">
						<option value="">请选择</option>
						<option value="0">待审核</option>
						<option value="1">审核通过</option>
						<option value="3">挂起</option>
					</select>
				</div>
			</c:if>
			<div class="form-group">
				<label class="control-label" for="ex_exampleInputName5">组织机构</label>
				<select type="text" class="form-control" id="ex_exampleInputName5" name="ex_exampleInputName5" placeholder="组织机构"></select>
			</div>
			<div class="form-group">
				<label class="control-label" for="ex_exampleInputName6">营销团队</label>
				<select type="text" class="form-control" id="ex_exampleInputName6" name="ex_exampleInputName6" placeholder="营销团队"></select>
			</div>
			<div class="form-group">
				<label class="control-label" for="ex_exampleInputName7">投保人</label>
				<input type="text" class="form-control" id="ex_exampleInputName7" name="ex_exampleInputName7" placeholder="投保人">
			</div>
			<div class="form-group">
				<label class="control-label" for="ex_exampleInputName8">被保人</label>
				<input type="text" class="form-control" id="ex_exampleInputName8" name="ex_exampleInputName8" placeholder="被保人">
			</div>
			<div class="form-group">
				<label class="control-label" for="ex_exampleInputName9">员工工号</label>
				<input type="text" class="form-control" id="ex_exampleInputName9" name="ex_exampleInputName9" placeholder="员工工号">
			</div>

			<div class="form-group">
				<label class="control-label" for="ex_exampleInputName20">投保日期</label>
				<input type="date" class="form-control" id="ex_exampleInputName20" name="ex_exampleInputName20" placeholder="投保日期">
				<label class="control-label control-label-time">至</label>
				<input type="date" class="form-control" id="ex_exampleInputName21" name="ex_exampleInputName21" placeholder="投保日期">
			</div>

			<div class="form-group" >
				<label class="control-label" for="opera_exampleInputName22">生效日期</label>
				<input type="date" class="form-control" id="opera_exampleInputName22" name="opera_exampleInputName22" placeholder="生效日期">
				<label class="control-label control-label-time">至</label>
				<input type="date" class="form-control" id="opera_exampleInputName23" name="opera_exampleInputName23" placeholder="生效日期">
			</div>

			<div class="form-group" >
				<label class="control-label" for="opera_exampleInputName24">承保日期</label>
				<input type="date" class="form-control" id="opera_exampleInputName24" name="opera_exampleInputName24" placeholder="承保日期">
				<label class="control-label control-label-time">至</label>
				<input type="date" class="form-control" id="opera_exampleInputName25" name="opera_exampleInputName25" placeholder="承保日期">
			</div>

			<div class="form-group">
				<button type="button" class="btn btn-info" onclick="settlymentPolicy.search()">
					<span class="glyphicon glyphicon-search" aria-hidden="true" >搜索</span>
				</button>
				<button type="button" class="btn btn-danger" onclick="cla()">
					<span class="glyphicon glyphicon-remove" aria-hidden="true" >清空</span>
				</button>
			</div>
		</form>
	</div>
</div>

<div style="width: 99%;overflow: auto;">
	<table id="examine_insComanpy-table" class="table table-hover table-striped table-condensed table-bordered"></table>
</div>
<div id="examine_alert_div_ipe" class="modal fade" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false"
	 aria-labelledby="examine_zsadd_myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="examine_zsadd_myModalLabel">佣金结算审核</h4>
			</div>
			<div class="container">
				<form class="form-horizontal" id="examine_fileForm"  enctype="application/x-www-form-urlencoded">
					<input type="hidden" id="auditPolicyId" name="auditPolicyId" />
					<input type="hidden" id="auditStatus" name="auditStatus" />
					<div class="form-group">
						<label class="col-md-2 control-label">审核结果：</label>
						<div class="radio-inline">
							<label class="radio">
								<input type="radio" name="examine_auditresults" value="1" />审核通过&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="radio" name="examine_auditresults" value="2" />审核不通过&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<span id="spanId"><input type="radio" name="examine_auditresults" value="3" />挂起</span>
							</label>
						</div>

						<%--<div class="col-md-3" class="form-control form-control-static" style="padding-top: 8px;">--%>
							<%--<input type="radio" name="examine_auditresults" value="1" />审核通过&nbsp;&nbsp;&nbsp;&nbsp;--%>
							<%--<input type="radio" name="examine_auditresults" value="2" />审核不通过&nbsp;&nbsp;&nbsp;&nbsp;--%>
							<%--<span id="spanId"><input type="radio" name="examine_auditresults" value="3" />挂起</span>--%>
						<%--</div>--%>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">结算月：</label>
						<div class="col-md-3" class="form-control form-control-static" style="padding-top: 8px;">
							<input name="datetimepicker" id="datetimepicker" type='text' class="form-control input-sm Wdate"
								   onfocus="WdatePicker({dateFmt:'yyyy-MM',readOnly:true,minDate: '${minMonth}'})"
								   style="width: 100px" />
						</div>

					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">备注：</label>
						<div class="col-md-3" class="form-control form-control-static" style="padding-top: 8px;">
							<textarea class="form-control" style=" width: 300px; height: 200px" id="remark" name="remark" type="text"></textarea>
						</div>
					</div>
					<div class="modal-footer col-md-6">
						<!--用来清空表单数据-->
						<input type="reset" name="reset" style="display: none;"/>
						<button type="button" class="btn btn-default" onclick="closeDlg()">关闭</button>
						<button id="examine_zs_saveButton" type="button" onclick="determine()" class="btn btn-primary">确定</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
</body>
</html>