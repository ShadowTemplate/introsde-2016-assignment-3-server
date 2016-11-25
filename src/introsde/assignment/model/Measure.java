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
}