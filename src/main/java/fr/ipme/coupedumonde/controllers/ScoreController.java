package fr.ipme.coupedumonde.controllers;

import fr.ipme.coupedumonde.entities.foot.Score;
import fr.ipme.coupedumonde.exceptions.UserNotFoundException;
import fr.ipme.coupedumonde.repository.ScoreRepository;
import fr.ipme.coupedumonde.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
public class ScoreController {

    @Autowired
    ScoreRepository scoreRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/api/scores")
    public List<Score> list(){
        return scoreRepository.findAll();
    }

    @GetMapping("/api/scores/{id}")
    public Score get(@PathVariable long id) {
        Optional<Score> maybeScore = scoreRepository.findById(id);

        if (!maybeScore.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Score not found");
        }

        return maybeScore.get();
    }

    @PostMapping("/api/scores")
    public Score create(@RequestParam(name = "userID") Long userID,@RequestBody Score score) {

        if(!userRepository.existsById(userID))
            throw  new UserNotFoundException(userID);
        return scoreRepository.save(score);
    }

    @DeleteMapping("/api/scores/{id}")
    public void delete(@PathVariable long id) {
        scoreRepository.deleteById(id);
    }
}
