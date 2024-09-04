package org.example.hexlet;

import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinJte;

import org.example.hexlet.controller.AppController;
import org.example.hexlet.controller.CoursesController;
import org.example.hexlet.controller.UsersController;
import org.example.hexlet.util.NamedRoutes;

public class HelloWorld {
    public static void main(String[] args) {
        // Создаем приложение
        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        Data.createCourses();
        Data.createUsers();

        app.get("/", AppController::index);
        app.get("/hello", AppController::hello);

        app.get(NamedRoutes.usersPath(), UsersController::index);
        app.get(NamedRoutes.userPath("{id}"), UsersController::show);
        app.get(NamedRoutes.buildUserPath(), UsersController::build);
        app.post(NamedRoutes.usersPath(), UsersController::create);

        app.get(NamedRoutes.coursesPath(), CoursesController::index);
        app.get(NamedRoutes.coursePath("{id}"), CoursesController::show);
        app.get(NamedRoutes.buildCoursePath(), CoursesController::build);
        app.post(NamedRoutes.coursesPath(), CoursesController::create);

        app.start(7070); // Стартуем веб-сервер
    }
}
