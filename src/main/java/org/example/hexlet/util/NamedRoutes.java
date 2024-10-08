package org.example.hexlet.util;

public class NamedRoutes {
    private static final String USERS_PATH = "/users";
    private static final String COURSES_PATH = "/courses";
    private static final String SESSION_PATH = "/sessions";
    private static final String CARS_PATH = "/cars";

    public static String rootPath() {
        return "/";
    }

    public static String helloPath() {
        return "/hello";
    }

    public static String usersPath() {
        return USERS_PATH;
    }

    public static String buildUserPath() {
        return USERS_PATH + "/build";
    }

    public static String userPath(Long id) {
        return userPath(String.valueOf(id));
    }

    public static String userPath(String id) {
        return USERS_PATH + "/" + id;
    }

    public static String coursesPath() {
        return COURSES_PATH;
    }

    public static String buildCoursePath() {
        return COURSES_PATH + "/build";
    }

    // Это нужно, чтобы не преобразовывать типы снаружи
    public static String coursePath(Long id) {
        return coursePath(String.valueOf(id));
    }

    public static String coursePath(String id) {
        return COURSES_PATH + "/" + id;
    }

    public static String sessionsPath() {
        return SESSION_PATH;
    }

    public static String buildSessionsPath() {
        return SESSION_PATH + "/build";
    }

    public static String carsPath() {
        return CARS_PATH;
    }

    public static String buildCarPath() {
        return CARS_PATH + "/build";
    }

    public static String carPath(Long id) {
        return carPath(String.valueOf(id));
    }

    public static String carPath(String id) {
        return CARS_PATH + "/" + id;
    }
}
