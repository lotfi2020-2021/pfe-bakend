package esprit.tn.mapper;

import esprit.tn.dto.UserNotificationDto;

import esprit.tn.entity.Notification;
import esprit.tn.entity.User;
import esprit.tn.entity.UserNotification;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import javax.persistence.*;
import java.util.List;

@Mapper(componentModel = "spring", uses = { UserMapper.class})
public interface UserNotificationMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "receiver.email", target = "receiverEmail")
    @Mapping(source = "receiver.id", target = "receiverId")
    @Mapping(source = "notification.sender.email", target = "senderEmail")
    @Mapping(source = "notification.sender.id", target = "senderId")
    @Mapping(source = "notification", target = "notification")
    @Mapping(source = "isSeen", target = "isSeen")


    UserNotificationDto userNotificationToUserNotificationDto(UserNotification userNotification) ;


    List<UserNotificationDto> userNotificationsToUserNotificationsDtos(List<UserNotification> notifications);
}
