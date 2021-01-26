<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/core.jsp" %>
<!DOCTYPE  html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <title>直辖组管理津贴设置</title>
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Cache" content="no-cache">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   	<link rel="stylesheet" href="${path}/css/system/role/role.css">
   	<link rel="stylesheet" href="${path}/css/product/product.css">
   	<script src="${path}/js/system/comm.js" charset="UTF-8" type="text/javascript"></script>
    <script src="${path}/static/js/bootstrap.js"></script>
	<script type="text/javascript">	
		//添加验证
		var bonusRate = function () {
			return{				
				 //添加
		        add:function () {
		        	flag = true; 
		        	$('input[name=minimum]').each(function(){ 
		    	    	 if ($(this).val()==''||$(this).val()==null) {
		    	    		confirm("规则不能为空！");
		    	    		flag = false;
		    	    		 }
		    	    	 })
		    	    	 
		    	    	  $('input[name=maximum]').each(function(){ 
		    	    	 if ($(this).val()==''||$(this).val()==null) {
		    	    			confirm("规则不能为空！");
		    	    			flag = false; 
		    	    		 }
		    	    	 })
		    	    	 
		    	    	  $('input[name=allowance]').each(function(){ 
		    	    	 if ($(this).val()==''||$(this).val()==null) {
		    	    		confirm("管理津贴比例不能为空！");
		    	    		flag = false;
		    	    		 }
		    	    	 })
		    	  if(flag){
		    		  document.getElementById("directlyAdministerSaveButton").setAttribute("disabled", false);
		    		  
			            	$.ajax({
			                    url:'directly_administer_manager/add_directly_administer',
			                    type:'post',
			                    dataType:'json',
			                    data:$("#addFormAdmin").serialize(),
			                    success:function(data){
			                        if(data){
			                        	clickThisTab();
			                        	 $.alert({
			                                 title: '提示信息！',
			                                 content: '添加成功!',
			                                 type: 'blue'
			                             });
			                        }else{
			                            $.alert({
			                                title: '提示信息！',
			                                content: '添加失败！',
			                                type: 'red'
			                            });
			                            
			                		    document.getElementById("directlyAdministerSaveButton").removeAttribute("disabled");
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
			        var MaxInputs    = 8; //maximum input boxes allowed
			        var InputsAdminList  = $("#InputsAdminList"); //Input boxes wrapper ID
			        var AddButton    = $("#adminTable"); //Add button ID
			        var x = InputsAdminList.length; //initlal text box count
			        var FieldCount=0; //to keep track of text box added
					var FieldCounts=1;
					var rowId=1;
			        $(AddButton).click(function (e) //on add input button click
			          {
			            if(x <= MaxInputs) //maximum input box allowed
			            {
			            	document.getElementById("directlyAdministerSaveButton").removeAttribute("disabled");
			            	
			                FieldCount++; //text box added increment
							FieldCounts++;
							rowId++;
			                //add input box
			                $(InputsAdminList).append('<tr id=tr'+ FieldCount +'>'
			                		+'<td> 总价值佣金FYC（元）</td>'
			                		+'<td  ><input   type=\"text\" id=\"minimum'+ FieldCount +'\"   name="minimum" >元　'
			                		+'<select style=\"width:50px;\" id=\"minSign'+ FieldCount +'\" name=\"minSign\">'
			                		+' <option value="0"  >></option>'
			                		+'<option value="1"   >=</option>'
			                		+'<option value="2"  >>=</option>'
			                		+'</select>FYC'
			                		+'<select style=\"width:50px;\"  id=\"maxSign'+ FieldCount +'\" name="maxSign">'
			                		+' <option value=\"0\"  >></option>'
			                		+'<option value=\"1\"  >=</option>'
			                		+'<option value=\"2\" >>=</option></select>'			
			                		+'<input  type=\"text\" id=\"maximum'+ FieldCount +'\"   name=\"maximum\" >元</td>'
			                		+'<td>   <input  type=\"text\" name=\"allowance\" id=\"allowance'+ FieldCount +'\" />%</td>'
			                		+'<td><a href=\"#\"   rel=\"external nofollow\" class=\"removeclass\"><input  type=\"button\" value=\"删除\"></a></td><tr>');
			                 x++; //text box increment
			            }
			            return false;
			        });
			        $("body").on("click",".removeclass", function(e){ //user click on remove text
			            if( x > 1 ) {
			                $(this).parent().parent().remove(); //remove text box   '<tr \"'+ FieldCount +'\">'
			                x--; //decrement textbox
			            }
			            return false;
			        })
	 });		
	
		   //删除
			function delRowNurture(id) {
				 var mymessage = confirm("确认删除吗？");
				if (mymessage == true) {
				/* 查询  */
				$.ajax({
					url : "directly_administer_manager/check_benchmarking_size",
					type : "post",
					dataType : "json",
					data : {
						id : id
					},
					success : function(size) {
						 if (size >1) {
							 /* 删除 */
								$.ajax({
									url : "directly_administer_manager/update_benchmarking_status",
									type : "post",
									dataType : "json",
									data : {
										id : id
									},
									success : function(msg) {
										   if (msg.messageCode == "200") {
											   clickThisTab();
											/*    $(".div").load(location.href+" .div"); */
											   /* $("#InputsDirect").bootstrapTable('refresh'); */
											alert("删除成功！");
										/* 	 createAddProcessTab('nurtureBonus:list'); */
											
										 } else {
											 /* $("#InputsDirect").bootstrapTable('refresh'); */
											 alert("删除失败！");
											 
									}  
								},
							});  
							
						} else {
							$.alert({
		                        title: '提示信息！',
		                        content: '至少保留一条记录！',
		                        type: 'red'
		                    });
							return false;
					}
				},
				error : function() {
					$.alert({
                        title: '提示信息！',
                        content: '请求失败！',
                        type: 'red'
                    });
				}
			});	
				
		}	
	}		
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
				  $("#bianjiAdmin").click(function(){
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
		<form class="form-horizontal" id="addFormAdmin"  method="post"  >
	
<div>
 <a href="#" rel="external nofollow" id="adminTable" class="btn btn-info">添加</a> 

	<table id="InputsAdminList" border="1" style="display：table-row; table-layout:fixed;width:95%;" >
		  <tr style="background: silver;">
			<th width="20%"  style="text-align:center;">设置项</th>
		 	<th width="50%"  style="text-align:center;" >规则</th>
		    <th width="15%"  style="text-align:center;" >管理津贴比例（%）</th>
		     <th width="12%"  style="text-align:center;">操作</th>
		  </tr>
		   <c:forEach items="${directlyAdministeredGroup}" var="base"> 
		 <tr >
		    <td style="display:none" ><input     value='${base.id}' id="admId"  name="admId" ></td>
		     <td  >${base.settings}</td>
		    <td  ><input   type="text" id="minimum"    value='${base.minimum}' name="minimum" >元
			     <select style="width:50px;" id="minSign" name="minSign">
                      <!--   <option value="">请选择</option> -->
                         <option value="0"  <c:if test="${base.minSign eq '0' }">selected</c:if>  >></option>
                         <option value="1"  <c:if test="${base.minSign eq '1' }">selected</c:if>  >=</option>
                          <option value="2" <c:if test="${base.minSign eq '2'}">selected</c:if>   >>=</option>
             		</select>				
             		FYC
             		<select style="width:50px;"  id="maxSign" name="maxSign">
                      <!--   <option value="">请选择</option> -->
                         <option value="0" <c:if test="${base.maxSign eq '0' }">selected</c:if>  >></option>
                         <option value="1" <c:if test="${base.maxSign eq '1' }">selected</c:if> >=</option>
                          <option value="2"<c:if test="${base.maxSign eq '2'}">selected</c:if>  >>=</option>
             		</select>				
			     <input  type="text" id="maximum"   value='${base.maximum}'  name="maximum" >元
		    </td>
		   <td  ><input  type="text" id="allowance"   value='${base.allowance}'  name="allowance" >%</td>
		  <td><a href="#"  rel="external nofollow" class="removeclass"><input  type="button" value="删除" onclick="delRowNurture('${base.id}');" ></a></td>
		  </tr>
		  </c:forEach>
	</table>
</div>
            <div class="modal-footer col-md-6">
            <!--用来清空表单数据-->
            <input type="reset" name="reset" style="display: none;" />
                     <button type="button" id="bianjiAdmin" class="btn btn-primary" >编辑</button>
               <button id="directlyAdministerSaveButton" type="button" onclick="bonusRate.add()" class="btn btn-primary">保存</button>
            </div>
            </form>
          </div>
</body>
</html>