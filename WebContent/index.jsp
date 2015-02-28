<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <script type="text/javascript" src="http://code.jquery.com/jquery-1.10.2.min.js"></script>
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
  <style>
  
  body {
	background: #fafafa url(http://jackrugile.com/images/misc/noise-diagonal.png);
	color: #444;

	text-shadow: 0 1px 0 #fff;
	
}
  	.form-control{
  		width:200px;
  	}
  	
  	#my_category{

  		width:200px;
  		
  	}
  	
  	#my_coordinates{

  		width:200px;

  	}
  	
  	#my_allcoupon{

  		width:200px;
  		
  	}
  	table{
  		width:100%;
  		
  		
  	}
  	th{
  		width:420px;
  			
  	}
  	
  	button{
  		position: relative;
  		left:100px;
  	}

	form
	{
		position: relative;
  		left:100px;	
	}
  	
  	td{
  		width:420px;
  		height:200px;
  	
  	}
  	 #center{

  		position: relative;
  		left:100px;	
  	}
  	#space{
  		height: 30px;
  		
  	}
  	
.center{
	text-align:center;
}
  
  	
  </style>
<script>
$(document).ready(function(){
	
	
	$("#adiv").hide();
	$("#button1").click(function(){$("#adiv").toggle(1000);});
	$("#bdiv").hide();
	$("#button2").click(function(){$("#bdiv").toggle(1000);});
});

</script>
</head>
<body>
	
<div class="container">
	<h2 align="center"><img src="http://imageshack.com/a/img903/251/qeHgbQ.png"></h2>
	<h3 align="center">Please Choose One Option</h3>
	<div id="space"></div>
<table><tbody><tr align="right"><th>
	<button class="btn btn-default" id="button1">Categories</button>
<br>
</th><th>
	<button class="btn btn-default" id="button2">Coordinates</button>
<br>
</th><th>
	<a href="couponsuser"><button  id="center" class="btn btn-primary">See all Coupon</button></a>
</th></tr>
<tr><td>
	<form method="get" action="couponbycategory" id="adiv">
		<div class="form-group">
				<label for="category">Coupon Category:</label>
				<select name="coupon_category" class="form-control"  >
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
		<input type="submit" value="Submit" class="btn btn-primary">
	</form>
</td>
<td>
	<form method="get" action="couponbylocation" id="bdiv">
			<div class="form-group">
				<label>Your Location Longitude:</label>
				<input class="form-control" type="number" name="longitude" placeholder=" longitude" required >
			</div>
			<div class="form-group">
				<label>Your Location Latitude:</label>
				<input class="form-control" type="number"  name="latitude" placeholder=" latitude" required >
			</div>
			<input type="submit" value="Submit" class="btn btn-primary">
	</form>
</td>
<td>

</td>
</tr></tbody></table>
</div>
<h2 class="center"><a href="signin" ><button type="button" class="btn btn-info">
		<span class="glyphicon glyphicon-log-in"></span> Back Log In</button></a></h2>
</body>
</html>
