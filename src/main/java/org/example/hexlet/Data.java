package org.example.hexlet;

import org.example.hexlet.model.Course;
import org.example.hexlet.model.User;
import org.example.hexlet.repository.CourseRepository;
import org.example.hexlet.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class Data {
    public static void createCourses() throws Exception {
        Course course1 = new Course("java", "Изучите основы java вместе с Хекслет");
        CourseRepository.save(course1);

        Course course2 = new Course("php", "Станьте профессионалом php за пол года");
        CourseRepository.save(course2);

        Course course3 = new Course("js", "js всего за один месяц, и вы - лучший");
        CourseRepository.save(course3);
    }

    public static void createUsers() throws Exception {
        List<User> users = new ArrayList<>();

        User user1 = new User("Ivan Ivanov", "ivan@mail.ru", "123");
        UserRepository.save(user1);

        User user2 = new User("Petr Petrov", "petr@mail.ru", "456");
        UserRepository.save(user2);

        User user3 = new User("Sidr Sidorov", "sidr@mail.ru", "789");
        UserRepository.save(user3);
    }
}
