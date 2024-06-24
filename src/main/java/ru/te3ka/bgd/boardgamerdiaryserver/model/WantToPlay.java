package ru.te3ka.bgd.boardgamerdiaryserver.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
}
