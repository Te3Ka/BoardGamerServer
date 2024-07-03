package ru.te3ka.bgd.boardgamerdiaryserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.te3ka.bgd.boardgamerdiaryserver.model.Invitation;

public interface InvitationRepository extends JpaRepository<Invitation, Integer> {
}
