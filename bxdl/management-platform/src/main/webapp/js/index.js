var demoIframe;
var treeview;
var mapObj = new Map();

//顶部tab切换事件
var tabScroll = {
	rollPage: function(e, i) {
		var t = $("#myTab"),
			n = t.children("li"),
			l = (t.prop("scrollWidth"), t.outerWidth()),
			s = parseFloat(t.css("left"));
		if ("left" === e) {
			if (!s && s <= 0) return;
			var r = -s - l;
			n.each(function(e, i) {
				var n = $(i),
				l = n.position().left;
				if (l >= r) return t.css("left", -l),
				!1
			})
		} else "auto" === e ? !
		function() {
			var e, r = n.eq(i);
			if (r[0]) {
				if (e = r.position().left, e < -s) return t.css("left", -e);
				if (e + r.outerWidth() >= l - s) {
					var o = e + r.outerWidth() - l;
					n.each(function(e, i) {
						var n = $(i),
						l = n.position().left;
						if (l + s > 0 && l > o) return t.css("left", -l),
						!1
					})
				}
			}
		} () : n.each(function(e, i) {
			var n = $(i),
			r = n.position().left;
			if (r + n.outerWidth() >= l - s) return t.css("left", -r),
			!1
		})
	},
	leftPage: function() {
		tabScroll.rollPage("left")
	},
	rightPage: function() {
		tabScroll.rollPage()
	}
};
$(document).ready(function(){
	demoIframe = $("#content");
	demoIframe.bind("load", loadReady);
	treeview=$('#treeview');
	$.ajax({
		url:'menu/getMenuTree',
		dataType:'json',
		type:'post',
		data:{rid:1},
		success:function(data){

			for(var i=0; i<data.length; i++){
				if(data[i].hasOwnProperty('nodes')){
					data[i].selectable = false;
				}
			}

			$('#treeview').treeview({
				color: "#a7b1c2",
				expandIcon: 'glyphicon glyphicon-chevron-right',
				collapseIcon: 'glyphicon glyphicon-chevron-down',
				nodeIcon: 'glyphicon  glyphicon-bookmark',
				data: data
			});
			$('#treeview').treeview('collapseAll', { silent: true });
			$('#treeview').on('nodeSelected', function(event, data) {
				if(data.href!=''){
					$(".active").removeClass("active");
					if ($("#content"+data.id+"").length){//如果当前tab已存在
						clickTab(data.id,data.href);
						$("#tab_"+ data.id).addClass('active');
						$("#content"+ data.id).addClass("active");
						tabScroll.rollPage("auto",$("#tab_"+ data.id).index());
					}else{
						 $('#myTab').append("<li id='tab_"+data.id+"'><a onclick=clickTab("+data.id+",'"+data.href+"') href='#content"+data.id+"' data-toggle='pill'>"+data.text+"<i onclick='closeTab("+data.id+")' class='fa fa-remove tab-close' style='padding-left: 3px;'></i></a></li>");
						 $('#myTabContent').append("<div class='tab-pane fade' id='content"+data.id+"'></div>");
						 clickTab(data.id,data.href);
						 $('#myTab a:last').tab('show');
						 tabScroll.rollPage("auto",$("#tab_"+ data.id).index());
					}
				}
			});
		}
	});
	$('.treeview').on('click','li',function(event, data) {//展开tree
		var nodeId=$(this).attr("data-nodeid");
		var liVal=$(this).html();
		var spLiVal=liVal.substr(liVal.lastIndexOf(">")+1);
		$('#input-expand-node').val(spLiVal);
		var findExpandibleNodess = function() {
			return treeview.treeview('search', [ $('#input-expand-node').val(), { ignoreCase: false, exactMatch: false } ]);
		};
		var expandibleNodes = findExpandibleNodess();
		if(mapObj.get(nodeId)!=null){
			treeview.treeview('expandNode', [ expandibleNodes, { silent: $('#chk-expand-silent').is(':checked') }]);
			mapObj.delete(nodeId);
			return true;
		}else{
			mapObj.set(nodeId,nodeId);
			treeview.treeview('collapseNode', [ expandibleNodes, { silent: $('#chk-expand-silent').is(':checked') }]);
			return true;
		}
	});

	//上一页
	$(".myTabWrap .prev").click(function(){
		tabScroll.leftPage();
	});

	//下一页
	$(".myTabWrap .next").click(function(){
		tabScroll.rightPage();
	});

	// 关闭全部选项卡
    $('.myTabWrap .J_tabCloseAll').on('click', function () {
        $('#myTab').children("li").not(":first").each(function () {
			var contId = $(this).children("a:first").attr("href").substr(1);
            $('#myTabContent .tab-pane[id="' + contId + '"]').remove();
            $(this).remove();
        });

		//默认选中第一个
		var firstLi = $('#myTab').children("li:first");
		if(firstLi.length>0){
			var contId = firstLi.children("a:first").attr("href").substr(1);
			firstLi.addClass("active");
            $('#myTabContent .tab-pane[id="' + contId + '"]').addClass("active");
		}
        $('#myTab').css("left", "0");
    });

	//关闭其他选项卡
    $('.myTabWrap .J_tabCloseOther').on('click', function () {
		$('#myTab').children("li").not(":first").not(".active").each(function () {
            var contId = $(this).children("a:first").attr("href").substr(1);
            $('#myTabContent .tab-pane[id="' + contId + '"]').remove();
            $(this).remove();
        });
        $('#myTab').css("left", "0");
	});
});
function clickTab (id,href) {
	$("#content"+id+"").siblings().not('#home').empty();
	//加载新页面
	demoIframe = $("#content"+id+"");
	demoIframe.bind("load", loadReady);
	$.ajax({cache: true});
	demoIframe.load(href,function(result){
		//将被加载页的JavaScript加载到本页执行
		$result = $(result);
		$result.find("script").appendTo("#content"+id+"");
	});
};
function closeTab (id) {
	//如果关闭的是当前激活的TAB，激活他的前一个TAB
	if ($("li.active").attr('id') =="tab_"+ id) {
		 $("#tab_"+ id).prev().addClass('active');
		 $("#content"+ id).prev().addClass('active in');
		 $("#tab_"+ id).prev().find("a").trigger("click");
	}
	//关闭TAB
	 $("#tab_"+ id).remove();
	 $("#content"+ id).remove();
};
function loadReady() {
	var bodyH = demoIframe.contents().find("body").get(0).scrollHeight,
	htmlH = demoIframe.contents().find("html").get(0).scrollHeight,
	maxH = Math.max(bodyH, htmlH), minH = Math.min(bodyH, htmlH),
	h = demoIframe.height() >= maxH ? minH:maxH ;
	if (h < 530) h = 630;
	demoIframe.height(h);
}
function getPage(url,name,node) {
	if(url != ""){
		url=url;
		$.ajax({cache: true});
		$("#title h2").text(name);
		$("#title strong").text(name);
		demoIframe.load(url,function(result){
			//将被加载页的JavaScript加载到本页执行
			$result = $(result);
			$result.find("script").appendTo('#content');
		});
		return true;
	}
}
//退出登陆
function quit(){
	$.confirm({
		title: '提示信息!',
		content: '您确定要退出登陆吗？',
		type: 'blue',
		typeAnimated: true,
		buttons: {
			确定: {
				action: function(){
					location.href="/logout";
				}
			},
			取消: function () {
			}
		}
	})
}
//open
function openPassDlg(){
	$("#passDlg").modal('show');
}
//关闭模态框
function closeDlgs(){
	$("#passDlg").modal('hide');
	$('#oldPwd').val("");
	$('#newPwd').val("");
	$('#againPwd').val("");
	// Modal验证销毁重构，防止第二次打开modal时显示上一次的验证痕迹
	$('#myform').data('bootstrapValidator', null);
	formValidator();
}



//修改密码
function updatePwd(){
	var employeeId =$("#employeeId").val();
	var oldPwd =$("#oldPwd").val();
	var newPwd =$("#newPwd").val();
	if(oldPwd != newPwd){
		if($("#myform").data('bootstrapValidator').validate().isValid()){
			//校验商户原始密码是否正确
			$.ajax({
				url:'/employee/checkOldPwd',
				dataType:'json',
				type:'post',
				data:{
					employeeId:employeeId,
					oldPwd:oldPwd
				},
				success:function(data){
					if(data){
						$.ajax({
							url:'/employee/updatePwd',
							dataType:'json',
							type:'post',
							data:{
								employeeId:employeeId,
								newPwd:newPwd
							},
							success:function(data){
								if(data){
									$.alert({
										title: '提示信息！',
										content: '密码修改成功，请退出重新登陆！',
										type: 'blue'
									});
									setTimeout('jumpurl()',2000);
								}else{
									$.alert({
										title: '提示信息！',
										content: '密码修改失败!',
										type: 'red'
									});
								}
							},
							error:function(){
								$.alert({
									title: '提示信息！',
									content: '请求失败!',
									type: 'red'
								});
							}
						})

					}else{
						$.alert({
							title: '提示信息！',
							content: '原始密码有误，请重新输入!',
							type: 'red'
						});
					}
				}
			});
		}else{
			return false;
		}
	}else if(oldPwd ==""||newPwd==""){
		$.alert({
			title: '提示信息！',
			content: '请输入密码!',
			type: 'red'
		});
	}
	else{
		$.alert({
			title: '提示信息！',
			content: '新密码不能与原密码一样!',
			type: 'red'
		});
	}
}

$(function(){
	formValidator();
});
function formValidator(){
	$("#myform").bootstrapValidator({
		fields:{
			oldPwd:{
				validators:{
					notEmpty:{
						message:'密码不能为空'
					},
					stringLength:{
						min:6,
						max:18,
						message:"字符长度要在6~18之间"
					},
					identical:{
						field:'password',
						message:'输入旧密码有误'
					}
				}
			},
			newPwd:{
				validators:{
					notEmpty:{
						messgae:'密码不能为空',
					},
					stringLength:{
						min:6,
						max:18,
						message:'字符长度要在6~18之间'
					},
				}
			},
			againPwd:{
				validators:{
					notEmpty:{
						message:'密码不能为空'
					},
					identical:{
						field:"newPwd",
						message:'两次输入密码不一致'
					}
				}
			}
		}
	});
}

function jumpurl(){
	location.href="/logout";
}