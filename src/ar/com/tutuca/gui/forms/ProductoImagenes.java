package ar.com.tutuca.gui.forms;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import javax.swing.JTextField;
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
	private static boolean isAlta;
	private List<ProdArchivos> prodArch;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ProductoImagenes dialog = new ProductoImagenes(id, isAlta);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ProductoImagenes(int id, boolean isAlta) {
		System.out.println(id + "*" + isAlta);
		table = new JTable();
		ProductoImagenes.id = id;
		ProductoImagenes.isAlta = isAlta;
		
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
				AltaModifica alta = new AltaModifica(true, table, id, 0);
				alta.setVisible(true);
			}
		});

		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() != -1) {
					refreshProdArch();
					ProdArchivos pa = (ProdArchivos) prodArch.get(table.getSelectedRow());
					AltaModifica modifica = new AltaModifica(false, table, id, pa.getOrden());
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
		
		if(!isAlta) {
			
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

class AltaModifica extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtPath;
	private JTextField txtNombre;
	private ArchivoDAO archDAO = new ArchivoDAO();
	private ProdArchivosDAO prodArchDAO = new ProdArchivosDAO(archDAO);
	private static boolean isAlta;
	private static JTable table;
	private static int id;
	private static int orden;
	private Archivo selectArch;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AltaModifica dialog = new AltaModifica(isAlta, table, id, orden);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AltaModifica(boolean isAlta, JTable table, int id, int orden) {
		AltaModifica.isAlta = isAlta;
		AltaModifica.table = table;
		AltaModifica.id = id;
		AltaModifica.orden = orden;
		JDialog dialog = this;
		String crear = isAlta ? "Nuevo" : "Modifica";
		setTitle("Imagen");
		setResizable(false);
		setBounds(100, 100, 450, 316);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		JLabel lblDireccion = new JLabel("Direccion:");

		txtPath = new JTextField();
		txtPath.setColumns(10);

		JLabel lblNombre = new JLabel("Nombre:");

		txtNombre = new JTextField();
		txtNombre.setColumns(10);

		JPanel panelImage = new JPanel();
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
						.addGroup(gl_contentPanel
								.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup().addComponent(lblDireccion)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(txtPath, GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE))
								.addGroup(gl_contentPanel.createSequentialGroup().addComponent(lblNombre)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(txtNombre,
												GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE))
								.addComponent(panelImage, GroupLayout.PREFERRED_SIZE, 434, GroupLayout.PREFERRED_SIZE))
						.addContainerGap()));
		gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPanel
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_contentPanel
						.createParallelGroup(Alignment.BASELINE).addComponent(lblDireccion).addComponent(txtPath,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE).addComponent(lblNombre).addComponent(
						txtNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
				.addComponent(panelImage, GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE)));

		JLabel lblImage = new JLabel();
		panelImage.add(lblImage);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);

			// Boton imagen
			JButton btnVerImagen = new JButton("Ver Imagen");
			btnVerImagen.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					BufferedImage img = new Util().scaleImage(panelImage.getWidth() - 20, panelImage.getHeight() - 20,
							txtPath.getText());
					lblImage.setIcon(new ImageIcon((Image) img));
				}
			});

			buttonPane.add(btnVerImagen);
			{
				JButton btnCrear = new JButton(crear);
				btnCrear.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						altaM(isAlta, id);
						dialog.dispose();
						return;
					}
				});
				buttonPane.add(btnCrear);
				getRootPane().setDefaultButton(btnCrear);
			}
			{
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dialog.dispose();
					}
				});
				buttonPane.add(btnCancelar);
			}
		}
		if (!isAlta) {
			if (table.getSelectedRow() != -1) {
				try {
					List<ProdArchivos> prodArchList = prodArchDAO.listPorProducto(id);
					ProdArchivos prodArch = prodArchList.get(table.getSelectedRow());
					selectArch = prodArch.getArch();
					txtPath.setText(selectArch.getPath());
					txtNombre.setText(selectArch.getNombre());

					txtPath.setEnabled(false);

				} catch (PersistenciaException e) {
					e.printStackTrace();
					return;
				}
			} else {
				JOptionPane.showMessageDialog(this, "Para modificar tiene que elegir una fila de la tabla.",
						"Precaucion", JOptionPane.WARNING_MESSAGE);
				this.dispose();
			}

		}
	}

	private void altaM(boolean alta, int id) {
		boolean estaSeguro = false;

		int path = Util.checkAll(txtPath, "\"Direccion\"", Util.isValid(txtPath.getText(), 3, 255, 3), null);
		if (path == 3) {
			JOptionPane.showMessageDialog(this, "El campo \"Direccion\" esta vacio.", "Precaucion",
					JOptionPane.WARNING_MESSAGE);
			return;
		} else if (path == 2) {
			return;
		}

		int nombre = Util.checkAll(txtNombre, "\"Nombre\"", Util.isValid(txtNombre.getText(), 3, 45, 3), null);
		if (nombre == 2) {
			return;
		} else if (nombre == 3) {
			estaSeguro = true;
		}

		if (estaSeguro == true) {
			int response = JOptionPane.showConfirmDialog(null, "Faltan \"Nombre\". Esta seguro?", "Confirme",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (response != JOptionPane.YES_OPTION) {
				return;
			}
		}

		try {
			if (alta) {
				/**
				 * Se fija si el archivo que estas queriendo poner ya existe en algun otro
				 * archivo
				 */
				List<Archivo> list = archDAO.list();
				int index = -1;
				for (Archivo archivo : list) {
					if (archivo.getPath().equals(txtPath.getText())) {
						index = archivo.getIdArchivo();
					}
				}

				/**
				 * Si encuentra uno igual, le actualiza el nombre y lo inserta
				 */
				if (index != -1) {
					selectArch = archDAO.load(index);
					selectArch.setNombre(txtNombre.getText());

					ProdArchivos selectProdArch = new ProdArchivos(id, selectArch, table.getRowCount());

					archDAO.update(selectArch);
					prodArchDAO.insert(selectProdArch);
				} else {
					/**
					 * Si no encuentra uno igual, se fija las propiedades del archivo. Lo inserta al
					 * archivo y luego inserta el ProdArchivo
					 */
					long tamano = 0;
					String mimeType = "";

					File f = new File(txtPath.getText());
					if (f.isFile()) {
						try {
							tamano = f.length() / 1024;
							Path source = Paths.get(txtPath.getText());
							mimeType = Files.probeContentType(source);
						} catch (IOException e) {
							e.printStackTrace();
							return;
						}
					} else {
						JOptionPane.showMessageDialog(this, "La direccion no corresponde a un archivo.", "Precaucion", JOptionPane.WARNING_MESSAGE);
					}
					Archivo newArch = new Archivo(txtPath.getText(), txtNombre.getText(), mimeType, (int) tamano);
					newArch = archDAO.insert(newArch);

					orden = table.getRowCount() + 1;
					ProdArchivos prodArch = new ProdArchivos(id, newArch, orden);
					prodArchDAO.insert(prodArch);
				}
			} else {
				/**
				 * Solo se puede cambiar el nombe cuando se esta modificando
				 */
				selectArch.setNombre(txtNombre.getText());
				ProdArchivos selectProdArch = new ProdArchivos(id, selectArch, orden);

				archDAO.update(selectArch);
				prodArchDAO.update(selectProdArch);
			}

			createTable();
		} catch (PersistenciaException e) {
			JOptionPane.showMessageDialog(this, "Error de persistensia.", "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			return;
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
}