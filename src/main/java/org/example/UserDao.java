package org.example;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.Arrays;

public class UserDao {
    private static final String CREATE_USER_QUERY = "INSERT INTO workshop2.users(username, email, password) VALUES (?, ?, ?)";
    private static final String DELETE_QUERY = "DELETE FROM workshop2.users where id = ?";
    private static final String READ_QUERY="SELECT * FROM  users where id=?";
    public static final String UPDATE_USER_QUERY= "UPDATE workshop2.users SET username = ?, email = ?, password = ? WHERE id = ?;";
    public static final String READ_ALL_QUERY="select  * from workshop2.users;";

    public String hashPassword(String password) {

        return BCrypt.hashpw(password, BCrypt.gensalt());

    }
    public User create(User user) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement =
                    conn.prepareStatement(CREATE_USER_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getEmail());
            statement.setString(3, hashPassword(user.getPassword()));
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public User read(int userId) {
        try (Connection conn = DbUtil.getConnection();) {
            PreparedStatement statement = conn.prepareStatement(READ_QUERY);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = new User()  ;
                user.setId(resultSet.getInt("id"));
                user.setUserName(resultSet.getString("userName"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User update(User user) {
        try (Connection conn = DbUtil.getConnection()) {
                PreparedStatement statement =
                        conn.prepareStatement(UPDATE_USER_QUERY, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, user.getUserName());
                statement.setString(2, user.getEmail());
                statement.setString(3, hashPassword(user.getPassword()));
                statement.setInt(4, user.getId());
                statement.executeUpdate();
                ResultSet resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    user.setId(resultSet.getInt(1));
                }
                return user;
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
    }
    public void delete(int userId) {
        try (Connection conn=DbUtil.getConnection(); PreparedStatement statement =
                         conn.prepareStatement(DELETE_QUERY);) {
                statement.setInt(1, userId);
                statement.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }

    }
    public User[] findAll(){

        User[] users = new User[0];
        try (Connection conn = DbUtil.getConnection();) {
            PreparedStatement statement = conn.prepareStatement(READ_ALL_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUserName(resultSet.getString("userName"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                users = addToArray(user, users);
            }
                return users;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    private User[] addToArray(User u, User[] users) {
        User[] tmpUsers = Arrays.copyOf(users, users.length + 1);
        tmpUsers[users.length] = u;
        return tmpUsers;
    }

    }

