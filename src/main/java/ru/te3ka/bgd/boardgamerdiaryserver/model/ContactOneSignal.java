package ru.te3ka.bgd.boardgamerdiaryserver.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "contact_one_signal")
public class ContactOneSignal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String phoneNumber;
    private String oneSignalToken;

    @OneToOne
    @JoinColumn(name = "profile_contact_phone", referencedColumnName = "contact_phone")
    private Profile profile;
}
