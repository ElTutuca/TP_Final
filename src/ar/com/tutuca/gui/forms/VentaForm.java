package ar.com.tutuca.gui.forms;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import ar.com.tutuca.dao.CategoriaIvaDAO;
import ar.com.tutuca.dao.ClienteDAO;
import ar.com.tutuca.dao.TipoDeComprobanteDAO;
import ar.com.tutuca.dao.VentaDAO;
import ar.com.tutuca.extras.PersistenciaException;
import ar.com.tutuca.model.Cliente;
import ar.com.tutuca.model.TipoDeComprobante;
import ar.com.tutuca.model.Venta;

public class VentaForm extends JDialog {

	private CategoriaIvaDAO catIvaDAO = new CategoriaIvaDAO();
	private ClienteDAO clDAO = new ClienteDAO(catIvaDAO);
	private VentaDAO ventaDAO = new VentaDAO();
	private TipoDeComprobanteDAO tpcDAO = new TipoDeComprobanteDAO();

	private final JPanel contentPanel = new JPanel();
	private static boolean isAlta;
	private static JTable superTable;
	private JTextField txtNmroComprob;
	private JTextField txtPuntoDeVenta;
	private JTextField txtNeto1050;
	private JTextField txtNeto2100;
	private JTextField txtNeto2700;
	private JTextField txtIva1050;
	private JTextField txtIva2100;
	private JTextField txtIva2700;
	private JLabel lblTotalNum;
	private Venta selectVenta;
	// TODO Averiguar sobre algo para el TimeStamp

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			VentaForm dialog = new VentaForm(isAlta, superTable);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public VentaForm(boolean isAlta, JTable superTable) {

		JDialog dialog = this;
		VentaForm.superTable = superTable;
		VentaForm.isAlta = isAlta;
		String accion = isAlta ? "Crear" : "Modificar";

		setResizable(false);
		setTitle("Ventas");

		setBounds(100, 100, 522, 283);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		JLabel lblNDeComprobante = new JLabel("N° de comprobante");

		txtNmroComprob = new JTextField();
		txtNmroComprob.setColumns(10);

		JLabel lblPuntoDeVenta = new JLabel("Punto de venta");

		txtPuntoDeVenta = new JTextField();
		txtPuntoDeVenta.setColumns(10);

		// ComboBox de Clientes
		JLabel lblClientes = new JLabel("Clientes");

		List<Cliente> clList = null;
		try {
			clList = clDAO.list();
		} catch (PersistenciaException e) {
			e.printStackTrace();
		}

		Object[] objCLArray = clList.toArray();
		Cliente[] clArray = (Cliente[]) objCLArray;
		JComboBox<Cliente> comboClientes = new JComboBox<Cliente>(clArray);

		// ComboBox de TipoDeComprobantes
		JLabel lblTipoDeComprobante = new JLabel("Tipo de comprobante");

		List<TipoDeComprobante> tpcList = null;
		try {
			tpcList = tpcDAO.list();
		} catch (PersistenciaException e) {
			e.printStackTrace();
		}

		Object[] objTPCArray = tpcList.toArray();
		TipoDeComprobante[] tpcArray = (TipoDeComprobante[]) objTPCArray;
		JComboBox<TipoDeComprobante> comboTipoComrpob = new JComboBox<TipoDeComprobante>(tpcArray);

		JLabel lblTotal = new JLabel("Total =");

		lblTotalNum = new JLabel("0,00");

		JLabel lblNeto1050 = new JLabel("Neto 10,50");

		txtNeto1050 = new JTextField();
		txtNeto1050.setColumns(10);

		JLabel lblNeto = new JLabel("Neto 21,00");

		txtNeto2100 = new JTextField();
		txtNeto2100.setColumns(10);

		JLabel lblNeto_1 = new JLabel("Neto 27,00");

		txtNeto2700 = new JTextField();
		txtNeto2700.setColumns(10);

		JLabel lblIva = new JLabel("Iva 10,50");

		txtIva1050 = new JTextField();
		txtIva1050.setColumns(10);

		JLabel lblIva_1 = new JLabel("Iva 21,00");

		JLabel lblIva_2 = new JLabel("Iva 27,00");

		txtIva2100 = new JTextField();
		txtIva2100.setColumns(10);

		txtIva2700 = new JTextField();
		txtIva2700.setColumns(10);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup().addComponent(lblNDeComprobante)
										.addGap(3).addComponent(txtNmroComprob, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPanel.createSequentialGroup().addComponent(lblPuntoDeVenta)
										.addGap(2).addComponent(txtPuntoDeVenta, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblClientes)
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(comboClientes, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(comboTipoComrpob, Alignment.LEADING, 0, 172, Short.MAX_VALUE))
								.addComponent(lblTipoDeComprobante))
						.addGap(45)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup().addComponent(lblIva_2)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(txtIva2700, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPanel.createSequentialGroup().addComponent(lblIva_1)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(txtIva2100,
												GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPanel.createSequentialGroup().addComponent(lblIva)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(txtIva1050,
												GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPanel.createSequentialGroup().addComponent(lblNeto)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(txtNeto2100,
												GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPanel.createSequentialGroup().addComponent(lblNeto1050)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(txtNeto1050, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPanel.createSequentialGroup().addComponent(lblNeto_1)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(txtNeto2700,
												GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPanel.createSequentialGroup().addComponent(lblTotal)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblTotalNum)))
						.addContainerGap(110, Short.MAX_VALUE)));
		gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPanel
				.createSequentialGroup()
				.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE).addComponent(lblNDeComprobante)
						.addComponent(txtNmroComprob, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNeto1050).addComponent(txtNeto1050, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE).addComponent(lblPuntoDeVenta)
						.addComponent(txtPuntoDeVenta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNeto).addComponent(txtNeto2100, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPanel
						.createSequentialGroup().addGap(24).addComponent(lblClientes)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(comboClientes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
						.addComponent(lblTipoDeComprobante).addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(comboTipoComrpob, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTotal).addComponent(lblTotalNum))
						.addGap(33))
						.addGroup(gl_contentPanel.createSequentialGroup().addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblNeto_1).addComponent(txtNeto2700, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE).addComponent(lblIva)
										.addComponent(txtIva1050, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE).addComponent(lblIva_1)
										.addComponent(txtIva2100, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE).addComponent(lblIva_2)
										.addComponent(txtIva2700, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))))
				.addContainerGap()));
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnAccion = new JButton(accion);
				buttonPane.add(btnAccion);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dialog.dispose();
					}
				});
				buttonPane.add(cancelButton);
			}
		}
		try {
			if (!isAlta) {
				if (superTable.getSelectedRow() != -1) {
					List<Venta> ventaList = ventaDAO.list();
					selectVenta = ventaList.get(superTable.getSelectedRow());
					lblTotalNum.setText(Double.toString(selectVenta.getTotal()));
					txtNmroComprob.setText(selectVenta.getNmroDeComprobante());
					txtPuntoDeVenta.setText(selectVenta.getPuntoDeVenta());
					txtIva1050.setText(Double.toString(selectVenta.getIva1050()));
					txtIva2100.setText(Double.toString(selectVenta.getIva2100()));
					txtIva2700.setText(Double.toString(selectVenta.getIva2700()));
					txtNeto1050.setText(Double.toString(selectVenta.getNeto1050()));
					txtNeto2100.setText(Double.toString(selectVenta.getNeto2100()));
					txtNeto2700.setText(Double.toString(selectVenta.getNeto2700()));
					comboClientes.setSelectedItem(selectVenta.getCliente());
					comboTipoComrpob.setSelectedItem(selectVenta.getTipoDeComprob());

				} else {
					JOptionPane.showMessageDialog(this, "Para modificar tiene que elegir una fila de la tabla.",
							"Precaucion", JOptionPane.WARNING_MESSAGE);
					this.dispose();
				}
			}
		} catch (PersistenciaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
