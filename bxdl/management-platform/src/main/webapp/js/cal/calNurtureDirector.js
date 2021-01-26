

function shownNrtureDirector(){
  $("#saveNrtureDirectorUp").hide();
  var directorIsDefaultCal = $("#show_isDefaultCal").val();
  var directorSalesOrgId = $("#show_salesOrgId").val();
  var directorCalOrgId = $("#show_calOrgId").val();

  if(directorIsDefaultCal == ''){
    $.alert({
      title: '提示信息！',
      content: '请先选择机构!',
      type: 'red'
    });
    return false;
  }
  var nurtureDirector;

  var OrgId;
  if(directorIsDefaultCal == 0){
    OrgId = directorCalOrgId;
  }else {
    OrgId = directorSalesOrgId;
  }

  $.ajax({
    url:'/cal_nurture_director/queryNurtureDirector',
    type:'post',
    dataType:'json',
    data : {
      OrgId:OrgId,
      isDefaultCal:directorIsDefaultCal
    },
    success:function(data){
      $("#tableNrtureDirector tr:not(:first)").empty("");
      nurtureDirector = eval(data.listDirector);
      console.log(nurtureDirector);
      $.each(nurtureDirector, function (key, value) {
        var tbBody = "";
        var directorId = value.id;
        var directorAlgebra = value.generativeAlgebra;
        var finalgenerativeAlgebra = directorAlgebra;
        if(directorAlgebra == 1){
          directorAlgebra = "直接育成奖金率";
        }else {
          directorAlgebra = "第二代育成奖金率";
        }
        var directorFastYear = accMul(value.fastYear,100);
        var directorFollowingYearAndBeyond = accMul(value.followingYearAndBeyond,100);

        tbBody+="<tr>" +
            "<td style=\"display:none\" ><input value='"+finalgenerativeAlgebra+"' id=\"finalgenerativeAlgebra\"  name=\"finalgenerativeAlgebra\" ></td>" +
            "<td style=\"display:none\" ><input value='"+directorSalesOrgId+"' id=\"directorSalesOrgId\"  name=\"directorSalesOrgId\" ></td>" +
            "<td style=\"display:none\" ><input value='"+directorCalOrgId+"' id=\"directorCalOrgId\"  name=\"directorCalOrgId\" ></td>" +
            "<td style=\"display:none\" ><input value='"+directorIsDefaultCal+"' id=\"directorIsDefaultCal\"  name=\"directorIsDefaultCal\" ></td>" +
            "<td style=\"display:none\" ><input value='"+directorId+"' id=\"directorId\"  name=\"directorId\" ></td>" +

            "<td >"+directorAlgebra+"</td>" +
            "<td ><input type='text' id='directorFastYear' name='directorFastYear' value = '"+directorFastYear+"' placeholder='请输入第一年' disabled='disabled' >"+"%"+"</td>" +
            "<td ><input type='text' id='directorFollowingYearAndBeyond' name='directorFollowingYearAndBeyond' value = '"+directorFollowingYearAndBeyond+"' placeholder='请输入第二年及以后' disabled='disabled' >"+"%"+"</td>" +
            "" +
            "</tr>";
        $("#tableNrtureDirector").append(tbBody);

      });
    }
  });

}

/*保存*/
function addNrtureDirector(){
  var state = checkDirector();
  if(!state){
    return false;
  }
  $.ajax({
    url:'cal_nurture_director/add_nurture_director',
    type:'post',
    dataType:'json',
    data:$("#formNrtureDirector").serialize(),
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
      shownNrtureDirector();
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
  $("#shownNrtureDirectorUp").click(function(){
    $("#tableNrtureDirector input[type='text']").attr("disabled",false);
    $("#saveNrtureDirectorUp").show();
  })

  // /*版本查询按钮*/
  // $("#queryVersionNrtureDirector").click(function(){
  //   alert("还没有做~~~~");
  // })

  /*取消按钮*/
  $("#cancelNrtureDirector").click(function(){
    $("#saveNrtureDirectorUp").hide();
    shownNrtureDirector();
  })

});

function checkDirector(){
  var flag = true;
  var reg = /^(0|[1-9][0-9]*)+(\.\d{0,2})?$/;
  $("#tableNrtureDirector input[name='directorFastYear']").each(function () {
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

  $("#tableNrtureDirector input[name='directorFollowingYearAndBeyond']").each(function () {
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
