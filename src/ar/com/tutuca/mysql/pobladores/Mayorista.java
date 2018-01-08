package ar.com.tutuca.mysql.pobladores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Mayorista implements IntPoblar {

	String mayoristas[] = { "Distribuidora informatica", "Electrodomesticos plus", "Sound's Owners",
			"Games for y'all" };

	@Override
	public void poblar(Connection c) throws ClassNotFoundException, SQLException {

		PreparedStatement ps = c.prepareStatement("INSERT INTO Mayorista (idMayorista,Nombre) VALUES (?,?)");

		for (int i = 0; i < mayoristas.length; i++) {

			ps.setInt(1, i + 1);
			ps.setString(2, mayoristas[i]);
			ps.execute();

			System.out.println(i + 1 + " / " + mayoristas.length);
		}
		System.out.println("---Done---");
		ps.close();
		c.close();

	}

}
