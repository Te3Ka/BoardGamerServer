package ru.te3ka.bgd.boardgamerdiaryserver.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Сущность, представляющая приглашение на встречу.
 *
 * Эта сущность содержит информацию о приглашении, включая ссылку на встречу, контакт, который получил приглашение,
 * и статус приглашения.
 */
@Getter
@Setter
@Entity
@Table(name = "invitation")
public class Invitation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "meeting_id")
    @JsonBackReference
    private Meeting meeting;

    @ManyToOne
    @JoinColumn(name = "contact_id")
    @JsonBackReference
    private Contact contact;

    private String status;
}
