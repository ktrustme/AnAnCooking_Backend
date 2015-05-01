package com.anan.anancooking.server.database;


import java.sql.*;
import java.util.HashSet;

import com.anan.anancooking.model.Step;

/**
 * Created by zhouyangdi on 4/29/15.
 */
public class RecipeIDGenerater {

    public static String createID() {
        String id;
        id = "" + (Integer.parseInt(currentID()) + 1);
        return id;
    }
    
    
    /* helper functions for manipulating database */
    private static String currentID() {
    	String current = null;
    	Connection conn = null;
    	Statement stmt = null;
    	try{
			Class.forName(DatabaseMacros.JDBC_DRIVER);
			conn = DriverManager.getConnection(DatabaseMacros.DB_URL, 
					DatabaseMacros.USER, DatabaseMacros.PASS);
			stmt = conn.createStatement();

			// use databases;
			String sql = "use " + DatabaseMacros.DATABASE;
			stmt.executeQuery(sql);

			String sql2 = "SELECT MAX(recipe_id) AS current from " + DatabaseMacros.RECIPE_OVERVIEW;
			
			ResultSet rs = stmt.executeQuery(sql2);

			
			if (rs.next())
			{
				current = rs.getString(1);
			}
			if(current==null){
				current = "0000";
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}

		finally{
			try{
				if(stmt != null)
					stmt.close();
			}
			catch(SQLException se2){
			}
			try{
				if(conn != null)
					conn.close();
			}
			catch(SQLException se){
				se.printStackTrace();
			}//end finally try
		}//end try
    	
    	return current;
    }
}
