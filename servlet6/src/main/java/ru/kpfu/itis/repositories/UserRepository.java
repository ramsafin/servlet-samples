package ru.kpfu.itis.repositories;

import ru.kpfu.itis.entities.User;
import ru.kpfu.itis.exceptions.*;
import ru.kpfu.itis.exceptions.SecurityException;
import ru.kpfu.itis.utilities.Database;
import ru.kpfu.itis.utilities.SecurityService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

public class UserRepository {


    public static void addUser(User user) throws DatabaseException, DuplicateEntryException,
            NotValidEmailException, NotValidPasswordException, SecurityException, SQLException {
        //проверяем сначала email и password
        //потому что не нужно образаться к БД
        checkEmail(user.getEmail());
        checkPassword(user.getPassword());
        checkForDuplicates(user);

        //меняем пароль на hashcode (password+salt)
        String safety[] = SecurityService.hash(user.getPassword());
        user.setPassword(safety[0]);
        user.setSalt(safety[1]);

        StringBuilder query = new StringBuilder("");
        query.append("insert into users(email,password,salt,sex,subscription,about,remember)");
        query.append(" values ('").append(user.getEmail()).append("','").append(user.getPassword()).append("',");
        query.append("'").append(user.getSalt()).append("','").append(user.getSex()).append("',");
        query.append("'").append(user.getSubscription()).append("','").append(user.getAbout());
        query.append("','").append(user.getRemember()).append("');");

        Database.getInstance().insert(query.toString());
    }


    public static List<User> getUsers() throws SQLException {
        List<User> users = new LinkedList<>();
        ResultSet set = Database.getInstance().query("select * from users;");

        while (set.next()){
            int id       = set.getInt(1);
            String e     = set.getString(2);
            String p     = set.getString(3);
            String salt  = set.getString(4);
            String sex   = set.getString(5);
            String subs  = set.getString(6);
            String about = set.getString(7);
            String remem = set.getString(8);
            users.add( new User(id,e,p,salt,sex,subs,about,remem) );
        }
        return users;
    }


    public static User getUserByEmail(String email) throws SQLException {
        ResultSet set = Database.getInstance()
                .query("select * from users where email ="+ "'"+email+"';");

        if (set.next()){
            int id       = set.getInt(1);
            String e     = set.getString(2);
            String p     = set.getString(3);
            String salt  = set.getString(4);
            String sex   = set.getString(5);
            String subs  = set.getString(6);
            String about = set.getString(7);
            String remem = set.getString(8);
            return new User(id,e,p,salt,sex,subs,about,remem);
        }
        return null;
    }


    public static User getUserByCookieValue(String value) throws SQLException {
        ResultSet set = Database.getInstance()
                .query("select * from users where remember ="+ "'"+value+"';");

        if (set.next()){
            int id       = set.getInt(1);
            String e     = set.getString(2);
            String p     = set.getString(3);
            String salt  = set.getString(4);
            String sex   = set.getString(5);
            String subs  = set.getString(6);
            String about = set.getString(7);
            String remem = set.getString(8);
            System.out.println("UserRepo returns user with cookie value = " + remem);
            return new User(id,e,p,salt,sex,subs,about,remem);
        }
        return null;
    }


    public static User getUserById(int id) throws SQLException {
        ResultSet set = Database.getInstance()
                .query("select * from users where id = " + "'" + id + "';");

        if (set.next()){
            int cID      = set.getInt(1);
            String e     = set.getString(2);
            String p     = set.getString(3);
            String salt  = set.getString(4);
            String sex   = set.getString(5);
            String subs  = set.getString(6);
            String about = set.getString(7);
            String remem = set.getString(8);
            return new User(cID,e,p,salt,sex,subs,about,remem);
        }
        return null;
    }


    public static void update(String param, String condition) throws SQLException {

        StringBuilder query = new StringBuilder("");
        query.append("update users");
        query.append(" set ").append(param).append(" = ").append("'")
                .append(SecurityService.genRndHash(12)).append("'");
        query.append(" where ").append(condition).append(";");
        Database.getInstance().update(query.toString());
    }


    private static void checkEmail(String email) throws NotValidEmailException {
        Pattern pattern = Pattern.compile("^([a-zA-Z0-9_\\.\\+-]{1,63})" +
                "@([a-zA-Z0-9-]+\\.[a-zA-Z]{2,})$");
        if (!pattern.matcher(email).matches()){
            throw new NotValidEmailException("Email is not valid!");
        }
    }


    private static void checkPassword(String password) throws NotValidPasswordException {
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

    private static void checkForDuplicates(User user) throws DuplicateEntryException,
            DatabaseException, SQLException {

        if (getUserByEmail(user.getEmail()) != null){
            throw new DuplicateEntryException("user already exists");
        }
    }
}
