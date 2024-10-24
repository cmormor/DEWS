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
    <form action="buscarEmpleado" method="post">
        <label for="dni">DNI:</label>
        <input type="text" id="dni" name="dni">

        <label for="nombre">Nombre:</label>
        <input type="text" id="nombre" name="nombre">

        <label for="sexo">Sexo:</label>
        <select id="sexo" name="sexo">
            <option value="">Escoge opción</option>
            <option value="M">Masculino</option>
            <option value="F">Femenino</option>
        </select>

        <label for="categoria">Categoría:</label>
        <input type="number" id="categoria" name="categoria" min="1" max="10">

        <label for="anyos">Años trabajados:</label>
        <input type="number" id="anyos" name="anyos" min="0">

        <input type="submit" value="Buscar">
        <input type="reset" value="Reiniciar">
    </form>
    <a href="index.jsp">Volver</a>
</body>
</html>
