package ru.te3ka.bgd.boardgamerdiaryserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.te3ka.bgd.boardgamerdiaryserver.model.ContactOneSignal;

import java.util.Optional;

/**
 * Репозиторий для работы с сущностью {@link ContactOneSignal}.
 *
 * Этот интерфейс расширяет {@link JpaRepository}, предоставляя базовые CRUD операции для работы с
 * таблицей в базе данных, которая хранит информацию о контактах и их идентификаторах OneSignal.
 * Также предоставляет метод для поиска контактов по номеру телефона.
 */
public interface ContactOneSignalRepository extends JpaRepository<ContactOneSignal, Long> {
    /**
     * Находит контакт по номеру телефона.
     *
     * @param phoneNumber Номер телефона контакта.
     * @return Опционально содержащий контакт {@link ContactOneSignal} с указанным номером телефона.
     */
    Optional<ContactOneSignal> findByPhoneNumber(String phoneNumber);
}
