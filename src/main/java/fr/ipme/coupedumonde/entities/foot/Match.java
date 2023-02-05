package fr.ipme.coupedumonde.entities.foot;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "MATCHS")
public class Match {

    @OneToOne
    protected Equipe equipeA;

    public Match(Equipe equipeA, Equipe equipeB, Score score) {
        this.equipeA = equipeA;
        this.equipeB = equipeB;
        this.score = score;
    }
    public Match(Equipe equipeA, Equipe equipeB, Score score,LocalDateTime match_date) {
        this.equipeA = equipeA;
        this.equipeB = equipeB;
        this.match_date = match_date;
        this.score = score;
    }
    public Match(Equipe equipeA, Equipe equipeB, LocalDateTime match_date) {
        this.equipeA = equipeA;
        this.equipeB = equipeB;
        this.match_date = match_date;
    }
    public Match(Equipe equipeA, Equipe equipeB) {
        this.equipeA = equipeA;
        this.equipeB = equipeB;
        this.match_date = LocalDateTime.now();
    }

    public Equipe getEquipeA() {
        return equipeA;
    }

    public void setEquipeA(Equipe equipeA) {
        this.equipeA = equipeA;
    }

    public Equipe getEquipeB() {
        return equipeB;
    }

    public void setEquipeB(Equipe equipeB) {
        this.equipeB = equipeB;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    @OneToOne
    protected Equipe equipeB;

    @OneToOne
    protected Score score;

    @Column(name = "match_date")
    private LocalDateTime match_date;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    public boolean isMatchNotFinished()
    {
        return match_date.isAfter(LocalDateTime.now());
    }


    public Match() {
        super();
    }

    public void setId(Long id) {
        this.id = id;
    }


    public LocalDateTime getMatchDate() {
        return match_date;
    }

    public void setMatchDate(LocalDateTime match_date) {
        this.match_date = match_date;
    }

    @Override
    public String toString() {
        return "Match:" + id + " " + equipeA.getName() +
            "/" + equipeB.getName() + (score != null ? " / Score : "+ score.getScoreFinalEquipeA() +":" + score.getScoreFinalEquipeB() : "");
    }

    public Long getId() {
        return id;
    }
}
