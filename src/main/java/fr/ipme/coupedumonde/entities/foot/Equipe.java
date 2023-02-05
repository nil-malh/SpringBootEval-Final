package fr.ipme.coupedumonde.entities.foot;


import javax.persistence.*;

@Entity
@Table(name = "EQUIPES")
public class Equipe {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Groupe groupe;

    public Groupe getGroupe() {
        return groupe;
    }

    public void setGroupe(Groupe groupe) {
        this.groupe = groupe;
    }

    public Long getId() {
        return id;
    }

    private String name;

    public Equipe() {
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Equipe(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
