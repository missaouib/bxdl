<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/core.jsp" %>
<!DOCTYPE  html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <title>统计计佣日设置</title>
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Cache" content="no-cache">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   	<link rel="stylesheet" href="${path}/css/system/role/role.css">
   	<link rel="stylesheet" href="${path}/css/product/product.css">
    <script src="${path}/static/js/bootstrap.js"></script>
	<script type="text/javascript">	

		var partnershipAdd = function () {
			return{				
				 //添加
		        add:function () {
		        	
		        	var sp=$('#stat option:selected').val();
		        	if(sp=='0'){
		        		 $.alert({
                             title: '提示信息！',
                             content: '不能为空！',
                             type: 'red'
                         });
		        		 return;
		        		flag = false;
		        	}
		        	document.getElementById("saveButton").setAttribute("disabled", false);
		            	flag = true; 
		    	  if(flag){
			            	$.ajax({
			                    url:'statistical_commission_manager/add_statistical_commission',
			                    type:'post',
			                    dataType:'json',
			                    data:$("#statis").serialize(),
			                    success:function(data){
			                    	  clickThisTab();
			                        if(data){
			                        	 $.alert({
			                                 title: '提示信息！',
			                                 content: '添加成功!',
			                                 type: 'blue'
			                             });
			                 		    document.getElementById("saveButton").removeAttribute("disabled");
			                        }else{
			                            $.alert({
			                                title: '提示信息！',
			                                content: '添加失败！',
			                                type: 'red'
			                            });
			                		    document.getElementById("saveButton").removeAttribute("disabled");
			                        }
			                    },
			                    error:function(){
			                        $.alert({
			                            title: '提示信息！',
			                            content: '请求失败！',
			                            type: 'red'
			                        });
			                    }
			                });
			            }
		        },
				}
			}();
	</script> 
</head>
<body>

<form class="form-inline clearfix" id="statis"  method="post"  >
   <!-- <div>统计佣日设置</div><br/> -->
	<div class="pull-left">每月
        <select class="form-control" id="stat" name="statisticalCommissionDays" >
            <option value="0"  >请选择</option>
             <option value="1" >1</option>
             <option value="2" >2</option>
             <option value="3" >3</option>
             <option value="4" >4</option>
             <option value="5" >5</option>
             <option value="6" >6</option>
             <option value="7" >7</option>
             <option value="8" >8</option>
             <option value="9" >9</option>
             <option value="10">10</option>
             <option value="11">11</option>
             <option value="12">12</option>
             <option value="13">13</option>
             <option value="14">14</option>
             <option value="15">15</option>
             <option value="16">16</option>
             <option value="17">17</option>
             <option value="18">18</option>
             <option value="19">19</option>
             <option value="20">20</option>
             <option value="21">21</option>
             <option value="22">22</option>
             <option value="23">23</option>
             <option value="24">24</option>
             <option value="25">25</option>
             <option value="26">26</option>
             <option value="27">27</option>
             <option value="28">28</option>
             <option value="29">29</option>
             <option value="30">30</option>
             <option value="31">31</option>
 		</select> 日0点统计上个月的员工佣金
 	</div>

   <div class="pull-left" style="margin-left: 10px;">
         <!--用来清空表单数据-->
<!-- 	             <input type="reset" name="reset" style="display: none;" />
-->	            <!--   <button type="button" class="btn btn-default" onclick="windowCloseTab()">关闭</button> -->
         <button id="saveButton" type="button" onclick="partnershipAdd.add()" class="btn btn-primary">保存</button>
   </div>
</form>
  
 <div style="margin: 10px 0 5px; font-weight: bold;">当前计佣金日</div>
 <div>
  <table id="beforeStstList" border="1" style="display：table-row; table-layout:fixed;width:98%;" >
		  <tr style="background: silver;">
			<!-- <th width="18%"  style="text-align:center;">序号</th>
		 	<th width="70%"  style="text-align:center;">设置日</th>
		    <th width="11%"  style="text-align:center;">展业津贴（元）</th>
		     <th width="5%"  style="text-align:center;">操作</th> -->
		  </tr>
		 
		   <c:forEach items="${commissionNow}" var="base"  varStatus="statusNum"> 
		 <tr >
		   <td   style="text-align:center;">${statusNum.count}</td>
		    <td  style="text-align:center;" >每月</td>
		    <td   style="text-align:center;">
		    
		  <%--   <input  style="width:70px;" type="text" id="statisticalCommissionDays"    value='${base.statisticalCommissionDays}' name="statisticalCommissionDays" > --%>
		
		    <select class="form-control form-control-static" id="statisticalCommissionDays" name="statisticalCommissionDays" disabled="disabled">
                        <option value=""  <c:if  test="${base.statisticalCommissionDays eq '' }">selected</c:if> >请选择</option>
                         <option value="1"<c:if  test="${base.statisticalCommissionDays eq '1' }">selected</c:if> >1</option>
                         <option value="2"<c:if  test="${base.statisticalCommissionDays eq '2'}">selected</c:if>  >2</option>
                         <option value="3"<c:if  test="${base.statisticalCommissionDays eq '3' }">selected</c:if> >3</option>
                         <option value="4"<c:if  test="${base.statisticalCommissionDays eq '4'}">selected</c:if>  >4</option> 
                         <option value="5"<c:if  test="${base.statisticalCommissionDays eq '5' }">selected</c:if> >5</option>
                         <option value="6"<c:if  test="${base.statisticalCommissionDays eq '6'}">selected</c:if>  >6</option>
                         <option value="7"<c:if  test="${base.statisticalCommissionDays eq '7' }">selected</c:if> >7</option>
                         <option value="8"<c:if  test="${base.statisticalCommissionDays eq '8'}">selected</c:if>  >8</option>
                         <option value="9"<c:if  test="${base.statisticalCommissionDays eq '9' }">selected</c:if> >9</option>
                         <option value="10"<c:if test="${base.statisticalCommissionDays eq '10'}">selected</c:if> >10</option> 
                         <option value="11"<c:if test="${base.statisticalCommissionDays eq '11' }">selected</c:if>>11</option>
                         <option value="12"<c:if test="${base.statisticalCommissionDays eq '12'}">selected</c:if> >12</option>
                         <option value="13"<c:if test="${base.statisticalCommissionDays eq '13' }">selected</c:if>>13</option>
                         <option value="14"<c:if test="${base.statisticalCommissionDays eq '14'}">selected</c:if> >14</option>
                         <option value="15"<c:if test="${base.statisticalCommissionDays eq '15' }">selected</c:if>>15</option>
                         <option value="16"<c:if test="${base.statisticalCommissionDays eq '16'}">selected</c:if> >16</option> 
                         <option value="17"<c:if test="${base.statisticalCommissionDays eq '17' }">selected</c:if>>17</option>
                         <option value="18"<c:if test="${base.statisticalCommissionDays eq '18'}">selected</c:if> >18</option>
                         <option value="19"<c:if test="${base.statisticalCommissionDays eq '19' }">selected</c:if>>19</option>
                         <option value="20"<c:if test="${base.statisticalCommissionDays eq '20'}">selected</c:if> >20</option>
                         <option value="21"<c:if test="${base.statisticalCommissionDays eq '21' }">selected</c:if>>21</option>
                         <option value="22"<c:if test="${base.statisticalCommissionDays eq '22'}">selected</c:if> >22</option> 
                         <option value="23"<c:if test="${base.statisticalCommissionDays eq '23' }">selected</c:if>>23</option>
                         <option value="24"<c:if test="${base.statisticalCommissionDays eq '24'}">selected</c:if> >24</option>
                         <option value="25"<c:if test="${base.statisticalCommissionDays eq '25' }">selected</c:if>>25</option>
                         <option value="26"<c:if test="${base.statisticalCommissionDays eq '26'}">selected</c:if> >26</option>
                         <option value="27"<c:if test="${base.statisticalCommissionDays eq '27' }">selected</c:if>>27</option>
                         <option value="28"<c:if test="${base.statisticalCommissionDays eq '28'}">selected</c:if> >28</option> 
                         <option value="29"<c:if test="${base.statisticalCommissionDays eq '29' }">selected</c:if>>29</option>
                         <option value="30"<c:if test="${base.statisticalCommissionDays eq '30'}">selected</c:if> >30</option>
                         <option value="31"<c:if test="${base.statisticalCommissionDays eq '31'}">selected</c:if> >31</option>
             		</select>                                                                 
		    </td>
		   <td  style="text-align:center;" >日0点统计上个月的员工佣金  </td>
		  </tr>
		  </c:forEach>
	</table>  
  </div>
   
 <div style="margin: 10px 0; font-weight: bold;">历史统计佣金日</div>
<!--  <div> -->
  <table id="ststList" border="1" style="display：table-row; table-layout:fixed;width:98%;" >
  
		  <tr style="background: silver;">
			<!-- <th width="18%"  style="text-align:center;">序号</th>
		 	<th width="70%"  style="text-align:center;">设置日</th>
		    <th width="11%"  style="text-align:center;">展业津贴（元）</th>
		     <th width="5%"  style="text-align:center;">操作</th> -->
		  </tr>
		 
		   <c:forEach items="${statisticalCommission}" var="base"  varStatus="statusNum"> 
		 <tr >
		   <td   style="text-align:center;">${statusNum.count}</td>
		    <td  style="text-align:center;" >每月</td>
		    <td   style="text-align:center;">
		    
		  <%--   <input  style="width:70px;" type="text" id="statisticalCommissionDays"    value='${base.statisticalCommissionDays}' name="statisticalCommissionDays" > --%>
		    <select class="form-control form-control-static" id="statisticalCommissionDays" name="statisticalCommissionDays" disabled="disabled">
                        <option value=""  <c:if  test="${base.statisticalCommissionDays eq '' }">selected</c:if> >请选择</option>
                         <option value="1"<c:if  test="${base.statisticalCommissionDays eq '1' }">selected</c:if> >1</option>
                         <option value="2"<c:if  test="${base.statisticalCommissionDays eq '2'}">selected</c:if>  >2</option>
                         <option value="3"<c:if  test="${base.statisticalCommissionDays eq '3' }">selected</c:if> >3</option>
                         <option value="4"<c:if  test="${base.statisticalCommissionDays eq '4'}">selected</c:if>  >4</option> 
                         <option value="5"<c:if  test="${base.statisticalCommissionDays eq '5' }">selected</c:if> >5</option>
                         <option value="6"<c:if  test="${base.statisticalCommissionDays eq '6'}">selected</c:if>  >6</option>
                         <option value="7"<c:if  test="${base.statisticalCommissionDays eq '7' }">selected</c:if> >7</option>
                         <option value="8"<c:if  test="${base.statisticalCommissionDays eq '8'}">selected</c:if>  >8</option>
                         <option value="9"<c:if  test="${base.statisticalCommissionDays eq '9' }">selected</c:if> >9</option>
                         <option value="10"<c:if test="${base.statisticalCommissionDays eq '10'}">selected</c:if> >10</option> 
                         <option value="11"<c:if test="${base.statisticalCommissionDays eq '11' }">selected</c:if>>11</option>
                         <option value="12"<c:if test="${base.statisticalCommissionDays eq '12'}">selected</c:if> >12</option>
                         <option value="13"<c:if test="${base.statisticalCommissionDays eq '13' }">selected</c:if>>13</option>
                         <option value="14"<c:if test="${base.statisticalCommissionDays eq '14'}">selected</c:if> >14</option>
                         <option value="15"<c:if test="${base.statisticalCommissionDays eq '15' }">selected</c:if>>15</option>
                         <option value="16"<c:if test="${base.statisticalCommissionDays eq '16'}">selected</c:if> >16</option> 
                         <option value="17"<c:if test="${base.statisticalCommissionDays eq '17' }">selected</c:if>>17</option>
                         <option value="18"<c:if test="${base.statisticalCommissionDays eq '18'}">selected</c:if> >18</option>
                         <option value="19"<c:if test="${base.statisticalCommissionDays eq '19' }">selected</c:if>>19</option>
                         <option value="20"<c:if test="${base.statisticalCommissionDays eq '20'}">selected</c:if> >20</option>
                         <option value="21"<c:if test="${base.statisticalCommissionDays eq '21' }">selected</c:if>>21</option>
                         <option value="22"<c:if test="${base.statisticalCommissionDays eq '22'}">selected</c:if> >22</option> 
                         <option value="23"<c:if test="${base.statisticalCommissionDays eq '23' }">selected</c:if>>23</option>
                         <option value="24"<c:if test="${base.statisticalCommissionDays eq '24'}">selected</c:if> >24</option>
                         <option value="25"<c:if test="${base.statisticalCommissionDays eq '25' }">selected</c:if>>25</option>
                         <option value="26"<c:if test="${base.statisticalCommissionDays eq '26'}">selected</c:if> >26</option>
                         <option value="27"<c:if test="${base.statisticalCommissionDays eq '27' }">selected</c:if>>27</option>
                         <option value="28"<c:if test="${base.statisticalCommissionDays eq '28'}">selected</c:if> >28</option> 
                         <option value="29"<c:if test="${base.statisticalCommissionDays eq '29' }">selected</c:if>>29</option>
                         <option value="30"<c:if test="${base.statisticalCommissionDays eq '30'}">selected</c:if> >30</option>
                         <option value="31"<c:if test="${base.statisticalCommissionDays eq '31'}">selected</c:if> >31</option>
             		</select>                                                                 
		    </td>
		   <td  style="text-align:center;" >日0点统计上个月的员工佣金  </td>
		  </tr>
		  </c:forEach>
	</table>  
<!--  </div> -->
</body>
</html>