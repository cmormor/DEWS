<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
<title>Buscar Empleado</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f4f4f4;
	margin: 0;
	padding: 20px;
}

h1 {
	color: #333;
	text-align: center;
}

form {
	background: #fff;
	padding: 20px;
	border-radius: 5px;
	box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
	max-width: 400px;
	margin: 0 auto;
}

label {
	display: block;
	margin: 10px 0 5px;
	font-weight: bold;
}

button {
	display: inline-block;
	margin-top: 10px;
	padding: 10px 15px;
	border: none;
	border-radius: 5px;
	background-color: #007bff;
	color: white;
	cursor: pointer;
	transition: background-color 0.3s;
}


input[type="text"], input[type="number"], select {
	width: 100%;
	padding: 10px;
	border: 1px solid #ccc;
	border-radius: 4px;
	margin-bottom: 10px;
}

input[type="submit"], input[type="reset"] {
	background-color: #007bff;
	color: white;
	border: none;
	border-radius: 5px;
	padding: 10px;
	cursor: pointer;
	font-size: 16px;
	margin-right: 10px;
}

input[type="submit"]:hover, input[type="reset"]:hover {
	background-color: #0056b3;
}

a {
	display: block;
	text-align: center;
	margin-top: 15px;
	color: #007bff;
	text-decoration: none;
}

a:hover {
	text-decoration: underline;
}
</style>
</head>
<body>
	<h1>Buscar Empleado</h1>
	<form action="controlador" method="get">
		<input type="hidden" name="action" value="buscarEmpleado"> <label
			for="dni">DNI:</label> <input type="text" id="dni" name="dni"><br>

		<label for="nombre">Nombre:</label> <input type="text" id="nombre"
			name="nombre"><br> <label for="sexo">Sexo:</label> <input
			type="text" id="sexo" name="sexo"><br> <label
			for="categoria">Categoría:</label> <input type="text" id="categoria"
			name="categoria"><br> <label for="anyos">Años
			trabajados:</label> <input type="text" id="anyos" name="anyos"><br>

		<button type="submit">Buscar</button>
	</form>

	<a href="index.jsp">Volver</a>

</body>
</html>
