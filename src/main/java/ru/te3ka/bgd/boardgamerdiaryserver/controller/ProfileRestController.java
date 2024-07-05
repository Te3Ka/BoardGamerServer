package ru.te3ka.bgd.boardgamerdiaryserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.te3ka.bgd.boardgamerdiaryserver.model.Profile;
import ru.te3ka.bgd.boardgamerdiaryserver.repository.ProfileRepository;

import java.util.List;
import java.util.Optional;

/**
 * Контроллер для обработки запросов, связанных с профилями пользователей.
 *
 * Этот контроллер управляет созданием, обновлением, удалением и получением информации о профилях пользователей.
 */
@RestController
@RequestMapping("/upload/profile")
public class ProfileRestController {
    @Autowired
    private ProfileRepository profileRepository;

    /**
     * Получает список всех профилей пользователей.
     *
     * @return Список всех профилей пользователей.
     */
    @GetMapping
    public List<Profile> getAllProfiles() {
        return profileRepository.findAll();
    }

    /**
     * Получает профиль пользователя по номеру телефона.
     *
     * @param contactPhone Номер телефона пользователя, для получения его профиля.
     * @return Профиль пользователя, соответствующий указанному номеру телефона, или статус "не найдено", если профиль отсутствует.
     */
    @GetMapping("/{contactPhone}")
    public ResponseEntity<Profile> getProfileById(@PathVariable("contactPhone") String contactPhone) {
        Optional<Profile> profile = profileRepository.findById(contactPhone);
        return profile.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Создает новый профиль пользователя.
     *
     * @param profile Объект профиля пользователя, который нужно создать.
     * @return Статус выполнения операции с сообщением об успешном создании профиля.
     */
    @PostMapping("/")
    public ResponseEntity<String> createProfile(@RequestBody Profile profile) {
        System.out.println("Получено сообщение");
        System.out.println(profile.getNickname());
        profileRepository.save(profile);
        return ResponseEntity.ok("Profile saved successfully");
    }

    /**
     * Обновляет существующий профиль пользователя.
     *
     * @param contactPhone Номер телефона пользователя, чей профиль нужно обновить.
     * @param profileDetails Обновленные детали профиля пользователя.
     * @return Обновленный профиль пользователя или статус "не найдено", если профиль не существует.
     */
    @PutMapping("/{contactPhone}")
    public ResponseEntity<Profile> updateProfile(@PathVariable("contactPhone") String contactPhone, @RequestBody Profile profileDetails) {
        Optional<Profile> optionalProfile = profileRepository.findById(contactPhone);
        if (optionalProfile.isPresent()) {
            Profile profile = optionalProfile.get();
            profile.setNickname(profileDetails.getNickname());
            profile.setFirstName(profileDetails.getFirstName());
            profile.setSurname(profileDetails.getSurname());
            profile.setCity(profileDetails.getCity());
            profile.setEmail(profileDetails.getEmail());
            profile.setHobbies(profileDetails.getHobbies());
            profile.setDayOfBirth(profileDetails.getDayOfBirth());
            profile.setMonthOfBirth(profileDetails.getMonthOfBirth());
            profile.setYearOfBirth(profileDetails.getYearOfBirth());
            profile.setPhotoPath(profileDetails.getPhotoPath());
            Profile updatedProfile = profileRepository.save(profile);
            return ResponseEntity.ok(updatedProfile);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Удаляет профиль пользователя по номеру телефона.
     *
     * @param contactPhone Номер телефона пользователя, чей профиль нужно удалить.
     * @return Статус выполнения операции (успешно или не найдено).
     */
    @DeleteMapping("/{contactPhone}")
    public ResponseEntity<Void> deleteProfile(@PathVariable("contactPhone") String contactPhone) {
        Optional<Profile> optionalProfile = profileRepository.findById(contactPhone);
        if (optionalProfile.isPresent()) {
            profileRepository.delete(optionalProfile.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
