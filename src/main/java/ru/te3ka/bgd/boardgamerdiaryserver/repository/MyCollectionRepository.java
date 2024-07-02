package ru.te3ka.bgd.boardgamerdiaryserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.te3ka.bgd.boardgamerdiaryserver.model.MyCollection;

import java.util.List;
import java.util.Optional;

public interface MyCollectionRepository extends JpaRepository<MyCollection, Integer> {
    @Query("SELECT mc FROM MyCollection mc WHERE mc.profile.contactPhone =:profileContactPhone")
    List<MyCollection> findMyCollectionByContactPhone(@Param("profileContactPhone") String profileContactPhone);
}
