package ru.te3ka.bgd.boardgamerdiaryserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.te3ka.bgd.boardgamerdiaryserver.model.ContactOneSignal;

import java.util.Optional;

public interface ContactOneSignalRepository extends JpaRepository<ContactOneSignal, Long> {
    Optional<ContactOneSignal> findByPhoneNumber(String phoneNumber);
}
