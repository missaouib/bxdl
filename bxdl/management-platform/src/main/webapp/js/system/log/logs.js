
//系统管理--角色管理的单例对象
var Logs = {
    seItem: null		//选中的条目
};
$(function(){
    Logs.init();
});
 
var Logs = function () {
    return{
        init:function(){
            $('#logs-table').bootstrapTable({
                url: "adminLog/getLogList",
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
                pageList : [5, 10, 20, 50 ],//分页数
                toolbar:"#toolbar",
                showColumns : false, //显示隐藏列
                uniqueId: "id", //每一行的唯一标识，一般为主键列
                queryParamsType:'',
                queryParams: Logs.queryParams,//传递参数（*）
                columns : [{
                    checkbox: true
            	},{
                    field : "id",
                    title : "主键",
                    class : 'col-md-1',
                    overflow: "auto",
                    align : "center",
                    width : 250,
                    height:60,
                    sortable : "true"
                },{
                    field : "requestURL",
                    title : "请求路径",
                    overflow: "auto",
                    align : "center",
                    width : 100,
                    height:60,
                    cellStyle:{
                    	css:{
                    	"overflow": "hidden",
                    	"text-overflow": "ellipsis",
                    	"white-space": "nowrap"
                    	} 
                    },
                    formatter: function(value,row,index){
						if(null == value){
							 return "";
						}
                         return "<span title='"+ value +"'>"+value+"</span>";
                	}
                }, {
                    field : "headerInfo",
                    title : "请求头",
                    overflow: "auto",
                    align : "center",
                    width : 200,
                    height:60,
                    cellStyle:{
                    	css:{
                    	"overflow": "hidden",
                    	"text-overflow": "ellipsis",
                    	"white-space": "nowrap"
                    	} 
                    },
                    formatter: function(value,row,index){
							if(null == value){
								 return "";
							}
	                         return "<span title='"+ value +"'>"+value+"</span>";
                    	}
                    }, {
                    field : "param",
                    title : "请求参数",
                    align : "center",
                    valign : "middle",
                    width : 150,
                    height:60,
                    cellStyle:{
                    	css:{
                    	"overflow": "hidden",
                    	"text-overflow": "ellipsis",
                    	"white-space": "nowrap"
                    	} 
                    },
                    formatter: function(value,row,index){
						if(null == value){
							 return "";
						}
                         return "<span title='"+ value +"'>"+value+"</span>";
                	}
                },{
                    field : "callTime",
                    title : "调用时间",
                    align : "center",
                    valign : "middle",
                    width:150,
                    height:60,
                    sortable : "true"
                }],
                formatLoadingMessage : function() {
                    return "请稍等，正在加载中...";
                },
                formatNoMatches : function() {
                    return '无符合条件的记录';
                }
            });
        },
        //得到查询的参数
        queryParams:function (params) {
        var temp = {
            pageSize: params.pageSize,  //页面大小
            pageNumber: params.pageNumber, //页码
            requestURL: $("#search_requestURL").val(),
            startTime:$("#startTime").val(),
            endTime:$("#endTime").val(),
        };
        	return temp;
        },
        //搜索
        searchLogs:function () {
            $("#logs-table").bootstrapTable('destroy');
            Logs.init();
        },
        //清空
        empty:function () {
            $("#search_requestURL").val('');
            $("#startTime").val('');
            $("#endTime").val('');
            $("#logs-table").bootstrapTable('refresh');
        },
    }
}();

