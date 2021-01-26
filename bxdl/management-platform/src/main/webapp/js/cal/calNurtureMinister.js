

function shownNrtureMinister(){
  $("#saveNrtureMinisterUp").hide();
  var show_isDefaultCal = $("#show_isDefaultCal").val();
  var show_salesOrgId = $("#show_salesOrgId").val();
  var show_calOrgId = $("#show_calOrgId").val();

  if(show_isDefaultCal == ''){

    $.alert({
      title: '提示信息！',
      content: '请先选择机构!',
      type: 'red'
    });

    return false;
  }
  var ministerNurturingBonus;

  var OrgId;
  if(show_isDefaultCal == 0){
    OrgId = show_calOrgId;
  }else {
    OrgId = show_salesOrgId;
  }

  $.ajax({
    url:'/cal_minister_nurturing/queryNurtureMinister',
    type:'post',
    dataType:'json',
    data : {
      OrgId:OrgId,
      isDefaultCal:show_isDefaultCal
    },
    success:function(data){
      $("#tableNrtureMinister tr:not(:first)").empty("");
      ministerNurturingBonus = eval(data.list);
      console.log(ministerNurturingBonus);
      $.each(ministerNurturingBonus, function (key, value) {
        var tbBody = "";
        var isDefaultCalMinister = data.isDefaultCalMinister;
        var id = value.id;
        var orgId = value.orgId;
        var generativeAlgebra = value.generativeAlgebra;
        var show_generativeAlgebra = generativeAlgebra;
        if(generativeAlgebra == 1){
          generativeAlgebra = "直接育成奖金率";
        }else {
          generativeAlgebra = "第二代育成奖金率";
        }
        var fastYear = accMul(value.fastYear,100);
        var followingYearAndBeyond = accMul(value.followingYearAndBeyond,100);

        tbBody+="<tr>" +
            "<td style=\"display:none\" ><input value='"+show_generativeAlgebra+"' id=\"show_generativeAlgebra\"  name=\"show_generativeAlgebra\" ></td>" +
            "<td style=\"display:none\" ><input value='"+show_salesOrgId+"' id=\"show_salesOrgId\"  name=\"show_salesOrgId\" ></td>" +
            "<td style=\"display:none\" ><input value='"+show_calOrgId+"' id=\"show_calOrgId\"  name=\"show_calOrgId\" ></td>" +
            "<td style=\"display:none\" ><input value='"+isDefaultCalMinister+"' id=\"isDefaultCalMinister\"  name=\"isDefaultCalMinister\" ></td>" +
            "<td style=\"display:none\" ><input value='"+id+"' id=\"ministerId\"  name=\"ministerId\" ></td>" +

            "<td >"+generativeAlgebra+"</td>" +
            "<td ><input type='text' id='fastYear' name='fastYear' value = '"+fastYear+"' placeholder='请输入第一年' disabled='disabled'>"+"%"+"</td>" +
            "<td ><input type='text' id='followingYearAndBeyond' name='followingYearAndBeyond' value = '"+followingYearAndBeyond+"' placeholder='请输入第二年及以后' disabled='disabled'>"+"%"+"</td>" +
            "" +
            "</tr>";
        $("#tableNrtureMinister").append(tbBody);

      });
    }
  });

}

/*保存*/
function addNrtureMinister(){
  var state = checkMinister();
  if(!state){
    return false;
  }
  $.ajax({
    url:'cal_minister_nurturing/add_minister_nurturing',
    type:'post',
    dataType:'json',
    data:$("#formNrtureMinister").serialize(),
    success:function(data){
      if(data){
        $.alert({
          title: '提示信息！',
          content: '保存成功!',
          type: 'blue'
        });

      }else{
        $.alert({
          title: '提示信息！',
          content: '保存失败！',
          type: 'red'
        });
      }
      $("#show_isDefaultCal").val("1");
      teokAll($("#show_salesOrgId").val(),$("#name_salesOrgId").val(),"1",$("#show_calOrgId").val(),"1");
      shownNrtureMinister();
      // $("#saveNrtureMinisterUp").hide();
      // $("#tableNrtureMinister input[type='text']").attr("disabled",true);
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



$(document).ready(function() {

  /*修改按钮*/
  $("#shownNrtureMinisterUp").click(function(){
    $("#tableNrtureMinister input[type='text']").attr("disabled",false);
    $("#saveNrtureMinisterUp").show();
  })

  // /*版本查询按钮*/
  // $("#queryVersionNrtureMinister").click(function(){
  //   alert("还没有做~~~~");
  // })

  /*取消按钮*/
  $("#cancelNrtureMinister").click(function(){
    $("#saveNrtureMinisterUp").hide();
    shownNrtureMinister();
  })

});

function checkMinister(){
  var flag = true;
  var reg = /^(0|[1-9][0-9]*)+(\.\d{0,2})?$/;
  $("#tableNrtureMinister input[name='fastYear']").each(function () {
    var parameter = $(this).val();
    if(!reg.test(parameter)){
      flag=false;
      $.alert({
        title: '提示信息！',
        content: '第一年有不正确数值！',
        type: 'red'
      });
      return false ;
    }
  });

  $("#tableNrtureMinister input[name='followingYearAndBeyond']").each(function () {
    var parameter = $(this).val();
    if(!reg.test(parameter)){
      flag=false;
      $.alert({
        title: '提示信息！',
        content: '第二年有不正确数值！',
        type: 'red'
      });
      return false ;
    }
  });
  return flag;
}
