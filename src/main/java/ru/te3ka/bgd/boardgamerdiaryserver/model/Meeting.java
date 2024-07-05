package ru.te3ka.bgd.boardgamerdiaryserver.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Сущность, представляющая встречу для игры в настольные игры.
 *
 * Эта сущность содержит информацию о встрече, включая дату, место, список настольных игр и контакты участников.
 */
@Getter
@Setter
@Entity
@Table(name = "meeting")
public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String date;
    private String location;

    @ElementCollection
    private List<String> boardgames;

    @ElementCollection
    private List<String> contacts;
}
