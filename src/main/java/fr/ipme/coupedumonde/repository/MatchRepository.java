package fr.ipme.coupedumonde.repository;

import fr.ipme.coupedumonde.entities.foot.Equipe;
import fr.ipme.coupedumonde.entities.foot.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface MatchRepository extends JpaRepository<Match, Long> {


    List<Match> findAllByEquipeA_Id(Long equipeAId);
    List<Match> findAllByEquipeB_Id(Long equipeBId);


    List<Match> findAllByEquipeA(Equipe equipe);
    List<Match> findAllByEquipeB(Equipe equipe);


    @Query("SELECT m FROM Match m WHERE m.equipeA.id = :equipeId OR m.equipeB.id = :equipeId")
    List<Match> findAllByEquipeId(Long equipeId);



}
