package ar.com.tutuca.dao;

import ar.com.tutuca.model.Mayorista;

public class TestDAO {

	public static void main(String[] args) throws PersistenciaException {
		MayoristaDAO mayDAO = new MayoristaDAO();
		
		marco("mayorista");
		for (Mayorista m : mayDAO.list()) {
			System.out.println(m);
		}
		
	}
	
	public static void marco(String titulo) {
		System.out.println("-------------------");
		System.out.println(titulo.toUpperCase());
		System.out.println("-------------------");
	}

}
