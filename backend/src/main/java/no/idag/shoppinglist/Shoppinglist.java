package no.idag.shoppinglist;

import jakarta.persistence.*;

@Entity
public class Shoppinglist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    protected Shoppinglist() {}

    public Shoppinglist(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
