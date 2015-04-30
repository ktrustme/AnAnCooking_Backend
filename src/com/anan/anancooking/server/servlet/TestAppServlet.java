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

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



/**
 * @author kuoxin
 * This servlet is responsible for fetch all configuration info from the server
 * and present the data to the corresponding JSP.
 *
 */
public class TestAppServlet extends HttpServlet {
	//String target = "showOption.jsp";

	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
			{

		System.out.println("Connection from app received");

		String id = request.getParameter("id");

		PrintWriter out = response.getWriter();
		String title = "Auto Refresh Header Setting";
		out.println("OK...Now I think it works, and the id is: "+id);
		//String host = getServletContext().getInitParameter("serverHost");
		//int port = Integer.valueOf(getServletContext().getInitParameter("serverPort"));
		/*
    	SelectCarOption selectCarOption = new SelectCarOption(host, port);
    	System.out.println(makeModel);
    	System.out.println(makeModel.split("\\.")[0]);
    	Automobile auto = selectCarOption.fetchAuto(Integer.valueOf(makeModel.split("\\.")[0]));
        request.setAttribute("Model/Make", makeModel);
    	HttpSession session = request.getSession();
    	session.setAttribute("auto", auto);
    	session.setAttribute("Model/Make", auto.getMake()+" " + auto.getModel());
    	ArrayList<ArrayList<String>> listSet = null;
    	request.setAttribute("basePrice","" + auto.getBasePrice());
    	listSet = auto.returnOptionList();


        request.setAttribute("listSet", listSet);

    	session.setAttribute("listSet", listSet);
		 */
		/*
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher(target);
		dispatcher.forward(request, response);
			}
		 */ 

			}
}
