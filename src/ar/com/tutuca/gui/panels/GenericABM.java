package ar.com.tutuca.gui.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import ar.com.tutuca.extras.GenericDAO;
import ar.com.tutuca.extras.GenericModel;
import ar.com.tutuca.extras.PersistenciaException;
import ar.com.tutuca.gui.forms.MayoristaForm;
import ar.com.tutuca.gui.tables.ModeloTabla;

public class GenericABM extends JPanel {
	private JTable table;
	JFrame form;

	/**
	 * Create the panel.
	 */

	// Pide una tabla y formularios para las ventanas de "Nuevo", "Modificar",
	// "Eliminar". (Posiblemente un DAO)
	public GenericABM(String nombre, GenericDAO dao, JFrame superFrame, int idForm) {
		JScrollPane scrollPane = new JScrollPane();

		JButton btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setForm(idForm, dao, superFrame);
				form.setVisible(true);
				superFrame.setEnabled(false);
			}
		});

		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setForm(idForm, dao, superFrame);
				form.setVisible(true);
				superFrame.setEnabled(false);
			}
		});

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				List<GenericModel> daoList;
				try {
					if (table.getSelectedRow() != -1) {
						daoList = dao.list();
						GenericModel gm = daoList.get(table.getSelectedRow());
						int response = JOptionPane.showConfirmDialog(null, "Esta seguro que desea eliminar?",
								"Confirme", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
						if (response == JOptionPane.YES_OPTION) {
							dao.delete(gm);
							table.setModel(new ModeloTabla(dao));
						}
					} else {
						JOptionPane.showMessageDialog(superFrame,
								"Tiene que elegir una fila en la tabla para poder eliminar.", "Precaucion",
								JOptionPane.ERROR_MESSAGE);
					}

				} catch (PersistenciaException e) {
					e.printStackTrace();
				}
			}
		});

		JLabel lblTabla = new JLabel(nombre);

		JButton btnRefresh = new JButton("Refrescar");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				table.setModel(new ModeloTabla(dao));
			}
		});

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 998, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup().addComponent(btnNuevo).addGap(62)
								.addComponent(btnModificar).addGap(65).addComponent(btnEliminar).addGap(57)
								.addComponent(btnRefresh).addGap(40)
								.addComponent(lblTabla, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup().addGap(35)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(btnNuevo)
										.addComponent(btnModificar).addComponent(btnEliminar).addComponent(btnRefresh)))
						.addGroup(groupLayout.createSequentialGroup().addGap(27).addComponent(lblTabla,
								GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)))
				.addGap(35).addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 548, Short.MAX_VALUE)
				.addContainerGap()));

		table = new JTable(new ModeloTabla(dao));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		setLayout(groupLayout);

	}

	private void setForm(int idForm, GenericDAO dao, JFrame superFrame) {
		if (idForm == 1) {
			form = new MayoristaForm(superFrame, true, dao, table);
		} else if (idForm == 2) {
			
		}
	}
}
