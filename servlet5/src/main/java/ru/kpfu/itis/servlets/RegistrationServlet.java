package ru.kpfu.itis.servlets;

import ru.kpfu.itis.entities.User;
import ru.kpfu.itis.exceptions.DatabaseException;
import ru.kpfu.itis.exceptions.DuplicateEntryException;
import ru.kpfu.itis.exceptions.NotValidEmailException;
import ru.kpfu.itis.exceptions.NotValidPasswordException;
import ru.kpfu.itis.repositories.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if ((session.getAttribute("user_a") == null)){
            req.getServletContext().getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(req,resp);
        }else {
            //зашел в профиль, пересылаем на профиль
            resp.sendRedirect("/profile");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();

        //вытаскиваем информацию формы
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String sex = req.getParameter("sex");
        String subscription = req.getParameter("subscription") == null? "off": "on";
        String about = req.getParameter("about");

        if (about == null){
            about = "";
        }

        if (sex == null || email == null || password == null){
            req.setAttribute("message","Fill all fields");
        }else {

            //пытаемся добавить пользователя
            User user = new User(email,password,sex,Boolean.valueOf(subscription),about);
            try{
                UserRepository.addUser(user);
                resp.sendRedirect("/authentication");
                return;
            } catch (NotValidPasswordException | NotValidEmailException |
                    DuplicateEntryException | DatabaseException e) {

                req.setAttribute("message",e.getMessage());
            }
        }
        req.getServletContext().getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(req,resp);
    }
}
