package ru.kpfu.itis.Utilities;

import ru.kpfu.itis.entities.User;
import ru.kpfu.itis.exceptions.DatabaseException;
import ru.kpfu.itis.exceptions.IdentifyException;
import ru.kpfu.itis.repository.UserRepository;

public class UserService {

    public static User identification(String email, String password) throws DatabaseException, IdentifyException {

        for (User user : UserRepository.getUsers()){
           if (user.getEmail().equalsIgnoreCase(email) && user.getPassword().equals(password)){
               return user;
           }
        }

        throw new IdentifyException("Wrong password or email");
    }
}
