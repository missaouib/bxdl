<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/core.jsp"%>

<html >
<head>
    <title>登录页面</title>
    <link rel="stylesheet" href="${path}/css/common/base.css">
    <link rel="stylesheet" href="${path}/css/system/login.css">
    <script src="${path}/js/jquery-1.8.0.min.js" charset="UTF-8" type="text/javascript"></script>
    <style>
        .loginBox {
            background: transparent;
            width: 450px; height: 536px;
            margin-top: -268px;
            margin-left: -206px;
            padding: 0;
        }
        .logBackground {
            padding: 39px 40px 50px;
            background: #fff;
            border-radius: 5px;
            margin-top: 50px;
        }
        .loginTitle {
            font-weight: normal;
            font-size: 32px;
            height: 46px;
            width: 450px;
            line-height: 46px;
            text-align: center;
            color: #fff;
        }
    </style>
</head>
    <script type="text/javascript">
		
		function changeCheckIMG(){
			$("#loginimg").attr("src","${path}/checkimg.jsp?timestamp=" + new Date());
		}
	</script>
    
<body>
<div class="container" style="height:100%;">

    <div class="loginBox ">
        <h1 class="loginTitle" >汇康保险销售（北京）有限公司<br/>保险核心系统</h1>
    <form id="form"   method="post" class="logBackground" >
        <div style="text-align: center;font-size: 26px; color: #9A9A9A; letter-spacing: 0.18px; line-height:50px;margin-bottom: 12px;"> 
            <p class="tit">用户登录</p>
        </div>
        <ul class="login">
            <li>
                <div class="user">
                    <input id="username" name="username" type="text" placeholder="请输入用户名">
                   <span class="del rR"></span> 
                </div>
            </li>
            <li>
                <div class="password">
	                    <input id="password" name="password" type="password" placeholder="请输入密码">
	                  <span class="eye rR"></span> 
                </div>
            </li>
            <li>
                <div class="code lL">
                    <input name="check_code" id="chkcode" type="text" placeholder="输入验证码">
                </div>
                <span  onclick="changeCheckIMG()" style="position:relative; top: 1px;">
                	<img class="verification"  id="loginimg"   src="${path}/checkimg.jsp" 
                	 alt="" >
                </span>
                 
            </li>
            <li>
            	<label id="checkError" style="color:red; height:6px; display: block;">
		           ${msg}
		         </label> 
            </li>
            <li>
                <input type="submit" value="登录" class="loginBtn" />
            </li>
        </ul>
    </form>
</div>

</div>
</body>
<script src="${path}/js/system/login.js"></script>
</html>