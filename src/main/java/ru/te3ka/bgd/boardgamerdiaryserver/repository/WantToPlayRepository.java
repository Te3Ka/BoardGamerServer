package ru.te3ka.bgd.boardgamerdiaryserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.te3ka.bgd.boardgamerdiaryserver.model.WantToPlay;

import java.util.List;

public interface WantToPlayRepository extends JpaRepository<WantToPlay, Integer> {
    @Query("SELECT wtp FROM WantToPlay wtp WHERE wtp.profile.contactPhone =:profileContactPhone")
    List<WantToPlay> findWantToPlayByContactPhone(@Param("profileContactPhone") String profileContactPhone);
}
