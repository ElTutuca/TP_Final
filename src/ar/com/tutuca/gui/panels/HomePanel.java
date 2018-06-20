package ar.com.tutuca.gui.panels;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;

public class HomePanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public HomePanel() {
		
		JLabel lblNewLabel = new JLabel("Bienvenido!");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 47));
		add(lblNewLabel);

	}

}
