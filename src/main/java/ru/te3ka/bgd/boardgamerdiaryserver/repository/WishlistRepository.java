package ru.te3ka.bgd.boardgamerdiaryserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.te3ka.bgd.boardgamerdiaryserver.model.Wishlist;

import java.util.List;

/**
 * Репозиторий для работы с сущностью {@link Wishlist}.
 *
 * Этот интерфейс расширяет {@link JpaRepository} и предоставляет методы для работы с таблицей "wishlist" в базе данных.
 * Включает метод для поиска всех записей в "wishlist" по номеру телефона профиля.
 */
public interface WishlistRepository extends JpaRepository<Wishlist, Integer> {
    /**
     * Находит все объекты {@link Wishlist}, связанные с указанным номером телефона профиля.
     *
     * @param profileContactPhone Номер телефона профиля, по которому осуществляется поиск.
     * @return Список объектов {@link Wishlist}, связанных с указанным профилем.
     */
    @Query("SELECT w FROM Wishlist w WHERE w.profile.contactPhone =:profileContactPhone")
    List<Wishlist> findWishlistByContactPhone(@Param("profileContactPhone") String profileContactPhone);
}
