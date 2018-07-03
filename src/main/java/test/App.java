package test;

import test.dao.DataBaseActions;

import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws SQLException {
        System.out.println( "Hello DataBase!" );
        DataBaseActions dataBaseActions = new DataBaseActions();

        dataBaseActions.connect();
        dataBaseActions.createTable();
        //dataBaseActions.insertInto();
        dataBaseActions.getUser();
        dataBaseActions.close();

    }
}
