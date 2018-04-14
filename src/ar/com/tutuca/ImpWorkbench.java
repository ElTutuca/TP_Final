package ar.com.tutuca;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.event.TableModelListener;

import ar.com.tutuca.dao.CategoriaDAO;
import ar.com.tutuca.extras.GenericDAO;
import ar.com.tutuca.extras.GenericModel;
import ar.com.tutuca.extras.PersistenciaException;
import ar.com.tutuca.model.Categoria;
import ar.com.tutuca.model.CategoriaIva;
import ar.com.tutuca.model.Mayorista;

public class ImpWorkbench {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while(true) {
			System.out.print("Ingrese algo: ");
			String str = "";
			str = scanner.nextLine();
			int type = str.matches("[a-zA-Z\\s(?)?:?=?.?,?-?/?]+") ? 1 : 2;
			if(type == 1) {
				System.out.println();
				System.out.println(type);
			} else {
				System.out.println();
				System.err.println(type);
			}			
		}
	}
}
