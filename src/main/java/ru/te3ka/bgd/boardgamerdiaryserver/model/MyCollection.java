package ru.te3ka.bgd.boardgamerdiaryserver.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Сущность, представляющая коллекцию настольных игр пользователя.
 *
 * Эта сущность хранит информацию о настольной игре, которая входит в коллекцию пользователя,
 * включая название игры, оценку, количество игр и дату покупки.
 */
@Getter
@Setter
@Entity
@Table(name = "my_collection")
public class MyCollection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String score;
    private String numberOfGames;
    private String yearOfPurchase;
    private String monthOfPurchase;

    @ManyToOne
    @JoinColumn(name = "profile_contact_phone")
    @JsonBackReference
    private Profile profile;
}
