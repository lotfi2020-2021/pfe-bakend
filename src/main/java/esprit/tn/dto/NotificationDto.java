package esprit.tn.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import esprit.tn.entity.User;
import esprit.tn.entity.UserNotification;
import esprit.tn.enumeration.NotificationType;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDto {


    private Long id;
    private String title;
    private String content;
    private NotificationType type;
    private UpdateUserInfoDto sender;
  //  private List<UserNotificationDto> receivers;
    private Date dateCreated;
    private Date dateUpdated;
}
