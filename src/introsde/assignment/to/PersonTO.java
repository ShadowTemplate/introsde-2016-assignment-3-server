package introsde.assignment.to;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class PersonTO implements Serializable {

    private static final long serialVersionUID = 42L;

    private Long id;
    private String firstname;
    private String lastname;
    private List<MeasureTO> currentHealth;
    private List<MeasureTO> healthHistory;

    public PersonTO() {

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

    public List<MeasureTO> getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(List<MeasureTO> currentHealth) {
        this.currentHealth = currentHealth;
    }

    public List<MeasureTO> getHealthHistory() {
        return healthHistory;
    }

    public void setHealthHistory(List<MeasureTO> healthHistory) {
        this.healthHistory = healthHistory;
    }

    @Override
    public String toString() {
        return "PersonTO{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", currentHealth=" + currentHealth +
                ", healthHistory=" + healthHistory +
                '}';
    }
}
