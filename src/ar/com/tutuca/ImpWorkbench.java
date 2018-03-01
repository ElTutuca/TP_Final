package ar.com.tutuca;

import java.util.ArrayList;
import java.util.List;

import ar.com.tutuca.dao.CategoriaDAO;
import ar.com.tutuca.extras.GenericDAO;
import ar.com.tutuca.extras.GenericModel;
import ar.com.tutuca.extras.PersistenciaException;
import ar.com.tutuca.model.Categoria;

public class ImpWorkbench {

	public static void main(String[] args) {
		
		Categoria c = new Categoria(1, "Hola");
		CategoriaDAO dao = new CategoriaDAO();
		
		List list;
		try {
			list = dao.list();
			GenericModel gm = (GenericModel) list.get(0);
			Object[] r = gm.getFieldsValues();
			System.out.println(r[1]);
		} catch (PersistenciaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
