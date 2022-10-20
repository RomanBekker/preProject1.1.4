package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Connection connection = Util.getConnection();
    public UserDaoJDBCImpl() {

    }

    //Создать таблицу пользователей
    public void createUsersTable() {
        try (PreparedStatement prepareStatement = connection.prepareStatement("create table if not exists table113 ( id bigint PRIMARY KEY AUTO_INCREMENT," +
                " name varchar(45),  lastName varchar(45),  age int);")) {
            connection.setAutoCommit(false);
            prepareStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    //Удалить таблицу пользователей
    public void dropUsersTable() {
        try (PreparedStatement prepareStatement = connection.prepareStatement("drop table if exists table113;")) {
            connection.setAutoCommit(false);
            prepareStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    //Сохранить пользователя (Create)
    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement prepareStatement = connection.prepareStatement("insert into table113 " +
                "(name, lastName, age) values (?, ?, ?);")) {
            connection.setAutoCommit(false);
            prepareStatement.setString(1, name);
            prepareStatement.setString(2, lastName);
            prepareStatement.setByte(3, age);
            prepareStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }

    //Удалить пользователя по ID (Delete)
    public void removeUserById(long id) {
        try (PreparedStatement prepareStatement = connection.prepareStatement("delete from table113 where id = ?;")) {
            connection.setAutoCommit(false);
            prepareStatement.setLong(1, id);
            prepareStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    //Получить всех пользователей (Read)
    public List<User> getAllUsers() {
        ArrayList<User> arr = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            connection.setAutoCommit(false);
            ResultSet resultSet = statement.executeQuery("select * from table113;");
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge(resultSet.getByte(4));
                arr.add(user);
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return arr;
    }

    //Очистить таблицу пользователей (Delete)
    public void cleanUsersTable() {
        try (Statement statement = connection.createStatement()) {
            connection.setAutoCommit(false);
            statement.executeUpdate("delete from table113;");
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
