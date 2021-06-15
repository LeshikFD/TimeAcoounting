<%@ include file="/WEB-INF/include/head.jspf"%>
<%@ taglib prefix="myTag" tagdir="/WEB-INF/tags/tagfile" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Account</title>
<link href="style/user.css" rel="stylesheet" type="text/css">
</head>
<body>
	<c:if test="${user.roleId == null}">
		<jsp:forward page="authentic.jsp"></jsp:forward>
	</c:if>
	<div class="backstage">
		<div class="head_container">
			<div class="title">
				<div>
					<fmt:message key='time_accounting' />
				</div>
				<div class="user">
					<fmt:message key='user_title' />
					: ${user.firstname} ${user.lastname}
				</div>
			</div>
			<div class="locale_container">
				<form class="locale_elements" action="changeLocale" method="post">
					<select name="locale">
						<c:forEach items="${locales}" var="locale">
							<c:set var="selected"
								value="${locale.key == currentLocale ? 'selected' : '' }" />
							<option value="${locale.key}" ${selected}>${locale.value}</option>
						</c:forEach>
					</select>
					<button type="submit" name="currPage" value="useraccount">
						<fmt:message key='set_language' />
					</button>
				</form>
			</div>
			<form action="logout" method="post">
				<input type="submit" class="logout" name="logout"
					value="<fmt:message key='logout' />">
			</form>
		</div>

		<div class="profile">
			<div class="prof_title">
				<fmt:message key='your_activities' />
				<br>
				<h5>${message}</h5>
				<div class="error_message">${errmsg}</div>
			</div>
			<c:forEach var="entry" items="${userActivities}">
				<c:set var="activity" value="${entry.key}" />
				<div class="activity_container">
					<div class="acivity-content">
						<div class="activity_name">${activity.name}</div>
						<div class="activity_info">
							<fmt:message key='spent_time' />
							: ${activity.timeCount}
							<fmt:message key='min' />
							<br>
							<fmt:message key='last_chages' />
							: ${activity.lastDate}<br>
							<c:forEach var="category" items="${categories}">
								<c:if test="${category.id == activity.categoryId }">
									<fmt:message key='category' />: ${category.name} <br>
									<fmt:message key='description' />: ${category.description}
								</c:if>
							</c:forEach>
						</div>
						<div class="add_time_Form">
							<myTag:appendTimeForm activity="${activity}" />
						</div>
					</div>

					<form action="deleteRequest" method="post">
						<button class="delete_btn" name="activityId"
							value="${activity.id}">&#128465;</button>
					</form>
				</div>
			</c:forEach>


			<form class="btn_locateion" action="fillacitvity" method="get">
				<input type="submit" class="fill_act_btn" name="fill"
					value="<fmt:message key='add_new_activity' />">
			</form>
		</div>
	</div>
</body>
</html>

