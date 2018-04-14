package ar.com.tutuca.gui.panels;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import ar.com.tutuca.dao.TipoDeComprobanteDAO;
import ar.com.tutuca.extras.GenericDAO;
import javax.swing.JLabel;

public class FacturacionPanel extends JPanel {
	private TipoDeComprobanteDAO comprobDAO = new TipoDeComprobanteDAO();

	/**
	 * Create the panel.
	 */
	public FacturacionPanel(JFrame superFrame) {
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 773, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
		);
		
		// TODO Hacer
		JPanel comprasPane = new JPanel();
		tabbedPane.addTab("Compras", null, comprasPane, null);
		
		JLabel lblNewLabel = new JLabel("New label");
		comprasPane.add(lblNewLabel);
		
		JPanel ventasPane = new JPanel();
		tabbedPane.addTab("Ventas", null, ventasPane, null);
		
		JPanel comprobantesPane = new GenericABM("Tipo de Comprobantes",(GenericDAO) comprobDAO, superFrame, GenericABM.COMPROBANTE_ID);
		tabbedPane.addTab("Tipos de comprobantes", null, comprobantesPane, null);
		
		JPanel categoriasIvaPane = new JPanel();
		tabbedPane.addTab("Categorias iva", null, categoriasIvaPane, null);
		
		JPanel metodosPagoPane = new JPanel();
		tabbedPane.addTab("Metodos de pago", null, metodosPagoPane, null);
		setLayout(groupLayout);

	}
}
