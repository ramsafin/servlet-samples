package ru.kpfu.itis.utilities;

import ru.kpfu.itis.entities.User;
import ru.kpfu.itis.exceptions.DatabaseException;
import ru.kpfu.itis.exceptions.IdentifyingException;
import ru.kpfu.itis.repositories.UserRepository;

public class UserService {

    public static User identification(String email, String password) throws DatabaseException, IdentifyingException {

        for (User user : UserRepository.getUsers()){
            if (user.getEmail().equalsIgnoreCase(email) && user.getPassword().equals(password)){
                return user;
            }
        }

        throw new IdentifyingException("Wrong password or email");
    }
}
