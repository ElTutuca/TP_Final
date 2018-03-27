package ar.com.tutuca.gui.forms;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import ar.com.tutuca.dao.ArchivoDAO;
import ar.com.tutuca.dao.ProdArchivosDAO;
import ar.com.tutuca.extras.PersistenciaException;
import ar.com.tutuca.extras.Util;
import ar.com.tutuca.model.Archivo;
import ar.com.tutuca.model.ProdArchivos;

public class ProductoImagenes extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private ArchivoDAO archDAO = new ArchivoDAO();
	private ProdArchivosDAO prodArchDAO = new ProdArchivosDAO(archDAO);
	private static int id;
	private DefaultTableModel m_model;
	private List<ProdArchivos> prodArch;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ProductoImagenes dialog = new ProductoImagenes(id);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ProductoImagenes(int id) {
		table = new JTable();
		ProductoImagenes.id = id;

		setBounds(100, 100, 450, 521);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		JScrollPane scrollPane = new JScrollPane();

		JPanel panelImage = new JPanel();

		// Botones
		JButton btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AltaModifica alta = new AltaModifica(true, table, ProductoImagenes.id, 0);
				alta.setVisible(true);
			}
		});

		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() != -1) {
					refreshProdArch();
					ProdArchivos pa = (ProdArchivos) prodArch.get(table.getSelectedRow());
					AltaModifica modifica = new AltaModifica(false, table, ProductoImagenes.id, pa.getOrden());
					modifica.setVisible(true);
				} else {
					modificarWarn();
				}

			}
		});

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (table.getSelectedRow() != -1) {
					// Lleno la lista con los archivos
					refreshProdArch();

					/*
					 * Saco el archivo elegido
					 */
					ProdArchivos pa = (ProdArchivos) prodArch.get(table.getSelectedRow());
					
					int response = JOptionPane.showConfirmDialog(null, "Esta seguro de que desea eliminar el arcivo?",
							"Confirme", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if (response == JOptionPane.YES_OPTION) {
						try {
							/**
							 * Comprueba si el archivo esta siendo usado en otro lado
							 */
							int count = 0;
							for (ProdArchivos prodArchivos : prodArchDAO.list()) {
								if(prodArchivos.getArch().equals(pa.getArch())) {
									count++;
								}
							}
							prodArchDAO.delete(pa);

							if (count == 1) {
								archDAO.delete(pa.getArch());
							}
							createTable();
						} catch (PersistenciaException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		});

		JButton btnUp = new JButton("Up");
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (table.getSelectedRow() != -1) {
					// Lleno la lista con los archivos
					refreshProdArch();

					/*
					 * Saco el archivo elegido
					 */
					ProdArchivos pa = (ProdArchivos) prodArch.get(table.getSelectedRow());

					/*
					 * Si el orden no es 1, entonces se le resta uno y se actualiza la entidad
					 */
					if (pa.getOrden() != 1) {
						int rowIndex = table.getSelectedRow();
						int columnIndex = table.getSelectedColumn();

						pa.setOrden(pa.getOrden() - 1);
						try {
							prodArchDAO.update(pa);
							rowIndex--;
						} catch (PersistenciaException e) {
							e.printStackTrace();
							return;
						}

						if (table.getSelectedRow() != 0 && table.getRowCount() > 1) {
							ProdArchivos pa1 = (ProdArchivos) prodArch.get(table.getSelectedRow() - 1);
							pa1.setOrden(pa1.getOrden() + 1);
							try {
								prodArchDAO.update(pa1);
								createTable();
							} catch (PersistenciaException e) {
								e.printStackTrace();
								return;
							}
						}

						/*
						 * Actualiza la pos de la seleccion para una mejor facilidad a la hora de subir
						 * muchas veces un mismo archivo
						 */
						table.changeSelection(rowIndex, columnIndex, false, false);
					}
				}
			}
		});

		JButton btnDown = new JButton("Down");
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (table.getSelectedRow() != -1) {
					// Lleno la lista con los archivos
					refreshProdArch();

					/*
					 * Saco el archivo elegido
					 */
					ProdArchivos pa = (ProdArchivos) prodArch.get(table.getSelectedRow());

					/*
					 * Si el orden no es el ultimo, entonces se le suma uno y se actualiza la
					 * entidad
					 */
					if (pa.getOrden() != table.getRowCount()) {
						int rowIndex = table.getSelectedRow();
						int columnIndex = table.getSelectedColumn();

						pa.setOrden(pa.getOrden() + 1);
						try {
							prodArchDAO.update(pa);
							rowIndex++;
						} catch (PersistenciaException e) {
							e.printStackTrace();
							return;
						}

						if (table.getSelectedRow() != table.getRowCount() && table.getRowCount() > 1) {
							ProdArchivos pa1 = (ProdArchivos) prodArch.get(table.getSelectedRow() + 1);
							pa1.setOrden(pa1.getOrden() - 1);
							try {
								prodArchDAO.update(pa1);
								createTable();
							} catch (PersistenciaException e) {
								e.printStackTrace();
								return;
							}
						}

						/*
						 * Actualiza la pos de la seleccion para una mejor facilidad a la hora de bajar
						 * muchas veces un mismo archivo
						 */
						table.changeSelection(rowIndex, columnIndex, false, false);
					}
				}
			}
		});

		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup().addContainerGap().addGroup(gl_contentPanel
						.createParallelGroup(Alignment.TRAILING)
						.addComponent(panelImage, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING,
								gl_contentPanel.createSequentialGroup().addComponent(btnNuevo).addGap(5)
										.addComponent(btnModificar).addGap(5).addComponent(btnEliminar)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnUp)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnDown)))
						.addContainerGap()));
		gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup().addContainerGap()
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING).addComponent(btnNuevo)
								.addComponent(btnModificar)
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
										.addComponent(btnEliminar).addComponent(btnUp).addComponent(btnDown)))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panelImage, GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)));

		JLabel lblImagen = new JLabel();
		panelImage.add(lblImagen);

		/*
		 * Muestra imagen
		 */
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (table.getSelectedRow() != -1) {
					// Imagen
					String str = (String) table.getValueAt(table.getSelectedRow(), 0);
					BufferedImage img = new Util().scaleImage(panelImage.getWidth() - 20, panelImage.getHeight() - 20,
							str);
					lblImagen.setIcon(new ImageIcon((Image) img));
				}
			}
		});
		scrollPane.setViewportView(table);
		table.setFillsViewportHeight(true);
		createTable();
		table.getColumnModel().getColumn(2).setPreferredWidth(10);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}

	}

	private void createTable() {
		// Modelo de tabla
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Path");
		model.addColumn("Nombre");
		model.addColumn("Tamaño");
		
		try {
			for (Archivo arc : archDAO.listPorProducto(id)) {
				model.addRow(new Object[] { arc.getPath(), arc.getNombre(), arc.getTamaño() + " KB" });
			}
		} catch (PersistenciaException e) {
			e.printStackTrace();
		}
		table.setModel(model);
	}

	private void refreshProdArch() {
		// Lleno la lista con los archivos
		try {
			prodArch = prodArchDAO.listPorProducto(id);
		} catch (PersistenciaException e) {
			e.printStackTrace();
		}
	}

	private void modificarWarn() {
		JOptionPane.showMessageDialog(this, "Para modificar tiene que elegir una fila de la tabla.", "Precaucion",
				JOptionPane.WARNING_MESSAGE);
	}

}
