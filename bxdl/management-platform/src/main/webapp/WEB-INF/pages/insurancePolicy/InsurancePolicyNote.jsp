<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/include/core.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>投保单照会</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Cache" content="no-cache">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="${path}/css/system/role/role.css">
    <script src="${path}/js/system/comm.js" charset="UTF-8" type="text/javascript"></script>
    <script src="${path}/static/js/jquery-form.js"></script>
    <script src="${path}/static/js/bootstrap.js"></script>
    <script type="text/javascript">
        function alertFile() {
            $("#note_alertFile").modal('show')
        }
        function note_commit() {
            $('#note_addForm').data('bootstrapValidator').validate();//启用验证
            var flag = $('#note_addForm').data('bootstrapValidator').isValid()//验证是否通过true/false
            if (!flag){
                return;
            }

            var data = $("#note_addForm").serialize();
            $.ajax({
                url: "insurance_policy/add_insurance_note",
                data: data,
                type: "post",
                dataType: "json",
                success: function (obj) {
                if (obj.code="200"){
                    alert("提交成功")
                        commCloseTab('insurancePolicyNote:list')
                        createAddProcessTab('insurancePolicyNoteOrEntry:list','')

                } else{
                    alert("提交异常")
                }
                },
                error: function (obj) {
                alert("系统异常")
                }
            })
        }
        function upload() {
            var multipart = $("#fileNameNote").val();
            if (multipart == "" || multipart == null) {
                alert("请先选择文件!");
                return;
            }
            closeDlg()
            $("#note_loadingModal").modal('show');
            //$('#loadingModal').modal('hide');
            $("#note_fileForm").ajaxSubmit({
                type: 'POST',
                url: 'insurance_policy/uploadFile',
                dataType: 'json',
                success: function (data) {
                    $('#note_loadingModal').modal('hide');
                    if (data.code = "200") {
                        changeVal(data)
                    } else {
                        alert("文件上传失败请重新上传")
                    }
                },
                error: function (data) {
                    $('#loadingModal').modal('hide');
                    alert("系统异常")
                }
            })
        }
        function closeDlg() {
            $("#note_alertFile").modal('hide');
        }
        function del_tr(obj) {
            var index = $(obj).parents("tr").index();
            $(obj).parents("tr").remove();

        }
        function changeVal(data) {
            var file = $("#fileNameNote").val();
            var fileName = getFileName(file);
            function getFileName(o) {
                var pos = o.lastIndexOf("\\");
                return o.substring(pos + 1);
            }
            var trSize =   $("#note_images").find("tr").length-1 ;
            trSize = trSize + 1;
            data.newFileName
            var ts = $("#note_images tr").length
            $("#note_images").append("<tr><td>" + ts + "</td><td>" + fileName + "<input value='" + fileName + "' name='note_fileName' type='hidden'><input value='" + data.fastPath + "' name='note_fastPath' type='hidden'></td><td onclick='del_tr(this)'>" + "删除" + "</td></tr>")

        }
var zh = function(){
            return {
                fromValidator:function () {
                    $("#note_addForm").bootstrapValidator({
                        fields:{
                            note_problem_type:{
                                validators:{
                                    notEmpty:{
                                        message:"问题件类型不能为空"
                                    }
                                }
                    },
                            note_sell_back:{
                                validators:{
                                    notEmpty:{
                                        message:"是否需要回销不能为空"
                                    }
                                }
                            },
                            note_problem_explain:{
                                validators:{
                                    notEmpty:{
                                        message:"问题件说明不能为空"
                                    }
                                }
                            },
                        }
                    })
                }
                    
                
                
    }
            
}();
        $(function () {
            zh.fromValidator();
            getSysDictVal("note_problem_type","ERROR_TYPE")
            getSysDictVal("note_sell_back","YESORNO")
        });
function clo() {
    commCloseTab('insurancePolicyNote:list')
    createAddProcessTab('insurancePolicyNoteOrEntry:list','')
}

    </script>

</head>
<body>
<!--列表 -->

<form class="form-horizontal" id="note_addForm" method="post" enctype="application/x-www-form-urlencoded">
    <div class="container" style="width: 2000px">

        <div style="overflow:scroll;">
            <div class="form-group">
                <label class="col-md-2 control-label">保险公司机构*</label>
                <div class="col-md-3 ">
                    <input value="${msg.INSURANCE_COMPANY_NAME}" type="text" class="form-control form-control-static" id="note_org"
                           name="note_org" readonly="readonly">
                </div>

                <label class="col-md-2 control-label">投保单号*</label>
                <div class="col-md-3 ">
                    <input value="${msg.POLICY_ID}" type="text" class="form-control form-control-static" id="note_policy"
                           name="note_policy" readonly="readonly">
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 control-label">投保人*</label>
                <div class="col-md-3 ">
                    <input value="${msg.NAME}" type="text" class="form-control form-control-static" id="note_ph"
                           name="note_ph" readonly="readonly">
                </div>


                <label class="col-md-2 control-label">问题件类型*</label>
                <div class="col-md-3 ">
                    <select type="text" class="form-control form-control-static" id="note_problem_type"
                            name="note_problem_type"></select>
                </div>


            </div>
            <div class="form-group">
                <label class="col-md-2 control-label">是否需要回销*</label>
                <div class="col-md-3 ">
                    <select  class="form-control form-control-static" id="note_sell_back"
                            name="note_sell_back">

                    </select>
                </div>

                <label class="col-md-2 control-label">问题件说明*</label>
                <div class="col-md-3 ">
                    <input type="text" class="form-control form-control-static" id="note_problem_explain"
                           name="note_problem_explain">
                </div>
            </div>
        </div>

    </div>
    <!--toolbar  -->

    <div id="toolbar_note" class="btn-toolbar" style="overflow:scroll;background: slategray;width: 1800px;">
        <span style="color: cornsilk">投保单影像信息</span>
        <a href="#" id="file" onclick="alertFile()" style="color: blue">添加影像件信息</a>
    </div>

    <div style="overflow:auto;width:1050px;">
        <table class="table table-hover table-striped table-condensed table-bordered"
               style="width:1200px;font-size:13px;" id="note_images">
            <tr>
                <td>影像件序列</td>
                <td>影像件名称</td>
                <td>操作</td>
            </tr>
        </table>
    </div>

</form>
<div class="form-group" style="margin-top:20px;">
    <div class="col-md-3 ">
        <button class="btn btn-primary" onclick="note_commit()">保存提交</button>

        <button class="btn btn-default" onclick="clo()">取消</button>
    </div>
</div>
<div id="note_alertFile" class="modal fade" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false"
     aria-labelledby="zsadd_myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="zsadd_myModalLabel">上传文件</h4>
            </div>
            <div class="container">
                <form class="form-horizontal" id="note_fileForm" enctype="multipart/form-data">
                    <div class="form-group">
                        <label class="col-md-2 control-label">选择文件</label>
                        <div class="col-md-3 ">
                            <input type="file" name="file" id="fileNameNote"/>
                        </div>
                    </div>
                    <div class="modal-footer col-md-6">
                        <!--用来清空表单数据-->
                        <input type="reset" name="reset" style="display: none;"/>
                        <button type="button" class="btn btn-default" onclick="closeDlg()">关闭</button>
                        <button id="zs_saveButton" type="button" onclick="upload()" class="btn btn-primary">保存</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="note_loadingModal">
    <div style="width: 200px;height:20px; z-index: 20000; position: absolute; text-align: center; left: 50%; top: 50%;margin-left:-100px;margin-top:-10px">
        <div class="progress progress-striped active" style="margin-bottom: 0;">
            <div class="progress-bar" style="width: 100%;"></div>
        </div>
        <h5 style="color:black"><strong>正在缓冲文件...预计1-5秒，请稍等！</strong></h5>
    </div>
</div>
</body>
</html>