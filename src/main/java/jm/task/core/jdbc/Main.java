package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {

    public static void main(String[] args) {
        UserServiceImpl JDBCManipulator = new UserServiceImpl();

        JDBCManipulator.createUsersTable();

        JDBCManipulator.saveUser("Timur", "Emelyanov", (byte) 55);
        JDBCManipulator.saveUser("Stas", "Glivenko", (byte) 29);
        JDBCManipulator.saveUser("Olga", "Zhabrovich", (byte) 35);
        JDBCManipulator.saveUser("Zhenya", "Karpluk", (byte) 40);

        JDBCManipulator.getAllUsers();

        JDBCManipulator.cleanUsersTable();

        JDBCManipulator.dropUsersTable();

    }
}

