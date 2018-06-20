package ar.com.tutuca.mysql.pobladores;

import java.io.File;

public class InsertArchivos {

	static String paths[] = { "/home/mateo/Workspaces/Workspace_Eclipse/T.P. Final/extra/Imagenes/Heladera/im1.jpg/",
			"/home/mateo/Workspaces/Workspace_Eclipse/T.P. Final/extra/Imagenes/Heladera/im2.jpg/",
			"/home/mateo/Workspaces/Workspace_Eclipse/T.P. Final/extra/Imagenes/Heladera/im3.jpg/",
			"/home/mateo/Workspaces/Workspace_Eclipse/T.P. Final/extra/Imagenes/Heladera/im4.jpg/",
			"/home/mateo/Workspaces/Workspace_Eclipse/T.P. Final/extra/Imagenes/Heladera/im5.jpg/",
			"/home/mateo/Workspaces/Workspace_Eclipse/T.P. Final/extra/Imagenes/Heladera/im6.jpg/" };
	
	static int idsProductos[] = {26,28,29,30};

	public static void main(String[] args) {

		for (int i = 0; i < paths.length; i++) {
			File im = new File(paths[i]);

			String path = paths[i];
			String mimeType = "image/jpeg";
			long size = im.length();
			long kb = size / 1024;

			System.out.println(
					"INSERT INTO `Sucursal`.`Archivos` ('PATH', `mimeType`, `Tamaño`) VALUES ((LOAD_FILE('" + path
							+ "')), '" + mimeType + "', '" + kb + " ');");
			
		}
		
		for (int i = 0; i < idsProductos.length; i++) {
			int cantIm = (int) (Math.random() * 5) + 1;
			for (int j = 1; j <= cantIm; j++) {
				System.out.println("INSERT INTO `Sucursal`.`Productos_Archivos` (`idProductos`, `idArchivos`) VALUES ('" + idsProductos[i] + "', '"+ j +"');");
			}
		}
	}
}
