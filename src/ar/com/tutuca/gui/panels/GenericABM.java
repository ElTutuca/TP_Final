package ar.com.tutuca.gui.panels;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import ar.com.tutuca.extras.GenericDAO;
import ar.com.tutuca.gui.tables.ModeloTabla;

public class GenericABM extends JPanel {
	private JTable table;

	/**
	 * Create the panel.
	 */
	
	// Pide una tabla y formularios para las ventanas de "Nuevo", "Modificar", "Eliminar". (Posiblemente un DAO)
	public GenericABM(String nombre, GenericDAO dao) {
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnNuevo = new JButton("Nuevo");
		
		JButton btnModificar = new JButton("Modificar");
		
		JButton btnEliminar = new JButton("Eliminar");
		
		JLabel lblTabla = new JLabel(nombre);
		
		JButton btnRefresh = new JButton("Refrescar");
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 998, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnNuevo)
							.addGap(62)
							.addComponent(btnModificar)
							.addGap(65)
							.addComponent(btnEliminar)
							.addGap(57)
							.addComponent(btnRefresh)
							.addGap(40)
							.addComponent(lblTabla, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(35)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnNuevo)
								.addComponent(btnModificar)
								.addComponent(btnEliminar)
								.addComponent(btnRefresh)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(27)
							.addComponent(lblTabla, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)))
					.addGap(35)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 548, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		table = new JTable(new ModeloTabla(dao));
		scrollPane.setViewportView(table);
		setLayout(groupLayout);

	}
}
