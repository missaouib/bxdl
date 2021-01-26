<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/include/core.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>考核设置</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Cache" content="no-cache">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="${path}/css/system/role/role.css">
    <script src="${path}/js/system/comm.js" charset="UTF-8" type="text/javascript"></script>
    <script src="${path}/static/js/bootstrap.js"></script>
    <script src="${path}/static/js/jquery-form.js"></script>
    <script src="${path}/js/insurance/salesAssess.js"></script>
</head>
<body>
<div class="container" style="width: 2000px">
    <form class="form-horizontal" enctype="application/x-www-form-urlencoded">
        <div id="toolbar" class="btn-toolbar" style="overflow:scroll;background: slategray;width: 1800px;">
            <span style="color: cornsilk">员工基本信息</span>
            <input type="hidden" value="${insuranceSalesId}" id="insuranceSalesId">
        </div>

        <div class="form-group">
            <label class="col-md-2 control-label">员工工号</label>
            <div class="col-md-3 ">
                <input type="text" class="form-control form-control-static" placeholder="员工工号"
                       value="${insuranceSalerNo}" readonly>
            </div>
            <label class="col-md-2 control-label">员工姓名</label>
            <div class="col-md-3 ">
                <input type="text" class="form-control form-control-static" placeholder="员工姓名" value="${insuranceSaler}"
                       readonly>
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-2 control-label">组织机构</label>
            <div class="col-md-3 ">
                <input type="text" class="form-control form-control-static" placeholder="组织机构" value="${salesOrgName}"
                       readonly>
            </div>
            <label class="col-md-2 control-label">营销团队</label>
            <div class="col-md-3 ">
                <input type="text" class="form-control form-control-static" placeholder="营销团队" value="${salesTeamName}"
                       readonly>
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-2 control-label">职级序列</label>
            <div class="col-md-3 ">
                <select class="form-control form-control-static" style="width: 200px; background-color: #EEEEEE;"
                        disabled="disabled" id="rankSequenceId" value="${rankSequenceId}" name="rankSequenceId">
                    <option value="">请选择职级序列</option>
                </select>
            </div>
            <label class="col-md-2 control-label">职级</label>
            <div class="col-md-3 ">
                <select class="form-control form-control-static" style="width: 200px; background-color: #EEEEEE;"
                        disabled="disabled" id="salesGradeId" value="${salesGradeId}" name="salesGradeId">
                    <option value="">请选择职级</option>
                </select>
            </div>
        </div>


        <div class="btn-toolbar" style="overflow:scroll;background: slategray;width: 1800px;">
            <span style="color: cornsilk">考核设置</span>
            <input type="hidden" value="${salesAssessId}" id="salesAssessId">
            <input type="hidden" value="${salesGradeId2}" id="salesGradeId2">
            <input type="hidden" value="${salesGradeName}" id="salesGradeName">
            <input type="hidden" value="${condition1}" id="condition1">
            <input type="hidden" value="${condition2}" id="condition2">
            <input type="hidden" value="${condition3}" id="condition3">
            <input type="hidden" value="${condition4}" id="condition4">
            <input type="hidden" value="${condition5}" id="condition5">
            <input type="hidden" value="${condition6}" id="condition6">
            <input type="hidden" value="${condition7}" id="condition7">
            <input type="hidden" value="${condition8}" id="condition8">
        </div>

        <div class="form-group" style="display:none;" id="khqkz">
            <div style="height: 45px">
                <label class="col-md-2 control-label" style="text-align: left;">新人岗前培训是否通过</label>
                <div class="col-md-3 ">
                    <select class="form-control form-control-static" id="khqkz_condition1" name="condition1">
                        <option value="">请选择</option>
                        <option value="1">是</option>
                        <option value="0">否</option>
                    </select>
                </div>
            </div>

            <div style="height: 45px">
                <label class="col-md-2 control-label" style="text-align: left;">日常会议及培训出勤率是否不低于70%</label>
                <div class="col-md-3 ">
                    <select class="form-control form-control-static" id="khqkz_condition2" name="condition2">
                        <option value="">请选择</option>
                        <option value="1">是</option>
                        <option value="0">否</option>
                    </select>
                </div>
            </div>
        </div>

        <div class="form-group" style="display:none;" id="kz">
            <div style="height: 45px">
                <label class="col-md-2 control-label" style="text-align: left;">代理制保险营销员品质考核是否通过</label>
                <div class="col-md-3 ">
                    <select class="form-control form-control-static" id="kz_condition8" name="condition8">
                        <option value="">请选择</option>
                        <option value="1">是</option>
                        <option value="0">否</option>
                    </select>
                </div>
            </div>

            <div style="height: 45px">
                <label class="col-md-2 control-label" style="text-align: left;">日常会议及培训出勤率是否不低于70%</label>
                <div class="col-md-3 ">
                    <select class="form-control form-control-static" id="kz_condition2" name="condition2">
                        <option value="">请选择</option>
                        <option value="1">是</option>
                        <option value="0">否</option>
                    </select>
                </div>
            </div>

            <div style="height: 45px">
                <label class="col-md-2 control-label" style="text-align: left;">晋级考核是否通过</label>
                <div class="col-md-3 ">
                    <select class="form-control form-control-static" id="kz_condition3" name="condition3">
                        <option value="">请选择</option>
                        <option value="1">是</option>
                        <option value="0">否</option>
                    </select>
                </div>
            </div>
        </div>

        <div class="form-group" style="display:none;" id="cz">
            <div style="height: 45px">
                <label class="col-md-2 control-label" style="text-align: left;">代理制保险营销员品质考核是否通过</label>
                <div class="col-md-3 ">
                    <select class="form-control form-control-static" id="cz_condition8" name="condition8">
                        <option value="">请选择</option>
                        <option value="1">是</option>
                        <option value="0">否</option>
                    </select>
                </div>
            </div>

            <div style="height: 45px">
                <label class="col-md-2 control-label" style="text-align: left;">日常会议及培训出勤率是否不低于70%</label>
                <div class="col-md-3 ">
                    <select class="form-control form-control-static" id="cz_condition2" name="condition2">
                        <option value="">请选择</option>
                        <option value="1">是</option>
                        <option value="0">否</option>
                    </select>
                </div>
            </div>

            <div style="height: 45px">
                <label class="col-md-2 control-label" style="text-align: left;">晋级考核是否通过</label>
                <div class="col-md-3 ">
                    <select class="form-control form-control-static" id="cz_condition3" name="condition3">
                        <option value="">请选择</option>
                        <option value="1">是</option>
                        <option value="0">否</option>
                    </select>
                </div>
            </div>

            <div style="height: 45px">
                <label class="col-md-2 control-label" style="text-align: left;">处长考核是否通过</label>
                <div class="col-md-3 ">
                    <select class="form-control form-control-static" id="cz_condition4" name="condition4">
                        <option value="">请选择</option>
                        <option value="1">是</option>
                        <option value="0">否</option>
                    </select>
                </div>
            </div>
        </div>

        <div class="form-group" style="display:none;" id="bz">
            <div style="height: 45px">
                <label class="col-md-2 control-label" style="text-align: left;">是否认同公司文化</label>
                <div class="col-md-3 ">
                    <select class="form-control form-control-static" id="bz_condition5" name="condition5">
                        <option value="">请选择</option>
                        <option value="1">是</option>
                        <option value="0">否</option>
                    </select>
                </div>
            </div>

            <div style="height: 45px">
                <label class="col-md-2 control-label" style="text-align: left;">总经理面谈是否通过</label>
                <div class="col-md-3 ">
                    <select class="form-control form-control-static" id="bz_condition6" name="condition6">
                        <option value="">请选择</option>
                        <option value="1">是</option>
                        <option value="0">否</option>
                    </select>
                </div>
            </div>

            <div style="height: 45px">
                <label class="col-md-2 control-label" style="text-align: left;">晋级考核是否通过</label>
                <div class="col-md-3 ">
                    <select class="form-control form-control-static" id="bz_condition3" name="condition3">
                        <option value="">请选择</option>
                        <option value="1">是</option>
                        <option value="0">否</option>
                    </select>
                </div>
            </div>

            <div style="height: 45px">
                <label class="col-md-2 control-label" style="text-align: left;">部长考核是否通过</label>
                <div class="col-md-3 ">
                    <select class="form-control form-control-static" id="bz_condition7" name="condition7">
                        <option value="">请选择</option>
                        <option value="1">是</option>
                        <option value="0">否</option>
                    </select>
                </div>
            </div>
        </div>

    </form>
    <div class="form-group" style="margin-top:20px;">
        <div class="col-md-3 ">
            <button class="btn btn-primary" onclick="saveSalesAssess()">保存</button>
            <button class="btn btn-primary" onclick="windowCloseTab()">返回</button>
        </div>
    </div>
</div>
</body>
</html>
