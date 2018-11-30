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
		
		Connection c = null;
        try {
        	String url = "jdbc:mysql://localhost:3306/smart_database";
        	String username = "root";
            String password = "password";

            c = DriverManager.getConnection( url, username, password );
			
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM users");
            
            while (rs.next()) {
            	//not used yet
            	int id = rs.getInt("id");
            	String user = rs.getString("username");
            	String pass = rs.getString("password");
            	int age = rs.getInt("age");
            	
            	users.put(user,new User(user,pass,age));
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		if (users.containsKey(username) && users.get(username).isValid(password)) {
			request.getSession().setAttribute("username",username);
			response.sendRedirect("./index.html");
		}
		else {
			response.sendRedirect("Login");
		}
	}

}