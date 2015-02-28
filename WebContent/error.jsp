<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ERROR</title>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<style>
body {
	background: #fafafa url(http://jackrugile.com/images/misc/noise-diagonal.png);
	color: #444;
	font: 100%/30px 'Helvetica Neue', helvetica, arial, sans-serif;
	text-shadow: 0 1px 0 #fff;
}
.center{
	text-align:center;
}
</style>
</head>
<body>
	<%= "<br><h2>exception: "+((Exception)request.getAttribute("exception")).getMessage() %>
	<%= "<br>cause: "+((Exception)request.getAttribute("exception")).getCause().getMessage()+ "</h2>" %>
	<h2><a href="signin">Go Back To Main Menu</a></h2>
</body>
</html>