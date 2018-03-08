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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GenericABM extends JPanel {
	private JTable table;
	JFrame form;

	/**
	 * Create the panel.
	 */

	public GenericABM(String nombre, GenericDAO dao, JFrame superFrame, int idForm) {
		JScrollPane scrollPane = new JScrollPane();
		
		// Botones y lblTabla
		JButton btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean isOk = setForm(idForm, dao, superFrame, true);
				if (isOk) {
					form.setVisible(true);
					superFrame.setEnabled(false);
				}
			}
		});

		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean isOk = setForm(idForm, dao, superFrame, false);
				if (isOk) {
					form.setVisible(true);
					superFrame.setEnabled(false);
				}
			}
		});

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				List<GenericModel> daoList;

				if (table.getSelectedRow() != -1) {
					try {
						daoList = dao.list();
						GenericModel gm = daoList.get(table.getSelectedRow());
						int response = JOptionPane.showConfirmDialog(null, "Esta seguro que desea eliminar?",
								"Confirme", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
						if (response == JOptionPane.YES_OPTION) {
							dao.delete(gm);
							table.setModel(new ModeloTabla(dao.list()));
						}
					} catch (PersistenciaException e) {
						JOptionPane.showMessageDialog(superFrame, "No se puede borrar porque esta siendo usado.",
								"ERROR", JOptionPane.ERROR_MESSAGE);
						e.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(superFrame,
							"Tiene que elegir una fila en la tabla para poder eliminar.", "Precaucion",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		
		JButton btnRefresh = new JButton("Refrescar");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					table.setModel(new ModeloTabla(dao.list()));
				} catch (PersistenciaException e) {
					e.printStackTrace();
				}
			}
		});
		JLabel lblTabla = new JLabel(nombre);
		
		// Ubicacion de todo el panel
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
		
		// Setteo de la tabla

		try {
			table = new JTable(new ModeloTabla(dao.list()));
			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					if (arg0.getClickCount() == 2) {
						// TODO JDialog a Producto
					}
				}
			});
		} catch (PersistenciaException e1) {
			e1.printStackTrace();
		}
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		setLayout(groupLayout);

	}

	private boolean setForm(int idForm, GenericDAO dao, JFrame superFrame, boolean isAlta) {
		if (!isAlta) {
			if (table.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(this, "Para modificar tiene que elegir una fila de la tabla.",
						"Precaucion", JOptionPane.WARNING_MESSAGE);
				return false;
			}
		}
		if (idForm == 1) {
			form = new MayoristaForm(superFrame, isAlta, dao, table, true);
			return true;
		} else if (idForm == 2) {
			form = new MayoristaForm(superFrame, isAlta, dao, table, false);
			return true;
		} else if (idForm == 3) {
			// TODO form = new ProductoForm();
		}
		return false;
	}
}