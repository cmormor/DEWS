<html>
<head>
<title>Menú Principal</title>
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
	margin-bottom: 20px;
}

ul {
	list-style-type: none;
	padding: 0;
	text-align: center;
}

li {
	margin: 15px 0;
}

a {
	display: inline-block;
	text-decoration: none;
	color: #007bff;
	font-size: 18px;
	padding: 15px 25px;
	border: 1px solid #007bff;
	border-radius: 5px;
	transition: background-color 0.3s, color 0.3s, border-color 0.3s;
	width: 200px;
	text-align: center;
}

/* Estilo hover corregido */
a:hover {
	background-color: #007bff; /* Color de fondo cuando se pasa el mouse */
	color: white; /* Color del texto cuando se pasa el mouse */
	border-color: #0056b3; /* Color del borde cuando se pasa el mouse */
}
</style>
</head>
<body>
	<h1>Menú Principal</h1>
	<ul>
		<li><a href="empleados">Mostrar todos los empleados</a></li>
		<li><a href="salario.jsp">Mostrar salario de un empleado</a></li>
		<li><a href="buscarEmpleado.jsp">Modificar datos de un
				empleado</a></li>

	</ul>
</body>
</html>