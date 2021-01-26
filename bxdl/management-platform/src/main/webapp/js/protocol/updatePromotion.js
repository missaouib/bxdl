/**
 * 业务推动
 */

//业务推动单例对象
var PromotionUpdate = {};

$(function () {
    PromotionUpdate.init();
    PromotionUpdate.getPromotion();
    PromotionUpdate.formValidator();
});
PromotionUpdate = function () {
    return {
        init: function () {
            $.ajax({
                url: 'salesOrgInfo/findSubordinateOrgList',
                dataType: 'json',
                type: 'get',
                async: false,
                data: {salesOrgId : $("#salesOrgId").val()},
                success: function (data) {
                    var salesOrgs = data.rows;
                    $.each(salesOrgs, function (key, value) {
                        $("#chooseOrg").append("" +
                            "<tr> " +
                            "<td> " +
                            "<input type='checkbox' name='org' value=" + value.salesOrgId + "  />" +
                            "<input type='hidden' name='orgId' value=''  />" +
                            "</td>  " +
                            "<td style='margin-left: 10px'>" + value.salesOrgName + "</td>" +
                            "</tr>");
                    });
                }
            });

            $.ajax({
                url: 'lifeProtocol/insProtocolProducts',
                dataType: 'json',
                type: 'GET',
                contentType: 'application/json',
                async: false,
                data: {protocolId: $("#protocolId").val()},
                success: function (data) {
                    $.each(data, function (key, value) {
                        $("#chooseProduct").append("" +
                            "<tr> " +
                            "<td> " +
                            "<input type='checkbox' name='productId' id = '" + value.productId + "' value=" + value.productId + "  />" +
                            "<input type='hidden' name='id' value=''  />" +
                            "<input type='hidden' name='productCode' value=''  />" +
                            "</td>  " +
                            "<td style='margin-left: 10px'>" + value.productName + "</td>" +
                            "</tr>");
                    });
                }
            });
        },

        getPromotion: function () {
            var promotionId = $("#promotionId").val();

            // 初始化数据
            $.ajax({
                url: 'insProtocolPromotion/'+promotionId,
                dataType: 'json',
                type: 'GET',
                data: {},
                success: function (data) {
                    $("#protocolId").val(data.protocolId);
                    $("#promotionId").val(data.id);
                    $("#state").val(data.state);
                    $("#deleted").val(data.deleted);
                    $("#promotionNameId").val(data.promotionName);
                    $("#startTimeId").val(data.startTimeStr);
                    $("#endTimeId").val(data.endTimeStr);
                    var orgScope = document.getElementsByName("orgScope");
                    for (var i = 0; i < orgScope.length; i++) {
                        if (orgScope[i].value == data.orgScopeStr) {
                            orgScope[i].checked = true;
                        }
                    }
                    if ("1" == data.orgScopeStr) {
                        $.each(data.orgScope, function (index, org) {
                            //获取页面所有checkbox，然后迭代
                            $("input[type=checkbox][name='org']").each(function () {
                                if ($(this).val() == org.salesOrgId) {
                                    $(this).next().val(org.id);
                                    $(this).attr("checked", true);
                                }
                            });
                        });
                    }
                    var productScope = document.getElementsByName("productScope");
                    for (var i = 0; i < productScope.length; i++) {
                        if (productScope[i].value == data.productScopeStr) {
                            productScope[i].checked = true;
                        }
                    }
                    if ("1" == data.productScopeStr) {
                        $.each(data.productScope, function (index, product) {
                            //获取页面所有checkbox，然后迭代
                            $("input[type=checkbox][name='productId']").each(function () {
                                if ($(this).val() == product.productId) {
                                    $(this).next().val(product.id);
                                    $(this).next().next().val(product.productCode);
                                    $(this).attr("checked", true);
                                }
                            });
                        });
                    }
                    var rateType = document.getElementsByName("rateType");
                    for (var i = 0; i < rateType.length; i++) {
                        if (rateType[i].value == data.rateType) {
                            rateType[i].checked = true;
                        }
                    }
                    var settlementCycle = document.getElementsByName("settlementCycle");
                    for (var i = 0; i < settlementCycle.length; i++) {
                        if (settlementCycle[i].value == data.settlementCycle) {
                            settlementCycle[i].checked = true;
                            $("#updateSettlementCycleTable").show();

                            var settlementHtml = "";

                            jQuery.each(data.settlementRate, function (i, item) {
                                var allowanceFormula = item.allowanceFormula;
                                var arr = [];
                                var rateType = "";
                                settlementHtml = settlementHtml + "<tr>\n" +
                                    "                        <td style=\"width:8%;\">\n" +
                                    "                            <select class=\"form-control form-control-static\" name=\"rateTypeSelect\">\n";
                                if ("0" == item.rateType) {
                                    settlementHtml = settlementHtml +
                                        "                            <option value=\"0\" selected = 'selected'>规保</option>\n" +
                                        "                            <option value=\"1\">标保</option>\n";
                                    rateType = "规保";
                                    arr = allowanceFormula.split("规保");
                                } else if ("1" == item.rateType) {
                                    settlementHtml = settlementHtml +
                                        "                            <option value=\"0\">规保</option>\n" +
                                        "                            <option value=\"1\" selected = 'selected'>标保</option>\n";
                                    rateType = "标保";
                                    arr = allowanceFormula.split("标保");
                                }
                                settlementHtml = settlementHtml + "</select>\n" + "</td>\n" +
                                    "                        <td style=\"width:77%;\">\n" +
                                    "                            <select style=\"width:15%;display:inline;\" class=\"form-control form-control-static\" name=\"cycleType\">\n";
                                if ("0" == item.cycle) {
                                    settlementHtml = settlementHtml +
                                        "                                <option value=\"0\" selected = 'selected'>月</option>\n" +
                                        "                                <option value=\"1\">季度</option>\n" +
                                        "                                <option value=\"2\">半年</option>\n" +
                                        "                                <option value=\"3\">年</option>\n";
                                } else if ("1" == item.cycle) {
                                    settlementHtml = settlementHtml +
                                        "                                <option value=\"0\">月</option>\n" +
                                        "                                <option value=\"1\" selected = 'selected'>季度</option>\n" +
                                        "                                <option value=\"2\">半年</option>\n" +
                                        "                                <option value=\"3\">年</option>\n";
                                } else if ("2" == item.cycle) {
                                    settlementHtml = settlementHtml +
                                        "                                <option value=\"0\">月</option>\n" +
                                        "                                <option value=\"1\">季度</option>\n" +
                                        "                                <option value=\"2\" selected = 'selected'>半年</option>\n" +
                                        "                                <option value=\"3\">年</option>\n";
                                } else if ("3" == item.cycle) {
                                    settlementHtml = settlementHtml +
                                        "                                <option value=\"0\">月</option>\n" +
                                        "                                <option value=\"1\">季度</option>\n" +
                                        "                                <option value=\"2\">半年</option>\n" +
                                        "                                <option value=\"3\" selected = 'selected'>年</option>\n";
                                }
                                jQuery.each(arr, function (j, info) {
                                    var value = info.replace('>', '').replace('=', '').replace('<', '');
                                    if (0 == j) {
                                        var bigValue = " <input style=\"width:20%;margin-left:2.5%;height:33px;display:inline;\"  type=\"text\" name=\"bigValue\" value='" + value + "'>元\n";
                                        var bigSignHtml = " <select style=\"width:15%;margin-left:0.5%;display:inline;\" class=\"form-control form-control-static\" name=\"bigSign\">\n";
                                        if (info.indexOf(">=") != -1) {
                                            bigSignHtml = bigSignHtml +
                                                "                                <option value=\">\">></option>\n" +
                                                "                                <option value=\">=\" selected='selected'>>=</option>\n" +
                                                "                                <option value=\"<\"><</option>\n" +
                                                "                                <option value=\"<=\"><=</option>\n";
                                        } else if (info.indexOf(">") != -1) {
                                            bigSignHtml = bigSignHtml +
                                                "                                <option value=\">\" selected='selected'>></option>\n" +
                                                "                                <option value=\">=\">>=</option>\n" +
                                                "                                <option value=\"<\"><</option>\n" +
                                                "                                <option value=\"<=\"><=</option>\n";
                                        } else if (info.indexOf("<=") != -1) {
                                            bigSignHtml = bigSignHtml +
                                                "                                <option value=\">\">></option>\n" +
                                                "                                <option value=\">=\">>=</option>\n" +
                                                "                                <option value=\"<\"><</option>\n" +
                                                "                                <option value=\"<=\" selected='selected'><=</option>\n";
                                        } else if (info.indexOf("<") != -1) {
                                            bigSignHtml = bigSignHtml +
                                                "                                <option value=\">\">></option>\n" +
                                                "                                <option value=\">=\">>=</option>\n" +
                                                "                                <option value=\"<\" selected='selected'><</option>\n" +
                                                "                                <option value=\"<=\" ><=</option>\n";
                                        }
                                        bigSignHtml = bigSignHtml + "</select>\n";
                                        settlementHtml = settlementHtml + bigValue + bigSignHtml;
                                    } else if (1 == j) {
                                        var smallValue = " <input style=\"width:20%;margin-left:0.5%;height:33px;display:inline;\"  type=\"text\" name=\"smallValue\" value='" + value + "'>元\n";
                                        var smallSignHtml = " <select style=\"width:15%;margin-left:2.5%;display:inline;\" class=\"form-control form-control-static\" name=\"smallSign\">\n";
                                        if (info.indexOf(">=") != -1) {
                                            smallSignHtml = smallSignHtml +
                                                "                                <option value=\">\">></option>\n" +
                                                "                                <option value=\">=\" selected='selected'>>=</option>\n" +
                                                "                                <option value=\"<\"><</option>\n" +
                                                "                                <option value=\"<=\"><=</option>\n";
                                        } else if (info.indexOf(">") != -1) {
                                            smallSignHtml = smallSignHtml +
                                                "                                <option value=\">\" selected='selected'>></option>\n" +
                                                "                                <option value=\">=\">>=</option>\n" +
                                                "                                <option value=\"<\"><</option>\n" +
                                                "                                <option value=\"<=\"><=</option>\n";
                                        } else if (info.indexOf("<=") != -1) {
                                            smallSignHtml = smallSignHtml +
                                                "                                <option value=\">\">></option>\n" +
                                                "                                <option value=\">=\">>=</option>\n" +
                                                "                                <option value=\"<\"><</option>\n" +
                                                "                                <option value=\"<=\" selected='selected'><=</option>\n";
                                        } else if (info.indexOf("<") != -1) {
                                            smallSignHtml = smallSignHtml +
                                                "                                <option value=\">\">></option>\n" +
                                                "                                <option value=\">=\">>=</option>\n" +
                                                "                                <option value=\"<\" selected='selected'><</option>\n" +
                                                "                                <option value=\"<=\" ><=</option>\n";
                                        }
                                        smallSignHtml = smallSignHtml + "</select>\n";
                                        settlementHtml = settlementHtml + smallSignHtml + smallValue;
                                    }
                                });
                                settlementHtml = settlementHtml + "</td>\n";
                                settlementHtml = settlementHtml +
                                    "  <td style=\"width:5%;\">\n" +
                                    "       <input style=\"width:auto;height:33px;\"  type=\"text\"  name=\"rate\" value='" + item.rate + "'>\n" +
                                    "  </td>\n";
                                if (i == 0) {
                                    settlementHtml = settlementHtml +
                                        "                        <td style=\"width:10%;\">\n" +
                                        "                            <a style=\"color: black\" href=\"#\" onclick=\"PromotionUpdate.addMonthRate(this)\">➕</a>\n" +
                                        "                        </td>\n";
                                } else {
                                    settlementHtml = settlementHtml +
                                        "                        <td style=\"width:10%;\">\n" +
                                        "                            <a style=\"color: black\" href=\"#\" onclick=\"PromotionUpdate.addMonthRate(this)\">➕</a>\n" +
                                        "                            <a style=\"color: black\" href=\"#\" onclick=\"PromotionUpdate.reduceMonthRate(this)\">➖</a>\n" +
                                        "                        </td>\n";
                                }
                                settlementHtml = settlementHtml + "</tr>";
                            });

                        }
                        $("#updateSettlementCycleTable").append(settlementHtml);
                    }

                    var commonCycle = document.getElementsByName("commonCycle");
                    for (var i = 0; i < commonCycle.length; i++) {
                        if (commonCycle[i].value == data.commonCycle) {
                            commonCycle[i].checked = true;
                            if ("1" == data.commonCycle) {
                                document.getElementById('updateCommonSpan').innerText = "通算方式*：季度算";
                            }
                            if ("2" == data.commonCycle) {
                                document.getElementById('updateCommonSpan').innerText = "通算方式*：半年算";
                            }
                            if ("3" == data.commonCycle) {
                                document.getElementById('updateCommonSpan').innerText = "通算方式*：年通算";
                            }

                            var commonCycleHtml = "";
                            jQuery.each(data.commonRate, function (i, item) {
                                var allowanceFormula = item.allowanceFormula;
                                var arr = [];
                                var rateType = "";
                                commonCycleHtml = commonCycleHtml + "<tr>\n" +
                                    "                        <td style=\"width:8%;\">\n" +
                                    "                            <select class=\"form-control form-control-static\" name=\"rateTypeSelect\">\n";
                                if ("0" == item.rateType) {
                                    commonCycleHtml = commonCycleHtml +
                                        "                            <option value=\"0\" selected = 'selected'>规保</option>\n" +
                                        "                            <option value=\"1\">标保</option>\n";
                                    rateType = "规保";
                                    arr = allowanceFormula.split("规保");
                                } else if ("1" == item.rateType) {
                                    commonCycleHtml = commonCycleHtml +
                                        "                            <option value=\"0\">规保</option>\n" +
                                        "                            <option value=\"1\" selected = 'selected'>标保</option>\n";
                                    rateType = "标保";
                                    arr = allowanceFormula.split("标保");
                                }
                                commonCycleHtml = commonCycleHtml + "</select>\n" + "</td>\n" +
                                    "                        <td style=\"width:77%;\">\n" +
                                    "                            <select style=\"width:15%;display:inline;\" class=\"form-control form-control-static\" name=\"cycleType\">\n";
                                if ("1" == item.cycle) {
                                    commonCycleHtml = commonCycleHtml +
                                        "                                <option value=\"1\" selected = 'selected'>季度</option>\n" +
                                        "                                <option value=\"2\">半年</option>\n" +
                                        "                                <option value=\"3\">年</option>\n";
                                } else if ("2" == item.cycle) {
                                    commonCycleHtml = commonCycleHtml +
                                        "                                <option value=\"1\">季度</option>\n" +
                                        "                                <option value=\"2\" selected = 'selected'>半年</option>\n" +
                                        "                                <option value=\"3\">年</option>\n";
                                } else if ("3" == item.cycle) {
                                    commonCycleHtml = commonCycleHtml +
                                        "                                <option value=\"1\">季度</option>\n" +
                                        "                                <option value=\"2\">半年</option>\n" +
                                        "                                <option value=\"3\" selected = 'selected'>年</option>\n";
                                }

                                jQuery.each(arr, function (j, info) {
                                    var value = info.replace('>', '').replace('=', '').replace('<', '');
                                    if (0 == j) {
                                        var bigValue = " <input style=\"width:20%;margin-left:2.5%;height:33px;display:inline;\"  type=\"text\" name=\"bigValue\" value='" + value + "'>元\n";
                                        var bigSignHtml = " <select style=\"width:15%;margin-left:0.5%;display:inline;\" class=\"form-control form-control-static\" name=\"bigSign\">\n";
                                        if (info.indexOf(">=") != -1) {
                                            bigSignHtml = bigSignHtml +
                                                "                                <option value=\">\">></option>\n" +
                                                "                                <option value=\">=\" selected='selected'>>=</option>\n" +
                                                "                                <option value=\"<\"><</option>\n" +
                                                "                                <option value=\"<=\"><=</option>\n";
                                        } else if (info.indexOf(">") != -1) {
                                            bigSignHtml = bigSignHtml +
                                                "                                <option value=\">\" selected='selected'>></option>\n" +
                                                "                                <option value=\">=\">>=</option>\n" +
                                                "                                <option value=\"<\"><</option>\n" +
                                                "                                <option value=\"<=\"><=</option>\n";
                                        } else if (info.indexOf("<=") != -1) {
                                            bigSignHtml = bigSignHtml +
                                                "                                <option value=\">\">></option>\n" +
                                                "                                <option value=\">=\">>=</option>\n" +
                                                "                                <option value=\"<\"><</option>\n" +
                                                "                                <option value=\"<=\" selected='selected'><=</option>\n";
                                        } else if (info.indexOf("<") != -1) {
                                            bigSignHtml = bigSignHtml +
                                                "                                <option value=\">\">></option>\n" +
                                                "                                <option value=\">=\">>=</option>\n" +
                                                "                                <option value=\"<\" selected='selected'><</option>\n" +
                                                "                                <option value=\"<=\" ><=</option>\n";
                                        }
                                        bigSignHtml = bigSignHtml + "</select>\n";
                                        commonCycleHtml = commonCycleHtml + bigValue + bigSignHtml;
                                    } else if (1 == j) {
                                        var smallValue = " <input style=\"width:20%;margin-left:0.5%;height:33px;display:inline;\"  type=\"text\" name=\"smallValue\" value='" + value + "'>元\n";
                                        var smallSignHtml = " <select style=\"width:15%;margin-left:2.5%;display:inline;\" class=\"form-control form-control-static\" name=\"smallSign\">\n";
                                        if (info.indexOf(">=") != -1) {
                                            smallSignHtml = smallSignHtml +
                                                "                                <option value=\">\">></option>\n" +
                                                "                                <option value=\">=\" selected='selected'>>=</option>\n" +
                                                "                                <option value=\"<\"><</option>\n" +
                                                "                                <option value=\"<=\"><=</option>\n";
                                        } else if (info.indexOf(">") != -1) {
                                            smallSignHtml = smallSignHtml +
                                                "                                <option value=\">\" selected='selected'>></option>\n" +
                                                "                                <option value=\">=\">>=</option>\n" +
                                                "                                <option value=\"<\"><</option>\n" +
                                                "                                <option value=\"<=\"><=</option>\n";
                                        } else if (info.indexOf("<=") != -1) {
                                            smallSignHtml = smallSignHtml +
                                                "                                <option value=\">\">></option>\n" +
                                                "                                <option value=\">=\">>=</option>\n" +
                                                "                                <option value=\"<\"><</option>\n" +
                                                "                                <option value=\"<=\" selected='selected'><=</option>\n";
                                        } else if (info.indexOf("<") != -1) {
                                            smallSignHtml = smallSignHtml +
                                                "                                <option value=\">\">></option>\n" +
                                                "                                <option value=\">=\">>=</option>\n" +
                                                "                                <option value=\"<\" selected='selected'><</option>\n" +
                                                "                                <option value=\"<=\" ><=</option>\n";
                                        }
                                        smallSignHtml = smallSignHtml + "</select>\n";
                                        commonCycleHtml = commonCycleHtml + smallSignHtml + smallValue;
                                    }
                                });
                                commonCycleHtml = commonCycleHtml + "</td>\n";
                                commonCycleHtml = commonCycleHtml +
                                    "  <td style=\"width:5%;\">\n" +
                                    "       <input style=\"width:auto;height:33px;\"  type=\"text\"  name=\"rate\" value='" + item.rate + "'>\n" +
                                    "  </td>\n";
                                if (i == 0) {
                                    commonCycleHtml = commonCycleHtml +
                                        "                        <td style=\"width:10%;\">\n" +
                                        "                            <a style=\"color: black\" href=\"#\" onclick=\"PromotionUpdate.addCommonRate(this)\">➕</a>\n" +
                                        "                        </td>\n";
                                } else {
                                    commonCycleHtml = commonCycleHtml +
                                        "                        <td style=\"width:10%;\">\n" +
                                        "                            <a style=\"color: black\" href=\"#\" onclick=\"PromotionUpdate.addCommonRate(this)\">➕</a>\n" +
                                        "                            <a style=\"color: black\" href=\"#\" onclick=\"PromotionUpdate.reduceCommonRate(this)\">➖</a>\n" +
                                        "                        </td>\n";
                                }
                                commonCycleHtml = commonCycleHtml + "</tr>";
                            });
                            $("#updateCommonCycleTable").append(commonCycleHtml);
                            $("#updateCommonCycle").show();
                        }
                    }
                }
            });
        },

        // 保存选择机构
        saveOrg: function () {
            var chk_value = [];//定义一个数组
            $('input[name="org"]:checked').each(function () {
                var org = {};
                org.salesOrgId = $(this).val();
                org.id = $(this).next().val();
                org.promotionId = $("#promotionId").val();
                org.deleted = 0;

                chk_value.push(org);
            });
            PromotionUpdate.orgs = chk_value;
            $("#chooseOrgModal").modal('hide');
        },

        // 保存选择产品
        saveProduct: function () {
            var chk_value = [];//定义一个数组
            $('#chooseProduct').find('tr').each(function (i) {
                var tdArr = $(this).children();
                var productId = tdArr.eq(0).find("input[name='productId']").val();
                if ($('#' + productId).is(':checked')) {
                    var product = {};
                    var id = tdArr.eq(1).find("input[name='id']").val();
                    var productCode = tdArr.eq(2).find("input[name='productCode']").val();
                    product.productId = productId;
                    product.id = id;
                    product.productCode = productCode;
                    product.deleted = 0;
                    product.promotionId = $("#promotionId").val();
                    chk_value.push(product);
                }
            });
            PromotionUpdate.products = chk_value;
            $("#chooseProductModal").modal('hide');
        },

        // 增加月结算推动奖励
        addMonthRate: function () {
            var monthRateHtml = "<tr>\n" +
                "                        <td style=\"width:8%;\">\n" +
                "                            <select class=\"form-control form-control-static\" name=\"rateTypeSelect\">\n" +
                "                            <option value=\"0\">规保</option>\n" +
                "                            <option value=\"1\">标保</option>\n" +
                "                            </select>\n" +
                "                        </td>\n" +
                "                        <td style=\"width:77%;\">\n" +
                "                            <select style=\"width:15%;display:inline;\" class=\"form-control form-control-static\" name=\"cycleType\">\n" +
                "                                <option value=\"0\">月</option>\n" +
                "                                <option value=\"1\">季度</option>\n" +
                "                                <option value=\"2\">半年</option>\n" +
                "                                <option value=\"3\">年</option>\n" +
                "                            </select>\n" +
                "                            <input style=\"width:20%;margin-left:2.5%;height:33px;display:inline;\"  type=\"text\"  name=\"bigValue\">元\n" +
                "                            <select style=\"margin-left:0.5%;width:15%;display:inline;\" class=\"form-control form-control-static\" name=\"bigSign\">\n" +
                "                                <option value=\">\">></option>\n" +
                "                                <option value=\">=\">>=</option>\n" +
                "                                <option value=\"<\"><</option>\n" +
                "                                <option value=\"<=\"><=</option>\n" +
                "                            </select>\n" +
                "                            <select style=\"margin-left:2.5%;width:15%;display:inline;\" class=\"form-control form-control-static\" name=\"smallSign\">\n" +
                "                                <option value=\">\">></option>\n" +
                "                                <option value=\">=\">>=</option>\n" +
                "                                <option value=\"<\"><</option>\n" +
                "                                <option value=\"<=\"><=</option>\n" +
                "                            </select>\n" +
                "                            <input style=\"margin-left:0.5%;width:20%;height:33px;display:inline;\"  type=\"text\" name=\"smallValue\">元\n" +
                "                        </td>\n" +
                "                        <td style=\"width:5%;\">\n" +
                "                            <input style=\"width:auto;height:33px;\"  type=\"text\"  name=\"rate\">\n" +
                "                        </td>\n" +
                "                        <td style=\"width:10%;\">\n" +
                "                            <a style=\"color: black\" href=\"#\" onclick=\"PromotionUpdate.addMonthRate(this)\">➕</a>\n" +
                "                            <a style=\"color: black\" href=\"#\" onclick=\"PromotionUpdate.reduceMonthRate(this)\">➖</a>\n" +
                "                        </td>\n" +
                "                    </tr>";
            $("#updateSettlementCycleTable").append(monthRateHtml);
        },

        // 删除月结算推动奖励
        reduceMonthRate: function (a) {
            var table = a.parentNode.parentNode.parentNode;
            var tr = a.parentNode.parentNode;
            table.removeChild(tr);
        },

        // 增加通算推动奖励
        addCommonRate: function () {
            var commonRateHtml = "<tr>\n" +
                "                        <td style=\"width:8%;\">\n" +
                "                            <select class=\"form-control form-control-static\" name=\"rateTypeSelect\">\n" +
                "                                <option value=\"0\">规保</option>\n" +
                "                                <option value=\"1\">标保</option>\n" +
                "                            </select>\n" +
                "                        </td>\n" +
                "                        <td style=\"width:77%;\">\n" +
                "                            <select style=\"width:15%;display:inline;\" class=\"form-control form-control-static\" name=\"cycleType\">\n" +
                "                                <option value=\"1\">季度</option>\n" +
                "                                <option value=\"2\">半年</option>\n" +
                "                                <option value=\"3\">年</option>\n" +
                "                            </select>\n" +
                "                            <input style=\"width:20%;margin-left:2.5%;height:33px;display:inline;\"  type=\"text\"  name=\"bigValue\">元\n" +
                "                            <select style=\"margin-left:0.5%;width:15%;display:inline;\" class=\"form-control form-control-static\" name=\"bigSign\">\n" +
                "                                <option value=\">\">></option>\n" +
                "                                <option value=\">=\">>=</option>\n" +
                "                                <option value=\"<\"><</option>\n" +
                "                                <option value=\"<=\"><=</option>\n" +
                "                            </select>\n" +
                "                            <select style=\"margin-left:2.5%;width:15%;display:inline;\" class=\"form-control form-control-static\" name=\"smallSign\">\n" +
                "                                <option value=\">\">></option>\n" +
                "                                <option value=\">=\">>=</option>\n" +
                "                                <option value=\"<\"><</option>\n" +
                "                                <option value=\"<=\"><=</option>\n" +
                "                            </select>\n" +
                "                            <input style=\"margin-left:0.5%;width:20%;height:33px;display:inline;\"  type=\"text\" name=\"smallValue\">元\n" +
                "                        </td>\n" +
                "                        <td style=\"width:5%;\">\n" +
                "                            <input style=\"width:auto;height:33px;\"  type=\"text\"  name=\"rate\">\n" +
                "                        </td>\n" +
                "                        <td style=\"width:10%;\">\n" +
                "                            <a style=\"color: black\" href=\"#\" onclick=\"PromotionUpdate.addCommonRate()\">➕</a>\n" +
                "                            <a style=\"color: black\" href=\"#\" onclick=\"PromotionUpdate.reduceCommonRate(this)\">➖</a>\n" +
                "                        </td>\n" +
                "                    </tr>";
            $("#updateCommonCycleTable").append(commonRateHtml);
        },

        // 删除通算推动奖励
        reduceCommonRate: function (a) {
            var table = a.parentNode.parentNode.parentNode;
            var tr = a.parentNode.parentNode;
            table.removeChild(tr);
        },

        // 更新推动奖励
        update: function () {
            if (this.getData()) {
                $.ajax({
                    url: 'insProtocolPromotion',
                    dataType: 'json',
                    type: 'PUT',
                    contentType: 'application/json',
                    data: JSON.stringify(PromotionUpdate),
                    success: function (data) {
                        if(data){
                            $.alert({
                                title: '提示信息！',
                                content: '编辑成功!',
                                type: 'blue'
                            });
                            PromotionList.init();
                            windowCloseTab();

                        }else{
                            $.alert({
                                title: '提示信息！',
                                content: '编辑失败！',
                                type: 'red'
                            });
                        }
                    }
                });
            }
        },

        getData: function () {
            if ($("#updatePromotionFrom").data('bootstrapValidator').validate().isValid()) {
                PromotionUpdate.id = $("#promotionId").val();
                PromotionUpdate.protocolId = $("#protocolId").val();
                PromotionUpdate.state = $("#state").val();
                PromotionUpdate.deleted = $("#deleted").val();
                PromotionUpdate.promotionName = $("#promotionNameId").val();
                PromotionUpdate.startTimeStr = $("#startTime").val();
                PromotionUpdate.endTimeStr = $("#endTime").val();
                PromotionUpdate.orgScope = $("#addEndTime").val();
                if (undefined == $("input[name='orgScope']:checked").val() || '' == $("input[name='orgScope']:checked").val()) {
                    confirm("请选择区域");
                    return false;
                }

                if ("1" == $("input[name='orgScope']:checked").val()) {
                    if (PromotionUpdate.orgs.length == 0) {
                        confirm("请选择销售机构");
                        return false;
                    }
                }else {
                    var chk_value = [];//定义一个数组
                    $('input[name="org"]').each(function () {
                        var org = {};
                        org.salesOrgId = $(this).val();
                        chk_value.push(org);
                    });
                    PromotionUpdate.orgs = chk_value;
                }
                PromotionUpdate.orgScopeStr = $("input[name='orgScope']:checked").val();
                PromotionUpdate.orgScope = PromotionUpdate.orgs;

                if (undefined == $("input[name='productScope']:checked").val() || '' == $("input[name='productScope']:checked").val()) {
                    confirm("请选择产品");
                    return false;
                }
                var product_value = [];
                if ("0" == $("input[name='productScope']:checked").val()) {
                    PromotionUpdate.productScope = product_value;
                } else {
                    if (undefined == PromotionUpdate.products) {
                        this.saveProduct();
                    }
                    if (PromotionUpdate.products.length == 0) {
                        confirm("请选择产品");
                        return false;
                    }
                    PromotionUpdate.productScope = PromotionUpdate.products;
                }
                if (undefined == $("input[name='rateType']:checked").val() || '' == $("input[name='rateType']:checked").val()) {
                    confirm("请选择费率基于");
                    return false;
                }
                PromotionUpdate.rateType = $("input[name='rateType']:checked").val();
                PromotionUpdate.settlementCycle = $("input[name='settlementCycle']:checked").val();
                var objArr = [];
                $('#updateSettlementCycleTable').find('tr').each(function (i) {
                    //去除表头
                    if (i > 0) {
                        var monthRate = {};
                        var tdArr = $(this).children();
                        var rateType = tdArr.eq(0).find('select');
                        var rateTypeText = rateType.find("option:selected").text();
                        monthRate.rateType = rateType.find("option:selected").val();
                        monthRate.cycle = tdArr.eq(1).find("select[name='cycleType']").val();
                        var bigValue = tdArr.eq(1).find("input[name='bigValue']").val();
                        var bigSign = tdArr.eq(1).find("select[name='bigSign']").val();
                        var smallSign = tdArr.eq(1).find("select[name='smallSign']").val();
                        var smallValue = tdArr.eq(1).find("input[name='smallValue']").val();
                        var rate = tdArr.eq(2).find("input[name='rate']").val();
                        monthRate.allowanceFormula = bigValue + bigSign + rateTypeText + smallSign + smallValue;
                        monthRate.rate = rate;
                        objArr.push(monthRate);
                    }
                });
                if (objArr.length == 0) {
                    confirm("请填写结算周期计算项");
                    return false;
                }
                PromotionUpdate.settlementRate = objArr;
                var commonCycle = $("input[name='commonCycle']:checked").val();
                if (undefined != commonCycle && "" != commonCycle) {
                    PromotionUpdate.commonCycle = commonCycle;
                    var objArr = [];
                    $('#updateCommonCycleTable').find('tr').each(function (i) {
                        //去除表头
                        if (i > 0) {
                            var monthRate = {};
                            var tdArr = $(this).children();
                            var rateType = tdArr.eq(0).find('select');
                            var rateTypeText = rateType.find("option:selected").text();
                            monthRate.rateType = rateType.find("option:selected").val();
                            monthRate.cycle = tdArr.eq(1).find("select[name='cycleType']").val();
                            var bigValue = tdArr.eq(1).find("input[name='bigValue']").val();
                            var bigSign = tdArr.eq(1).find("select[name='bigSign']").val();
                            var smallSign = tdArr.eq(1).find("select[name='smallSign']").val();
                            var smallValue = tdArr.eq(1).find("input[name='smallValue']").val();
                            var rate = tdArr.eq(2).find("input[name='rate']").val();
                            monthRate.allowanceFormula = bigValue + bigSign + rateTypeText + smallSign + smallValue;
                            monthRate.rate = rate;
                            objArr.push(monthRate);
                        }
                    });
                    if (objArr == 0) {
                        confirm("请填写通算计算项");
                        return false;
                    }
                    PromotionUpdate.commonRate = objArr;
                }
            }
            return true;
        },

        //表单验证
        formValidator: function () {
            $("#updatePromotionFrom").bootstrapValidator({
                fields: {
                    promotionName: {
                        validators: {
                            notEmpty: {
                                message: "方案名称不能为空"
                            },
                            stringLength: {
                                max: 32,
                                message: '不能超过32个字符长度'
                            }
                        }
                    },
                    startTime: {
                        validators: {
                            notEmpty: {
                                message: '有效起期不能为空',
                            }
                        }
                    },
                    endTime: {
                        validators: {
                            notEmpty: {
                                message: "有效止期不能为空"
                            }
                        }
                    }
                }
            });
        }

    }
}();

$(document).ready(function () {
    $("input[name=orgScope]").click(function () {
        var val = $(this).val();
        if ("1" == val) {
            $("#chooseOrgModal").modal('show');
        }
    });
    $("a[name=showOrg]").click(function () {
        $("#chooseOrgModal").modal('show');
    });

    $("input[name=productScope]").click(function () {
        var val = $(this).val();
        if ("1" == val) {
            $("#chooseProductModal").modal('show');
        }
    });
    $("a[name=showProduct]").click(function () {
        $("#chooseProductModal").modal('show');
    });
    $("input[name=commonCycle]").click(function () {
        var val = $(this).val();
        if ("1" == val) {
            document.getElementById('updateCommonSpan').innerText = "通算方式*：季度算";
        }
        if ("2" == val) {
            document.getElementById('updateCommonSpan').innerText = "通算方式*：半年算";
        }
        if ("3" == val) {
            document.getElementById('updateCommonSpan').innerText = "通算方式*：年通算";
        }

        if ("none" == $("#updateCommonCycle").css("display")) {
            PromotionUpdate.addCommonRate();
            $("#updateCommonCycle").css("display", "block");
        }

    });

});





