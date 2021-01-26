<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/core.jsp" %>
<!DOCTYPE  html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <title>标保折标系数设置</title>
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
	var FieldCount=Date.parse(new Date()); //to keep track of text box added
		$(function () {	
		});
	
	//添加验证
	 function checkedBenchmarkingForm(){
		//alert("32323");
	}
		var bonusRate = function () {
			return{				
				 //添加
		        add:function () {
		            	flag = true; 
		    	  if(flag){
		        	document.getElementById("benchmarkingSaveButton").setAttribute("disabled", false);
		    		  checkedBenchmarkingForm();
			            	$.ajax({
			                    url:'benchmarking_discount_manager/add_benchmarking_discount',
			                    type:'post',
			                    dataType:'json',
			                    data:$("#addFormBonus").serialize(),
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
			                		    document.getElementById("benchmarkingSaveButton").removeAttribute("disabled");
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
			        var InputsBenchList  = $("#InputsBenchList"); //Input boxes wrapper ID
			        var AddButton    = $("#addBenchTable"); //Add button ID
			        var x = InputsBenchList.length; //initlal text box count
			//        var FieldCount=0; //to keep track of text box added
					var FieldCounts=1;
					var rowId=1;
			        $(AddButton).click(function (e) //on add input button click
			          {
			            if(x <= MaxInputs) //maximum input box allowed
			            {
			            	document.getElementById("benchmarkingSaveButton").removeAttribute("disabled");
			            	
			            	 FieldCount++; //text box added increment
							 FieldCounts++;
							 rowId++;
			                //add input box
			                $(InputsBenchList).append('<tr id=tr'+ FieldCount +'>'			                		
			                		+'<td><select class="insTypeSelect" style=\"width:80px;\" id=\"insuranceTypeId'+ FieldCount +'\" name=\"insuranceTypeId\">'
			                		+'</select>'
			                		+'</td>'
			                		+'<td><select class="isDun" style=\"width:50px;\" id=\"select'+FieldCount+'\" name=\"status\" onchange=\"funcChengeAppen(this.options[this.options.selectedIndex].value,this.id)\">'
			                		+'<option value="0">否</option>'
			                		+'<option value="1">是</option>'
			                		+'</select>'
			                		+'</td>'
			                		+'<td> <div id=\"ad'+ FieldCount+'\" class="first_div"><input style=\"width:50px;\"  type=\"text\" id=\"minimum'+ FieldCount +'\" name="minimum" >年 '
			                		+'<select style=\"width:50px;\" id=\"minSign'+FieldCount+'\" name=\"minSign\">'
			                		+'<option value="0"  >></option>'
			                		+'<option value="1"   >=</option>'
			                		+'<option value="2"  >>=</option>'
			                		+'</select> 缴费年期 '
			                		+' <select style=\"width:50px;\"  id=\"maxSign'+FieldCount+'\" name="maxSign">'
			                		+'<option value=\"0\"  >></option>'
			                		+'<option value=\"1\"  >=</option>'
			                		+'<option value=\"2\" >>=</option></select> '			
			                		+'<input style=\"width:50px;\" type=\"text\" id=\"maximum'+FieldCount+'\"   name=\"maximum\" >年</div>'
			                		+'<div class="sec_div" style="display:none" id=\"aid'+FieldCount+'\">趸交</div></td>'
			                		+'<td>   <input  type=\"text\" name=\"indexingCoefficient\" id=\"indexingCoefficient'+FieldCount+'\" /></td>'
			                		+'<td><a href=\"#\" rel=\"external nofollow\" class=\"removeclass\"><input  type=\"button\" value=\"删除\"></a></td><tr>');
			                x++; //text box increment
			                getLocalTypes("insuranceTypeId"+FieldCount,"0"); 
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
					 function getLocalTypes(selectId,sysParameter){
							  $.ajax({
						         type: "POST",
						         data:{sysParameter:sysParameter},
						         url: "Insurance_type_manager/find_types",
						         dataType: "json",
						         success: function(data){
						        	var dataObject = data.rows;
									var h = "<option value=''>请选择汇康险种类别</option>";
						            $.each(dataObject, function(key, value) {
						            	  h += "<option value='" + value.insuranceTypeId + "' parentInsuranceTypeId='"+ value.parentInsuranceTypeId +"'>"  + value.insuranceTypeName //下拉框序言的循环数据
							                + "</option>"; 
						            });
						            $("#"+selectId).empty();
						            $("#"+selectId).append(h);
						            
						         }
						     }); 
						}		  
	 });		
	
		   //删除
			function delRowNurture(id) {
				 var mymessage = confirm("确认删除吗？");
				if (mymessage == true) {
				/* 查询  */
				$.ajax({
					url : "benchmarking_discount_manager/check_benchmarking_discount_size",
					type : "post",
					dataType : "json",
					data : {
						id : id
					},
					success : function(size) {
						 if (size >1) {
							 /* 删除 */
								$.ajax({
									url : "benchmarking_discount_manager/update_benchmarking_discount_status",
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
											
										 $("body").on("click",".removeclass", function(e){ //user click on remove text
								             $(this).parent().parent().remove(); //remove text box   '<tr \"'+ FieldCount +'\">'
								            return false;
								        })
								        
										 } else {
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
			  $("#bianjiBench").click(function(){
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
		//控制追加的div 
		 function funcChengeAppen(value,id){
			 var flag = checkSame(id);
			 if(flag){
				 id = id.substring(6,id.length);
				 value = $("#select"+id).val();
			     if(value=='0'){
			    	 $("#ad"+id).show();
			    	 $("#aid"+id).hide();  		    	  
			     }else if(value=='1'){
			    	 $("#ad"+id).hide();
			    	 $("#aid"+id).show();  
			     }
			 }
		 }  
		 //控制遍历的div
		 function funcChenge(value,id){
			var flag = checkSame(id);
		    if(flag){
		    	 id = id.substring(6,id.length);
		    	 value = $("#select"+id).val();
			     //获取被选中的option标签  
			     if(value=='0'){
			    	 $("#td"+id).show();
			    	 $("#tid"+id).hide();  		    	  
			     }else if(value=='1'){
			    	 $("#td"+id).hide();
			    	 $("#tid"+id).show(); 
			     }
			 }
		 }
		 
		 function checkSame(id){
			var flag = true;
			var currValue = $("#"+id).parent().parent().find(".insTypeSelect").val();
			if(currValue==""){
				$.alert({
                    title: '提示信息！',
                    content: '请先选择险种!',
                    type: 'blue'
                });
				$("#"+id).val('0');
				flag = false;
			}
			//alert(currValue);
			if($("#"+id).val()=="1"){
				var j = 0;
				var InputsBenchLen = $("#InputsBenchList").find("tr").length;
				for(var i=1 ; i< InputsBenchLen ; i++){
					var thisValue = $("#InputsBenchList tr:eq("+i+")").find(".insTypeSelect").val();
					var isDun = $("#InputsBenchList tr:eq("+i+")").find(".isDun").val();
					if(currValue==thisValue && isDun=="1"){
						j++;
					}
				}
				if(j>1){
					$.alert({
	                     title: '提示信息！',
	                     content: '该险种已存在趸交参数，请重新选择!',
	                     type: 'blue'
	                });
					$("#"+id).val("0");
					flag = false;
				}
				//alert(flag);
			}
			return flag;			 
		 }
		 
		 getLocalType("0");
		 function getLocalType(sysParameter){
				  $.ajax({
			         type: "POST",
			         data:{sysParameter:sysParameter},
			         url: "Insurance_type_manager/find_types",
			         dataType: "json",
			         success: function(data){
			        	var dataObject = data.rows;
						var h = "<option value=''>请选择汇康险种类别</option>";
						$.each(dataObject, function(key, value) {
			            	  h += "<option selected='selected' value='" + value.insuranceTypeId + "'>"+ value.insuranceTypeName //下拉框序言的循环数据
				                + "</option>"; 			            		
			            });						
						var InputsBenchLen = $("#InputsBenchList").find("tr").length;
						for(var i=1 ; i< InputsBenchLen ; i++){
							var thisValue = $("#InputsBenchList tr:eq("+i+")").find("#localType").attr("myvalue");
							//alert(thisValue);
							$("#InputsBenchList tr:eq("+i+")").find("#localType").empty();
							$("#InputsBenchList tr:eq("+i+")").find("#localType").append(h);
							$("#InputsBenchList tr:eq("+i+")").find("#localType").val(thisValue);
						}			            
			         }
			     }); 
			}		 
	</script> 
</head>
<body>
        <div class="container">
		<form class="form-horizontal" id="addFormBonus"  method="post"  >
	
<div>
 <a href="#" rel="external nofollow" id="addBenchTable" class="btn btn-info">添加</a> 
	<table id="InputsBenchList" border="1" style="display：table-row;border-collapse: inherit;width:90%;" >
		  <tr style="background: silver;">
			<th width="10%"  style="text-align:center;">险种</th>
			 <th width="10%"   style="text-align:center;">是否趸交</th>
		 	<th width="60%"   style="text-align:center;">规则</th>
		    <th width="10%"  style="text-align:center;">标保折标系数（%）</th>
		     <th width="10%"  style="text-align:center;">操作</th>
		  </tr>
		   <c:forEach items="${benchmarkingDiscount}" var="base" varStatus="statusNum"> 
		   
		 <tr>
		    <td  style="display:none"><input value='${base.id}' id="benId"  name="benId" ></td>
		       <td  >
                <select style="width:80px;" class="insTypeSelect" id='localType' name="insuranceTypeId" myvalue="${base.insuranceTypeId}">
                       <%--  <option value=""  <c:if test="${base.insuranceTypeId eq '' }">selected</c:if>  >请选择</option>
                      <option value="0"  <c:if test="${base.insuranceTypeId eq '0' }">selected</c:if>  >健康险</option>
                      <option value="1"  <c:if test="${base.insuranceTypeId eq '1' }">selected</c:if>  >年金险</option> --%>
          		</select>	
             </td>
		    <td  >
                <select class="isDun" style="width:50px;" id="select${statusNum.count}"  name="status"  onchange="funcChenge(this.options[this.options.selectedIndex].value,this.id)">
                      <option value="0"  <c:if test="${base.status eq '0' }">selected</c:if>  >否</option>
                      <option value="1"  <c:if test="${base.status eq '1' }">selected</c:if>  >是</option>
          		</select>	
             </td>
              <td>
              <c:if test="${base.status eq '0' }">
             	 <div  class="first_div" id="td${statusNum.count}">
	              <input  style="width:50px;"  type="text" id="minimum"    value='${base.minimum}' name="minimum" >年
				     <select style="width:50px;" id="minSign" name="minSign">
	                      <!--   <option value="">请选择</option> -->
	                         <option value="0"  <c:if test="${base.minSign eq '0' }">selected</c:if>  >></option>
	                         <option value="1"  <c:if test="${base.minSign eq '1' }">selected</c:if>  >=</option>
	                          <option value="2" <c:if test="${base.minSign eq '2'}">selected</c:if>   >>=</option>
	             		</select>				
	             		缴费年期
	             		<select style="width:50px;"  id="maxSign" name="maxSign">
	                      <!--   <option value="">请选择</option> -->
	                         <option value="0" <c:if test="${base.maxSign eq '0' }">selected</c:if>  >></option>
	                         <option value="1" <c:if test="${base.maxSign eq '1' }">selected</c:if> >=</option>
	                          <option value="2"<c:if test="${base.maxSign eq '2'}">selected</c:if>  >>=</option>
	             		</select>				
				     <input  style="width:50px;"  type="text" id="maximum"   value='${base.maximum}'  name="maximum" >年
			  </div>
			  <div class="sec_div" id="tid${statusNum.count}" style="display:none">趸交</div> 
              </c:if>
              <c:if test="${base.status eq '1' }">
              <div  style="display:none" class="first_div" id="td${statusNum.count}">
	              <input  style="width:50px;"  type="text" id="minimum"    value='${base.minimum}' name="minimum" >年
				     <select style="width:50px;" id="minSign" name="minSign">
	                      <!--   <option value="">请选择</option> -->
	                         <option value="0"  <c:if test="${base.minSign eq '0' }">selected</c:if>  >></option>
	                         <option value="1"  <c:if test="${base.minSign eq '1' }">selected</c:if>  >=</option>
	                          <option value="2" <c:if test="${base.minSign eq '2'}">selected</c:if>   >>=</option>
	             		</select>				
	             		缴费年期
	             		<select style="width:50px;"  id="maxSign" name="maxSign">
	                      <!--   <option value="">请选择</option> -->
	                         <option value="0" <c:if test="${base.maxSign eq '0' }">selected</c:if>  >></option>
	                         <option value="1" <c:if test="${base.maxSign eq '1' }">selected</c:if> >=</option>
	                          <option value="2"<c:if test="${base.maxSign eq '2'}">selected</c:if>  >>=</option>
	             		</select>				
				     <input  style="width:50px;"  type="text" id="maximum"   value='${base.maximum}'  name="maximum" >年
			  </div> 
              <div class="sec_div" id="tid${statusNum.count}">趸交</div>
		      </c:if>              
		    </td>
		   <td  ><input  type="text" id="indexingCoefficient"   value='${base.indexingCoefficient}'  name="indexingCoefficient" >  </td>
		  <td><a href="#"  rel="external nofollow" class="removeclass"><input  type="button" value="删除" onclick="delRowNurture('${base.id}');" ></a></td>
		  </tr>
		  </c:forEach>
	</table>
</div>
            <div class="modal-footer col-md-6">
            <!--用来清空表单数据-->
            <input type="reset" name="reset" style="display: none;" />
                     <button type="button" id="bianjiBench" class="btn btn-primary" >编辑</button>
               <button id="benchmarkingSaveButton" type="button" onclick="bonusRate.add()" class="btn btn-primary">保存</button>
            </div>
            </form>
          </div>
</body>
</html>