package ru.kpfu.itis.servlets;

import ru.kpfu.itis.entities.User;
import ru.kpfu.itis.repositories.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

public class ProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        Cookie[] cookies = req.getCookies();

        if(cookies != null){
            for (Cookie cookie : cookies){
                if (cookie.getName().equals("remember")){
                    //достаем user'а из БД с таким же cookie
                    User user = null;
                    try {
                        user = UserRepository.getUserByCookieValue("remember");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    if (user !=null){
                        try {
                            //меняем значение cookie
                            UserRepository.update("remember","id = "+user.getId());
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        session.setAttribute("user_a",user);
                    }
                    break;
                }
            }
        }

        if (session.getAttribute("user_a") == null){
            resp.sendRedirect("/authentication");
        }else {
            req.setAttribute("user", session.getAttribute("user_a"));
            req.getServletContext().getRequestDispatcher("/WEB-INF/views/profile.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        //обнуляем куку
        Cookie[] cookies = req.getCookies();
        if (cookies != null){
            for (Cookie cookie : cookies){
                if (cookie.getName().equals("remember")){
                    cookie.setMaxAge(0);
                    cookie.setValue(null);
                    resp.addCookie(cookie);
                    break;
                }
            }
        }
        session.setAttribute("user_a", null);
        resp.sendRedirect("/authentication");
    }
}
