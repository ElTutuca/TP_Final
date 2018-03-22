package ar.com.tutuca.gui.forms;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import ar.com.tutuca.dao.CategoriaIvaDAO;
import ar.com.tutuca.dao.ClienteDAO;
import ar.com.tutuca.dao.MayoristaDAO;
import ar.com.tutuca.extras.GenericDAO;
import ar.com.tutuca.extras.GenericModel;
import ar.com.tutuca.extras.PersistenciaException;
import ar.com.tutuca.extras.Util;
import ar.com.tutuca.gui.tables.ModeloTabla;
import ar.com.tutuca.model.CategoriaIva;
import ar.com.tutuca.model.Cliente;
import ar.com.tutuca.model.Mayorista;

public class MayoristaForm extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1834122103150408730L;
	private CategoriaIvaDAO catIvaDAO = new CategoriaIvaDAO();
	private MayoristaDAO mayDAO = new MayoristaDAO(catIvaDAO);
	private ClienteDAO clDAO = new ClienteDAO(catIvaDAO);
	private GenericModel genericModel;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtNombreDeFantasia;
	private JTextField txtDireccion;
	private JTextField txtTelefono;
	private JTextField txtNmroDeIngresos;
	private JTextField txtCuit;
	private CategoriaIva selectCatIva;
	private List<CategoriaIva> catList = null;
	private static JFrame superFrame;
	private static boolean alta;
	private static GenericDAO dao;
	private static JTable table;
	private Mayorista selectMayorista;
	private Cliente selectCliente;
	private static boolean isMayorista;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MayoristaForm frame = new MayoristaForm(superFrame, alta, dao, table, isMayorista);
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
	public MayoristaForm(JFrame superFrame, boolean alta, GenericDAO dao, JTable table, boolean isMayorista) {
		MayoristaForm.isMayorista = isMayorista;
		MayoristaForm.dao = dao;
		MayoristaForm.table = table;
		MayoristaForm.alta = alta;
		MayoristaForm.superFrame = superFrame;
		String accion = alta ? "Crear" : "Modificar";
		JFrame frame = this;
		setAlwaysOnTop(true);
		setResizable(false);
		setTitle((isMayorista ? "Mayorista" : "Cliente"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 630, 176);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				try {
					table.setModel(new ModeloTabla(dao.list()));
					superFrame.setEnabled(true);
				} catch (PersistenciaException e1) {
					e1.printStackTrace();
				}
			}
		});

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(17, 19, 60, 15);

		JLabel lblNombreDeFantasia = new JLabel("Nombre de Fantasia:");
		lblNombreDeFantasia.setBounds(17, 46, 148, 15);

		JLabel lblDireccion = new JLabel("Direccion:");
		lblDireccion.setBounds(17, 73, 70, 15);

		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setBounds(316, 46, 67, 15);

		JLabel lblNmroDeIngresos = new JLabel("Nmro de Ingresos Brutos:");
		lblNmroDeIngresos.setBounds(316, 73, 181, 15);

		JLabel lblCuit = new JLabel("CUIT:");
		lblCuit.setBounds(316, 19, 36, 15);

		JLabel lblCategoriaIva = new JLabel("Categoria IVA");
		lblCategoriaIva.setBounds(17, 107, 96, 15);

		txtNombre = new JTextField();
		txtNombre.setBounds(79, 17, 219, 19);
		txtNombre.setColumns(10);

		txtNombreDeFantasia = new JTextField();
		txtNombreDeFantasia.setBounds(166, 44, 132, 19);
		txtNombreDeFantasia.setColumns(10);

		txtDireccion = new JTextField();
		txtDireccion.setBounds(89, 73, 209, 19);
		txtDireccion.setColumns(10);

		txtTelefono = new JTextField();
		txtTelefono.setBounds(385, 44, 209, 19);
		txtTelefono.setColumns(10);

		txtNmroDeIngresos = new JTextField();
		txtNmroDeIngresos.setBounds(497, 71, 97, 19);
		txtNmroDeIngresos.setColumns(10);

		txtCuit = new JTextField();
		txtCuit.setBounds(385, 17, 209, 19);
		txtCuit.setColumns(10);

		List<String> nombres = new ArrayList<String>();
		try {
			catList = catIvaDAO.list();
			for (CategoriaIva categ : catList) {
				nombres.add(categ.getNombre());
			}
		} catch (PersistenciaException e) {
			JOptionPane.showMessageDialog(this, "Error al recibir las categorias de iva.", "Error",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		
		String[] ivas = new String[nombres.size()];
		ivas = nombres.toArray(ivas);
		// Poner "ivas" en ..JComboBox<String>(*aca*);
		JComboBox<String> comboBox = new JComboBox<String>(ivas);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectCatIva = (catList.get(comboBox.getSelectedIndex()));
			}
		});
		comboBox.setBounds(117, 102, 243, 24);
		JButton btnCrear = new JButton(accion);
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (alta) {
					try {
						altaModifica(true, 0);
						table.setModel(new ModeloTabla(dao.list()));
						superFrame.setEnabled(true);
						frame.dispose();
						return;
					} catch (PersistenciaException e1) {
						e1.printStackTrace();
					}
				} else {
					try {
						int id = isMayorista ? selectMayorista.getIdMayorista() : selectCliente.getIdCliente();
						altaModifica(false, id);
						table.setModel(new ModeloTabla(dao.list()));
						superFrame.setEnabled(true);
						frame.dispose();
						return;
					} catch (PersistenciaException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnCrear.setBounds(385, 102, 100, 25);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					table.setModel(new ModeloTabla(dao.list()));
					superFrame.setEnabled(true);
					frame.dispose();
				} catch (PersistenciaException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnCancelar.setBounds(497, 102, 96, 25);
		contentPane.setLayout(null);
		contentPane.add(lblNombreDeFantasia);
		contentPane.add(btnCrear);
		contentPane.add(btnCancelar);
		contentPane.add(lblNmroDeIngresos);
		contentPane.add(txtNmroDeIngresos);
		contentPane.add(txtNombreDeFantasia);
		contentPane.add(lblNombre);
		contentPane.add(txtNombre);
		contentPane.add(lblCuit);
		contentPane.add(lblDireccion);
		contentPane.add(lblCategoriaIva);
		contentPane.add(comboBox);
		contentPane.add(lblTelefono);
		contentPane.add(txtTelefono);
		contentPane.add(txtDireccion);
		contentPane.add(txtCuit);

		if (!alta) {
			if (table.getSelectedRow() != -1) {
				try {
					if (isMayorista) {
						List<Mayorista> mayList = mayDAO.list();
						selectMayorista = mayList.get(table.getSelectedRow());
						txtNombre.setText(selectMayorista.getNombre());
						txtNombreDeFantasia.setText(selectMayorista.getNombreDeFantasia());
						txtDireccion.setText(selectMayorista.getDireccion());
						txtTelefono.setText(selectMayorista.getTelefono());
						txtCuit.setText(selectMayorista.getCuit());
						txtNmroDeIngresos.setText(selectMayorista.getNmroIngresosBrutos());
						comboBox.setSelectedItem(selectMayorista.getCatIva().getNombre());
					} else {
						List<Cliente> clList = clDAO.list();
						selectCliente = clList.get(table.getSelectedRow());
						txtNombre.setText(selectCliente.getNombre());
						txtNombreDeFantasia.setText(selectCliente.getNombreDeFantasia());
						txtDireccion.setText(selectCliente.getDireccion());
						txtTelefono.setText(selectCliente.getTelefono());
						txtCuit.setText(selectCliente.getCuit());
						txtNmroDeIngresos.setText(selectCliente.getNmroIngresosBrutos());
						comboBox.setSelectedItem(selectCliente.getCatIva().getNombre());
					}
				} catch (PersistenciaException e1) {
					e1.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(this, "Para modificar tiene que elegir una fila de la tabla.",
						"Precaucion", JOptionPane.WARNING_MESSAGE);
				superFrame.setEnabled(true);
				frame.dispose();
			}
		}
	}

	private void altaModifica(boolean alta, int id) {
		boolean estaSeguro = false;
		int valid = Util.isValid(txtNombre.getText(), 3, 30, 3);
		int nombre = Util.checkAll(txtNombre, "\"Nombre\"", valid, this);
		if (nombre == 3) {
			JOptionPane.showMessageDialog(this, "El campo \"Nombre\" esta vacio.", "Precaucion",
					JOptionPane.WARNING_MESSAGE);
			return;
		} else if (nombre == 2) {
			return;
		}

		if (selectCatIva == null) {
			JOptionPane.showMessageDialog(this, "Tiene que seleccionar una Categoria Iva.", "Precaucion",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		int nmbreDeFantasia = Util.checkAll(txtNombreDeFantasia, "\"Nombre de Fantasia\"",
				Util.isValid(txtNombreDeFantasia.getText(), 3, 45, 3), this);
		if (nmbreDeFantasia == 2) {
			return;
		} else if (nmbreDeFantasia == 3) {
			estaSeguro = true;
		}

		int direccion = Util.checkAll(txtDireccion, "\"Direccion\"", Util.isValid(txtDireccion.getText(), 6, 60, 3), this);
		if (direccion == 2) {
			return;
		} else if (direccion == 3) {
			estaSeguro = true;
		}

		int telefono = Util.checkAll(txtTelefono, "\"Telefono\"", Util.isValid(txtTelefono.getText(), 9, 40, 3), this);
		if (telefono == 2) {
			return;
		} else if (telefono == 3) {
			estaSeguro = true;
		}

		int nmroDeIngresos = Util.checkAll(txtNmroDeIngresos, "\"Nmro de Ingresos Brutos\"",
				Util.isValid(txtNmroDeIngresos.getText(), 9, 11, 3), this);
		if (nmroDeIngresos == 2) {
			return;
		} else if (nmroDeIngresos == 3) {
			estaSeguro = true;
		}

		int cuit = Util.checkAll(txtCuit, "\"Cuit\"", Util.isValid(txtCuit.getText(), 9, 11, 2), this);
		if (cuit == 2) {
			return;
		} else if (cuit == 3) {
			estaSeguro = true;
		}

		if (estaSeguro == true) {
			int response = JOptionPane.showConfirmDialog(null, "Faltan algunos campos. Esta seguro?", "Confirme",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (response != JOptionPane.YES_OPTION) {
				return;
			}
		}

		try {
			if (alta) {
				if (isMayorista) {
					Mayorista entidad = new Mayorista(txtNombre.getText(), Util.isNull(txtNombreDeFantasia),
							Util.isNull(txtDireccion), Util.isNull(txtTelefono), Util.isNull(txtNmroDeIngresos), Util.isNull(txtCuit),
							selectCatIva);
					mayDAO.insert(entidad);
					return;
				} else {
					Cliente entidad = new Cliente(txtNombre.getText(), Util.isNull(txtNombreDeFantasia),
							Util.isNull(txtDireccion), Util.isNull(txtTelefono), Util.isNull(txtNmroDeIngresos), Util.isNull(txtCuit),
							selectCatIva);
					clDAO.insert(entidad);
					return;
				}
			} else {
				if (isMayorista) {
					Mayorista entidad = new Mayorista(id, txtNombre.getText(), Util.isNull(txtNombreDeFantasia),
							Util.isNull(txtDireccion), Util.isNull(txtTelefono), Util.isNull(txtNmroDeIngresos), Util.isNull(txtCuit),
							selectCatIva);
					mayDAO.update(entidad);
					return;
				} else {
					Cliente entidad = new Cliente(id, txtNombre.getText(), Util.isNull(txtNombreDeFantasia),
							Util.isNull(txtDireccion), Util.isNull(txtTelefono), Util.isNull(txtNmroDeIngresos), Util.isNull(txtCuit),
							selectCatIva);
					clDAO.update(entidad);
					return;
				}
			}
		} catch (PersistenciaException e) {
			JOptionPane.showMessageDialog(this, "Error de persistensia.", "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			return;
		}
	}
}
