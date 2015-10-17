package ru.kpfu.itis.servlets;

import ru.kpfu.itis.Utilities.UserService;
import ru.kpfu.itis.entities.User;
import ru.kpfu.itis.exceptions.DatabaseException;
import ru.kpfu.itis.exceptions.IdentifyException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class AuthenticateServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("user_authentication") == null){
            req.getServletContext().getRequestDispatcher("/WEB-INF/views/authentication.jsp").forward(req, resp);
        }else {
            resp.sendRedirect("/profile");
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        try {
            User user = UserService.identification(email,password);
            session.setAttribute("user_authentication",user);
            resp.sendRedirect("/profile");
            return;
        } catch (DatabaseException | IdentifyException e) {
            req.setAttribute("message", e.getMessage());
        }
        req.getServletContext().getRequestDispatcher("/WEB-INF/views/authentication.jsp").forward(req,resp);
    }

}
