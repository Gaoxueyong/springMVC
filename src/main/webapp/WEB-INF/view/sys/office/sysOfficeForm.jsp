<%@ page contentType="text/html;charset=UTF-8" %>
<%-- <%@ include file="/WEB-INF/views/include/taglib.jsp"%> --%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
	<title>机构管理</title>
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
	<title>机构管理</title>
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
		function showOffice(){
			top.$.jBox.open("iframe:${ctx}/sys/office/sysOfficeTree?id&module=${module}&checked=${checked}&extId=${extId}&isAll=${isAll}", "选择${title}", 300, 420, {
				buttons:{"确定":"ok", "关闭":true}, 
				loaded:function(h){
					$("#jbox-iframe").css("height","98%");
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
						 debugger;
						 //treeNode.id   4 treeNode.name = '历城区'  treeNode.pId 3  treeNode.pIds 0,1,2
						 $("#areaId").val(treeNode.id);
						 $("#code").val(treeNode.code);
						 $("#areaName").val(treeNode.name);
					 }
					}
				}	
			})
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/sys/office/list?parentId=${sysOffice.parentId}">机构列表</a></li>
		<li class="active"><a href="${ctx}/sys/office/sysOfficeForm?parentId=${sysOffice.parentId}">机构${not empty sysOffice.id?'修改':'添加'}</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="sysOffice" action="${ctx}/sys/office/saveSysOffice" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<c:if test="${not empty sysOffice.parentName }">
		<div class="control-group">
			<label class="control-label">上级机构:</label>
			<div class="controls">
               <%--  <sys:treeselect id="office" name="parent.id" value="${sysOffice.parent.id}" labelName="parent.name" labelValue="${sysOffice.parent.name}"
					title="机构" url="/sys/office/treeData" extId="${sysOffice.id}" cssClass="" allowClear="${sysOffice.currentUser.admin}"/> --%>
					<input id="parentId" name="parentId" class="" type="hidden" value="${sysOffice.parentId }">
					<input id="parentIds" name="parentIds" class="" type="hidden" value="${sysOffice.parentIds}" >
					<input id="parentName" name="parentName" readonly="readonly"   type="text" value="${sysOffice.parentName}" data-msg-required="" class="required" style=""><a id="areaButton" href="javascript:" onclick="showOffice();" class="btn  " style="">&nbsp;<i class="icon-search"></i>&nbsp;</a>&nbsp;&nbsp;
				</div>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</c:if>
		 <div class="control-group">
			<label class="control-label">归属区域:</label>
			<div class="controls">
				<div class="input-append">
					<input id="areaId" name="areaId" class="" type="hidden" value="${sysOffice.areaId }">
					<input id="code" name="code" class="" type="hidden" value="${sysOffice.code}" >
					<input id="areaName" name="areaName" readonly="readonly"   type="text" value="${sysOffice.areaName}" data-msg-required="" class="required" style=""><a id="areaButton" href="javascript:" onclick="showArea();" class="btn  " style="">&nbsp;<i class="icon-search"></i>&nbsp;</a>&nbsp;&nbsp;
				</div>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">机构名称:</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="50" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">排序:</label>
			<div class="controls">
				<form:input path="sort" htmlEscape="false" maxlength="50"/>
			</div>
		</div>
		<%--
		<div class="control-group">
			<label class="control-label">机构编码:</label>
			<div class="controls">
				<form:input path="code" htmlEscape="false" maxlength="50"/>
			</div>
		</div>
		 <div class="control-group">
			<label class="control-label">机构类型:</label>
			<div class="controls">
				<form:select path="type" class="input-medium">
					<form:options items="${fns:getDictList('sys_office_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">机构级别:</label>
			<div class="controls">
				<form:select path="grade" class="input-medium">
					<form:options items="${fns:getDictList('sys_office_grade')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否可用:</label>
			<div class="controls">
				<form:select path="useable">
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">主负责人:</label>
			<div class="controls">
				 <sys:treeselect id="primaryPerson" name="primaryPerson.id" value="${sysOffice.primaryPerson.id}" labelName="sysOffice.primaryPerson.name" labelValue="${sysOffice.primaryPerson.name}"
					title="用户" url="/sys/office/treeData?type=3" allowClear="true" notAllowSelectParent="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">副负责人:</label>
			<div class="controls">
				 <sys:treeselect id="deputyPerson" name="deputyPerson.id" value="${sysOffice.deputyPerson.id}" labelName="sysOffice.deputyPerson.name" labelValue="${sysOffice.deputyPerson.name}"
					title="用户" url="/sys/office/treeData?type=3" allowClear="true" notAllowSelectParent="true"/>
			</div>
		</div>
		 --%>
		<div class="control-group">
			<label class="control-label">联系地址:</label>
			<div class="controls">
				<form:input path="address" htmlEscape="false" maxlength="50"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">邮政编码:</label>
			<div class="controls">
				<form:input path="zipCode" htmlEscape="false" maxlength="50"/>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">负责人:</label>
			<div class="controls">
				<form:input path="master" htmlEscape="false" maxlength="50"/>
			</div>
		</div> 
		<div class="control-group">
			<label class="control-label">电话:</label>
			<div class="controls">
				<form:input path="phone" htmlEscape="false" maxlength="50"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">传真:</label>
			<div class="controls">
				<form:input path="fax" htmlEscape="false" maxlength="50"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">邮箱:</label>
			<div class="controls">
				<form:input path="email" htmlEscape="false" maxlength="50"/>
			</div>
		</div>
		--%>
		<div class="control-group">
			<label class="control-label">备注:</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="3" maxlength="200" class="input-xlarge"/>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>