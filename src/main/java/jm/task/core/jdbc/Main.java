package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {

    public static void main(String[] args) {
        UserService HibernateManipulator = new UserServiceImpl();

        HibernateManipulator.createUsersTable();

        HibernateManipulator.saveUser("Timur", "Emelyanov", (byte) 55);
        HibernateManipulator.saveUser("Stas", "Glivenko", (byte) 29);
        HibernateManipulator.saveUser("Olga", "Zhabrovich", (byte) 35);
        HibernateManipulator.saveUser("Zhenya", "Karpluk", (byte) 40);

        HibernateManipulator.getAllUsers();

        HibernateManipulator.cleanUsersTable();

        HibernateManipulator.dropUsersTable();

    }

}

