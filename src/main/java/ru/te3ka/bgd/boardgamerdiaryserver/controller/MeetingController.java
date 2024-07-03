package ru.te3ka.bgd.boardgamerdiaryserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.te3ka.bgd.boardgamerdiaryserver.model.Contact;
import ru.te3ka.bgd.boardgamerdiaryserver.model.Invitation;
import ru.te3ka.bgd.boardgamerdiaryserver.model.Meeting;
import ru.te3ka.bgd.boardgamerdiaryserver.repository.ContactRepository;
import ru.te3ka.bgd.boardgamerdiaryserver.repository.InvitationRepository;
import ru.te3ka.bgd.boardgamerdiaryserver.repository.MeetingRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/meetings")
public class MeetingController {
    @Autowired
    private MeetingRepository meetingRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private InvitationRepository invitationRepository;

    @PostMapping("/")
    public Meeting createMeeting(@RequestBody Meeting meeting) {
        return meetingRepository.save(meeting);
    }

    @PostMapping("/{meetingId}/invite")
    public ResponseEntity<String> inviteContacts(@PathVariable("meetingId") Integer meetingId, @RequestBody List<String> contactPhones) {
        Optional<Meeting> optionalMeeting = meetingRepository.findById(meetingId);
        if (optionalMeeting.isPresent()) {
            Meeting meeting = optionalMeeting.get();
            meeting.getContacts().addAll(contactPhones);
            meetingRepository.save(meeting);
            return ResponseEntity.ok("Invitations sent successfully");
        } else {
            return ResponseEntity.badRequest().body("Meeting with ID " + meetingId + " not found.");
        }
    }

    @GetMapping("/{meetingId}/invitations")
    public ResponseEntity<List<String>> getInvitationsForMeeting(@PathVariable("meetingId") Integer meetingId) {
        Optional<Meeting> optionalMeeting = meetingRepository.findById(meetingId);
        if (optionalMeeting.isPresent()) {
            Meeting meeting = optionalMeeting.get();
            return ResponseEntity.ok(meeting.getContacts());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/")
    public List<Meeting> getAllMeetings() {
        return meetingRepository.findAll();
    }

    @GetMapping("/{meetingId}")
    public ResponseEntity<Meeting> getMeetingById(@PathVariable("meetingId") Integer meetingId) {
        Optional<Meeting> optionalMeeting = meetingRepository.findById(meetingId);
        return optionalMeeting.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
