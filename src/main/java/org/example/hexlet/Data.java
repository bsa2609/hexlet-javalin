package org.example.hexlet;

import org.example.hexlet.model.Course;
import org.example.hexlet.model.User;

import java.util.ArrayList;
import java.util.List;

public class Data {
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

    public static List<User> getUsers() {
        List<User> users = new ArrayList<>();

        User user1 = new User("Ivan Ivanov", "ivan@mail.ru", "123");
        user1.setId(1L);

        users.add(user1);

        User user2 = new User("Petr Petrov", "petr@mail.ru", "456");
        user2.setId(2L);

        users.add(user2);

        User user3 = new User("Sidr Sidorov", "sidr@mail.ru", "789");
        user3.setId(2L);

        users.add(user3);

        return users;
    }
}
