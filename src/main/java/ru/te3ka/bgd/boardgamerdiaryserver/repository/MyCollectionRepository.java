package ru.te3ka.bgd.boardgamerdiaryserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.te3ka.bgd.boardgamerdiaryserver.model.MyCollection;

import java.util.List;

/**
 * Репозиторий для работы с сущностью {@link MyCollection}.
 *
 * Этот интерфейс расширяет {@link JpaRepository}, предоставляя стандартные CRUD операции для работы с
 * таблицей коллекций в базе данных. Он также включает метод для поиска коллекций по номеру телефона профиля.
 */
public interface MyCollectionRepository extends JpaRepository<MyCollection, Integer> {
    /**
     * Находит все коллекции {@link MyCollection} для заданного номера телефона профиля.
     *
     * @param profileContactPhone Номер телефона профиля, по которому выполняется поиск коллекций.
     * @return Список коллекций {@link MyCollection}, соответствующих заданному номеру телефона профиля.
     */
    @Query("SELECT mc FROM MyCollection mc WHERE mc.profile.contactPhone =:profileContactPhone")
    List<MyCollection> findMyCollectionByContactPhone(@Param("profileContactPhone") String profileContactPhone);
}
