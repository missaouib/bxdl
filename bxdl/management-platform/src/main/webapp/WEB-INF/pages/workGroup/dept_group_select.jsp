<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
&lt;%&ndash;<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>&ndash;%&gt;
<c:set var="app" scope="page" value="${pageContext.request.contextPath}" />
<c:set var="scheme" scope="page" value="${pageContext.request.scheme}"/>
<c:set var="serverName" scope="page" value="${pageContext.request.serverName}"/>
<c:set var="serverPort" scope="page" value="${pageContext.request.serverPort}"/>
<c:set var="messageMaxLength" value="30"></c:set>--%>
<link rel="stylesheet" href="${app}/js/JQuery-zTree-v3.5.15/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${app}/js/JQuery-zTree-v3.5.15/js/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript" src="${app}/js/ajaxupload.js"></script>
<script type="text/javascript" src="${app}/js/jquery.form.js"></script>
	<script type="text/javascript">
        
		var zTreeObj = null;
		$(document).ready(function(){
			var setting = {
				check: {
					enable: true
				},
				data: {
					key:{name: 'SALES_ORG_NAME'},
					simpleData: {
						enable: true,
						idKey: 'dept_id',
						pIdKey: 'parent_id'
					}
				}
			};
			
			var zTreeNodes = null;
			//查询树节点
			var eid = '${employeeId}'
			var data ={"eid":eid}
			$.ajax({
				url : "/empWorkGroup/listTreeDeptGroup",
				data:data,
				type : "post",
				dataType : "json",
				async : false,
				success : function(msg){
                    $("#dept_name").html(msg.deptNames)
                    $("#emp_name").html(msg.empName)
                    $("#deptIds").val(msg.deptIds)
                    $("#checkedStatus").val(msg.checkedStatus)
                    $("#halfStatus").val(msg.halfStatus)
					if(msg.deptLists != null && msg.deptLists.length != 0){
						zTreeNodes = msg.deptLists;
					}
				},		
				error : function(){
					$.messager.alert("提示信息","系统错误！","info");
				}
			});
			
			zTreeObj = $.fn.zTree.init($("#treeDept"), setting, zTreeNodes);
			zTreeObj.expandAll(true);//全部不展开

            //查詢
		});
		function check() {
            var nodes = zTreeObj.getCheckedNodes(true);
            var deptIds = "";
            var checkedStatus = "";
            var halfStatus = "";
            for(var i=0;i<nodes.length;i++){
                console.info(nodes[i].getCheckStatus());//获得半选状态  true 表示半选   false 表示真正选中
                deptIds += nodes[i].dept_id + ",";
                checkedStatus += nodes[i].getCheckStatus().checked + ",";
                halfStatus += nodes[i].getCheckStatus().half + ",";
            }
            deptIds = deptIds.substr(0, deptIds.length-1);
            $("#deptIds").val(deptIds);
            checkedStatus = checkedStatus.substr(0, checkedStatus.length-1);
            $("#checkedStatus").val(checkedStatus);
            halfStatus = halfStatus.substr(0, halfStatus.length-1);
            $("#halfStatus").val(halfStatus);
           // deptDialog.dialog('close');
        }

        //提交
        function submitForm(){
            check();
            $.ajax({
                url:"empWorkGroup/addWorkGroup",
                data:$("#empAuthorizationForm").serialize(),
                type:"POST",
                dataType:"JSON",
                success:function (obj) {

                    alert("添加成功")
                    windowCloseTab()
                },
                error:function () {
                }
            })

        }

        function closes(){
            windowCloseTab()
        }
	</script>
	


<body style="background: white;">
<span style="font-size: 20px">当前部门：</span><span id="dept_name" style="font-size: 17px;color: crimson"></span><br>
<span style="font-size: 20px">员工姓名：</span><span id="emp_name" style="font-size: 17px;color: crimson"></span>
<br></br>
<form id="empAuthorizationForm" class="easyui-form" method="post">
 <input type="hidden" id="employeeId" name="employeeId" value="${employeeId}"/>

    <table class="tableForm" border="0" width="100%">
        <tr>
            <td class="tdR">工作组:</td>
            <td colspan="3">
                <input type="hidden" id="deptIds" name="deptIds" value="${deptIds}"/>
                <input type="hidden" id="checkedStatus" name="checkedStatus" value="${checkedStatus}"/>
                <input type="hidden" id="halfStatus" name="halfStatus" value="${halfStatus}"/>
            </td>
        </tr>
    </table>
    <div id="deptDialog" style="height:300px; overflow:scroll;">
        <ul id="treeDept" class="ztree"></ul>
    </div>
</form>
<a class="btn btn-primary"  id="submitButton"  onclick="submitForm();">提交</a>
&nbsp;&nbsp;&nbsp;&nbsp;
<a class="easyui-linkbutton" iconCls="icon-cancel" onclick="closes()">取消</a>

</body>
