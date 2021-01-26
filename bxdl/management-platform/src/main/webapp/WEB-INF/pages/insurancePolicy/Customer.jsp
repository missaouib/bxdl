<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/include/core.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>查看</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Cache" content="no-cache">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="${path}/css/system/role/role.css">
    <script src="${path}/js/system/comm.js" charset="UTF-8" type="text/javascript"></script>
    <script src="${path}/static/js/bootstrap.js"></script>
    <script src="${path}/static/js/jquery-form.js"></script>
    <script type="text/javascript">
	    $(function () {
	    	$('input,select,textarea',$('#customer_view_addForm')).prop('readonly',true);
	    });
    
        function closeThisTab() {
            windowCloseTab();
        }
    </script>
</head>
<body>
<div class="container" style="width: 2000px">
    <form class="form-horizontal" id="customer_view_addForm" method="post" enctype="application/x-www-form-urlencoded">
        <div id="custoolbar" class="btn-toolbar" style="overflow:scroll;background: slategray;width: 1800px;">
            <span style="color: cornsilk">基本信息</span>
        </div>

        <div class="form-group">
            <label class="col-md-2 control-label">客户姓名</label>
            <div class="col-md-3 ">
                <input type="text" class="form-control form-control-static" placeholder="客户姓名" value="${customer.name}">
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-2 control-label">客户性别</label>
            <div class="col-md-3 ">
                <input type="text" class="form-control form-control-static" placeholder="性别" value="${customer.sex}">
            </div>
            <label class="col-md-2 control-label">出生日期</label>
            <div class="col-md-3 ">
                <input type="date" class="form-control form-control-static" placeholder="出生日期" value="${customer.birthday}">
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-2 control-label">证件类型</label>
            <div class="col-md-3 ">
                <input type="text" class="form-control form-control-static" placeholder="证件号码" value="${customer.cardType}">
            </div>
            <label class="col-md-2 control-label">证件号码</label>
            <div class="col-md-3 ">
                <input type="text" class="form-control form-control-static" placeholder="证件号码" value="${customer.cardNo}">
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-2 control-label">证件有效期</label>
            <div class="col-md-3 ">
                <input type="text" class="form-control form-control-static" placeholder="证件有效期" value="">
            </div>
            <label class="col-md-2 control-label">国籍</label>
            <div class="col-md-3 ">
                <input type="text" class="form-control form-control-static" placeholder="国籍" value="">
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-2 control-label">户籍所在地</label>
            <div class="col-md-3 ">
                <input type="text" class="form-control form-control-static" placeholder="户籍所在地" value="${customer.domicileAddress}">
            </div>
            <label class="col-md-2 control-label">户籍地址</label>
            <div class="col-md-3 ">
                <input type="text" class="form-control form-control-static" placeholder="户籍所在地" value="${customer.domicileAddress}">
            </div>
        </div>


        <div class="btn-toolbar" style="overflow:scroll;background: slategray;width: 1800px;">
            <span style="color: cornsilk">联系信息</span>
        </div>

        <div class="form-group">
            <label class="col-md-2 control-label">工作单位</label>
            <div class="col-md-3 ">
                <input type="text" class="form-control form-control-static" placeholder="工作单位" value="">
            </div>
            <label class="col-md-2 control-label">单位类型</label>
            <div class="col-md-3 ">
                <input type="text" class="form-control form-control-static" placeholder="单位类型" value="">
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-2 control-label">职业大类</label>
            <div class="col-md-3 ">
                <input type="text" class="form-control form-control-static" placeholder="职业大类" value="">
            </div>
            <label class="col-md-2 control-label">职业分类</label>
            <div class="col-md-3 ">
                <input type="text" class="form-control form-control-static" placeholder="职业分类" value="">
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-2 control-label">职业</label>
            <div class="col-md-3 ">
                <input type="text" class="form-control form-control-static" placeholder="职业分类" value="${customer.occupation}">
            </div>
            <label class="col-md-2 control-label">职业代码</label>
            <div class="col-md-3 ">
                <input type="text" class="form-control form-control-static" placeholder="职业代码" value="${customer.occupationCode}">
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-2 control-label">单位电话</label>
            <div class="col-md-3 ">
                <input type="text" class="form-control form-control-static" placeholder="单位电话" value="${customer.telephone}">
            </div>
            <label class="col-md-2 control-label">通讯邮件</label>
            <div class="col-md-3 ">
                <input type="text" class="form-control form-control-static" placeholder="通讯邮件" value="">
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-2 control-label">移动电话</label>
            <div class="col-md-3 ">
                <input type="text" class="form-control form-control-static" placeholder="移动电话" value="${customer.mobile}">
            </div>
            <label class="col-md-2 control-label">通讯地址</label>
            <div class="col-md-3 ">
                <input type="text" class="form-control form-control-static" placeholder="通讯地址" value="${customer.companyAddress}">
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-2 control-label">家庭电话</label>
            <div class="col-md-3 ">
                <input type="text" class="form-control form-control-static" placeholder="家庭电话" value="${customer.telephone}">
            </div>
            <label class="col-md-2 control-label">常住址邮件</label>
            <div class="col-md-3 ">
                <input type="text" class="form-control form-control-static" placeholder="常住址邮件" value="">
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-2 control-label">邮箱</label>
            <div class="col-md-3 ">
                <input type="text" class="form-control form-control-static" placeholder="邮箱" value="${customer.email}">
            </div>
            <label class="col-md-2 control-label">年收入(万元)</label>
            <div class="col-md-3 ">
                <input type="text" class="form-control form-control-static" placeholder="年收入(万元)" value="${customer.annualIncome}">
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-2 control-label">兼职</label>
            <div class="col-md-3 ">
                <input type="text" class="form-control form-control-static" placeholder="兼职" value="">
            </div>
        </div>


        <div class="btn-toolbar" style="overflow:scroll;background: slategray;width: 1800px;">
            <span style="color: cornsilk">联系信息</span>
        </div>

        <div class="form-group">
            <label class="col-md-2 control-label">学历</label>
            <div class="col-md-3 ">
                <input type="text" class="form-control form-control-static" placeholder="学历" value="${customer.eduBackground}">
            </div>
            <label class="col-md-2 control-label">婚姻状态</label>
            <div class="col-md-3 ">
                <input type="text" class="form-control form-control-static" placeholder="婚姻状态" value="${customer.maritalStatus}">
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-2 control-label">身高</label>
            <div class="col-md-3 ">
                <input type="text" class="form-control form-control-static" placeholder="身高" value="">
            </div>
            <label class="col-md-2 control-label">学校名称</label>
            <div class="col-md-3 ">
                <input type="text" class="form-control form-control-static" placeholder="学校名称" value="">
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-2 control-label">备注</label>
            <div class="col-md-3 ">
                <input type="text" class="form-control form-control-static" placeholder="备注" value="">
            </div>
            <label class="col-md-2 control-label">体重(kg)</label>
            <div class="col-md-3 ">
                <input type="text" class="form-control form-control-static" placeholder="体重(kg)" value="">
            </div>
        </div>


        <div class="btn-toolbar" style="overflow:scroll;background: slategray;width: 1800px;">
            <span style="color: cornsilk">联系信息</span>
        </div>

        <div class="form-group">
            <label class="col-md-2 control-label">首次员工工号</label>
            <div class="col-md-3 ">
                <input type="text" class="form-control form-control-static" placeholder="首次员工工号" value="${sales.firstEmployeeNo}">
            </div>
            <label class="col-md-2 control-label">首次员工姓名</label>
            <div class="col-md-3 ">
                <input type="text" class="form-control form-control-static" placeholder="首次员工姓名" value="${sales.firstSaleName}">
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-2 control-label">首次组织机构</label>
            <div class="col-md-3 ">
                <input type="text" class="form-control form-control-static" placeholder="首次组织机构" value="${sales.firstSalesOrgName}">
            </div>
            <label class="col-md-2 control-label">首次营销团队</label>
            <div class="col-md-3 ">
                <input type="text" class="form-control form-control-static" placeholder="首次营销团队" value="${sales.firstSalesTeamName}">
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-2 control-label">最新员工工号</label>
            <div class="col-md-3 ">
                <input type="text" class="form-control form-control-static" placeholder="最新员工工号" value="${sales.newEmployeeNo}">
            </div>
            <label class="col-md-2 control-label">最新员工姓名</label>
            <div class="col-md-3 ">
                <input type="text" class="form-control form-control-static" placeholder="最新员工姓名" value="${sales.newSaleName}">
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-2 control-label">最新组织机构</label>
            <div class="col-md-3 ">
                <input type="text" class="form-control form-control-static" placeholder="最新组织机构" value="${sales.newSalesOrgName}">
            </div>
            <label class="col-md-2 control-label">最新营销团队</label>
            <div class="col-md-3 ">
                <input type="text" class="form-control form-control-static" placeholder="最新营销团队" value="${sales.newSalesTeamName}">
            </div>
        </div>

        <div class="form-group" style="margin-top:20px;">
            <div class="col-md-3 ">
                <button class="btn btn-primary" onclick="closeThisTab()">返回</button>
            </div>
        </div>
    </form>
</div>
</body>
</html>
