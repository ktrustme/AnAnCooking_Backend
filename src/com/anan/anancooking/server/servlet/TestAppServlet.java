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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.anan.anancooking.model.RecipeImplementation;
import com.anan.anancooking.model.Step;
import com.anan.anancooking.server.database.DBServer;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
//import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;



/**
 * @author kuoxin
 * This servlet is responsible for fetch all configuration info from the server
 * and present the data to the corresponding JSP.
 *
 */
public class TestAppServlet extends HttpServlet {
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
			/*
			String test = request.getHeader("steps");
			
			System.out.println("AuthType: "+request.getAuthType());
			System.out.println("ContentType: "+request.getContentType());
			System.out.println("Header: "+request.getHeaderNames());
			System.out.println("test:");
			System.out.println(test);
			*/
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String json = "";
        if(br != null){
        	
            json = br.readLine();
            JsonParser parser = new JsonParser();
            Object object = (Object) parser.parse(json);
            JsonObject jsonObj = (JsonObject) object;
            
            // parse the JSON element by element
            Gson gson = new Gson();
            String recipeName = gson.fromJson(jsonObj.get("recipeName"),String.class);
            String description = gson.fromJson(jsonObj.get("description"),String.class);
            String ingredients = gson.fromJson(jsonObj.get("ingredients"),String.class);
            byte[] image = gson.fromJson(jsonObj.get("previewByteCode"),byte[].class);
            int time =  Integer.parseInt(gson.fromJson(jsonObj.get("time"),String.class));
            //ArrayList<Step> steps2 = gson.fromJson(jsonObj.get("steps"),ArrayList.class);
            
            ArrayList<Step> steps =(ArrayList<Step>) gson.fromJson(jsonObj.get("steps"),
                    new TypeToken<ArrayList<Step>>() {
                    }.getType() );
            
            // reconstruct the recipe
            RecipeImplementation ri = new RecipeImplementation();
            
            // reconstruct the ingredient.....
            ArrayList<String> ingred = new ArrayList<String>();
            String[] temp = description.split("[^A-Za-z]");
            for(int i=0;i<temp.length;i++){
            	ingred.add(temp[i]);
            }
            
            ri.setDescription(description);
            ri.setIngredients(ingred);
            ri.setName(recipeName);
            ri.setPreviewByteCode(image);
            ri.setSteps(steps);
            
            DBServer dbServer = new DBServer();
            dbServer.addRecipe(ri);
            // test all the variables 
            /*
            System.out.println(time);
            System.out.println(recipeName);
            System.out.println(description);
            System.out.println(ingredients);
            System.out.println(image);
            for(int i =0;i<steps.size();i++){
            	System.out.println(steps.get(i).getDes());
            }
            */

        }
		
		
			//this.doGet(request, response);
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
			{
		
		}
	
}
