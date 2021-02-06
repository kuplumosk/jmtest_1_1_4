package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {

    public static Connection getMySQLConnection() throws SQLException, ClassNotFoundException {

        String hostName = "localhost";
        String dbName = "jmtest";
        String userName = "root";
        String password = "root";

        Class.forName("com.mysql.jdbc.Driver");

        String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;

        return DriverManager.getConnection(connectionURL, userName, password);
    }

}
