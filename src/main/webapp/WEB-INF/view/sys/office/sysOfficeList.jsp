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
	<title>机构管理</title>
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
		<li class="active"><a href="${ctx}/sys/office/list?parentId=${sysOffice.parentId}">机构列表</a></li>
		<shiro:hasPermission name="sys:office:add"><li><a href="${ctx}/sys/office/sysOfficeForm?parentId=${sysOffice.parentId}">机构添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="sysOffice" action="${ctx}/sys/office/list" method="post" class="breadcrumb form-search">
		<ul class="ul-form">
			<li><label>名称：</label>
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
				<th style="text-align: center;width: 20%;">机构名称</th>
				<th style="text-align: center;width: 20%;">归属区域</th>
				<th style="text-align: center;">备注</th>
				<th style="text-align: center;width: 10%;">操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${treeList}" var="office">
			<tr id="${office.id }" pId="${office.parentId }">
				<td  style="text-align: left;">${office.name}</td>
				<td  style="text-align: center;">${office.parentName}</td>
				<td  style="text-align: left;">${office.remarks }</td>
				<td  style="text-align: center;">
					<shiro:hasPermission name="sys:office:edit"><a href="${ctx}/sys/office/sysOfficeForm?id=${office.id}">修改</a></shiro:hasPermission>
					<c:if test="${not empty office.parentName }">
						<shiro:hasPermission name="sys:office:del"><a href="${ctx}/sys/office/delSysOfficeById?id=${office.id}" onclick="return confirmx('确认要删除该组织吗？删除后该组织的下级组织也会被删除', this.href)">删除</a></shiro:hasPermission>
					</c:if>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</body>
</html>