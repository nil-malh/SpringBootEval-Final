package fr.ipme.coupedumonde.entities.user;

import javax.persistence.*;

@Entity
@Table(name = "USERS")
public class User {
    private String name;


    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
@Column(name = "won_bets")
    private int wonBets;

    public User() {

    }

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }

    public User(String name, RoleEnum role) {
        this.name = name;
        this.role = role;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public int getWonBets() {
        return wonBets;
    }
    public void addWonBets() {
        this.wonBets++;
    }
}
