package edu.gatech.cs6310.projectOne;

import java.sql.Connection;
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
			ResultSet rset = con.getMetaData().getTables(null, null, "school", null);
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
			con = DriverManager.getConnection("jdbc:h2:"+DatabaseName, "ARMS", "" );
	        Statement stmt = con.createStatement();
	        //stmt.executeUpdate( "DROP TABLE table1" );
	        stmt.executeUpdate( "CREATE TABLE school (UNIQUE KEY schoolid varchar(50), students integer, maxstudents integer, semesters varchar(50), maxsemesters integer )" );
	        stmt.executeUpdate( "CREATE TABLE semester (UNIQUE KEY semesterid varchar(50), semestername varchar(50), classes varchar(50), maxclasses integer)" );
	        stmt.executeUpdate( "CREATE TABLE class (UNIQUE KEY classid varchar(50), classname varchar(50), students varchar(50), maxstudents integer, semesters varchar(50), maxsemesters integer )" );
	        stmt.executeUpdate( "CREATE TABLE student (UNIQUE KEY studentid varchar(50), studentname varchar(50), classesenrolled varchar(50), maxclasses integer, semesters varchar(50), maxsemesters integer )" );
			stmt.close();
			con.close();
		}
		catch( Exception e )
		{
			System.out.println( e.getMessage() );
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
