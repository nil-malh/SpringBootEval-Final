package fr.ipme.coupedumonde.services;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public class GenericService<T>{

    protected JpaRepository<T, Long> repo;

    public T save(final T objectToSave){
        return repo.save(objectToSave);
    }

    public List<T> get(){
        return repo.findAll();
    }

    public Optional<T> get(long id){
        return repo.findById(id);
    }

    public void delete(long id){
        repo.deleteById(id);
    }

}
