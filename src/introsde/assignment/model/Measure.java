package introsde.assignment.model;


import java.util.Date;

public class Measure {
    private Long mid;
    private Date dateRegistered;
    private String measureType;
    private String measureValue;
    private String measureValueType;

    public Measure(Long mid, Date dateRegistered, String measureType, String measureValue, String measureValueType) {
        this.mid = mid;
        this.dateRegistered = dateRegistered;
        this.measureType = measureType;
        this.measureValue = measureValue;
        this.measureValueType = measureValueType;
    }

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
}