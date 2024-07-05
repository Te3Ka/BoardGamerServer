package ru.te3ka.bgd.boardgamerdiaryserver.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Сущность, представляющая игру, в которую пользователь хочет сыграть.
 *
 * Этот класс используется для хранения информации о настольной игре, которую пользователь
 * хотел бы добавить в свой список желаний сыграть. Он содержит связь с профилем пользователя,
 * который указал эту игру в своем списке.
 */
@Getter
@Setter
@Entity
@Table(name = "want_to_play")
public class WantToPlay {
    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "profile_contact_phone")
    @JsonBackReference
    private Profile profile;
}
