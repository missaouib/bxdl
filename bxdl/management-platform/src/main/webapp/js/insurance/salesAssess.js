$(function () {
    getRankSequenceList("rankSequenceId");
    $("#rankSequenceId").on(
        "change",function(){
            getSalesGrade("salesGradeId");
        }
    );

    //入职职级序列
    setTimeout(function(){
        $("#rankSequenceId option").each(function(){
            if($(this).val()==$("#rankSequenceId").attr("value")){
                $(this).attr("selected", true);
                getSalesGrade("salesGradeId");
                setTimeout(function(){
                    $("#salesGradeId option").each(function(){
                        if($(this).val()==$("#salesGradeId").attr("value")){
                            $(this).attr("selected", true);
                            setSalesAssess();
                        }
                    });
                },100);
            }
        });
    },200);

});


/*保存*/
function saveSalesAssess(){
    var insuranceSalesId = $("#insuranceSalesId").val();
    var salesGradeId = $("#salesGradeId").find("option:selected").val();
    var data ={
        insuranceSalesId: insuranceSalesId,
        salesGradeId: salesGradeId,
    };

    if(5 == salesGradeId){
        data.condition1 = $("#khqkz_condition1").find("option:selected").val();
        data.condition2 = $("#khqkz_condition2").find("option:selected").val();
    }else if(4 == salesGradeId){
        data.condition8 = $("#kz_condition8").find("option:selected").val();
        data.condition2 = $("#kz_condition2").find("option:selected").val();
        data.condition3 = $("#kz_condition3").find("option:selected").val();
    }else if(3 == salesGradeId || 6  == salesGradeId){
        data.condition8 = $("#cz_condition8").find("option:selected").val();
        data.condition2 = $("#cz_condition2").find("option:selected").val();
        data.condition3 = $("#cz_condition3").find("option:selected").val();
        data.condition4 = $("#cz_condition4").find("option:selected").val();
    }else if(2 == salesGradeId || 7  == salesGradeId){
        data.condition5 = $("#bz_condition5").find("option:selected").val();
        data.condition6 = $("#bz_condition6").find("option:selected").val();
        data.condition3 = $("#bz_condition3").find("option:selected").val();
        data.condition7 = $("#bz_condition7").find("option:selected").val();
    }

    if($("#salesAssessId").val() != null && $("#salesAssessId").val() != undefined && $("#salesAssessId").val() != ""){
        var salesGradeName = $("#salesGradeName").val();
        data.salesGradeName = salesGradeName;
        data.salesGradeId = $("#salesGradeId2").val();
        data.id = $("#salesAssessId").val();
        $.ajax({
            url:'insuranceSalesInfo/salesAssess',
            dataType:'json',
            contentType: 'application/json',
            type:'put',
            data:JSON.stringify(data),
            success:function(data){
                if(data){
                    $.alert({
                        title: '提示信息！',
                        content: '保存成功!',
                        type: 'blue'
                    });
                    //commCloseTab('salesAssess:update');
                    windowCloseTab();
                }else{
                    $.alert({
                        title: '提示信息！',
                        content: '保存失败！',
                        type: 'red'
                    });
                }
            },
            error:function (data) {
                $.alert({
                    title: '提示信息！',
                    content: '请求,系统异常',
                    type: 'red'
                });
            }
        });
    }else {
        var salesGradeName = $("#salesGradeId").find("option:selected").text();
        data.salesGradeName = salesGradeName;
        $.ajax({
            url:'insuranceSalesInfo/salesAssess',
            dataType: 'json',
            contentType: 'application/json',
            type: 'POST',
            data:JSON.stringify(data),
            success:function(data){
                if("200" == data.messageCode){
                    $.alert({
                        title: '提示信息！',
                        content: '保存成功!',
                        type: 'blue'
                    });
                    //commCloseTab('salesAssess:add');
                    windowCloseTab();
                }else{
                    $.alert({
                        title: '提示信息！',
                        content: '保存失败！',
                        type: 'red'
                    });
                }
            },
            error:function (data) {
                $.alert({
                    title: '提示信息！',
                    content: '请求,系统异常',
                    type: 'red'
                });
            }
        });
    }
}


/*获取职级序列*/
function getRankSequenceList(selectId){
    $.ajax({
        url:'rankSequence/getRankSequenceList',
        dataType:'json',
        type:'post',
        data:{},
        success:function(data){
            var h = "<option value=''>请选择职级序列</option>";
            if(data){
                var rankSequences = data.rows;
                $.each(rankSequences, function(key, value){
                    h += "<option value='" + value.sequenceId +"'>" + value.sequenceName //下拉框序言的循环数据
                        + "</option>";
                });
                $("#"+selectId).empty();
                $("#"+selectId).append(h);
            }
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

function setSalesAssess() {
    var salesGradeId = $("#salesGradeId").val();

    if($("#salesAssessId").val() != null && $("#salesAssessId").val() != undefined && $("#salesAssessId").val() != ""){
        // 编辑回显
        var condition1 = $("#condition1").val();
        var condition2 = $("#condition2").val();
        var condition3 = $("#condition3").val();
        var condition4 = $("#condition4").val();
        var condition5 = $("#condition5").val();
        var condition6 = $("#condition6").val();
        var condition7 = $("#condition7").val();
        var condition8 = $("#condition8").val();

        if(5 == salesGradeId){
            $("#khqkz_condition1").find('option').each(function () {
                if ($(this).val() == condition1) {
                    $(this).attr("selected", true);
                }
            });
            $("#khqkz_condition2").find('option').each(function () {
                if ($(this).val() == condition2) {
                    $(this).attr("selected", true);
                }
            });
            $("#khqkz").show();
        }else if(4 == salesGradeId){
            $("#kz_condition8").find('option').each(function () {
                if ($(this).val() == condition8) {
                    $(this).attr("selected", true);
                }
            });
            $("#kz_condition2").find('option').each(function () {
                if ($(this).val() == condition2) {
                    $(this).attr("selected", true);
                }
            });
            $("#kz_condition3").find('option').each(function () {
                if ($(this).val() == condition3) {
                    $(this).attr("selected", true);
                }
            });
            $("#kz").show();
        }else if(3 == salesGradeId || 6 == salesGradeId){
            $("#cz_condition8").find('option').each(function () {
                if ($(this).val() == condition8) {
                    $(this).attr("selected", true);
                }
            });
            $("#cz_condition2").find('option').each(function () {
                if ($(this).val() == condition2) {
                    $(this).attr("selected", true);
                }
            });
            $("#cz_condition3").find('option').each(function () {
                if ($(this).val() == condition3) {
                    $(this).attr("selected", true);
                }
            });
            $("#cz_condition4").find('option').each(function () {
                if ($(this).val() == condition4) {
                    $(this).attr("selected", true);
                }
            });
            $("#cz").show();
        }else if(2 == salesGradeId || 7 == salesGradeId){
            $("#bz_condition5").find('option').each(function () {
                if ($(this).val() == condition5) {
                    $(this).attr("selected", true);
                }
            });
            $("#bz_condition5").find('option').each(function () {
                if ($(this).val() == condition6) {
                    $(this).attr("selected", true);
                }
            });
            $("#bz_condition3").find('option').each(function () {
                if ($(this).val() == condition3) {
                    $(this).attr("selected", true);
                }
            });
            $("#bz_condition7").find('option').each(function () {
                if ($(this).val() == condition7) {
                    $(this).attr("selected", true);
                }
            });
            $("#bz").show();
        }
    }else {
        // 新增 根据员工职级显示考核项
        if(5 == salesGradeId){
            $("#khqkz").show();
        }else if(4 == salesGradeId){
            $("#kz").show();
        }else if(3 == salesGradeId || 6 == salesGradeId){
            $("#cz").show();
        }else if(2 == salesGradeId || 7 == salesGradeId){
            $("#bz").show();
        }
    }
}

/*获取职级*/
function getSalesGrade(selectId){
    var rankSequenceId = $("#rankSequenceId").val();
    $.ajax({
        url:'salesGrade/getSalesGradeList',
        dataType:'json',
        type:'post',
        data:{rankSequenceId:rankSequenceId},
        success:function(data){
            var h = "<option value=''>请选择职级序列</option>";
            if(data){
                var salesGrades = data.rows;
                $.each(salesGrades, function(key, value){
                    h += "<option value='" + value.salesGradeId +"'>" + value.salesGradeName //下拉框序言的循环数据
                        + "</option>";
                });
                $("#"+selectId).empty();
                $("#"+selectId).append(h);
            }
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
