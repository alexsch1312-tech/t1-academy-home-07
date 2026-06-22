package org.example.t1academyhome05;

import org.example.t1academyhome05.entity.User;
import org.example.t1academyhome05.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

@SpringBootApplication
public class T1AcademyHome05Application {

    public static void main(String[] args) {
        SpringApplication.run(T1AcademyHome05Application.class, args);
    }

    @Bean
    public CommandLineRunner runDemo(UserService userService) {
        return args -> {
            System.out.println("\n=== 1. Проверка предустановленных миграцией тестовых данных ===");
            userService.getAllUsers().forEach(System.out::println);

            System.out.println("\n=== 2. Создание нового пользователя ===");
            User newUser = userService.createUser("David");
            System.out.println("Создан пользователь: " + newUser);

            System.out.println("\n=== 3. Поиск пользователя по ID ===");
            Optional<User> foundUser = userService.getUserById(newUser.getId());
            foundUser.ifPresent(u -> System.out.println("Найдено в бд: " + u));

            System.out.println("\n=== 4. Поиск с использованием кастомного @Query ===");
            userService.getUserByUsername("Alice").ifPresent(u -> System.out.println("Найден по JPQL: " + u));

            System.out.println("\n=== 5. Обновление пользователя ===");
            userService.updateUser(newUser.getId(), "David_Updated");
            userService.getUserById(newUser.getId()).ifPresent(u -> System.out.println("После обновления: " + u));

            System.out.println("\n=== 6. Удаление пользователя ===");
            userService.deleteUser(newUser.getId());
            System.out.println("Текущий список пользователей в БД:");
            userService.getAllUsers().forEach(System.out::println);
        };
    }

}
