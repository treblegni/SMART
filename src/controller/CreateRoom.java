package controller;

import model.Track;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.IOException;
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
	private static List<Track> userTrackCache = null;
	private static HashMap<String,Track> userTracks = new HashMap<>();
	
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
		
		if (currentUser != null) {
			String[] trackNames = request.getParameterValues("trackNames[]");
			String[] trackArtists = request.getParameterValues("trackArtists[]");
			String[] trackIds = request.getParameterValues("trackIds[]");
			String selected = request.getParameter("selected");
			
			if (trackNames != null) {
				userTrackCache = new ArrayList<>();
				for (int i = 0 ; i < trackNames.length ; i++) {
					userTrackCache.add(new Track(trackNames[i],trackArtists[i],trackIds[i]));
				}
			}
			
			if (selected != null) {
				String[] info = selected.split(",");
				
				if (!userTracks.containsKey(info[0])) {
					Track track = new Track(info[0],info[1],info[2]);
					userTracks.put(info[0],track);
				}
			}
			request.setAttribute("userTracks",userTracks);
			request.setAttribute("userTrackCache",userTrackCache);
			request.getRequestDispatcher("/WEB-INF/create-room.jsp").forward(request,response);
		}
		else {
			response.sendRedirect("Login");
		}
	}
}
