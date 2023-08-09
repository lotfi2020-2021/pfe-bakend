package esprit.tn.repository;

import esprit.tn.entity.Chat;
import esprit.tn.entity.Chatroom;
import esprit.tn.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRoomRepository extends JpaRepository<Chatroom, Long> {
    @Query("FROM Chatroom c WHERE c.sender.id = :senderId AND c.reciever.id = :receiverId ORDER BY c.created DESC")
   Chatroom findChatroomBySenderIdAndRecipientId(@Param("senderId") Long senderId, @Param("receiverId") Long receiverId);


    @Query("SELECT  c.chatroomId FROM Chatroom c WHERE c.sender = :user")
    List<String> findAllDistinctBySender(@Param("user") User user);


}
