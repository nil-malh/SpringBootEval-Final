package fr.ipme.coupedumonde.repository;

import fr.ipme.coupedumonde.entities.foot.Groupe;
import org.springframework.data.jpa.repository.JpaRepository;


public interface GroupeRepository extends JpaRepository<Groupe, Long> {
}
