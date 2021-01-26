<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/include/core.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>回执录入</title>
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
            $("#recei_alertFile").modal('show')
        }
        function note_commit() {
            var RECE_Customer_Signature = $("#RECE_Customer_Signature").val();
            var RECE_ORG_DATE = $("#RECE_ORG_DATE").val();

            var RECE_Explain = $("#RECE_Explain").val();
            if (!RECE_Customer_Signature){
                alert("客户签收回执日期不能为空")
                return;
            }
            if (!RECE_ORG_DATE){
                alert("组织机构回执日期录入不能为空")
                return;
            }
            if (!RECE_Explain){
                alert("回执情况说明不能为空")
                return;
            }


            var data = $("#note_addForm").serialize();
            $.ajax({
                url: "insurance_policy/add_insurance_receipt",
                data: data,
                type: "post",
                dataType: "json",
                success: function (obj) {
                    if (obj.code=="200"){
                        alert("提交成功")
                        commCloseTab('updateReceiptOne:list')
                        createAddProcessTab("receipt:list","")
                    } else {
                        alert("提交失败")
                    }
                },
                error: function (obj) {
                    alert("系统异常")
                }
            })
        }

        function upload() {
            var multipart = $("#fileNameRecei").val();
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
            $("#recei_alertFile").modal('hide');
        }
        function changeVal(data) {
            var file = $("#fileNameRecei").val();
            var fileName = getFileName(file);

            function getFileName(o) {
                var pos = o.lastIndexOf("\\");
                return o.substring(pos + 1);
            }
            var trSize =   $("#rece_images").find("tr").length-1 ;
            trSize = trSize + 1;
            data.newFileName
            var ts = $("#rece_images tr").length
            $("#rece_images").append("<tr><td>" + ts + "</td><td>" + fileName + "<input value='" + fileName + "' name='recei_fileName' type='hidden'><input value='" + data.fastPath + "' name='recei_fastPath' type='hidden'></td><td onclick='del_tr(this)'>" + "删除" + "</td></tr>")

        }
        function del_tr(obj) {
            var index = $(obj).parents("tr").index();
            $(obj).parents("tr").remove();
        }
        $(function () {
            note_sell_back
            getSysDictVal("note_sell_back","YESORNO")
        });
function insDetaPage() {
    var id = $("#RECE_POLICY_ID").val()
    commCloseTab('updateReceiptOne:list')
    createAddProcessTab("insDetaPage:list",id)
}
function clo() {
    commCloseTab('updateReceiptOne:list')
    createAddProcessTab("receipt:list","")
}
    </script>

</head>
<body>
<!--列表 -->
<div>
    <shiro:hasPermission name="insDetaPage:list">
    <button type="button" class="btn btn-info" onclick="insDetaPage()">
    <span class="glyphicon glyphicon-pencil" >保单明细</span>
    </button>
    </shiro:hasPermission>

</div>
<form class="form-horizontal" id="note_addForm" method="post" enctype="application/x-www-form-urlencoded">
    <div class="container" style="width: 2000px">

        <div style="overflow:scroll;">
            <div class="form-group">
                <label class="col-md-2 control-label">投保单号*</label>
                <div class="col-md-3 ">
                    <input value="${obj.CORRESPONDING}" type="text" class="form-control form-control-static" id="RECE_CORRESPONDING"
                           name="RECE_CORRESPONDING" readonly="readonly">
                </div>

                <label class="col-md-2 control-label">保单号*</label>
                <div class="col-md-3 ">
                    <input value="${obj.POLICY_ID}" type="text" class="form-control form-control-static" id="RECE_POLICY_ID"
                           name="RECE_POLICY_ID" readonly="readonly">
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 control-label">保险公司*</label>
                <div class="col-md-3 ">
                    <input value="${obj.INSURANCE_COMPANY_NAME}" type="text" class="form-control form-control-static" id="RECE_INSURANCE_COMPANY_NAME"
                           name="RECE_INSURANCE_COMPANY_NAME" readonly="readonly">
                </div>


                <label class="col-md-2 control-label">组织机构*</label>
                <div class="col-md-3 ">
                    <input value="${obj.SALES_ORG_NAME}" type="text" class="form-control form-control-static" id="RECE_SALES_ORG_NAME"
                           name="RECE_SALES_ORG_NAME" readonly="readonly">
                </div>


            </div>
            <div class="form-group">
                <label class="col-md-2 control-label">营销团队*</label>
                <div class="col-md-3 ">
                    <input value="${obj.SALES_TEAM_NAME}" type="text"  class="form-control form-control-static" id="RECE_SALES_TEAM_NAME"
                           name="RECE_SALES_TEAM_NAME" readonly="readonly"/>
                </div>
                <label class="col-md-2 control-label">员工工号*</label>
                <div class="col-md-3 ">
                    <input value="${obj.EMPLOYEE_NO}" type="text" class="form-control form-control-static" id="RECE_EMPLOYEE_NO"
                           name="RECE_EMPLOYEE_NO" readonly="readonly">
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-2 control-label">员工姓名</label>
                <div class="col-md-3 ">
                    <input value="${obj.NAME3}" type="text"  class="form-control form-control-static" id="RECE_NAME3"
                           name="RECE_NAME3" readonly="readonly"/>
                </div>
                <label class="col-md-2 control-label">投保人姓名</label>
                <div class="col-md-3 ">
                    <input value="${obj.NAME1}" type="text" class="form-control form-control-static" id="RECE_NAME1"
                           name="RECE_NAME1" readonly="readonly">
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-2 control-label">被保人姓名</label>
                <div class="col-md-3 ">
                    <input value="${obj.NAME2}" type="text"  class="form-control form-control-static" id="RECE_NAME2"
                           name="RECE_NAME2" readonly="readonly"/>
                </div>
                <label class="col-md-2 control-label">客户签收回执日期*</label>
                <div class="col-md-3 ">
                    <input  type="date" class="form-control form-control-static" id="RECE_Customer_Signature"
                            name="RECE_Customer_Signature">
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-2 control-label">组织机构回执日期录入</label>
                <div class="col-md-3 ">
                    <input  type="date"  class="form-control form-control-static" id="RECE_ORG_DATE"
                            name="RECE_ORG_DATE"/>
                </div>
                <label class="col-md-2 control-label">回执情况说明</label>
                <div class="col-md-3 ">
                    <input  type="text" class="form-control form-control-static" id="RECE_Explain"
                            name="RECE_Explain">
                </div>
            </div>
        </div>

    </div>
    <!--toolbar  -->
    <div id="rctoolbar" class="btn-toolbar" style="overflow:scroll;background: slategray;width: 1800px;">
        <span style="color: cornsilk">投保单影像信息</span>
        <a href="#" id="file" onclick="alertFile()" style="color: blue">添加影像件信息</a>
    </div>

    <div style="overflow:auto;width:1050px;">
        <table class="table table-hover table-striped table-condensed table-bordered"
               style="width:1200px;font-size:13px;" id="rece_images">
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
<div id="recei_alertFile" class="modal fade" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false"
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
                            <input type="file" name="file" id="fileNameRecei"/>
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