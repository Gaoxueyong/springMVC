<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
	<div class="accordion" id="menu-${parentId }">
		<c:set var="firstMenu" value="0"/>
		<c:forEach items="${menuList}" var="menu" varStatus="idxStatus">
			<c:if test="${fn:length(fn:split(menu.parentIds,',')) eq 3}">
				<c:set var="firstMenu" value="${firstMenu+1}"/>
				<div class="accordion-group">
					<div class="accordion-heading">
						<a class="accordion-toggle" data-toggle="collapse" data-parent="#menu-${parentId }" data-href="#collapse-${menu.id }" href="#collapse-${menu.id }" title="">
						<i class="icon-chevron-down"></i>&nbsp;${menu.name }</a>
					</div>
					<div id="collapse-${menu.id}" class="accordion-body collapse <c:if test="${firstMenu eq '1'}">in</c:if>">
						<div class="accordion-inner">
							<ul class="nav nav-list">
								<c:forEach items="${menuList}" var="child" varStatus="idxStatusChild">
									<c:if test="${menu.id eq child.parentId}">
										<li class="">
											<c:choose>
												<c:when test="${not empty child.href}">
													<a data-href=".menu3-${child.id }" href="${ctx}${child.href}" target="mainFrame">
												</c:when>
												<c:otherwise>
												<a data-href=".menu3-${child.id }" href="javascript:void(0);" target="mainFrame">
												</c:otherwise>
											</c:choose>
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
		</c:forEach>
	</div>