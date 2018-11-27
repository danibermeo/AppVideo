package interfaz;



import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class Registro {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registro window = new Registro();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	public Registro() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 770, 568);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{50, 0, 0, 0, 0, 0, 0, 0, 0, 30, 0, 0, 0, 100, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{50, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 22, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblnombre = new JLabel("*Nombre");
		GridBagConstraints gbc_lblnombre = new GridBagConstraints();
		gbc_lblnombre.anchor = GridBagConstraints.EAST;
		gbc_lblnombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblnombre.gridx = 6;
		gbc_lblnombre.gridy = 4;
		panel.add(lblnombre, gbc_lblnombre);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridwidth = 6;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridx = 7;
		gbc_textField.gridy = 4;
		panel.add(textField, gbc_textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Apellidos");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.ABOVE_BASELINE_TRAILING;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 6;
		gbc_lblNewLabel.gridy = 5;
		panel.add(lblNewLabel, gbc_lblNewLabel);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridwidth = 6;
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.gridx = 7;
		gbc_textField_1.gridy = 5;
		panel.add(textField_1, gbc_textField_1);
		
		JLabel lblfechaDeNacimiento = new JLabel("*Fecha de nacimiento");
		GridBagConstraints gbc_lblfechaDeNacimiento = new GridBagConstraints();
		gbc_lblfechaDeNacimiento.anchor = GridBagConstraints.EAST;
		gbc_lblfechaDeNacimiento.insets = new Insets(0, 0, 5, 5);
		gbc_lblfechaDeNacimiento.gridx = 6;
		gbc_lblfechaDeNacimiento.gridy = 6;
		panel.add(lblfechaDeNacimiento, gbc_lblfechaDeNacimiento);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridwidth = 6;
		gbc_textField_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField_2.gridx = 7;
		gbc_textField_2.gridy = 6;
		panel.add(textField_2, gbc_textField_2);
		
		JLabel lblEmail = new JLabel("email");
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.anchor = GridBagConstraints.EAST;
		gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail.gridx = 6;
		gbc_lblEmail.gridy = 7;
		panel.add(lblEmail, gbc_lblEmail);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		GridBagConstraints gbc_textField_4 = new GridBagConstraints();
		gbc_textField_4.gridwidth = 6;
		gbc_textField_4.insets = new Insets(0, 0, 5, 5);
		gbc_textField_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_4.gridx = 7;
		gbc_textField_4.gridy = 7;
		panel.add(textField_4, gbc_textField_4);
		
		JLabel lblusuario = new JLabel("*Usuario");
		GridBagConstraints gbc_lblusuario = new GridBagConstraints();
		gbc_lblusuario.anchor = GridBagConstraints.EAST;
		gbc_lblusuario.insets = new Insets(0, 0, 5, 5);
		gbc_lblusuario.gridx = 6;
		gbc_lblusuario.gridy = 8;
		panel.add(lblusuario, gbc_lblusuario);
		
		textField_3 = new JTextField();
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.gridwidth = 6;
		gbc_textField_3.insets = new Insets(0, 0, 5, 5);
		gbc_textField_3.gridx = 7;
		gbc_textField_3.gridy = 8;
		panel.add(textField_3, gbc_textField_3);
		textField_3.setColumns(10);
		
		JLabel lblcontrasea = new JLabel("*Contrase\u00F1a");
		GridBagConstraints gbc_lblcontrasea = new GridBagConstraints();
		gbc_lblcontrasea.anchor = GridBagConstraints.EAST;
		gbc_lblcontrasea.insets = new Insets(0, 0, 5, 5);
		gbc_lblcontrasea.gridx = 6;
		gbc_lblcontrasea.gridy = 9;
		panel.add(lblcontrasea, gbc_lblcontrasea);
		
		passwordField = new JPasswordField();
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridwidth = 6;
		gbc_passwordField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField.gridx = 7;
		gbc_passwordField.gridy = 9;
		panel.add(passwordField, gbc_passwordField);
		
		JLabel lblrepetirContrasea = new JLabel("*Repetir Contrase\u00F1a");
		GridBagConstraints gbc_lblrepetirContrasea = new GridBagConstraints();
		gbc_lblrepetirContrasea.anchor = GridBagConstraints.EAST;
		gbc_lblrepetirContrasea.insets = new Insets(0, 0, 5, 5);
		gbc_lblrepetirContrasea.gridx = 6;
		gbc_lblrepetirContrasea.gridy = 10;
		panel.add(lblrepetirContrasea, gbc_lblrepetirContrasea);
		
		passwordField_1 = new JPasswordField();
		GridBagConstraints gbc_passwordField_1 = new GridBagConstraints();
		gbc_passwordField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField_1.gridwidth = 6;
		gbc_passwordField_1.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField_1.gridx = 7;
		gbc_passwordField_1.gridy = 10;
		panel.add(passwordField_1, gbc_passwordField_1);
		
		JButton btnRegistrar = new JButton("Registrar");
		GridBagConstraints gbc_btnRegistrar = new GridBagConstraints();
		gbc_btnRegistrar.insets = new Insets(0, 0, 5, 5);
		gbc_btnRegistrar.gridx = 6;
		gbc_btnRegistrar.gridy = 12;
		panel.add(btnRegistrar, gbc_btnRegistrar);
		
		JButton btnCancelar = new JButton("Cancelar");
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.gridwidth = 3;
		gbc_btnCancelar.anchor = GridBagConstraints.WEST;
		gbc_btnCancelar.insets = new Insets(0, 0, 5, 5);
		gbc_btnCancelar.gridx = 7;
		gbc_btnCancelar.gridy = 12;
		panel.add(btnCancelar, gbc_btnCancelar);
		
		JLabel lblcamposObligatorios = new JLabel("*Campos obligatorios");
		GridBagConstraints gbc_lblcamposObligatorios = new GridBagConstraints();
		gbc_lblcamposObligatorios.insets = new Insets(0, 0, 0, 5);
		gbc_lblcamposObligatorios.gridx = 6;
		gbc_lblcamposObligatorios.gridy = 13;
		panel.add(lblcamposObligatorios, gbc_lblcamposObligatorios);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setVisible(true);
	}

}
