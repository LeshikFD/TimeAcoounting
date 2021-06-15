<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="mylib" uri="/WEB-INF/tags/customtag/myLib.tld"%>
<!DOCTYPE html>
<html>
<head>
<title>Login</title>
<link href="style/authorize.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div class="backstage">
		<div class="form-container">
			<div class="title">
				<h1>Time accounting</h1>
				<h2>Login</h2>
			</div>
			<form action="authentific" method="post">
				<span class="subtitle">Login</span>
				<br>
				<input name="login" /><br>
				<span class="subtitle">Password</span><br>
				<input type="password" name="password" /><br>
				<div class="message_positioner">
					<input class="submit-btn" type="submit" name="confirm" value="LOGIN" />
					<div class="error_message"><mylib:show-message message="${errmsg}"/></div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>