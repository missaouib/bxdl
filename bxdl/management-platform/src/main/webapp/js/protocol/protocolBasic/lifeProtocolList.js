
/**
 * 寿险代理协议管理
 */

var Protocol = {
    seItem: null		//选中的条目
};

$(function (){
	Protocol.init();
    $('.close').click(function(){
    	Protocol.closeDlg();
	 });
});
//表格数据展示
var Protocol = function (){
    return{
        init:function (){
            $('#protocol-table').bootstrapTable({
                url: "lifeProtocol/getLifeProtocolList",
                method:"post",
                dataType: "json",
                contentType: "application/x-www-form-urlencoded",
                striped:true,//隔行变色
                cache:false,  //是否使用缓存
                showColumns:false,// 列
                toobar:'#toolbar',
                pagination: true, //分页
                sortable: false, //是否启用排序
                singleSelect: false,
                search:false, //显示搜索框
                buttonsAlign: "right", //按钮对齐方式
                showRefresh:false,//是否显示刷新按钮
                sidePagination: "server", //服务端处理分页
                pageSize : 5, //默认每页条数
                pageNumber : 1, //默认分页
                pageList : [ 5, 10, 20, 50],//分页数
                showColumns : false, //显示隐藏列
                uniqueId: "PROTOCOL_ID", //每一行的唯一标识，一般为主键列
                queryParamsType:'',
                queryParams: Protocol.queryParams,//传递参数（*）
                columns : [{
					title: '序列',
					width : '60',
					align : "center",
					valign : "middle",
		            switchable:false,
		            formatter:function(value,row,index){
		                var pageSize=$('#protocol-table').bootstrapTable('getOptions').pageSize;//通过表的#id 可以得到每页多少条
		                var pageNumber=$('#protocol-table').bootstrapTable('getOptions').pageNumber;//通过表的#id 可以得到当前第几页
		                return pageSize * (pageNumber - 1) + index + 1;    //返回每条的序号： 每页条数 * （当前页 - 1 ）+ 序号
		            }
	             },{
                    field : "INSURANCE_COMPANY_NAME",
                    title : "签约保险公司",
                    width : '50',
                    align : "center",
                    valign : "middle",
                    sortable : "true"
                }, {
                    field : "INSURANCE_ORG_NAME",
                    title : "签约机构",
                    align : "center",
                    width : '50',
                    valign : "middle",
                    sortable : "true"
                }, {
                    field : "PROTOCOL_NAME",
                    title : "协议名称",
                    align : "center",
                    width : '100',
                    valign : "middle",
                    sortable : "true"
                },  {
                    field : "PROTOCOL_CODE",
                    title : "协议编号",
                    width : '70',
                    align : "center",
                    valign : "middle",
                    sortable : "true"
                },  {
                    field : "EFFECTIVE_DATE",
                    title : "生效日期",
                    align : "center",
                    width : '50',
                    valign : "middle",
                    sortable : "true"
                },{
                    field : "TERMINATION_DATE",
                    title : "终止日期",
                    width : '50',
                    align : "center",
                    valign : "middle",
                    sortable : "true"
                },{
                    field : "CREATE_TIME",
                    title : "创建日期",
                    align : "center",
                    width : '50',
                    valign : "middle",
                    sortable : "true"
                },{
                    field : "PROTOCOL_STATUS_ZH",
                    title : "状态",
                    align : "center",
                    width : '50',
                    valign : "middle",
                    sortable : "true"
                },{
                    field: 'operate',
                    title: '操作',
                    width: '80px',
                    formatter: operateFormatter
                },]
            });
        },
        queryParams:function(params){
            var temp = {
                pageSize: params.pageSize,  //页面大小
                pageNumber: params.pageNumber, //页码
                INSURANCE_COMPANY_NAME: $("#INSURANCE_COMPANY_NAME").val(),
                INSURANCE_ORG_NAME:$("#INSURANCE_ORG_NAME").val(),
                PROTOCOL_NAME:$("#PROTOCOL_NAME").val(),
                PROTOCOL_CODE:$("#PROTOCOL_CODE").val(),
                EFFECTIVE_DATE:$("#EFFECTIVE_DATE").val(),
                TERMINATION_DATE:$("#TERMINATION_DATE").val(),
                PROTOCOL_STATUS:$("#PROTOCOL_STATUS").val(),
            };
            return temp;
        },
        //搜索
        searchProtocol:function () {
        	$("#protocol-table").bootstrapTable('destroy');
        	Protocol.init();
        },
        //清空
        empty:function () {
        	$("#search_protocol_form input").val(""); 
        	document.getElementById("PROTOCOL_STATUS").value = "";
            $("#protocol-table").bootstrapTable('refresh');
        }
    }
}();
//新增协议
function addProtocolServiceBasic(){
	commCloseTab('lifeProtocol:list');
	createAddProcessTab('lifeProtocolBasic:add','')
}
function operateFormatter(value, row, index) {
	var protocolStatus = row.PROTOCOL_STATUS;
	var prorocolId = row.PROTOCOL_ID;
	//录入中
	if(protocolStatus == 0){
		return [
	            '<button class="btn btn-default"  type="button" onclick="updateProtocolStatus('+prorocolId+',1)" >生效</button>',
	            '<button  class="btn btn-default" style="margin-left: 5px;" type="button" onclick="editProtocol('+prorocolId+')" > 编辑</button>',
	            '<button class="btn btn-default" style="margin-left: 5px;" type="button" onclick="lookProtocol('+prorocolId+')" > 查看</button>',
	            ].join('');
	}
	//生效
	if(protocolStatus == 1){
		return [
	            '<button class="btn btn-default" type="button" onclick="updateProtocolStatus('+prorocolId+',2)" >失效</button>',
	            '<button class="btn btn-default" style="margin-left: 5px;" type="button" onclick="lookProtocol('+prorocolId+')" >查看</button>',
	            ].join('');
	}
	//失效
	if(protocolStatus ==2){
		return [
	            '<button class="btn btn-default" type="button" onclick="updateProtocolStatus('+prorocolId+',1)" >生效</button>',
	            '<button class="btn btn-default" style="margin-left: 5px;"  type="button" onclick="editProtocol('+prorocolId+')" >编辑</button>',
	            '<button class="btn btn-default" type="button" onclick="lookProtocol('+prorocolId+')" >查看</button>',
	            ].join('');
	}
	//其他
	if(protocolStatus ==3){
		return [
	            '<button class="btn btn-default" type="button" onclick="updateProtocolStatus('+prorocolId+',2)" >失效</button>',
	            '<button class="btn btn-default" style="margin-left: 5px;" type="button" onclick="editProtocol('+prorocolId+')" >查看</button>',
	            ].join('');
	}
	
};

//修改协议
function editProtocol(prorocolId){
	commCloseTab('lifeProtocol:list');
	createAddProcessTab('lifeProtocolBasic:add',prorocolId)
};
//查看协议
function lookProtocol(prorocolId){
	commCloseTab('lifeProtocol:list');
	createAddProcessTab('lifeProtocolBasic:add',"look"+":"+prorocolId)
};
//修改协议状态
function updateProtocolStatus(protocolId,status){
	 $.ajax({
         url:'lifeProtocol/updateProtocolStatus',
         type:'post',
         dataType:'json',
         data:{"protocolId":protocolId,
        	  "status":status},
         success:function(data){
        	if(data){
                $("#protocol-table").bootstrapTable('refresh');
        	 }else{
                 $.alert({
                     title: '提示信息！',
                     content: '修改状态失败！',
                     type: 'red'
                 });
         }
       },
         error:function(){
             $.alert({
                 title: '提示信息！',
                 content: '修改状态失败！',
                 type: 'red'
             });
         }
     });
};
