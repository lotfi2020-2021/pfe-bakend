package esprit.tn.controller;


import esprit.tn.dto.NotificationDto;
import esprit.tn.entity.Notification;
import esprit.tn.entity.User;
import esprit.tn.mapper.NotificationMapper;
import esprit.tn.repository.UserRepository;
import esprit.tn.response.NotificationResponse;
import esprit.tn.service.NotificationService;
import esprit.tn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/notification")
public class NotificationController {


    @Autowired
    NotificationMapper notificationMapper;
    @Autowired
    private  NotificationService notificationService;

    @Autowired
    private UserRepository userRepository;


    @PostMapping("/send/{userId}")
    public ResponseEntity<?> sendNotification(@RequestBody NotificationResponse notificationResponse , @PathVariable Long userId) {
        List<User> users = new ArrayList<>();
        for (String email : notificationResponse.getEmailList()) {
            User receiver = userRepository.findByEmail(email).get();
            users.add(receiver);
        }
            User sender = userRepository.findUserById(userId);

            Notification notification =  notificationService.save( notificationResponse.getNotification() , users , sender) ;
          NotificationDto notificationDto = notificationMapper.notificationToNotificationDto(notification);
        System.out.println("notifications" + notification);
        notificationService.send(notificationDto);
        return new ResponseEntity<>( notificationDto ,HttpStatus.OK);
    }


    @GetMapping("/notifications")
    public ResponseEntity<?> GetNotifications() {
       List<Notification> list = notificationService.getNotifications();
        List<NotificationDto> notificationsDtos = notificationMapper.notificationsToNotificationsDto(list) ;
        return new ResponseEntity<>(notificationsDtos, HttpStatus.OK);
    }





    @GetMapping("/get/{id}")
    public ResponseEntity<?> GetNotificationById( @PathVariable Long id) {
        Notification notification = notificationService.getNotificationById(id);
     NotificationDto notificationDto =  notificationMapper.notificationToNotificationDto(notification) ;
        return new ResponseEntity<>(notificationDto, HttpStatus.OK);

    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteNotification( @PathVariable Long id) {
       notificationService.deleteNotification(id);
        return new ResponseEntity<>( HttpStatus.OK);
    }


    @PutMapping("/update/{notificationId}")
    public ResponseEntity<NotificationDto> updateNotification(@PathVariable Long notificationId, @RequestBody NotificationResponse notificationResponse) {
        List<User> users = new ArrayList<>();
        for (String email : notificationResponse.getEmailList()) {
            User receiver = userRepository.findByEmail(email).get();
            users.add(receiver);
        }
        Notification updatedNotification = notificationService.update(notificationResponse.getNotification(), users, notificationId);
     NotificationDto updatedNotificationDto = notificationMapper.notificationToNotificationDto(updatedNotification) ;
        System.out.println("notifications" + updatedNotificationDto);
        notificationService.send(updatedNotificationDto);
        return new ResponseEntity<>( updatedNotificationDto ,HttpStatus.OK);
    }


}
