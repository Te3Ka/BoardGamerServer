package ru.te3ka.bgd.boardgamerdiaryserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.te3ka.bgd.boardgamerdiaryserver.model.Meeting;
import ru.te3ka.bgd.boardgamerdiaryserver.repository.MeetingRepository;
import ru.te3ka.bgd.boardgamerdiaryserver.service.PushNotificationService;

import java.util.List;
import java.util.Optional;


/**
 * Контроллер для обработки запросов, связанных с встречами и приглашениями.
 *
 * Этот контроллер управляет созданием встреч, отправкой приглашений, получением информации
 * о встречах и их участниках. Использует сервис PushNotificationService для отправки уведомлений.
 */
@RestController
@RequestMapping("/meetings")
public class MeetingController {
    @Autowired
    private MeetingRepository meetingRepository;

    @Autowired
    private PushNotificationService pushNotificationService;

    /**
     * Создает новую встречу и отправляет уведомления участникам.
     *
     * @param meeting Объект встречи, содержащий информацию о дате, месте и участниках.
     * @return Ответ с созданной встречей.
     */
    @PostMapping("/")
    public ResponseEntity<Meeting> createMeeting(@RequestBody Meeting meeting) {
//        meetingRepository.save(meeting); // TODO: для сохранения строку надо расскоменитровать.

        String title = "Новая встреча!";
        String body = "Вы приглашены на встречу " + meeting.getDate()
                + " в " + meeting.getLocation()
                + " с " + meeting.getContacts()
                + " для игры в " + meeting.getBoardgames();
        for (String contact : meeting.getContacts()) {
            String token = pushNotificationService.getTokenByPhoneNumber(contact);
            if (token != null) {
                pushNotificationService.sendPushNotificationToMeetingInvitation(token, title, body);
            }
        }
        return ResponseEntity.ok(meeting);
    }

    /**
     * Приглашает контакты на существующую встречу.
     *
     * @param meetingId ID встречи, на которую отправляются приглашения.
     * @param contactPhones Список телефонов контактов, которых нужно пригласить.
     * @return Ответ о результате операции.
     */
    @PostMapping("/{meetingId}/invite")
    public ResponseEntity<String> inviteContacts(@PathVariable("meetingId") Integer meetingId, @RequestBody List<String> contactPhones) {
        Optional<Meeting> optionalMeeting = meetingRepository.findById(meetingId);
        if (optionalMeeting.isPresent()) {
            Meeting meeting = optionalMeeting.get();
            meeting.getContacts().addAll(contactPhones);
            meetingRepository.save(meeting);
            return ResponseEntity.ok("Invitations sent successfully.");
        } else {
            return ResponseEntity.badRequest().body("Meeting with ID " + meetingId + " not found.");
        }
    }

    /**
     * Получает список контактов, приглашенных на указанную встречу.
     *
     * @param meetingId ID встречи.
     * @return Список телефонов контактов, приглашенных на встречу.
     */
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

    /**
     * Получает список всех встреч.
     *
     * @return Список всех встреч.
     */
    @GetMapping("/")
    public List<Meeting> getAllMeetings() {
        return meetingRepository.findAll();
    }

    /**
     * Получает информацию о встрече по ее ID.
     *
     * @param meetingId ID встречи.
     * @return Информация о встрече или статус "не найдено", если встреча не существует.
     */
    @GetMapping("/{meetingId}")
    public ResponseEntity<Meeting> getMeetingById(@PathVariable("meetingId") Integer meetingId) {
        Optional<Meeting> optionalMeeting = meetingRepository.findById(meetingId);
        return optionalMeeting.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
