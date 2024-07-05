package ru.te3ka.bgd.boardgamerdiaryserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.te3ka.bgd.boardgamerdiaryserver.model.Contact;

import java.util.List;

/**
 * Репозиторий для работы с сущностью {@link Contact}.
 *
 * Этот интерфейс расширяет {@link JpaRepository}, предоставляя базовые CRUD операции для работы с
 * таблицей контактов в базе данных. Дополнительно включает пользовательский запрос для поиска контактов
 * по номеру телефона профиля.
 */
public interface ContactRepository extends JpaRepository<Contact, Integer> {
    /**
     * Находит список контактов, связанных с указанным номером телефона профиля.
     *
     * @param profileContactPhone Номер телефона профиля, с которым связаны искомые контакты.
     * @return Список контактов {@link Contact}, связанных с указанным номером телефона профиля.
     */
    @Query("SELECT c FROM Contact c WHERE c.profile.contactPhone =:profileContactPhone")
    List<Contact> findContactsByContactPhone(@Param("profileContactPhone") String profileContactPhone);
}
