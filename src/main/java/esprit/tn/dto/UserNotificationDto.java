package esprit.tn.dto;


import esprit.tn.entity.Notification;
import esprit.tn.entity.User;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserNotificationDto {
    private Long id;
    private String  receiverEmail;
    private Long  receiverId;
    private Long  senderId ;
    private String  senderEmail;
    private NotificationDto notification;
    private Boolean isSeen;

}
