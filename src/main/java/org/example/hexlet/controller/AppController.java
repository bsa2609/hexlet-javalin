package org.example.hexlet.controller;

import io.javalin.http.Context;

public class AppController {
    public static void index(Context ctx) {
        ctx.render("index.jte");
    }

    public static void hello(Context ctx) {
        var name = ctx.queryParamAsClass("name", String.class).getOrDefault("World");
        var capitalizeName = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
        var response = String.format("Hello, %s!", capitalizeName);
        ctx.result(response);
    }
}
