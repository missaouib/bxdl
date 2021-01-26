
/**
 * 费率调节 列表
 */
var adjustProtocolId = $("#adjust-protocol-id").val();
var isLook = $("#isLook").val();
var ProtocolRateAdjust = {
    seItem: null		//选中的条目
};

$(function (){
	ProtocolRateAdjust.init();
    $('.close').click(function(){
    	ProtocolRateAdjust.closeDlg();
	 });
});
//表格数据展示
var ProtocolRateAdjust = function (){
    return{
        init:function (){
            $('#protocol-adjust-table').bootstrapTable({
                url: "lifeProtocol/getProtocolRateAdjustList",
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
                toolbar:"#toolbar",
                showColumns : false, //显示隐藏列
                uniqueId: "ADJUST_ID", //每一行的唯一标识，一般为主键列
                queryParamsType:'',
                queryParams: ProtocolRateAdjust.queryAdjustParams,//传递参数（*）
                columns : [{
					title: '序列',
					width : '50',
					align : "center",
					valign : "middle",
		            switchable:false,
		            formatter:function(value,row,index){
		                var pageSize=$('#protocol-adjust-table').bootstrapTable('getOptions').pageSize;//通过表的#id 可以得到每页多少条
		                var pageNumber=$('#protocol-adjust-table').bootstrapTable('getOptions').pageNumber;//通过表的#id 可以得到当前第几页
		                return pageSize * (pageNumber - 1) + index + 1;    //返回每条的序号： 每页条数 * （当前页 - 1 ）+ 序号
		            }
	             },{
                    field : "ADJUST_NAME",
                    title : "费率调节名称",
                    align : "center",
                    valign : "middle",
                    sortable : "true"
                }, {
                    field : "RATE_TYPE",
                    title : "费率基于",
                    align : "center",
                    valign : "middle",
                    sortable : "true"
                }, {
                    field : "SUBJOIN_TYPE",
                    title : "附加费率基于",
                    align : "center",
                    valign : "middle",
                    sortable : "true"
                },  {
                    field : "SETTLEMENT_INTERVAl",
                    title : "结算周期",
                    align : "center",
                    valign : "middle",
                    sortable : "true"
                },  {
                    field : "RATE_ADJUST_TYPE",
                    title : "费率调节类型",
                    align : "center",
                    valign : "middle",
                    sortable : "true"
                },{
                    field: 'operate',
                    title: '操作',
                    width: '80px',
                    formatter: operateAdjustFormatter
                },],
                onLoadSuccess:function(data){
                    if(isLook =='yes'){
                  	  disProtocol();
                	}
                }
            });
        },
        queryAdjustParams:function(params){
            var temp = {
                pageSize: params.pageSize,  //页面大小
                pageNumber: params.pageNumber, //页码
                PROTOCOL_ID:adjustProtocolId,
            };
            return temp;
        },
        //搜索
        searchProtocol:function () {
            $("#protocol-table").bootstrapTable('refresh');
        },
        //清空
        empty:function () {
        	$("#search_protocol_form input").val(""); 
        	document.getElementById("PROTOCOL_STATUS").value = "";
            $("#protocol-table").bootstrapTable('refresh');
        }
    }
}();
//新增费率调节
function addProtocolAdjust(){
	commCloseTab('lifeProtocolRateAdjust:list');
	createAddProcessTab('lifeProtocolAdjustBasic:add',$("#adjust-protocol-id").val());
};

function operateAdjustFormatter(value, row, index) {
	var adjustId = row.ADJUST_ID;
		return [
	           /* '<button style="background:#c2c2c2;  height: 30px;width: 80px;color: white; line-height:20px"  type="button" onclick="lookProtocolRate('+adjustId+')" >查看</button>'*/,
	            '<input onclick="editProtocolRate('+adjustId+')" style="background:#c2c2c2;  height: 30px;width: 80px;color: white; line-height:20px" type="button"  value="编辑" />',
	            '<input onclick="deleteProtocolRate('+adjustId+')" style="background:#ed5565;  height: 30px;width: 80px;color: white; line-height:20px" type="button"  value="删除" />'
	            ].join('');
};

//修改费率调节
function editProtocolRate(adjustId){
	var protocolId= $("#adjust-protocol-id").val();
	commCloseTab('lifeProtocolRateAdjust:list');
	createAddProcessTab('lifeProtocolAdjustBasic:add',protocolId+":"+adjustId)
};
//查看费率调节
function lookProtocolRate(adjustId){
	var protocolId= $("#adjust-protocol-id").val();
	commCloseTab('lifeProtocolRateAdjust:list');
	createAddProcessTab('lifeProtocolAdjustBasic:add',"look"+":"+protocolId+":"+adjustId)
};
function deleteProtocolRate(adjustId){
	$.confirm({
        title: '提示信息!',
        content: '您确定要删除吗？',
        type: 'blue',
        typeAnimated: true,
        buttons: {
            确定: {
                action: function(){
                	$.ajax({
                		type : "post",
                		url : "lifeProtocol/deleteProtocolRate",
                		dataType : "json",
                		data : {
                			"adjustId" : adjustId,
                		},
                		success : function(data) {
                			if (data) {
                				$("#protocol-adjust-table").bootstrapTable('refresh');
                			} else {
                				$.alert({
                					title : '提示信息！',
                					content : '保存失败！',
                					type : 'red'
                				});
                			}
                		}
                	})
                }
            },
            取消: function () {
            }
        }
    });
}
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
function returnProtocol(){
	commCloseTab('lifeProtocolRateAdjust:list');
	createAddProcessTab('lifeProtocolBasic:add',$('#adjust_look_protocolId').val());
}