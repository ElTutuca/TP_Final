package ar.com.tutuca.gui;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.BevelBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ar.com.tutuca.dao.MarcaDAO;
import ar.com.tutuca.dao.extras.Exceptions.PersistenciaException;
import ar.com.tutuca.model.Marca;

public class Main extends JFrame {
	
	private MarcaDAO mDAO = new MarcaDAO();
	
	private int maxPrecio = 20000;
	private int minPrecio = 0;

	private int desde = minPrecio;
	private int hasta = maxPrecio;

	private JTextField busqueda;
	private final ButtonGroup buttonGroup = new ButtonGroup();

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1022, 685);

		busqueda = new JTextField();
		busqueda.setColumns(10);

		JLabel lblBusqueda = new JLabel("Busqueda");

		// pnResultados va a tener un arreglos de objetos paneles, que cada uno va a
		// tener informacion de un producto
		JScrollPane pnResultados = new JScrollPane();
		pnResultados.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));

		JPanel pnFiltros = new JPanel();
		pnFiltros.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		JLabel lblFiltros = new JLabel("Filtros");
		lblFiltros.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblFiltros, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(pnFiltros, GroupLayout.PREFERRED_SIZE, 146, Short.MAX_VALUE))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(6)
							.addComponent(pnResultados, GroupLayout.DEFAULT_SIZE, 835, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(261)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(37)
									.addComponent(lblBusqueda, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
									.addContainerGap())
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(busqueda, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
									.addGap(419))))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(26)
					.addComponent(lblBusqueda)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(busqueda, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(pnResultados, GroupLayout.DEFAULT_SIZE, 533, Short.MAX_VALUE)
							.addGap(28))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblFiltros, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(pnFiltros, GroupLayout.PREFERRED_SIZE, 559, Short.MAX_VALUE)))
					.addGap(7))
		);
		pnFiltros.setLayout(new GridLayout(30, 0, 0, 0));

		JLabel lblNewLabel = new JLabel("Rango de precios");
		pnFiltros.add(lblNewLabel);

		JLabel rango = new JLabel(desde + " - " + hasta);

		JSlider desdePrecio = new JSlider(minPrecio, maxPrecio, minPrecio);
		desdePrecio.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider) e.getSource();
				desde = source.getValue();
				rango.setText(desde + " - " + hasta);
			}
		});
		pnFiltros.add(desdePrecio);

		JSlider hastaPrecio = new JSlider(minPrecio, maxPrecio, maxPrecio);
		hastaPrecio.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider) e.getSource();
				hasta = source.getValue();
				rango.setText(desde + " - " + hasta);
			}
		});
		pnFiltros.add(hastaPrecio);

		pnFiltros.add(rango);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		pnFiltros.add(verticalStrut);
		
		JLabel lblCategoria = new JLabel("Categoria");
		pnFiltros.add(lblCategoria);
		
		JCheckBox chckbxTecnologia = new JCheckBox("Tecnologia");
		pnFiltros.add(chckbxTecnologia);
		
		JCheckBox chckbxBla = new JCheckBox("Bla");
		pnFiltros.add(chckbxBla);
		
		JCheckBox chckbxBla_1 = new JCheckBox("Bla");
		pnFiltros.add(chckbxBla_1);
		
		JCheckBox chckbxBla_2 = new JCheckBox("Bla");
		pnFiltros.add(chckbxBla_2);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		pnFiltros.add(verticalStrut_1);
		
		JLabel lblDescuentos = new JLabel("Marcas");
		pnFiltros.add(lblDescuentos);
		
		try {
			for (Marca m : mDAO.list()) {
				JCheckBox chckbxNewCheckBox = new JCheckBox(m.getNombre());
				pnFiltros.add(chckbxNewCheckBox);
			}
		} catch (PersistenciaException e1) {
			e1.printStackTrace();
		}

		getContentPane().setLayout(groupLayout);
	}
}
