package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
@WebServlet("/Room")
public class Room extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static HashMap<String,String> playlist = new HashMap<>();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Room() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		request.getRequestDispatcher("/WEB-INF/room.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
