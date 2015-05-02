package com.anan.anancooking.server.database;

import java.io.*;
import java.nio.file.Files;
import java.sql.*;
import java.util.*;

import com.anan.anancooking.model.RecipeImplementation;
import com.anan.anancooking.model.RecipePreviewImplementation;
import com.anan.anancooking.model.Step;

public class TestImage {


	// JDBC driver name and database URL
	//private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	//private static final String DB_URL = "jdbc:mysql://localhost/";
	//private static final String USER = "username";
	//private static final String PASS = "password";
	private static final int MAX_IMAGE = 4096;


	public static void main(String[] args){
		   String workingDir = System.getProperty("user.dir");
		   System.out.println("Current working directory : " + workingDir);
		   
		RecipeImplementation recipe = new RecipeImplementation();
		List<String> in = new ArrayList<String> ();
		in.add("111");
		in.add("222");
		in.add("333");
		
		recipe.setIngredients(in);

		recipe.setTime(40);

		recipe.setDescription("test");

		Step step1 = new Step();
		step1.setDescription("step1");

		byte[] image = new byte[MAX_IMAGE];
		File file = new File("/Users/kuoxin/Desktop/image.jpg");
		try {
//			FileInputStream fis = new FileInputStream(file);
//			fis.read(image);
			image = Files.readAllBytes(file.toPath());
//			System.out.println("image = " + image.toString());
			step1.setBytes(image);

		} catch (Exception e1) {
			e1.printStackTrace();
		}

		Step step2 = new Step();
		step2.setDescription("step2");

		byte[] image2 = new byte[MAX_IMAGE];
		File file2 = new File("/Users/kuoxin/Desktop/image2.jpg");
		try {
			FileInputStream fis = new FileInputStream(file2);
			fis.read(image2);
			step2.setBytes(image2);

		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		List<Step> steps = new ArrayList<Step>();
		steps.add(step1);
		steps.add(step2);
		
		recipe.setSteps(steps);

		recipe.setCook("ccccccook");
		recipe.setPreviewByteCode(image2);

		

		DBServer db = new DBServer();
		
		
		db.addRecipe(recipe);
		
		System.out.println("haha"+recipe.getTime());
		List<String> ingredients = new ArrayList<String>();
		ingredients.add("111");
		List<RecipePreviewImplementation> pre = db.searchForPreview(ingredients);
		
		
		for (RecipePreviewImplementation preview: pre) {
			System.out.println("recipe id : " + preview.getRecipeID() + " ingredient : " + preview.getIngredients());
			List<Step> ss = db.getSteps(preview.getRecipeID());
			for (Step step : ss) {
				System.out.println("step " + step.getStepID() + " des : " + step.getDes());
				byte[] fileBytes = step.getBytes();
				OutputStream targetFile;
				try {
					targetFile = new FileOutputStream(
							"/Users/zhouyangdi/Desktop/step" + step.getStepID() + ".jpg");
					targetFile.write(fileBytes);
					targetFile.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}

