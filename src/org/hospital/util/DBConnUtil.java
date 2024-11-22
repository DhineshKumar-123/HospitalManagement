package org.hospital.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//import org.hexaware.util.DBPropertyUtil;

public class DBConnUtil 
{
	public static Connection establishconnection(String url)
	{
			String urlink = null;
			Connection con = null;
			try {
				urlink = DBPropertyUtil.createstring("db.properties");
				con = DriverManager.getConnection(urlink);	
				
			}catch(SQLException ex)
			{
				ex.printStackTrace();
				System.out.println("Connection Not Happened !!!!!!");
			}
			return con;
	}
//	public static void main(String[] args)
//	{
//		DBConnUtil.establishconnection("db.properties");
//	}
}
