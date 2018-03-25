package ar.com.tutuca.extras;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Util {
	private static Connection c;

	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		if (c == null) {
			Class.forName("com.mysql.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sucursal", "root", "root");
		}
		return c;
	}

	public static Statement createStatement() throws ClassNotFoundException, SQLException {
		return getConnection().createStatement();
	}

	public static PreparedStatement prepareStatement(String sql) throws ClassNotFoundException, SQLException {
		return getConnection().prepareStatement(sql);

	}

	public static int lastId() throws SQLException, ClassNotFoundException {
		ResultSet rs = createStatement().executeQuery("SELECT LAST_INSERT_ID();");
		rs.next();
		return rs.getInt(1);
	}

	public static String isNull(JTextField txt) {
		return isUsed(txt) ? txt.getText() : null;
	}

	public static boolean isUsed(JTextField txt) {
		return (!txt.getText().equals(""));
	}

	public static int isValid(String str, int min, int max, int tipo) {
		/**
		 * tipo 1: Solo letras || tipo 2: Solo Numeros || tipo 3: Numeros y letras
		 */
		boolean minMax = str.length() >= min && str.length() <= max;
		int type = 1;

		if (tipo == 1) {
			// Busca solo por letras. Devuelve 2 si encuentra numeros
			type = str.matches("[a-zA-Z\\\\s-?(?)?:?=?.?,?-?/?]+") ? 1 : 2;
		} else if (tipo == 2) {
			// Busca solo por numeros. Devuelve 3 si encuentra alguna letra
			type = str.matches("[\\d-?(?)?:?=?.?,?-?/?]+") ? 1 : 3;
		}

		if ((!minMax) && type == 2) {
			// Contiene un numero (no deberia) y no cumple con el min o max de chars
			return 6;
		} else if ((!minMax) && type == 3) {
			// Contiene una letra (no deberia) y no cumple con el min o max de chars
			return 5;
		} else if (!minMax) {
			// No cumple con el min o max de chars
			return 4;
		} else if (type == 2) {
			// Contiene numeros y solo deberia contener Letras
			return 3;
		} else if (type == 3) {
			// Contiene letras y solo deberia contener numeros
			return 2;
		}
		// Todo esta bien
		return 1;
	}

	public static int checkAll(JTextField txt, String name, int isValid, JFrame parent) {
		// 2 Significa que esta siendo usado pero no es valido
		int fin = 2;
		if (isUsed(txt)) {
			if (isValid == 2) {
				String r = name + " no puede contener letras.";
				JOptionPane.showMessageDialog(parent, r, "Precaucion", JOptionPane.WARNING_MESSAGE);
			} else if (isValid == 3) {
				String r = name + " no puede contener numeros.";
				JOptionPane.showMessageDialog(parent, r, "Precaucion", JOptionPane.WARNING_MESSAGE);
			} else if (isValid == 4) {
				String r = name + " se escapa del limite minimo o maximo de caracteres.";
				JOptionPane.showMessageDialog(parent, r, "Precaucion", JOptionPane.WARNING_MESSAGE);
			} else if (isValid == 5) {
				String r = name + " no puede contener letras y se escapa del limite minimo o maximo de caracteres.";
				JOptionPane.showMessageDialog(parent, r, "Precaucion", JOptionPane.WARNING_MESSAGE);
			} else if (isValid == 6) {
				String r = name + " no puede contener numeros y se escapa del limite minimo o maximo de caracteres.";
				JOptionPane.showMessageDialog(parent, r, "Precaucion", JOptionPane.WARNING_MESSAGE);
			} else {
				// 1 Significa que esta todo bien.
				fin = 1;
			}
		} else {
			// 3 Significa que no esta siendo usado.
			fin = 3;
		}
		return fin;
	}

	public BufferedImage scaleImage(int WIDTH, int HEIGHT, String filename) {
		BufferedImage bi = null;
		try {
			ImageIcon ii = new ImageIcon(filename);// path to image
			bi = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
			Graphics2D g2d = (Graphics2D) bi.createGraphics();
			g2d.addRenderingHints(
					new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
			g2d.drawImage(ii.getImage(), 0, 0, WIDTH, HEIGHT, null);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return bi;
	}
	
	public String getMimeType(String name) {
		String r = name.substring(name.indexOf("."), name.length());
		if (r.equals("jpg"))
		return r;
	}

}
