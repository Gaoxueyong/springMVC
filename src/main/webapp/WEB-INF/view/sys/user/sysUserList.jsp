<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
	<title>通知管理</title>
	<meta name="decorator" content="default"/>
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=8,IE=9,IE=10">
	<meta http-equiv="Expires" content="0">
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
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
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
		<li class="active"><a href="${ctx}/sys/user/list">用户列表</a></li>
		<li><a href="${ctx}/sys/user/sysUserForm">用户添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="sysUser" action="${ctx}/sys/user/list" method="post" class="breadcrumb form-search">
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
				<th style="text-align: center;">名称</th>
				<th style="text-align: center;">登录名</th>
				<th style="text-align: center;">状态</th>
				<th style="text-align: center;">创建时间</th>
				<th style="text-align: center;">更新时间</th>
				<th style="text-align: center;">操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="user">
			<tr>
				<td  style="text-align: center;">${user.name}</td>
				<td  style="text-align: center;">
					 ${user.no}
				</td>
				<td  style="text-align: center;">
					 <c:choose>
					 	<c:when test="${user.loginFlag eq '1' }"><font color="green">允许登录</font></c:when>
					 	<c:when test="${user.loginFlag eq '2' }"><font color="red">禁止登录</font></c:when>
					 	<c:otherwise></c:otherwise>
					 </c:choose>
				</td>
				<td  style="text-align: center;">
				<fmt:formatDate value="${user.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td  style="text-align: center;">
					<fmt:formatDate value="${user.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td  style="text-align: center;">
					<a href="${ctx}/sys/user/sysUserForm?id=${user.id}">修改</a>
					<a href="${ctx}/sys/user/delSysUserById?id=${user.id}" onclick="return confirmx('确认要删除该用户吗？', this.href)">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page.pageInfo}</div>
</body>
</html>