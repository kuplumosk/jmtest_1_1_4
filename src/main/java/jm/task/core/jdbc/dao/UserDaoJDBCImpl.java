package jm.task.core.jdbc.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

public class UserDaoJDBCImpl implements UserDao {

    private Connection connection;
    {
        try {
            connection = Util.getMySQLConnection();
            connection.setAutoCommit(false);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String createUsersTable = "CREATE TABLE IF NOT EXISTS `jmtest`.`user` ("
            + "  `id` INT NOT NULL AUTO_INCREMENT,"
            + "  `name` VARCHAR(45) NOT NULL,"
            + "  `last_name` VARCHAR(45) NULL,"
            + "  `age` INT(3) NULL,"
            + "  PRIMARY KEY (`id`));";

        try {
            connection.prepareStatement(createUsersTable).executeUpdate();
            connection.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String dropUsersTable = "DROP TABLE IF EXISTS user";

        try {
            connection.prepareStatement(dropUsersTable).executeUpdate();
            connection.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String saveUser =
            "INSERT INTO user (name, last_name, age) VALUES (" + "'" + name + "', '" + lastName + "'," + age + ");";

        try {
            connection.prepareStatement(saveUser).executeUpdate();
            connection.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        System.out.println("( User с именем " + name + " добавлен в базу данных )");
    }

    public void removeUserById(long id) {
        String removeUserById = "DELETE FROM user WHERE id=" + id;

        try {
            connection.prepareStatement(removeUserById).executeUpdate();
            connection.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        String getAllUsers = "SELECT * FROM user;";
        List<User> listOfUsers = new ArrayList<>();

        try {
            ResultSet resultSet = connection.prepareStatement(getAllUsers).executeQuery(getAllUsers);

            while (resultSet.next()) {
                User user = new User();
                user.setId((long) resultSet.getInt(1));
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge((byte) resultSet.getInt(4));
                listOfUsers.add(user);
            }
            connection.commit();

            System.out.println(listOfUsers);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return listOfUsers;
    }

    public void cleanUsersTable() {
        String cleanUsersTable = "DELETE FROM user;";
        try {
            connection.prepareStatement(cleanUsersTable).executeUpdate();
            connection.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
