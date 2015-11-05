package ru.kpfu.itis.servlets;

import ru.kpfu.itis.entities.User;
import ru.kpfu.itis.repositories.UserRepository;
import ru.kpfu.itis.utilities.SecurityService;
import ru.kpfu.itis.utilities.ServletUtilities;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

public class WelcomeServlet extends HttpServlet {

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
                        resp.sendRedirect(req.getRequestURI());
                        return;
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }else {
            //если юзер уже вошел
            req.setAttribute("user",session.getAttribute("user_a"));
        }

        getServletContext().getRequestDispatcher("/WEB-INF/views/welcome.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        String exitParam = req.getParameter("exit");

        if ("exit".equals(exitParam)){
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
            resp.sendRedirect("/login");
        }
    }
}
