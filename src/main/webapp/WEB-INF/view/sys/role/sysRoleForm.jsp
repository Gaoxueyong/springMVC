<%@ page language="java"  pageEncoding="UTF-8"%>  
<%-- <%@ include file="/WEB-INF/views/include/taglib.jsp"%> --%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
	<title>角色管理</title>
	<meta name="decorator" content="default"/>
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
	<meta name="author" content="http://jeesite.com/">
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=8,IE=9,IE=10">
	<meta http-equiv="Expires" content="0">
	<meta http-equiv="Cache-Control" content="no-cache">
	<meta http-equiv="Cache-Control" content="no-store">
	<style type="text/css">
	input[type="text"]{height: 30px;line-height: 30px;}
	input[type=password]{height: 30px;line-height: 30px;}
	</style>
	<link href="${ctx }/static/static/bootstrap/2.3.1/css_cerulean/bootstrap.min.css" type="text/css" rel="stylesheet">
	<link href="${ctx }/static/static/bootstrap/2.3.1/awesome/font-awesome.min.css" type="text/css" rel="stylesheet">
	<link href="${ctx }/static/static/jquery-select2/3.4/select2.min.css" rel="stylesheet">
	<link href="${ctx }/static/static/jquery-jbox/2.3/Skins/Bootstrap/jbox.min.css" rel="stylesheet">
	<link href="${ctx }/static/static/My97DatePicker/skin/WdatePicker.css" rel="stylesheet" type="text/css">
	<link href="${ctx }/static/static/common/jeesite.css" type="text/css" rel="stylesheet">
	<script type="text/javascript" src="${ctx }/static/jquery/jquery-1.8.3.min.js"></script>
	<link href="${ctx }/static/static/jquery-validation/1.11.0/jquery.validate.min.css" type="text/css" rel="stylesheet">
	<script src="${ctx }/static/static/jquery-validation/1.11.0/jquery.validate.min.js" type="text/javascript"></script>
	<script type="text/javascript" src="${ctx }/static/static/bootstrap/2.3.1/js/bootstrap.min.js" ></script>
 	<script type="text/javascript" src="${ctx }/static/static/jquery-select2/3.4/select2.min.js" ></script>
	<script type="text/javascript" src="${ctx }/static/static/jquery-jbox/2.3/jquery.jBox-2.3.min.js" ></script>
	<script type="text/javascript" src="${ctx }/static/static/My97DatePicker/WdatePicker.js" ></script>
	<script type="text/javascript" src="${ctx }/static/static/common/mustache.min.js" ></script>
	<script type="text/javascript" src="${ctx }/static/static/common/jeesite.js" > </script>
	
	<link href="${ctx}/static/static/jquery-ztree/3.5.12/css/zTreeStyle/zTreeStyle.min.css" rel="stylesheet" type="text/css"/>
	<script src="${ctx}/static/static/jquery-ztree/3.5.12/js/jquery.ztree.all-3.5.min.js" type="text/javascript"></script>
	<style type="text/css">
		.ztree {overflow:auto;margin:0;_margin-top:10px;padding:10px 0 0 10px;}
	</style>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#no").focus();
			$("#inputForm").validate({
				rules: {
					//loginName: {remote: "${ctx}/sys/user/checkLoginName?oldLoginName=" + encodeURIComponent('${user.loginName}')}
				},
				messages: {
					loginName: {remote: "用户登录名已存在"},
					confirmNewPassword: {equalTo: "输入与上面相同的密码"}
				},
				submitHandler: function(form){
					debugger;
					var array = [];
					var array_checkedNodes = $.fn.zTree.getZTreeObj("ztree").getCheckedNodes()
					$.each(array_checkedNodes,function(){
						array.push(this.id);
					})
					$("input[name='roleMenus']").val(array.join(","));
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
		
		
		var setting = {data:{simpleData:{enable:true,idKey:"id",pIdKey:"pId",rootPId:'0'}},
			callback:{onClick:function(event, treeId, treeNode){
				
				    }
			},
			check:{
				enable:true
			}
		};
			
		function refreshTree(){
			$.getJSON("${ctx}/sys/menu/sysMenuTreeDataCheckBox?roleId=${sysRole.id}",function(data){
				$.fn.zTree.init($("#ztree"), setting, data.data).expandAll(true);
			});
		}
		refreshTree();
	</script> 
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/sys/role/list">角色列表</a></li>
		<li class="active"><a href="${ctx}/sys/role/sysRoleForm?id=${sysRole.id}">角色${not empty sysRole.id?'修改':'添加'}</a></li>
	</ul><br/>
	<form:form  id="inputForm" modelAttribute="sysRole" action="${ctx}/sys/role/saveSysRole" method="post" class="form-horizontal">
		<form:hidden path="id" htmlEscape="false" maxlength="50" value="${sysRole.id }"   />
		<div class="control-group">
			<label class="control-label">角色名称:</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="50" value="${sysRole.name }"  class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">英文名称:</label>
			<div class="controls">
				<form:input path="enname" htmlEscape="false" value="${sysRole.enname}"  maxlength="50" class="required enname" type="text"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否可用:</label>
			<div class="controls">
				<select name="useable">
					<c:forEach items="${list }" var="ls">
					<option value="${ls.value }" <c:if test="${ls.value eq sysRole.useable}">selected="selected"</c:if>>${ls.lable }</option>
					</c:forEach>
				</select>
				<span class="help-inline"><font color="red">*</font> “是”是否可用，“否”不可用</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">角色资源:</label>
			<div class="controls">
				 <div id="ztree" class="ztree"></div>
				 <input id="roleMenus" name="roleMenus" type="hidden">
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">备注:</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="3" maxlength="200" value="${sysRole.remarks }"  class="input-xlarge"/>
			</div>
		</div>
	 	<c:if test="${not empty sysRole.id}">
			<div class="control-group">
				<label class="control-label">创建时间:</label>
				<div class="controls">
					<label class="lbl"><fmt:formatDate value="${sysRole.createDate}" type="both" dateStyle="full"/></label>
				</div>
			</div>
		</c:if>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>