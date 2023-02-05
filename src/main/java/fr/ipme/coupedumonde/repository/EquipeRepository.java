package fr.ipme.coupedumonde.repository;

import fr.ipme.coupedumonde.entities.foot.Equipe;
import fr.ipme.coupedumonde.entities.foot.Match;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface EquipeRepository extends JpaRepository<Equipe, Long> {

    List<Equipe> findAllByGroupeId(Long groupeId);

}
