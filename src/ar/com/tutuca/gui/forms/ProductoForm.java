package ar.com.tutuca.gui.forms;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import ar.com.tutuca.dao.CategoriaIvaDAO;
import ar.com.tutuca.dao.MayoristaDAO;
import ar.com.tutuca.dao.SubcategoriaDAO;
import ar.com.tutuca.extras.PersistenciaException;
import ar.com.tutuca.model.Mayorista;
import ar.com.tutuca.model.Subcategoria;

public class ProductoForm extends JFrame {

	private JPanel contentPane;
	private JTextField txtCodigo;
	private JTextField txtPrecio;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTable subTable;
	private String toggleButton = "Eliminado";
	private SubcategoriaDAO subDAO = new SubcategoriaDAO();
	private MayoristaDAO mayDAO = new MayoristaDAO(new CategoriaIvaDAO());
	private JTable mayTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProductoForm frame = new ProductoForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ProductoForm() {
		setAlwaysOnTop(true);
		setResizable(false);
		setTitle("Producto");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 678, 443);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblCodigo = new JLabel("Codigo:");

		txtCodigo = new JTextField();
		txtCodigo.setColumns(10);

		JLabel lblPrecio = new JLabel("Precio:");

		txtPrecio = new JTextField();
		txtPrecio.setColumns(10);

		JLabel lblNombre = new JLabel("Nombre:");

		textField = new JTextField();
		textField.setColumns(10);

		JLabel lblUbicacion = new JLabel("Ubicacion:");

		textField_1 = new JTextField();
		textField_1.setColumns(10);

		JLabel lblStockMax = new JLabel("Stock Max:");

		textField_2 = new JTextField();
		textField_2.setColumns(10);

		JLabel lblStockMin = new JLabel("Stock Min:");

		textField_3 = new JTextField();
		textField_3.setColumns(10);

		JLabel lblStockIdeal = new JLabel("Stock Ideal:");

		textField_4 = new JTextField();
		textField_4.setColumns(10);

		JLabel lblStock = new JLabel("Stock:");

		textField_5 = new JTextField();
		textField_5.setColumns(10);

		JLabel lblDescuento = new JLabel("Descuento:");

		textField_6 = new JTextField();
		textField_6.setColumns(10);

		JLabel lblPorcentajeIva = new JLabel("Porcentaje Iva:");

		textField_7 = new JTextField();
		textField_7.setColumns(10);

		JLabel lblMarca = new JLabel("Marca");

		JComboBox comboBox = new JComboBox();

		JToggleButton tglbtnEliminado = new JToggleButton(toggleButton);
		tglbtnEliminado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (tglbtnEliminado.isSelected()) {
					toggleButton = "Eliminado";
				} else {
					toggleButton = "No Eliminado";
				}
				tglbtnEliminado.setText(toggleButton);
			}
		});

		JButton btnCrear = new JButton("Crear");
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < subTable.getRowCount(); i++) {
					boolean checked = Boolean.valueOf(subTable.getValueAt(i, 0).toString());
					String col = subTable.getValueAt(i, 1).toString();
					System.out.println(checked);
				}
			}
		});

		JScrollPane scrollPane = new JScrollPane();

		JScrollPane scrollPane_1 = new JScrollPane();
		
		JButton btnImagenes = new JButton("Imagenes");

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 347, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addComponent(btnCrear)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
											.addComponent(lblCodigo)
											.addComponent(lblPrecio)
											.addComponent(lblNombre))
										.addGap(18)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
											.addComponent(textField)
											.addComponent(txtCodigo)
											.addComponent(txtPrecio, GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)))
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(lblUbicacion)
										.addGap(4)
										.addComponent(textField_1)))
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addGap(85)
										.addComponent(btnImagenes)
										.addGap(18)
										.addComponent(tglbtnEliminado))
									.addGroup(gl_contentPane.createSequentialGroup()
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
											.addGroup(gl_contentPane.createSequentialGroup()
												.addComponent(lblMarca)
												.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
													.addGroup(gl_contentPane.createSequentialGroup()
														.addGap(133)
														.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
															.addComponent(lblStock, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
															.addComponent(lblPorcentajeIva)
															.addComponent(lblStockMin, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE))
														.addGap(3)
														.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
															.addComponent(textField_7, 0, 0, Short.MAX_VALUE)
															.addComponent(textField_5, GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
															.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)))
													.addGroup(gl_contentPane.createSequentialGroup()
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 217, GroupLayout.PREFERRED_SIZE))))
											.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
												.addGroup(gl_contentPane.createSequentialGroup()
													.addComponent(lblStockMax)
													.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
													.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE))
												.addGroup(gl_contentPane.createSequentialGroup()
													.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
														.addComponent(lblDescuento)
														.addComponent(lblStockIdeal))
													.addPreferredGap(ComponentPlacement.RELATED)
													.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
														.addComponent(textField_6, 0, 0, Short.MAX_VALUE)
														.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE))))))))))
					.addGap(186))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblCodigo)
								.addComponent(txtCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblPrecio)
								.addComponent(txtPrecio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblNombre)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblUbicacion)
								.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(12))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblStockMax))
									.addGap(14)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
											.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addComponent(lblStockIdeal))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGap(31)
											.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
												.addComponent(textField_6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblDescuento)))))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblStockMin)
										.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblStock)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
												.addComponent(textField_7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblPorcentajeIva))))))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblMarca)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE))
					.addGap(73)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCrear)
						.addComponent(btnImagenes)
						.addComponent(tglbtnEliminado)))
		);

		// Tabla de Mayoritas
		mayTable = new JTable();
		scrollPane_1.setViewportView(mayTable);
		mayTable.setFillsViewportHeight(true);
		createTableMayorista();
		mayTable.getColumnModel().getColumn(0).setPreferredWidth(10);
		mayTable.getColumnModel().getColumn(1).setPreferredWidth(110);
		mayTable.getColumnModel().getColumn(2).setPreferredWidth(140);
		
		// Tabla de Subcategoria
		subTable = new JTable();
		subTable.setFillsViewportHeight(true);
		scrollPane.setViewportView(subTable);
		createTableSub();
		subTable.getColumnModel().getColumn(0).setPreferredWidth(20);
		subTable.getColumnModel().getColumn(1).setPreferredWidth(130);

		contentPane.setLayout(gl_contentPane);
	}

	private void createTableSub() {

		// Modelo de tabla
		DefaultTableModel model = new DefaultTableModel() {
			public Class<?> getColumnClass(int column) {
				switch (column) {
				case 0:
					return Boolean.class;
				case 1:
					return String.class;
				default:
					return String.class;
				}
			}

			public boolean isCellEditable(int row, int column) {
				return column == 0;
			}
		};
		model.addColumn("");
		model.addColumn("Subcategoria");

		try {
			for (Subcategoria sub : subDAO.list()) {
				model.addRow(new Object[] { false, sub.getSubcategoria() });
			}
		} catch (PersistenciaException e) {
			e.printStackTrace();
		}
		subTable.setModel(model);
	}

	private void createTableMayorista() {

		// Modelo de tabla
		DefaultTableModel model = new DefaultTableModel() {
			public Class<?> getColumnClass(int column) {
				switch (column) {
				case 0:
					return Boolean.class;
				case 1:
					return String.class;
				default:
					return String.class;
				}
			}

			public boolean isCellEditable(int row, int column) {
				return column == 0;
			}
		};
		model.addColumn("");
		model.addColumn("Nombre");
		model.addColumn("Mayorista");

		try {
			for (Mayorista may : mayDAO.list()) {
				model.addRow(new Object[] { false, may.getNombre(), may.getNombreDeFantasia() });
			}
		} catch (PersistenciaException e) {
			e.printStackTrace();
		}
		mayTable.setModel(model);
	}
}
