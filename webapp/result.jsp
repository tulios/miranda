<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="miranda" uri="/WEB-INF/tags/miranda_taglib.tld"%>

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
			<td><miranda:value name="business.name" /></td>
			<td><miranda:value name="business.value" /></td>
		</tr>
		<tr>
			<td><miranda:value name="business.business.name" /></td>
			<td><miranda:value name="business.business.value" /></td>
		</tr>
	</table>
</body>
</html>