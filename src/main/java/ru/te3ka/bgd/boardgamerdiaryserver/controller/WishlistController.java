package ru.te3ka.bgd.boardgamerdiaryserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import ru.te3ka.bgd.boardgamerdiaryserver.model.Wishlist;
import ru.te3ka.bgd.boardgamerdiaryserver.repository.WishlistRepository;

import java.util.List;
import java.util.Optional;

@RestController
@Service
@RequestMapping("/upload/wishlist")
public class WishlistController {
    @Autowired
    private WishlistRepository wishlistRepository;

    @GetMapping
    public List<Wishlist> getAllWishlists() {
        return wishlistRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Wishlist> getWishlistById(@PathVariable("id") Integer id) {
        Optional<Wishlist> wishlist = wishlistRepository.findById(id);
        return wishlist.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    public Wishlist createWishlist(@RequestBody Wishlist wishlist) {
        return wishlistRepository.save(wishlist);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Wishlist> updateWishlist(@PathVariable("id") Integer id, @RequestBody Wishlist wishlistDetails) {
        Optional<Wishlist> optionalWishlist = wishlistRepository.findById(id);
        if (optionalWishlist.isPresent()) {
            Wishlist wishlist = optionalWishlist.get();
            wishlist.setName(wishlistDetails.getName());
            Wishlist updatedWishlist = wishlistRepository.save(wishlist);
            return ResponseEntity.ok(updatedWishlist);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWishlist(@PathVariable("id") Integer id) {
        Optional<Wishlist> optionalWishlist = wishlistRepository.findById(id);
        if (optionalWishlist.isPresent()) {
            wishlistRepository.delete(optionalWishlist.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

