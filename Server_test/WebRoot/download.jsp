<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文件下载实例 - 菜鸟教程</title>
</head>
<body>
<h1>文件下载实例 - 菜鸟教程</h1>
<form method="post" action="/Server_test/DownLoad">
<div>
文件名<input type="text" name="fileName" />
</div>
<div>
用户名：<input type="text" name="userName" />
</div>	
	<br/><br/>
	<input type="submit" value="上传" />
</form>
</body>
</html>