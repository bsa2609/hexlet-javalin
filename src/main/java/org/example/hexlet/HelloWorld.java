package org.example.hexlet;

import io.javalin.Javalin;
import io.javalin.http.NotFoundResponse;

/*config*/
public class HelloWorld {
    public static void main(String[] args) {
        // Создаем приложение
        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
        });
        // Описываем, что загрузится по адресу /
        app.get("/", ctx -> ctx.result("Hello World"));

        app.get("/users", ctx -> ctx.result("GET /users"));
        app.post("/users", ctx -> ctx.result("POST /users"));

        app.get("/hello", ctx -> {
            var name = ctx.queryParamAsClass("name", String.class).getOrDefault("World");
            var capitalizeName = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
            var response = String.format("Hello, %s!", capitalizeName);
            ctx.result(response);
        });

        app.get("/courses/{id}", ctx -> {
            ctx.result("Course ID: " + ctx.pathParam("id"));
        });

        app.get("/users/all", ctx -> ctx.result("It's GET request on ALL users"));
        app.get("/users/{id}", ctx -> {
            var id = ctx.pathParamAsClass("id", Long.class).getOrDefault(0L);

            if (id == 22L) {
                throw new NotFoundResponse("User with id = " + id + " not found");
            }

            ctx.result("User ID: " + id);
        });

        app.get("/users/{userId}/posts/{postId}", ctx -> {
            Long userId = 0L;

            try {
                userId = ctx.pathParamAsClass("userId", Long.class).getOrDefault(0L);
            } catch (Exception e) {
                throw new NotFoundResponse("User id = " + ctx.pathParam("userId") + " is not Long type");
            }

            if (userId == 22L) {
                throw new NotFoundResponse("User with id = " + userId + " not found");
            }

            ctx.result("User ID: " + userId + ". Post ID: " + ctx.pathParam("postId"));
        });

        app.start(7070); // Стартуем веб-сервер
    }
}
