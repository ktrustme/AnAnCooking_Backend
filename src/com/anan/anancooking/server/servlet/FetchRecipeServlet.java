/*------------------------------
 This file: showOptionServlet.java
 Programmer: Xin Kuo
 Andrew ID: xkuo
 Course/Section: 18641
 Assignment: proj1_unit5
 Description:	
 Last Modified: 02/28/2015
 Known Bugs: Currently none
 Compiler: Java SE 7, javac
 ------------------------------*/

package com.anan.anancooking.server.servlet;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.anan.anancooking.model.RecipeImplementation;
import com.google.gson.Gson;



/**
 * @author kuoxin
 * This servlet is responsible for fetch all configuration info from the server
 * and present the data to the corresponding JSP.
 *
 */
public class FetchRecipeServlet extends HttpServlet {
	//String target = "showOption.jsp";

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{

		System.out.println("FetchRecipeRequest from app received");

		String id = request.getParameter("id");
		System.out.println("The recipe id is: "+id);

		PrintWriter out = response.getWriter();
		String title = "Auto Refresh Header Setting";

		//out.println("OK...Now I think it works, and the id is: "+id);
		//out.println("{ key1: 'value1', key2: 'value2' }");


		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		Gson gson = new Gson();
		//create Json Object
		List<String> ingredients = new ArrayList<String>();
		ingredients.add("Cabbage");ingredients.add("Pork");
		String imageName="/image.jpg";
		/*
		System.out.println(gson.toJson((new RecipeImplementation()).setIngredients(ingredients)
				.setTime(5)
				.setDescription("A strange dish").setPreviewByteCode(extractBytes(imageName))));
		*/
		
		out.print(gson.toJson((new RecipeImplementation()).setIngredients(ingredients)
				.setTime(5)
				.setDescription("A strange dish").setPreviewByteCode(extractBytes(imageName))));
	}


	public byte[] extractBytes (String imageName){
		// open image		
		BufferedImage bufferedImage;
		InputStream input = getServletContext().getResourceAsStream(imageName);

		byte[] imageInByte=null;
		try {
			bufferedImage = ImageIO.read(input
					);
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
}
