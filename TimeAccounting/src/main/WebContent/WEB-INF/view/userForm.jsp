<%@ include file="/WEB-INF/include/head.jspf"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
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
				<form action="changeLocale" method="post">
					<select name="locale">
						<c:forEach items="${locales}" var="locale">
							<c:set var="selected"
								value="${locale.key == currentLocale ? 'selected' : '' }" />
							<option value="${locale.key}" ${selected}>${locale.value}</option>
						</c:forEach>
					</select>
					<button type="submit" name="currPage" value="fillacitvity">
						<fmt:message key='set_language' />
					</button>
				</form>
			</div>
			<form action="logout" method="post">
				<input type="submit" class="logout" name="logout"
					value="<fmt:message key='logout' />">
			</form>
		</div>
		
		<div class="return_btn">
			<nav>
				<ul>
					<li><a href="${app}/useraccount"><fmt:message key='return' /></a></li>
				</ul>
			</nav>
		</div>
		<div class="profile">
			<div class="prof_title"><fmt:message key='create_new_activity' /></div>
			<div class="form_container">
				<form class="fill_form_location" action="createActivity"
					method="post">
					<input type="text" class="input_field" name="actName"
						placeholder="<fmt:message key='activity_name' />"> <select name="categoryId">
						<c:forEach var="category" items="${categories}">
							<option value="${category.id}">${category.name}</option>
						</c:forEach>
						<c:out value="${user}" />
					</select> <input type="submit" class="confirm_btn" name="fill"
						value="<fmt:message key='confirm' />">
						<div class="error_message">${errmsg}</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>