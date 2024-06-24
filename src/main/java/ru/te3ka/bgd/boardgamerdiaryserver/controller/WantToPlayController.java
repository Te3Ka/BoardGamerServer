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

    @GetMapping
    public List<WantToPlay> getAllWantToPlays() {
        return myWantToPlayRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<WantToPlay> getWantToPlayById(@PathVariable("id") Integer id) {
        Optional<WantToPlay> myWantToPlay = myWantToPlayRepository.findById(id);
        if (myWantToPlay.isPresent()) {
            return ResponseEntity.ok(myWantToPlay.get());
        } else {
            return ResponseEntity.notFound().build();
        }
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

