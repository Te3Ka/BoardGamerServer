package ru.te3ka.bgd.boardgamerdiaryserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.te3ka.bgd.boardgamerdiaryserver.model.Contact;

import java.util.List;
import java.util.Optional;

public interface ContactRepository extends JpaRepository<Contact, Integer> {
    @Query("SELECT c FROM Contact c WHERE c.profile.contactPhone =:profileContactPhone")
    List<Contact> findContactsByContactPhone(@Param("profileContactPhone") String profileContactPhone);
}
