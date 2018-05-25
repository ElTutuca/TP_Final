package ar.com.tutuca.gui.panels;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import ar.com.tutuca.dao.CategoriaIvaDAO;
import ar.com.tutuca.dao.MetodoPagoDAO;
import ar.com.tutuca.dao.TipoDeComprobanteDAO;
import ar.com.tutuca.extras.GenericDAO;
import javax.swing.JLabel;

public class FacturacionPanel extends JPanel {
	private TipoDeComprobanteDAO comprobDAO = new TipoDeComprobanteDAO();
	private MetodoPagoDAO pagoDAO = new MetodoPagoDAO();
	private CategoriaIvaDAO catIvaDAO = new CategoriaIvaDAO();
	// TODO Hacer daos de Venta

	/**
	 * Create the panel.
	 */
	public FacturacionPanel(JFrame superFrame) {

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(tabbedPane,
				GroupLayout.DEFAULT_SIZE, 773, Short.MAX_VALUE));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(tabbedPane,
				GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE));

		// TODO Hacer compras
		JPanel comprasPane = new JPanel();
		tabbedPane.addTab("Compras", null, comprasPane, null);

		// TODO Hacer ventas
		JPanel ventasPane = new JPanel();
		tabbedPane.addTab("Ventas", null, ventasPane, null);

		JPanel comprobantesPane = new GenericABM("Tipo de Comprobantes", (GenericDAO) comprobDAO, superFrame,
				GenericABM.COMPROBANTE_ID);
		tabbedPane.addTab("Tipos de comprobantes", null, comprobantesPane, null);

		JPanel categoriasIvaPane = new GenericABM("Categoria IVA", (GenericDAO) catIvaDAO, superFrame,
				GenericABM.CATEGORIA_IVA_ID);
		tabbedPane.addTab("Categorias iva", null, categoriasIvaPane, null);

		JPanel metodosPagoPane = new GenericABM("Metodos de Pago", (GenericDAO) pagoDAO, superFrame,
				GenericABM.METODO_PAGO_ID);
		tabbedPane.addTab("Metodos de pago", null, metodosPagoPane, null);
		setLayout(groupLayout);

	}
}
