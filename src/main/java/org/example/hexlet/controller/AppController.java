package org.example.hexlet.controller;

import io.javalin.http.Context;
import org.example.hexlet.dto.MainPage;

import static io.javalin.rendering.template.TemplateUtil.model;

public class AppController {
    public static void index(Context ctx) {
        var visited = Boolean.valueOf(ctx.cookie("visited"));
        String currentUser = ctx.sessionAttribute("currentUser");

        var page = new MainPage(visited, currentUser);
        page.setCurrentPage("Main");
        ctx.render("index.jte", model("page", page));
        ctx.cookie("visited", String.valueOf(true));
    }

    public static void hello(Context ctx) {
        var name = ctx.queryParamAsClass("name", String.class).getOrDefault("World");
        var capitalizeName = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
        var response = String.format("Hello, %s!", capitalizeName);
        ctx.result(response);
    }
}
