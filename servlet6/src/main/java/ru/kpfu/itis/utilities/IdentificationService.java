package ru.kpfu.itis.utilities;

import ru.kpfu.itis.entities.User;
import ru.kpfu.itis.exceptions.IdentifyingException;
import ru.kpfu.itis.exceptions.SecurityException;
import ru.kpfu.itis.repositories.UserRepository;

import java.sql.SQLException;

public class IdentificationService {

    public static User identification(String email, String password) throws IdentifyingException,
            SecurityException, SQLException {

        User user = UserRepository.getUserByEmail(email);

        if (user != null && SecurityService.validate(password,user.getPassword(),user.getSalt())){
            return user;
        }

        throw new IdentifyingException("wrong email or password");
    }
}
