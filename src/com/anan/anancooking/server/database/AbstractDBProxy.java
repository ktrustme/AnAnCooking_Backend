package com.anan.anancooking.server.database;

/**
 * Created by zhouyangdi on 4/29/15.
 */

import com.anan.anancooking.model.RecipeImplementation;
import com.anan.anancooking.model.RecipePreviewImplementation;
import com.anan.anancooking.model.Step;



import com.anan.anancooking.server.exception.ExceptionEnum;
import com.anan.anancooking.server.exception.ServerException;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.*;
import java.util.*;

public abstract class AbstractDBProxy {


	Connection conn = null;
	Statement stmt = null;

	public void addRecipe(RecipeImplementation recipe) {
		/* get the recipe id */
		String recipeID = RecipeIDGenerater.createID();

		try{

			Class.forName(DatabaseMacros.JDBC_DRIVER);

			conn = DriverManager.getConnection(DatabaseMacros.DB_URL, 
					DatabaseMacros.USER, DatabaseMacros.PASS);


			stmt = conn.createStatement();

			// use databases;

			String sql = "use " + DatabaseMacros.DATABASE;

			stmt.executeQuery(sql);


			/* add to recipe_overview table */


			byte[] bytes = recipe.getPreviewByteCode();
			if(bytes.length > DatabaseMacros.MAX_IMAGE)
				throw new ServerException(ExceptionEnum.IMAGE_OVERSIZE);
			String query = ("INSERT INTO " + DatabaseMacros.RECIPE_OVERVIEW + " VALUES(?,?,?,?,?)");

			PreparedStatement pstmt = conn.prepareStatement(query);

			pstmt.setString(1, recipeID);
			pstmt.setString(2, recipe.getName());
			pstmt.setInt(3, recipe.getTime());
			pstmt.setString(4, recipe.getDescription());
			//Method used to insert a stream of bytes
			pstmt.setBytes(5, bytes);
			pstmt.executeUpdate();



			/* add to step table */
			for (int i = 0; i < recipe.getSteps().size(); i ++) {
				Step step = recipe.getSteps().get(i);

				String query2 = ("INSERT INTO " + DatabaseMacros.STEP + " VALUES(?,?,?,?)");
				PreparedStatement pstmt2 = conn.prepareStatement(query2);
				pstmt2.setString(1, recipeID);
				pstmt2.setInt(2, i);
				pstmt2.setString(3, step.getDes());
				pstmt2.setBytes(4, step.getBytes());

				pstmt2.executeUpdate();
			}

			/* add to search table */
			if(recipe.getIngredients() == null)
				new ServerException(ExceptionEnum.NO_INGREDIENTS);


			for (int i = 0; i < recipe.getIngredients().size(); i ++) {


				String query3 = ("INSERT INTO " + DatabaseMacros.SEARCH + " VALUE(\"" + recipeID + "\", \"" + recipe.getIngredients().get(i) + "\")");

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

			Class.forName(DatabaseMacros.JDBC_DRIVER);
			conn = DriverManager.getConnection(DatabaseMacros.DB_URL, 
					DatabaseMacros.USER, DatabaseMacros.PASS);
			stmt = conn.createStatement();

			// use databases;
			String sql = "use " + DatabaseMacros.DATABASE;
			stmt.executeQuery(sql);

			String sql2 = "SELECT * FROM " + DatabaseMacros.RECIPE_OVERVIEW + " WHERE recipe_id IN (SELECT recipe_id FROM search WHERE ingredient = "

					+ sb.toString() + ")";
			ResultSet rs = stmt.executeQuery(sql2);
			/* fetch image */
			byte[] fileBytes;
			if (!rs.next())
				throw new ServerException(ExceptionEnum.NO_RECORD_IN_TABLE);
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

						previews.add(pre);
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
			Class.forName(DatabaseMacros.JDBC_DRIVER);
			conn = DriverManager.getConnection(DatabaseMacros.DB_URL, 
					DatabaseMacros.USER, DatabaseMacros.PASS);
			stmt = conn.createStatement();

			// use databases;
			String sql = "use " + DatabaseMacros.DATABASE;
			stmt.executeQuery(sql);

			String sql2 = "SELECT * FROM " + DatabaseMacros.STEP + " WHERE recipe_id = '" + recipeID + "'";

			ResultSet rs = stmt.executeQuery(sql2);
			byte[] fileBytes;

			while (rs.next())
			{
				//				fileBytes = rs.getBytes(1);
				Step step = new Step(rs.getString(1), rs.getInt(2), rs.getString(3), rs.getBytes(4));
				steps.add(step);

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




						/* fetch all the ingredients */
						String sql3 = "SELECT ingredient FROM " + DatabaseMacros.SEARCH + " WHERE recipe_id = '" + recipeID + "'";
						ResultSet rs2 = stmt2.executeQuery(sql3);
						StringBuilder in = new StringBuilder();

						while (rs2.next()) {
							in.append(rs2.getString(1));
							System.out.println(rs2.getString(1));
							in.append("; ");
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
		
		Collections.shuffle(recipes);
		
		
		return recipes;
	}


	public RecipeImplementation fetchRecipe(String recipeID) {

		RecipeImplementation recipe = new RecipeImplementation();

		try{
			Class.forName(DatabaseMacros.JDBC_DRIVER);
			conn = DriverManager.getConnection(DatabaseMacros.DB_URL, 
					DatabaseMacros.USER, DatabaseMacros.PASS);
			stmt = conn.createStatement();

			// use databases;
			String sql = "use " + DatabaseMacros.DATABASE;
			stmt.executeQuery(sql);

			String sql2 = "SELECT * FROM " + DatabaseMacros.RECIPE_OVERVIEW + " WHERE recipe_id = '" + recipeID + "'";

			ResultSet rs = stmt.executeQuery(sql2);

			if (rs.next())
			{
				Class.forName(DatabaseMacros.JDBC_DRIVER);
				Connection conn2 = DriverManager.getConnection(DatabaseMacros.DB_URL, 
						DatabaseMacros.USER, DatabaseMacros.PASS);;
						Statement stmt2 = conn2.createStatement();

						stmt2.executeQuery(sql);
						recipe.setRecipeID(recipeID);
						recipe.setName(rs.getString(2));
						recipe.setTime(rs.getInt(3));
						recipe.setDescription(rs.getString(4));
						recipe.setPreviewByteCode(rs.getBytes(5));




						/* fetch all the ingredients */
						String sql3 = "SELECT ingredient FROM " + DatabaseMacros.SEARCH + " WHERE recipe_id = '" + recipeID + "'";
						ResultSet rs2 = stmt2.executeQuery(sql3);
						List<String> in = new ArrayList<String>();

						while (rs2.next()) {
							in.add(rs2.getString(1));
						}

						recipe.setIngredients(in);

			}

			recipe.setSteps(this.getSteps(recipeID));


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
		return recipe;
	}


	public void setRating(String recipeID, int rate, int number) {

		try{
			Class.forName(DatabaseMacros.JDBC_DRIVER);
			conn = DriverManager.getConnection(DatabaseMacros.DB_URL, 
					DatabaseMacros.USER, DatabaseMacros.PASS);
			stmt = conn.createStatement();

			// use databases;
			String sql = "use " + DatabaseMacros.DATABASE;
			stmt.executeQuery(sql);

			StringBuilder sql2 = new StringBuilder("INSERT INTO ");
			sql2.append(DatabaseMacros.RATING);
			sql2.append(" VALUES ('");
			sql2.append(recipeID);
			sql2.append("', ");
			sql2.append(rate);
			sql2.append(", ");
			sql2.append(number);
			sql2.append(")");


			stmt.executeQuery(sql2.toString());


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


	public int fetchRating(String recipeID) {

		try{
			Class.forName(DatabaseMacros.JDBC_DRIVER);
			conn = DriverManager.getConnection(DatabaseMacros.DB_URL, 
					DatabaseMacros.USER, DatabaseMacros.PASS);
			stmt = conn.createStatement();

			// use databases;
			String sql = "use " + DatabaseMacros.DATABASE;
			stmt.executeQuery(sql);

			String sql2 = "SELECT rating FROM " + DatabaseMacros.RATING + " WHERE recipe_id = '" + recipeID + "'";

			ResultSet rs = stmt.executeQuery(sql2);

			if (rs.next())
			{
				return rs.getInt(1);
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
		return 0;
	}


	public int fetchNumberOfPeople(String recipeID) {
		try{
			Class.forName(DatabaseMacros.JDBC_DRIVER);
			conn = DriverManager.getConnection(DatabaseMacros.DB_URL, 
					DatabaseMacros.USER, DatabaseMacros.PASS);
			stmt = conn.createStatement();

			// use databases;
			String sql = "use " + DatabaseMacros.DATABASE;
			stmt.executeQuery(sql);

			String sql2 = "SELECT number FROM " + DatabaseMacros.RATING + " WHERE recipe_id = '" + recipeID + "'";

			ResultSet rs = stmt.executeQuery(sql2);

			if (rs.next())
			{
				return rs.getInt(1);
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
		return 0;
	}



}

