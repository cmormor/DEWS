<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Resultados de Búsqueda</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f9f9f9;
	margin: 0;
	padding: 20px;
}

h1 {
	color: #333;
	text-align: center;
}

table {
	width: 100%;
	border-collapse: collapse;
	margin: 20px 0;
}

th, td {
	padding: 12px;
	text-align: left;
	border: 1px solid #ddd;
}

th {
	background-color: #007bff;
	color: white;
}

tr:nth-child(even) {
	background-color: #f2f2f2;
}

a {
	color: #007bff;
	text-decoration: none;
	padding: 5px 10px;
	border: 1px solid #007bff;
	border-radius: 5px;
	transition: background-color 0.3s, color 0.3s;
}

a:hover {
	background-color: #007bff;
	color: white;
}
</style>
</head>
<body>
	<h1>Resultados de Búsqueda</h1>
	<table border="1">
		<tr>
			<th>Nombre</th>
			<th>DNI</th>
			<th>Sexo</th>
			<th>Categoría</th>
			<th>Años</th>
			<th>Acciones</th>
		</tr>
		<c:forEach var="empleado" items="${empleados}">
			<tr>
				<td>${empleado.nombre}</td>
				<td>${empleado.dni}</td>
				<td>${empleado.sexo}</td>
				<td>${empleado.categoria}</td>
				<td>${empleado.anyos}</td>
				<td><a href="editarEmpleado.jsp?dni=${empleado.dni}">Modificar</a></td>
			</tr>
		</c:forEach>
	</table>
	<a href="index.jsp">Volver</a>
	<a href="buscarEmpleado.jsp">Realizar otra búsqueda</a>
</body>
</html>