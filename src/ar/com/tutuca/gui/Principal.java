package ar.com.tutuca.gui;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import ar.com.tutuca.dao.CategoriaIvaDAO;
import ar.com.tutuca.dao.ClienteDAO;
import ar.com.tutuca.dao.MayoristaDAO;
import ar.com.tutuca.gui.panels.GenericABM;
import ar.com.tutuca.gui.panels.HomePanel;
import ar.com.tutuca.gui.panels.ProductosPanel;

public class Principal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2729982729672823163L;
	private MayoristaDAO mayDAO = new MayoristaDAO(new CategoriaIvaDAO());
	private ClienteDAO clDAO = new ClienteDAO(new CategoriaIvaDAO());
	private JPanel homePanel;
	private JPanel mayPanel;
	private JPanel clPanel;
	private JPanel contentPane;
	private JPanel prodPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
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
	public Principal() {
		setTitle("Sistema de Facturacion");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JPanel panelDerecha = new JPanel();
		panelDerecha.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));

		JPanel panelIzquierda = new JPanel();
		panelIzquierda.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup().addContainerGap()
						.addComponent(panelIzquierda, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
						.addGap(18).addComponent(panelDerecha, GroupLayout.DEFAULT_SIZE, 1026, Short.MAX_VALUE)
						.addContainerGap()));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup().addContainerGap()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(panelIzquierda, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 653,
										Short.MAX_VALUE)
								.addComponent(panelDerecha, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 653,
										Short.MAX_VALUE))
						.addContainerGap()));
		panelIzquierda.setLayout(null);

		JLabel lblProductos = new JLabel("Productos");
		lblProductos.setBounds(12, 224, 90, 15);
		panelIzquierda.add(lblProductos);

		JButton btnFacturacion = new JButton("");
		btnFacturacion.setBounds(12, 251, 90, 90);
		panelIzquierda.add(btnFacturacion);

		JLabel lblFacturacion = new JLabel("Facturacion");
		lblFacturacion.setBounds(12, 343, 90, 15);
		panelIzquierda.add(lblFacturacion);

		JLabel lblClientes = new JLabel("Clientes");
		lblClientes.setBounds(12, 462, 90, 15);
		panelIzquierda.add(lblClientes);

		JLabel lblMayoristas = new JLabel("Mayoristas");
		lblMayoristas.setBounds(12, 581, 90, 15);
		panelIzquierda.add(lblMayoristas);
		contentPane.setLayout(gl_contentPane);

		// Carga paneles
		homePanel = new HomePanel();
		mayPanel = new GenericABM("Mayoristas", mayDAO, this, 1);
		clPanel = new GenericABM("Clientes", clDAO, this, 2);
		prodPanel = new ProductosPanel(this);
		
		panelDerecha.setLayout(new CardLayout(0, 0));
		panelDerecha.add(homePanel, "home");
		panelDerecha.add(mayPanel, "mayorista");
		panelDerecha.add(clPanel, "cliente");
		panelDerecha.add(prodPanel, "productos");

		CardLayout cl = (CardLayout) (panelDerecha.getLayout());
		cl.show(panelDerecha, "homePanel");
		
		JButton btnProductos = new JButton("");
		btnProductos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CardLayout cl = (CardLayout) (panelDerecha.getLayout());
				cl.show(panelDerecha, "productos");
			}
		});
		btnProductos.setBounds(12, 132, 90, 90);
		panelIzquierda.add(btnProductos);

		JButton btnMayoristas = new JButton("");
		btnMayoristas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				CardLayout cl = (CardLayout) (panelDerecha.getLayout());
				cl.show(panelDerecha, "mayorista");
			}
		});

		btnMayoristas.setBounds(12, 489, 90, 90);
		panelIzquierda.add(btnMayoristas);
		
		JButton btnClientes = new JButton("");
		btnClientes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				CardLayout cl = (CardLayout) (panelDerecha.getLayout());
				cl.show(panelDerecha, "cliente");
			}
		});
		btnClientes.setBounds(12, 370, 90, 90);
		panelIzquierda.add(btnClientes);

		JButton btnHome = new JButton("");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CardLayout cl = (CardLayout) (panelDerecha.getLayout());
				cl.show(panelDerecha, "home");
			}
		});
		btnHome.setBounds(12, 13, 90, 90);
		panelIzquierda.add(btnHome);

		JLabel lblMain = new JLabel("Home");
		lblMain.setBounds(12, 105, 90, 15);
		panelIzquierda.add(lblMain);
	}

}
