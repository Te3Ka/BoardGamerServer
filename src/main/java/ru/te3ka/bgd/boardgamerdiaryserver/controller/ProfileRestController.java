package ru.te3ka.bgd.boardgamerdiaryserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.te3ka.bgd.boardgamerdiaryserver.model.Profile;
import ru.te3ka.bgd.boardgamerdiaryserver.repository.ProfileRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/upload/profile")
public class ProfileRestController {
    @Autowired
    private ProfileRepository profileRepository;

    @GetMapping
    public List<Profile> getAllProfiles() {
        return profileRepository.findAll();
    }

    @GetMapping("/{contactPhone}")
    public ResponseEntity<Profile> getProfileById(@PathVariable("contactPhone") String contactPhone) {
        Optional<Profile> profile = profileRepository.findById(contactPhone);
        if (profile.isPresent()) {
            return ResponseEntity.ok(profile.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/")
    public Profile createProfile(@RequestBody Profile profile) {
        return profileRepository.save(profile);
    }

    @PutMapping("/{contactPhone}")
    public ResponseEntity<Profile> updateProfile(@PathVariable("contactPhone") String contactPhone, @RequestBody Profile profileDetails) {
        Optional<Profile> optionalProfile = profileRepository.findById(contactPhone);
        if (optionalProfile.isPresent()) {
            Profile profile = optionalProfile.get();
            profile.setContactId(profileDetails.getContactId());
            profile.setMyCollectionId(profileDetails.getMyCollectionId());
            profile.setWantToPlayId(profileDetails.getWantToPlayId());
            profile.setWishlistId(profileDetails.getWishlistId());
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
