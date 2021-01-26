$(function () {
    CalExhibition.formValidator();
});

function showcalEx() {
  var isDefaultCal = $("#show_isDefaultCal").val();
  if(isDefaultCal == ''){
        $.alert({
            title: '提示信息！',
            content: '请先选择机构！',
            type: 'red'
        });
        return false;
    }
  $("#versionExhibitionList").hide();
  $("#exhibitionList").show();
  $("#exhibitionOperation").hide();
  $("#updateExhibitionBtn").hide();


  var orgid = "";
  if('0'==isDefaultCal){
      orgid =$("#show_calOrgId").val();
  }else if('1'==isDefaultCal){
      orgid =$("#show_salesOrgId").val();
  }
  $("#exhibitionList  tr:not(:first)").empty("");
  viewCalEx(orgid);
}
function updateCalEx() {
    $("#exhibitionOperation").show();
    // $("#addExhibition").show();
    // $("#saveExhibition").show();
    // $("#cancelExhibition").show();
    $("#updateExhibitionBtn").show();
    $("#exhibitionList input[type='text']").attr("disabled",false);
    $("#exhibitionList select").attr("disabled",false);
    $("#exhibitionList input[name='exDelete']").attr("hidden",false);

    updateOperation();

}

function updateOperation() {
   var xx = paramTypeMap[0].ruleType;
   var str = xx.split(",");
   // console.log(str);
   var initSelectObj = $("#exhibitionList select[class ='exruleType']").val();
   // debugger;
   var html = '';
   for(var i=0;i<str.length;i++){
       html += "<option value='"+RULETYPE[str[i]].dictCode+"'> "+RULETYPE[str[i]].dictName+" </option>"
   }
   $("#exhibitionList select[class ='exruleType']").each(function(){
       var val = $(this).val();
       // debugger;
       $(this).html(html);
       $(this).find("option[value='"+val+"']").attr("selected",true);
   });

   var optionsminSign =  "<option value='0'> < </option>"+
                                  "<option value='1'> <= </option>";
   var optionsmaxSign =  "<option value='0'> < </option>"+
                                  "<option value='1'> <= </option>";
   // $("#exhibitionList select[name='optionsminSign']").append(optionsminSign);
   $("#exhibitionList select[name='optionsminSign']").each(function () {
       var minV = $(this).val();
       $(this).html(optionsminSign);
       $(this).find("option[value='"+minV+"']").attr("selected",true);
   })
   // $("#exhibitionList select[name='optionsmaxSign']").append(optionsmaxSign);
   $("#exhibitionList select[name='optionsmaxSign']").each(function () {
       var maxV = $(this).val();
       $(this).html(optionsmaxSign);
       $(this).find("option[value='"+maxV+"']").attr("selected",true);
   })
}

$("#exhibitionList").delegate("select[name = 'exruleType']","change",function () {
    var exruleType = $(this).val();
    var obj = $(this).attr("id");
    var index= obj.lastIndexOf("\-");
    obj=obj.substring(index+1,obj.length);
    // alert($("#allowance-"+obj+"").val());
    // alert(obj);
    var str00 = "<input style=\"width:150px;\" value='"+0+"' myvalue='"+0+"' name='allowance'  type='text'>";
    var str01 = "<input style=\"width:150px;\" value='FYC百位取整' myvalue='"+0+"'  name='allowance' disabled='disabled'>";
    var str02 = "<input style=\"width:75px;\" value='FYC' disabled='disabled'>x<input style=\"width:75px;\"value='"+0+"' myvalue='"+0+"' name='allowance' type='text'>%";
    var str03 = "<input style=\"width:55px;\" value='"+0+"' name='allowance' type='text'>额外增加<input  style=\"width:50px;\"  name='extraAllowance' value='"+0+"' type='text'>";
    var ftr3 = "<input style=\"width:140px;\" name='minValue' value='"+0+"' type='text' ><select style=\"width:40px;\" value='' name ='optionsminSign'><option value='0'> < </option><option value='1'> <= </option></select>FYC<select value='' style=\"width:40px;\" name ='optionsmaxSign'><option value='0'> < </option><option value='1'> <= </option></select><input name='maxValue' style=\"width:50px;\" value='"+0+"' type='text'><span>每增加<input style=\"width:35px;\" name='step' type='text' value='"+0+"'/>元</span>";
    var ftr = "<input  style=\"width:140px;\" name='minValue' value='"+0+"' type='text' ><select value='' style=\"width:40px;\" name ='optionsminSign'><option value='0'> < </option><option value='1'> <= </option></select>FYC<select  value='' style=\"width:40px;\" name ='optionsmaxSign'><option value='0'> < </option><option value='1'> <= </option></select><input name='maxValue' style=\"width:120px;\" value='' type='text'>";
    if("00"==exruleType){
        $("#tda"+obj+"").html(str00);
        $("#tdf"+obj+"").html(ftr);
    }else if("01"==exruleType){
        $("#tda"+obj+"").html(str01);
        $("#tdf"+obj+"").html(ftr);
    }else if("02"==exruleType){
        $("#tda"+obj+"").html(str02);
        $("#tdf"+obj+"").html(ftr);
    }else if("03"==exruleType){
        $("#tda"+obj+"").html(str03);
        $("#tdf"+obj+"").html(ftr3);
    }

});

function viewCalEx(orgid) {
    $("#exhibitionList tr[class='ruleTr']").remove();
    $.ajax({
        url:'/calExhibitionAllowance/showCalExhibition',
        dataType:'json',
        type:"get",
        data:{orgId:orgid},
        success:function (data) {
            if(data.messageCode=='200'){
                $.each(data.rows, function(i, n) {
                      var tbBody = "";
                      var exhibitionId = n.id;
                      var ruleType = n.ruleType;
                      var minValue = n.minValue;
                      var maxValue = n.maxValue;
                      var minSign = n.minSign;
                      var maxSign = n.maxSign;
                      var allowance = n.allowance;
                      var step = n.step;
                      if("0"==n.minSign){
                          minSign = "<" ;
                      }else if("1"==n.minSign){
                          minSign = "<=" ;
                      }
                      if("0"==n.maxSign){
                          maxSign = "<" ;
                      }else if("1"==n.maxSign){
                          maxSign = "<=" ;
                      }
                      tbBody+="<tr class='ruleTr'><td>"+"<input value='总价值佣金FYC(元)' style='width:200px;' disabled='disabled'/>"+" </td>"
                      if("00" == n.ruleType) {
                          ruleType ='区间设定值';
                          tbBody+="<td><select name='exruleType' id='exruleType-"+i+"' class='exruleType' disabled='disabled' style=\"width:200px;\">" +
                              "<option value='"+n.ruleType+"'>"+ruleType+"</option></select></td><td id='tdf"+i+"'>" +
                              "<input name='minValue' value='"+minValue+"' disabled='disabled' type='text' style=\"width:140px;\">" +
                              "<select value='' style='width:40px;' name ='optionsminSign' disabled='disabled'><option value='"+n.minSign+"'>"+minSign+"</option></select>FYC" +
                              "<select  value='' style='width:40px;' name ='optionsmaxSign' disabled='disabled'><option value='"+n.maxSign+"'>"+maxSign+"</option></select>" +
                              "<input style=\"width:120px;\" value='"+maxValue+"' name='maxValue' disabled='disabled' type='text' > </td><td id='tda"+i+"'>" +
                              "<input value='"+allowance+"' myvalue='"+allowance+"' id='allowance-"+i+"' name='allowance' disabled='disabled' type='text' style=\"width:150px;\"></td>"
                              +"<td style='display:none' ><input   name='exhibitionId'  value="+exhibitionId+" id='exhibitionId'></td> " +
                              "<td style='display:none' ><input   name='salesOrgId'  value="+$('#show_salesOrgId').val()+"></td> " +
                              "<td style='display:none' ><input   name='isDefaultCal'  value="+$('#show_isDefaultCal').val()+"></td> ";
                      }else if("01" == n.ruleType) {
                          ruleType ='FYC百位取整';
                          tbBody+="<td><select name='exruleType' id='exruleType-"+i+"' class='exruleType' value='' disabled='disabled' style=\"width:200px;\">" +
                              "<option value='"+n.ruleType+"'>"+ruleType+"</option></select></td><td id='tdf"+i+"'>" +
                              "<input name='minValue' style=\"width:140px;\" value='"+minValue+"' disabled='disabled' type='text'>" +
                              "<select value='' style='width:40px;'name ='optionsminSign' disabled='disabled'><option value='"+n.minSign+"'>"+minSign+"</option></select>FYC" +
                              "<select  value='' style='width:40px;'name ='optionsmaxSign' disabled='disabled'><option value='"+n.maxSign+"'>"+maxSign+"</option></select>" +
                              "<input style=\"width:120px;\" name='maxValue' value='"+maxValue+"' disabled='disabled' type='text'> </td><td id='tda"+i+"'>" +
                              "<input value='FYC百位取整' myvalue='"+0+"' id='allowance-"+i+"' name='allowance' disabled='disabled'  style=\"width:150px;\"></td>"
                              +"<td style='display:none' ><input   name='exhibitionId'  value="+exhibitionId+" id='exhibitionId'></td> " +
                              "<td style='display:none' ><input   name='salesOrgId'  value="+$('#show_salesOrgId').val()+"></td> " +
                              "<td style='display:none' ><input   name='isDefaultCal'  value="+$('#show_isDefaultCal').val()+"></td> ";
                      }else if("02" == n.ruleType) {
                          ruleType ='FYC乘以比例参数';
                          allowance =  accMul(allowance,100);
                          tbBody+="<td><select name='exruleType' id='exruleType-"+i+"' class='exruleType' value='' disabled='disabled' style=\"width:200px;\">" +
                              "<option value='"+n.ruleType+"'>"+ruleType+"</option></select></td><td id='tdf"+i+"'>" +
                              "<input name='minValue' style=\"width:140px;\" value='"+minValue+"' disabled='disabled' type='text' >" +
                              "<select value='' style='width:40px;'name ='optionsminSign' disabled='disabled'><option value='"+n.minSign+"'>"+minSign+"</option></select>FYC" +
                              "<select  value=''  style='width:40px;'name ='optionsmaxSign' disabled='disabled'><option value='"+n.maxSign+"'>"+maxSign+"</option></select>" +
                              "<input style=\"width:120px;\" name='maxValue' value='"+maxValue+"' disabled='disabled' type='text'> </td><td id='tda"+i+"'>" +
                              "<input style=\"width:75px;\" value='FYC' disabled='disabled'>x" +
                              "<input style=\"width:75px;\"value='"+allowance+"' myvalue='"+allowance+"' id='allowance-"+i+"' name='allowance' disabled='disabled' type='text'>%</td>"
                              +"<td style='display:none' ><input   name='exhibitionId'  value="+exhibitionId+" id='exhibitionId'></td> " +
                              "<td style='display:none' ><input   name='salesOrgId'  value="+$('#show_salesOrgId').val()+"></td> " +
                              "<td style='display:none' ><input   name='isDefaultCal'  value="+$('#show_isDefaultCal').val()+"></td> ";
                      }else if("03" == n.ruleType) {
                          ruleType ='有额外的递增';
                          tbBody+="<td><select name='exruleType' id='exruleType-"+i+"' class='exruleType' value='' disabled='disabled' style=\"width:200px;\">" +
                              "<option value='"+n.ruleType+"'>"+ruleType+"</option></select></td><td id='tdf"+i+"'>" +
                              "<input name='minValue' style=\"width:140px;\" value='"+minValue+"' disabled='disabled' type='text' >" +
                              "<select value='' style='width:40px;' name ='optionsminSign' disabled='disabled'><option value='"+n.minSign+"'>"+minSign+"</option></select>FYC" +
                              "<select value='' style='width:40px;' name ='optionsmaxSign' disabled='disabled'><option value='"+n.maxSign+"'>"+maxSign+"</option></select>" +
                              "<input style=\"width:50px;\" name='maxValue' value='"+maxValue+"' disabled='disabled' type='text'>每增加" +
                              "<input name='step' style=\"width:35px;\"  type='text' value='"+step+"' disabled='disabled'/>元</td><td id='tda"+i+"'>" +
                              "<input style=\"width:55px;\" value='"+allowance+"' myvalue='"+allowance+"' disabled='disabled' id='allowance-"+i+"' name='allowance' type='text' style=\"width:75px;\">额外增加" +
                              "<input  style=\"width:40px;\"  name='extraAllowance' value='"+n.extraAllowance+"' disabled='disabled' type='text' ></td>"
                          +"<td style='display:none' ><input   name='exhibitionId'  value="+exhibitionId+" id='exhibitionId'></td> " +
                          "<td style='display:none' ><input   name='salesOrgId'  value="+$('#show_salesOrgId').val()+"></td> " +
                          "<td style='display:none' ><input   name='isDefaultCal'  value="+$('#show_isDefaultCal').val()+"></td> ";
                      }
                      tbBody+="<td><a href='#' rel='external nofollow' class='viewVersion' ><input type='button' name='exDelete' value='删除' hidden='true' onclick='deleteById(\""+exhibitionId+ "\",\""+$('#show_salesOrgId').val()+"\",\""+$('#show_calOrgId').val()+"\",\""+$('#show_isDefaultCal').val()+"\");'></a></td></tr>";
                      $("#exhibitionList").append(tbBody);
                 });
            }
        },
    });
}


function addEx() {
   var index =$("#exhibitionList input[name='salesOrgId']").length;
   var xx = paramTypeMap[0].ruleType;
   var str = xx.split(",");
   var tbbody ="";
   if(str.length==1){
        tbbody =  addSizeOne(tbbody);
   }else{
       tbbody =  addSize(tbbody);
   }
   $("#exhibitionList").append(tbbody);
}

function addSize(tbbody) {
   var index =$("#exhibitionList input[name='salesOrgId']").length;
   var xx = paramTypeMap[0].ruleType;
   var str = xx.split(",");
    tbbody+="<tr class='ruleTr'><td>"+"<input value='总价值佣金FYC(元)'  disabled='disabled' style=\"width:220px;\"/>"+" </td>";
   tbbody+="<td><select name='exruleType' id='exruleType-"+index+"' class='exruleTypeAdd' style=\"width:220px;\">";
   for(var i=0;i<str.length;i++){
       tbbody+="<option value='"+RULETYPE[str[i]].dictCode+"'> "+RULETYPE[str[i]].dictName+" </option>";
   }
   tbbody +="</select></td>" +
       "<td id='tdf"+index+"'><input name='minValue' style=\"width:70px;\" value='' type='text' >" +
       "<select value='' name ='optionsminSign' style='width:50px;'><option value='0'> < </option><option value='1'> <= </option></select>FYC" +
       "<select  value='' name ='optionsmaxSign'style='width:50px;'><option value='0'> < </option><option value='1'> <= </option></select>" +
       "<input style=\"width:150px;\" value='' name='maxValue' type='text'></td>";
   tbbody +="<td id='tda"+index+"''><input value='' myvalue='"+0+"' name='allowance' type='text'></td>" +
          "<td style='display:none' ><input   name='salesOrgId'  value="+$('#show_salesOrgId').val()+"></td> " +
       "<td style='display:none' ><input   name='isDefaultCal'  value="+$('#show_isDefaultCal').val()+"></td> "+
       "</tr>";
   return tbbody;
}

function addSizeOne(tbbody) {
     var index =$("#exhibitionList input[name='salesOrgId']").length;
     var xx = paramTypeMap[0].ruleType;
     var str = xx.split(",");
    tbbody+="<tr class='ruleTr'><td>"+"<input value='总价值佣金FYC(元)'  disabled='disabled' style=\"width:220px;\"/>"+" </td>";
    tbbody+="<td><select name='exruleType' id='exruleType-"+index+"' class='exruleType' style=\"width:220px;\">";
    var html = '';
    for(var i=0;i<str.length;i++){
       tbbody += "<option value='"+RULETYPE[str[i]].dictCode+"'> "+RULETYPE[str[i]].dictName+" </option>"
    }
    tbbody +="</select></td>";
    if('00'==RULETYPE[str[0]].dictCode){
        tbbody += "<td id='tdf"+index+"'><input name='minValue' value='' type='text' style=\"width:140px;\">" +
                              "<select value='' style='width:40px;' name ='optionsminSign'><option value='0'> < </option><option value='1'> <= </option></select>FYC" +
                              "<select  value='' style='width:40px;' name ='optionsmaxSign'><option value='0'> < </option><option value='1'> <= </option></select>" +
                              "<input style=\"width:120px;\" value='' name='maxValue' type='text' > </td><td id='tda"+i+"'>" +
                              "<input value='' myvalue='' name='allowance'  type='text' style=\"width:150px;\"></td>"
                              "<td style='display:none' ><input   name='salesOrgId'  value="+$('#show_salesOrgId').val()+"></td> " +
                              "<td style='display:none' ><input   name='isDefaultCal'  value="+$('#show_isDefaultCal').val()+"></td> ";

    }else if('01'==RULETYPE[str[0]].dictCode){
        tbbody += "<td id='tdf"+index+"'><input name='minValue' value=''  type='text' style=\"width:140px;\">" +
                              "<select value='' style='width:40px;' name ='optionsminSign'><option value='0'> < </option><option value='1'> <= </option></select>FYC" +
                              "<select  value='' style='width:40px;' name ='optionsmaxSign'><option value='0'> < </option><option value='1'> <= </option></select>" +
                              "<input style=\"width:120px;\" value='' name='maxValue' type='text' > </td><td id='tda"+i+"'>" +
                              "<input value='FYC百位取整' myvalue='' name='allowance' type='text' disabled='disabled' style=\"width:150px;\"></td>"
                              "<td style='display:none' ><input   name='salesOrgId'  value="+$('#show_salesOrgId').val()+"></td> " +
                              "<td style='display:none' ><input   name='isDefaultCal'  value="+$('#show_isDefaultCal').val()+"></td> ";
    }else if('02'==RULETYPE[str[0]].dictCode){
        tbbody +=  "<td id='tdf"+index+"'><input name='minValue' style=\"width:140px;\" value='' type='text' >" +
                              "<select value='' style='width:40px;'name ='optionsminSign'><option value='0'> < </option><option value='1'> <= </option></select>FYC" +
                              "<select  value=''  style='width:40px;'name ='optionsmaxSign'><option value='0'> < </option><option value='1'> <= </option></select>" +
                              "<input style=\"width:120px;\" name='maxValue' value=''  type='text'> </td><td id='tda"+index+"'>" +
                              "<input style=\"width:75px;\" disabled='disabled' value='FYC'>x" +
                              "<input style=\"width:75px;\"value='' myvalue='' id='' name='allowance' type='text'>%</td>"
                              "<td style='display:none' ><input   name='salesOrgId'  value="+$('#show_salesOrgId').val()+"></td> " +
                              "<td style='display:none' ><input   name='isDefaultCal'  value="+$('#show_isDefaultCal').val()+"></td> ";
    }else if('03'==RULETYPE[str[0]].dictCode){
         tbbody += "<td id='tdf"+index+"'><input name='minValue' style=\"width:140px;\" value='' type='text' >" +
                              "<select value='' style='width:40px;' name ='optionsminSign' ><option value='0'> < </option><option value='1'> <= </option></select>FYC" +
                              "<select value='' style='width:40px;' name ='optionsmaxSign' ><option value='0'> < </option><option value='1'> <= </option></select>" +
                              "<input style=\"width:50px;\" name='maxValue' value=''  type='text'>每增加" +
                              "<input name='step' style=\"width:35px;\"  type='text' value=''/>元</td><td id='tda"+index+"'>" +
                              "<input style=\"width:55px;\" value='' myvalue=''  id='' name='allowance' type='text' style=\"width:75px;\">额外增加" +
                              "<input  style=\"width:40px;\"  name='extraAllowance' value=''  type='text' ></td>"
                          "<td style='display:none' ><input   name='salesOrgId'  value="+$('#show_salesOrgId').val()+"></td> " +
                          "<td style='display:none' ><input   name='isDefaultCal'  value="+$('#show_isDefaultCal').val()+"></td> ";
    }
    return tbbody;
}


/** 表单序列化成json方法  */
function form2Json() {
    var paramArray = $("#exhibitionList tr[class='ruleTr']");
    /*请求参数转json对象*/
    var paramJsonArr =[]
    $(paramArray).each(function(){
        /** 组装规则参数*/
        var ruleType = $(this).find("select[name='exruleType']").val();
        var minValue = $(this).find("input[name='minValue']").val();
        var minSign = $(this).find("select[name='optionsminSign']").val();
        var maxSign = $(this).find("select[name='optionsmaxSign']").val();
        var maxValue = $(this).find("input[name='maxValue']").val();
        var allowance = $(this).find("input[name='allowance']").val();
        var step = $(this).find("input[name='step']").val();
        var extraAllowance = $(this).find("input[name='extraAllowance']").val();
        var exhibitionId = $(this).find("input[name='exhibitionId']").val();
        if('FYC百位取整'==allowance){
            allowance = '0';
        }
        var jsonObj={};
        jsonObj['orgId'] = $('#show_salesOrgId').val();

        jsonObj['ruleType'] = ruleType;
        jsonObj['minValue'] = minValue;
        jsonObj['minSign'] = minSign;
        jsonObj['maxSign'] = maxSign;
        jsonObj['maxValue'] = maxValue;
        jsonObj['allowance'] = allowance;
        jsonObj['step'] = step;
        jsonObj['extraAllowance'] = extraAllowance;
        jsonObj['id'] = exhibitionId;

        paramJsonArr.push(jsonObj);
    });
    console.log(paramJsonArr);
    var fromJSon = {};
    fromJSon['list'] = paramJsonArr;
    // json对象再转换成json字符串
    return fromJSon;
}

function commintYz(formFlag) {
    var paramArray = $("#exhibitionList tr[class='ruleTr']");
    /*请求参数转json对象*/
    var paramJsonArr =[]
    $(paramArray).each(function(){
        /** 组装规则参数*/
        var ruleType = $(this).find("select[name='exruleType']").val();
        var minValue = $(this).find("input[name='minValue']").val();
        // var minSign = $(this).find("select[name='optionsminSign']").val();
        // var maxSign = $(this).find("select[name='optionsmaxSign']").val();
        var maxValue = $(this).find("input[name='maxValue']").val();
        var allowance = $(this).find("input[name='allowance']").val();
        var step = $(this).find("input[name='step']").val();
        var extraAllowance = $(this).find("input[name='extraAllowance']").val();
        // var exhibitionId = $(this).find("input[name='exhibitionId']").val();
        var reg = /(^[1-9]([0-9]*)$|^[0-9]$)/;
        var yxreg =/^(0|[1-9][0-9]*)+(\.\d{0,2})?$/;
        if(''==minValue){
                $.alert({
                    title: '提示信息！',
                    content: '请输入区间最小值！',
                    type: 'red'
                });
                formFlag = false;
                return false;
        }
        if(''==maxValue){
                $.alert({
                    title: '提示信息！',
                    content: '请输入区间最大值！',
                    type: 'red'
                });
                formFlag = false;
                return false;
        }
        if(''==allowance){
                $.alert({
                    title: '提示信息！',
                    content: '请输入展业津贴！',
                    type: 'red'
                });
                formFlag = false;
                return false;
        }

        if ('01'!=ruleType){
            if(!reg.test(minValue)||!reg.test(maxValue)){
                 $.alert({
                        title: '提示信息！',
                        content: '请输入正整数！',
                        type: 'red'
                    });
                    formFlag = false;
                    return false;
            }
            if(!yxreg.test(allowance)){
                     $.alert({
                            title: '提示信息！',
                            content: '请输入有效金额，小数点后保留俩位！',
                            type: 'red'
                        });
                        formFlag = false;
                        return false;
            }
        }
        if('03'==ruleType){
                if(''==step){
                        $.alert({
                            title: '提示信息！',
                            content: '请输入每增加金额！',
                            type: 'red'
                        });
                        formFlag = false;
                        return false;
                }
                if(''==extraAllowance){
                        $.alert({
                            title: '提示信息！',
                            content: '请输入额外增加金额！',
                            type: 'red'
                        });
                        formFlag = false;
                        return false;
                }
                if(!reg.test(step)||!reg.test(extraAllowance)){
                         $.alert({
                                title: '提示信息！',
                                content: '请输入正整数！',
                                type: 'red'
                            });
                            formFlag = false;
                            return false;
                }
        }
    });
    console.log(formFlag);
    return formFlag;
}

function saveEx() {
    var formFlag =true;
    var formJson = form2Json(formFlag);
    formJson['salesOrgId'] = $('#show_salesOrgId').val();
    formJson['isDefaultCal'] = $('#show_isDefaultCal').val();
    formJson['showCalOrgId'] = $('#show_calOrgId').val();
    var formFlag = commintYz(formFlag);
    // alert(formFlag);
    if(formFlag){
        $.ajax({
        url:'/calExhibitionAllowance/saveCalPromote',
        type:'post',
        dataType:'json',
        contentType: "application/json",
        data:JSON.stringify(formJson),
        success:function (data) {
         if(data.messageCode == "200"){
                    $.alert({
                        title: '提示信息！',
                        content: '添加修改成功!',
                        type: 'blue'
                    });
                    $("#show_isDefaultCal").val("1");
                    teokAll($("#show_salesOrgId").val(),$("#name_salesOrgId").val(),"1",$("#show_calOrgId").val(),"1");
                    showcalEx();
                }else{
                    $.alert({
                        title: '提示信息！',
                        content: '添加修改异常！',
                        type: 'red'
                    });
                }
            },
        error:function(){
                $.alert({
                    title: '提示信息！',
                    content: '添加修改异常！',
                    type: 'red'
                });
        }
    });
    }

}

function cancelEx() {
   $("#updateExhibitionBtn").hide();
   showcalEx();
}

function versionCalEx() {
  $("#versionExhibitionList").show();
  $("#exhibitionList").hide();
  $("#closeVersionExhibition").show();
  var isDefaultCal = $("#show_isDefaultCal").val();
  var orgid = "";
  if('0'==isDefaultCal){
      orgid =$("#show_calOrgId").val();
  }else if('1'==isDefaultCal){
      orgid =$("#show_salesOrgId").val();
  }
  viewVersion(orgid);
}

/**
 * 动态拼接版本查询页面
 * @param orgid
 */
function viewVersion(orgid) {
    $.ajax({
        url:'/calExhibitionAllowance/getExCalParamVersion',
        dataType:'json',
        type:"get",
        data:{orgId:orgid},
        success:function (data) {
            if(data.messageCode=='200'){
                $.each(data.rows, function(i, n){
                    var id = n.id;
                    var majorVersion = n.majorVersion;
                    var createTime = n.createTime;
                    var str ="<tr><td><input value='"+majorVersion+"'/></td><td><input value='"+createTime+"'/></td><td><a href=\"#\"  rel=\"external nofollow\" class=\"viewVersion\"><input  type=\"button\" value=\"查看\" onclick=\"viewVersionById('"+id+"');\" ></a></td></tr>";
                });
            }
        },
    });
}

function closeVersionEx() {
    showcalEx();
    $("#closeVersionExhibition").hide();
}


function deleteById(exhibitionId,show_salesOrgId ,show_calOrgId,show_isDefaultCal) {
    var mymessage = confirm("确认删除吗？");
    if (mymessage == true) {
        /* 查询  */
        $.ajax({
            url : "calExhibitionAllowance/updateIncreaseState",
            type : "post",
            dataType : "json",
            data : {
                id : exhibitionId,
                show_salesOrgId:show_salesOrgId ,
                show_calOrgId:show_calOrgId,
                show_isDefaultCal:show_isDefaultCal
            },
            success : function(msg) {
                if (msg.messageCode == "200") {
                    $.alert({
                        title: '提示信息！',
                        content: '删除成功!',
                        type: 'blue'
                    });
                    $("#show_isDefaultCal").val("1");
                    teokAll($("#show_salesOrgId").val(),$("#name_salesOrgId").val(),"1",$("#show_calOrgId").val(),"1");
                    showcalEx();
                } else {
                    $.alert({
                        title: '提示信息！',
                        content: '删除失败!',
                        type: 'red'
                    });
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

var CalExhibition = function () {
    return{
        formValidator:function (){
            $("#addFormExhibitionList").bootstrapValidator({
                 fields:{
                     minValue:{
                         validators:{
                             notEmpty:{
                                    message:"不能为空",
                             },
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


