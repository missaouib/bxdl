<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/include/core.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>数据字典管理</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Cache" content="no-cache">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="${path}/css/system/dict/dict.css">
    <script src="${path}/js/system/dict/dict.js" charset="UTF-8" type="text/javascript"></script>
    <script src="${path}/static/js/bootstrap.js"></script>
<style>
.panel-body .form-inline {margin-bottom: 0;}
.panel-body .form-group {margin-bottom: 0;}

.btn-toolbar {margin-bottom: 5px;}
/* 弹出框样式 */
.modal { padding-left: 0!important;}
.modal-dialog {	background: #fff;}
.modal-dialog .row { margin-left: 0; margin-right: 0;}
.modal-dialog .form-horizontal { padding-top: 15px;}
.modal-dialog .form-horizontal .form-group { padding-left: 100px;}
.modal-content { box-shadow: none;}
</style>    
</head>
<body>

<div class="panel panel-default">
    <div class="panel-body">
        <form id="dictSearchForm" class="form-inline hz-form-inline">

            <div class="form-group">
                    <label class="control-label">字典类别名称</label>
                    <input type="text" class="form-control" id="search_dict_type_name" name="dictTypeName"
                           placeholder="请输入字典类别名称">
            </div>
             <div class="form-group">
                    <label class="control-label">字典名称</label>
                    <input type="text" class="form-control" id="search_dict_name" name="dictName" placeholder="请输入字典名称">
            </div>
            <div class="form-group">
                <button type="button" onclick="Dict.searchDict()" class="btn btn-info ">
                    <span class="glyphicon glyphicon-search" aria-hidden="true">搜索</span>
                </button>
                <button type="button" onclick="Dict.empty()" class="btn btn-danger ">
                    <span class="glyphicon glyphicon-remove" aria-hidden="true">清空</span>
                </button>
            </div>
        </form>
    </div>
</div>
<!--toolbar  -->
<div id="dictToolbar" class="btn-toolbar">
    <shiro:hasPermission name="dictManager:add">
        <button type="button" class="btn btn-info" onclick="Dict.openDlg()">
            <span class="glyphicon glyphicon-plus">添加</span>
        </button>
    </shiro:hasPermission>
    <shiro:hasPermission name="dictManager:update">
        <button type="button" class=" btn btn-info" onclick="Dict.openUpdateModal()">
            <span class="glyphicon glyphicon-pencil">修改</span>
        </button>
    </shiro:hasPermission>

    <shiro:hasPermission name="dictManager:enable">
        <button class=" btn btn-info" type="button" onclick="Dict.enable()">
            <span class="glyphicon glyphicon-ok-circle">启用</span>
        </button>
    </shiro:hasPermission>
    <shiro:hasPermission name="dictManager:disable">
        <button class=" btn btn-danger" type="button" onclick="Dict.disable()">
            <span class="glyphicon glyphicon-remove">禁用</span>
        </button>
    </shiro:hasPermission>
</div>

<!--列表 -->
<table id="dict-table" class="table table-hover table-striped table-condensed table-bordered"></table>

<!-- 添加字典 -->
<div id="dictAddDlg" class="modal fade" id="myModal" tabindex="-1" role="dialog" data-backdrop="static"
     data-keyboard="false" aria-labelledby="myModalLabel" aria-hidden="true" style="width: 605px;">
    <div class="modal-dialog">
    	<div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h4 class="modal-title" id="myModalLabel">添加系统字典</h4>
        </div>
        <div class="modal-content">
            <div class="row">
                <form class="form-horizontal" id="addDictForm" method="post">
                    <div class="form-group">
                        <label class="col-md-3 control-label">字典类别：</label>
                        <div class="col-md-6 ">
                            <input type="text" id="dict_type" name="dictType" class="form-control form-control-static">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">字典类别名称：</label>
                        <div class="col-md-6 ">
                            <input type="text" id="dict_type_name" name="dictTypeName"
                                   class="form-control form-control-static">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-3 control-label">字典名称：</label>
                        <div class="col-md-6 ">
                            <input type="text" id="dict_name" name="dictName" class="form-control form-control-static">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-3 control-label">字典代码：</label>
                        <div class="col-md-6 ">
                            <input type="text" id="dict_code" name="dictCode" class="form-control form-control-static">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-3 control-label">备注：</label>
                        <div class="col-md-6 ">
                            <input type="text" id="remark" name="remark" class="form-control form-control-static">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-3 control-label">排序号：</label>
                        <div class="col-md-6 ">
                            <input type="text" id="order_id" name="orderId" class="form-control form-control-static">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-3 control-label">父类：</label>
                        <div class="col-md-6">
                            <input type="text" id="dict_tree_name" class="form-control" value="" onclick=""
                                   placeholder="父类">
                            <input type="hidden" id="parent_id" name="parentId" class="form-control" value="" onclick=""
                                   placeholder="父类">
                            <div id="dictTreeView" style="z-index: 9999" class=""></div>
                        </div>
                    </div>
                </form>
            </div>
        </div><!-- /.modal-content -->
        <div class="modal-footer">
            <!--用来清空表单数据-->
            <input type="reset" name="reset" style="display: none;"/>
            <button type="button" class="btn btn-default" onclick="Dict.closeDlg()">关闭</button>
            <button id="saveButton" type="button" onclick="Dict.saveDict()" class="btn btn-primary">提交
            </button>
        </div>
    </div><!-- /.modal -->
</div>

<!-- 修改字典 -->
<div id="dictUpdateDlg" class="modal fade" id="dictUpdateModal" tabindex="-1" role="dialog" data-backdrop="static"
     data-keyboard="false" aria-labelledby="myModalLabel" aria-hidden="true" style="width: 605px;">
    <div class="modal-dialog">
    		<div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="updateModalLabel">修改系统字典</h4>
            </div>
        <div class="modal-content">
            <div class="row">
                <form class="form-horizontal" id="updateDictForm" method="post">
                    <div class="form-group">
                        <label class="col-md-3 control-label">字典类别：</label>
                        <div class="col-md-6 ">
                            <input type="hidden"  id="update_dict_id" name="dictId">
                            <input type="text" id="update_dict_type" name="dictType" class="form-control form-control-static">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">字典类别名称：</label>
                        <div class="col-md-6 ">
                            <input type="text" id="update_dict_type_name" name="dictTypeName"
                                   class="form-control form-control-static">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-3 control-label">字典名称：</label>
                        <div class="col-md-6 ">
                            <input type="text" id="update_dict_name" name="dictName" class="form-control form-control-static">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-3 control-label">字典代码：</label>
                        <div class="col-md-6 ">
                            <input type="text" id="update_dict_code" name="dictCode" class="form-control form-control-static">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-3 control-label">备注：</label>
                        <div class="col-md-6 ">
                            <input type="text" id="update_remark" name="remark" class="form-control form-control-static">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-3 control-label">排序号：</label>
                        <div class="col-md-6 ">
                            <input type="text" id="update_order_id" name="orderId" class="form-control form-control-static">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-3 control-label">父类：</label>
                        <div class="col-md-6">
                            <input type="text" id="update_dict_tree_name" class="form-control" value="" onclick=""
                                   placeholder="父类">
                            <input type="hidden" id="update_parent_id" name="parentId" class="form-control" value="" onclick=""
                                   placeholder="父类">
                            <div id="dictUpdateTreeView" style="z-index: 9999" class=""></div>
                        </div>
                    </div>
                </form>
            </div>
        </div><!-- /.modal-content -->
        <div class="modal-footer">
             <!--用来清空表单数据-->
             <input type="reset" name="reset" style="display: none;"/>
             <button type="button" class="btn btn-default" onclick="Dict.closeDlg()">关闭</button>
             <button id="updateButton" type="button" onclick="Dict.updateDict()" class="btn btn-primary">修改
             </button>
        </div>
    </div><!-- /.modal -->
</div>
</body>
</html>