package controler;

import java.sql.SQLException;

import data.SQLClients;
import data.SQLE_S;
import data.SQLGYMS;
import model.Client;
import model.E_S;
import model.GYMS;

public class mainTest {
	
	public static void main(String[] args) throws SQLException {	
		
		/*
		 * SQL CLIENTS
		 *
		 
			 SQLClients sqlClients = new SQLClients();
			 Client c1 = new Client("2179982s","Arnau2","Bosch2","Admin12342","6172813772","086402","Barcelona2","arnaubosch16@gmail.com2","ES23 4324 2345 25353","False");
			 Client c2;
			 
			 	System.out.println("Insertar Clients");
			 	sqlClients.insertaClients(c1);
			 	
			 	System.out.println("Eliminar Clients");
			 	//sqlClients.deleteClients(c1);
			
				c2 = new Client("2179982s","ArnauUpdate","Bosch2","Admin12342","6172813772","086402","Barcelona2","arnaubosch16@gmail.com2","ES23 4324 2345 25353","False");
				
				System.out.println("Actualitzar Clients");
				sqlClients.updateClient(c1);
				
				System.out.println("Consutla Clients");
				sqlClients.queryClients();
		
		/*
		 * SQL E_S
		 *
		
			SQLE_S sqlE_S = new SQLE_S();
			E_S e_s1 = new E_S("aaaa0000","0","aaaa","26102000","E");
			E_S e_s2;
			
			 	System.out.println("Insertar E_S");
				sqlE_S.insertaE_S(e_s1);
				
				System.out.println("Eliminar E_S");
				//sqlE_S.deleteE_S(e_s1);
					
				e_s2 = new E_S("aaaa0000","0","aaaa","26102000","E");
				
				System.out.println("Actualizar E_S");
				sqlE_S.updateE_S(e_s2);
				
				System.out.println("Consulta E_S");
				sqlE_S.queryE_S();
				
		/*
		 * SQL GYMS
		 *
				
			SQLGYMS sqlGYMS = new SQLGYMS();
			GYMS gym1 = new GYMS("0","GIMSAL OLESA","08640","Olesa de Montserrat");
			GYMS gym2;
			
			 	System.out.println("Insertar E_S");
				sqlGYMS.insertaGYMS(gym1);
				
				System.out.println("Eliminar E_S");
				//sqlGYMS.deleteGYMS(gym1);
					
				gym2 = new GYMS("0","GIMSAL OLESA","08640","Olesa de Montserrat");
	
				System.out.println("Actualizar E_S");
				sqlGYMS.updateGYMS(gym2);
				
				System.out.println("Consulta E_S");
				sqlGYMS.queryGYMS();
				
		*/	
			
		
	}

}
