package fr.ipme.coupedumonde.entities.foot;


import fr.ipme.coupedumonde.entities.user.User;

import javax.persistence.*;


@Entity
@Table(name = "PRONOSTICS")
public class Pronostic {

    @OneToOne
    private User user;
    @OneToOne
    private Match match;
    @OneToOne
    private Score score;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public Pronostic() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
