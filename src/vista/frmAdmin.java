package vista;

import java.awt.BorderLayout;


import java.awt.FlowLayout;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
import javax.swing.table.TableColumnModel;
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
import com.toedter.calendar.JTextFieldDateEditor;

import javax.swing.JDesktopPane;
import javax.swing.JList;
import java.awt.Font;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
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
import javax.swing.JSpinner;
import javax.swing.JCheckBox;
import javax.swing.SpinnerNumberModel;
import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class frmAdmin extends JFrame {


	private SQLE_S sqlE_S;
	private SQLClients sqlClient;
	private SQLGYMS sqlGYMS;

	private static ArrayList<E_S> searchE_S = new ArrayList<E_S>();
	private static ArrayList<Client> searchClients = new ArrayList<Client>();
	private static ArrayList<Client> searchClients2 = new ArrayList<Client>();

	private static ArrayList<GYMS> searchGYMS = new ArrayList<GYMS>();

	private JScrollPane scrollCalendar;
	private JTable gymTable;
	private static DefaultTableModel modelCalendar;
	private DefaultTableCellRenderer centerRenderer;
	
	
	private JTextField gymidField, movimentidField, clientidField, userField, e_sField,	dateField;
	
	private JTextFieldDateEditor editorHasta;
	private JTextFieldDateEditor editorDesde;
	
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
    private JPanel panelAcciones;
    private JPanel panelCampos;
    private JPanel panelEscogerFecha;
    
	private static ArrayList<Client> sqlFieldClient = new ArrayList<Client>();
	private static ArrayList<E_S> sqlFieldE_S = new ArrayList<E_S>();
	private String dniClient;
	private ArrayList<String> fieldsAdminToUpdate;
	
    private JLabel label;
    private JLabel lblEscogeLaFecha;
    private JLabel lblDesde;
    private JLabel lblHasta;
    private JLabel lblIcon;
    private JLabel lblAccions;
    private JLabel lblIcono;
    private JLabel lblCampos;
    private JLabel lblHoraDesde;

    private JLabel lblGymId;
    private JLabel lblMovimentId;
    private JLabel lblUser;
    private JLabel lblClientId;
    private JLabel lblEs;
    private JLabel lblFecha;
    private JLabel lblHoraHasta;

    private JLabel lblAdminView;
    private JComboBox<String> gymsNames;
    private JComboBox<String> userNames;

    Date dateFromDateChooserDesde;
    Date dateFromDateChooserHasta;
    private List<LocalDate> datesAdmin;
    
    private String dateDesde;
    private String dateHasta;
    private JSpinner spinnerMinutosDesde;
    private JSpinner spinnerHoraDesde;
    private JCheckBox checkBoxHorasMinutos;
    private JSpinner spinnerHoraHasta;
    private JSpinner spinnerMinutosHasta;
    private JLabel lblDni;
    private JTextField dniSearch;
    private JCheckBox checkBoxDNI;
    
    
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
		setBounds(100, 100, 1144, 662);
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
		
		//Desplegable Gyms
		addGyms();
		
		//Añadiendo Users en el desplegable
		addUserName();
		
		//Refrescar Tabla
		update("","","");
		
		
	}
	public void addUserName() throws SQLException{
		
		sqlClient = new SQLClients();
		searchClients = sqlClient.queryClients("");
		
		/**
		 * 
		 * Afegint les dades en el desplegable GYM
		 * 
		 */
		for (int i = 0; i < searchClients.size(); i++) {
			userNames.addItem(searchClients.get(i).getUser().toString());
			System.out.println(searchClients.get(i).getUser().toString());
		}
	}
	
	public void addGyms() throws SQLException{
		
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
	
	public void setBooleansDateFilter(String s) {
		
		
		if (s != "true") {
			dateChooserDesde.setCalendar(null);
			dateChooserHasta.setCalendar(null);
		}
		
		btnFiltrar.setEnabled((s == "true")? true:false);

		
	}
	
	public void setBooleansDateChooser(String s) {
		
		dateChooserDesde.setEnabled((s == "true")? true:false);
		dateChooserHasta.setEnabled((s == "true")? true:false);
		
		checkBoxHorasMinutos.setEnabled((s == "true")? true:false);
		checkBoxDNI.setEnabled((s == "true")? true:false);
		
	}
	
	public void setBooleansCheckBoxHorasMinutos(String s) {
		
		spinnerHoraDesde.setEnabled((s == "true")? true:false);
    	spinnerHoraHasta.setEnabled((s == "true")? true:false);
    	spinnerMinutosDesde.setEnabled((s == "true")? true:false);
    	spinnerMinutosHasta.setEnabled((s == "true")? true:false);
    	
	}
	
	public void setEmptyCheckBoxHorasMinutos() {
		
		spinnerHoraDesde.setValue(0);
    	spinnerHoraHasta.setValue(0);
    	spinnerMinutosDesde.setValue(0);
    	spinnerMinutosHasta.setValue(0);
    	
	}
	
	public void setBooleansCheckBoxDNI(String s) {
		
		dniSearch.setEnabled((s == "true")? true:false);
		dniSearch.setEditable((s == "true")? true:false);

    	
	}
	
	public void setEmptyCheckBoxDNI() {
		
		dniSearch.setText("");
		
	}
	
	public void setBooleansActions(String s) {
		
		btnAgregar.setEnabled((s == "true")? true:false);
		btnModificar.setEnabled((s == "true")? true:false);
		btnEliminar.setEnabled((s == "true")? true:false);
		//btnFichar.setEnabled((s == "true")? true:false);
		
	}
		
	public void setBooleansFields(String s) {
		
		
		lblGymId.setEnabled((s == "true")? true:false);
	    lblMovimentId.setEnabled((s == "true")? true:false);
	    lblUser.setEnabled((s == "true")? true:false);
	    lblClientId.setEnabled((s == "true")? true:false);
	    lblEs.setEnabled((s == "true")? true:false);
	    lblFecha.setEnabled((s == "true")? true:false);
		
	    
	    
		gymidField.setEnabled((s == "true")? true:false);
		movimentidField.setEnabled((s == "true")? true:false);
		userField.setEnabled((s == "true")? true:false);
		clientidField.setEnabled((s == "true")? true:false);
		e_sField.setEnabled((s == "true")? true:false);
		dateField.setEnabled((s == "true")? true:false);
		
		btnAplicar.setEnabled((s == "true")? true:false);
		
	}
	
	public void addFieldsInArray() {
		fieldsAdminToUpdate = new ArrayList<String>();

	    for (int i = 0; i < gymTable.getColumnCount(); i++) {
	    	fieldsAdminToUpdate.add(modelCalendar.getValueAt(gymTable.getSelectedRow(),i).toString());
		}
	    
	    for (int i = 0; i < fieldsAdminToUpdate.size(); i++) {
			System.out.print(fieldsAdminToUpdate.get(i) + " ");
		}
	    
	}
	
	public void fillFields() {
		
		
		gymidField.setText(modelCalendar.getValueAt(gymTable.getSelectedRow(),0).toString());
		movimentidField.setText(modelCalendar.getValueAt(gymTable.getSelectedRow(),1).toString());
		userField.setText(modelCalendar.getValueAt(gymTable.getSelectedRow(),2).toString());
		clientidField.setText(modelCalendar.getValueAt(gymTable.getSelectedRow(),3).toString());
		e_sField.setText(modelCalendar.getValueAt(gymTable.getSelectedRow(),4).toString());
		dateField.setText(modelCalendar.getValueAt(gymTable.getSelectedRow(),5).toString());

		
	}
	
	public void fillNullFields() {
		
		gymidField.setText(null);
		movimentidField.setText(null);
		userField.setText(null);
		clientidField.setText(null);
		e_sField.setText(null);
		dateField.setText(null);
	}
	
	
	public void addTitle(){
		   
	    
		scrollCalendar = new JScrollPane();
		scrollCalendar.setBounds(42, 80, 532, 511);
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
		    
		    
		    TableColumnModel columnModel = gymTable.getColumnModel();
		    columnModel.getColumn(5).setPreferredWidth(150);
		    
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
		    panelGlobal.setBounds(584, 80, 532, 511);
		    getContentPane().add(panelGlobal);
		    panelGlobal.setLayout(null);
		    	    
		    panelAcciones = new JPanel();
		    panelAcciones.setBounds(10, 248, 216, 252);
		    panelGlobal.add(panelAcciones);
		    
		    panelCampos = new JPanel();
		    panelCampos.setBounds(274, 248, 248, 252);
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
		    		FormSpecs.DEFAULT_ROWSPEC,
		    		FormSpecs.RELATED_GAP_ROWSPEC,
		    		FormSpecs.DEFAULT_ROWSPEC,}));
		    
		    panelAdminView = new JPanel();
		    panelAdminView.setBackground(Color.LIGHT_GRAY);
		    panelAdminView.setBounds(42, 11, 1074, 58);
		    getContentPane().add(panelAdminView);
		    
		    panelEscogerFecha = new JPanel();
		    panelEscogerFecha.setBounds(10, 11, 512, 226);
		    panelGlobal.add(panelEscogerFecha);
		    
		    /**
		     * 
		     * JDate Choosers
		     * 
		     */
		    panelEscogerFecha.setLayout(null);
		    
		    dateChooserDesde = new JDateChooser();
		    dateChooserDesde.setBounds(73, 92, 156, 20);
		    panelEscogerFecha.add(dateChooserDesde);
		    dateChooserDesde.setToolTipText("");
		    
		    		    lblHoraDesde = new JLabel("Hora:");
		    		    lblHoraDesde.setBounds(293, 76, 35, 14);
		    		    panelEscogerFecha.add(lblHoraDesde);
		    
		    spinnerHoraDesde = new JSpinner();
		    spinnerHoraDesde.setEnabled(false);
		    spinnerHoraDesde.setBounds(338, 73, 39, 20);
		    spinnerHoraDesde.setModel(new SpinnerNumberModel(0, 0, 24, 1));
		    panelEscogerFecha.add(spinnerHoraDesde);
		    
		    JLabel lblMinutosDesde = new JLabel("Minutos");
		    lblMinutosDesde.setBounds(387, 73, 49, 20);
		    panelEscogerFecha.add(lblMinutosDesde);
		    
		    spinnerMinutosDesde = new JSpinner();
		    spinnerMinutosDesde.setEnabled(false);
		    spinnerMinutosDesde.setBounds(446, 73, 39, 20);
		    spinnerMinutosDesde.setModel(new SpinnerNumberModel(0, null, 64, 1));
		    panelEscogerFecha.add(spinnerMinutosDesde);
		    
		    checkBoxHorasMinutos = new JCheckBox("Horas y Minutos");
		    checkBoxHorasMinutos.setBounds(283, 46, 121, 21);
		    panelEscogerFecha.add(checkBoxHorasMinutos);
		    
		    dateChooserHasta = new JDateChooser();
		    dateChooserHasta.setBounds(73, 120, 156, 20);
		    panelEscogerFecha.add(dateChooserHasta);
		    dateChooserHasta.setToolTipText("");
		    
		    editorHasta = (JTextFieldDateEditor) dateChooserHasta.getDateEditor();
		    editorDesde = (JTextFieldDateEditor) dateChooserDesde.getDateEditor();

			editorHasta.setEditable(false);
			editorDesde.setEditable(false);
		    
		    /*
		     * 
		     * JLabels
		     * 
		     */
		    panelAcciones.setLayout(null);
		    		    
		    lblIcon = new JLabel("");
		    lblIcon.setBounds(6, 6, 36, 36);
		    lblIcon.setEnabled(false);
		    lblIcon.setIcon(new ImageIcon("C:\\Users\\b0_0sk\\git\\ProjectGYM\\Icon\\Acciones\\android-icon-36x36.png"));
		    panelAcciones.add(lblIcon);
		    
		    lblAccions = new JLabel("Acciones");
		    lblAccions.setBounds(59, 14, 71, 20);
		    lblAccions.setEnabled(false);
		    lblAccions.setFont(new Font("Tahoma", Font.BOLD, 16));
		    panelAcciones.add(lblAccions);
		    
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
		     * JButtons
		     * 
		     */
		    
		    btnAgregar = new JButton("Agregar");
		    btnAgregar.setBounds(48, 48, 114, 23);
		    btnAgregar.setEnabled(false);
		    panelAcciones.add(btnAgregar);
		    
		    btnAplicar = new JButton("Aplicar");
		    btnAplicar.setEnabled(false);
		    panelCampos.add(btnAplicar, "2, 16");
		    
		    btnModificar = new JButton("Modificar");
		    btnModificar.setBounds(48, 77, 114, 23);
		    btnModificar.setEnabled(false);
		    panelAcciones.add(btnModificar);
		    
		    btnEliminar = new JButton("Eliminar");
		    btnEliminar.setBounds(48, 106, 114, 23);
		    btnEliminar.setEnabled(false);
		    panelAcciones.add(btnEliminar);
		    
		    btnFichar = new JButton("Fichar Salida");
		    btnFichar.setBounds(48, 135, 114, 23);
		    panelAcciones.add(btnFichar);
		    
		    gymsNames = new JComboBox<String>();
		    gymsNames.setBounds(48, 164, 114, 20);
		    gymsNames.setToolTipText("");
		    panelAcciones.add(gymsNames);
		    
		    
		    userNames = new JComboBox();
		    userNames.setBounds(48, 189, 114, 20);
		    panelAcciones.add(userNames);
		    
		    lblHoraHasta = new JLabel("Hora:");
		    lblHoraHasta.setBounds(293, 104, 35, 14);
		    panelEscogerFecha.add(lblHoraHasta);
		    
		    spinnerHoraHasta = new JSpinner();
		    spinnerHoraHasta.setEnabled(false);
		    spinnerHoraHasta.setBounds(338, 101, 39, 20);
		    panelEscogerFecha.add(spinnerHoraHasta);
		    
		    btnFiltrar = new JButton("Filtrar");
		    btnFiltrar.setBounds(31, 192, 156, 23);
		    panelEscogerFecha.add(btnFiltrar);
		    btnFiltrar.setEnabled(false);
		    
		    /*
		     * 
		     * JTextFields
		     * 
		     */
				   
		    gymidField = new JTextField();
		    gymidField.setEnabled(false);
		    panelCampos.add(gymidField, "4, 4, 5, 1, fill, default");
		    gymidField.setColumns(10);
		    
		    movimentidField = new JTextField();
		    movimentidField.setEnabled(false);
		    panelCampos.add(movimentidField, "4, 6, 5, 1, fill, default");
		    movimentidField.setColumns(10);
		    
		    clientidField = new JTextField();
		    clientidField.setEnabled(false);
		    panelCampos.add(clientidField, "4, 8, 5, 1, fill, default");
		    clientidField.setColumns(10);
		    
		    userField = new JTextField();
		    userField.setEnabled(false);
		    panelCampos.add(userField, "4, 10, 5, 1, fill, default");
		    userField.setColumns(10);
		    
		    e_sField= new JTextField();
		    e_sField.setEnabled(false);
		    panelCampos.add(e_sField, "4, 12, 5, 1, fill, default");
		    e_sField.setColumns(10);
		    
		    dateField = new JTextField();
		    dateField.setEnabled(false);
		    panelCampos.add(dateField, "4, 14, 5, 1, fill, default");
		    dateField.setColumns(10);
		    	    
		    label = new JLabel("");
		    label.setBounds(10, 11, 36, 36);
		    panelEscogerFecha.add(label);
		    label.setIcon(new ImageIcon("C:\\Users\\b0_0sk\\git\\ProjectGYM\\Icon\\Calendario\\android-icon-36x36.png"));
		    
		    lblEscogeLaFecha = new JLabel("Escoge la fecha y la hora:");
		    lblEscogeLaFecha.setBounds(56, 11, 211, 36);
		    panelEscogerFecha.add(lblEscogeLaFecha);
		    lblEscogeLaFecha.setFont(new Font("Tahoma", Font.BOLD, 16));
		    
		    lblDesde = new JLabel("Desde:");
		    lblDesde.setBounds(31, 98, 57, 14);
		    panelEscogerFecha.add(lblDesde);
	    
		    lblHasta = new JLabel("Hasta:");
		    lblHasta.setBounds(31, 126, 57, 14);
		    panelEscogerFecha.add(lblHasta);
		    
		    JLabel lblMinutosHasta = new JLabel("Minutos");
		    lblMinutosHasta.setBounds(387, 101, 49, 20);
		    panelEscogerFecha.add(lblMinutosHasta);
		    
		    spinnerMinutosHasta = new JSpinner();
		    spinnerMinutosHasta.setEnabled(false);
		    spinnerMinutosHasta.setBounds(446, 101, 39, 20);
		    panelEscogerFecha.add(spinnerMinutosHasta);
		    
		    lblDni = new JLabel("DNI:");
		    lblDni.setBounds(295, 168, 35, 14);
		    panelEscogerFecha.add(lblDni);
		    
		    dniSearch = new JTextField();
		    dniSearch.setEditable(false);
		    dniSearch.setEnabled(false);
		    dniSearch.setBounds(329, 165, 97, 20);
		    panelEscogerFecha.add(dniSearch);
		    dniSearch.setColumns(10);

		    checkBoxDNI = new JCheckBox("DNI");
		    checkBoxDNI.setBounds(283, 138, 97, 23);
		    panelEscogerFecha.add(checkBoxDNI);
		    	    
		    
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	

	
	
	
	/*
	 * 
	 * DATES : Getting dates between the client search
	 * 
	 */
	
	public static long getDatesBetweenUsing(LocalDate startDate, LocalDate endDate) { // List<LocalDate>  
		  
	    long numOfDaysBetween = ChronoUnit.DAYS.between(startDate, endDate);
	    return numOfDaysBetween;
	    
	}
	
	public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
	    return dateToConvert.toInstant()
	      .atZone(ZoneId.systemDefault())
	      .toLocalDate();
	}
	
	/*public static List<LocalDate> getDatesBetweenUsing(LocalDate startDate, LocalDate endDate) { // List<LocalDate>  
		  
	    long numOfDaysBetween = ChronoUnit.DAYS.between(startDate, endDate);
	    
	    System.out.println(numOfDaysBetween);
	    return IntStream.iterate(0, i -> i + 1)
	      .limit(numOfDaysBetween+1)
	      .mapToObj(i -> startDate.plusDays(i))
	      .collect(Collectors.toList());
	    
	}
	
	public void daysBetween(Date s, Date e) {
		
		datesAdmin = new ArrayList<>();
		
		datesAdmin = getDatesBetweenUsing(
				convertToLocalDateViaInstant(s),
				convertToLocalDateViaInstant(e));					
		
		ArrayList<String> dateAdminFormated = new ArrayList<String>();
		
		for (int i = 0; i < datesAdmin.size(); i++) {
			
			dateAdminFormated.add(datesAdmin.get(i).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
			
		}
		
		for (int i = 0; i < datesAdmin.size(); i++) {
			
			System.out.println(dateAdminFormated.get(i));
			
		}
				
	}*/
	
	public void saveUserDniTableLine() {
		
		dniClient = modelCalendar.getValueAt(gymTable.getSelectedRow(),3).toString();
		//System.out.println(dniClient);
		
	}
	
//	public void updateGymTable() {
//		
//		sqlClient = new SQLClients();
//		sqlE_S = new SQLE_S();
//
//		try {
//			
//			Client c = new Client(
//					fieldsAdminToUpdate.get(2).toString(),
//					fieldsAdminToUpdate.get(3).toString());
//			
//			E_S e_s = new E_S(
//					fieldsAdminToUpdate.get(1).toString(),
//					fieldsAdminToUpdate.get(2).toString(),
//					fieldsAdminToUpdate.get(4).toString()+" 0:00:0",
//					fieldsAdminToUpdate.get(5).toString());		
//			
//			sqlClient.updateClient(c);
//			sqlE_S.updateE_S(e_s);
//
//			
//		} catch (Exception e2) {
//			// TODO: handle exception
//		}
//		
//	}
	
	
	public void insertSignUP(String s){
		
		sqlE_S = new SQLE_S();

		try {
			
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
			LocalDateTime now = LocalDateTime.now();
			System.out.println(dtf.format(now));
			
			E_S e_s = new E_S(
					fieldsAdminToUpdate.get(1).toString(),
					fieldsAdminToUpdate.get(2).toString(),
					dtf.format(now)+":0",
					s);		
			
			sqlE_S.updateE_S(e_s);

	
		} catch (Exception e2) {
			// TODO: handle exception
		}
		
	}

	
	
	/*
	 * 
	 * LISTENNERS
	 * 
	 */
	
	public void addListenners() {
		
		gymTable.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(final MouseEvent e) {
			
				try {

					if (btnFiltrar.isEnabled()) setBooleansDateFilter("false");
					if (panelCampos.isEnabled()) setBooleansDateFilter("false");
					
					setBooleansActions("true");
					
					addFieldsInArray();

					saveUserDniTableLine();

				} catch (Exception e2) {
					// TODO: handle exception
					
				}
			
			}
		});
		
		dateChooserDesde.getCalendarButton().addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(final MouseEvent e) {
					
					
				try {
				
					setBooleansActions("false");
					setBooleansFields("false");
					fillNullFields();
				
				} catch (Exception e2) {
					// TODO: handle exception
					
				}
			
			}
		});
		
		dateChooserDesde.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
			
			        @Override
			        public void propertyChange(PropertyChangeEvent e) {
			        	
			        	dateChooserHasta.getDateEditor().addPropertyChangeListener(
			    			    new PropertyChangeListener() {
			    			        @Override
			    			        public void propertyChange(PropertyChangeEvent e) {
			    			        	
			    			        	if ("date".equals(e.getPropertyName())) {
			    			        		
			    			        		if (!btnFiltrar.isEnabled()) setBooleansDateFilter("true");    					
			    						}  
			    			        }		
			    		});
			        }				
	    });
		
		checkBoxHorasMinutos.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(final MouseEvent e) {
			
				try {
	        	
		        	if (checkBoxHorasMinutos.isSelected()) {
						
			        	setBooleansCheckBoxHorasMinutos("true");
	
					}else {
			        	setBooleansCheckBoxHorasMinutos("false");
			        	setEmptyCheckBoxHorasMinutos();
					}
				} catch (Exception e2) {
					// TODO: handle exception
					
				}
			}
		});
		
		checkBoxDNI.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(final MouseEvent e) {
			
				try {
	        	
		        	if (checkBoxDNI.isSelected()) {
						
			        	setBooleansCheckBoxDNI("true");
			        	
	
					}else {
						setBooleansCheckBoxDNI("false");
			        	setEmptyCheckBoxDNI();
					}
				} catch (Exception e2) {
					// TODO: handle exception
					
				}
			}
		});
		
		btnFiltrar.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(final MouseEvent e) {
			
				try {
					if (btnFiltrar.isEnabled()) {
						
						gymTable.setEnabled(true);
						
						//Date
						dateFromDateChooserDesde = dateChooserDesde.getDate();
						//String %tR
						dateDesde = String.format("%1$tY-%1$tm-%1$td ", dateFromDateChooserDesde);

						dateFromDateChooserHasta = dateChooserHasta.getDate();
						dateHasta = String.format("%1$tY-%1$tm-%1$td ", dateFromDateChooserHasta);
								        
						//daysBetween(dateFromDateChooserDesde,dateFromDateChooserHatsa);
				        
						String horasDesde = spinnerHoraDesde.getValue().toString();
						String minutosDesde = spinnerMinutosDesde.getValue().toString();
						String horasTotalesDesde = horasDesde+":"+minutosDesde+":00";
						
						String horasHasta = spinnerHoraHasta.getValue().toString();
						String minutosHasta = spinnerMinutosHasta.getValue().toString();
						String horasTotalesHasta= horasHasta+":"+minutosHasta+":00";
						
						//System.out.println(spinnerHora.getValue().toString()+":"+spinnerMinutos.getValue().toString());
						
						if ((Integer)spinnerHoraDesde.getValue() < 9) {
							horasDesde = "0"+spinnerHoraDesde.getValue().toString();
						}
						
						if ((Integer)spinnerMinutosDesde.getValue() < 9) {
							minutosDesde = "0"+spinnerMinutosDesde.getValue().toString();
							//System.out.println(horasDesde+":"+minutosDesde+":00");
						}
						
						if ((Integer)spinnerHoraHasta.getValue() < 9) {
							horasHasta = "0"+spinnerHoraHasta.getValue().toString();
						}
						
						if ((Integer)spinnerMinutosHasta.getValue() < 9) {
							minutosHasta = "0"+spinnerMinutosHasta.getValue().toString();
						}
										
						if (getDatesBetweenUsing(convertToLocalDateViaInstant(dateFromDateChooserDesde),convertToLocalDateViaInstant(dateFromDateChooserHasta)) >= 0) {
					        
							update(dateDesde+horasTotalesDesde,dateHasta+horasTotalesHasta,dniSearch.getText().toString());
							
						}else {
							
							System.out.println("ERROR INTRODUZCA CORRECTAMENTE LA FECHA");
							
						}
						
					}else {
						checkBoxDNI.setSelected(false);
						checkBoxHorasMinutos.setSelected(false);
					}
					
					
			        
				} catch (Exception e2) {
					// TODO: handle exception
					
				}
			
			}
		});
		
		btnModificar.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(final MouseEvent e) {
			
				try {
					
					if (btnModificar.isEnabled()) {
						
						
						
						setBooleansFields("true");						
						fillFields();		
						
						
						gymTable.setEnabled(false);
						
									


					}else {
						System.out.println("No funciono");
					}
	
				} catch (Exception e2) {
					// TODO: handle exception
					
				}
			
			}
		});
		
		btnFichar.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(final MouseEvent e) {
			
				try {
					
					if (btnFichar.isEnabled()) {
						
						System.out.println("FICHAR: "+btnFichar.getText());
						
						//if (btnFichar.getText() == "Fichar Entrada") {
							
							btnFichar.setText("Fichar Salida");
							System.out.println("Fichar Entrada");
							
							update("","","");
							
							setBooleansDateChooser("true");
							gymTable.setEnabled(true);
							
							
							insertSignUP("E");
							
							
						}else if (btnFichar.getText() == "Fichar Salida") {
							
							//btnFichar.setText("Fichar Entrada");
							System.out.println("Fichar Salida");
							
							gymTable.setEnabled(false);
							update("null","null","null");
							
							setBooleansDateChooser("false");
							setBooleansActions("false");
							
							insertSignUP("S");
						}
						
						//update("","");
						
						
						
						
						
					
		
				} catch (Exception e2) {
					// TODO: handle exception
					
				}
			
			}
		});
		
		btnEliminar.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(final MouseEvent e) {
			
				try {
					
					if (btnEliminar.isEnabled()) {
						System.out.println("Activando BUTTON Eliminar");

					}else {
						System.out.println("No funciono");
					}
	
					
					
				} catch (Exception e2) {
					// TODO: handle exception
					
				}
			
			}
		});
		
		btnAgregar.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(final MouseEvent e) {
			
				try {
					
					if (btnAgregar.isEnabled()) {
						
						setBooleansFields("true");						
						fillFields();
						//updateGymTable();
						
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
						
						//updateGymTable();
						
						
					}else {
						System.out.println("No funciono");
					}
	
					
					
				} catch (Exception e2) {
					// TODO: handle exception
					
				}
			
			}
		});
			
	}
	
	public void update(String s,String e,String dniCli) throws SQLException {


		modelCalendar.setRowCount(0);

		sqlE_S = new SQLE_S();
		
		searchE_S = sqlE_S.queryE_S(s,e,dniCli);
	
		sqlClient = new SQLClients();
		
		
	  	
		/**
		 * 
		 * Afegint les dades en el model de la taula Admin
		 * 
		 */
		
		
		for (int i = 0; i < searchE_S.size(); i++) {
			
			//Formating the dato to look much better
			String dateFormated = searchE_S.get(i).getDate().substring(0,15);	
			
			//To synchronize the userID of Client Table and the data of E_S Table
			searchClients = sqlClient.queryClients(searchE_S.get(i).getUserID());

			modelCalendar.addRow(new Object[] {
					
				searchE_S.get(i).getGymID(),
				searchE_S.get(i).getMovimentsID(),
				searchE_S.get(i).getUserID(),
				searchClients.get(i).getUser(),
				searchE_S.get(i).getE_s(),
				searchE_S.get(i).getDate()
	
			});
			
			
		}	 
	}
}

