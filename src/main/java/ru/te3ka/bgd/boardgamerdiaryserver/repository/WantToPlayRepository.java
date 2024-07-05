package ru.te3ka.bgd.boardgamerdiaryserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.te3ka.bgd.boardgamerdiaryserver.model.WantToPlay;

import java.util.List;

/**
 * Репозиторий для работы с сущностью {@link WantToPlay}.
 *
 * Этот интерфейс расширяет {@link JpaRepository} и предоставляет методы для работы с таблицей "want_to_play" в базе данных.
 * Включает метод для поиска всех записей в "want_to_play" по номеру телефона профиля.
 */
public interface WantToPlayRepository extends JpaRepository<WantToPlay, Integer> {
    /**
     * Находит все объекты {@link WantToPlay}, связанные с указанным номером телефона профиля.
     *
     * @param profileContactPhone Номер телефона профиля, по которому осуществляется поиск.
     * @return Список объектов {@link WantToPlay}, связанных с указанным профилем.
     */
    @Query("SELECT wtp FROM WantToPlay wtp WHERE wtp.profile.contactPhone =:profileContactPhone")
    List<WantToPlay> findWantToPlayByContactPhone(@Param("profileContactPhone") String profileContactPhone);
}
