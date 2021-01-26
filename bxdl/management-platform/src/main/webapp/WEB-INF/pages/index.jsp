<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8"%>
<c:set value="${pageContext.request.contextPath }" var="path"></c:set>
<%@ include file="/include/index_core.jsp" %>

<html>
<head>
    <title>保险核心系统</title>
</head>
<style>
/* nav-tabs-top */
.myTabWrap { 
	position: absolute; top: 50px; left: 0; right: 0; 
	height: 40px; padding: 0 120px 0 40px; background: #fff;
	transition: all .2s; 
	box-shadow: 0 1px 2px rgba(0,0,0,.1);
}
.myTabWrap .prev,
.myTabWrap .next {
	position: absolute; top: 0; z-index: 2;
	text-align: center; display: block; width: 40px; background: #fff;
	line-height: 40px; font-size: 20px; color: #999;
	cursor: pointer;
}
.myTabWrap .prev { left:0; border-right: 1px solid #f2f2f2; }
.myTabWrap .next { right: 80px; border-left: 1px solid #f2f2f2; }
.myTabWrap>.nav {
	position: relative;	white-space:nowrap; font-size:0; 
}
.myTabWrap>.nav > li { 
	display: inline-block; float: none; position: relative; border-right: 1px solid #f2f2f2;
}
.nav > li + li { margin-left: 0; }
.myTabWrap>.nav > li > a {
	border-radius: 0;
	padding: 0 15px;
	line-height: 44px;
	font-size: 13px;
	font-weight: normal;
}
.myTabWrap>.nav > li:before {
	content:''; display:block; width:0; background:#19aa8d;	
	position: absolute; top: 0; left:0; height: 2px; z-index: 1;
	transition: all 0.2s;
}
.myTabWrap>.nav > li.active:before { width:100%;}
.myTabWrap>.nav > li.active {	border-left: none;}
.myTabWrap>.nav > li.active > a, 
.myTabWrap>.nav > li.active > a:hover, 
.myTabWrap>.nav > li.active > a:focus {
	color: #999;
	background-color: #eee;
}
/* 关闭选项卡按钮*/
.myTabWrap .btnGroup {
	position: absolute;
	right: 0;
	z-index: 2;
	height: 40px;
	background: #fff;
	width: 80px;
	border-left: 1px solid #f2f2f2;
	cursor: pointer;
}
.myTabWrap .btnGroup button {
	background: transparent;
	color: #999;
	border: none;
	box-shadow: none;
	line-height: 40px;
	padding: 0;
	text-align: center;
	width: 100%;
}
.myTabWrap .btnGroup .dropdown-menu {
	left: auto; right: 0;
}
/* 顶部菜单 */
#page-wrapper { position: relative; }
#page-wrapper .topNavbarWrap,
#page-wrapper #myTabContent {
	position:absolute; top: 0; left: 0; right: 0;
}
#page-wrapper #myTabContent { 
	top: 105px; bottom: 0; margin-top: 0; 
	overflow: auto; padding: 0 15px;
}
table td{
     overflow: hidden;
     text-overflow:ellipsis;
     white-space: nowrap;
     font-size:12px;
     font-family: "Microsoft YaHei";
     font-weight:400;
}
.table>thead th { font-size: 12px; background: #e2ebef!important;}
.btn .glyphicon{ top: 0;}
.btn .glyphicon { line-height: normal;}
.btn .glyphicon::before {
	font-size: 12px;
	margin-right: 3px;
} 

/* 表单样式 */
.form-inline .control-label { width: 110px;line-height:35px;}
.form-inline .form-group { margin-bottom: 6px;}
.form-inline .form-control { width: 168px;}

/* 按钮与table间距 margin-bottom: 5px; */
.mgrB0 { margin-bottom: 0!important; }
.mgrB5 { margin-bottom: 5px!important; }



@media (min-width: 768px){
	.form-inline .form-control-static {
	    padding-right: 0;
	}
}
@media (max-width: 768px){
	#page-wrapper {
    	margin: 0 0;
    }
}


/* form表单样式结构：
<form class="form-inline hz-form-inline">
	<div class="form-group">
		<label class="control-label">标签名</label>
		<input type="text" class="form-control" id="search_dict_type_name" name="dictTypeName" placeholder="请输入字典类别名称">
	</div>

	<div class="form-group">
        <label class="control-label">证件类型</label>
        <select class="form-control" name="cardType" id="cardType">
	        <option value="">请选择</option>
	        <option value="1" myvalue="1">身份证</option>
	        <option value="2" myvalue="2">港澳通行证</option>
	        <option value="3" myvalue="3">护照</option>
        </select>
    </div>


	<div class="form-group">
	    <label class="control-label">调用时间</label>
         <input class="form-control" id="startTime" type="text" placeholder="开始调用时间" readonly="">
         <label class="control-label control-label-time">—</label>
         <input class="form-control" id="endTime" type="text" placeholder="结束调用时间"readonly="">
	</div>

	<div class="form-group">
		<button type="button" class="btn btn-info" onclick="a.search()">
		<span class="glyphicon glyphicon-search" aria-hidden="true" >搜索</span>
		</button>
		<button type="button" class="btn btn-danger" onclick="empty()">
		<span class="glyphicon glyphicon-remove" aria-hidden="true" >清空</span>
		</button>
	</div>
</form>
*/
.hz-form-inline { margin-bottom: 0; clear: both;}
.hz-form-inline .form-group {float: left; margin: 5px 0; margin-right: 20px;}
.hz-form-inline .control-label {
    width: 110px;
    line-height: 33px;
    text-align: center;
    background: #fefefe;
    border: 1px solid #e5e6e7;
    float: left;
    border-right: none;
    margin: 0;
    overflow: hidden; text-overflow: ellipsis; white-space: nowrap;
}
.hz-form-inline .control-label-time {width: 20px; border: none; color: #999; }
.hz-form-inline .form-control {width: 168px; margin: 0; float: left; }
/* bootstrap table样式 2019.11.07*/
.bootstrap-table .fixed-table-body {clear: left;overflow-x: auto; }
.fixed-table-pagination .pull-left.pagination-detail {margin-bottom: 20px; }
.fixed-table-pagination .pagination {margin: 0; }
/* 样式 2019.11.11*/
td>.btn {margin-right: 5px;}
td>.btn:last-child {margin-right: 0; }
/* bootstrap container 样式 2019.11.12*/
/*@media (min-width: 1200px) {
  .tab-pane>.container {
    width: 970px;
  }
}*/
</style>

<body class="fixed-sidebar full-height-layout gray-bg">
<div>
    <nav class=" navbar-static-side" role="navigation">
        <div class=""style="overflow: auto; height: 99%;">
            <ul class="nav metismenu" id="side-menu">
                <li class="nav-header" style="text-align: center;">
                		<div class="dropdown profile-element">
							<span><img alt="image" class="img-circle" src="${path}/static/img/head_logo.png" />&nbsp; &nbsp; &nbsp; &nbsp;&nbsp;</span>
								<a data-toggle="dropdown" class="dropdown-toggle" href="#"> 
									<span class="clear">
							 			<span style="margin-left:-30px;margin-top: 6px;" class="text-muted text-xs block" th:text="${employee.name}"><b class="caret"></b>${employee.name}
							 			</span>
									</span>
								</a>
							<ul class="dropdown-menu animated fadeInRight m-t-xs">
								<li><a class="J_menuItem" onclick="openPassDlg()" href="#"><i class="fa fa-key"></i>&nbsp;修改密码</a></li>
								<li><a class="J_menuItem" onclick="quit()" href="#"><i class="fa fa-sign-out"></i>&nbsp;退出登陆</a></li>
							</ul>
						</div>
                </li>
                
                <div id="treeview" class=""></div>
                 <input style="display: none" type="input" class="form-control" id="input-expand-node" placeholder="Identify node..." value="">
            </ul>
       </div> 
        </div>
    </nav>
    <div id="page-wrapper" class="gray-bg" style="height: 99%; position: relative;">
        <div class="row border-bottom">
            <nav class="navbar navbar-static-top  " role="navigation" style="margin-bottom: 0">
                <!-- <div class="navbar-header">
                    <a class="navbar-minimalize minimalize-styl-2 btn btn-primary " href="#"><i class="fa fa-bars"></i> </a>
                </div> -->
                <div class="nav navbar-top-links navbar-right">
                        <span class="m-r-sm text-muted welcome-message">
                        <iframe frameborder='0' scrolling='auto' src='${path}/include/time.html' style='padding:0px;width:100%;height:43px;' ></iframe>
                        </span>
				</div>
            </nav>
        </div>
        <div class="myTabWrap">
        	<div class="dropdown btnGroup">
			  <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">关闭操作<span class="caret"></span></button>
			  <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
			    <li class="J_tabCloseAll"><a href="#">关闭全部选项卡</a></li>
			    <li class="J_tabCloseOther"><a href="#">关闭其他选项卡</a></li>
			  </ul>
			</div>
			<div class="prev">&laquo;</div>
			<div class="next">&raquo;</div>
	        <ul id="myTab" class="nav nav-pills" style="left: 0;">
				<li class="active" style="width: 100px;">
					<a href="#home" data-toggle="tab" >
						 首页
					</a>
				</li>
			</ul>
		</div>
<div id="myTabContent" class="tab-content">
		<div class="tab-pane fade in active" id="home">
			<h2 style="text-align: center; margin-top: 180px;">欢迎访问保险核心系统</h2>
		</div>
</div>
    </div>
</div>


<!-- 修改密码modal -->
<div id="passDlg" class="modal fade"  tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">修改密码</h4>
            </div>
            <div class="container">
			<form class="form-horizontal" id="myform"  method="post">
			<div class="form-group">
			<label class="col-md-2 control-label">旧密码：</label>
			<div class="col-md-3 ">
				<input type="password" id="oldPwd" name="oldPwd" class="form-control form-control-static"  placeholder="请输入原始密码">
				<input  type="hidden" id="employeeId" value="${employee.employeeId}" name="employeeId">
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">新密码：</label>
			<div class="col-md-3 ">
				<input type="password" id="newPwd"  name="newPwd" class="form-control form-control-static" placeholder="请输入新密码">
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">确认密码：</label>
			<div class="col-md-3">
				<input type="password" id="againPwd"  name="againPwd" class="form-control form-control-static" placeholder="请输入新密码">
			</div>
			</div>
            <div class="modal-footer col-md-6">
            <!--用来清空表单数据-->
            <input type="reset" name="reset" style="display: none;" />
                <button type="button" class="btn btn-default" onclick="closeDlgs()">关闭</button>
               <button type="button" onclick="updatePwd()" class="btn btn-primary">修改</button>
            </div>
            </form>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
</body>
</html>
