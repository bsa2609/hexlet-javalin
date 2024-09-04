package org.example.hexlet.controller;

import static io.javalin.rendering.template.TemplateUtil.model;

import io.javalin.validation.ValidationException;
import org.example.hexlet.dto.courses.BuildCoursePage;
import org.example.hexlet.model.Course;
import org.example.hexlet.repository.CarRepository;
import org.example.hexlet.repository.CourseRepository;
import org.example.hexlet.util.NamedRoutes;
import org.example.hexlet.dto.courses.CoursePage;
import org.example.hexlet.dto.courses.CoursesPage;

import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

import java.sql.SQLException;
import java.util.List;

public class CoursesController {
    public static void index(Context ctx) throws SQLException {
        var term = ctx.queryParam("term");
        var header = "Курсы по программированию";

        List<Course> filteredCourses;

        if (term == null || term.isBlank()) {
            filteredCourses = CourseRepository.getEntities();
        } else {
            filteredCourses = CourseRepository.search(term);
        }

        var page = new CoursesPage(filteredCourses, header, term);
        page.setFlash(ctx.consumeSessionAttribute("flashCourses"));
        page.setCurrentPage("Courses");
        ctx.render("courses/index.jte", model("page", page));
    }

    public static void show(Context ctx) throws SQLException {
        final Long id;

        try {
            id = ctx.pathParamAsClass("id", Long.class).getOrDefault(0L);
        } catch (Exception e) {
            throw new NotFoundResponse("Course id = " + ctx.pathParam("id") + " not Long type, course not found");
        }

        var course = CourseRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("Course with id = " + id + " not found"));

        var page = new CoursePage(course);
        ctx.render("courses/show.jte", model("page", page));
    }

    public static void build(Context ctx) {
        var page = new BuildCoursePage();
        ctx.render("courses/build.jte", model("page", page));
    }

    public static void create(Context ctx) throws SQLException {
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

            ctx.sessionAttribute("flashCourses", "Course has been created!");
            ctx.redirect(NamedRoutes.coursesPath());
        } catch (ValidationException e) {
            var name = ctx.formParam("name");
            var description = ctx.formParam("description");

            var page = new BuildCoursePage(name, description, e.getErrors());
            ctx.render("courses/build.jte", model("page", page));
        }
    }


}
