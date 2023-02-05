package fr.ipme.coupedumonde.controllers;

import fr.ipme.coupedumonde.entities.user.User;
import fr.ipme.coupedumonde.exceptions.MalformedUserRequestException;
import fr.ipme.coupedumonde.exceptions.UserNotFoundException;
import fr.ipme.coupedumonde.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/")
    public List<User> list() {
        return userRepository.findAll();
    }
    @GetMapping("/leaderboard")
    public List<User> getLeaderBoard() {
        return userRepository.findAllOrderedByWonBets();

    }

    @GetMapping("/{id}")
    public User get(@PathVariable long id) {
        Optional<User> maybeUser = userRepository.findById(id);
        if (!maybeUser.isPresent())
            throw new UserNotFoundException(id,"get");

        return maybeUser.get();
    }

    @PostMapping(value = "/")
    public User create(@RequestBody User user) {

        if(user.getName().isEmpty() || user.getRole() == null)
            throw new MalformedUserRequestException("create");

        return userRepository.save(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        if(!userRepository.existsById(id))
            throw new UserNotFoundException(id,"delete");
        userRepository.deleteById(id);
    }

    @PutMapping("/api/users/{id}")
    public User update(@PathVariable long id, @RequestBody User providedUser) {

        if(!userRepository.existsById(id))
            throw new UserNotFoundException(id,"update");


        return userRepository.findById(id)
                .map(user -> {
                    user.setName(providedUser.getName());
                    user.setRole(providedUser.getRole());
                    return userRepository.save(user);
                })
                .orElseGet(() -> {
                    providedUser.setId(id);
                    return userRepository.save(providedUser);
                });
    }
}
