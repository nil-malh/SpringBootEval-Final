package fr.ipme.coupedumonde.controllers.generic;

import fr.ipme.coupedumonde.entities.user.User;
import fr.ipme.coupedumonde.exceptions.UserNotFoundException;
import fr.ipme.coupedumonde.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


/**
 * Version different de UserController à partir du GenericController
 */
@RestController
@RequestMapping("/api/users2")
public class UserController2 extends GenericController<User>{

    //Exemple de logger
    Logger logger = LoggerFactory.getLogger(UserController2.class);

    @Autowired
    public UserController2(UserRepository repo) {
        this.repo = repo;
    }


    @Override
    @GetMapping("/{id}")
    public User get(@PathVariable long id){
        Optional<User> maybeStuff = repo.findById(id);
        if (!maybeStuff.isPresent()){
            logger.warn("Trying to retrieve a non existing user");
            throw new UserNotFoundException(id);
        }
        return maybeStuff.get();
    }

}
