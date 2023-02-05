package fr.ipme.coupedumonde.controllers.generic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

public class GenericController<T> {

    protected JpaRepository<T, Long> repo;

    @GetMapping("/")
    public List<T> list(){
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public T get(@PathVariable long id){
        Optional<T> maybeStuff = repo.findById(id);
        if (!maybeStuff.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "not found");
        }
        return maybeStuff.get();
    }

    @PostMapping(value = "/")
    public T create(@RequestBody T stuff) {
        return repo.save(stuff);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        repo.deleteById(id);
    }
}
