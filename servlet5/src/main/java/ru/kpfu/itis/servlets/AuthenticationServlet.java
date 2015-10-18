package ru.kpfu.itis.servlets;

import ru.kpfu.itis.exceptions.DatabaseException;
import ru.kpfu.itis.exceptions.IdentifyingException;
import ru.kpfu.itis.utilities.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthenticationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("user_a") == null){
            req.getServletContext().getRequestDispatcher("/WEB-INF/views/authentication.jsp").forward(req, resp);
        }else {
            resp.sendRedirect("/profile");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        if (email == null || password == null){
            req.setAttribute("message","Fill email and password fields");
        }else {
            try {
                session.setAttribute("user_a", UserService.identification(email, password));
                resp.sendRedirect("/profile");
                return;
            } catch (DatabaseException | IdentifyingException e) {
                req.setAttribute("message",e.getMessage());
            }
        }
        req.getServletContext().getRequestDispatcher("/WEB-INF/views/authentication.jsp").forward(req,resp);
    }
}
