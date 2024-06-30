package ru.te3ka.bgd.boardgamerdiaryserver.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
    private Profile profile;
}
