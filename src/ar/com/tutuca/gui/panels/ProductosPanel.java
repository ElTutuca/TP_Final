package ar.com.tutuca.gui.panels;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import ar.com.tutuca.dao.ArchivoDAO;
import ar.com.tutuca.dao.CategoriaIvaDAO;
import ar.com.tutuca.dao.MayoristaDAO;
import ar.com.tutuca.dao.ProdArchivosDAO;
import ar.com.tutuca.dao.ProductoDAO;
import ar.com.tutuca.dao.SubcategoriaDAO;

public class ProductosPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public ProductosPanel(JFrame superFrame) {

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(tabbedPane,
				GroupLayout.DEFAULT_SIZE, 851, Short.MAX_VALUE));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(tabbedPane,
				GroupLayout.DEFAULT_SIZE, 565, Short.MAX_VALUE));

		ProductoDAO prodDAO = new ProductoDAO(new MayoristaDAO(new CategoriaIvaDAO()), new SubcategoriaDAO(),
				new ProdArchivosDAO(new ArchivoDAO()));

		JPanel prodPane = new GenericABM("Productos", prodDAO, superFrame, 3);
		tabbedPane.addTab("Productos", null, prodPane, null);

		JPanel subCatPane = new JPanel();
		tabbedPane.addTab("Subcategorias", null, subCatPane, null);

		JPanel catPane = new JPanel();
		tabbedPane.addTab("Categorias", null, catPane, null);

		JPanel marcasPane = new JPanel();
		tabbedPane.addTab("Marcas", null, marcasPane, null);
		setLayout(groupLayout);

	}
}
