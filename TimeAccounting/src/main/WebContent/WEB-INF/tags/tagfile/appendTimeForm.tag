<%@ attribute name="activity" type="com.my.project.model.Activity" required="true"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<form action="addtime" method="post">
	<input class="time_field" type="number" name="addTime" min="0"
		max="480" placeholder="<fmt:message key='spent_time' />">
	<button class="add_time_btn" name="activityBtn" value="${activity.id}">
		<fmt:message key='add' />
	</button>
</form>