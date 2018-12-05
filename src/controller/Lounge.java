package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Lounge
 */
@WebServlet("/Lounge")
public class Lounge extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Lounge() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String currentUser = (String) request.getSession().getAttribute("currentUser");
		
		if (currentUser != null) {
			Connection c = null;
			
	        try {
	        	String url = "jdbc:mysql://localhost:3306/smart_database";
	        	String username = "root";
	            String password = "password";

	            c = DriverManager.getConnection( url, username, password );
				
	            String stmtQuery = "DELETE FROM room_tracks WHERE room_host=?;";
	            PreparedStatement pstmt = c.prepareStatement(stmtQuery);
	            pstmt.setString(1,currentUser);
	            pstmt.executeUpdate();
	            
	            stmtQuery = "DELETE FROM rooms WHERE room_host=?;";
	            pstmt = c.prepareStatement(stmtQuery);
	            pstmt.setString(1,currentUser);
	            pstmt.executeUpdate();
	            
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
			request.getRequestDispatcher("/WEB-INF/lounge.jsp").forward(request,response);
		}
		else {
			response.sendRedirect("Login");
		}
		
	}
}
