<html>
<head>
<title>Men� Principal</title>
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

a:hover {
	background-color: #007bff;
	color: white;
	border-color: #0056b3;
}
</style>
</head>
<body>
	<h1>Bienvenido a la Gesti�n de N�minas</h1>
	<nav>
		<ul>
			<li><a href="controlador?action=mostrarEmpleados">Mostrar
					Empleados</a></li>
			<li><a href="controlador?action=buscarEmpleado">Buscar
					Empleado</a></li>
			<li><a href="controlador?action=mostrarSalarios">Mostrar
					Salario</a></li>
		</ul>
	</nav>
</body>
</html>