package data;
import java.sql.*;


import java.sql.Connection;
import model.Client;
import model.E_S;

import java.sql.DriverManager;

import java.sql.ResultSet;

import java.sql.SQLException;

import java.sql.Statement;
import java.util.ArrayList;


public class SQLE_S {

	Connection c = null;

	Statement sentencia = null;
	
	ArrayList<E_S> searchE_S = new ArrayList<E_S>();



	public Connection conectar() {

		try {

			Class.forName("org.sqlite.JDBC");
			
			c = DriverManager.getConnection("jdbc:sqlite:E:\\DAM\\CODE\\git\\Java\\ProjectGYM\\Server\\gym.db");

			System.out.println("Exito en la primera conexion con la base de datos");

		} catch (Exception e) {

			System.out.println("Error al conectar con base de datos");

		}
		return c;

	}



	public void insertaE_S(E_S e_s) throws SQLException {

	
		
		
		String sqlInsert = "INSERT INTO E_S" 
				+ " VALUES ("
				+"\""+e_s.getMovimentsID()+ "\""+","
				+"\""+e_s.getGymID()+ "\""+","
				+"\""+e_s.getUserID()+ "\""+","
				+"\""+e_s.getDate()+ "\""+","
				+"\""+e_s.getE_s()+"\")";
		
		
		

		try {

			conectar();

			sentencia = c.createStatement();
			
			sentencia.executeUpdate(sqlInsert); 
			

			sentencia.close();

			c.close();

			System.out.println("Datos insertados");

		} catch (Exception e) {

			System.out.println(e.getMessage());

		}

	}
	
	
	public void updateE_S(E_S e_s) throws SQLException{
		
		
		
		String sqlUpdate ="UPDATE E_S "
				+ "SET "
				+ " movimentsID ='" + e_s.getMovimentsID()
				+ "', gymID ='" + e_s.getGymID()
				+ "', dniClient ='" + e_s.getUserID()
				+ "', date ='" + e_s.getDate()
				+ "', e_s ='" + e_s.getE_s()
				+ "' WHERE movimentsID ='" + e_s.getUserID()+ "';";
		
		try{
			
			conectar();

			sentencia = c.createStatement();
			sentencia.executeUpdate(sqlUpdate); 



			sentencia.close();

			c.close();

			System.out.println("Datos actualizados");
			  
		}catch (SQLException e) {
			System.out.println(e.getMessage());

		}
	}

	
	public void deleteE_S(E_S e_s) throws SQLException {
					
		//String sqlDelete = "DELETE FROM E_S " + " WHERE cif = "+"\""+e_s.getGymID()+"\""+";";
		String sqlDelete = "DELETE FROM E_S " + " WHERE movimentsID ='" + e_s.getMovimentsID()+ "' AND  gymID ='" + e_s.getGymID() + "' AND dniClient ='" + e_s.getUserID() + "';";
		try {
			conectar();

			sentencia = c.createStatement();
			sentencia.executeUpdate(sqlDelete); 
			
			sentencia.close();

			c.close();

			System.out.println("Datos eliminados");
	
				
	
		} catch (Exception e) {
	
			System.out.println(e.getMessage());
	
		}
	
	}
	

	public ArrayList<E_S> queryE_S(String start ,String end,String dniClient) throws SQLException {

		conectar();

		sentencia = c.createStatement();
		
		System.out.println(start);
		System.out.println(end);
		System.out.println(dniClient.length());
		String consultaSql = "SELECT * from E_S ;";
		
		if (start != "" && end != "" ) {
			consultaSql = "SELECT * from E_S WHERE dniClient = '"+dniClient+"' AND date BETWEEN '"+start+"' AND '"+end+"';";
		}
		
		if (start != "" && end != "" && dniClient.length()==0) {
			consultaSql = "SELECT * from E_S WHERE date BETWEEN '"+start+"' AND '"+end+"';";
		}
		
		
		System.out.println(consultaSql);
		
		try {
		     
			ResultSet rs = sentencia.executeQuery(consultaSql);

			while (rs.next()) {

				
				this.searchE_S.add(new E_S(
						rs.getString("movimentsID"),
						rs.getString("gymID"),
						rs.getString("dniClient"),
						rs.getString("date"),
						rs.getString("e_s")));
				
			}	
			

			rs.close();

			sentencia.close();

			c.close();
			


		} catch (Exception e) {

			System.out.println(e.getMessage());

		}
		
		return searchE_S;




	}
}
