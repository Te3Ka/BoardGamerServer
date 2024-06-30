package ru.te3ka.bgd.boardgamerdiaryserver.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
    private Profile profile;
}
