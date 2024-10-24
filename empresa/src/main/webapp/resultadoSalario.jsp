<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
<title>Resultado de Salario</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f9f9f9;
	margin: 0;
	padding: 20px;
	text-align: center;
}

h1 {
	color: #333;
	margin-bottom: 20px;
}

p {
	font-size: 20px;
	color: #555;
	margin: 20px 0;
}

.button-container {
	display: flex;
	justify-content: center;
	flex-wrap: wrap;
	margin: 20px 0;
}

a {
	display: inline-block;
	margin: 10px;
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
	<h1>Resultado de Salario</h1>
	<p>El salario del empleado es: ${salario}</p>
	<a href="salario.jsp">Buscar otro salario</a>
	<a href="index.jsp">Volver</a>
</body>
</html>