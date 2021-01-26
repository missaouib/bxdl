var PromotionList = {
    seItem: null		//选中的条目
};

$(function () {
    PromotionList.init();
});
//表格数据展示
PromotionList = function () {
    return {
        init: function () {
            $('#promotion-table').bootstrapTable({
                url: "insProtocolPromotions",
                method: "GET",
                dataType: "json",
                contentType: 'application/x-www-form-urlencoded',
                striped: true,//隔行变色
                cache: false,  //是否使用缓存
                pagination: false, //分页
                sortable: false, //是否启用排序
                singleSelect: false,
                search: false, //显示搜索框
                buttonsAlign: "right", //按钮对齐方式
                showRefresh: false,//是否显示刷新按钮
                sidePagination: "client", //服务端处理分页
                pageSize: 5, //默认每页条数
                pageNumber: 1, //默认分页
                pageList: [5, 10, 20, 50],//分页数
                toolbar: "#toolbar",
                showColumns: false, //显示隐藏列
                uniqueId: "id", //每一行的唯一标识，一般为主键列
                queryParamsType: '',
                queryParams: {protocolId: $("#protocolId").val()},//传递参数（*）
                columns: [{
                    checkbox: true
                }, {
                    field: "id",
                    title: "方案代码",
                    width: "4%",
                    align: "center",
                    valign: "middle",
                    sortable: "true"
                }, {
                    field: "promotionName",
                    title: "方案名称",
                    width: "13%",
                    align: "center",
                    valign: "middle",
                    sortable: "true"
                }, {
                    field: "orgScope",
                    title: "方案区域",
                    width: "24%",
                    align: "center",
                    valign: "middle",
                    sortable: "true"
                }, {
                    field: "productScope",
                    title: "产品范围",
                    width: "24%",
                    align: "center",
                    valign: "middle",
                    sortable: "true"
                }, {
                    field: "startTime",
                    title: "有效起期",
                    width: "11%",
                    align: "center",
                    valign: "middle",
                    sortable: "true"
                }, {
                    field: "endTime",
                    title: "有效止期",
                    width: "11%",
                    align: "center",
                    valign: "middle",
                    sortable: "true"
                }, {
                    field: "state",
                    title: "状态",
                    width: "3%",
                    align: "center",
                    valign: "middle",
                    sortable: "true"
                }, {
                    field: "id",
                    title: "操作",
                    width: "10%",
                    align: "center",
                    valign: "middle",
                    sortable: "true",
                    formatter: function (data, type, row) {
                        if (type.state == "有效") {
                            if($("#isLook").val() == "yes"){
                                return "<div style=\"text-align:left; vertical-align:middel;\" >" +
                                    "<a style='margin-left: 5px;color: #0d8ddb;' href='#' onclick='PromotionList.getPromotion(" + data + ")'>查看</a>" +
                                    "</div>"
                            }else {
                                return "<div style=\"text-align:left; vertical-align:middel;\" >" +
                                    "<a style='margin-left: 5px;color: #0d8ddb;' href='#' onclick='PromotionList.disable(" + data + ")'>失效</a>" +
                                    "<a style='margin-left: 5px;color: #0d8ddb;' href='#' onclick='PromotionList.getPromotion(" + data + ")'>查看</a>" +
                                    "</div>"
                            }
                        } else if (type.state == "失效" || type.state == "无效") {
                            if($("#isLook").val() == "yes"){
                                return "<div style=\"text-align:left; vertical-align:middel;\" >" +
                                    "<a style='margin-left: 5px;color: #0d8ddb;' href='#' onclick='PromotionList.getPromotion(" + data + ")'>查看</a></div>"
                            }else {
                                return "<div style=\"text-align:left; vertical-align:middel;\" >" +
                                    "<a style='margin-left: 5px;color: #0d8ddb;' href='#' onclick='PromotionList.enable(" + data + ")'>生效</a>" +
                                    "<a style='margin-left: 5px;color: #0d8ddb;' href='#' onclick='PromotionList.toModify("+ data+ ")'>配置</a>" +
                                    "<a style='margin-left: 5px;color: #0d8ddb;' href='#' onclick='PromotionList.getPromotion(" + data + ")'>查看</a>" +
                                    "<a style='margin-left: 5px;color: #0d8ddb;' href='#' onclick='PromotionList.delete(" + data + ")'>删除</a></div>"
                            }
                        }
                    }
                }],
                formatLoadingMessage: function () {
                    return "请稍等，正在加载中...";
                },
                formatNoMatches: function () {
                    return '无符合条件的记录';
                }
            });

        },

        disable: function (id) {
            $.ajax({
                url: 'insProtocolPromotion/state',
                dataType: 'json',
                type: 'PUT',
                data: {
                    id: id,
                    state: 0
                },
                success: function (data) {
                    if (data.messageCode == "200") {
                        $("#promotion-table").bootstrapTable('refresh');
                    } else {
                        $.alert({
                            title: '提示信息！',
                            content: '操作失败!',
                            type: 'red'
                        });
                    }

                }
            });
        },

        enable: function (id) {
            $.ajax({
                url: 'insProtocolPromotion/state',
                dataType: 'json',
                type: 'PUT',
                data: {
                    id: id,
                    state: 1
                },
                success: function (data) {
                    if (data.messageCode == "200") {
                        $("#promotion-table").bootstrapTable('refresh');
                    } else {
                        $.alert({
                            title: '提示信息！',
                            content: '操作失败!',
                            type: 'red'
                        });
                    }

                }
            });
        },

        delete: function (id) {
            $.ajax({
                url: 'insProtocolPromotion/' + id,
                dataType: 'json',
                type: 'DELETE',
                data: {},
                success: function (data) {
                    if (data.messageCode == "200") {
                        $("#promotion-table").bootstrapTable('refresh');
                    } else {
                        $.alert({
                            title: '提示信息！',
                            content: '操作失败!',
                            type: 'red'
                        });
                    }

                }
            });
        },

        toModify: function (id) {
            var idStr = id+"&protocolId="+$("#protocolId").val();
            createAddProcessTab('protocolPromotion:update', idStr);
        },


        getPromotion: function (promotionId) {
            $.ajax({
                url: 'insProtocolPromotion/'+promotionId,
                dataType: 'json',
                type: 'GET',
                data: {},
                success: function (data) {
                    $("#promotionName").text(data.promotionName);
                    $("#startTime").text(data.startTimeStr);
                    $("#endTime").text(data.endTimeStr);
                    if("0" == data.orgScopeStr){
                        $("#orgScope").text("全部");
                    }else {
                        $("#orgScope").text(data.orgScopeStr);
                    }

                    if("0" == data.productScopeStr){
                        $("#productScope").text("全部");
                    }else {
                        $("#productScope").text(data.productScopeStr);
                    }

                    if ("0" == data.rateType) {
                        $("#rateType").text("规保");
                    } else if ("1" == data.rateType) {
                        $("#rateType").text("标保");
                    }
                    if ("0" == data.settlementCycle) {
                        $("#settlementCycle").text("月");
                        $("#settlementCycleSpan").text("结算周期：月");
                    } else if ("1" == data.settlementCycle) {
                        $("#settlementCycle").text("季度");
                        $("#settlementCycleSpan").text("结算周期：季度");
                    } else if ("2" == data.settlementCycle) {
                        $("#settlementCycle").text("半年");
                        $("#settlementCycleSpan").text("结算周期：半年");
                    } else if ("3" == data.settlementCycle) {
                        $("#settlementCycle").text("年");
                        $("#settlementCycleSpan").text("结算周期：年");
                    }

                    var settlementHtml = "";

                    jQuery.each(data.settlementRate, function (i, item) {
                        var allowanceFormula = item.allowanceFormula;
                        var arr = [];
                        settlementHtml = settlementHtml + "<tr>\n" +
                                                            "<td style=\"width:15%;\">\n";
                        var rateType = "";
                        if ("0" == item.rateType) {
                            settlementHtml = settlementHtml + "<div>规保</div>\n";
                            arr = allowanceFormula.split("规保");
                            rateType = "规保";
                        } else if ("1" == item.rateType) {
                            settlementHtml = settlementHtml + "<div>标保</div>\n";
                            arr = allowanceFormula.split("标保");
                            rateType = "标保";
                        }



                        settlementHtml = settlementHtml + "</td>\n" +
                                                            "<td style=\"width:60%;\">\n";
                        if (0 == item.cycle) {
                            settlementHtml = settlementHtml + "<div style='display:inline'>月</div>\n";
                        } else if ("1" == item.cycle) {
                            settlementHtml = settlementHtml + "<div style='display:inline'>季度</div>\n";
                        } else if ("2" == item.cycle) {
                            settlementHtml = settlementHtml + "<div style='display:inline'>半年</div>\n";
                        } else if ("3" == item.cycle) {
                            settlementHtml = settlementHtml + "<div style='display:inline'>年</div>\n";
                        }
                        jQuery.each(arr, function (j, info) {
                            var value = info.replace('>', '').replace('=', '').replace('<', '');
                            if (0 == j) {
                                var bigValue = " <div style=\"width:20%;margin-left:0.5%;height:33px;display:inline;\">" + value + "元</div>\n";
                                var bigSignHtml = " <div style=\"width:15%;margin-left:0.5%;display:inline;\">\n";
                                if (info.indexOf(">=") != -1) {
                                    bigSignHtml = bigSignHtml + ">=";
                                } else if (info.indexOf(">") != -1) {
                                    bigSignHtml = bigSignHtml + ">";
                                } else if (info.indexOf("<=") != -1) {
                                    bigSignHtml = bigSignHtml + "<=";
                                } else if (info.indexOf("<") != -1) {
                                    bigSignHtml = bigSignHtml + "<";
                                }
                                bigSignHtml = bigSignHtml + "</div>\n";
                                settlementHtml = settlementHtml + bigValue + bigSignHtml;
                            } else if (1 == j) {
                                var smallValue = " <div style=\"width:20%;margin-left:0.5%;height:33px;display:inline;\">" + value + "元</div>\n";
                                var smallSignHtml = " <div style=\"width:15%;margin-left:1.0%;display:inline;\" >\n";
                                if (info.indexOf(">=") != -1) {
                                    smallSignHtml = smallSignHtml + ">=";
                                } else if (info.indexOf(">") != -1) {
                                    smallSignHtml = smallSignHtml + ">";
                                } else if (info.indexOf("<=") != -1) {
                                    smallSignHtml = smallSignHtml + "<=";
                                } else if (info.indexOf("<") != -1) {
                                    smallSignHtml = smallSignHtml + "<";
                                }
                                smallSignHtml = smallSignHtml + "</div>\n";
                                settlementHtml = settlementHtml + " " + rateType + " " + smallSignHtml + smallValue;
                            }
                        });
                        settlementHtml = settlementHtml + "</td>\n";
                        settlementHtml = settlementHtml +
                            "  <td style=\"width:20%;\">\n" +
                            "       <div style=\"width:25%;margin-left:0.5%;height:33px;display:inline;\" >" + item.rate + "</div>\n" +
                            "  </td>\n";
                        settlementHtml = settlementHtml + "</tr>";
                    });

                    $("#settlementCycleTable  tr").eq(0).nextAll().remove();
                    $("#settlementCycleTable").append(settlementHtml);
                    $("#settlementCycleTable").show();


                    if(null != data.commonCycle){
                        if ("1" == data.commonCycle) {
                            $("#commonCycleId").text("季度算");
                            $("#commonCycleSpan").text("通算方式：季度算");
                        }
                        if ("2" == data.commonCycle) {
                            $("#commonCycleId").text("半年算");
                            $("#commonCycleSpan").text("通算方式：半年算");
                        }
                        if ("3" == data.commonCycle) {
                            $("#commonCycleId").text("年通算");
                            $("#commonCycleSpan").text("通算方式：年通算");
                        }

                        var commonCycleHtml = "";
                        jQuery.each(data.commonRate, function (i, item) {
                            console.log(item);
                            var allowanceFormula = item.allowanceFormula;
                            var arr = [];
                            commonCycleHtml = commonCycleHtml + "<tr>\n" +
                                "<td style=\"width:15%;\">\n";

                            var rateType = "";
                            if ("0" == item.rateType) {
                                commonCycleHtml = commonCycleHtml + "<div>规保</div>\n";
                                arr = allowanceFormula.split("规保");
                                rateType = "规保";
                            }else if ("1" == item.rateType) {
                                commonCycleHtml = commonCycleHtml + "<div>标保</div>\n";
                                arr = allowanceFormula.split("标保");
                                rateType = "标保";
                            }
                            commonCycleHtml = commonCycleHtml + "</td>\n" +
                                "                        <td style=\"width:60%;\">\n";
                            if ("1" == item.cycle) {
                                commonCycleHtml = commonCycleHtml + "<div style=\"display:inline;\">季度</div>\n";
                            } else if ("2" == item.cycle) {
                                commonCycleHtml = commonCycleHtml + "<div style=\"display:inline;\">半年</div>\n";
                            } else if ("3" == item.cycle) {
                                commonCycleHtml = commonCycleHtml + "<div style=\"display:inline;\">年</div>\n";
                            }
                            commonCycleHtml = commonCycleHtml + "</div>\n";

                            jQuery.each(arr, function (j, info) {
                                var value = info.replace('>', '').replace('=', '').replace('<', '');
                                if (0 == j) {
                                    var bigValue = " <div style=\"width:20%;margin-left:0.5%;height:33px;display:inline;\">" + value + "元</div>\n";
                                    var bigSignHtml = " <div style=\"width:15%;margin-left:0.5%;display:inline;\">\n";
                                    if (info.indexOf(">=") != -1) {
                                        bigSignHtml = bigSignHtml + ">=";
                                    } else if (info.indexOf(">") != -1) {
                                        bigSignHtml = bigSignHtml + ">";
                                    } else if (info.indexOf("<=") != -1) {
                                        bigSignHtml = bigSignHtml + "<=";
                                    } else if (info.indexOf("<") != -1) {
                                        bigSignHtml = bigSignHtml + "<";
                                    }
                                    bigSignHtml = bigSignHtml + "</div>\n";
                                    commonCycleHtml = commonCycleHtml + bigValue + bigSignHtml;
                                } else if (1 == j) {
                                    var smallValue = " <div style=\"width:20%;margin-left:0.5%;height:33px;display:inline;\">" + value + "元</div>\n";
                                    var smallSignHtml = " <div style=\"width:15%;margin-left:1.0%;display:inline;\">\n";
                                    if (info.indexOf(">=") != -1) {
                                        smallSignHtml = smallSignHtml + ">=";
                                    } else if (info.indexOf(">") != -1) {
                                        smallSignHtml = smallSignHtml + ">";
                                    } else if (info.indexOf("<=") != -1) {
                                        smallSignHtml = smallSignHtml + "<=";
                                    } else if (info.indexOf("<") != -1) {
                                        smallSignHtml = smallSignHtml + "<";
                                    }
                                    smallSignHtml = smallSignHtml + "</div>\n";
                                    commonCycleHtml = commonCycleHtml + " " + rateType + "" + smallSignHtml + smallValue;
                                }
                            });

                            commonCycleHtml = commonCycleHtml + "</td>\n";
                            commonCycleHtml = commonCycleHtml +
                                "  <td style=\"width:25%;\">\n" +
                                "       <div style=\"width:auto;height:33px;display:inline;\">"+item.rate+"</div>\n" +
                                "  </td>\n";
                            commonCycleHtml = commonCycleHtml + "</tr>\n";
                        });

                        $("#commonCycleTable  tr").eq(0).nextAll().remove();
                        $("#commonCycleTable").append(commonCycleHtml);
                        $("#commonCycleDiv").css("display","block");
                    }

                    $("#promotionModal").modal('show');
                }
            });
        },

        //关闭模态框
        close: function () {
            $("#promotionModal").modal('hide');
        },

        // 关闭业务推动
        closeTab: function () {
            windowCloseTab();
        }
    }
}();

//1.打开协议信息
function openAddProtocolBasic(){
    commCloseTab('protocolPromotion:list');
    createAddProcessTab('lifeProtocolBasic:add',$('#protocolId').val());
}

//2.1打开手续费费
function openAddProtocolService(){
    if(checkNewProtocol()){
        commCloseTab('protocolPromotion:list');
        createAddProcessTab('lifeProtocolService:add',$('#protocolId').val());
    }else{
        $.alert({
            title: '提示信息！',
            content: '请先保存产品基本信息!',
            type: 'red'
        });
    }
}
//2.2 打开折标率配置
function openAddProtocolStandard(){
    if(checkNewProtocol()){
        commCloseTab('protocolPromotion:list');
        createAddProcessTab('lifeProtocolStandard:add',$('#protocolId').val());
    }else{
        $.alert({
            title: '提示信息！',
            content: '请先保存产品基本信息!',
            type: 'red'
        });
    }
}
//2.3 打开内部折标率配置
function openAddProtocolInsideStandard(){
    if(checkNewProtocol()){
        commCloseTab('protocolPromotion:list');
        createAddProcessTab('lifeProtocolInsideStandard:add',$('#protocolId').val());
    }else{
        $.alert({
            title: '提示信息！',
            content: '请先保存产品基本信息!',
            type: 'red'
        });
    }
}
//3.打开续年度服务津贴
function openAddProtocolSubsidy(){
    if(checkNewProtocol()){
        commCloseTab('protocolPromotion:list');
        createAddProcessTab('lifeProtocolSubsidy:add',$('#protocolId').val());
    }else{
        $.alert({
            title: '提示信息！',
            content: '请先保存产品基本信息!',
            type: 'red'
        });
    }
}

//6.打开业务推动
function openProtocolPromotion(){
    if(checkNewProtocol()){
        // commCloseTab('protocolPromotion:list');
        // createAddProcessTab('protocolPromotion:list',$('#protocolId').val());
    }else{
        $.alert({
            title: '提示信息！',
            content: '请先保存产品基本信息!',
            type: 'red'
        });
    }
}
//获取最新添加的协议id
function checkNewProtocol(){
    var protocolId = $("#protocolId").val();
    if(isEmpty(protocolId)){
        return false;
    }
    return true;
}
//返回列表
function returnProtocolList(){
    commCloseTab('protocolPromotion:list');
//    createAddProcessTab('lifeProtocol:list','');
    createAddProcessTab('lifeProtocolBasic:add',$('#promotion_look_protocolId').val());
}



