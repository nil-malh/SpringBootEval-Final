package fr.ipme.coupedumonde.entities.foot;

import javax.persistence.*;

@Entity
@Table(name = "scores")
public class Score {
    private int scoreFinalEquipeA;
    private int scoreFinalEquipeB;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Score() {
    }

    public int getScoreFinalEquipeA() {
        return scoreFinalEquipeA;
    }

    public int getScoreFinalEquipeB() {
        return scoreFinalEquipeB;
    }

    public void setScoreFinalEquipeA(int scoreFinalEquipeA) {
        this.scoreFinalEquipeA = scoreFinalEquipeA;
    }

    public void setScoreFinalEquipeB(int scoreFinalEquipeB) {
        this.scoreFinalEquipeB = scoreFinalEquipeB;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
