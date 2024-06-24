package ru.te3ka.bgd.boardgamerdiaryserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.te3ka.bgd.boardgamerdiaryserver.model.Wishlist;

public interface WishlistRepository extends JpaRepository<Wishlist, Integer> {
}
