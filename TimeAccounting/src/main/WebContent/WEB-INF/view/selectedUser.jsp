<%@ include file="/WEB-INF/include/head.jspf"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Users</title>
<link href="style/admin.css" rel="stylesheet" type="text/css">
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
					<fmt:message key='admin_title' />
					: ${user.firstname} ${user.lastname}
				</div>
			</div>
			<header>
				<nav>
					<ul>
						<li><a href="${app}/newrequest"><fmt:message
									key='new_request_link' /></a></li>
						<li><a href="${app}/allusers"><fmt:message
									key='users_link' /></a></li>
						<li><a href="${app}/categories"><fmt:message
									key='categories_link' /></a></li>
						<li><a href="${app}/activityList"><fmt:message
									key='activities_link' /></a></li>
					</ul>
				</nav>
			</header>
			<div class="locale_container">
				<form class="locale_elements" action="changeLocale" method="post">
					<select name="locale">
						<c:forEach items="${locales}" var="locale">
							<c:set var="selected"
								value="${locale.key == currentLocale ? 'selected' : '' }" />
							<option value="${locale.key}" ${selected}>${locale.value}</option>
						</c:forEach>
					</select>
					<button type="submit" name="currPage" value="selectedUser">
						<fmt:message key='set_language' />
					</button>
				</form>
			</div>
			<form action="logout" method="post">
				<input type="submit" class="logout" name="logout"
					value="<fmt:message key='logout' />">
			</form>
		</div>
		
		<div class="user_list_container">
			<div class="user_list_title"><fmt:message key='selected_user' />:
				${currentUser.firstname} ${currentUser.lastname}</div>
			<div class="current_user_activities">
				<c:forEach var="entry" items="${userActivities}">
					<c:set var="activity" value="${entry.key}" />
					<div class="selected_user_acivity-content">
							<div class="selected_user_activity_name">${activity.name}</div>
							<div class="selected_user_activity_info">
								<fmt:message key='spent_time' />: ${activity.timeCount} min<br> <fmt:message key='last_chages' />:
								${activity.lastDate}<br>
								<c:forEach var="category" items="${categories}">
									<c:if test="${category.id == activity.categoryId }">
									<fmt:message key='category' />: ${category.name} <br>
									<fmt:message key='description' />: ${category.description}
								</c:if>
								</c:forEach>
							</div>
					</div>
				</c:forEach>
			</div>

		</div>
	</div>
</body>
</html>