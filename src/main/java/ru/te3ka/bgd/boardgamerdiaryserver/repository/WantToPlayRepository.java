package ru.te3ka.bgd.boardgamerdiaryserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.te3ka.bgd.boardgamerdiaryserver.model.WantToPlay;

public interface WantToPlayRepository extends JpaRepository<WantToPlay, Integer> {
}
