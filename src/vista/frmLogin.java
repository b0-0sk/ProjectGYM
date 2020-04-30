package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import data.SQLGYMS;
import model.GYMS;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPasswordField;

public class frmLogin extends JDialog {
	
	private final JPanel contentPanel = new JPanel();
	
	private SQLGYMS sqlGYMS;
	
	private static ArrayList<GYMS> searchGYMS = new ArrayList<GYMS>();
    private JComboBox<String> gymsNames;
	private JTextField user;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			frmLogin dialog = new frmLogin();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @throws SQLException 
	 */
	public frmLogin() throws SQLException {
		
		addElements();
		
		//Desplegable Gyms
		addGyms();
	}
	
	
	public void addElements(){
		setBackground(Color.LIGHT_GRAY);
		setResizable(false);
		getContentPane().setBackground(Color.LIGHT_GRAY);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 444, 20);
		contentPanel.setBackground(Color.LIGHT_GRAY);
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 238, 444, 33);
			buttonPane.setBackground(Color.LIGHT_GRAY);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		JLabel lblUser = new JLabel("User:");
		lblUser.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblUser.setBounds(154, 107, 56, 28);
		getContentPane().add(lblUser);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblPassword.setBounds(98, 146, 119, 19);
		getContentPane().add(lblPassword);
		
		user = new JTextField();
		user.setColumns(10);
		user.setBounds(227, 115, 153, 20);
		getContentPane().add(user);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("E:\\DAM\\CODE\\git\\Java\\ProjectGYM\\Icon\\Login\\android-icon-72x72.png"));
		label.setBounds(10, 22, 78, 78);
		getContentPane().add(label);
		
		JLabel lblLogin = new JLabel("LOGIN");
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblLogin.setBounds(98, 53, 59, 14);
		getContentPane().add(lblLogin);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(227, 149, 153, 20);
		getContentPane().add(passwordField);
		
		gymsNames = new JComboBox();
		gymsNames.setBounds(227, 180, 153, 20);
		getContentPane().add(gymsNames);
		
		JLabel lblGym = new JLabel("Gym:");
		lblGym.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblGym.setBounds(154, 181, 63, 19);
		getContentPane().add(lblGym);
	}
	
	public void addGyms() throws SQLException {

		sqlGYMS = new SQLGYMS();
		searchGYMS = sqlGYMS.queryGYMS();
			
		/**
		 * 
		 * Afegint les dades en el desplegable GYM
		 * 
		 */
		
		for (int i = 0; i < searchGYMS.size(); i++) {
			gymsNames.addItem(searchGYMS.get(i).getName().toString());
		}
		
	}
}
