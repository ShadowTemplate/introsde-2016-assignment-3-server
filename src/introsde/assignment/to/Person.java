package introsde.assignment.to;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Person implements Serializable {

    private Long id;
    private String firstname;
    private String lastname;
    private List<Measure> currentHealth;
    private List<Measure> healthHistory;

    public Person() {

    }

    public Person(String firstname, String lastname, List<Measure> currentHealth, List<Measure> healthHistory) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.currentHealth = currentHealth;
        this.healthHistory = healthHistory;
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
