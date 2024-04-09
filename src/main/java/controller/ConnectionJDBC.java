package controller;

import java.sql.Connection;
import java.sql.DriverManager;

import static Constants.ConstantsJDBC.*;


public class ConnectionJDBC {
    //static Constants allConstant = new Constants();
    public static Connection getConnection() throws Exception {
        Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
        return connection;
    }

    /*public static int getLastId(){
        return
    }*/
}
