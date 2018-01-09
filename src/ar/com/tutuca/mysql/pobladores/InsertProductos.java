package ar.com.tutuca.mysql.pobladores;

public class InsertProductos {
	
	static String fabricantes[] = {"TOSHIBA","SAMSUNG","KINGSTON","WESTERN DIGITAL"};
	static String modelos[] = {"NVidia ","AMD "};
	
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
	static int contador = 7;
	static int aux = 1;
	
	public static void main(String[] args) {
		
		for (int i = 0; i < 1; i++) {
			
			//idProducto
			contador++;
			
			//Precio
			int max = 30000;
			int min = 900;
			precio = ((int) (Math.random() * (max - min) + min));
			
			//Deposito?
			int rand = (int) (Math.random() * 10) + 1;
			deposito = rand == 1 ? 0 : 1;
			
			//Stock
			stockMaximo = (int) (precio * -0.0015 + 70);
			stockMinimo = (int) (precio * -0.00065 + 30);
			stockIdeal = stockMinimo + ((stockMaximo - stockMinimo) / 2);
			stock = stockIdeal + ((int) (Math.random() * 20) + 5);
			
			//Descuento
			int chances = (int) ((Math.random() * 5) + 1);
			descuento = chances == 1 ? ((int) (Math.random() * 50 + 1)) : 0;
			
			//Puntos
			puntosReq = (int) ((precio / 2) * 1.2);
			
			

			idMayorista = 1;
			idSubcategoria = 1;
			idMarca = 007;
			
			System.out.printf(
					"INSERT INTO `Sucursal`.`Productos` (`Precio`, `Nombre`, `Deposito`, `StockMaximo`, `StockMinimo`, `StockIdeal`, `Stock`, `Descuento`, `PuntosReq`, `idMayorista`, `idSucursal`, `idMarca`)"
							+ " VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s');\n",
					precio, nombre, deposito, stockMaximo, stockMinimo, stockIdeal, stock, descuento, puntosReq,
					idMayorista, idSucursal, idMarca);

			System.out.printf(
					"INSERT INTO `Sucursal`.`Subcategorias_Productos` (`idProductos`, `idSubcategoria`) VALUES ('%s', '%s');\n",
					contador, idSubcategoria);
			
			
		}
	}
}
