package br.com.ticketmaster.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import oracle.jdbc.OracleDriver;

public class ConnectionFactory {
	 
	public Connection getConnection() {
	     try {
	    	 	Connection conn = null;
				
				DriverManager.registerDriver(new OracleDriver());
				conn = DriverManager.getConnection("jdbc:oracle:thin:@oracle.inf.poa.ifrs.edu.br:1521:XE", "0590142", "0590142");
				return conn;
	     } catch (SQLException e) {
	         throw new RuntimeException(e);
	     }
	 }
}
