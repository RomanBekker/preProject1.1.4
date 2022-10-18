package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    //Создать таблицу пользователей
    public void createUsersTable() {
        Util util = new Util();
        try {
            PreparedStatement prepareStatement = util.getConnection().prepareStatement("create table if not exists table113 ( id bigint PRIMARY KEY AUTO_INCREMENT," +
                    " name varchar(45),  lastName varchar(45),  age int);");
            prepareStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Удалить таблицу пользователей
    public void dropUsersTable() {
        Util util = new Util();
        try {
            PreparedStatement prepareStatement = util.getConnection().prepareStatement("drop table if exists table113;");
            prepareStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Сохранить пользователя (Create)
    public void saveUser(String name, String lastName, byte age) {
        Util util = new Util();
        try {
            PreparedStatement prepareStatement = util.getConnection().prepareStatement("insert into table113 " +
                    "(name, lastName, age) values (?, ?, ?);");
            prepareStatement.setString(1, name);
            prepareStatement.setString(2, lastName);
            prepareStatement.setByte(3, age);
            prepareStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //Удалить пользователя по ID (Delete)
    public void removeUserById(long id) {
        Util util = new Util();
        try {
            Long x = id;
            PreparedStatement prepareStatement = util.getConnection().prepareStatement("delete from table113 where id = ?;");
            prepareStatement.setLong(1, id);
            prepareStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Получить всех пользователей (Read)
    public List<User> getAllUsers() {
        Util util = new Util();
        ArrayList<User> arr = new ArrayList<>();
        try {
            Statement statement = util.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("select * from table113;");
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge(resultSet.getByte(4));
                arr.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return arr;
    }

    //Очистить таблицу пользователей (Delete)
    public void cleanUsersTable() {
        Util util = new Util();
        try {
            Statement statement = util.getConnection().createStatement();
            statement.executeUpdate("delete from table113;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
