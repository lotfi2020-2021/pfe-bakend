package esprit.tn.repository;


import esprit.tn.entity.Department;
import esprit.tn.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository  extends JpaRepository<Event, Long> {

 Event findByGoogleEventId(String eventId);


}
