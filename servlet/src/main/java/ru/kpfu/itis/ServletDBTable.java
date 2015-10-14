package ru.kpfu.itis;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Ramil Safin
 */

public class ServletDBTable extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");
        resp.getWriter().println(getHTMLCode());
    }

    private ArrayList<String> getData(){
        ArrayList<String> stringList = new ArrayList<>();
        try(Scanner sc = new Scanner(ServletForm.DATA)) {
            while (sc.hasNext()){
                stringList.add(sc.next());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return stringList;
    }

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

        ArrayList<String> list = getData();
        for (int i = 0; i < list.size(); i += 4) {
            sb.append("<tr style=\"text-align:left;\">");
            sb.append("<td>").append(list.get(i)).append("</td><td>").append(list.get(i + 1)).append("</td><td>")
                    .append(list.get(i + 2)).append("</td>").append("<td>").append(list.get(i + 3)).append("</td>");
            sb.append("</tr>");
        }
        sb.append("</table>");
        sb.append("</body>");
        sb.append("</html>");
        return sb.toString();
    }
}
