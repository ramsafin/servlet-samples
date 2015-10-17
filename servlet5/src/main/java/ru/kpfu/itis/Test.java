package ru.kpfu.itis;

import ru.kpfu.itis.entities.SEX;
import ru.kpfu.itis.entities.User;
import ru.kpfu.itis.exceptions.DatabaseException;
import ru.kpfu.itis.repository.UserRepository;

public class Test {

    public static void main(String[] args) {
        User user = new User("ra2iL@mail.ru","pasww1", SEX.MALE);
        User user1 = new User("RaMil@mail.ru","pass2",SEX.FEMALE);
        User user2 = new User("RAMIL@mail.ru","pass21",SEX.MALE);
//
//        try{
//            UserRepository.addUser(user);
//            UserRepository.addUser(user1);
//        } catch (NotValidPasswordException e) {
//            e.printStackTrace();
//        } catch (UserDuplicateException e) {
//            e.printStackTrace();
//        } catch (NotValidEmailException e) {
//            e.printStackTrace();
//        } catch (DatabaseException e) {
//            e.printStackTrace();
//        }

        try{
            for (User u : UserRepository.getUsers()){
                System.out.println(u.toString());
            }
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
    }
}
