package esprit.tn.service;


import esprit.tn.entity.Demande;
import org.springframework.stereotype.Service;

import java.util.List;


public interface DemandeService {


    Demande AddDemande(Demande demande);
    void  deleteDemande (Long id ) ;

    List<Demande> getAllDemandes();

   Demande getDemandeById(Long id) ;

   Demande updateDemande(Long id ) ;

}