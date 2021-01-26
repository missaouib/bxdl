<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/core.jsp" %>
<!DOCTYPE  html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <title>处长育成奖金率设置</title>
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Cache" content="no-cache">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   	<link rel="stylesheet" href="${path}/css/system/role/role.css">
   	<link rel="stylesheet" href="${path}/css/product/product.css">
    <script src="${path}/static/js/bootstrap.js"></script>
	<script type="text/javascript">	
		$(function () {	
			/* director.formValidator(); */
			/*  $('input[type="text"]').attr('readOnly',true); */
			 
		});
	
		var director = function () {
			return{				
				 //添加
		        add:function () {
		        	document.getElementById("saveButtonDir").setAttribute("disabled", false);
		            	flag = true; 
		    	  if(flag){
			            	$.ajax({
			                    url:'nurture_bonus_manager/add_nurture_bonus',
			                    type:'post',
			                    dataType:'json',
			                    data:$("#addDirector").serialize(),
			                    success:function(data){
			                        if(data){
			                        	 $.alert({
			                                 title: '提示信息！',
			                                 content: '添加成功!',
			                                 type: 'blue'
			                             });
			                 		    document.getElementById("saveButtonDir").removeAttribute("disabled");
			                 		 
			                        }else{
			                            $.alert({
			                                title: '提示信息！',
			                                content: '添加失败！',
			                                type: 'red'
			                            });
			                            
			                		    document.getElementById("saveButtonDir").removeAttribute("disabled");
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
			
			 $(document).ready(function() {
				 $('input[type="text"]').attr('readOnly',true); 
				 var nodeList = document.getElementsByTagName("input");
				    for (var i = 0; i < nodeList.length; i++) {
				        nodeList[i].readOnly = true;
				    }
				    nodeList = document.getElementsByTagName("select");
				    for (var i = 0; i < nodeList.length; i++) {
				        nodeList[i].readOnly = true;
				    } 
			  $("#czBianji").click(function(){
				 $.alert({
                     title: '提示信息！',
                     content: '可以编辑了!',
                     type: 'blue'
                 });
				 
				 $('input[type="text"]').attr('readOnly',false);
				 var nodeList = document.getElementsByTagName("input");
				    for (var i = 0; i < nodeList.length; i++) {
				        nodeList[i].readOnly = false;
				    }
				    nodeList = document.getElementsByTagName("select");
				    for (var i = 0; i < nodeList.length; i++) {
				        nodeList[i].readOnly = false;
				    } 
			 }) 
	   });		
			
	</script> 
</head>
<body>
        <div class="container">
		<form class="form-horizontal" id="addDirector"  method="post"  >
	
	<div class="div" >
	  
	<table id="InputsDirect" border="1" style="width:90%">
		  <tr style="background: silver;">
			<th width="11%"  style="text-align:center;">育成代数</th>
		 	<th width="11%"  style="text-align:center;">第一年 %</th>
		    <th width="11%"  style="text-align:center;">第二年及以后 %</th>
		  </tr>
		   <c:forEach items="${directorNurtureBonus}" var="base"> 
		
		  <tr id=tr+FieldCount>
		    <td style="display:none" ><input     value='${base.id}' id="dirId"  name="dirId" ></td>
		    <td  >${base.generativeAlgebra} </td>
		    <td  ><input  type="text" id="fastYear"    value='${base.fastYear}' name="fastYear" placeholder="请输入第一年" >%</td>
		    <td  ><input  type="text" id="followingYearAndBeyond"   value='${base.followingYearAndBeyond}'  name="followingYearAndBeyond"placeholder="请输入第二年及以后" >%</td>
		  </tr>
		  </c:forEach>
		  
	</table>
</div>

            <div class="modal-footer col-md-6">
            <!--用来清空表单数据-->
            <input type="reset" name="reset" style="display: none;" />
                     <button type="button" id="czBianji" class="btn btn-primary" >编辑</button>
               <button id="saveButtonDir" type="button" onclick="director.add()" class="btn btn-primary">保存</button>
            </div>
            </form>
          </div>
</body>
</html>