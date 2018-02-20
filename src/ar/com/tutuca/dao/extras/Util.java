package ar.com.tutuca.dao.extras;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {
	private static Connection c;

	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		if (c == null) {
			Class.forName("com.mysql.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sucursal", "root", "root");
		}
		return c;
	}

	public static Statement createStatement() throws ClassNotFoundException, SQLException {
		return getConnection().createStatement();
	}

	public static PreparedStatement prepareStatement(String sql) throws ClassNotFoundException, SQLException {
		return getConnection().prepareStatement(sql);

	}
	
	public static int lastId() throws SQLException, ClassNotFoundException {
		ResultSet rs = Util.createStatement().executeQuery("SELECT LAST_INSERT_ID();");
		rs.next();
		return rs.getInt(1);
	}

}
