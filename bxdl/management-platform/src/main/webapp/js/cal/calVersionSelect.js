var versionparamtType;//基本法类型
var versionorgId;//选择组织机构Id
var version_id;//所选择主版本Id
var increaseColumns; //增员津贴历史版本展示列标题
var deptColumns; //直辖部管理津贴历史版本展示列标题
var groupColumns;//直辖组管理津贴历史版本展示列标题
var CalVersionSelect = function () {
    return {
        selectCalVersionClick: function (param_type, show_salesOrgId, show_isDefaultCal, show_calOrgId) {
            versionparamtType = param_type;
            if (show_isDefaultCal == 0) { //0：默认是分公司的组织机构Id ；1:自定义是各自团队的组织机构Id
                versionorgId = show_calOrgId;
            } else {
                versionorgId = show_salesOrgId;
            }
            //alert(param_type+"+"+show_salesOrgId+"+"+show_isDefaultCal+"+"+show_calOrgId);
            $("#cal_version-table").bootstrapTable('destroy');
            $('#cal_version-table').bootstrapTable({
                url: "cal_param_version/getCalParamVersion",
                method: "post",
                dataType: "json",
                contentType: "application/x-www-form-urlencoded",
                striped: true,//隔行变色
                cache: false,  //是否使用缓存
                showColumns: false,// 列
                pagination: true, //分页
                sortable: false, //是否启用排序
                singleSelect: false,
                search: false, //显示搜索框
                buttonsAlign: "right", //按钮对齐方式
                showRefresh: false,//是否显示刷新按钮
                sidePagination: "server", //服务端处理分页
                pageSize: 5, //默认每页条数
                pageNumber: 1, //默认分页
                pageList: [5, 10, 20, 50],//分页数
                toolbar: "#checkProducrButton",
                showColumns: false, //显示隐藏列
                uniqueId: "PRODUCTS_RATIO_ID", //每一行的唯一标识，一般为主键列
                queryParamsType: '',
                queryParams: CalVersionSelect.queryCalVersionBasicParams,//传递参数（*）
                columns: [
                    {
                        title: '序列',
                        width: '50',
                        align: "center",
                        valign: "middle",
                        switchable: false,
                        formatter: function (value, row, index) {
                            var pageSize = $('#cal_version-table').bootstrapTable('getOptions').pageSize;//通过表的#id 可以得到每页多少条
                            var pageNumber = $('#cal_version-table').bootstrapTable('getOptions').pageNumber;//通过表的#id 可以得到当前第几页
                            return pageSize * (pageNumber - 1) + index + 1;    //返回每条的序号： 每页条数 * （当前页 - 1 ）+ 序号
                        }
                    }, {
                        field: "version",
                        title: "版本号",
                        align: "center",
                        valign: "middle"
                    }, {
                        field: "createTime",
                        title: "修改时间",
                        align: "center",
                        valign: "middle"
                    }, {
                        field: "id",
                        title: "查看",
                        align: "center",
                        valign: "middle",
                        sortable: "true",
                        formatter: viewCalVersion
                    }]
            });
            $("#calVersionDlg").modal('show');
        },
        //参数传递
        queryCalVersionBasicParams: function (params) {
            var temp = {
                pageSize: params.pageSize,  //页面大小
                pageNumber: params.pageNumber, //页码
                param_type: versionparamtType,
                org_id: versionorgId,
                version_id: version_id,
                // protocolId: $("#new_protocol_id").val(),
                // insuranceOrgId:$("#INSURANCE_ORG_ID").val(),
                // insuranceCompanyId:$("#INSURANCE_COMPANY_ID").val(),
            };
            return temp;
        },
        //关闭
        cancelCalVersion: function () {
            $("#calVersionDlg").modal('hide');
            //$("#check-product-table").bootstrapTable('refresh');
        },
        selectCalHisNurtureDirector: function (versionId) {//处长/业务经理育成奖金历史查询
            version_id = versionId;
            $("#cal_version-table").bootstrapTable('destroy');
            $('#cal_version-table').bootstrapTable({
                url: "cal_nurture_director/getCalHisNurtureDirector",
                method: "post",
                dataType: "json",
                contentType: "application/x-www-form-urlencoded",
                striped: true,//隔行变色
                cache: false,  //是否使用缓存
                showColumns: false,// 列
                pagination: true, //分页
                sortable: false, //是否启用排序
                singleSelect: false,
                search: false, //显示搜索框
                buttonsAlign: "right", //按钮对齐方式
                showRefresh: false,//是否显示刷新按钮
                sidePagination: "server", //服务端处理分页
                pageSize: 5, //默认每页条数
                pageNumber: 1, //默认分页
                pageList: [5, 10, 20, 50],//分页数
                toolbar: "#checkProducrButton",
                showColumns: false, //显示隐藏列
                uniqueId: "PRODUCTS_RATIO_ID", //每一行的唯一标识，一般为主键列
                queryParamsType: '',
                queryParams: CalVersionSelect.queryCalVersionBasicParams,//传递参数（*）
                columns: [
                    {
                        title: '序列',
                        width: '50',
                        align: "center",
                        valign: "middle",
                        switchable: false,
                        formatter: function (value, row, index) {
                            var pageSize = $('#cal_version-table').bootstrapTable('getOptions').pageSize;//通过表的#id 可以得到每页多少条
                            var pageNumber = $('#cal_version-table').bootstrapTable('getOptions').pageNumber;//通过表的#id 可以得到当前第几页
                            return pageSize * (pageNumber - 1) + index + 1;    //返回每条的序号： 每页条数 * （当前页 - 1 ）+ 序号
                        }
                    }, {
                        field: "generative_algebra",
                        title: "育成代数",
                        align: "center",
                        valign: "middle",
                        formatter: directorAlgebraCalHisNurture
                    }, {
                        field: "fast_year",
                        title: "第一年",
                        align: "center",
                        valign: "middle",
                        formatter: fastYearCalHisNurture
                    }, {
                        field: "following_year_and_beyond",
                        title: "第二年及以后",
                        align: "center",
                        valign: "middle",
                        sortable: "true",
                        formatter: followingyearandbeyondCalHisNurture
                    }]
            });
            $("#calVersionDlg").modal('show');

        },
        selectCalHisPromote: function (versionId) {//处长/业务经理育成奖金历史查询
            version_id = versionId;
            $("#cal_version-table").bootstrapTable('destroy');
            $('#cal_version-table').bootstrapTable({
                url: "calExhibitionAllowance/getCalHisPromotePage",
                method: "post",
                dataType: "json",
                contentType: "application/x-www-form-urlencoded",
                striped: true,//隔行变色
                cache: false,  //是否使用缓存
                showColumns: false,// 列
                pagination: true, //分页
                sortable: false, //是否启用排序
                singleSelect: false,
                search: false, //显示搜索框
                buttonsAlign: "right", //按钮对齐方式
                showRefresh: false,//是否显示刷新按钮
                sidePagination: "server", //服务端处理分页
                pageSize: 5, //默认每页条数
                pageNumber: 1, //默认分页
                pageList: [5, 10, 20, 50],//分页数
                toolbar: "#checkProducrButton",
                showColumns: false, //显示隐藏列
                uniqueId: "PRODUCTS_RATIO_ID", //每一行的唯一标识，一般为主键列
                queryParamsType: '',
                queryParams: CalVersionSelect.queryCalVersionBasicParams,//传递参数（*）
                columns: [
                    {
                        title: '序列',
                        width: '50',
                        align: "center",
                        valign: "middle",
                        switchable: false,
                        formatter: function (value, row, index) {
                            var pageSize = $('#cal_version-table').bootstrapTable('getOptions').pageSize;//通过表的#id 可以得到每页多少条
                            var pageNumber = $('#cal_version-table').bootstrapTable('getOptions').pageNumber;//通过表的#id 可以得到当前第几页
                            return pageSize * (pageNumber - 1) + index + 1;    //返回每条的序号： 每页条数 * （当前页 - 1 ）+ 序号
                        }
                    }, {
                        field: "set_item",
                        title: "设置项",
                        align: "center",
                        valign: "middle"
                    }, {
                        field: "ruleType",
                        title: "计算规则选择",
                        align: "center",
                        valign: "middle"
                    }, {
                        field: "intervalRangeFyc",
                        title: "FYC区间范围判断",
                        align: "center",
                        valign: "middle",
                        sortable: "true"
                    }, {
                        field: "allowanceCalPromote",
                        title: "展业津贴（元）",
                        align: "center",
                        valign: "middle",
                        sortable: "true"
                    }]
            });
            $("#calVersionDlg").modal('show');

        },

        selectCalHisIncrease: function (versionId) {//增员津贴历史记录展示
            version_id = versionId;
            // 获取增员津贴的配置参数
            $.ajax({
                async: false,
                url: 'calIncreaseStaff/getFirstCalHisIncreaseByVersionId',
                type: 'post',
                dataType: 'json',
                data: {"versionId": versionId},
                success: function (data) {
                    if (data.messageCode == "200") {
                        calDepts = data.rows;
                        if (data.rows.length > 0) {
                            var his_increase_ruleType = data.rows[0].ruleType;
                            if (10 == his_increase_ruleType) { //区间设定值展示
                                increaseColumns = increaseColumns_qj
                            } else { // 固定值展示
                                increaseColumns = increaseColumns_gd
                            }
                        } else {
                            $.alert({
                                title: '提示信息！',
                                content: '没有增员津贴历史版本信息,请联系业务人员！',
                                type: 'red'
                            });
                        }
                        return false;
                    } else {
                        $.alert({
                            title: '提示信息！',
                            content: '获取增员津贴历史版本信息失败！',
                            type: 'red'
                        });
                    }
                },
                error: function () {
                    $.alert({
                        title: '提示信息！',
                        content: '获取增员津贴历史版本信息失败！',
                        type: 'red'
                    });
                }
            });

            $("#cal_version-table").bootstrapTable('destroy');
            $('#cal_version-table').bootstrapTable({
                url: "calIncreaseStaff/getCalHisIncreasePage",
                method: "post",
                dataType: "json",
                contentType: "application/x-www-form-urlencoded",
                striped: true,//隔行变色
                cache: false,  //是否使用缓存
                showColumns: false, //显示隐藏列
                pagination: true, //分页
                sortable: false, //是否启用排序
                singleSelect: false,
                search: false, //显示搜索框
                buttonsAlign: "right", //按钮对齐方式
                showRefresh: false,//是否显示刷新按钮
                sidePagination: "server", //服务端处理分页
                pageSize: 5, //默认每页条数
                pageNumber: 1, //默认分页
                pageList: [5, 10, 20, 50],//分页数
                toolbar: "#checkProducrButton",
                uniqueId: "PRODUCTS_RATIO_ID", //每一行的唯一标识，一般为主键列
                queryParamsType: '',
                queryParams: CalVersionSelect.queryCalVersionBasicParams,//传递参数（*）
                columns: increaseColumns
            });
            $("#calVersionDlg").modal('show');

        },
		selectCalHisUnderGroup:function(versionId){//直辖组管理津贴
            version_id = versionId;
            // 获取直辖组管理津贴的配置参数
            $.ajax({
                async: false,
                url: 'calDirectlyUnderGroup/getFirstCalDirectlyUnderGroupByVersionId',
                type: 'post',
                dataType: 'json',
                data: {"versionId": versionId},
                success: function (data) {
                    if (data.messageCode == "200") {
                        calDepts = data.rows;
                        if (data.rows.length > 0) {
                            var his_group_ruleType = data.rows[0].ruleType;
                            if (40 == his_group_ruleType) { //区间设定值展示
                                groupColumns = groupColumns_qj
                            } else if(41 == his_group_ruleType) { // 固定值展示
                                groupColumns = groupColumns_gd
                            }else if(42 == his_group_ruleType) {
                            	groupColumns = groupColumns_hl
							}
                        } else {
                            $.alert({
                                title: '提示信息！',
                                content: '没有直辖组管理津贴历史版本信息,请联系业务人员！',
                                type: 'red'
                            });
                        }
                        return false;
                    } else {
                        $.alert({
                            title: '提示信息！',
                            content: '获取直辖组管理津贴历史版本信息失败！',
                            type: 'red'
                        });
                    }
                },
                error: function () {
                    $.alert({
                        title: '提示信息！',
                        content: '获取增员津贴历史版本信息失败！',
                        type: 'red'
                    });
                }
            });

            $("#cal_version-table").bootstrapTable('destroy');
            $('#cal_version-table').bootstrapTable({
                url: "calDirectlyUnderGroup/getCalDirectlyUnderGroupPage",
                method: "post",
                dataType: "json",
                contentType: "application/x-www-form-urlencoded",
                striped: true,//隔行变色
                cache: false,  //是否使用缓存
                showColumns: false, //显示隐藏列
                pagination: true, //分页
                sortable: false, //是否启用排序
                singleSelect: false,
                search: false, //显示搜索框
                buttonsAlign: "right", //按钮对齐方式
                showRefresh: false,//是否显示刷新按钮
                sidePagination: "server", //服务端处理分页
                pageSize: 5, //默认每页条数
                pageNumber: 1, //默认分页
                pageList: [5, 10, 20, 50],//分页数
                toolbar: "#checkProducrButton",
                uniqueId: "PRODUCTS_RATIO_ID", //每一行的唯一标识，一般为主键列
                queryParamsType: '',
                queryParams: CalVersionSelect.queryCalVersionBasicParams,//传递参数（*）
                columns: groupColumns
            });
            $("#calVersionDlg").modal('show');

        },

        selectCalHisDept: function (versionId) {//增员津贴历史记录展示
            version_id = versionId;
            // 获取增员津贴的配置参数
            $.ajax({
                async: false,
                url: 'calDept/getFirstCalHisDeptByVersionId',
                type: 'post',
                dataType: 'json',
                data: {"versionId": versionId},
                success: function (data) {
                    if (data.messageCode == "200") {
                        calDepts = data.rows;
                        if (data.rows.length > 0) {
                            var his_dept_ruleType = data.rows[0].ruleType;
                            if (50 == his_dept_ruleType) { //区间设定值展示
                                deptColumns = deptColumns_qj
                            } else { // 固定值展示
                                deptColumns = deptColumns_gd
                            }
                        } else {
                            $.alert({
                                title: '提示信息！',
                                content: '没有直辖部管理津贴历史版本信息,请联系业务人员！',
                                type: 'red'
                            });
                        }
                        return false;
                    } else {
                        $.alert({
                            title: '提示信息！',
                            content: '获取直辖部管理津贴历史版本信息失败！',
                            type: 'red'
                        });
                    }
                },
                error: function () {
                    $.alert({
                        title: '提示信息！',
                        content: '获取直辖部管理津贴历史信息失败！',
                        type: 'red'
                    });
                }
            });

            $("#cal_version-table").bootstrapTable('destroy');
            $('#cal_version-table').bootstrapTable({
                url: "calDept/getCalHisDeptPage",
                method: "post",
                dataType: "json",
                contentType: "application/x-www-form-urlencoded",
                striped: true,//隔行变色
                cache: false,  //是否使用缓存
                showColumns: false, //显示隐藏列
                pagination: true, //分页
                sortable: false, //是否启用排序
                singleSelect: false,
                search: false, //显示搜索框
                buttonsAlign: "right", //按钮对齐方式
                showRefresh: false,//是否显示刷新按钮
                sidePagination: "server", //服务端处理分页
                pageSize: 5, //默认每页条数
                pageNumber: 1, //默认分页
                pageList: [5, 10, 20, 50],//分页数
                toolbar: "#checkProducrButton",
                uniqueId: "PRODUCTS_RATIO_ID", //每一行的唯一标识，一般为主键列
                queryParamsType: '',
                queryParams: CalVersionSelect.queryCalVersionBasicParams,//传递参数（*）
                columns: deptColumns
            });
            $("#calVersionDlg").modal('show');

        },
        selectCalHisNurtureMinister: function (versionId) {//处长/业务经理育成奖金历史查询
            version_id = versionId;
            $("#cal_version-table").bootstrapTable('destroy');
            $('#cal_version-table').bootstrapTable({
                url: "cal_minister_nurturing/getCalHisNurtureMinister",
                method: "post",
                dataType: "json",
                contentType: "application/x-www-form-urlencoded",
                striped: true,//隔行变色
                cache: false,  //是否使用缓存
                showColumns: false,// 列
                pagination: true, //分页
                sortable: false, //是否启用排序
                singleSelect: false,
                search: false, //显示搜索框
                buttonsAlign: "right", //按钮对齐方式
                showRefresh: false,//是否显示刷新按钮
                sidePagination: "server", //服务端处理分页
                pageSize: 5, //默认每页条数
                pageNumber: 1, //默认分页
                pageList: [5, 10, 20, 50],//分页数
                toolbar: "#checkProducrButton",
                showColumns: false, //显示隐藏列
                uniqueId: "PRODUCTS_RATIO_ID", //每一行的唯一标识，一般为主键列
                queryParamsType: '',
                queryParams: CalVersionSelect.queryCalVersionBasicParams,//传递参数（*）
                columns: [
                    {
                        title: '序列',
                        width: '50',
                        align: "center",
                        valign: "middle",
                        switchable: false,
                        formatter: function (value, row, index) {
                            var pageSize = $('#cal_version-table').bootstrapTable('getOptions').pageSize;//通过表的#id 可以得到每页多少条
                            var pageNumber = $('#cal_version-table').bootstrapTable('getOptions').pageNumber;//通过表的#id 可以得到当前第几页
                            return pageSize * (pageNumber - 1) + index + 1;    //返回每条的序号： 每页条数 * （当前页 - 1 ）+ 序号
                        }
                    }, {
                        field: "generative_algebra",
                        title: "育成代数",
                        align: "center",
                        valign: "middle"
                    }, {
                        field: "fast_year",
                        title: "第一年",
                        align: "center",
                        valign: "middle"
                    }, {
                        field: "following_year_and_beyond",
                        title: "第二年及以后",
                        align: "center",
                        valign: "middle",
                        sortable: "true"
                    }]
            });
            $("#calVersionDlg").modal('show');

        }

    }
}();

function viewCalVersion(value, row, index) {
    var versionId = row.id;
    return [
        '<a href="#" rel="external nofollow" class="viewCalVersion"><input type="button" value="查看" onclick="viewToSonVersion(' + versionId + ')"></a>'
    ].join('');
};

function directorAlgebraCalHisNurture(value, row, index) {
    var directorAlgebra = row.generative_algebra;
    if (directorAlgebra == 1) {
        directorAlgebra = "直接育成奖金率";
    } else {
        directorAlgebra = "第二代育成奖金率";
    }
    return [
        '' + directorAlgebra + ''
    ].join('');
}

function fastYearCalHisNurture(value, row, index) {
    var directorFastYear = accMul(row.fast_year, 100);
    return [
        '' + directorFastYear + '%'
    ].join('');
}

function followingyearandbeyondCalHisNurture(value, row, index) {
    var directorFollowingYearAndBeyond = accMul(row.following_year_and_beyond, 100);
    return [
        '' + directorFollowingYearAndBeyond + '%'
    ].join('');
}

/**
 * 调度判断显示具体历史模态框
 * @param id
 */
function viewToSonVersion(id) {
    // alert(versionparamtType);
    var versionId = id;
    if ('2' == versionparamtType) {//处长/业务经理育成奖金历史查询
        CalVersionSelect.selectCalHisNurtureDirector(versionId)
    } else if ('0' == versionparamtType) {//展业津贴历史查询

        CalVersionSelect.selectCalHisPromote(versionId)
    } else if ('1' == versionparamtType) {//增员津贴历史查询
        CalVersionSelect.selectCalHisIncrease(versionId)
    } else if ('5' == versionparamtType) {//增员津贴历史查询
        CalVersionSelect.selectCalHisDept(versionId)
    } else if ('3' == versionparamtType) {// 部长/总监
        CalVersionSelect.selectCalHisNurtureMinister(versionId)
    } else if('4'==versionparamtType){
    	CalVersionSelect.selectCalHisUnderGroup(versionId)
	}

};

var increaseColumns_qj = [
    {
        title: '序列',
        width: '50',
        align: "center",
        valign: "middle",
        switchable: false,
        formatter: function (value, row, index) {
            var pageSize = $('#cal_version-table').bootstrapTable('getOptions').pageSize;//通过表的#id 可以得到每页多少条
            var pageNumber = $('#cal_version-table').bootstrapTable('getOptions').pageNumber;//通过表的#id 可以得到当前第几页
            return pageSize * (pageNumber - 1) + index + 1;    //返回每条的序号： 每页条数 * （当前页 - 1 ）+ 序号
        }
    }, {
        field: "set_item",
        title: "设置项",
        align: "center",
        valign: "middle"
    }, {
        field: "ruleType",
        title: "计算规则选择",
        align: "center",
        valign: "middle"
    }, {
        field: "intervalRangeFyc",
        title: "FYC区间范围判断",
        align: "center",
        valign: "middle",
        sortable: "true"
    }, {
        field: "allowanceCalIncrease",
        title: "增员津贴比例(%)",
        align: "center",
        valign: "middle",
        sortable: "true"
    }];

/*增员津贴历史版本展示column*/
var deptColumns_qj = [
    {
        title: '序列',
        width: '50',
        align: "center",
        valign: "middle",
        switchable: false,
        formatter: function (value, row, index) {
            var pageSize = $('#cal_version-table').bootstrapTable('getOptions').pageSize;//通过表的#id 可以得到每页多少条
            var pageNumber = $('#cal_version-table').bootstrapTable('getOptions').pageNumber;//通过表的#id 可以得到当前第几页
            return pageSize * (pageNumber - 1) + index + 1;    //返回每条的序号： 每页条数 * （当前页 - 1 ）+ 序号
        }
    }, {
        field: "set_item",
        title: "设置项",
        align: "center",
        valign: "middle"
    }, {
        field: "ruleType",
        title: "计算规则选择",
        align: "center",
        valign: "middle"
    }, {
        field: "intervalRangeFyc",
        title: "FYC区间范围判断",
        align: "center",
        valign: "middle",
        sortable: "true"
    }, {
        field: "allowanceCalIncrease",
        title: "增员津贴比例(%)",
        align: "center",
        valign: "middle",
        sortable: "true"
    }];

var increaseColumns_gd = [
    {
        title: '序列',
        width: '50',
        align: "center",
        valign: "middle",
        switchable: false,
        formatter: function (value, row, index) {
            var pageSize = $('#cal_version-table').bootstrapTable('getOptions').pageSize;//通过表的#id 可以得到每页多少条
            var pageNumber = $('#cal_version-table').bootstrapTable('getOptions').pageNumber;//通过表的#id 可以得到当前第几页
            return pageSize * (pageNumber - 1) + index + 1;    //返回每条的序号： 每页条数 * （当前页 - 1 ）+ 序号
        }
    }, {
        field: "set_item",
        title: "设置项",
        align: "center",
        valign: "middle"
    }, {
        field: "ruleType",
        title: "计算规则选择",
        align: "center",
        valign: "middle"
    }, {
        field: "allowanceCalIncrease",
        title: "增员津贴比例(%)",
        align: "center",
        valign: "middle",
        sortable: "true"
    }];
/*直辖部历史版本展示column*/
var deptColumns_qj = [
    {
        title: '序列',
        width: '50',
        align: "center",
        valign: "middle",
        switchable: false,
        formatter: function (value, row, index) {
            var pageSize = $('#cal_version-table').bootstrapTable('getOptions').pageSize;//通过表的#id 可以得到每页多少条
            var pageNumber = $('#cal_version-table').bootstrapTable('getOptions').pageNumber;//通过表的#id 可以得到当前第几页
            return pageSize * (pageNumber - 1) + index + 1;    //返回每条的序号： 每页条数 * （当前页 - 1 ）+ 序号
        }
    }, {
        field: "set_item",
        title: "设置项",
        align: "center",
        valign: "middle"
    }, {
        field: "ruleType",
        title: "计算规则选择",
        align: "center",
        valign: "middle"
    }, {
        field: "intervalRangeFyc",
        title: "FYC区间范围判断",
        align: "center",
        valign: "middle",
        sortable: "true"
    }, {
        field: "allowanceCalDept",
        title: "直辖部津贴比例(%)",
        align: "center",
        valign: "middle",
        sortable: "true"
    }];

var deptColumns_gd = [
    {
        title: '序列',
        width: '50',
        align: "center",
        valign: "middle",
        switchable: false,
        formatter: function (value, row, index) {
            var pageSize = $('#cal_version-table').bootstrapTable('getOptions').pageSize;//通过表的#id 可以得到每页多少条
            var pageNumber = $('#cal_version-table').bootstrapTable('getOptions').pageNumber;//通过表的#id 可以得到当前第几页
            return pageSize * (pageNumber - 1) + index + 1;    //返回每条的序号： 每页条数 * （当前页 - 1 ）+ 序号
        }
    }, {
        field: "set_item",
        title: "设置项",
        align: "center",
        valign: "middle"
    }, {
        field: "ruleType",
        title: "计算规则选择",
        align: "center",
        valign: "middle"
    }, {
        field: "includeDirectly",
        title: "直辖/非直辖处",
        align: "center",
        valign: "middle"
    }, {
        field: "allowanceCalDept",
        title: "直辖部津贴比例(%)",
        align: "center",
        valign: "middle",
        sortable: "true"
    }];

var groupColumns_qj = [
    {
        title: '序列',
        width: '50',
        align: "center",
        valign: "middle",
        switchable: false,
        formatter: function (value, row, index) {
            var pageSize = $('#cal_version-table').bootstrapTable('getOptions').pageSize;//通过表的#id 可以得到每页多少条
            var pageNumber = $('#cal_version-table').bootstrapTable('getOptions').pageNumber;//通过表的#id 可以得到当前第几页
            return pageSize * (pageNumber - 1) + index + 1;    //返回每条的序号： 每页条数 * （当前页 - 1 ）+ 序号
        }
    }, {
        field: "set_item",
        title: "设置项",
        align: "center",
        valign: "middle"
    }, {
        field: "ruleType",
        title: "计算规则选择",
        align: "center",
        valign: "middle"
    }, {
        field: "intervalRangeFyc",
        title: "FYC区间范围判断",
        align: "center",
        valign: "middle",
        sortable: "true"
    }, {
        field: "allowanceCalGroup",
        title: "直辖组管理津贴比例（%）",
        align: "center",
        valign: "middle",
        sortable: "true"
    }];

var groupColumns_gd = [
    {
        title: '序列',
        width: '50',
        align: "center",
        valign: "middle",
        switchable: false,
        formatter: function (value, row, index) {
            var pageSize = $('#cal_version-table').bootstrapTable('getOptions').pageSize;//通过表的#id 可以得到每页多少条
            var pageNumber = $('#cal_version-table').bootstrapTable('getOptions').pageNumber;//通过表的#id 可以得到当前第几页
            return pageSize * (pageNumber - 1) + index + 1;    //返回每条的序号： 每页条数 * （当前页 - 1 ）+ 序号
        }
    }, {
        field: "set_item",
        title: "设置项",
        align: "center",
        valign: "middle"
    }, {
        field: "ruleType",
        title: "计算规则选择",
        align: "center",
        valign: "middle"
    }, {
        field: "allowanceCalGroup",
        title: "直辖组管理津贴比例（%）",
        align: "center",
        valign: "middle",
        sortable: "true"
    }];

var groupColumns_hl = [
    {
        title: '序列',
        width: '50',
        align: "center",
        valign: "middle",
        switchable: false,
        formatter: function (value, row, index) {
            var pageSize = $('#cal_version-table').bootstrapTable('getOptions').pageSize;//通过表的#id 可以得到每页多少条
            var pageNumber = $('#cal_version-table').bootstrapTable('getOptions').pageNumber;//通过表的#id 可以得到当前第几页
            return pageSize * (pageNumber - 1) + index + 1;    //返回每条的序号： 每页条数 * （当前页 - 1 ）+ 序号
        }
    }, {
        field: "set_item",
        title: "设置项",
        align: "center",
        valign: "middle"
    }, {
        field: "ruleType",
        title: "计算规则选择",
        align: "center",
        valign: "middle"
    },{
        field: "activeSaler",
        title: "组活动人力",
        align: "center",
        valign: "middle"
    },
	{
        field: "relationType",
        title: "关系",
        align: "center",
        valign: "middle"
    },
	{
        field: "intervalRangeFyc",
        title: "FYC区间范围判断",
        align: "center",
        valign: "middle",
        sortable: "true"
    }, {
        field: "allowanceCalGroup",
        title: "直辖组管理津贴比例（%）",
        align: "center",
        valign: "middle",
        sortable: "true"
    }];