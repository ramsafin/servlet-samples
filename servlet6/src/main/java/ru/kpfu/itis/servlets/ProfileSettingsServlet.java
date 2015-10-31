package ru.kpfu.itis.servlets;

import ru.kpfu.itis.entities.User;
import ru.kpfu.itis.repositories.UserRepository;
import ru.kpfu.itis.utilities.SecurityService;
import ru.kpfu.itis.utilities.ServletUtilities;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

public class ProfileSettingsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        if ((session.getAttribute("user_a") == null)){

            Cookie cookie = ServletUtilities.getCookie(req, "remember");

            if (cookie != null){
                //достаем user'а из БД с таким же cookie
                try {
                    User user = UserRepository.getUserByCookie(cookie);
                    if (user !=null){
                        //меняем значение cookie для безопасности
                        Cookie newCookie = new Cookie("remember", SecurityService.genRndHash(12));
                        newCookie.setMaxAge(60*60*48);
                        UserRepository.updateUserCookie(user,newCookie);
                        resp.addCookie(newCookie);
                        session.setAttribute("user_a",user);
                        resp.sendRedirect("/profileSettings");
                        return;
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            resp.sendRedirect("/login");
        }else {
            req.setAttribute("user", session.getAttribute("user_a"));
            req.getServletContext().getRequestDispatcher("/WEB-INF/views/profileSettings.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        String sex = req.getParameter("sex");
        String subscription = req.getParameter("subscription") == null ? "off":"on";
        String about = req.getParameter("about");

        if (req.getParameter("settings") != null){
            try {
                User user = (User) session.getAttribute("user_a");
                user.setSex(sex);
                user.setAbout(about);
                user.setSubscription(subscription);

                UserRepository.updateUser(user);
                resp.sendRedirect("/profile");
            } catch (SQLException e) {
                req.setAttribute("message","server problems");
                e.printStackTrace();
            }
        }
    }
}
