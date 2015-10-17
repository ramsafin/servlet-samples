package ru.kpfu.itis.repository;

import ru.kpfu.itis.Utilities.Database;
import ru.kpfu.itis.entities.User;
import ru.kpfu.itis.exceptions.DatabaseException;
import ru.kpfu.itis.exceptions.DuplicateEntryException;
import ru.kpfu.itis.exceptions.NotValidEmailException;
import ru.kpfu.itis.exceptions.NotValidPasswordException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class UserRepository{

    private static String repo = "users";

    private static void checkForDuplicates(User user) throws DuplicateEntryException, DatabaseException {
        for(User cUser : getUsers()){
            if (cUser.getEmail().equalsIgnoreCase(user.getEmail())){
                throw new DuplicateEntryException("This user already exists!");
            }
        }
    }

    public static void addUser(User user) throws DatabaseException, DuplicateEntryException,
            NotValidEmailException, NotValidPasswordException {
        if (user != null){
            checkEmail(user.getEmail());
            checkPassword(user.getPassword());
            checkForDuplicates(user);

            Database.addEntry(repo,new String[]{
                    user.getEmail(),
                    user.getPassword(),
                    user.getSex(),
                    user.getCheckbox(),
                    trimString(user.getAbout())}
            );
        }
    }

    public static List<User> getUsers() throws DatabaseException {
        List<String[]> entries = Database.getAllEntries(repo);
        List<User> users = new ArrayList<>();
        for (String[] entry : entries){
            User user = new User(entry[0],entry[1],entry[2],entry[3],reversTrimString(entry[4]));
            users.add(user);
        }
        return users;
    }

    private static String reversTrimString(String trimedAbout){
        StringBuilder sb = new StringBuilder("");
        for (char ch : trimedAbout.toCharArray()){
            if (ch == '\u02E8'){
                sb.append('\n');
            }else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }

    private static String trimString(String about){
        StringBuilder aboutTrim = new StringBuilder("");
        char [] aboutReal = about.toCharArray();
        for (int i = 0; i < about.length(); i++){
            if (aboutReal[i] == '\n'){
                aboutTrim.append('\u02E8');
            }else {
                aboutTrim.append(aboutReal[i]);
            }
        }
        return aboutTrim.toString();
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
            throw new NotValidPasswordException("Password is not valid: it must contain 4 to 24 symbols");
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
