package ar.com.tutuca.gui.panels;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

public class MayoristaMain extends JPanel {
	private JTable table;

	/**
	 * Create the panel.
	 */
	public MayoristaMain() {
		
		table = new JTable();
		table.setForeground(Color.WHITE);
		
		JButton btnNewButton = new JButton("New button");
		
		JButton btnNewButton_1 = new JButton("New button");
		
		JButton btnNewButton_2 = new JButton("New button");
		
		JButton btnNewButton_3 = new JButton("New button");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(39, Short.MAX_VALUE)
					.addComponent(table, GroupLayout.PREFERRED_SIZE, 851, GroupLayout.PREFERRED_SIZE)
					.addGap(27))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(63)
					.addComponent(btnNewButton)
					.addGap(100)
					.addComponent(btnNewButton_1)
					.addGap(44)
					.addComponent(btnNewButton_2)
					.addGap(64)
					.addComponent(btnNewButton_3)
					.addContainerGap(178, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGap(83)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(btnNewButton_1)
						.addComponent(btnNewButton_2)
						.addComponent(btnNewButton_3))
					.addPreferredGap(ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
					.addComponent(table, GroupLayout.PREFERRED_SIZE, 362, GroupLayout.PREFERRED_SIZE)
					.addGap(40))
		);
		setLayout(groupLayout);

	}
}
