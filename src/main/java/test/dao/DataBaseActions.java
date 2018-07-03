package test.dao;

import java.sql.*;
import java.util.Random;

public class DataBaseActions {

    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    private static String URL = "jdbc:mysql://lt80glfe2gj8p5n2.chr7pe7iynqr.eu-west-1.rds.amazonaws.com:3306/hytxwnavkj1qz6g4";
    private static String USER_NAME = "tt62008f7lbsry1x";
    private static String USER_PASSWORD = "kueqt32c8snffxff";

    public void connect(){
        try {
            connection = DriverManager.getConnection(URL, USER_NAME, USER_PASSWORD);
            System.out.println("Data Base is connected!");
        } catch (SQLException e) {
            System.out.println("Error connecting to Data Base");
            //e.printStackTrace();
        }
    }

    public void createTable() throws SQLException {
        statement = connection.createStatement();
    }

    public void insertInto() throws SQLException {
        for (int i = 0; i < 20; i++){
            Random rnd = new Random();
            int z = rnd.nextInt(400) + 2000;
            statement.executeUpdate("INSERT into test_table (sallary, id) value(" + z + "," + 5 + ");");
        }
    }

    public void getUser() throws SQLException{

        String name = "Ruslan";
        String password = "admin";

        resultSet = statement.executeQuery("SELECT status FROM users WHERE user_name='" + name + "' AND password='" + password + "';");
        while (resultSet.next()){
            //System.out.println(resultSet.getInt("id"));
            //System.out.println(resultSet.getString("user_name"));
            //System.out.println(resultSet.getString("password"));
            System.out.println(resultSet.getString("status"));
        }

        resultSet = statement.executeQuery("SELECT AVG(test_table.sallary) FROM test_table");
        String i = "";
        while (resultSet.next()){
            System.out.println("Check " + resultSet.getString(1));
            i = resultSet.getString(1);
        }

        resultSet = statement.executeQuery("SELECT sallary, id FROM test_table WHERE sallary >= " + i + " ORDER BY sallary;");
        while (resultSet.next()){
            System.out.println("Check 2 " + resultSet.getString("sallary"));
            System.out.println(resultSet.getString("id"));
            break;
        }

        resultSet = statement.executeQuery("SELECT user_name, status FROM users WHERE user_name LIKE '%usl%' ORDER BY id");
        while (resultSet.next()){
            System.out.println("User name: " + resultSet.getString("user_name"));
            System.out.println("User status: " + resultSet.getString("status"));
        }

        resultSet = statement.executeQuery("SELECT sallary, id FROM test_table\n" +
                "CROSS JOIN\n" +
                "(\n" +
                "    SELECT SUM(sallary)/COUNT(sallary) AS aver FROM test_table\n" +
                ") average\n" +
                " WHERE sallary >= average.aver ORDER BY sallary");
        while (resultSet.next()){
            System.out.println("sallary: " + resultSet.getString("sallary"));
            System.out.println("id: " + resultSet.getString("id"));
        }

    }

    public void close() throws SQLException {
        connection.close();
    }
}
