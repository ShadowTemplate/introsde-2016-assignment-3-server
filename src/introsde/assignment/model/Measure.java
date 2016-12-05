package introsde.assignment.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="Measure")
public class Measure implements Serializable {

    @Id
    @TableGenerator(name="MEASURE_ID_GENERATOR", table="MEASURE_SEQUENCES", pkColumnName="MEASURE_SEQ_NAME",
            valueColumnName="MEASURE_SEQ_NUMBER", pkColumnValue = "MEASURE_SEQUENCE", allocationSize=1)
    @GeneratedValue(strategy= GenerationType.TABLE, generator="MEASURE_ID_GENERATOR")
    private Long mid;

    @Column(name="dateRegistered")
    @Temporal(TemporalType.DATE)
    private Date dateRegistered;

    @Column(name="measureType")
    private String measureType;

    @Column(name="measureValue")
    private String measureValue;

    @Column(name="measureValueType")
    private String measureValueType;

    @ManyToOne
    @JoinColumn(name="id",referencedColumnName="id")
    private Person person;

    public Measure() {

    }

    public Long getMid() {
        return mid;
    }

    public void setMid(Long mid) {
        this.mid = mid;
    }

    public Date getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(Date dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public String getMeasureType() {
        return measureType;
    }

    public void setMeasureType(String measureType) {
        this.measureType = measureType;
    }

    public String getMeasureValue() {
        return measureValue;
    }

    public void setMeasureValue(String measureValue) {
        this.measureValue = measureValue;
    }

    public String getMeasureValueType() {
        return measureValueType;
    }

    public void setMeasureValueType(String measureValueType) {
        this.measureValueType = measureValueType;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "Measure{" +
                "mid=" + mid +
                ", dateRegistered=" + dateRegistered +
                ", measureType='" + measureType + '\'' +
                ", measureValue='" + measureValue + '\'' +
                ", measureValueType='" + measureValueType + '\'' +
                '}';
    }


}