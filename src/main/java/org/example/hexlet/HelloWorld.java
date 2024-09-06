package org.example.hexlet;

import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinJte;

import org.example.hexlet.controller.*;
import org.example.hexlet.repository.BaseRepository;
import org.example.hexlet.util.NamedRoutes;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class HelloWorld {
    public static void createStartData() throws Exception {
        Data.createCourses();
        Data.createUsers();
    }

    public static void createDataSource() {
        var hikariConfig = new HikariConfig();
        String value = System.getenv("JDBC_DATABASE_URL");
        if (value == null) {
            hikariConfig.setJdbcUrl("jdbc:h2:mem:hexlet_project;DB_CLOSE_DELAY=-1;");
        } else {
            String connectstring = value
                    .replace("${HOST}", System.getenv("HOST"))
                    .replace("${DB_PORT}", System.getenv("DB_PORT"))
                    .replace("${DATABASE}", System.getenv("DATABASE"))
                    .replace("${PASSWORD}", System.getenv("PASSWORD"))
                    .replace("${USERNAME}", System.getenv("USERNAME"));

            hikariConfig.setJdbcUrl(connectstring);
        }

        BaseRepository.dataSource = new HikariDataSource(hikariConfig);

    }

    public static void createDataTables() throws Exception {
        // Получаем путь до файла в src/main/resources
        var url = HelloWorld.class.getClassLoader().getResourceAsStream("schema.sql");
        var sql = new BufferedReader(new InputStreamReader(url))
                .lines().collect(Collectors.joining("\n"));

        // Получаем соединение, создаем стейтмент и выполняем запрос
        try (var connection = BaseRepository.dataSource.getConnection();
             var statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }

    public static Javalin getApp() throws Exception {
        createDataSource();
        createDataTables();
        createStartData();

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        app.get(NamedRoutes.rootPath(), AppController::index);
        app.get(NamedRoutes.helloPath(), AppController::hello);

        app.get(NamedRoutes.usersPath(), UsersController::index);
        app.get(NamedRoutes.buildUserPath(), UsersController::build);
        app.get(NamedRoutes.userPath("{id}"), UsersController::show);
        app.post(NamedRoutes.usersPath(), UsersController::create);

        app.get(NamedRoutes.coursesPath(), CoursesController::index);
        app.get(NamedRoutes.buildCoursePath(), CoursesController::build);
        app.get(NamedRoutes.coursePath("{id}"), CoursesController::show);
        app.post(NamedRoutes.coursesPath(), CoursesController::create);

        app.get(NamedRoutes.carsPath(), CarController::index);
        app.get(NamedRoutes.buildCarPath(), CarController::build);
        app.get(NamedRoutes.carPath("{id}"), CarController::show);
        app.post(NamedRoutes.carsPath(), CarController::create);

        app.get(NamedRoutes.buildSessionsPath(), SessionsController::build);
        app.post(NamedRoutes.sessionsPath(), SessionsController::create);
        app.delete(NamedRoutes.sessionsPath(), SessionsController::destroy);

        return app;
    }

    public static void main(String[] args) throws Exception {
        var app = getApp();
        app.start(7070);
    }
}
