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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public List<Measure> getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(List<Measure> currentHealth) {
        this.currentHealth = currentHealth;
    }

    public List<Measure> getHealthHistory() {
        return healthHistory;
    }

    public void setHealthHistory(List<Measure> healthHistory) {
        this.healthHistory = healthHistory;
    }
}
