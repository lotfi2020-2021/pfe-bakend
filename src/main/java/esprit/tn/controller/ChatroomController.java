package esprit.tn.controller;

import esprit.tn.dto.ChatDto;
import esprit.tn.dto.ChatroomDto;
import esprit.tn.entity.Chat;
import esprit.tn.entity.Chatroom;
import esprit.tn.entity.User;
import esprit.tn.mapper.ChatMapper;
import esprit.tn.mapper.ChatroomMapper;
import esprit.tn.repository.ChatRepository;
import esprit.tn.repository.ChatRoomRepository;
import esprit.tn.repository.UserRepository;
import esprit.tn.service.ChatServiceImpl;
import esprit.tn.service.ChatroomServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/chatroom")
public class ChatroomController {



    @Autowired
    ChatroomServiceImpl  chatroomServiceImpl ;



    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ChatroomMapper chatroomMapper;
    @Autowired
    private ChatMapper chatMapper;
    @Autowired
    private ChatRepository messageRepository;

    @Autowired
    private ChatServiceImpl chatService;

    @Autowired
    private ChatRoomRepository chatroomRepository;

    @PostMapping("/chat/{senderId}/{receiverId}")
    public  ResponseEntity<?> sendMessage( @RequestBody Chat chatMessage,
          @PathVariable Long senderId , @PathVariable Long receiverId) {

        User sender = userRepository.findById(senderId).get();
        User receiver = userRepository.findById(receiverId).get();

      Chatroom chatroom = chatroomRepository.findChatroomBySenderIdAndRecipientId(senderId,receiverId);

        String chatroomId = "";
        if(chatroom ==null){
            chatroomId= String.format("%d_%d", senderId, receiverId);



            Chatroom senderRecipient = Chatroom
                    .builder()
                    .chatroomId(chatroomId)
                    .sender(sender)
                    .created(new Date())
                    .reciever(receiver)
                    .build();

            Chatroom recipientSender = Chatroom
                    .builder()
                    .chatroomId(chatroomId)
                    .sender(receiver)
                    .reciever(sender)
                    .created(new Date())
                    .build();
            try{
                chatroomRepository.save(senderRecipient);
                chatroomRepository.save(recipientSender);
            }
            catch(Exception ex){
                ex.printStackTrace();
                throw new RuntimeException("Cannont create new chat room between sender "+senderId+" and recipient "+receiverId);
            }

        }
        else{
            chatroomId = chatroom.getChatroomId();
        }
        chatMessage.setCreated(new Date());
        chatMessage.setChatroomId(chatroomId);
        chatMessage.setIsRead(false);
        chatMessage.setReciever(receiver);
        chatMessage.setSender(sender);
        chatMessage.setBody(chatMessage.getBody());
        Chat saved = null;
        try{
            saved = messageRepository.save(chatMessage);

        }
        catch(Exception ex){
            throw new RuntimeException("Cannot create new message in chatroomId "+ chatroomId);
        }

        ChatDto chatDto = chatMapper.chatToChatDTO(saved);

      chatroomServiceImpl.send(chatDto);
        return  new ResponseEntity(chatDto,HttpStatus.OK);
    }







}
