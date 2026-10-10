package no.idag.Group;

import jakarta.persistence.*;
import no.idag.User.AppUser;

import java.util.List;

@Entity
@Table(name = "app_groups")
public class AppGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @ManyToMany(mappedBy = "appGroups")
    private List<AppUser> users;
    // TODO add Calendar, dinnerlist, shoppinglist and tasks

    protected AppGroup() {}

    public AppGroup(int id, String name, List<AppUser> users) {
        this.id = id;
        this.name = name;
        this.users = users;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AppUser> getUsers() {
        return users;
    }

    public void addUser(AppUser user) {
        this.users.add(user);
    }
}
