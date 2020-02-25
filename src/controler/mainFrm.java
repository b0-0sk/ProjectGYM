package controler;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;


public class mainFrm {
	
    public static void main(String[] ar) {
    	
//        formulario1.setSize(800,600); //tamaño de la ventana
//        formulario1.setVisible(true); //que se visible
//        formulario1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //instruccion de cierre        
//        formulario1.setLocationRelativeTo(null); //hacer que este centrada en la pantalla
//        
        
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
	    int height = pantalla.height;
	    int width = pantalla.width;

    } 
}
