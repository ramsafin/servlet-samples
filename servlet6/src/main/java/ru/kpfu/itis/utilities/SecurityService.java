package ru.kpfu.itis.utilities;

import org.apache.commons.codec.binary.Hex;
import ru.kpfu.itis.exceptions.SecurityException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class SecurityService {

    private static final int HASH_SIZE = 24;
    private static final int SALT_SIZE = 24;

    //return hash and salt
    public static String[] hash(String password) throws SecurityException {
        SecureRandom sr = new SecureRandom();
        byte[] byteSalt = new byte[SALT_SIZE];
        sr.nextBytes(byteSalt);
        String salt = toHex(byteSalt);
        String hash = sha256(password,salt);
        return new String[] {hash,salt};
    }

    /**
     * This method checking password of user
     * @param password - user having entered password
     * @param hash - real hash of correct password+salt
     * @param salt - real salt
     * @return true - if password is correct, else false
     * @throws SecurityException
     */
    public static boolean validate(String password,String hash,String salt) throws SecurityException {
        String currentHash = sha256(password,salt);
        return hash.equals(currentHash);
    }

    /**
     * @param size
     * @return random hash
     */
    public static String genRndHash(int size){
        byte [] bytes = new byte[size];
        SecureRandom sr = new SecureRandom();
        sr.nextBytes(bytes);
        return toHex(bytes);
    }


    /**
     * This method returns hash of password + salt
     * @param password
     * @param salt
     * @return hex hash code of password + salt
     * @throws SecurityException
     */
    private static String sha256(String password, String salt) throws SecurityException {
        try {

            MessageDigest ms = MessageDigest.getInstance("SHA-256");

            ms.reset();

            //salt+hash
            ms.update((password+salt).getBytes());
            byte [] hash = new byte[HASH_SIZE+SALT_SIZE];
            hash = ms.digest(hash);

            return toHex(hash);

        } catch (NoSuchAlgorithmException e) {
            throw new SecurityException();
        }
    }

    private static String toHex(byte [] bytes){

       return Hex.encodeHexString(bytes);
    }
}
