<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/core.jsp" %>
<!DOCTYPE  html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>编辑业务推动奖励</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Cache" content="no-cache">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <script src="${path}/static/js/bootstrap.js"></script>
    <script src="${path}/js/jquery-form.js" charset="UTF-8" type="text/javascript"></script>
    <script src="${path}/js/protocol/updatePromotion.js" charset="UTF-8" type="text/javascript"></script>
</head>
<body>
<style type="text/css">
    td {white-space:nowrap;overflow:hidden;word-break:keep-all;}
</style>
<div class="container">

    <button style="margin-left: 400px;"  class="btn btn-info" type="button" onclick="PromotionUpdate.update()">
        <span>保存</span>
    </button>
    <input type="hidden" value="${promotionId}" id="promotionId">
    <input type="hidden" value="${protocolId}" id="protocolId">
    <input type="hidden" value="${salesOrgId}" id="salesOrgId">
    <input type="hidden" id="state">
    <input type="hidden" id="deleted">
    <form class="form-horizontal" id="updatePromotionFrom"  method="post" enctype="application/x-www-form-urlencoded">
        <div class="form-group">
            <label class="col-md-1 control-label" style="width: 130px;">方案名称*</label>
            <div class="col-md-3 ">
                <input type="text" class="form-control form-control-static" id="promotionNameId"
                       name="promotionName" placeholder="方案名称">
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-1 control-label" style="width: 130px;">有效起期*</label>
            <div class="col-md-3 ">
                <input type="text" class="form-control form-control-static" name="startTime"
                       id="startTimeId" onfocus="WdatePicker({startDate:'%y-%M-%d %H:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss'})" placeholder="有效起期">
            </div>

            <label class="col-md-1 control-label" style="width: 130px;">有效止期*</label>
            <div class="col-md-3 ">
                <input type="text" class="form-control form-control-static" name="endTime"
                       id="endTimeId" onfocus="WdatePicker({startDate:'%y-%M-%d %H:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss'})" placeholder="有效止期">
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-1 control-label" style="width: 130px;">区域范围*：</label>
            <div class="col-md-3" style="width: auto">
                <input type="radio" name="orgScope" value="0"/>全部
                <input type="radio" style="margin-left:10px;text-decoration:underline;" name="orgScope" value="1"/>选择机构
            </div>
            <div style="margin-left: 10px">
                <a style="border-bottom:1px solid #00F; color:#00F;" href="#" name="showOrg">查看机构</a>
            </div>
        </div>
        <%--机构选择modal--%>
        <div id="chooseOrgModal" class="modal fade" tabindex="-1" role="dialog" data-backdrop="static"
             data-keyboard="false" aria-labelledby="myModalLabel" aria-hidden="true" style="height: 450px;width: auto;">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title">选择销售机构</h4>
                        <div class="container">
                            <table id="chooseOrg">
                            </table>
                            <div class="modal-footer col-md-6">
                                <button id="saveOrg" type="button" onclick="PromotionUpdate.saveOrg()" class="btn btn-primary">确定
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-1 control-label" style="width: 130px;">产品及年期*：</label>
            <div class="col-md-3" style="width: auto">
                <input type="radio" name="productScope" value="0"/>全部
                <input type="radio" style="margin-left: 10px" name="productScope" value="1"/>选择产品及年期
            </div>
            <div style="margin-left: 10px">
                <a style="border-bottom:1px solid #00F; color:#00F;" name="showProduct">查看产品及年期</a>
            </div>
        </div>
        <%--产品选择modal--%>
        <div id="chooseProductModal" class="modal fade" tabindex="-1" role="dialog" data-backdrop="static"
             data-keyboard="false" aria-labelledby="myModalLabel" aria-hidden="true" style="height: 450px;width: auto;">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title">选择产品及年期</h4>
                        <div class="container">
                            <table id="chooseProduct">
                            </table>
                            <div class="modal-footer col-md-6">
                                <button id="saveProduct" type="button" onclick="PromotionUpdate.saveProduct()" class="btn btn-primary">确定
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-1 control-label" style="width: 130px;">费率基于*：</label>
            <div class="col-md-3">
                <input type="radio" name="rateType" value="0"/>规保
                <input type="radio" style="margin-left: 10px"  name="rateType" value="1"/>标保
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-1 control-label" style="width: 130px;">结算周期*：</label>
            <div class="col-md-3">
                <input type="radio" checked name="settlementCycle" value="0"/>月
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-1 control-label" style="width: 130px;">通算周期*：</label>
            <div class="col-md-3">
                <input type="radio" name="commonCycle" value="1"/>季度算
                <input type="radio" style="margin-left: 10px" name="commonCycle" value="2"/>半年算
                <input type="radio" style="margin-left: 10px" name="commonCycle" value="3" />年通算
            </div>
        </div>

        <br/>
        <span>方案规则及费率*</span>
        <div style="float:left;width:100%;">
            <span class="col-md-1 control-label" style="width: auto;">结算周期*：月</span>
            <div style="word-wrap: break-word; margin:50px">
                <table class="table table-hover table-striped table-condensed table-bordered"
                       style="width:100%;font-size:13px;display: none;" id="updateSettlementCycleTable">
                    <tr>
                        <td>计算项</td>
                        <td>规则</td>
                        <td>业务推动率</td>
                        <td>操作</td>
                    </tr>
                </table>
            </div>
        </div>

        <div style="float:left;width:100%;display:none;" id="updateCommonCycle">
            <span class="col-md-1 control-label" style="width: auto;" id="updateCommonSpan"></span>
            <div style="word-wrap: break-word; margin:50px">
                <table class="table table-hover table-striped table-condensed table-bordered"
                       style="width:100%;font-size:13px;" id="updateCommonCycleTable">
                    <tr>
                        <td>计算项</td>
                        <td>规则</td>
                        <td>业务推动率</td>
                        <td>操作</td>
                    </tr>
                </table>
            </div>
        </div>
    </form>
</body>
</html>