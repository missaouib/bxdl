<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/core.jsp" %>
<!DOCTYPE  html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <title>新增组织机构</title>
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Cache" content="no-cache">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   	<link rel="stylesheet" href="${path}/css/system/role/role.css">
    <script src="${path}/static/js/bootstrap.js"></script>
	<script type="text/javascript">	
		$(function () {	
			getAreaByPid("0","sheng");
			getInsuranceCompany("insuranceCode","01","");
			InsuranceOrg.formValidator();
		});
		function orgLevelOnChange() {
			var getInsuranceCode = $("#insuranceCode").val();
			if(getInsuranceCode==""){
				alert("请先选择保险公司");
				$(this).val("");
			}else{
				var getlevel = $("#orgLevel").val();
				if(getlevel=="02"){getlevel="01";}else if(getlevel=="03"){getlevel="02";}else if(getlevel=="04"){getlevel="03";}
				getInsuranceCompany("parentCompanyCode",getlevel,getInsuranceCode);
			}
		}
		function number(){
			var parentCode = $("#parentCompanyCode").val();
			var codess = $("#insuranceCompanyCode").val();
			var showCheap=document.getElementById("showCheap");
			var el =new RegExp("^"+"("+parentCode+")");
			Judge(codess,el,showCheap)
		}
		function Judge(k,m,n){

			//k 是被判断的元素
			//m是正则表达式，用来判断
			//n是警告的显示元素
			var patrn=m;
			if (patrn.exec(k)) {
			 if(k==$("#parentCompanyCode").val()){
					$("#saveButton").attr("disabled", true);
					$("#showCheap").html("子集组织机构代码不能等于父集组织机构代码");
					n.setAttribute("style","display:inline;");
					n.setAttribute("style","color:red;font-size:10px;");
				}else{
				 $("#saveButton").attr("disabled", false);
				 n.setAttribute("style","display:none;");
			 }


			} else{
				if(k==""){
					$("#saveButton").attr("disabled", false);
				n.setAttribute("style","display:none;");

				}
                else{
					$("#saveButton").attr("disabled", true);
					$("#showCheap").html("请以"+$("#parentCompanyCode").val()+"开头");
					n.setAttribute("style","display:inline;");
					n.setAttribute("style","color:red;font-size:10px;");
				}
			}
		}
		function getInsuranceCompany(selectId,orgLevel,indexCode){
			 $.ajax({
		         type: "POST",
		         url: "insurance_dept/findInsurCompanys",
		         data:{orgLevel:orgLevel,indexCode:indexCode},
		         dataType: "json",
		         success: function(data){
		        	//alert(data.rows);
		        	var insuranceCompanys = data.rows;
					var h = "<option value=''>请选择保险公司</option>";
					if(selectId=="parentCompanyCode"){h="<option value=''>请选择上级组织机构</option>"}
					if(selectId=="parentCompanyCode" && insuranceCompanys.length==0){
						alert("所选择的公司级别无上级机构，请先创建上级机构！");
						$("#orgLevel").val("");
						return false;
					}
		            $.each(insuranceCompanys, function(key, value) {
		            	//alert(value.insuranceCompanyCode);
		                h += "<option value='" + value.insuranceCompanyCode + "'>" + value.insuranceCompanyName //下拉框序言的循环数据
		                + "</option>";  
		            });
		            $("#"+selectId).empty();
		            $("#"+selectId).append(h);
		            if(selectId="insuranceCode"){
			            $("#"+selectId).on(  
			    	            "change",function(){
									var getlevel = $("#orgLevel").val();
									if(getlevel=="02"){getlevel="01";}else if(getlevel=="03"){getlevel="02";}else if(getlevel=="04"){getlevel="03";}
									if(getlevel!=""){
										getInsuranceCompany("parentCompanyCode",getlevel,$(this).val());
									}
			    	            }
		    	        )
		            }
		         }
		     });
		}
		
		var InsuranceOrg = function () {
			return{				
				 //添加
		        add:function () {
		        	document.getElementById("saveButton").setAttribute("disabled", true);
		            if($("#insuranceOrg_addForm").data('bootstrapValidator').validate().isValid()){
		            	flag = true;
		            	var sheng = $("#sheng").val();
		            	var shi = $("#shi").val();
		            	var qu = $("#qu").val();
		            	if(qu != ""){
		            		$("#areaCode").val(qu);
		            	}else{
		            		if(shi!=""){$("#areaCode").val(shi);}else{$("#areaCode").val(sheng);}
		            	}
						var parentCompanyCode = $("#parentCompanyCode").val();

		            	if(parentCompanyCode == undefined || parentCompanyCode == null || parentCompanyCode == ''){
							$.alert({
								title: '提示信息！',
								content: '上级组织机构不能为空',
								type: 'red'
							});
                            document.getElementById("saveButton").removeAttribute("disabled");
                            return;
						}

		    			if(flag){
			            	$.ajax({
			                    url:'insurance_dept/add_insurance_dept',
			                    dataType:'json',
			                    type:'post',
			                    data:$("#insuranceOrg_addForm").serialize(),
			                    success:function(data){
			                        if(data){
										if(data.messageCode != "200"){
											$.alert({
												title: '提示信息！',
												content: data.data,
												type: 'red'
											});
											document.getElementById("saveButton").removeAttribute("disabled");
											return;
										}
			                        	 $.alert({
			                                 title: '提示信息！',
			                                 content: '添加成功!',
			                                 type: 'blue'
			                             });
			                 		    document.getElementById("saveButton").removeAttribute("disabled");
										createAddProcessTab('insCompanyOrg:list','');
										commCloseTab('insCompanyOrg:add')
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
		            }else{
		    		    document.getElementById("saveButton").removeAttribute("disabled");
		            }
		        },
			
		        //表单验证
		        formValidator:function () {
		            $("#insuranceOrg_addForm").bootstrapValidator({
		                fields:{
		                	insuranceCode:{
		                        validators:{
		                            notEmpty:{
		                                message:"保险公司名称不能为空"
		                            }
		                        }
		                    },
		                    insuranceCompanyName:{
		                        validators:{
		                            notEmpty:{
		                                message:"组织机构名称不能为空"
		                            },
		                            stringLength:{
		                                max:128,
		                                message:'不能超过128个字符长度'
		                            },
		                        }
		                    },	
		                    orgLevel:{
		                        validators:{
		                            notEmpty:{
		                                message:"组织机构级别不能为空"
		                            }
		                        }
		                    },	
		                    parentCompanyCode:{
		                        validators:{
		                            notEmpty:{
		                                message:"请选择上级组织机构"
		                            }
		                        }
		                    },		                    
		                    insuranceCompanyCode:{
		                        validators:{
		                            notEmpty:{
		                                message:'组织机构代码不能为空',
		                            },
		                            stringLength:{
		                                max:32,
		                                message:'字符长度不能超过32'
		                            }
		                        }
		                    },
							insuranceCompanyNameLess:{
		                		validators:{
		                			notEmpty:{
		                				message:'组织机构简称不能为空',
									}
								}
							},
							postCode:{
								validators:{
									regexp: {
										regexp: /^\d+(\.\d+)?$/,
										message: '请输入数字'
									}
								}
							}
		                }
		            });
		         }
				}
			}();
		function clo() {
			createAddProcessTab('insCompanyOrg:list','');
			commCloseTab('insCompanyOrg:add')
		}
	</script> 
</head>
<body>
            <div class="container">
			<form class="form-horizontal" id="insuranceOrg_addForm"  method="post">
			<div class="form-group" style="margin-top:20px;">
			</div>			
			<div class="form-group">
				<label class="col-md-2 control-label">保险公司名称 *：</label>
				<div class="col-md-3 ">
					<select class="form-control form-control-static" id="insuranceCode" name="insuranceCode">
                        <option value="">请选择保险公司</option>
             		</select>
				</div>

				<label class="col-md-2 control-label">组织机构名称 *：</label>
				<div class="col-md-3 ">
					<input type="text" id="insuranceCompanyName"  name="insuranceCompanyName" class="form-control form-control-static" placeholder="请输入组织机构名称">
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-2 control-label">组织机构级别 *：</label>
				<div class="col-md-3 ">
					<select class="form-control form-control-static" id="orgLevel" name="orgLevel" onchange="orgLevelOnChange()">
						<option value="">请选择组织机构级别</option>
<%--                        <option value="01">总公司</option>--%>
                        <option value="02">省份公司</option>
                        <option value="03">地市公司</option>
             		</select>
				</div>

				<label class="col-md-2 control-label">上级组织机构 *：</label>
				<div class="col-md-3 ">
					<select class="form-control form-control-static" id="parentCompanyCode" name="parentCompanyCode">
                        <option value="">请选择上级组织机构</option>
             		</select>				
             	</div>
			</div>
			<div class="form-group">
			
				<label class="col-md-2 control-label">组织机构代码 *：</label>
				<div class="col-md-3 ">
					<input type="text" id="insuranceCompanyCode"  name="insuranceCompanyCode" class="form-control form-control-static" placeholder="请输入组织机构代码" onkeyup="number()"/>
					<span style="display:none;" id="showCheap">格式错误</span>
				</div>

				<label class="col-md-2 control-label">组织机构简称 *：</label>
				<div class="col-md-3 ">
					<input type="text" id="insuranceCompanyNameLess"  name="insuranceCompanyNameLess" class="form-control form-control-static" placeholder="请输入组织机构简称">				
             	</div>
			</div>
			<div class="form-group">
				<label class="col-md-2 control-label">组织机构英文名：</label>
               <div class="col-md-3">
                   <input class="form-control form-control-static" id="insuranceCompanyEnName" name="insuranceCompanyEnName" type="text" placeholder="请选择英文名" />
               </div>

				<label class="col-md-2 control-label">组织机构英文简称：</label>
				<div class="col-md-3 ">
					<input type="text" id="insuranceCompanyEnNameLess"  name="insuranceCompanyEnNameLess" class="form-control form-control-static" placeholder="请输入英文简称">
				</div>
			</div>	
			<div class="form-group">
				<input type="hidden" id="areaCode"  name="areaCode" class="form-control form-control-static" placeholder="请输入区域编码">
				<label class="col-md-2 control-label">所在省份：</label>
				<div class="col-md-3 ">
					<select class="form-control form-control-static" id="sheng" name="sheng">
                        <option value="">请选择省份</option>
             		</select>
             	</div>
             	
             	<label class="col-md-2 control-label">所在城市：</label>
             	<div class="col-md-3 ">	
             		<select class="form-control form-control-static" id="shi" name="shi">
                        <option value="">请选择城市</option>
             		</select>
             	</div>
             	
             </div>
            <div class="form-group">
            	<label class="col-md-2 control-label">所在区县：</label>
             	<div class="col-md-3 ">		
             		<select class="form-control form-control-static" id="qu" name="qu">
                        <option value="">请选择区县</option>
             		</select>
				</div>
				<label class="col-md-2 control-label">邮政编码：</label>
				<div class="col-md-3 ">
					<input type="text" id="postCode"  name="postCode" class="form-control form-control-static" placeholder="请输入邮政编码">
				</div>
			</div>	
			<div class="form-group">
				<label class="col-md-2 control-label">详细地址：</label>
				<div class="col-md-3 ">
					<input style="width:655px;" type="text" id="address"  name="address" class="form-control form-control-static" placeholder="请输入详细地址">
				</div>	
			</div>	
			<div class="form-group">
				<label class="col-md-2 control-label">公司传真：</label>
				<div class="col-md-3 ">
					<input type="text" id="fax"  name="fax" class="form-control form-control-static" placeholder="请输入公司传真">
				</div>

				<label class="col-md-2 control-label">公司电话：</label>
				<div class="col-md-3 ">
					<input type="text" id="tel"  name="tel" class="form-control form-control-static" placeholder="请输入公司电话">
				</div>
			</div>																																											
            <div class="modal-footer col-md-6">
            <!--用来清空表单数据-->
            <input type="reset" name="reset" style="display: none;" />
                <button type="button" class="btn btn-default" onclick="clo()">关闭</button>
               <button id="saveButton" type="button" onmouseover="number()" onclick="InsuranceOrg.add()" class="btn btn-primary">保存</button>
            </div>

            </form>
            </div>
</body>
</html>