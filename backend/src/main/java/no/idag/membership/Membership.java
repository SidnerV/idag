package no.idag.membership;

import jakarta.persistence.*;
import no.idag.enums.Role;
import no.idag.group.AppGroup;
import no.idag.user.AppUser;

@Entity
public class Membership {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private AppUser user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "group_id")
    private AppGroup group;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    protected Membership() {}

    public Membership(AppUser user, AppGroup group, Role role) {
        this.user = user;
        this.group = group;
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public AppUser getUser() {
        return user;
    }

    public AppGroup getGroup() {
        return group;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
