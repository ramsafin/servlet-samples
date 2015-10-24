package ru.kpfu.itis.servlets;

import ru.kpfu.itis.entities.User;
import ru.kpfu.itis.repositories.UserRepository;
import ru.kpfu.itis.utilities.SecurityService;
import ru.kpfu.itis.utilities.ServletUtilities;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

public class PostServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        if (session.getAttribute("user_a") == null){
            Cookie cookie = ServletUtilities.getCookie(req,"remember");
            if (cookie != null){

                try{
                    User user = UserRepository.getUserByCookie(cookie);
                    if (user != null){
                        //меняем значение cookie для безопасности
                        Cookie newCookie = new Cookie("remember", SecurityService.genRndHash(12));
                        newCookie.setMaxAge(60*60*10);
                        UserRepository.updateUserCookie(user,newCookie);
                        resp.addCookie(newCookie);
                        session.setAttribute("user_a",user);
                        resp.sendRedirect("/profile?id="+user.getId());
                        return;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }

        }else {
            req.setAttribute("user", session.getAttribute("user_a"));
            req.getServletContext().getRequestDispatcher("/WEB-INF/views/profile.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
