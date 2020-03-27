package vista;

import java.awt.BorderLayout;


import java.awt.FlowLayout;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.SpringLayout;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JToggleButton;
import javax.swing.ScrollPaneConstants;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import com.toedter.calendar.JDayChooser;
import com.toedter.calendar.JDateChooser;
import com.toedter.components.JSpinField;

import data.SQLE_S;
import data.SQLClients;
import data.SQLGYMS;

import model.Client;
import model.E_S;
import model.GYMS;

import com.toedter.calendar.JYearChooser;
import com.toedter.calendar.JCalendar;
import com.toedter.components.JLocaleChooser;
import com.toedter.calendar.JMonthChooser;
import javax.swing.JDesktopPane;
import javax.swing.JList;
import java.awt.Font;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.LayoutStyle.ComponentPlacement;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class frmAdmin extends JFrame {


	private SQLE_S sqlE_S;
	private SQLClients sqlClient;
	private SQLGYMS sqlGYMS;

	private static ArrayList<E_S> searchE_S = new ArrayList<E_S>();
	private static ArrayList<Client> searchClients = new ArrayList<Client>();
	private static ArrayList<GYMS> searchGYMS = new ArrayList<GYMS>();

	private JScrollPane scrollCalendar;
	private JTable gymTable;
	private static DefaultTableModel modelCalendar;
	private DefaultTableCellRenderer centerRenderer;
	
	
	private JTextField gymidField, movimentidField, clientidField, userField, e_sField,	dateField;
	
	private JButton btnFiltrar;
	private JButton btnAgregar;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JButton btnFichar;
	private JButton btnAplicar;
	
	private JDateChooser dateChooserHasta;
    private JDateChooser dateChooserDesde;
    
    private JDesktopPane desktopPane;
    private JPanel panelGlobal;
    private JPanel panelAdminView;
    private JPanel panelEscogerFecha;
    private JPanel panelAcciones;
    private JPanel panelCampos;
    
    private JLabel label;
    private JLabel lblEscogeLaFecha;
    private JLabel lblDesde;
    private JLabel lblHasta;
    private JLabel lblIcon;
    private JLabel lblAccions;
    private JLabel lblIcono;
    private JLabel lblCampos;
    private JLabel lblGymId;
    private JLabel lblMovimentId;
    private JLabel lblUser;
    private JLabel lblClientId;
    private JLabel lblEs;
    private JLabel lblFecha;
    private JLabel lblAdminView;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			frmAdmin dialog = new frmAdmin();
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
	
	public frmAdmin() throws SQLException {
		setBounds(100, 100, 1021, 631);
    	setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\b0_0sk\\git\\ProjectGYM\\Icon\\APP\\elite-gym.png"));

		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnAccions = new JMenu("Tables");
		menuBar.add(mnAccions);
		
		JMenuItem mntmTablaUsuaris = new JMenuItem("Tabla Usuaris");
		mnAccions.add(mntmTablaUsuaris);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Tabla Moviments");
		mnAccions.add(mntmNewMenuItem);
		
		//Añadir Titulos
		addTitle();
		
		//Aádir componentes de la tabla
		addTable();

		//Añadir Elementos
		addElements();
		
		//Añadir Listenners
		addListenners();
		
		//Refrescar la tabla
		update();
		
	}
	
	public void setBooleansDate(String s) {
		
		btnFiltrar.setEnabled((s == "true")? true:false);
		
	}
	
	public void setBooleansActions(String s) {
		
		btnAgregar.setEnabled((s == "true")? true:false);
		btnModificar.setEnabled((s == "true")? true:false);
		btnEliminar.setEnabled((s == "true")? true:false);
		btnFichar.setEnabled((s == "true")? true:false);
		
	}
	
	public void setBooleansFields(String s) {
		
		gymidField.setEnabled((s == "true")? true:false);
		movimentidField.setEnabled((s == "true")? true:false);
		userField.setEnabled((s == "true")? true:false);
		clientidField.setEnabled((s == "true")? true:false);
		e_sField.setEnabled((s == "true")? true:false);
		dateField.setEnabled((s == "true")? true:false);
		
		btnAplicar.setEnabled((s == "true")? true:false);
		
	}
	
	
	public void addTitle(){
		
		scrollCalendar = new JScrollPane();
		scrollCalendar.setBounds(42, 80, 532, 432);
		scrollCalendar.setViewportBorder(null);
		scrollCalendar.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollCalendar.setToolTipText("");
		String[] dataCommand = new String[] {"Gym ID","Moviment ID","Client ID","User","E/S","Date"};
	    modelCalendar = new DefaultTableModel(new Object[][] {},dataCommand ) {
	    	
	    	public boolean isCellEditable(int r, int c){
	    		return false;
	    	}
	    };
		
	    
	}

	public void addTable() throws SQLException {
		
		/*
		 * 
		 * CALENDAR TABLE
		 * 
		 */
		
		try {
			
			gymTable = new JTable(modelCalendar);
		    gymTable.getTableHeader().setReorderingAllowed(false); // columns drawing
		    getContentPane().setLayout(null);

		    scrollCalendar.setViewportView(gymTable); //Scrolls table
		    getContentPane().add(scrollCalendar);
		    		    
		 	centerRenderer = new DefaultTableCellRenderer(); // Centre les dades del camp
		  	centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		  	gymTable.setDefaultRenderer(Object.class, centerRenderer);
		  	
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	public void addElements() {
		
		try {
			
			
			/**
			 * 
			 * JDesktopPane
			 * 
			 */
			
			desktopPane = new JDesktopPane();
		    scrollCalendar.setColumnHeaderView(desktopPane);
		    
		    /**
		     * 
		     * JPanels
		     * 
		     */
		    
		    panelGlobal = new JPanel();
		    panelGlobal.setBackground(SystemColor.activeCaptionBorder);
		    panelGlobal.setBounds(584, 80, 396, 432);
		    getContentPane().add(panelGlobal);
		    panelGlobal.setLayout(null);
		    
		    panelEscogerFecha = new JPanel();
		    panelEscogerFecha.setBounds(10, 11, 376, 156);
		    panelGlobal.add(panelEscogerFecha);
		    panelEscogerFecha.setLayout(new FormLayout(new ColumnSpec[] {
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
		    		FormSpecs.DEFAULT_COLSPEC,
		    		FormSpecs.RELATED_GAP_COLSPEC,
		    		FormSpecs.DEFAULT_COLSPEC,
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
		    		RowSpec.decode("default:grow"),
		    		FormSpecs.RELATED_GAP_ROWSPEC,
		    		RowSpec.decode("default:grow"),
		    		FormSpecs.RELATED_GAP_ROWSPEC,
		    		FormSpecs.DEFAULT_ROWSPEC,
		    		FormSpecs.RELATED_GAP_ROWSPEC,
		    		FormSpecs.DEFAULT_ROWSPEC,}));
		    
		    panelAcciones = new JPanel();
		    panelAcciones.setBounds(10, 177, 171, 244);
		    panelGlobal.add(panelAcciones);
		    panelAcciones.setLayout(new FormLayout(new ColumnSpec[] {
		    		FormSpecs.RELATED_GAP_COLSPEC,
		    		FormSpecs.DEFAULT_COLSPEC,
		    		FormSpecs.RELATED_GAP_COLSPEC,
		    		FormSpecs.DEFAULT_COLSPEC,
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
		    		FormSpecs.DEFAULT_ROWSPEC,
		    		FormSpecs.RELATED_GAP_ROWSPEC,
		    		FormSpecs.DEFAULT_ROWSPEC,
		    		FormSpecs.RELATED_GAP_ROWSPEC,
		    		FormSpecs.DEFAULT_ROWSPEC,}));
		    
		    panelCampos = new JPanel();
		    panelCampos.setBounds(201, 177, 185, 244);
		    panelGlobal.add(panelCampos);
		    panelCampos.setLayout(new FormLayout(new ColumnSpec[] {
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
		    		FormSpecs.DEFAULT_ROWSPEC,
		    		FormSpecs.RELATED_GAP_ROWSPEC,
		    		FormSpecs.DEFAULT_ROWSPEC,
		    		FormSpecs.RELATED_GAP_ROWSPEC,
		    		FormSpecs.DEFAULT_ROWSPEC,
		    		FormSpecs.RELATED_GAP_ROWSPEC,
		    		FormSpecs.DEFAULT_ROWSPEC,
		    		FormSpecs.RELATED_GAP_ROWSPEC,
		    		FormSpecs.DEFAULT_ROWSPEC,
		    		FormSpecs.RELATED_GAP_ROWSPEC,
		    		FormSpecs.DEFAULT_ROWSPEC,
		    		FormSpecs.RELATED_GAP_ROWSPEC,
		    		FormSpecs.DEFAULT_ROWSPEC,}));
		    
		    panelAdminView = new JPanel();
		    panelAdminView.setBackground(Color.LIGHT_GRAY);
		    panelAdminView.setBounds(42, 11, 938, 58);
		    getContentPane().add(panelAdminView);
		    
		    /**
		     * 
		     * JDate Choosers
		     * 
		     */
		    
		    dateChooserDesde = new JDateChooser();
		    panelEscogerFecha.add(dateChooserDesde, "4, 4, 14, 1, fill, center");
		    
		    dateChooserHasta = new JDateChooser();
		    panelEscogerFecha.add(dateChooserHasta, "4, 6, 14, 1, fill, center");
		    
		    /*
		     * 
		     * JLabels
		     * 
		     */
		    		    
		    label = new JLabel("");
		    label.setIcon(new ImageIcon("C:\\Users\\b0_0sk\\git\\ProjectGYM\\Icon\\Calendario\\android-icon-36x36.png"));
		    panelEscogerFecha.add(label, "2, 2");
		    
		    lblEscogeLaFecha = new JLabel("Escoge la fecha:");
		    lblEscogeLaFecha.setFont(new Font("Tahoma", Font.BOLD, 16));
		    panelEscogerFecha.add(lblEscogeLaFecha, "4, 2");
		    
		    lblDesde = new JLabel("Desde:");
		    panelEscogerFecha.add(lblDesde, "2, 4");
		    
		    lblHasta = new JLabel("Hasta:");
		    panelEscogerFecha.add(lblHasta, "2, 6");
		    		    
		    lblIcon = new JLabel("");
		    lblIcon.setEnabled(false);
		    lblIcon.setIcon(new ImageIcon("C:\\Users\\b0_0sk\\git\\ProjectGYM\\Icon\\Acciones\\android-icon-36x36.png"));
		    panelAcciones.add(lblIcon, "2, 2");
		    
		    lblAccions = new JLabel("Acciones");
		    lblAccions.setEnabled(false);
		    lblAccions.setFont(new Font("Tahoma", Font.BOLD, 16));
		    panelAcciones.add(lblAccions, "4, 2");
		    
		    lblIcono = new JLabel("");
		    lblIcono.setEnabled(false);
		    lblIcono.setIcon(new ImageIcon("C:\\Users\\b0_0sk\\git\\ProjectGYM\\Icon\\fields\\android-icon-36x36.png"));
		    panelCampos.add(lblIcono, "2, 2, center, default");
		    
		    lblCampos = new JLabel("Campos");
		    lblCampos.setEnabled(false);
		    lblCampos.setFont(new Font("Tahoma", Font.BOLD, 16));
		    panelCampos.add(lblCampos, "4, 2, center, default");
		    
		    lblGymId = new JLabel("Gym ID");
		    lblGymId.setEnabled(false);
		    panelCampos.add(lblGymId, "2, 4, center, default");
		    
		    lblMovimentId = new JLabel("Moviment ID");
		    lblMovimentId.setEnabled(false);
		    panelCampos.add(lblMovimentId, "2, 6, center, default");
		    
		    lblUser = new JLabel("User");
		    lblUser.setEnabled(false);
		    panelCampos.add(lblUser, "2, 8, center, default");
		    
		    lblClientId = new JLabel("Client ID");
		    lblClientId.setEnabled(false);
		    panelCampos.add(lblClientId, "2, 10, center, default");
		    
		    lblEs = new JLabel("E/S");
		    lblEs.setEnabled(false);
		    panelCampos.add(lblEs, "2, 12, center, default");
		    
		    lblFecha = new JLabel("Fecha");
		    lblFecha.setEnabled(false);
		    panelCampos.add(lblFecha, "2, 14, center, center");
		    
		    lblAdminView = new JLabel("Admin View");
		    lblAdminView.setFont(new Font("Tahoma", Font.BOLD, 30));
		    panelAdminView.add(lblAdminView);
		    
		    
		    
		    /*
		     *
		     * Components
		     *
		     */
		      		    
		    Component horizontalGlue = Box.createHorizontalGlue();
		    panelAcciones.add(horizontalGlue, "12, 4");
		    
		    Component horizontalGlue_1 = Box.createHorizontalGlue();
		    panelCampos.add(horizontalGlue_1, "12, 4");
		    
		    /*
		     * 
		     * JButtons
		     * 
		     */
		    
		    btnFiltrar = new JButton("Filtrar");
		    btnFiltrar.setEnabled(false);
		    panelEscogerFecha.add(btnFiltrar, "4, 8");
		    
		    btnAgregar = new JButton("Agregar");
		    btnAgregar.setEnabled(false);
		    panelAcciones.add(btnAgregar, "4, 4, fill, default");
		    
		    btnModificar = new JButton("Modificar");
		    btnModificar.setEnabled(false);
		    panelAcciones.add(btnModificar, "4, 6, fill, default");
		    
		    btnEliminar = new JButton("Eliminar");
		    btnEliminar.setEnabled(false);
		    panelAcciones.add(btnEliminar, "4, 8, fill, default");
		    
		    btnFichar = new JButton("Fichar");
		    btnFichar.setEnabled(false);
		    panelAcciones.add(btnFichar, "4, 10");
		    
		    btnAplicar = new JButton("Aplicar");
		    btnAplicar.setEnabled(false);
		    panelCampos.add(btnAplicar, "2, 16");
		    
		    /*
		     * 
		     * JTextFields
		     * 
		     */
				   
		    gymidField = new JTextField();
		    gymidField.setEnabled(false);
		    panelCampos.add(gymidField, "4, 4, fill, default");
		    gymidField.setColumns(10);
		    
		    movimentidField = new JTextField();
		    movimentidField.setEnabled(false);
		    panelCampos.add(movimentidField, "4, 6, fill, default");
		    movimentidField.setColumns(10);
		    
		    clientidField = new JTextField();
		    clientidField.setEnabled(false);
		    panelCampos.add(clientidField, "4, 8, fill, default");
		    clientidField.setColumns(10);
		    
		    userField = new JTextField();
		    userField.setEnabled(false);
		    panelCampos.add(userField, "4, 10, fill, default");
		    userField.setColumns(10);
		    
		    e_sField= new JTextField();
		    e_sField.setEnabled(false);
		    panelCampos.add(e_sField, "4, 12, fill, default");
		    e_sField.setColumns(10);
		    
		    dateField = new JTextField();
		    dateField.setEnabled(false);
		    panelCampos.add(dateField, "4, 14, fill, default");
		    dateField.setColumns(10);
		    
		    
		    
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	

	
	public void update() throws SQLException {
		
		modelCalendar.setRowCount(0);

		sqlE_S = new SQLE_S();
		searchE_S = sqlE_S.queryE_S();
		
		sqlClient = new SQLClients();
		searchClients = sqlClient.queryClients();
		
	  	
		/**
		 * 
		 * Afegint les dades en el model de la taula Admin
		 * 
		 */
		
		
		for (int i = 0; i < searchE_S.size(); i++) {
			
			modelCalendar.addRow(new Object[] {
	
				searchE_S.get(i).getGymID(),
				searchE_S.get(i).getMovimentsID(),
				searchE_S.get(i).getUserID(),
				searchClients.get(i).getUser(),
				searchE_S.get(i).getE_s(),
				searchE_S.get(i).getData()
	
			});
			
			
		}
	  	
	  	
	  	/*
	  	for (int i = 0; i < 50; i++) {
	  		modelCalendar.addRow(new Object[] {null,null,null,null,null,null,null});
		}*/		  	
	 
	}
	
	
	public void addListenners() {
		
		btnFiltrar.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(final MouseEvent e) {
			
				try {
					
					if (btnFiltrar.isEnabled()) {
						System.out.println("Funciono");

					}else {
						System.out.println("No funciono");
					}
	
					
					
				} catch (Exception e2) {
					// TODO: handle exception
					
				}
			
			}
		});
		
		btnAplicar.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(final MouseEvent e) {
			
				try {
					
					if (btnAplicar.isEnabled()) {
						
						System.out.println("Activando BUTTON FILTRAR");
						
						btnFiltrar.setEnabled(true);
						
					}else {
						System.out.println("No funciono");
					}
	
					
					
				} catch (Exception e2) {
					// TODO: handle exception
					
				}
			
			}
		});
		
		
	}

	
}

