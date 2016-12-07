<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">  
 <html>
<head>
	<title>SpringMVC 快速开发平台</title>
	<meta name="decorator" content="blank"/>
	<link rel="Stylesheet" href="${ctx }/static/jerichotab/css/jquery.jerichotab.css" />
	<script type="text/javascript" src="${ctx }/static/jquery/jquery-1.9.1.js"></script>
	<script type="text/javascript" src="${ctx}/static/jquery/jquery-migrate-1.1.1.min.js" ></script>
	<link href="${ctx }/static/static/bootstrap/2.3.1/css_readable/bootstrap.min.css" type="text/css" rel="stylesheet">
	<script src="${ctx }/static/jerichotab/js/jquery.jerichotab.js" type="text/javascript"></script>
	<script src="${ctx }/static/static/bootstrap/2.3.1/js/bootstrap.min.js" type="text/javascript"></script>
	<link href="${ctx }/static/static/bootstrap/2.3.1/awesome/font-awesome.min.css" type="text/css" rel="stylesheet">
	<!--[if lte IE 7]><link href="/jeesite/static/bootstrap/2.3.1/awesome/font-awesome-ie7.min.css" type="text/css" rel="stylesheet" /><![endif]-->
	<!--[if lte IE 6]><link href="/jeesite/static/bootstrap/bsie/css/bootstrap-ie6.min.css" type="text/css" rel="stylesheet" />
	<script src="/jeesite/static/bootstrap/bsie/js/bootstrap-ie.min.js" type="text/javascript"></script><![endif]-->
	<link href="${ctx }/static/static/jquery-select2/3.4/select2.min.css" rel="stylesheet">
	<script src="${ctx }/static/static/jquery-select2/3.4/select2.min.js" type="text/javascript"></script>
	<link href="${ctx }/static/static/jquery-validation/1.11.0/jquery.validate.min.css" type="text/css" rel="stylesheet">
	<script src="${ctx }/static/static/jquery-validation/1.11.0/jquery.validate.min.js" type="text/javascript"></script>
	<link href="${ctx }/static/static/jquery-jbox/2.3/Skins/Bootstrap/jbox.min.css" rel="stylesheet">
	<script src="${ctx }/static/static/jquery-jbox/2.3/jquery.jBox-2.3.min.js" type="text/javascript"></script>
	<script src="${ctx }/static/static/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
	<link href="${ctx }/static/static/My97DatePicker/skin/WdatePicker.css" rel="stylesheet" type="text/css">
	<script src="${ctx }/static/static/common/mustache.min.js" type="text/javascript"></script>
	<link href="${ctx }/static/static/common/jeesite.css" type="text/css" rel="stylesheet">
	<script src="${ctx }/static/static/common/jeesite.js" type="text/javascript"></script>
	<style type="text/css">
		#main {padding:0;margin:0;} #main .container-fluid{padding:0 4px 0 6px;}
		#header {margin:0 0 8px;position:static;} #header li {font-size:14px;_font-size:12px;}
		#header .brand {font-family:Helvetica, Georgia, Arial, sans-serif, 黑体;font-size:26px;padding-left:33px;}
		#footer {margin:8px 0 0 0;padding:3px 0 0 0;font-size:11px;text-align:center;border-top:2px solid #0663A2;}
		#footer, #footer a {color:#999;} #left{overflow-x:hidden;overflow-y:auto;} #left .collapse{position:static;}
		#userControl>li>a{/*color:#fff;*/text-shadow:none;} #userControl>li>a:hover, #user #userControl>li.open>a{background:transparent;}
	</style>
	<script type="text/javascript">
		 	$(document).ready(function() {
			// <c:if test="${tabmode eq '1'}"> 初始化页签
			$.fn.initJerichoTab({
                renderTo: '#right', uniqueId: 'jerichotab',
                contentCss: { 'height': $('#right').height() - tabTitleHeight },
                tabs: [], loadOnce: true, tabWidth: 110, titleHeight: tabTitleHeight
            });//</c:if>
			// 绑定菜单单击事件
			$("#menu a.menu").click(function(){
				// 一级菜单焦点
				$("#menu li.menu").removeClass("active");
				$(this).parent().addClass("active");
				// 左侧区域隐藏
				if ($(this).attr("target") == "mainFrame"){
					$("#left,#openClose").hide();
					wSizeWidth();
					// <c:if test="${tabmode eq '1'}"> 隐藏页签
					$(".jericho_tab").hide();
					$("#mainFrame").show();//</c:if>
					return true;
				}
				// 左侧区域显示
				$("#left,#openClose").show();
				if(!$("#openClose").hasClass("close")){
					$("#openClose").click();
				}
				// 显示二级菜单
				var menuId = "#menu-" + $(this).attr("data-id");
				if ($(menuId).length > 0){
					$("#left .accordion").hide();
					$(menuId).show();
					// 初始化点击第一个二级菜单
					if (!$(menuId + " .accordion-body:first").hasClass('in')){
						$(menuId + " .accordion-heading:first a").click();
					}
					if (!$(menuId + " .accordion-body li:first ul:first").is(":visible")){
						$(menuId + " .accordion-body a:first i").click();
					}
					// 初始化点击第一个三级菜单
					$(menuId + " .accordion-body li:first li:first a:first i").click();
				}else{
					// 获取二级菜单数据
					$.get($(this).attr("data-href"), function(data){
						//if (data.indexOf("id=\"loginForm\"") != -1){
						//	alert('未登录或登录超1111时。请重新登录，谢谢！');
						//	top.location = "${ctx}";
						//	return false;
						//}
						$("#left .accordion").hide();
						$("#left").append(data);
						// 链接去掉虚框
						$(menuId + " a").bind("focus",function() {
							if(this.blur) {this.blur()};
						});
						// 二级标题
						$(menuId + " .accordion-heading a").click(function(){
							$(menuId + " .accordion-toggle i").removeClass('icon-chevron-down').addClass('icon-chevron-right');
							if(!$($(this).attr('data-href')).hasClass('in')){
								$(this).children("i").removeClass('icon-chevron-right').addClass('icon-chevron-down');
							}
						});
						// 二级内容
						$(menuId + " .accordion-body a").click(function(){
							
							$(menuId + " li").removeClass("active");
							$(menuId + " li i").removeClass("icon-white");
							$(this).parent().addClass("active");
							$(this).children("i").addClass("icon-white");
						});
						// 展现三级
						$(menuId + " .accordion-inner a").click(function(){
							
							var href = $(this).attr("data-href");
							if($(href).length > 0){
								$(href).toggle().parent().toggle();
								return false;
							}
							// <c:if test="${tabmode eq '1'}"> 打开显示页签
							return addTab($(this)); // </c:if>
						});
						// 默认选中第一个菜单
						$(menuId + " .accordion-body a:first i").click();
						$(menuId + " .accordion-body li:first li:first a:first i").click();
					});
				}
				// 大小宽度调整
				wSizeWidth();
				return false;
			});
			// 初始化点击第一个一级菜单
			$("#menu a.menu:first span").click();
			// <c:if test="${tabmode eq '1'}"> 下拉菜单以选项卡方式打开
			$("#userInfo .dropdown-menu a").mouseup(function(){
				return addTab($(this), true);
			});// </c:if>
			// 鼠标移动到边界自动弹出左侧菜单
			$("#openClose").mouseover(function(){
				if($(this).hasClass("open")){
					$(this).click();
				}
			});
			
		 	//左侧菜单的点击事件
		 	$("#left li").click(function(){
		 		$("#left li").removeClass("active");
		 	  	$(this).addClass("active");
		 	});
			
			
			function getNotifyNum(){
				$.get("${ctx}/oa/oaNotify/self/count?updateSession=0&t="+new Date().getTime(),function(data){
					var num = parseFloat(data);
					if (num > 0){
						$("#notifyNum,#notifyNum2").show().html("("+num+")");
					}else{
						$("#notifyNum,#notifyNum2").hide()
					}
				});
			}
			//getNotifyNum(); //<c:if test="${oaNotifyRemindInterval ne '' && oaNotifyRemindInterval ne '0'}">
			//setInterval(getNotifyNum, '${oaNotifyRemindInterval}'); //</c:if>
		});
		// <c:if test="${tabmode eq '1'}"> 添加一个页签
		function addTab($this, refresh){
			$(".jericho_tab").show();
			$("#mainFrame").hide();
			$.fn.jerichoTab.addTab({
                tabFirer: $this,
                title: $this.text(),
                closeable: true,
                data: {
                    dataType: 'iframe',
                    dataLink: $this.attr('href')
                }
            }).loadData(refresh);
			return false;
		}// </c:if>
	</script>
</head>
 <body>
	<div id="main" style="width: auto;">
		<div id="header" class="navbar navbar-fixed-top">
			<div class="navbar-inner">
				<div class="brand"><span id="productName">SpringMVC 快速开发平台</span></div>
				<ul id="userControl" class="nav pull-right">
					<li><a href="javascript:#" target="_blank" title="访问网站主页"><i class="icon-home"></i></a></li>
					<li id="themeSwitch" class="dropdown">
						<a class="dropdown-toggle" data-toggle="dropdown" href="#" title="主题切换"><i class="icon-th-large"></i></a>
						<ul class="dropdown-menu">
							<!-- <li><a href="#" onclick="location='/jeesite/theme/default?url='+location.href">默认主题</a></li><li><a href="#" onclick="location='/jeesite/theme/cerulean?url='+location.href">天蓝主题</a></li><li><a href="#" onclick="location='/jeesite/theme/readable?url='+location.href">橙色主题</a></li><li><a href="#" onclick="location='/jeesite/theme/united?url='+location.href">红色主题</a></li><li><a href="#" onclick="location='/jeesite/theme/flat?url='+location.href">Flat主题</a></li> -->
							<li><a href="#" >默认主题</a></li>
							<li><a href="#" >天蓝主题</a></li>
							<li><a href="#" >橙色主题</a></li>
							<li><a href="#" >红色主题</a></li>
							<li><a href="#" >Flat主题</a></li>
							<!-- <li><a href="javascript:cookie('tabmode','1');location=location.href">开启页签模式</a></li> -->
						</ul>
						<!--[if lte IE 6]><script type="text/javascript">$('#themeSwitch').hide();</script><![endif]-->
					</li>
					<li id="userInfo" class="dropdown">
						<a class="dropdown-toggle" style="padding-top: 10px;" data-toggle="dropdown" href="#" title="个人信息">您好, ${loginname}&nbsp;<span id="notifyNum" class="label label-info hide" style="display: none;"></span></a>
						<ul class="dropdown-menu">
							<li><a href="${ctx}/sys/user/list" target="mainFrame"><i class="icon-user"></i>&nbsp; 个人信息</a></li>
							<li><a href="#" target="mainFrame"><i class="icon-lock"></i>&nbsp;  修改密码</a></li>
							<li><a href="#" target="mainFrame"><i class="icon-bell"></i>&nbsp;  我的通知<span id="notifyNum2" class="label label-info hide" style="display: none;"></span></a></li>
						</ul>
					</li>
					<li><a style="padding-top: 10px;" href="${pageContext.request.contextPath }/logout" title="退出登录">退出</a></li>
					<li>&nbsp;</li>
				</ul>
				
				<div class="nav-collapse">
					<ul id="menu" class="nav" style="*white-space:nowrap;float:none;">
						<c:forEach items="${menuListFirst }" var="ml">
							<c:if test="${fn:length(fn:split(ml.parentIds,',')) eq 2}">
								<li class="menu"> 
									<a class="menu" href="javascript:void(0);" data-href="${ctx}/main/menuTree?parentId=${ml.id}" data-id="${ml.id}"><span>${ml.name }</span></a>
								</li>
							</c:if>
						</c:forEach>
					</ul>
				</div><!--/.nav-collapse -->
			</div>
	    </div>
	    <div class="container-fluid">
			<div id="content" class="row-fluid">
				<div id="left" style="width: 160px; height: 385px;">
				</div>
				<div id="openClose" class="close" style="height: 380px;">&nbsp;</div>
				<div id="right" style="height: 385px; width: 1242px;">
					<iframe id="mainFrame" name="mainFrame" src="" style="overflow: visible; height: 385px;" scrolling="yes" frameborder="no" width="100%" height="650"></iframe>
				</div>
			</div>
		    <div id="footer" class="row-fluid">
	            Copyright © 2012-2016 SpringMVC 快速开发平台 - Powered By <a href="#" target="_blank">SpringMVC</a> V1.0.0
			</div>
		</div>
	</div>
	<script type="text/javascript"> 
		var leftWidth = 160; // 左侧窗口大小
		var tabTitleHeight = 33; // 页签的高度
		var htmlObj = $("html"), mainObj = $("#main");
		var headerObj = $("#header"), footerObj = $("#footer");
		var frameObj = $("#left, #openClose, #right, #right iframe");
		function wSize(){
			var minHeight = 500, minWidth = 980;
			var strs = getWindowSize().toString().split(",");
			htmlObj.css({"overflow-x":strs[1] < minWidth ? "auto" : "hidden", "overflow-y":strs[0] < minHeight ? "auto" : "hidden"});
			mainObj.css("width",strs[1] < minWidth ? minWidth - 10 : "auto");
			frameObj.height((strs[0] < minHeight ? minHeight : strs[0]) - headerObj.height() - footerObj.height() - (strs[1] < minWidth ? 42 : 28));
			$("#openClose").height($("#openClose").height() - 5);// 
			wSizeWidth();
		}
		function wSizeWidth(){
			if (!$("#openClose").is(":hidden")){
				var leftWidth = ($("#left").width() < 0 ? 0 : $("#left").width());
				$("#right").width($("#content").width()- leftWidth - $("#openClose").width() -5);
			}else{
				$("#right").width("100%");
			}
		}// 
	</script>
	<script src="${ctx }/static/static/common/wsize.min.js" type="text/javascript"></script>
</body>
</html>