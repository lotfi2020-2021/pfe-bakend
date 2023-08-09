package esprit.tn.mapper;

import com.fasterxml.jackson.annotation.JsonFormat;
import esprit.tn.dto.ChatDto;

import esprit.tn.entity.Chat;
import esprit.tn.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.List;

@Mapper(componentModel = "spring" )
public interface ChatMapper {


    @Mapping(source = "body",target = "body")
    @Mapping( source = "sender.id",target = "senderId")
    @Mapping( source = "reciever.id",target = "recieverId")
    @Mapping( source = "sender.firstName",target = "userNameSender")
    @Mapping( source = "reciever.firstName",target = "userNameReceiver")
    @Mapping( source = "sender.profilePhoto",target = "photoSender")
    @Mapping( source = "reciever.profilePhoto",target = "photoReceiver")
    @Mapping( source = "isRead",target = "isRead")
    @Mapping( source = "chatroomId",target = "chatroomId")
    @Mapping( source = "created",target = "created")
    ChatDto chatToChatDTO(Chat chat);

    List<ChatDto> chatsToChatsDTO(List<Chat> chats);
}


