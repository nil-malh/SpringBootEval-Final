package fr.ipme.coupedumonde.controllers;

import fr.ipme.coupedumonde.entities.foot.Pronostic;
import fr.ipme.coupedumonde.entities.user.RoleEnum;
import fr.ipme.coupedumonde.exceptions.MalformedPronosticRequestException;
import fr.ipme.coupedumonde.exceptions.MatchFinishedException;
import fr.ipme.coupedumonde.exceptions.PronosticNotFoundException;
import fr.ipme.coupedumonde.exceptions.UnauthorizedBetException;
import fr.ipme.coupedumonde.repository.MatchRepository;
import fr.ipme.coupedumonde.repository.PronosticRepository;
import fr.ipme.coupedumonde.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pronostics")
public class PronosticController {

    @Autowired
    PronosticRepository pronosticRepository;
    @Autowired
    private MatchRepository matchRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public List<Pronostic> list(){
        return pronosticRepository.findAll();
    }

    @GetMapping("/{id}")
    public Pronostic get(@PathVariable long id){
        Optional<Pronostic> maybePronostic = pronosticRepository.findById(id);
        if (!maybePronostic.isPresent())
            throw new PronosticNotFoundException(id,"get");

        return maybePronostic.get();
    }

    @PostMapping("/")
    public Pronostic create(@RequestBody Pronostic bet) {

        if(bet == null)
            throw new MalformedPronosticRequestException("create");
        if(!matchRepository.findById(bet.getMatch().getId()).isPresent())
            throw new MalformedPronosticRequestException("create");
        if(!userRepository.findById(bet.getUser().getId()).isPresent())
            throw new MalformedPronosticRequestException("create");
        if(bet.getUser().getRole() == RoleEnum.ADMIN)
            throw new UnauthorizedBetException(bet.getId());
        if(!bet.getMatch().isMatchNotFinished())
            throw new MatchFinishedException(bet.getMatch().getId());


        return pronosticRepository.save(bet);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        if(!pronosticRepository.existsById(id))
            throw new PronosticNotFoundException(id,"delete");
        pronosticRepository.deleteById(id);
    }
}
