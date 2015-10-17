package ru.kpfu.itis.repository;

import ru.kpfu.itis.entities.SEX;
import ru.kpfu.itis.entities.User;
import ru.kpfu.itis.exceptions.DatabaseException;
import ru.kpfu.itis.exceptions.NotValidEmailException;
import ru.kpfu.itis.exceptions.NotValidPasswordException;
import ru.kpfu.itis.exceptions.UserDuplicateException;
import ru.kpfu.itis.utilities.Database;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class UserRepository{

    private static String repo = "users";

    private static void checkForDuplicates(User user) throws UserDuplicateException, DatabaseException {
        if (getUsers().contains(user)){
            throw new UserDuplicateException("user already exists");
        }
    }

    public static void addUser(User user) throws DatabaseException, UserDuplicateException,
            NotValidEmailException, NotValidPasswordException {
        if (user != null){
            checkEmail(user.getEmail());
            checkPassword(user.getPassword());
            checkForDuplicates(user);

            Database.addEntry(repo, new String[]{user.getEmail(),
                    user.getPassword(), user.getSex().toString(), user.getSubscription(), user.getAbout()});
        }
    }

    public static List<User> getUsers() throws DatabaseException {
        List<String[]> entries = Database.getAllEntries(repo);
        List<User> users = new ArrayList<>();
        for (String[] entry : entries){
                                //email  password  sex
            User user = new User(entry[0],entry[1],entry[2].equals("male")? SEX.MALE:SEX.FEMALE,
                    //subs   about
                    entry[3],entry[4]);

            users.add(user);
        }
        return users;
    }

    private static void checkEmail(String email) throws NotValidEmailException {
        Pattern pattern = Pattern.compile("^([a-zA-Z0-9_\\.\\+-]{1,63})@([a-zA-Z0-9-]+\\.[a-zA-Z]{2,})$");
        if (!pattern.matcher(email).matches()){
            throw new NotValidEmailException("email is not valid");
        }
    }


    private static void checkPassword(String password) throws NotValidPasswordException, NotValidEmailException {
        if (password.length() < 4 || password.length() > 24){
            throw new NotValidPasswordException("Password is not valid: it must contain 4 to 24 symbols");
        }
        byte digits = 0;
        byte letters = 0;
        for (char ch : password.toCharArray()){
            if (Character.isDigit(ch)){
                digits++;
                continue;
            }
            letters++;
        }
        if (letters == 0 || digits == 0){
            throw new NotValidEmailException("password must contain letters and digits");
        }
    }
}