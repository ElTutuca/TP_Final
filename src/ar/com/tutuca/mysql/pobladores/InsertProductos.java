package ar.com.tutuca.mysql.pobladores;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class InsertProductos {

	static String fabricantes[] = { "TOSHIBA", "SAMSUNG", "KINGSTON", "WESTERN DIGITAL" };
	static String modelos[] = { "NVidia ", "AMD " };

	static int precio;
	static String nombre;
	static int deposito;
	static int stockMaximo;
	static int stockMinimo;
	static int stockIdeal;
	static int stock;
	static int descuento;
	static int puntosReq;
	static int idMayorista;
	static int idSucursal = 1;
	static int idMarca;
	static int idSubcategoria;
	static int contador = 30;
	static int aux = 1;
	static boolean done = false;

	public static void main(String[] args) throws IOException {
		PrintWriter pw = new PrintWriter(
				new FileWriter("/home/mateo/Workspaces/Workspace_Eclipse/T.P. Final/test.sql"));
		Scanner scanner = new Scanner(System.in);

		for (int i = 0; done == false; i++) {
			System.out.print("idMarca: ");
			int marca = Integer.parseInt(scanner.nextLine());
			idMarca = marca;

			System.out.print("Nombre: ");
			String nom = scanner.nextLine();
			nombre = nom;

			// idProducto
			contador++;

			// Precio
			int max = 30000;
			int min = 900;
			precio = ((int) (Math.random() * (max - min) + min));

			// Deposito?
			int rand = (int) (Math.random() * 10) + 1;
			deposito = rand == 1 ? 0 : 1;

			// Stock
			stockMaximo = (int) (precio * -0.0015 + 70);
			stockMinimo = (int) (precio * -0.00065 + 30);
			stockIdeal = stockMinimo + ((stockMaximo - stockMinimo) / 2);
			stock = stockIdeal + ((int) (Math.random() * 5) + 5);

			// Descuento
			int chances = (int) ((Math.random() * 5) + 1);
			descuento = chances == 1 ? ((int) (Math.random() * 50 + 1)) : 0;

			// Puntos
			puntosReq = (int) ((precio / 2) * 1.2);

			idMayorista = 3;
			idSubcategoria = 10;

			String in1 = "INSERT INTO `Sucursal`.`Productos` (`Precio`, `Nombre`, `Deposito`, `StockMaximo`, `StockMinimo`, `StockIdeal`, `Stock`, `Descuento`, `PuntosReq`, `idMayorista`, `idSucursal`, `idMarca`)"
					+ " VALUES ('" + precio + "', '" + nombre + "', '" + deposito + "', '" + stockMaximo + "', '"
					+ stockMinimo + "', '" + stockIdeal + "', '" + stock + "', '" + descuento + "', '" + puntosReq
					+ "', '" + idMayorista + "', '" + idSucursal + "', '" + idMarca + "');";

			String in2 = "INSERT INTO `Sucursal`.`Subcategorias_Productos` (`idProductos`, `idSubcategoria`) VALUES ('"
					+ contador + "', '" + idSubcategoria + "');";

			pw.println(in1);
			pw.println(in2);
			pw.flush();

			/*
			 * System.out.printf(
			 * "INSERT INTO `Sucursal`.`Productos` (`Precio`, `Nombre`, `Deposito`, `StockMaximo`, `StockMinimo`, `StockIdeal`, `Stock`, `Descuento`, `PuntosReq`, `idMayorista`, `idSucursal`, `idMarca`)"
			 * +
			 * " VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s');\n"
			 * , precio, nombre, deposito, stockMaximo, stockMinimo, stockIdeal, stock,
			 * descuento, puntosReq, idMayorista, idSucursal, idMarca);
			 */

			/*
			 * System.out.printf(
			 * "INSERT INTO `Sucursal`.`Subcategorias_Productos` (`idProductos`, `idSubcategoria`) VALUES ('%s', '%s');\n"
			 * , contador, idSubcategoria);
			 *
			 * System.out.println();
			 */
		}
	}
}
