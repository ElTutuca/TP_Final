package ar.com.tutuca.mysql.pobladores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Categoria implements IntPoblar {

	String categorias[] = { "Informatica", "Electrodomesticos", "Consolas y videojuegos", "Sonido" };
	String subCategorias[][] = { { "Componentes", "Notebooks", "Teclados", "Mouse" },
			{ "Heladeras", "Lavavajillas", "De cocina" }, { "PlayStation", "PC", "Xbox", "Nintendo", "Arcade" },
			{ "Microfonos", "Parlantes", "Auriculares", "Tocadiscos" } };

	@Override
	public void poblar(Connection c) throws ClassNotFoundException, SQLException {
		PreparedStatement ps1 = c.prepareStatement("INSERT INTO Categoria (idCategoria,Categoria) VALUES (?,?)");
		PreparedStatement ps2 = c.prepareStatement("INSERT INTO Subcategoria (idSubcategoria,Subcategoria,idCategoria) VALUES (?,?,?)");

		int contador = 1;
		for (int i = 0; i < categorias.length; i++) {
			
			ps1.setInt(1, i + 1);
			ps1.setString(2, categorias[i]);
			ps1.execute();
			
			for (int j = 0; j < subCategorias[i].length; j++) {
				
				ps2.setInt(1, contador);
				ps2.setString(2, subCategorias[i][j]);
				ps2.setInt(3, i + 1);
				
				contador++;
				
				ps2.execute();
				
			}

			System.out.println(i + 1 + " / " + categorias.length);
		}
		
		System.out.println("---Done---");
		ps1.close();
		ps2.close();
		c.close();
		
		
	}

}
