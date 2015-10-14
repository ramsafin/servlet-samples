package ru.kpfu.itis.repo;

import ru.kpfu.itis.entities.User;
import ru.kpfu.itis.exceptions.NoSuchUserException;
import ru.kpfu.itis.exceptions.NotValidEmailException;
import ru.kpfu.itis.exceptions.NotValidPasswordException;
import ru.kpfu.itis.exceptions.UserExistsException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserRepository {

    private final static File DATA = new File("data.txt");

    public static void addUser(User user) throws UserExistsException, NotValidEmailException, NotValidPasswordException {
        if (user == null){
            throw new NullPointerException("user is null");
        }

        if (!checkPassword(user.getPassword())){
            throw new NotValidPasswordException("Password must be from 4 symbols to 16");
        }

        if (!checkEmail(user.getEmail())){
            throw new NotValidEmailException("Email is not valid");
        }

        if (isExist(user)){
            throw new UserExistsException("user already exists");
        }
        //add user to Database if no exception
        try(FileWriter fileWriter = new FileWriter(DATA,true)) {
            StringBuilder sb = new StringBuilder();
            sb.append(user.getEmail()).append(" ").append(user.getPassword()).append(" ");
            sb.append(user.getSex()).append(" ").append(user.getCheckboxStatus()).append(" ");
            fileWriter.append(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * @param email
     * @return user by his email
     * @throws ru.kpfu.itis.exceptions.NoSuchUserException if user doesn't exists
     */
    public static User getUser(String email) throws NoSuchUserException {
        if (email == null){
            throw new NullPointerException("email is null");
        }
        ArrayList<String> userInfo = new ArrayList<>(4);

        try(Scanner scanner = new Scanner(DATA)) {
            int i = 1;
            while (scanner.hasNext()){
                userInfo.add(email.toLowerCase());

                if (email.compareToIgnoreCase(scanner.next()) == 0){
                    while (scanner.hasNext() && i++<=3){
                        userInfo.add(scanner.next());
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return new User(userInfo.get(0),userInfo.get(1),userInfo.get(2),userInfo.get(3));
    }

    /**
     * @return ArrayList of users
     */
    public static ArrayList<User> getUsers(){

        ArrayList<User> users = new ArrayList<>();

        try(Scanner scanner = new Scanner(DATA)) {
            int i = 1;
            User currentUser = new User();
            while (scanner.hasNext()){

                switch (i++){
                    case 1 : currentUser.setEmail(scanner.next());
                        break;
                    case 2 : currentUser.setPassword(scanner.next());
                        break;
                    case 3 : currentUser.setSex(scanner.next());
                        break;
                    case 4 : currentUser.setCheckboxStatus(scanner.next());
                             users.add(currentUser);
                }
                if (i == 5){
                    currentUser = new User();
                    i = 1;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return users;
    }

    /**
     * @param email
     * @return true if email is valid, else false
     */
    private static boolean checkEmail(String email){
        Pattern pattern = Pattern.compile("^([a-zA-Z0-9_\\.\\+-]{1,63})@([a-zA-Z0-9-]+\\.[a-zA-Z]{2,})$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     * @param password
     * @return true if password valid, else false
     */
    private static boolean checkPassword(String password){
        return !(password == null || password.length() < 4 | password.length() > 16);
    }


    /**
     * @return true, if user exists and false, if doesn't
     */
    private static boolean isExist(User user){
        //checking Database for matches (only emails)
        try(Scanner scanner = new Scanner(DATA)) {
            int i = 1;
            while (scanner.hasNext()){
                String currentEmail = scanner.next();
                if (user.getEmail().equalsIgnoreCase(currentEmail)){
                    return true;
                }else {
                    while (scanner.hasNext() && i++ <= 3){
                        scanner.next();
                    }
                    i = 1;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

}
