/*
 * This is SMART's user interface. It is used to validate a username and password when a user attempts to login. If a user creates an account, the interface will add their account to the database, keeping track of the username, password, and age that the user entered into each text field.
 */
package controller;

import model.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Users")
public class Users extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		// Add the list of all users to the servlet context.
		List<User> users = new ArrayList<User>();

		getServletContext().setAttribute("users", users);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html lang=\"en\">");

		out.println("<head>");
		out.println("    <meta charset=\"UTF-8\">");
		out.println("    <title>Users</title>");
		out.println("     <link rel=\"stylesheet\" href=\"https://bootswatch.com/4/pulse/bootstrap.css\">");
		out.println("</head>");
		out.println("<body>");
		out.println("<div class=\"container\">");

		// Get the list of users from the init() method.
		List<User> users = (List<User>) getServletContext().getAttribute("users");

		// Initialize data fields for username, password, and age.
		String username = "";
		String password = "";
		int age = 0;

		// If the user chooses to log into their account, verify that the
		// username and password that they entered matches the username and
		// password of an account within the database. If the username and
		// password are valid, send them to the main menu. If the data fields
		// are invalid, redirect the user to the login page.
		if (request.getParameter("username") != null) {

			username = request.getParameter("username");
			password = request.getParameter("password");

			for (User user : users) {

				if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {

					response.sendRedirect("MainMenu.html");
					return;
				}
			}

			response.sendRedirect("Login.jsp");
			return;
		}

		// If the user chooses to create a new account, get their username,
		// password, and age and add them to the database. Once their account
		// has been added to the database, send the user to the main menu.
		if (request.getParameter("createusername") != null) {

			username = request.getParameter("createusername");
			password = request.getParameter("createpassword");
			age = Integer.parseInt(request.getParameter("age"));

			User user = new User(username, password, age);

			users.add(user);

			response.sendRedirect("MainMenu.html");
		}

		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
	}

}