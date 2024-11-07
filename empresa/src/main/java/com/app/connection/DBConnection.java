package com.app.connection;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	 private static DBConnection instance;
	    private Connection connection;

	    private static final String URL = "jdbc:mysql://localhost:3306/gestionnominas?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	    private static final String USER = "root";
	    private static final String PASSWORD = "123456";

	    private DBConnection() {
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
	        } catch (SQLException | ClassNotFoundException e) {
	            throw new RuntimeException("Error al conectar con la base de datos", e);
	        }
	    }

	    public static synchronized DBConnection getInstance() {
	        if (instance == null) {
	            instance = new DBConnection();
	        }
	        return instance;
	    }

	    public Connection getConnection() {
	        try {
	            if (connection == null || connection.isClosed()) {
	                connection = DriverManager.getConnection(URL, USER, PASSWORD);
	            }
	        } catch (SQLException e) {
	            throw new RuntimeException("Error al obtener la conexión", e);
	        }
	        return connection;
    }
}