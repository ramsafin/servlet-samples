package ru.kpfu.itis.utilities;

import java.sql.*;


public final class Database {

    private Connection conn;

    private static Database database;

    private Database() {
        String url= "jdbc:mysql://localhost:3306/";
        String dbName = "server_db";
        String userName = "root";
        String password = "armin";
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            this.conn = DriverManager.getConnection(url+dbName,userName,password);
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