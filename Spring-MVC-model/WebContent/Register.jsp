<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>  
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>  
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
request.setAttribute("basePath", basePath);  
%>  
<!DOCTYPE html>  
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">  
<title>FileUpload</title>  
</head>  

<style type="text/css">
	.inputstyle{
		color: #FF0000;
		font-weight: bold;
		margin-right: 5px;
		margin-top: 5px;
		margin-left:5px;
		width: 100px;
	}
</style>

<body>  

	<form action="${basePath}/register" method="post" enctype="multipart/form-data">  
	    <label>用&nbsp&nbsp户&nbsp&nbsp名：</label><input type="text" name="name" class="inputstyle"/><br/>  
	    <label>密　　码：</label><input type="password" name="password" class="inputstyle"/><br/>  
	    <label>年　　龄：</label><input type="text" name="age" class="inputstyle"/><br/>  
	    <label>号　　码：</label><input type="text" name="phone" class="inputstyle"/><br/>  
	    <label>出生日期：</label><input type="text" name="birth" class="inputstyle"/><br/>  
	    <label>email：</label><input type="text" name="email" class="inputstyle"/><br/>  
	    <label>头　　像：</label><input type="file" name="file"/><br/>  
	    <input type="submit" value="提  交"/>  
	</form>  
</body>  
</html>