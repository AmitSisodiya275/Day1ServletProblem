package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/LoginServlet", initParams = { @WebInitParam(name = "user", value = "^[A-Z]{1}[a-z]{2,}"),
		@WebInitParam(name = "password", value = "(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%^&_++()]).{8,}") })

public class LoginServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String user = request.getParameter("user");
		String pwd = request.getParameter("pwd");

		String userId = getServletConfig().getInitParameter("user");
		String password = getServletConfig().getInitParameter("password");

		if (Pattern.matches(userId, user) && Pattern.matches(password, pwd)) {
			request.setAttribute("user", user);
			request.getRequestDispatcher("LoginSuccess.jsp").forward(request, response);
		} else {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
			PrintWriter writer = response.getWriter();
			writer.println("<font color=red> Either Username or Password is Wrong.</font>");
			rd.include(request, response);
			writer.close();
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter writer = resp.getWriter();
		writer.println("This is servler");
		writer.close();
	}
}
