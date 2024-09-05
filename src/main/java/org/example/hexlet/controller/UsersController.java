package org.example.hexlet.controller;

import static io.javalin.rendering.template.TemplateUtil.model;

import io.javalin.validation.ValidationException;
import org.example.hexlet.dto.users.BuildUserPage;
import org.example.hexlet.util.NamedRoutes;
import org.example.hexlet.dto.users.UserPage;
import org.example.hexlet.dto.users.UsersPage;
import org.example.hexlet.model.User;
import org.example.hexlet.repository.UserRepository;

import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

public class UsersController {
    public static void index(Context ctx) throws Exception {
        var users = UserRepository.getEntities();
        var page = new UsersPage(users);
        page.setCurrentPage("Users");
        ctx.render("users/index.jte", model("page", page));
    }

    public static void show(Context ctx) throws Exception {
        final Long id;

        try {
            id = ctx.pathParamAsClass("id", Long.class).getOrDefault(0L);
        } catch (Exception e) {
            throw new NotFoundResponse("User id = " + ctx.pathParam("id") + " not Long type, user not found");
        }

        var user = UserRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("User id = " + id + " not found"));

        var page = new UserPage(user);
        ctx.render("users/show.jte", model("page", page));
    }

    public static void build(Context ctx) {
        var page = new BuildUserPage();
        ctx.render("users/build.jte", model("page", page));
    }

    public static void create(Context ctx) throws Exception {
        var name = ctx.formParam("name");
        var email = ctx.formParam("email");

        try {
            var passwordConfirmation = ctx.formParam("passwordConfirmation");
            var password = ctx.formParamAsClass("password", String.class)
                    .check(value -> value.equals(passwordConfirmation), "Пароли не совпадают")
                    .check(value -> value.length() > 6, "У пароля недостаточная длина")
                    .get();
            var user = new User(name, email, password);
            UserRepository.save(user);
            ctx.redirect(NamedRoutes.usersPath());

        } catch (ValidationException e) {
            var page = new BuildUserPage(name, email, e.getErrors());
            ctx.render("users/build.jte", model("page", page));
        }
    }
}
