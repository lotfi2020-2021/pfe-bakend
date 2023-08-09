package esprit.tn.mapper;

import com.fasterxml.jackson.annotation.JsonFormat;
import esprit.tn.dto.ChatDto;
import esprit.tn.dto.ChatroomDto;
import esprit.tn.entity.Chat;
import esprit.tn.entity.Chatroom;
import esprit.tn.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring" )
public interface ChatroomMapper {



    @Mapping( source = "sender.id",target = "senderId")
    @Mapping( source = "reciever.id",target = "recieverId")
    @Mapping( source = "sender.firstName",target = "userNameSender")
    @Mapping( source = "reciever.firstName",target = "userNameReceiver")
    @Mapping( source = "sender.profilePhoto",target = "photoSender")
    @Mapping( source = "reciever.profilePhoto",target = "photoReceiver")
    @Mapping( source = "created",target = "created")
    ChatroomDto chatroomToChatroomDTO(Chatroom chat);

    List<ChatroomDto> chatroomsToChatroomsDTO(List<Chatroom> chats);

}


