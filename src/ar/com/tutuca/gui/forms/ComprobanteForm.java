package ar.com.tutuca.gui.forms;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import ar.com.tutuca.dao.TipoDeComprobanteDAO;
import ar.com.tutuca.extras.GenericDAO;
import ar.com.tutuca.extras.PersistenciaException;
import ar.com.tutuca.extras.Util;
import ar.com.tutuca.gui.tables.ModeloTabla;
import ar.com.tutuca.model.TipoDeComprobante;

public class ComprobanteForm extends JDialog {

	private static boolean isAlta;
	private static JTable superTable;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JTextField txtLetra;
	private JTextField txtAbreviatura;
	private TipoDeComprobanteDAO comprobDAO = new TipoDeComprobanteDAO();
	private TipoDeComprobante selectTipoDeComprobante;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ComprobanteForm dialog = new ComprobanteForm(isAlta, superTable);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ComprobanteForm(boolean isAlta, JTable superTable) {
		JDialog dialog = this;
		ComprobanteForm.superTable = superTable;
		ComprobanteForm.isAlta = isAlta;
		String accion = isAlta ? "Crear" : "Modificar";

		setAlwaysOnTop(true);
		setResizable(false);
		setTitle("Tipo de comprobante");

		setBounds(100, 100, 242, 173);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		JLabel lblNombre = new JLabel("Nombre:");
		JLabel lblLetra = new JLabel("Letra:");
		JLabel lblAbreviatura = new JLabel("Abreviatura:");

		txtNombre = new JTextField();
		txtNombre.setColumns(10);

		txtLetra = new JTextField();
		txtLetra.setColumns(10);

		txtAbreviatura = new JTextField();
		txtAbreviatura.setColumns(10);

		JButton btnAccion = new JButton(accion);
		btnAccion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int id = isAlta ? 0 : selectTipoDeComprobante.getIdTipoDeComprob();
				altaModifica(isAlta, id);
				
				GenericDAO dao = (GenericDAO) comprobDAO;
				try {
					superTable.setModel(new ModeloTabla(dao.list()));
				} catch (PersistenciaException e) {
					e.printStackTrace();
				}
			}
		});

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dialog.dispose();
			}
		});
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup().addComponent(lblAbreviatura).addGap(3)
								.addComponent(txtAbreviatura, GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
								.addContainerGap())
						.addGroup(gl_contentPanel.createSequentialGroup().addGroup(gl_contentPanel
								.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup().addComponent(lblNombre).addGap(4)
										.addComponent(txtNombre, GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE))
								.addGroup(gl_contentPanel.createSequentialGroup().addComponent(lblLetra)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(txtLetra, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)))
								.addContainerGap()))
				.addGroup(gl_contentPanel.createSequentialGroup().addComponent(btnAccion)
						.addPreferredGap(ComponentPlacement.RELATED, 49, Short.MAX_VALUE).addComponent(btnCancelar)));
		gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPanel
				.createSequentialGroup()
				.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE).addComponent(lblNombre).addComponent(
						txtNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE).addComponent(lblLetra).addComponent(
						txtLetra, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE).addComponent(lblAbreviatura)
						.addComponent(txtAbreviatura, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED, 36, Short.MAX_VALUE).addGroup(gl_contentPanel
						.createParallelGroup(Alignment.BASELINE).addComponent(btnAccion).addComponent(btnCancelar))));
		contentPanel.setLayout(gl_contentPanel);
		try {
			if (!isAlta) {
				if (superTable.getSelectedRow() != -1) {
					List<TipoDeComprobante> listTipos = comprobDAO.list();
					selectTipoDeComprobante = listTipos.get(superTable.getSelectedRow());
					txtAbreviatura.setText(selectTipoDeComprobante.getAbreviatura());
					txtLetra.setText(selectTipoDeComprobante.getLetra());
					txtNombre.setText(selectTipoDeComprobante.getNombre());

				} else {
					JOptionPane.showMessageDialog(this, "Para modificar tiene que elegir una fila de la tabla.",
							"Precaucion", JOptionPane.WARNING_MESSAGE);
					this.dispose();
				}
			}
		} catch (PersistenciaException e1) {
			e1.printStackTrace();
		}
	}

	private void altaModifica(boolean isAlta, int id) {
		// Control de Nombre
		int nombre = Util.checkAll(txtNombre, "\"Nombre\"", Util.isValid(txtNombre.getText(), 2, 15, 3), this);
		if (nombre == 3) {
			JOptionPane.showMessageDialog(this, "El campo \"Nombre\" esta vacio.", "Precaucion",
					JOptionPane.WARNING_MESSAGE);
			return;
		} else if (nombre == 2) {
			return;
		}

		// Control de Letra
		int letra = Util.checkAll(txtLetra, "\"Letra\"", Util.isValid(txtLetra.getText(), 1, 1, 1), this);
		if (letra == 3) {
			JOptionPane.showMessageDialog(this, "El campo \"Letra\" esta vacio.", "Precaucion",
					JOptionPane.WARNING_MESSAGE);
			return;
		} else if (letra == 2) {
			return;
		}

		// Control de Abreviatura
		int abreviatura = Util.checkAll(txtAbreviatura, "\"Abreviatura\"",
				Util.isValid(txtAbreviatura.getText(), 2, 6, 3), this);
		if (abreviatura == 3) {
			JOptionPane.showMessageDialog(this, "El campo \"Abreviatura\" esta vacio.", "Precaucion",
					JOptionPane.WARNING_MESSAGE);
			return;
		} else if (abreviatura == 2) {
			return;
		}

		// Insert / Update
		TipoDeComprobante rComprob = new TipoDeComprobante(txtNombre.getText(), txtLetra.getText(),
				txtAbreviatura.getText());
		if (isAlta) {
			try {
				selectTipoDeComprobante = comprobDAO.insert(rComprob);
			} catch (PersistenciaException e) {
				JOptionPane.showMessageDialog(this, "Error insertando el tipo de comprobante.", "Precaucion",
						JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
				return;
			}
		} else {
			rComprob.setIdTipoDeComprob(selectTipoDeComprobante.getIdTipoDeComprob());
			try {
				comprobDAO.update(rComprob);
			} catch (PersistenciaException e) {
				JOptionPane.showMessageDialog(this, "Error modificando el tipo de comprobante.", "Precaucion",
						JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
				return;
			}
		}
		this.dispose();
	}
}
