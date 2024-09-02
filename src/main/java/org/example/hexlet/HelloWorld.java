package org.example.hexlet;

import io.javalin.Javalin;
import io.javalin.http.NotFoundResponse;
import io.javalin.rendering.template.JavalinJte;

import static io.javalin.rendering.template.TemplateUtil.model;

import org.example.hexlet.dto.courses.CoursesPage;
import org.example.hexlet.model.Course;
import org.example.hexlet.dto.courses.CoursePage;
import java.util.ArrayList;
import java.util.List;

public class HelloWorld {
    public static List<Course> courses;

    public static void main(String[] args) {
        // Создаем приложение
        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        courses = getCourses();

        // Описываем, что загрузится по адресу /
        //app.get("/", ctx -> ctx.result("Hello World"));
        app.get("/", ctx -> ctx.render("index.jte"));

        app.get("/users", ctx -> ctx.result("GET /users"));
        app.post("/users", ctx -> ctx.result("POST /users"));

        app.get("/hello", ctx -> {
            var name = ctx.queryParamAsClass("name", String.class).getOrDefault("World");
            var capitalizeName = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
            var response = String.format("Hello, %s!", capitalizeName);
            ctx.result(response);
        });

        app.get("/courses", ctx -> {
            var header = "Курсы по программированию";
            var page = new CoursesPage(courses, header);
            ctx.render("courses/index.jte", model("page", page));
        });

        app.get("/courses/{id}", ctx -> {
            final Long id;

            try {
                id = ctx.pathParamAsClass("id", Long.class).getOrDefault(0L);
            } catch (Exception e) {
                throw new NotFoundResponse("Course id = " + ctx.pathParam("id") + " not Long type");
            }

            var course = courses.stream()
                    .filter(c -> c.getId() == id)
                    .findFirst()
                    .orElseThrow(() -> new NotFoundResponse("Course id = " + id + " not found"));

            var page = new CoursePage(course);
            ctx.render("courses/show.jte", model("page", page));
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

    public static List<Course> getCourses() {
        List<Course> courses = new ArrayList<>();

        Course course1 = new Course("java", "Изучите основы java вместе с Хекслет");
        course1.setId(1L);

        courses.add(course1);

        Course course2 = new Course("php", "Станьте профессионалом php за пол года");
        course2.setId(2L);

        courses.add(course2);

        Course course3 = new Course("js", "js всего за один месяц, и вы - лучший");
        course3.setId(2L);

        courses.add(course3);

        return courses;
    }
}
