package ru.kpfu.itis.servlets;

import ru.kpfu.itis.entities.User;
import ru.kpfu.itis.exceptions.NotValidEmailException;
import ru.kpfu.itis.exceptions.NotValidPasswordException;
import ru.kpfu.itis.exceptions.UserExistsException;
import ru.kpfu.itis.repo.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html");
        String reqParam = req.getParameter("exception");
        if (reqParam  == null){
            //if no exception
            resp.getWriter().println(getPageCode(""));
            return;
        }
            //else
        switch (reqParam){
            case "password" : resp.getWriter().println(getPageCode("Password must be from 4 to 16 symbols"));
                break;
            case "email" : resp.getWriter().println(getPageCode("Email is not valid"));
                break;
            case "user" : resp.getWriter().println(getPageCode("user with the same email is already exists"));
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String sex = req.getParameter("radio");
        String checkboxStatus = req.getParameter("checkbox");
        if (email == null || password == null){
            return;
        }
        //trying to add new user
        try {
            UserRepository.addUser(new User(email,password,sex,checkboxStatus==null? "off" : "on"));
        } catch (UserExistsException e) {
            //if user exists redirect to exception(user) page
            resp.sendRedirect("/registration?exception=user");
            return;
        } catch (NotValidPasswordException e) {
            //if password is not valid redirect to exception(password) page
            resp.sendRedirect("/registration?exception=password");
            return;
        } catch (NotValidEmailException e) {
            //if email is not valid redirect to exception(email) page
            resp.sendRedirect("/registration?exception=email");
            return;
        }
        //if user was successfully added
        resp.getWriter().println(getPageCode(""));
        resp.sendRedirect("/registration");
    }

    /**
     * @param error - string that describes exception(error)
     * @return string html code
     */
    private String getPageCode(String error){

        StringBuilder sb = new StringBuilder();
        sb.append("<!DOCTYPE html>");
        sb.append("<html> <head><title>My Task 2</title> <meta charset=\"utf-8\"/></head>");
        sb.append("<body>");
        sb.append("<form action=\"\\registration\" method=\"POST\">");
        sb.append("<p>Email:<br><input type=\"text\" size=\"20\" name=\"email\"></p>");
        sb.append("<p>Password:<br><input id=\"pass\" type=\"password\" name=\"password\" value=\"\" required placeholder=\"password\"></p>");
        sb.append("<p><input type=\"radio\" name=\"radio\" value=\"male\">male&nbsp&nbsp");
        sb.append("<input type=\"radio\" name=\"radio\" value=\"female\">female</p>");
        sb.append("<input type=\"checkbox\" name=\"checkbox\" style=\"height:15px; width:20px;\">Подписаться на новости</input></p>");
        sb.append("<p><input id=\"submit\" type=\"submit\" value=\"Отправить\"></form></p><p></p></form>");
        sb.append("<p>").append(error).append("</p>");
        sb.append("</body>");
        sb.append("</html>");
        return sb.toString();
    }
}
