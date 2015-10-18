package ru.kpfu.itis.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("user_a") == null){
            resp.sendRedirect("/authentication");
        }else {
            System.out.println("attribute user_a in doGet равно");
            System.out.println(session.getAttribute("user_a"));
            req.setAttribute("user", session.getAttribute("user_a"));
            req.getServletContext().getRequestDispatcher("/WEB-INF/views/profile.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.setAttribute("user_a", null);
        System.out.println(session.getAttribute("user_a")+"  "+"attribute user_a in doPost");
        resp.sendRedirect("/authentication");
    }
}
