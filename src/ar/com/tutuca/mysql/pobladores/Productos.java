package ar.com.tutuca.mysql.pobladores;

public class Productos {

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
	static boolean done = false;
	static int contador = 0;

	public static void main(String[] args) {

		for (int i = 1; i <= 10; i++) {
			int may = 1;

			if (i == 5 || i == 6 || i == 7) {
				may = 2;
			} else if (i == 8 || i == 9 || i == 10) {
				may = 3;
			}
			
			String discos[] = discos();
			
			for (int j = 0; done == true; j++) {

				//idProducto
				contador++;
				
				int max = 30000;
				int min = 900;
				precio = ((int) (Math.random() * (max - min) + min));

				int rand = (int) (Math.random() * 6) + 1;
				deposito = rand == 1 ? 0 : 1;

				stockMaximo = (int) (precio * -0.0015 + 70);
				stockMinimo = (int) (precio * -0.00065 + 30);
				stockIdeal = stockMinimo + ((stockMaximo - stockMinimo) / 2);
				stock = stockIdeal + ((int) (Math.random() * 20) + 5);

				int chances = (int) ((Math.random() * 5) + 1);
				descuento = chances == 1 ? ((int) (Math.random() * 50 + 1)) : 0;
				puntosReq = (int) ((precio / 2) * 1.2);

				idMayorista = may;
				idSubcategoria = i;
				
				if(contador >= 0 && contador <= 28) {
					nombre = discos[contador];
				}
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
	
	//Nombre y idMarca
	public static String[] discos() {
		String resultado[] = new String [28];
		//Necesito 3 SSD (128,256,512GB) y 4 HDD(512GB,1T,2T,5T) por marca
		

		//Formato: Tipo "de" ?"GB/T" Marca,id 
		
		int contador = 0;
		String fabricantes[] = {"TOSHIBA,1","SAMSUNG,4","KINGSTON,5","WESTERN DIGITAL,11"};
		//Marcas
		for (int i = 0; i < fabricantes.length; i++) {
			//SSD
			for (int k = 0; k < 3; k++) {
				resultado[contador] = "SSD de " + (128 * (k + 1)) +"GB " + fabricantes[i];
				contador++;
			}
			
			//HDD
			for (int k = 0; k < 4; k++) {
				resultado[contador] = "HDD de " + (512 * (k + 1)) +"GB " + fabricantes[i];
				contador++;
			}
		}
		return resultado;
	}
}
