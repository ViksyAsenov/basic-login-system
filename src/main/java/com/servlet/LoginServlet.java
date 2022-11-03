package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().append("Served at: ").append(req.getContextPath());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user = new User(username, "", password);
        String result = ConnectToDB.get(user);

        out.println("<script type=\"text/javascript\">");
        if(result.equals("Welcome!")) {
            out.println("alert('" + result + "'); window.location = 'home.html';");
        } else {
            out.println("alert('" + result + "'); window.location = 'loginPage.html';");
        }
        out.println("</script>");
    }
}
