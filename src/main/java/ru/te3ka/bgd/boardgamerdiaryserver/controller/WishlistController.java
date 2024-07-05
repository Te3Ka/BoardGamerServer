package ru.te3ka.bgd.boardgamerdiaryserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.te3ka.bgd.boardgamerdiaryserver.model.Wishlist;
import ru.te3ka.bgd.boardgamerdiaryserver.repository.WishlistRepository;

import java.util.List;
import java.util.Optional;

/**
 * Контроллер для обработки запросов, связанных с играми в списке желаемого.
 *
 * Этот контроллер управляет созданием, обновлением, удалением и получением информации об играх в списке желаемого.
 */
@RestController
@RequestMapping("/upload/wishlist")
public class WishlistController {
    @Autowired
    private WishlistRepository wishlistRepository;

    /**
     * Получает список всех записей в списке желаемого.
     *
     * @return Список всех записей в списке желаемого.
     */
    @GetMapping
    public List<Wishlist> getAllWishlists() {
        return wishlistRepository.findAll();
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<Wishlist> getWishlistById(@PathVariable("id") String id) {
//        Optional<Wishlist> wishlist = wishlistRepository.findByProfileContactPhone(id);
//        return wishlist.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//    }

    /**
     * Получает список записей в списке желаемого по номеру телефона контакта.
     *
     * @param contactPhone Номер телефона контакта, для получения записей в списке желаемого.
     * @return Список записей в списке желаемого, соответствующих указанному номеру телефона, или статус "не найдено", если список пуст.
     */
    @GetMapping("/{contactPhone}")
    public ResponseEntity<List<Wishlist>> getWishlistByContactPhone(@PathVariable("contactPhone") String contactPhone) {
        List<Wishlist> wishlists = wishlistRepository.findWishlistByContactPhone(contactPhone);
        if (wishlists.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(wishlists);
    }

    /**
     * Создает новую запись в списке желаемого.
     *
     * @param wishlist Объект желания в списке желаемого, который нужно создать.
     * @return Созданный объект желания в списке желаемого.
     */
    @PostMapping("/")
    public Wishlist createWishlist(@RequestBody Wishlist wishlist) {
        return wishlistRepository.save(wishlist);
    }

    /**
     * Обновляет существующую запись в списке желаемого.
     *
     * @param id Идентификатор записи в списке желаемого, которую нужно обновить.
     * @param wishlistDetails Обновленные детали записи в списке желаемого.
     * @return Обновленная запись в списке желаемого или статус "не найдено", если запись не существует.
     */
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

    /**
     * Удаляет запись в списке желаемого по идентификатору.
     *
     * @param id Идентификатор записи в списке желаемого, которую нужно удалить.
     * @return Статус выполнения операции (успешно или не найдено).
     */
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

