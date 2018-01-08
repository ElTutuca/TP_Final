package ar.com.tutuca.mysql;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {

	public static Connection getConnection(String db) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + db, "root", "Cod3m41n14c");
		return c;
	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		Connection c = getConnection("Sucursal");

		DatabaseMetaData metadata = c.getMetaData();

		System.out.printf("%s, %s%n", metadata.getDatabaseProductName(), metadata.getDatabaseProductVersion());

		Statement consulta = c.createStatement();
		ResultSet rs = consulta.executeQuery("SELECT * FROM Clientes");

		while (rs.next()) {
			System.out.printf("%s - %s - %s%n", rs.getInt("idProducto"), rs.getString("producto"),
					rs.getDouble("precio"));
		}

		rs.close();
		c.close();
	}

}
