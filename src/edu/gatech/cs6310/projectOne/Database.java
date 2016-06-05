package edu.gatech.cs6310.projectOne;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
	private Connection con;
	private String DatabaseName = "";
	public Boolean isDbReady = false;
	public Database(String dbName){
		Boolean tableExists = false;
		if(dbName == null || dbName == "")
		{
			dbName = "~/ARMS";
		}
		try {
			con = DriverManager.getConnection("jdbc:h2:"+dbName, "ARMS", "" );
			DatabaseMetaData dbm = con.getMetaData();
			ResultSet rset = dbm.getTables(null, null, "SCHOOL", null);
			if (rset.next())
			{
			  tableExists = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			isDbReady = false;
		}
		if(!tableExists)
		{
			isDbReady = false; 
			if(con != null)
			{
				DatabaseName = dbName;
				SetupDatabase();
			}
		}
		else
		{
			DatabaseName = dbName;
			isDbReady = true;
		}
        
	}
	public Boolean Connect()
	{
		try {
			con = DriverManager.getConnection("jdbc:h2:"+DatabaseName, "ARMS", "" );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public Boolean Disconnect()
	{
		try {
			if(con != null)
			{
				con.close();
			}
			else
			{
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public Boolean SetupDatabase()
	{
		try
		{
	        Statement stmt = con.createStatement();
	        //stmt.executeUpdate( "DROP TABLE table1" );
	        stmt.executeUpdate( "CREATE TABLE school (id INT PRIMARY KEY AUTO_INCREMENT NOT NULL, schoolid varchar(50), students integer, maxstudents integer, semesters array, maxsemesters integer )" );
	        stmt.executeUpdate( "CREATE TABLE semester (id INT PRIMARY KEY AUTO_INCREMENT NOT NULL, semesterid varchar(50), semestername varchar(50), classes array, maxclasses integer)" );
	        stmt.executeUpdate( "CREATE TABLE class (id INT PRIMARY KEY AUTO_INCREMENT NOT NULL, classid varchar(50), classname varchar(50), students array, maxstudents integer, semesters array, maxsemesters integer )" );
	        stmt.executeUpdate( "CREATE TABLE student (id INT PRIMARY KEY AUTO_INCREMENT NOT NULL, studentid varchar(50), studentname varchar(50),classescomplete array, classesenrolled array, maxclasses integer, semesters array, maxsemesters integer )" );
			stmt.close();
			con.close();
		}
		catch( Exception e )
		{
			System.out.println( e.getMessage() );
			return false;
		}
		return true;
	}
	public Boolean QueryDatabase(String table, String query)
	{
		if(con != null)
		{
			Statement stmt;
			try {
				stmt = con.createStatement();
		        ResultSet rs = stmt.executeQuery("SELECT "+query+" FROM "+table);
				while( rs.next() )
				{
				    String name = rs.getString("user");
				    System.out.println( name );
				}
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		}
		return true;
	}
	public Boolean InsertIntoDatabase(String table, String query)
	{
		if(con != null)
		{
			Statement stmt;
			try {
				stmt = con.createStatement();
		        stmt.executeUpdate("INSERT INTO "+table+" VALUES ("+query+")");
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		}
		return true;
	}
	public Boolean DeleteFromDatabase(String table, String query)
	{
		if(con != null)
		{
			Statement stmt;
			try {
				stmt = con.createStatement();
		        stmt.executeUpdate("DELETE FROM"+table+" WHERE "+query);
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		}
		return true;
	}
	
}
