package ru.kpfu.itis.servlets;

import org.json.JSONObject;
import ru.kpfu.itis.entities.Post;
import ru.kpfu.itis.entities.User;
import ru.kpfu.itis.repositories.PostRepository;
import ru.kpfu.itis.repositories.UserRepository;
import ru.kpfu.itis.utilities.SecurityService;
import ru.kpfu.itis.utilities.ServletUtilities;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AjaxServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        if (session.getAttribute("user_a") == null){

            Cookie cookie = ServletUtilities.getCookie(req, "remember");

            if (cookie != null){

                try{

                    User user = UserRepository.getUserByCookie(cookie);

                    if (user != null){

                        //меняем значение cookie для безопасности
                        Cookie newCookie = new Cookie("remember", SecurityService.genRndHash(12));
                        newCookie.setMaxAge(60*60*48);
                        UserRepository.updateUserCookie(user,newCookie);
                        resp.addCookie(newCookie);
                        session.setAttribute("user_a",user);
                        resp.sendRedirect("/ajaxTest");
                        return;

                    }
                } catch (SQLException e) {
                    req.setAttribute("message","Server problems(");
                    e.printStackTrace();
                }
            }
            resp.sendRedirect("/login");

        }else {

            req.setAttribute("user",session.getAttribute("user_a"));

            //все посты всех юзеров
            List<Post> posts = new ArrayList<>();
            try {
                posts = PostRepository.getAllPosts();
            } catch (SQLException e) {
                req.setAttribute("message","Sorry, some problems with server(");
                e.printStackTrace();
            }
            req.setAttribute("posts", posts);
            req.getServletContext().getRequestDispatcher("/WEB-INF/views/ajaxTest.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        User user = (User)session.getAttribute("user_a");
        String textForPost = req.getParameter("text");

        Post post = new Post(textForPost,user.getId());
        post.setUserName(user.getEmail());

        try {
            PostRepository.addPost(post);
            String data = getJSON("post", textForPost);
            writeJSON("src/main/webapp/post.json",data);
            resp.getWriter().write(data);
        } catch (SQLException e) {
            req.setAttribute("message","problems in server");
            e.printStackTrace();
        }
    }


    private static void writeJSON(String file,String data){

        if (data == null){
            return;
        }

        try(FileWriter fw = new FileWriter(file)){
            fw.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private static String getJSON(String key, String value){

        try(StringWriter sWriter  = new StringWriter()) {

            JSONObject obj = new JSONObject();

            obj.put(key,value);

            obj.write(sWriter);

            return sWriter.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
