package esprit.tn.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import esprit.tn.entity.User;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatroomDto {

        public Long senderId ;
        public Long recieverId ;
        public String photoSender ;
        public String userNameSender ;
        public String photoReceiver ;
        public String userNameReceiver ;
        public Date created ;

}
