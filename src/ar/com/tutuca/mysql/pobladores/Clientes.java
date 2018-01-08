package ar.com.tutuca.mysql.pobladores;

public class Clientes {

	private String nombres[] = { "Juan", "Mateo", "Carlos", "Maria", "German", "Mariano", "Ernesto", "Milagros",
			"Mariana", "Valeria", "Agustina", "Gonzalo", "Kevin", "Brian", "John", "Ignacio", "Antonela", "Luis",
			"Claudio", "Maximiliano", "Elon", "Donald", "Michael", "Candelaria", "Alejandra", "Alejandro", "Martin",
			"Tobias", "Maximo", "Tadeo", "Enzo", "Lautaro", "Agustin", "Neil", "Clara", "Sol", "Ramiro", "Alexa",
			"Fernando", "Fernanda", "Jorge", "Andrea", "Gustavo", "Tim", "Miguel", "Sofia", "Elena", "Emilia",
			"Valentina", "Santiago", "Pedro", "Matias", "Nicolas", "Santino", "Thiago" };

	private String apellidos[] = { "Gonzalez", "Rodriguez", "Cabaña", "Fernandez", "Lopez", "Diaz", "Martinez", "Perez",
			"Garcia", "Sanchez", "Romero", "Sosa", "Alvarez", "Torres", "Ruiz", "Ramirez", "Flores", "Acosta",
			"Benitez", "Medina", "Suarez", "Herrera", "Aguirre", "Pereyra", "Gutierrez", "Gimenez", "Molina", "Silva",
			"Castro", "Rojas", "Ortiz", "Nuñez", "Luna", "Juarez", "Cabrera", "Rios", "Ferreyra", "Godoy", "Morales",
			"Dominguez", "Moreno", "Peralta", "Vega", "Carrizo", "Quiroga", "Castillo", "Ledesma" };

	private String calles[] = { "Juan Jose Paso", "Sta Maria de Oro", "Falucho", "Av. San Martin", "La Rioja",
			"Remedios de Escalada", "Tablada", "España", "San Carlos", "Carlos Alonso", "Sta. Rosa", "Velez Sardfield",
			"Av. Sarmiento", "Oscar Ferreyra", "Martin Fierro", "Mitre", "Chacabuco", "Gral. Paz", "San Luis",
			"25 de Mayo", "3 de Febrero", "Alberdi", "Lestache", "Saavedra", "Saenz Peña", "Berutti", "Av. Belgrano",
			"Dr. Bregante", "Alem", "Laprida", "Lincoln", "Aristoteles", "Brasil" };

	public void poblar() {

		int lim = 100;
		for (int i = 1; i <= lim; i++) {

			int aux1 = random(0, nombres.length);
			int aux2 = random(0, apellidos.length);
			int aux3 = random(0, calles.length);
			int aux4 = random(1, 500);
			String tel = random(10);
			String dni = random(8);
			
			System.out.printf("INSERT INTO Clientes (idCliente,Nombre,Apellido,Direccion,Telefono,DNI) VALUES (%s,%s,%s,%s,%s,%s);\n",i,nombres[aux1],apellidos[aux2],(calles[aux3] + " " + aux4),tel,dni);
		}
		
	}
	
	private int random(int desde, int hasta) {
		return (int) ((Math.random()) * hasta + desde);
	}

	private String random(int cantDigitos) {
		String resultado = "";
		for (int i = 0; i < cantDigitos; i++) {
			int num = ((int) (Math.random() * 9));
			resultado = resultado + Integer.toString(num);
		}
		return resultado;
	}

}
