package controller;

import model.Track;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Room
 */
@WebServlet("/CreateRoom")
public class CreateRoom extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
//	private static List<Track> userTrackCache = new ArrayList<>();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateRoom() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String currentUser = (String) request.getSession().getAttribute("currentUser");
		List<Track> playlist = new ArrayList<>();
		
		if (currentUser != null) {
			Connection c = null;
			
	        try {
	        	String url = "jdbc:mysql://localhost:3306/smart_database";
	        	String username = "root";
	            String password = "password";

	            c = DriverManager.getConnection( url, username, password );
				
	            String stmtQuery = "SELECT * FROM room_tracks WHERE room_host=?;";
	            PreparedStatement pstmt = c.prepareStatement(stmtQuery);
	            pstmt.setString(1,currentUser);
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
			request.getRequestDispatcher("/WEB-INF/create-room.jsp").forward(request,response);
		}
		else {
			response.sendRedirect("Login");
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String currentUser = (String) request.getSession().getAttribute("currentUser");
		String selected = request.getParameter("selected");
		HashMap<String,Track> roomTracks = new HashMap<>();
		
		Connection c = null;
		
        try {
        	String url = "jdbc:mysql://localhost:3306/smart_database";
        	String username = "root";
            String password = "password";

            c = DriverManager.getConnection( url, username, password );
			
            String stmtQuery = "SELECT * FROM room_tracks WHERE room_host=?;";
            PreparedStatement pstmt = c.prepareStatement(stmtQuery);
            pstmt.setString(1,currentUser);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
            	String id = rs.getString("track_id");
            	String name = rs.getString("track_name");
            	String artist = rs.getString("track_artist");
            	
            	roomTracks.put(name,new Track(name,artist,id));
            }
            String[] info = selected.split(",");

            if (roomTracks != null) {
            	if (!roomTracks.containsKey(info[0])) {
                	stmtQuery = "INSERT INTO room_tracks VALUES(?,?,?,?);";
    	            pstmt = c.prepareStatement(stmtQuery);
    	            pstmt.setString(1,info[2]);
    	            pstmt.setString(2,info[0]);
    	            pstmt.setString(3,info[1]);
    	            pstmt.setString(4,currentUser);
    	            pstmt.executeUpdate();
        		}
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
        response.sendRedirect("CreateRoom");
	}
}
