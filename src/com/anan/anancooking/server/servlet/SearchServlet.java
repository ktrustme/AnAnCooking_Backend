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
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.anan.anancooking.model.RecipeImplementation;
import com.anan.anancooking.model.RecipeInterface;
import com.anan.anancooking.model.RecipePreviewImplementation;
import com.anan.anancooking.model.RecipePreviewInterface;
import com.anan.anancooking.server.database.DBServer;
import com.google.gson.Gson;



/**
 * @author kuoxin
 * This servlet is responsible for fetch all configuration info from the server
 * and present the data to the corresponding JSP.
 *
 */
public class SearchServlet extends HttpServlet {
	//String target = "showOption.jsp";

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String ingredients = request.getParameter("ingredients");
		if(ingredients!=null){
			System.out.println("SearchRequest from app received");

			PrintWriter out = response.getWriter();
			Gson gson = new Gson();
			DBServer dbServer = new DBServer();
			List<RecipePreviewImplementation>  searchRecultList= dbServer.searchForPreview(Arrays.asList(ingredients.trim().split("\\W+")));

			if(searchRecultList!=null){
				System.out.println("Now list sent back");
				out.print(gson.toJson(searchRecultList));
			}
			else{
				out.print("No record...");
			}
		}

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
