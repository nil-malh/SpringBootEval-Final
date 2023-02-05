package fr.ipme.coupedumonde.controllers;

import fr.ipme.coupedumonde.entities.foot.Groupe;
import fr.ipme.coupedumonde.exceptions.GroupNotFoundException;
import fr.ipme.coupedumonde.exceptions.MalformedGroupRequestException;
import fr.ipme.coupedumonde.repository.GroupeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/groupes")
public class GroupeController {

    @Autowired
    GroupeRepository groupeRepository;

    @GetMapping("/")
    public List<Groupe> list(){

        return groupeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Groupe get(@PathVariable long id){
        Optional<Groupe> maybeGroupe = groupeRepository.findById(id);
        if (!maybeGroupe.isPresent())
            throw new GroupNotFoundException(id);
        return maybeGroupe.get();
    }

    @PostMapping("/")
    public Groupe create(@RequestBody Groupe groupe) {
            if(groupe.getEquipes().size() == 4)
                throw new MalformedGroupRequestException("create");
            if(!groupeRepository.existsById(groupe.getId()))
                throw new MalformedGroupRequestException("create");


        return groupeRepository.save(groupe);
    }

    @PutMapping("/")
    public Groupe update(@RequestParam(name = "groupID") Long groupID,@RequestBody Groupe providedGroup)
    {
        if(!groupeRepository.existsById(groupID))
            throw new GroupNotFoundException(groupID,"update");

        if(providedGroup == null)
            throw new MalformedGroupRequestException(groupID,"update");

        return groupeRepository.findById(groupID)
                .map(groupe -> {
                    groupe.setMatchs(providedGroup.getMatchs());
                    groupe.setEquipes(providedGroup.getEquipes());
                    return groupeRepository.save(groupe);
                })
                .orElseGet(() -> {
                    providedGroup.setId(groupID);
                    return groupeRepository.save(providedGroup);
                });
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        if(!groupeRepository.existsById(id))
            throw new GroupNotFoundException(id);

        groupeRepository.deleteById(id);
    }
}
