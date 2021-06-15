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
					<button type="submit" name="currPage" value="allusers">
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
			<div class="user_list_title">
				<fmt:message key='all_users_list' />
			</div>
			<c:forEach var="entry" items="${usersList}">
				<div class="user_details">
					<div class="current_user">
						User ID: ${entry.id} <br> User first name: ${entry.firstname}
						<br> User last name: ${entry.lastname}
					</div>
					<form action="selectedUser" method="get">
						<button class="user_info_btn" name="currentUser"
							value="${entry.id}">
							<fmt:message key='info' />
						</button>
					</form>
				</div>
			</c:forEach>
		</div>
	</div>
</body>
</html>