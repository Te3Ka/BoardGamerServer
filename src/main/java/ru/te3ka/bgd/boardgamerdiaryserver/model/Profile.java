package ru.te3ka.bgd.boardgamerdiaryserver.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Сущность, представляющая профиль пользователя в системе.
 *
 * Этот класс хранит информацию о пользователе, такую как контактный телефон, имя, фамилия,
 * дата рождения и другие персональные данные. Также содержит связи с другими сущностями,
 * которые представляют связанные коллекции данных пользователя.
 */
@Getter
@Setter
@Entity
@Table(name = "profiles")
public class Profile {
    @Id
    @Column(name = "contact_phone")
    private String contactPhone;

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


    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Contact> contactList;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<MyCollection> myCollectionList;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<WantToPlay> wantToPlayList;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Wishlist> wishlistList;
}
