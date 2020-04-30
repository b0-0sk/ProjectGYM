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


public class SQLClients {

	Connection c = null;
	Statement sentencia = null;
	ArrayList<Client> clients = new ArrayList<Client>();

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



	public void insertaClients(Client client) throws SQLException {

		String sqlInsert = "INSERT INTO Client" 
				+ " VALUES ("
				+"\""+client.getDniClient()+ "\""+","
				+"\""+client.getUser()+ "\""+","
				+"\""+client.getSurname()+ "\""+","
				+"\""+client.getPassword()+ "\""+","
				+"\""+client.getTelf()+ "\""+","
				+"\""+client.getCp()+ "\""+","
				+"\""+client.getProvince()+ "\""+","
				+"\""+client.getEmail()+ "\""+","
				+"\""+client.getIban()+ "\""+","
				+"\""+client.getGoodPayer()+"\")";
		
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
	
	public void updateClient(Client client) throws SQLException{
				
//		String sqlUpdate ="UPDATE Client "
//				+ "SET "
//				+ "dniClient ='" + client.getDniClient()
//				+ "', user ='" + client.getUser()
//				+ "', surname ='" + client.getSurname()
//				+ "', password ='" + client.getPassword()
//				+ "', telf ='" + client.getTelf()
//				+ "', cp ='" + client.getCp()
//				+ "', province='" + client.getProvince()
//				+ "', email ='" + client.getEmail()
//				+ "', iban ='" + client.getIban()
//				+ "', goodPayer ='" + client.getGoodPayer()
//				+ "' WHERE dniClient ='" + client.getDniClient() + "';";
		
		String sqlUpdate ="UPDATE Client "
				+ "SET "
				+ "dniClient ='" + client.getDniClient()
				+ "', user ='" + client.getUser()
				+ "' WHERE dniClient ='" + client.getDniClient() + "';";	
		
		try{
			
			conectar();

			sentencia = c.createStatement();
			sentencia.executeUpdate(sqlUpdate); 

			sentencia.close();
			c.close();

			System.out.println("Datos actualizados");
			
		}catch (SQLException e){
			
			System.out.println(e.getMessage());

		}
	}

	
	public void deleteClients(Client client) throws SQLException {
					
		String sqlDelete = "DELETE FROM Client " + " WHERE dniClient = "+"\""+client.getDniClient()+"\""+";";
		
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




	public ArrayList<Client> queryClients(String dniClient) throws SQLException {

		conectar();

		sentencia = c.createStatement();

		String consultaSql = "SELECT * FROM Client WHERE dniClient = '"+dniClient+"';";
		
		if (dniClient.length()==0) {
			consultaSql = "SELECT * FROM Client ;";
			System.out.println();
		}
		System.out.println(consultaSql);
		try {
			ResultSet rs = sentencia.executeQuery(consultaSql);
			while (rs.next()) {

				
				this.clients.add(new Client(
						rs.getString("dniClient"),
						rs.getString("user"),
						rs.getString("surname"),
						rs.getString("password"),
						rs.getString("telf"),
						rs.getString("cp"),
						rs.getString("province"),
						rs.getString("email"),
						rs.getString("iban"),
						rs.getString("goodPayer")));
				

			}	
			

			rs.close();

			sentencia.close();

			c.close();
			


		} catch (Exception e) {

			System.out.println(e.getMessage());

		}
		
		return clients;




	}

}
