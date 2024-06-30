package ru.te3ka.bgd.boardgamerdiaryserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.te3ka.bgd.boardgamerdiaryserver.model.Contact;
import ru.te3ka.bgd.boardgamerdiaryserver.repository.ContactRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/upload/contacts")
public class ContactController {
    @Autowired
    private ContactRepository contactRepository;

    @GetMapping
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contact> getContactById(@PathVariable("id") Integer id) {
        Optional<Contact> contact = contactRepository.findById(id);
        return contact.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    public Contact createContact(@RequestBody Contact contact) {
        return contactRepository.save(contact);
    }

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
