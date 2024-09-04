package org.example.hexlet;

import gg.jte.Content;
import io.javalin.Javalin;
import io.javalin.http.NotFoundResponse;
import io.javalin.rendering.template.JavalinJte;

import static io.javalin.rendering.template.TemplateUtil.model;

import io.javalin.validation.ValidationException;

import org.example.hexlet.dto.courses.BuildCoursePage;
import org.example.hexlet.dto.users.BuildUserPage;
import org.example.hexlet.dto.users.UserPage;
import org.example.hexlet.repository.CourseRepository;
import org.example.hexlet.repository.UserRepository;
import org.example.hexlet.util.NamedRoutes;
import org.owasp.html.HtmlPolicyBuilder;

import org.example.hexlet.dto.Page;
import org.example.hexlet.dto.courses.CoursesPage;
import org.example.hexlet.dto.users.UsersPage;
import org.example.hexlet.model.Course;
import org.example.hexlet.model.User;
import org.example.hexlet.dto.courses.CoursePage;
import org.owasp.html.PolicyFactory;

import java.util.List;

public class HelloWorld {
    public static void main(String[] args) {
        // Создаем приложение
        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        Data.createCourses();
        Data.createUsers();

        app.get("/", ctx -> ctx.render("index.jte"));

        app.get(NamedRoutes.usersPath(), ctx -> {
            var page = new UsersPage(UserRepository.getEntities());
            ctx.render("users/index.jte", model("usersPage", page));
        });

        app.post(NamedRoutes.usersPath(), ctx -> {
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
        });

        app.get(NamedRoutes.buildUserPath(), ctx -> {
            var page = new BuildUserPage();
            ctx.render("users/build.jte", model("page", page));
        });

        app.post(NamedRoutes.coursesPath(), ctx -> {
            try {
                var name = ctx.formParamAsClass("name", String.class)
                        .check(value -> value.length() > 2, "Название курса должно быть длиннее 2 символов")
                        .get()
                        .trim();

                var description = ctx.formParamAsClass("description", String.class)
                        .check(value -> value.length() > 10, "Описание курса должно быть длиннее 10 символов")
                        .get()
                        .trim();

                var course = new Course(name, description);
                CourseRepository.save(course);
                ctx.redirect(NamedRoutes.coursesPath());
            } catch (ValidationException e) {
                var name = ctx.formParam("name");
                var description = ctx.formParam("description");

                var page = new BuildCoursePage(name, description, e.getErrors());
                ctx.render("courses/build.jte", model("page", page));
            }
        });

        app.get(NamedRoutes.buildCoursePath(), ctx -> {
            var page = new BuildCoursePage();
            ctx.render("courses/build.jte", model("page", page));
        });

        app.get("/hello", ctx -> {
            var name = ctx.queryParamAsClass("name", String.class).getOrDefault("World");
            var capitalizeName = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
            var response = String.format("Hello, %s!", capitalizeName);
            ctx.result(response);
        });

        app.get(NamedRoutes.coursesPath(), ctx -> {
            var term = ctx.queryParam("term");
            var header = "Курсы по программированию";

            List<Course> filteredCourses;

            if (term == null || term.isBlank()) {
                filteredCourses = CourseRepository.getEntities();
            } else {
                filteredCourses = CourseRepository.search(term);
            }

            var page = new CoursesPage(filteredCourses, header, term);
            ctx.render("courses/index.jte", model("coursesPage", page));
        });

        app.get(NamedRoutes.coursePath("{id}"), ctx -> {
            final Long id;

            try {
                id = ctx.pathParamAsClass("id", Long.class).getOrDefault(0L);
            } catch (Exception e) {
                throw new NotFoundResponse("Course id = " + ctx.pathParam("id") + " not Long type");
            }

            var course = CourseRepository.getEntities().stream()
                    .filter(c -> c.getId().equals(id))
                    .findFirst()
                    .orElseThrow(() -> new NotFoundResponse("Course id = " + id + " not found"));

            var page = new CoursePage(course);
            ctx.render("courses/show.jte", model("page", page));
        });

        app.get(NamedRoutes.userPath("{id}"), ctx -> {
            final Long id;

            try {
                id = ctx.pathParamAsClass("id", Long.class).getOrDefault(0L);
            } catch (Exception e) {
                throw new NotFoundResponse("User id = " + ctx.pathParam("id") + " not Long type");
            }

            var user = UserRepository.getEntities().stream()
                    .filter(c -> c.getId().equals(id))
                    .findFirst()
                    .orElseThrow(() -> new NotFoundResponse("User id = " + id + " not found"));

            var page = new UserPage(user);
            ctx.render("users/show.jte", model("userPage", page));
        });

        /*
        app.get("/users/all", ctx -> ctx.result("It's GET request on ALL users"));
        app.get("/users/{id}", ctx -> {

            var id = ctx.pathParam("id");
            //var escapedId = StringEscapeUtils.escapeHtml4(id);

            PolicyFactory policy = new HtmlPolicyBuilder()
                    .allowElements("a")
                    .allowUrlProtocols("https")
                    .allowAttributes("href").onElements("a")
                    .requireRelNofollowOnLinks()
                    .toFactory();
            String safeHTML = policy.sanitize(id);

            ctx.contentType("text/html");
            ctx.result(safeHTML);
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
        */

        app.start(7070); // Стартуем веб-сервер
    }
}
