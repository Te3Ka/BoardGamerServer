package ru.te3ka.bgd.boardgamerdiaryserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.te3ka.bgd.boardgamerdiaryserver.model.MyCollection;
import ru.te3ka.bgd.boardgamerdiaryserver.repository.MyCollectionRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/upload/my_collection")
public class MyCollectionController {
    @Autowired
    private MyCollectionRepository myCollectionRepository;

    @GetMapping
    public List<MyCollection> getAllMyCollections() {
        return myCollectionRepository.findAll();
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<MyCollection> getMyCollectionById(@PathVariable("id") String id) {
//        Optional<MyCollection> myCollection = myCollectionRepository.findByProfileContactPhone(id);
//        return myCollection.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//    }

    @GetMapping("/{contactPhone}")
    public ResponseEntity<List<MyCollection>> getMyCollectionByContactPhone(@PathVariable("contactPhone") String contactPhone) {
        List<MyCollection> myCollectionList = myCollectionRepository.findMyCollectionByContactPhone(contactPhone);
        if (myCollectionList.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(myCollectionList);
    }

    @PostMapping("/")
    public MyCollection createMyCollection(@RequestBody MyCollection myCollection) {
        return myCollectionRepository.save(myCollection);
    }

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

