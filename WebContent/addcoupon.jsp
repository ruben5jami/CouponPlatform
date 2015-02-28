<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ADD COUPON</title>

<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
	<link rel="stylesheet" href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">
	<script src="//code.jquery.com/jquery-1.10.2.js"></script>
	<script src="//code.jquery.com/ui/1.11.2/jquery-ui.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
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
<script>
	$(function() {
		$( "#datepicker" ).datepicker();
	});
	</script>
</head>
<body>
	<div class="container">
	<h2><img src="http://imageshack.com/a/img538/2599/pbnLQO.png">Add Coupon</h2>
	<%
		if(!(boolean)request.getAttribute("dateFormat")){
			out.println("<h3>The correct format for the expiry date is: yyyy-mm-dd hh:mm:ss:ML </h3>");			
		}
	%>
		<form method="get" action="insert">
			<div class="form-group">
				<label for="name">Name:</label>
				<input class="form-control"  type="text" name="coupon_name" placeholder=" Enter Name" required>
			</div>
			<div class="form-group">
				<label for="coupon_description">Coupon description:</label>
				<input class="form-control" type="text" name="coupon_description" placeholder=" Enter a description" required>
			</div>
			<div class="form-group">
				<label for="expiry_date">Coupon Expiry Date:</label>
				<input class="form-control"  type="text" name="coupon_expiry_date" placeholder="yyyy-mm-dd hh:mm:dd" id="datepicker" required>
			</div>
			<div class="form-group">
				<label for="longitude">Coupon Longitude:</label>
				<input class="form-control" type="number" name="coupon_longitude" placeholder=" Enter Longitude" required>
			</div>
			<div class="form-group">
				<label for="latitude">Coupon Latitude:</label>
				<input class="form-control" type="number" name="coupon_latitude" placeholder=" Enter Latitude" required>
			</div>
			<div class="form-group">
				<label for="price">Coupon Price:</label>
				<input class="form-control" type="number"  min="0" name="coupon_price" placeholder=" Enter Price" required>
			</div>
			<div class="form-group">
				<label for="category">Coupon Category:</label>
				<select name="coupon_category" class="form-control" >
				<%
					String[] list = (String[])request.getServletContext().getAttribute("categories");
					if(list != null){
					int size = list.length;
					
					for(int i=0; i< size; i++){
						out.println("<option value="+list[i]+">"+list[i]+"</option>");
					}
					}else{
						System.out.println("not working");
						
					}
				%>
				</select>
			</div>
				<input type="submit" value="Submit" class="btn btn-default">
				<br>
			
		</form>
	
    </div>
	<h2><a href="coupons">Go Back To Main Menu</a></h2>

</body>
</html>