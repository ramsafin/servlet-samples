package ru.kpfu.itis.utilities;

import java.sql.*;


public final class Database {

    private Connection conn;
    private Statement statement;

    private static Database connection;

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

        if(connection == null) {
            synchronized (Database.class) {
                if(connection == null) {
                    connection = new Database();
                }
            }
        }
        return connection;
    }

    public ResultSet query(String query) throws SQLException{
        statement = connection.conn.createStatement();
        return statement.executeQuery(query);
    }

    public int insert(String insertQuery) throws SQLException {
        statement = connection.conn.createStatement();
        return statement.executeUpdate(insertQuery);
    }

    public int update(String updateQuery) throws SQLException{
        statement = connection.conn.createStatement();
        return statement.executeUpdate(updateQuery);
    }

}