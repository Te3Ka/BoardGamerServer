package ru.te3ka.bgd.boardgamerdiaryserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.te3ka.bgd.boardgamerdiaryserver.model.WantToPlay;
import ru.te3ka.bgd.boardgamerdiaryserver.repository.WantToPlayRepository;

import java.util.List;
import java.util.Optional;


/**
 * Контроллер для обработки запросов, связанных с играми, в которые хочется сыграть.
 *
 * Этот контроллер управляет созданием, обновлением, удалением и получением информации о играх, в которые хочется сыграть.
 */
@RestController
@RequestMapping("/upload/want_to_play")
public class WantToPlayController {
    @Autowired
    private WantToPlayRepository myWantToPlayRepository;
    @Autowired
    private WantToPlayRepository wantToPlayRepository;

    /**
     * Получает список всех записей об играх, в которые хочется сыграть.
     *
     * @return Список всех записей об играх, в которые хочется сыграть.
     */
    @GetMapping
    public List<WantToPlay> getAllWantToPlays() {
        return myWantToPlayRepository.findAll();
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<WantToPlay> getWantToPlayById(@PathVariable("id") String id) {
//        Optional<WantToPlay> myWantToPlay = myWantToPlayRepository.findByProfileContactPhone(id);
//        return myWantToPlay.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//    }

    /**
     * Получает список игр в которые хочет сыграть пользователь по номеру телефона контакта.
     *
     * @param contactPhone Номер телефона контакта, для получения записей об играх, в которые хочется сыграть.
     * @return Список игр в которые хочет сыграть пользователь, соответствующих указанному номеру телефона, или статус "не найдено", если список пуст.
     */
    @GetMapping("/{contactPhone}")
    public ResponseEntity<List<WantToPlay>> getWantToPlayByContactPhone(@PathVariable("contactPhone") String contactPhone) {
        List<WantToPlay> wantToPlayList = wantToPlayRepository.findWantToPlayByContactPhone(contactPhone);
        if (wantToPlayList.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(wantToPlayList);
    }

    /**
     * Создает новую запись об игре, в которую хочет сыграть пользователь.
     *
     * @param wantToPlay Объект игры, в которую хочет сыграть пользователь, который нужно создать.
     * @return Созданный объект игры, в которую хочет сыграть пользователь.
     */
    @PostMapping("/")
    public WantToPlay createWantToPlay(@RequestBody WantToPlay wantToPlay) {
        return myWantToPlayRepository.save(wantToPlay);
    }

    /**
     * Обновляет существующую запись об игре, в которую хочет сыграть пользователь.
     *
     * @param id Идентификатор записи об игре, в которую хочет сыграть пользователь, которую нужно обновить.
     * @param wantToPlayDetails Обновленные детали об игре, в которую хочет сыграть пользователь.
     * @return Обновленная запись об игре, в которую хочет сыграть пользователь или статус "не найдено", если запись не существует.
     */
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

    /**
     * Удаляет запись об игре, в которую хочет сыграть пользователь по идентификатору.
     *
     * @param id Идентификатор записи об игре, в которую хочет сыграть пользователь, которую нужно удалить.
     * @return Статус выполнения операции (успешно или не найдено).
     */
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