<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
	<%-- <div class="accordion" id="menu-${param.parentId}">
	<c:set var="firstMenu" value="true"/>
	<c:forEach items="${menuList}" var="menu" varStatus="idxStatus">
		 
	</c:forEach>
	</div> --%>
	<div class="accordion" id="menu-${parentId }">
		<c:set var="firstMenu" value="true"/>
		<c:forEach items="${menuList}" var="menu" varStatus="idxStatus">
			<c:if test="${fn:length(fn:split(menu.parentIds,',')) eq 3}">
				<div class="accordion-group">
					<div class="accordion-heading">
						<a class="accordion-toggle" data-toggle="collapse" data-parent="#menu-${parentId }" data-href="#collapse-${menu.id }" href="#collapse-${menu.id }" title="">
						<i class="icon-chevron-down"></i>&nbsp;${menu.name }</a>
					</div>
					<div id="collapse-${menu.id}" class="accordion-body collapse in">
						<div class="accordion-inner">
							<ul class="nav nav-list">
								<c:forEach items="${menuList}" var="child" varStatus="idxStatusChild">
									<c:if test="${menu.id eq child.parentId}">
										<li class="">
											<a data-href=".menu3-${child.id }" href="${ctx}${child.href}" target="mainFrame">
											<i class="${child.icon }"></i>&nbsp;${child.name }</a>
											<ul class="nav nav-list hide" style="margin: 0; padding-right: 0;"></ul>
										</li>
									</c:if>
								</c:forEach>
							</ul>
						</div>
					</div>
				</div>
			</c:if>
		<!-- <div class="accordion-group">
			<div class="accordion-heading">
				<a class="accordion-toggle" data-toggle="collapse"
					data-parent="#menu-27" data-href="#collapse-71" href="#collapse-71"
					title=""><i class="icon-chevron-right"></i>&nbsp;文件管理</a>
			</div>
			<div id="collapse-71" class="accordion-body collapse ">
				<div class="accordion-inner">
					<ul class="nav nav-list">
						<li><a data-href=".menu3-56" href="#" target="mainFrame"><i
								class="icon-folder-open"></i>&nbsp;文件管理</a>
							<ul class="nav nav-list hide" style="margin: 0; padding-right: 0;">
							</ul></li>
					</ul>
				</div>
			</div>
		</div> -->
		</c:forEach>
	</div>