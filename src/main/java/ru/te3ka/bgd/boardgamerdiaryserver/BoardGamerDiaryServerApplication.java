package ru.te3ka.bgd.boardgamerdiaryserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Основной класс приложения Spring Boot для сервера Board Gamer Diary.
 *
 * Этот класс содержит метод {@code main}, который запускает приложение Spring Boot.
 */
@SpringBootApplication
public class BoardGamerDiaryServerApplication {

    /**
     * Точка входа в приложение.
     *
     * @param args Аргументы командной строки.
     */
    public static void main(String[] args) {
        SpringApplication.run(BoardGamerDiaryServerApplication.class, args);
    }
}
