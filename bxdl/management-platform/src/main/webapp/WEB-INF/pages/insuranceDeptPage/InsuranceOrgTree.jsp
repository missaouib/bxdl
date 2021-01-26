<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/core.jsp" %>
<!DOCTYPE  html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <title>销售组织机构树</title>
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Cache" content="no-cache">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   	<link rel="stylesheet" href="${path}/css/system/role/role.css">
    <script src="${path}/static/js/bootstrap.js"></script>
    <link rel="stylesheet" href="${path}/js/insurance/dtree.css">
    <script src="${path}/js/insurance/dtree.js"></script>
    <style>
        html,body,div,span,applet,object,iframe,h1,h2,h3,h4,h5,h6,p,blockquote,pre,a,abbr,acronym,address,big,cite,code,del,dfn,em,img,ins,kbd,q,s,samp,small,strike,strong,sub,sup,tt,var,b,u,i,center,dl,dt,dd,ol,ul,li,fieldset,form,label,legend,table,caption,tbody,tfoot,thead,tr,th,td,article,aside,canvas,details,figcaption,figure,footer,header,hgroup,menu,nav,section,summary,time,mark,audio,video { margin: 0;padding: 0;border: 0;outline: 0;}
        /*当样式表里font-size<12px时，中文版chrome浏览器里字体显示仍为12px，修复bug*/
        html,body,form,fieldset,p,div,h1,h2,h3,h4,h5,h6 { -webkit-text-size-adjust: none;}
        /* 重设 HTML5 标签*/
        article,aside,details,figcaption,figure,footer,header,hgroup,menu,nav,section {display: block;clear: both;}
        body {font-family: "Helvetica Neue", Helvetica, STHeiTi, sans-serif; -webkit-text-size-adjust: none; color: #111; background: #eff1f3; -webkit-text-size-adjust: none; min-width: 320px;  }
        html,body{height:100%;}
        h1,h2,h3,h4,h5,h6 {font-size: 100%; font-weight: normal;  }
        form {display: inline;}
        textarea {resize: none;}
        table {border-collapse: collapse; border-spacing: 0;}
        ul,ol {list-style: none;}
        a {text-decoration: none; color: #111;}
        a:hover {color: #111;text-decoration: none;}
        a:visited {text-decoration: none;}
        .wrap{width: 100%;height: 100%;overflow: hidden;}
        .wrap .left{float: left;width: 100%;height: 100%;background: #F3F3F4;}
        .wrap .data-item ul{height: 40px;width: 100%;}
        .wrap .data-item ul>li{float: left;width: 100px;height: 40px;color: #222222;text-align: center;line-height: 40px;font-size: 14px;}
        .wrap .data-item ul>li.bur{color: #ff0;border-bottom: 1px solid #ff0;}
        .wrap .data-item .cont{padding: 40px;}
    </style> 

</head>
<body>
<input type="hidden" id="treeJson" name="treeJson" value='${companyOrgList}'>
<div class="wrap" style="height: 700px; overflow-y:scroll">
        <div class="left dtree" id="orgTreeDiv">
			<script type="text/javascript">	
				d = new dTree('d');
				var salesOrgList = $("#treeJson").val();
				d.add(0,-1,'组织机构树状图');
				$.each(JSON.parse(salesOrgList),function(key, value) {					
					d.add(value.insuranceCompanyId,0,value.insuranceCompanyName,'javascript:teok('+value.insuranceCompanyId+')');
					if(value.orgChildren){
						setChild(d,value.insuranceCompanyId,value.orgChildren);
					}
				});					
				function setChild(d,i,data){
					$.each(data,function(key, value) {
						d.add(value.insuranceCompanyId,i,value.insuranceCompanyName,'javascript:teok('+value.insuranceCompanyId+')');
						if(value.orgChildren){
							setChild(d,value.insuranceCompanyId,value.orgChildren);
						}
					});						
				}				
				// function teok(insuranceCompanyId){
				// 	$("#searchInsuranceCompanyId").val(insuranceCompanyId);
				// 	salesTeamInfo.search();
				// }
				
				document.getElementById("orgTreeDiv").innerHTML=d;		
			</script> 
        </div>
<%--        <div class="right">--%>
<%--            <div class="data-item">--%>
<%--				<div style="overflow:scroll;">--%>
<%--					<input type="hidden" id="searchInsuranceCompanyId" value="" />--%>
<%--					<table id="salesTeamInfo-table" class="table table-hover table-striped table-condensed table-bordered">--%>
<%--					</table>--%>
<%--				</div>--%>
<%--            </div>--%>
<%--        </div>--%>
</div>
    <script>
        $(function(){
            $('#menu li').each(function(){
                var _html=$(this).html();
                $(this).click(function(){
                    $(this).siblings('li').attr('data-disabled','');
                    $('.data-item li').attr('data-disabled','');
                    if($(this).attr('data-disabled')==""){
                        $(this).attr('data-disabled','disabled');
                        $('.data-item li').removeClass('bur');
                        $('.data-item li:first').addClass('bur');
                        $('.data-item .cont').html('这是'+$('.data-item li:first').html());
                    }
                })
            });
            $('.data-item li').each(function(){
                var liHtml=$(this).html();
                $(this).click(function(){
                    $('.data-item li').removeClass('bur');
                    $(this).siblings('li').attr('data-disabled','');
                    $(this).addClass('bur');
                    if($(this).attr('data-disabled')==""){
                        $('.data-item .cont').html('这是'+liHtml);
                        $(this).attr('data-disabled','disabled')
                    }
                })
            })
        })
    </script>
</body>
</html>