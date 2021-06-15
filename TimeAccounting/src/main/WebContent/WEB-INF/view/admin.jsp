<%@ include file="/WEB-INF/include/head.jspf"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Account</title>
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
					<button type="submit" name="currPage" value="newrequest">
						<fmt:message key='set_language' />
					</button>
				</form>
			</div>
			<form action="logout" method="post">
				<input type="submit" class="logout" name="logout"
					value="<fmt:message key='logout' />">
			</form>
		</div>

		<div class="action_messages">${message}</div>

		<div class="new_requests_container">
			<div class="new_req_title"><fmt:message key='new_request_for_addition' /></div>

			<c:forEach var="entry" items="${allActivityUser}">
				<c:set var="act" value="${entry.key}" />
				<c:set var="us" value="${entry.value}" />
				<c:forEach var="agreement" items="${allAgreements}">
					<c:if test="${agreement.activityId == act.id}">
						<c:if test="${agreement.statusId == 1}">
							<div class="req_item">
								<div class="req_item_flex">
									<div class="req_info">
										<div class="requested_act_name">
											<fmt:message key='name' />: ${act.name}
											<div class="requested_act_info">
												<c:forEach var="category" items="${categories}">
													<c:if test="${category.id == act.categoryId}"><fmt:message key='category' />: ${category.name} 
												<div><fmt:message key='description' />: ${category.description}</div>
													</c:if>
												</c:forEach>
												<div><fmt:message key='requested' />: ${act.lastDate}</div>
												<div><fmt:message key='user_initials' />: ${us.firstname} ${us.lastname}</div>
											</div>
										</div>
									</div>
									<div class="req_control_btns">
										<form action="confirmact" method="post">
											<button class="act_control_confirm_btn" name="confirmact"
												value="${act.id}"><fmt:message key='confirm' /></button>
										</form>
										<form action="rejectact" method="post">
											<button class="act_control_reject_btn" name="rejectact"
												value="${act.id}"><fmt:message key='reject' /></button>
										</form>
									</div>
								</div>
							</div>
						</c:if>
					</c:if>
				</c:forEach>
			</c:forEach>

			<c:if test="${reqForAddCount == 0}">
				<div class="wrong_req_message"><fmt:message key='no_new_requests_for_addition' /></div>
			</c:if>
		</div>

		<div class="new_requests_container">
			<div class="new_req_title"><fmt:message key='new_request_for_deletion' /></div>
			<c:forEach var="entry" items="${allActivityUser}">
				<c:set var="act" value="${entry.key}" />
				<c:set var="us" value="${entry.value}" />
				<c:forEach var="agreement" items="${allAgreements}">
					<c:if test="${agreement.activityId == act.id}">
						<c:if test="${agreement.statusId == 2}">
							<div class="req_item">
								<div class="req_item_flex">
									<div class="requested_act_name">
										<fmt:message key='name' />: ${act.name}
										<div class="requested_act_info">
											<c:forEach var="category" items="${categories}">
												<c:if test="${category.id == act.categoryId}"><fmt:message key='category' />: ${category.name} 
											<div><fmt:message key='description' />: ${category.description}</div>
												</c:if>
											</c:forEach>
											<div><fmt:message key='last_chages' />: ${act.lastDate}</div>
											<div><fmt:message key='spent_time' />: ${act.timeCount}</div>
											<div><fmt:message key='user_initials' />: ${us.firstname} ${us.lastname}</div>
										</div>
									</div>
									
									<div class="req_control_btns">
										<form action="confirmact" method="post">
											<button class="act_control_cancel_btn" name="cancelact"
												value="${act.id}"><fmt:message key='cancel' /></button>
										</form>
										<form action="rejectact" method="post">
											<button class="act_control_reject_btn" name="rejectact"
												value="${act.id}"><fmt:message key='delete' /></button>
										</form>
									</div>
								</div>
							</div>
						</c:if>
					</c:if>
				</c:forEach>
			</c:forEach>

			<c:if test="${reqForDelCount == 0}">
				<div class="wrong_req_message"><fmt:message key='no_new_requests_for_deletion' /></div>
			</c:if>
		</div>
	</div>
</body>
</html>