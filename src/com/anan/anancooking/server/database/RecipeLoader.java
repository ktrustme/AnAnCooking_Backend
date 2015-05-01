package com.anan.anancooking.server.database;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.imageio.ImageIO;

import com.anan.anancooking.model.RecipeImplementation;
import com.anan.anancooking.model.RecipeInterface;
import com.anan.anancooking.model.RecipePreviewImplementation;
import com.anan.anancooking.model.RecipePreviewInterface;
import com.anan.anancooking.model.Step;
import com.anan.anancooking.model.StepImplementation;
import com.anan.anancooking.model.StepInterface;

public class RecipeLoader {
	public static List<RecipeInterface> loadRecipesFromFile(String path){
		List<RecipeInterface> resultRecipes = new ArrayList<RecipeInterface>();
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		for(int i = 0; i < listOfFiles.length; i ++){
			if(listOfFiles[i].isDirectory())
				resultRecipes.add(recipeFileConverter(listOfFiles[i]));
		}
		return resultRecipes;
	}

	public static RecipeInterface recipeFileConverter(File file){
		RecipeInterface recipe = new RecipeImplementation();
		String filename = file.toString();
		recipe.setName(filename.substring(filename.lastIndexOf("/")+1, filename.length()));

		File[] listOfFolders = file.listFiles();
		RecipePreviewInterface recipePreview = null;
		List<Step> steps = new ArrayList<Step>();
		StepInterface step;
		for(int i = 0; i < listOfFolders.length; i ++){
			//System.out.println(listOfFolders[i]);
			if(listOfFolders[i].toString().contains("Preview")){
				recipePreview = recipePreviewConverter(listOfFolders[i]);
				recipe.setIngredients(new ArrayList<String>(Arrays.asList(recipePreview.getIngredients().split(";"))));
				recipe.setTime(recipePreview.getTime());
				recipe.setPreviewByteCode(recipePreview.getPreviewByteCode());
			}
			else if(listOfFolders[i].toString().contains("description.txt")){
				try {
					recipe.setDescription(new Scanner(listOfFolders[i]).useDelimiter("\\Z").next());
				} catch (FileNotFoundException e) { 
					e.printStackTrace();
				}
			}
			else if (listOfFolders[i].isDirectory()){
				steps.add(recipeStepConverter(listOfFolders[i]));
			}
		}
		recipe.setSteps(steps);
		/*
		for(Step step2: steps){
			System.out.println(step2.getDes());
		}
		*/
		return recipe;
	}


	public static RecipePreviewInterface recipePreviewConverter(File file){
		RecipePreviewInterface recipePreview = new RecipePreviewImplementation();
		File[] listOfFolders = file.listFiles();
		byte[] imageBytes = null;
		for(int i = 0; i < listOfFolders.length; i ++){
			if(listOfFolders[i].toString().contains(".jpg")){
				recipePreview.setPreviewByteCode(extractBytesFromImage(listOfFolders[i].toString()));
			}else{
				try {
					Scanner inputScanner = new Scanner(listOfFolders[i]);
					recipePreview.setTime(Integer.valueOf(inputScanner.nextLine()));
					recipePreview.setIngredients(inputScanner.nextLine());
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		}		
		
		return recipePreview;
	}

	
	public static Step recipeStepConverter(File file){
		
		Step step = new Step();
		File[] listOfFolders = file.listFiles();
		byte[] imageBytes = null;
		
		for(int i = 0; i < listOfFolders.length; i ++){
			if(listOfFolders[i].toString().contains(".jpg")){
				step.setBytes(extractBytesFromImage(listOfFolders[i].toString()));
			}else if (listOfFolders[i].toString().contains(".txt")){
				try {
					step.setDescription(new Scanner(listOfFolders[i]).useDelimiter("\\Z").next());
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
		return step;
	}

	public static byte[] extractBytesFromImage (String imageName){
		// open image		
		BufferedImage bufferedImage;
		InputStream input = null;
		try {
			input = new FileInputStream(imageName);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		byte[] imageInByte=null;
		try {
			bufferedImage = ImageIO.read(input);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write( bufferedImage, "jpg", baos );
			baos.flush();
			imageInByte = baos.toByteArray();
			baos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return imageInByte;
	}	


	public static void main(String[] args){
		List<RecipeInterface> recipes= loadRecipesFromFile(System.getProperty("user.dir")+"/resources");
		DBServer db = new DBServer();
		for(RecipeInterface recipe : recipes){
			db.addRecipe((RecipeImplementation)recipe);
		}
	}
}
