<%@ include file="/WEB-INF/include/head.jspf"%>

<%-- set the locale --%>
<c:if test="${user != null}">
	<fmt:setLocale value="${user.locale}" scope="session" />
</c:if>
<c:if test="${user == null}">
	<fmt:setLocale value="${param.locale}" scope="session" />
</c:if>

<%-- load the bundle (by locale) --%>
<fmt:setBundle basename="resources" />

<%-- set current locale to session --%>
<c:if test="${user != null}">
	<c:set var="currentLocale" value="${user.locale}" scope="session" />
</c:if>
<c:if test="${user == null}">
	<c:set var="currentLocale" value="${param.locale}" scope="session" />
</c:if>

<%
	response.sendRedirect(request.getAttribute("currPage").toString());
%>