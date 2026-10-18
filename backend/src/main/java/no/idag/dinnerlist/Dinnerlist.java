package no.idag.dinnerlist;

import jakarta.persistence.*;

@Entity
public class Dinnerlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    protected Dinnerlist() {}

    public Dinnerlist(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return this.name;
    }
}
