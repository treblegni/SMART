/*
 * This class is designed as a prototype for the database. It contains the username, password, and age of every user entered into the SMART database.
 */
package controller;

import model.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AllUsers")
public class AllUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html lang=\"en\">");

		out.println("<head>");
		out.println("    <meta charset=\"UTF-8\">");
		out.println("    <title>Database</title>");
		out.println("     <link rel=\"stylesheet\" href=\"https://bootswatch.com/4/pulse/bootstrap.css\">");
		out.println("</head>");
		out.println("<body>");
		out.println("<div class=\"container\">");

		// Get a list of all user accounts.
		List<User> users = (List<User>) getServletContext().getAttribute("users");
		out.println("<table class = \"table table-bordered table-striped table-hover\">");
		out.print("<tr>");
		out.print("<td>Username</td>");
		out.print("<td>Password</td>");
		out.print("<td>Age</td>");
		out.print("</tr>");
		// Display the username, password, and age of a single user in each row
		// of the table.
		for (User user : users) {

			out.print("<tr>");
			out.println("<td>" + user.getUsername() + "</td>");
			out.println("<td>" + user.getPassword() + "</td>");
			out.println("<td>" + user.getAge() + "</td>");
			out.println("</tr>");
		}

		out.println("</table>");

		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}