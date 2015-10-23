package ru.kpfu.itis.utilities;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import ru.kpfu.itis.exceptions.DatabaseException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Database {

    private final static String extension = "db";
    private final static String path = "database/";

    private static void checkDb(String db) throws DatabaseException {
        File f = new File(getDbFullPath(db));
        if (!f.exists() || !f.isFile() || !f.canRead() || !f.canWrite()) {
            throw new DatabaseException("Database is not founded");
        }
    }

    private static String getDbFullPath(String db) {
        String fullName = db + "." + extension;
        return path + fullName;
    }

    public static void addEntry(String db, String[] data) throws DatabaseException {
        checkDb(db);
        try (CSVWriter writer = new CSVWriter(new FileWriter(getDbFullPath(db),true))) {
            writer.writeNext(data);
        } catch (IOException e) {
            throw new DatabaseException("can't add entry in database");
        }
    }

    public static List<String[]> getAllEntries(String db) throws DatabaseException {
        checkDb(db);
        try(CSVReader reader = new CSVReader(new FileReader(getDbFullPath(db)))){
            return reader.readAll();
        } catch (IOException ex) {
            throw new DatabaseException("can't get all entries from database");
        }
    }
}