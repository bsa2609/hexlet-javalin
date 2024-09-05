package org.example.hexlet.repository;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.example.hexlet.model.Course;

public class CourseRepository extends BaseRepository {

    public static void save(Course course) throws SQLException {
        String sql = "INSERT INTO courses (name, description) VALUES (?, ?)";
        try (var conn = dataSource.getConnection();
             var preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, course.getName());
            preparedStatement.setString(2, course.getDescription());
            preparedStatement.executeUpdate();
            var generatedKeys = preparedStatement.getGeneratedKeys();
            // Устанавливаем ID в сохраненную сущность
            if (generatedKeys.next()) {
                course.setId(generatedKeys.getLong(1));
            } else {
                throw new SQLException("DB have not returned an id after saving an entity");
            }
        }
    }

    public static List<Course> search(String term) throws SQLException {
        var courses = new ArrayList<Course>();

        var sql = "SELECT * FROM courses WHERE name ILIKE ?";
        try (var conn = dataSource.getConnection();
             var stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + term + "%");
            var resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                var name = resultSet.getString("name");
                var description = resultSet.getString("description");
                var course = new Course(name, description);
                course.setId(Long.valueOf(resultSet.getString("id")));
                courses.add(course);
            }
        }

        return courses;
    }

    public static Optional<Course> find(Long id) throws SQLException {
        var sql = "SELECT * FROM courses WHERE id = ?";
        try (var conn = dataSource.getConnection();
             var stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            var resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                var name = resultSet.getString("name");
                var description = resultSet.getString("description");
                var course = new Course(name, description);
                course.setId(id);
                return Optional.of(course);
            }
            return Optional.empty();
        }

    }

    public static void delete(Long id) {

    }

    public static List<Course> getEntities() throws SQLException {
        var sql = "SELECT * FROM courses";
        try (var conn = dataSource.getConnection();
             var stmt = conn.prepareStatement(sql)) {
            var resultSet = stmt.executeQuery();
            var result = new ArrayList<Course>();
            while (resultSet.next()) {
                var id = resultSet.getLong("id");
                var name = resultSet.getString("name");
                var description = resultSet.getString("description");
                var course = new Course(name, description);
                course.setId(id);
                result.add(course);
            }
            return result;
        }
    }
}