package ru.te3ka.bgd.boardgamerdiaryserver.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Сущность, представляющая контакт.
 *
 * Эта сущность содержит информацию о контакте, такую как номер телефона, имя, фамилия и связь с профилем пользователя.
 */
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
    @JsonBackReference
    private Profile profile;

    @OneToMany
    @JsonManagedReference
    private List<Invitation> invitations;
}
