/**
 * 业务推动
 */

//业务推动单例对象
var Promotion = {};

$(function () {
    Promotion.formValidator();
    Promotion.init();
});
//表格数据展示
var Promotion = function () {
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
                            "</td>  " +
                            "<td style='margin-left: 10px'>" + value.salesOrgName + "</td>" +
                            "</tr>");
                    });
                }
            });

            var protocolId = $("#protocolId").val();
            $.ajax({
                url: 'lifeProtocol/insProtocolProducts',
                dataType: 'json',
                type: 'GET',
                contentType: 'application/json',
                async: false,
                data: {protocolId: protocolId},
                success: function (data) {
                    $.each(data, function (key, value) {
                        $("#chooseProduct").append("" +
                            "<tr> " +
                            "<td> " +
                            "<input type='checkbox' name='productId' id = '" + value.productId + "' value=" + value.productId + "  />" +
                            "</td>  " +
                            "<td style='margin-left: 10px'>" + value.productName + "</td>" +
                            "<td style='margin-left: 10px;display:none;'>" +
                            "<input type='text' name='productCode' value=" + value.productCode + "  />" +
                            "</td>  " +
                            "</tr>");
                    });
                }
            });
        },

        // 保存选择机构
        saveOrg: function () {
            var chk_value = [];//定义一个数组
            $('input[name="org"]:checked').each(function () {
                var org = {};
                org.salesOrgId = $(this).val();
                chk_value.push(org);
            });
            Promotion.orgs = chk_value;
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
                    var productCode = tdArr.eq(2).find("input[name='productCode']").val();
                    product.productId = productId;
                    product.productCode = productCode;
                    chk_value.push(product);
                }
            });
            Promotion.products = chk_value;
            $("#chooseProductModal").modal('hide');
        },

        // 增加月结算推动奖励
        addMonthRate: function () {
            var monthRateHtml = "<tr>\n" +
                "                        <td style=\"width:8%;\">\n" +
                "                            <select class=\"form-control form-control-static\" name=\"rateType\">\n" +
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
                "                            <a style=\"color: black\" href=\"#\" onclick=\"Promotion.addMonthRate()\">➕</a>\n" +
                "                            <a style=\"color: black\" href=\"#\" onclick=\"Promotion.reduceMonthRate(this)\">➖</a>\n" +
                "                        </td>\n" +
                "                    </tr>";
            $("#addSettlementCycleTable").append(monthRateHtml);
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
                "                            <select class=\"form-control form-control-static\" name=\"rateType\">\n" +
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
                "                            <a style=\"color: black\" href=\"#\" onclick=\"Promotion.addCommonRate()\">➕</a>\n" +
                "                            <a style=\"color: black\" href=\"#\" onclick=\"Promotion.reduceCommonRate(this)\">➖</a>\n" +
                "                        </td>\n" +
                "                    </tr>";
            $("#addCommonCycleTable").append(commonRateHtml);
        },

        // 删除通算推动奖励
        reduceCommonRate: function (a) {
            var table = a.parentNode.parentNode.parentNode;
            var tr = a.parentNode.parentNode;
            table.removeChild(tr);
        },


        // 新增推动奖励
        save: function () {
            if (this.getData()) {
                $.ajax({
                    url: 'insProtocolPromotion',
                    dataType: 'json',
                    type: 'POST',
                    contentType: 'application/json',
                    data: JSON.stringify(Promotion),
                    success: function (data) {
                        if("200" == data.messageCode){
                            $.alert({
                                title: '提示信息！',
                                content: '添加成功!',
                                type: 'blue'
                            });
                            PromotionList.init();
                            windowCloseTab();

                        }else{
                            $.alert({
                                title: '提示信息！',
                                content: '添加失败！',
                                type: 'red'
                            });
                        }
                    }
                });
            }
        },

        getData: function () {
            if ($("#addPromotion").data('bootstrapValidator').validate().isValid()) {
                Promotion.protocolId = $("#protocolId").val();
                Promotion.promotionName = $("#addPromotionName").val();
                Promotion.startTimeStr = $("#addStartTimeId").val();
                Promotion.endTimeStr = $("#addEndTimeId").val();
                if (undefined == $("input[name='orgScope']:checked").val() || '' == $("input[name='orgScope']:checked").val()) {
                    confirm("请选择区域");
                    return false;
                }

                if ("1" == $("input[name='orgScope']:checked").val()) {
                    if (Promotion.orgs.length == 0) {
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
                    Promotion.orgs = chk_value;
                }
                Promotion.orgScopeStr = $("input[name='orgScope']:checked").val();
                Promotion.orgScope = Promotion.orgs;

                if (undefined == $("input[name='productScope']:checked").val() || '' == $("input[name='productScope']:checked").val()) {
                    confirm("请选择产品");
                    return false;
                }
                var product_value = [];
                if ("0" == $("input[name='productScope']:checked").val()) {
                    Promotion.productScope = product_value;
                } else {
                    if (Promotion.products.length == 0) {
                        confirm("请选择产品");
                        return false;
                    }
                    Promotion.productScope = Promotion.products;
                }
                if (undefined == $("input[name='rateType']:checked").val() || '' == $("input[name='rateType']:checked").val()) {
                    confirm("请选择费率基于");
                    return false;
                }
                Promotion.rateType = $("input[name='rateType']:checked").val();
                Promotion.settlementCycle = $("input[name='settlementCycle']:checked").val();
                var objArr = [];
                $('#addSettlementCycleTable').find('tr').each(function (i) {
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
                Promotion.settlementRate = objArr;
                var commonCycle = $("input[name='commonCycle']:checked").val();
                if (undefined != commonCycle && "" != commonCycle) {
                    Promotion.commonCycle = commonCycle;
                    var objArr = [];
                    $('#addCommonCycleTable').find('tr').each(function (i) {
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
                    Promotion.commonRate = objArr;
                }
            }
            return true;
        },

        //表单验证
        formValidator: function () {
            $("#addPromotion").bootstrapValidator({
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
            document.getElementById('addCommonSpan').innerText = "通算方式*：季度算";
        }
        if ("2" == val) {
            document.getElementById('addCommonSpan').innerText = "通算方式*：半年算";
        }
        if ("3" == val) {
            document.getElementById('addCommonSpan').innerText = "通算方式*：年通算";
        }
        $("#common").css("display", "block");
    });
});





