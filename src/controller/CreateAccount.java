/*
 * This servlet allows users to create a new account, which will be stored within SMART's database.
 */
package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;

@WebServlet("/CreateAccount")
public class CreateAccount extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String currentUser = (String) request.getSession().getAttribute("currentUser");
		
		if (currentUser != null) {
			response.sendRedirect("Lounge");
		}
		else {
			request.getRequestDispatcher("/WEB-INF/create-account.jsp").forward(request,response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String newUsername = request.getParameter("username");
		String newPassword = request.getParameter("password");
		String newAge = request.getParameter("age");
		
		newUsername = newUsername.trim();
		newPassword = newPassword.trim();
		newAge = newAge.trim();
		
		HashMap<String,User> users = new HashMap<>();
		
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
            	user = user.toLowerCase();
            	
            	users.put(user,new User(user,pass,age,host));
            }
            
            if (!users.containsKey(newUsername.toLowerCase())) {
            	String stmtQuery = "INSERT INTO users VALUES(NULL,?,?,?,false);";
	            PreparedStatement pstmt = c.prepareStatement(stmtQuery);
	            pstmt.setString(1,newUsername.toLowerCase());
	            pstmt.setString(2,newPassword);
	            pstmt.setInt(3,Integer.parseInt(newAge));
	            pstmt.executeUpdate();
	            
	            c.close();
	            
	            response.sendRedirect("UserValidation");
    		}
            else {
            	c.close();
            	
            	String error = "That username already exists";
        		request.setAttribute("error",error);
        		request.getRequestDispatcher("/WEB-INF/create-account.jsp").forward(request,response);
            }
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
	}

}
