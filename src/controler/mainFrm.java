package controler;

import java.awt.Dimension;
import vista.*;
import java.awt.Toolkit;
import java.sql.SQLException;

import javax.swing.JDialog;
import javax.swing.JFrame;


public class mainFrm {
	

	public static void main(String[] ar) throws SQLException {
    	
//        formulario1.setSize(800,600); //tamaño de la ventana
//        formulario1.setVisible(true); //que se visible
//        formulario1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //instruccion de cierre        
//        formulario1.setLocationRelativeTo(null); //hacer que este centrada en la pantalla
//            
    	frmAdmin frame = new frmAdmin(); 
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
	      int height = pantalla.height;
	      int width = pantalla.width;
	    frame.setSize(1020, 636);		
	    int both = JFrame.MAXIMIZED_BOTH;
		//frame.setExtendedState((frame.getExtendedState() & both) != both ? both : JFrame.NORMAL);	    
		frame.setLocationRelativeTo(null);		
	    frame.setVisible(true);

    } 
}
