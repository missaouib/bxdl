<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/core.jsp" %>
<!DOCTYPE  html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>混合产品-变动费率设置</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Cache" content="no-cache">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script src="${path}/js/system/comm.js" charset="UTF-8" type="text/javascript"></script>
    <script src="${path}/static/js/bootstrap.js"></script>
    <script src="${path}/js/protocol/rateAdjust/addBlendAdjustChangelRate.js" charset="UTF-8" type="text/javascript"></script>
</head>
<body >
<!--**************************************单产品********************************** -->
<div>
<!--  单产品 费率基于设置规则 -->
<label class="col-md-2 control-label" style="width: 180px;margin-top: 20px;">调节规则*</label>
<div style="float:left;width:100%;">
    <span style="width: 130px;margin-left: 15px;margin-top: 10px;margin-bottom:10px; display:block;">单产品</span>
    <span style="width: 130px;margin-left: 50px;margin-top: 10px;">费率基于：</span>
     <div style="word-wrap: break-word; margin:10px">
        <table  id="addChangeRateTable" class="table table-hover table-striped table-condensed table-bordered">
         	<thead>
             <tr>
                 <td>计算项</td>
                 <td style="text-align:center">规则</td>
                 <td>调节费率</td>
                 <td>操作</td>
             </tr>
            </thead>
            <tbody>
      		<c:forEach items="${changeRateList}" var="changeRate" varStatus="status">
             <tr>
                <td style="width:10%;">
                 	<c:if test="${status.index ==0}">
                    		 <select id="changeSingleRateType" class="form-control form-control-static" name="rateType" st onclick="changeSingleRateType()">
                	</c:if>
                	<c:if test="${status.index != 0}">
                    	     <select id="changeSingleRateType" class="form-control form-control-static" name="rateType" disabled="disabled" >
                	</c:if> 
			                      <option value="0" <c:if test="${changeRate.rateTypeValue == '0'}">selected</c:if>>规保</option>
			                      <option value="1" <c:if test="${changeRate.rateTypeValue== '1'}">selected</c:if>>标保</option>
		                     </select>
                 </td>
                 <td style="width:77%;">
                    	 <input style="width:15%;margin-left:2.5%;height:32px;display:inline;"   type="number" step="0.01" min="0" name="bigNum" value="${changeRate.bigNum}"> 万元
                    
                     <select id="firstMark" style="margin-left:0.5%;width:15%;display:inline;" class="form-control form-control-static" name="firstMark">
                         <option value=">" <c:if test="${changeRate.firstMark == '>'}">selected</c:if>> > </option>
                         <option value=">=" <c:if test="${changeRate.firstMark == '>='}">selected</c:if>> >= </option>
                     </select>
                     <!-- 显示标保或者规保 -->
                     <c:if test="${changeRate.rateTypeText  == null}">
                    	 <span id="addSingleChangeRateType" name="zhRateType">规保</span> 
                     </c:if>
                      <c:if test="${changeRate.rateTypeText  != null }">
                    	 <span id="addSingleChangeRateType" name="zhRateType">${changeRate.rateTypeText}</span> 
                     </c:if>
                     <select style="margin-left:2.5%;width:15%;display:inline;" class="form-control form-control-static" name="secondMark">
                         <option value=">" <c:if test="${changeRate.secondMark == '>'}">selected</c:if>> > </option>
                         <option value=">=" <c:if test="${changeRate.secondMark == '>='}">selected</c:if>> >= </option>
                     </select>
                    
                     <input style="margin-left:0.5%;width:15%;height:32px;display:inline;"   type="number" step="0.01" min="0" name="smallNum" value="${changeRate.smallNum}"> 万元
                 </td>
                 <td style="width:5%;">
                     <input style="width:auto;height:32px;"   type="number" step="0.01" min="0"  name="changeRate" value="${changeRate.changeRate}">
                 </td>
                 <td style="width:10%;">
                     <a style="color: black" href="javascript:void(0)" onclick="addChangeRate('addChangeRateTable')">➕</a>
                    <c:if test="${status.index != 0}">
                  	  <a style="color: black" href="javascript:void(0)" onclick="deleteChangeRate(this)">➖</a>
                    </c:if>
                 </td>
             </tr>
            </c:forEach>
        </tbody>
        </table>
    </div>
</div>
<!-- 单产品  附加费率 设置规则 -->
 <div  class="table-responsive" style="width:1100px;">
     <span style="width: 130px;margin-left: 50px;margin-top: 10px;">附加费率基于：</span>
      <table  id="addSubjoinRateTable" class="table text-nowrap">
          	<thead>
              <tr>
                  <td>计算项</td>
                  <td style="text-align:center">规则</td>
                  <td>调节费率</td>
                  <td>操作</td>
              </tr>
             </thead>
             <tbody>
       		<c:forEach items="${subjoinRatelist}" var="subjoinRate" varStatus="status">
              <tr>
                <td>
                 <c:if test="${status.index ==0}">
                       <select id="checkSingleSubjoinRateType"  style="width:100px;height:32px;" onchange="checkSubjoinRateType(${status.index})">
                 </c:if>
                 <c:if test="${status.index != 0}">
                       <select id="checkSingleSubjoinRateType"   style="width:100px;height:32px;" disabled="disabled" >
                 </c:if>
                       <option value="0" <c:if test="${subjoinRate.rateTypeValue == '0'}">selected</c:if>>规保</option>
                       <option value="1" <c:if test="${subjoinRate.rateTypeValue== '1'}">selected</c:if>>标保</option>
                     	    <option value="2" <c:if test="${subjoinRate.rateTypeValue== '2'}">selected</c:if>>继续率</option>
                      </select>
                 </td>
                <td style="width: 900px;">
         				<div id="${status.index}showRule" style="display:inline">
                       <!-- 规则最大值 -->
                       <input style="margin-left:2px;width:100px;height:32px;"   type="number" step="0.01" min="0"  name="bigNum" value="${subjoinRate.bigNum}"> 万元
                       
                       <select  style="margin-left:2px;width:100px;height:32px;" name="firstMark">
                           <option value=">" <c:if test="${subjoinRate.firstMark == '>'}">selected</c:if>> > </option>
                           <option value=">=" <c:if test="${subjoinRate.firstMark == '>='}">selected</c:if>> >= </option>
                       </select>
                       <!-- 显示标保或者规保 -->
	                     <c:if test="${subjoinRate.rateTypeText  == null}">
	                    	 <span id="addSubjoinRateType">规保</span> 
	                     </c:if>
	                      <c:if test="${subjoinRate.rateTypeText  != null }">
	                    	 <span id="addSubjoinRateType">${subjoinRate.rateTypeText}</span> 
	                     </c:if>
                       <select style="margin-left:2px;width:100px;height:32px;"  name="secondMark">
                           <option value=">" <c:if test="${subjoinRate.secondMark == '>'}">selected</c:if>> > </option>
                           <option value=">=" <c:if test="${subjoinRate.secondMark == '>='}">selected</c:if>> >= </option>
                       </select>
                       
                        <!-- 规则最小值 -->
                       <input style="margin-left:2px;width:100px;height:32px;"   type="number" step="0.01" min="0"  name="smallNum" value="${subjoinRate.smallNum}"> 万元
                     	
                     	<span >是否与继续率组合:</span>
                       <select style="margin-left:2px;width:100px;height:32px;" id="${status.index}singleIsGroupPerRate" name="isGroupPerRate"  onchange="checkPerRate(${status.index})">
                           <option value="是" <c:if test="${subjoinRate.isGroupPerRate == '是'}">selected</c:if>>是</option>
                           <option value="否" <c:if test="${subjoinRate.isGroupPerRate == '否'}">selected</c:if> >否</option>
                       </select>
                      
          	  			</div>
                      <!-- 继续率展示或者隐藏  -->
                      <div id ="${status.index}showPerRate" style="display:inline">
                       	<div id="${status.index}syntagmatic" style="display:inline">
                        	<span>组合关系:</span>
                         <select style="margin-left:2px;width:100px;height:32px;"  name="syntagmatic">
                             <option value="&&" <c:if test="${subjoinRate.syntagmatic == '&&'}">selected</c:if>>并且</option>
                             <option value="||" <c:if test="${subjoinRate.syntagmatic == '||'}">selected</c:if> >或者</option>
                         </select>
                       	</div>
                       <span>继续率:</span>
                       <select style="margin-left:2px;width:100px;height:32px;"  name="perRateNum">
                          <option value="">请选择</option>
                          <c:forEach items="${subjoinList}" var="var"> 
                          		 <option value="${var.subKey}" <c:if test="${subjoinRate.perRateNum == var.subKey}">selected</c:if> >${var.subValue}</option>
		   				 </c:forEach> 
                       </select>
                       
                       <!-- 规则继续率最大值 -->
                       <input style="margin-left:2px;width:100px;height:32px;"   type="number" step="0.01" min="0"  name="bigParRateNum" value="${subjoinRate.bigParRateNum}">%
                       
                       
                       <select  style="margin-left:2px;width:100px;height:32px;" name="thirdMark">
                           <option value=">" <c:if test="${subjoinRate.thirdMark == '>'}">selected</c:if>> > </option>
                           <option value=">=" <c:if test="${subjoinRate.thirdMark == '>='}">selected</c:if>> >= </option>
                       </select>
                       
                       <span>继续率</span> 
                       <select style="margin-left:2px;width:100px;height:32px;"  name="fourMark">
                           <option value=">" <c:if test="${subjoinRate.fourMark == '>'}">selected</c:if>> > </option>
                           <option value=">=" <c:if test="${subjoinRate.fourMark == '>='}">selected</c:if>> >= </option>
                       </select>
                       <!-- 规则继续率最小值 -->
                        <input style="margin-left:2px;width:100px;height:32px;"   type="number" step="0.01" min="0"  name="smallPerRateNum" value="${subjoinRate.smallPerRateNum}">%
                      </div>
                      
                  </td>
                  <td>
                      <input style="width:auto;height:32px;"   type="number" step="0.01" min="0"  name="changeRate" value="${subjoinRate.changeRate}">
                  </td>
                  <td>
                     <a style="color: black" href="javascript:void(0)" onclick="addSubjoinRate('addSubjoinRateTable')">➕</a>
                     <c:if test="${status.index != 0}">
                   	  <a style="color: black" href="javascript:void(0)" onclick="deleteChangeRate(this)">➖</a>
                     </c:if>
                  </td>
              </tr>
             </c:forEach>
         </tbody>
      </table>
 	</div>
</div>
<!--************************************** 全产品  ********************************** -->
<div>
<!-- 全产品 费率基于设置规则 -->
<div style="float:left;width:100%;">
    <span style="width: 130px;margin-left: 15px;margin-top: 10px;margin-bottom:10px; display:block;">全产品</span>
    <span style="width: 130px;margin-left: 50px;margin-top: 10px;">费率基于：</span>
     <div style="word-wrap: break-word; margin:10px">
        <table  id="addAllChangeRateTable" class="table table-hover table-striped table-condensed table-bordered">
         	<thead>
             <tr>
                 <td>计算项</td>
                 <td style="text-align:center">规则</td>
                 <td>调节费率</td>
                 <td>操作</td>
             </tr>
            </thead>
            <tbody>
      		<c:forEach items="${allChangeRatelist}" var="allChangeRate" varStatus="status">
             <tr>
                <td style="width:10%;">
                 	<c:if test="${status.index ==0}">
                    		 <select id="changeBlendRateType" class="form-control form-control-static" name="rateType" onclick="changeBlendRateType()">
                	</c:if>
                	<c:if test="${status.index != 0}">
                    	     <select id="changeBlendRateType" class="form-control form-control-static" name="rateType" disabled="disabled" >
                	</c:if>
                      <option value="0" <c:if test="${allChangeRate.rateTypeValue == '0'}">selected</c:if>>规保</option>
                      <option value="1" <c:if test="${allChangeRate.rateTypeValue == '1'}">selected</c:if>>标保</option>
                     </select>
                 </td>
                 <td style="width:77%;">
                     <input style="width:15%;margin-left:2.5%;height:32px;display:inline;"   type="number" step="0.01" min="0" name="bigNum" value="${allChangeRate.bigNum}"> 万元
                    
                     <select id="firstMark" style="margin-left:0.5%;width:15%;display:inline;" class="form-control form-control-static" name="firstMark">
                         <option value=">" <c:if test="${allChangeRate.firstMark == '>'}">selected</c:if>> > </option>
                         <option value=">=" <c:if test="${allChangeRate.firstMark == '>='}">selected</c:if>> >= </option>
                     </select>
                     <!-- 显示标保或者规保 -->
                     <c:if test="${allChangeRate.rateTypeText  == null}">
                    	 <span id="addBlendChangeRateType">规保</span> 
                     </c:if>
                      <c:if test="${allChangeRate.rateTypeText  != null }">
                    	 <span id="addBlendChangeRateType">${allChangeRate.rateTypeText}</span> 
                     </c:if>
                     <select style="margin-left:2.5%;width:15%;display:inline;" class="form-control form-control-static" name="secondMark">
                         <option value=">" <c:if test="${allChangeRate.secondMark == '>'}">selected</c:if>> > </option>
                         <option value=">=" <c:if test="${allChangeRate.secondMark == '>='}">selected</c:if>> >= </option>
                     </select>
                    
                     <input style="margin-left:0.5%;width:15%;height:32px;display:inline;"   type="number" step="0.01" min="0" name="smallNum" value="${allChangeRate.smallNum}"> 万元
                 </td>
                 <td style="width:5%;">
                     <input style="width:auto;height:32px;"   type="number" step="0.01" min="0"  name="changeRate" value="${allChangeRate.changeRate}">
                 </td>
                 <td style="width:10%;">
                     <a style="color: black" href="javascript:void(0)" onclick="addChangeRate('addAllChangeRateTable')">➕</a>
                    <c:if test="${status.index != 0}">
                  	  <a style="color: black" href="javascript:void(0)" onclick="deleteChangeRate(this)">➖</a>
                    </c:if>
                 </td>
             </tr>
            </c:forEach>
        </tbody>
        </table>
    </div>
</div>
       
<!-- 全产品 附加费率 设置规则 -->
 <div  class="table-responsive" style="width:1100px;">
     <span style="width: 130px;margin-left: 50px;margin-top: 10px;">附加费率基于：</span>
      <table  id="addAllSubjoinRateTable" class="table text-nowrap">
          	<thead>
              <tr>
                  <td>计算项</td>
                  <td style="text-align:center">规则</td>
                  <td>调节费率</td>
                  <td>操作</td>
              </tr>
             </thead>
             <tbody>
       		<c:forEach items="${allChangeSubjoinRateList}" var="allSubjoinRate" varStatus="status">
              <tr>
                <td>
                 <c:if test="${status.index ==0}">
                       <select id="checkBlendSubjoinRateType"  style="width:100px;height:32px;" onchange="checkAllSubjoinRateType(${status.index})">
                 </c:if>
                 <c:if test="${status.index != 0}">
                       <select id="checkBlendSubjoinRateType"   style="width:100px;height:32px;" disabled="disabled" >
                 </c:if>
                        <option value="0" <c:if test="${allSubjoinRate.rateTypeValue == '0'}">selected</c:if>>规保</option>
                        <option value="1" <c:if test="${allSubjoinRate.rateTypeValue== '1'}">selected</c:if>>标保</option>
                    	<option value="2" <c:if test="${allSubjoinRate.rateTypeValue== '2'}">selected</c:if>>继续率</option>
                      </select>
                 </td>
                <td style="width: 900px;">
         				<div id="${status.index}blendShowRule" style="display:inline">
                       <!-- 规则最大值 -->
                       <input style="margin-left:2px;width:100px;height:32px;"   type="number" step="0.01" min="0"  name="bigNum" value="${allSubjoinRate.bigNum}"> 万元
                       
                       <select  style="margin-left:2px;width:100px;height:32px;" name="firstMark">
                           <option value=">" <c:if test="${allSubjoinRate.firstMark == '>'}">selected</c:if>> > </option>
                           <option value=">=" <c:if test="${allSubjoinRate.firstMark == '>='}">selected</c:if>> >= </option>
                       </select>
                        <!-- 显示标保或者规保 -->
	                     <c:if test="${allSubjoinRate.rateTypeText  == null}">
	                    	 <span id="addAllSubjoinRateType">规保</span> 
	                     </c:if>
	                      <c:if test="${allSubjoinRate.rateTypeText  != null }">
	                    	 <span id="addAllSubjoinRateType">${allSubjoinRate.rateTypeText}</span> 
	                     </c:if>
                       <select style="margin-left:2px;width:100px;height:32px;"  name="secondMark">
                           <option value=">" <c:if test="${allSubjoinRate.secondMark == '>'}">selected</c:if>> > </option>
                           <option value=">=" <c:if test="${allSubjoinRate.secondMark == '>='}">selected</c:if>> >= </option>
                       </select>
                       
                        <!-- 规则最小值 -->
                       <input style="margin-left:2px;width:100px;height:32px;"   type="number" step="0.01" min="0"  name="smallNum" value="${allSubjoinRate.smallNum}"> 万元
                     	
                     	<span >是否与继续率组合:</span>
                        <select style="margin-left:2px;width:100px;height:32px;" id="${status.index}blendIsGroupPerRate" name="isGroupPerRate"  onchange="checkAllPerRate(${status.index})">
                           <option value="是" <c:if test="${allSubjoinRate.isGroupPerRate == '是'}">selected</c:if>>是</option>
                           <option value="否" <c:if test="${allSubjoinRate.isGroupPerRate == '否'}">selected</c:if> >否</option>
                        </select>
                      
          	  			</div>
                      <!-- 继续率展示或者隐藏  -->
                      <div id ="${status.index}blendShowPerRate" style="display:inline">
                       	<div id="${status.index}blendSyntagmatic" style="display:inline">
                        	<span>组合关系:</span>
	                         <select style="margin-left:2px;width:100px;height:32px;"  name="syntagmatic">
	                             <option value="&&" <c:if test="${allSubjoinRate.syntagmatic == '&&'}">selected</c:if>>并且</option>
	                             <option value="||" <c:if test="${allSubjoinRate.syntagmatic == '||'}">selected</c:if> >或者</option>
	                         </select>
                       	</div>
                       <span>继续率:</span>
                       <select style="margin-left:2px;width:100px;height:32px;"  name="perRateNum">
                          <option value="">请选择</option>
                          <c:forEach items="${subjoinList}" var="var"> 
                          		 <option value="${var.subKey}" <c:if test="${allSubjoinRate.perRateNum == var.subKey}">selected</c:if> >${var.subValue}</option>
		   				 </c:forEach> 
                       </select>
                       
                       <!-- 规则继续率最大值 -->
                       <input style="margin-left:2px;width:100px;height:32px;"   type="number" step="0.01" min="0"  name="bigParRateNum" value="${allSubjoinRate.bigParRateNum}">%
                       
                       <select  style="margin-left:2px;width:100px;height:32px;" name="thirdMark">
                           <option value=">" <c:if test="${allSubjoinRate.thirdMark == '>'}">selected</c:if>> > </option>
                           <option value=">=" <c:if test="${allSubjoinRate.thirdMark == '>='}">selected</c:if>> >= </option>
                       </select>
                       
                       <span>继续率</span> 
                       <select style="margin-left:2px;width:100px;height:32px;"  name="fourMark">
                           <option value=">" <c:if test="${allSubjoinRate.fourMark == '>'}">selected</c:if>> > </option>
                           <option value=">=" <c:if test="${allSubjoinRate.fourMark == '>='}">selected</c:if>> >= </option>
                       </select>
                       <!-- 规则继续率最小值 -->
                        <input style="margin-left:2px;width:100px;height:32px;"   type="number" step="0.01" min="0"  name="smallPerRateNum" value="${allSubjoinRate.smallPerRateNum}">%
                      </div>
                      
                  </td>
                  <td>
                      <input style="width:auto;height:32px;"   type="number" step="0.01" min="0"  name="changeRate" value="${allSubjoinRate.changeRate}">
                  </td>
                  <td>
                     <a style="color: black" href="javascript:void(0)" onclick="addSubjoinRate('addAllSubjoinRateTable')">➕</a>
                     <c:if test="${status.index != 0}">
                   	  <a style="color: black" href="javascript:void(0)" onclick="deleteChangeRate(this)">➖</a>
                     </c:if>
                  </td>
              </tr>
             </c:forEach>
         </tbody>
         </table>
 		<input onclick="saveAdjustChangeRate()" style="background:#1ab394;  height: 30px;margin-left: 400px;width: 80px;color: white; line-height:20px" type="button"  value="保存" />
 		<button onclick="cancelAdjustChangeRate()"  type="button" style="background:#1ab394;  height: 30px;width: 80px;color: white; line-height:20px" >返回</button>
 
 </div>
</div>
	<input type="hidden" id="adjust-param-id" value="${adjustParamId}">
	<div style="display: none; overflow: hidden;">
		<!-- 回显下拉框 -->
		<span id="subjoinList">${subjoinList}</span>
		<!-- 回显单产品附加费率-->
		<span id="subjoinRatelist">${subjoinRatelist}</span>
		<!-- 回显全产品附加费率-->
		<span id="allChangeSubjoinRateList">${allChangeSubjoinRateList}</span>
	</div>
	<!-- 是否查看 标识-->
	<input id="blend_adjust_isLook" type="hidden" value="${isLook}">
</body>
</html>