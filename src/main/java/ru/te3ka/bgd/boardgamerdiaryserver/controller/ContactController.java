package ru.te3ka.bgd.boardgamerdiaryserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.te3ka.bgd.boardgamerdiaryserver.model.Contact;
import ru.te3ka.bgd.boardgamerdiaryserver.repository.ContactRepository;

import java.util.List;
import java.util.Optional;

/**
 * Контроллер для обработки запросов, связанных с контактами.
 *
 * Этот контроллер управляет CRUD-операциями для сущности Contact,
 * такими как создание, чтение, обновление и удаление контактов.
 */
@RestController
@RequestMapping("/upload/contacts")
public class ContactController {
    @Autowired
    private ContactRepository contactRepository;

    /**
     * Получает список всех контактов.
     *
     * @return Список всех контактов.
     */
    @GetMapping
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

//    /**
//     * Получает контакт по уникальному идентификатору.
//     *
//     * @param id Уникальный идентификатор контакта.
//     * @return Контакт с указанным идентификатором или ошибка 404, если контакт не найден.
//     */
//    @GetMapping("/{id}")
//    public ResponseEntity<Contact> getContactById(@PathVariable("id") String id) {
//        Optional<Contact> contact = contactRepository.findByProfileContactPhone(id);
//        return contact.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//    }

    /**
     * Получает список контактов по номеру телефона.
     *
     * @param contactPhone Номер телефона контакта.
     * @return Список контактов с указанным номером телефона или ошибка 404, если контакты не найдены.
     */
    @GetMapping("/{contactPhone}")
    public ResponseEntity<List<Contact>> getContactsByContactPhone(@PathVariable("contactPhone") String contactPhone) {
        List<Contact> contactList = contactRepository.findContactsByContactPhone(contactPhone);
        if (contactList.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(contactList);
    }

    /**
     * Создает новый контакт.
     *
     * @param contact Объект Contact для создания.
     * @return Созданный контакт.
     */
    @PostMapping("/")
    public Contact createContact(@RequestBody Contact contact) {
        return contactRepository.save(contact);
    }

    /**
     * Обновляет существующий контакт.
     *
     * @param id Идентификатор контакта для обновления.
     * @param contactDetails Объект Contact с новыми данными.
     * @return Обновленный контакт или ошибка 404, если контакт не найден.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Contact> updateContact(@PathVariable("id") Integer id, @RequestBody Contact contactDetails) {
        Optional<Contact> optionalContact = contactRepository.findById(id);
        if (optionalContact.isPresent()) {
            Contact contact = optionalContact.get();
            contact.setPhone(contactDetails.getPhone());
            contact.setNickname(contactDetails.getNickname());
            contact.setFirstName(contactDetails.getFirstName());
            contact.setSurname(contactDetails.getSurname());
            Contact updatedContact = contactRepository.save(contact);
            return ResponseEntity.ok(updatedContact);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Удаляет контакт по уникальному идентификатору.
     *
     * @param id Идентификатор контакта для удаления.
     * @return Статус выполнения операции: 200 OK, если контакт удален, или ошибка 404, если контакт не найден.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable("id") Integer id) {
        Optional<Contact> optionalContact = contactRepository.findById(id);
        if (optionalContact.isPresent()) {
            contactRepository.delete(optionalContact.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
