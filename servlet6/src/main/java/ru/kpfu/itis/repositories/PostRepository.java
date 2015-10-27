package ru.kpfu.itis.repositories;

import ru.kpfu.itis.entities.Post;
import ru.kpfu.itis.utilities.Database;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.LinkedList;
import java.util.List;

public class PostRepository {


    public static void addPost(Post post) throws SQLException {

        StringBuilder query = new StringBuilder("");
        query.append("insert into posts(text,date,user_id) values ").
                append("('").append(post.getText()).append("', '").append(post.getPublishedTime())
                .append("',").append(post.getUser()).append(");");

        Database.getInstance().insert(query.toString());
    }



    public static List<Post> getAllPosts() throws SQLException {
        ResultSet set = Database.getInstance()
                .query("select * from posts");
        List<Post> posts = new LinkedList<>();
        while (set.next()){
            int id      = set.getInt(1);
            String text = set.getNString(2);
            Date date   = set.getDate(3);
            Time time   = set.getTime(3);
            int user_id = set.getInt(4);
            Post post = new Post(id,text,date.toString()+"  " + time.toString(),user_id);
            post.setUserName(UserRepository.getUserById(user_id).getEmail());
            posts.add(post);
        }
        return posts;
    }

}
