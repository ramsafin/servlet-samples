package ru.kpfu.itis.servlets;

import ru.kpfu.itis.entities.User;
import ru.kpfu.itis.exceptions.IdentifyingException;
import ru.kpfu.itis.exceptions.SecurityException;
import ru.kpfu.itis.repositories.UserRepository;
import ru.kpfu.itis.utilities.IdentificationService;
import ru.kpfu.itis.utilities.SecurityService;
import ru.kpfu.itis.utilities.ServletUtilities;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

public class AuthenticationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        if ((session.getAttribute("user_a") == null)){

            Cookie cookie = ServletUtilities.getCookie(req, "remember");

            if (cookie != null){
                //достаем user'а из БД с таким же cookie
                User user;
                try {
                    user = UserRepository.getUserByCookie(cookie);
                    if (user !=null){
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

            req.getServletContext().getRequestDispatcher("/WEB-INF/views/authentication.jsp").forward(req, resp);
        }else {
            //зашел в профиль, пересылаем на профиль
            resp.sendRedirect("/profile");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String remember = req.getParameter("remember"); //box запомнить меня

        if (email == null || password == null){
            req.setAttribute("message","Fill email and password fields");
        }else {
            try {
                User user = IdentificationService.identification(email,password);
                session.setAttribute("user_a",user);

                //если нужно запомнить user`а и он прошел идентификацию
                if (remember != null){
                    Cookie cookie = new Cookie("remember", SecurityService.genRndHash(12));
                    cookie.setMaxAge(60*60*10);
                    UserRepository.updateUserCookie(user,cookie);
                    resp.addCookie(cookie);

                }
                resp.sendRedirect("/profile");
                return;
            } catch (IdentifyingException e) {
                req.setAttribute("message",e.getMessage());
            } catch (SecurityException | SQLException e) {
                e.printStackTrace();
            }
        }
        req.getServletContext().getRequestDispatcher("/WEB-INF/views/authentication.jsp").forward(req,resp);
    }
}
