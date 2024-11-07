<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
<title>Buscar Salario de Empleado</title>
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

form {
	max-width: 400px;
	margin: 0 auto;
	padding: 20px;
	background-color: #ffffff;
	border-radius: 8px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

label {
	display: block;
	margin: 10px 0 5px;
}

input[type="text"] {
	width: 100%;
	padding: 10px;
	border: 1px solid #ccc;
	border-radius: 4px;
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

button:hover {
	background-color: #0056b3;
}

a {
	display: block;
	margin-top: 20px;
	text-align: center;
	text-decoration: none;
	color: #007bff;
}

a:hover {
	text-decoration: underline;
}
</style>
</head>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>Mostrar Salarios</title>
</head>
<body>
	<h1>Buscar Salario de Empleado</h1>
    <form action="controlador" method="get">
        <input type="hidden" name="action" value="mostrarSalarios">
        
        <label for="dni">Ingrese el DNI del empleado:</label>
        <input type="text" id="dni" name="dni" required>

        <button type="submit">Buscar Salario</button>
    </form>
    <a href="index.jsp">Volver</a>
</body>

</html>

</html>