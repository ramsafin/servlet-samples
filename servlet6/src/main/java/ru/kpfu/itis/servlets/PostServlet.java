package ru.kpfu.itis.servlets;

import ru.kpfu.itis.entities.Post;
import ru.kpfu.itis.entities.User;
import ru.kpfu.itis.repositories.PostRepository;
import ru.kpfu.itis.repositories.UserRepository;
import ru.kpfu.itis.utilities.SecurityService;
import ru.kpfu.itis.utilities.ServletUtilities;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        if (session.getAttribute("user_a") == null){

            Cookie cookie = ServletUtilities.getCookie(req,"remember");

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
                        resp.sendRedirect("/posts");
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
            req.getServletContext().getRequestDispatcher("/WEB-INF/views/posts.jsp").forward(req, resp);
        }
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
            return;
        }

        String textForPost = req.getParameter("post");

        if ( "".equals(textForPost)  || textForPost == null){
            req.setAttribute("message","write at least one symbol!");
        }else {

            try {
                User user = (User)session.getAttribute("user_a");
                Post post = new Post(textForPost,user.getId());
                post.setUserName(user.getEmail());
                PostRepository.addPost(post);
                req.setAttribute("message","Post was successfully added)");
                resp.sendRedirect("/posts");
                return;
            } catch (SQLException e) {
                req.setAttribute("message","Sorry, we can't publish your post(");
                e.printStackTrace();
            }
        }
        req.getServletContext().getRequestDispatcher("/WEB-INF/views/posts.jsp").forward(req,resp);
    }
}
