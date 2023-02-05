package fr.ipme.coupedumonde.repository;

import fr.ipme.coupedumonde.entities.foot.Score;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ScoreRepository extends JpaRepository<Score, Long> {
}
