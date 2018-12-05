package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Track;

/**
 * Servlet implementation class Room
 */
@WebServlet("/RoomGuest")
public class RoomGuest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RoomGuest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String currentUser = (String) request.getSession().getAttribute("currentUser");
		String roomHost = request.getParameter("selected");
		List<Track> playlist = new ArrayList<>();
		
		System.out.println(roomHost);
		
		if (currentUser != null) {
			if (roomHost != null) {
				Connection c = null;
				
		        try {
		        	String url = "jdbc:mysql://localhost:3306/smart_database";
		        	String username = "root";
		            String password = "password";

		            c = DriverManager.getConnection( url, username, password );
		            
		            String stmtQuery = "SELECT * FROM room_tracks WHERE room_host=?;";
		            PreparedStatement pstmt = c.prepareStatement(stmtQuery);
		            pstmt.setString(1,roomHost);
		            ResultSet rs = pstmt.executeQuery();
		            
		            while (rs.next()) {
		            	String id = rs.getString("track_id");
		            	String name = rs.getString("track_name");
		            	String artist = rs.getString("track_artist");
		            	
		            	playlist.add(new Track(name,artist,id));
		            }
		            c.close();
		            request.setAttribute("playlist",playlist);
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
			if (!playlist.isEmpty()) {
				request.getRequestDispatcher("/WEB-INF/room-guest.jsp").forward(request,response);
	        }
	        response.sendRedirect("Lounge");
		}
		else {
			response.sendRedirect("Login");
		}
	}
}
