package com.peaksoft.dao;



import com.peaksoft.model.User;
import com.peaksoft.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdbcImpl implements UserDao {

    public UserDaoJdbcImpl() {

    }

    public void createUsersTable() {
        String SQL = "CREATE TABLE IF NOT EXISTS users(" +
                "id SERIAL PRIMARY KEY," +
                "name VARCHAR(55), " +
                "lastName VARCHAR(45)," +
                "age INT)";

        try (Connection connection = Util.connection();
             Statement st = connection.createStatement()) {
            st.executeUpdate(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void dropUsersTable() {
        String SQL = "DROP TABLE IF EXISTS users";

        try (Connection connection = Util.connection();
             Statement st = connection.createStatement()) {

            st.executeUpdate(SQL);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        String SQL = "INSERT INTO users (name, lastName, age) VALUES(?, ?, ?)";

        try (Connection connection = Util.connection();
             PreparedStatement pst = connection.prepareStatement(SQL)) {

            pst.setString(1, name);
            pst.setString(2, lastName);
            pst.setByte(3, age);
            pst.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void removeUserById(long id) {
        String SQL = "DELETE FROM users WHERE id=?";

        try (Connection connection = Util.connection();
             PreparedStatement pst = connection.prepareStatement(SQL)) {

            pst.setLong(1, id);

            pst.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<User> getAllUsers() {
        String SQL = "SELECT * FROM users";
        List<User> users = new ArrayList<>();
        try (Connection connection = Util.connection();
             Statement st = connection.createStatement()) {

            ResultSet resultSet = st.executeQuery(SQL);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));

                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
        // return null;
    }

    public void cleanUsersTable() {
        String sql = "DELETE FROM users";
        try (Connection connection = Util.connection();
             Statement stat = connection.createStatement()) {

            stat.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}