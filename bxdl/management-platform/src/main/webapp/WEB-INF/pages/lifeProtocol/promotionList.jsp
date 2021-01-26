<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/include/core.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>业务推动列表</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Cache" content="no-cache">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <script src="${path}/js/system/comm.js" charset="UTF-8" type="text/javascript"></script>
    <script src="${path}/static/js/bootstrap.js"></script>
    <script src="${path}/js/protocol/promotion.js" charset="UTF-8" type="text/javascript"></script>
</head>
<body>

<style type="text/css">
    /*文本两端对齐*/
    .text-justify {
        float: left;
        text-align: justify;
        width: 100px;
    }

    .div-justify {
        display: inline;
        width: 100%;
    }
</style>

<!-- 导航栏 -->
<!-- <div style="height: 70px;margin-top: 5px;">
    <button type="button" style="margin-left: 20px;height: 40px;width: 150px;" onclick="openAddProtocolBasic()">1.协议信息</button>
    <button type="button" style="margin-left: 25px;height: 40px;width: 150px;"  onclick="showSecondMenu()" >2.手续费及折标率</button>
    <button type="button" style="margin-left: 25px;height: 40px;width: 150px;" onclick="openAddProtocolSubsidy()" >3.续年度服务津贴</button>
    <button type="button" style="margin-left: 25px;height: 40px;width: 150px;" onclick="" >4.继续率奖金</button>
    <button type="button" style="margin-left: 25px;height: 40px;width: 150px;" onclick="" >5.费率调节</button>
    <button type="button" style="margin-left: 25px;height: 40px;width: 150px;" onclick="openProtocolPromotion()" >6.业务推动</button>
</div>
<div id="showSecond" style="height: 50px;display: none; overflow: hidden;">
    <button type="button" style="margin-left: 50px;height: 35px;width: 140px;" onclick="openAddProtocolService()">2.1.手续费配置</button>
    <button type="button" style="margin-left: 20px;height: 35px;width: 140px;" onclick="openAddProtocolStandard()" >2.2.折标率配置</button>
    <button type="button" style="margin-left: 20px;height: 35px;width: 140px;" onclick="openAddProtocolInsideStandard()" >2.3.内部折标率配置</button>
</div> -->

<!--toolbar  -->
<div id="dictToolbar" class="btn-toolbar">
    <shiro:hasPermission name="protocolPromotion:add">
    	<c:choose>
    		<c:when test="${isLook == 'yes'}">
		        <button type="button" disabled="disabled" class="btn btn-info" onclick="createAddProcessTab('protocolPromotion:add','${protocolId}')">
    		</c:when>
    		<c:otherwise>
		        <button type="button" class="btn btn-info" onclick="createAddProcessTab('protocolPromotion:add','${protocolId}')">
    		</c:otherwise>
    	</c:choose>
            <span class="glyphicon glyphicon-plus">新增业务推动</span>
        </button>
    </shiro:hasPermission>
</div>
<!--列表 -->
<input type="hidden" value="${protocolId}" id="protocolId">
<input type="hidden" value="${isLook}" id="isLook">
<input type="hidden" value="${promotionLookProtocolId}" id="promotion_look_protocolId">
<table id="promotion-table" style="word-break:break-all; word-wrap:break-word;" class="table table-hover table-striped table-condensed table-bordered"></table>
<c:choose>
   	<c:when test="${isLook == 'yes'}">
		<input onclick="PromotionList.closeTab()" disabled="disabled" style="background:#1ab394;  height: 30px;margin-left: 400px;width: 80px;color: white; line-height:20px" type="button"  value="保存" />
   	</c:when>
  	<c:otherwise>
		<input onclick="PromotionList.closeTab()" style="background:#1ab394;  height: 30px;margin-left: 400px;width: 80px;color: white; line-height:20px" type="button"  value="保存" />
  	</c:otherwise>
</c:choose>
<input onclick="returnProtocolList()" style="background:#1ab394;  height:30px;width: 80px; color: white; line-height:20px" type="button"  value="返回" />

<%--modal--%>
<div id="promotionModal" class="modal fade" tabindex="-1" role="dialog" data-backdrop="static"
     data-keyboard="false" aria-labelledby="myModalLabel" aria-hidden="true" style="height: 600px;width: 600px;">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">推动奖励详情</h4>
            </div>
            <div class="container">
                <div class="form-group">
                    <label class="text-justify">方案名称：</label>
                    <div class="div-justify" id="promotionName"></div>
                </div>
                <div class="form-group">
                    <label class="text-justify" style="width: 100px;">有效起期：</label>
                    <div class="div-justify" id="startTime"></div>
                </div>
                <div class="form-group">
                    <label class="text-justify" style="width: 100px;">有效止期：</label>
                    <div class="div-justify" id="endTime"></div>
                </div>
                <div class="form-group">
                    <label class="text-justify" style="width: 100px;">区域范围：</label>
                    <div class="div-justify" id="orgScope"></div>
                </div>
                <div class="form-group">
                    <label class="text-justify" style="width: 100px;">产品及年期：</label>
                    <div class="div-justify" id="productScope"></div>
                </div>
                <div class="form-group">
                    <label class="text-justify" style="width: 100px;">费率基于：</label>
                    <div class="div-justify" id="rateType"></div>
                </div>

                <div class="form-group">
                    <label class="text-justify" style="width: 100px;">结算周期：</label>
                    <div class="div-justify" id="settlementCycle"></div>
                </div>
                <div class="form-group">
                    <label style="width: 100px;">通算周期：</label>
                    <div class="div-justify" id="commonCycleId"></div>
                </div>

                <br/>
                <span>方案规则及费率*</span>
                <br/>
                <div style="float:left;width:100%;">
                    <div class="div-justify" style="width: auto;" id="settlementCycleSpan"></div>
                    <div style="word-wrap: break-word; margin:10px">
                        <table class="table table-hover table-striped table-condensed table-bordered"
                               style="width:400px;font-size:13px;" id="settlementCycleTable">
                            <tr>
                                <td>计算项</td>
                                <td>规则</td>
                                <td>业务推动率</td>
                            </tr>
                        </table>
                    </div>
                </div>

                <div style="display: none" id="commonCycleDiv">
                    <div class="div-justify" style="width: auto;" id="commonCycleSpan"></div>
                    <div style="word-wrap: break-word; margin:10px">
                        <table class="table table-hover table-striped table-condensed table-bordered"
                               style="width:400px;font-size:13px;" id="commonCycleTable">
                            <tr>
                                <td>计算项</td>
                                <td>规则</td>
                                <td>业务推动率</td>
                            </tr>
                        </table>
                    </div>
                </div>
                <div class="modal-footer col-md-6">
                    <button type="button" class="btn btn-default" onclick="PromotionList.close()">关闭</button>
                </div>
            </div>
        </div>
    </div>

</div>

</body>
</html>
