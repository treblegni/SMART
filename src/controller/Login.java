/*
 * This servlet allows users to log into an existing account.
 */
package controller;

import java.util.HashMap;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HashMap<String,User> users;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		users = new HashMap<>();
		
		String currentUser = (String) request.getSession().getAttribute("currentUser");
		
		if (currentUser != null) {
			response.sendRedirect("Lounge");
		}
		else {
			Connection c = null;
	        try {
	        	String url = "jdbc:mysql://localhost:3306/smart_database";
	        	String username = "root";
	            String password = "password";

	            c = DriverManager.getConnection( url, username, password );
				
	            Statement stmt = c.createStatement();
	            ResultSet rs = stmt.executeQuery("SELECT * FROM users");
	            
	            while (rs.next()) {
	            	String user = rs.getString("username");
	            	String pass = rs.getString("password");
	            	int age = rs.getInt("age");
	            	Boolean host = rs.getBoolean("host");
	            	
	            	users.put(user,new User(user,pass,age,host));
	            }
	            
	            c.close();
	        }
	        catch( SQLException e )
	        {
	            throw new ServletException( e );
	        }
	        finally
	        {
	            try
	            {
	                if( c != null ) c.close();
	            }
	            catch( SQLException e )
	            {
	                throw new ServletException( e );
	            }
	        }
	        request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request,response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		if (username != null) {
			if (users.containsKey(username.toLowerCase()) && users.get(username).isPassword(password)) {
				request.getSession().setAttribute("currentUser",username);
				request.getSession().setAttribute("isHost",false);
				
				response.sendRedirect("Lounge");
			}
			else {
				String error = "We could not find your account with given username/password";
				request.setAttribute("error",error);
				request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request,response);
			}
		}
		else {
			request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request,response);
		}
	}
}