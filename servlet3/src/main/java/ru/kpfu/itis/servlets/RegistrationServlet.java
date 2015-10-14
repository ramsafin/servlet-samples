package ru.kpfu.itis.servlets;

import ru.kpfu.itis.entities.User;
import ru.kpfu.itis.exceptions.DatabaseException;
import ru.kpfu.itis.exceptions.DuplicateEntryException;
import ru.kpfu.itis.exceptions.NotValidEmailException;
import ru.kpfu.itis.exceptions.NotValidPasswordException;
import ru.kpfu.itis.repository.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("status") != null){
            if (req.getParameter("status").equals("ok")){
                req.setAttribute("message","User has been created!");
            }
        }
        req.getServletContext().getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String sex = req.getParameter("sex");
        String checkbox = req.getParameter("subscription") == null? "off": "on";

        if (email == null || sex == null){
            req.setAttribute("message","Fill all fields, please!");
        }else {
            try{
                User user = new User(email,password,sex,checkbox);
                UserRepository.addUser(user);
                resp.sendRedirect(req.getRequestURI()+"?status=ok");
                return;
            } catch (DuplicateEntryException e) {
                req.setAttribute("message",e.getMessage());
            } catch (DatabaseException | NotValidPasswordException | NotValidEmailException e) {
                req.setAttribute("message",e.getMessage());
            }
        }
        req.getServletContext().getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(req,resp);
    }
}
