package ar.com.tutuca.mysql.pobladores;

import java.sql.Connection;
import java.sql.SQLException;

public interface IntPoblar {
	
	public void poblar(Connection c) throws ClassNotFoundException, SQLException;
	
}
