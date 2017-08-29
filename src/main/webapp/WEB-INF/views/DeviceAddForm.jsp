<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
<title>Add AR/IoT Mapping Information</title>
</head>
<body>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
	<div class="jumbotron text-center">
		<h1>Add AR/IoT Mapping Information</h1>
	</div>
	<div class="container">
		<form:form modelAttribute="deviceInfo" method="post" action="/ariotmiddleware/devices/add"  enctype="multipart/form-data">
			<table>
				<tr>
					<td><h2>Middleware Data</h2></td>	
				</tr>
				<tr>
					<td>Device ID:</td>	
					<td><form:input path="deviceId" type="text" /></td>	
				</tr>
				<tr>
					<td><h2>AR Data</h2></td>	
				</tr>
				<tr>
					<td>AR Marker ID <a href="http://au.gmented.com/app/marker/marker.php">(Marker Generator !use 5x5 Barcodes!)</a>:</td>
					<td><form:input path="markerId" type="text" /></td>
				</tr>
				<tr>
					<td><h2>IoT Platform API Information</h2></td>		
				</tr>
				<tr>
					<td>Platform Name:</td>	
					<td><form:input path="platformName" type="text" /></td>	
				</tr>
				<tr>
					<td>URL to the device data:</td>	
					<td><form:input path="url" type="text" /></td>	
				</tr>
				<tr>
					<td>Header (in JSON Format):</td>	
					<td><form:input path="header" type="text" /></td>	
				</tr>
				<tr>
					<td>Body (in JSON Format):</td>	
					<td><form:input path="body" type="text" /></td>	
				</tr>
				<tr>
					<td>Request Method (e.g.:POST,GET):</td>	
					<td><form:input path="method" type="text" /></td>	
				</tr>
				<tr>
					<td>JSON Path to Content in Response (Comma-Separated):</td>	
					<td><form:input path="filter" type="text" /></td>	
				</tr>
				<tr>
					<td>Base64-encoded Response Content?</td>	
					<td><form:checkbox path="base64Encoded" /></td>	
				</tr>
				
				<tr>
					<td><button class="btn" type="submit">Add</button></td>		
				</tr>
			</table>
		</form:form>
		<h3>${message}</h3>
	</div>
	
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>
</body>
</html>