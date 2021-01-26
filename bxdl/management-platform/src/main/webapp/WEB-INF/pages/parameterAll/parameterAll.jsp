<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/core.jsp" %>
<!DOCTYPE  html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>基本法</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Cache" content="no-cache">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="${path}/css/system/role/role.css">
    <script src="${path}/static/js/bootstrap.js"></script>
    <link rel="stylesheet" href="${path}/js/insurance/dtree.css">
    <script src="${path}/js/insurance/dtree.js"></script>
    <script src="${path}/js/cal/calExhibitionConfig.js" charset="UTF-8" type="text/javascript"></script>
    <script src="${path}/js/cal/calIncreaseStaff.js" charset="UTF-8" type="text/javascript"></script>
    <script src="${path}/js/cal/calNurtureDirector.js"></script>
    <script src="${path}/js/cal/calNurtureMinister.js"></script>
    <script src="${path}/js/cal/calDept.js"></script>
    <script src="${path}/js/cal/calDirectlyUnderGroup.js"></script>
    <script src="${path}/js/cal/calVersionSelect.js"></script>
    <style>
        html,body,div,span,applet,object,iframe,h1,h2,h3,h4,h5,h6,p,blockquote,pre,a,abbr,acronym,address,big,cite,code,del,dfn,em,img,ins,kbd,q,s,samp,small,strike,strong,sub,sup,tt,var,b,u,i,center,dl,dt,dd,ol,ul,li,fieldset,form,label,legend,table,caption,tbody,tfoot,thead,tr,th,td,article,aside,canvas,details,figcaption,figure,footer,header,hgroup,menu,nav,section,summary,time,mark,audio,video { margin: 0;padding: 0;border: 0;outline: 0;}
        /*当样式表里font-size<12px时，中文版chrome浏览器里字体显示仍为12px，修复bug*/
        html,body,form,fieldset,p,div,h1,h2,h3,h4,h5,h6 { -webkit-text-size-adjust: none;}
        /* 重设 HTML5 标签*/
        article,aside,details,figcaption,figure,footer,header,hgroup,menu,nav,section {display: block;clear: both;}
        body {font-family: "Helvetica Neue", Helvetica, STHeiTi, sans-serif; -webkit-text-size-adjust: none; color: #111; background: #eff1f3; -webkit-text-size-adjust: none; min-width: 320px;  }
        html,body{height:100%;}
        h1,h2,h3,h4,h5,h6 {font-size: 100%; font-weight: normal;}
        form {display: inline;}
        textarea {resize: none;}
        table {border-collapse: collapse; border-spacing: 0;}
        ul,ol {list-style: none;}
        a {text-decoration: none; color: #111;}
        a:hover {color: #111;text-decoration: none;}
        a:visited {text-decoration: none;}
        .wrap{width: 100%;height: 100%;overflow: hidden;margin-top:20px;}
        .wrap .left{float: left;width: 30%;height:100%;border:1px solid #999999}
        .wrap .right{float: right;width:70%;height:100%;border:1px solid #ff1f3;padding-left:10px;overflow:auto}
        .wrap .data-item .cont{padding: 40px;}

        .tab_tou li {
            float: left; padding: 0 10px;
        }
        .tab_tou li.active {
            background: #f0ad4e; color: #fff;
        }
        .tab_zhu li {
            display: none;
        }
        .tab_zhu li.active {
            display: block;
        }
    </style>

    <script type="text/javascript">

        var paramTypeMap;// 对应机构的基本法参数规则
        var RULETYPE = ${RULETYPE};// 基本法规则字典
        var cal_increase_staff_ruleType ='';
        var cal_dept_staff_ruleType = '';
        var deptIncludeDirectlyGroupFlag = '';
        var cal_directly_under_group_ruleType = '';

        $(function() {
            //几个tab切换～
            $("#tab_tou li").on("click",function(){
                $(this).addClass("active").siblings("li").removeClass("active");
                $(".tab_zhu li").eq($(this).index()).addClass("active").siblings("li").removeClass("active");
            })
        })

        $(document).ready(function(){
            // 临时按钮，临时用来确定能否获取3个参数
            $("#btn1").click(function(){
                alert("salesOrgId = " + $("#show_salesOrgId").val() + "，is_default_cal = " + $("#show_isDefaultCal").val() + "，cal_org_id = " + $("#show_calOrgId").val());
            });
        });

        // 页面显示百分比*100；示例：0.06显示为6
        function accMul(arg1, arg2) {
            var m = 0, s1 = arg1.toString(), s2 = arg2.toString();
            try {
                m += s1.split(".")[1].length;
            } catch (e) {
            }
            try {
                m += s2.split(".")[1].length;
            } catch (e) {
            }
            return Number(s1.replace(".", "")) * Number(s2.replace(".", ""))
                / Math.pow(10, m);
        }



    </script>

</head>
<body>
<input type="hidden" id="treeJson" name="treeJson" value='${salesOrgList}'>
<div class="wrap">
    <div class="left dtree" id="orgTreeDiv" style="height:488px;overflow-y:auto">
        <script type="text/javascript">
            d = new dTree('d');
            var salesOrgList = $("#treeJson").val();
            d.add(0,-1,'','javascript:teokAll()');
            $.each(JSON.parse(salesOrgList),function(key, value) {
                d.add(value.salesOrgId,0,value.salesOrgName,'javascript:teokAll('+value.salesOrgId+',\''+ value.salesOrgName +'\','+value.isDefaultCal+','+value.calOrgId+','+"0"+')');
                if(value.orgChildrens){
                    setChild(d,value.salesOrgId,value.orgChildrens);
                }
            });
            function setChild(d,i,data){
                $.each(data,function(key, value) {
                    d.add(value.salesOrgId,i,value.salesOrgName,'javascript:teokAll('+value.salesOrgId+',\''+ value.salesOrgName+'\','+value.isDefaultCal+','+value.calOrgId+','+"0"+')');
                    if(value.orgChildrens){
                        setChild(d,value.salesOrgId,value.orgChildrens);
                    }
                });
            }
            function teokAll(salesOrgId,salesOrgName,isDefaultCal,calOrgId,isChanged){

                $.ajax({
                    url:'/toParameterPage/queryOrgInfo',
                    type:'post',
                    dataType:'json',
                    data : {
                        salesOrgId:salesOrgId
                    },
                    success:function(data){
                        var dataList = eval(data.data);
                        isDefaultCal = eval(data.sales)["salesIsDefaultCal"];
                        $("#name_salesOrgId").val(salesOrgName);
                        $("#name_calOrgId").val(dataList[0]["salesOrgName"]);

                        $("#show_salesOrgId").val(salesOrgId);
                        $("#show_isDefaultCal").val(isDefaultCal);
                        $("#show_calOrgId").val(dataList[0]["calOrgId"]);
                        paramTypeMap = eval(data.paramTypeMap);
                        console.log(paramTypeMap);
                        cal_increase_staff_ruleType=paramTypeMap["1"].ruleType; //增员津贴规则
                        cal_dept_staff_ruleType=paramTypeMap["5"].ruleType; //直辖处津贴规则
                        deptIncludeDirectlyGroupFlag=paramTypeMap["5"].includeDirectlyGroupFlag; //直辖部参数是否包含部长直辖组（0：不包含；1：包含）
                        cal_directly_under_group_ruleType = paramTypeMap["4"].ruleType; //直辖组管理津贴规则

                        //debugger;
                        if(isChanged == "0"){
                            $('#btn').trigger("click");
                            showcalEx();
                        }

                        if(isDefaultCal == "0"){
                            $("#radio1").show();
                            $("#radio2").hide();
                        }else {
                            $("#radio2").show();
                            $("#radio1").hide();
                        }

                    }
                });


            }






            document.getElementById("orgTreeDiv").innerHTML = d;
        </script>
    </div>
    <div class="right">
        <div class="data-item">

            <div >
                <%--组织机构主键：SALES_ORG_ID--%>
                <input type="text" id="show_salesOrgId" name="show_salesOrgId" value="" style="display:none"/>
                <%--是否默认基本法（0：默认；1:自定义）：is_default_cal--%>
                <input type="text" id="show_isDefaultCal" name="show_isDefaultCal" value="" style="display:none"/>
                <%--使用默认基本法总/分公司机构id：cal_org_id--%>
                <input type="text" id="show_calOrgId" name="show_calOrgId" value="" style="display:none"/>
          <div class="input-group mb-3 input-group-sm">
              <span class="input-group-addon" style="background: #A3C6C8">组织机构</span>
              <input class="form-control" type="text" id="name_salesOrgId" value="" style="width: 330px;" disabled='disabled'>
          </div><br>
          <div class="input-group mb-3 input-group-sm" style="margin-top: -12px ">
               <span class="input-group-addon" style="background: #A3C6C8">&nbsp;基本法&nbsp;&nbsp;</span>
             <input class="form-control" type="text" id="name_calOrgId" value="" style="width: 330px;" disabled='disabled'>
             &nbsp;&nbsp;
              <span id="radio1" style="display:none">
                    <input type="radio" checked="checked" disabled/>默认
                    <input type="radio" disabled/>自定义
                </span>
                <span id="radio2" style="display:none">
                    <input type="radio" disabled/>默认
                    <input type="radio" checked="checked" disabled/>自定义
                </span>
       </div>


                <%--<br/>--%>
                <%--<button id="btn1">临时按钮</button>--%>
            </div>

            <br/>
            <!--tab切换部分 -->
            <div style="width:1150px;overflow-x:auto">
            <ul class="nav nav-tabs" id="tab_tou">
                <li role="presentation" class="active" id = "btn"><a href="javascript:void(0)" onclick="showcalEx()">展业津贴</a></li>
                <li role="presentation"><a href="javascript:void(0)" onclick="showcalIncreaseStaff(cal_increase_staff_ruleType,$('#show_salesOrgId').val(),$('#show_isDefaultCal').val(),$('#show_calOrgId').val())">增员津贴</a></li>
                <li role="presentation"><a href="javascript:void(0)" onclick="showcalIUnderGroup(cal_directly_under_group_ruleType,$('#show_salesOrgId').val(),$('#show_isDefaultCal').val(),$('#show_calOrgId').val())">直辖组管理津贴</a></li>
                <li role="presentation"><a href="javascript:void(0)" onclick="showcalDept(cal_dept_staff_ruleType,$('#show_salesOrgId').val(),$('#show_isDefaultCal').val(),$('#show_calOrgId').val())">直辖部管理津贴</a></li>
                <li role="presentation"><a href="javascript:void(0)" onclick="shownNrtureDirector()">处长/业务经理育成津贴</a></li>
                <li role="presentation"><a href="javascript:void(0)" onclick="shownNrtureMinister()">部长/业务总监育成津贴</a></li>
            </ul>
            <ul class="tab_zhu">
                <li class="active" id="">
                    <div class="modal-footer col-md-6">
                        <input type="reset" name="reset" style="display: none;" />
                        <button type="button" id="updateExhibition" class="btn btn-primary"  onclick="updateCalEx()">修改</button>
                        <button id="exhibitionListVersion" type="button" class="btn btn-primary" onclick="CalVersionSelect.selectCalVersionClick('0',$('#show_salesOrgId').val(),$('#show_isDefaultCal').val(),$('#show_calOrgId').val())">版本查询</button>
                    </div>
                   <div style="text-align:center">

                        <div>
                            <table id="versionExhibition" border="1" style="display：table-row; table-layout:fixed;width:90%;border-collapse: inherit;"  hidden="true">
                                  <tr style="background: silver;">
                                    <th width="20%"  style="text-align:center;">版本号</th>
                                    <th width="30%" style="text-align:center;">修改时间</th>
                                    <th width="50"></th>
                                  </tr>
                            </table>
                            <button id="closeVersionExhibition" type="button" class="btn btn-primary" style="display:none" onclick="closeVersionEx()">关闭</button>
                        </div>
                    </div>
                    <form class="form-horizontal" id="addFormExhibitionList"  method="post"  >
                            <table id="exhibitionList" border="1" style="display：table-row; table-layout:fixed;width:90%;border-collapse: inherit;"  hidden="true">
                                  <tr style="background: silver;">
                                    <th width="18%"  style="text-align:center;">设置项</th>
                                    <th width="20%" style="text-align:center;">计算规则选择</th>
                                    <th width="40%"  style="text-align:center;">FYC区间范围判断</th>
                                    <th width="20%"  style="text-align:center;">展业津贴（元）</th>
                                     <th id="exhibitionOperation" width="8%"  style="text-align:center;" hidden="true">操作</th>
                                  </tr>
                            </table>
                    </form>
                    <div id="updateExhibitionBtn" style="text-align:center" hidden="true">
                        <button id="addExhibition" name="exbtn"  type="button" class="btn btn-primary" onclick="addEx()">添加</button>
                        <button id="saveExhibition" name="exbtn"  type="button" class="btn btn-primary"  onclick="saveEx()">保存</button>
                        <button id="cancelExhibition" name="exbtn"  type="button" class="btn btn-primary" onclick="cancelEx()">取消</button>
                    </div>
                </li>
                <li class=""> <%--增员津贴设置--%>
                    <div class="modal-footer col-md-6">
                        <button id="increseUpdate"    type="button"  class="btn btn-primary"  onclick="increaseStaffUpdate()">修改</button>
                        <button id="increseHisShow"   type="button"  class="btn btn-primary"  onclick="CalVersionSelect.selectCalVersionClick('1',$('#show_salesOrgId').val(),$('#show_isDefaultCal').val(),$('#show_calOrgId').val())">版本查询</button>
                    </div>

                    <table id="versionExhibitionList" border="1" style="display：table-row; table-layout:fixed;width:90%;border-collapse: inherit;"  hidden="true">
                        <tr style="background: silver;">
                            <th width="20%"  style="text-align:center;">版本号</th>
                            <th width="30%" style="text-align:center;">修改时间</th>
                            <th width="50"></th>
                        </tr>
                    </table>
                <form class="form-horizontal" id="addFormIncreaseStaffg"  method="post"  >
                    <table id="calIncreaseStaff_g" border="1" style="display：table-row; table-layout:fixed;width:90%;border-collapse: inherit;"  hidden="true">
                        <tr style="background: silver;">
                            <th width="18%"  style="text-align:center;">设置项</th>
                            <th width="20%" style="text-align:center;">计算规则选择</th>
                            <th width="30%"  style="text-align:center;">增员津贴比例(%)</th>
                          <%--  <th width="8%"  style="text-align:center;" hidden="true">操作</th>--%>
                        </tr>
                    </table>
                </form>
                <form class="form-horizontal" id="addFormIncreaseStaffd"  method="post"  >
                    <table id="calIncreaseStaff_d" border="1" style="display：table-row; table-layout:fixed;width:100%;border-collapse: inherit;"  hidden="true">
                        <tr style="background: silver;">
                            <th width="18%"  style="text-align:center;">设置项</th>
                            <th width="20%" style="text-align:center;">计算规则选择</th>
                            <th width="40%"  style="text-align:center;">FYC区间范围判断</th>
                            <th width="20%"  style="text-align:center;">增员津贴比例(%)</th>
                            <th width="8%"  style="text-align:center;">操作</th>
                        </tr>
                    </table>
                </form>
                    <div id="updateButton" style="text-align:center" hidden="true">
                        <button id="increseAdd"     type="button"  class="btn btn-primary"  onclick="increseStaffAdd()"    >添加</button>
                        <button id="increseCommit"  type="button"  class="btn btn-primary"  onclick="increseStaffCommit()" >保存</button>
                        <button id="increseCancel"  type="button"  class="btn btn-primary"  onclick="increseStaffCancel()" >取消</button>
                    </div>

                </li><%--增员津贴设置结束--%>
                <li class="">
                    <div class="modal-footer col-md-6">
                        <button id="underGroupUpdate"    type="button"  class="btn btn-primary"  onclick="underGroupUpdate()">修改</button>
                        <button id="underGroupHisShow"   type="button"  class="btn btn-primary"  onclick="CalVersionSelect.selectCalVersionClick('4',$('#show_salesOrgId').val(),$('#show_isDefaultCal').val(),$('#show_calOrgId').val())">版本查询</button>
                    </div>
                    <form class="form-horizontal" id="addFormUnderGroupGd"  method="post"  >
                        <table id="calIUnderGroup_gd" border="1" style="display：table-row; table-layout:fixed;width:90%;border-collapse: inherit;"  hidden="true">
                            <tr style="background: silver;">
                                <th width="18%"  style="text-align:center;">设置项</th>
                                <th width="20%" style="text-align:center;">计算规则选择</th>
                                <th width="30%"  style="text-align:center;">直辖组管理津贴比例(%)</th>
                            </tr>
                        </table>
                    </form>
                    <form class="form-horizontal" id="addFormUnderGroupqj"  method="post"  >
                        <table id="calFormUnderGroup_q" border="1" style="display：table-row; table-layout:fixed;width:100%;border-collapse: inherit;"  hidden="true">
                            <tr style="background: silver;">
                                <th width="18%"  style="text-align:center;">设置项</th>
                                <th width="18%" style="text-align:center;">计算规则选择</th>
                                <th width="9%" style="text-align:center;" hidden="true" id ='groupNum'>组活动人力</th>
                                <th width="4%"  style="text-align:center;" hidden="true" id = 'grouprelation'>关系</th>
                                <th width="40%"  style="text-align:center;">FYC区间范围判断</th>
                                <th width="20%"  style="text-align:center;">直辖组管理津贴比例(%)</th>
                                <th width="8%"  style="text-align:center;">操作</th>
                            </tr>
                        </table>
                     </form>
                    <div id="updateUnderGroupButton" style="text-align:center" hidden="true">
                        <button id="underGroupAdd"     type="button"  class="btn btn-primary"  onclick="underGroupAdd()"    >添加</button>
                        <button id="underGroupCommit"  type="button"  class="btn btn-primary"  onclick="underGroupCommit()" >保存</button>
                        <button id="underGroupCancel"  type="button"  class="btn btn-primary"  onclick="underGroupCancel()" >取消</button>
                    </div>
                </li>
                <li class=""><%--直辖部管理津贴开始--%>
                    <div class="modal-footer col-md-6">
                        <button id="deptUpdate"    type="button"  class="btn btn-primary"  onclick="deptUpdate()">修改</button>
                        <button id="deptHisShow"   type="button"  class="btn btn-primary"  onclick="CalVersionSelect.selectCalVersionClick('5',$('#show_salesOrgId').val(),$('#show_isDefaultCal').val(),$('#show_calOrgId').val())">版本查询</button>
                    </div>

                    <form class="form-horizontal" id="addFormDeptg"  method="post"  >
                        <table id="calDept_g" border="1" style="display：table-row; table-layout:fixed;width:90%;border-collapse: inherit;"  hidden="true">
                            <tr style="background: silver;">
                                <th width="18%"  style="text-align:center;">设置项</th>
                                <th width="20%" style="text-align:center;">计算规则选择</th>
                                <th width="20%" style="text-align:center;">直辖/非直辖</th>
                                <th width="30%"  style="text-align:center;">直辖部管理津贴比例(%)</th>
                            </tr>
                        </table>
                    </form>
                    <form class="form-horizontal" id="addFormDeptd"  method="post"  >
                        <table id="calDept_d" border="1" style="display：table-row; table-layout:fixed;width:100%;border-collapse: inherit;"  hidden="true">
                            <tr style="background: silver;">
                                <th width="18%"  style="text-align:center;">设置项</th>
                                <th width="20%" style="text-align:center;">计算规则选择</th>
                                <th width="40%"  style="text-align:center;">FYC区间范围判断</th>
                                <th width="20%"  style="text-align:center;">直辖部管理津贴比例(%)</th>
                                <th width="8%"  style="text-align:center;">操作</th>
                            </tr>
                        </table>
                    </form>
                    <div id="deptButton" style="text-align:center" hidden="true">
                        <button id="deptAdd"     type="button"  class="btn btn-primary"  onclick="deptAdd()"    >添加</button>
                        <button id="deptCommit"  type="button"  class="btn btn-primary"  onclick="deptCommit()" >保存</button>
                        <button id="deptCancel"  type="button"  class="btn btn-primary"  onclick="deptCancel()" >取消</button>
                    </div>

                </li><%--直辖部管理津贴结束--%>
                <li class="">
                    <%--处长/经理--%>
                    <div class="modal-footer col-md-6">
                        <input type="reset" name="reset" style="display: none;" />
                        <button type="button" id="shownNrtureDirectorUp" class="btn btn-primary" >修改</button>
                        <button type="button" id="queryVersionNrtureDirector" class="btn btn-primary" onclick="CalVersionSelect.selectCalVersionClick('2',$('#show_salesOrgId').val(),$('#show_isDefaultCal').val(),$('#show_calOrgId').val())">版本查询</button>
                    </div>
                    </form>
                    <form class="form-horizontal" id="formNrtureDirector"  method="post"  >
                        <table id="tableNrtureDirector" border="1" style="width:90%">
                            <tr style="background: silver;">
                                <th width="11%"  style="text-align:center;">育成代数</th>
                                <th width="11%"  style="text-align:center;">第一年</th>
                                <th width="11%"  style="text-align:center;">第二年及以后</th>
                            </tr>
                        </table>
                    </form>
                    <div class="modal-footer col-md-6" id="saveNrtureDirectorUp" style="display: none;">
                        <button id="saveDirectorButton" type="button" onclick="addNrtureDirector()" class="btn btn-primary">保存</button>
                        <button id="cancelNrtureDirector" type="button" class="btn btn-primary">取消</button>
                    </div>
                </li>
                <li class="">
                    <%--部长/总监--%>
                    <div class="modal-footer col-md-6">
                        <input type="reset" name="reset" style="display: none;" />
                        <button type="button" id="shownNrtureMinisterUp" class="btn btn-primary" >修改</button>
                        <button type="button" id="queryVersionNrtureMinister" class="btn btn-primary" onclick="CalVersionSelect.selectCalVersionClick('3',$('#show_salesOrgId').val(),$('#show_isDefaultCal').val(),$('#show_calOrgId').val())">版本查询</button>
                    </div>
                    </form>
                    <form class="form-horizontal" id="formNrtureMinister"  method="post"  >
                        <table id="tableNrtureMinister" border="1" style="width:90%">
                            <tr style="background: silver;">
                                <th width="11%"  style="text-align:center;">育成代数</th>
                                <th width="11%"  style="text-align:center;">第一年</th>
                                <th width="11%"  style="text-align:center;">第二年及以后</th>
                            </tr>
                        </table>
                    </form>
                    <div class="modal-footer col-md-6" id="saveNrtureMinisterUp" style="display: none;">
                        <button id="saveButton" type="button" onclick="addNrtureMinister()" class="btn btn-primary">保存</button>
                        <button id="cancelNrtureMinister" type="button" class="btn btn-primary">取消</button>
                    </div>
                </li>
            </ul>
            </div>

            <form class="form-horizontal" id="cal_version_select" method="post">
                <div id="calVersionDlg"  class="modal fade" aria-hidden="true" style="height: 370px;background: #fff; ">
                    <div>
                        <table id="cal_version-table"  style="overflow: hidden;padding: 5px 15px 15px;" class="table table-hover table-striped table-condensed table-bordered"></table>
                    </div>
                    <div id="calVersionButton" class="btn-group" style="text-align:center">
                        <button type="button" onclick="CalVersionSelect.cancelCalVersion()" class="btn btn-default" style="margin-left: 20px;">关闭</button>
                    </div>
                </div>
            </form>

<br/>
            
        </div>
    </div>
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
        })
        $('.data-item li').each(function(){
            var liHtml=$(this).html();
            $(this).click(function(){
                $('.data-item li').removeClass('bur');
                $(this).siblings('li').attr('data-disabled','')
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