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
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=8,IE=9,IE=10">
	<meta http-equiv="Expires" content="0">
	<meta http-equiv="Cache-Control" content="no-cache">
	<meta http-equiv="Cache-Control" content="no-store">
	<link href="${ctx }/static/static/jquery-validation/1.11.0/jquery.validate.min.css" type="text/css" rel="stylesheet">
	<link href="${ctx }/static/static/bootstrap/2.3.1/css_cerulean/bootstrap.min.css" type="text/css" rel="stylesheet">
	<link href="${ctx }/static/static/bootstrap/2.3.1/awesome/font-awesome.min.css" type="text/css" rel="stylesheet">
	<link href="${ctx }/static/static/jquery-select2/3.4/select2.min.css" rel="stylesheet">
	<link href="${ctx }/static/static/jquery-jbox/2.3/Skins/Bootstrap/jbox.min.css" rel="stylesheet">
	<link href="${ctx }/static/static/My97DatePicker/skin/WdatePicker.css" rel="stylesheet" type="text/css">
	<link href="${ctx }/static/static/common/jeesite.css" type="text/css" rel="stylesheet">
	<script type="text/javascript" src="${ctx }/static/jquery/jquery-1.8.3.min.js"></script>
	<script src="${ctx }/static/static/jquery-validation/1.11.0/jquery.validate.min.js" type="text/javascript"></script>
	<script type="text/javascript" src="${ctx }/static/static/bootstrap/2.3.1/js/bootstrap.min.js" ></script>
	<script type="text/javascript" src="${ctx }/static/static/jquery-select2/3.4/select2.min.js" ></script>
	<script type="text/javascript" src="${ctx }/static/static/jquery-jbox/2.3/jquery.jBox-2.3.min.js" ></script>
	<script type="text/javascript" src="${ctx }/static/static/My97DatePicker/WdatePicker.js" ></script>
	<script type="text/javascript" src="${ctx }/static/static/common/mustache.min.js" ></script>
	<script type="text/javascript" src="${ctx }/static/static/common/jeesite.js" ></script>
	<head>
	<title>区域管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
		
		//弹出选择area的对话框
		function showArea(){
			top.$.jBox.open("iframe:${ctx}/sys/area/sysAreaTree?id&module=${module}&checked=${checked}&extId=${extId}&isAll=${isAll}", "选择${title}", 300, $(top.document).height()-200, {
				buttons:{"确定":"ok", "关闭":true}, 
				loaded:function(h){
					$(".jbox-content", top.document).css("overflow-y","hidden");
				},submit: function (v, h, f) { 
					if(h.length>0){
					 var  array = h[0].firstChild.contentWindow.callBackFu();
					 if(array.length>0){
						 var even = array[0];
						 var treeId = array[1];
						 var treeNode = array[2];
						 //treeNode.id   4 treeNode.name = '历城区'  treeNode.pId 3  treeNode.pIds 0,1,2
						 $("#parentId").val(treeNode.id);
						 $("#parentIds").val(treeNode.pIds+treeNode.id+",");
						 $("#parentName").val(treeNode.name);
					 }
					}
				}	
			})
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/sys/area/list">区域列表</a></li>
		<li class="active"><a href="${ctx}/sys/area/sysAreaForm">区域${not empty office.id?'修改':'添加'}查看</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="sysArea" action="${ctx}/sys/area/saveSysArea" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<%-- <c:if test="${not empty sysArea.parentName }"> --%>
		 <div class="control-group">
			<label class="control-label">上级区域:</label>
			<div class="controls">
				<div class="input-append">
					<input id="parentId" name="parentId" class="" type="hidden" value="${sysArea.parentId }">
					<input id="parentIds" name="parentIds" class="" type="hidden" value="${sysArea.parentIds}" >
					<input id="parentName" name="parentName" readonly="readonly"   type="text" value="${sysArea.parentName}" data-msg-required="" class="required" style=""><a id="areaButton" href="javascript:" onclick="showArea();" class="btn  " style="">&nbsp;<i class="icon-search"></i>&nbsp;</a>&nbsp;&nbsp;
				</div>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<%-- </c:if> --%>
		<div class="control-group">
			<label class="control-label">区域名称:</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="50" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">区域编码:</label>
			<div class="controls">
				<form:input path="code" htmlEscape="false" maxlength="50"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">排序:</label>
			<div class="controls">
				<form:input path="sort" htmlEscape="false" maxlength="50"/>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">区域类型:</label>
			<div class="controls">
				<form:select path="type" class="input-medium">
					<form:options items="${fns:getDictList('sys_area_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label">备注:</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="3" maxlength="200" class="input-xlarge"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="sys:area:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>