package com.anan.anancooking.server.database;

import java.awt.Component;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class DatabaseInitialize {
	private static String jdbcDriver = "com.mysql.jdbc.Driver";
	//private static String dbName = "TIGER";


	public static void main(String[] args) throws Exception {
		//Before all the code, start Mysql Server
		//Command: sudo /usr/local/mysql/support-files/mysql.server start
		Class.forName(jdbcDriver);
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/?user=root&password=");
		Statement s = conn.createStatement();
		s.execute("drop database if exists "+DatabaseMacros.DATABASE);
		int Result = s.executeUpdate("CREATE DATABASE "+DatabaseMacros.DATABASE);

		if(Result!=1){
			//if unable to create the database
			JOptionPane.showMessageDialog(
					null, "Creating database failed!");
			System.exit(1);
		}
		s.execute("use "+DatabaseMacros.DATABASE);
		
		s.execute("create table if not exists "+DatabaseMacros.STEP +" (recipe_id text, step_no int(11), step_text text, step_image blob)");
		
		s.execute("create table if not exists "+DatabaseMacros.SEARCH + " (recipe_id text, ingredient text)");
		
		s.execute("create table if not exists "+DatabaseMacros.RECIPE_OVERVIEW +" (recipe_id text, recipe_name text, time int(11), cook text, image blob)");
		//Then create the tables
	}
}
