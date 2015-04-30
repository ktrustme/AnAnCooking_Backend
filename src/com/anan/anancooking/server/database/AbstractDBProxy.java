package com.anan.anancooking.server.database;

/**
 * Created by zhouyangdi on 4/29/15.
 */

import com.anan.anancooking.model.RecipeImplementation;
import com.anan.anancooking.model.RecipePreviewImplementation;
import com.anan.anancooking.model.Step;

<<<<<<< HEAD
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.*;
import java.util.*;

import javax.imageio.ImageIO;

public abstract class AbstractDBProxy {
	// JDBC driver name and database URL
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost/";
	private static final String USER = "username";
	private static final String PASS = "password";
	private static final int MAX_IMAGE = 16 * 1024;

	private static final String DATABASE = "test";
=======
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.*;
import java.util.*;

public abstract class AbstractDBProxy {

>>>>>>> 32644bfd86eeba3c3e76c982066bdd221ad34b27
	Connection conn = null;
	Statement stmt = null;

	public void addRecipe(RecipeImplementation recipe) {
		/* get the recipe id */
		String recipeID = RecipeIDGenerater.createID();

		try{
<<<<<<< HEAD
			Class.forName(JDBC_DRIVER);

			conn = DriverManager.getConnection(DB_URL, USER, PASS);
=======
			Class.forName(DatabaseMacros.JDBC_DRIVER);

			conn = DriverManager.getConnection(DatabaseMacros.DB_URL, 
					DatabaseMacros.USER, DatabaseMacros.PASS);
>>>>>>> 32644bfd86eeba3c3e76c982066bdd221ad34b27

			stmt = conn.createStatement();

			// use databases;
<<<<<<< HEAD
			String sql = "use " + DATABASE;
=======
			String sql = "use " + DatabaseMacros.DATABASE;
>>>>>>> 32644bfd86eeba3c3e76c982066bdd221ad34b27
			stmt.executeQuery(sql);


			/* add to recipe_overview table */

<<<<<<< HEAD
			byte[] bytes = new byte[MAX_IMAGE];
			String query = ("INSERT INTO recipe_overview VALUES(?,?,?,?,?)");
=======
			byte[] bytes = new byte[DatabaseMacros.MAX_IMAGE];
			String query = ("INSERT INTO " + DatabaseMacros.RECIPE_OVERVIEW + " VALUES(?,?,?,?,?)");
>>>>>>> 32644bfd86eeba3c3e76c982066bdd221ad34b27
			PreparedStatement pstmt = conn.prepareStatement(query);

			pstmt.setString(1, recipeID);
			pstmt.setString(2, recipe.getDescription());
			pstmt.setInt(3, recipe.getTime());
			pstmt.setString(4, recipe.getCook());
			// Method used to insert a stream of bytes
			pstmt.setBytes(5, bytes);
			pstmt.executeUpdate();



			/* add to step table */
			for (int i = 0; i < recipe.getSteps().size(); i ++) {
				Step step = recipe.getSteps().get(i);
<<<<<<< HEAD
				String query2 = ("INSERT INTO step VALUES(?,?,?,?)");
=======
				String query2 = ("INSERT INTO " + DatabaseMacros.STEP + " VALUES(?,?,?,?)");
>>>>>>> 32644bfd86eeba3c3e76c982066bdd221ad34b27
				PreparedStatement pstmt2 = conn.prepareStatement(query2);
				pstmt2.setString(1, recipeID);
				pstmt2.setInt(2, i);
				pstmt2.setString(3, step.getDes());
				pstmt2.setBytes(4, step.getBytes());
				pstmt2.executeUpdate(query2);
			}

			/* add to search table */
			for (int i = 0; i < recipe.getIngredients().size(); i ++) {
<<<<<<< HEAD
				//				String query3 = ("INSERT INTO search VALUE(?,?)");
				//				PreparedStatement pstmt3 = conn.prepareStatement(query3);
				//				pstmt3.setString(1, recipeID);
				//				pstmt3.setString(2, recipe.getIngredients().get(i));
				//				pstmt3.executeUpdate(query3);

				String query3 = ("INSERT INTO search VALUE(\"" + recipeID + "\", \"" + recipe.getIngredients().get(i) + "\")");
=======
			
				String query3 = ("INSERT INTO " + DatabaseMacros.SEARCH + "VALUE(\"" + recipeID + "\", \"" + recipe.getIngredients().get(i) + "\")");
>>>>>>> 32644bfd86eeba3c3e76c982066bdd221ad34b27
				stmt.executeUpdate(query3);
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

	}

	public List<RecipePreviewImplementation> searchForPreview(List<String> ingredients) {
		List<RecipePreviewImplementation> previews = new ArrayList<RecipePreviewImplementation>();
		StringBuilder sb = new StringBuilder("'");
		for (int i = 0; i < ingredients.size(); i ++) {
			sb.append(ingredients.get(i));
			if (i == ingredients.size() - 1)
				sb.append("'");
			else
				sb.append("' OR '");
		}
		try{
<<<<<<< HEAD
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();

			// use databases;
			String sql = "use " + DATABASE;
			stmt.executeQuery(sql);

			String sql2 = "SELECT * FROM recipe_overview WHERE recipe_id IN (SELECT recipe_id FROM search WHERE ingredient = "
=======
			Class.forName(DatabaseMacros.JDBC_DRIVER);
			conn = DriverManager.getConnection(DatabaseMacros.DB_URL, 
					DatabaseMacros.USER, DatabaseMacros.PASS);
			stmt = conn.createStatement();

			// use databases;
			String sql = "use " + DatabaseMacros.DATABASE;
			stmt.executeQuery(sql);

			String sql2 = "SELECT * FROM " + DatabaseMacros.RECIPE_OVERVIEW + " WHERE recipe_id IN (SELECT recipe_id FROM search WHERE ingredient = "
>>>>>>> 32644bfd86eeba3c3e76c982066bdd221ad34b27
					+ sb.toString() + ")";
			ResultSet rs = stmt.executeQuery(sql2);
			System.out.println("fetching image");
			/* fetch image */
			byte[] fileBytes;

			while (rs.next())
			{
<<<<<<< HEAD
				Class.forName(JDBC_DRIVER);
				Connection conn2 = DriverManager.getConnection(DB_URL, USER, PASS);;
=======
				Class.forName(DatabaseMacros.JDBC_DRIVER);
				Connection conn2 = DriverManager.getConnection(DatabaseMacros.DB_URL, 
						DatabaseMacros.USER, DatabaseMacros.PASS);;
>>>>>>> 32644bfd86eeba3c3e76c982066bdd221ad34b27
				Statement stmt2 = conn2.createStatement();

				stmt2.executeQuery(sql);

				String recipeID = rs.getString(1);
				String recipeName = rs.getString(2);
				int time = rs.getInt(3);
				String cook = rs.getString(4);
				fileBytes = rs.getBytes(5);



				/* fetch all the ingredients */
<<<<<<< HEAD
				String sql3 = "SELECT ingredient FROM search WHERE recipe_id = '" + recipeID + "'";
=======
				String sql3 = "SELECT ingredient FROM " + DatabaseMacros.SEARCH + " WHERE recipe_id = '" + recipeID + "'";
>>>>>>> 32644bfd86eeba3c3e76c982066bdd221ad34b27
				ResultSet rs2 = stmt2.executeQuery(sql3);
				StringBuilder in = new StringBuilder();

				while (rs2.next()) {
					in.append(rs2.getString(1));
					System.out.println(rs2.getString(1));
					in.append(", ");
				}
				System.out.println("ingredients = " + in.toString());

				RecipePreviewImplementation pre = new RecipePreviewImplementation(recipeID,
						recipeName, time, cook, in.toString(), fileBytes);

				previews.add(pre);
<<<<<<< HEAD
				//				OutputStream targetFile =
				//						new FileOutputStream(
				//								"/Users/zhouyangdi/Desktop/oppalovesme.jpg");
				//				targetFile.write(fileBytes);
				//				targetFile.close();
=======
>>>>>>> 32644bfd86eeba3c3e76c982066bdd221ad34b27
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
		return previews;

	}

	public List<Step> getSteps(String recipeID) {
		List<Step> steps = new ArrayList<Step>();

		try{
<<<<<<< HEAD
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();

			// use databases;
			String sql = "use " + DATABASE;
			stmt.executeQuery(sql);

			String sql2 = "SELECT * FROM step WHERE recipe_id = '" + recipeID + "'";
=======
			Class.forName(DatabaseMacros.JDBC_DRIVER);
			conn = DriverManager.getConnection(DatabaseMacros.DB_URL, 
					DatabaseMacros.USER, DatabaseMacros.PASS);
			stmt = conn.createStatement();

			// use databases;
			String sql = "use " + DatabaseMacros.DATABASE;
			stmt.executeQuery(sql);

			String sql2 = "SELECT * FROM " + DatabaseMacros.STEP + " WHERE recipe_id = '" + recipeID + "'";
>>>>>>> 32644bfd86eeba3c3e76c982066bdd221ad34b27
			ResultSet rs = stmt.executeQuery(sql2);
			byte[] fileBytes;

			while (rs.next())
			{
				//				fileBytes = rs.getBytes(1);
				Step step = new Step(rs.getString(1), rs.getInt(2), rs.getString(3), rs.getBytes(4));
				steps.add(step);
<<<<<<< HEAD
				//				OutputStream targetFile =
				//						new FileOutputStream(
				//								"/Users/zhouyangdi/Desktop/oppalovesme.jpg");
				//				targetFile.write(fileBytes);
				//				targetFile.close();
=======
>>>>>>> 32644bfd86eeba3c3e76c982066bdd221ad34b27
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
		return steps;
	}

<<<<<<< HEAD
=======
    public List<RecipePreviewImplementation> recommendPreview() {
    	List<RecipePreviewImplementation> recipes = new ArrayList<RecipePreviewImplementation>();
    	try{
			Class.forName(DatabaseMacros.JDBC_DRIVER);
			conn = DriverManager.getConnection(DatabaseMacros.DB_URL, 
					DatabaseMacros.USER, DatabaseMacros.PASS);
			stmt = conn.createStatement();

			// use databases;
			String sql = "use " + DatabaseMacros.DATABASE;
			stmt.executeQuery(sql);

			String sql2 = "SELECT * FROM " + DatabaseMacros.RECIPE_OVERVIEW + 
					" LIMIT " + DatabaseMacros.RECOMMEND_NO;
			ResultSet rs = stmt.executeQuery(sql2);
			/* fetch image */
			byte[] fileBytes;

			while (rs.next())
			{
				Class.forName(DatabaseMacros.JDBC_DRIVER);
				Connection conn2 = DriverManager.getConnection(DatabaseMacros.DB_URL, 
						DatabaseMacros.USER, DatabaseMacros.PASS);;
				Statement stmt2 = conn2.createStatement();

				stmt2.executeQuery(sql);

				String recipeID = rs.getString(1);
				String recipeName = rs.getString(2);
				int time = rs.getInt(3);
				String cook = rs.getString(4);
				fileBytes = rs.getBytes(5);
>>>>>>> 32644bfd86eeba3c3e76c982066bdd221ad34b27



				/* fetch all the ingredients */
				String sql3 = "SELECT ingredient FROM " + DatabaseMacros.SEARCH + " WHERE recipe_id = '" + recipeID + "'";
				ResultSet rs2 = stmt2.executeQuery(sql3);
				StringBuilder in = new StringBuilder();

				while (rs2.next()) {
					in.append(rs2.getString(1));
					System.out.println(rs2.getString(1));
					in.append(", ");
				}
				System.out.println("ingredients = " + in.toString());

				RecipePreviewImplementation pre = new RecipePreviewImplementation(recipeID,
						recipeName, time, cook, in.toString(), fileBytes);

				recipes.add(pre);
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
    	return recipes;
    }
    
    
    

    
}

