<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*, il.ac.shenkar.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ALL COUPONS</title>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<style>
body {
	background: #fafafa url(http://jackrugile.com/images/misc/noise-diagonal.png);
	color: #444;
	font: 100%/30px 'Helvetica Neue', helvetica, arial, sans-serif;
	text-shadow: 0 1px 0 #fff;
}

strong {
	font-weight: bold; 
}

em {
	font-style: italic; 
}

table {
	background: #f5f5f5;
	border-collapse: separate;
	box-shadow: inset 0 1px 0 #fff;
	font-size: 12px;
	line-height: 24px;
	margin: 30px auto;
	text-align: left;
	width: 900px;
}	

th {
	background: url(http://jackrugile.com/images/misc/noise-diagonal.png), linear-gradient(#777, #444);
	border-left: 1px solid #555;
	border-right: 1px solid #777;
	border-top: 1px solid #555;
	border-bottom: 1px solid #333;
	box-shadow: inset 0 1px 0 #999;
	color: #fff;
  font-weight: bold;
	padding: 10px 15px;
	position: relative;
	text-shadow: 0 1px 0 #000;	
}

th:after {
	background: linear-gradient(rgba(255,255,255,0), rgba(255,255,255,.08));
	content: '';
	display: block;
	height: 25%;
	left: 0;
	margin: 1px 0 0 0;
	position: absolute;
	top: 25%;
	width: 100%;
}

th:first-child {
	border-left: 1px solid #777;	
	box-shadow: inset 1px 1px 0 #999;
}

th:last-child {
	box-shadow: inset -1px 1px 0 #999;
}

td {
	border-right: 1px solid #fff;
	border-left: 1px solid #e8e8e8;
	border-top: 1px solid #fff;
	border-bottom: 1px solid #e8e8e8;
	padding: 10px 15px;
	position: relative;
	transition: all 300ms;
}

td:first-child {
	box-shadow: inset 1px 0 0 #fff;
}	

td:last-child {
	border-right: 1px solid #e8e8e8;
	box-shadow: inset -1px 0 0 #fff;
}	

tr {
	background: url(http://jackrugile.com/images/misc/noise-diagonal.png);	
}

tr:nth-child(odd) td {
	background: #f1f1f1 url(http://jackrugile.com/images/misc/noise-diagonal.png);	
}

tr:last-of-type td {
	box-shadow: inset 0 -1px 0 #fff; 
}

tr:last-of-type td:first-child {
	box-shadow: inset 1px -1px 0 #fff;
}	

tr:last-of-type td:last-child {
	box-shadow: inset -1px -1px 0 #fff;
}	

tbody:hover td {
	color: transparent;
	text-shadow: 0 0 3px #aaa;
}

tbody:hover tr:hover td {
	color: #444;
	text-shadow: 0 1px 0 #fff;
}
.center{
	text-align: center;
}
</style>
</head>
<body>
<%@ taglib uri="/WEB-INF/tlds/mytld.tld" prefix="tag" %>
		<tag:date/>
	<h2 class="center"><img src="http://imageshack.com/a/img903/251/qeHgbQ.png">Coupons</h2>
		<table>
			<thead>
				<tr>
					<th>id</th>
					<th>Name</th>
					<th>Description</th>
					<th>Expiry Date</th>
					<th>Longitude</th>
					<th>Latitude</th>
					<th>Price</th>
					<th>Category</th>
					<th>Admin Options</th>
				</tr>
			</thead>
			<tbody>
<%
Collection<Coupon> collection = (Collection<Coupon>)request.getAttribute("coupons");
for (Coupon coupon : collection) {
	out.println("<tr><td>"+coupon.getId()+"</td><td>"+coupon.getName()+"</td><td>"+coupon.getDescription()+
			"</td><td>"+coupon.getExpiryDate() +
			"</td><td>"+ coupon.getLongitude() +"</td><td>"+coupon.getLatitude() +
			"</td><td>"+coupon.getPrice()+"</td><td>"+coupon.getCategory()+
			"</td><td><a href='delete?coupon_id="+ coupon.getId() + 
			"'><span class='glyphicon glyphicon-trash'></span>Delete </a>"+
			"<a href='updatecoupon?id="+  coupon.getId() + 
			"'><span class='glyphicon glyphicon-pencil'></span> Edit </a></td></tr>");
}
out.println("</tbody></table>");
%>
<h2 class="center"><a href="addcoupon" ><button type="button" class="btn btn-info">
		<span class="glyphicon glyphicon-plus"></span> Add Coupon</button></a></h2>
<h2 class="center"><a href="signout" ><button type="button" class="btn btn-info">
		<span class="glyphicon glyphicon-log-out"></span> Log Out</button></a></h2>
<tag:copyright  firstName="Ruben Rotem Ben"/>
</body>
</html>