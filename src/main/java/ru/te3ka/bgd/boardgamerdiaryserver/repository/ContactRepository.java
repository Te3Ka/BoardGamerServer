package ru.te3ka.bgd.boardgamerdiaryserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.te3ka.bgd.boardgamerdiaryserver.model.Contact;

public interface ContactRepository extends JpaRepository<Contact, Integer> {
}
