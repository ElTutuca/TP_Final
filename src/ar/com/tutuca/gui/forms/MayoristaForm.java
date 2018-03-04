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
import ar.com.tutuca.dao.MayoristaDAO;
import ar.com.tutuca.extras.GenericDAO;
import ar.com.tutuca.extras.PersistenciaException;
import ar.com.tutuca.gui.tables.ModeloTabla;
import ar.com.tutuca.model.CategoriaIva;
import ar.com.tutuca.model.Mayorista;

public class MayoristaForm extends JFrame {

	private CategoriaIvaDAO catIvaDAO = new CategoriaIvaDAO();
	private MayoristaDAO mayDAO = new MayoristaDAO(catIvaDAO);
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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MayoristaForm frame = new MayoristaForm(superFrame, alta, dao, table);
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
	public MayoristaForm(JFrame superFrame, boolean alta, GenericDAO dao, JTable table) {
		MayoristaForm.dao = dao;
		MayoristaForm.table = table;
		MayoristaForm.alta = alta;
		String accion = alta ? "Crear" : "Modificar";
		MayoristaForm.superFrame = superFrame;
		JFrame frame = this;
		setAlwaysOnTop(true);
		setResizable(false);
		setTitle("Mayorista");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 630, 176);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				table.setModel(new ModeloTabla(dao));
				superFrame.setEnabled(true);
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
					altaModifica(true, 0);
					table.setModel(new ModeloTabla(dao));
					superFrame.setEnabled(true);
					frame.dispose();
					return;
				} else {
					altaModifica(false, selectMayorista.getIdMayorista());
					table.setModel(new ModeloTabla(dao));
					superFrame.setEnabled(true);
					frame.dispose();
					return;
				}
			}
		});
		btnCrear.setBounds(385, 102, 73, 25);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.setModel(new ModeloTabla(dao));
				superFrame.setEnabled(true);
				frame.dispose();
			}
		});
		btnCancelar.setBounds(470, 102, 96, 25);
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

		boolean chau = false;
		if (!alta) {
			if (table.getSelectedRow() != -1) {
				try {
					List<Mayorista> mayList = mayDAO.list();
					selectMayorista = mayList.get(table.getSelectedRow());
					txtNombre.setText(selectMayorista.getNombre());
					txtNombreDeFantasia.setText(selectMayorista.getNombreDeFantasia());
					txtDireccion.setText(selectMayorista.getDireccion());
					txtTelefono.setText(selectMayorista.getTelefono());
					txtCuit.setText(selectMayorista.getCuit());
					txtNmroDeIngresos.setText(selectMayorista.getNmroIngresosBrutos());
					comboBox.setSelectedItem(selectMayorista.getCatIva().getNombre());
				} catch (PersistenciaException e1) {
					e1.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(this, "Para modificar tiene que elegir un Mayorista ", "Precaucion",
						JOptionPane.WARNING_MESSAGE);
				superFrame.setEnabled(true);
				chau = true;
			}
		}

		if (chau) {
			frame.dispose();
		}
	}

	private String isNull(JTextField txt) {
		return isUsed(txt) ? txt.getText() : null;
	}

	private boolean isUsed(JTextField txt) {
		return (!txt.getText().equals(""));
	}

	private int isValid(String str, int min, int max, int tipo) {
		/**
		 * tipo 1: Solo letras || tipo 2: Solo Numeros || tipo 3: Numeros y letras
		 */
		boolean minMax = str.length() >= min && str.length() <= max;
		int type = 1;

		if (tipo == 1) {
			// Busca solo por letras. Devuelve 2 si encuentra numeros
			type = str.matches("[a-zA-Z\\\\s-?(?)?:?=?.?,?-?/?]+") ? 1 : 2;
		} else if (tipo == 2) {
			// Busca solo por numeros. Devuelve 3 si encuentra alguna letra
			type = str.matches("[\\d-?(?)?:?=?.?,?-?/?]+") ? 1 : 3;
		}

		if ((!minMax) && type == 2) {
			// Contiene un numero (no deberia) y no cumple con el min o max de chars
			return 6;
		} else if ((!minMax) && type == 3) {
			// Contiene una letra (no deberia) y no cumple con el min o max de chars
			return 5;
		} else if (!minMax) {
			// No cumple con el min o max de chars
			return 4;
		} else if (type == 2) {
			// Contiene numeros y solo deberia contener Letras
			return 3;
		} else if (type == 3) {
			// Contiene letras y solo deberia contener numeros
			return 2;
		}
		// Todo esta bien
		return 1;
	}

	private int checkAll(JTextField txt, String name, int isValid) {
		// 2 Significa que esta siendo usado pero no es valido
		int fin = 2;
		if (isUsed(txt)) {
			if (isValid == 2) {
				String r = name + " no puede contener letras.";
				JOptionPane.showMessageDialog(this, r, "Precaucion", JOptionPane.WARNING_MESSAGE);
			} else if (isValid == 3) {
				String r = name + " no puede contener numeros.";
				JOptionPane.showMessageDialog(this, r, "Precaucion", JOptionPane.WARNING_MESSAGE);
			} else if (isValid == 4) {
				String r = name + " se escapa del limite minimo o maximo de caracteres.";
				JOptionPane.showMessageDialog(this, r, "Precaucion", JOptionPane.WARNING_MESSAGE);
			} else if (isValid == 5) {
				String r = name + " no puede contener letras y se escapa del limite minimo o maximo de caracteres.";
				JOptionPane.showMessageDialog(this, r, "Precaucion", JOptionPane.WARNING_MESSAGE);
			} else if (isValid == 6) {
				String r = name + " no puede contener numeros y se escapa del limite minimo o maximo de caracteres.";
				JOptionPane.showMessageDialog(this, r, "Precaucion", JOptionPane.WARNING_MESSAGE);
			} else {
				// 1 Significa que esta todo bien.
				fin = 1;
			}
		} else {
			// 3 Significa que no esta siendo usado.
			fin = 3;
		}
		return fin;
	}

	private void altaModifica(boolean alta, int id) {
		boolean estaSeguro = false;
		int valid = isValid(txtNombre.getText(), 3, 30, 3);
		int nombre = checkAll(txtNombre, "\"Nombre\"", valid);
		if (nombre == 3) {
			JOptionPane.showMessageDialog(this, "El campo \"Nombre\" esta vacio.", "Precaucion",
					JOptionPane.WARNING_MESSAGE);
			return;
		} else if (nombre == 2) {
			return;
		}

		int catIva = selectCatIva == null ? 2 : 1;
		if (catIva != 1) {
			JOptionPane.showMessageDialog(this, "Tiene que seleccionar una Categoria Iva.", "Precaucion",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		int nmbreDeFantasia = checkAll(txtNombreDeFantasia, "\"Nombre de Fantasia\"",
				isValid(txtNombreDeFantasia.getText(), 3, 45, 3));
		if (nmbreDeFantasia == 2) {
			return;
		} else if (nmbreDeFantasia == 3) {
			estaSeguro = true;
		}

		int direccion = checkAll(txtDireccion, "\"Direccion\"", isValid(txtDireccion.getText(), 6, 60, 3));
		if (direccion == 2) {
			return;
		} else if (direccion == 3) {
			estaSeguro = true;
		}

		int telefono = checkAll(txtTelefono, "\"Telefono\"", isValid(txtTelefono.getText(), 9, 40, 3));
		if (telefono == 2) {
			return;
		} else if (telefono == 3) {
			estaSeguro = true;
		}

		int nmroDeIngresos = checkAll(txtNmroDeIngresos, "\"Nmro de Ingresos Brutos\"",
				isValid(txtNmroDeIngresos.getText(), 9, 11, 3));
		if (nmroDeIngresos == 2) {
			return;
		} else if (nmroDeIngresos == 3) {
			estaSeguro = true;
		}

		int cuit = checkAll(txtCuit, "\"Cuit\"", isValid(txtCuit.getText(), 9, 11, 2));
		if (cuit == 2) {
			return;
		} else if (cuit == 3) {
			estaSeguro = true;
		}

		if (estaSeguro == true) {
			int response = JOptionPane.showConfirmDialog(null, "Faltan algunos campos. Esta seguro?", "Confirme",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (response == JOptionPane.YES_OPTION) {
				try {
					if (alta) {
						Mayorista entidad = new Mayorista(txtNombre.getText(), isNull(txtNombreDeFantasia),
								isNull(txtDireccion), isNull(txtTelefono), isNull(txtNmroDeIngresos), isNull(txtCuit),
								selectCatIva);
						mayDAO.insert(entidad);
						return;
					} else {
						Mayorista entidad = new Mayorista(id, txtNombre.getText(), isNull(txtNombreDeFantasia),
								isNull(txtDireccion), isNull(txtTelefono), isNull(txtNmroDeIngresos), isNull(txtCuit),
								selectCatIva);
						mayDAO.update(entidad);
						return;
					}
				} catch (PersistenciaException e) {
					JOptionPane.showMessageDialog(this, "Error de persistensia.", "Error", JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
					return;
				}
			} else {
				return;
			}
		} else {
			try {
				if (alta) {
					Mayorista entidad = new Mayorista(txtNombre.getText(), isNull(txtNombreDeFantasia),
							isNull(txtDireccion), isNull(txtTelefono), isNull(txtNmroDeIngresos), isNull(txtCuit),
							selectCatIva);
					mayDAO.insert(entidad);
					return;
				} else {
					Mayorista entidad = new Mayorista(id, txtNombre.getText(), isNull(txtNombreDeFantasia),
							isNull(txtDireccion), isNull(txtTelefono), isNull(txtNmroDeIngresos), isNull(txtCuit),
							selectCatIva);
					mayDAO.update(entidad);
					return;
				}
			} catch (PersistenciaException e) {
				JOptionPane.showMessageDialog(this, "Error de persistensia.", "Error", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
				return;
			}
		}
	}
}
