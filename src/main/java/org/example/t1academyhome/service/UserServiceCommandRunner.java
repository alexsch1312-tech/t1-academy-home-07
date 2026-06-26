package org.example.t1academyhome.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.t1academyhome.entity.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceCommandRunner implements CommandLineRunner {
    private final UserService userService;

    @Override
    public void run(String... args) throws Exception {
        log.info("!!!!Запуск сервиса!!!!");
/*
        System.out.println("\n--- Удаление всех пользователей ---");
        userService.deleteALL();

        System.out.println("\n--- Создание пользователей ---");
        User newUser1 = userService.createUser("Леша");
        System.out.println("Создан пользователь: " + newUser1);
        User newUser2 = userService.createUser("Саша");
        System.out.println("Создан пользователь: " + newUser2);
        User newUser3 = userService.createUser("Глория");
        System.out.println("Создан пользователь: " + newUser3);

        System.out.println("\n--- Поиск пользователя по ID ---");
        Optional<User> foundUser = userService.getUserById(newUser3.getId());
        foundUser.ifPresent(u -> System.out.println("Найдено: " + u));

        System.out.println("\n--- Поиск по имени (@Query) ===");
        userService.getUserByUsername("Саша").ifPresent(u -> System.out.println("Найдено: " + u));

        System.out.println("\n--- Обновление пользователя ---");
        userService.updateUser(newUser3.getId(), "Саманта");
        userService.getUserById(newUser3.getId()).ifPresent(u -> System.out.println("После обновления: " + u));

        System.out.println("\n--- Удаление пользователя ---");
        userService.deleteUser(newUser2.getId());

        System.out.println("\n--- Итоговый список пользователей ---");
        userService.getAllUsers().forEach(System.out::println);


 */
        log.info("!!!!Сервис завершен!!!!");
    }
}
