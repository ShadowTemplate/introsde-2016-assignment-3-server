package introsde.assignment.model;

import java.util.List;

public class Person {
    private Long id;
    private String firstname;
    private String lastname;
    private List<Measure> currentHealth; // one for each type of measure
    private List<Measure> healthHistory; // all measurements

    public Person(Long id, String firstname, String lastname, List<Measure> currentHealth, List<Measure> healthHistory) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.currentHealth = currentHealth;
        this.healthHistory = healthHistory;
    }

    public Person() {

    }
}
