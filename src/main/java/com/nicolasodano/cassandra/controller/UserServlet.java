package com.nicolasodano.cassandra.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nicolasodano.cassandra.model.UserData;

/**
 * Simple servlet that handles users
 * 
 * The servlet is registered and mapped as /users using @WebServlet annotation
 * 
 * @author Nicola Sodano
 * 
 */
@SuppressWarnings("serial")
@WebServlet("/users")
public class UserServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
		String email = req.getParameter("email");
		
		List<UserData> list = UserData.findAll();
		req.setAttribute("users", list);
		
		if(email != null) {
			UserData user = UserData.findByPrimaryKey(email);
			req.setAttribute("user", user);
		}

		getServletContext().getRequestDispatcher("/home.jsp").forward(req, resp);
	}
}
