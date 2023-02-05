package fr.ipme.coupedumonde.controllers;

import fr.ipme.coupedumonde.entities.foot.Equipe;
import fr.ipme.coupedumonde.repository.EquipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/equipes")
public class EquipeController {

    @Autowired
    EquipeRepository equipeRepository;

    @GetMapping("/")
    public List<Equipe> list(){
        return equipeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Equipe get(@PathVariable long id){
        Optional<Equipe> maybeEquipe = equipeRepository.findById(id);
        if (!maybeEquipe.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Equipe not found");
        }
        return maybeEquipe.get();
    }

    @PostMapping("/")
    public Equipe create(@RequestBody Equipe equipe) {
        return equipeRepository.save(equipe);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        equipeRepository.deleteById(id);
    }
}
