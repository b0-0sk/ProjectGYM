package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SpringLayout;
import java.awt.Color;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JTextField;

public class frmLogin extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JPanel panel;
	
	private JTextField user;
	private JTextField password;
	
	private JLabel lblIcon;
	private JLabel lblLogin;
	private JLabel lblUser;
	private JLabel lblPassword;
	

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
	 */
	public frmLogin() {
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.LIGHT_GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		SpringLayout sl_contentPanel = new SpringLayout();
		contentPanel.setLayout(sl_contentPanel);
		
		/*
		 * 
		 * JPanel
		 * 
		 */
		
		panel = new JPanel();
		sl_contentPanel.putConstraint(SpringLayout.NORTH, panel, 10, SpringLayout.NORTH, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.WEST, panel, 20, SpringLayout.WEST, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, panel, -15, SpringLayout.SOUTH, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.EAST, panel, -21, SpringLayout.EAST, contentPanel);
		panel.setBackground(Color.LIGHT_GRAY);
		contentPanel.add(panel);
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		/*
		 * 
		 * JLabel
		 * 
		 */
		
		lblIcon = new JLabel("");
		lblIcon.setIcon(new ImageIcon("C:\\Users\\b0_0sk\\git\\ProjectGYM\\Icon\\Login\\android-icon-72x72.png"));
		panel.add(lblIcon);
		
		lblLogin = new JLabel("LOGIN");
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 22));
		panel.add(lblLogin, "4, 2");
		
		lblUser = new JLabel("User:");
		lblUser.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel.add(lblUser, "4, 4, right, default");
		
		lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel.add(lblPassword, "4, 6, right, default");
		
		/*
		 * 
		 * TextFields
		 * 
		 */
		
		user = new JTextField();
		panel.add(user, "6, 4, 7, 1, fill, default");
		user.setColumns(10);
		
		password = new JTextField();
		panel.add(password, "6, 6, 7, 1, fill, default");
		password.setColumns(10);
		
	}
}
