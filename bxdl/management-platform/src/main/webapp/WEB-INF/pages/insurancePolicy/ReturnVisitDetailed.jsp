<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/include/core.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>回执录入</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Cache" content="no-cache">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="${path}/css/system/role/role.css">
    <script src="${path}/js/system/comm.js" charset="UTF-8" type="text/javascript"></script>
    <script src="${path}/static/js/jquery-form.js"></script>
    <script src="${path}/static/js/bootstrap.js"></script>
    <script type="text/javascript">
        $(function () {

        })
        function returnPage() {
            commCloseTab('retuenVisit:det')
            createAddProcessTab('returnVisit:list','')
        }
    </script>

</head>
<body>
<!--列表 -->

<form class="form-horizontal" id="note_addForm" method="post" enctype="application/x-www-form-urlencoded">
    <div class="container" style="width: 2000px">
        <div style="overflow:scroll;">
            <div class="form-group">
                <label class="col-md-2 control-label">投保单号</label>
                <div class="col-md-3 ">
                    <input value="${obj.CORRESPONDING}" type="text" class="form-control form-control-static" id="RETURN_CORRESPONDING"
                           name="RETURN_CORRESPONDING" readonly="readonly">
                </div>

                <label class="col-md-2 control-label">保单号*</label>
                <div class="col-md-3 ">
                    <input value="${obj.POLICY_ID}" type="text" class="form-control form-control-static" id="RETURN_POLICY_ID"
                           name="RETURN_POLICY_ID" readonly="readonly">
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 control-label">保险公司*</label>
                <div class="col-md-3 ">
                    <input value="${obj.INSURANCE_COMPANY_NAME}" type="text" class="form-control form-control-static" id="RETURN_INSURANCE_COMPANY_NAME"
                           name="RETURN_INSURANCE_COMPANY_NAME" readonly="readonly">
                </div>


                <label class="col-md-2 control-label">组织机构*</label>
                <div class="col-md-3 ">
                    <input value="${obj.SALES_ORG_NAME}" type="text" class="form-control form-control-static" id="RETURN_SALES_ORG_NAME"
                           name="RETURN_SALES_ORG_NAME" readonly="readonly">
                </div>


            </div>
            <div class="form-group">
                <label class="col-md-2 control-label">营销团队*</label>
                <div class="col-md-3 ">
                    <input value="${obj.SALES_TEAM_NAME}" type="text"  class="form-control form-control-static" id="RETURN_SALES_TEAM_NAME"
                           name="RETURN_SALES_TEAM_NAME" readonly="readonly"/>
                </div>
                <label class="col-md-2 control-label">员工工号*</label>
                <div class="col-md-3 ">
                    <input value="${obj.EMPLOYEE_NO}" type="text" class="form-control form-control-static" id="RETURN_EMPLOYEE_NO"
                           name="RETURN_EMPLOYEE_NO" readonly="readonly">
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-2 control-label">员工姓名</label>
                <div class="col-md-3 ">
                    <input value="${obj.NAME3}" type="text"  class="form-control form-control-static" id="RETURN_NAME3"
                           name="RETURN_NAME3" readonly="readonly"/>
                </div>
                <label class="col-md-2 control-label">投保人姓名</label>
                <div class="col-md-3 ">
                    <input value="${obj.NAME1}" type="text" class="form-control form-control-static" id="RETURN_NAME1"
                           name="RETURN_NAME1" readonly="readonly">
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-2 control-label">被保人姓名</label>
                <div class="col-md-3 ">
                    <input value="${obj.NAME2}" type="text"  class="form-control form-control-static" id="RETURN_NAME2"
                           name="RETURN_NAME2" readonly="readonly"/>
                </div>
                <label class="col-md-2 control-label">回访方式*</label>
                <div class="col-md-3 ">
                    <input readonly="readonly" value="${obj.RETURN_TYPE}" type="text" class="form-control form-control-static" id="RETURN_type"
                            name="RETURN_type">
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-2 control-label">回访成功日期*</label>
                <div class="col-md-3 ">
                    <input readonly="readonly" type="date"  class="form-control form-control-static" id="RETURN_ORG_DATE"
                            name="RETURN_ORG_DATE" value="${obj.RETURN_ORG_DATE}"/>
                </div>
                <label class="col-md-2 control-label">回访情况说明</label>
                <div class="col-md-3 ">
                    <input readonly="readonly" value="${obj.RETURN_EXPLAIN}" type="text" class="form-control form-control-static" id="RETURN_Explain"
                            name="RETURN_Explain">
                </div>
            </div>
        </div>

    </div>
</form>
<div class="form-group" style="margin-top:20px;">
    <div class="col-md-3 ">
        <button class="btn btn-default" onclick="returnPage()">返回</button>
    </div>
</div>

</body>
</html>