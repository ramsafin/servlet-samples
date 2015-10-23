package ru.kpfu.itis.servlets;

import ru.kpfu.itis.entities.User;
import ru.kpfu.itis.exceptions.IdentifyingException;
import ru.kpfu.itis.exceptions.SecurityException;
import ru.kpfu.itis.repositories.UserRepository;
import ru.kpfu.itis.utilities.IdentificationService;
import ru.kpfu.itis.utilities.SecurityService;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

//import ru.kpfu.itis.utilities.UserService;

public class AuthenticationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        Cookie[] cookies = req.getCookies();
        if(cookies != null){
            for (Cookie cookie : cookies){
                if (cookie.getName().equals("remember")){
                    //достаем user'а из БД с таким же значением
                    User user = null;
                    try {
                        user = UserRepository.getUserByCookieValue("remember");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    if (user !=null){
                        try {
                            //меняем значение cookie
                            System.out.println("auth do get user != null по getUserByCookie");
                            UserRepository.update("remember", "id = "+user.getId());
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        session.setAttribute("user_a", user);
                    }
                    break;
                }
            }
        }

        if ((session.getAttribute("user_a") == null)){
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
        String remember = req.getParameter("remember");

        System.out.println("remember in doPost auth = " + remember);

        if (email == null || password == null){
            req.setAttribute("message","Fill email and password fields");
        }else {
            try {
                if (remember != null){
                    System.out.println("remember is not null in doPost auth = " + remember);
                    Cookie cookie = new Cookie("remember", SecurityService.genRndHash(12));
                    cookie.setMaxAge(60*60*60*72);
                    resp.addCookie(cookie);
                }
                session.setAttribute("user_a", IdentificationService.identification(email, password));
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
