package ru.kpfu.itis.repositories;

import ru.kpfu.itis.entities.User;
import ru.kpfu.itis.exceptions.DatabaseException;
import ru.kpfu.itis.exceptions.NotValidEmailException;
import ru.kpfu.itis.exceptions.NotValidPasswordException;
import ru.kpfu.itis.exceptions.SecurityException;
import ru.kpfu.itis.utilities.Database;
import ru.kpfu.itis.utilities.SecurityService;

import javax.servlet.http.Cookie;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class UserRepository {

    /**
     * Method adding user to DB
     * @param user - entity to add to DB
     */

    public static void addUser(User user) throws DatabaseException, NotValidEmailException,
            NotValidPasswordException, SecurityException, SQLException {

        checkUserEmail(user.getEmail()); //checking an email
        checkUserPassword(user.getPassword()); //checking a password

        //меняем пароль на hashcode (password+salt)
        String safety[] = SecurityService.hash(user.getPassword());
        user.setPassword(safety[0]);
        user.setSalt(safety[1]);

        StringBuilder s = new StringBuilder("insert into users(email,password,salt,sex,subscription,about,remember)")
        .append(" values (?,?,?,?,?,?,?);");

        PreparedStatement p = Database.getConnection().prepareStatement(s.toString());
        p.setString(1,user.getEmail());
        p.setString(2,user.getPassword());
        p.setString(3,user.getSalt());
        p.setString(4, user.getSex());
        p.setString(5, user.getSubscription());
        p.setNString(6,user.getAbout());
        p.setString(7,user.getRemember());

        p.executeUpdate();
    }

    public static void deleteUser(User user) throws SQLException {
        Database.getConnection().createStatement()
                .executeUpdate("delete from users where id = " + user.getId());
    }


    public static User getUserByEmail(String email) throws SQLException {

        StringBuilder s = new StringBuilder("select * from users where email = ?;");
        PreparedStatement p = Database.getConnection().prepareStatement(s.toString());

        p.setString(1,email);

        ResultSet set = p.executeQuery();

        if (set.next()){
            int id      = set.getInt(1);
            String e     = set.getString(2);
            String pass  = set.getString(3);
            String salt  = set.getString(4);
            String sex   = set.getString(5);
            String subs  = set.getString(6);
            String about = set.getNString(7);
            String remem = set.getString(8);
            return new User(id,e,pass,salt,sex,subs,about,remem);
        }
        return null;
    }

    public static User getUserByCookie(Cookie cookie) throws SQLException {

        StringBuilder s = new StringBuilder("select * from users where remember = ").append("?;");
        PreparedStatement p = Database.getConnection().prepareStatement(s.toString());

        p.setString(1, cookie.getValue());

        ResultSet set = p.executeQuery();

        if (set.next()){
            int id       = set.getInt(1);
            String e     = set.getString(2);
            String pass  = set.getString(3);
            String salt  = set.getString(4);
            String sex   = set.getString(5);
            String subs  = set.getString(6);
            String about = set.getNString(7);
            String remem = set.getString(8);
            return new User(id,e,pass,salt,sex,subs,about,remem);
        }
        return null;
    }


    public static User getUserById(int id) throws SQLException {
        String s = "select * from users where `id` = ?;";
        PreparedStatement ps = Database.getConnection().prepareStatement(s);
        ps.setInt(1, id);

        ResultSet set = ps.executeQuery();

        if (set.next()){
            int cID      = set.getInt(1);
            String e     = set.getString(2);
            String p     = set.getString(3);
            String salt  = set.getString(4);
            String sex   = set.getString(5);
            String subs  = set.getString(6);
            String about = set.getNString(7);
            String remem = set.getString(8);
            return new User(cID,e,p,salt,sex,subs,about,remem);
        }
        return null;
    }

    public static void updateUser(User user) throws SQLException {
        String s = "update users set `sex` = ? , `about` = ? , `subscription` = ?  where `id` = "+user.getId()+";";
        PreparedStatement p = Database.getConnection().prepareStatement(s);
        p.setString(1,user.getSex());
        p.setNString(2,user.getAbout());
        p.setString(3,user.getSubscription());

        p.executeUpdate();
    }


    public static void updateUserCookie(User user, Cookie cookie) throws SQLException {

        String s = "update users set `remember` = ? where `id` = ? ;";
        PreparedStatement p = Database.getConnection().prepareStatement(s);

        p.setString(1,cookie.getValue());
        p.setInt(2,user.getId());

        p.executeUpdate();

    }



    private static void checkUserEmail(String email) throws NotValidEmailException {
        Pattern pattern = Pattern.compile("^([a-zA-Z0-9_\\.\\+-]{1,63})" +
                "@([a-zA-Z0-9-]+\\.[a-zA-Z]{2,})$");
        if (!pattern.matcher(email).matches()){
            throw new NotValidEmailException("Email is not valid!");
        }
    }


    private static void checkUserPassword(String password) throws NotValidPasswordException {
        if (password.length() < 4 || password.length() > 24){
            throw new NotValidPasswordException("Password is not valid: it must contain from 4 to 24 symbols");
        }
        byte letters = 0;
        byte digits = 0;
        for (char ch : password.toCharArray()){
            if (Character.isDigit(ch)){
                digits++;
                continue;
            }
            letters++;
        }
        if (digits == 0 || letters == 0){
            throw new NotValidPasswordException("password is not valid: it must contain letters and digits");
        }
    }

}
