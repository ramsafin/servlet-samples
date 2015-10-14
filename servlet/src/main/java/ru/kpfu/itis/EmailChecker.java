package ru.kpfu.itis;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class EmailChecker {

    private File file;
    private String email;

    public EmailChecker(File file, String currEmail) {
        this.file = file;
        email = currEmail;
    }

    public boolean check() {
        for (String str : getEmails()) {
            if (email.compareToIgnoreCase(str) == 0) {
                return false;
            }
        }
        return true;
    }

    private ArrayList<String> getWords() {
        ArrayList<String> words = new ArrayList<>();

        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNext()) {
                words.add(sc.next());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return words;
    }

    private ArrayList<String> getEmails() {
        ArrayList<String> emails = new ArrayList<>();
        ArrayList<String> words = getWords();
        for (int i = 0; i < words.size(); i += 4) {
            emails.add(words.get(i).trim());
        }
        return emails;
    }
}
