package fr.ipme.coupedumonde.controllers;

import fr.ipme.coupedumonde.entities.foot.Match;
import fr.ipme.coupedumonde.exceptions.MatchNotFoundException;
import fr.ipme.coupedumonde.repository.MatchRepository;
import fr.ipme.coupedumonde.services.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
public class MatchController {

    @Autowired
    private MatchRepository matchRepository;            //old way

    @Autowired
    private MatchService service;                       //clean way

    @GetMapping("/api/matchs")
    public List<Match> list(){
        return matchRepository.findAll();
    }

    @GetMapping("/api/matchs/{id}")
    public Match get(@PathVariable long id) {
        Optional<Match> maybeMatch = matchRepository.findById(id);

        if (!maybeMatch.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Match not found");
        }

        return maybeMatch.get();
    }

    @PostMapping("/api/matchs")
    public Match create(@RequestBody Match match) {
        return matchRepository.save(match);
    }

    @PutMapping("/api/matchs/{id}")
    public Match update(@PathVariable Long id, @RequestBody Match suppliedMatch) {
         if (suppliedMatch.getId() != id){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ids don't match");
        }
        //check it exists
        matchRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Match not found"));

        return matchRepository.save(suppliedMatch);
    }

    @DeleteMapping("/api/matchs/{id}")
    public void delete(@PathVariable long id) {

        if(matchRepository.existsById(id))
                throw new MatchNotFoundException(id);


        matchRepository.deleteById(id);
    }



    @PostMapping("/api/matchs/{id}/results")
    public Match playExistingMatch(@RequestBody Match match, @PathVariable Long id) {
        return service.play(id, match);
    }
}
