package ru.te3ka.bgd.boardgamerdiaryserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.te3ka.bgd.boardgamerdiaryserver.model.Invitation;

/**
 * Репозиторий для работы с сущностью {@link Invitation}.
 *
 * Этот интерфейс расширяет {@link JpaRepository}, предоставляя стандартные CRUD операции для работы с
 * таблицей приглашений в базе данных. Он не содержит дополнительных методов, поскольку CRUD операции
 * покрываются базовым функционалом {@link JpaRepository}.
 */
public interface InvitationRepository extends JpaRepository<Invitation, Integer> {
}
