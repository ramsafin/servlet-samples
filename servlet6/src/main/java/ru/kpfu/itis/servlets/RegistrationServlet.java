package ru.kpfu.itis.servlets;

import ru.kpfu.itis.entities.User;
import ru.kpfu.itis.exceptions.*;
import ru.kpfu.itis.exceptions.SecurityException;
import ru.kpfu.itis.repositories.UserRepository;
import ru.kpfu.itis.utilities.SecurityService;
import ru.kpfu.itis.utilities.ServletUtilities;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

public class RegistrationServlet extends HttpServlet {

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

            req.getServletContext().getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(req,resp);
        }else {
            //зашел в профиль, пересылаем на профиль
            resp.sendRedirect("/profile");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //вытаскиваем информацию формы
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String sex = req.getParameter("sex");
        String subscription = req.getParameter("subscription") == null ? "off":"on";
        String about = req.getParameter("about") == null? "" : req.getParameter("about");
        String remember = req.getParameter("remember");


        if (sex == null || email == null || password == null){

            req.setAttribute("message","Fill all fields");

        }else {

            //пытаемся добавить пользователя
            try{
                UserRepository.addUser( new User(email,password,sex,subscription,about,remember) );
                resp.sendRedirect("/authentication");
                return;
            } catch (NotValidPasswordException | NotValidEmailException |
                    DuplicateEntryException | DatabaseException e) {

                req.setAttribute("message",e.getMessage());
            } catch (SecurityException | SQLException e) {
                e.printStackTrace();
            }
        }
        req.getServletContext().getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(req,resp);
    }
}
