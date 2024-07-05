package ru.te3ka.bgd.boardgamerdiaryserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.te3ka.bgd.boardgamerdiaryserver.model.Profile;

/**
 * Репозиторий для работы с сущностью {@link Profile}.
 *
 * Этот интерфейс расширяет {@link JpaRepository}, предоставляя стандартные CRUD операции для работы с
 * таблицей профилей в базе данных. Ключевым идентификатором в данной сущности является номер телефона, который
 * используется в качестве первичного ключа (типа {@link String}).
 */
public interface ProfileRepository extends JpaRepository<Profile, String> {
}