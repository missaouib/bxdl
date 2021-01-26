		$(function () {
			getAreaByPid("0","sheng");
			getSalesOrgs("topParentSalesOrgCode","01","");
			var areaCode = $("#areaCode").val();
			//alert(areaCode);
			setTimeout(function(){
				
				if($("#cooperationType").val()=="0"){
					$("#zjjt_div").show();
			        //总监津贴
			        var zjjtListArry = JSON.parse($("#zjjtList").val());
			        for(var i=0;i<zjjtListArry.length;i++){
			        		var allowanceFormula = zjjtListArry[i].allowanceFormula;
			        		var max_min = (allowanceFormula.replace('>','').replace('=','')).replace('>','').replace('=','').split('FYC');
			        		var max_min_length = allowanceFormula.split(">=").length-1;
			        		var max_min_all = allowanceFormula.split('FYC');
			        		var max_t = "";
			        		var min_t = "";
			        		if(max_min_length==2){
			        			max_t = min_t = ">=";
			        		}else if(max_min_length==1){
			        			if(max_min_all[0].indexOf(">=")>max_min_all[1].indexOf(">=")){
			        				max_t = ">=";
			        				if(max_min_all[1].indexOf(">")>max_min_all[1].indexOf("=")){min_t = ">"}else{min_t="=";}		        					
			        			}else{
			        				min_t = ">=";
			        				if(max_min_all[0].indexOf(">")>max_min_all[0].indexOf("=")){max_t = ">"}else{max_t="=";}
			        			}
			        		}else{
			        			if(max_min_all[1].indexOf(">")>max_min_all[1].indexOf("=")){min_t = ">"}else{min_t="=";}
			        			if(max_min_all[0].indexOf(">")>max_min_all[0].indexOf("=")){max_t = ">"}else{max_t="=";}
			        		}
			        		var maxFYC_thtml = "<option>></option><option>=</option><option>>=</option>"; 
			        		if(max_t==">"){maxFYC_thtml = "<option selected='selected'>></option><option>=</option><option>>=</option>"}
			        		if(max_t==">="){maxFYC_thtml = "<option>></option><option>=</option><option selected='selected'>>=</option>"}
			        		if(max_t=="="){maxFYC_thtml = "<option>></option><option selected='selected'>=</option><option>>=</option>"}		        		
			        		var minFYC_thtml = "<option>></option><option>=</option><option>>=</option>"; 
			        		if(min_t==">"){minFYC_thtml = "<option selected='selected'>></option><option>=</option><option>>=</option>"}
			        		if(min_t==">="){minFYC_thtml = "<option>></option><option>=</option><option selected='selected'>>=</option>"}
			        		if(min_t=="="){minFYC_thtml = "<option>></option><option selected='selected'>=</option><option>>=</option>"}
			        		var max = max_min[0];
			        		var min = max_min[1];
				        	var zjjt_tr_index = (Date.parse(new Date()))+''+zjjtListArry[i].allowanceId;
				        	var h = '<tr id="zjjt_tr_index_'+zjjt_tr_index+'">'
				    		+'<td style="width:120px;text-align:center;"><a href="javascript:void(0)" onclick="SalesOrgInfo.removeList(\'zjjt_tr_index_'+zjjt_tr_index+'\')" style="color:red">删除</a></td>'
				    		+'<td style="width:140px;text-align:center;">总价值佣金FYC(元)</td>'
				    		+'<td style="width:470px;text-align:center;">'
				    		+'<input type="hidden" id="allowanceId" style="width:70px;" value="'+zjjtListArry[i].allowanceId+'">'
				    		+'<input id="maxFYC" style="width:70px;" type="number" value="'+max+'">&nbsp;元&nbsp;'
				    		+'<select id="maxFYC_t" style="width:50px">'+maxFYC_thtml
				    		+'</select>&nbsp;FYC&nbsp;'
				    		+'<select id="minFYC_t" style="width:50px">'+minFYC_thtml+'</select>&nbsp;' 
				    		+'<input id="minFYC" style="width:70px;" type="number" value="'+min+'">&nbsp;元</td>'
				    		+'<td style="width:120px;text-align:center;"><input id="jtRate" style="width:80px;" type="number" value="'+zjjtListArry[i].allowanceRatio+'">%</td>';
				        	$("#zjjt_list").append(h);        	
			        }

}
                $("#topParentSalesOrgCode option").each(function(){
					//alert($(this).val());
			        if($(this).val()==$("#topParentSalesOrgCode").attr("hideValue")){
			            $(this).attr("selected", true);
			        }
				});

				var getlevelindex = $("#salesOrgLevel").val();
				var parentLevel = "";
				var parentCodeLen = 0;
				//alert(getlevelindex);
				if(getlevelindex=="02"){
					parentLevel="01";parentCodeLen = 4;
				}else if(getlevelindex=="03"){
					parentLevel="02";parentCodeLen = 8;
				}else if(getlevelindex=="04"){
					parentLevel="03";parentCodeLen = 12;
				}else if(getlevelindex=="05") {
					parentLevel="05";
					parentCodeLen = 4;
				}
				getSalesOrgs("parentSalesOrgCode",parentLevel,$("#treeCode").val().substring(0,parentCodeLen));
				setTimeout(function(){ 
					$("#parentSalesOrgCode option").each(function(){
						//alert($(this).val());
				        if($(this).val()==$("#parentSalesOrgCode").attr("hideValue")){
				            $(this).attr("selected", true);
				        }
					});
				},100);	 
				
				$("#sheng option").each(function(){
					//alert($(this).val());
			        if($(this).val()==areaCode.substring(0,6)){
			            $(this).attr("selected", true);
			        }
				});
				getAreaByPid($("#sheng").find("option:selected").attr("myvalue"),"shi");
		        if(areaCode.length>8){
					setTimeout(function(){ 
						$("#shi option").each(function(){
							//alert($(this).val());
					        if($(this).val()==areaCode.substring(0,9)){
					            $(this).attr("selected", true);					            
					        }
						});
						getAreaByPid($("#shi").find("option:selected").attr("myvalue"),"qu");
					},100);	
		        }
		        if(areaCode.length>11){		        	
					setTimeout(function(){ 
						$("#qu option").each(function(){
							//alert($(this).val());
					        if($(this).val()==areaCode.substring(0,12)){
					            $(this).attr("selected", true);
					        }
						});
					},300);	
		        }
				var isflag = $("#accountingOrg").val();
				setTimeout(function () {
					if(isflag=="0"){
						getParents("1");
					}else if(isflag=="1"){
						$("#belongAccountingOrgCode").empty();
						var h = "<option value='' myvalue=''>请选择</option>";
						$("#belongAccountingOrgCode").append(h);
						$("#invoiceOrgCode").empty();
						$("#invoiceOrgCode").append(h);
					}
				}, 300)

			},300);
			SalesOrgInfo.formValidator();


		});
		
		function creatArryList(){			
			var zjjtList = [];
			var zjjtListLength = $("#zjjt_list").find("tr").length;
			for(var i=1 ; i< zjjtListLength ; i++){
				var zjjtObj = {};
				zjjtObj.salesOrgId = $("#salesOrgId").val();
				zjjtObj.allowanceRatio = $("#zjjt_list tr:eq("+i+")").find("#jtRate").val();
				var maxFYC = $("#zjjt_list tr:eq("+i+")").find("#maxFYC").val();
				var maxFYC_t = $("#zjjt_list tr:eq("+i+")").find("#maxFYC_t").val();
				var minFYC = $("#zjjt_list tr:eq("+i+")").find("#minFYC").val();
				var minFYC_t = $("#zjjt_list tr:eq("+i+")").find("#minFYC_t").val();
				zjjtObj.allowanceFormula = (maxFYC+maxFYC_t+"FYC"+minFYC_t+minFYC).replace(/>/g,"&gt;");
				zjjtObj.allowanceId = $("#zjjt_list tr:eq("+i+")").find("#allowanceId").val();
				zjjtList.push(zjjtObj);
			}
			//console.log(zjjtList);//---------合同信息
			$("#zjjtList").val(JSON.stringify(zjjtList));	
		}	
		
		function getSalesOrgs(selectId,orgLevel,indexCode){
			 $.ajax({
		         type: "POST",
		         url: "salesOrgInfo/findSalesOrgs",
		         data:{salesOrgLevel:orgLevel,treeCode:indexCode, salesOrgId: $("#salesOrgId").val()},
		         dataType: "json",
				 async : false,
		         success: function(data){
		        	//alert(data.rows);
		        	var salesOrgs = data.rows;
					var h = "<option value='' myvalue=''>请选择顶级机构</option>";
					if(selectId=="parentSalesOrgCode"){h="<option value=''>请选择上级组织机构</option>"}
					if(selectId=="parentSalesOrgCode" && salesOrgs.length==0 && orgLevel != "01"){
						alert("所选择的机构级别无上级机构，请先创建上级机构！");
						$("#salesOrgLevel").val("");
						return false;
					}
		            $.each(salesOrgs, function(key, value) {
		            	//alert(value.insuranceCompanyCode);
		                h += "<option value='" + value.salesOrgCode + "' myvalue='"+ value.treeCode +"'>" + value.salesOrgName //下拉框序言的循环数据
		                + "</option>";  
		            });
		            $("#"+selectId).empty();
		            $("#"+selectId).append(h);
		            if(selectId="topParentSalesOrgCode"){
			            $("#"+selectId).on(  
			    	            "change",function(){
			    	            	if($(this).val()==""){
			    	            		$("#salesOrgLevel").val("01");
			    	            		var h = "<option value='' myvalue=''>请选择上级机构</option>";
			    	            		$("#parentSalesOrgCode").empty();
			    	            		$("#parentSalesOrgCode").append(h);
			    	            	}else{
										var getlevel = $("#salesOrgLevel").val();										
										if(getlevel!=""){
											if(getlevel=="01"){
												alert("顶级机构下不允许创建总公司级别！");
												$(this).val("");
					    	            		var h = "<option value='' myvalue=''>请选择上级机构</option>";
					    	            		$("#parentSalesOrgCode").empty();
					    	            		$("#parentSalesOrgCode").append(h);
											}else{ 
												if(getlevel=="02"){getlevel="01";}else if(getlevel=="03"){getlevel="02";}else if(getlevel=="04"){getlevel="03";}
												getSalesOrgs("parentSalesOrgCode",getlevel,$(this).find("option:selected").attr("myvalue"));
											}
										}
			    	            	}
			    	            }
		    	        )
		            }
		         }
		     });
		}

		$("#accountingOrg,#parentSalesOrgCode").on(
			"change",function(){
				var isflag = $("#accountingOrg").val();
				if(isflag=="0"){
					getParents("0");
				}else if(isflag=="1"){
					var h = "<option value='' myvalue=''>请选择</option>";
					$("#belongAccountingOrgCode").html(h);
					$("#invoiceOrgCode").empty();
					$("#invoiceOrgCode").append(h);
				}
			}
		);

		function getParents(initFlag){

			var accountingOrg = "1";
			var pTreeCode = $("#parentSalesOrgCode").find("option:selected").attr("myvalue");
			if(topParentSalesOrgCode != ""){
				$.ajax({
					type: "POST",
					url: "salesOrgInfo/getParents",
					data:{accountingOrg:accountingOrg,pTreeCode:pTreeCode},
					dataType: "json",
					async : false,
					success: function(data){
						var salesOrgs = data.rows;
						var h = "<option value='' myvalue=''>请选择</option>";
						var selectEle = $("#belongAccountingOrgCodeHid").val();
						var selectFlag = "";
						$.each(salesOrgs, function(key, value) {
							if(initFlag == "1" && selectEle == value.salesOrgCode ) {
								selectFlag = "selected"
							}
							//alert(value.insuranceCompanyCode);
							h += "<option value='" + value.salesOrgCode + "' myvalue='"+ value.treeCode +"' " +
								"selected='"+ selectFlag +"'>" + value.salesOrgName //下拉框序言的循环数据
								+ "</option>";
						});
						$("#belongAccountingOrgCode").empty();
						$("#belongAccountingOrgCode").append(h);
						$("#invoiceOrgCode").empty();
						$("#invoiceOrgCode").append(h);
					}
				})
			}
		}

		var SalesOrgInfo = function () {
			return{				
				 //添加
		        add:function () {
		        	document.getElementById("saveButton").setAttribute("disabled", true);
		            if($("#updateSalesOrgInfoForm").data('bootstrapValidator').validate().isValid()){
		            	flag = true;
		            	var sheng = $("#sheng").val();
		            	var shi = $("#shi").val();
		            	var qu = $("#qu").val();
		            	if(qu != ""){ 
		            		$("#areaCode").val(qu);
		            	}else{
		            		if(shi!=""){$("#areaCode").val(shi);}else{$("#areaCode").val(sheng);}
		            	}
		            	if($("#salesOrgLevel").val()!="01" && $("#parentSalesOrgCode").val()==""){
		            		$.alert({
                                title: '提示信息！',
                                content: '请选择上级机构!',
                                type: 'blue'
                            });
		            		flag = false;
                		    document.getElementById("saveButton").removeAttribute("disabled");
                		    return false;
		            	}
		            	debugger;
		            	var oldparentSalesOrgCode = $("#parentSalesOrgCode").attr("oldValue");
		            	var oldsalesOrgName = $("#salesOrgName").attr("oldValue");
		            	var oldsalesOrgLevel = $("#salesOrgLevel").attr("oldValue");
                       debugger;
		            	var newparentSalesOrgCode = $("#parentSalesOrgCode").val();
		            	var newsalesOrgName = $("#salesOrgName").val();
		            	var newsalesOrgLevel = $("#salesOrgLevel").val();
		            	if (newparentSalesOrgCode != oldparentSalesOrgCode || newsalesOrgName !=oldsalesOrgName ||newsalesOrgLevel != oldsalesOrgLevel ){
		            		$("#isNoticy").val("1");
						}

		    			if(flag){
		    				creatArryList();
			            	$.ajax({
			                    url:'salesOrgInfo/updateSalesOrgInfo',
			                    dataType:'json',
			                    type:'post',
			                    data:$("#updateSalesOrgInfoForm").serialize(),
			                    success:function(data){
									if(data.messageCode=="200"){
										windowCloseTab();
										$.alert({
											title: '提示信息！',
											content: '修改成功!',
											type: 'blue'
										});
										document.getElementById("saveButton").removeAttribute("disabled");
									}else{
										$.alert({
											title: '提示信息！',
											content:data.requestState,
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
		        
		        openImageDlg:function(value){
		        	$("#backto").val(value);
		   	        $("#openImageDlg").modal('show');
		        },
		        
		        showImg:function(value){
		        	var url = $("#"+value).val();
		        	//var fullUrl = configSys.fdfs_location+url;
					var fullUrl = $("#fastInnerUrl").val()+url;
		        	if(isEmpty(url)){
	                   	 $.alert({
	                         title: '提示信息！',
	                         content: '请先上传资料!',
	                         type: 'red'
	                     });
	                   	 return false;
		        	}
		        	window.open(fullUrl,"_blank");
		        },
		        
		        uploadImage:function(formId){
		        	comm_uploadImage(formId,$("#backto").val());
		        },
		        
		        //----------------------------------------------------------------------------------------总监津贴
		        addZjjt:function(){
		        	var zjjt_tr_index = Date.parse(new Date());
		        	var h = '<tr id="zjjt_tr_index_'+zjjt_tr_index+'">'
		    		+'<td style="width:120px;text-align:center;"><a href="javascript:void(0)" onclick="SalesOrgInfo.removeList(\'zjjt_tr_index_'+zjjt_tr_index+'\')" style="color:red">删除</a></td>'
		    		+'<td style="width:140px;text-align:center;">总价值佣金FYC(元)</td>'
		    		+'<td style="width:470px;text-align:center;">'
		    		+'<input type="hidden" id="allowanceId" style="width:70px;" value="">'
		    		+'<input id="maxFYC" style="width:70px;" type="number" value="">&nbsp;元&nbsp;'
		    		+'<select id="maxFYC_t" style="width:50px"><option>></option><option>=</option><option>>=</option></select>&nbsp;FYC&nbsp;'
		    		+'<select id="minFYC_t" style="width:50px"><option>></option><option>=</option><option>>=</option></select>&nbsp;' 
		    		+'<input id="minFYC" style="width:70px;" type="number" value="">&nbsp;元</td>'
		    		+'<td style="width:120px;text-align:center;"><input id="jtRate" style="width:80px;" type="number" value=""></td>';
		        	$("#zjjt_list").append(h);
		        },
		        
		        //打开添加模态框
		        removeList:function (trid) {
		            $("#"+trid).remove();
		        },
			
		        //表单验证
		        formValidator:function () {
		            $("#updateSalesOrgInfoForm").bootstrapValidator({
		                fields:{
		                	salesOrgName:{
		                        validators:{
		                            notEmpty:{
		                                message:"组织机构不能为空"
		                            }
		                        }
		                    },
		                    salesOrgCode:{
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
							salesOrgNameLess:{
								validators:{
									notEmpty:{
										message:"组织机构简称不能为空"
									},
									stringLength:{
										max:100,
										message:'不能超过100个字符长度'
									},
								}
							},
		                    salesOrgLevel:{
		                        validators:{
		                            notEmpty:{
		                                message:'组织机构级别不能为空',
		                            }
		                        }
		                    },
		                    sheng:{
		                        validators:{
		                            notEmpty:{
		                                message:'所在省份不能为空',
		                            }
		                        }
		                    },
							shi:{
								validators:{
									notEmpty:{
										message:'所在城市不能为空',
									}
								}
							},
		                    businessTaxRate:{
		                        validators:{
		                        	regexp: {
		                                regexp:/^-?(100|(([1-9]\d|\d)(\.\d{1,2})?))%$/,
		                                message: '请输入小于100%的百分数,最多2位小数!'
		                            }
		                        }
		                    },
		                    incomeTaxRate:{
		                        validators:{
		                        	regexp: {
		                                regexp:/^-?(100|(([1-9]\d|\d)(\.\d{1,2})?))%$/,
		                                message: '请输入小于100%的百分数,最多2位小数!'
		                            }									
		                        }
		                    },
							contactsTel:{
		                		validators:{
									regexp:{
                                        regexp:/^1[3456789]\d{9}$/,
                                        message:"请输入正确手机号格式"
                                    }
								}
							},
							postCode:{
		                		validators:{
		                			regexp:{
		                				regexp: /^\d+(\.\d+)?$/,
										message: '请输入数字'
									}
								}
							},
							employeesNum:{
		                		validators:{
		                			regexp:{
		                				regexp: /^\d+(\.\d+)?$/,
										message: '请输入数字'
									}
								}
							},
							socialSecurityNum:{
		                		validators:{
		                			regexp:{
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
		$("#salesOrgLevel").on(
			"change",function(){
				var topParentSalesOrgCode = $("#topParentSalesOrgCode").find("option:selected").attr("myvalue");
				//alert(topParentSalesOrgCode);
				if(topParentSalesOrgCode=="" && $(this).val()!="01"){
					alert("请先选择顶级机构");
					$(this).val("");
				}else if(topParentSalesOrgCode!="" && $(this).val()=="01"){
					alert("顶级机构下不允许创建总公司级别！");
					$(this).val("");
					var h = "<option value='' myvalue=''>请选择上级机构</option>";
					$("#parentSalesOrgCode").empty();
					$("#parentSalesOrgCode").append(h);
				}else{
					var getlevel = $(this).val();
					if(getlevel=="02"){
						getlevel="01";
					}else if(getlevel=="03"){
						getlevel="02";
					}else if(getlevel=="04"){
						getlevel="03";
					}else if(getlevel=="05"){
						getlevel = "05";
					}
					getSalesOrgs("parentSalesOrgCode",getlevel,topParentSalesOrgCode);
				}
			}
		);