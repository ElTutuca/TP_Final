package ar.com.tutuca.gui.panels;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import ar.com.tutuca.dao.ArchivoDAO;
import ar.com.tutuca.dao.CategoriaIvaDAO;
import ar.com.tutuca.dao.CompraDAO;
import ar.com.tutuca.dao.MayoristaDAO;
import ar.com.tutuca.dao.MetodoPagoDAO;
import ar.com.tutuca.dao.ProdArchivosDAO;
import ar.com.tutuca.dao.ProductoDAO;
import ar.com.tutuca.dao.SubcategoriaDAO;
import ar.com.tutuca.dao.TipoDeComprobanteDAO;
import ar.com.tutuca.dao.VentaDAO;
import ar.com.tutuca.extras.GenericDAO;

public class FacturacionPanel extends JPanel {
	
	private TipoDeComprobanteDAO comprobDAO = new TipoDeComprobanteDAO();
	private MetodoPagoDAO pagoDAO = new MetodoPagoDAO();
	private CategoriaIvaDAO catIvaDAO = new CategoriaIvaDAO();
	
	private VentaDAO ventaDAO = new VentaDAO();
	
	private CompraDAO compraDAO = new CompraDAO();

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

		JPanel comprasPane = new GenericABM("Compras", (GenericDAO) compraDAO, superFrame,
				GenericABM.COMPRA_ID);
		tabbedPane.addTab("Compras", null, comprasPane, null);

		JPanel ventasPane = new GenericABM("Ventas", (GenericDAO) ventaDAO, superFrame,
				GenericABM.VENTA_ID);
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
