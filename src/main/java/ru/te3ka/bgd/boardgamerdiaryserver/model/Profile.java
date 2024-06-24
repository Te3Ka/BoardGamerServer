package ru.te3ka.bgd.boardgamerdiaryserver.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "profiles")
public class Profile {
    @Id
    @Column(name = "contact_phone")
    private String contactPhone;

    @Column(name = "contact_id", nullable = true)
    private Integer contactId;

    @Column(name = "my_collection_id", nullable = true)
    private Integer myCollectionId;

    @Column(name = "wishlist_id", nullable = true)
    private Integer wishlistId;

    @Column(name = "want_to_play_id", nullable = true)
    private Integer wantToPlayId;

    private String nickname;
    private String firstName;
    private String surname;
    private String city;
    private String email;
    private String hobbies;
    private int dayOfBirth;
    private int monthOfBirth;
    private int yearOfBirth;
    private String photoPath;
}
