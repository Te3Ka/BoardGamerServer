package ru.te3ka.bgd.boardgamerdiaryserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.te3ka.bgd.boardgamerdiaryserver.model.MyCollection;
import ru.te3ka.bgd.boardgamerdiaryserver.repository.MyCollectionRepository;

import java.util.List;
import java.util.Optional;

/**
 * Контроллер для обработки запросов, связанных с коллекциями игр пользователя.
 *
 * Этот контроллер управляет созданием, обновлением, удалением и получением информации о коллекциях игр.
 */
@RestController
@RequestMapping("/upload/my_collection")
public class MyCollectionController {
    @Autowired
    private MyCollectionRepository myCollectionRepository;

    /**
     * Получает список всех коллекций игр.
     *
     * @return Список всех коллекций игр.
     */
    @GetMapping
    public List<MyCollection> getAllMyCollections() {
        return myCollectionRepository.findAll();
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<MyCollection> getMyCollectionById(@PathVariable("id") String id) {
//        Optional<MyCollection> myCollection = myCollectionRepository.findByProfileContactPhone(id);
//        return myCollection.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//    }

    /**
     * Получает коллекции игр по номеру телефона контакта.
     *
     * @param contactPhone Номер телефона контакта, по которому ищутся коллекции.
     * @return Список коллекций игр, соответствующих указанному номеру телефона, или статус "не найдено", если коллекции отсутствуют.
     */
    @GetMapping("/{contactPhone}")
    public ResponseEntity<List<MyCollection>> getMyCollectionByContactPhone(@PathVariable("contactPhone") String contactPhone) {
        List<MyCollection> myCollectionList = myCollectionRepository.findMyCollectionByContactPhone(contactPhone);
        if (myCollectionList.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(myCollectionList);
    }

    /**
     * Создает новую коллекцию игр.
     *
     * @param myCollection Объект коллекции игр, который нужно создать.
     * @return Созданная коллекция игр.
     */
    @PostMapping("/")
    public MyCollection createMyCollection(@RequestBody MyCollection myCollection) {
        return myCollectionRepository.save(myCollection);
    }

    /**
     * Обновляет существующую коллекцию игр.
     *
     * @param id ID коллекции игр, которую нужно обновить.
     * @param myCollectionDetails Обновленные детали коллекции игр.
     * @return Обновленная коллекция игр или статус "не найдено", если коллекция не существует.
     */
    @PutMapping("/{id}")
    public ResponseEntity<MyCollection> updateMyCollection(@PathVariable("id") Integer id, @RequestBody MyCollection myCollectionDetails) {
        Optional<MyCollection> optionalMyCollection = myCollectionRepository.findById(id);
        if (optionalMyCollection.isPresent()) {
            MyCollection myCollection = optionalMyCollection.get();
            myCollection.setName(myCollectionDetails.getName());
            myCollection.setScore(myCollectionDetails.getScore());
            myCollection.setNumberOfGames(myCollectionDetails.getNumberOfGames());
            myCollection.setMonthOfPurchase(myCollectionDetails.getMonthOfPurchase());
            myCollection.setYearOfPurchase(myCollectionDetails.getYearOfPurchase());
            MyCollection updatedMyCollection = myCollectionRepository.save(myCollection);
            return ResponseEntity.ok(updatedMyCollection);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Удаляет коллекцию игр по ее ID.
     *
     * @param id ID коллекции игр, которую нужно удалить.
     * @return Статус выполнения операции (успешно или не найдено).
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable("id") Integer id) {
        Optional<MyCollection> optionalMyCollection = myCollectionRepository.findById(id);
        if (optionalMyCollection.isPresent()) {
            myCollectionRepository.delete(optionalMyCollection.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

