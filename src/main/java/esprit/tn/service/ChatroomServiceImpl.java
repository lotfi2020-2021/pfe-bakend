package esprit.tn.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import esprit.tn.dto.ChatDto;
import esprit.tn.dto.NotificationDto;
import esprit.tn.entity.Chat;
import esprit.tn.entity.Chatroom;
import esprit.tn.entity.User;
import esprit.tn.exception.MapperException;
import esprit.tn.repository.ChatRoomRepository;
import esprit.tn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ChatroomServiceImpl {
    private static final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    ChatRoomRepository chatroomRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private  BrokerProducerService brokerProducerService;
    @Autowired
    private  Environment env;

    public Chatroom findChatroomBySenderIdAndRecipientId(Long senderId, Long recipientId){
        Chatroom found = chatroomRepository.findChatroomBySenderIdAndRecipientId(senderId, recipientId);

        return found;
    }
    public void send(ChatDto chat) {
        brokerProducerService.sendMessage(env.getProperty("producer.kafka.topic-name2"), toJson(chat));
    }





    /**
     * Convert Object to json
     *
     * @param object object
     * @return string json
     */
    private <T> String toJson(T object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new MapperException(e.getMessage());
        }
    }

}
