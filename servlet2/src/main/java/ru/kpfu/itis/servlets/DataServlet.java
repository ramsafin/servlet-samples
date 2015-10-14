package ru.kpfu.itis.servlets;

import ru.kpfu.itis.entities.User;
import ru.kpfu.itis.repo.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class DataServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html");
        resp.getWriter().println(getHTMLCode());
    }

    @Override
     protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
     }

    /**
     * @return string html code which shows (table of users)
     */
    protected String getHTMLCode(){
        StringBuilder sb = new StringBuilder();
        sb.append("<!DOCTYPE html>");
        sb.append("<html>");
        sb.append("<head><meta charset=\"utf-8\"/>");
        sb.append("<title>database</title>");
        sb.append("<style>");
        sb.append("table {" +
                "border-collapse: collapse;" +
                "border: 3px solid black;" +
                "}td, th {border: 2px solid lightgray;}");
        sb.append("</style>");
        sb.append("</head");
        sb.append("<body>");
        sb.append("<table cellpadding=\"10\" style=\"margin:20px;\">");
        sb.append("<tr style=\"text-align:center;\">");
        sb.append("<th>Email</th><th>Password</th><th>Пол</th><th>Подписка на новости</th>");
        sb.append("</tr>");
        //list if users
        ArrayList<User> users = UserRepository.getUsers();
        for (User currentUser : users) {

            sb.append("<tr style=\"text-align:left;\">");

            sb.append("<td>");
            sb.append(currentUser.getEmail());
            sb.append("</td>");

            sb.append("<td>");
            sb.append(currentUser.getPassword());
            sb.append("</td>");

            sb.append("<td>");
            sb.append(currentUser.getSex());
            sb.append("</td>");

            sb.append("<td>");
            sb.append(currentUser.getCheckboxStatus());
            sb.append("</td>");

            sb.append("</tr>");
        }

        sb.append("</table>");
        sb.append("</body>");
        sb.append("</html>");
        return sb.toString();
    }
}
