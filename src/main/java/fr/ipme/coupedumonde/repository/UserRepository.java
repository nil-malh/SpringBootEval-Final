package fr.ipme.coupedumonde.repository;

import fr.ipme.coupedumonde.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u ORDER BY u.wonBets DESC")
    List<User> findAllOrderedByWonBets();
}
