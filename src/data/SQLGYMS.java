package data;
import java.sql.*;


import java.sql.Connection;
import model.Client;
import model.GYMS;

import java.sql.DriverManager;

import java.sql.ResultSet;

import java.sql.SQLException;

import java.sql.Statement;
import java.util.ArrayList;


public class SQLGYMS {

	Connection c = null;

	Statement sentencia = null;

	String nombreTabla;

	String name, surname;

	
	ArrayList<GYMS> gyms = new ArrayList<GYMS>();



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



	public void insertaGYMS(GYMS gyms) throws SQLException {

	
		
		String sqlInsert = "INSERT INTO GYMS" 
				+ " VALUES ("
				+"\""+gyms.getGymID()+ "\""+","
				+"\""+gyms.getName()+ "\""+","
				+"\""+gyms.getCp()+ "\""+","
				+"\""+gyms.getProvince()+"\")";
		
		
		

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
	
	public void updateGYMS(GYMS gyms) throws SQLException{
		
		
		String sqlUpdate ="UPDATE GYMS "
				+ "SET "
				+ "gymID ='" + gyms.getGymID()
				+ "', name ='" + gyms.getName()
				+ "', cp ='" + gyms.getCp()
				+ "', province ='" + gyms.getProvince()
				+ "' WHERE gymID ='" + gyms.getGymID() + "';";
		
		
		try{
			
			conectar();

			sentencia = c.createStatement();
			sentencia.executeUpdate(sqlUpdate); 



			sentencia.close();

			c.close();

			System.out.println("Datos actualizados");
			  
		}
		catch (SQLException e)
		{
			System.out.println(e.getMessage());

		}
	}

	
	public void deleteGYMS(GYMS gyms) throws SQLException {
					
		String sqlDelete = "DELETE FROM GYMS " + " WHERE gymID = "+"\""+gyms.getGymID()+"\""+";";
		
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




	public ArrayList<GYMS> queryGYMS() throws SQLException {

		conectar();

		sentencia = c.createStatement();

		String consultaSql = "SELECT * FROM GYMS ;";

		try {
			ResultSet rs = sentencia.executeQuery(consultaSql);
			while (rs.next()) {

				
				this.gyms.add(new GYMS(
						rs.getString("gymID"),
						rs.getString("name"),
						rs.getString("cp"),
						rs.getString("province")));
				

			}	
			

			rs.close();

			sentencia.close();

			c.close();
			


		} catch (Exception e) {

			System.out.println(e.getMessage());

		}
		
		return gyms;




	}

}
