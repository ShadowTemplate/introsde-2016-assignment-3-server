package introsde.assignment.to;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Measure implements Serializable {

    private Long mid;
    private Date dateRegistered;
    private String measureType;
    private String measureValue;
    private String measureValueType;

    public Measure() {

    }

    public Measure(Date dateRegistered, String measureType, String measureValue, String measureValueType) {
        this.dateRegistered = dateRegistered;
        this.measureType = measureType;
        this.measureValue = measureValue;
        this.measureValueType = measureValueType;
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