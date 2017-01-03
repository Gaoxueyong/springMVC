<%@ page contentType="text/html;charset=UTF-8" %>
<%-- <%@ include file="/WEB-INF/views/include/taglib.jsp"%> --%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
	<title>菜单管理</title>
	<meta name="decorator" content="default"/>
	<meta http-equiv="Cache-Control" content="no-cache">
	<meta http-equiv="Cache-Control" content="no-store">
	<script type="text/javascript" src="${ctx }/static/jquery/jquery-1.8.3.min.js"></script> 
	<link href="${ctx }/static/static/bootstrap/2.3.1/css_cerulean/bootstrap.min.css" type="text/css" rel="stylesheet">
	<link href="${ctx }/static/static/bootstrap/2.3.1/awesome/font-awesome.min.css" type="text/css" rel="stylesheet">
	<link href="${ctx }/static/static/jquery-select2/3.4/select2.min.css" rel="stylesheet">
	<link href="${ctx }/static/static/jquery-jbox/2.3/Skins/Bootstrap/jbox.min.css" rel="stylesheet">
	<link href="${ctx }/static/static/My97DatePicker/skin/WdatePicker.css" rel="stylesheet" type="text/css">
	<link href="${ctx }/static/static/common/jeesite.css" type="text/css" rel="stylesheet">
	<script type="text/javascript" src="${ctx }/static/static/bootstrap/2.3.1/js/bootstrap.min.js" ></script>
	<script type="text/javascript" src="${ctx }/static/static/jquery-select2/3.4/select2.min.js" ></script>
	<script type="text/javascript" src="${ctx }/static/static/jquery-jbox/2.3/jquery.jBox-2.3.min.js" ></script>
	<script type="text/javascript" src="${ctx }/static/static/My97DatePicker/WdatePicker.js" ></script>
	<script type="text/javascript" src="${ctx }/static/static/common/mustache.min.js" ></script>
	<script type="text/javascript" src="${ctx }/static/static/common/jeesite.js" ></script>
	<!-- jquery treeTable -->
	<script type="text/javascript" src="${ctx }/static/treeTable/treeTable v1.4.2/script/treeTable/jquery.treeTable.js" ></script>
	<link href="${ctx }/static/treeTable/treeTable v1.4.2/script/treeTable/default/jquery.treeTable.css" rel="stylesheet" type="text/css" />
	<link href="${ctx }/static/treeTable/treeTable v1.4.2/script/treeTable/vsStyle/jquery.treeTable.css" rel="stylesheet" type="text/css" />
	<!-- jquery treeTable -->
	<script type="text/javascript">
	$(function(){
        var option = {
            theme:'vsStyle',
            expandLevel : 5,//展开树的级别
            beforeExpand : function($treeTable, id) {
                //判断id是否已经有了孩子节点，如果有了就不再加载，这样就可以起到缓存的作用
                if ($('.' + id, $treeTable).length) { return; }
                //这里的html可以是ajax请求
                 
                $treeTable.addChilds(html);
            },
            onSelect : function($treeTable, id) {
                window.console && console.log('onSelect:' + id);
            }
        };
        $('#treeTable').treeTable(option);
    });
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sys/menu/list">菜单列表</a></li>
		<shiro:hasPermission name="sys:menu:add"><li><a href="${ctx}/sys/menu/sysMenuForm?parentId=${sysMenu.parentId}">菜单添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="sysMenu" action="${ctx}/sys/menu/list" method="post" class="breadcrumb form-search">
		<input id="currentNo" name="currentNo" type="hidden" value="${page.currentNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>菜单名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="200" />
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page('${page.currentNo}','${page.pageSize}');"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="treeTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="text-align: center;">菜单名称</th>
				<th style="text-align: center;">上级名称</th>
				<th style="text-align: center;">权限标识</th>
				<th style="text-align: center;">连接</th>
				<th style="text-align: center;">图标</th>
				<th style="text-align: center;">显示</th>
				<th style="text-align: center;">排序</th>
				<!-- <th>备注</th> -->
				<th style="text-align: center;">操作</th>
			</tr>
		</thead>
		<tbody id="treeTableList">
		 <c:forEach items="${treeList}" var="menu">
			<tr id="${menu.id }" pId="${menu.parentId }" >
				<td style="text-align: left;">${menu.name}</td>
				<td style="text-align: center;">${menu.parentName }</td>
				<td style="text-align: center;">${menu.permission }</td>
				<td style="text-align: left;">${menu.href }</td>
				<td style="text-align: center;"><i id="iconShow" class="${menu.icon}"></i></td>
				<td style="text-align: center;">
					<c:choose>
						<c:when test="${menu.isShow eq '1'}"><font color="green;">是</font></c:when>
						<c:when test="${menu.isShow eq '0'}"><font color="red">否</font></c:when>
						<c:otherwise></c:otherwise>
					</c:choose>
				</td>
				<td style="text-align: center;">${menu.sort }</td>
				<%-- <td>${menu.remarks }</td> --%>
				<td style="text-align: center;">
					<shiro:hasPermission name="sys:menu:edit"><a href="${ctx}/sys/menu/sysMenuForm?id=${menu.id}">修改</a></shiro:hasPermission>
					<c:if test="${not empty menu.parentName }">
						<shiro:hasPermission name="sys:menu:del"><a href="${ctx}/sys/menu/delSysMenuById?id=${menu.id}" onclick="return confirmx('确认要删除该区域吗？删除后连同其下属的区域也会一并删除！', this.href)">删除</a></shiro:hasPermission>
					</c:if>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	 <!-- <table id="treeTable1" style="width:100%">
                <tr>
                    <td style="width:200px;">标题</td>
                    <td>内容</td>
                </tr>
                <tr id="1">
                    <td><span controller="true">1</span></td>
                    <td>内容</td></tr>
                <tr id="2" pId="1">
                    <td><span controller="true">2</span></td>
                    <td>内容</td></tr>
                <tr id="3" pId="2">
                    <td>3</td>
                    <td>内容</td>
                </tr>
                <tr id="4" pId="2">
                    <td>4</td>
                    <td>内容</td>
                </tr>
                <tr id="5" pId="4">
                    <td>4.1</td>
                    <td>内容</td>
                </tr>
                <tr id="6" pId="1" hasChild="true">
                    <td>5</td>
                    <td>注意这个节点是动态加载的</td>
                </tr>
                <tr id="7">
                    <td>8</td>
                    <td>内容</td>
                </tr>
            </table>
            <hr/>
            <table id="treeTable2" style="width:100%">
                <tr>
                    <td style="width:200px;">标题</td>
                    <td>内容</td>
                </tr>
                <tr id="1">
                    <td><span controller="true">1</span></td>
                    <td>内容</td></tr>
                <tr id="2" pId="1">
                    <td><span controller="true">2</span></td>
                    <td>内容</td></tr>
                <tr id="3" pId="2">
                    <td>3</td>
                    <td>内容</td>
                </tr>
                <tr id="4" pId="2">
                    <td>4</td>
                    <td>内容</td>
                </tr>
                <tr id="5" pId="4">
                    <td>4.1</td>
                    <td>内容</td>
                </tr>
                <tr id="6" pId="1" hasChild="true">
                    <td>5</td>
                    <td>注意这个节点是动态加载的</td>
                </tr>
                <tr id="7">
                    <td>8</td>
                    <td>内容</td>
                </tr>
            </table> -->
</body>
</html>