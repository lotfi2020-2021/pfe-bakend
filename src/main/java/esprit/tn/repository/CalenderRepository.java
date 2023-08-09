package esprit.tn.repository;

import esprit.tn.entity.Calendar;
import esprit.tn.entity.Demande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CalenderRepository   extends JpaRepository<Calendar, Long> {


    Calendar findByGoogleCalenderId(String id);


}
