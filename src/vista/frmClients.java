package vista;

import java.awt.BorderLayout;

import java.awt.FlowLayout;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
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
import com.toedter.calendar.JDayChooser;
import com.toedter.calendar.JDateChooser;
import com.toedter.components.JSpinField;


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

@SuppressWarnings("serial")
public class frmClients extends JDialog {


	private JDesktopPane desktopPane;
	private JPanel panel;
	private JPanel dateSearchPanel;
	private JPanel actionsPanel;
	private JPanel clientViewPanel;
	
	private JScrollPane scrollCalendar;
	private JTable gymTable;
	
	private static DefaultTableModel modelCalendar;
	private DefaultTableCellRenderer centerRenderer;
	
	private JDateChooser dateChooserDe;
    private JDateChooser dateChooserHasta;
    
	private JButton btnFiltrar;
    private JButton btnFichar;
    
    private JLabel label;
    private JLabel lblEscogeLaFecha;
    private JLabel lblDesde;
    private JLabel lblHasta;
    private JLabel lblNewLabel;
    private JLabel lblAccions;
    private JLabel lblClientView;
    
    private Component horizontalGlue;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			frmClients dialog = new frmClients();
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
	
	public frmClients() throws SQLException {
		getContentPane().setBackground(Color.WHITE);
		setBounds(100, 100, 981, 628);
		
		//Añadir titulos
		addTitle();
		
		//Añadir Tabla
		addTable();
		
		//Añadir elementos de la vista
		addElements();
		
		//Añador Listenners
		addListenners();
		
		//Resfrescar la Tabla
		update();
	}
	
	
	public void addTitle(){
		
		scrollCalendar = new JScrollPane();
		scrollCalendar.setBounds(20, 80, 554, 397);
		scrollCalendar.setViewportBorder(null);
		scrollCalendar.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollCalendar.setToolTipText("");
		String[] dataCommand = new String[] {"GYM","E/S","Date"};
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
			
			/*
			 * 
			 * JTable
			 * 
			 */
			
			gymTable = new JTable(modelCalendar);
		    gymTable.getTableHeader().setReorderingAllowed(false); // columns drawing
		    getContentPane().setLayout(null);

		    scrollCalendar.setViewportView(gymTable); //Scrolls table
		    getContentPane().add(scrollCalendar);
		  	
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	public void addElements() {
		
		/*
	     * 
	     * JDesktopPanel 
	     * 
	     */
	    
	    desktopPane = new JDesktopPane();
	    scrollCalendar.setColumnHeaderView(desktopPane);
	    
	    /*
	     * 
	     * JPanel
	     * 
	     */
	    
	    panel = new JPanel();
	    panel.setBackground(SystemColor.activeCaptionBorder);
	    panel.setBounds(584, 80, 371, 397);
	    getContentPane().add(panel);
	    panel.setLayout(null);
	    
	    dateSearchPanel = new JPanel();
	    dateSearchPanel.setBounds(10, 11, 351, 155);
	    panel.add(dateSearchPanel);
	    dateSearchPanel.setLayout(new FormLayout(new ColumnSpec[] {
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
	    
	    actionsPanel = new JPanel();
	    actionsPanel.setBounds(102, 177, 171, 209);
	    panel.add(actionsPanel);
	    actionsPanel.setLayout(new FormLayout(new ColumnSpec[] {
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
	    
	    clientViewPanel = new JPanel();
	    clientViewPanel.setBackground(Color.LIGHT_GRAY);
	    clientViewPanel.setBounds(20, 11, 935, 57);
	    getContentPane().add(clientViewPanel);
	    
	    /*
	     * 
	     * JDateChooser
	     * 
	     */
	    
	    dateChooserDe = new JDateChooser();
	    dateSearchPanel.add(dateChooserDe, "4, 4, 14, 1, fill, center");
	    
	    dateChooserHasta = new JDateChooser();
	    dateSearchPanel.add(dateChooserHasta, "4, 6, 14, 1, fill, center");
	    
	    
	    /*
	     * 
	     * JButtons
	     * 
	     */
	    
	    btnFiltrar = new JButton("Filtrar");
	    dateSearchPanel.add(btnFiltrar, "4, 8");
	    
	    btnFichar = new JButton("Fichar");
	    actionsPanel.add(btnFichar, "4, 6, fill, default");
	    
	    
	    /*
	     * 
	     * JLabels
	     * 
	     */
	   		    
	    label = new JLabel("");
	    label.setIcon(new ImageIcon("C:\\Users\\b0_0sk\\git\\ProjectGYM\\Icon\\Calendario\\android-icon-36x36.png"));
	    dateSearchPanel.add(label, "2, 2");
	    
	    lblEscogeLaFecha = new JLabel("Escoge la fecha:");
	    lblEscogeLaFecha.setFont(new Font("Tahoma", Font.BOLD, 16));
	    dateSearchPanel.add(lblEscogeLaFecha, "4, 2");
	    
	    lblDesde = new JLabel("Desde:");
	    dateSearchPanel.add(lblDesde, "2, 4");
	    
	    lblHasta = new JLabel("Hasta:");
	    dateSearchPanel.add(lblHasta, "2, 6");		    
	   
	    lblNewLabel = new JLabel("");
	    lblNewLabel.setIcon(new ImageIcon("C:\\Users\\b0_0sk\\git\\ProjectGYM\\Icon\\Acciones\\android-icon-36x36.png"));
	    actionsPanel.add(lblNewLabel, "2, 2");
	    
	    lblAccions = new JLabel("Acciones");
	    lblAccions.setFont(new Font("Tahoma", Font.BOLD, 16));
	    actionsPanel.add(lblAccions, "4, 2");
	    
	    lblClientView = new JLabel("Client View");
	    lblClientView.setFont(new Font("Tahoma", Font.BOLD, 30));
	    lblClientView.setBackground(Color.DARK_GRAY);
	    clientViewPanel.add(lblClientView);
	    
	    /*
	     * 
	     * DefaultTableCellRenderer
	     * 
	     */
	    
	 	centerRenderer = new DefaultTableCellRenderer(); // Centre les dades del camp
	  	centerRenderer.setHorizontalAlignment( JLabel.CENTER );
	  	gymTable.setDefaultRenderer(Object.class, centerRenderer);
	  	
	  	/*
	  	 * 
	  	 * Compents
	  	 * 
	  	 */
	  	
	  	horizontalGlue = Box.createHorizontalGlue();
	  	actionsPanel.add(horizontalGlue, "12, 4");
	}
	
	public void addListenners() {
		
		btnFichar.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(final MouseEvent e) {
			
				try {
					
					if (btnFichar.isEnabled()) {
						System.out.println("Activando BUTTON FILTRAR");

					}else {
						System.out.println("No funciono");
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
						System.out.println("Activando BUTTON FILTRAR");

					}else {
						System.out.println("No funciono");
					}
	
					
					
				} catch (Exception e2) {
					// TODO: handle exception
					
				}
			
			}
		});
		
	}
	
	public void update() {
		
		modelCalendar.setRowCount(0);

		//sqlCommand = new SQLCommand();
		//command = sqlCommand.queryCommand("command",status);
		
	  	String concludeDate;
	  	
	  	
		/**
		 * 
		 * Afegint les dades en el model de la taula Command
		 * 
		 */
	  	
	  	/*for (int i = 0; i < command.size(); i++) {
	  		
		  	if (command.get(i).getConcludeDate().equals("00")) {concludeDate = "Unspecified date";}  		
		  	else {concludeDate = command.get(i).getConcludeDate();}
		  	
		  	
		  	
		  	System.out.println(command.get(i).getConcludeDate());
				  	
		  	modelCommand.addRow(new Object[]{
	  				command.get(i).getId_command(),
	  				command.get(i).getId_client(),
	  				command.get(i).getOvertureDate(),
	  				concludeDate,
	  				command.get(i).getStatus(),
	  				command.get(i).getTotalPrice()	
	  		});
		  }*/
	  	
	  	for (int i = 0; i < 50; i++) {
	  		modelCalendar.addRow(new Object[] {null,null,null,null,null,null,null});
		}		  	
	 
	}

	
}

