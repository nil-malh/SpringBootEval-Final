package fr.ipme.coupedumonde.repository;

import fr.ipme.coupedumonde.entities.foot.Pronostic;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PronosticRepository extends JpaRepository<Pronostic, Long> {
}
