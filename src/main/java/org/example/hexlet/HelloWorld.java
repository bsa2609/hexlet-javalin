package org.example.hexlet;

import io.javalin.Javalin;
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

        app.start(7070); // Стартуем веб-сервер
    }
}
