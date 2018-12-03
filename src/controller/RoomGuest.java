package controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Room
 */
@WebServlet("/RoomGuest")
public class RoomGuest extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static HashMap<String,String> playlist = new HashMap<>();
       
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
		
		if (currentUser != null) {
			String[] trackIds = request.getParameterValues("trackIds[]");
			String[] trackNames =  request.getParameterValues("trackNames[]");
			
			if (trackIds != null) {
				System.out.println(trackNames[0]);
				for (int i = 0 ; i < trackIds.length ; i++) {
					if (!playlist.containsKey(trackIds[i])) {
						playlist.put(trackNames[i],trackIds[i]);
					}
				}
			}
			request.setAttribute("playlist",playlist);
			request.getRequestDispatcher("/WEB-INF/room-guest.jsp").forward(request,response);
		}
		else {
			response.sendRedirect("Login");
		}	
	}
}
