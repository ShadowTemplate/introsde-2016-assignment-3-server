package introsde.assignment.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="Person")
public class Person implements Serializable {

    @Id
    @TableGenerator(name="PERSON_ID_GENERATOR", table="PERSON_SEQUENCES", pkColumnName="PERSON_SEQ_NAME",
            valueColumnName="PERSON_SEQ_NUMBER", pkColumnValue = "PERSON_SEQUENCE", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.TABLE, generator="PERSON_ID_GENERATOR")
    private Long id;

    @Column(name="firstname")
    private String firstname;

    @Column(name="lastname")
    private String lastname;

    @OneToMany(mappedBy="Person",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    private List<Measure> currentHealth;

    @OneToMany(mappedBy="Person",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    private List<Measure> healthHistory;

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

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", currentHealth=" + currentHealth +
                ", healthHistory=" + healthHistory +
                '}';
    }
}
