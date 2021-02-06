package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {

    public static void main(String[] args) {
        UserServiceImpl hibernateManipulator = new UserServiceImpl();

        User user1 = new User("Hi", "RazRaz", (byte) 25);
        User user2 = new User("Hi", "RazRaz", (byte) 25);
        User user3 = new User("Hi", "RazRaz", (byte) 25);
        User user4 = new User("Hi", "RazRaz", (byte) 25);
        hibernateManipulator.saveUser(user1.getName(), user1.getLastName(), user1.getAge());
        hibernateManipulator.saveUser(user2.getName(), user2.getLastName(), user2.getAge());
        hibernateManipulator.saveUser(user3.getName(), user3.getLastName(), user3.getAge());
        hibernateManipulator.saveUser(user4.getName(), user4.getLastName(), user4.getAge());


    }
}

