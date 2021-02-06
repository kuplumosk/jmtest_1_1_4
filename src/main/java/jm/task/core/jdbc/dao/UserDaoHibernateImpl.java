package jm.task.core.jdbc.dao;

import java.util.List;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserDaoHibernateImpl implements UserDao {

    private Transaction transaction = null;

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        String createUsersTable = "CREATE TABLE IF NOT EXISTS `jmtest`.`user` ("
            + "  `id` INT NOT NULL AUTO_INCREMENT,"
            + "  `name` VARCHAR(45) NOT NULL,"
            + "  `last_name` VARCHAR(45) NULL,"
            + "  `age` INT(3) NULL,"
            + "  PRIMARY KEY (`id`));";

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();
            session.createSQLQuery(createUsersTable).executeUpdate();
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void dropUsersTable() {
        String dropUsersTable = "DROP TABLE IF EXISTS user";

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();
            session.createSQLQuery(dropUsersTable).executeUpdate();
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            User user = new User(name, lastName, age);
            session.save(user);

            transaction.commit();

            System.out.println("( User с именем " + name + " добавлен в базу данных )");

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }


    @Override
    public void removeUserById(long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            User user = session.get(User.class, id);
            session.delete(user);

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }

    }

    @Override
    public List<User> getAllUsers() {
        List<User> listOfUsers = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            listOfUsers = session.createQuery("FROM User")
                .getResultList();

            System.out.println(listOfUsers);

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return listOfUsers;
    }

    @Override
    public void cleanUsersTable() {
        String cleanUsersTable = "DELETE FROM user;";

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();
            session.createSQLQuery(cleanUsersTable).executeUpdate();
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}
