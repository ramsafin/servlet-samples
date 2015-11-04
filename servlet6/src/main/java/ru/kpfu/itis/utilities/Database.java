package ru.kpfu.itis.utilities;

import java.sql.*;



public final class Database {

    private Connection conn;
    private static Database database;

    private static final String URL           = "jdbc:mysql://localhost:3306/";
    private static final String DATABASE_NAME = "server_db";
    private static final String USER_NAME     = "root";
    private static final String PASSWORD      = "armin";


    private Database() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            this.conn = DriverManager.getConnection(URL+DATABASE_NAME,USER_NAME,PASSWORD);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Database getInstance() {

        if(database == null) {
            synchronized (Database.class) {
                if(database == null) {
                    database = new Database();
                }
            }
        }
        return database;
    }

    public static Connection getConnection(){

        return Database.getInstance().conn;
    }

}