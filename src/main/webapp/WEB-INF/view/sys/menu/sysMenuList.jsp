<%@ page contentType="text/html;charset=UTF-8" %>
<%-- <%@ include file="/WEB-INF/views/include/taglib.jsp"%> --%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
	<title>菜单管理</title>
	<meta name="decorator" content="default"/>
	<meta http-equiv="Cache-Control" content="no-cache">
	<meta http-equiv="Cache-Control" content="no-store">
	<link href="${ctx }/static/static/bootstrap/2.3.1/css_cerulean/bootstrap.min.css" type="text/css" rel="stylesheet">
	<link href="${ctx }/static/static/bootstrap/2.3.1/awesome/font-awesome.min.css" type="text/css" rel="stylesheet">
	<link href="${ctx }/static/static/jquery-select2/3.4/select2.min.css" rel="stylesheet">
	<link href="${ctx }/static/static/jquery-jbox/2.3/Skins/Bootstrap/jbox.min.css" rel="stylesheet">
	<link href="${ctx }/static/static/My97DatePicker/skin/WdatePicker.css" rel="stylesheet" type="text/css">
	<link href="${ctx }/static/static/common/jeesite.css" type="text/css" rel="stylesheet">
	<script type="text/javascript" src="${ctx }/static/jquery/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="${ctx }/static/static/bootstrap/2.3.1/js/bootstrap.min.js" ></script>
	<script type="text/javascript" src="${ctx }/static/static/jquery-select2/3.4/select2.min.js" ></script>
	<script type="text/javascript" src="${ctx }/static/static/jquery-jbox/2.3/jquery.jBox-2.3.min.js" ></script>
	<script type="text/javascript" src="${ctx }/static/static/My97DatePicker/WdatePicker.js" ></script>
	<script type="text/javascript" src="${ctx }/static/static/common/mustache.min.js" ></script>
	<script type="text/javascript" src="${ctx }/static/static/common/jeesite.js" ></script>
	<link href="${ctx}/static/static/treeTable/themes/vsStyle/treeTable.min.css" rel="stylesheet" type="text/css" />
	<script src="${ctx}/static/static/treeTable/jquery.treeTable.min.js" type="text/javascript"></script>
	<script type="text/javascript">
	function page(n,s){
		$("#currentNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
    	return false;
    }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sys/menu/list?parentId=${sysMenu.parentId}">菜单列表</a></li>
		<li><a href="${ctx}/sys/menu/sysMenuForm?parentId=${sysMenu.parentId}">菜单添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="sysMenu" action="${ctx}/sys/menu/list?parentId=${sysMenu.parentId}" method="post" class="breadcrumb form-search">
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
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="text-align: center;width:10%"">菜单名称</th>
				<th style="text-align: center;width:10%">上级名称</th>
				<th style="text-align: center;width:10%">权限标识</th>
				<th style="text-align: center;">连接</th>
				<th style="text-align: center;width:5%">图标</th>
				<th style="text-align: center;width:5%">显示</th>
				<th style="text-align: center;width:5%"">排序</th>
				<!-- <th style="text-align: center;">备注</th> -->
				<th style="text-align: center;width:10%"">操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="menu">
			<tr>
				<td  style="text-align: center;">${menu.name}</td>
				<td  style="text-align: center;">${menu.parentName }</td>
				<td  style="text-align: center;">${menu.permission }</td>
				<td  style="text-align: left;">${menu.href }</td>
				<td  style="text-align: center;"><i id="iconShow" class="${menu.icon}"></i></td>
				<td  style="text-align: center;">
					<c:choose>
						<c:when test="${menu.isShow eq '1'}"><font color="green;">是</font></c:when>
						<c:when test="${menu.isShow eq '0'}"><font color="red">否</font></c:when>
						<c:otherwise></c:otherwise>
					</c:choose>
				</td>
				<td  style="text-align: center;">${menu.sort }</td>
				<%-- <td  style="text-align: left;">${menu.remarks }</td> --%>
				<td  style="text-align: center;">
					<a href="${ctx}/sys/menu/sysMenuForm?id=${menu.id}">修改</a>
					<c:if test="${not empty menu.parentName }">
					<a href="${ctx}/sys/menu/delSysMenuById?id=${menu.id}" onclick="return confirmx('确认要删除该区域吗？删除后连同其下属的区域也会一并删除！', this.href)">删除</a>
					</c:if>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page.pageInfo}</div>
</body>
</html>