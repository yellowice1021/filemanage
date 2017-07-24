<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录 - 菜鸟教程</title>
</head>
<body>
<h1>登录 - 菜鸟教程</h1>
<form name="login" method="POST" action="/Server_test/LoginServlet">
	用户名：<input type="text" name="userName" /><br>
	密码：<input type="text" name="passwd"/><br>
	<input type="submit" value="提交" />
</form>
</body>
</html>