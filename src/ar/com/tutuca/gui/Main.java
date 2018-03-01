package ar.com.tutuca.gui;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import ar.com.tutuca.dao.CategoriaIvaDAO;
import ar.com.tutuca.dao.ClienteDAO;
import ar.com.tutuca.dao.MayoristaDAO;
import ar.com.tutuca.gui.panels.ClientesMain;
import ar.com.tutuca.gui.panels.GenericABM;
import ar.com.tutuca.gui.panels.HomePanel;
import ar.com.tutuca.gui.panels.MayoristaMain;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.CardLayout;

public class Main extends JFrame {
	
	private MayoristaDAO mayDAO = new MayoristaDAO(new CategoriaIvaDAO());
	private ClienteDAO clDAO = new ClienteDAO(new CategoriaIvaDAO());
	private JPanel homePanel = new HomePanel();
	private JPanel mayPanel;
	private JPanel clPanel;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
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
	public Main() {
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
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(panelIzquierda, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(panelDerecha, GroupLayout.DEFAULT_SIZE, 1026, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(panelIzquierda, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 653, Short.MAX_VALUE)
						.addComponent(panelDerecha, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 653, Short.MAX_VALUE))
					.addContainerGap())
		);
		panelIzquierda.setLayout(null);
		
		JButton btnProductos = new JButton("");
		btnProductos.setBounds(12, 132, 90, 90);
		panelIzquierda.add(btnProductos);
		
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
		panelDerecha.add(homePanel);
		homePanel.setVisible(true);
		
		mayPanel = new GenericABM("Mayoristas", mayDAO);
		mayPanel.setVisible(false);
		panelDerecha.add(mayPanel);
		
		clPanel = new GenericABM("Clientes", clDAO);
		clPanel.setVisible(false);
		panelDerecha.add(clPanel);
		panelDerecha.setLayout(new CardLayout(0, 0));
		panelDerecha.add(homePanel, "name_19263292375661");
		panelDerecha.add(mayPanel, "name_19263325384853");
		panelDerecha.add(clPanel, "name_19263339766640");
		
		JButton btnMayoristas = new JButton("");
		btnMayoristas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				loadPanel(mayPanel);
			}
		});
		btnMayoristas.setBounds(12, 489, 90, 90);
		panelIzquierda.add(btnMayoristas);
		
		JButton btnClientes = new JButton("");
		btnClientes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				loadPanel(clPanel);
			}
		});
		btnClientes.setBounds(12, 370, 90, 90);
		panelIzquierda.add(btnClientes);
		
		JButton btnHome = new JButton("");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				loadPanel(homePanel);
			}
		});
		btnHome.setBounds(12, 13, 90, 90);
		panelIzquierda.add(btnHome);
		
		JLabel lblMain = new JLabel("Home");
		lblMain.setBounds(12, 105, 90, 15);
		panelIzquierda.add(lblMain);
	}
	
	private void loadPanel(JPanel panel) {
		mayPanel.setVisible(false);
		homePanel.setVisible(false);
		clPanel.setVisible(false);
		panel.setVisible(true);
	}
}
