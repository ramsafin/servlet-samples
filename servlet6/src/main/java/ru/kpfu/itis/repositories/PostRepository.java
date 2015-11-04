package ru.kpfu.itis.repositories;

import ru.kpfu.itis.entities.Post;
import ru.kpfu.itis.entities.User;
import ru.kpfu.itis.utilities.Database;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class PostRepository {


    public static void addPost(Post post) throws SQLException {

        StringBuilder s = new StringBuilder("insert into posts(text,date,user_id)")
        .append("values(?, ?, ?);");

        PreparedStatement p = Database.getConnection().prepareStatement(s.toString());

        p.setNString(1, post.getText());

        p.setString(2, post.getPublishedTime());
        p.setInt(3,post.getUser());

        p.executeUpdate();
    }


    //в разработке
    public static void removePost(Post post) throws SQLException {

        String s = "delete from posts where id = ?";
        PreparedStatement p = Database.getConnection().prepareStatement(s);

        p.setInt(1,post.getId());

        p.executeUpdate();
    }


    public static List<Post> getAllPosts() throws SQLException {

        ResultSet set = Database.getConnection().createStatement()
                .executeQuery("select * from posts");

        List<Post> posts = new LinkedList<>();
        while (set.next()){
            int id      = set.getInt(1);
            String text = set.getNString(2);
            Date date   = set.getDate(3);
            Time time   = set.getTime(3);
            int user_id = set.getInt(4);

            Post post = new Post(id,text,date.toString()+"  " + time.toString(),user_id);
            User user = UserRepository.getUserById(user_id);
            if (user != null){
                post.setUserName(user.getEmail());

                posts.add(post);
            }
        }
        return posts;
    }

}
