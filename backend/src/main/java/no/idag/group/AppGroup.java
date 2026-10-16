package no.idag.group;

import jakarta.persistence.*;
import no.idag.calendar.Calendar;
import no.idag.dinnerlist.Dinnerlist;
import no.idag.shoppinglist.Shoppinglist;

@Entity
@Table(name = "app_groups")
public class AppGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @OneToOne
    @JoinColumn(name = "calendar_id")
    private Calendar calendar;

    @OneToOne
    @JoinColumn(name = "dinnerlist_id")
    private Dinnerlist dinnerlist;

    @OneToOne
    @JoinColumn(name = "shoppinglist_id")
    private Shoppinglist shoppinglist;

    protected AppGroup() {}

    public AppGroup(String name, Calendar calendar, Dinnerlist dinnerlist, Shoppinglist shoppinglist) {
        this.name = name;
        this.calendar = calendar;
        this.dinnerlist = dinnerlist;
        this.shoppinglist = shoppinglist;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Calendar getCalendar() {
        return this.calendar;
    }

    public Dinnerlist getDinnerlist() {
        return this.dinnerlist;
    }

    public Shoppinglist getShoppinglist() {
        return this.shoppinglist;
    }
}
