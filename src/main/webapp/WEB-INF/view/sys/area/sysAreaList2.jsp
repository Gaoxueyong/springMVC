<%@ page contentType="text/html;charset=UTF-8" %>
<%-- <%@ include file="/WEB-INF/views/include/taglib.jsp"%> --%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
	<title>区域管理</title>
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
		<li class="active"><a href="${ctx}/sys/area/list?parentId=${sysArea.parentId}">机构列表</a></li>
		<li><a href="${ctx}/sys/area/sysAreaForm?parentId=${sysArea.parentId}">区域添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="sysArea" action="${ctx}/sys/area/list?parentId=${sysArea.parentId}" method="post" class="breadcrumb form-search">
		<input id="currentNo" name="currentNo" type="hidden" value="${page.currentNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>名称：</label>
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
				<th style="text-align: center;width:20%"">归属区域</th>
				<th style="text-align: center;width:20%">区域编码</th>
				<th style="text-align: center;width:10%"">排序</th>
				<th style="text-align: center;">备注</th>
				<th style="text-align: center;width:10%"">操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="area">
			<tr>
				<td  style="text-align: center;">${area.name}</td>
				<td  style="text-align: center;">${area.id }</td>
				<td  style="text-align: center;">${area.sort }</td>
				<td  style="text-align: left;">${area.remarks }</td>
				<td  style="text-align: center;">
					<a href="${ctx}/sys/area/sysAreaForm?id=${area.id}">修改</a>
					<c:if test="${not empty area.parentName }">
					<a href="${ctx}/sys/area/delSysAreaById?id=${area.id}" onclick="return confirmx('确认要删除该区域吗？删除后连同其下属的区域也会一并删除！', this.href)">删除</a>
					</c:if>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page.pageInfo}</div>
</body>
</html>