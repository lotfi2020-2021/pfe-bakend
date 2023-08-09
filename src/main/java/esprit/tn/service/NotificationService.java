package esprit.tn.service;


import esprit.tn.dto.NotificationDto;
import esprit.tn.entity.Notification;
import esprit.tn.entity.User;

import java.util.List;

public interface NotificationService {

    /**
     * Send notification
     * @param notifications model of notification
     */
    void send(NotificationDto notifications);

    Notification save(Notification notification ,List<User> users ,User sender) ;

    List<Notification> getNotifications();

    Notification getNotificationById(Long idNotification);

    Notification update(Notification notification, List<User> users, Long notificationId);

    void deleteNotification(Long id);



}
