package ar.com.tutuca.mysql.pobladores;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		//Clientes y cuentas
		new Clientes().poblar();
		//new Cuentas().poblar(c);
		
		
		//Productos
		//new Mayorista().poblar(c);
		//new Categoria().poblar(c);
		
	}

}
