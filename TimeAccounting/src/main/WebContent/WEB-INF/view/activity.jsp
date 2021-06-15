<%@ include file="/WEB-INF/include/head.jspf"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Activities</title>
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
					<button type="submit" name="currPage" value="activityList">
						<fmt:message key='set_language' />
					</button>
				</form>
			</div>
			<form action="logout" method="post">
				<input type="submit" class="logout" name="logout"
					value="<fmt:message key='logout' />">
			</form>
		</div>

		<div class="all_activities_list">
			<div class="list_controllers">
				<form action="orderActByName" method="get">
					<button name="sort" value="asc">
						<fmt:message key='name' />
						&#9650;
					</button>
					<button name="sort" value="desc">
						<fmt:message key='name' />
						&#9660;
					</button>
				</form>
				<form action="orderActByCategory" method="get">
					<button name="sort" value="asc">
						<fmt:message key='category' />
						&#9650;
					</button>
					<button name="sort" value="desc">
						<fmt:message key='category' />
						&#9660;
					</button>
				</form>
				<form action="orderActByUserCount" method="get">
					<button name="sort" value="asc">
						<fmt:message key='users' />
						&#9650;
					</button>
					<button name="sort" value="desc">
						<fmt:message key='users' />
						&#9660;
					</button>
				</form>
				<form action="filterActivity" method="get">
					<select name="categoryId">
						<c:forEach var="category" items="${categories}">
							<option value="${category.id}">${category.name}</option>
						</c:forEach>
					</select>
					<button name="filter" value="categoryId">
						<fmt:message key='filter' />
					</button>
				</form>
			</div>

			<div class="navs_btns">
				<div class="previous_btn">
					<c:choose>
						<c:when test="${page - 1 > 0}">
							<a class="previous_btn"
								href="activities?page=${page-1}&pageSize=${pageSize}"><fmt:message
									key='previous' /></a>
						</c:when>
					</c:choose>
				</div>
				<div class="number_navs">
					<c:forEach var="p" begin="${downLimitPage}" end="${upperLimitPage}">
						<c:choose>
							<c:when test="${page == p}">
								<div class="cur_page">${p}</div>
							</c:when>
							<c:otherwise>
								<a class="number_link" href="activities?page=${p}">${p}</a>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</div>
				<div class="next_btn">
					<c:choose>
						<c:when test="${page + 1 <= pageCount}">
							<a class="next_btn" href="activities?page=${page+1}"><fmt:message
									key='next' /></a>
						</c:when>
					</c:choose>
				</div>
			</div>

			<div class="activity_element">
				<table class="table table-dark table-striped">
					<thead>
						<tr>
							<th scope="col">ID</th>
							<th scope="col"><fmt:message key='name' /></th>
							<th scope="col"><fmt:message key='category' /></th>
						</tr>
					</thead>



					<c:forEach var="activity" items="${activitiesList}"
						begin="${(page-1)*portion}"
						end="${((page-1)*portion + portion) > listLen ? listLen : ((page-1)*portion + portion - 1)}">
						<tbody>
							<tr>
								<td scope="row">${activity.id}</td>
								<td scope="row">${activity.name}</td>
								<td><c:forEach var="category" items="${categories}">
										<c:if test="${category.id == activity.categoryId}">${category.name}</c:if>
									</c:forEach></td>
							</tr>
						</tbody>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
</body>
</html>