<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Result</title>
</head>
<body>
	<h2>Result page</h2>
	<table>
		<tr>
			<td>${business.name}</td>
			<td>${business.value}</td>
		</tr>
		<tr>
			<td>${business.business.name}</td>
			<td>${business.business.value}</td>
		</tr>
	</table>
</body>
</html>