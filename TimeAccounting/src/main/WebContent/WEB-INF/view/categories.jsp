<%@ include file="/WEB-INF/include/head.jspf"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Categories</title>
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
					<button type="submit" name="currPage" value="categories">
						<fmt:message key='set_language' />
					</button>
				</form>
			</div>
			<form action="logout" method="post">
				<input type="submit" class="logout" name="logout"
					value="<fmt:message key='logout' />">
			</form>
		</div>

		<div class="all_categories_list">
			<div class="table_container">
				<table class="table table-dark table-striped">
					<thead>
						<tr>
							<th scope="col">ID</th>
							<th scope="col"><fmt:message key='name' /></th>
							<th scope="col"><fmt:message key='description' /></th>
							<th scope="col"></th>
						</tr>
					</thead>
					<c:forEach var="category" items="${categoryList}">
						<tbody>
							<tr>
								<td scope="row">${category.id}</td>
								<td>${category.name}</td>
								<td>${category.description}</td>
								<td><form action="deleteCategory" method="post">
										<button class="delete_category_btn" name="categoryId"
											value="${category.id}">&#9932;</button>
									</form></td>
							</tr>
						</tbody>
					</c:forEach>
					<form action="appendCategory" method="post">
						<tbody>
							<tr>
								<td scope="row"></td>
								<td><input type="text" class="input_field"
									name="categoryName" placeholder="<fmt:message key='category_name' />"></td>

								<td><input name="categoryDescr" type="text"
									placeholder="<fmt:message key='category_description' />"></td>

								<td><input type="submit" class="confirm_btn" name="fill"
									value="<fmt:message key='append' />"></td>
							</tr>
						</tbody>
					</form>
				</table>
				<h2>${message}</h2>
				<div class="error_message">${errmsg}</div>
			</div>
		</div>

	</div>
</body>
</html>