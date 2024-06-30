package ru.te3ka.bgd.boardgamerdiaryserver.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "contacts")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String phone;
    private String nickname;
    private String firstName;
    private String surname;

    @ManyToOne
    @JoinColumn(name = "profile_contact_phone")
    private Profile profile;
}
