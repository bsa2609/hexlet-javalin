package org.example.hexlet.repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.example.hexlet.model.User;

public class UserRepository extends BaseRepository {

    public static void save(User user) throws SQLException {
        String sql =
                """
                INSERT INTO users_javalin (
                    name,
                    email,
                    password,
                    created_at
                )
                VALUES(?, ?, ?, ?);
                """;
        try (Connection conn = dataSource.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            preparedStatement.executeUpdate();
            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getLong(1));
            } else {
                throw new SQLException("DB have not returned an id after saving an entity");
            }
        }
    }

    public static List<User> search(String term) throws SQLException {
        List<User> users = new ArrayList<>();

        String sql =
                """
                SELECT
                    id,
                    name,
                    email,
                    password,
                    created_at
                FROM users_javalin
                WHERE
                    name ILIKE ?
                """;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setString(1, "%" + term + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User(
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("password")
                );

                user.setId(resultSet.getLong("id"));
                user.setCreatedAt(resultSet.getTimestamp("created_at").toLocalDateTime());

                users.add(user);
            }
        }

        return users;
    }

    public static Optional<User> find(Long id) throws SQLException {
        String sql =
                """
                SELECT
                    id,
                    name,
                    email,
                    password,
                    created_at
                FROM users_javalin
                WHERE
                    id = ?
                """;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = new User(
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("password")
                );

                user.setId(resultSet.getLong("id"));
                user.setCreatedAt(resultSet.getTimestamp("created_at").toLocalDateTime());

                return Optional.of(user);
            }
        }

        return Optional.empty();
   }

    public static void delete(Long id) {

    }

    public static List<User> getEntities() throws Exception {
        List<User> users = new ArrayList<>();

        String sql =
                """
                SELECT
                    id,
                    name,
                    email,
                    password,
                    created_at
                FROM users_javalin
                """;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User(
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("password")
                );

                user.setId(resultSet.getLong("id"));
                user.setCreatedAt(resultSet.getTimestamp("created_at").toLocalDateTime());

                users.add(user);
            }
        }

        return users;
    }
}