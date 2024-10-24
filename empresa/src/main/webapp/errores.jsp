<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
<title>Error</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f9f9f9;
	text-align: center;
	padding: 50px;
}

h1 {
	color: #d9534f;
	font-size: 48px;
}

p {
	font-size: 20px;
	color: #333;
}

a {
	display: inline-block;
	margin: 20px;
	padding: 10px 15px;
	border: 1px solid #007bff;
	border-radius: 5px;
	color: #007bff;
	text-decoration: none;
	transition: background-color 0.3s, color 0.3s;
}

a:hover {
	background-color: #007bff;
	color: white;
}
</style>
</head>
<body>
	<h1>¡Error!</h1>
	<p>${errorMessage}</p>
	<a href="index.jsp">Volver al Inicio</a>
</body>
</html>