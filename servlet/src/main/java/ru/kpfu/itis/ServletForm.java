package ru.kpfu.itis;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @author Ramil Safin
 */

public class ServletForm extends HttpServlet {

    //database file, where storing email, password, gender, checkbox status
    public static final File DATA = new File("/Users/Ramil/Desktop/data.txt");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");
        resp.getWriter().println(getHTMLCode(""));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");
        if (new EmailChecker(DATA, req.getParameter("email")).check()) {
            resp.sendRedirect("\\form");
            resp.getWriter().println(getHTMLCode(""));
            writeToFile(req.getParameter("email"), req.getParameter("password"), req.getParameter("gender"), req.getParameter("checkbox"));
        } else {
            resp.getWriter().println(getHTMLCode("This email address is used!!!"));
        }
    }

    protected void writeToFile(String email, String password, String pol, String checkbox_st) {

        try (FileWriter pw = new FileWriter(DATA, true)) {
            if (checkbox_st == null){
                checkbox_st = "off";
            }
            if (pol == null) {
                pol = "unknown";
            }
            pw.append(email + " " + password + " " + pol + " " + checkbox_st + " ");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected String getHTMLCode(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("<!DOCTYPE html>\n");
        sb.append("<html> <head><title>My Task</title> <meta charset=\"utf-8\"/></head>");
        sb.append("<body>");
        sb.append("<form action=\"\\form\" method=\"POST\">");
        sb.append("<p>Email:<br><input type=\"email\" name=\"email\" required placeholder=\"example@mailbox.ru\"></p>");
        sb.append("<p>Password:<br><input id=\"pass\" type=\"password\" name=\"password\" value=\"\" required placeholder=\"password\"></p>");
        sb.append("<p><br><br><select required id=\"selecter\" size=\"1\" name=\"gender\">");
        sb.append("<option selected disabled>выберите пол</option>");
        sb.append("<option>male</option>");
        sb.append("<option>female</option>");
        sb.append("</select></p><p><br>");
        sb.append("<input type=\"checkbox\" name=\"checkbox\" style=\"height:15px; width:20px;\">Подписаться на новости</input></p>");
        sb.append("<p><input id=\"submit\" type=\"submit\" value=\"Отправить\"></form></p><p></p></form>");
        sb.append("<p>").append(str).append("</p>");
        sb.append("</body>");
        sb.append("</html>");
        return sb.toString();
    }


}
