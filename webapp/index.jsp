<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Index</title>
</head>
<body>
	<h2>Olá projeto!</h2>
	<form action="businessAction.execute" method="post">
		<input type="hidden" name="business.name" value="name_one">
		<input type="hidden" name="business.value" value="15">
		<input type="hidden" name="business.business.name" value="name_two">
		<input type="hidden" name="business.business.value" value="40">
		
		<input type="submit" value="go!">
	</form>
	
</body>
</html>