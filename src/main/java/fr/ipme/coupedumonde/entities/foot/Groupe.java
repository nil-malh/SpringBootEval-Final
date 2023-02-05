package fr.ipme.coupedumonde.entities.foot;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import fr.ipme.coupedumonde.config.GroupSerializer;
import fr.ipme.coupedumonde.exceptions.MalformedGroupRequestException;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "GROUPES")
@JsonSerialize(using = GroupSerializer.class)

public class Groupe {

    @OneToMany
    private List<Equipe> equipes;

    @OneToMany
    private List<Match> matchs;

    public void setEquipes(List<Equipe> equipes) {
        if(!(equipes.size() == 4))
            throw new MalformedGroupRequestException("set team");

        this.equipes = equipes;
    }

    public void setMatchs(List<Match> matchs) {
        this.matchs = matchs;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Groupe() {

    }

    public List<Match> getMatchs() {
        return matchs;
    }

    public List<Equipe> getEquipes() {
        return equipes;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
