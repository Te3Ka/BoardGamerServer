package ru.te3ka.bgd.boardgamerdiaryserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.te3ka.bgd.boardgamerdiaryserver.model.WantToPlay;
import ru.te3ka.bgd.boardgamerdiaryserver.repository.WantToPlayRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/upload/want_to_play")
public class WantToPlayController {
    @Autowired
    private WantToPlayRepository myWantToPlayRepository;
    @Autowired
    private WantToPlayRepository wantToPlayRepository;

    @GetMapping
    public List<WantToPlay> getAllWantToPlays() {
        return myWantToPlayRepository.findAll();
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<WantToPlay> getWantToPlayById(@PathVariable("id") String id) {
//        Optional<WantToPlay> myWantToPlay = myWantToPlayRepository.findByProfileContactPhone(id);
//        return myWantToPlay.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//    }

    @GetMapping("/{contactPhone}")
    public ResponseEntity<List<WantToPlay>> getWantToPlayByContactPhone(@PathVariable("contactPhone") String contactPhone) {
        List<WantToPlay> wantToPlayList = wantToPlayRepository.findWantToPlayByContactPhone(contactPhone);
        if (wantToPlayList.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(wantToPlayList);
    }

    @PostMapping("/")
    public WantToPlay createWantToPlay(@RequestBody WantToPlay wantToPlay) {
        return myWantToPlayRepository.save(wantToPlay);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WantToPlay> updateWantToPlay(@PathVariable("id") Integer id, @RequestBody WantToPlay wantToPlayDetails) {
        Optional<WantToPlay> optionalWantToPlay = myWantToPlayRepository.findById(id);
        if (optionalWantToPlay.isPresent()) {
            WantToPlay wantToPlay = optionalWantToPlay.get();
            wantToPlay.setName(wantToPlayDetails.getName());
            WantToPlay updatedWantToPlay = myWantToPlayRepository.save(wantToPlay);
            return ResponseEntity.ok(updatedWantToPlay);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWantToPlay(@PathVariable("id") Integer id) {
        Optional<WantToPlay> optionalWantToPlay = myWantToPlayRepository.findById(id);
        if (optionalWantToPlay.isPresent()) {
            myWantToPlayRepository.delete(optionalWantToPlay.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

