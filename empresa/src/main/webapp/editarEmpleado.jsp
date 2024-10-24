<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.app.Laboral.Empleado"%>
<%@ page import="com.app.DAO.EmpresaDAO"%>
<%
String dni = request.getParameter("dni");
EmpresaDAO empleadoDAO = new EmpresaDAO();
Empleado empleado = empleadoDAO.getEmpleadoByDNI(dni);
%>
<!DOCTYPE html>
<html>
<head>
<title>Editar Empleado</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f4f4f4;
	margin: 0;
	padding: 20px;
}

h2 {
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

input[type="text"], select {
	width: 100%;
	padding: 10px;
	border: 1px solid #ccc;
	border-radius: 4px;
	margin-bottom: 10px;
}

input[type="submit"] {
	background-color: #007bff;
	color: white;
	border: none;
	border-radius: 5px;
	padding: 10px;
	cursor: pointer;
	font-size: 16px;
}

input[type="submit"]:hover {
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
	<h2>Editar Empleado</h2>
	<form action="actualizarEmpleado" method="post">
		<input type="hidden" name="dni" value="<%=empleado.getDni()%>">

		<label>Nombre:</label> <input type="text" name="nombre"
			value="<%=empleado.getNombre()%>"><br> <label>Sexo:</label>
		<select id="sexo" name="sexo">
			<option value="M" ${empleado.sexo.equals('M') ? 'selected' : ''}>Masculino</option>
			<option value="F" ${empleado.sexo.equals('F') ? 'selected' : ''}>Femenino</option>
		</select> <label>Categoría:</label> <input type="text" name="categoria"
			value="<%=empleado.getCategoria()%>"><br> <label>Años
			Trabajados:</label> <input type="text" name="anyos"
			value="<%=empleado.getAnyos()%>"><br> <input
			type="submit" value="Actualizar">
	</form>
</body>
</html>
