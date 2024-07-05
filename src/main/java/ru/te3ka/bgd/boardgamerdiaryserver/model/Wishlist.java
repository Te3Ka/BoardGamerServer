package ru.te3ka.bgd.boardgamerdiaryserver.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Сущность, представляющая настольную игру, который пользователь хотел бы получить в будущем.
 *
 * Этот класс используется для хранения информации об играх, которые пользователь
 * добавил в свой список желаемых к покупке. Он содержит связь с профилем пользователя,
 * который указал эту игру в своем списке желаемого.
 */
@Getter
@Setter
@Entity
@Table(name = "wishlist")
public class Wishlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "profile_contact_phone")
    @JsonBackReference
    private Profile profile;
}
